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

package com.liferay.portlet.documentlibrary.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Lock;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.documentlibrary.NoSuchFileEntryException;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFileVersion;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.model.impl.DLFileEntryImpl;
import com.liferay.portlet.documentlibrary.service.base.DLFileEntryServiceBaseImpl;
import com.liferay.portlet.documentlibrary.service.permission.DLFileEntryPermission;
import com.liferay.portlet.documentlibrary.service.permission.DLFolderPermission;
import com.liferay.portlet.documentlibrary.store.DLStoreUtil;
import com.liferay.portlet.dynamicdatamapping.storage.Fields;

import java.io.File;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 * @author Alexander Chow
 */
public class DLFileEntryServiceImpl extends DLFileEntryServiceBaseImpl {

	public DLFileEntry addFileEntry(
			long groupId, long repositoryId, long folderId,
			String sourceFileName, String mimeType, String title,
			String description, String changeLog, long fileEntryTypeId,
			Map<String, Fields> fieldsMap, File file, InputStream is, long size,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		DLFolderPermission.check(
			getPermissionChecker(), groupId, folderId, ActionKeys.ADD_DOCUMENT);

		return dlFileEntryLocalService.addFileEntry(
			getUserId(), groupId, repositoryId, folderId, sourceFileName,
			mimeType, title, description, changeLog, fileEntryTypeId,
			fieldsMap, file, is, size, serviceContext);
	}

	public void cancelCheckOut(long fileEntryId)
		throws PortalException, SystemException {

		try {
			DLFileEntryPermission.check(
				getPermissionChecker(), fileEntryId, ActionKeys.UPDATE);
		}
		catch (NoSuchFileEntryException nsfee) {
		}

		dlFileEntryLocalService.cancelCheckOut(getUserId(), fileEntryId);
	}

	public void checkInFileEntry(
			long fileEntryId, boolean major, String changeLog,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		try {
			DLFileEntryPermission.check(
				getPermissionChecker(), fileEntryId, ActionKeys.UPDATE);
		}
		catch (NoSuchFileEntryException nsfee) {
		}

		dlFileEntryLocalService.checkInFileEntry(
			getUserId(), fileEntryId, major, changeLog, serviceContext);
	}

	public void checkInFileEntry(long fileEntryId, String lockUuid)
		throws PortalException, SystemException {

		try {
			DLFileEntryPermission.check(
				getPermissionChecker(), fileEntryId, ActionKeys.UPDATE);
		}
		catch (NoSuchFileEntryException nsfee) {
		}

		dlFileEntryLocalService.checkInFileEntry(
			getUserId(), fileEntryId, lockUuid);
	}

	public DLFileEntry checkOutFileEntry(long fileEntryId)
		throws PortalException, SystemException {

		return checkOutFileEntry(
			fileEntryId, null, DLFileEntryImpl.LOCK_EXPIRATION_TIME);
	}

	public DLFileEntry checkOutFileEntry(
			long fileEntryId, String owner, long expirationTime)
		throws PortalException, SystemException {

		DLFileEntryPermission.check(
			getPermissionChecker(), fileEntryId, ActionKeys.UPDATE);

		if ((expirationTime <= 0) ||
			(expirationTime > DLFileEntryImpl.LOCK_EXPIRATION_TIME)) {

			expirationTime = DLFileEntryImpl.LOCK_EXPIRATION_TIME;
		}

		return dlFileEntryLocalService.checkOutFileEntry(
			getUserId(), fileEntryId, owner, expirationTime);
	}

	public DLFileEntry copyFileEntry(
			long groupId, long repositoryId, long fileEntryId,
			long destFolderId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		DLFileEntry dlFileEntry = getFileEntry(fileEntryId);

		String sourceFileName = "A." + dlFileEntry.getExtension();
		InputStream inputStream = DLStoreUtil.getFileAsStream(
			dlFileEntry.getCompanyId(), dlFileEntry.getFolderId(),
			dlFileEntry.getName());

		DLFileEntry newDlFileEntry = addFileEntry(
			groupId, repositoryId, destFolderId, sourceFileName,
			dlFileEntry.getMimeType(), dlFileEntry.getTitle(),
			dlFileEntry.getDescription(), null,
			dlFileEntry.getFileEntryTypeId(), null, null, inputStream,
			dlFileEntry.getSize(), serviceContext);

		DLFileVersion dlFileVersion = dlFileEntry.getFileVersion();

		DLFileVersion newDlFileVersion = newDlFileEntry.getFileVersion();

		dlFileEntryLocalService.copyFileEntryMetadata(
			dlFileVersion.getCompanyId(), dlFileVersion.getFileEntryTypeId(),
			fileEntryId, newDlFileVersion.getFileVersionId(),
			dlFileVersion.getFileVersionId(), serviceContext);

		return newDlFileEntry;
	}

