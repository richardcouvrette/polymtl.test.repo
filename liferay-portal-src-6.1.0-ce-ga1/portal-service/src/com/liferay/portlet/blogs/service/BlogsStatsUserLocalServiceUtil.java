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

package com.liferay.portlet.blogs.service;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the blogs stats user local service. This utility wraps {@link com.liferay.portlet.blogs.service.impl.BlogsStatsUserLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BlogsStatsUserLocalService
 * @see com.liferay.portlet.blogs.service.base.BlogsStatsUserLocalServiceBaseImpl
 * @see com.liferay.portlet.blogs.service.impl.BlogsStatsUserLocalServiceImpl
 * @generated
 */
public class BlogsStatsUserLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portlet.blogs.service.impl.BlogsStatsUserLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the blogs stats user to the database. Also notifies the appropriate model listeners.
	*
	* @param blogsStatsUser the blogs stats user
	* @return the blogs stats user that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.blogs.model.BlogsStatsUser addBlogsStatsUser(
		com.liferay.portlet.blogs.model.BlogsStatsUser blogsStatsUser)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addBlogsStatsUser(blogsStatsUser);
	}

	/**
	* Creates a new blogs stats user with the primary key. Does not add the blogs stats user to the database.
	*
	* @param statsUserId the primary key for the new blogs stats user
	* @return the new blogs stats user
	*/
	public static com.liferay.portlet.blogs.model.BlogsStatsUser createBlogsStatsUser(
		long statsUserId) {
		return getService().createBlogsStatsUser(statsUserId);
	}

	/**
	* Deletes the blogs stats user with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param statsUserId the primary key of the blogs stats user
	* @throws PortalException if a blogs stats user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteBlogsStatsUser(long statsUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteBlogsStatsUser(statsUserId);
	}

	/**
	* Deletes the blogs stats user from the database. Also notifies the appropriate model listeners.
	*
	* @param blogsStatsUser the blogs stats user
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteBlogsStatsUser(
		com.liferay.portlet.blogs.model.BlogsStatsUser blogsStatsUser)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteBlogsStatsUser(blogsStatsUser);
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

	public static com.liferay.portlet.blogs.model.BlogsStatsUser fetchBlogsStatsUser(
		long statsUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchBlogsStatsUser(statsUserId);
	}

	/**
	* Returns the blogs stats user with the primary key.
	*
	* @param statsUserId the primary key of the blogs stats user
	* @return the blogs stats user
	* @throws PortalException if a blogs stats user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.blogs.model.BlogsStatsUser getBlogsStatsUser(
		long statsUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getBlogsStatsUser(statsUserId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the blogs stats users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of blogs stats users
	* @param end the upper bound of the range of blogs stats users (not inclusive)
	* @return the range of blogs stats users
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.blogs.model.BlogsStatsUser> getBlogsStatsUsers(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getBlogsStatsUsers(start, end);
	}

	/**
	* Returns the number of blogs stats users.
	*
	* @return the number of blogs stats users
	* @throws SystemException if a system exception occurred
	*/
	public static int getBlogsStatsUsersCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getBlogsStatsUsersCount();
	}

	/**
	* Updates the blogs stats user in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param blogsStatsUser the blogs stats user
	* @return the blogs stats user that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.blogs.model.BlogsStatsUser updateBlogsStatsUser(
		com.liferay.portlet.blogs.model.BlogsStatsUser blogsStatsUser)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateBlogsStatsUser(blogsStatsUser);
	}

	/**
	* Updates the blogs stats user in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param blogsStatsUser the blogs stats user
	* @param merge whether to merge the blogs stats user with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the blogs stats user that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.blogs.model.BlogsStatsUser updateBlogsStatsUser(
		com.liferay.portlet.blogs.model.BlogsStatsUser blogsStatsUser,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateBlogsStatsUser(blogsStatsUser, merge);
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

	public static void deleteStatsUser(
		com.liferay.portlet.blogs.model.BlogsStatsUser statsUsers)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteStatsUser(statsUsers);
	}

	public static void deleteStatsUser(long statsUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteStatsUser(statsUserId);
	}

	public static void deleteStatsUserByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteStatsUserByGroupId(groupId);
	}

	public static void deleteStatsUserByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteStatsUserByUserId(userId);
	}

	public static java.util.List<com.liferay.portlet.blogs.model.BlogsStatsUser> getCompanyStatsUsers(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCompanyStatsUsers(companyId, start, end);
	}

	public static java.util.List<com.liferay.portlet.blogs.model.BlogsStatsUser> getCompanyStatsUsers(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCompanyStatsUsers(companyId, start, end, obc);
	}

	public static int getCompanyStatsUsersCount(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCompanyStatsUsersCount(companyId);
	}

	public static java.util.List<com.liferay.portlet.blogs.model.BlogsStatsUser> getGroupsStatsUsers(
		long companyId, long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getGroupsStatsUsers(companyId, groupId, start, end);
	}

	public static java.util.List<com.liferay.portlet.blogs.model.BlogsStatsUser> getGroupStatsUsers(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getGroupStatsUsers(groupId, start, end);
	}

	public static java.util.List<com.liferay.portlet.blogs.model.BlogsStatsUser> getGroupStatsUsers(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getGroupStatsUsers(groupId, start, end, obc);
	}

	public static int getGroupStatsUsersCount(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getGroupStatsUsersCount(groupId);
	}

	public static java.util.List<com.liferay.portlet.blogs.model.BlogsStatsUser> getOrganizationStatsUsers(
		long organizationId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getOrganizationStatsUsers(organizationId, start, end);
	}

	public static java.util.List<com.liferay.portlet.blogs.model.BlogsStatsUser> getOrganizationStatsUsers(
		long organizationId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getOrganizationStatsUsers(organizationId, start, end, obc);
	}

	public static int getOrganizationStatsUsersCount(long organizationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getOrganizationStatsUsersCount(organizationId);
	}

	public static com.liferay.portlet.blogs.model.BlogsStatsUser getStatsUser(
		long groupId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getStatsUser(groupId, userId);
	}

	public static void updateStatsUser(long groupId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().updateStatsUser(groupId, userId);
	}

	public static void updateStatsUser(long groupId, long userId,
		java.util.Date displayDate)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().updateStatsUser(groupId, userId, displayDate);
	}

	public static BlogsStatsUserLocalService getService() {
		if (_service == null) {
			_service = (BlogsStatsUserLocalService)PortalBeanLocatorUtil.locate(BlogsStatsUserLocalService.class.getName());

			ReferenceRegistry.registerReference(BlogsStatsUserLocalServiceUtil.class,
				"_service");
			MethodCache.remove(BlogsStatsUserLocalService.class);
		}

		return _service;
	}

	public void setService(BlogsStatsUserLocalService service) {
		MethodCache.remove(BlogsStatsUserLocalService.class);

		_service = service;

		ReferenceRegistry.registerReference(BlogsStatsUserLocalServiceUtil.class,
			"_service");
		MethodCache.remove(BlogsStatsUserLocalService.class);
	}

	private static BlogsStatsUserLocalService _service;
}