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
 * This class is a wrapper for {@link LayoutPrototypeService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       LayoutPrototypeService
 * @generated
 */
public class LayoutPrototypeServiceWrapper implements LayoutPrototypeService,
	ServiceWrapper<LayoutPrototypeService> {
	public LayoutPrototypeServiceWrapper(
		LayoutPrototypeService layoutPrototypeService) {
		_layoutPrototypeService = layoutPrototypeService;
	}

	public com.liferay.portal.model.LayoutPrototype addLayoutPrototype(
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.lang.String description, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutPrototypeService.addLayoutPrototype(nameMap, description,
			active);
	}

	public void deleteLayoutPrototype(long layoutPrototypeId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_layoutPrototypeService.deleteLayoutPrototype(layoutPrototypeId);
	}

	public com.liferay.portal.model.LayoutPrototype getLayoutPrototype(
		long layoutPrototypeId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutPrototypeService.getLayoutPrototype(layoutPrototypeId);
	}

	public java.util.List<com.liferay.portal.model.LayoutPrototype> search(
		long companyId, java.lang.Boolean active,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutPrototypeService.search(companyId, active, obc);
	}

	public com.liferay.portal.model.LayoutPrototype updateLayoutPrototype(
		long layoutPrototypeId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.lang.String description, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutPrototypeService.updateLayoutPrototype(layoutPrototypeId,
			nameMap, description, active);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public LayoutPrototypeService getWrappedLayoutPrototypeService() {
		return _layoutPrototypeService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedLayoutPrototypeService(
		LayoutPrototypeService layoutPrototypeService) {
		_layoutPrototypeService = layoutPrototypeService;
	}

	public LayoutPrototypeService getWrappedService() {
		return _layoutPrototypeService;
	}

	public void setWrappedService(LayoutPrototypeService layoutPrototypeService) {
		_layoutPrototypeService = layoutPrototypeService;
	}

	private LayoutPrototypeService _layoutPrototypeService;
}