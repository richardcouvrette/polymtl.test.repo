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

package com.liferay.portal.service;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the portlet remote service. This utility wraps {@link com.liferay.portal.service.impl.PortletServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PortletService
 * @see com.liferay.portal.service.base.PortletServiceBaseImpl
 * @see com.liferay.portal.service.impl.PortletServiceImpl
 * @generated
 */
public class PortletServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portal.service.impl.PortletServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.json.JSONArray getWARPortlets() {
		return getService().getWARPortlets();
	}

	public static com.liferay.portal.model.Portlet updatePortlet(
		long companyId, java.lang.String portletId, java.lang.String roles,
		boolean active)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().updatePortlet(companyId, portletId, roles, active);
	}

	public static PortletService getService() {
		if (_service == null) {
			_service = (PortletService)PortalBeanLocatorUtil.locate(PortletService.class.getName());

			ReferenceRegistry.registerReference(PortletServiceUtil.class,
				"_service");
			MethodCache.remove(PortletService.class);
		}

		return _service;
	}

	public void setService(PortletService service) {
		MethodCache.remove(PortletService.class);

		_service = service;

		ReferenceRegistry.registerReference(PortletServiceUtil.class, "_service");
		MethodCache.remove(PortletService.class);
	}

	private static PortletService _service;
}