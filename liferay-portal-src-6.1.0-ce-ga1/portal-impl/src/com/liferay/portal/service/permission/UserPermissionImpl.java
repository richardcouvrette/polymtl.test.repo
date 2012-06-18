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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PropsValues;

/**
 * @author Charles May
 * @author Jorge Ferrer
 */
public class UserPermissionImpl implements UserPermission {

	/**
	 * @deprecated Replaced by {@link #check(PermissionChecker, long, long[],
	 *             String)}
	 */
	public void check(
			PermissionChecker permissionChecker, long userId,
			long organizationId, long locationId, String actionId)
		throws PrincipalException {

		check(
			permissionChecker, userId, new long[] {organizationId, locationId},
			actionId);
	}

	public void check(
			PermissionChecker permissionChecker, long userId,
			long[] organizationIds, String actionId)
		throws PrincipalException {

		if (!contains(permissionChecker, userId, organizationIds, actionId)) {
			throw new PrincipalException();
		}
	}

	public void check(
			PermissionChecker permissionChecker, long userId, String actionId)
		throws PrincipalException {

		if (!contains(permissionChecker, userId, actionId)) {
			throw new PrincipalException();
		}
	}

	/**
	 * @deprecated Replaced by {@link #contains(PermissionChecker, long, long[],
	 *             String)}
	 */
	public boolean contains(
		PermissionChecker permissionChecker, long userId, long organizationId,
		long locationId, String actionId) {

		return contains(
			permissionChecker, userId, new long[] {organizationId, locationId},
			actionId);
	}

	public boolean contains(
		PermissionChecker permissionChecker, long userId,
		long[] organizationIds, String actionId) {

		if (actionId.equals(ActionKeys.IMPERSONATE) &&
			PortalUtil.isOmniadmin(userId) &&
			!permissionChecker.isOmniadmin()) {

			return false;
		}

		if (((PropsValues.PERMISSIONS_USER_CHECK_ALGORITHM == 5 ||
			  PropsValues.PERMISSIONS_USER_CHECK_ALGORITHM == 6) &&
			 (permissionChecker.hasOwnerPermission(
				permissionChecker.getCompanyId(), User.class.getName(), userId,
				userId, actionId))) ||
			(permissionChecker.getUserId() == userId)) {

			return true;
		}
		else if (permissionChecker.hasPermission(
					0, User.class.getName(), userId, actionId)) {

			return true;
		}
		else if (userId != ResourceConstants.PRIMKEY_DNE) {
			try {
				if (organizationIds == null) {
					User user = UserLocalServiceUtil.getUserById(userId);

					organizationIds = user.getOrganizationIds();
				}

				for (int i = 0; i < organizationIds.length; i++) {
					long organizationId = organizationIds[i];

					if (OrganizationPermissionUtil.contains(
							permissionChecker, organizationId,
							ActionKeys.MANAGE_USERS)) {

						return true;
					}
				}
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		return false;
	}

	public boolean contains(
		PermissionChecker permissionChecker, long userId, String actionId) {

		return contains(permissionChecker, userId, null, actionId);
	}

	private static Log _log = LogFactoryUtil.getLog(UserPermissionImpl.class);

}