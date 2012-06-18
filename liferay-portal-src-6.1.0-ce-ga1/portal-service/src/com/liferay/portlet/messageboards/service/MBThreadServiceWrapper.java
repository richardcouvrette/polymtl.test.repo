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
 * This class is a wrapper for {@link MBThreadService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       MBThreadService
 * @generated
 */
public class MBThreadServiceWrapper implements MBThreadService,
	ServiceWrapper<MBThreadService> {
	public MBThreadServiceWrapper(MBThreadService mbThreadService) {
		_mbThreadService = mbThreadService;
	}

	public void deleteThread(long threadId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_mbThreadService.deleteThread(threadId);
	}

	public java.util.List<com.liferay.portlet.messageboards.model.MBThread> getGroupThreads(
		long groupId, long userId, int status, boolean subscribed,
		boolean includeAnonymous, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadService.getGroupThreads(groupId, userId, status,
			subscribed, includeAnonymous, start, end);
	}

	public java.util.List<com.liferay.portlet.messageboards.model.MBThread> getGroupThreads(
		long groupId, long userId, int status, boolean subscribed, int start,
		int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadService.getGroupThreads(groupId, userId, status,
			subscribed, start, end);
	}

	public java.util.List<com.liferay.portlet.messageboards.model.MBThread> getGroupThreads(
		long groupId, long userId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadService.getGroupThreads(groupId, userId, status, start,
			end);
	}

	public int getGroupThreadsCount(long groupId, long userId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadService.getGroupThreadsCount(groupId, userId, status);
	}

	public int getGroupThreadsCount(long groupId, long userId, int status,
		boolean subscribed)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadService.getGroupThreadsCount(groupId, userId, status,
			subscribed);
	}

	public int getGroupThreadsCount(long groupId, long userId, int status,
		boolean subscribed, boolean includeAnonymous)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadService.getGroupThreadsCount(groupId, userId, status,
			subscribed, includeAnonymous);
	}

	public java.util.List<com.liferay.portlet.messageboards.model.MBThread> getThreads(
		long groupId, long categoryId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadService.getThreads(groupId, categoryId, status, start,
			end);
	}

	public int getThreadsCount(long groupId, long categoryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadService.getThreadsCount(groupId, categoryId, status);
	}

	public com.liferay.portal.model.Lock lockThread(long threadId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadService.lockThread(threadId);
	}

	public com.liferay.portlet.messageboards.model.MBThread moveThread(
		long categoryId, long threadId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadService.moveThread(categoryId, threadId);
	}

	public com.liferay.portlet.messageboards.model.MBThread splitThread(
		long messageId, java.lang.String subject,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mbThreadService.splitThread(messageId, subject, serviceContext);
	}

	public void unlockThread(long threadId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_mbThreadService.unlockThread(threadId);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public MBThreadService getWrappedMBThreadService() {
		return _mbThreadService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedMBThreadService(MBThreadService mbThreadService) {
		_mbThreadService = mbThreadService;
	}

	public MBThreadService getWrappedService() {
		return _mbThreadService;
	}

	public void setWrappedService(MBThreadService mbThreadService) {
		_mbThreadService = mbThreadService;
	}

	private MBThreadService _mbThreadService;
}