	public void deleteFileEntry(long fileEntryId)
		throws PortalException, SystemException {

		DLFileEntryPermission.check(
			getPermissionChecker(), fileEntryId, ActionKeys.DELETE);

		dlFileEntryLocalService.deleteFileEntry(getUserId(), fileEntryId);
	}

	public void deleteFileEntry(long groupId, long folderId, String title)
		throws PortalException, SystemException {

		DLFileEntry dlFileEntry = getFileEntry(groupId, folderId, title);

		deleteFileEntry(dlFileEntry.getFileEntryId());
	}

	public DLFileEntry fetchFileEntryByImageId(long imageId)
		throws PortalException, SystemException {

		DLFileEntry dlFileEntry = dlFileEntryFinder.fetchByAnyImageId(imageId);

		if (dlFileEntry != null) {
			DLFileEntryPermission.check(
				getPermissionChecker(), dlFileEntry, ActionKeys.VIEW);
		}

		return dlFileEntry;
	}

	public InputStream getFileAsStream(long fileEntryId, String version)
		throws PortalException, SystemException {

		DLFileEntryPermission.check(
			getPermissionChecker(), fileEntryId, ActionKeys.VIEW);

		return dlFileEntryLocalService.getFileAsStream(
			getGuestOrUserId(), fileEntryId, version);
	}

	public InputStream getFileAsStream(
			long fileEntryId, String version, boolean incrementCounter)
		throws PortalException, SystemException {

		DLFileEntryPermission.check(
			getPermissionChecker(), fileEntryId, ActionKeys.VIEW);

		return dlFileEntryLocalService.getFileAsStream(
			getGuestOrUserId(), fileEntryId, version, incrementCounter);
	}

	public List<DLFileEntry> getFileEntries(
			long groupId, long folderId, int start, int end,
			OrderByComparator obc)
		throws SystemException {

		return dlFileEntryPersistence.filterFindByG_F(
			groupId, folderId, start, end, obc);
	}

	public List<DLFileEntry> getFileEntries(
			long groupId, long folderId, long fileEntryTypeId, int start,
			int end, OrderByComparator obc)
		throws SystemException {

		return dlFileEntryPersistence.filterFindByG_F_F(
			groupId, folderId, fileEntryTypeId, start, end, obc);
	}

	public List<DLFileEntry> getFileEntries(
			long groupId, long folderId, String[] mimeTypes, int start,
			int end, OrderByComparator obc)
		throws SystemException {

		List<Long> folderIds = new ArrayList<Long>();

		folderIds.add(folderId);

		return dlFileEntryFinder.findByG_U_F_M_S(
			groupId, 0, folderIds, mimeTypes, WorkflowConstants.STATUS_ANY,
			start, end, obc);
	}

	public int getFileEntriesCount(long groupId, long folderId)
		throws SystemException {

		return dlFileEntryPersistence.filterCountByG_F(groupId, folderId);
	}

	public int getFileEntriesCount(
			long groupId, long folderId, long fileEntryTypeId)
		throws SystemException {

		return dlFileEntryPersistence.filterCountByG_F_F(
			groupId, folderId, fileEntryTypeId);
	}

	public int getFileEntriesCount(
			long groupId, long folderId, String[] mimeTypes)
		throws SystemException {

		List<Long> folderIds = new ArrayList<Long>();

		folderIds.add(folderId);

		return dlFileEntryFinder.countByG_U_F_M_S(
			groupId, 0, folderIds, mimeTypes, WorkflowConstants.STATUS_ANY);
	}

	public DLFileEntry getFileEntry(long fileEntryId)
		throws PortalException, SystemException {

		DLFileEntryPermission.check(
			getPermissionChecker(), fileEntryId, ActionKeys.VIEW);

		return dlFileEntryLocalService.getFileEntry(fileEntryId);
	}

