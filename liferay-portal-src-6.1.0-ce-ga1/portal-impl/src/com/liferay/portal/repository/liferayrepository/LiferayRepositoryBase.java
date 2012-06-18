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

package com.liferay.portal.repository.liferayrepository;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.SortedArrayList;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.repository.liferayrepository.util.LiferayBase;
import com.liferay.portal.service.RepositoryLocalService;
import com.liferay.portal.service.RepositoryService;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFileEntryConstants;
import com.liferay.portlet.documentlibrary.model.DLFileEntryType;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.service.DLAppHelperLocalService;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalService;
import com.liferay.portlet.documentlibrary.service.DLFileEntryService;
import com.liferay.portlet.documentlibrary.service.DLFileEntryTypeLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileVersionLocalService;
import com.liferay.portlet.documentlibrary.service.DLFileVersionService;
import com.liferay.portlet.documentlibrary.service.DLFolderLocalService;
import com.liferay.portlet.documentlibrary.service.DLFolderService;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.storage.Field;
import com.liferay.portlet.dynamicdatamapping.storage.Fields;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @author Alexander Chow
 */
public abstract class LiferayRepositoryBase extends LiferayBase {

	public LiferayRepositoryBase(
		RepositoryLocalService repositoryLocalService,
		RepositoryService repositoryService,
		DLAppHelperLocalService dlAppHelperLocalService,
		DLFileEntryLocalService dlFileEntryLocalService,
		DLFileEntryService dlFileEntryService,
		DLFileVersionLocalService dlFileVersionLocalService,
		DLFileVersionService dlFileVersionService,
		DLFolderLocalService dlFolderLocalService,
		DLFolderService dlFolderService, long repositoryId) {

		this.repositoryLocalService = repositoryLocalService;
		this.repositoryService = repositoryService;
		this.dlAppHelperLocalService = dlAppHelperLocalService;
		this.dlFileEntryLocalService = dlFileEntryLocalService;
		this.dlFileEntryService = dlFileEntryService;
		this.dlFileVersionLocalService = dlFileVersionLocalService;
		this.dlFileVersionService = dlFileVersionService;
		this.dlFolderLocalService = dlFolderLocalService;
		this.dlFolderService = dlFolderService;

		initByRepositoryId(repositoryId);
	}

	public LiferayRepositoryBase(
		RepositoryLocalService repositoryLocalService,
		RepositoryService repositoryService,
		DLAppHelperLocalService dlAppHelperLocalService,
		DLFileEntryLocalService dlFileEntryLocalService,
		DLFileEntryService dlFileEntryService,
		DLFileVersionLocalService dlFileVersionLocalService,
		DLFileVersionService dlFileVersionService,
		DLFolderLocalService dlFolderLocalService,
		DLFolderService dlFolderService, long folderId, long fileEntryId,
		long fileVersionId) {

		this.repositoryLocalService = repositoryLocalService;
		this.repositoryService = repositoryService;
		this.dlAppHelperLocalService = dlAppHelperLocalService;
		this.dlFileEntryLocalService = dlFileEntryLocalService;
		this.dlFileEntryService = dlFileEntryService;
		this.dlFileVersionLocalService = dlFileVersionLocalService;
		this.dlFileVersionService = dlFileVersionService;
		this.dlFolderLocalService = dlFolderLocalService;
		this.dlFolderService = dlFolderService;

		if (folderId != 0) {
			initByFolderId(folderId);
		}
		else if (fileEntryId != 0) {
			initByFileEntryId(fileEntryId);
		}
		else if (fileVersionId != 0) {
			initByFileVersionId(fileVersionId);
		}
	}

	public long getRepositoryId() {
		return _repositoryId;
	}

