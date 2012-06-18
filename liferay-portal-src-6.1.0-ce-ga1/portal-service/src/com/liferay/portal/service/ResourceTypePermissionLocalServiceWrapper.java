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
 * This class is a wrapper for {@link ResourceTypePermissionLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ResourceTypePermissionLocalService
 * @generated
 */
public class ResourceTypePermissionLocalServiceWrapper
	implements ResourceTypePermissionLocalService,
		ServiceWrapper<ResourceTypePermissionLocalService> {
	public ResourceTypePermissionLocalServiceWrapper(
		ResourceTypePermissionLocalService resourceTypePermissionLocalService) {
		_resourceTypePermissionLocalService = resourceTypePermissionLocalService;
	}

	/**
	* Adds the resource type permission to the database. Also notifies the appropriate model listeners.
	*
	* @param resourceTypePermission the resource type permission
	* @return the resource type permission that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.ResourceTypePermission addResourceTypePermission(
		com.liferay.portal.model.ResourceTypePermission resourceTypePermission)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _resourceTypePermissionLocalService.addResourceTypePermission(resourceTypePermission);
	}

	/**
	* Creates a new resource type permission with the primary key. Does not add the resource type permission to the database.
	*
	* @param resourceTypePermissionId the primary key for the new resource type permission
	* @return the new resource type permission
	*/
	public com.liferay.portal.model.ResourceTypePermission createResourceTypePermission(
		long resourceTypePermissionId) {
		return _resourceTypePermissionLocalService.createResourceTypePermission(resourceTypePermissionId);
	}

	/**
	* Deletes the resource type permission with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param resourceTypePermissionId the primary key of the resource type permission
	* @throws PortalException if a resource type permission with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteResourceTypePermission(long resourceTypePermissionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_resourceTypePermissionLocalService.deleteResourceTypePermission(resourceTypePermissionId);
	}

	/**
	* Deletes the resource type permission from the database. Also notifies the appropriate model listeners.
	*
	* @param resourceTypePermission the resource type permission
	* @throws SystemException if a system exception occurred
	*/
	public void deleteResourceTypePermission(
		com.liferay.portal.model.ResourceTypePermission resourceTypePermission)
		throws com.liferay.portal.kernel.exception.SystemException {
		_resourceTypePermissionLocalService.deleteResourceTypePermission(resourceTypePermission);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _resourceTypePermissionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _resourceTypePermissionLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _resourceTypePermissionLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _resourceTypePermissionLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.portal.model.ResourceTypePermission fetchResourceTypePermission(
		long resourceTypePermissionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _resourceTypePermissionLocalService.fetchResourceTypePermission(resourceTypePermissionId);
	}

	/**
	* Returns the resource type permission with the primary key.
	*
	* @param resourceTypePermissionId the primary key of the resource type permission
	* @return the resource type permission
	* @throws PortalException if a resource type permission with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.ResourceTypePermission getResourceTypePermission(
		long resourceTypePermissionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _resourceTypePermissionLocalService.getResourceTypePermission(resourceTypePermissionId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _resourceTypePermissionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the resource type permissions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of resource type permissions
	* @param end the upper bound of the range of resource type permissions (not inclusive)
	* @return the range of resource type permissions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.ResourceTypePermission> getResourceTypePermissions(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _resourceTypePermissionLocalService.getResourceTypePermissions(start,
			end);
	}

	/**
	* Returns the number of resource type permissions.
	*
	* @return the number of resource type permissions
	* @throws SystemException if a system exception occurred
	*/
	public int getResourceTypePermissionsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _resourceTypePermissionLocalService.getResourceTypePermissionsCount();
	}

	/**
	* Updates the resource type permission in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param resourceTypePermission the resource type permission
	* @return the resource type permission that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.ResourceTypePermission updateResourceTypePermission(
		com.liferay.portal.model.ResourceTypePermission resourceTypePermission)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _resourceTypePermissionLocalService.updateResourceTypePermission(resourceTypePermission);
	}

	/**
	* Updates the resource type permission in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param resourceTypePermission the resource type permission
	* @param merge whether to merge the resource type permission with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the resource type permission that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.ResourceTypePermission updateResourceTypePermission(
		com.liferay.portal.model.ResourceTypePermission resourceTypePermission,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _resourceTypePermissionLocalService.updateResourceTypePermission(resourceTypePermission,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _resourceTypePermissionLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_resourceTypePermissionLocalService.setBeanIdentifier(beanIdentifier);
	}

	public long getCompanyScopeActionIds(long companyId, java.lang.String name,
		long roleId) throws com.liferay.portal.kernel.exception.SystemException {
		return _resourceTypePermissionLocalService.getCompanyScopeActionIds(companyId,
			name, roleId);
	}

	public long getGroupScopeActionIds(long companyId, long groupId,
		java.lang.String name, long roleId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _resourceTypePermissionLocalService.getGroupScopeActionIds(companyId,
			groupId, name, roleId);
	}

	public java.util.List<com.liferay.portal.model.ResourceTypePermission> getGroupScopeResourceTypePermissions(
		long companyId, java.lang.String name, long roleId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _resourceTypePermissionLocalService.getGroupScopeResourceTypePermissions(companyId,
			name, roleId);
	}

	public com.liferay.portal.model.ResourceBlockPermissionsContainer getResourceBlockPermissionsContainer(
		long companyId, long groupId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _resourceTypePermissionLocalService.getResourceBlockPermissionsContainer(companyId,
			groupId, name);
	}

	public java.util.List<com.liferay.portal.model.ResourceTypePermission> getRoleResourceTypePermissions(
		long roleId) throws com.liferay.portal.kernel.exception.SystemException {
		return _resourceTypePermissionLocalService.getRoleResourceTypePermissions(roleId);
	}

	public boolean hasCompanyScopePermission(long companyId,
		java.lang.String name, long roleId, java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _resourceTypePermissionLocalService.hasCompanyScopePermission(companyId,
			name, roleId, actionId);
	}

	public boolean hasEitherScopePermission(long companyId,
		java.lang.String name, long roleId, java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _resourceTypePermissionLocalService.hasEitherScopePermission(companyId,
			name, roleId, actionId);
	}

	public boolean hasGroupScopePermission(long companyId, long groupId,
		java.lang.String name, long roleId, java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _resourceTypePermissionLocalService.hasGroupScopePermission(companyId,
			groupId, name, roleId, actionId);
	}

	public void updateCompanyScopeResourceTypePermissions(long companyId,
		java.lang.String name, long roleId, long actionIdsLong, long operator)
		throws com.liferay.portal.kernel.exception.SystemException {
		_resourceTypePermissionLocalService.updateCompanyScopeResourceTypePermissions(companyId,
			name, roleId, actionIdsLong, operator);
	}

	public void updateGroupScopeResourceTypePermissions(long companyId,
		long groupId, java.lang.String name, long roleId, long actionIdsLong,
		long operator)
		throws com.liferay.portal.kernel.exception.SystemException {
		_resourceTypePermissionLocalService.updateGroupScopeResourceTypePermissions(companyId,
			groupId, name, roleId, actionIdsLong, operator);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public ResourceTypePermissionLocalService getWrappedResourceTypePermissionLocalService() {
		return _resourceTypePermissionLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedResourceTypePermissionLocalService(
		ResourceTypePermissionLocalService resourceTypePermissionLocalService) {
		_resourceTypePermissionLocalService = resourceTypePermissionLocalService;
	}

	public ResourceTypePermissionLocalService getWrappedService() {
		return _resourceTypePermissionLocalService;
	}

	public void setWrappedService(
		ResourceTypePermissionLocalService resourceTypePermissionLocalService) {
		_resourceTypePermissionLocalService = resourceTypePermissionLocalService;
	}

	private ResourceTypePermissionLocalService _resourceTypePermissionLocalService;
}