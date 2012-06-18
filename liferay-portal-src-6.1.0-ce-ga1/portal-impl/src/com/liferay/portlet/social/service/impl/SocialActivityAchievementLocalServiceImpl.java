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

package com.liferay.portlet.social.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portlet.social.model.SocialAchievement;
import com.liferay.portlet.social.model.SocialActivityAchievement;
import com.liferay.portlet.social.service.base.SocialActivityAchievementLocalServiceBaseImpl;

import java.util.List;

/**
 * @author Zsolt Berentey
 * @author Brian Wing Shun Chan
 */
public class SocialActivityAchievementLocalServiceImpl
	extends SocialActivityAchievementLocalServiceBaseImpl {

	public void addActivityAchievement(
			long userId, long groupId, SocialAchievement achievement)
		throws PortalException, SystemException {

		SocialActivityAchievement activityAchievement =
			socialActivityAchievementPersistence.fetchByG_U_N(
				groupId, userId, achievement.getName());

		if (activityAchievement != null) {
			return;
		}

		User user = userPersistence.findByPrimaryKey(userId);

		long activityAchievementId = counterLocalService.increment();

		activityAchievement = socialActivityAchievementPersistence.create(
			activityAchievementId);

		activityAchievement.setGroupId(groupId);
		activityAchievement.setCompanyId(user.getCompanyId());
		activityAchievement.setUserId(userId);
		activityAchievement.setCreateDate(System.currentTimeMillis());

		int count = socialActivityAchievementPersistence.countByG_N(
			groupId, achievement.getName());

		if (count == 0) {
			activityAchievement.setFirstInGroup(true);
		}

		activityAchievement.setName(achievement.getName());

		socialActivityAchievementPersistence.update(activityAchievement, false);

		socialActivityCounterLocalService.incrementUserAchievementCounter(
			userId, groupId);
	}

	public SocialActivityAchievement fetchUserAchievement(
			long userId, long groupId, String name)
		throws SystemException {

		return socialActivityAchievementPersistence.fetchByG_U_N(
			groupId, userId, name);
	}

	public List<SocialActivityAchievement> getGroupAchievements(long groupId)
		throws SystemException {

		return socialActivityAchievementPersistence.findByGroupId(groupId);
	}

	public List<SocialActivityAchievement> getGroupAchievements(
			long groupId, String name)
		throws SystemException {

		return socialActivityAchievementPersistence.findByG_N(groupId, name);
	}

	public int getGroupAchievementsCount(long groupId)
		throws SystemException {

		return socialActivityAchievementPersistence.countByGroupId(groupId);
	}

	public int getGroupAchievementsCount(long groupId, String name)
		throws SystemException {

		return socialActivityAchievementPersistence.countByG_N(groupId, name);
	}

	public List<SocialActivityAchievement> getGroupFirstAchievements(
			long groupId)
		throws SystemException {

		return socialActivityAchievementPersistence.findByG_F(groupId, true);
	}

	public int getGroupFirstAchievementsCount(long groupId)
		throws SystemException {

		return socialActivityAchievementPersistence.countByG_F(groupId, true);
	}

	public int getUserAchievementCount(long userId, long groupId, String name)
		throws SystemException {

		return socialActivityAchievementPersistence.countByG_U_N(
			groupId, userId, name);
	}

	public List<SocialActivityAchievement> getUserAchievements(
			long userId, long groupId, String name)
		throws SystemException {

		return socialActivityAchievementPersistence.findByG_U(groupId, userId);
	}

}