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

package com.liferay.portal.repository.liferayrepository.model;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.service.permission.DLFolderPermission;
import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Alexander Chow
 */
public class LiferayFolder extends LiferayModel implements Folder {

	public LiferayFolder(DLFolder dlFolder) {
		_dlFolder = dlFolder;
	}

	public LiferayFolder(DLFolder dlFolder, boolean escapedModel) {
		_dlFolder = dlFolder;
		_escapedModel = escapedModel;
	}

	public boolean containsPermission(
			PermissionChecker permissionChecker, String actionId)
		throws PortalException, SystemException {

		return DLFolderPermission.contains(
			permissionChecker, _dlFolder, actionId);
	}

	public List<Folder> getAncestors() throws PortalException, SystemException {
		return toFolders(_dlFolder.getAncestors());
	}

	public Map<String, Serializable> getAttributes() {
		ExpandoBridge expandoBridge = getExpandoBridge();

		return expandoBridge.getAttributes();
	}

	@Override
	public long getCompanyId() {
		return _dlFolder.getCompanyId();
	}

	public Date getCreateDate() {
		return _dlFolder.getCreateDate();
	}

	public String getDescription() {
		return _dlFolder.getDescription();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _dlFolder.getExpandoBridge();
	}

	public long getFolderId() {
		return _dlFolder.getFolderId();
	}

	public long getGroupId() {
		return _dlFolder.getGroupId();
	}

	public Date getLastPostDate() {
		return _dlFolder.getLastPostDate();
	}

	public Object getModel() {
		return _dlFolder;
	}

	public Class<?> getModelClass() {
		return DLFolder.class;
	}

	@Override
	public String getModelClassName() {
		return DLFolder.class.getName();
	}

	public Date getModifiedDate() {
		return _dlFolder.getModifiedDate();
	}

	public String getName() {
		return _dlFolder.getName();
	}

	public Folder getParentFolder() throws PortalException, SystemException {
		DLFolder dlParentFolder = _dlFolder.getParentFolder();

		if (dlParentFolder == null) {
			return null;
		}
		else {
			return new LiferayFolder(dlParentFolder);
		}
	}

	public long getParentFolderId() {
		return _dlFolder.getParentFolderId();
	}

	@Override
	public long getPrimaryKey() {
		return _dlFolder.getPrimaryKey();
	}

	public Serializable getPrimaryKeyObj() {
		return getPrimaryKey();
	}

	public long getRepositoryId() {
		return _dlFolder.getRepositoryId();
	}

	public long getUserId() {
		return _dlFolder.getUserId();
	}

	public String getUserName() {
		return _dlFolder.getUserName();
	}

	public String getUserUuid() throws SystemException {
		return _dlFolder.getUserUuid();
	}

	public String getUuid() {
		return _dlFolder.getUuid();
	}

	public boolean hasInheritableLock() {
		return _dlFolder.hasInheritableLock();
	}

	public boolean hasLock() {
		return _dlFolder.hasLock();
	}

	public boolean isDefaultRepository() {
		if (_dlFolder.getGroupId() == _dlFolder.getRepositoryId()) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isEscapedModel() {
		return _escapedModel;
	}

	public boolean isLocked() {
		return _dlFolder.isLocked();
	}

	public boolean isMountPoint() {
		return _dlFolder.isMountPoint();
	}

	public boolean isRoot() {
		return _dlFolder.isRoot();
	}

	public boolean isSupportsLocking() {
		if (isMountPoint()) {
			return false;
		}
		else {
			return true;
		}
	}

	public boolean isSupportsMetadata() {
		if (isMountPoint()) {
			return false;
		}
		else {
			return true;
		}
	}

	public boolean isSupportsMultipleUpload() {
		if (isMountPoint()) {
			return false;
		}
		else {
			return true;
		}
	}

	public boolean isSupportsShortcuts() {
		if (isMountPoint()) {
			return false;
		}
		else {
			return true;
		}
	}

	public boolean isSupportsSocial() {
		if (isMountPoint()) {
			return false;
		}
		else {
			return true;
		}
	}

	public void setCompanyId(long companyId) {
		_dlFolder.setCompanyId(companyId);
	}

	public void setCreateDate(Date date) {
		_dlFolder.setCreateDate(date);
	}

	public void setGroupId(long groupId) {
		_dlFolder.setGroupId(groupId);
	}

	public void setModifiedDate(Date date) {
		_dlFolder.setModifiedDate(date);
	}

	public void setPrimaryKey(long primaryKey) {
		_dlFolder.setPrimaryKey(primaryKey);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public void setUserId(long userId) {
		_dlFolder.setUserId(userId);
	}

	public void setUserName(String userName) {
		_dlFolder.setUserName(userName);
	}

	public void setUserUuid(String userUuid) {
		_dlFolder.setUserUuid(userUuid);
	}

	public Folder toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			return new LiferayFolder(_dlFolder.toEscapedModel(), true);
		}
	}

	private DLFolder _dlFolder;
	private boolean _escapedModel;

}