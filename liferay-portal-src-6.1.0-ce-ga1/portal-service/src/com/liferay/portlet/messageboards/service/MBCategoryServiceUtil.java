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

package com.liferay.portlet.messageboards.service;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the message boards category remote service. This utility wraps {@link com.liferay.portlet.messageboards.service.impl.MBCategoryServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MBCategoryService
 * @see com.liferay.portlet.messageboards.service.base.MBCategoryServiceBaseImpl
 * @see com.liferay.portlet.messageboards.service.impl.MBCategoryServiceImpl
 * @generated
 */
public class MBCategoryServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portlet.messageboards.service.impl.MBCategoryServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portlet.messageboards.model.MBCategory addCategory(
		long parentCategoryId, java.lang.String name,
		java.lang.String description, java.lang.String displayStyle,
		java.lang.String emailAddress, java.lang.String inProtocol,
		java.lang.String inServerName, int inServerPort, boolean inUseSSL,
		java.lang.String inUserName, java.lang.String inPassword,
		int inReadInterval, java.lang.String outEmailAddress,
		boolean outCustom, java.lang.String outServerName, int outServerPort,
		boolean outUseSSL, java.lang.String outUserName,
		java.lang.String outPassword, boolean mailingListActive,
		boolean allowAnonymousEmail,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addCategory(parentCategoryId, name, description,
			displayStyle, emailAddress, inProtocol, inServerName, inServerPort,
			inUseSSL, inUserName, inPassword, inReadInterval, outEmailAddress,
			outCustom, outServerName, outServerPort, outUseSSL, outUserName,
			outPassword, mailingListActive, allowAnonymousEmail, serviceContext);
	}

	public static void deleteCategory(long groupId, long categoryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteCategory(groupId, categoryId);
	}

	public static java.util.List<com.liferay.portlet.messageboards.model.MBCategory> getCategories(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCategories(groupId);
	}

	public static java.util.List<com.liferay.portlet.messageboards.model.MBCategory> getCategories(
		long groupId, long parentCategoryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCategories(groupId, parentCategoryId, start, end);
	}

	public static java.util.List<com.liferay.portlet.messageboards.model.MBCategory> getCategories(
		long groupId, long[] parentCategoryIds, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCategories(groupId, parentCategoryIds, start, end);
	}

	public static int getCategoriesCount(long groupId, long parentCategoryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCategoriesCount(groupId, parentCategoryId);
	}

	public static int getCategoriesCount(long groupId, long[] parentCategoryIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCategoriesCount(groupId, parentCategoryIds);
	}

	public static com.liferay.portlet.messageboards.model.MBCategory getCategory(
		long categoryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getCategory(categoryId);
	}

	public static long[] getCategoryIds(long groupId, long categoryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCategoryIds(groupId, categoryId);
	}

	public static java.util.List<java.lang.Long> getSubcategoryIds(
		java.util.List<java.lang.Long> categoryIds, long groupId,
		long categoryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSubcategoryIds(categoryIds, groupId, categoryId);
	}

	public static java.util.List<com.liferay.portlet.messageboards.model.MBCategory> getSubscribedCategories(
		long groupId, long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSubscribedCategories(groupId, userId, start, end);
	}

	public static int getSubscribedCategoriesCount(long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSubscribedCategoriesCount(groupId, userId);
	}

	public static void subscribeCategory(long groupId, long categoryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().subscribeCategory(groupId, categoryId);
	}

	public static void unsubscribeCategory(long groupId, long categoryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().unsubscribeCategory(groupId, categoryId);
	}

	public static com.liferay.portlet.messageboards.model.MBCategory updateCategory(
		long categoryId, long parentCategoryId, java.lang.String name,
		java.lang.String description, java.lang.String displayStyle,
		java.lang.String emailAddress, java.lang.String inProtocol,
		java.lang.String inServerName, int inServerPort, boolean inUseSSL,
		java.lang.String inUserName, java.lang.String inPassword,
		int inReadInterval, java.lang.String outEmailAddress,
		boolean outCustom, java.lang.String outServerName, int outServerPort,
		boolean outUseSSL, java.lang.String outUserName,
		java.lang.String outPassword, boolean mailingListActive,
		boolean allowAnonymousEmail, boolean mergeWithParentCategory,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateCategory(categoryId, parentCategoryId, name,
			description, displayStyle, emailAddress, inProtocol, inServerName,
			inServerPort, inUseSSL, inUserName, inPassword, inReadInterval,
			outEmailAddress, outCustom, outServerName, outServerPort,
			outUseSSL, outUserName, outPassword, mailingListActive,
			allowAnonymousEmail, mergeWithParentCategory, serviceContext);
	}

	public static MBCategoryService getService() {
		if (_service == null) {
			_service = (MBCategoryService)PortalBeanLocatorUtil.locate(MBCategoryService.class.getName());

			ReferenceRegistry.registerReference(MBCategoryServiceUtil.class,
				"_service");
			MethodCache.remove(MBCategoryService.class);
		}

		return _service;
	}

	public void setService(MBCategoryService service) {
		MethodCache.remove(MBCategoryService.class);

		_service = service;

		ReferenceRegistry.registerReference(MBCategoryServiceUtil.class,
			"_service");
		MethodCache.remove(MBCategoryService.class);
	}

	private static MBCategoryService _service;
}