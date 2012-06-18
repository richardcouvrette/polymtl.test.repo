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
 * This class is a wrapper for {@link DLContent}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       DLContent
 * @generated
 */
public class DLContentWrapper implements DLContent, ModelWrapper<DLContent> {
	public DLContentWrapper(DLContent dlContent) {
		_dlContent = dlContent;
	}

	public Class<?> getModelClass() {
		return DLContent.class;
	}

	public String getModelClassName() {
		return DLContent.class.getName();
	}

	/**
	* Returns the primary key of this document library content.
	*
	* @return the primary key of this document library content
	*/
	public long getPrimaryKey() {
		return _dlContent.getPrimaryKey();
	}

	/**
	* Sets the primary key of this document library content.
	*
	* @param primaryKey the primary key of this document library content
	*/
	public void setPrimaryKey(long primaryKey) {
		_dlContent.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the content ID of this document library content.
	*
	* @return the content ID of this document library content
	*/
	public long getContentId() {
		return _dlContent.getContentId();
	}

	/**
	* Sets the content ID of this document library content.
	*
	* @param contentId the content ID of this document library content
	*/
	public void setContentId(long contentId) {
		_dlContent.setContentId(contentId);
	}

	/**
	* Returns the group ID of this document library content.
	*
	* @return the group ID of this document library content
	*/
	public long getGroupId() {
		return _dlContent.getGroupId();
	}

	/**
	* Sets the group ID of this document library content.
	*
	* @param groupId the group ID of this document library content
	*/
	public void setGroupId(long groupId) {
		_dlContent.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this document library content.
	*
	* @return the company ID of this document library content
	*/
	public long getCompanyId() {
		return _dlContent.getCompanyId();
	}

	/**
	* Sets the company ID of this document library content.
	*
	* @param companyId the company ID of this document library content
	*/
	public void setCompanyId(long companyId) {
		_dlContent.setCompanyId(companyId);
	}

	/**
	* Returns the repository ID of this document library content.
	*
	* @return the repository ID of this document library content
	*/
	public long getRepositoryId() {
		return _dlContent.getRepositoryId();
	}

	/**
	* Sets the repository ID of this document library content.
	*
	* @param repositoryId the repository ID of this document library content
	*/
	public void setRepositoryId(long repositoryId) {
		_dlContent.setRepositoryId(repositoryId);
	}

	/**
	* Returns the path of this document library content.
	*
	* @return the path of this document library content
	*/
	public java.lang.String getPath() {
		return _dlContent.getPath();
	}

	/**
	* Sets the path of this document library content.
	*
	* @param path the path of this document library content
	*/
	public void setPath(java.lang.String path) {
		_dlContent.setPath(path);
	}

	/**
	* Returns the version of this document library content.
	*
	* @return the version of this document library content
	*/
	public java.lang.String getVersion() {
		return _dlContent.getVersion();
	}

	/**
	* Sets the version of this document library content.
	*
	* @param version the version of this document library content
	*/
	public void setVersion(java.lang.String version) {
		_dlContent.setVersion(version);
	}

	/**
	* Returns the data of this document library content.
	*
	* @return the data of this document library content
	*/
	public java.sql.Blob getData() {
		return _dlContent.getData();
	}

	/**
	* Sets the data of this document library content.
	*
	* @param data the data of this document library content
	*/
	public void setData(java.sql.Blob data) {
		_dlContent.setData(data);
	}

	/**
	* Returns the size of this document library content.
	*
	* @return the size of this document library content
	*/
	public long getSize() {
		return _dlContent.getSize();
	}

	/**
	* Sets the size of this document library content.
	*
	* @param size the size of this document library content
	*/
	public void setSize(long size) {
		_dlContent.setSize(size);
	}

	public boolean isNew() {
		return _dlContent.isNew();
	}

	public void setNew(boolean n) {
		_dlContent.setNew(n);
	}

	public boolean isCachedModel() {
		return _dlContent.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_dlContent.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _dlContent.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _dlContent.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_dlContent.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _dlContent.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_dlContent.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new DLContentWrapper((DLContent)_dlContent.clone());
	}

	public int compareTo(
		com.liferay.portlet.documentlibrary.model.DLContent dlContent) {
		return _dlContent.compareTo(dlContent);
	}

	@Override
	public int hashCode() {
		return _dlContent.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portlet.documentlibrary.model.DLContent> toCacheModel() {
		return _dlContent.toCacheModel();
	}

	public com.liferay.portlet.documentlibrary.model.DLContent toEscapedModel() {
		return new DLContentWrapper(_dlContent.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _dlContent.toString();
	}

	public java.lang.String toXmlString() {
		return _dlContent.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_dlContent.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public DLContent getWrappedDLContent() {
		return _dlContent;
	}

	public DLContent getWrappedModel() {
		return _dlContent;
	}

	public void resetOriginalValues() {
		_dlContent.resetOriginalValues();
	}

	private DLContent _dlContent;
}