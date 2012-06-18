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

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the bookmarks entry remote service. This utility wraps {@link com.liferay.portlet.bookmarks.service.impl.BookmarksEntryServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BookmarksEntryService
 * @see com.liferay.portlet.bookmarks.service.base.BookmarksEntryServiceBaseImpl
 * @see com.liferay.portlet.bookmarks.service.impl.BookmarksEntryServiceImpl
 * @generated
 */
public class BookmarksEntryServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portlet.bookmarks.service.impl.BookmarksEntryServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portlet.bookmarks.model.BookmarksEntry addEntry(
		long groupId, long folderId, java.lang.String name,
		java.lang.String url, java.lang.String description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addEntry(groupId, folderId, name, url, description,
			serviceContext);
	}

	public static void deleteEntry(long entryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteEntry(entryId);
	}

	public static java.util.List<com.liferay.portlet.bookmarks.model.BookmarksEntry> getEntries(
		long groupId, long folderId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getEntries(groupId, folderId, start, end);
	}

	public static java.util.List<com.liferay.portlet.bookmarks.model.BookmarksEntry> getEntries(
		long groupId, long folderId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getEntries(groupId, folderId, start, end, orderByComparator);
	}

	public static int getEntriesCount(long groupId, long folderId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getEntriesCount(groupId, folderId);
	}

	public static com.liferay.portlet.bookmarks.model.BookmarksEntry getEntry(
		long entryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getEntry(entryId);
	}

	public static int getFoldersEntriesCount(long groupId,
		java.util.List<java.lang.Long> folderIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getFoldersEntriesCount(groupId, folderIds);
	}

	public static java.util.List<com.liferay.portlet.bookmarks.model.BookmarksEntry> getGroupEntries(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getGroupEntries(groupId, start, end);
	}

	public static java.util.List<com.liferay.portlet.bookmarks.model.BookmarksEntry> getGroupEntries(
		long groupId, long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getGroupEntries(groupId, userId, start, end);
	}

	public static int getGroupEntriesCount(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getGroupEntriesCount(groupId);
	}

	public static int getGroupEntriesCount(long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getGroupEntriesCount(groupId, userId);
	}

	public static com.liferay.portlet.bookmarks.model.BookmarksEntry openEntry(
		long entryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().openEntry(entryId);
	}

	public static com.liferay.portlet.bookmarks.model.BookmarksEntry updateEntry(
		long entryId, long groupId, long folderId, java.lang.String name,
		java.lang.String url, java.lang.String description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateEntry(entryId, groupId, folderId, name, url,
			description, serviceContext);
	}

	public static BookmarksEntryService getService() {
		if (_service == null) {
			_service = (BookmarksEntryService)PortalBeanLocatorUtil.locate(BookmarksEntryService.class.getName());

			ReferenceRegistry.registerReference(BookmarksEntryServiceUtil.class,
				"_service");
			MethodCache.remove(BookmarksEntryService.class);
		}

		return _service;
	}

	public void setService(BookmarksEntryService service) {
		MethodCache.remove(BookmarksEntryService.class);

		_service = service;

		ReferenceRegistry.registerReference(BookmarksEntryServiceUtil.class,
			"_service");
		MethodCache.remove(BookmarksEntryService.class);
	}

	private static BookmarksEntryService _service;
}