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

package com.liferay.portlet.wiki.service.persistence;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
public class WikiPageFinderUtil {
	public static int countByCreateDate(long nodeId, java.util.Date createDate,
		boolean before)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByCreateDate(nodeId, createDate, before);
	}

	public static int countByCreateDate(long nodeId,
		java.sql.Timestamp createDate, boolean before)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByCreateDate(nodeId, createDate, before);
	}

	public static com.liferay.portlet.wiki.model.WikiPage findByResourcePrimKey(
		long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.wiki.NoSuchPageException {
		return getFinder().findByResourcePrimKey(resourcePrimKey);
	}

	public static java.util.List<com.liferay.portlet.wiki.model.WikiPage> findByCreateDate(
		long nodeId, java.util.Date createDate, boolean before, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByCreateDate(nodeId, createDate, before, start, end);
	}

	public static java.util.List<com.liferay.portlet.wiki.model.WikiPage> findByCreateDate(
		long nodeId, java.sql.Timestamp createDate, boolean before, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByCreateDate(nodeId, createDate, before, start, end);
	}

	public static java.util.List<com.liferay.portlet.wiki.model.WikiPage> findByNoAssets()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByNoAssets();
	}

	public static WikiPageFinder getFinder() {
		if (_finder == null) {
			_finder = (WikiPageFinder)PortalBeanLocatorUtil.locate(WikiPageFinder.class.getName());

			ReferenceRegistry.registerReference(WikiPageFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(WikiPageFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(WikiPageFinderUtil.class, "_finder");
	}

	private static WikiPageFinder _finder;
}