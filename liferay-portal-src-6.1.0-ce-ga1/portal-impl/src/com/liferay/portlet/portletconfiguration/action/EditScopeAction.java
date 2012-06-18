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

import com.liferay.portal.NoSuchLayoutException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Tuple;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.PortletConfigFactoryUtil;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portlet.portletconfiguration.util.PortletConfigurationUtil;

import java.util.ResourceBundle;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.ServletContext;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Jesper Weissglas
 * @author Jorge Ferrer
 * @author Hugo Huijser
 */
public class EditScopeAction extends EditConfigurationAction {

	@Override
	public void processAction(
			ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		Portlet portlet = null;

		try {
			portlet = getPortlet(actionRequest);
		}
		catch (PrincipalException pe) {
			SessionErrors.add(
				actionRequest, PrincipalException.class.getName());

			setForward(actionRequest, "portlet.portlet_configuration.error");
		}

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		if (cmd.equals(Constants.SAVE)) {
			updateScope(actionRequest, portlet);
		}

		if (SessionErrors.isEmpty(actionRequest)) {
			String portletResource = ParamUtil.getString(
				actionRequest, "portletResource");

			SessionMessages.add(
				actionRequest,
				portletConfig.getPortletName() +
					SessionMessages.KEY_SUFFIX_REFRESH_PORTLET,
				portletResource);

			SessionMessages.add(
				actionRequest,
				portletConfig.getPortletName() +
					SessionMessages.KEY_SUFFIX_UPDATED_CONFIGURATION);

			String redirect = PortalUtil.escapeRedirect(
				ParamUtil.getString(actionRequest, "redirect"));

			if (Validator.isNotNull(redirect)) {
				actionResponse.sendRedirect(redirect);
			}
		}
	}

	@Override
	public ActionForward render(
			ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws Exception {

		Portlet portlet = null;

		try {
			portlet = getPortlet(renderRequest);
		}
		catch (PrincipalException pe) {
			SessionErrors.add(
				renderRequest, PrincipalException.class.getName());

			return mapping.findForward("portlet.portlet_configuration.error");
		}

		renderResponse.setTitle(getTitle(portlet, renderRequest));

		return mapping.findForward(getForward(
			renderRequest, "portlet.portlet_configuration.edit_scope"));
	}

	protected Tuple getNewScope(ActionRequest actionRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Layout layout = themeDisplay.getLayout();

		String scopeType = ParamUtil.getString(actionRequest, "scopeType");

		long scopeGroupId = 0;
		String scopeName = null;

		if (Validator.isNull(scopeType)) {
			scopeGroupId = layout.getGroupId();
		}
		else if (scopeType.equals("company")) {
			scopeGroupId = themeDisplay.getCompanyGroupId();
			scopeName = themeDisplay.translate("global");
		}
		else if (scopeType.equals("layout")) {
			String scopeLayoutUuid = ParamUtil.getString(
				actionRequest, "scopeLayoutUuid");

			Layout scopeLayout =
				LayoutLocalServiceUtil.getLayoutByUuidAndGroupId(
					scopeLayoutUuid, layout.getGroupId());

			if (!scopeLayout.hasScopeGroup()) {
				String name = String.valueOf(scopeLayout.getPlid());

				GroupLocalServiceUtil.addGroup(
					themeDisplay.getUserId(), Layout.class.getName(),
					scopeLayout.getPlid(), name, null, 0, null, false, true,
					null);
			}

			scopeGroupId = scopeLayout.getGroupId();
			scopeName = scopeLayout.getName(themeDisplay.getLocale());
		}
		else {
			throw new IllegalArgumentException(
				"Scope type " + scopeType + " is invalid");
		}

		return new Tuple(scopeGroupId, scopeName);
	}

	protected String getOldScopeName(
			ActionRequest actionRequest, Portlet portlet)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Layout layout = themeDisplay.getLayout();

		PortletPreferences preferences =
			PortletPreferencesFactoryUtil.getLayoutPortletSetup(
				layout, portlet.getPortletId());

		String scopeType = GetterUtil.getString(
			preferences.getValue("lfrScopeType", null));

		if (Validator.isNull(scopeType)) {
			return null;
		}

		String scopeName = null;

		if (scopeType.equals("company")) {
			scopeName = themeDisplay.translate("global");
		}
		else if (scopeType.equals("layout")) {
			String scopeLayoutUuid = GetterUtil.getString(
				preferences.getValue("lfrScopeLayoutUuid", null));

			try {
				Layout scopeLayout =
					LayoutLocalServiceUtil.getLayoutByUuidAndGroupId(
						scopeLayoutUuid, layout.getGroupId());

				scopeName = scopeLayout.getName(themeDisplay.getLocale());
			}
			catch (NoSuchLayoutException nsle) {
			}
		}
		else {
			throw new IllegalArgumentException(
				"Scope type " + scopeType + " is invalid");
		}

		return scopeName;
	}

	protected String getPortletTitle(
		PortletRequest portletRequest, Portlet portlet,
		PortletPreferences preferences) {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String portletTitle = PortletConfigurationUtil.getPortletTitle(
			preferences, themeDisplay.getLanguageId());

		if (Validator.isNull(portletTitle)) {
			ServletContext servletContext =
				(ServletContext)portletRequest.getAttribute(WebKeys.CTX);

			PortletConfig portletConfig = PortletConfigFactoryUtil.create(
				portlet, servletContext);

			ResourceBundle resourceBundle = portletConfig.getResourceBundle(
				themeDisplay.getLocale());

			portletTitle = resourceBundle.getString(
				JavaConstants.JAVAX_PORTLET_TITLE);
		}

		return portletTitle;
	}

	protected void updateScope(ActionRequest actionRequest, Portlet portlet)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Layout layout = themeDisplay.getLayout();

		PortletPreferences preferences =
			PortletPreferencesFactoryUtil.getLayoutPortletSetup(
				layout, portlet.getPortletId());

		String scopeType = ParamUtil.getString(actionRequest, "scopeType");

		preferences.setValue("lfrScopeType", scopeType);

		String scopeLayoutUuid = ParamUtil.getString(
			actionRequest, "scopeLayoutUuid");

		if (!scopeType.equals("layout")) {
			scopeLayoutUuid = StringPool.BLANK;
		}

		preferences.setValue("lfrScopeLayoutUuid", scopeLayoutUuid);

		String portletTitle = getPortletTitle(
			actionRequest, portlet, preferences);

		Tuple newScopeTuple = getNewScope(actionRequest);

		long newScopeGroupId = (Long)newScopeTuple.getObject(0);

		preferences.setValue("groupId", String.valueOf(newScopeGroupId));

		String oldScopeName = getOldScopeName(actionRequest, portlet);
		String newScopeName = (String)newScopeTuple.getObject(1);

		String newPortletTitle = PortalUtil.getNewPortletTitle(
			portletTitle, oldScopeName, newScopeName);

		if (!newPortletTitle.equals(portletTitle)) {
			preferences.setValue(
				"portletSetupTitle_" + themeDisplay.getLanguageId(),
				newPortletTitle);
			preferences.setValue(
				"portletSetupUseCustomTitle", Boolean.TRUE.toString());
		}

		preferences.store();
	}

}