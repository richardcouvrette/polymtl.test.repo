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

package com.liferay.portlet.blogs.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link BlogsEntry}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       BlogsEntry
 * @generated
 */
public class BlogsEntryWrapper implements BlogsEntry, ModelWrapper<BlogsEntry> {
	public BlogsEntryWrapper(BlogsEntry blogsEntry) {
		_blogsEntry = blogsEntry;
	}

	public Class<?> getModelClass() {
		return BlogsEntry.class;
	}

	public String getModelClassName() {
		return BlogsEntry.class.getName();
	}

	/**
	* Returns the primary key of this blogs entry.
	*
	* @return the primary key of this blogs entry
	*/
	public long getPrimaryKey() {
		return _blogsEntry.getPrimaryKey();
	}

	/**
	* Sets the primary key of this blogs entry.
	*
	* @param primaryKey the primary key of this blogs entry
	*/
	public void setPrimaryKey(long primaryKey) {
		_blogsEntry.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this blogs entry.
	*
	* @return the uuid of this blogs entry
	*/
	public java.lang.String getUuid() {
		return _blogsEntry.getUuid();
	}

	/**
	* Sets the uuid of this blogs entry.
	*
	* @param uuid the uuid of this blogs entry
	*/
	public void setUuid(java.lang.String uuid) {
		_blogsEntry.setUuid(uuid);
	}

	/**
	* Returns the entry ID of this blogs entry.
	*
	* @return the entry ID of this blogs entry
	*/
	public long getEntryId() {
		return _blogsEntry.getEntryId();
	}

	/**
	* Sets the entry ID of this blogs entry.
	*
	* @param entryId the entry ID of this blogs entry
	*/
	public void setEntryId(long entryId) {
		_blogsEntry.setEntryId(entryId);
	}

	/**
	* Returns the group ID of this blogs entry.
	*
	* @return the group ID of this blogs entry
	*/
	public long getGroupId() {
		return _blogsEntry.getGroupId();
	}

	/**
	* Sets the group ID of this blogs entry.
	*
	* @param groupId the group ID of this blogs entry
	*/
	public void setGroupId(long groupId) {
		_blogsEntry.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this blogs entry.
	*
	* @return the company ID of this blogs entry
	*/
	public long getCompanyId() {
		return _blogsEntry.getCompanyId();
	}

	/**
	* Sets the company ID of this blogs entry.
	*
	* @param companyId the company ID of this blogs entry
	*/
	public void setCompanyId(long companyId) {
		_blogsEntry.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this blogs entry.
	*
	* @return the user ID of this blogs entry
	*/
	public long getUserId() {
		return _blogsEntry.getUserId();
	}

	/**
	* Sets the user ID of this blogs entry.
	*
	* @param userId the user ID of this blogs entry
	*/
	public void setUserId(long userId) {
		_blogsEntry.setUserId(userId);
	}

	/**
	* Returns the user uuid of this blogs entry.
	*
	* @return the user uuid of this blogs entry
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _blogsEntry.getUserUuid();
	}

	/**
	* Sets the user uuid of this blogs entry.
	*
	* @param userUuid the user uuid of this blogs entry
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_blogsEntry.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this blogs entry.
	*
	* @return the user name of this blogs entry
	*/
	public java.lang.String getUserName() {
		return _blogsEntry.getUserName();
	}

	/**
	* Sets the user name of this blogs entry.
	*
	* @param userName the user name of this blogs entry
	*/
	public void setUserName(java.lang.String userName) {
		_blogsEntry.setUserName(userName);
	}

	/**
	* Returns the create date of this blogs entry.
	*
	* @return the create date of this blogs entry
	*/
	public java.util.Date getCreateDate() {
		return _blogsEntry.getCreateDate();
	}

	/**
	* Sets the create date of this blogs entry.
	*
	* @param createDate the create date of this blogs entry
	*/
	public void setCreateDate(java.util.Date createDate) {
		_blogsEntry.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this blogs entry.
	*
	* @return the modified date of this blogs entry
	*/
	public java.util.Date getModifiedDate() {
		return _blogsEntry.getModifiedDate();
	}

	/**
	* Sets the modified date of this blogs entry.
	*
	* @param modifiedDate the modified date of this blogs entry
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_blogsEntry.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the title of this blogs entry.
	*
	* @return the title of this blogs entry
	*/
	public java.lang.String getTitle() {
		return _blogsEntry.getTitle();
	}

	/**
	* Sets the title of this blogs entry.
	*
	* @param title the title of this blogs entry
	*/
	public void setTitle(java.lang.String title) {
		_blogsEntry.setTitle(title);
	}

	/**
	* Returns the url title of this blogs entry.
	*
	* @return the url title of this blogs entry
	*/
	public java.lang.String getUrlTitle() {
		return _blogsEntry.getUrlTitle();
	}

	/**
	* Sets the url title of this blogs entry.
	*
	* @param urlTitle the url title of this blogs entry
	*/
	public void setUrlTitle(java.lang.String urlTitle) {
		_blogsEntry.setUrlTitle(urlTitle);
	}

	/**
	* Returns the description of this blogs entry.
	*
	* @return the description of this blogs entry
	*/
	public java.lang.String getDescription() {
		return _blogsEntry.getDescription();
	}

	/**
	* Sets the description of this blogs entry.
	*
	* @param description the description of this blogs entry
	*/
	public void setDescription(java.lang.String description) {
		_blogsEntry.setDescription(description);
	}

	/**
	* Returns the content of this blogs entry.
	*
	* @return the content of this blogs entry
	*/
	public java.lang.String getContent() {
		return _blogsEntry.getContent();
	}

	/**
	* Sets the content of this blogs entry.
	*
	* @param content the content of this blogs entry
	*/
	public void setContent(java.lang.String content) {
		_blogsEntry.setContent(content);
	}

	/**
	* Returns the display date of this blogs entry.
	*
	* @return the display date of this blogs entry
	*/
	public java.util.Date getDisplayDate() {
		return _blogsEntry.getDisplayDate();
	}

	/**
	* Sets the display date of this blogs entry.
	*
	* @param displayDate the display date of this blogs entry
	*/
	public void setDisplayDate(java.util.Date displayDate) {
		_blogsEntry.setDisplayDate(displayDate);
	}

	/**
	* Returns the allow pingbacks of this blogs entry.
	*
	* @return the allow pingbacks of this blogs entry
	*/
	public boolean getAllowPingbacks() {
		return _blogsEntry.getAllowPingbacks();
	}

	/**
	* Returns <code>true</code> if this blogs entry is allow pingbacks.
	*
	* @return <code>true</code> if this blogs entry is allow pingbacks; <code>false</code> otherwise
	*/
	public boolean isAllowPingbacks() {
		return _blogsEntry.isAllowPingbacks();
	}

	/**
	* Sets whether this blogs entry is allow pingbacks.
	*
	* @param allowPingbacks the allow pingbacks of this blogs entry
	*/
	public void setAllowPingbacks(boolean allowPingbacks) {
		_blogsEntry.setAllowPingbacks(allowPingbacks);
	}

	/**
	* Returns the allow trackbacks of this blogs entry.
	*
	* @return the allow trackbacks of this blogs entry
	*/
	public boolean getAllowTrackbacks() {
		return _blogsEntry.getAllowTrackbacks();
	}

	/**
	* Returns <code>true</code> if this blogs entry is allow trackbacks.
	*
	* @return <code>true</code> if this blogs entry is allow trackbacks; <code>false</code> otherwise
	*/
	public boolean isAllowTrackbacks() {
		return _blogsEntry.isAllowTrackbacks();
	}

	/**
	* Sets whether this blogs entry is allow trackbacks.
	*
	* @param allowTrackbacks the allow trackbacks of this blogs entry
	*/
	public void setAllowTrackbacks(boolean allowTrackbacks) {
		_blogsEntry.setAllowTrackbacks(allowTrackbacks);
	}

	/**
	* Returns the trackbacks of this blogs entry.
	*
	* @return the trackbacks of this blogs entry
	*/
	public java.lang.String getTrackbacks() {
		return _blogsEntry.getTrackbacks();
	}

	/**
	* Sets the trackbacks of this blogs entry.
	*
	* @param trackbacks the trackbacks of this blogs entry
	*/
	public void setTrackbacks(java.lang.String trackbacks) {
		_blogsEntry.setTrackbacks(trackbacks);
	}

	/**
	* Returns the small image of this blogs entry.
	*
	* @return the small image of this blogs entry
	*/
	public boolean getSmallImage() {
		return _blogsEntry.getSmallImage();
	}

	/**
	* Returns <code>true</code> if this blogs entry is small image.
	*
	* @return <code>true</code> if this blogs entry is small image; <code>false</code> otherwise
	*/
	public boolean isSmallImage() {
		return _blogsEntry.isSmallImage();
	}

	/**
	* Sets whether this blogs entry is small image.
	*
	* @param smallImage the small image of this blogs entry
	*/
	public void setSmallImage(boolean smallImage) {
		_blogsEntry.setSmallImage(smallImage);
	}

	/**
	* Returns the small image ID of this blogs entry.
	*
	* @return the small image ID of this blogs entry
	*/
	public long getSmallImageId() {
		return _blogsEntry.getSmallImageId();
	}

	/**
	* Sets the small image ID of this blogs entry.
	*
	* @param smallImageId the small image ID of this blogs entry
	*/
	public void setSmallImageId(long smallImageId) {
		_blogsEntry.setSmallImageId(smallImageId);
	}

	/**
	* Returns the small image u r l of this blogs entry.
	*
	* @return the small image u r l of this blogs entry
	*/
	public java.lang.String getSmallImageURL() {
		return _blogsEntry.getSmallImageURL();
	}

	/**
	* Sets the small image u r l of this blogs entry.
	*
	* @param smallImageURL the small image u r l of this blogs entry
	*/
	public void setSmallImageURL(java.lang.String smallImageURL) {
		_blogsEntry.setSmallImageURL(smallImageURL);
	}

	/**
	* Returns the status of this blogs entry.
	*
	* @return the status of this blogs entry
	*/
	public int getStatus() {
		return _blogsEntry.getStatus();
	}

	/**
	* Sets the status of this blogs entry.
	*
	* @param status the status of this blogs entry
	*/
	public void setStatus(int status) {
		_blogsEntry.setStatus(status);
	}

	/**
	* Returns the status by user ID of this blogs entry.
	*
	* @return the status by user ID of this blogs entry
	*/
	public long getStatusByUserId() {
		return _blogsEntry.getStatusByUserId();
	}

	/**
	* Sets the status by user ID of this blogs entry.
	*
	* @param statusByUserId the status by user ID of this blogs entry
	*/
	public void setStatusByUserId(long statusByUserId) {
		_blogsEntry.setStatusByUserId(statusByUserId);
	}

	/**
	* Returns the status by user uuid of this blogs entry.
	*
	* @return the status by user uuid of this blogs entry
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getStatusByUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _blogsEntry.getStatusByUserUuid();
	}

	/**
	* Sets the status by user uuid of this blogs entry.
	*
	* @param statusByUserUuid the status by user uuid of this blogs entry
	*/
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_blogsEntry.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Returns the status by user name of this blogs entry.
	*
	* @return the status by user name of this blogs entry
	*/
	public java.lang.String getStatusByUserName() {
		return _blogsEntry.getStatusByUserName();
	}

	/**
	* Sets the status by user name of this blogs entry.
	*
	* @param statusByUserName the status by user name of this blogs entry
	*/
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_blogsEntry.setStatusByUserName(statusByUserName);
	}

	/**
	* Returns the status date of this blogs entry.
	*
	* @return the status date of this blogs entry
	*/
	public java.util.Date getStatusDate() {
		return _blogsEntry.getStatusDate();
	}

	/**
	* Sets the status date of this blogs entry.
	*
	* @param statusDate the status date of this blogs entry
	*/
	public void setStatusDate(java.util.Date statusDate) {
		_blogsEntry.setStatusDate(statusDate);
	}

	/**
	* @deprecated Renamed to {@link #isApproved()}
	*/
	public boolean getApproved() {
		return _blogsEntry.getApproved();
	}

	/**
	* Returns <code>true</code> if this blogs entry is approved.
	*
	* @return <code>true</code> if this blogs entry is approved; <code>false</code> otherwise
	*/
	public boolean isApproved() {
		return _blogsEntry.isApproved();
	}

	/**
	* Returns <code>true</code> if this blogs entry is a draft.
	*
	* @return <code>true</code> if this blogs entry is a draft; <code>false</code> otherwise
	*/
	public boolean isDraft() {
		return _blogsEntry.isDraft();
	}

	/**
	* Returns <code>true</code> if this blogs entry is expired.
	*
	* @return <code>true</code> if this blogs entry is expired; <code>false</code> otherwise
	*/
	public boolean isExpired() {
		return _blogsEntry.isExpired();
	}

	/**
	* Returns <code>true</code> if this blogs entry is pending.
	*
	* @return <code>true</code> if this blogs entry is pending; <code>false</code> otherwise
	*/
	public boolean isPending() {
		return _blogsEntry.isPending();
	}

	public boolean isNew() {
		return _blogsEntry.isNew();
	}

	public void setNew(boolean n) {
		_blogsEntry.setNew(n);
	}

	public boolean isCachedModel() {
		return _blogsEntry.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_blogsEntry.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _blogsEntry.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _blogsEntry.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_blogsEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _blogsEntry.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_blogsEntry.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new BlogsEntryWrapper((BlogsEntry)_blogsEntry.clone());
	}

	public int compareTo(com.liferay.portlet.blogs.model.BlogsEntry blogsEntry) {
		return _blogsEntry.compareTo(blogsEntry);
	}

	@Override
	public int hashCode() {
		return _blogsEntry.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portlet.blogs.model.BlogsEntry> toCacheModel() {
		return _blogsEntry.toCacheModel();
	}

	public com.liferay.portlet.blogs.model.BlogsEntry toEscapedModel() {
		return new BlogsEntryWrapper(_blogsEntry.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _blogsEntry.toString();
	}

	public java.lang.String toXmlString() {
		return _blogsEntry.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_blogsEntry.persist();
	}

	public java.lang.String getSmallImageType()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _blogsEntry.getSmallImageType();
	}

	public void setSmallImageType(java.lang.String smallImageType) {
		_blogsEntry.setSmallImageType(smallImageType);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public BlogsEntry getWrappedBlogsEntry() {
		return _blogsEntry;
	}

	public BlogsEntry getWrappedModel() {
		return _blogsEntry;
	}

	public void resetOriginalValues() {
		_blogsEntry.resetOriginalValues();
	}

	private BlogsEntry _blogsEntry;
}