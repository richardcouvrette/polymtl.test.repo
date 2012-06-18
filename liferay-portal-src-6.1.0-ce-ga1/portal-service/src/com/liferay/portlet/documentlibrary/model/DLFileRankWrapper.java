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
 * This class is a wrapper for {@link DLFileRank}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       DLFileRank
 * @generated
 */
public class DLFileRankWrapper implements DLFileRank, ModelWrapper<DLFileRank> {
	public DLFileRankWrapper(DLFileRank dlFileRank) {
		_dlFileRank = dlFileRank;
	}

	public Class<?> getModelClass() {
		return DLFileRank.class;
	}

	public String getModelClassName() {
		return DLFileRank.class.getName();
	}

	/**
	* Returns the primary key of this document library file rank.
	*
	* @return the primary key of this document library file rank
	*/
	public long getPrimaryKey() {
		return _dlFileRank.getPrimaryKey();
	}

	/**
	* Sets the primary key of this document library file rank.
	*
	* @param primaryKey the primary key of this document library file rank
	*/
	public void setPrimaryKey(long primaryKey) {
		_dlFileRank.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the file rank ID of this document library file rank.
	*
	* @return the file rank ID of this document library file rank
	*/
	public long getFileRankId() {
		return _dlFileRank.getFileRankId();
	}

	/**
	* Sets the file rank ID of this document library file rank.
	*
	* @param fileRankId the file rank ID of this document library file rank
	*/
	public void setFileRankId(long fileRankId) {
		_dlFileRank.setFileRankId(fileRankId);
	}

	/**
	* Returns the group ID of this document library file rank.
	*
	* @return the group ID of this document library file rank
	*/
	public long getGroupId() {
		return _dlFileRank.getGroupId();
	}

	/**
	* Sets the group ID of this document library file rank.
	*
	* @param groupId the group ID of this document library file rank
	*/
	public void setGroupId(long groupId) {
		_dlFileRank.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this document library file rank.
	*
	* @return the company ID of this document library file rank
	*/
	public long getCompanyId() {
		return _dlFileRank.getCompanyId();
	}

	/**
	* Sets the company ID of this document library file rank.
	*
	* @param companyId the company ID of this document library file rank
	*/
	public void setCompanyId(long companyId) {
		_dlFileRank.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this document library file rank.
	*
	* @return the user ID of this document library file rank
	*/
	public long getUserId() {
		return _dlFileRank.getUserId();
	}

	/**
	* Sets the user ID of this document library file rank.
	*
	* @param userId the user ID of this document library file rank
	*/
	public void setUserId(long userId) {
		_dlFileRank.setUserId(userId);
	}

	/**
	* Returns the user uuid of this document library file rank.
	*
	* @return the user uuid of this document library file rank
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlFileRank.getUserUuid();
	}

	/**
	* Sets the user uuid of this document library file rank.
	*
	* @param userUuid the user uuid of this document library file rank
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_dlFileRank.setUserUuid(userUuid);
	}

	/**
	* Returns the create date of this document library file rank.
	*
	* @return the create date of this document library file rank
	*/
	public java.util.Date getCreateDate() {
		return _dlFileRank.getCreateDate();
	}

	/**
	* Sets the create date of this document library file rank.
	*
	* @param createDate the create date of this document library file rank
	*/
	public void setCreateDate(java.util.Date createDate) {
		_dlFileRank.setCreateDate(createDate);
	}

	/**
	* Returns the file entry ID of this document library file rank.
	*
	* @return the file entry ID of this document library file rank
	*/
	public long getFileEntryId() {
		return _dlFileRank.getFileEntryId();
	}

	/**
	* Sets the file entry ID of this document library file rank.
	*
	* @param fileEntryId the file entry ID of this document library file rank
	*/
	public void setFileEntryId(long fileEntryId) {
		_dlFileRank.setFileEntryId(fileEntryId);
	}

	public boolean isNew() {
		return _dlFileRank.isNew();
	}

	public void setNew(boolean n) {
		_dlFileRank.setNew(n);
	}

	public boolean isCachedModel() {
		return _dlFileRank.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_dlFileRank.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _dlFileRank.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _dlFileRank.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_dlFileRank.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _dlFileRank.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_dlFileRank.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new DLFileRankWrapper((DLFileRank)_dlFileRank.clone());
	}

	public int compareTo(
		com.liferay.portlet.documentlibrary.model.DLFileRank dlFileRank) {
		return _dlFileRank.compareTo(dlFileRank);
	}

	@Override
	public int hashCode() {
		return _dlFileRank.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portlet.documentlibrary.model.DLFileRank> toCacheModel() {
		return _dlFileRank.toCacheModel();
	}

	public com.liferay.portlet.documentlibrary.model.DLFileRank toEscapedModel() {
		return new DLFileRankWrapper(_dlFileRank.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _dlFileRank.toString();
	}

	public java.lang.String toXmlString() {
		return _dlFileRank.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_dlFileRank.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public DLFileRank getWrappedDLFileRank() {
		return _dlFileRank;
	}

	public DLFileRank getWrappedModel() {
		return _dlFileRank;
	}

	public void resetOriginalValues() {
		_dlFileRank.resetOriginalValues();
	}

	private DLFileRank _dlFileRank;
}