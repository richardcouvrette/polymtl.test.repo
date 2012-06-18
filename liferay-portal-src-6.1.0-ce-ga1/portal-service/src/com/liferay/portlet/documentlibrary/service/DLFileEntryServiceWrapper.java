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

package com.liferay.portlet.documentlibrary.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link DLFileEntryService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       DLFileEntryService
 * @generated
 */
public class DLFileEntryServiceWrapper implements DLFileEntryService,
	ServiceWrapper<DLFileEntryService> {
	public DLFileEntryServiceWrapper(DLFileEntryService dlFileEntryService) {
		_dlFileEntryService = dlFileEntryService;
	}

	public com.liferay.portlet.documentlibrary.model.DLFileEntry addFileEntry(
		long groupId, long repositoryId, long folderId,
		java.lang.String sourceFileName, java.lang.String mimeType,
		java.lang.String title, java.lang.String description,
		java.lang.String changeLog, long fileEntryTypeId,
		java.util.Map<java.lang.String, com.liferay.portlet.dynamicdatamapping.storage.Fields> fieldsMap,
		java.io.File file, java.io.InputStream is, long size,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlFileEntryService.addFileEntry(groupId, repositoryId,
			folderId, sourceFileName, mimeType, title, description, changeLog,
			fileEntryTypeId, fieldsMap, file, is, size, serviceContext);
	}

	public void cancelCheckOut(long fileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_dlFileEntryService.cancelCheckOut(fileEntryId);
	}

	public void checkInFileEntry(long fileEntryId, boolean major,
		java.lang.String changeLog,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_dlFileEntryService.checkInFileEntry(fileEntryId, major, changeLog,
			serviceContext);
	}

	public void checkInFileEntry(long fileEntryId, java.lang.String lockUuid)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_dlFileEntryService.checkInFileEntry(fileEntryId, lockUuid);
	}

	public com.liferay.portlet.documentlibrary.model.DLFileEntry checkOutFileEntry(
		long fileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlFileEntryService.checkOutFileEntry(fileEntryId);
	}

	public com.liferay.portlet.documentlibrary.model.DLFileEntry checkOutFileEntry(
		long fileEntryId, java.lang.String owner, long expirationTime)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlFileEntryService.checkOutFileEntry(fileEntryId, owner,
			expirationTime);
	}

	public com.liferay.portlet.documentlibrary.model.DLFileEntry copyFileEntry(
		long groupId, long repositoryId, long fileEntryId, long destFolderId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlFileEntryService.copyFileEntry(groupId, repositoryId,
			fileEntryId, destFolderId, serviceContext);
	}

	public void deleteFileEntry(long fileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_dlFileEntryService.deleteFileEntry(fileEntryId);
	}

	public void deleteFileEntry(long groupId, long folderId,
		java.lang.String title)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_dlFileEntryService.deleteFileEntry(groupId, folderId, title);
	}

	public com.liferay.portlet.documentlibrary.model.DLFileEntry fetchFileEntryByImageId(
		long imageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlFileEntryService.fetchFileEntryByImageId(imageId);
	}

	public java.io.InputStream getFileAsStream(long fileEntryId,
		java.lang.String version)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlFileEntryService.getFileAsStream(fileEntryId, version);
	}

	public java.io.InputStream getFileAsStream(long fileEntryId,
		java.lang.String version, boolean incrementCounter)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlFileEntryService.getFileAsStream(fileEntryId, version,
			incrementCounter);
	}

	public java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> getFileEntries(
		long groupId, long folderId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlFileEntryService.getFileEntries(groupId, folderId, start,
			end, obc);
	}

	public java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> getFileEntries(
		long groupId, long folderId, long fileEntryTypeId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlFileEntryService.getFileEntries(groupId, folderId,
			fileEntryTypeId, start, end, obc);
	}

	public java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> getFileEntries(
		long groupId, long folderId, java.lang.String[] mimeTypes, int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlFileEntryService.getFileEntries(groupId, folderId, mimeTypes,
			start, end, obc);
	}

	public int getFileEntriesCount(long groupId, long folderId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlFileEntryService.getFileEntriesCount(groupId, folderId);
	}

	public int getFileEntriesCount(long groupId, long folderId,
		long fileEntryTypeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlFileEntryService.getFileEntriesCount(groupId, folderId,
			fileEntryTypeId);
	}

	public int getFileEntriesCount(long groupId, long folderId,
		java.lang.String[] mimeTypes)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlFileEntryService.getFileEntriesCount(groupId, folderId,
			mimeTypes);
	}

	public com.liferay.portlet.documentlibrary.model.DLFileEntry getFileEntry(
		long fileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlFileEntryService.getFileEntry(fileEntryId);
	}

	public com.liferay.portlet.documentlibrary.model.DLFileEntry getFileEntry(
		long groupId, long folderId, java.lang.String title)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlFileEntryService.getFileEntry(groupId, folderId, title);
	}

	public com.liferay.portlet.documentlibrary.model.DLFileEntry getFileEntryByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlFileEntryService.getFileEntryByUuidAndGroupId(uuid, groupId);
	}

	public com.liferay.portal.model.Lock getFileEntryLock(long fileEntryId) {
		return _dlFileEntryService.getFileEntryLock(fileEntryId);
	}

	public int getFoldersFileEntriesCount(long groupId,
		java.util.List<java.lang.Long> folderIds, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlFileEntryService.getFoldersFileEntriesCount(groupId,
			folderIds, status);
	}

	public java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> getGroupFileEntries(
		long groupId, long userId, long rootFolderId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlFileEntryService.getGroupFileEntries(groupId, userId,
			rootFolderId, start, end, obc);
	}

	public java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> getGroupFileEntries(
		long groupId, long userId, long rootFolderId,
		java.lang.String[] mimeTypes, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlFileEntryService.getGroupFileEntries(groupId, userId,
			rootFolderId, mimeTypes, status, start, end, obc);
	}

	public int getGroupFileEntriesCount(long groupId, long userId,
		long rootFolderId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlFileEntryService.getGroupFileEntriesCount(groupId, userId,
			rootFolderId);
	}

	public int getGroupFileEntriesCount(long groupId, long userId,
		long rootFolderId, java.lang.String[] mimeTypes, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlFileEntryService.getGroupFileEntriesCount(groupId, userId,
			rootFolderId, mimeTypes, status);
	}

	public boolean hasFileEntryLock(long fileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlFileEntryService.hasFileEntryLock(fileEntryId);
	}

	public boolean isFileEntryCheckedOut(long fileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlFileEntryService.isFileEntryCheckedOut(fileEntryId);
	}

	public com.liferay.portal.model.Lock lockFileEntry(long fileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlFileEntryService.lockFileEntry(fileEntryId);
	}

	public com.liferay.portal.model.Lock lockFileEntry(long fileEntryId,
		java.lang.String owner, long expirationTime)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlFileEntryService.lockFileEntry(fileEntryId, owner,
			expirationTime);
	}

	public com.liferay.portlet.documentlibrary.model.DLFileEntry moveFileEntry(
		long fileEntryId, long newFolderId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlFileEntryService.moveFileEntry(fileEntryId, newFolderId,
			serviceContext);
	}

	public com.liferay.portal.model.Lock refreshFileEntryLock(
		java.lang.String lockUuid, long expirationTime)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlFileEntryService.refreshFileEntryLock(lockUuid, expirationTime);
	}

	public void revertFileEntry(long fileEntryId, java.lang.String version,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_dlFileEntryService.revertFileEntry(fileEntryId, version, serviceContext);
	}

	public void unlockFileEntry(long fileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_dlFileEntryService.unlockFileEntry(fileEntryId);
	}

	public void unlockFileEntry(long fileEntryId, java.lang.String lockUuid)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_dlFileEntryService.unlockFileEntry(fileEntryId, lockUuid);
	}

	public com.liferay.portlet.documentlibrary.model.DLFileEntry updateFileEntry(
		long fileEntryId, java.lang.String sourceFileName,
		java.lang.String mimeType, java.lang.String title,
		java.lang.String description, java.lang.String changeLog,
		boolean majorVersion, long fileEntryTypeId,
		java.util.Map<java.lang.String, com.liferay.portlet.dynamicdatamapping.storage.Fields> fieldsMap,
		java.io.File file, java.io.InputStream is, long size,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlFileEntryService.updateFileEntry(fileEntryId, sourceFileName,
			mimeType, title, description, changeLog, majorVersion,
			fileEntryTypeId, fieldsMap, file, is, size, serviceContext);
	}

	public boolean verifyFileEntryCheckOut(long fileEntryId,
		java.lang.String lockUuid)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlFileEntryService.verifyFileEntryCheckOut(fileEntryId, lockUuid);
	}

	public boolean verifyFileEntryLock(long fileEntryId,
		java.lang.String lockUuid)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlFileEntryService.verifyFileEntryLock(fileEntryId, lockUuid);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public DLFileEntryService getWrappedDLFileEntryService() {
		return _dlFileEntryService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedDLFileEntryService(
		DLFileEntryService dlFileEntryService) {
		_dlFileEntryService = dlFileEntryService;
	}

	public DLFileEntryService getWrappedService() {
		return _dlFileEntryService;
	}

	public void setWrappedService(DLFileEntryService dlFileEntryService) {
		_dlFileEntryService = dlFileEntryService;
	}

	private DLFileEntryService _dlFileEntryService;
}