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

package com.liferay.portlet.layoutsadmin.action;

import com.liferay.portal.events.EventsProcessorUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.model.LayoutPrototype;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.LayoutPrototypeServiceUtil;
import com.liferay.portal.service.LayoutServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.permission.LayoutPermissionUtil;
import com.liferay.portal.struts.JSONAction;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.LayoutSettings;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.sites.util.SitesUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @author Ming-Gih Lam
 * @author Hugo Huijser
 */
public class UpdateLayoutAction extends JSONAction {

	@Override
	public String getJSON(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		long plid = ParamUtil.getLong(request, "plid");

		long groupId = ParamUtil.getLong(request, "groupId");
		boolean privateLayout = ParamUtil.getBoolean(request, "privateLayout");
		long layoutId = ParamUtil.getLong(request, "layoutId");
		long parentLayoutId = ParamUtil.getLong(request, "parentLayoutId");

		Layout layout = null;

		if (plid > 0) {
			layout = LayoutLocalServiceUtil.getLayout(plid);
		}
		else if (layoutId > 0) {
			layout = LayoutLocalServiceUtil.getLayout(
				groupId, privateLayout, layoutId);
		}
		else if (parentLayoutId > 0) {
			layout = LayoutLocalServiceUtil.getLayout(
				groupId, privateLayout, parentLayoutId);
		}

		if ((layout != null) &&
			!LayoutPermissionUtil.contains(
				permissionChecker, layout, ActionKeys.UPDATE)) {

			return null;
		}

		String cmd = ParamUtil.getString(request, Constants.CMD);

		JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

		if (cmd.equals("add")) {
			String[] array = addPage(themeDisplay, request, response);

			jsonObj.put("deletable", Boolean.valueOf(array[2]));
			jsonObj.put("layoutId", array[0]);
			jsonObj.put("updateable", Boolean.valueOf(array[3]));
			jsonObj.put("url", array[1]);
		}
		else if (cmd.equals("delete")) {
			SitesUtil.deleteLayout(request, response);
		}
		else if (cmd.equals("display_order")) {
			updateDisplayOrder(request);
		}
		else if (cmd.equals("name")) {
			updateName(request);
		}
		else if (cmd.equals("parent_layout_id")) {
			updateParentLayoutId(request);
		}
		else if (cmd.equals("priority")) {
			updatePriority(request);
		}

		return jsonObj.toString();
	}

	protected String[] addPage(
			ThemeDisplay themeDisplay, HttpServletRequest request,
			HttpServletResponse response)
		throws Exception {

		String doAsUserId = ParamUtil.getString(request, "doAsUserId");
		String doAsUserLanguageId = ParamUtil.getString(
			request, "doAsUserLanguageId");

		long groupId = ParamUtil.getLong(request, "groupId");
		boolean privateLayout = ParamUtil.getBoolean(request, "privateLayout");
		long parentLayoutId = ParamUtil.getLong(request, "parentLayoutId");
		String name = ParamUtil.getString(request, "name", "New Page");
		String title = StringPool.BLANK;
		String description = StringPool.BLANK;
		String type = LayoutConstants.TYPE_PORTLET;
		boolean hidden = false;
		String friendlyURL = StringPool.BLANK;
		long layoutPrototypeId = ParamUtil.getLong(
			request, "layoutPrototypeId");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			request);

		Layout layout = null;

		if (layoutPrototypeId > 0) {
			LayoutPrototype layoutPrototype =
				LayoutPrototypeServiceUtil.getLayoutPrototype(
					layoutPrototypeId);

			serviceContext.setAttribute("layoutPrototypeLinkEnabled", true);
			serviceContext.setAttribute(
				"layoutPrototypeUuid", layoutPrototype.getUuid());

			layout = LayoutServiceUtil.addLayout(
				groupId, privateLayout, parentLayoutId, name, title,
				description, LayoutConstants.TYPE_PORTLET, false, friendlyURL,
				serviceContext);
		}
		else {
			layout = LayoutServiceUtil.addLayout(
				groupId, privateLayout, parentLayoutId, name, title,
				description, type, hidden, friendlyURL, serviceContext);
		}

