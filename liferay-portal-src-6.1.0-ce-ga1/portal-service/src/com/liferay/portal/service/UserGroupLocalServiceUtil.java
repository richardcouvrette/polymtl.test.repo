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
 * The utility for the user group local service. This utility wraps {@link com.liferay.portal.service.impl.UserGroupLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserGroupLocalService
 * @see com.liferay.portal.service.base.UserGroupLocalServiceBaseImpl
 * @see com.liferay.portal.service.impl.UserGroupLocalServiceImpl
 * @generated
 */
public class UserGroupLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portal.service.impl.UserGroupLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the user group to the database. Also notifies the appropriate model listeners.
	*
	* @param userGroup the user group
	* @return the user group that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.UserGroup addUserGroup(
		com.liferay.portal.model.UserGroup userGroup)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addUserGroup(userGroup);
	}

	/**
	* Creates a new user group with the primary key. Does not add the user group to the database.
	*
	* @param userGroupId the primary key for the new user group
	* @return the new user group
	*/
	public static com.liferay.portal.model.UserGroup createUserGroup(
		long userGroupId) {
		return getService().createUserGroup(userGroupId);
	}

	/**
	* Deletes the user group with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userGroupId the primary key of the user group
	* @throws PortalException if a user group with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteUserGroup(long userGroupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteUserGroup(userGroupId);
	}

	/**
	* Deletes the user group from the database. Also notifies the appropriate model listeners.
	*
	* @param userGroup the user group
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteUserGroup(
		com.liferay.portal.model.UserGroup userGroup)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteUserGroup(userGroup);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
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
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	public static com.liferay.portal.model.UserGroup fetchUserGroup(
		long userGroupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchUserGroup(userGroupId);
	}

	/**
	* Returns the user group with the primary key.
	*
	* @param userGroupId the primary key of the user group
	* @return the user group
	* @throws PortalException if a user group with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.UserGroup getUserGroup(
		long userGroupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserGroup(userGroupId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the user groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of user groups
	* @param end the upper bound of the range of user groups (not inclusive)
	* @return the range of user groups
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.UserGroup> getUserGroups(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserGroups(start, end);
	}

	/**
	* Returns the number of user groups.
	*
	* @return the number of user groups
	* @throws SystemException if a system exception occurred
	*/
	public static int getUserGroupsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserGroupsCount();
	}

	/**
	* Updates the user group in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param userGroup the user group
	* @return the user group that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.UserGroup updateUserGroup(
		com.liferay.portal.model.UserGroup userGroup)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateUserGroup(userGroup);
	}

	/**
	* Updates the user group in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param userGroup the user group
	* @param merge whether to merge the user group with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the user group that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.UserGroup updateUserGroup(
		com.liferay.portal.model.UserGroup userGroup, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateUserGroup(userGroup, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	/**
	* Adds the user groups to the group.
	*
	* @param groupId the primary key of the group
	* @param userGroupIds the primary keys of the user groups
	* @throws SystemException if a system exception occurred
	*/
	public static void addGroupUserGroups(long groupId, long[] userGroupIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().addGroupUserGroups(groupId, userGroupIds);
	}

	/**
	* Adds the user groups to the team.
	*
	* @param teamId the primary key of the team
	* @param userGroupIds the primary keys of the user groups
	* @throws SystemException if a system exception occurred
	*/
	public static void addTeamUserGroups(long teamId, long[] userGroupIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().addTeamUserGroups(teamId, userGroupIds);
	}

	/**
	* Adds a user group.
	*
	* <p>
	* This method handles the creation and bookkeeping of the user group,
	* including its resources, metadata, and internal data structures. It is
	* not necessary to make subsequent calls to setup default groups and
	* resources for the user group.
	* </p>
	*
	* @param userId the primary key of the user
	* @param companyId the primary key of the user group's company
	* @param name the user group's name
	* @param description the user group's description
	* @return the user group
	* @throws PortalException if the user group's information was invalid
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.UserGroup addUserGroup(long userId,
		long companyId, java.lang.String name, java.lang.String description)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().addUserGroup(userId, companyId, name, description);
	}

	/**
	* Clears all associations between the user and its user groups and clears
	* the permissions cache.
	*
	* <p>
	* This method is called from {@link #deleteUserGroup(UserGroup)}.
	* </p>
	*
	* @param userId the primary key of the user
	* @throws SystemException if a system exception occurred
	*/
	public static void clearUserUserGroups(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().clearUserUserGroups(userId);
	}

	/**
	* Copies the user group's layouts to the users who are not already members
	* of the user group.
	*
	* @param userGroupId the primary key of the user group
	* @param userIds the primary keys of the users
	* @throws PortalException if any one of the users could not be found or
	if a portal exception occurred
	* @throws SystemException if a system exception occurred
	* @deprecated
	*/
	public static void copyUserGroupLayouts(long userGroupId, long[] userIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().copyUserGroupLayouts(userGroupId, userIds);
	}

	/**
	* Copies the user groups' layouts to the user.
	*
	* @param userGroupIds the primary keys of the user groups
	* @param userId the primary key of the user
	* @throws PortalException if a user with the primary key could not be
	found or if a portal exception occurred
	* @throws SystemException if a system exception occurred
	* @deprecated
	*/
	public static void copyUserGroupLayouts(long[] userGroupIds, long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().copyUserGroupLayouts(userGroupIds, userId);
	}

	/**
	* Copies the user group's layout to the user.
	*
	* @param userGroupId the primary key of the user group
	* @param userId the primary key of the user
	* @throws PortalException if a user with the primary key could not be
	found or if a portal exception occurred
	* @throws SystemException if a system exception occurred
	* @deprecated
	*/
	public static void copyUserGroupLayouts(long userGroupId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().copyUserGroupLayouts(userGroupId, userId);
	}

	/**
	* Returns the user group with the name.
	*
	* @param companyId the primary key of the user group's company
	* @param name the user group's name
	* @return Returns the user group with the name
	* @throws PortalException if a user group with the name could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.UserGroup getUserGroup(
		long companyId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserGroup(companyId, name);
	}

	/**
	* Returns all the user groups belonging to the company.
	*
	* @param companyId the primary key of the user groups' company
	* @return the user groups belonging to the company
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.UserGroup> getUserGroups(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserGroups(companyId);
	}

	/**
	* Returns all the user groups with the primary keys.
	*
	* @param userGroupIds the primary keys of the user groups
	* @return the user groups with the primary keys
	* @throws PortalException if any one of the user groups could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.UserGroup> getUserGroups(
		long[] userGroupIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserGroups(userGroupIds);
	}

	/**
	* Returns all the user groups to which the user belongs.
	*
	* @param userId the primary key of the user
	* @return the user groups to which the user belongs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.UserGroup> getUserUserGroups(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserUserGroups(userId);
	}

	/**
	* Returns <code>true</code> if the user group is associated with the group.
	*
	* @param groupId the primary key of the group
	* @param userGroupId the primary key of the user group
	* @return <code>true</code> if the user group belongs to the group;
	<code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean hasGroupUserGroup(long groupId, long userGroupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().hasGroupUserGroup(groupId, userGroupId);
	}

	/**
	* Returns <code>true</code> if the user group belongs to the team.
	*
	* @param teamId the primary key of the team
	* @param userGroupId the primary key of the user group
	* @return <code>true</code> if the user group belongs to the team;
	<code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean hasTeamUserGroup(long teamId, long userGroupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().hasTeamUserGroup(teamId, userGroupId);
	}

	/**
	* Returns an ordered range of all the user groups that match the name and
	* description.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link
	* com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param companyId the primary key of the user group's company
	* @param name the user group's name (optionally <code>null</code>)
	* @param description the user group's description (optionally
	<code>null</code>)
	* @param params the finder params (optionally <code>null</code>). For more
	information see {@link
	com.liferay.portal.service.persistence.UserGroupFinder}
	* @param start the lower bound of the range of user groups to return
	* @param end the upper bound of the range of user groups to return (not
	inclusive)
	* @param obc the comparator to order the user groups (optionally
	<code>null</code>)
	* @return the matching user groups ordered by comparator <code>obc</code>
	* @throws SystemException if a system exception occurred
	* @see com.liferay.portal.service.persistence.UserGroupFinder
	*/
	public static java.util.List<com.liferay.portal.model.UserGroup> search(
		long companyId, java.lang.String name, java.lang.String description,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .search(companyId, name, description, params, start, end, obc);
	}

	/**
	* Returns the number of user groups that match the name and description.
	*
	* @param companyId the primary key of the user group's company
	* @param name the user group's name (optionally <code>null</code>)
	* @param description the user group's description (optionally
	<code>null</code>)
	* @param params the finder params (optionally <code>null</code>). For more
	information see {@link
	com.liferay.portal.service.persistence.UserGroupFinder}
	* @return the number of matching user groups
	* @throws SystemException if a system exception occurred
	* @see com.liferay.portal.service.persistence.UserGroupFinder
	*/
	public static int searchCount(long companyId, java.lang.String name,
		java.lang.String description,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().searchCount(companyId, name, description, params);
	}

	/**
	* Sets the user groups associated with the user copying the user group
	* layouts and removing and adding user group associations for the user as
	* necessary.
	*
	* @param userId the primary key of the user
	* @param userGroupIds the primary keys of the user groups
	* @throws PortalException if a portal exception occurred
	* @throws SystemException if a system exception occurred
	*/
	public static void setUserUserGroups(long userId, long[] userGroupIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().setUserUserGroups(userId, userGroupIds);
	}

	/**
	* Removes the user groups from the group.
	*
	* @param groupId the primary key of the group
	* @param userGroupIds the primary keys of the user groups
	* @throws SystemException if a system exception occurred
	*/
	public static void unsetGroupUserGroups(long groupId, long[] userGroupIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().unsetGroupUserGroups(groupId, userGroupIds);
	}

	/**
	* Removes the user groups from the team.
	*
	* @param teamId the primary key of the team
	* @param userGroupIds the primary keys of the user groups
	* @throws SystemException if a system exception occurred
	*/
	public static void unsetTeamUserGroups(long teamId, long[] userGroupIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().unsetTeamUserGroups(teamId, userGroupIds);
	}

	/**
	* Updates the user group.
	*
	* @param companyId the primary key of the user group's company
	* @param userGroupId the primary key of the user group
	* @param name the user group's name
	* @param description the user group's description
	* @return the user group
	* @throws PortalException if a user group with the primary key could not be
	found or if the new information was invalid
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.UserGroup updateUserGroup(
		long companyId, long userGroupId, java.lang.String name,
		java.lang.String description)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateUserGroup(companyId, userGroupId, name, description);
	}

	public static UserGroupLocalService getService() {
		if (_service == null) {
			_service = (UserGroupLocalService)PortalBeanLocatorUtil.locate(UserGroupLocalService.class.getName());

			ReferenceRegistry.registerReference(UserGroupLocalServiceUtil.class,
				"_service");
			MethodCache.remove(UserGroupLocalService.class);
		}

		return _service;
	}

	public void setService(UserGroupLocalService service) {
		MethodCache.remove(UserGroupLocalService.class);

		_service = service;

		ReferenceRegistry.registerReference(UserGroupLocalServiceUtil.class,
			"_service");
		MethodCache.remove(UserGroupLocalService.class);
	}

	private static UserGroupLocalService _service;
}