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
 * This class is a wrapper for {@link UserNotificationEvent}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       UserNotificationEvent
 * @generated
 */
public class UserNotificationEventWrapper implements UserNotificationEvent,
	ModelWrapper<UserNotificationEvent> {
	public UserNotificationEventWrapper(
		UserNotificationEvent userNotificationEvent) {
		_userNotificationEvent = userNotificationEvent;
	}

	public Class<?> getModelClass() {
		return UserNotificationEvent.class;
	}

	public String getModelClassName() {
		return UserNotificationEvent.class.getName();
	}

	/**
	* Returns the primary key of this user notification event.
	*
	* @return the primary key of this user notification event
	*/
	public long getPrimaryKey() {
		return _userNotificationEvent.getPrimaryKey();
	}

	/**
	* Sets the primary key of this user notification event.
	*
	* @param primaryKey the primary key of this user notification event
	*/
	public void setPrimaryKey(long primaryKey) {
		_userNotificationEvent.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this user notification event.
	*
	* @return the uuid of this user notification event
	*/
	public java.lang.String getUuid() {
		return _userNotificationEvent.getUuid();
	}

	/**
	* Sets the uuid of this user notification event.
	*
	* @param uuid the uuid of this user notification event
	*/
	public void setUuid(java.lang.String uuid) {
		_userNotificationEvent.setUuid(uuid);
	}

	/**
	* Returns the user notification event ID of this user notification event.
	*
	* @return the user notification event ID of this user notification event
	*/
	public long getUserNotificationEventId() {
		return _userNotificationEvent.getUserNotificationEventId();
	}

	/**
	* Sets the user notification event ID of this user notification event.
	*
	* @param userNotificationEventId the user notification event ID of this user notification event
	*/
	public void setUserNotificationEventId(long userNotificationEventId) {
		_userNotificationEvent.setUserNotificationEventId(userNotificationEventId);
	}

	/**
	* Returns the company ID of this user notification event.
	*
	* @return the company ID of this user notification event
	*/
	public long getCompanyId() {
		return _userNotificationEvent.getCompanyId();
	}

	/**
	* Sets the company ID of this user notification event.
	*
	* @param companyId the company ID of this user notification event
	*/
	public void setCompanyId(long companyId) {
		_userNotificationEvent.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this user notification event.
	*
	* @return the user ID of this user notification event
	*/
	public long getUserId() {
		return _userNotificationEvent.getUserId();
	}

	/**
	* Sets the user ID of this user notification event.
	*
	* @param userId the user ID of this user notification event
	*/
	public void setUserId(long userId) {
		_userNotificationEvent.setUserId(userId);
	}

	/**
	* Returns the user uuid of this user notification event.
	*
	* @return the user uuid of this user notification event
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEvent.getUserUuid();
	}

	/**
	* Sets the user uuid of this user notification event.
	*
	* @param userUuid the user uuid of this user notification event
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_userNotificationEvent.setUserUuid(userUuid);
	}

	/**
	* Returns the type of this user notification event.
	*
	* @return the type of this user notification event
	*/
	public java.lang.String getType() {
		return _userNotificationEvent.getType();
	}

	/**
	* Sets the type of this user notification event.
	*
	* @param type the type of this user notification event
	*/
	public void setType(java.lang.String type) {
		_userNotificationEvent.setType(type);
	}

	/**
	* Returns the timestamp of this user notification event.
	*
	* @return the timestamp of this user notification event
	*/
	public long getTimestamp() {
		return _userNotificationEvent.getTimestamp();
	}

	/**
	* Sets the timestamp of this user notification event.
	*
	* @param timestamp the timestamp of this user notification event
	*/
	public void setTimestamp(long timestamp) {
		_userNotificationEvent.setTimestamp(timestamp);
	}

	/**
	* Returns the deliver by of this user notification event.
	*
	* @return the deliver by of this user notification event
	*/
	public long getDeliverBy() {
		return _userNotificationEvent.getDeliverBy();
	}

	/**
	* Sets the deliver by of this user notification event.
	*
	* @param deliverBy the deliver by of this user notification event
	*/
	public void setDeliverBy(long deliverBy) {
		_userNotificationEvent.setDeliverBy(deliverBy);
	}

	/**
	* Returns the payload of this user notification event.
	*
	* @return the payload of this user notification event
	*/
	public java.lang.String getPayload() {
		return _userNotificationEvent.getPayload();
	}

	/**
	* Sets the payload of this user notification event.
	*
	* @param payload the payload of this user notification event
	*/
	public void setPayload(java.lang.String payload) {
		_userNotificationEvent.setPayload(payload);
	}

	/**
	* Returns the archived of this user notification event.
	*
	* @return the archived of this user notification event
	*/
	public boolean getArchived() {
		return _userNotificationEvent.getArchived();
	}

	/**
	* Returns <code>true</code> if this user notification event is archived.
	*
	* @return <code>true</code> if this user notification event is archived; <code>false</code> otherwise
	*/
	public boolean isArchived() {
		return _userNotificationEvent.isArchived();
	}

	/**
	* Sets whether this user notification event is archived.
	*
	* @param archived the archived of this user notification event
	*/
	public void setArchived(boolean archived) {
		_userNotificationEvent.setArchived(archived);
	}

	public boolean isNew() {
		return _userNotificationEvent.isNew();
	}

	public void setNew(boolean n) {
		_userNotificationEvent.setNew(n);
	}

	public boolean isCachedModel() {
		return _userNotificationEvent.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_userNotificationEvent.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _userNotificationEvent.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _userNotificationEvent.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_userNotificationEvent.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _userNotificationEvent.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_userNotificationEvent.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new UserNotificationEventWrapper((UserNotificationEvent)_userNotificationEvent.clone());
	}

	public int compareTo(
		com.liferay.portal.model.UserNotificationEvent userNotificationEvent) {
		return _userNotificationEvent.compareTo(userNotificationEvent);
	}

	@Override
	public int hashCode() {
		return _userNotificationEvent.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portal.model.UserNotificationEvent> toCacheModel() {
		return _userNotificationEvent.toCacheModel();
	}

	public com.liferay.portal.model.UserNotificationEvent toEscapedModel() {
		return new UserNotificationEventWrapper(_userNotificationEvent.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _userNotificationEvent.toString();
	}

	public java.lang.String toXmlString() {
		return _userNotificationEvent.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_userNotificationEvent.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public UserNotificationEvent getWrappedUserNotificationEvent() {
		return _userNotificationEvent;
	}

	public UserNotificationEvent getWrappedModel() {
		return _userNotificationEvent;
	}

	public void resetOriginalValues() {
		_userNotificationEvent.resetOriginalValues();
	}

	private UserNotificationEvent _userNotificationEvent;
}