		LayoutSettings layoutSettings = LayoutSettings.getInstance(layout);

		EventsProcessorUtil.process(
			PropsKeys.LAYOUT_CONFIGURATION_ACTION_UPDATE,
			layoutSettings.getConfigurationActionUpdate(), request, response);

		String layoutURL = PortalUtil.getLayoutURL(layout, themeDisplay);

		if (Validator.isNotNull(doAsUserId)) {
			layoutURL = HttpUtil.addParameter(
				layoutURL, "doAsUserId", themeDisplay.getDoAsUserId());
		}

		if (Validator.isNotNull(doAsUserLanguageId)) {
			layoutURL = HttpUtil.addParameter(
				layoutURL, "doAsUserLanguageId",
				themeDisplay.getDoAsUserLanguageId());
		}

		boolean updateable = SitesUtil.isLayoutUpdateable(layout);
		boolean deleteable = updateable && LayoutPermissionUtil.contains(
			themeDisplay.getPermissionChecker(), layout, ActionKeys.DELETE);

		return new String[] {
			String.valueOf(layout.getLayoutId()), layoutURL,
			String.valueOf(deleteable), String.valueOf(updateable)
		};
	}

	protected void updateDisplayOrder(HttpServletRequest request)
		throws Exception {

		long groupId = ParamUtil.getLong(request, "groupId");
		boolean privateLayout = ParamUtil.getBoolean(request, "privateLayout");
		long parentLayoutId = ParamUtil.getLong(request, "parentLayoutId");
		long[] layoutIds = StringUtil.split(
			ParamUtil.getString(request, "layoutIds"), 0L);

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			request);

		LayoutServiceUtil.setLayouts(
			groupId, privateLayout, parentLayoutId, layoutIds, serviceContext);
	}

	protected void updateName(HttpServletRequest request) throws Exception {
		long plid = ParamUtil.getLong(request, "plid");

		long groupId = ParamUtil.getLong(request, "groupId");
		boolean privateLayout = ParamUtil.getBoolean(request, "privateLayout");
		long layoutId = ParamUtil.getLong(request, "layoutId");
		String name = ParamUtil.getString(request, "name");
		String languageId = ParamUtil.getString(request, "languageId");

		LayoutLocalServiceUtil.updateScopedPortletNames(
			groupId, privateLayout, layoutId, name, languageId);

		if (plid <= 0) {
			LayoutServiceUtil.updateName(
				groupId, privateLayout, layoutId, name, languageId);
		}
		else {
			LayoutServiceUtil.updateName(plid, name, languageId);
		}
	}

	protected void updateParentLayoutId(HttpServletRequest request)
		throws Exception {

		long plid = ParamUtil.getLong(request, "plid");

		long parentPlid = ParamUtil.getLong(request, "parentPlid");

		long groupId = ParamUtil.getLong(request, "groupId");
		boolean privateLayout = ParamUtil.getBoolean(request, "privateLayout");
		long layoutId = ParamUtil.getLong(request, "layoutId");
		long parentLayoutId = ParamUtil.getLong(
			request, "parentLayoutId",
			LayoutConstants.DEFAULT_PARENT_LAYOUT_ID);

		if (plid <= 0) {
			LayoutServiceUtil.updateParentLayoutId(
				groupId, privateLayout, layoutId, parentLayoutId);
		}
		else {
			LayoutServiceUtil.updateParentLayoutId(plid, parentPlid);
		}

		updatePriority(request);
	}

	protected void updatePriority(HttpServletRequest request) throws Exception {
		long plid = ParamUtil.getLong(request, "plid");

		long groupId = ParamUtil.getLong(request, "groupId");
		boolean privateLayout = ParamUtil.getBoolean(request, "privateLayout");
		long layoutId = ParamUtil.getLong(request, "layoutId");
		int priority = ParamUtil.getInteger(request, "priority");

		if (plid <= 0) {
			LayoutServiceUtil.updatePriority(
				groupId, privateLayout, layoutId, priority);
		}
		else {
			LayoutServiceUtil.updatePriority(plid, priority);
		}
	}

}