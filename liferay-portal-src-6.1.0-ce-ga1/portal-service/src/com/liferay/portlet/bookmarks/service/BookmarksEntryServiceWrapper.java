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

package com.liferay.portlet.bookmarks.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link BookmarksEntryService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       BookmarksEntryService
 * @generated
 */
public class BookmarksEntryServiceWrapper implements BookmarksEntryService,
	ServiceWrapper<BookmarksEntryService> {
	public BookmarksEntryServiceWrapper(
		BookmarksEntryService bookmarksEntryService) {
		_bookmarksEntryService = bookmarksEntryService;
	}

	public com.liferay.portlet.bookmarks.model.BookmarksEntry addEntry(
		long groupId, long folderId, java.lang.String name,
		java.lang.String url, java.lang.String description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _bookmarksEntryService.addEntry(groupId, folderId, name, url,
			description, serviceContext);
	}

	public void deleteEntry(long entryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_bookmarksEntryService.deleteEntry(entryId);
	}

	public java.util.List<com.liferay.portlet.bookmarks.model.BookmarksEntry> getEntries(
		long groupId, long folderId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _bookmarksEntryService.getEntries(groupId, folderId, start, end);
	}

	public java.util.List<com.liferay.portlet.bookmarks.model.BookmarksEntry> getEntries(
		long groupId, long folderId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _bookmarksEntryService.getEntries(groupId, folderId, start, end,
			orderByComparator);
	}

	public int getEntriesCount(long groupId, long folderId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _bookmarksEntryService.getEntriesCount(groupId, folderId);
	}

	public com.liferay.portlet.bookmarks.model.BookmarksEntry getEntry(
		long entryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _bookmarksEntryService.getEntry(entryId);
	}

	public int getFoldersEntriesCount(long groupId,
		java.util.List<java.lang.Long> folderIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _bookmarksEntryService.getFoldersEntriesCount(groupId, folderIds);
	}

	public java.util.List<com.liferay.portlet.bookmarks.model.BookmarksEntry> getGroupEntries(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _bookmarksEntryService.getGroupEntries(groupId, start, end);
	}

	public java.util.List<com.liferay.portlet.bookmarks.model.BookmarksEntry> getGroupEntries(
		long groupId, long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _bookmarksEntryService.getGroupEntries(groupId, userId, start,
			end);
	}

	public int getGroupEntriesCount(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _bookmarksEntryService.getGroupEntriesCount(groupId);
	}

	public int getGroupEntriesCount(long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _bookmarksEntryService.getGroupEntriesCount(groupId, userId);
	}

	public com.liferay.portlet.bookmarks.model.BookmarksEntry openEntry(
		long entryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _bookmarksEntryService.openEntry(entryId);
	}

	public com.liferay.portlet.bookmarks.model.BookmarksEntry updateEntry(
		long entryId, long groupId, long folderId, java.lang.String name,
		java.lang.String url, java.lang.String description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _bookmarksEntryService.updateEntry(entryId, groupId, folderId,
			name, url, description, serviceContext);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public BookmarksEntryService getWrappedBookmarksEntryService() {
		return _bookmarksEntryService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedBookmarksEntryService(
		BookmarksEntryService bookmarksEntryService) {
		_bookmarksEntryService = bookmarksEntryService;
	}

	public BookmarksEntryService getWrappedService() {
		return _bookmarksEntryService;
	}

	public void setWrappedService(BookmarksEntryService bookmarksEntryService) {
		_bookmarksEntryService = bookmarksEntryService;
	}

	private BookmarksEntryService _bookmarksEntryService;
}