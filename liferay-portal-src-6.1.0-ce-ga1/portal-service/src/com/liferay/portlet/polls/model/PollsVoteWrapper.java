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

package com.liferay.portlet.polls.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PollsVote}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PollsVote
 * @generated
 */
public class PollsVoteWrapper implements PollsVote, ModelWrapper<PollsVote> {
	public PollsVoteWrapper(PollsVote pollsVote) {
		_pollsVote = pollsVote;
	}

	public Class<?> getModelClass() {
		return PollsVote.class;
	}

	public String getModelClassName() {
		return PollsVote.class.getName();
	}

	/**
	* Returns the primary key of this polls vote.
	*
	* @return the primary key of this polls vote
	*/
	public long getPrimaryKey() {
		return _pollsVote.getPrimaryKey();
	}

	/**
	* Sets the primary key of this polls vote.
	*
	* @param primaryKey the primary key of this polls vote
	*/
	public void setPrimaryKey(long primaryKey) {
		_pollsVote.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the vote ID of this polls vote.
	*
	* @return the vote ID of this polls vote
	*/
	public long getVoteId() {
		return _pollsVote.getVoteId();
	}

	/**
	* Sets the vote ID of this polls vote.
	*
	* @param voteId the vote ID of this polls vote
	*/
	public void setVoteId(long voteId) {
		_pollsVote.setVoteId(voteId);
	}

	/**
	* Returns the company ID of this polls vote.
	*
	* @return the company ID of this polls vote
	*/
	public long getCompanyId() {
		return _pollsVote.getCompanyId();
	}

	/**
	* Sets the company ID of this polls vote.
	*
	* @param companyId the company ID of this polls vote
	*/
	public void setCompanyId(long companyId) {
		_pollsVote.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this polls vote.
	*
	* @return the user ID of this polls vote
	*/
	public long getUserId() {
		return _pollsVote.getUserId();
	}

	/**
	* Sets the user ID of this polls vote.
	*
	* @param userId the user ID of this polls vote
	*/
	public void setUserId(long userId) {
		_pollsVote.setUserId(userId);
	}

	/**
	* Returns the user uuid of this polls vote.
	*
	* @return the user uuid of this polls vote
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsVote.getUserUuid();
	}

	/**
	* Sets the user uuid of this polls vote.
	*
	* @param userUuid the user uuid of this polls vote
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_pollsVote.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this polls vote.
	*
	* @return the user name of this polls vote
	*/
	public java.lang.String getUserName() {
		return _pollsVote.getUserName();
	}

	/**
	* Sets the user name of this polls vote.
	*
	* @param userName the user name of this polls vote
	*/
	public void setUserName(java.lang.String userName) {
		_pollsVote.setUserName(userName);
	}

	/**
	* Returns the create date of this polls vote.
	*
	* @return the create date of this polls vote
	*/
	public java.util.Date getCreateDate() {
		return _pollsVote.getCreateDate();
	}

	/**
	* Sets the create date of this polls vote.
	*
	* @param createDate the create date of this polls vote
	*/
	public void setCreateDate(java.util.Date createDate) {
		_pollsVote.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this polls vote.
	*
	* @return the modified date of this polls vote
	*/
	public java.util.Date getModifiedDate() {
		return _pollsVote.getModifiedDate();
	}

	/**
	* Sets the modified date of this polls vote.
	*
	* @param modifiedDate the modified date of this polls vote
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_pollsVote.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the question ID of this polls vote.
	*
	* @return the question ID of this polls vote
	*/
	public long getQuestionId() {
		return _pollsVote.getQuestionId();
	}

	/**
	* Sets the question ID of this polls vote.
	*
	* @param questionId the question ID of this polls vote
	*/
	public void setQuestionId(long questionId) {
		_pollsVote.setQuestionId(questionId);
	}

	/**
	* Returns the choice ID of this polls vote.
	*
	* @return the choice ID of this polls vote
	*/
	public long getChoiceId() {
		return _pollsVote.getChoiceId();
	}

	/**
	* Sets the choice ID of this polls vote.
	*
	* @param choiceId the choice ID of this polls vote
	*/
	public void setChoiceId(long choiceId) {
		_pollsVote.setChoiceId(choiceId);
	}

	/**
	* Returns the vote date of this polls vote.
	*
	* @return the vote date of this polls vote
	*/
	public java.util.Date getVoteDate() {
		return _pollsVote.getVoteDate();
	}

	/**
	* Sets the vote date of this polls vote.
	*
	* @param voteDate the vote date of this polls vote
	*/
	public void setVoteDate(java.util.Date voteDate) {
		_pollsVote.setVoteDate(voteDate);
	}

	public boolean isNew() {
		return _pollsVote.isNew();
	}

	public void setNew(boolean n) {
		_pollsVote.setNew(n);
	}

	public boolean isCachedModel() {
		return _pollsVote.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_pollsVote.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _pollsVote.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _pollsVote.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_pollsVote.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _pollsVote.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_pollsVote.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new PollsVoteWrapper((PollsVote)_pollsVote.clone());
	}

	public int compareTo(com.liferay.portlet.polls.model.PollsVote pollsVote) {
		return _pollsVote.compareTo(pollsVote);
	}

	@Override
	public int hashCode() {
		return _pollsVote.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portlet.polls.model.PollsVote> toCacheModel() {
		return _pollsVote.toCacheModel();
	}

	public com.liferay.portlet.polls.model.PollsVote toEscapedModel() {
		return new PollsVoteWrapper(_pollsVote.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _pollsVote.toString();
	}

	public java.lang.String toXmlString() {
		return _pollsVote.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_pollsVote.persist();
	}

	public com.liferay.portlet.polls.model.PollsChoice getChoice()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _pollsVote.getChoice();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public PollsVote getWrappedPollsVote() {
		return _pollsVote;
	}

	public PollsVote getWrappedModel() {
		return _pollsVote;
	}

	public void resetOriginalValues() {
		_pollsVote.resetOriginalValues();
	}

	private PollsVote _pollsVote;
}