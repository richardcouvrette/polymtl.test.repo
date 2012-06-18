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

package com.liferay.portal.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.UserTracker;
import com.liferay.portal.model.UserTrackerPath;
import com.liferay.portal.service.base.UserTrackerLocalServiceBaseImpl;
import com.liferay.portal.util.PropsValues;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class UserTrackerLocalServiceImpl
	extends UserTrackerLocalServiceBaseImpl {

	public UserTracker addUserTracker(
			long companyId, long userId, Date modifiedDate, String sessionId,
			String remoteAddr, String remoteHost, String userAgent,
			List<UserTrackerPath> userTrackerPaths)
		throws SystemException {

		if (PropsValues.SESSION_TRACKER_PERSISTENCE_ENABLED) {
			long userTrackerId = counterLocalService.increment(
				UserTracker.class.getName());

			UserTracker userTracker =
				userTrackerPersistence.create(userTrackerId);

			userTracker.setCompanyId(companyId);
			userTracker.setUserId(userId);
			userTracker.setModifiedDate(modifiedDate);
			userTracker.setSessionId(sessionId);
			userTracker.setRemoteAddr(remoteAddr);
			userTracker.setRemoteHost(remoteHost);
			userTracker.setUserAgent(userAgent);

			userTrackerPersistence.update(userTracker, false);

			Iterator<UserTrackerPath> itr = userTrackerPaths.iterator();

			while (itr.hasNext()) {
				UserTrackerPath userTrackerPath = itr.next();

				long pathId = counterLocalService.increment(
					UserTrackerPath.class.getName());

				userTrackerPath.setUserTrackerPathId(pathId);
				userTrackerPath.setUserTrackerId(userTrackerId);

				userTrackerPathPersistence.update(userTrackerPath, false);
			}

			return userTracker;
		}
		else {
			return null;
		}
	}

	@Override
	public void deleteUserTracker(long userTrackerId)
		throws PortalException, SystemException {

		UserTracker userTracker = userTrackerPersistence.findByPrimaryKey(
			userTrackerId);

		deleteUserTracker(userTracker);
	}

	@Override
	public void deleteUserTracker(UserTracker userTracker)
		throws SystemException {

		// Paths

		userTrackerPathPersistence.removeByUserTrackerId(
			userTracker.getUserTrackerId());

		// User tracker

		userTrackerPersistence.remove(userTracker);
	}

	public List<UserTracker> getUserTrackers(long companyId, int start, int end)
		throws SystemException {

		return userTrackerPersistence.findByCompanyId(companyId, start, end);
	}

}