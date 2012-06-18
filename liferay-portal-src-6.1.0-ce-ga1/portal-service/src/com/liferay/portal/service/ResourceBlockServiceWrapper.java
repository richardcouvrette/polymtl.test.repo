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
 * This class is a wrapper for {@link ResourceBlockService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ResourceBlockService
 * @generated
 */
public class ResourceBlockServiceWrapper implements ResourceBlockService,
	ServiceWrapper<ResourceBlockService> {
	public ResourceBlockServiceWrapper(
		ResourceBlockService resourceBlockService) {
		_resourceBlockService = resourceBlockService;
	}

	public void addCompanyScopePermission(long scopeGroupId, long companyId,
		java.lang.String name, long roleId, java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_resourceBlockService.addCompanyScopePermission(scopeGroupId,
			companyId, name, roleId, actionId);
	}

	public void addGroupScopePermission(long scopeGroupId, long companyId,
		long groupId, java.lang.String name, long roleId,
		java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_resourceBlockService.addGroupScopePermission(scopeGroupId, companyId,
			groupId, name, roleId, actionId);
	}

	public void addIndividualScopePermission(long companyId, long groupId,
		java.lang.String name, long primKey, long roleId,
		java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_resourceBlockService.addIndividualScopePermission(companyId, groupId,
			name, primKey, roleId, actionId);
	}

	public void removeAllGroupScopePermissions(long scopeGroupId,
		long companyId, java.lang.String name, long roleId,
		java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_resourceBlockService.removeAllGroupScopePermissions(scopeGroupId,
			companyId, name, roleId, actionId);
	}

	public void removeCompanyScopePermission(long scopeGroupId, long companyId,
		java.lang.String name, long roleId, java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_resourceBlockService.removeCompanyScopePermission(scopeGroupId,
			companyId, name, roleId, actionId);
	}

	public void removeGroupScopePermission(long scopeGroupId, long companyId,
		long groupId, java.lang.String name, long roleId,
		java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_resourceBlockService.removeGroupScopePermission(scopeGroupId,
			companyId, groupId, name, roleId, actionId);
	}

	public void removeIndividualScopePermission(long companyId, long groupId,
		java.lang.String name, long primKey, long roleId,
		java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_resourceBlockService.removeIndividualScopePermission(companyId,
			groupId, name, primKey, roleId, actionId);
	}

	public void setCompanyScopePermissions(long scopeGroupId, long companyId,
		java.lang.String name, long roleId,
		java.util.List<java.lang.String> actionIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_resourceBlockService.setCompanyScopePermissions(scopeGroupId,
			companyId, name, roleId, actionIds);
	}

	public void setGroupScopePermissions(long scopeGroupId, long companyId,
		long groupId, java.lang.String name, long roleId,
		java.util.List<java.lang.String> actionIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_resourceBlockService.setGroupScopePermissions(scopeGroupId, companyId,
			groupId, name, roleId, actionIds);
	}

	public void setIndividualScopePermissions(long companyId, long groupId,
		java.lang.String name, long primKey, long roleId,
		java.util.List<java.lang.String> actionIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_resourceBlockService.setIndividualScopePermissions(companyId, groupId,
			name, primKey, roleId, actionIds);
	}

	public void setIndividualScopePermissions(long companyId, long groupId,
		java.lang.String name, long primKey,
		java.util.Map<java.lang.Long, java.lang.String[]> roleIdsToActionIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_resourceBlockService.setIndividualScopePermissions(companyId, groupId,
			name, primKey, roleIdsToActionIds);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public ResourceBlockService getWrappedResourceBlockService() {
		return _resourceBlockService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedResourceBlockService(
		ResourceBlockService resourceBlockService) {
		_resourceBlockService = resourceBlockService;
	}

	public ResourceBlockService getWrappedService() {
		return _resourceBlockService;
	}

	public void setWrappedService(ResourceBlockService resourceBlockService) {
		_resourceBlockService = resourceBlockService;
	}

	private ResourceBlockService _resourceBlockService;
}