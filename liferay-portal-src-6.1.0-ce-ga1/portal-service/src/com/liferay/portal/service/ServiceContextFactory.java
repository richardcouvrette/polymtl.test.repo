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

import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.PortletPreferencesIds;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public class ServiceContextFactory {

	public static ServiceContext getInstance(HttpServletRequest request)
		throws PortalException, SystemException {

		ServiceContext serviceContext = new ServiceContext();

		// Theme display

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (themeDisplay != null) {
			serviceContext.setCompanyId(themeDisplay.getCompanyId());
			serviceContext.setLanguageId(themeDisplay.getLanguageId());
			serviceContext.setLayoutFullURL(
				PortalUtil.getCanonicalURL(
					PortalUtil.getLayoutFullURL(themeDisplay), themeDisplay));
			serviceContext.setLayoutURL(
				PortalUtil.getCanonicalURL(
					PortalUtil.getLayoutURL(themeDisplay), themeDisplay));
			serviceContext.setPathMain(PortalUtil.getPathMain());
			serviceContext.setPlid(themeDisplay.getPlid());
			serviceContext.setPortalURL(
				PortalUtil.getCanonicalURL(
					PortalUtil.getPortalURL(request), themeDisplay));
			serviceContext.setScopeGroupId(themeDisplay.getScopeGroupId());
			serviceContext.setSignedIn(themeDisplay.isSignedIn());

			User user = themeDisplay.getUser();

			serviceContext.setUserDisplayURL(user.getDisplayURL(themeDisplay));
			serviceContext.setUserId(user.getUserId());
		}
		else {
			long companyId = PortalUtil.getCompanyId(request);

			serviceContext.setCompanyId(companyId);

			serviceContext.setPathMain(PortalUtil.getPathMain());

			User user = null;

			try {
				user = PortalUtil.getUser(request);
			}
			catch (NoSuchUserException nsue) {

				// LPS-24160

			}

			if (user != null) {
				serviceContext.setSignedIn(!user.isDefaultUser());
				serviceContext.setUserId(user.getUserId());
			}
			else {
				serviceContext.setSignedIn(false);
			}
		}

		// Attributes

		Map<String, Serializable> attributes =
			new HashMap<String, Serializable>();

		Map<String, String[]> parameters = request.getParameterMap();

		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			String name = entry.getKey();
			String[] values = entry.getValue();

			if ((values != null) && (values.length > 0)) {
				if (values.length == 1) {
					attributes.put(name, values[0]);
				}
				else {
					attributes.put(name, values);
				}
			}
		}

		serviceContext.setAttributes(attributes);

		// Command

		String cmd = ParamUtil.getString(request, Constants.CMD);

		serviceContext.setCommand(cmd);

		// Current URL

		String currentURL = PortalUtil.getCurrentURL(request);

		serviceContext.setCurrentURL(currentURL);

		// Permissions

		boolean addGroupPermissions = ParamUtil.getBoolean(
			request, "addGroupPermissions");
		boolean addGuestPermissions = ParamUtil.getBoolean(
			request, "addGuestPermissions");
		String[] groupPermissions = PortalUtil.getGroupPermissions(request);
		String[] guestPermissions = PortalUtil.getGuestPermissions(request);

		serviceContext.setAddGroupPermissions(addGroupPermissions);
		serviceContext.setAddGuestPermissions(addGuestPermissions);
		serviceContext.setGroupPermissions(groupPermissions);
		serviceContext.setGuestPermissions(guestPermissions);

		// Portlet preferences ids

		String portletId = PortalUtil.getPortletId(request);

		if (Validator.isNotNull(portletId)) {
			PortletPreferencesIds portletPreferencesIds =
				PortletPreferencesFactoryUtil.getPortletPreferencesIds(
					request, portletId);

			serviceContext.setPortletPreferencesIds(portletPreferencesIds);
		}

		// Request

		Map<String, String> headerMap = new HashMap<String, String>();

		Enumeration<String> enu = request.getHeaderNames();

		while (enu.hasMoreElements()) {
			String header = enu.nextElement();

			String value = request.getHeader(header);

			headerMap.put(header, value);
		}

		serviceContext.setHeaders(headerMap);

		serviceContext.setRemoteAddr(request.getRemoteAddr());
		serviceContext.setRemoteHost(request.getRemoteHost());

		// Asset

		Map<String, String[]> parameterMap = request.getParameterMap();

		List<Long> assetCategoryIdsList = new ArrayList<Long>();

		for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
			String name = entry.getKey();

			if (name.startsWith("assetCategoryIds")) {
				long[] assetVocabularyAssetCategoryIds = StringUtil.split(
					ParamUtil.getString(request, name), 0L);

				for (long assetCategoryId : assetVocabularyAssetCategoryIds) {
					assetCategoryIdsList.add(assetCategoryId);
				}
			}
		}

		long[] assetCategoryIds = ArrayUtil.toArray(
			assetCategoryIdsList.toArray(
				new Long[assetCategoryIdsList.size()]));
		boolean assetEntryVisible = ParamUtil.getBoolean(
			request, "assetEntryVisible", true);
		long[] assetLinkEntryIds = StringUtil.split(
			ParamUtil.getString(
				request, "assetLinkSearchContainerPrimaryKeys"), 0L);
		String[] assetTagNames = StringUtil.split(
			ParamUtil.getString(request, "assetTagNames"));

		serviceContext.setAssetCategoryIds(assetCategoryIds);
		serviceContext.setAssetEntryVisible(assetEntryVisible);
		serviceContext.setAssetLinkEntryIds(assetLinkEntryIds);
		serviceContext.setAssetTagNames(assetTagNames);

		// Workflow

		int workflowAction = ParamUtil.getInteger(
			request, "workflowAction", WorkflowConstants.ACTION_PUBLISH);

		serviceContext.setWorkflowAction(workflowAction);

		return serviceContext;
	}

	public static ServiceContext getInstance(PortletRequest portletRequest)
		throws PortalException, SystemException {

		// Theme display

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		ThemeDisplay themeDisplay =
			(ThemeDisplay)portletRequest.getAttribute(WebKeys.THEME_DISPLAY);

		if (serviceContext != null) {
			serviceContext = (ServiceContext)serviceContext.clone();
		}
		else {
			serviceContext = new ServiceContext();

			serviceContext.setCompanyId(themeDisplay.getCompanyId());
			serviceContext.setLanguageId(themeDisplay.getLanguageId());
			serviceContext.setLayoutFullURL(
				PortalUtil.getLayoutFullURL(themeDisplay));
			serviceContext.setLayoutURL(PortalUtil.getLayoutURL(themeDisplay));
			serviceContext.setPathMain(PortalUtil.getPathMain());
			serviceContext.setPlid(themeDisplay.getPlid());
			serviceContext.setPortalURL(
				PortalUtil.getPortalURL(portletRequest));
			serviceContext.setSignedIn(themeDisplay.isSignedIn());

			User user = themeDisplay.getUser();

			serviceContext.setUserDisplayURL(user.getDisplayURL(themeDisplay));
			serviceContext.setUserId(user.getUserId());
		}

		serviceContext.setScopeGroupId(themeDisplay.getScopeGroupId());

		// Attributes

		Map<String, Serializable> attributes =
			new HashMap<String, Serializable>();

		Enumeration<String> enu = portletRequest.getParameterNames();

		while (enu.hasMoreElements()) {
			String param = enu.nextElement();

			String[] values = portletRequest.getParameterValues(param);

			if ((values != null) && (values.length > 0)) {
				if (values.length == 1) {
					attributes.put(param, values[0]);
				}
				else {
					attributes.put(param, values);
				}
			}
		}

		serviceContext.setAttributes(attributes);

		// Command

		String cmd = ParamUtil.getString(portletRequest, Constants.CMD);

		serviceContext.setCommand(cmd);

		// Current URL

		String currentURL = PortalUtil.getCurrentURL(portletRequest);

		serviceContext.setCurrentURL(currentURL);

		// Permissions

		boolean addGroupPermissions = ParamUtil.getBoolean(
			portletRequest, "addGroupPermissions");
		boolean addGuestPermissions = ParamUtil.getBoolean(
			portletRequest, "addGuestPermissions");
		String[] groupPermissions = PortalUtil.getGroupPermissions(
			portletRequest);
		String[] guestPermissions = PortalUtil.getGuestPermissions(
			portletRequest);

		serviceContext.setAddGroupPermissions(addGroupPermissions);
		serviceContext.setAddGuestPermissions(addGuestPermissions);
		serviceContext.setGroupPermissions(groupPermissions);
		serviceContext.setGuestPermissions(guestPermissions);

		// Portlet preferences ids

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		String portletId = PortalUtil.getPortletId(portletRequest);

		PortletPreferencesIds portletPreferencesIds =
			PortletPreferencesFactoryUtil.getPortletPreferencesIds(
				request, portletId);

		serviceContext.setPortletPreferencesIds(portletPreferencesIds);

		// Request

		Map<String, String> headerMap = new HashMap<String, String>();

		enu = request.getHeaderNames();

		while (enu.hasMoreElements()) {
			String header = enu.nextElement();

			String value = request.getHeader(header);

			headerMap.put(header, value);
		}

		serviceContext.setHeaders(headerMap);

		serviceContext.setRemoteAddr(request.getRemoteAddr());
		serviceContext.setRemoteHost(request.getRemoteHost());

		// Asset

		Map<String, String[]> parameterMap = portletRequest.getParameterMap();

		List<Long> assetCategoryIdsList = new ArrayList<Long>();

		for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
			String name = entry.getKey();

			if (name.startsWith("assetCategoryIds")) {
				long[] assetVocabularyAssetCategoryIds = StringUtil.split(
					ParamUtil.getString(portletRequest, name), 0L);

				for (long assetCategoryId : assetVocabularyAssetCategoryIds) {
					assetCategoryIdsList.add(assetCategoryId);
				}
			}
		}

		long[] assetCategoryIds = ArrayUtil.toArray(
			assetCategoryIdsList.toArray(
				new Long[assetCategoryIdsList.size()]));
		boolean assetEntryVisible = ParamUtil.getBoolean(
			portletRequest, "assetEntryVisible", true);
		long[] assetLinkEntryIds = StringUtil.split(
			ParamUtil.getString(
				portletRequest, "assetLinkSearchContainerPrimaryKeys"), 0L);
		String[] assetTagNames = StringUtil.split(
			ParamUtil.getString(portletRequest, "assetTagNames"));

		serviceContext.setAssetCategoryIds(assetCategoryIds);
		serviceContext.setAssetEntryVisible(assetEntryVisible);
		serviceContext.setAssetLinkEntryIds(assetLinkEntryIds);
		serviceContext.setAssetTagNames(assetTagNames);

		// Workflow

		int workflowAction = ParamUtil.getInteger(
			portletRequest, "workflowAction", WorkflowConstants.ACTION_PUBLISH);

		serviceContext.setWorkflowAction(workflowAction);

		return serviceContext;
	}

	public static ServiceContext getInstance(
			String className, PortletRequest portletRequest)
		throws PortalException, SystemException {

		ServiceContext serviceContext = getInstance(portletRequest);

		// Expando

		Map<String, Serializable> expandoBridgeAttributes =
			PortalUtil.getExpandoBridgeAttributes(
				ExpandoBridgeFactoryUtil.getExpandoBridge(
					serviceContext.getCompanyId(), className),
				portletRequest);

		serviceContext.setExpandoBridgeAttributes(expandoBridgeAttributes);

		return serviceContext;
	}

}