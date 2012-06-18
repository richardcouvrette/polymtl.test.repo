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

package com.liferay.portlet.softwarecatalog.service.persistence;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.softwarecatalog.model.SCProductScreenshot;

import java.util.List;

/**
 * The persistence utility for the s c product screenshot service. This utility wraps {@link SCProductScreenshotPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SCProductScreenshotPersistence
 * @see SCProductScreenshotPersistenceImpl
 * @generated
 */
public class SCProductScreenshotUtil {
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
	public static void clearCache(SCProductScreenshot scProductScreenshot) {
		getPersistence().clearCache(scProductScreenshot);
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
	public static List<SCProductScreenshot> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SCProductScreenshot> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SCProductScreenshot> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static SCProductScreenshot update(
		SCProductScreenshot scProductScreenshot, boolean merge)
		throws SystemException {
		return getPersistence().update(scProductScreenshot, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static SCProductScreenshot update(
		SCProductScreenshot scProductScreenshot, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence()
				   .update(scProductScreenshot, merge, serviceContext);
	}

	/**
	* Caches the s c product screenshot in the entity cache if it is enabled.
	*
	* @param scProductScreenshot the s c product screenshot
	*/
	public static void cacheResult(
		com.liferay.portlet.softwarecatalog.model.SCProductScreenshot scProductScreenshot) {
		getPersistence().cacheResult(scProductScreenshot);
	}

	/**
	* Caches the s c product screenshots in the entity cache if it is enabled.
	*
	* @param scProductScreenshots the s c product screenshots
	*/
	public static void cacheResult(
		java.util.List<com.liferay.portlet.softwarecatalog.model.SCProductScreenshot> scProductScreenshots) {
		getPersistence().cacheResult(scProductScreenshots);
	}

	/**
	* Creates a new s c product screenshot with the primary key. Does not add the s c product screenshot to the database.
	*
	* @param productScreenshotId the primary key for the new s c product screenshot
	* @return the new s c product screenshot
	*/
	public static com.liferay.portlet.softwarecatalog.model.SCProductScreenshot create(
		long productScreenshotId) {
		return getPersistence().create(productScreenshotId);
	}

	/**
	* Removes the s c product screenshot with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param productScreenshotId the primary key of the s c product screenshot
	* @return the s c product screenshot that was removed
	* @throws com.liferay.portlet.softwarecatalog.NoSuchProductScreenshotException if a s c product screenshot with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.softwarecatalog.model.SCProductScreenshot remove(
		long productScreenshotId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.softwarecatalog.NoSuchProductScreenshotException {
		return getPersistence().remove(productScreenshotId);
	}

	public static com.liferay.portlet.softwarecatalog.model.SCProductScreenshot updateImpl(
		com.liferay.portlet.softwarecatalog.model.SCProductScreenshot scProductScreenshot,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(scProductScreenshot, merge);
	}

	/**
	* Returns the s c product screenshot with the primary key or throws a {@link com.liferay.portlet.softwarecatalog.NoSuchProductScreenshotException} if it could not be found.
	*
	* @param productScreenshotId the primary key of the s c product screenshot
	* @return the s c product screenshot
	* @throws com.liferay.portlet.softwarecatalog.NoSuchProductScreenshotException if a s c product screenshot with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.softwarecatalog.model.SCProductScreenshot findByPrimaryKey(
		long productScreenshotId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.softwarecatalog.NoSuchProductScreenshotException {
		return getPersistence().findByPrimaryKey(productScreenshotId);
	}

	/**
	* Returns the s c product screenshot with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param productScreenshotId the primary key of the s c product screenshot
	* @return the s c product screenshot, or <code>null</code> if a s c product screenshot with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.softwarecatalog.model.SCProductScreenshot fetchByPrimaryKey(
		long productScreenshotId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(productScreenshotId);
	}

	/**
	* Returns all the s c product screenshots where productEntryId = &#63;.
	*
	* @param productEntryId the product entry ID
	* @return the matching s c product screenshots
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.softwarecatalog.model.SCProductScreenshot> findByProductEntryId(
		long productEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByProductEntryId(productEntryId);
	}

	/**
	* Returns a range of all the s c product screenshots where productEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param productEntryId the product entry ID
	* @param start the lower bound of the range of s c product screenshots
	* @param end the upper bound of the range of s c product screenshots (not inclusive)
	* @return the range of matching s c product screenshots
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.softwarecatalog.model.SCProductScreenshot> findByProductEntryId(
		long productEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByProductEntryId(productEntryId, start, end);
	}

	/**
	* Returns an ordered range of all the s c product screenshots where productEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param productEntryId the product entry ID
	* @param start the lower bound of the range of s c product screenshots
	* @param end the upper bound of the range of s c product screenshots (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching s c product screenshots
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.softwarecatalog.model.SCProductScreenshot> findByProductEntryId(
		long productEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByProductEntryId(productEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first s c product screenshot in the ordered set where productEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param productEntryId the product entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching s c product screenshot
	* @throws com.liferay.portlet.softwarecatalog.NoSuchProductScreenshotException if a matching s c product screenshot could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.softwarecatalog.model.SCProductScreenshot findByProductEntryId_First(
		long productEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.softwarecatalog.NoSuchProductScreenshotException {
		return getPersistence()
				   .findByProductEntryId_First(productEntryId, orderByComparator);
	}

	/**
	* Returns the last s c product screenshot in the ordered set where productEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param productEntryId the product entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching s c product screenshot
	* @throws com.liferay.portlet.softwarecatalog.NoSuchProductScreenshotException if a matching s c product screenshot could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.softwarecatalog.model.SCProductScreenshot findByProductEntryId_Last(
		long productEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.softwarecatalog.NoSuchProductScreenshotException {
		return getPersistence()
				   .findByProductEntryId_Last(productEntryId, orderByComparator);
	}

	/**
	* Returns the s c product screenshots before and after the current s c product screenshot in the ordered set where productEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param productScreenshotId the primary key of the current s c product screenshot
	* @param productEntryId the product entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next s c product screenshot
	* @throws com.liferay.portlet.softwarecatalog.NoSuchProductScreenshotException if a s c product screenshot with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.softwarecatalog.model.SCProductScreenshot[] findByProductEntryId_PrevAndNext(
		long productScreenshotId, long productEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.softwarecatalog.NoSuchProductScreenshotException {
		return getPersistence()
				   .findByProductEntryId_PrevAndNext(productScreenshotId,
			productEntryId, orderByComparator);
	}

	/**
	* Returns the s c product screenshot where thumbnailId = &#63; or throws a {@link com.liferay.portlet.softwarecatalog.NoSuchProductScreenshotException} if it could not be found.
	*
	* @param thumbnailId the thumbnail ID
	* @return the matching s c product screenshot
	* @throws com.liferay.portlet.softwarecatalog.NoSuchProductScreenshotException if a matching s c product screenshot could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.softwarecatalog.model.SCProductScreenshot findByThumbnailId(
		long thumbnailId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.softwarecatalog.NoSuchProductScreenshotException {
		return getPersistence().findByThumbnailId(thumbnailId);
	}

	/**
	* Returns the s c product screenshot where thumbnailId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param thumbnailId the thumbnail ID
	* @return the matching s c product screenshot, or <code>null</code> if a matching s c product screenshot could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.softwarecatalog.model.SCProductScreenshot fetchByThumbnailId(
		long thumbnailId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByThumbnailId(thumbnailId);
	}

	/**
	* Returns the s c product screenshot where thumbnailId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param thumbnailId the thumbnail ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching s c product screenshot, or <code>null</code> if a matching s c product screenshot could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.softwarecatalog.model.SCProductScreenshot fetchByThumbnailId(
		long thumbnailId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByThumbnailId(thumbnailId, retrieveFromCache);
	}

	/**
	* Returns the s c product screenshot where fullImageId = &#63; or throws a {@link com.liferay.portlet.softwarecatalog.NoSuchProductScreenshotException} if it could not be found.
	*
	* @param fullImageId the full image ID
	* @return the matching s c product screenshot
	* @throws com.liferay.portlet.softwarecatalog.NoSuchProductScreenshotException if a matching s c product screenshot could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.softwarecatalog.model.SCProductScreenshot findByFullImageId(
		long fullImageId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.softwarecatalog.NoSuchProductScreenshotException {
		return getPersistence().findByFullImageId(fullImageId);
	}

	/**
	* Returns the s c product screenshot where fullImageId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param fullImageId the full image ID
	* @return the matching s c product screenshot, or <code>null</code> if a matching s c product screenshot could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.softwarecatalog.model.SCProductScreenshot fetchByFullImageId(
		long fullImageId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByFullImageId(fullImageId);
	}

	/**
	* Returns the s c product screenshot where fullImageId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param fullImageId the full image ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching s c product screenshot, or <code>null</code> if a matching s c product screenshot could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.softwarecatalog.model.SCProductScreenshot fetchByFullImageId(
		long fullImageId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByFullImageId(fullImageId, retrieveFromCache);
	}

	/**
	* Returns the s c product screenshot where productEntryId = &#63; and priority = &#63; or throws a {@link com.liferay.portlet.softwarecatalog.NoSuchProductScreenshotException} if it could not be found.
	*
	* @param productEntryId the product entry ID
	* @param priority the priority
	* @return the matching s c product screenshot
	* @throws com.liferay.portlet.softwarecatalog.NoSuchProductScreenshotException if a matching s c product screenshot could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.softwarecatalog.model.SCProductScreenshot findByP_P(
		long productEntryId, int priority)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.softwarecatalog.NoSuchProductScreenshotException {
		return getPersistence().findByP_P(productEntryId, priority);
	}

	/**
	* Returns the s c product screenshot where productEntryId = &#63; and priority = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param productEntryId the product entry ID
	* @param priority the priority
	* @return the matching s c product screenshot, or <code>null</code> if a matching s c product screenshot could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.softwarecatalog.model.SCProductScreenshot fetchByP_P(
		long productEntryId, int priority)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByP_P(productEntryId, priority);
	}

	/**
	* Returns the s c product screenshot where productEntryId = &#63; and priority = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param productEntryId the product entry ID
	* @param priority the priority
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching s c product screenshot, or <code>null</code> if a matching s c product screenshot could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.softwarecatalog.model.SCProductScreenshot fetchByP_P(
		long productEntryId, int priority, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByP_P(productEntryId, priority, retrieveFromCache);
	}

	/**
	* Returns all the s c product screenshots.
	*
	* @return the s c product screenshots
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.softwarecatalog.model.SCProductScreenshot> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the s c product screenshots.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of s c product screenshots
	* @param end the upper bound of the range of s c product screenshots (not inclusive)
	* @return the range of s c product screenshots
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.softwarecatalog.model.SCProductScreenshot> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the s c product screenshots.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of s c product screenshots
	* @param end the upper bound of the range of s c product screenshots (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of s c product screenshots
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.softwarecatalog.model.SCProductScreenshot> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the s c product screenshots where productEntryId = &#63; from the database.
	*
	* @param productEntryId the product entry ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByProductEntryId(long productEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByProductEntryId(productEntryId);
	}

	/**
	* Removes the s c product screenshot where thumbnailId = &#63; from the database.
	*
	* @param thumbnailId the thumbnail ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByThumbnailId(long thumbnailId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.softwarecatalog.NoSuchProductScreenshotException {
		getPersistence().removeByThumbnailId(thumbnailId);
	}

	/**
	* Removes the s c product screenshot where fullImageId = &#63; from the database.
	*
	* @param fullImageId the full image ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByFullImageId(long fullImageId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.softwarecatalog.NoSuchProductScreenshotException {
		getPersistence().removeByFullImageId(fullImageId);
	}

	/**
	* Removes the s c product screenshot where productEntryId = &#63; and priority = &#63; from the database.
	*
	* @param productEntryId the product entry ID
	* @param priority the priority
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByP_P(long productEntryId, int priority)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.softwarecatalog.NoSuchProductScreenshotException {
		getPersistence().removeByP_P(productEntryId, priority);
	}

	/**
	* Removes all the s c product screenshots from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of s c product screenshots where productEntryId = &#63;.
	*
	* @param productEntryId the product entry ID
	* @return the number of matching s c product screenshots
	* @throws SystemException if a system exception occurred
	*/
	public static int countByProductEntryId(long productEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByProductEntryId(productEntryId);
	}

	/**
	* Returns the number of s c product screenshots where thumbnailId = &#63;.
	*
	* @param thumbnailId the thumbnail ID
	* @return the number of matching s c product screenshots
	* @throws SystemException if a system exception occurred
	*/
	public static int countByThumbnailId(long thumbnailId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByThumbnailId(thumbnailId);
	}

	/**
	* Returns the number of s c product screenshots where fullImageId = &#63;.
	*
	* @param fullImageId the full image ID
	* @return the number of matching s c product screenshots
	* @throws SystemException if a system exception occurred
	*/
	public static int countByFullImageId(long fullImageId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByFullImageId(fullImageId);
	}

	/**
	* Returns the number of s c product screenshots where productEntryId = &#63; and priority = &#63;.
	*
	* @param productEntryId the product entry ID
	* @param priority the priority
	* @return the number of matching s c product screenshots
	* @throws SystemException if a system exception occurred
	*/
	public static int countByP_P(long productEntryId, int priority)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByP_P(productEntryId, priority);
	}

	/**
	* Returns the number of s c product screenshots.
	*
	* @return the number of s c product screenshots
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static SCProductScreenshotPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (SCProductScreenshotPersistence)PortalBeanLocatorUtil.locate(SCProductScreenshotPersistence.class.getName());

			ReferenceRegistry.registerReference(SCProductScreenshotUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(SCProductScreenshotPersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(SCProductScreenshotUtil.class,
			"_persistence");
	}

	private static SCProductScreenshotPersistence _persistence;
}