	public DLFileEntry getFileEntry(long groupId, long folderId, String title)
		throws PortalException, SystemException {

		DLFileEntry dlFileEntry = dlFileEntryLocalService.getFileEntry(
			groupId, folderId, title);

		DLFileEntryPermission.check(
			getPermissionChecker(), dlFileEntry, ActionKeys.VIEW);

		return dlFileEntry;
	}

	public DLFileEntry getFileEntryByUuidAndGroupId(String uuid, long groupId)
		throws PortalException, SystemException {

		DLFileEntry dlFileEntry = dlFileEntryPersistence.findByUUID_G(
			uuid, groupId);

		DLFileEntryPermission.check(
			getPermissionChecker(), dlFileEntry, ActionKeys.VIEW);

		return dlFileEntry;
	}

	public Lock getFileEntryLock(long fileEntryId) {
		try {
			return lockLocalService.getLock(
				DLFileEntry.class.getName(), fileEntryId);
		}
		catch (Exception e) {
			return null;
		}
	}

	public int getFoldersFileEntriesCount(
			long groupId, List<Long> folderIds, int status)
		throws SystemException {

		if (folderIds.size() <= PropsValues.SQL_DATA_MAX_PARAMETERS) {
			return dlFileEntryFinder.filterCountByG_F_S(
				groupId, folderIds, status);
		}
		else {
			int start = 0;
			int end = PropsValues.SQL_DATA_MAX_PARAMETERS;

			int filesCount = dlFileEntryFinder.filterCountByG_F_S(
				groupId, folderIds.subList(start, end), status);

			folderIds.subList(start, end).clear();

			filesCount += getFoldersFileEntriesCount(
				groupId, folderIds, status);

			return filesCount;
		}
	}

	public List<DLFileEntry> getGroupFileEntries(
			long groupId, long userId, long rootFolderId, int start, int end,
			OrderByComparator obc)
		throws SystemException {

		long[] folderIds = dlFolderService.getFolderIds(groupId, rootFolderId);

		if (folderIds.length == 0) {
			return Collections.emptyList();
		}
		else if (userId <= 0) {
			return dlFileEntryPersistence.filterFindByG_F(
				groupId, folderIds, start, end, obc);
		}
		else {
			return dlFileEntryPersistence.filterFindByG_U_F(
				groupId, userId, folderIds, start, end, obc);
		}
	}

	public List<DLFileEntry> getGroupFileEntries(
			long groupId, long userId, long rootFolderId, String[] mimeTypes,
			int status, int start, int end, OrderByComparator obc)
		throws SystemException {

		long[] folderIds = dlFolderService.getFolderIds(groupId, rootFolderId);

		if (folderIds.length == 0) {
			return Collections.emptyList();
		}

		List<Long> folderIdsList = ListUtil.toList(folderIds);

		return dlFileEntryFinder.findByG_U_F_M_S(
			groupId, userId, folderIdsList, mimeTypes, status, start, end, obc);
	}

	public int getGroupFileEntriesCount(
			long groupId, long userId, long rootFolderId)
		throws SystemException {

		long[] folderIds = dlFolderService.getFolderIds(groupId, rootFolderId);

		if (folderIds.length == 0) {
			return 0;
		}
		else if (userId <= 0) {
			return dlFileEntryPersistence.filterCountByG_F(groupId, folderIds);
		}
		else {
			return dlFileEntryPersistence.filterCountByG_U_F(
				groupId, userId, folderIds);
		}
	}

	public int getGroupFileEntriesCount(
			long groupId, long userId, long rootFolderId, String[] mimeTypes,
			int status)
		throws SystemException {

		long[] folderIds = dlFolderService.getFolderIds(groupId, rootFolderId);

		if (folderIds.length == 0) {
			return 0;
		}

		List<Long> folderIdsList = ListUtil.toList(folderIds);

		return dlFileEntryFinder.countByG_U_F_M_S(
			groupId, userId, folderIdsList, mimeTypes, status);
	}

