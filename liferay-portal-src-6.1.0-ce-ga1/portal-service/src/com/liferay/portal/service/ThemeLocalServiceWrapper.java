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
 * This class is a wrapper for {@link ThemeLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ThemeLocalService
 * @generated
 */
public class ThemeLocalServiceWrapper implements ThemeLocalService,
	ServiceWrapper<ThemeLocalService> {
	public ThemeLocalServiceWrapper(ThemeLocalService themeLocalService) {
		_themeLocalService = themeLocalService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _themeLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_themeLocalService.setBeanIdentifier(beanIdentifier);
	}

	public com.liferay.portal.model.ColorScheme fetchColorScheme(
		long companyId, java.lang.String themeId, java.lang.String colorSchemeId) {
		return _themeLocalService.fetchColorScheme(companyId, themeId,
			colorSchemeId);
	}

	public com.liferay.portal.model.Theme fetchTheme(long companyId,
		java.lang.String themeId) {
		return _themeLocalService.fetchTheme(companyId, themeId);
	}

	public com.liferay.portal.model.ColorScheme getColorScheme(long companyId,
		java.lang.String themeId, java.lang.String colorSchemeId,
		boolean wapTheme)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _themeLocalService.getColorScheme(companyId, themeId,
			colorSchemeId, wapTheme);
	}

	public com.liferay.portal.model.Theme getTheme(long companyId,
		java.lang.String themeId, boolean wapTheme)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _themeLocalService.getTheme(companyId, themeId, wapTheme);
	}

	public java.util.List<com.liferay.portal.model.Theme> getThemes(
		long companyId) {
		return _themeLocalService.getThemes(companyId);
	}

	public java.util.List<com.liferay.portal.model.Theme> getThemes(
		long companyId, long groupId, long userId, boolean wapTheme)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _themeLocalService.getThemes(companyId, groupId, userId, wapTheme);
	}

	public java.util.List<com.liferay.portal.model.Theme> getWARThemes() {
		return _themeLocalService.getWARThemes();
	}

	public java.util.List<java.lang.String> init(
		javax.servlet.ServletContext servletContext,
		java.lang.String themesPath, boolean loadFromServletContext,
		java.lang.String[] xmls,
		com.liferay.portal.kernel.plugin.PluginPackage pluginPackage) {
		return _themeLocalService.init(servletContext, themesPath,
			loadFromServletContext, xmls, pluginPackage);
	}

	public java.util.List<java.lang.String> init(
		java.lang.String servletContextName,
		javax.servlet.ServletContext servletContext,
		java.lang.String themesPath, boolean loadFromServletContext,
		java.lang.String[] xmls,
		com.liferay.portal.kernel.plugin.PluginPackage pluginPackage) {
		return _themeLocalService.init(servletContextName, servletContext,
			themesPath, loadFromServletContext, xmls, pluginPackage);
	}

	public void uninstallThemes(java.util.List<java.lang.String> themeIds) {
		_themeLocalService.uninstallThemes(themeIds);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public ThemeLocalService getWrappedThemeLocalService() {
		return _themeLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedThemeLocalService(ThemeLocalService themeLocalService) {
		_themeLocalService = themeLocalService;
	}

	public ThemeLocalService getWrappedService() {
		return _themeLocalService;
	}

	public void setWrappedService(ThemeLocalService themeLocalService) {
		_themeLocalService = themeLocalService;
	}

	private ThemeLocalService _themeLocalService;
}