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

package com.liferay.portal.service;

/**
 * <p>
 * This class is a wrapper for {@link UserNotificationEventLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       UserNotificationEventLocalService
 * @generated
 */
public class UserNotificationEventLocalServiceWrapper
	implements UserNotificationEventLocalService,
		ServiceWrapper<UserNotificationEventLocalService> {
	public UserNotificationEventLocalServiceWrapper(
		UserNotificationEventLocalService userNotificationEventLocalService) {
		_userNotificationEventLocalService = userNotificationEventLocalService;
	}

	/**
	* Adds the user notification event to the database. Also notifies the appropriate model listeners.
	*
	* @param userNotificationEvent the user notification event
	* @return the user notification event that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.UserNotificationEvent addUserNotificationEvent(
		com.liferay.portal.model.UserNotificationEvent userNotificationEvent)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEventLocalService.addUserNotificationEvent(userNotificationEvent);
	}

	/**
	* Creates a new user notification event with the primary key. Does not add the user notification event to the database.
	*
	* @param userNotificationEventId the primary key for the new user notification event
	* @return the new user notification event
	*/
	public com.liferay.portal.model.UserNotificationEvent createUserNotificationEvent(
		long userNotificationEventId) {
		return _userNotificationEventLocalService.createUserNotificationEvent(userNotificationEventId);
	}

	/**
	* Deletes the user notification event with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userNotificationEventId the primary key of the user notification event
	* @throws PortalException if a user notification event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteUserNotificationEvent(long userNotificationEventId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_userNotificationEventLocalService.deleteUserNotificationEvent(userNotificationEventId);
	}

	/**
	* Deletes the user notification event from the database. Also notifies the appropriate model listeners.
	*
	* @param userNotificationEvent the user notification event
	* @throws SystemException if a system exception occurred
	*/
	public void deleteUserNotificationEvent(
		com.liferay.portal.model.UserNotificationEvent userNotificationEvent)
		throws com.liferay.portal.kernel.exception.SystemException {
		_userNotificationEventLocalService.deleteUserNotificationEvent(userNotificationEvent);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEventLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEventLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEventLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEventLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.portal.model.UserNotificationEvent fetchUserNotificationEvent(
		long userNotificationEventId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEventLocalService.fetchUserNotificationEvent(userNotificationEventId);
	}

	/**
	* Returns the user notification event with the primary key.
	*
	* @param userNotificationEventId the primary key of the user notification event
	* @return the user notification event
	* @throws PortalException if a user notification event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.UserNotificationEvent getUserNotificationEvent(
		long userNotificationEventId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEventLocalService.getUserNotificationEvent(userNotificationEventId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEventLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the user notification events.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @return the range of user notification events
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.UserNotificationEvent> getUserNotificationEvents(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEventLocalService.getUserNotificationEvents(start,
			end);
	}

	/**
	* Returns the number of user notification events.
	*
	* @return the number of user notification events
	* @throws SystemException if a system exception occurred
	*/
	public int getUserNotificationEventsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEventLocalService.getUserNotificationEventsCount();
	}

	/**
	* Updates the user notification event in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param userNotificationEvent the user notification event
	* @return the user notification event that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.UserNotificationEvent updateUserNotificationEvent(
		com.liferay.portal.model.UserNotificationEvent userNotificationEvent)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEventLocalService.updateUserNotificationEvent(userNotificationEvent);
	}

	/**
	* Updates the user notification event in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param userNotificationEvent the user notification event
	* @param merge whether to merge the user notification event with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the user notification event that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.UserNotificationEvent updateUserNotificationEvent(
		com.liferay.portal.model.UserNotificationEvent userNotificationEvent,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEventLocalService.updateUserNotificationEvent(userNotificationEvent,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _userNotificationEventLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_userNotificationEventLocalService.setBeanIdentifier(beanIdentifier);
	}

	public com.liferay.portal.model.UserNotificationEvent addUserNotificationEvent(
		long userId,
		com.liferay.portal.kernel.notifications.NotificationEvent notificationEvent)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEventLocalService.addUserNotificationEvent(userId,
			notificationEvent);
	}

	public com.liferay.portal.model.UserNotificationEvent addUserNotificationEvent(
		long userId, java.lang.String type, long timestamp, long deliverBy,
		java.lang.String payload, boolean archived,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEventLocalService.addUserNotificationEvent(userId,
			type, timestamp, deliverBy, payload, archived, serviceContext);
	}

	public java.util.List<com.liferay.portal.model.UserNotificationEvent> addUserNotificationEvents(
		long userId,
		java.util.Collection<com.liferay.portal.kernel.notifications.NotificationEvent> notificationEvents)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEventLocalService.addUserNotificationEvents(userId,
			notificationEvents);
	}

	public void deleteUserNotificationEvent(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		_userNotificationEventLocalService.deleteUserNotificationEvent(uuid);
	}

	public void deleteUserNotificationEvents(
		java.util.Collection<java.lang.String> uuids)
		throws com.liferay.portal.kernel.exception.SystemException {
		_userNotificationEventLocalService.deleteUserNotificationEvents(uuids);
	}

	public java.util.List<com.liferay.portal.model.UserNotificationEvent> getUserNotificationEvents(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEventLocalService.getUserNotificationEvents(userId);
	}

	public java.util.List<com.liferay.portal.model.UserNotificationEvent> getUserNotificationEvents(
		long userId, boolean archived)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEventLocalService.getUserNotificationEvents(userId,
			archived);
	}

	public java.util.List<com.liferay.portal.model.UserNotificationEvent> getUserNotificationEvents(
		long userId, boolean archived, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEventLocalService.getUserNotificationEvents(userId,
			archived, start, end);
	}

	public java.util.List<com.liferay.portal.model.UserNotificationEvent> getUserNotificationEvents(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEventLocalService.getUserNotificationEvents(userId,
			start, end);
	}

	public int getUserNotificationEventsCount(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEventLocalService.getUserNotificationEventsCount(userId);
	}

	public int getUserNotificationEventsCount(long userId, boolean archived)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEventLocalService.getUserNotificationEventsCount(userId,
			archived);
	}

	public com.liferay.portal.model.UserNotificationEvent updateUserNotificationEvent(
		java.lang.String uuid, boolean archive)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEventLocalService.updateUserNotificationEvent(uuid,
			archive);
	}

	public java.util.List<com.liferay.portal.model.UserNotificationEvent> updateUserNotificationEvents(
		java.util.Collection<java.lang.String> uuids, boolean archive)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEventLocalService.updateUserNotificationEvents(uuids,
			archive);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public UserNotificationEventLocalService getWrappedUserNotificationEventLocalService() {
		return _userNotificationEventLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedUserNotificationEventLocalService(
		UserNotificationEventLocalService userNotificationEventLocalService) {
		_userNotificationEventLocalService = userNotificationEventLocalService;
	}

	public UserNotificationEventLocalService getWrappedService() {
		return _userNotificationEventLocalService;
	}

	public void setWrappedService(
		UserNotificationEventLocalService userNotificationEventLocalService) {
		_userNotificationEventLocalService = userNotificationEventLocalService;
	}

	private UserNotificationEventLocalService _userNotificationEventLocalService;
}