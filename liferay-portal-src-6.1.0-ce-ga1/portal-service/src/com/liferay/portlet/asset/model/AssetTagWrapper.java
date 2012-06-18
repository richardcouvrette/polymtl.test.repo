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

package com.liferay.portlet.asset.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link AssetTag}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AssetTag
 * @generated
 */
public class AssetTagWrapper implements AssetTag, ModelWrapper<AssetTag> {
	public AssetTagWrapper(AssetTag assetTag) {
		_assetTag = assetTag;
	}

	public Class<?> getModelClass() {
		return AssetTag.class;
	}

	public String getModelClassName() {
		return AssetTag.class.getName();
	}

	/**
	* Returns the primary key of this asset tag.
	*
	* @return the primary key of this asset tag
	*/
	public long getPrimaryKey() {
		return _assetTag.getPrimaryKey();
	}

	/**
	* Sets the primary key of this asset tag.
	*
	* @param primaryKey the primary key of this asset tag
	*/
	public void setPrimaryKey(long primaryKey) {
		_assetTag.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the tag ID of this asset tag.
	*
	* @return the tag ID of this asset tag
	*/
	public long getTagId() {
		return _assetTag.getTagId();
	}

	/**
	* Sets the tag ID of this asset tag.
	*
	* @param tagId the tag ID of this asset tag
	*/
	public void setTagId(long tagId) {
		_assetTag.setTagId(tagId);
	}

	/**
	* Returns the group ID of this asset tag.
	*
	* @return the group ID of this asset tag
	*/
	public long getGroupId() {
		return _assetTag.getGroupId();
	}

	/**
	* Sets the group ID of this asset tag.
	*
	* @param groupId the group ID of this asset tag
	*/
	public void setGroupId(long groupId) {
		_assetTag.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this asset tag.
	*
	* @return the company ID of this asset tag
	*/
	public long getCompanyId() {
		return _assetTag.getCompanyId();
	}

	/**
	* Sets the company ID of this asset tag.
	*
	* @param companyId the company ID of this asset tag
	*/
	public void setCompanyId(long companyId) {
		_assetTag.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this asset tag.
	*
	* @return the user ID of this asset tag
	*/
	public long getUserId() {
		return _assetTag.getUserId();
	}

	/**
	* Sets the user ID of this asset tag.
	*
	* @param userId the user ID of this asset tag
	*/
	public void setUserId(long userId) {
		_assetTag.setUserId(userId);
	}

	/**
	* Returns the user uuid of this asset tag.
	*
	* @return the user uuid of this asset tag
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetTag.getUserUuid();
	}

	/**
	* Sets the user uuid of this asset tag.
	*
	* @param userUuid the user uuid of this asset tag
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_assetTag.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this asset tag.
	*
	* @return the user name of this asset tag
	*/
	public java.lang.String getUserName() {
		return _assetTag.getUserName();
	}

	/**
	* Sets the user name of this asset tag.
	*
	* @param userName the user name of this asset tag
	*/
	public void setUserName(java.lang.String userName) {
		_assetTag.setUserName(userName);
	}

	/**
	* Returns the create date of this asset tag.
	*
	* @return the create date of this asset tag
	*/
	public java.util.Date getCreateDate() {
		return _assetTag.getCreateDate();
	}

	/**
	* Sets the create date of this asset tag.
	*
	* @param createDate the create date of this asset tag
	*/
	public void setCreateDate(java.util.Date createDate) {
		_assetTag.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this asset tag.
	*
	* @return the modified date of this asset tag
	*/
	public java.util.Date getModifiedDate() {
		return _assetTag.getModifiedDate();
	}

	/**
	* Sets the modified date of this asset tag.
	*
	* @param modifiedDate the modified date of this asset tag
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_assetTag.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the name of this asset tag.
	*
	* @return the name of this asset tag
	*/
	public java.lang.String getName() {
		return _assetTag.getName();
	}

	/**
	* Sets the name of this asset tag.
	*
	* @param name the name of this asset tag
	*/
	public void setName(java.lang.String name) {
		_assetTag.setName(name);
	}

	/**
	* Returns the asset count of this asset tag.
	*
	* @return the asset count of this asset tag
	*/
	public int getAssetCount() {
		return _assetTag.getAssetCount();
	}

	/**
	* Sets the asset count of this asset tag.
	*
	* @param assetCount the asset count of this asset tag
	*/
	public void setAssetCount(int assetCount) {
		_assetTag.setAssetCount(assetCount);
	}

	public boolean isNew() {
		return _assetTag.isNew();
	}

	public void setNew(boolean n) {
		_assetTag.setNew(n);
	}

	public boolean isCachedModel() {
		return _assetTag.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_assetTag.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _assetTag.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _assetTag.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_assetTag.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _assetTag.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_assetTag.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AssetTagWrapper((AssetTag)_assetTag.clone());
	}

	public int compareTo(com.liferay.portlet.asset.model.AssetTag assetTag) {
		return _assetTag.compareTo(assetTag);
	}

	@Override
	public int hashCode() {
		return _assetTag.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portlet.asset.model.AssetTag> toCacheModel() {
		return _assetTag.toCacheModel();
	}

	public com.liferay.portlet.asset.model.AssetTag toEscapedModel() {
		return new AssetTagWrapper(_assetTag.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _assetTag.toString();
	}

	public java.lang.String toXmlString() {
		return _assetTag.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_assetTag.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public AssetTag getWrappedAssetTag() {
		return _assetTag;
	}

	public AssetTag getWrappedModel() {
		return _assetTag;
	}

	public void resetOriginalValues() {
		_assetTag.resetOriginalValues();
	}

	private AssetTag _assetTag;
}