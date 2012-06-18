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

package com.liferay.portlet.mobiledevicerules.service;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the m d r rule group remote service. This utility wraps {@link com.liferay.portlet.mobiledevicerules.service.impl.MDRRuleGroupServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Edward C. Han
 * @see MDRRuleGroupService
 * @see com.liferay.portlet.mobiledevicerules.service.base.MDRRuleGroupServiceBaseImpl
 * @see com.liferay.portlet.mobiledevicerules.service.impl.MDRRuleGroupServiceImpl
 * @generated
 */
public class MDRRuleGroupServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portlet.mobiledevicerules.service.impl.MDRRuleGroupServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portlet.mobiledevicerules.model.MDRRuleGroup addRuleGroup(
		long groupId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addRuleGroup(groupId, nameMap, descriptionMap,
			serviceContext);
	}

	public static com.liferay.portlet.mobiledevicerules.model.MDRRuleGroup copyRuleGroup(
		long ruleGroupId, long groupId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().copyRuleGroup(ruleGroupId, groupId, serviceContext);
	}

	public static void deleteRuleGroup(long ruleGroupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteRuleGroup(ruleGroupId);
	}

	public static com.liferay.portlet.mobiledevicerules.model.MDRRuleGroup fetchRuleGroup(
		long ruleGroupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchRuleGroup(ruleGroupId);
	}

	public static com.liferay.portlet.mobiledevicerules.model.MDRRuleGroup getRuleGroup(
		long ruleGroupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getRuleGroup(ruleGroupId);
	}

	public static com.liferay.portlet.mobiledevicerules.model.MDRRuleGroup updateRuleGroup(
		long ruleGroupId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateRuleGroup(ruleGroupId, nameMap, descriptionMap,
			serviceContext);
	}

	public static MDRRuleGroupService getService() {
		if (_service == null) {
			_service = (MDRRuleGroupService)PortalBeanLocatorUtil.locate(MDRRuleGroupService.class.getName());

			ReferenceRegistry.registerReference(MDRRuleGroupServiceUtil.class,
				"_service");
			MethodCache.remove(MDRRuleGroupService.class);
		}

		return _service;
	}

	public void setService(MDRRuleGroupService service) {
		MethodCache.remove(MDRRuleGroupService.class);

		_service = service;

		ReferenceRegistry.registerReference(MDRRuleGroupServiceUtil.class,
			"_service");
		MethodCache.remove(MDRRuleGroupService.class);
	}

	private static MDRRuleGroupService _service;
}