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
 * This class is a wrapper for {@link WebDAVProps}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       WebDAVProps
 * @generated
 */
public class WebDAVPropsWrapper implements WebDAVProps,
	ModelWrapper<WebDAVProps> {
	public WebDAVPropsWrapper(WebDAVProps webDAVProps) {
		_webDAVProps = webDAVProps;
	}

	public Class<?> getModelClass() {
		return WebDAVProps.class;
	}

	public String getModelClassName() {
		return WebDAVProps.class.getName();
	}

	/**
	* Returns the primary key of this web d a v props.
	*
	* @return the primary key of this web d a v props
	*/
	public long getPrimaryKey() {
		return _webDAVProps.getPrimaryKey();
	}

	/**
	* Sets the primary key of this web d a v props.
	*
	* @param primaryKey the primary key of this web d a v props
	*/
	public void setPrimaryKey(long primaryKey) {
		_webDAVProps.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the web dav props ID of this web d a v props.
	*
	* @return the web dav props ID of this web d a v props
	*/
	public long getWebDavPropsId() {
		return _webDAVProps.getWebDavPropsId();
	}

	/**
	* Sets the web dav props ID of this web d a v props.
	*
	* @param webDavPropsId the web dav props ID of this web d a v props
	*/
	public void setWebDavPropsId(long webDavPropsId) {
		_webDAVProps.setWebDavPropsId(webDavPropsId);
	}

	/**
	* Returns the company ID of this web d a v props.
	*
	* @return the company ID of this web d a v props
	*/
	public long getCompanyId() {
		return _webDAVProps.getCompanyId();
	}

	/**
	* Sets the company ID of this web d a v props.
	*
	* @param companyId the company ID of this web d a v props
	*/
	public void setCompanyId(long companyId) {
		_webDAVProps.setCompanyId(companyId);
	}

	/**
	* Returns the create date of this web d a v props.
	*
	* @return the create date of this web d a v props
	*/
	public java.util.Date getCreateDate() {
		return _webDAVProps.getCreateDate();
	}

	/**
	* Sets the create date of this web d a v props.
	*
	* @param createDate the create date of this web d a v props
	*/
	public void setCreateDate(java.util.Date createDate) {
		_webDAVProps.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this web d a v props.
	*
	* @return the modified date of this web d a v props
	*/
	public java.util.Date getModifiedDate() {
		return _webDAVProps.getModifiedDate();
	}

	/**
	* Sets the modified date of this web d a v props.
	*
	* @param modifiedDate the modified date of this web d a v props
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_webDAVProps.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the fully qualified class name of this web d a v props.
	*
	* @return the fully qualified class name of this web d a v props
	*/
	public java.lang.String getClassName() {
		return _webDAVProps.getClassName();
	}

	/**
	* Returns the class name ID of this web d a v props.
	*
	* @return the class name ID of this web d a v props
	*/
	public long getClassNameId() {
		return _webDAVProps.getClassNameId();
	}

	/**
	* Sets the class name ID of this web d a v props.
	*
	* @param classNameId the class name ID of this web d a v props
	*/
	public void setClassNameId(long classNameId) {
		_webDAVProps.setClassNameId(classNameId);
	}

	/**
	* Returns the class p k of this web d a v props.
	*
	* @return the class p k of this web d a v props
	*/
	public long getClassPK() {
		return _webDAVProps.getClassPK();
	}

	/**
	* Sets the class p k of this web d a v props.
	*
	* @param classPK the class p k of this web d a v props
	*/
	public void setClassPK(long classPK) {
		_webDAVProps.setClassPK(classPK);
	}

	/**
	* Returns the props of this web d a v props.
	*
	* @return the props of this web d a v props
	*/
	public java.lang.String getProps() {
		return _webDAVProps.getProps();
	}

	/**
	* Sets the props of this web d a v props.
	*
	* @param props the props of this web d a v props
	*/
	public void setProps(java.lang.String props) {
		_webDAVProps.setProps(props);
	}

	public boolean isNew() {
		return _webDAVProps.isNew();
	}

	public void setNew(boolean n) {
		_webDAVProps.setNew(n);
	}

	public boolean isCachedModel() {
		return _webDAVProps.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_webDAVProps.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _webDAVProps.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _webDAVProps.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_webDAVProps.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _webDAVProps.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_webDAVProps.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new WebDAVPropsWrapper((WebDAVProps)_webDAVProps.clone());
	}

	public int compareTo(com.liferay.portal.model.WebDAVProps webDAVProps) {
		return _webDAVProps.compareTo(webDAVProps);
	}

	@Override
	public int hashCode() {
		return _webDAVProps.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portal.model.WebDAVProps> toCacheModel() {
		return _webDAVProps.toCacheModel();
	}

	public com.liferay.portal.model.WebDAVProps toEscapedModel() {
		return new WebDAVPropsWrapper(_webDAVProps.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _webDAVProps.toString();
	}

	public java.lang.String toXmlString() {
		return _webDAVProps.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_webDAVProps.persist();
	}

	public java.util.Set<com.liferay.portal.kernel.xml.QName> getPropsSet()
		throws java.lang.Exception {
		return _webDAVProps.getPropsSet();
	}

	public java.lang.String getText(java.lang.String name,
		java.lang.String prefix, java.lang.String uri)
		throws java.lang.Exception {
		return _webDAVProps.getText(name, prefix, uri);
	}

	public void addProp(java.lang.String name, java.lang.String prefix,
		java.lang.String uri) throws java.lang.Exception {
		_webDAVProps.addProp(name, prefix, uri);
	}

	public void addProp(java.lang.String name, java.lang.String prefix,
		java.lang.String uri, java.lang.String text) throws java.lang.Exception {
		_webDAVProps.addProp(name, prefix, uri, text);
	}

	public void removeProp(java.lang.String name, java.lang.String prefix,
		java.lang.String uri) throws java.lang.Exception {
		_webDAVProps.removeProp(name, prefix, uri);
	}

	public void store() throws java.lang.Exception {
		_webDAVProps.store();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public WebDAVProps getWrappedWebDAVProps() {
		return _webDAVProps;
	}

	public WebDAVProps getWrappedModel() {
		return _webDAVProps;
	}

	public void resetOriginalValues() {
		_webDAVProps.resetOriginalValues();
	}

	private WebDAVProps _webDAVProps;
}