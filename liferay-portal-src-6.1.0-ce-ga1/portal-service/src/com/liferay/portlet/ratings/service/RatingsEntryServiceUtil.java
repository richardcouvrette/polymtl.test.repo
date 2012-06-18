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

package com.liferay.portlet.ratings.service;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the ratings entry remote service. This utility wraps {@link com.liferay.portlet.ratings.service.impl.RatingsEntryServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RatingsEntryService
 * @see com.liferay.portlet.ratings.service.base.RatingsEntryServiceBaseImpl
 * @see com.liferay.portlet.ratings.service.impl.RatingsEntryServiceImpl
 * @generated
 */
public class RatingsEntryServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portlet.ratings.service.impl.RatingsEntryServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static void deleteEntry(java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteEntry(className, classPK);
	}

	public static com.liferay.portlet.ratings.model.RatingsEntry updateEntry(
		java.lang.String className, long classPK, double score)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().updateEntry(className, classPK, score);
	}

	public static RatingsEntryService getService() {
		if (_service == null) {
			_service = (RatingsEntryService)PortalBeanLocatorUtil.locate(RatingsEntryService.class.getName());

			ReferenceRegistry.registerReference(RatingsEntryServiceUtil.class,
				"_service");
			MethodCache.remove(RatingsEntryService.class);
		}

		return _service;
	}

	public void setService(RatingsEntryService service) {
		MethodCache.remove(RatingsEntryService.class);

		_service = service;

		ReferenceRegistry.registerReference(RatingsEntryServiceUtil.class,
			"_service");
		MethodCache.remove(RatingsEntryService.class);
	}

	private static RatingsEntryService _service;
}