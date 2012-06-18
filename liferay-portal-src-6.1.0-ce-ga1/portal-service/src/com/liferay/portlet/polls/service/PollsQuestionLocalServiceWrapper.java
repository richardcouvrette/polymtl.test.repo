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

package com.liferay.portlet.polls.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PollsQuestionLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PollsQuestionLocalService
 * @generated
 */
public class PollsQuestionLocalServiceWrapper
	implements PollsQuestionLocalService,
		ServiceWrapper<PollsQuestionLocalService> {
	public PollsQuestionLocalServiceWrapper(
		PollsQuestionLocalService pollsQuestionLocalService) {
		_pollsQuestionLocalService = pollsQuestionLocalService;
	}

	/**
	* Adds the polls question to the database. Also notifies the appropriate model listeners.
	*
	* @param pollsQuestion the polls question
	* @return the polls question that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.polls.model.PollsQuestion addPollsQuestion(
		com.liferay.portlet.polls.model.PollsQuestion pollsQuestion)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsQuestionLocalService.addPollsQuestion(pollsQuestion);
	}

	/**
	* Creates a new polls question with the primary key. Does not add the polls question to the database.
	*
	* @param questionId the primary key for the new polls question
	* @return the new polls question
	*/
	public com.liferay.portlet.polls.model.PollsQuestion createPollsQuestion(
		long questionId) {
		return _pollsQuestionLocalService.createPollsQuestion(questionId);
	}

	/**
	* Deletes the polls question with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param questionId the primary key of the polls question
	* @throws PortalException if a polls question with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deletePollsQuestion(long questionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_pollsQuestionLocalService.deletePollsQuestion(questionId);
	}

	/**
	* Deletes the polls question from the database. Also notifies the appropriate model listeners.
	*
	* @param pollsQuestion the polls question
	* @throws SystemException if a system exception occurred
	*/
	public void deletePollsQuestion(
		com.liferay.portlet.polls.model.PollsQuestion pollsQuestion)
		throws com.liferay.portal.kernel.exception.SystemException {
		_pollsQuestionLocalService.deletePollsQuestion(pollsQuestion);
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
		return _pollsQuestionLocalService.dynamicQuery(dynamicQuery);
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
		return _pollsQuestionLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _pollsQuestionLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
		return _pollsQuestionLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.portlet.polls.model.PollsQuestion fetchPollsQuestion(
		long questionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsQuestionLocalService.fetchPollsQuestion(questionId);
	}

	/**
	* Returns the polls question with the primary key.
	*
	* @param questionId the primary key of the polls question
	* @return the polls question
	* @throws PortalException if a polls question with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.polls.model.PollsQuestion getPollsQuestion(
		long questionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _pollsQuestionLocalService.getPollsQuestion(questionId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _pollsQuestionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the polls question with the UUID in the group.
	*
	* @param uuid the UUID of polls question
	* @param groupId the group id of the polls question
	* @return the polls question
	* @throws PortalException if a polls question with the UUID in the group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.polls.model.PollsQuestion getPollsQuestionByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _pollsQuestionLocalService.getPollsQuestionByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the polls questions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of polls questions
	* @param end the upper bound of the range of polls questions (not inclusive)
	* @return the range of polls questions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portlet.polls.model.PollsQuestion> getPollsQuestions(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsQuestionLocalService.getPollsQuestions(start, end);
	}

	/**
	* Returns the number of polls questions.
	*
	* @return the number of polls questions
	* @throws SystemException if a system exception occurred
	*/
	public int getPollsQuestionsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsQuestionLocalService.getPollsQuestionsCount();
	}

	/**
	* Updates the polls question in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param pollsQuestion the polls question
	* @return the polls question that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.polls.model.PollsQuestion updatePollsQuestion(
		com.liferay.portlet.polls.model.PollsQuestion pollsQuestion)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsQuestionLocalService.updatePollsQuestion(pollsQuestion);
	}

	/**
	* Updates the polls question in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param pollsQuestion the polls question
	* @param merge whether to merge the polls question with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the polls question that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.polls.model.PollsQuestion updatePollsQuestion(
		com.liferay.portlet.polls.model.PollsQuestion pollsQuestion,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsQuestionLocalService.updatePollsQuestion(pollsQuestion,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _pollsQuestionLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_pollsQuestionLocalService.setBeanIdentifier(beanIdentifier);
	}

	public com.liferay.portlet.polls.model.PollsQuestion addQuestion(
		long userId,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		int expirationDateMonth, int expirationDateDay, int expirationDateYear,
		int expirationDateHour, int expirationDateMinute, boolean neverExpire,
		java.util.List<com.liferay.portlet.polls.model.PollsChoice> choices,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _pollsQuestionLocalService.addQuestion(userId, titleMap,
			descriptionMap, expirationDateMonth, expirationDateDay,
			expirationDateYear, expirationDateHour, expirationDateMinute,
			neverExpire, choices, serviceContext);
	}

	public void addQuestionResources(long questionId,
		boolean addGroupPermissions, boolean addGuestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_pollsQuestionLocalService.addQuestionResources(questionId,
			addGroupPermissions, addGuestPermissions);
	}

	public void addQuestionResources(long questionId,
		java.lang.String[] groupPermissions, java.lang.String[] guestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_pollsQuestionLocalService.addQuestionResources(questionId,
			groupPermissions, guestPermissions);
	}

	public void addQuestionResources(
		com.liferay.portlet.polls.model.PollsQuestion question,
		boolean addGroupPermissions, boolean addGuestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_pollsQuestionLocalService.addQuestionResources(question,
			addGroupPermissions, addGuestPermissions);
	}

	public void addQuestionResources(
		com.liferay.portlet.polls.model.PollsQuestion question,
		java.lang.String[] groupPermissions, java.lang.String[] guestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_pollsQuestionLocalService.addQuestionResources(question,
			groupPermissions, guestPermissions);
	}

	public void deleteQuestion(long questionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_pollsQuestionLocalService.deleteQuestion(questionId);
	}

	public void deleteQuestion(
		com.liferay.portlet.polls.model.PollsQuestion question)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_pollsQuestionLocalService.deleteQuestion(question);
	}

	public void deleteQuestions(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_pollsQuestionLocalService.deleteQuestions(groupId);
	}

	public com.liferay.portlet.polls.model.PollsQuestion getQuestion(
		long questionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _pollsQuestionLocalService.getQuestion(questionId);
	}

	public java.util.List<com.liferay.portlet.polls.model.PollsQuestion> getQuestions(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsQuestionLocalService.getQuestions(groupId);
	}

	public java.util.List<com.liferay.portlet.polls.model.PollsQuestion> getQuestions(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsQuestionLocalService.getQuestions(groupId, start, end);
	}

	public int getQuestionsCount(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsQuestionLocalService.getQuestionsCount(groupId);
	}

	public com.liferay.portlet.polls.model.PollsQuestion updateQuestion(
		long userId, long questionId,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		int expirationDateMonth, int expirationDateDay, int expirationDateYear,
		int expirationDateHour, int expirationDateMinute, boolean neverExpire,
		java.util.List<com.liferay.portlet.polls.model.PollsChoice> choices,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _pollsQuestionLocalService.updateQuestion(userId, questionId,
			titleMap, descriptionMap, expirationDateMonth, expirationDateDay,
			expirationDateYear, expirationDateHour, expirationDateMinute,
			neverExpire, choices, serviceContext);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public PollsQuestionLocalService getWrappedPollsQuestionLocalService() {
		return _pollsQuestionLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedPollsQuestionLocalService(
		PollsQuestionLocalService pollsQuestionLocalService) {
		_pollsQuestionLocalService = pollsQuestionLocalService;
	}

	public PollsQuestionLocalService getWrappedService() {
		return _pollsQuestionLocalService;
	}

	public void setWrappedService(
		PollsQuestionLocalService pollsQuestionLocalService) {
		_pollsQuestionLocalService = pollsQuestionLocalService;
	}

	private PollsQuestionLocalService _pollsQuestionLocalService;
}