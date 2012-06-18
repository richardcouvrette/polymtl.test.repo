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

package com.liferay.portlet.calendar.asset;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.PortletURLFactoryUtil;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.asset.model.BaseAssetRendererFactory;
import com.liferay.portlet.calendar.model.CalEvent;
import com.liferay.portlet.calendar.service.CalEventLocalServiceUtil;
import com.liferay.portlet.calendar.service.permission.CalEventPermission;
import com.liferay.portlet.calendar.service.permission.CalendarPermission;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Juan Fernández
 * @author Raymond Augé
 * @author Sergio González
 */
public class CalEventAssetRendererFactory extends BaseAssetRendererFactory {

	public static final String CLASS_NAME = CalEvent.class.getName();

	public static final String TYPE = "event";

	public AssetRenderer getAssetRenderer(long classPK, int type)
		throws PortalException, SystemException {

		CalEvent event = CalEventLocalServiceUtil.getEvent(classPK);

		return new CalEventAssetRenderer(event);
	}

	public String getClassName() {
		return CLASS_NAME;
	}

	public String getType() {
		return TYPE;
	}

	@Override
	public PortletURL getURLAdd(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse)
		throws PortalException, SystemException {

		HttpServletRequest request =
			liferayPortletRequest.getHttpServletRequest();

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (!CalendarPermission.contains(
				themeDisplay.getPermissionChecker(),
				themeDisplay.getScopeGroupId(), ActionKeys.ADD_EVENT)) {

			return null;
		}

		PortletURL portletURL = PortletURLFactoryUtil.create(
			request, PortletKeys.CALENDAR, getControlPanelPlid(themeDisplay),
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter("struts_action", "/calendar/edit_event");

		return portletURL;
	}

	@Override
	public boolean hasPermission(
			PermissionChecker permissionChecker, long classPK, String actionId)
		throws Exception {

		return CalEventPermission.contains(
			permissionChecker, classPK, actionId);
	}

	@Override
	public boolean isLinkable() {
		return _LINKABLE;
	}

	@Override
	protected String getIconPath(ThemeDisplay themeDisplay) {
		return themeDisplay.getPathThemeImages() + "/common/date.png";
	}

	private static final boolean _LINKABLE = true;

}