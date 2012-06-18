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

import com.liferay.portal.service.persistence.BasePersistence;

import com.liferay.portlet.social.model.SocialActivityLimit;

/**
 * The persistence interface for the social activity limit service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SocialActivityLimitPersistenceImpl
 * @see SocialActivityLimitUtil
 * @generated
 */
public interface SocialActivityLimitPersistence extends BasePersistence<SocialActivityLimit> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SocialActivityLimitUtil} to access the social activity limit persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the social activity limit in the entity cache if it is enabled.
	*
	* @param socialActivityLimit the social activity limit
	*/
	public void cacheResult(
		com.liferay.portlet.social.model.SocialActivityLimit socialActivityLimit);

	/**
	* Caches the social activity limits in the entity cache if it is enabled.
	*
	* @param socialActivityLimits the social activity limits
	*/
	public void cacheResult(
		java.util.List<com.liferay.portlet.social.model.SocialActivityLimit> socialActivityLimits);

	/**
	* Creates a new social activity limit with the primary key. Does not add the social activity limit to the database.
	*
	* @param activityLimitId the primary key for the new social activity limit
	* @return the new social activity limit
	*/
	public com.liferay.portlet.social.model.SocialActivityLimit create(
		long activityLimitId);

	/**
	* Removes the social activity limit with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param activityLimitId the primary key of the social activity limit
	* @return the social activity limit that was removed
	* @throws com.liferay.portlet.social.NoSuchActivityLimitException if a social activity limit with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.social.model.SocialActivityLimit remove(
		long activityLimitId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivityLimitException;

	public com.liferay.portlet.social.model.SocialActivityLimit updateImpl(
		com.liferay.portlet.social.model.SocialActivityLimit socialActivityLimit,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the social activity limit with the primary key or throws a {@link com.liferay.portlet.social.NoSuchActivityLimitException} if it could not be found.
	*
	* @param activityLimitId the primary key of the social activity limit
	* @return the social activity limit
	* @throws com.liferay.portlet.social.NoSuchActivityLimitException if a social activity limit with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.social.model.SocialActivityLimit findByPrimaryKey(
		long activityLimitId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivityLimitException;

	/**
	* Returns the social activity limit with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param activityLimitId the primary key of the social activity limit
	* @return the social activity limit, or <code>null</code> if a social activity limit with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.social.model.SocialActivityLimit fetchByPrimaryKey(
		long activityLimitId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the social activity limits where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching social activity limits
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portlet.social.model.SocialActivityLimit> findByC_C(
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the social activity limits where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of social activity limits
	* @param end the upper bound of the range of social activity limits (not inclusive)
	* @return the range of matching social activity limits
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portlet.social.model.SocialActivityLimit> findByC_C(
		long classNameId, long classPK, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the social activity limits where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of social activity limits
	* @param end the upper bound of the range of social activity limits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching social activity limits
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portlet.social.model.SocialActivityLimit> findByC_C(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first social activity limit in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching social activity limit
	* @throws com.liferay.portlet.social.NoSuchActivityLimitException if a matching social activity limit could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.social.model.SocialActivityLimit findByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivityLimitException;

	/**
	* Returns the last social activity limit in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching social activity limit
	* @throws com.liferay.portlet.social.NoSuchActivityLimitException if a matching social activity limit could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.social.model.SocialActivityLimit findByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivityLimitException;

	/**
	* Returns the social activity limits before and after the current social activity limit in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param activityLimitId the primary key of the current social activity limit
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next social activity limit
	* @throws com.liferay.portlet.social.NoSuchActivityLimitException if a social activity limit with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.social.model.SocialActivityLimit[] findByC_C_PrevAndNext(
		long activityLimitId, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivityLimitException;

	/**
	* Returns the social activity limit where groupId = &#63; and userId = &#63; and classNameId = &#63; and classPK = &#63; and activityType = &#63; and activityCounterName = &#63; or throws a {@link com.liferay.portlet.social.NoSuchActivityLimitException} if it could not be found.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param activityType the activity type
	* @param activityCounterName the activity counter name
	* @return the matching social activity limit
	* @throws com.liferay.portlet.social.NoSuchActivityLimitException if a matching social activity limit could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.social.model.SocialActivityLimit findByG_U_C_C_A_A(
		long groupId, long userId, long classNameId, long classPK,
		int activityType, java.lang.String activityCounterName)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivityLimitException;

	/**
	* Returns the social activity limit where groupId = &#63; and userId = &#63; and classNameId = &#63; and classPK = &#63; and activityType = &#63; and activityCounterName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param activityType the activity type
	* @param activityCounterName the activity counter name
	* @return the matching social activity limit, or <code>null</code> if a matching social activity limit could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.social.model.SocialActivityLimit fetchByG_U_C_C_A_A(
		long groupId, long userId, long classNameId, long classPK,
		int activityType, java.lang.String activityCounterName)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the social activity limit where groupId = &#63; and userId = &#63; and classNameId = &#63; and classPK = &#63; and activityType = &#63; and activityCounterName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param activityType the activity type
	* @param activityCounterName the activity counter name
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching social activity limit, or <code>null</code> if a matching social activity limit could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.social.model.SocialActivityLimit fetchByG_U_C_C_A_A(
		long groupId, long userId, long classNameId, long classPK,
		int activityType, java.lang.String activityCounterName,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the social activity limits.
	*
	* @return the social activity limits
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portlet.social.model.SocialActivityLimit> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the social activity limits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of social activity limits
	* @param end the upper bound of the range of social activity limits (not inclusive)
	* @return the range of social activity limits
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portlet.social.model.SocialActivityLimit> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the social activity limits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of social activity limits
	* @param end the upper bound of the range of social activity limits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of social activity limits
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portlet.social.model.SocialActivityLimit> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the social activity limits where classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @throws SystemException if a system exception occurred
	*/
	public void removeByC_C(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the social activity limit where groupId = &#63; and userId = &#63; and classNameId = &#63; and classPK = &#63; and activityType = &#63; and activityCounterName = &#63; from the database.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param activityType the activity type
	* @param activityCounterName the activity counter name
	* @throws SystemException if a system exception occurred
	*/
	public void removeByG_U_C_C_A_A(long groupId, long userId,
		long classNameId, long classPK, int activityType,
		java.lang.String activityCounterName)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.social.NoSuchActivityLimitException;

	/**
	* Removes all the social activity limits from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of social activity limits where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the number of matching social activity limits
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_C(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of social activity limits where groupId = &#63; and userId = &#63; and classNameId = &#63; and classPK = &#63; and activityType = &#63; and activityCounterName = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param activityType the activity type
	* @param activityCounterName the activity counter name
	* @return the number of matching social activity limits
	* @throws SystemException if a system exception occurred
	*/
	public int countByG_U_C_C_A_A(long groupId, long userId, long classNameId,
		long classPK, int activityType, java.lang.String activityCounterName)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of social activity limits.
	*
	* @return the number of social activity limits
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}