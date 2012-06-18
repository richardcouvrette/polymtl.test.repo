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

package com.liferay.portlet.documentlibrary.service.persistence;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
public class DLFileEntryFinderUtil {
	public static int countByExtraSettings()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByExtraSettings();
	}

	public static int countByG_F_S(long groupId,
		java.util.List<java.lang.Long> folderIds, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByG_F_S(groupId, folderIds, status);
	}

	public static int countByG_U_F_M_S(long groupId, long userId,
		java.util.List<java.lang.Long> folderIds, java.lang.String[] mimeTypes,
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByG_U_F_M_S(groupId, userId, folderIds, mimeTypes,
			status);
	}

	public static com.liferay.portlet.documentlibrary.model.DLFileEntry fetchByAnyImageId(
		long imageId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().fetchByAnyImageId(imageId);
	}

	public static int filterCountByG_F_S(long groupId,
		java.util.List<java.lang.Long> folderIds, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().filterCountByG_F_S(groupId, folderIds, status);
	}

	public static com.liferay.portlet.documentlibrary.model.DLFileEntry findByAnyImageId(
		long imageId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFileEntryException {
		return getFinder().findByAnyImageId(imageId);
	}

	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> findByExtraSettings(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByExtraSettings(start, end);
	}

	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> findByNoAssets()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByNoAssets();
	}

	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> findByOrphanedFileEntries()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByOrphanedFileEntries();
	}

	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> findByG_U_F_M_S(
		long groupId, long userId, java.util.List<java.lang.Long> folderIds,
		java.lang.String[] mimeTypes, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByG_U_F_M_S(groupId, userId, folderIds, mimeTypes,
			status, start, end, obc);
	}

	public static DLFileEntryFinder getFinder() {
		if (_finder == null) {
			_finder = (DLFileEntryFinder)PortalBeanLocatorUtil.locate(DLFileEntryFinder.class.getName());

			ReferenceRegistry.registerReference(DLFileEntryFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(DLFileEntryFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(DLFileEntryFinderUtil.class,
			"_finder");
	}

	private static DLFileEntryFinder _finder;
}