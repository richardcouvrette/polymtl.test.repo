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
 * This class is a wrapper for {@link BlogsStatsUser}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       BlogsStatsUser
 * @generated
 */
public class BlogsStatsUserWrapper implements BlogsStatsUser,
	ModelWrapper<BlogsStatsUser> {
	public BlogsStatsUserWrapper(BlogsStatsUser blogsStatsUser) {
		_blogsStatsUser = blogsStatsUser;
	}

	public Class<?> getModelClass() {
		return BlogsStatsUser.class;
	}

	public String getModelClassName() {
		return BlogsStatsUser.class.getName();
	}

	/**
	* Returns the primary key of this blogs stats user.
	*
	* @return the primary key of this blogs stats user
	*/
	public long getPrimaryKey() {
		return _blogsStatsUser.getPrimaryKey();
	}

	/**
	* Sets the primary key of this blogs stats user.
	*
	* @param primaryKey the primary key of this blogs stats user
	*/
	public void setPrimaryKey(long primaryKey) {
		_blogsStatsUser.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the stats user ID of this blogs stats user.
	*
	* @return the stats user ID of this blogs stats user
	*/
	public long getStatsUserId() {
		return _blogsStatsUser.getStatsUserId();
	}

	/**
	* Sets the stats user ID of this blogs stats user.
	*
	* @param statsUserId the stats user ID of this blogs stats user
	*/
	public void setStatsUserId(long statsUserId) {
		_blogsStatsUser.setStatsUserId(statsUserId);
	}

	/**
	* Returns the stats user uuid of this blogs stats user.
	*
	* @return the stats user uuid of this blogs stats user
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getStatsUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _blogsStatsUser.getStatsUserUuid();
	}

	/**
	* Sets the stats user uuid of this blogs stats user.
	*
	* @param statsUserUuid the stats user uuid of this blogs stats user
	*/
	public void setStatsUserUuid(java.lang.String statsUserUuid) {
		_blogsStatsUser.setStatsUserUuid(statsUserUuid);
	}

	/**
	* Returns the group ID of this blogs stats user.
	*
	* @return the group ID of this blogs stats user
	*/
	public long getGroupId() {
		return _blogsStatsUser.getGroupId();
	}

	/**
	* Sets the group ID of this blogs stats user.
	*
	* @param groupId the group ID of this blogs stats user
	*/
	public void setGroupId(long groupId) {
		_blogsStatsUser.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this blogs stats user.
	*
	* @return the company ID of this blogs stats user
	*/
	public long getCompanyId() {
		return _blogsStatsUser.getCompanyId();
	}

	/**
	* Sets the company ID of this blogs stats user.
	*
	* @param companyId the company ID of this blogs stats user
	*/
	public void setCompanyId(long companyId) {
		_blogsStatsUser.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this blogs stats user.
	*
	* @return the user ID of this blogs stats user
	*/
	public long getUserId() {
		return _blogsStatsUser.getUserId();
	}

	/**
	* Sets the user ID of this blogs stats user.
	*
	* @param userId the user ID of this blogs stats user
	*/
	public void setUserId(long userId) {
		_blogsStatsUser.setUserId(userId);
	}

	/**
	* Returns the user uuid of this blogs stats user.
	*
	* @return the user uuid of this blogs stats user
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _blogsStatsUser.getUserUuid();
	}

	/**
	* Sets the user uuid of this blogs stats user.
	*
	* @param userUuid the user uuid of this blogs stats user
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_blogsStatsUser.setUserUuid(userUuid);
	}

	/**
	* Returns the entry count of this blogs stats user.
	*
	* @return the entry count of this blogs stats user
	*/
	public int getEntryCount() {
		return _blogsStatsUser.getEntryCount();
	}

	/**
	* Sets the entry count of this blogs stats user.
	*
	* @param entryCount the entry count of this blogs stats user
	*/
	public void setEntryCount(int entryCount) {
		_blogsStatsUser.setEntryCount(entryCount);
	}

	/**
	* Returns the last post date of this blogs stats user.
	*
	* @return the last post date of this blogs stats user
	*/
	public java.util.Date getLastPostDate() {
		return _blogsStatsUser.getLastPostDate();
	}

	/**
	* Sets the last post date of this blogs stats user.
	*
	* @param lastPostDate the last post date of this blogs stats user
	*/
	public void setLastPostDate(java.util.Date lastPostDate) {
		_blogsStatsUser.setLastPostDate(lastPostDate);
	}

	/**
	* Returns the ratings total entries of this blogs stats user.
	*
	* @return the ratings total entries of this blogs stats user
	*/
	public int getRatingsTotalEntries() {
		return _blogsStatsUser.getRatingsTotalEntries();
	}

	/**
	* Sets the ratings total entries of this blogs stats user.
	*
	* @param ratingsTotalEntries the ratings total entries of this blogs stats user
	*/
	public void setRatingsTotalEntries(int ratingsTotalEntries) {
		_blogsStatsUser.setRatingsTotalEntries(ratingsTotalEntries);
	}

	/**
	* Returns the ratings total score of this blogs stats user.
	*
	* @return the ratings total score of this blogs stats user
	*/
	public double getRatingsTotalScore() {
		return _blogsStatsUser.getRatingsTotalScore();
	}

	/**
	* Sets the ratings total score of this blogs stats user.
	*
	* @param ratingsTotalScore the ratings total score of this blogs stats user
	*/
	public void setRatingsTotalScore(double ratingsTotalScore) {
		_blogsStatsUser.setRatingsTotalScore(ratingsTotalScore);
	}

	/**
	* Returns the ratings average score of this blogs stats user.
	*
	* @return the ratings average score of this blogs stats user
	*/
	public double getRatingsAverageScore() {
		return _blogsStatsUser.getRatingsAverageScore();
	}

	/**
	* Sets the ratings average score of this blogs stats user.
	*
	* @param ratingsAverageScore the ratings average score of this blogs stats user
	*/
	public void setRatingsAverageScore(double ratingsAverageScore) {
		_blogsStatsUser.setRatingsAverageScore(ratingsAverageScore);
	}

	public boolean isNew() {
		return _blogsStatsUser.isNew();
	}

	public void setNew(boolean n) {
		_blogsStatsUser.setNew(n);
	}

	public boolean isCachedModel() {
		return _blogsStatsUser.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_blogsStatsUser.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _blogsStatsUser.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _blogsStatsUser.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_blogsStatsUser.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _blogsStatsUser.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_blogsStatsUser.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new BlogsStatsUserWrapper((BlogsStatsUser)_blogsStatsUser.clone());
	}

	public int compareTo(
		com.liferay.portlet.blogs.model.BlogsStatsUser blogsStatsUser) {
		return _blogsStatsUser.compareTo(blogsStatsUser);
	}

	@Override
	public int hashCode() {
		return _blogsStatsUser.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portlet.blogs.model.BlogsStatsUser> toCacheModel() {
		return _blogsStatsUser.toCacheModel();
	}

	public com.liferay.portlet.blogs.model.BlogsStatsUser toEscapedModel() {
		return new BlogsStatsUserWrapper(_blogsStatsUser.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _blogsStatsUser.toString();
	}

	public java.lang.String toXmlString() {
		return _blogsStatsUser.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_blogsStatsUser.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public BlogsStatsUser getWrappedBlogsStatsUser() {
		return _blogsStatsUser;
	}

	public BlogsStatsUser getWrappedModel() {
		return _blogsStatsUser;
	}

	public void resetOriginalValues() {
		_blogsStatsUser.resetOriginalValues();
	}

	private BlogsStatsUser _blogsStatsUser;
}