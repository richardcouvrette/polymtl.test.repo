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

package com.liferay.portal.service.persistence;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.model.ResourceBlock;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the resource block service. This utility wraps {@link ResourceBlockPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ResourceBlockPersistence
 * @see ResourceBlockPersistenceImpl
 * @generated
 */
public class ResourceBlockUtil {
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
	public static void clearCache(ResourceBlock resourceBlock) {
		getPersistence().clearCache(resourceBlock);
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
	public static List<ResourceBlock> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ResourceBlock> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ResourceBlock> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static ResourceBlock update(ResourceBlock resourceBlock,
		boolean merge) throws SystemException {
		return getPersistence().update(resourceBlock, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static ResourceBlock update(ResourceBlock resourceBlock,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(resourceBlock, merge, serviceContext);
	}

	/**
	* Caches the resource block in the entity cache if it is enabled.
	*
	* @param resourceBlock the resource block
	*/
	public static void cacheResult(
		com.liferay.portal.model.ResourceBlock resourceBlock) {
		getPersistence().cacheResult(resourceBlock);
	}

	/**
	* Caches the resource blocks in the entity cache if it is enabled.
	*
	* @param resourceBlocks the resource blocks
	*/
	public static void cacheResult(
		java.util.List<com.liferay.portal.model.ResourceBlock> resourceBlocks) {
		getPersistence().cacheResult(resourceBlocks);
	}

	/**
	* Creates a new resource block with the primary key. Does not add the resource block to the database.
	*
	* @param resourceBlockId the primary key for the new resource block
	* @return the new resource block
	*/
	public static com.liferay.portal.model.ResourceBlock create(
		long resourceBlockId) {
		return getPersistence().create(resourceBlockId);
	}

	/**
	* Removes the resource block with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param resourceBlockId the primary key of the resource block
	* @return the resource block that was removed
	* @throws com.liferay.portal.NoSuchResourceBlockException if a resource block with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.ResourceBlock remove(
		long resourceBlockId)
		throws com.liferay.portal.NoSuchResourceBlockException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(resourceBlockId);
	}

	public static com.liferay.portal.model.ResourceBlock updateImpl(
		com.liferay.portal.model.ResourceBlock resourceBlock, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(resourceBlock, merge);
	}

	/**
	* Returns the resource block with the primary key or throws a {@link com.liferay.portal.NoSuchResourceBlockException} if it could not be found.
	*
	* @param resourceBlockId the primary key of the resource block
	* @return the resource block
	* @throws com.liferay.portal.NoSuchResourceBlockException if a resource block with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.ResourceBlock findByPrimaryKey(
		long resourceBlockId)
		throws com.liferay.portal.NoSuchResourceBlockException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(resourceBlockId);
	}

	/**
	* Returns the resource block with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param resourceBlockId the primary key of the resource block
	* @return the resource block, or <code>null</code> if a resource block with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.ResourceBlock fetchByPrimaryKey(
		long resourceBlockId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(resourceBlockId);
	}

	/**
	* Returns all the resource blocks where companyId = &#63; and name = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @return the matching resource blocks
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.ResourceBlock> findByC_N(
		long companyId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_N(companyId, name);
	}

	/**
	* Returns a range of all the resource blocks where companyId = &#63; and name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param name the name
	* @param start the lower bound of the range of resource blocks
	* @param end the upper bound of the range of resource blocks (not inclusive)
	* @return the range of matching resource blocks
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.ResourceBlock> findByC_N(
		long companyId, java.lang.String name, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_N(companyId, name, start, end);
	}

	/**
	* Returns an ordered range of all the resource blocks where companyId = &#63; and name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param name the name
	* @param start the lower bound of the range of resource blocks
	* @param end the upper bound of the range of resource blocks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching resource blocks
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.ResourceBlock> findByC_N(
		long companyId, java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_N(companyId, name, start, end, orderByComparator);
	}

	/**
	* Returns the first resource block in the ordered set where companyId = &#63; and name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching resource block
	* @throws com.liferay.portal.NoSuchResourceBlockException if a matching resource block could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.ResourceBlock findByC_N_First(
		long companyId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchResourceBlockException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_N_First(companyId, name, orderByComparator);
	}

	/**
	* Returns the last resource block in the ordered set where companyId = &#63; and name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching resource block
	* @throws com.liferay.portal.NoSuchResourceBlockException if a matching resource block could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.ResourceBlock findByC_N_Last(
		long companyId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchResourceBlockException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_N_Last(companyId, name, orderByComparator);
	}

	/**
	* Returns the resource blocks before and after the current resource block in the ordered set where companyId = &#63; and name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourceBlockId the primary key of the current resource block
	* @param companyId the company ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next resource block
	* @throws com.liferay.portal.NoSuchResourceBlockException if a resource block with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.ResourceBlock[] findByC_N_PrevAndNext(
		long resourceBlockId, long companyId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchResourceBlockException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_N_PrevAndNext(resourceBlockId, companyId, name,
			orderByComparator);
	}

	/**
	* Returns all the resource blocks where companyId = &#63; and groupId = &#63; and name = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param name the name
	* @return the matching resource blocks
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.ResourceBlock> findByC_G_N(
		long companyId, long groupId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_G_N(companyId, groupId, name);
	}

	/**
	* Returns a range of all the resource blocks where companyId = &#63; and groupId = &#63; and name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param name the name
	* @param start the lower bound of the range of resource blocks
	* @param end the upper bound of the range of resource blocks (not inclusive)
	* @return the range of matching resource blocks
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.ResourceBlock> findByC_G_N(
		long companyId, long groupId, java.lang.String name, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_G_N(companyId, groupId, name, start, end);
	}

	/**
	* Returns an ordered range of all the resource blocks where companyId = &#63; and groupId = &#63; and name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param name the name
	* @param start the lower bound of the range of resource blocks
	* @param end the upper bound of the range of resource blocks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching resource blocks
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.ResourceBlock> findByC_G_N(
		long companyId, long groupId, java.lang.String name, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_G_N(companyId, groupId, name, start, end,
			orderByComparator);
	}

	/**
	* Returns the first resource block in the ordered set where companyId = &#63; and groupId = &#63; and name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching resource block
	* @throws com.liferay.portal.NoSuchResourceBlockException if a matching resource block could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.ResourceBlock findByC_G_N_First(
		long companyId, long groupId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchResourceBlockException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_G_N_First(companyId, groupId, name,
			orderByComparator);
	}

	/**
	* Returns the last resource block in the ordered set where companyId = &#63; and groupId = &#63; and name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching resource block
	* @throws com.liferay.portal.NoSuchResourceBlockException if a matching resource block could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.ResourceBlock findByC_G_N_Last(
		long companyId, long groupId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchResourceBlockException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_G_N_Last(companyId, groupId, name, orderByComparator);
	}

	/**
	* Returns the resource blocks before and after the current resource block in the ordered set where companyId = &#63; and groupId = &#63; and name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourceBlockId the primary key of the current resource block
	* @param companyId the company ID
	* @param groupId the group ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next resource block
	* @throws com.liferay.portal.NoSuchResourceBlockException if a resource block with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.ResourceBlock[] findByC_G_N_PrevAndNext(
		long resourceBlockId, long companyId, long groupId,
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.NoSuchResourceBlockException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_G_N_PrevAndNext(resourceBlockId, companyId,
			groupId, name, orderByComparator);
	}

	/**
	* Returns the resource block where companyId = &#63; and groupId = &#63; and name = &#63; and permissionsHash = &#63; or throws a {@link com.liferay.portal.NoSuchResourceBlockException} if it could not be found.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param name the name
	* @param permissionsHash the permissions hash
	* @return the matching resource block
	* @throws com.liferay.portal.NoSuchResourceBlockException if a matching resource block could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.ResourceBlock findByC_G_N_P(
		long companyId, long groupId, java.lang.String name,
		java.lang.String permissionsHash)
		throws com.liferay.portal.NoSuchResourceBlockException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_G_N_P(companyId, groupId, name, permissionsHash);
	}

	/**
	* Returns the resource block where companyId = &#63; and groupId = &#63; and name = &#63; and permissionsHash = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param name the name
	* @param permissionsHash the permissions hash
	* @return the matching resource block, or <code>null</code> if a matching resource block could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.ResourceBlock fetchByC_G_N_P(
		long companyId, long groupId, java.lang.String name,
		java.lang.String permissionsHash)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_G_N_P(companyId, groupId, name, permissionsHash);
	}

	/**
	* Returns the resource block where companyId = &#63; and groupId = &#63; and name = &#63; and permissionsHash = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param name the name
	* @param permissionsHash the permissions hash
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching resource block, or <code>null</code> if a matching resource block could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.ResourceBlock fetchByC_G_N_P(
		long companyId, long groupId, java.lang.String name,
		java.lang.String permissionsHash, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_G_N_P(companyId, groupId, name, permissionsHash,
			retrieveFromCache);
	}

	/**
	* Returns all the resource blocks.
	*
	* @return the resource blocks
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.ResourceBlock> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the resource blocks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of resource blocks
	* @param end the upper bound of the range of resource blocks (not inclusive)
	* @return the range of resource blocks
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.ResourceBlock> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the resource blocks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of resource blocks
	* @param end the upper bound of the range of resource blocks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of resource blocks
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.ResourceBlock> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the resource blocks where companyId = &#63; and name = &#63; from the database.
	*
	* @param companyId the company ID
	* @param name the name
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_N(long companyId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByC_N(companyId, name);
	}

	/**
	* Removes all the resource blocks where companyId = &#63; and groupId = &#63; and name = &#63; from the database.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param name the name
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_G_N(long companyId, long groupId,
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByC_G_N(companyId, groupId, name);
	}

	/**
	* Removes the resource block where companyId = &#63; and groupId = &#63; and name = &#63; and permissionsHash = &#63; from the database.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param name the name
	* @param permissionsHash the permissions hash
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_G_N_P(long companyId, long groupId,
		java.lang.String name, java.lang.String permissionsHash)
		throws com.liferay.portal.NoSuchResourceBlockException,
			com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByC_G_N_P(companyId, groupId, name, permissionsHash);
	}

	/**
	* Removes all the resource blocks from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of resource blocks where companyId = &#63; and name = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @return the number of matching resource blocks
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_N(long companyId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_N(companyId, name);
	}

	/**
	* Returns the number of resource blocks where companyId = &#63; and groupId = &#63; and name = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param name the name
	* @return the number of matching resource blocks
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_G_N(long companyId, long groupId,
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_G_N(companyId, groupId, name);
	}

	/**
	* Returns the number of resource blocks where companyId = &#63; and groupId = &#63; and name = &#63; and permissionsHash = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param name the name
	* @param permissionsHash the permissions hash
	* @return the number of matching resource blocks
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_G_N_P(long companyId, long groupId,
		java.lang.String name, java.lang.String permissionsHash)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByC_G_N_P(companyId, groupId, name, permissionsHash);
	}

	/**
	* Returns the number of resource blocks.
	*
	* @return the number of resource blocks
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static ResourceBlockPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (ResourceBlockPersistence)PortalBeanLocatorUtil.locate(ResourceBlockPersistence.class.getName());

			ReferenceRegistry.registerReference(ResourceBlockUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(ResourceBlockPersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(ResourceBlockUtil.class,
			"_persistence");
	}

	private static ResourceBlockPersistence _persistence;
}