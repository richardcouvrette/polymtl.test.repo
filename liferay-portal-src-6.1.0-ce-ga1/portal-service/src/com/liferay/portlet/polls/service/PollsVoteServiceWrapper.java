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
 * This class is a wrapper for {@link PollsVoteService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PollsVoteService
 * @generated
 */
public class PollsVoteServiceWrapper implements PollsVoteService,
	ServiceWrapper<PollsVoteService> {
	public PollsVoteServiceWrapper(PollsVoteService pollsVoteService) {
		_pollsVoteService = pollsVoteService;
	}

	public com.liferay.portlet.polls.model.PollsVote addVote(long questionId,
		long choiceId, com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _pollsVoteService.addVote(questionId, choiceId, serviceContext);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public PollsVoteService getWrappedPollsVoteService() {
		return _pollsVoteService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedPollsVoteService(PollsVoteService pollsVoteService) {
		_pollsVoteService = pollsVoteService;
	}

	public PollsVoteService getWrappedService() {
		return _pollsVoteService;
	}

	public void setWrappedService(PollsVoteService pollsVoteService) {
		_pollsVoteService = pollsVoteService;
	}

	private PollsVoteService _pollsVoteService;
}