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
 * The utility for the layout set branch remote service. This utility wraps {@link com.liferay.portal.service.impl.LayoutSetBranchServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutSetBranchService
 * @see com.liferay.portal.service.base.LayoutSetBranchServiceBaseImpl
 * @see com.liferay.portal.service.impl.LayoutSetBranchServiceImpl
 * @generated
 */
public class LayoutSetBranchServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portal.service.impl.LayoutSetBranchServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.model.LayoutSetBranch addLayoutSetBranch(
		long groupId, boolean privateLayout, java.lang.String name,
		java.lang.String description, boolean master,
		long copyLayoutSetBranchId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addLayoutSetBranch(groupId, privateLayout, name,
			description, master, copyLayoutSetBranchId, serviceContext);
	}

	public static void deleteLayoutSetBranch(long layoutSetBranchId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteLayoutSetBranch(layoutSetBranchId);
	}

	public static java.util.List<com.liferay.portal.model.LayoutSetBranch> getLayoutSetBranches(
		long groupId, boolean privateLayout)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getLayoutSetBranches(groupId, privateLayout);
	}

	public static com.liferay.portal.model.LayoutSetBranch mergeLayoutSetBranch(
		long layoutSetBranchId, long mergeLayoutSetBranchId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .mergeLayoutSetBranch(layoutSetBranchId,
			mergeLayoutSetBranchId, serviceContext);
	}

	public static com.liferay.portal.model.LayoutSetBranch updateLayoutSetBranch(
		long groupId, long layoutSetBranchId, java.lang.String name,
		java.lang.String description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateLayoutSetBranch(groupId, layoutSetBranchId, name,
			description, serviceContext);
	}

	public static LayoutSetBranchService getService() {
		if (_service == null) {
			_service = (LayoutSetBranchService)PortalBeanLocatorUtil.locate(LayoutSetBranchService.class.getName());

			ReferenceRegistry.registerReference(LayoutSetBranchServiceUtil.class,
				"_service");
			MethodCache.remove(LayoutSetBranchService.class);
		}

		return _service;
	}

	public void setService(LayoutSetBranchService service) {
		MethodCache.remove(LayoutSetBranchService.class);

		_service = service;

		ReferenceRegistry.registerReference(LayoutSetBranchServiceUtil.class,
			"_service");
		MethodCache.remove(LayoutSetBranchService.class);
	}

	private static LayoutSetBranchService _service;
}