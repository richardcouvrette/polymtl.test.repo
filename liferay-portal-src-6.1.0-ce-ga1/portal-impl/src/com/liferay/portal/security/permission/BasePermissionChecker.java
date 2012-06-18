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

package com.liferay.portal.security.permission;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.admin.util.OmniadminUtil;

import java.util.Collections;
import java.util.List;

import javax.portlet.PortletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public abstract class BasePermissionChecker implements PermissionChecker {

	@Override
	public abstract PermissionChecker clone();

	public long getCompanyId() {
		return user.getCompanyId();
	}

	public List<Long> getGuestResourceBlockIds(
		long companyId, long groupId, String name, String actionId) {

		return Collections.emptyList();
	}

	public List<Long> getOwnerResourceBlockIds(
		long companyId, long groupId, String name, String actionId) {

		return Collections.emptyList();
	}

	public long getOwnerRoleId() {
		return ownerRole.getRoleId();
	}

	public List<Long> getResourceBlockIds(
		long companyId, long groupId, long userId, String name,
		String actionId) {

		return Collections.emptyList();
	}

	public long[] getRoleIds(long userId, long groupId) {
		return PermissionChecker.DEFAULT_ROLE_IDS;
	}

	public long getUserId() {
		return user.getUserId();
	}

	public boolean hasOwnerPermission(
		long companyId, String name, long primKey, long ownerId,
		String actionId) {

		return hasOwnerPermission(
			companyId, name, String.valueOf(primKey), ownerId, actionId);
	}

	public boolean hasPermission(
		long groupId, String name, long primKey, String actionId) {

		return hasPermission(groupId, name, String.valueOf(primKey), actionId);
	}

	public void init(User user, boolean checkGuest) {
		this.user = user;

		if (user.isDefaultUser()) {
			this.defaultUserId = user.getUserId();
			this.signedIn = false;
		}
		else {
			try {
				this.defaultUserId = UserLocalServiceUtil.getDefaultUserId(
					user.getCompanyId());
			}
			catch (Exception e) {
				_log.error(e, e);
			}

			this.signedIn = true;
		}

		this.checkGuest = checkGuest;

		try {
			this.ownerRole = RoleLocalServiceUtil.getRole(
				user.getCompanyId(), RoleConstants.OWNER);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	public boolean isCheckGuest() {
		return checkGuest;
	}

	/**
	 * @deprecated As of 6.1, renamed to {@link #isGroupAdmin(long)}
	 */
	public boolean isCommunityAdmin(long groupId) {
		return isGroupAdmin(groupId);
	}

	/**
	 * @deprecated As of 6.1, renamed to {@link #isGroupOwner(long)}
	 */
	public boolean isCommunityOwner(long groupId) {
		return isGroupOwner(groupId);
	}

	public boolean isOmniadmin() {
		if (omniadmin == null) {
			omniadmin = Boolean.valueOf(OmniadminUtil.isOmniadmin(getUserId()));
		}

		return omniadmin.booleanValue();
	}

	public boolean isSignedIn() {
		return signedIn;
	}

	public void resetValues() {
	}

	public void setCheckGuest(boolean checkGuest) {
		this.checkGuest = checkGuest;
	}

	public void setValues(PortletRequest portletRequest) {
	}

	protected User user;
	protected long defaultUserId;
	protected boolean signedIn;
	protected boolean checkGuest;
	protected Boolean omniadmin;
	protected Role ownerRole;

	private static Log _log = LogFactoryUtil.getLog(
		BasePermissionChecker.class);

}