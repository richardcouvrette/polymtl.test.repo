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

import com.liferay.portlet.social.model.SocialActivitySetting;

import java.util.List;

/**
 * The persistence utility for the social activity setting service. This utility wraps {@link SocialActivitySettingPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SocialActivitySettingPersistence
 * @see SocialActivitySettingPersistenceImpl
 * @generated
 */
public class SocialActivitySettingUtil {
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
	public static void clearCache(SocialActivitySetting socialActivitySetting) {
		getPersistence().clearCache(socialActivitySetting);
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
	public static List<SocialActivitySetting> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SocialActivitySetting> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SocialActivitySetting> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static SocialActivitySetting update(
		SocialActivitySetting socialActivitySetting, boolean merge)
		throws SystemException {
		return getPersistence().update(socialActivitySetting, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static SocialActivitySetting update(
		SocialActivitySetting socialActivitySetting, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence()
				   .update(socialActivitySetting, merge, serviceContext);
	}

	/**
	* Caches the social activity setting in the entity cache if it is enabled.
	*
	* @param socialActivitySetting the social activity setting
	*/
	public static void cacheResult(
		com.liferay.portlet.social.model.SocialActivitySetting socialActivitySetting) {
		getPersistence().cacheResult(socialActivitySetting);
	}

	/**
	* Caches the social activity settings in the entity cache if it is enabled.
	*
	* @param socialActivitySettings the social activity settings
	*/
	public static void cacheResult(
		java.util.List<com.liferay.portlet.social.model.SocialActivitySetting> socialActivitySettings) {
		getPersistence().cacheResult(socialActivitySettings);
	}

	/**
	* Creates a new social activity setting with the primary key. Does not add the social activity setting to the database.
	*
	* @param activitySettingId the primary key for the new social activity setting
	* @return the new social activity setting
	*/
	public static com.liferay.portlet.social.model.SocialActivitySetting create(
		long activitySettingId) {
		return getPersistence().create(activitySettingId);
	}

	/**
	* Removes the social activity setting with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param activitySettingId the primary key of the social activity setting
	* @return the social activity setting that was removed
	* @throws com.liferay.portlet.social.NoSuchActivitySettingException if a social activity setting with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.social.model.SocialActivitySetting remove(
		long activitySettingId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivitySettingException {
		return getPersistence().remove(activitySettingId);
	}

	public static com.liferay.portlet.social.model.SocialActivitySetting updateImpl(
		com.liferay.portlet.social.model.SocialActivitySetting socialActivitySetting,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(socialActivitySetting, merge);
	}

	/**
	* Returns the social activity setting with the primary key or throws a {@link com.liferay.portlet.social.NoSuchActivitySettingException} if it could not be found.
	*
	* @param activitySettingId the primary key of the social activity setting
	* @return the social activity setting
	* @throws com.liferay.portlet.social.NoSuchActivitySettingException if a social activity setting with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.social.model.SocialActivitySetting findByPrimaryKey(
		long activitySettingId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivitySettingException {
		return getPersistence().findByPrimaryKey(activitySettingId);
	}

	/**
	* Returns the social activity setting with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param activitySettingId the primary key of the social activity setting
	* @return the social activity setting, or <code>null</code> if a social activity setting with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.social.model.SocialActivitySetting fetchByPrimaryKey(
		long activitySettingId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(activitySettingId);
	}

	/**
	* Returns all the social activity settings where groupId = &#63; and activityType = &#63;.
	*
	* @param groupId the group ID
	* @param activityType the activity type
	* @return the matching social activity settings
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.social.model.SocialActivitySetting> findByG_A(
		long groupId, int activityType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_A(groupId, activityType);
	}

	/**
	* Returns a range of all the social activity settings where groupId = &#63; and activityType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param activityType the activity type
	* @param start the lower bound of the range of social activity settings
	* @param end the upper bound of the range of social activity settings (not inclusive)
	* @return the range of matching social activity settings
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.social.model.SocialActivitySetting> findByG_A(
		long groupId, int activityType, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_A(groupId, activityType, start, end);
	}

	/**
	* Returns an ordered range of all the social activity settings where groupId = &#63; and activityType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param activityType the activity type
	* @param start the lower bound of the range of social activity settings
	* @param end the upper bound of the range of social activity settings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching social activity settings
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.social.model.SocialActivitySetting> findByG_A(
		long groupId, int activityType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_A(groupId, activityType, start, end,
			orderByComparator);
	}

	/**
	* Returns the first social activity setting in the ordered set where groupId = &#63; and activityType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param activityType the activity type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching social activity setting
	* @throws com.liferay.portlet.social.NoSuchActivitySettingException if a matching social activity setting could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.social.model.SocialActivitySetting findByG_A_First(
		long groupId, int activityType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivitySettingException {
		return getPersistence()
				   .findByG_A_First(groupId, activityType, orderByComparator);
	}

	/**
	* Returns the last social activity setting in the ordered set where groupId = &#63; and activityType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param activityType the activity type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching social activity setting
	* @throws com.liferay.portlet.social.NoSuchActivitySettingException if a matching social activity setting could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.social.model.SocialActivitySetting findByG_A_Last(
		long groupId, int activityType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivitySettingException {
		return getPersistence()
				   .findByG_A_Last(groupId, activityType, orderByComparator);
	}

	/**
	* Returns the social activity settings before and after the current social activity setting in the ordered set where groupId = &#63; and activityType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param activitySettingId the primary key of the current social activity setting
	* @param groupId the group ID
	* @param activityType the activity type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next social activity setting
	* @throws com.liferay.portlet.social.NoSuchActivitySettingException if a social activity setting with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.social.model.SocialActivitySetting[] findByG_A_PrevAndNext(
		long activitySettingId, long groupId, int activityType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivitySettingException {
		return getPersistence()
				   .findByG_A_PrevAndNext(activitySettingId, groupId,
			activityType, orderByComparator);
	}

	/**
	* Returns all the social activity settings where groupId = &#63; and classNameId = &#63; and activityType = &#63;.
	*
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @param activityType the activity type
	* @return the matching social activity settings
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.social.model.SocialActivitySetting> findByG_C_A(
		long groupId, long classNameId, int activityType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_C_A(groupId, classNameId, activityType);
	}

	/**
	* Returns a range of all the social activity settings where groupId = &#63; and classNameId = &#63; and activityType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @param activityType the activity type
	* @param start the lower bound of the range of social activity settings
	* @param end the upper bound of the range of social activity settings (not inclusive)
	* @return the range of matching social activity settings
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.social.model.SocialActivitySetting> findByG_C_A(
		long groupId, long classNameId, int activityType, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_C_A(groupId, classNameId, activityType, start, end);
	}

	/**
	* Returns an ordered range of all the social activity settings where groupId = &#63; and classNameId = &#63; and activityType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @param activityType the activity type
	* @param start the lower bound of the range of social activity settings
	* @param end the upper bound of the range of social activity settings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching social activity settings
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.social.model.SocialActivitySetting> findByG_C_A(
		long groupId, long classNameId, int activityType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_C_A(groupId, classNameId, activityType, start, end,
			orderByComparator);
	}

	/**
	* Returns the first social activity setting in the ordered set where groupId = &#63; and classNameId = &#63; and activityType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @param activityType the activity type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching social activity setting
	* @throws com.liferay.portlet.social.NoSuchActivitySettingException if a matching social activity setting could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.social.model.SocialActivitySetting findByG_C_A_First(
		long groupId, long classNameId, int activityType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivitySettingException {
		return getPersistence()
				   .findByG_C_A_First(groupId, classNameId, activityType,
			orderByComparator);
	}

	/**
	* Returns the last social activity setting in the ordered set where groupId = &#63; and classNameId = &#63; and activityType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @param activityType the activity type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching social activity setting
	* @throws com.liferay.portlet.social.NoSuchActivitySettingException if a matching social activity setting could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.social.model.SocialActivitySetting findByG_C_A_Last(
		long groupId, long classNameId, int activityType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivitySettingException {
		return getPersistence()
				   .findByG_C_A_Last(groupId, classNameId, activityType,
			orderByComparator);
	}

	/**
	* Returns the social activity settings before and after the current social activity setting in the ordered set where groupId = &#63; and classNameId = &#63; and activityType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param activitySettingId the primary key of the current social activity setting
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @param activityType the activity type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next social activity setting
	* @throws com.liferay.portlet.social.NoSuchActivitySettingException if a social activity setting with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.social.model.SocialActivitySetting[] findByG_C_A_PrevAndNext(
		long activitySettingId, long groupId, long classNameId,
		int activityType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivitySettingException {
		return getPersistence()
				   .findByG_C_A_PrevAndNext(activitySettingId, groupId,
			classNameId, activityType, orderByComparator);
	}

	/**
	* Returns the social activity setting where groupId = &#63; and classNameId = &#63; and activityType = &#63; and name = &#63; or throws a {@link com.liferay.portlet.social.NoSuchActivitySettingException} if it could not be found.
	*
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @param activityType the activity type
	* @param name the name
	* @return the matching social activity setting
	* @throws com.liferay.portlet.social.NoSuchActivitySettingException if a matching social activity setting could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.social.model.SocialActivitySetting findByG_C_A_N(
		long groupId, long classNameId, int activityType, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivitySettingException {
		return getPersistence()
				   .findByG_C_A_N(groupId, classNameId, activityType, name);
	}

	/**
	* Returns the social activity setting where groupId = &#63; and classNameId = &#63; and activityType = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @param activityType the activity type
	* @param name the name
	* @return the matching social activity setting, or <code>null</code> if a matching social activity setting could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.social.model.SocialActivitySetting fetchByG_C_A_N(
		long groupId, long classNameId, int activityType, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_C_A_N(groupId, classNameId, activityType, name);
	}

	/**
	* Returns the social activity setting where groupId = &#63; and classNameId = &#63; and activityType = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @param activityType the activity type
	* @param name the name
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching social activity setting, or <code>null</code> if a matching social activity setting could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.social.model.SocialActivitySetting fetchByG_C_A_N(
		long groupId, long classNameId, int activityType,
		java.lang.String name, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_C_A_N(groupId, classNameId, activityType, name,
			retrieveFromCache);
	}

	/**
	* Returns all the social activity settings.
	*
	* @return the social activity settings
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.social.model.SocialActivitySetting> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the social activity settings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of social activity settings
	* @param end the upper bound of the range of social activity settings (not inclusive)
	* @return the range of social activity settings
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.social.model.SocialActivitySetting> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the social activity settings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of social activity settings
	* @param end the upper bound of the range of social activity settings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of social activity settings
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.social.model.SocialActivitySetting> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the social activity settings where groupId = &#63; and activityType = &#63; from the database.
	*
	* @param groupId the group ID
	* @param activityType the activity type
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_A(long groupId, int activityType)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_A(groupId, activityType);
	}

	/**
	* Removes all the social activity settings where groupId = &#63; and classNameId = &#63; and activityType = &#63; from the database.
	*
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @param activityType the activity type
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_C_A(long groupId, long classNameId,
		int activityType)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_C_A(groupId, classNameId, activityType);
	}

	/**
	* Removes the social activity setting where groupId = &#63; and classNameId = &#63; and activityType = &#63; and name = &#63; from the database.
	*
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @param activityType the activity type
	* @param name the name
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_C_A_N(long groupId, long classNameId,
		int activityType, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivitySettingException {
		getPersistence()
			.removeByG_C_A_N(groupId, classNameId, activityType, name);
	}

	/**
	* Removes all the social activity settings from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of social activity settings where groupId = &#63; and activityType = &#63;.
	*
	* @param groupId the group ID
	* @param activityType the activity type
	* @return the number of matching social activity settings
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_A(long groupId, int activityType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_A(groupId, activityType);
	}

	/**
	* Returns the number of social activity settings where groupId = &#63; and classNameId = &#63; and activityType = &#63;.
	*
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @param activityType the activity type
	* @return the number of matching social activity settings
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_C_A(long groupId, long classNameId,
		int activityType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_C_A(groupId, classNameId, activityType);
	}

	/**
	* Returns the number of social activity settings where groupId = &#63; and classNameId = &#63; and activityType = &#63; and name = &#63;.
	*
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @param activityType the activity type
	* @param name the name
	* @return the number of matching social activity settings
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_C_A_N(long groupId, long classNameId,
		int activityType, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByG_C_A_N(groupId, classNameId, activityType, name);
	}

	/**
	* Returns the number of social activity settings.
	*
	* @return the number of social activity settings
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static SocialActivitySettingPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (SocialActivitySettingPersistence)PortalBeanLocatorUtil.locate(SocialActivitySettingPersistence.class.getName());

			ReferenceRegistry.registerReference(SocialActivitySettingUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(SocialActivitySettingPersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(SocialActivitySettingUtil.class,
			"_persistence");
	}

	private static SocialActivitySettingPersistence _persistence;
}