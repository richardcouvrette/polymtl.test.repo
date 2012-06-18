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
 * This class is a wrapper for {@link UserTrackerPath}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       UserTrackerPath
 * @generated
 */
public class UserTrackerPathWrapper implements UserTrackerPath,
	ModelWrapper<UserTrackerPath> {
	public UserTrackerPathWrapper(UserTrackerPath userTrackerPath) {
		_userTrackerPath = userTrackerPath;
	}

	public Class<?> getModelClass() {
		return UserTrackerPath.class;
	}

	public String getModelClassName() {
		return UserTrackerPath.class.getName();
	}

	/**
	* Returns the primary key of this user tracker path.
	*
	* @return the primary key of this user tracker path
	*/
	public long getPrimaryKey() {
		return _userTrackerPath.getPrimaryKey();
	}

	/**
	* Sets the primary key of this user tracker path.
	*
	* @param primaryKey the primary key of this user tracker path
	*/
	public void setPrimaryKey(long primaryKey) {
		_userTrackerPath.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the user tracker path ID of this user tracker path.
	*
	* @return the user tracker path ID of this user tracker path
	*/
	public long getUserTrackerPathId() {
		return _userTrackerPath.getUserTrackerPathId();
	}

	/**
	* Sets the user tracker path ID of this user tracker path.
	*
	* @param userTrackerPathId the user tracker path ID of this user tracker path
	*/
	public void setUserTrackerPathId(long userTrackerPathId) {
		_userTrackerPath.setUserTrackerPathId(userTrackerPathId);
	}

	/**
	* Returns the user tracker ID of this user tracker path.
	*
	* @return the user tracker ID of this user tracker path
	*/
	public long getUserTrackerId() {
		return _userTrackerPath.getUserTrackerId();
	}

	/**
	* Sets the user tracker ID of this user tracker path.
	*
	* @param userTrackerId the user tracker ID of this user tracker path
	*/
	public void setUserTrackerId(long userTrackerId) {
		_userTrackerPath.setUserTrackerId(userTrackerId);
	}

	/**
	* Returns the path of this user tracker path.
	*
	* @return the path of this user tracker path
	*/
	public java.lang.String getPath() {
		return _userTrackerPath.getPath();
	}

	/**
	* Sets the path of this user tracker path.
	*
	* @param path the path of this user tracker path
	*/
	public void setPath(java.lang.String path) {
		_userTrackerPath.setPath(path);
	}

	/**
	* Returns the path date of this user tracker path.
	*
	* @return the path date of this user tracker path
	*/
	public java.util.Date getPathDate() {
		return _userTrackerPath.getPathDate();
	}

	/**
	* Sets the path date of this user tracker path.
	*
	* @param pathDate the path date of this user tracker path
	*/
	public void setPathDate(java.util.Date pathDate) {
		_userTrackerPath.setPathDate(pathDate);
	}

	public boolean isNew() {
		return _userTrackerPath.isNew();
	}

	public void setNew(boolean n) {
		_userTrackerPath.setNew(n);
	}

	public boolean isCachedModel() {
		return _userTrackerPath.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_userTrackerPath.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _userTrackerPath.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _userTrackerPath.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_userTrackerPath.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _userTrackerPath.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_userTrackerPath.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new UserTrackerPathWrapper((UserTrackerPath)_userTrackerPath.clone());
	}

	public int compareTo(
		com.liferay.portal.model.UserTrackerPath userTrackerPath) {
		return _userTrackerPath.compareTo(userTrackerPath);
	}

	@Override
	public int hashCode() {
		return _userTrackerPath.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portal.model.UserTrackerPath> toCacheModel() {
		return _userTrackerPath.toCacheModel();
	}

	public com.liferay.portal.model.UserTrackerPath toEscapedModel() {
		return new UserTrackerPathWrapper(_userTrackerPath.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _userTrackerPath.toString();
	}

	public java.lang.String toXmlString() {
		return _userTrackerPath.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_userTrackerPath.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public UserTrackerPath getWrappedUserTrackerPath() {
		return _userTrackerPath;
	}

	public UserTrackerPath getWrappedModel() {
		return _userTrackerPath;
	}

	public void resetOriginalValues() {
		_userTrackerPath.resetOriginalValues();
	}

	private UserTrackerPath _userTrackerPath;
}