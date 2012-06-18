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

package com.liferay.portal.kernel.cluster;

import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.io.Serializable;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Tina Tian
 */
public class ClusterRequest implements Serializable {

	public static ClusterRequest createClusterNotifyRequest(
		ClusterNode originatingClusterNode) {

		ClusterRequest clusterRequest = new ClusterRequest();

		clusterRequest.setClusterMessageType(ClusterMessageType.NOTIFY);
		clusterRequest.setMulticast(true);
		clusterRequest.setOriginatingClusterNode(originatingClusterNode);
		clusterRequest.setParallelized(true);
		clusterRequest.setSkipLocal(true);
		clusterRequest.setUuid(PortalUUIDUtil.generate());

		return clusterRequest;
	}

	public static ClusterRequest createMulticastRequest(
		MethodHandler methodHandler) {

		return createMulticastRequest(methodHandler, false);
	}

	public static ClusterRequest createMulticastRequest(
		MethodHandler methodHandler, boolean skipLocal) {

		ClusterRequest clusterRequest = new ClusterRequest();

		clusterRequest.setClusterMessageType(ClusterMessageType.EXECUTE);
		clusterRequest.setMethodHandler(methodHandler);
		clusterRequest.setMulticast(true);
		clusterRequest.setSkipLocal(skipLocal);
		clusterRequest.setUuid(PortalUUIDUtil.generate());

		return clusterRequest;
	}

	public static ClusterRequest createUnicastRequest(
		MethodHandler methodHandler, Address... targetClusterNodeAddresses) {

		ClusterRequest clusterRequest = new ClusterRequest();

		clusterRequest.addTargetClusterNodeAddresses(
			targetClusterNodeAddresses);
		clusterRequest.setClusterMessageType(ClusterMessageType.EXECUTE);
		clusterRequest.setMethodHandler(methodHandler);
		clusterRequest.setMulticast(false);
		clusterRequest.setSkipLocal(false);
		clusterRequest.setUuid(PortalUUIDUtil.generate());

		return clusterRequest;
	}

	public static ClusterRequest createUnicastRequest(
		MethodHandler methodHandler, String... targetClusterNodeIds) {

		ClusterRequest clusterRequest = new ClusterRequest();

		clusterRequest.addTargetClusterNodeIds(targetClusterNodeIds);
		clusterRequest.setClusterMessageType(ClusterMessageType.EXECUTE);
		clusterRequest.setMethodHandler(methodHandler);
		clusterRequest.setMulticast(false);
		clusterRequest.setSkipLocal(false);
		clusterRequest.setUuid(PortalUUIDUtil.generate());

		return clusterRequest;
	}

	public void addTargetClusterNodeAddresses(
		Address... targetClusterNodeAddresses) {

		if (_targetClusterNodeAddresses == null) {
			_targetClusterNodeAddresses = new HashSet<Address>(
				targetClusterNodeAddresses.length);
		}

		for (Address targetClusterNodeAddress : targetClusterNodeAddresses) {
			_targetClusterNodeAddresses.add(targetClusterNodeAddress);
		}
	}

	public void addTargetClusterNodeIds(String... targetClusterNodeIds) {
		if (_targetClusterNodeIds == null) {
			_targetClusterNodeIds = new HashSet<String>(
				targetClusterNodeIds.length);
		}

		for (String targetClusterNodeId : targetClusterNodeIds) {
			_targetClusterNodeIds.add(targetClusterNodeId);
		}
	}

	public String getBeanIdentifier() {
		return _beanIdentifier;
	}

	public ClusterMessageType getClusterMessageType() {
		return _clusterMessageType;
	}

	public MethodHandler getMethodHandler() {
		return _methodHandler;
	}

	public ClusterNode getOriginatingClusterNode() {
		return _originatingClusterNode;
	}

	public String getServletContextName() {
		return _servletContextName;
	}

	public Collection<Address> getTargetClusterNodeAddresses() {
		return _targetClusterNodeAddresses;
	}

	public Collection<String> getTargetClusterNodeIds() {
		return _targetClusterNodeIds;
	}

	public String getUuid() {
		return _uuid;
	}

	public boolean isFireAndForget() {
		return _fireAndForget;
	}

	public boolean isMulticast() {
		return _multicast;
	}

	public boolean isParallelized() {
		return _parallelized;
	}

	public boolean isSkipLocal() {
		return _skipLocal;
	}

	public void setBeanIdentifier(String beanIdentifier) {
		_beanIdentifier = beanIdentifier;
	}

	public void setClusterMessageType(ClusterMessageType clusterMessageType) {
		_clusterMessageType = clusterMessageType;
	}

	public void setFireAndForget(boolean fireAndForget) {
		_fireAndForget = fireAndForget;
	}

	public void setMethodHandler(MethodHandler methodHandler) {
		_methodHandler = methodHandler;
	}

	public void setMulticast(boolean multicast) {
		_multicast = multicast;
	}

	public void setOriginatingClusterNode(ClusterNode originatingClusterNode) {
		_originatingClusterNode = originatingClusterNode;
	}

	public void setParallelized(boolean parallelized) {
		_parallelized = parallelized;
	}

	public void setServletContextName(String servletContextName) {
		_servletContextName = servletContextName;
	}

	public void setSkipLocal(boolean skipLocal) {
		_skipLocal = skipLocal;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{clusterMessageType=");
		sb.append(_clusterMessageType);
		sb.append(", methodHandler=");
		sb.append(_methodHandler);
		sb.append(", multicast=");
		sb.append(_multicast);

		if (_clusterMessageType.equals(ClusterMessageType.NOTIFY)) {
			sb.append(", originatingClusterNode=");
			sb.append(_originatingClusterNode);
		}

		sb.append(", servletContextName=");
		sb.append(_servletContextName);
		sb.append(", parallelized=");
		sb.append(_parallelized);
		sb.append(", skipLocal=");
		sb.append(_skipLocal);
		sb.append(", uuid=");
		sb.append(_uuid);
		sb.append("}");

		return sb.toString();
	}

	private ClusterRequest() {
	}

	private String _beanIdentifier;
	private ClusterMessageType _clusterMessageType;
	private boolean _fireAndForget;
	private MethodHandler _methodHandler;
	private boolean _multicast;
	private ClusterNode _originatingClusterNode;
	private boolean _parallelized;
	private String _servletContextName;
	private boolean _skipLocal;
	private Set<Address> _targetClusterNodeAddresses;
	private Set<String> _targetClusterNodeIds;
	private String _uuid;

}