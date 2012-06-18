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

package com.liferay.portal.staging.permission;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.staging.permission.StagingPermission;
import com.liferay.portal.model.Group;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.GroupLocalServiceUtil;

/**
 * @author Jorge Ferrer
 */
public class StagingPermissionImpl implements StagingPermission {

	public Boolean hasPermission(
		PermissionChecker permissionChecker, Group group, String className,
		long classPK, String portletId, String actionId) {

		try {
			return doHasPermission(
				permissionChecker, group, className, classPK, portletId,
				actionId);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return null;
	}

	public Boolean hasPermission(
		PermissionChecker permissionChecker, long groupId, String className,
		long classPK, String portletId, String actionId) {

		try {
			Group group = GroupLocalServiceUtil.getGroup(groupId);

			return doHasPermission(
				permissionChecker, group, className, classPK, portletId,
				actionId);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return null;
	}

	protected Boolean doHasPermission(
			PermissionChecker permissionChecker, Group group, String className,
			long classPK, String portletId, String actionId)
		throws Exception {

		if (!actionId.equals(ActionKeys.VIEW) &&
			!actionId.equals(ActionKeys.DELETE) && group.hasStagingGroup() &&
			group.isStagedPortlet(portletId)) {

			return false;
		}
		else {
			return null;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		StagingPermissionImpl.class);

}