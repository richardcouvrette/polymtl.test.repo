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
 * The utility for the portal remote service. This utility wraps {@link com.liferay.portal.service.impl.PortalServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PortalService
 * @see com.liferay.portal.service.base.PortalServiceBaseImpl
 * @see com.liferay.portal.service.impl.PortalServiceImpl
 * @generated
 */
public class PortalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portal.service.impl.PortalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static java.lang.String getAutoDeployDirectory()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAutoDeployDirectory();
	}

	public static int getBuildNumber() {
		return getService().getBuildNumber();
	}

	public static void testAddClassName_Rollback(
		java.lang.String classNameValue)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().testAddClassName_Rollback(classNameValue);
	}

	public static void testAddClassName_Success(java.lang.String classNameValue)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().testAddClassName_Success(classNameValue);
	}

	public static void testAddClassNameAndTestTransactionPortletBar_PortalRollback(
		java.lang.String transactionPortletBarText)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.testAddClassNameAndTestTransactionPortletBar_PortalRollback(transactionPortletBarText);
	}

	public static void testAddClassNameAndTestTransactionPortletBar_PortletRollback(
		java.lang.String transactionPortletBarText)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.testAddClassNameAndTestTransactionPortletBar_PortletRollback(transactionPortletBarText);
	}

	public static void testAddClassNameAndTestTransactionPortletBar_Success(
		java.lang.String transactionPortletBarText)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.testAddClassNameAndTestTransactionPortletBar_Success(transactionPortletBarText);
	}

	public static void testCounterIncrement_Rollback()
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().testCounterIncrement_Rollback();
	}

	public static void testDeleteClassName()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().testDeleteClassName();
	}

	public static void testGetUserId() {
		getService().testGetUserId();
	}

	public static boolean testHasClassName()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().testHasClassName();
	}

	public static PortalService getService() {
		if (_service == null) {
			_service = (PortalService)PortalBeanLocatorUtil.locate(PortalService.class.getName());

			ReferenceRegistry.registerReference(PortalServiceUtil.class,
				"_service");
			MethodCache.remove(PortalService.class);
		}

		return _service;
	}

	public void setService(PortalService service) {
		MethodCache.remove(PortalService.class);

		_service = service;

		ReferenceRegistry.registerReference(PortalServiceUtil.class, "_service");
		MethodCache.remove(PortalService.class);
	}

	private static PortalService _service;
}