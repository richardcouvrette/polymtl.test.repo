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

package com.liferay.portlet.documentlibrary.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link DLSync}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       DLSync
 * @generated
 */
public class DLSyncWrapper implements DLSync, ModelWrapper<DLSync> {
	public DLSyncWrapper(DLSync dlSync) {
		_dlSync = dlSync;
	}

	public Class<?> getModelClass() {
		return DLSync.class;
	}

	public String getModelClassName() {
		return DLSync.class.getName();
	}

	/**
	* Returns the primary key of this d l sync.
	*
	* @return the primary key of this d l sync
	*/
	public long getPrimaryKey() {
		return _dlSync.getPrimaryKey();
	}

	/**
	* Sets the primary key of this d l sync.
	*
	* @param primaryKey the primary key of this d l sync
	*/
	public void setPrimaryKey(long primaryKey) {
		_dlSync.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the sync ID of this d l sync.
	*
	* @return the sync ID of this d l sync
	*/
	public long getSyncId() {
		return _dlSync.getSyncId();
	}

	/**
	* Sets the sync ID of this d l sync.
	*
	* @param syncId the sync ID of this d l sync
	*/
	public void setSyncId(long syncId) {
		_dlSync.setSyncId(syncId);
	}

	/**
	* Returns the company ID of this d l sync.
	*
	* @return the company ID of this d l sync
	*/
	public long getCompanyId() {
		return _dlSync.getCompanyId();
	}

	/**
	* Sets the company ID of this d l sync.
	*
	* @param companyId the company ID of this d l sync
	*/
	public void setCompanyId(long companyId) {
		_dlSync.setCompanyId(companyId);
	}

	/**
	* Returns the create date of this d l sync.
	*
	* @return the create date of this d l sync
	*/
	public java.util.Date getCreateDate() {
		return _dlSync.getCreateDate();
	}

	/**
	* Sets the create date of this d l sync.
	*
	* @param createDate the create date of this d l sync
	*/
	public void setCreateDate(java.util.Date createDate) {
		_dlSync.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this d l sync.
	*
	* @return the modified date of this d l sync
	*/
	public java.util.Date getModifiedDate() {
		return _dlSync.getModifiedDate();
	}

	/**
	* Sets the modified date of this d l sync.
	*
	* @param modifiedDate the modified date of this d l sync
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_dlSync.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the file ID of this d l sync.
	*
	* @return the file ID of this d l sync
	*/
	public long getFileId() {
		return _dlSync.getFileId();
	}

	/**
	* Sets the file ID of this d l sync.
	*
	* @param fileId the file ID of this d l sync
	*/
	public void setFileId(long fileId) {
		_dlSync.setFileId(fileId);
	}

	/**
	* Returns the file uuid of this d l sync.
	*
	* @return the file uuid of this d l sync
	*/
	public java.lang.String getFileUuid() {
		return _dlSync.getFileUuid();
	}

	/**
	* Sets the file uuid of this d l sync.
	*
	* @param fileUuid the file uuid of this d l sync
	*/
	public void setFileUuid(java.lang.String fileUuid) {
		_dlSync.setFileUuid(fileUuid);
	}

	/**
	* Returns the repository ID of this d l sync.
	*
	* @return the repository ID of this d l sync
	*/
	public long getRepositoryId() {
		return _dlSync.getRepositoryId();
	}

	/**
	* Sets the repository ID of this d l sync.
	*
	* @param repositoryId the repository ID of this d l sync
	*/
	public void setRepositoryId(long repositoryId) {
		_dlSync.setRepositoryId(repositoryId);
	}

	/**
	* Returns the parent folder ID of this d l sync.
	*
	* @return the parent folder ID of this d l sync
	*/
	public long getParentFolderId() {
		return _dlSync.getParentFolderId();
	}

	/**
	* Sets the parent folder ID of this d l sync.
	*
	* @param parentFolderId the parent folder ID of this d l sync
	*/
	public void setParentFolderId(long parentFolderId) {
		_dlSync.setParentFolderId(parentFolderId);
	}

	/**
	* Returns the name of this d l sync.
	*
	* @return the name of this d l sync
	*/
	public java.lang.String getName() {
		return _dlSync.getName();
	}

	/**
	* Sets the name of this d l sync.
	*
	* @param name the name of this d l sync
	*/
	public void setName(java.lang.String name) {
		_dlSync.setName(name);
	}

	/**
	* Returns the event of this d l sync.
	*
	* @return the event of this d l sync
	*/
	public java.lang.String getEvent() {
		return _dlSync.getEvent();
	}

	/**
	* Sets the event of this d l sync.
	*
	* @param event the event of this d l sync
	*/
	public void setEvent(java.lang.String event) {
		_dlSync.setEvent(event);
	}

	/**
	* Returns the type of this d l sync.
	*
	* @return the type of this d l sync
	*/
	public java.lang.String getType() {
		return _dlSync.getType();
	}

	/**
	* Sets the type of this d l sync.
	*
	* @param type the type of this d l sync
	*/
	public void setType(java.lang.String type) {
		_dlSync.setType(type);
	}

	/**
	* Returns the version of this d l sync.
	*
	* @return the version of this d l sync
	*/
	public java.lang.String getVersion() {
		return _dlSync.getVersion();
	}

	/**
	* Sets the version of this d l sync.
	*
	* @param version the version of this d l sync
	*/
	public void setVersion(java.lang.String version) {
		_dlSync.setVersion(version);
	}

	public boolean isNew() {
		return _dlSync.isNew();
	}

	public void setNew(boolean n) {
		_dlSync.setNew(n);
	}

	public boolean isCachedModel() {
		return _dlSync.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_dlSync.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _dlSync.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _dlSync.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_dlSync.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _dlSync.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_dlSync.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new DLSyncWrapper((DLSync)_dlSync.clone());
	}

	public int compareTo(
		com.liferay.portlet.documentlibrary.model.DLSync dlSync) {
		return _dlSync.compareTo(dlSync);
	}

	@Override
	public int hashCode() {
		return _dlSync.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portlet.documentlibrary.model.DLSync> toCacheModel() {
		return _dlSync.toCacheModel();
	}

	public com.liferay.portlet.documentlibrary.model.DLSync toEscapedModel() {
		return new DLSyncWrapper(_dlSync.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _dlSync.toString();
	}

	public java.lang.String toXmlString() {
		return _dlSync.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_dlSync.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public DLSync getWrappedDLSync() {
		return _dlSync;
	}

	public DLSync getWrappedModel() {
		return _dlSync;
	}

	public void resetOriginalValues() {
		_dlSync.resetOriginalValues();
	}

	private DLSync _dlSync;
}