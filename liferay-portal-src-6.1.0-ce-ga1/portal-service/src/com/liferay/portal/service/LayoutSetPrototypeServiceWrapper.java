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

/**
 * <p>
 * This class is a wrapper for {@link LayoutSetPrototypeService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       LayoutSetPrototypeService
 * @generated
 */
public class LayoutSetPrototypeServiceWrapper
	implements LayoutSetPrototypeService,
		ServiceWrapper<LayoutSetPrototypeService> {
	public LayoutSetPrototypeServiceWrapper(
		LayoutSetPrototypeService layoutSetPrototypeService) {
		_layoutSetPrototypeService = layoutSetPrototypeService;
	}

	public com.liferay.portal.model.LayoutSetPrototype addLayoutSetPrototype(
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.lang.String description, boolean active,
		boolean layoutsUpdateable,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutSetPrototypeService.addLayoutSetPrototype(nameMap,
			description, active, layoutsUpdateable, serviceContext);
	}

	public void deleteLayoutSetPrototype(long layoutSetPrototypeId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_layoutSetPrototypeService.deleteLayoutSetPrototype(layoutSetPrototypeId);
	}

	public com.liferay.portal.model.LayoutSetPrototype getLayoutSetPrototype(
		long layoutSetPrototypeId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutSetPrototypeService.getLayoutSetPrototype(layoutSetPrototypeId);
	}

	public java.util.List<com.liferay.portal.model.LayoutSetPrototype> search(
		long companyId, java.lang.Boolean active,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutSetPrototypeService.search(companyId, active, obc);
	}

	public com.liferay.portal.model.LayoutSetPrototype updateLayoutSetPrototype(
		long layoutSetPrototypeId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.lang.String description, boolean active,
		boolean layoutsUpdateable,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutSetPrototypeService.updateLayoutSetPrototype(layoutSetPrototypeId,
			nameMap, description, active, layoutsUpdateable, serviceContext);
	}

	public com.liferay.portal.model.LayoutSetPrototype updateLayoutSetPrototype(
		long layoutSetPrototypeId, java.lang.String settings)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutSetPrototypeService.updateLayoutSetPrototype(layoutSetPrototypeId,
			settings);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public LayoutSetPrototypeService getWrappedLayoutSetPrototypeService() {
		return _layoutSetPrototypeService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedLayoutSetPrototypeService(
		LayoutSetPrototypeService layoutSetPrototypeService) {
		_layoutSetPrototypeService = layoutSetPrototypeService;
	}

	public LayoutSetPrototypeService getWrappedService() {
		return _layoutSetPrototypeService;
	}

	public void setWrappedService(
		LayoutSetPrototypeService layoutSetPrototypeService) {
		_layoutSetPrototypeService = layoutSetPrototypeService;
	}

	private LayoutSetPrototypeService _layoutSetPrototypeService;
}