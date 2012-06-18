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

package com.liferay.portlet.wiki.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link WikiPageResource}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       WikiPageResource
 * @generated
 */
public class WikiPageResourceWrapper implements WikiPageResource,
	ModelWrapper<WikiPageResource> {
	public WikiPageResourceWrapper(WikiPageResource wikiPageResource) {
		_wikiPageResource = wikiPageResource;
	}

	public Class<?> getModelClass() {
		return WikiPageResource.class;
	}

	public String getModelClassName() {
		return WikiPageResource.class.getName();
	}

	/**
	* Returns the primary key of this wiki page resource.
	*
	* @return the primary key of this wiki page resource
	*/
	public long getPrimaryKey() {
		return _wikiPageResource.getPrimaryKey();
	}

	/**
	* Sets the primary key of this wiki page resource.
	*
	* @param primaryKey the primary key of this wiki page resource
	*/
	public void setPrimaryKey(long primaryKey) {
		_wikiPageResource.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this wiki page resource.
	*
	* @return the uuid of this wiki page resource
	*/
	public java.lang.String getUuid() {
		return _wikiPageResource.getUuid();
	}

	/**
	* Sets the uuid of this wiki page resource.
	*
	* @param uuid the uuid of this wiki page resource
	*/
	public void setUuid(java.lang.String uuid) {
		_wikiPageResource.setUuid(uuid);
	}

	/**
	* Returns the resource prim key of this wiki page resource.
	*
	* @return the resource prim key of this wiki page resource
	*/
	public long getResourcePrimKey() {
		return _wikiPageResource.getResourcePrimKey();
	}

	/**
	* Sets the resource prim key of this wiki page resource.
	*
	* @param resourcePrimKey the resource prim key of this wiki page resource
	*/
	public void setResourcePrimKey(long resourcePrimKey) {
		_wikiPageResource.setResourcePrimKey(resourcePrimKey);
	}

	/**
	* Returns the node ID of this wiki page resource.
	*
	* @return the node ID of this wiki page resource
	*/
	public long getNodeId() {
		return _wikiPageResource.getNodeId();
	}

	/**
	* Sets the node ID of this wiki page resource.
	*
	* @param nodeId the node ID of this wiki page resource
	*/
	public void setNodeId(long nodeId) {
		_wikiPageResource.setNodeId(nodeId);
	}

	/**
	* Returns the title of this wiki page resource.
	*
	* @return the title of this wiki page resource
	*/
	public java.lang.String getTitle() {
		return _wikiPageResource.getTitle();
	}

	/**
	* Sets the title of this wiki page resource.
	*
	* @param title the title of this wiki page resource
	*/
	public void setTitle(java.lang.String title) {
		_wikiPageResource.setTitle(title);
	}

	public boolean isNew() {
		return _wikiPageResource.isNew();
	}

	public void setNew(boolean n) {
		_wikiPageResource.setNew(n);
	}

	public boolean isCachedModel() {
		return _wikiPageResource.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_wikiPageResource.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _wikiPageResource.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _wikiPageResource.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_wikiPageResource.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _wikiPageResource.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_wikiPageResource.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new WikiPageResourceWrapper((WikiPageResource)_wikiPageResource.clone());
	}

	public int compareTo(
		com.liferay.portlet.wiki.model.WikiPageResource wikiPageResource) {
		return _wikiPageResource.compareTo(wikiPageResource);
	}

	@Override
	public int hashCode() {
		return _wikiPageResource.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portlet.wiki.model.WikiPageResource> toCacheModel() {
		return _wikiPageResource.toCacheModel();
	}

	public com.liferay.portlet.wiki.model.WikiPageResource toEscapedModel() {
		return new WikiPageResourceWrapper(_wikiPageResource.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _wikiPageResource.toString();
	}

	public java.lang.String toXmlString() {
		return _wikiPageResource.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_wikiPageResource.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public WikiPageResource getWrappedWikiPageResource() {
		return _wikiPageResource;
	}

	public WikiPageResource getWrappedModel() {
		return _wikiPageResource;
	}

	public void resetOriginalValues() {
		_wikiPageResource.resetOriginalValues();
	}

	private WikiPageResource _wikiPageResource;
}