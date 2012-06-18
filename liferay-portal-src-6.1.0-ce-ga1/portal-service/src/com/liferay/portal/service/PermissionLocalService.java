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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

/**
 * The interface for the permission local service.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PermissionLocalServiceUtil
 * @see com.liferay.portal.service.base.PermissionLocalServiceBaseImpl
 * @see com.liferay.portal.service.impl.PermissionLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface PermissionLocalService extends PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PermissionLocalServiceUtil} to access the permission local service. Add custom service methods to {@link com.liferay.portal.service.impl.PermissionLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the permission to the database. Also notifies the appropriate model listeners.
	*
	* @param permission the permission
	* @return the permission that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.Permission addPermission(
		com.liferay.portal.model.Permission permission)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Creates a new permission with the primary key. Does not add the permission to the database.
	*
	* @param permissionId the primary key for the new permission
	* @return the new permission
	*/
	public com.liferay.portal.model.Permission createPermission(
		long permissionId);

	/**
	* Deletes the permission with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param permissionId the primary key of the permission
	* @throws PortalException if a permission with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deletePermission(long permissionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Deletes the permission from the database. Also notifies the appropriate model listeners.
	*
	* @param permission the permission
	* @throws SystemException if a system exception occurred
	*/
	public void deletePermission(com.liferay.portal.model.Permission permission)
		throws com.liferay.portal.kernel.exception.SystemException;

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
		throws com.liferay.portal.kernel.exception.SystemException;

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
		int end) throws com.liferay.portal.kernel.exception.SystemException;

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
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portal.model.Permission fetchPermission(
		long permissionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the permission with the primary key.
	*
	* @param permissionId the primary key of the permission
	* @return the permission
	* @throws PortalException if a permission with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portal.model.Permission getPermission(long permissionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the permissions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of permissions
	* @param end the upper bound of the range of permissions (not inclusive)
	* @return the range of permissions
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.portal.model.Permission> getPermissions(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of permissions.
	*
	* @return the number of permissions
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getPermissionsCount()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Updates the permission in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param permission the permission
	* @return the permission that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.Permission updatePermission(
		com.liferay.portal.model.Permission permission)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Updates the permission in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param permission the permission
	* @param merge whether to merge the permission with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the permission that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.Permission updatePermission(
		com.liferay.portal.model.Permission permission, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier();

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier);

	/**
	* Adds a permission to perform the action on the resource.
	*
	* <p>
	* This method will retrieve the permission of the company, action, and
	* resource with the primary keys. The method creates the permission if it
	* fails to retrieve it.
	* </p>
	*
	* @param companyId the primary key of the company
	* @param actionId the action's ID
	* @param resourceId the primary key of the resource
	* @return the permission of the company, action, and resource with the
	primary keys
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.Permission addPermission(long companyId,
		java.lang.String actionId, long resourceId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds permissions to perform the actions on the resource.
	*
	* <p>
	* This method will retrieve the permissions of the company, actions, and
	* resource with the primary keys. The method creates any permissions it
	* fails to retrieve.
	* </p>
	*
	* @param companyId the primary key of the company
	* @param actionIds the primary keys of the actions
	* @param resourceId the primary key of the resource
	* @return the permissions to perform the actions on the resource
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.Permission> addPermissions(
		long companyId, java.util.List<java.lang.String> actionIds,
		long resourceId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds permissions to perform either the portlet resource actions or model
	* resource actions on the resource.
	*
	* <p>
	* This method will retrieve the permissions of the company, actions, and
	* resource with the primary keys. The method creates any permissions it
	* fails to retrieve.
	* </p>
	*
	* @param companyId the primary key of the company
	* @param name the resource name
	* @param resourceId the primary key of the resource
	* @param portletActions whether to retrieve the action primary keys from
	the portlet or the model resource
	* @return the permissions to perform the actions on the resource
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.Permission> addPermissions(
		long companyId, java.lang.String name, long resourceId,
		boolean portletActions)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds user permissions to perform the actions on the resource.
	*
	* @param userId the primary key of the user
	* @param actionIds the primary keys of the actions
	* @param resourceId the primary key of the resource
	* @throws PortalException if a user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void addUserPermissions(long userId, java.lang.String[] actionIds,
		long resourceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Checks to see if the actions are permitted on the named resource.
	*
	* @param name the resource name
	* @param actionIds the primary keys of the actions
	* @throws PortalException if the resource company or name could not be
	found or were invalid
	* @throws SystemException if a system exception occurred
	*/
	public void checkPermissions(java.lang.String name,
		java.util.List<java.lang.String> actionIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the IDs of all the actions belonging to the permissions.
	*
	* @param permissions the permissions
	* @return the IDs of all the actions belonging to the permissions
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<java.lang.String> getActions(
		java.util.List<com.liferay.portal.model.Permission> permissions);

	/**
	* Returns all the group's permissions on the resource.
	*
	* @param groupId the primary key of the group
	* @param resourceId the primary key of the resource
	* @return the group's permissions on the resource
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.portal.model.Permission> getGroupPermissions(
		long groupId, long resourceId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the group's permissions on the named resource with the scope
	* and primKey.
	*
	* @param groupId the primary key of the group
	* @param companyId the primary key of the company
	* @param name the resource name
	* @param scope the resource scope
	* @param primKey the resource primKey
	* @return the group's permissions on the named resource with the scope and
	primKey
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.portal.model.Permission> getGroupPermissions(
		long groupId, long companyId, java.lang.String name, int scope,
		java.lang.String primKey)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the primary key of the latest permission created.
	*
	* @return the primary key of the latest permission created
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long getLatestPermissionId()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the permissions of the organization's group with respect to
	* the resource.
	*
	* @param organizationId the primary key of the organization
	* @param groupId the primary key of the group
	* @param resourceId the primary key of the resource
	* @return the permissions of the organization's group with respect to the
	resource
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.portal.model.Permission> getOrgGroupPermissions(
		long organizationId, long groupId, long resourceId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the permissions to perform the actions on the resource,
	* creating new permissions for any permissions not found.
	*
	* @param companyId the primary key of the company
	* @param actionIds the primary keys of the actions
	* @param resourceId the primary key of the resource
	* @return the permissions to perform the actions on the resource
	* @throws SystemException if a system exception occurred
	* @see #addPermission(long, String, long)
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.portal.model.Permission> getPermissions(
		long companyId, java.lang.String[] actionIds, long resourceId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the role's permissions.
	*
	* @param roleId the primary key of the role
	* @return the role's permissions
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.portal.model.Permission> getRolePermissions(
		long roleId) throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.portal.model.Permission> getRolePermissions(
		long roleId, int[] scopes)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the role's permissions on the resource.
	*
	* @param roleId the primary key of the role
	* @param resourceId the primary key of the resource
	* @return the role's permissions on the resource
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.portal.model.Permission> getRolePermissions(
		long roleId, long resourceId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the user's permissions.
	*
	* @param userId the primary key of the user
	* @return the user's permissions
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.portal.model.Permission> getUserPermissions(
		long userId) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the user's permissions on the resource.
	*
	* @param userId the primary key of the user
	* @param resourceId the primary key of the resource
	* @return the user's permissions on the resource
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.portal.model.Permission> getUserPermissions(
		long userId, long resourceId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the user's permissions on the named resource with the scope
	* and primKey.
	*
	* @param userId the primary key of the user
	* @param companyId the primary key of the company
	* @param name the resource name
	* @param scope the resource scope
	* @param primKey the resource primKey
	* @return the user permissions of the resource name, scope, and primKey
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.portal.model.Permission> getUserPermissions(
		long userId, long companyId, java.lang.String name, int scope,
		java.lang.String primKey)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns <code>true</code> if the group has permission to perform the
	* action on the resource.
	*
	* @param groupId the primary key of the group
	* @param actionId the action's ID
	* @param resourceId the primary key of the resource
	* @return <code>true</code> if the group has permission to perform the
	action on the resource; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasGroupPermission(long groupId, java.lang.String actionId,
		long resourceId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns <code>true</code> if the role has permission to perform the
	* action on the named resource with the scope.
	*
	* @param roleId the primary key of the role
	* @param companyId the primary key of the company
	* @param name the resource name
	* @param scope the resource scope
	* @param actionId the action's ID
	* @return <code>true</code> if the role has permission to perform the
	action on the named resource with the scope; <code>false</code>
	otherwise
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasRolePermission(long roleId, long companyId,
		java.lang.String name, int scope, java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns <code>true</code> if the role has permission to perform the
	* action on the named resource with the scope and primKey.
	*
	* @param roleId the primary key of the role
	* @param companyId the primary key of the company
	* @param name the resource name
	* @param scope the resource scope
	* @param primKey the resource primKey
	* @param actionId the action's ID
	* @return <code>true</code> if the role has permission to perform the
	action on the named resource with the scope and primKey;
	<code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasRolePermission(long roleId, long companyId,
		java.lang.String name, int scope, java.lang.String primKey,
		java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns <code>true</code> if the user has permission to perform the
	* action on the resource.
	*
	* @param userId the primary key of the user
	* @param actionId the action's ID
	* @param resourceId the primary key of the resource
	* @return <code>true</code> if the user has permission to perform the
	action on the resource; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasUserPermission(long userId, java.lang.String actionId,
		long resourceId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns <code>true</code> if the user has permission to perform the
	* action on the resources.
	*
	* <p>
	* This method does not support resources managed by the resource block
	* system.
	* </p>
	*
	* @param userId the primary key of the user
	* @param groupId the primary key of the group containing the resource
	* @param resources representations of the resource at each scope level
	returned by {@link
	com.liferay.portal.security.permission.AdvancedPermissionChecker#getResources(
	long, long, String, String, String)}
	* @param actionId the action's ID
	* @param permissionCheckerBag the permission checker bag
	* @return <code>true</code> if the user has permission to perform the
	action on the resources; <code>false</code> otherwise
	* @throws PortalException if a resource action based on any one of the
	resources and the action ID could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasUserPermissions(long userId, long groupId,
		java.util.List<com.liferay.portal.model.Resource> resources,
		java.lang.String actionId,
		com.liferay.portal.security.permission.PermissionCheckerBag permissionCheckerBag)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Sets the container wide permissions of either the role or the default
	* user of each company to perform the actions on the named resource.
	*
	* @param name the resource name
	* @param roleName the role name. Supported role names include {@link
	com.liferay.portal.model.RoleConstants#ORGANIZATION_USER}, {@link
	com.liferay.portal.model.RoleConstants#OWNER}, and {@link
	com.liferay.portal.model.RoleConstants#SITE_MEMBER}.
	* @param actionId the action's ID
	* @throws PortalException if a matching role could not be found or if a
	default user for the company could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void setContainerResourcePermissions(java.lang.String name,
		java.lang.String roleName, java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Sets the group's permissions to perform the actions on the resource,
	* replacing the group's existing permissions on the resource.
	*
	* @param groupId the primary key of the group
	* @param actionIds the primary keys of the actions
	* @param resourceId the primary key of the resource
	* @throws PortalException if a group with the primary key could not be
	found
	* @throws SystemException if a system exception occurred
	*/
	public void setGroupPermissions(long groupId, java.lang.String[] actionIds,
		long resourceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Sets the entity's group permissions to perform the actions on the
	* resource, replacing the entity's existing group permissions on the
	* resource. Only {@link com.liferay.portal.model.Organization} and {@link
	* com.liferay.portal.model.UserGroup} class entities are supported.
	*
	* @param className the class name of an organization or user group
	* @param classPK the primary key of the class
	* @param groupId the primary key of the group
	* @param actionIds the primary keys of the actions
	* @param resourceId the primary key of the resource
	* @throws PortalException if an entity with the class name and primary key
	could not be found or if the entity's associated group could not
	be found
	* @throws SystemException if a system exception occurred
	*/
	public void setGroupPermissions(java.lang.String className,
		java.lang.String classPK, long groupId, java.lang.String[] actionIds,
		long resourceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Sets the organization's group permissions to perform the actions on the
	* resource, replacing the organization's existing group permissions on the
	* resource.
	*
	* @param organizationId the primary key of the organization
	* @param groupId the primary key of the group in which to scope the
	permissions
	* @param actionIds the primary keys of the actions
	* @param resourceId the primary key of the resource
	* @throws PortalException if an organization with the primary key could not
	be found
	* @throws SystemException if a system exception occurred
	*/
	public void setOrgGroupPermissions(long organizationId, long groupId,
		java.lang.String[] actionIds, long resourceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Sets the role's permissions to perform the action on the named resource,
	* replacing the role's existing permissions on the resource.
	*
	* @param roleId the primary key of the role
	* @param companyId the primary key of the company
	* @param name the resource name
	* @param scope the resource scope
	* @param primKey the resource primKey
	* @param actionId the action's ID
	* @throws PortalException if the scope was {@link
	com.liferay.portal.model.ResourceConstants#SCOPE_INDIVIDUAL}
	* @throws SystemException if a system exception occurred
	*/
	public void setRolePermission(long roleId, long companyId,
		java.lang.String name, int scope, java.lang.String primKey,
		java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Sets the role's permissions to perform the actions on the named resource,
	* replacing the role's existing permission for each of these actions on the
	* resource.
	*
	* @param roleId the primary key of the role
	* @param companyId the primary key of the company
	* @param name the resource name
	* @param scope the resource scope
	* @param primKey the resource primKey
	* @param actionIds the primary keys of the actions
	* @throws PortalException if the scope was {@link
	com.liferay.portal.model.ResourceConstants#SCOPE_INDIVIDUAL}
	* @throws SystemException if a system exception occurred
	*/
	public void setRolePermissions(long roleId, long companyId,
		java.lang.String name, int scope, java.lang.String primKey,
		java.lang.String[] actionIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Sets the role's permissions to perform the actions on the resource,
	* replacing the role's existing permissions on the resource.
	*
	* @param roleId the primary key of the role
	* @param actionIds the primary keys of the actions
	* @param resourceId the primary key of the resource
	* @throws PortalException if a role with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void setRolePermissions(long roleId, java.lang.String[] actionIds,
		long resourceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Sets the permissions of each role to perform respective actions on the
	* resource, replacing the existing permissions of each role on the
	* resource.
	*
	* @param companyId the primary key of the company
	* @param roleIdsToActionIds the map of roles to their new actions on the
	resource
	* @param resourceId the primary key of the resource
	* @throws SystemException if a system exception occurred
	*/
	public void setRolesPermissions(long companyId,
		java.util.Map<java.lang.Long, java.lang.String[]> roleIdsToActionIds,
		long resourceId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Sets the permissions of each role to perform respective actions on the
	* named resource, replacing the existing permissions of each role on the
	* resource.
	*
	* @param companyId the primary key of the company
	* @param roleIdsToActionIds the map of roles to their new actions on the
	resource
	* @param name the resource name
	* @param scope the resource scope
	* @param primKey the resource primKey
	* @throws SystemException if a system exception occurred
	*/
	public void setRolesPermissions(long companyId,
		java.util.Map<java.lang.Long, java.lang.String[]> roleIdsToActionIds,
		java.lang.String name, int scope, java.lang.String primKey)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Sets the user's permissions to perform the actions on the resource,
	* replacing the user's existing permissions on the resource.
	*
	* @param userId the primary key of the user
	* @param actionIds the primary keys of the actions
	* @param resourceId the primary key of the resource
	* @throws PortalException if a user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void setUserPermissions(long userId, java.lang.String[] actionIds,
		long resourceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the permission from the role.
	*
	* @param roleId the primary key of the role
	* @param permissionId the primary key of the permission
	* @throws SystemException if a system exception occurred
	*/
	public void unsetRolePermission(long roleId, long permissionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the role's permissions to perform the action on the named
	* resource with the scope and primKey.
	*
	* @param roleId the primary key of the role
	* @param companyId the primary key of the company
	* @param name the resource name
	* @param scope the resource scope
	* @param primKey the resource primKey
	* @param actionId the action's ID
	* @throws SystemException if a system exception occurred
	*/
	public void unsetRolePermission(long roleId, long companyId,
		java.lang.String name, int scope, java.lang.String primKey,
		java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the role's permissions to perform the action on the named
	* resource.
	*
	* @param roleId the primary key of the role
	* @param companyId the primary key of the company
	* @param name the resource name
	* @param scope the resource scope
	* @param actionId the action's ID
	* @throws SystemException if a system exception occurred
	*/
	public void unsetRolePermissions(long roleId, long companyId,
		java.lang.String name, int scope, java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the user's permissions to perform the actions on the resource.
	*
	* @param userId the primary key of the user
	* @param actionIds the primary keys of the actions
	* @param resourceId the primary key of the resource
	* @throws SystemException if a system exception occurred
	*/
	public void unsetUserPermissions(long userId, java.lang.String[] actionIds,
		long resourceId)
		throws com.liferay.portal.kernel.exception.SystemException;
}