	protected void addFileEntryResources(
			DLFileEntry dlFileEntry, ServiceContext serviceContext)
		throws PortalException, SystemException {

		if (serviceContext.isAddGroupPermissions() ||
			serviceContext.isAddGuestPermissions()) {

			dlFileEntryLocalService.addFileEntryResources(
				dlFileEntry, serviceContext.isAddGroupPermissions(),
				serviceContext.isAddGuestPermissions());
		}
		else {
			if (serviceContext.isDeriveDefaultPermissions()) {
				serviceContext.deriveDefaultPermissions(
					dlFileEntry.getRepositoryId(),
					DLFileEntryConstants.getClassName());
			}

			dlFileEntryLocalService.addFileEntryResources(
				dlFileEntry, serviceContext.getGroupPermissions(),
				serviceContext.getGuestPermissions());
		}
	}

	protected HashMap<String, Fields> getFieldsMap(
			ServiceContext serviceContext, long fileEntryTypeId)
		throws PortalException, SystemException {

		HashMap<String, Fields> fieldsMap = new HashMap<String, Fields>();

		if (fileEntryTypeId <= 0) {
			return fieldsMap;
		}

		DLFileEntryType fileEntryType =
			DLFileEntryTypeLocalServiceUtil.getFileEntryType(fileEntryTypeId);

		List<DDMStructure> ddmStructures = fileEntryType.getDDMStructures();

		for (DDMStructure ddmStructure : ddmStructures) {
			String namespace = String.valueOf(ddmStructure.getStructureId());

			Set<String> fieldNames = ddmStructure.getFieldNames();

			Fields fields = (Fields)serviceContext.getAttribute(
				Fields.class.getName() + ddmStructure.getStructureId());

			if (fields == null) {
				fields = new Fields();

				for (String name : fieldNames) {
					Field field = new Field();

					field.setName(name);

					String value = ParamUtil.getString(
						serviceContext, namespace + name);

					field.setValue(value);

					fields.put(field);
				}
			}

			fieldsMap.put(ddmStructure.getStructureKey(), fields);
		}

		return fieldsMap;
	}

	protected long getGroupId() {
		return _groupId;
	}

	protected SortedArrayList<Long> getLongList(
		ServiceContext serviceContext, String name) {

		String value = ParamUtil.getString(serviceContext, name);

		if (value == null) {
			return new SortedArrayList<Long>();
		}

		long[] longArray = StringUtil.split(value, 0L);

		SortedArrayList<Long> longList = new SortedArrayList<Long>();

		for (long longValue : longArray) {
			longList.add(longValue);
		}

		return longList;
	}

	protected abstract void initByFileEntryId(long fileEntryId);

	protected abstract void initByFileVersionId(long fileVersionId);

	protected abstract void initByFolderId(long folderId);

	protected abstract void initByRepositoryId(long repositoryId);

	protected void setDlFolderId(long dlFolderId) {
		_dlFolderId = dlFolderId;
	}

	protected void setGroupId(long groupId) {
		_groupId = groupId;
	}

	protected void setRepositoryId(long repositoryId) {
		_repositoryId = repositoryId;
	}

	protected boolean isDefaultRepository() {
		if (_groupId == _repositoryId) {
			return true;
		}
		else {
			return false;
		}
	}

	protected long toFolderId(long folderId) {
		if (folderId == DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			return _dlFolderId;
		}
		else {
			return folderId;
		}
	}

	protected List<Long> toFolderIds(List<Long> folderIds) {
		List<Long> toFolderIds = new ArrayList<Long>(folderIds.size());

		for (long folderId : folderIds) {
			toFolderIds.add(toFolderId(folderId));
		}

		return toFolderIds;
	}

	protected DLAppHelperLocalService dlAppHelperLocalService;
	protected DLFileEntryLocalService dlFileEntryLocalService;
	protected DLFileEntryService dlFileEntryService;
	protected DLFileVersionLocalService dlFileVersionLocalService;
	protected DLFileVersionService dlFileVersionService;
	protected DLFolderLocalService dlFolderLocalService;
	protected DLFolderService dlFolderService;
	protected RepositoryLocalService repositoryLocalService;
	protected RepositoryService repositoryService;

	private long _dlFolderId;
	private long _groupId;
	private long _repositoryId;

}