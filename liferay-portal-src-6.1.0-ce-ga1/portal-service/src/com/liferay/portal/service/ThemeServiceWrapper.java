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
 * This class is a wrapper for {@link ThemeService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ThemeService
 * @generated
 */
public class ThemeServiceWrapper implements ThemeService,
	ServiceWrapper<ThemeService> {
	public ThemeServiceWrapper(ThemeService themeService) {
		_themeService = themeService;
	}

	public java.util.List<com.liferay.portal.model.Theme> getThemes(
		long companyId) {
		return _themeService.getThemes(companyId);
	}

	public com.liferay.portal.kernel.json.JSONArray getWARThemes() {
		return _themeService.getWARThemes();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public ThemeService getWrappedThemeService() {
		return _themeService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedThemeService(ThemeService themeService) {
		_themeService = themeService;
	}

	public ThemeService getWrappedService() {
		return _themeService;
	}

	public void setWrappedService(ThemeService themeService) {
		_themeService = themeService;
	}

	private ThemeService _themeService;
}