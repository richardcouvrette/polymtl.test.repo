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

package com.liferay.portal.service;

/**
 * <p>
 * This class is a wrapper for {@link UserGroupGroupRoleService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       UserGroupGroupRoleService
 * @generated
 */
public class UserGroupGroupRoleServiceWrapper
	implements UserGroupGroupRoleService,
		ServiceWrapper<UserGroupGroupRoleService> {
	public UserGroupGroupRoleServiceWrapper(
		UserGroupGroupRoleService userGroupGroupRoleService) {
		_userGroupGroupRoleService = userGroupGroupRoleService;
	}

	public void addUserGroupGroupRoles(long userGroupId, long groupId,
		long[] roleIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_userGroupGroupRoleService.addUserGroupGroupRoles(userGroupId, groupId,
			roleIds);
	}

	public void addUserGroupGroupRoles(long[] userGroupIds, long groupId,
		long roleId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_userGroupGroupRoleService.addUserGroupGroupRoles(userGroupIds,
			groupId, roleId);
	}

	public void deleteUserGroupGroupRoles(long userGroupId, long groupId,
		long[] roleIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_userGroupGroupRoleService.deleteUserGroupGroupRoles(userGroupId,
			groupId, roleIds);
	}

	public void deleteUserGroupGroupRoles(long[] userGroupIds, long groupId,
		long roleId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_userGroupGroupRoleService.deleteUserGroupGroupRoles(userGroupIds,
			groupId, roleId);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public UserGroupGroupRoleService getWrappedUserGroupGroupRoleService() {
		return _userGroupGroupRoleService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedUserGroupGroupRoleService(
		UserGroupGroupRoleService userGroupGroupRoleService) {
		_userGroupGroupRoleService = userGroupGroupRoleService;
	}

	public UserGroupGroupRoleService getWrappedService() {
		return _userGroupGroupRoleService;
	}

	public void setWrappedService(
		UserGroupGroupRoleService userGroupGroupRoleService) {
		_userGroupGroupRoleService = userGroupGroupRoleService;
	}

	private UserGroupGroupRoleService _userGroupGroupRoleService;
}