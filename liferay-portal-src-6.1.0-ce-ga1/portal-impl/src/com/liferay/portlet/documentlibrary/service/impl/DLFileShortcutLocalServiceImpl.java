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
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.documentlibrary.NoSuchFileEntryException;
import com.liferay.portlet.documentlibrary.model.DLFileShortcut;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.service.base.DLFileShortcutLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class DLFileShortcutLocalServiceImpl
	extends DLFileShortcutLocalServiceBaseImpl {

	public DLFileShortcut addFileShortcut(
			long userId, long groupId, long folderId, long toFileEntryId,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// File shortcut

		User user = userPersistence.findByPrimaryKey(userId);
		folderId = getFolderId(user.getCompanyId(), folderId);
		Date now = new Date();

		validate(user, toFileEntryId);

		long fileShortcutId = counterLocalService.increment();

		DLFileShortcut fileShortcut = dlFileShortcutPersistence.create(
			fileShortcutId);

		fileShortcut.setUuid(serviceContext.getUuid());
		fileShortcut.setGroupId(groupId);
		fileShortcut.setCompanyId(user.getCompanyId());
		fileShortcut.setUserId(user.getUserId());
		fileShortcut.setUserName(user.getFullName());
		fileShortcut.setCreateDate(serviceContext.getCreateDate(now));
		fileShortcut.setModifiedDate(serviceContext.getModifiedDate(now));
		fileShortcut.setFolderId(folderId);
		fileShortcut.setToFileEntryId(toFileEntryId);
		fileShortcut.setStatus(WorkflowConstants.STATUS_APPROVED);
		fileShortcut.setStatusByUserId(userId);
		fileShortcut.setStatusByUserName(user.getFullName());
		fileShortcut.setStatusDate(now);

		dlFileShortcutPersistence.update(fileShortcut, false);

		// Resources

		if (serviceContext.isAddGroupPermissions() ||
			serviceContext.isAddGuestPermissions()) {

			addFileShortcutResources(
				fileShortcut, serviceContext.isAddGroupPermissions(),
				serviceContext.isAddGuestPermissions());
		}
		else {
			addFileShortcutResources(
				fileShortcut, serviceContext.getGroupPermissions(),
				serviceContext.getGuestPermissions());
		}

		// Folder

		if (folderId != DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			DLFolder dlFolder = dlFolderPersistence.findByPrimaryKey(folderId);

			dlFolder.setLastPostDate(fileShortcut.getModifiedDate());

			dlFolderPersistence.update(dlFolder, false);
		}

		// Asset

		FileEntry fileEntry = dlAppLocalService.getFileEntry(toFileEntryId);

		copyAssetTags(fileEntry, serviceContext);

		updateAsset(
			userId, fileShortcut, serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames());

		return fileShortcut;
	}

	public void addFileShortcutResources(
			DLFileShortcut fileShortcut, boolean addGroupPermissions,
			boolean addGuestPermissions)
		throws PortalException, SystemException {

		resourceLocalService.addResources(
			fileShortcut.getCompanyId(), fileShortcut.getGroupId(),
			fileShortcut.getUserId(), DLFileShortcut.class.getName(),
			fileShortcut.getFileShortcutId(), false, addGroupPermissions,
			addGuestPermissions);
	}

	public void addFileShortcutResources(
			DLFileShortcut fileShortcut, String[] groupPermissions,
			String[] guestPermissions)
		throws PortalException, SystemException {

		resourceLocalService.addModelResources(
			fileShortcut.getCompanyId(), fileShortcut.getGroupId(),
			fileShortcut.getUserId(), DLFileShortcut.class.getName(),
			fileShortcut.getFileShortcutId(), groupPermissions,
			guestPermissions);
	}

	public void addFileShortcutResources(
			long fileShortcutId, boolean addGroupPermissions,
			boolean addGuestPermissions)
		throws PortalException, SystemException {

		DLFileShortcut fileShortcut =
			dlFileShortcutPersistence.findByPrimaryKey(fileShortcutId);

		addFileShortcutResources(
			fileShortcut, addGroupPermissions, addGuestPermissions);
	}

	public void addFileShortcutResources(
			long fileShortcutId, String[] groupPermissions,
			String[] guestPermissions)
		throws PortalException, SystemException {

		DLFileShortcut fileShortcut =
			dlFileShortcutPersistence.findByPrimaryKey(fileShortcutId);

		addFileShortcutResources(
			fileShortcut, groupPermissions, guestPermissions);
	}

	public void deleteFileShortcut(DLFileShortcut fileShortcut)
		throws PortalException, SystemException {

		// File shortcut

		dlFileShortcutPersistence.remove(fileShortcut);

		// Resources

		resourceLocalService.deleteResource(
			fileShortcut.getCompanyId(), DLFileShortcut.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL,
			fileShortcut.getFileShortcutId());

		// Asset

		assetEntryLocalService.deleteEntry(
			DLFileShortcut.class.getName(), fileShortcut.getFileShortcutId());
	}

	public void deleteFileShortcut(long fileShortcutId)
		throws PortalException, SystemException {

		DLFileShortcut fileShortcut =
			dlFileShortcutLocalService.getDLFileShortcut(fileShortcutId);

		deleteFileShortcut(fileShortcut);
	}

	public void deleteFileShortcuts(long toFileEntryId)
		throws PortalException, SystemException {

		List<DLFileShortcut> fileShortcuts =
			dlFileShortcutPersistence.findByToFileEntryId(toFileEntryId);

		for (DLFileShortcut fileShortcut : fileShortcuts) {
			deleteFileShortcut(fileShortcut);
		}
	}

	public DLFileShortcut getFileShortcut(long fileShortcutId)
		throws PortalException, SystemException {

		return dlFileShortcutPersistence.findByPrimaryKey(fileShortcutId);
	}

	public void updateAsset(
			long userId, DLFileShortcut fileShortcut, long[] assetCategoryIds,
			String[] assetTagNames)
		throws PortalException, SystemException {

		FileEntry fileEntry = dlAppLocalService.getFileEntry(
			fileShortcut.getToFileEntryId());

		assetEntryLocalService.updateEntry(
			userId, fileShortcut.getGroupId(), DLFileShortcut.class.getName(),
			fileShortcut.getFileShortcutId(), fileShortcut.getUuid(), 0,
			assetCategoryIds, assetTagNames, false, null, null, null, null,
			fileEntry.getMimeType(), fileEntry.getTitle(),
			fileEntry.getDescription(), null, null, null, 0, 0, null, false);
	}

	public DLFileShortcut updateFileShortcut(
			long userId, long fileShortcutId, long folderId, long toFileEntryId,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// File shortcut

		User user = userPersistence.findByPrimaryKey(userId);

		DLFileShortcut fileShortcut =
			dlFileShortcutPersistence.findByPrimaryKey(fileShortcutId);

		validate(user, toFileEntryId);

		fileShortcut.setModifiedDate(
			serviceContext.getModifiedDate(new Date()));
		fileShortcut.setFolderId(folderId);
		fileShortcut.setToFileEntryId(toFileEntryId);

		dlFileShortcutPersistence.update(fileShortcut, false);

		// Folder

		if (folderId != DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			DLFolder dlFolder = dlFolderPersistence.findByPrimaryKey(folderId);

			dlFolder.setLastPostDate(fileShortcut.getModifiedDate());

			dlFolderPersistence.update(dlFolder, false);
		}

		// Asset

		FileEntry fileEntry = dlAppLocalService.getFileEntry(toFileEntryId);

		copyAssetTags(fileEntry, serviceContext);

		updateAsset(
			userId, fileShortcut, serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames());

		return fileShortcut;
	}

	public void updateFileShortcuts(
			long oldToFileEntryId, long newToFileEntryId)
		throws SystemException {

		List<DLFileShortcut> fileShortcuts =
			dlFileShortcutPersistence.findByToFileEntryId(oldToFileEntryId);

		for (DLFileShortcut fileShortcut : fileShortcuts) {
			fileShortcut.setToFileEntryId(newToFileEntryId);

			dlFileShortcutPersistence.update(fileShortcut, false);
		}
	}

	protected void copyAssetTags(
			FileEntry fileEntry, ServiceContext serviceContext)
		throws PortalException, SystemException {

		String[] assetTagNames = assetTagLocalService.getTagNames(
			FileEntry.class.getName(), fileEntry.getFileEntryId());

		assetTagLocalService.checkTags(
			serviceContext.getUserId(), serviceContext.getScopeGroupId(),
			assetTagNames);

		serviceContext.setAssetTagNames(assetTagNames);
	}

	protected long getFolderId(long companyId, long folderId)
		throws SystemException {

		if (folderId != DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

			// Ensure folder exists and belongs to the proper company

			DLFolder dlFolder = dlFolderPersistence.fetchByPrimaryKey(folderId);

			if ((dlFolder == null) || (companyId != dlFolder.getCompanyId())) {
				folderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;
			}
		}

		return folderId;
	}

	protected void validate(User user, long toFileEntryId)
		throws PortalException, SystemException {

		FileEntry fileEntry = dlAppLocalService.getFileEntry(toFileEntryId);

		if (user.getCompanyId() != fileEntry.getCompanyId()) {
			throw new NoSuchFileEntryException();
		}
	}

}