	public boolean hasFileEntryLock(long fileEntryId)
		throws PortalException, SystemException {

		DLFileEntry dlFileEntry = dlFileEntryLocalService.getFileEntry(
			fileEntryId);

		long folderId = dlFileEntry.getFolderId();

		boolean hasLock = lockLocalService.hasLock(
			getUserId(), DLFileEntry.class.getName(), fileEntryId);

		if ((!hasLock) &&
			(folderId != DLFolderConstants.DEFAULT_PARENT_FOLDER_ID)) {

			hasLock = dlFolderService.hasInheritableLock(folderId);
		}

		return hasLock;
	}

	public boolean isFileEntryCheckedOut(long fileEntryId)
		throws PortalException, SystemException {

		return dlFileEntryLocalService.isFileEntryCheckedOut(fileEntryId);
	}

	public Lock lockFileEntry(long fileEntryId)
		throws PortalException, SystemException {

		try {
			DLFileEntryPermission.check(
				getPermissionChecker(), fileEntryId, ActionKeys.UPDATE);
		}
		catch (NoSuchFileEntryException nsfee) {
		}

		return dlFileEntryLocalService.lockFileEntry(getUserId(), fileEntryId);
	}

	public Lock lockFileEntry(
			long fileEntryId, String owner, long expirationTime)
		throws PortalException, SystemException {

		try {
			DLFileEntryPermission.check(
				getPermissionChecker(), fileEntryId, ActionKeys.UPDATE);
		}
		catch (NoSuchFileEntryException nsfee) {
		}

		return dlFileEntryLocalService.lockFileEntry(
			getUserId(), fileEntryId, owner, expirationTime);
	}

	public DLFileEntry moveFileEntry(
			long fileEntryId, long newFolderId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		DLFileEntryPermission.check(
			getPermissionChecker(), fileEntryId, ActionKeys.UPDATE);

		return dlFileEntryLocalService.moveFileEntry(
			getUserId(), fileEntryId, newFolderId, serviceContext);

	}

	public Lock refreshFileEntryLock(String lockUuid, long expirationTime)
		throws PortalException, SystemException {

		return lockLocalService.refresh(lockUuid, expirationTime);
	}

	public void revertFileEntry(
			long fileEntryId, String version, ServiceContext serviceContext)
		throws PortalException, SystemException {

		DLFileEntryPermission.check(
			getPermissionChecker(), fileEntryId, ActionKeys.UPDATE);

		dlFileEntryLocalService.revertFileEntry(
			getUserId(), fileEntryId, version, serviceContext);
	}

	public void unlockFileEntry(long fileEntryId)
		throws PortalException, SystemException {

		try {
			DLFileEntryPermission.check(
				getPermissionChecker(), fileEntryId, ActionKeys.UPDATE);
		}
		catch (NoSuchFileEntryException nsfee) {
		}

		dlFileEntryLocalService.unlockFileEntry(fileEntryId);
	}

	public void unlockFileEntry(long fileEntryId, String lockUuid)
		throws PortalException, SystemException {

		try {
			DLFileEntryPermission.check(
				getPermissionChecker(), fileEntryId, ActionKeys.UPDATE);
		}
		catch (NoSuchFileEntryException nsfee) {
		}

		dlFileEntryLocalService.unlockFileEntry(fileEntryId, lockUuid);
	}

	public DLFileEntry updateFileEntry(
			long fileEntryId, String sourceFileName, String mimeType,
			String title, String description, String changeLog,
			boolean majorVersion, long fileEntryTypeId,
			Map<String, Fields> fieldsMap, File file, InputStream is, long size,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		DLFileEntryPermission.check(
			getPermissionChecker(), fileEntryId, ActionKeys.UPDATE);

		return dlFileEntryLocalService.updateFileEntry(
			getUserId(), fileEntryId, sourceFileName, mimeType, title,
			description, changeLog, majorVersion, fileEntryTypeId, fieldsMap,
			file, is, size, serviceContext);
	}

	public boolean verifyFileEntryCheckOut(long fileEntryId, String lockUuid)
		throws PortalException, SystemException {

		return dlFileEntryLocalService.verifyFileEntryCheckOut(
			fileEntryId, lockUuid);
	}

	public boolean verifyFileEntryLock(long fileEntryId, String lockUuid)
		throws PortalException, SystemException {

		return dlFileEntryLocalService.verifyFileEntryLock(
			fileEntryId, lockUuid);
	}

}