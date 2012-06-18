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
 * This class is a wrapper for {@link AssetCategoryProperty}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AssetCategoryProperty
 * @generated
 */
public class AssetCategoryPropertyWrapper implements AssetCategoryProperty,
	ModelWrapper<AssetCategoryProperty> {
	public AssetCategoryPropertyWrapper(
		AssetCategoryProperty assetCategoryProperty) {
		_assetCategoryProperty = assetCategoryProperty;
	}

	public Class<?> getModelClass() {
		return AssetCategoryProperty.class;
	}

	public String getModelClassName() {
		return AssetCategoryProperty.class.getName();
	}

	/**
	* Returns the primary key of this asset category property.
	*
	* @return the primary key of this asset category property
	*/
	public long getPrimaryKey() {
		return _assetCategoryProperty.getPrimaryKey();
	}

	/**
	* Sets the primary key of this asset category property.
	*
	* @param primaryKey the primary key of this asset category property
	*/
	public void setPrimaryKey(long primaryKey) {
		_assetCategoryProperty.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the category property ID of this asset category property.
	*
	* @return the category property ID of this asset category property
	*/
	public long getCategoryPropertyId() {
		return _assetCategoryProperty.getCategoryPropertyId();
	}

	/**
	* Sets the category property ID of this asset category property.
	*
	* @param categoryPropertyId the category property ID of this asset category property
	*/
	public void setCategoryPropertyId(long categoryPropertyId) {
		_assetCategoryProperty.setCategoryPropertyId(categoryPropertyId);
	}

	/**
	* Returns the company ID of this asset category property.
	*
	* @return the company ID of this asset category property
	*/
	public long getCompanyId() {
		return _assetCategoryProperty.getCompanyId();
	}

	/**
	* Sets the company ID of this asset category property.
	*
	* @param companyId the company ID of this asset category property
	*/
	public void setCompanyId(long companyId) {
		_assetCategoryProperty.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this asset category property.
	*
	* @return the user ID of this asset category property
	*/
	public long getUserId() {
		return _assetCategoryProperty.getUserId();
	}

	/**
	* Sets the user ID of this asset category property.
	*
	* @param userId the user ID of this asset category property
	*/
	public void setUserId(long userId) {
		_assetCategoryProperty.setUserId(userId);
	}

	/**
	* Returns the user uuid of this asset category property.
	*
	* @return the user uuid of this asset category property
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetCategoryProperty.getUserUuid();
	}

	/**
	* Sets the user uuid of this asset category property.
	*
	* @param userUuid the user uuid of this asset category property
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_assetCategoryProperty.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this asset category property.
	*
	* @return the user name of this asset category property
	*/
	public java.lang.String getUserName() {
		return _assetCategoryProperty.getUserName();
	}

	/**
	* Sets the user name of this asset category property.
	*
	* @param userName the user name of this asset category property
	*/
	public void setUserName(java.lang.String userName) {
		_assetCategoryProperty.setUserName(userName);
	}

	/**
	* Returns the create date of this asset category property.
	*
	* @return the create date of this asset category property
	*/
	public java.util.Date getCreateDate() {
		return _assetCategoryProperty.getCreateDate();
	}

	/**
	* Sets the create date of this asset category property.
	*
	* @param createDate the create date of this asset category property
	*/
	public void setCreateDate(java.util.Date createDate) {
		_assetCategoryProperty.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this asset category property.
	*
	* @return the modified date of this asset category property
	*/
	public java.util.Date getModifiedDate() {
		return _assetCategoryProperty.getModifiedDate();
	}

	/**
	* Sets the modified date of this asset category property.
	*
	* @param modifiedDate the modified date of this asset category property
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_assetCategoryProperty.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the category ID of this asset category property.
	*
	* @return the category ID of this asset category property
	*/
	public long getCategoryId() {
		return _assetCategoryProperty.getCategoryId();
	}

	/**
	* Sets the category ID of this asset category property.
	*
	* @param categoryId the category ID of this asset category property
	*/
	public void setCategoryId(long categoryId) {
		_assetCategoryProperty.setCategoryId(categoryId);
	}

	/**
	* Returns the key of this asset category property.
	*
	* @return the key of this asset category property
	*/
	public java.lang.String getKey() {
		return _assetCategoryProperty.getKey();
	}

	/**
	* Sets the key of this asset category property.
	*
	* @param key the key of this asset category property
	*/
	public void setKey(java.lang.String key) {
		_assetCategoryProperty.setKey(key);
	}

	/**
	* Returns the value of this asset category property.
	*
	* @return the value of this asset category property
	*/
	public java.lang.String getValue() {
		return _assetCategoryProperty.getValue();
	}

	/**
	* Sets the value of this asset category property.
	*
	* @param value the value of this asset category property
	*/
	public void setValue(java.lang.String value) {
		_assetCategoryProperty.setValue(value);
	}

	public boolean isNew() {
		return _assetCategoryProperty.isNew();
	}

	public void setNew(boolean n) {
		_assetCategoryProperty.setNew(n);
	}

	public boolean isCachedModel() {
		return _assetCategoryProperty.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_assetCategoryProperty.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _assetCategoryProperty.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _assetCategoryProperty.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_assetCategoryProperty.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _assetCategoryProperty.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_assetCategoryProperty.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AssetCategoryPropertyWrapper((AssetCategoryProperty)_assetCategoryProperty.clone());
	}

	public int compareTo(
		com.liferay.portlet.asset.model.AssetCategoryProperty assetCategoryProperty) {
		return _assetCategoryProperty.compareTo(assetCategoryProperty);
	}

	@Override
	public int hashCode() {
		return _assetCategoryProperty.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portlet.asset.model.AssetCategoryProperty> toCacheModel() {
		return _assetCategoryProperty.toCacheModel();
	}

	public com.liferay.portlet.asset.model.AssetCategoryProperty toEscapedModel() {
		return new AssetCategoryPropertyWrapper(_assetCategoryProperty.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _assetCategoryProperty.toString();
	}

	public java.lang.String toXmlString() {
		return _assetCategoryProperty.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_assetCategoryProperty.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public AssetCategoryProperty getWrappedAssetCategoryProperty() {
		return _assetCategoryProperty;
	}

	public AssetCategoryProperty getWrappedModel() {
		return _assetCategoryProperty;
	}

	public void resetOriginalValues() {
		_assetCategoryProperty.resetOriginalValues();
	}

	private AssetCategoryProperty _assetCategoryProperty;
}