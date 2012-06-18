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

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the journal feed remote service. This utility wraps {@link com.liferay.portlet.journal.service.impl.JournalFeedServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JournalFeedService
 * @see com.liferay.portlet.journal.service.base.JournalFeedServiceBaseImpl
 * @see com.liferay.portlet.journal.service.impl.JournalFeedServiceImpl
 * @generated
 */
public class JournalFeedServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portlet.journal.service.impl.JournalFeedServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portlet.journal.model.JournalFeed addFeed(
		long groupId, java.lang.String feedId, boolean autoFeedId,
		java.lang.String name, java.lang.String description,
		java.lang.String type, java.lang.String structureId,
		java.lang.String templateId, java.lang.String rendererTemplateId,
		int delta, java.lang.String orderByCol, java.lang.String orderByType,
		java.lang.String targetLayoutFriendlyUrl,
		java.lang.String targetPortletId, java.lang.String contentField,
		java.lang.String feedType, double feedVersion,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addFeed(groupId, feedId, autoFeedId, name, description,
			type, structureId, templateId, rendererTemplateId, delta,
			orderByCol, orderByType, targetLayoutFriendlyUrl, targetPortletId,
			contentField, feedType, feedVersion, serviceContext);
	}

	public static void deleteFeed(long groupId, long feedId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteFeed(groupId, feedId);
	}

	public static void deleteFeed(long groupId, java.lang.String feedId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteFeed(groupId, feedId);
	}

	public static com.liferay.portlet.journal.model.JournalFeed getFeed(
		long groupId, long feedId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getFeed(groupId, feedId);
	}

	public static com.liferay.portlet.journal.model.JournalFeed getFeed(
		long groupId, java.lang.String feedId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getFeed(groupId, feedId);
	}

	public static com.liferay.portlet.journal.model.JournalFeed updateFeed(
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
		return getService()
				   .updateFeed(groupId, feedId, name, description, type,
			structureId, templateId, rendererTemplateId, delta, orderByCol,
			orderByType, targetLayoutFriendlyUrl, targetPortletId,
			contentField, feedType, feedVersion, serviceContext);
	}

	public static JournalFeedService getService() {
		if (_service == null) {
			_service = (JournalFeedService)PortalBeanLocatorUtil.locate(JournalFeedService.class.getName());

			ReferenceRegistry.registerReference(JournalFeedServiceUtil.class,
				"_service");
			MethodCache.remove(JournalFeedService.class);
		}

		return _service;
	}

	public void setService(JournalFeedService service) {
		MethodCache.remove(JournalFeedService.class);

		_service = service;

		ReferenceRegistry.registerReference(JournalFeedServiceUtil.class,
			"_service");
		MethodCache.remove(JournalFeedService.class);
	}

	private static JournalFeedService _service;
}