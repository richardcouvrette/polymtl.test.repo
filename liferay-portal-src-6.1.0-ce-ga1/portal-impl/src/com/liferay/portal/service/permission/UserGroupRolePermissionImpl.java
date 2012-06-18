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

package com.liferay.portal.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;

/**
 * @author Brian Wing Shun Chan
 * @author Julio Camarero
 */
public class UserGroupRolePermissionImpl implements UserGroupRolePermission {

	public void check(
			PermissionChecker permissionChecker, long groupId, long roleId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, groupId, roleId)) {
			throw new PrincipalException();
		}
	}

	public boolean contains(
			PermissionChecker permissionChecker, long groupId, long roleId)
		throws PortalException, SystemException {

		if (permissionChecker.isGroupOwner(groupId) ||
			GroupPermissionUtil.contains(
				permissionChecker, groupId, ActionKeys.ASSIGN_USER_ROLES) ||
			RolePermissionUtil.contains(
				permissionChecker, roleId, ActionKeys.ASSIGN_MEMBERS)) {

			return true;
		}
		else {
			return false;
		}
	}

}