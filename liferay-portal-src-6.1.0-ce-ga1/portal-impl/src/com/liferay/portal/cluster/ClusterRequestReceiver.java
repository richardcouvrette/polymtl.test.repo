/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.cluster;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.cluster.Address;
import com.liferay.portal.kernel.cluster.ClusterException;
import com.liferay.portal.kernel.cluster.ClusterMessageType;
import com.liferay.portal.kernel.cluster.ClusterNode;
import com.liferay.portal.kernel.cluster.ClusterNodeResponse;
import com.liferay.portal.kernel.cluster.ClusterRequest;
import com.liferay.portal.kernel.cluster.FutureClusterResponses;
import com.liferay.portal.kernel.executor.PortalExecutorManagerUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.NamedThreadFactory;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PropsValues;

import java.io.Serializable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jgroups.Channel;
import org.jgroups.ChannelException;
import org.jgroups.Message;

/**
 * @author Michael C. Han
 * @author Tina Tian
 */
public class ClusterRequestReceiver extends BaseReceiver {

	public ClusterRequestReceiver(ClusterExecutorImpl clusterExecutorImpl) {
		_clusterExecutorImpl = clusterExecutorImpl;
	}

	public void destroy() {
		_parallelExecutorService.shutdownNow();
		_serialExecutorService.shutdownNow();
	}

	public void initialize() {
		_parallelExecutorService = PortalExecutorManagerUtil.getPortalExecutor(
			ClusterRequestReceiver.class.getName() + "_parallel");
		_serialExecutorService = Executors.newSingleThreadExecutor(
			new NamedThreadFactory(
				ClusterRequestReceiver.class.getName() + "_serial",
				Thread.NORM_PRIORITY, PortalClassLoaderUtil.getClassLoader()));
	}

	@Override
	public void receive(Message message) {
		org.jgroups.Address sourceAddress = message.getSrc();

		Channel controlChannel = _clusterExecutorImpl.getControlChannel();

		org.jgroups.Address localAddress = controlChannel.getAddress();

		Object obj = message.getObject();

		if (obj == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("Message content is null");
			}

			return;
		}

		if (localAddress.equals(sourceAddress)) {
			boolean isProcessed = processLocalMessage(obj, sourceAddress);

			if (isProcessed) {
				return;
			}
		}

