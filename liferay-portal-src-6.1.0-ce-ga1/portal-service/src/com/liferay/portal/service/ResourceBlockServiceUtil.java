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
 * The utility for the resource block remote service. This utility wraps {@link com.liferay.portal.service.impl.ResourceBlockServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ResourceBlockService
 * @see com.liferay.portal.service.base.ResourceBlockServiceBaseImpl
 * @see com.liferay.portal.service.impl.ResourceBlockServiceImpl
 * @generated
 */
public class ResourceBlockServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portal.service.impl.ResourceBlockServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static void addCompanyScopePermission(long scopeGroupId,
		long companyId, java.lang.String name, long roleId,
		java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addCompanyScopePermission(scopeGroupId, companyId, name, roleId,
			actionId);
	}

	public static void addGroupScopePermission(long scopeGroupId,
		long companyId, long groupId, java.lang.String name, long roleId,
		java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addGroupScopePermission(scopeGroupId, companyId, groupId, name,
			roleId, actionId);
	}

	public static void addIndividualScopePermission(long companyId,
		long groupId, java.lang.String name, long primKey, long roleId,
		java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addIndividualScopePermission(companyId, groupId, name, primKey,
			roleId, actionId);
	}

	public static void removeAllGroupScopePermissions(long scopeGroupId,
		long companyId, java.lang.String name, long roleId,
		java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.removeAllGroupScopePermissions(scopeGroupId, companyId, name,
			roleId, actionId);
	}

	public static void removeCompanyScopePermission(long scopeGroupId,
		long companyId, java.lang.String name, long roleId,
		java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.removeCompanyScopePermission(scopeGroupId, companyId, name,
			roleId, actionId);
	}

	public static void removeGroupScopePermission(long scopeGroupId,
		long companyId, long groupId, java.lang.String name, long roleId,
		java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.removeGroupScopePermission(scopeGroupId, companyId, groupId, name,
			roleId, actionId);
	}

	public static void removeIndividualScopePermission(long companyId,
		long groupId, java.lang.String name, long primKey, long roleId,
		java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.removeIndividualScopePermission(companyId, groupId, name, primKey,
			roleId, actionId);
	}

	public static void setCompanyScopePermissions(long scopeGroupId,
		long companyId, java.lang.String name, long roleId,
		java.util.List<java.lang.String> actionIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.setCompanyScopePermissions(scopeGroupId, companyId, name, roleId,
			actionIds);
	}

	public static void setGroupScopePermissions(long scopeGroupId,
		long companyId, long groupId, java.lang.String name, long roleId,
		java.util.List<java.lang.String> actionIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.setGroupScopePermissions(scopeGroupId, companyId, groupId, name,
			roleId, actionIds);
	}

	public static void setIndividualScopePermissions(long companyId,
		long groupId, java.lang.String name, long primKey, long roleId,
		java.util.List<java.lang.String> actionIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.setIndividualScopePermissions(companyId, groupId, name, primKey,
			roleId, actionIds);
	}

	public static void setIndividualScopePermissions(long companyId,
		long groupId, java.lang.String name, long primKey,
		java.util.Map<java.lang.Long, java.lang.String[]> roleIdsToActionIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.setIndividualScopePermissions(companyId, groupId, name, primKey,
			roleIdsToActionIds);
	}

	public static ResourceBlockService getService() {
		if (_service == null) {
			_service = (ResourceBlockService)PortalBeanLocatorUtil.locate(ResourceBlockService.class.getName());

			ReferenceRegistry.registerReference(ResourceBlockServiceUtil.class,
				"_service");
			MethodCache.remove(ResourceBlockService.class);
		}

		return _service;
	}

	public void setService(ResourceBlockService service) {
		MethodCache.remove(ResourceBlockService.class);

		_service = service;

		ReferenceRegistry.registerReference(ResourceBlockServiceUtil.class,
			"_service");
		MethodCache.remove(ResourceBlockService.class);
	}

	private static ResourceBlockService _service;
}