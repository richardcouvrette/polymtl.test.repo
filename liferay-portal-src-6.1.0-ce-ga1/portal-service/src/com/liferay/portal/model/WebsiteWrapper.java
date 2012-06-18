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

package com.liferay.portal.model;

/**
 * <p>
 * This class is a wrapper for {@link Website}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Website
 * @generated
 */
public class WebsiteWrapper implements Website, ModelWrapper<Website> {
	public WebsiteWrapper(Website website) {
		_website = website;
	}

	public Class<?> getModelClass() {
		return Website.class;
	}

	public String getModelClassName() {
		return Website.class.getName();
	}

	/**
	* Returns the primary key of this website.
	*
	* @return the primary key of this website
	*/
	public long getPrimaryKey() {
		return _website.getPrimaryKey();
	}

	/**
	* Sets the primary key of this website.
	*
	* @param primaryKey the primary key of this website
	*/
	public void setPrimaryKey(long primaryKey) {
		_website.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the website ID of this website.
	*
	* @return the website ID of this website
	*/
	public long getWebsiteId() {
		return _website.getWebsiteId();
	}

	/**
	* Sets the website ID of this website.
	*
	* @param websiteId the website ID of this website
	*/
	public void setWebsiteId(long websiteId) {
		_website.setWebsiteId(websiteId);
	}

	/**
	* Returns the company ID of this website.
	*
	* @return the company ID of this website
	*/
	public long getCompanyId() {
		return _website.getCompanyId();
	}

	/**
	* Sets the company ID of this website.
	*
	* @param companyId the company ID of this website
	*/
	public void setCompanyId(long companyId) {
		_website.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this website.
	*
	* @return the user ID of this website
	*/
	public long getUserId() {
		return _website.getUserId();
	}

	/**
	* Sets the user ID of this website.
	*
	* @param userId the user ID of this website
	*/
	public void setUserId(long userId) {
		_website.setUserId(userId);
	}

	/**
	* Returns the user uuid of this website.
	*
	* @return the user uuid of this website
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _website.getUserUuid();
	}

	/**
	* Sets the user uuid of this website.
	*
	* @param userUuid the user uuid of this website
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_website.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this website.
	*
	* @return the user name of this website
	*/
	public java.lang.String getUserName() {
		return _website.getUserName();
	}

	/**
	* Sets the user name of this website.
	*
	* @param userName the user name of this website
	*/
	public void setUserName(java.lang.String userName) {
		_website.setUserName(userName);
	}

	/**
	* Returns the create date of this website.
	*
	* @return the create date of this website
	*/
	public java.util.Date getCreateDate() {
		return _website.getCreateDate();
	}

	/**
	* Sets the create date of this website.
	*
	* @param createDate the create date of this website
	*/
	public void setCreateDate(java.util.Date createDate) {
		_website.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this website.
	*
	* @return the modified date of this website
	*/
	public java.util.Date getModifiedDate() {
		return _website.getModifiedDate();
	}

	/**
	* Sets the modified date of this website.
	*
	* @param modifiedDate the modified date of this website
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_website.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the fully qualified class name of this website.
	*
	* @return the fully qualified class name of this website
	*/
	public java.lang.String getClassName() {
		return _website.getClassName();
	}

	/**
	* Returns the class name ID of this website.
	*
	* @return the class name ID of this website
	*/
	public long getClassNameId() {
		return _website.getClassNameId();
	}

	/**
	* Sets the class name ID of this website.
	*
	* @param classNameId the class name ID of this website
	*/
	public void setClassNameId(long classNameId) {
		_website.setClassNameId(classNameId);
	}

	/**
	* Returns the class p k of this website.
	*
	* @return the class p k of this website
	*/
	public long getClassPK() {
		return _website.getClassPK();
	}

	/**
	* Sets the class p k of this website.
	*
	* @param classPK the class p k of this website
	*/
	public void setClassPK(long classPK) {
		_website.setClassPK(classPK);
	}

	/**
	* Returns the url of this website.
	*
	* @return the url of this website
	*/
	public java.lang.String getUrl() {
		return _website.getUrl();
	}

	/**
	* Sets the url of this website.
	*
	* @param url the url of this website
	*/
	public void setUrl(java.lang.String url) {
		_website.setUrl(url);
	}

	/**
	* Returns the type ID of this website.
	*
	* @return the type ID of this website
	*/
	public int getTypeId() {
		return _website.getTypeId();
	}

	/**
	* Sets the type ID of this website.
	*
	* @param typeId the type ID of this website
	*/
	public void setTypeId(int typeId) {
		_website.setTypeId(typeId);
	}

	/**
	* Returns the primary of this website.
	*
	* @return the primary of this website
	*/
	public boolean getPrimary() {
		return _website.getPrimary();
	}

	/**
	* Returns <code>true</code> if this website is primary.
	*
	* @return <code>true</code> if this website is primary; <code>false</code> otherwise
	*/
	public boolean isPrimary() {
		return _website.isPrimary();
	}

	/**
	* Sets whether this website is primary.
	*
	* @param primary the primary of this website
	*/
	public void setPrimary(boolean primary) {
		_website.setPrimary(primary);
	}

	public boolean isNew() {
		return _website.isNew();
	}

	public void setNew(boolean n) {
		_website.setNew(n);
	}

	public boolean isCachedModel() {
		return _website.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_website.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _website.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _website.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_website.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _website.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_website.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new WebsiteWrapper((Website)_website.clone());
	}

	public int compareTo(com.liferay.portal.model.Website website) {
		return _website.compareTo(website);
	}

	@Override
	public int hashCode() {
		return _website.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portal.model.Website> toCacheModel() {
		return _website.toCacheModel();
	}

	public com.liferay.portal.model.Website toEscapedModel() {
		return new WebsiteWrapper(_website.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _website.toString();
	}

	public java.lang.String toXmlString() {
		return _website.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_website.persist();
	}

	public com.liferay.portal.model.ListType getType()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _website.getType();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public Website getWrappedWebsite() {
		return _website;
	}

	public Website getWrappedModel() {
		return _website;
	}

	public void resetOriginalValues() {
		_website.resetOriginalValues();
	}

	private Website _website;
}