		if (obj instanceof ClusterRequest) {
			ClusterRequest clusterRequest = (ClusterRequest)obj;

			RequestTask requestTask = new RequestTask(
				clusterRequest, sourceAddress, localAddress);

			if (clusterRequest.isParallelized()) {
				_parallelExecutorService.execute(requestTask);
			}
			else {
				_serialExecutorService.execute(requestTask);
			}
		}
		else if (obj instanceof ClusterNodeResponse) {
			ClusterNodeResponse clusterNodeResponse = (ClusterNodeResponse)obj;

			processClusterResponse(
				clusterNodeResponse, sourceAddress, localAddress);
		}
		else if (_log.isWarnEnabled()) {
			_log.warn(
				"Unable to process message content of type " + obj.getClass());
		}
	}

	protected Object invoke(
			String servletContextName, String beanIdentifier,
			MethodHandler methodHandler)
		throws Exception {

		if (servletContextName == null) {
			if (Validator.isNull(beanIdentifier)) {
				return methodHandler.invoke(true);
			}
			else {
				Object bean = PortalBeanLocatorUtil.locate(beanIdentifier);

				return methodHandler.invoke(bean);
			}
		}

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			ClassLoader classLoader =
				(ClassLoader)PortletBeanLocatorUtil.locate(
					servletContextName, "portletClassLoader");

			currentThread.setContextClassLoader(classLoader);

			if (Validator.isNull(beanIdentifier)) {
				return methodHandler.invoke(true);
			}
			else {
				Object bean = PortletBeanLocatorUtil.locate(
					servletContextName, beanIdentifier);

				return methodHandler.invoke(bean);
			}
		}
		catch (Exception e) {
			throw e;
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}
	}

	protected void processClusterRequest(
		ClusterRequest clusterRequest, org.jgroups.Address sourceAddress,
		org.jgroups.Address localAddress) {

		ClusterMessageType clusterMessageType =
			clusterRequest.getClusterMessageType();

		if (clusterMessageType.equals(ClusterMessageType.NOTIFY)) {
			ClusterNode originatingClusterNode =
				clusterRequest.getOriginatingClusterNode();

			if (originatingClusterNode != null) {
				long expirationTime =
					System.currentTimeMillis() +
						(PropsValues.CLUSTER_EXECUTOR_HEARTBEAT_INTERVAL * 2);

				_clusterExecutorImpl.notify(
					new AddressImpl(sourceAddress), originatingClusterNode,
					expirationTime);
			}
			else {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Content of notify message does not contain cluster " +
							"node information");
				}
			}

			return;
		}

		ClusterNodeResponse clusterNodeResponse = new ClusterNodeResponse();

		Address address = new AddressImpl(localAddress);

		clusterNodeResponse.setAddress(address);

		clusterNodeResponse.setClusterMessageType(ClusterMessageType.EXECUTE);

		try {
			ClusterNode localClusterNode =
				_clusterExecutorImpl.getLocalClusterNode();

			clusterNodeResponse.setClusterNode(localClusterNode);
		}
		catch (Exception e) {
			clusterNodeResponse.setException(e);
		}

		clusterNodeResponse.setMulticast(clusterRequest.isMulticast());
		clusterNodeResponse.setUuid(clusterRequest.getUuid());

		MethodHandler methodHandler = clusterRequest.getMethodHandler();

		if (methodHandler != null) {
			try {
				ClusterInvokeThreadLocal.setEnabled(false);

				Object returnValue = invoke(
					clusterRequest.getServletContextName(),
					clusterRequest.getBeanIdentifier(), methodHandler);

				if (returnValue instanceof Serializable) {
					clusterNodeResponse.setResult(returnValue);
				}
				else if (returnValue != null) {
					clusterNodeResponse.setException(
						new ClusterException(
							"Return value is not serializable"));
				}
			}
			catch (Exception e) {
				clusterNodeResponse.setException(e);

				_log.error("Failed to invoke method " + methodHandler, e);
			}
			finally {
				ClusterInvokeThreadLocal.setEnabled(true);
			}
		}
		else {
			clusterNodeResponse.setException(
				new ClusterException(
					"Payload is not of type " + MethodHandler.class.getName()));
		}

		Channel controlChannel = _clusterExecutorImpl.getControlChannel();

		try {
			controlChannel.send(
				sourceAddress, localAddress, clusterNodeResponse);
		}
		catch (ChannelException ce) {
			_log.error(
				"Unable to send response message " + clusterNodeResponse, ce);
		}
		catch (Throwable t) {
			_log.error(t, t);
		}
	}

	protected void processClusterResponse(
		ClusterNodeResponse clusterNodeResponse,
		org.jgroups.Address sourceAddress, org.jgroups.Address localAddress) {

		String uuid = clusterNodeResponse.getUuid();

		FutureClusterResponses futureClusterResponses =
			_clusterExecutorImpl.getExecutionResults(uuid);

		if (futureClusterResponses == null) {
			if (_log.isInfoEnabled()) {
				_log.info("Unable to find response container for " + uuid);
			}

			return;
		}

		Address address = new AddressImpl(sourceAddress);

		if (futureClusterResponses.expectsReply(address)) {
			futureClusterResponses.addClusterNodeResponse(clusterNodeResponse);
		}
		else {
			if (_log.isWarnEnabled()) {
				_log.warn("Unknown uuid " + uuid + " from " + sourceAddress);
			}
		}
	}

	protected boolean processLocalMessage(
		Object message, org.jgroups.Address sourceAddress) {

		if (message instanceof ClusterRequest) {
			ClusterRequest clusterRequest = (ClusterRequest)message;

			if (clusterRequest.isSkipLocal()) {
				return true;
			}
		}

		if (_clusterExecutorImpl.isShortcutLocalMethod()) {
			return true;
		}

		return false;
	}

	private static Log _log = LogFactoryUtil.getLog(
		ClusterRequestReceiver.class);

	private ClusterExecutorImpl _clusterExecutorImpl;
	private ExecutorService _parallelExecutorService;
	private ExecutorService _serialExecutorService;

	private class RequestTask implements Runnable {

		public RequestTask(
			ClusterRequest clusterRequest, org.jgroups.Address sourceAddress,
			org.jgroups.Address localAddress) {

			_clusterRequest = clusterRequest;
			_sourceAddress = sourceAddress;
			_localAddress = localAddress;
		}

		public void run() {
			processClusterRequest(
				_clusterRequest, _sourceAddress, _localAddress);
		}

		private ClusterRequest _clusterRequest;
		private org.jgroups.Address _localAddress;
		private org.jgroups.Address _sourceAddress;

	}

}