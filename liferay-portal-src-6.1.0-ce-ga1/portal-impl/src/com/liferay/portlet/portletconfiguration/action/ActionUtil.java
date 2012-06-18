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

package com.liferay.portlet.portletconfiguration.action;

import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.PublicRenderParameter;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portlet.portletconfiguration.util.PublicRenderParameterConfiguration;
import com.liferay.portlet.portletconfiguration.util.PublicRenderParameterIdentifierComparator;
import com.liferay.portlet.portletconfiguration.util.PublicRenderParameterIdentifierConfigurationComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;

/**
 * @author Jorge Ferrer
 */
public class ActionUtil {

	public static void getLayoutPublicRenderParameters(
			PortletRequest portletRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Set<String> identifiers = new HashSet<String>();

		Set<PublicRenderParameter> publicRenderParameters =
			new TreeSet<PublicRenderParameter>(
				new PublicRenderParameterIdentifierComparator());

		LayoutTypePortlet layoutTypePortlet =
			themeDisplay.getLayoutTypePortlet();

		List<Portlet> portlets = layoutTypePortlet.getAllPortlets();

		for (Portlet portlet : portlets) {
			for (PublicRenderParameter publicRenderParameter:
					portlet.getPublicRenderParameters()) {

				if (!identifiers.contains(
						publicRenderParameter.getIdentifier())) {

					identifiers.add(publicRenderParameter.getIdentifier());

					publicRenderParameters.add(publicRenderParameter);
				}
			}
		}

		portletRequest.setAttribute(
			WebKeys.PUBLIC_RENDER_PARAMETERS, publicRenderParameters);
	}

	public static void getPublicRenderParameterConfigurationList(
			PortletRequest portletRequest, Portlet portlet)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Layout layout = themeDisplay.getLayout();

		PortletPreferences preferences =
			PortletPreferencesFactoryUtil.getLayoutPortletSetup(
				layout, portlet.getPortletId());

		List<PublicRenderParameterConfiguration>
			publicRenderParameterConfigurations =
				new ArrayList<PublicRenderParameterConfiguration>();

		for (PublicRenderParameter publicRenderParameter:
				portlet.getPublicRenderParameters()) {

			String mappingKey =
				PublicRenderParameterConfiguration.getMappingKey(
					publicRenderParameter);
			String ignoreKey = PublicRenderParameterConfiguration.getIgnoreKey(
				publicRenderParameter);

			String mappingValue = null;
			boolean ignoreValue = false;

			if (SessionErrors.isEmpty(portletRequest)) {
				mappingValue = preferences.getValue(mappingKey, null);
				ignoreValue = GetterUtil.getBoolean(
					preferences.getValue(ignoreKey, null));
			}
			else {
				mappingValue = ParamUtil.getString(portletRequest, mappingKey);
				ignoreValue = GetterUtil.getBoolean(
					ParamUtil.getString(portletRequest, ignoreKey));
			}

			publicRenderParameterConfigurations.add(
				new PublicRenderParameterConfiguration(
					publicRenderParameter, mappingValue, ignoreValue));
		}

		Collections.sort(
			publicRenderParameterConfigurations,
			new PublicRenderParameterIdentifierConfigurationComparator());

		portletRequest.setAttribute(
			WebKeys.PUBLIC_RENDER_PARAMETER_CONFIGURATIONS,
			publicRenderParameterConfigurations);
	}

}