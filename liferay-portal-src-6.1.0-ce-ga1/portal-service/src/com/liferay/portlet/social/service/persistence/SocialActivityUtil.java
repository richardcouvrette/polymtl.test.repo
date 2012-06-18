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

package com.liferay.portlet.social.service.persistence;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.social.model.SocialActivity;

import java.util.List;

/**
 * The persistence utility for the social activity service. This utility wraps {@link SocialActivityPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SocialActivityPersistence
 * @see SocialActivityPersistenceImpl
 * @generated
 */
public class SocialActivityUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(SocialActivity socialActivity) {
		getPersistence().clearCache(socialActivity);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<SocialActivity> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SocialActivity> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SocialActivity> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static SocialActivity update(SocialActivity socialActivity,
		boolean merge) throws SystemException {
		return getPersistence().update(socialActivity, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static SocialActivity update(SocialActivity socialActivity,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(socialActivity, merge, serviceContext);
	}

	/**
	* Caches the social activity in the entity cache if it is enabled.
	*
	* @param socialActivity the social activity
	*/
	public static void cacheResult(
		com.liferay.portlet.social.model.SocialActivity socialActivity) {
		getPersistence().cacheResult(socialActivity);
	}

	/**
	* Caches the social activities in the entity cache if it is enabled.
	*
	* @param socialActivities the social activities
	*/
	public static void cacheResult(
		java.util.List<com.liferay.portlet.social.model.SocialActivity> socialActivities) {
		getPersistence().cacheResult(socialActivities);
	}

	/**
	* Creates a new social activity with the primary key. Does not add the social activity to the database.
	*
	* @param activityId the primary key for the new social activity
	* @return the new social activity
	*/
	public static com.liferay.portlet.social.model.SocialActivity create(
		long activityId) {
		return getPersistence().create(activityId);
	}

	/**
	* Removes the social activity with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param activityId the primary key of the social activity
	* @return the social activity that was removed
	* @throws com.liferay.portlet.social.NoSuchActivityException if a social activity with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.social.model.SocialActivity remove(
		long activityId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivityException {
		return getPersistence().remove(activityId);
	}

	public static com.liferay.portlet.social.model.SocialActivity updateImpl(
		com.liferay.portlet.social.model.SocialActivity socialActivity,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(socialActivity, merge);
	}

	/**
	* Returns the social activity with the primary key or throws a {@link com.liferay.portlet.social.NoSuchActivityException} if it could not be found.
	*
	* @param activityId the primary key of the social activity
	* @return the social activity
	* @throws com.liferay.portlet.social.NoSuchActivityException if a social activity with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.social.model.SocialActivity findByPrimaryKey(
		long activityId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivityException {
		return getPersistence().findByPrimaryKey(activityId);
	}

	/**
	* Returns the social activity with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param activityId the primary key of the social activity
	* @return the social activity, or <code>null</code> if a social activity with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.social.model.SocialActivity fetchByPrimaryKey(
		long activityId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(activityId);
	}

	/**
	* Returns all the social activities where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching social activities
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.social.model.SocialActivity> findByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the social activities where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of social activities
	* @param end the upper bound of the range of social activities (not inclusive)
	* @return the range of matching social activities
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.social.model.SocialActivity> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the social activities where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of social activities
	* @param end the upper bound of the range of social activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching social activities
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.social.model.SocialActivity> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns the first social activity in the ordered set where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching social activity
	* @throws com.liferay.portlet.social.NoSuchActivityException if a matching social activity could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.social.model.SocialActivity findByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivityException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last social activity in the ordered set where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching social activity
	* @throws com.liferay.portlet.social.NoSuchActivityException if a matching social activity could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.social.model.SocialActivity findByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivityException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the social activities before and after the current social activity in the ordered set where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param activityId the primary key of the current social activity
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next social activity
	* @throws com.liferay.portlet.social.NoSuchActivityException if a social activity with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.social.model.SocialActivity[] findByGroupId_PrevAndNext(
		long activityId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivityException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(activityId, groupId,
			orderByComparator);
	}

	/**
	* Returns all the social activities where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching social activities
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.social.model.SocialActivity> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	* Returns a range of all the social activities where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of social activities
	* @param end the upper bound of the range of social activities (not inclusive)
	* @return the range of matching social activities
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.social.model.SocialActivity> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the social activities where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of social activities
	* @param end the upper bound of the range of social activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching social activities
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.social.model.SocialActivity> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first social activity in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching social activity
	* @throws com.liferay.portlet.social.NoSuchActivityException if a matching social activity could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.social.model.SocialActivity findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivityException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the last social activity in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching social activity
	* @throws com.liferay.portlet.social.NoSuchActivityException if a matching social activity could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.social.model.SocialActivity findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivityException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the social activities before and after the current social activity in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param activityId the primary key of the current social activity
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next social activity
	* @throws com.liferay.portlet.social.NoSuchActivityException if a social activity with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.social.model.SocialActivity[] findByCompanyId_PrevAndNext(
		long activityId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivityException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(activityId, companyId,
			orderByComparator);
	}

	/**
	* Returns all the social activities where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching social activities
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.social.model.SocialActivity> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId);
	}

	/**
	* Returns a range of all the social activities where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of social activities
	* @param end the upper bound of the range of social activities (not inclusive)
	* @return the range of matching social activities
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.social.model.SocialActivity> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId, start, end);
	}

	/**
	* Returns an ordered range of all the social activities where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of social activities
	* @param end the upper bound of the range of social activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching social activities
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.social.model.SocialActivity> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator);
	}

	/**
	* Returns the first social activity in the ordered set where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching social activity
	* @throws com.liferay.portlet.social.NoSuchActivityException if a matching social activity could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.social.model.SocialActivity findByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivityException {
		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the last social activity in the ordered set where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching social activity
	* @throws com.liferay.portlet.social.NoSuchActivityException if a matching social activity could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.social.model.SocialActivity findByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivityException {
		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the social activities before and after the current social activity in the ordered set where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param activityId the primary key of the current social activity
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next social activity
	* @throws com.liferay.portlet.social.NoSuchActivityException if a social activity with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.social.model.SocialActivity[] findByUserId_PrevAndNext(
		long activityId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivityException {
		return getPersistence()
				   .findByUserId_PrevAndNext(activityId, userId,
			orderByComparator);
	}

	/**
	* Returns the social activity where mirrorActivityId = &#63; or throws a {@link com.liferay.portlet.social.NoSuchActivityException} if it could not be found.
	*
	* @param mirrorActivityId the mirror activity ID
	* @return the matching social activity
	* @throws com.liferay.portlet.social.NoSuchActivityException if a matching social activity could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.social.model.SocialActivity findByMirrorActivityId(
		long mirrorActivityId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivityException {
		return getPersistence().findByMirrorActivityId(mirrorActivityId);
	}

	/**
	* Returns the social activity where mirrorActivityId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param mirrorActivityId the mirror activity ID
	* @return the matching social activity, or <code>null</code> if a matching social activity could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.social.model.SocialActivity fetchByMirrorActivityId(
		long mirrorActivityId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByMirrorActivityId(mirrorActivityId);
	}

	/**
	* Returns the social activity where mirrorActivityId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param mirrorActivityId the mirror activity ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching social activity, or <code>null</code> if a matching social activity could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.social.model.SocialActivity fetchByMirrorActivityId(
		long mirrorActivityId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByMirrorActivityId(mirrorActivityId, retrieveFromCache);
	}

	/**
	* Returns all the social activities where classNameId = &#63;.
	*
	* @param classNameId the class name ID
	* @return the matching social activities
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.social.model.SocialActivity> findByClassNameId(
		long classNameId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByClassNameId(classNameId);
	}

	/**
	* Returns a range of all the social activities where classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param start the lower bound of the range of social activities
	* @param end the upper bound of the range of social activities (not inclusive)
	* @return the range of matching social activities
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.social.model.SocialActivity> findByClassNameId(
		long classNameId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByClassNameId(classNameId, start, end);
	}

	/**
	* Returns an ordered range of all the social activities where classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param start the lower bound of the range of social activities
	* @param end the upper bound of the range of social activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching social activities
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.social.model.SocialActivity> findByClassNameId(
		long classNameId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByClassNameId(classNameId, start, end, orderByComparator);
	}

	/**
	* Returns the first social activity in the ordered set where classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching social activity
	* @throws com.liferay.portlet.social.NoSuchActivityException if a matching social activity could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.social.model.SocialActivity findByClassNameId_First(
		long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivityException {
		return getPersistence()
				   .findByClassNameId_First(classNameId, orderByComparator);
	}

	/**
	* Returns the last social activity in the ordered set where classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching social activity
	* @throws com.liferay.portlet.social.NoSuchActivityException if a matching social activity could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.social.model.SocialActivity findByClassNameId_Last(
		long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivityException {
		return getPersistence()
				   .findByClassNameId_Last(classNameId, orderByComparator);
	}

	/**
	* Returns the social activities before and after the current social activity in the ordered set where classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param activityId the primary key of the current social activity
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next social activity
	* @throws com.liferay.portlet.social.NoSuchActivityException if a social activity with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.social.model.SocialActivity[] findByClassNameId_PrevAndNext(
		long activityId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivityException {
		return getPersistence()
				   .findByClassNameId_PrevAndNext(activityId, classNameId,
			orderByComparator);
	}

	/**
	* Returns all the social activities where receiverUserId = &#63;.
	*
	* @param receiverUserId the receiver user ID
	* @return the matching social activities
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.social.model.SocialActivity> findByReceiverUserId(
		long receiverUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByReceiverUserId(receiverUserId);
	}

	/**
	* Returns a range of all the social activities where receiverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param receiverUserId the receiver user ID
	* @param start the lower bound of the range of social activities
	* @param end the upper bound of the range of social activities (not inclusive)
	* @return the range of matching social activities
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.social.model.SocialActivity> findByReceiverUserId(
		long receiverUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByReceiverUserId(receiverUserId, start, end);
	}

	/**
	* Returns an ordered range of all the social activities where receiverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param receiverUserId the receiver user ID
	* @param start the lower bound of the range of social activities
	* @param end the upper bound of the range of social activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching social activities
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.social.model.SocialActivity> findByReceiverUserId(
		long receiverUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByReceiverUserId(receiverUserId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first social activity in the ordered set where receiverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param receiverUserId the receiver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching social activity
	* @throws com.liferay.portlet.social.NoSuchActivityException if a matching social activity could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.social.model.SocialActivity findByReceiverUserId_First(
		long receiverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivityException {
		return getPersistence()
				   .findByReceiverUserId_First(receiverUserId, orderByComparator);
	}

	/**
	* Returns the last social activity in the ordered set where receiverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param receiverUserId the receiver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching social activity
	* @throws com.liferay.portlet.social.NoSuchActivityException if a matching social activity could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.social.model.SocialActivity findByReceiverUserId_Last(
		long receiverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivityException {
		return getPersistence()
				   .findByReceiverUserId_Last(receiverUserId, orderByComparator);
	}

	/**
	* Returns the social activities before and after the current social activity in the ordered set where receiverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param activityId the primary key of the current social activity
	* @param receiverUserId the receiver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next social activity
	* @throws com.liferay.portlet.social.NoSuchActivityException if a social activity with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.social.model.SocialActivity[] findByReceiverUserId_PrevAndNext(
		long activityId, long receiverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivityException {
		return getPersistence()
				   .findByReceiverUserId_PrevAndNext(activityId,
			receiverUserId, orderByComparator);
	}

	/**
	* Returns all the social activities where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching social activities
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.social.model.SocialActivity> findByC_C(
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_C(classNameId, classPK);
	}

	/**
	* Returns a range of all the social activities where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of social activities
	* @param end the upper bound of the range of social activities (not inclusive)
	* @return the range of matching social activities
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.social.model.SocialActivity> findByC_C(
		long classNameId, long classPK, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_C(classNameId, classPK, start, end);
	}

	/**
	* Returns an ordered range of all the social activities where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of social activities
	* @param end the upper bound of the range of social activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching social activities
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.social.model.SocialActivity> findByC_C(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C(classNameId, classPK, start, end,
			orderByComparator);
	}

	/**
	* Returns the first social activity in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching social activity
	* @throws com.liferay.portlet.social.NoSuchActivityException if a matching social activity could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.social.model.SocialActivity findByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivityException {
		return getPersistence()
				   .findByC_C_First(classNameId, classPK, orderByComparator);
	}

	/**
	* Returns the last social activity in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching social activity
	* @throws com.liferay.portlet.social.NoSuchActivityException if a matching social activity could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.social.model.SocialActivity findByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivityException {
		return getPersistence()
				   .findByC_C_Last(classNameId, classPK, orderByComparator);
	}

	/**
	* Returns the social activities before and after the current social activity in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param activityId the primary key of the current social activity
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next social activity
	* @throws com.liferay.portlet.social.NoSuchActivityException if a social activity with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.social.model.SocialActivity[] findByC_C_PrevAndNext(
		long activityId, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivityException {
		return getPersistence()
				   .findByC_C_PrevAndNext(activityId, classNameId, classPK,
			orderByComparator);
	}

	/**
	* Returns all the social activities where mirrorActivityId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param mirrorActivityId the mirror activity ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching social activities
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.social.model.SocialActivity> findByM_C_C(
		long mirrorActivityId, long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByM_C_C(mirrorActivityId, classNameId, classPK);
	}

	/**
	* Returns a range of all the social activities where mirrorActivityId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param mirrorActivityId the mirror activity ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of social activities
	* @param end the upper bound of the range of social activities (not inclusive)
	* @return the range of matching social activities
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.social.model.SocialActivity> findByM_C_C(
		long mirrorActivityId, long classNameId, long classPK, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByM_C_C(mirrorActivityId, classNameId, classPK, start,
			end);
	}

	/**
	* Returns an ordered range of all the social activities where mirrorActivityId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param mirrorActivityId the mirror activity ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of social activities
	* @param end the upper bound of the range of social activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching social activities
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.social.model.SocialActivity> findByM_C_C(
		long mirrorActivityId, long classNameId, long classPK, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByM_C_C(mirrorActivityId, classNameId, classPK, start,
			end, orderByComparator);
	}

	/**
	* Returns the first social activity in the ordered set where mirrorActivityId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param mirrorActivityId the mirror activity ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching social activity
	* @throws com.liferay.portlet.social.NoSuchActivityException if a matching social activity could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.social.model.SocialActivity findByM_C_C_First(
		long mirrorActivityId, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivityException {
		return getPersistence()
				   .findByM_C_C_First(mirrorActivityId, classNameId, classPK,
			orderByComparator);
	}

	/**
	* Returns the last social activity in the ordered set where mirrorActivityId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param mirrorActivityId the mirror activity ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching social activity
	* @throws com.liferay.portlet.social.NoSuchActivityException if a matching social activity could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.social.model.SocialActivity findByM_C_C_Last(
		long mirrorActivityId, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivityException {
		return getPersistence()
				   .findByM_C_C_Last(mirrorActivityId, classNameId, classPK,
			orderByComparator);
	}

	/**
	* Returns the social activities before and after the current social activity in the ordered set where mirrorActivityId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param activityId the primary key of the current social activity
	* @param mirrorActivityId the mirror activity ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next social activity
	* @throws com.liferay.portlet.social.NoSuchActivityException if a social activity with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.social.model.SocialActivity[] findByM_C_C_PrevAndNext(
		long activityId, long mirrorActivityId, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivityException {
		return getPersistence()
				   .findByM_C_C_PrevAndNext(activityId, mirrorActivityId,
			classNameId, classPK, orderByComparator);
	}

	/**
	* Returns all the social activities where groupId = &#63; and userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and receiverUserId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param receiverUserId the receiver user ID
	* @return the matching social activities
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.social.model.SocialActivity> findByG_U_C_C_T_R(
		long groupId, long userId, long classNameId, long classPK, int type,
		long receiverUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_U_C_C_T_R(groupId, userId, classNameId, classPK,
			type, receiverUserId);
	}

	/**
	* Returns a range of all the social activities where groupId = &#63; and userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and receiverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param receiverUserId the receiver user ID
	* @param start the lower bound of the range of social activities
	* @param end the upper bound of the range of social activities (not inclusive)
	* @return the range of matching social activities
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.social.model.SocialActivity> findByG_U_C_C_T_R(
		long groupId, long userId, long classNameId, long classPK, int type,
		long receiverUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_U_C_C_T_R(groupId, userId, classNameId, classPK,
			type, receiverUserId, start, end);
	}

	/**
	* Returns an ordered range of all the social activities where groupId = &#63; and userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and receiverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param receiverUserId the receiver user ID
	* @param start the lower bound of the range of social activities
	* @param end the upper bound of the range of social activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching social activities
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.social.model.SocialActivity> findByG_U_C_C_T_R(
		long groupId, long userId, long classNameId, long classPK, int type,
		long receiverUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_U_C_C_T_R(groupId, userId, classNameId, classPK,
			type, receiverUserId, start, end, orderByComparator);
	}

	/**
	* Returns the first social activity in the ordered set where groupId = &#63; and userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and receiverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param receiverUserId the receiver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching social activity
	* @throws com.liferay.portlet.social.NoSuchActivityException if a matching social activity could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.social.model.SocialActivity findByG_U_C_C_T_R_First(
		long groupId, long userId, long classNameId, long classPK, int type,
		long receiverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivityException {
		return getPersistence()
				   .findByG_U_C_C_T_R_First(groupId, userId, classNameId,
			classPK, type, receiverUserId, orderByComparator);
	}

	/**
	* Returns the last social activity in the ordered set where groupId = &#63; and userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and receiverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param receiverUserId the receiver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching social activity
	* @throws com.liferay.portlet.social.NoSuchActivityException if a matching social activity could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.social.model.SocialActivity findByG_U_C_C_T_R_Last(
		long groupId, long userId, long classNameId, long classPK, int type,
		long receiverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivityException {
		return getPersistence()
				   .findByG_U_C_C_T_R_Last(groupId, userId, classNameId,
			classPK, type, receiverUserId, orderByComparator);
	}

	/**
	* Returns the social activities before and after the current social activity in the ordered set where groupId = &#63; and userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and receiverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param activityId the primary key of the current social activity
	* @param groupId the group ID
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param receiverUserId the receiver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next social activity
	* @throws com.liferay.portlet.social.NoSuchActivityException if a social activity with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.social.model.SocialActivity[] findByG_U_C_C_T_R_PrevAndNext(
		long activityId, long groupId, long userId, long classNameId,
		long classPK, int type, long receiverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivityException {
		return getPersistence()
				   .findByG_U_C_C_T_R_PrevAndNext(activityId, groupId, userId,
			classNameId, classPK, type, receiverUserId, orderByComparator);
	}

	/**
	* Returns the social activity where groupId = &#63; and userId = &#63; and createDate = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and receiverUserId = &#63; or throws a {@link com.liferay.portlet.social.NoSuchActivityException} if it could not be found.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param createDate the create date
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param receiverUserId the receiver user ID
	* @return the matching social activity
	* @throws com.liferay.portlet.social.NoSuchActivityException if a matching social activity could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.social.model.SocialActivity findByG_U_CD_C_C_T_R(
		long groupId, long userId, long createDate, long classNameId,
		long classPK, int type, long receiverUserId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivityException {
		return getPersistence()
				   .findByG_U_CD_C_C_T_R(groupId, userId, createDate,
			classNameId, classPK, type, receiverUserId);
	}

	/**
	* Returns the social activity where groupId = &#63; and userId = &#63; and createDate = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and receiverUserId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param createDate the create date
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param receiverUserId the receiver user ID
	* @return the matching social activity, or <code>null</code> if a matching social activity could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.social.model.SocialActivity fetchByG_U_CD_C_C_T_R(
		long groupId, long userId, long createDate, long classNameId,
		long classPK, int type, long receiverUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_U_CD_C_C_T_R(groupId, userId, createDate,
			classNameId, classPK, type, receiverUserId);
	}

	/**
	* Returns the social activity where groupId = &#63; and userId = &#63; and createDate = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and receiverUserId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param createDate the create date
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param receiverUserId the receiver user ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching social activity, or <code>null</code> if a matching social activity could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.social.model.SocialActivity fetchByG_U_CD_C_C_T_R(
		long groupId, long userId, long createDate, long classNameId,
		long classPK, int type, long receiverUserId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_U_CD_C_C_T_R(groupId, userId, createDate,
			classNameId, classPK, type, receiverUserId, retrieveFromCache);
	}

	/**
	* Returns all the social activities.
	*
	* @return the social activities
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.social.model.SocialActivity> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the social activities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of social activities
	* @param end the upper bound of the range of social activities (not inclusive)
	* @return the range of social activities
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.social.model.SocialActivity> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the social activities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of social activities
	* @param end the upper bound of the range of social activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of social activities
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.social.model.SocialActivity> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the social activities where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Removes all the social activities where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	* Removes all the social activities where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUserId(userId);
	}

	/**
	* Removes the social activity where mirrorActivityId = &#63; from the database.
	*
	* @param mirrorActivityId the mirror activity ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByMirrorActivityId(long mirrorActivityId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivityException {
		getPersistence().removeByMirrorActivityId(mirrorActivityId);
	}

	/**
	* Removes all the social activities where classNameId = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByClassNameId(long classNameId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByClassNameId(classNameId);
	}

	/**
	* Removes all the social activities where receiverUserId = &#63; from the database.
	*
	* @param receiverUserId the receiver user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByReceiverUserId(long receiverUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByReceiverUserId(receiverUserId);
	}

	/**
	* Removes all the social activities where classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_C(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByC_C(classNameId, classPK);
	}

	/**
	* Removes all the social activities where mirrorActivityId = &#63; and classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param mirrorActivityId the mirror activity ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByM_C_C(long mirrorActivityId, long classNameId,
		long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByM_C_C(mirrorActivityId, classNameId, classPK);
	}

	/**
	* Removes all the social activities where groupId = &#63; and userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and receiverUserId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param receiverUserId the receiver user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_U_C_C_T_R(long groupId, long userId,
		long classNameId, long classPK, int type, long receiverUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByG_U_C_C_T_R(groupId, userId, classNameId, classPK, type,
			receiverUserId);
	}

	/**
	* Removes the social activity where groupId = &#63; and userId = &#63; and createDate = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and receiverUserId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param createDate the create date
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param receiverUserId the receiver user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_U_CD_C_C_T_R(long groupId, long userId,
		long createDate, long classNameId, long classPK, int type,
		long receiverUserId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivityException {
		getPersistence()
			.removeByG_U_CD_C_C_T_R(groupId, userId, createDate, classNameId,
			classPK, type, receiverUserId);
	}

	/**
	* Removes all the social activities from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of social activities where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching social activities
	* @throws SystemException if a system exception occurred
	*/
	public static int countByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns the number of social activities where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching social activities
	* @throws SystemException if a system exception occurred
	*/
	public static int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	* Returns the number of social activities where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching social activities
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Returns the number of social activities where mirrorActivityId = &#63;.
	*
	* @param mirrorActivityId the mirror activity ID
	* @return the number of matching social activities
	* @throws SystemException if a system exception occurred
	*/
	public static int countByMirrorActivityId(long mirrorActivityId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByMirrorActivityId(mirrorActivityId);
	}

	/**
	* Returns the number of social activities where classNameId = &#63;.
	*
	* @param classNameId the class name ID
	* @return the number of matching social activities
	* @throws SystemException if a system exception occurred
	*/
	public static int countByClassNameId(long classNameId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByClassNameId(classNameId);
	}

	/**
	* Returns the number of social activities where receiverUserId = &#63;.
	*
	* @param receiverUserId the receiver user ID
	* @return the number of matching social activities
	* @throws SystemException if a system exception occurred
	*/
	public static int countByReceiverUserId(long receiverUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByReceiverUserId(receiverUserId);
	}

	/**
	* Returns the number of social activities where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the number of matching social activities
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_C(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_C(classNameId, classPK);
	}

	/**
	* Returns the number of social activities where mirrorActivityId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param mirrorActivityId the mirror activity ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the number of matching social activities
	* @throws SystemException if a system exception occurred
	*/
	public static int countByM_C_C(long mirrorActivityId, long classNameId,
		long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByM_C_C(mirrorActivityId, classNameId, classPK);
	}

	/**
	* Returns the number of social activities where groupId = &#63; and userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and receiverUserId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param receiverUserId the receiver user ID
	* @return the number of matching social activities
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_U_C_C_T_R(long groupId, long userId,
		long classNameId, long classPK, int type, long receiverUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByG_U_C_C_T_R(groupId, userId, classNameId, classPK,
			type, receiverUserId);
	}

	/**
	* Returns the number of social activities where groupId = &#63; and userId = &#63; and createDate = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; and receiverUserId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param createDate the create date
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param receiverUserId the receiver user ID
	* @return the number of matching social activities
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_U_CD_C_C_T_R(long groupId, long userId,
		long createDate, long classNameId, long classPK, int type,
		long receiverUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByG_U_CD_C_C_T_R(groupId, userId, createDate,
			classNameId, classPK, type, receiverUserId);
	}

	/**
	* Returns the number of social activities.
	*
	* @return the number of social activities
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static SocialActivityPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (SocialActivityPersistence)PortalBeanLocatorUtil.locate(SocialActivityPersistence.class.getName());

			ReferenceRegistry.registerReference(SocialActivityUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(SocialActivityPersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(SocialActivityUtil.class,
			"_persistence");
	}

	private static SocialActivityPersistence _persistence;
}