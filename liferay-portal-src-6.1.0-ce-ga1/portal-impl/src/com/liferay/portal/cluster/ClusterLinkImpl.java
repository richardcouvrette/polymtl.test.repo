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

import com.liferay.portal.kernel.cluster.Address;
import com.liferay.portal.kernel.cluster.ClusterLink;
import com.liferay.portal.kernel.cluster.Priority;
import com.liferay.portal.kernel.cluster.messaging.ClusterForwardMessageListener;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.util.PropsUtil;
import com.liferay.portal.util.PropsValues;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.jgroups.ChannelException;
import org.jgroups.JChannel;

/**
 * @author Shuyang Zhou
 */
public class ClusterLinkImpl extends ClusterBase implements ClusterLink {

	@Override
	public void destroy() {
		if (!PropsValues.CLUSTER_LINK_ENABLED) {
			return;
		}

		for (JChannel jChannel : _transportChannels) {
			jChannel.close();
		}
	}

	public List<Address> getLocalTransportAddresses() {
		if (!PropsValues.CLUSTER_LINK_ENABLED) {
			return Collections.emptyList();
		}

		List<Address> addresses = new ArrayList<Address>(
			_localTransportAddresses.size());

		for (org.jgroups.Address address : _localTransportAddresses) {
			addresses.add(new AddressImpl(address));
		}

		return addresses;
	}

	public List<Address> getTransportAddresses(Priority priority) {
		if (!PropsValues.CLUSTER_LINK_ENABLED) {
			return Collections.emptyList();
		}

		JChannel jChannel = getChannel(priority);

		return getAddresses(jChannel);
	}

	public void sendMulticastMessage(Message message, Priority priority) {
		if (!PropsValues.CLUSTER_LINK_ENABLED) {
			return;
		}

		JChannel jChannel = getChannel(priority);

		try {
			jChannel.send(null, null, message);
		}
		catch (ChannelException ce) {
			_log.error("Unable to send multicast message " + message, ce);
		}
	}

	public void sendUnicastMessage(
		Address address, Message message, Priority priority) {

		if (!PropsValues.CLUSTER_LINK_ENABLED) {
			return;
		}

		org.jgroups.Address jGroupsAddress =
			(org.jgroups.Address)address.getRealAddress();

		JChannel jChannel = getChannel(priority);

		try {
			jChannel.send(jGroupsAddress, null, message);
		}
		catch (ChannelException ce) {
			_log.error("Unable to send unicast message:" + message, ce);
		}
	}

	public void setClusterForwardMessageListener(
		ClusterForwardMessageListener clusterForwardMessageListener) {

		_clusterForwardMessageListener = clusterForwardMessageListener;
	}

	protected JChannel getChannel(Priority priority) {
		int channelIndex =
			priority.ordinal() * _channelCount / _MAX_CHANNEL_COUNT;

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Select channel number " + channelIndex + " for priority " +
					priority);
		}

		return _transportChannels.get(channelIndex);
	}

	@Override
	protected void initChannels() throws ChannelException {
		Properties transportProperties = PropsUtil.getProperties(
			PropsKeys.CLUSTER_LINK_CHANNEL_PROPERTIES_TRANSPORT, true);

		_channelCount = transportProperties.size();

		if ((_channelCount <= 0) || (_channelCount > _MAX_CHANNEL_COUNT)) {
			throw new IllegalArgumentException(
				"Channel count must be between 1 and " + _MAX_CHANNEL_COUNT);
		}

		_localTransportAddresses = new ArrayList<org.jgroups.Address>(
			_channelCount);
		_transportChannels = new ArrayList<JChannel>(_channelCount);

		List<String> keys = new ArrayList<String>(_channelCount);

		for (Object key : transportProperties.keySet()) {
			keys.add((String)key);
		}

		Collections.sort(keys);

		for (int i = 0; i < keys.size(); i++) {
			String customName = keys.get(i);

			String value = transportProperties.getProperty(customName);

			JChannel jChannel = createJChannel(
				value,
				new ClusterForwardReceiver(
					_localTransportAddresses, _clusterForwardMessageListener),
					_LIFERAY_TRANSPORT_CHANNEL + i);

			_localTransportAddresses.add(jChannel.getLocalAddress());
			_transportChannels.add(jChannel);
		}
	}

	private static final String _LIFERAY_TRANSPORT_CHANNEL =
		"LIFERAY-TRANSPORT-CHANNEL-";

	private static final int _MAX_CHANNEL_COUNT = Priority.values().length;

	private static Log _log = LogFactoryUtil.getLog(ClusterLinkImpl.class);

	private int _channelCount;
	private ClusterForwardMessageListener _clusterForwardMessageListener;
	private List<org.jgroups.Address> _localTransportAddresses;
	private List<JChannel> _transportChannels;

}