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

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ColorScheme;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Theme;
import com.liferay.portal.model.VirtualHost;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ThemeLocalServiceUtil;
import com.liferay.portal.service.VirtualHostLocalServiceUtil;
import com.liferay.portal.util.PrefsPropsUtil;

import java.io.IOException;

/**
 * @author Brian Wing Shun Chan
 * @author Jorge Ferrer
 */
public class LayoutSetImpl extends LayoutSetBaseImpl {

	public LayoutSetImpl() {
	}

	public Theme getTheme() throws SystemException {
		return ThemeLocalServiceUtil.getTheme(
			getCompanyId(), getThemeId(), false);
	}

	public ColorScheme getColorScheme() throws SystemException {
		return ThemeLocalServiceUtil.getColorScheme(
			getCompanyId(), getTheme().getThemeId(), getColorSchemeId(), false);
	}

	public Group getGroup() throws PortalException, SystemException {
		return GroupLocalServiceUtil.getGroup(getGroupId());
	}

	@Override
	public String getSettings() {
		if (_settingsProperties == null) {
			return super.getSettings();
		}
		else {
			return _settingsProperties.toString();
		}
	}

	public UnicodeProperties getSettingsProperties() {
		if (_settingsProperties == null) {
			_settingsProperties = new UnicodeProperties(true);

			try {
				_settingsProperties.load(super.getSettings());
			}
			catch (IOException ioe) {
				_log.error(ioe, ioe);
			}
		}

		return _settingsProperties;
	}

	public String getSettingsProperty(String key) {
		UnicodeProperties settingsProperties = getSettingsProperties();

		return settingsProperties.getProperty(key);
	}

	public String getThemeSetting(String key, String device)
		throws SystemException {

		UnicodeProperties settingsProperties = getSettingsProperties();

		String value = settingsProperties.getProperty(
			ThemeSettingImpl.namespaceProperty(device, key));

		if (value != null) {
			return value;
		}

		Theme theme = null;

		boolean controlPanel = false;

		try {
			Group group = getGroup();

			controlPanel = group.isControlPanel();
		}
		catch (Exception e) {
		}

		if (controlPanel) {
			String themeId = PrefsPropsUtil.getString(
				getCompanyId(),
				PropsKeys.CONTROL_PANEL_LAYOUT_REGULAR_THEME_ID);

			theme = ThemeLocalServiceUtil.getTheme(
				getCompanyId(), themeId, !device.equals("regular"));
		}
		else if (device.equals("regular")) {
			theme = getTheme();
		}
		else {
			theme = getWapTheme();
		}

		value = theme.getSetting(key);

		return value;
	}

	public String getVirtualHostname() {
		try {
			VirtualHost virtualHost =
				VirtualHostLocalServiceUtil.fetchVirtualHost(
					getCompanyId(), getLayoutSetId());

			if (virtualHost == null) {
				return StringPool.BLANK;
			}
			else {
				return virtualHost.getHostname();
			}
		}
		catch (Exception e) {
			return StringPool.BLANK;
		}
	}

	public Theme getWapTheme() throws SystemException {
		return ThemeLocalServiceUtil.getTheme(
			getCompanyId(), getWapThemeId(), true);
	}

	public ColorScheme getWapColorScheme() throws SystemException {
		return ThemeLocalServiceUtil.getColorScheme(
			getCompanyId(), getWapTheme().getThemeId(), getWapColorSchemeId(),
			true);
	}

	public boolean isLayoutSetPrototypeLinkActive() {
		if (isLayoutSetPrototypeLinkEnabled() &&
			Validator.isNotNull(getLayoutSetPrototypeUuid())) {

			return true;
		}

		return false;
	}

	@Override
	public void setSettings(String settings) {
		_settingsProperties = null;

		super.setSettings(settings);
	}

	public void setSettingsProperties(UnicodeProperties settingsProperties) {
		_settingsProperties = settingsProperties;

		super.setSettings(_settingsProperties.toString());
	}

	private static Log _log = LogFactoryUtil.getLog(LayoutSetImpl.class);

	private UnicodeProperties _settingsProperties;

}