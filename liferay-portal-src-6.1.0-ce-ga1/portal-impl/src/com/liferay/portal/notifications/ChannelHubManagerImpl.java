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

package com.liferay.portal.notifications;

import com.liferay.portal.kernel.notifications.Channel;
import com.liferay.portal.kernel.notifications.ChannelException;
import com.liferay.portal.kernel.notifications.ChannelHub;
import com.liferay.portal.kernel.notifications.ChannelHubManager;
import com.liferay.portal.kernel.notifications.ChannelListener;
import com.liferay.portal.kernel.notifications.DuplicateChannelHubException;
import com.liferay.portal.kernel.notifications.NotificationEvent;
import com.liferay.portal.kernel.notifications.UnknownChannelHubException;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author Edward Han
 * @author Brian Wing Shun
 * @author Shuyang Zhou
 */
public class ChannelHubManagerImpl implements ChannelHubManager {

	public void confirmDelivery(
			long companyId, long userId,
			Collection<String> notificationEventUuids)
		throws ChannelException {

		confirmDelivery(companyId, userId, notificationEventUuids, false);
	}

	public void confirmDelivery(
			long companyId, long userId,
			Collection<String> notificationEventUuids, boolean archive)
		throws ChannelException {

		ChannelHub channelHub = getChannelHub(companyId);

		channelHub.confirmDelivery(userId, notificationEventUuids, archive);
	}

	public void confirmDelivery(
			long companyId, long userId, String notificationEventUuid)
		throws ChannelException {

		confirmDelivery(companyId, userId, notificationEventUuid, false);
	}

	public void confirmDelivery(
			long companyId, long userId, String notificationEventUuid,
			boolean archive)
		throws ChannelException {

		ChannelHub channelHub = getChannelHub(companyId);

		channelHub.confirmDelivery(userId, notificationEventUuid, archive);
	}

	public Channel createChannel(long companyId, long userId)
		throws ChannelException {

		ChannelHub channelHub = getChannelHub(companyId);

		return channelHub.createChannel(userId);
	}

	public ChannelHub createChannelHub(long companyId)
		throws ChannelException {

		ChannelHub channelHub = _channelHub.clone(companyId);

		if (_channelHubs.putIfAbsent(companyId, channelHub) != null) {
			throw new DuplicateChannelHubException(
				"Channel already exists with company id " + companyId);
		}

		return channelHub;
	}

	public void deleteUserNotificiationEvent(
			long companyId, long userId, String notificationEventUuid)
		throws ChannelException {

		ChannelHub channelHub = getChannelHub(companyId);

		channelHub.deleteUserNotificiationEvent(userId, notificationEventUuid);
	}

	public void deleteUserNotificiationEvents(
			long companyId, long userId,
			Collection<String> notificationEventUuids)
		throws ChannelException {

		ChannelHub channelHub = getChannelHub(companyId);

		channelHub.deleteUserNotificiationEvents(
			userId, notificationEventUuids);
	}

	public void destroyChannel(long companyId, long userId)
		throws ChannelException {

		ChannelHub channelHub = getChannelHub(companyId);

		channelHub.destroyChannel(userId);
	}

	public void destroyChannelHub(long companyId) throws ChannelException {
		ChannelHub channelHub = _channelHubs.remove(companyId);

		if (channelHub != null) {
			channelHub.destroy();
		}
	}

	public ChannelHub fetchChannelHub(long companyId) throws ChannelException {
		return fetchChannelHub(companyId, false);
	}

	public ChannelHub fetchChannelHub(long companyId, boolean createIfAbsent)
		throws ChannelException {

		ChannelHub channelHub = _channelHubs.get(companyId);

		if (channelHub == null) {
			synchronized(_channelHubs) {
				channelHub = _channelHubs.get(companyId);

				if (channelHub == null) {
					if (createIfAbsent) {
						channelHub = createChannelHub(companyId);
					}
				}
			}
		}

		return channelHub;
	}

