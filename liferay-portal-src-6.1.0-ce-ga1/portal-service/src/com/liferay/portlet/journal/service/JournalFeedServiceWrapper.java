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

package com.liferay.portlet.journal.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link JournalFeedService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       JournalFeedService
 * @generated
 */
public class JournalFeedServiceWrapper implements JournalFeedService,
	ServiceWrapper<JournalFeedService> {
	public JournalFeedServiceWrapper(JournalFeedService journalFeedService) {
		_journalFeedService = journalFeedService;
	}

	public com.liferay.portlet.journal.model.JournalFeed addFeed(long groupId,
		java.lang.String feedId, boolean autoFeedId, java.lang.String name,
		java.lang.String description, java.lang.String type,
		java.lang.String structureId, java.lang.String templateId,
		java.lang.String rendererTemplateId, int delta,
		java.lang.String orderByCol, java.lang.String orderByType,
		java.lang.String targetLayoutFriendlyUrl,
		java.lang.String targetPortletId, java.lang.String contentField,
		java.lang.String feedType, double feedVersion,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _journalFeedService.addFeed(groupId, feedId, autoFeedId, name,
			description, type, structureId, templateId, rendererTemplateId,
			delta, orderByCol, orderByType, targetLayoutFriendlyUrl,
			targetPortletId, contentField, feedType, feedVersion, serviceContext);
	}

	public void deleteFeed(long groupId, long feedId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_journalFeedService.deleteFeed(groupId, feedId);
	}

	public void deleteFeed(long groupId, java.lang.String feedId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_journalFeedService.deleteFeed(groupId, feedId);
	}

	public com.liferay.portlet.journal.model.JournalFeed getFeed(long groupId,
		long feedId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _journalFeedService.getFeed(groupId, feedId);
	}

	public com.liferay.portlet.journal.model.JournalFeed getFeed(long groupId,
		java.lang.String feedId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _journalFeedService.getFeed(groupId, feedId);
	}

	public com.liferay.portlet.journal.model.JournalFeed updateFeed(
		long groupId, java.lang.String feedId, java.lang.String name,
		java.lang.String description, java.lang.String type,
		java.lang.String structureId, java.lang.String templateId,
		java.lang.String rendererTemplateId, int delta,
		java.lang.String orderByCol, java.lang.String orderByType,
		java.lang.String targetLayoutFriendlyUrl,
		java.lang.String targetPortletId, java.lang.String contentField,
		java.lang.String feedType, double feedVersion,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _journalFeedService.updateFeed(groupId, feedId, name,
			description, type, structureId, templateId, rendererTemplateId,
			delta, orderByCol, orderByType, targetLayoutFriendlyUrl,
			targetPortletId, contentField, feedType, feedVersion, serviceContext);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public JournalFeedService getWrappedJournalFeedService() {
		return _journalFeedService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedJournalFeedService(
		JournalFeedService journalFeedService) {
		_journalFeedService = journalFeedService;
	}

	public JournalFeedService getWrappedService() {
		return _journalFeedService;
	}

	public void setWrappedService(JournalFeedService journalFeedService) {
		_journalFeedService = journalFeedService;
	}

	private JournalFeedService _journalFeedService;
}