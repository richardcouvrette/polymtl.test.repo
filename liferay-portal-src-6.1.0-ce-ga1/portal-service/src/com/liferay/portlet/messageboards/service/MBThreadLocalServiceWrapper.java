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

package com.liferay.portlet.messageboards.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link MBThreadLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       MBThreadLocalService
 * @generated
 */
public class MBThreadLocalServiceWrapper implements MBThreadLocalService,
	ServiceWrapper<MBThreadLocalService> {
	public MBThreadLocalServiceWrapper(
		MBThreadLocalService mbThreadLocalService) {
		_mbThreadLocalService = mbThreadLocalService;
	}

	/**
	* Adds the message boards thread to the database. Also notifies the appropriate model listeners.
	*
	* @param mbThread the message boards thread
	* @return the message boards thread that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.messageboards.model.MBThread addMBThread(
		com.liferay.portlet.messageboards.model.MBThread mbThread)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadLocalService.addMBThread(mbThread);
	}

	/**
	* Creates a new message boards thread with the primary key. Does not add the message boards thread to the database.
	*
	* @param threadId the primary key for the new message boards thread
	* @return the new message boards thread
	*/
	public com.liferay.portlet.messageboards.model.MBThread createMBThread(
		long threadId) {
		return _mbThreadLocalService.createMBThread(threadId);
	}

	/**
	* Deletes the message boards thread with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param threadId the primary key of the message boards thread
	* @throws PortalException if a message boards thread with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteMBThread(long threadId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_mbThreadLocalService.deleteMBThread(threadId);
	}

	/**
	* Deletes the message boards thread from the database. Also notifies the appropriate model listeners.
	*
	* @param mbThread the message boards thread
	* @throws SystemException if a system exception occurred
	*/
	public void deleteMBThread(
		com.liferay.portlet.messageboards.model.MBThread mbThread)
		throws com.liferay.portal.kernel.exception.SystemException {
		_mbThreadLocalService.deleteMBThread(mbThread);
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
		return _mbThreadLocalService.dynamicQuery(dynamicQuery);
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
		return _mbThreadLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _mbThreadLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
		return _mbThreadLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.portlet.messageboards.model.MBThread fetchMBThread(
		long threadId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadLocalService.fetchMBThread(threadId);
	}

	/**
	* Returns the message boards thread with the primary key.
	*
	* @param threadId the primary key of the message boards thread
	* @return the message boards thread
	* @throws PortalException if a message boards thread with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.messageboards.model.MBThread getMBThread(
		long threadId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadLocalService.getMBThread(threadId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the message boards threads.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of message boards threads
	* @param end the upper bound of the range of message boards threads (not inclusive)
	* @return the range of message boards threads
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portlet.messageboards.model.MBThread> getMBThreads(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadLocalService.getMBThreads(start, end);
	}

	/**
	* Returns the number of message boards threads.
	*
	* @return the number of message boards threads
	* @throws SystemException if a system exception occurred
	*/
	public int getMBThreadsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadLocalService.getMBThreadsCount();
	}

	/**
	* Updates the message boards thread in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param mbThread the message boards thread
	* @return the message boards thread that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.messageboards.model.MBThread updateMBThread(
		com.liferay.portlet.messageboards.model.MBThread mbThread)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadLocalService.updateMBThread(mbThread);
	}

	/**
	* Updates the message boards thread in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param mbThread the message boards thread
	* @param merge whether to merge the message boards thread with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the message boards thread that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.messageboards.model.MBThread updateMBThread(
		com.liferay.portlet.messageboards.model.MBThread mbThread, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadLocalService.updateMBThread(mbThread, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _mbThreadLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_mbThreadLocalService.setBeanIdentifier(beanIdentifier);
	}

	public com.liferay.portlet.messageboards.model.MBThread addThread(
		long categoryId,
		com.liferay.portlet.messageboards.model.MBMessage message)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadLocalService.addThread(categoryId, message);
	}

	public void deleteThread(long threadId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_mbThreadLocalService.deleteThread(threadId);
	}

	public void deleteThread(
		com.liferay.portlet.messageboards.model.MBThread thread)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_mbThreadLocalService.deleteThread(thread);
	}

	public void deleteThreads(long groupId, long categoryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_mbThreadLocalService.deleteThreads(groupId, categoryId);
	}

	public com.liferay.portlet.messageboards.model.MBThread fetchThread(
		long threadId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadLocalService.fetchThread(threadId);
	}

	public int getCategoryThreadsCount(long groupId, long categoryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadLocalService.getCategoryThreadsCount(groupId,
			categoryId, status);
	}

	public java.util.List<com.liferay.portlet.messageboards.model.MBThread> getGroupThreads(
		long groupId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadLocalService.getGroupThreads(groupId, status, start, end);
	}

	public java.util.List<com.liferay.portlet.messageboards.model.MBThread> getGroupThreads(
		long groupId, long userId, int status, boolean subscribed,
		boolean includeAnonymous, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadLocalService.getGroupThreads(groupId, userId, status,
			subscribed, includeAnonymous, start, end);
	}

	public java.util.List<com.liferay.portlet.messageboards.model.MBThread> getGroupThreads(
		long groupId, long userId, int status, boolean subscribed, int start,
		int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadLocalService.getGroupThreads(groupId, userId, status,
			subscribed, start, end);
	}

	public java.util.List<com.liferay.portlet.messageboards.model.MBThread> getGroupThreads(
		long groupId, long userId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadLocalService.getGroupThreads(groupId, userId, status,
			start, end);
	}

	public int getGroupThreadsCount(long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadLocalService.getGroupThreadsCount(groupId, status);
	}

	public int getGroupThreadsCount(long groupId, long userId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadLocalService.getGroupThreadsCount(groupId, userId,
			status);
	}

	public int getGroupThreadsCount(long groupId, long userId, int status,
		boolean subscribed)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadLocalService.getGroupThreadsCount(groupId, userId,
			status, subscribed);
	}

	public int getGroupThreadsCount(long groupId, long userId, int status,
		boolean subscribed, boolean includeAnonymous)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadLocalService.getGroupThreadsCount(groupId, userId,
			status, subscribed, includeAnonymous);
	}

	public java.util.List<com.liferay.portlet.messageboards.model.MBThread> getNoAssetThreads()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadLocalService.getNoAssetThreads();
	}

	public java.util.List<com.liferay.portlet.messageboards.model.MBThread> getPriorityThreads(
		long categoryId, double priority)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadLocalService.getPriorityThreads(categoryId, priority);
	}

	public java.util.List<com.liferay.portlet.messageboards.model.MBThread> getPriorityThreads(
		long categoryId, double priority, boolean inherit)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadLocalService.getPriorityThreads(categoryId, priority,
			inherit);
	}

	public com.liferay.portlet.messageboards.model.MBThread getThread(
		long threadId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadLocalService.getThread(threadId);
	}

	public java.util.List<com.liferay.portlet.messageboards.model.MBThread> getThreads(
		long groupId, long categoryId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadLocalService.getThreads(groupId, categoryId, status,
			start, end);
	}

	public int getThreadsCount(long groupId, long categoryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadLocalService.getThreadsCount(groupId, categoryId, status);
	}

	public boolean hasAnswerMessage(long threadId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadLocalService.hasAnswerMessage(threadId);
	}

	public com.liferay.portlet.messageboards.model.MBThread incrementViewCounter(
		long threadId, int increment)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadLocalService.incrementViewCounter(threadId, increment);
	}

	public com.liferay.portlet.messageboards.model.MBThread moveThread(
		long groupId, long categoryId, long threadId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadLocalService.moveThread(groupId, categoryId, threadId);
	}

	public com.liferay.portlet.messageboards.model.MBThread splitThread(
		long messageId, java.lang.String subject,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadLocalService.splitThread(messageId, subject,
			serviceContext);
	}

	public void updateQuestion(long threadId, boolean question)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_mbThreadLocalService.updateQuestion(threadId, question);
	}

	/**
	* @deprecated {@link #incrementViewCounter(long, int)}
	*/
	public com.liferay.portlet.messageboards.model.MBThread updateThread(
		long threadId, int viewCount)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadLocalService.updateThread(threadId, viewCount);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public MBThreadLocalService getWrappedMBThreadLocalService() {
		return _mbThreadLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedMBThreadLocalService(
		MBThreadLocalService mbThreadLocalService) {
		_mbThreadLocalService = mbThreadLocalService;
	}

	public MBThreadLocalService getWrappedService() {
		return _mbThreadLocalService;
	}

	public void setWrappedService(MBThreadLocalService mbThreadLocalService) {
		_mbThreadLocalService = mbThreadLocalService;
	}

	private MBThreadLocalService _mbThreadLocalService;
}