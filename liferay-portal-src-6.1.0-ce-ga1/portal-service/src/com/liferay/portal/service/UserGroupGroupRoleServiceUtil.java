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

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the user group group role remote service. This utility wraps {@link com.liferay.portal.service.impl.UserGroupGroupRoleServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserGroupGroupRoleService
 * @see com.liferay.portal.service.base.UserGroupGroupRoleServiceBaseImpl
 * @see com.liferay.portal.service.impl.UserGroupGroupRoleServiceImpl
 * @generated
 */
public class UserGroupGroupRoleServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portal.service.impl.UserGroupGroupRoleServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static void addUserGroupGroupRoles(long userGroupId, long groupId,
		long[] roleIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().addUserGroupGroupRoles(userGroupId, groupId, roleIds);
	}

	public static void addUserGroupGroupRoles(long[] userGroupIds,
		long groupId, long roleId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().addUserGroupGroupRoles(userGroupIds, groupId, roleId);
	}

	public static void deleteUserGroupGroupRoles(long userGroupId,
		long groupId, long[] roleIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteUserGroupGroupRoles(userGroupId, groupId, roleIds);
	}

	public static void deleteUserGroupGroupRoles(long[] userGroupIds,
		long groupId, long roleId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteUserGroupGroupRoles(userGroupIds, groupId, roleId);
	}

	public static UserGroupGroupRoleService getService() {
		if (_service == null) {
			_service = (UserGroupGroupRoleService)PortalBeanLocatorUtil.locate(UserGroupGroupRoleService.class.getName());

			ReferenceRegistry.registerReference(UserGroupGroupRoleServiceUtil.class,
				"_service");
			MethodCache.remove(UserGroupGroupRoleService.class);
		}

		return _service;
	}

	public void setService(UserGroupGroupRoleService service) {
		MethodCache.remove(UserGroupGroupRoleService.class);

		_service = service;

		ReferenceRegistry.registerReference(UserGroupGroupRoleServiceUtil.class,
			"_service");
		MethodCache.remove(UserGroupGroupRoleService.class);
	}

	private static UserGroupGroupRoleService _service;
}