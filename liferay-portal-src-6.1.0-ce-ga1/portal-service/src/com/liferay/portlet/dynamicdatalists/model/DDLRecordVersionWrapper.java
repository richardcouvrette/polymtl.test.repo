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

package com.liferay.portlet.dynamicdatalists.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link DDLRecordVersion}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       DDLRecordVersion
 * @generated
 */
public class DDLRecordVersionWrapper implements DDLRecordVersion,
	ModelWrapper<DDLRecordVersion> {
	public DDLRecordVersionWrapper(DDLRecordVersion ddlRecordVersion) {
		_ddlRecordVersion = ddlRecordVersion;
	}

	public Class<?> getModelClass() {
		return DDLRecordVersion.class;
	}

	public String getModelClassName() {
		return DDLRecordVersion.class.getName();
	}

	/**
	* Returns the primary key of this d d l record version.
	*
	* @return the primary key of this d d l record version
	*/
	public long getPrimaryKey() {
		return _ddlRecordVersion.getPrimaryKey();
	}

	/**
	* Sets the primary key of this d d l record version.
	*
	* @param primaryKey the primary key of this d d l record version
	*/
	public void setPrimaryKey(long primaryKey) {
		_ddlRecordVersion.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the record version ID of this d d l record version.
	*
	* @return the record version ID of this d d l record version
	*/
	public long getRecordVersionId() {
		return _ddlRecordVersion.getRecordVersionId();
	}

	/**
	* Sets the record version ID of this d d l record version.
	*
	* @param recordVersionId the record version ID of this d d l record version
	*/
	public void setRecordVersionId(long recordVersionId) {
		_ddlRecordVersion.setRecordVersionId(recordVersionId);
	}

	/**
	* Returns the group ID of this d d l record version.
	*
	* @return the group ID of this d d l record version
	*/
	public long getGroupId() {
		return _ddlRecordVersion.getGroupId();
	}

	/**
	* Sets the group ID of this d d l record version.
	*
	* @param groupId the group ID of this d d l record version
	*/
	public void setGroupId(long groupId) {
		_ddlRecordVersion.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this d d l record version.
	*
	* @return the company ID of this d d l record version
	*/
	public long getCompanyId() {
		return _ddlRecordVersion.getCompanyId();
	}

	/**
	* Sets the company ID of this d d l record version.
	*
	* @param companyId the company ID of this d d l record version
	*/
	public void setCompanyId(long companyId) {
		_ddlRecordVersion.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this d d l record version.
	*
	* @return the user ID of this d d l record version
	*/
	public long getUserId() {
		return _ddlRecordVersion.getUserId();
	}

	/**
	* Sets the user ID of this d d l record version.
	*
	* @param userId the user ID of this d d l record version
	*/
	public void setUserId(long userId) {
		_ddlRecordVersion.setUserId(userId);
	}

	/**
	* Returns the user uuid of this d d l record version.
	*
	* @return the user uuid of this d d l record version
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ddlRecordVersion.getUserUuid();
	}

	/**
	* Sets the user uuid of this d d l record version.
	*
	* @param userUuid the user uuid of this d d l record version
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_ddlRecordVersion.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this d d l record version.
	*
	* @return the user name of this d d l record version
	*/
	public java.lang.String getUserName() {
		return _ddlRecordVersion.getUserName();
	}

	/**
	* Sets the user name of this d d l record version.
	*
	* @param userName the user name of this d d l record version
	*/
	public void setUserName(java.lang.String userName) {
		_ddlRecordVersion.setUserName(userName);
	}

	/**
	* Returns the create date of this d d l record version.
	*
	* @return the create date of this d d l record version
	*/
	public java.util.Date getCreateDate() {
		return _ddlRecordVersion.getCreateDate();
	}

	/**
	* Sets the create date of this d d l record version.
	*
	* @param createDate the create date of this d d l record version
	*/
	public void setCreateDate(java.util.Date createDate) {
		_ddlRecordVersion.setCreateDate(createDate);
	}

	/**
	* Returns the d d m storage ID of this d d l record version.
	*
	* @return the d d m storage ID of this d d l record version
	*/
	public long getDDMStorageId() {
		return _ddlRecordVersion.getDDMStorageId();
	}

	/**
	* Sets the d d m storage ID of this d d l record version.
	*
	* @param DDMStorageId the d d m storage ID of this d d l record version
	*/
	public void setDDMStorageId(long DDMStorageId) {
		_ddlRecordVersion.setDDMStorageId(DDMStorageId);
	}

	/**
	* Returns the record set ID of this d d l record version.
	*
	* @return the record set ID of this d d l record version
	*/
	public long getRecordSetId() {
		return _ddlRecordVersion.getRecordSetId();
	}

	/**
	* Sets the record set ID of this d d l record version.
	*
	* @param recordSetId the record set ID of this d d l record version
	*/
	public void setRecordSetId(long recordSetId) {
		_ddlRecordVersion.setRecordSetId(recordSetId);
	}

	/**
	* Returns the record ID of this d d l record version.
	*
	* @return the record ID of this d d l record version
	*/
	public long getRecordId() {
		return _ddlRecordVersion.getRecordId();
	}

	/**
	* Sets the record ID of this d d l record version.
	*
	* @param recordId the record ID of this d d l record version
	*/
	public void setRecordId(long recordId) {
		_ddlRecordVersion.setRecordId(recordId);
	}

	/**
	* Returns the version of this d d l record version.
	*
	* @return the version of this d d l record version
	*/
	public java.lang.String getVersion() {
		return _ddlRecordVersion.getVersion();
	}

	/**
	* Sets the version of this d d l record version.
	*
	* @param version the version of this d d l record version
	*/
	public void setVersion(java.lang.String version) {
		_ddlRecordVersion.setVersion(version);
	}

	/**
	* Returns the display index of this d d l record version.
	*
	* @return the display index of this d d l record version
	*/
	public int getDisplayIndex() {
		return _ddlRecordVersion.getDisplayIndex();
	}

	/**
	* Sets the display index of this d d l record version.
	*
	* @param displayIndex the display index of this d d l record version
	*/
	public void setDisplayIndex(int displayIndex) {
		_ddlRecordVersion.setDisplayIndex(displayIndex);
	}

	/**
	* Returns the status of this d d l record version.
	*
	* @return the status of this d d l record version
	*/
	public int getStatus() {
		return _ddlRecordVersion.getStatus();
	}

	/**
	* Sets the status of this d d l record version.
	*
	* @param status the status of this d d l record version
	*/
	public void setStatus(int status) {
		_ddlRecordVersion.setStatus(status);
	}

	/**
	* Returns the status by user ID of this d d l record version.
	*
	* @return the status by user ID of this d d l record version
	*/
	public long getStatusByUserId() {
		return _ddlRecordVersion.getStatusByUserId();
	}

	/**
	* Sets the status by user ID of this d d l record version.
	*
	* @param statusByUserId the status by user ID of this d d l record version
	*/
	public void setStatusByUserId(long statusByUserId) {
		_ddlRecordVersion.setStatusByUserId(statusByUserId);
	}

	/**
	* Returns the status by user uuid of this d d l record version.
	*
	* @return the status by user uuid of this d d l record version
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getStatusByUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ddlRecordVersion.getStatusByUserUuid();
	}

	/**
	* Sets the status by user uuid of this d d l record version.
	*
	* @param statusByUserUuid the status by user uuid of this d d l record version
	*/
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_ddlRecordVersion.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Returns the status by user name of this d d l record version.
	*
	* @return the status by user name of this d d l record version
	*/
	public java.lang.String getStatusByUserName() {
		return _ddlRecordVersion.getStatusByUserName();
	}

	/**
	* Sets the status by user name of this d d l record version.
	*
	* @param statusByUserName the status by user name of this d d l record version
	*/
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_ddlRecordVersion.setStatusByUserName(statusByUserName);
	}

	/**
	* Returns the status date of this d d l record version.
	*
	* @return the status date of this d d l record version
	*/
	public java.util.Date getStatusDate() {
		return _ddlRecordVersion.getStatusDate();
	}

	/**
	* Sets the status date of this d d l record version.
	*
	* @param statusDate the status date of this d d l record version
	*/
	public void setStatusDate(java.util.Date statusDate) {
		_ddlRecordVersion.setStatusDate(statusDate);
	}

	/**
	* @deprecated Renamed to {@link #isApproved()}
	*/
	public boolean getApproved() {
		return _ddlRecordVersion.getApproved();
	}

	/**
	* Returns <code>true</code> if this d d l record version is approved.
	*
	* @return <code>true</code> if this d d l record version is approved; <code>false</code> otherwise
	*/
	public boolean isApproved() {
		return _ddlRecordVersion.isApproved();
	}

	/**
	* Returns <code>true</code> if this d d l record version is a draft.
	*
	* @return <code>true</code> if this d d l record version is a draft; <code>false</code> otherwise
	*/
	public boolean isDraft() {
		return _ddlRecordVersion.isDraft();
	}

	/**
	* Returns <code>true</code> if this d d l record version is expired.
	*
	* @return <code>true</code> if this d d l record version is expired; <code>false</code> otherwise
	*/
	public boolean isExpired() {
		return _ddlRecordVersion.isExpired();
	}

	/**
	* Returns <code>true</code> if this d d l record version is pending.
	*
	* @return <code>true</code> if this d d l record version is pending; <code>false</code> otherwise
	*/
	public boolean isPending() {
		return _ddlRecordVersion.isPending();
	}

	public boolean isNew() {
		return _ddlRecordVersion.isNew();
	}

	public void setNew(boolean n) {
		_ddlRecordVersion.setNew(n);
	}

	public boolean isCachedModel() {
		return _ddlRecordVersion.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_ddlRecordVersion.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _ddlRecordVersion.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _ddlRecordVersion.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_ddlRecordVersion.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _ddlRecordVersion.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_ddlRecordVersion.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new DDLRecordVersionWrapper((DDLRecordVersion)_ddlRecordVersion.clone());
	}

	public int compareTo(
		com.liferay.portlet.dynamicdatalists.model.DDLRecordVersion ddlRecordVersion) {
		return _ddlRecordVersion.compareTo(ddlRecordVersion);
	}

	@Override
	public int hashCode() {
		return _ddlRecordVersion.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portlet.dynamicdatalists.model.DDLRecordVersion> toCacheModel() {
		return _ddlRecordVersion.toCacheModel();
	}

	public com.liferay.portlet.dynamicdatalists.model.DDLRecordVersion toEscapedModel() {
		return new DDLRecordVersionWrapper(_ddlRecordVersion.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _ddlRecordVersion.toString();
	}

	public java.lang.String toXmlString() {
		return _ddlRecordVersion.toXmlString();
	}

	public com.liferay.portlet.dynamicdatalists.model.DDLRecord getRecord()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ddlRecordVersion.getRecord();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public DDLRecordVersion getWrappedDDLRecordVersion() {
		return _ddlRecordVersion;
	}

	public DDLRecordVersion getWrappedModel() {
		return _ddlRecordVersion;
	}

	public void resetOriginalValues() {
		_ddlRecordVersion.resetOriginalValues();
	}

	private DDLRecordVersion _ddlRecordVersion;
}