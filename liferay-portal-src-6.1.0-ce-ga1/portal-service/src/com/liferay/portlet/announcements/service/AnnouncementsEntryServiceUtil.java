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

package com.liferay.portlet.announcements.service;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the announcements entry remote service. This utility wraps {@link com.liferay.portlet.announcements.service.impl.AnnouncementsEntryServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AnnouncementsEntryService
 * @see com.liferay.portlet.announcements.service.base.AnnouncementsEntryServiceBaseImpl
 * @see com.liferay.portlet.announcements.service.impl.AnnouncementsEntryServiceImpl
 * @generated
 */
public class AnnouncementsEntryServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portlet.announcements.service.impl.AnnouncementsEntryServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portlet.announcements.model.AnnouncementsEntry addEntry(
		long plid, long classNameId, long classPK, java.lang.String title,
		java.lang.String content, java.lang.String url, java.lang.String type,
		int displayDateMonth, int displayDateDay, int displayDateYear,
		int displayDateHour, int displayDateMinute, int expirationDateMonth,
		int expirationDateDay, int expirationDateYear, int expirationDateHour,
		int expirationDateMinute, int priority, boolean alert)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addEntry(plid, classNameId, classPK, title, content, url,
			type, displayDateMonth, displayDateDay, displayDateYear,
			displayDateHour, displayDateMinute, expirationDateMonth,
			expirationDateDay, expirationDateYear, expirationDateHour,
			expirationDateMinute, priority, alert);
	}

	public static void deleteEntry(long entryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteEntry(entryId);
	}

	public static com.liferay.portlet.announcements.model.AnnouncementsEntry updateEntry(
		long entryId, java.lang.String title, java.lang.String content,
		java.lang.String url, java.lang.String type, int displayDateMonth,
		int displayDateDay, int displayDateYear, int displayDateHour,
		int displayDateMinute, int expirationDateMonth, int expirationDateDay,
		int expirationDateYear, int expirationDateHour,
		int expirationDateMinute, int priority)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateEntry(entryId, title, content, url, type,
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, expirationDateMonth, expirationDateDay,
			expirationDateYear, expirationDateHour, expirationDateMinute,
			priority);
	}

	public static AnnouncementsEntryService getService() {
		if (_service == null) {
			_service = (AnnouncementsEntryService)PortalBeanLocatorUtil.locate(AnnouncementsEntryService.class.getName());

			ReferenceRegistry.registerReference(AnnouncementsEntryServiceUtil.class,
				"_service");
			MethodCache.remove(AnnouncementsEntryService.class);
		}

		return _service;
	}

	public void setService(AnnouncementsEntryService service) {
		MethodCache.remove(AnnouncementsEntryService.class);

		_service = service;

		ReferenceRegistry.registerReference(AnnouncementsEntryServiceUtil.class,
			"_service");
		MethodCache.remove(AnnouncementsEntryService.class);
	}

	private static AnnouncementsEntryService _service;
}