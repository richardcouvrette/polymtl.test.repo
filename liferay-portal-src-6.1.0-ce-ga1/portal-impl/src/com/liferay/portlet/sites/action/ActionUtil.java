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

package com.liferay.portlet.sites.action;

import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.model.MembershipRequest;
import com.liferay.portal.model.PortletPreferencesIds;
import com.liferay.portal.model.Team;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.MembershipRequestLocalServiceUtil;
import com.liferay.portal.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.service.TeamLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

import java.util.List;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class ActionUtil
	extends com.liferay.portlet.rolesadmin.action.ActionUtil {

	public static void copyPreferences(
			HttpServletRequest request, Layout targetLayout,
			Layout sourceLayout)
		throws Exception {

		long companyId = targetLayout.getCompanyId();

		LayoutTypePortlet sourceLayoutTypePortlet =
			(LayoutTypePortlet)sourceLayout.getLayoutType();

		List<String> sourcePortletIds = sourceLayoutTypePortlet.getPortletIds();

		for (String sourcePortletId : sourcePortletIds) {

			// Copy preference

			PortletPreferencesIds portletPreferencesIds =
				PortletPreferencesFactoryUtil.getPortletPreferencesIds(
					request, targetLayout, sourcePortletId);

			PortletPreferencesLocalServiceUtil.getPreferences(
				portletPreferencesIds);

			PortletPreferencesIds sourcePortletPreferencesIds =
				PortletPreferencesFactoryUtil.getPortletPreferencesIds(
					request, sourceLayout, sourcePortletId);

			PortletPreferences sourcePreferences =
				PortletPreferencesLocalServiceUtil.getPreferences(
					sourcePortletPreferencesIds);

			PortletPreferencesLocalServiceUtil.updatePreferences(
				portletPreferencesIds.getOwnerId(),
				portletPreferencesIds.getOwnerType(),
				portletPreferencesIds.getPlid(),
				portletPreferencesIds.getPortletId(), sourcePreferences);

			// Copy portlet setup

			PortletPreferences targetPreferences =
				PortletPreferencesLocalServiceUtil.getPreferences(
					companyId, PortletKeys.PREFS_OWNER_ID_DEFAULT,
					PortletKeys.PREFS_OWNER_TYPE_LAYOUT, targetLayout.getPlid(),
					sourcePortletId);

			sourcePreferences =
				PortletPreferencesLocalServiceUtil.getPreferences(
					companyId, PortletKeys.PREFS_OWNER_ID_DEFAULT,
					PortletKeys.PREFS_OWNER_TYPE_LAYOUT, sourceLayout.getPlid(),
					sourcePortletId);

			PortletPreferencesLocalServiceUtil.updatePreferences(
				PortletKeys.PREFS_OWNER_ID_DEFAULT,
				PortletKeys.PREFS_OWNER_TYPE_LAYOUT, targetLayout.getPlid(),
				sourcePortletId, sourcePreferences);

			String scopeType = GetterUtil.getString(
				sourcePreferences.getValue("lfrScopeType", null));

			if (Validator.isNotNull(scopeType) && scopeType.equals("layout")) {
				ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
					WebKeys.THEME_DISPLAY);

				Layout targetScopeLayout =
					LayoutLocalServiceUtil.getLayoutByUuidAndGroupId(
						targetLayout.getUuid(), targetLayout.getGroupId());

				String languageId = themeDisplay.getLanguageId();

				if (!targetScopeLayout.hasScopeGroup()) {
					GroupLocalServiceUtil.addGroup(
						themeDisplay.getUserId(), Layout.class.getName(),
						targetLayout.getPlid(),
						targetLayout.getName(languageId), null, 0, null, false,
						true, null);
				}

				String portletTitle = PortalUtil.getPortletTitle(
					sourcePortletId, languageId);

				String newPortletTitle = PortalUtil.getNewPortletTitle(
					portletTitle, String.valueOf(sourceLayout.getLayoutId()),
					targetLayout.getName(languageId));

				targetPreferences.setValue(
					"groupId", String.valueOf(targetLayout.getGroupId()));
				targetPreferences.setValue("lfrScopeType", "layout");
				targetPreferences.setValue(
					"lfrScopeLayoutUuid", targetLayout.getUuid());
				targetPreferences.setValue(
					"portletSetupTitle_" + languageId, newPortletTitle);
				targetPreferences.setValue(
					"portletSetupUseCustomTitle", Boolean.TRUE.toString());

				targetPreferences.store();
			}
		}
	}

	public static void copyPreferences(
			PortletRequest portletRequest, Layout targetLayout,
			Layout sourceLayout)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		copyPreferences(request, targetLayout, sourceLayout);
	}

	public static Group getGroup(HttpServletRequest request) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		String cmd = ParamUtil.getString(request, Constants.CMD);

		long groupId = ParamUtil.getLong(request, "groupId");

		Group group = null;

		if (groupId > 0) {
			group = GroupLocalServiceUtil.getGroup(groupId);
		}
		else if (!cmd.equals(Constants.ADD)) {
			group = themeDisplay.getScopeGroup();
		}

		request.setAttribute(WebKeys.GROUP, group);

		return group;
	}

	public static Group getGroup(PortletRequest portletRequest)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		return getGroup(request);
	}

	public static void getMembershipRequest(HttpServletRequest request)
		throws Exception {

		long membershipRequestId = ParamUtil.getLong(
			request, "membershipRequestId");

		MembershipRequest membershipRequest = null;

		if (membershipRequestId > 0) {
			membershipRequest =
				MembershipRequestLocalServiceUtil.getMembershipRequest(
					membershipRequestId);
		}

		request.setAttribute(WebKeys.MEMBERSHIP_REQUEST, membershipRequest);
	}

	public static void getMembershipRequest(PortletRequest portletRequest)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		getMembershipRequest(request);
	}

	public static void getTeam(HttpServletRequest request)
		throws Exception {

		long teamId = ParamUtil.getLong(request, "teamId");

		Team team = null;

		if (teamId > 0) {
			team = TeamLocalServiceUtil.getTeam(teamId);
		}

		request.setAttribute(WebKeys.TEAM, team);
	}

	public static void getTeam(PortletRequest portletRequest)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		getTeam(request);
	}

}