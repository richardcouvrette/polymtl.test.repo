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

package com.liferay.portlet.documentlibrary.service.persistence;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.documentlibrary.model.DLFileEntry;

import java.util.List;

/**
 * The persistence utility for the document library file entry service. This utility wraps {@link DLFileEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DLFileEntryPersistence
 * @see DLFileEntryPersistenceImpl
 * @generated
 */
public class DLFileEntryUtil {
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
	public static void clearCache(DLFileEntry dlFileEntry) {
		getPersistence().clearCache(dlFileEntry);
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
	public static List<DLFileEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<DLFileEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<DLFileEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static DLFileEntry update(DLFileEntry dlFileEntry, boolean merge)
		throws SystemException {
		return getPersistence().update(dlFileEntry, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static DLFileEntry update(DLFileEntry dlFileEntry, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(dlFileEntry, merge, serviceContext);
	}

	/**
	* Caches the document library file entry in the entity cache if it is enabled.
	*
	* @param dlFileEntry the document library file entry
	*/
	public static void cacheResult(
		com.liferay.portlet.documentlibrary.model.DLFileEntry dlFileEntry) {
		getPersistence().cacheResult(dlFileEntry);
	}

	/**
	* Caches the document library file entries in the entity cache if it is enabled.
	*
	* @param dlFileEntries the document library file entries
	*/
	public static void cacheResult(
		java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> dlFileEntries) {
		getPersistence().cacheResult(dlFileEntries);
	}

	/**
	* Creates a new document library file entry with the primary key. Does not add the document library file entry to the database.
	*
	* @param fileEntryId the primary key for the new document library file entry
	* @return the new document library file entry
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFileEntry create(
		long fileEntryId) {
		return getPersistence().create(fileEntryId);
	}

	/**
	* Removes the document library file entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param fileEntryId the primary key of the document library file entry
	* @return the document library file entry that was removed
	* @throws com.liferay.portlet.documentlibrary.NoSuchFileEntryException if a document library file entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFileEntry remove(
		long fileEntryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFileEntryException {
		return getPersistence().remove(fileEntryId);
	}

	public static com.liferay.portlet.documentlibrary.model.DLFileEntry updateImpl(
		com.liferay.portlet.documentlibrary.model.DLFileEntry dlFileEntry,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(dlFileEntry, merge);
	}

	/**
	* Returns the document library file entry with the primary key or throws a {@link com.liferay.portlet.documentlibrary.NoSuchFileEntryException} if it could not be found.
	*
	* @param fileEntryId the primary key of the document library file entry
	* @return the document library file entry
	* @throws com.liferay.portlet.documentlibrary.NoSuchFileEntryException if a document library file entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFileEntry findByPrimaryKey(
		long fileEntryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFileEntryException {
		return getPersistence().findByPrimaryKey(fileEntryId);
	}

	/**
	* Returns the document library file entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param fileEntryId the primary key of the document library file entry
	* @return the document library file entry, or <code>null</code> if a document library file entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFileEntry fetchByPrimaryKey(
		long fileEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(fileEntryId);
	}

	/**
	* Returns all the document library file entries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching document library file entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the document library file entries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of document library file entries
	* @param end the upper bound of the range of document library file entries (not inclusive)
	* @return the range of matching document library file entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the document library file entries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of document library file entries
	* @param end the upper bound of the range of document library file entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching document library file entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns the first document library file entry in the ordered set where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching document library file entry
	* @throws com.liferay.portlet.documentlibrary.NoSuchFileEntryException if a matching document library file entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFileEntry findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFileEntryException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last document library file entry in the ordered set where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching document library file entry
	* @throws com.liferay.portlet.documentlibrary.NoSuchFileEntryException if a matching document library file entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFileEntry findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFileEntryException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the document library file entries before and after the current document library file entry in the ordered set where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param fileEntryId the primary key of the current document library file entry
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next document library file entry
	* @throws com.liferay.portlet.documentlibrary.NoSuchFileEntryException if a document library file entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFileEntry[] findByUuid_PrevAndNext(
		long fileEntryId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFileEntryException {
		return getPersistence()
				   .findByUuid_PrevAndNext(fileEntryId, uuid, orderByComparator);
	}

	/**
	* Returns the document library file entry where uuid = &#63; and groupId = &#63; or throws a {@link com.liferay.portlet.documentlibrary.NoSuchFileEntryException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching document library file entry
	* @throws com.liferay.portlet.documentlibrary.NoSuchFileEntryException if a matching document library file entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFileEntry findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFileEntryException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the document library file entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching document library file entry, or <code>null</code> if a matching document library file entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFileEntry fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the document library file entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching document library file entry, or <code>null</code> if a matching document library file entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFileEntry fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Returns all the document library file entries where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching document library file entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> findByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the document library file entries where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of document library file entries
	* @param end the upper bound of the range of document library file entries (not inclusive)
	* @return the range of matching document library file entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the document library file entries where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of document library file entries
	* @param end the upper bound of the range of document library file entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching document library file entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns the first document library file entry in the ordered set where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching document library file entry
	* @throws com.liferay.portlet.documentlibrary.NoSuchFileEntryException if a matching document library file entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFileEntry findByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFileEntryException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last document library file entry in the ordered set where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching document library file entry
	* @throws com.liferay.portlet.documentlibrary.NoSuchFileEntryException if a matching document library file entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFileEntry findByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFileEntryException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the document library file entries before and after the current document library file entry in the ordered set where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param fileEntryId the primary key of the current document library file entry
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next document library file entry
	* @throws com.liferay.portlet.documentlibrary.NoSuchFileEntryException if a document library file entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFileEntry[] findByGroupId_PrevAndNext(
		long fileEntryId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFileEntryException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(fileEntryId, groupId,
			orderByComparator);
	}

	/**
	* Returns all the document library file entries that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching document library file entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> filterFindByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByGroupId(groupId);
	}

	/**
	* Returns a range of all the document library file entries that the user has permission to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of document library file entries
	* @param end the upper bound of the range of document library file entries (not inclusive)
	* @return the range of matching document library file entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> filterFindByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the document library file entries that the user has permissions to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of document library file entries
	* @param end the upper bound of the range of document library file entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching document library file entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> filterFindByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns the document library file entries before and after the current document library file entry in the ordered set of document library file entries that the user has permission to view where groupId = &#63;.
	*
	* @param fileEntryId the primary key of the current document library file entry
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next document library file entry
	* @throws com.liferay.portlet.documentlibrary.NoSuchFileEntryException if a document library file entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFileEntry[] filterFindByGroupId_PrevAndNext(
		long fileEntryId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFileEntryException {
		return getPersistence()
				   .filterFindByGroupId_PrevAndNext(fileEntryId, groupId,
			orderByComparator);
	}

	/**
	* Returns all the document library file entries where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching document library file entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	* Returns a range of all the document library file entries where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of document library file entries
	* @param end the upper bound of the range of document library file entries (not inclusive)
	* @return the range of matching document library file entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the document library file entries where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of document library file entries
	* @param end the upper bound of the range of document library file entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching document library file entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first document library file entry in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching document library file entry
	* @throws com.liferay.portlet.documentlibrary.NoSuchFileEntryException if a matching document library file entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFileEntry findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFileEntryException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the last document library file entry in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching document library file entry
	* @throws com.liferay.portlet.documentlibrary.NoSuchFileEntryException if a matching document library file entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFileEntry findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFileEntryException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the document library file entries before and after the current document library file entry in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param fileEntryId the primary key of the current document library file entry
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next document library file entry
	* @throws com.liferay.portlet.documentlibrary.NoSuchFileEntryException if a document library file entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFileEntry[] findByCompanyId_PrevAndNext(
		long fileEntryId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFileEntryException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(fileEntryId, companyId,
			orderByComparator);
	}

	/**
	* Returns all the document library file entries where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the matching document library file entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> findByG_U(
		long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_U(groupId, userId);
	}

	/**
	* Returns a range of all the document library file entries where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param start the lower bound of the range of document library file entries
	* @param end the upper bound of the range of document library file entries (not inclusive)
	* @return the range of matching document library file entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> findByG_U(
		long groupId, long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_U(groupId, userId, start, end);
	}

	/**
	* Returns an ordered range of all the document library file entries where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param start the lower bound of the range of document library file entries
	* @param end the upper bound of the range of document library file entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching document library file entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> findByG_U(
		long groupId, long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_U(groupId, userId, start, end, orderByComparator);
	}

	/**
	* Returns the first document library file entry in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching document library file entry
	* @throws com.liferay.portlet.documentlibrary.NoSuchFileEntryException if a matching document library file entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFileEntry findByG_U_First(
		long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFileEntryException {
		return getPersistence()
				   .findByG_U_First(groupId, userId, orderByComparator);
	}

	/**
	* Returns the last document library file entry in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching document library file entry
	* @throws com.liferay.portlet.documentlibrary.NoSuchFileEntryException if a matching document library file entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFileEntry findByG_U_Last(
		long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFileEntryException {
		return getPersistence()
				   .findByG_U_Last(groupId, userId, orderByComparator);
	}

	/**
	* Returns the document library file entries before and after the current document library file entry in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param fileEntryId the primary key of the current document library file entry
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next document library file entry
	* @throws com.liferay.portlet.documentlibrary.NoSuchFileEntryException if a document library file entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFileEntry[] findByG_U_PrevAndNext(
		long fileEntryId, long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFileEntryException {
		return getPersistence()
				   .findByG_U_PrevAndNext(fileEntryId, groupId, userId,
			orderByComparator);
	}

	/**
	* Returns all the document library file entries that the user has permission to view where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the matching document library file entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> filterFindByG_U(
		long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByG_U(groupId, userId);
	}

	/**
	* Returns a range of all the document library file entries that the user has permission to view where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param start the lower bound of the range of document library file entries
	* @param end the upper bound of the range of document library file entries (not inclusive)
	* @return the range of matching document library file entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> filterFindByG_U(
		long groupId, long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByG_U(groupId, userId, start, end);
	}

	/**
	* Returns an ordered range of all the document library file entries that the user has permissions to view where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param start the lower bound of the range of document library file entries
	* @param end the upper bound of the range of document library file entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching document library file entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> filterFindByG_U(
		long groupId, long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_U(groupId, userId, start, end,
			orderByComparator);
	}

	/**
	* Returns the document library file entries before and after the current document library file entry in the ordered set of document library file entries that the user has permission to view where groupId = &#63; and userId = &#63;.
	*
	* @param fileEntryId the primary key of the current document library file entry
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next document library file entry
	* @throws com.liferay.portlet.documentlibrary.NoSuchFileEntryException if a document library file entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFileEntry[] filterFindByG_U_PrevAndNext(
		long fileEntryId, long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFileEntryException {
		return getPersistence()
				   .filterFindByG_U_PrevAndNext(fileEntryId, groupId, userId,
			orderByComparator);
	}

	/**
	* Returns all the document library file entries where groupId = &#63; and folderId = &#63;.
	*
	* @param groupId the group ID
	* @param folderId the folder ID
	* @return the matching document library file entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> findByG_F(
		long groupId, long folderId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_F(groupId, folderId);
	}

	/**
	* Returns a range of all the document library file entries where groupId = &#63; and folderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param folderId the folder ID
	* @param start the lower bound of the range of document library file entries
	* @param end the upper bound of the range of document library file entries (not inclusive)
	* @return the range of matching document library file entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> findByG_F(
		long groupId, long folderId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_F(groupId, folderId, start, end);
	}

	/**
	* Returns an ordered range of all the document library file entries where groupId = &#63; and folderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param folderId the folder ID
	* @param start the lower bound of the range of document library file entries
	* @param end the upper bound of the range of document library file entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching document library file entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> findByG_F(
		long groupId, long folderId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_F(groupId, folderId, start, end, orderByComparator);
	}

	/**
	* Returns the first document library file entry in the ordered set where groupId = &#63; and folderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param folderId the folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching document library file entry
	* @throws com.liferay.portlet.documentlibrary.NoSuchFileEntryException if a matching document library file entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFileEntry findByG_F_First(
		long groupId, long folderId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFileEntryException {
		return getPersistence()
				   .findByG_F_First(groupId, folderId, orderByComparator);
	}

	/**
	* Returns the last document library file entry in the ordered set where groupId = &#63; and folderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param folderId the folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching document library file entry
	* @throws com.liferay.portlet.documentlibrary.NoSuchFileEntryException if a matching document library file entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFileEntry findByG_F_Last(
		long groupId, long folderId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFileEntryException {
		return getPersistence()
				   .findByG_F_Last(groupId, folderId, orderByComparator);
	}

	/**
	* Returns the document library file entries before and after the current document library file entry in the ordered set where groupId = &#63; and folderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param fileEntryId the primary key of the current document library file entry
	* @param groupId the group ID
	* @param folderId the folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next document library file entry
	* @throws com.liferay.portlet.documentlibrary.NoSuchFileEntryException if a document library file entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFileEntry[] findByG_F_PrevAndNext(
		long fileEntryId, long groupId, long folderId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFileEntryException {
		return getPersistence()
				   .findByG_F_PrevAndNext(fileEntryId, groupId, folderId,
			orderByComparator);
	}

	/**
	* Returns all the document library file entries where groupId = &#63; and folderId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param folderIds the folder IDs
	* @return the matching document library file entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> findByG_F(
		long groupId, long[] folderIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_F(groupId, folderIds);
	}

	/**
	* Returns a range of all the document library file entries where groupId = &#63; and folderId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param folderIds the folder IDs
	* @param start the lower bound of the range of document library file entries
	* @param end the upper bound of the range of document library file entries (not inclusive)
	* @return the range of matching document library file entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> findByG_F(
		long groupId, long[] folderIds, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_F(groupId, folderIds, start, end);
	}

	/**
	* Returns an ordered range of all the document library file entries where groupId = &#63; and folderId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param folderIds the folder IDs
	* @param start the lower bound of the range of document library file entries
	* @param end the upper bound of the range of document library file entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching document library file entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> findByG_F(
		long groupId, long[] folderIds, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_F(groupId, folderIds, start, end, orderByComparator);
	}

	/**
	* Returns all the document library file entries that the user has permission to view where groupId = &#63; and folderId = &#63;.
	*
	* @param groupId the group ID
	* @param folderId the folder ID
	* @return the matching document library file entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> filterFindByG_F(
		long groupId, long folderId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByG_F(groupId, folderId);
	}

	/**
	* Returns a range of all the document library file entries that the user has permission to view where groupId = &#63; and folderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param folderId the folder ID
	* @param start the lower bound of the range of document library file entries
	* @param end the upper bound of the range of document library file entries (not inclusive)
	* @return the range of matching document library file entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> filterFindByG_F(
		long groupId, long folderId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByG_F(groupId, folderId, start, end);
	}

	/**
	* Returns an ordered range of all the document library file entries that the user has permissions to view where groupId = &#63; and folderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param folderId the folder ID
	* @param start the lower bound of the range of document library file entries
	* @param end the upper bound of the range of document library file entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching document library file entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> filterFindByG_F(
		long groupId, long folderId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_F(groupId, folderId, start, end,
			orderByComparator);
	}

	/**
	* Returns the document library file entries before and after the current document library file entry in the ordered set of document library file entries that the user has permission to view where groupId = &#63; and folderId = &#63;.
	*
	* @param fileEntryId the primary key of the current document library file entry
	* @param groupId the group ID
	* @param folderId the folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next document library file entry
	* @throws com.liferay.portlet.documentlibrary.NoSuchFileEntryException if a document library file entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFileEntry[] filterFindByG_F_PrevAndNext(
		long fileEntryId, long groupId, long folderId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFileEntryException {
		return getPersistence()
				   .filterFindByG_F_PrevAndNext(fileEntryId, groupId, folderId,
			orderByComparator);
	}

	/**
	* Returns all the document library file entries that the user has permission to view where groupId = &#63; and folderId = any &#63;.
	*
	* @param groupId the group ID
	* @param folderIds the folder IDs
	* @return the matching document library file entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> filterFindByG_F(
		long groupId, long[] folderIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByG_F(groupId, folderIds);
	}

	/**
	* Returns a range of all the document library file entries that the user has permission to view where groupId = &#63; and folderId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param folderIds the folder IDs
	* @param start the lower bound of the range of document library file entries
	* @param end the upper bound of the range of document library file entries (not inclusive)
	* @return the range of matching document library file entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> filterFindByG_F(
		long groupId, long[] folderIds, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByG_F(groupId, folderIds, start, end);
	}

	/**
	* Returns an ordered range of all the document library file entries that the user has permission to view where groupId = &#63; and folderId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param folderIds the folder IDs
	* @param start the lower bound of the range of document library file entries
	* @param end the upper bound of the range of document library file entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching document library file entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> filterFindByG_F(
		long groupId, long[] folderIds, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_F(groupId, folderIds, start, end,
			orderByComparator);
	}

	/**
	* Returns all the document library file entries where groupId = &#63; and userId = &#63; and folderId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param folderId the folder ID
	* @return the matching document library file entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> findByG_U_F(
		long groupId, long userId, long folderId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_U_F(groupId, userId, folderId);
	}

	/**
	* Returns a range of all the document library file entries where groupId = &#63; and userId = &#63; and folderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param folderId the folder ID
	* @param start the lower bound of the range of document library file entries
	* @param end the upper bound of the range of document library file entries (not inclusive)
	* @return the range of matching document library file entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> findByG_U_F(
		long groupId, long userId, long folderId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_U_F(groupId, userId, folderId, start, end);
	}

	/**
	* Returns an ordered range of all the document library file entries where groupId = &#63; and userId = &#63; and folderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param folderId the folder ID
	* @param start the lower bound of the range of document library file entries
	* @param end the upper bound of the range of document library file entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching document library file entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> findByG_U_F(
		long groupId, long userId, long folderId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_U_F(groupId, userId, folderId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first document library file entry in the ordered set where groupId = &#63; and userId = &#63; and folderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param folderId the folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching document library file entry
	* @throws com.liferay.portlet.documentlibrary.NoSuchFileEntryException if a matching document library file entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFileEntry findByG_U_F_First(
		long groupId, long userId, long folderId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFileEntryException {
		return getPersistence()
				   .findByG_U_F_First(groupId, userId, folderId,
			orderByComparator);
	}

	/**
	* Returns the last document library file entry in the ordered set where groupId = &#63; and userId = &#63; and folderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param folderId the folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching document library file entry
	* @throws com.liferay.portlet.documentlibrary.NoSuchFileEntryException if a matching document library file entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFileEntry findByG_U_F_Last(
		long groupId, long userId, long folderId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFileEntryException {
		return getPersistence()
				   .findByG_U_F_Last(groupId, userId, folderId,
			orderByComparator);
	}

	/**
	* Returns the document library file entries before and after the current document library file entry in the ordered set where groupId = &#63; and userId = &#63; and folderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param fileEntryId the primary key of the current document library file entry
	* @param groupId the group ID
	* @param userId the user ID
	* @param folderId the folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next document library file entry
	* @throws com.liferay.portlet.documentlibrary.NoSuchFileEntryException if a document library file entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFileEntry[] findByG_U_F_PrevAndNext(
		long fileEntryId, long groupId, long userId, long folderId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFileEntryException {
		return getPersistence()
				   .findByG_U_F_PrevAndNext(fileEntryId, groupId, userId,
			folderId, orderByComparator);
	}

	/**
	* Returns all the document library file entries where groupId = &#63; and userId = &#63; and folderId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param folderIds the folder IDs
	* @return the matching document library file entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> findByG_U_F(
		long groupId, long userId, long[] folderIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_U_F(groupId, userId, folderIds);
	}

	/**
	* Returns a range of all the document library file entries where groupId = &#63; and userId = &#63; and folderId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param folderIds the folder IDs
	* @param start the lower bound of the range of document library file entries
	* @param end the upper bound of the range of document library file entries (not inclusive)
	* @return the range of matching document library file entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> findByG_U_F(
		long groupId, long userId, long[] folderIds, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_U_F(groupId, userId, folderIds, start, end);
	}

	/**
	* Returns an ordered range of all the document library file entries where groupId = &#63; and userId = &#63; and folderId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param folderIds the folder IDs
	* @param start the lower bound of the range of document library file entries
	* @param end the upper bound of the range of document library file entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching document library file entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> findByG_U_F(
		long groupId, long userId, long[] folderIds, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_U_F(groupId, userId, folderIds, start, end,
			orderByComparator);
	}

	/**
	* Returns all the document library file entries that the user has permission to view where groupId = &#63; and userId = &#63; and folderId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param folderId the folder ID
	* @return the matching document library file entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> filterFindByG_U_F(
		long groupId, long userId, long folderId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByG_U_F(groupId, userId, folderId);
	}

	/**
	* Returns a range of all the document library file entries that the user has permission to view where groupId = &#63; and userId = &#63; and folderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param folderId the folder ID
	* @param start the lower bound of the range of document library file entries
	* @param end the upper bound of the range of document library file entries (not inclusive)
	* @return the range of matching document library file entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> filterFindByG_U_F(
		long groupId, long userId, long folderId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_U_F(groupId, userId, folderId, start, end);
	}

	/**
	* Returns an ordered range of all the document library file entries that the user has permissions to view where groupId = &#63; and userId = &#63; and folderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param folderId the folder ID
	* @param start the lower bound of the range of document library file entries
	* @param end the upper bound of the range of document library file entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching document library file entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> filterFindByG_U_F(
		long groupId, long userId, long folderId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_U_F(groupId, userId, folderId, start, end,
			orderByComparator);
	}

	/**
	* Returns the document library file entries before and after the current document library file entry in the ordered set of document library file entries that the user has permission to view where groupId = &#63; and userId = &#63; and folderId = &#63;.
	*
	* @param fileEntryId the primary key of the current document library file entry
	* @param groupId the group ID
	* @param userId the user ID
	* @param folderId the folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next document library file entry
	* @throws com.liferay.portlet.documentlibrary.NoSuchFileEntryException if a document library file entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFileEntry[] filterFindByG_U_F_PrevAndNext(
		long fileEntryId, long groupId, long userId, long folderId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFileEntryException {
		return getPersistence()
				   .filterFindByG_U_F_PrevAndNext(fileEntryId, groupId, userId,
			folderId, orderByComparator);
	}

	/**
	* Returns all the document library file entries that the user has permission to view where groupId = &#63; and userId = &#63; and folderId = any &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param folderIds the folder IDs
	* @return the matching document library file entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> filterFindByG_U_F(
		long groupId, long userId, long[] folderIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByG_U_F(groupId, userId, folderIds);
	}

	/**
	* Returns a range of all the document library file entries that the user has permission to view where groupId = &#63; and userId = &#63; and folderId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param folderIds the folder IDs
	* @param start the lower bound of the range of document library file entries
	* @param end the upper bound of the range of document library file entries (not inclusive)
	* @return the range of matching document library file entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> filterFindByG_U_F(
		long groupId, long userId, long[] folderIds, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_U_F(groupId, userId, folderIds, start, end);
	}

	/**
	* Returns an ordered range of all the document library file entries that the user has permission to view where groupId = &#63; and userId = &#63; and folderId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param folderIds the folder IDs
	* @param start the lower bound of the range of document library file entries
	* @param end the upper bound of the range of document library file entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching document library file entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> filterFindByG_U_F(
		long groupId, long userId, long[] folderIds, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_U_F(groupId, userId, folderIds, start, end,
			orderByComparator);
	}

	/**
	* Returns the document library file entry where groupId = &#63; and folderId = &#63; and name = &#63; or throws a {@link com.liferay.portlet.documentlibrary.NoSuchFileEntryException} if it could not be found.
	*
	* @param groupId the group ID
	* @param folderId the folder ID
	* @param name the name
	* @return the matching document library file entry
	* @throws com.liferay.portlet.documentlibrary.NoSuchFileEntryException if a matching document library file entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFileEntry findByG_F_N(
		long groupId, long folderId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFileEntryException {
		return getPersistence().findByG_F_N(groupId, folderId, name);
	}

	/**
	* Returns the document library file entry where groupId = &#63; and folderId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param folderId the folder ID
	* @param name the name
	* @return the matching document library file entry, or <code>null</code> if a matching document library file entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFileEntry fetchByG_F_N(
		long groupId, long folderId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByG_F_N(groupId, folderId, name);
	}

	/**
	* Returns the document library file entry where groupId = &#63; and folderId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param folderId the folder ID
	* @param name the name
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching document library file entry, or <code>null</code> if a matching document library file entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFileEntry fetchByG_F_N(
		long groupId, long folderId, java.lang.String name,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_F_N(groupId, folderId, name, retrieveFromCache);
	}

	/**
	* Returns the document library file entry where groupId = &#63; and folderId = &#63; and title = &#63; or throws a {@link com.liferay.portlet.documentlibrary.NoSuchFileEntryException} if it could not be found.
	*
	* @param groupId the group ID
	* @param folderId the folder ID
	* @param title the title
	* @return the matching document library file entry
	* @throws com.liferay.portlet.documentlibrary.NoSuchFileEntryException if a matching document library file entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFileEntry findByG_F_T(
		long groupId, long folderId, java.lang.String title)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFileEntryException {
		return getPersistence().findByG_F_T(groupId, folderId, title);
	}

	/**
	* Returns the document library file entry where groupId = &#63; and folderId = &#63; and title = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param folderId the folder ID
	* @param title the title
	* @return the matching document library file entry, or <code>null</code> if a matching document library file entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFileEntry fetchByG_F_T(
		long groupId, long folderId, java.lang.String title)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByG_F_T(groupId, folderId, title);
	}

	/**
	* Returns the document library file entry where groupId = &#63; and folderId = &#63; and title = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param folderId the folder ID
	* @param title the title
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching document library file entry, or <code>null</code> if a matching document library file entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFileEntry fetchByG_F_T(
		long groupId, long folderId, java.lang.String title,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_F_T(groupId, folderId, title, retrieveFromCache);
	}

	/**
	* Returns all the document library file entries where groupId = &#63; and folderId = &#63; and fileEntryTypeId = &#63;.
	*
	* @param groupId the group ID
	* @param folderId the folder ID
	* @param fileEntryTypeId the file entry type ID
	* @return the matching document library file entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> findByG_F_F(
		long groupId, long folderId, long fileEntryTypeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_F_F(groupId, folderId, fileEntryTypeId);
	}

	/**
	* Returns a range of all the document library file entries where groupId = &#63; and folderId = &#63; and fileEntryTypeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param folderId the folder ID
	* @param fileEntryTypeId the file entry type ID
	* @param start the lower bound of the range of document library file entries
	* @param end the upper bound of the range of document library file entries (not inclusive)
	* @return the range of matching document library file entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> findByG_F_F(
		long groupId, long folderId, long fileEntryTypeId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_F_F(groupId, folderId, fileEntryTypeId, start, end);
	}

	/**
	* Returns an ordered range of all the document library file entries where groupId = &#63; and folderId = &#63; and fileEntryTypeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param folderId the folder ID
	* @param fileEntryTypeId the file entry type ID
	* @param start the lower bound of the range of document library file entries
	* @param end the upper bound of the range of document library file entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching document library file entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> findByG_F_F(
		long groupId, long folderId, long fileEntryTypeId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_F_F(groupId, folderId, fileEntryTypeId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first document library file entry in the ordered set where groupId = &#63; and folderId = &#63; and fileEntryTypeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param folderId the folder ID
	* @param fileEntryTypeId the file entry type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching document library file entry
	* @throws com.liferay.portlet.documentlibrary.NoSuchFileEntryException if a matching document library file entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFileEntry findByG_F_F_First(
		long groupId, long folderId, long fileEntryTypeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFileEntryException {
		return getPersistence()
				   .findByG_F_F_First(groupId, folderId, fileEntryTypeId,
			orderByComparator);
	}

	/**
	* Returns the last document library file entry in the ordered set where groupId = &#63; and folderId = &#63; and fileEntryTypeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param folderId the folder ID
	* @param fileEntryTypeId the file entry type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching document library file entry
	* @throws com.liferay.portlet.documentlibrary.NoSuchFileEntryException if a matching document library file entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFileEntry findByG_F_F_Last(
		long groupId, long folderId, long fileEntryTypeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFileEntryException {
		return getPersistence()
				   .findByG_F_F_Last(groupId, folderId, fileEntryTypeId,
			orderByComparator);
	}

	/**
	* Returns the document library file entries before and after the current document library file entry in the ordered set where groupId = &#63; and folderId = &#63; and fileEntryTypeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param fileEntryId the primary key of the current document library file entry
	* @param groupId the group ID
	* @param folderId the folder ID
	* @param fileEntryTypeId the file entry type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next document library file entry
	* @throws com.liferay.portlet.documentlibrary.NoSuchFileEntryException if a document library file entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFileEntry[] findByG_F_F_PrevAndNext(
		long fileEntryId, long groupId, long folderId, long fileEntryTypeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFileEntryException {
		return getPersistence()
				   .findByG_F_F_PrevAndNext(fileEntryId, groupId, folderId,
			fileEntryTypeId, orderByComparator);
	}

	/**
	* Returns all the document library file entries where groupId = &#63; and folderId = any &#63; and fileEntryTypeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param folderIds the folder IDs
	* @param fileEntryTypeId the file entry type ID
	* @return the matching document library file entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> findByG_F_F(
		long groupId, long[] folderIds, long fileEntryTypeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_F_F(groupId, folderIds, fileEntryTypeId);
	}

	/**
	* Returns a range of all the document library file entries where groupId = &#63; and folderId = any &#63; and fileEntryTypeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param folderIds the folder IDs
	* @param fileEntryTypeId the file entry type ID
	* @param start the lower bound of the range of document library file entries
	* @param end the upper bound of the range of document library file entries (not inclusive)
	* @return the range of matching document library file entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> findByG_F_F(
		long groupId, long[] folderIds, long fileEntryTypeId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_F_F(groupId, folderIds, fileEntryTypeId, start, end);
	}

	/**
	* Returns an ordered range of all the document library file entries where groupId = &#63; and folderId = any &#63; and fileEntryTypeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param folderIds the folder IDs
	* @param fileEntryTypeId the file entry type ID
	* @param start the lower bound of the range of document library file entries
	* @param end the upper bound of the range of document library file entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching document library file entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> findByG_F_F(
		long groupId, long[] folderIds, long fileEntryTypeId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_F_F(groupId, folderIds, fileEntryTypeId, start,
			end, orderByComparator);
	}

	/**
	* Returns all the document library file entries that the user has permission to view where groupId = &#63; and folderId = &#63; and fileEntryTypeId = &#63;.
	*
	* @param groupId the group ID
	* @param folderId the folder ID
	* @param fileEntryTypeId the file entry type ID
	* @return the matching document library file entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> filterFindByG_F_F(
		long groupId, long folderId, long fileEntryTypeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_F_F(groupId, folderId, fileEntryTypeId);
	}

	/**
	* Returns a range of all the document library file entries that the user has permission to view where groupId = &#63; and folderId = &#63; and fileEntryTypeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param folderId the folder ID
	* @param fileEntryTypeId the file entry type ID
	* @param start the lower bound of the range of document library file entries
	* @param end the upper bound of the range of document library file entries (not inclusive)
	* @return the range of matching document library file entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> filterFindByG_F_F(
		long groupId, long folderId, long fileEntryTypeId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_F_F(groupId, folderId, fileEntryTypeId,
			start, end);
	}

	/**
	* Returns an ordered range of all the document library file entries that the user has permissions to view where groupId = &#63; and folderId = &#63; and fileEntryTypeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param folderId the folder ID
	* @param fileEntryTypeId the file entry type ID
	* @param start the lower bound of the range of document library file entries
	* @param end the upper bound of the range of document library file entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching document library file entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> filterFindByG_F_F(
		long groupId, long folderId, long fileEntryTypeId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_F_F(groupId, folderId, fileEntryTypeId,
			start, end, orderByComparator);
	}

	/**
	* Returns the document library file entries before and after the current document library file entry in the ordered set of document library file entries that the user has permission to view where groupId = &#63; and folderId = &#63; and fileEntryTypeId = &#63;.
	*
	* @param fileEntryId the primary key of the current document library file entry
	* @param groupId the group ID
	* @param folderId the folder ID
	* @param fileEntryTypeId the file entry type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next document library file entry
	* @throws com.liferay.portlet.documentlibrary.NoSuchFileEntryException if a document library file entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.documentlibrary.model.DLFileEntry[] filterFindByG_F_F_PrevAndNext(
		long fileEntryId, long groupId, long folderId, long fileEntryTypeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFileEntryException {
		return getPersistence()
				   .filterFindByG_F_F_PrevAndNext(fileEntryId, groupId,
			folderId, fileEntryTypeId, orderByComparator);
	}

	/**
	* Returns all the document library file entries that the user has permission to view where groupId = &#63; and folderId = any &#63; and fileEntryTypeId = &#63;.
	*
	* @param groupId the group ID
	* @param folderIds the folder IDs
	* @param fileEntryTypeId the file entry type ID
	* @return the matching document library file entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> filterFindByG_F_F(
		long groupId, long[] folderIds, long fileEntryTypeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_F_F(groupId, folderIds, fileEntryTypeId);
	}

	/**
	* Returns a range of all the document library file entries that the user has permission to view where groupId = &#63; and folderId = any &#63; and fileEntryTypeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param folderIds the folder IDs
	* @param fileEntryTypeId the file entry type ID
	* @param start the lower bound of the range of document library file entries
	* @param end the upper bound of the range of document library file entries (not inclusive)
	* @return the range of matching document library file entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> filterFindByG_F_F(
		long groupId, long[] folderIds, long fileEntryTypeId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_F_F(groupId, folderIds, fileEntryTypeId,
			start, end);
	}

	/**
	* Returns an ordered range of all the document library file entries that the user has permission to view where groupId = &#63; and folderId = any &#63; and fileEntryTypeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param folderIds the folder IDs
	* @param fileEntryTypeId the file entry type ID
	* @param start the lower bound of the range of document library file entries
	* @param end the upper bound of the range of document library file entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching document library file entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> filterFindByG_F_F(
		long groupId, long[] folderIds, long fileEntryTypeId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_F_F(groupId, folderIds, fileEntryTypeId,
			start, end, orderByComparator);
	}

	/**
	* Returns all the document library file entries.
	*
	* @return the document library file entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the document library file entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of document library file entries
	* @param end the upper bound of the range of document library file entries (not inclusive)
	* @return the range of document library file entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the document library file entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of document library file entries
	* @param end the upper bound of the range of document library file entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of document library file entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the document library file entries where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Removes the document library file entry where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFileEntryException {
		getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Removes all the document library file entries where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Removes all the document library file entries where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	* Removes all the document library file entries where groupId = &#63; and userId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_U(long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_U(groupId, userId);
	}

	/**
	* Removes all the document library file entries where groupId = &#63; and folderId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param folderId the folder ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_F(long groupId, long folderId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_F(groupId, folderId);
	}

	/**
	* Removes all the document library file entries where groupId = &#63; and userId = &#63; and folderId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param folderId the folder ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_U_F(long groupId, long userId, long folderId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_U_F(groupId, userId, folderId);
	}

	/**
	* Removes the document library file entry where groupId = &#63; and folderId = &#63; and name = &#63; from the database.
	*
	* @param groupId the group ID
	* @param folderId the folder ID
	* @param name the name
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_F_N(long groupId, long folderId,
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFileEntryException {
		getPersistence().removeByG_F_N(groupId, folderId, name);
	}

	/**
	* Removes the document library file entry where groupId = &#63; and folderId = &#63; and title = &#63; from the database.
	*
	* @param groupId the group ID
	* @param folderId the folder ID
	* @param title the title
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_F_T(long groupId, long folderId,
		java.lang.String title)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFileEntryException {
		getPersistence().removeByG_F_T(groupId, folderId, title);
	}

	/**
	* Removes all the document library file entries where groupId = &#63; and folderId = &#63; and fileEntryTypeId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param folderId the folder ID
	* @param fileEntryTypeId the file entry type ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_F_F(long groupId, long folderId,
		long fileEntryTypeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_F_F(groupId, folderId, fileEntryTypeId);
	}

	/**
	* Removes all the document library file entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of document library file entries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching document library file entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the number of document library file entries where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching document library file entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of document library file entries where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching document library file entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns the number of document library file entries that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching document library file entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByGroupId(groupId);
	}

	/**
	* Returns the number of document library file entries where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching document library file entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	* Returns the number of document library file entries where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the number of matching document library file entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_U(long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_U(groupId, userId);
	}

	/**
	* Returns the number of document library file entries that the user has permission to view where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the number of matching document library file entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_U(long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByG_U(groupId, userId);
	}

	/**
	* Returns the number of document library file entries where groupId = &#63; and folderId = &#63;.
	*
	* @param groupId the group ID
	* @param folderId the folder ID
	* @return the number of matching document library file entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_F(long groupId, long folderId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_F(groupId, folderId);
	}

	/**
	* Returns the number of document library file entries where groupId = &#63; and folderId = any &#63;.
	*
	* @param groupId the group ID
	* @param folderIds the folder IDs
	* @return the number of matching document library file entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_F(long groupId, long[] folderIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_F(groupId, folderIds);
	}

	/**
	* Returns the number of document library file entries that the user has permission to view where groupId = &#63; and folderId = &#63;.
	*
	* @param groupId the group ID
	* @param folderId the folder ID
	* @return the number of matching document library file entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_F(long groupId, long folderId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByG_F(groupId, folderId);
	}

	/**
	* Returns the number of document library file entries that the user has permission to view where groupId = &#63; and folderId = any &#63;.
	*
	* @param groupId the group ID
	* @param folderIds the folder IDs
	* @return the number of matching document library file entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_F(long groupId, long[] folderIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByG_F(groupId, folderIds);
	}

	/**
	* Returns the number of document library file entries where groupId = &#63; and userId = &#63; and folderId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param folderId the folder ID
	* @return the number of matching document library file entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_U_F(long groupId, long userId, long folderId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_U_F(groupId, userId, folderId);
	}

	/**
	* Returns the number of document library file entries where groupId = &#63; and userId = &#63; and folderId = any &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param folderIds the folder IDs
	* @return the number of matching document library file entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_U_F(long groupId, long userId, long[] folderIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_U_F(groupId, userId, folderIds);
	}

	/**
	* Returns the number of document library file entries that the user has permission to view where groupId = &#63; and userId = &#63; and folderId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param folderId the folder ID
	* @return the number of matching document library file entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_U_F(long groupId, long userId,
		long folderId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByG_U_F(groupId, userId, folderId);
	}

	/**
	* Returns the number of document library file entries that the user has permission to view where groupId = &#63; and userId = &#63; and folderId = any &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param folderIds the folder IDs
	* @return the number of matching document library file entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_U_F(long groupId, long userId,
		long[] folderIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByG_U_F(groupId, userId, folderIds);
	}

	/**
	* Returns the number of document library file entries where groupId = &#63; and folderId = &#63; and name = &#63;.
	*
	* @param groupId the group ID
	* @param folderId the folder ID
	* @param name the name
	* @return the number of matching document library file entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_F_N(long groupId, long folderId,
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_F_N(groupId, folderId, name);
	}

	/**
	* Returns the number of document library file entries where groupId = &#63; and folderId = &#63; and title = &#63;.
	*
	* @param groupId the group ID
	* @param folderId the folder ID
	* @param title the title
	* @return the number of matching document library file entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_F_T(long groupId, long folderId,
		java.lang.String title)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_F_T(groupId, folderId, title);
	}

	/**
	* Returns the number of document library file entries where groupId = &#63; and folderId = &#63; and fileEntryTypeId = &#63;.
	*
	* @param groupId the group ID
	* @param folderId the folder ID
	* @param fileEntryTypeId the file entry type ID
	* @return the number of matching document library file entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_F_F(long groupId, long folderId,
		long fileEntryTypeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_F_F(groupId, folderId, fileEntryTypeId);
	}

	/**
	* Returns the number of document library file entries where groupId = &#63; and folderId = any &#63; and fileEntryTypeId = &#63;.
	*
	* @param groupId the group ID
	* @param folderIds the folder IDs
	* @param fileEntryTypeId the file entry type ID
	* @return the number of matching document library file entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_F_F(long groupId, long[] folderIds,
		long fileEntryTypeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_F_F(groupId, folderIds, fileEntryTypeId);
	}

	/**
	* Returns the number of document library file entries that the user has permission to view where groupId = &#63; and folderId = &#63; and fileEntryTypeId = &#63;.
	*
	* @param groupId the group ID
	* @param folderId the folder ID
	* @param fileEntryTypeId the file entry type ID
	* @return the number of matching document library file entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_F_F(long groupId, long folderId,
		long fileEntryTypeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByG_F_F(groupId, folderId, fileEntryTypeId);
	}

	/**
	* Returns the number of document library file entries that the user has permission to view where groupId = &#63; and folderId = any &#63; and fileEntryTypeId = &#63;.
	*
	* @param groupId the group ID
	* @param folderIds the folder IDs
	* @param fileEntryTypeId the file entry type ID
	* @return the number of matching document library file entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_F_F(long groupId, long[] folderIds,
		long fileEntryTypeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByG_F_F(groupId, folderIds, fileEntryTypeId);
	}

	/**
	* Returns the number of document library file entries.
	*
	* @return the number of document library file entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static DLFileEntryPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (DLFileEntryPersistence)PortalBeanLocatorUtil.locate(DLFileEntryPersistence.class.getName());

			ReferenceRegistry.registerReference(DLFileEntryUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(DLFileEntryPersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(DLFileEntryUtil.class,
			"_persistence");
	}

	private static DLFileEntryPersistence _persistence;
}