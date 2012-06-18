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
 * This class is a wrapper for {@link AssetLink}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AssetLink
 * @generated
 */
public class AssetLinkWrapper implements AssetLink, ModelWrapper<AssetLink> {
	public AssetLinkWrapper(AssetLink assetLink) {
		_assetLink = assetLink;
	}

	public Class<?> getModelClass() {
		return AssetLink.class;
	}

	public String getModelClassName() {
		return AssetLink.class.getName();
	}

	/**
	* Returns the primary key of this asset link.
	*
	* @return the primary key of this asset link
	*/
	public long getPrimaryKey() {
		return _assetLink.getPrimaryKey();
	}

	/**
	* Sets the primary key of this asset link.
	*
	* @param primaryKey the primary key of this asset link
	*/
	public void setPrimaryKey(long primaryKey) {
		_assetLink.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the link ID of this asset link.
	*
	* @return the link ID of this asset link
	*/
	public long getLinkId() {
		return _assetLink.getLinkId();
	}

	/**
	* Sets the link ID of this asset link.
	*
	* @param linkId the link ID of this asset link
	*/
	public void setLinkId(long linkId) {
		_assetLink.setLinkId(linkId);
	}

	/**
	* Returns the company ID of this asset link.
	*
	* @return the company ID of this asset link
	*/
	public long getCompanyId() {
		return _assetLink.getCompanyId();
	}

	/**
	* Sets the company ID of this asset link.
	*
	* @param companyId the company ID of this asset link
	*/
	public void setCompanyId(long companyId) {
		_assetLink.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this asset link.
	*
	* @return the user ID of this asset link
	*/
	public long getUserId() {
		return _assetLink.getUserId();
	}

	/**
	* Sets the user ID of this asset link.
	*
	* @param userId the user ID of this asset link
	*/
	public void setUserId(long userId) {
		_assetLink.setUserId(userId);
	}

	/**
	* Returns the user uuid of this asset link.
	*
	* @return the user uuid of this asset link
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetLink.getUserUuid();
	}

	/**
	* Sets the user uuid of this asset link.
	*
	* @param userUuid the user uuid of this asset link
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_assetLink.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this asset link.
	*
	* @return the user name of this asset link
	*/
	public java.lang.String getUserName() {
		return _assetLink.getUserName();
	}

	/**
	* Sets the user name of this asset link.
	*
	* @param userName the user name of this asset link
	*/
	public void setUserName(java.lang.String userName) {
		_assetLink.setUserName(userName);
	}

	/**
	* Returns the create date of this asset link.
	*
	* @return the create date of this asset link
	*/
	public java.util.Date getCreateDate() {
		return _assetLink.getCreateDate();
	}

	/**
	* Sets the create date of this asset link.
	*
	* @param createDate the create date of this asset link
	*/
	public void setCreateDate(java.util.Date createDate) {
		_assetLink.setCreateDate(createDate);
	}

	/**
	* Returns the entry id1 of this asset link.
	*
	* @return the entry id1 of this asset link
	*/
	public long getEntryId1() {
		return _assetLink.getEntryId1();
	}

	/**
	* Sets the entry id1 of this asset link.
	*
	* @param entryId1 the entry id1 of this asset link
	*/
	public void setEntryId1(long entryId1) {
		_assetLink.setEntryId1(entryId1);
	}

	/**
	* Returns the entry id2 of this asset link.
	*
	* @return the entry id2 of this asset link
	*/
	public long getEntryId2() {
		return _assetLink.getEntryId2();
	}

	/**
	* Sets the entry id2 of this asset link.
	*
	* @param entryId2 the entry id2 of this asset link
	*/
	public void setEntryId2(long entryId2) {
		_assetLink.setEntryId2(entryId2);
	}

	/**
	* Returns the type of this asset link.
	*
	* @return the type of this asset link
	*/
	public int getType() {
		return _assetLink.getType();
	}

	/**
	* Sets the type of this asset link.
	*
	* @param type the type of this asset link
	*/
	public void setType(int type) {
		_assetLink.setType(type);
	}

	/**
	* Returns the weight of this asset link.
	*
	* @return the weight of this asset link
	*/
	public int getWeight() {
		return _assetLink.getWeight();
	}

	/**
	* Sets the weight of this asset link.
	*
	* @param weight the weight of this asset link
	*/
	public void setWeight(int weight) {
		_assetLink.setWeight(weight);
	}

	public boolean isNew() {
		return _assetLink.isNew();
	}

	public void setNew(boolean n) {
		_assetLink.setNew(n);
	}

	public boolean isCachedModel() {
		return _assetLink.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_assetLink.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _assetLink.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _assetLink.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_assetLink.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _assetLink.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_assetLink.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AssetLinkWrapper((AssetLink)_assetLink.clone());
	}

	public int compareTo(com.liferay.portlet.asset.model.AssetLink assetLink) {
		return _assetLink.compareTo(assetLink);
	}

	@Override
	public int hashCode() {
		return _assetLink.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portlet.asset.model.AssetLink> toCacheModel() {
		return _assetLink.toCacheModel();
	}

	public com.liferay.portlet.asset.model.AssetLink toEscapedModel() {
		return new AssetLinkWrapper(_assetLink.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _assetLink.toString();
	}

	public java.lang.String toXmlString() {
		return _assetLink.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_assetLink.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public AssetLink getWrappedAssetLink() {
		return _assetLink;
	}

	public AssetLink getWrappedModel() {
		return _assetLink;
	}

	public void resetOriginalValues() {
		_assetLink.resetOriginalValues();
	}

	private AssetLink _assetLink;
}