	public void flush() throws ChannelException {
		for (ChannelHub channelHub : _channelHubs.values()) {
			channelHub.flush();
		}
	}

	public void flush(long companyId) throws ChannelException {
		ChannelHub channelHub = fetchChannelHub(companyId);

		if (channelHub != null) {
			channelHub.flush();
		}
	}

	public void flush(long companyId, long userId, long timestamp)
		throws ChannelException {

		ChannelHub channelHub = fetchChannelHub(companyId);

		if (channelHub != null) {
			channelHub.flush(userId, timestamp);
		}
	}

	public Channel getChannel(long companyId, long userId)
		throws ChannelException {

		return getChannel(companyId, userId, false);
	}

	public Channel getChannel(
			long companyId, long userId, boolean createIfAbsent)
		throws ChannelException {

		ChannelHub channelHub = getChannelHub(companyId, createIfAbsent);

		return channelHub.getChannel(userId, createIfAbsent);
	}

	public ChannelHub getChannelHub(long companyId) throws ChannelException {
		return getChannelHub(companyId, false);
	}

	public ChannelHub getChannelHub(long companyId, boolean createIfAbsent)
		throws ChannelException {

		ChannelHub channelHub = fetchChannelHub(companyId, createIfAbsent);

		if (channelHub == null) {
			throw new UnknownChannelHubException(
				"No channel exists with company id " + companyId);
		}

		return channelHub;
	}

	public List<NotificationEvent> getNotificationEvents(
			long companyId, long userId)
		throws ChannelException {

		ChannelHub channelHub = getChannelHub(companyId);

		return channelHub.getNotificationEvents(userId);
	}

	public List<NotificationEvent> getNotificationEvents(
			long compnayId, long userId, boolean flush)
		throws ChannelException {

		return getChannelHub(compnayId).getNotificationEvents(userId, flush);
	}

	public Collection<Long> getUserIds(long companyId) throws ChannelException {
		ChannelHub channelHub = getChannelHub(companyId);

		return channelHub.getUserIds();
	}

	public void registerChannelListener(
			long companyId, long userId, ChannelListener channelListener)
		throws ChannelException {

		ChannelHub channelHub = getChannelHub(companyId);

		channelHub.registerChannelListener(userId, channelListener);
	}

	public void removeTransientNotificationEvents(
			long companyId, long userId,
			Collection<NotificationEvent> notificationEvents)
		throws ChannelException {

		ChannelHub channelHub = getChannelHub(companyId);

		channelHub.removeTransientNotificationEvents(
			userId, notificationEvents);
	}

	public void removeTransientNotificationEventsByUuid(
			long companyId, long userId,
			Collection<String> notificationEventUuids)
		throws ChannelException {

		ChannelHub channelHub = getChannelHub(companyId);

		channelHub.removeTransientNotificationEventsByUuid(
			userId, notificationEventUuids);
	}

	public void sendNotificationEvent(
			long companyId, long userId, NotificationEvent notificationEvent)
		throws ChannelException {

		ChannelHub channelHub = getChannelHub(companyId);

		channelHub.sendNotificationEvent(userId, notificationEvent);
	}

	public void sendNotificationEvents(
			long companyId, long userId,
			Collection<NotificationEvent> notificationEvents)
		throws ChannelException {

		ChannelHub channelHub = getChannelHub(companyId);

		channelHub.sendNotificationEvents(userId, notificationEvents);
	}

	public void setChannelHubPrototype(ChannelHub channelHub) {
		_channelHub = channelHub;
	}

	public void unregisterChannelListener(
			long companyId, long userId, ChannelListener channelListener)
		throws ChannelException {

		ChannelHub channelHub = getChannelHub(companyId);

		channelHub.unregisterChannelListener(userId, channelListener);
	}

	private ChannelHub _channelHub;
	private ConcurrentMap<Long, ChannelHub> _channelHubs =
		new ConcurrentHashMap<Long, ChannelHub>();

}