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
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.PortletConstants;
import com.liferay.portal.model.Resource;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionPropagator;
import com.liferay.portal.service.PermissionServiceUtil;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.service.ResourceBlockLocalServiceUtil;
import com.liferay.portal.service.ResourceBlockServiceUtil;
import com.liferay.portal.service.ResourceLocalServiceUtil;
import com.liferay.portal.service.ResourcePermissionServiceUtil;
import com.liferay.portal.servlet.filters.cache.CacheUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PropsValues;
import com.liferay.portal.util.WebKeys;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Brian Wing Shun Chan
 * @author Connor McKay
 */
public class EditPermissionsAction extends EditConfigurationAction {

	@Override
	public void processAction(
			ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			if (cmd.equals("group_permissions")) {
				updateGroupPermissions(actionRequest);
			}
			else if (cmd.equals("guest_permissions")) {
				updateGuestPermissions(actionRequest);
			}
			else if (cmd.equals("organization_permissions")) {
				updateOrganizationPermissions(actionRequest);
			}
			else if (cmd.equals("role_permissions")) {
				updateRolePermissions(actionRequest);
			}
			else if (cmd.equals("user_group_permissions")) {
				updateUserGroupPermissions(actionRequest);
			}
			else if (cmd.equals("user_permissions")) {
				updateUserPermissions(actionRequest);
			}

			if (PropsValues.PERMISSIONS_USER_CHECK_ALGORITHM < 5) {
				String redirect = ParamUtil.getString(
					actionRequest, "permissionsRedirect");

				sendRedirect(actionRequest, actionResponse, redirect);
			}
			else {
				addSuccessMessage(actionRequest, actionResponse);
			}
		}
		catch (Exception e) {
			if (e instanceof PrincipalException) {
				SessionErrors.add(actionRequest, e.getClass().getName());

				setForward(
					actionRequest, "portlet.portlet_configuration.error");
			}
			else {
				throw e;
			}
		}
	}

	@Override
	public ActionForward render(
			ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long groupId = themeDisplay.getScopeGroupId();

		String portletResource = ParamUtil.getString(
			renderRequest, "portletResource");
		String modelResource = ParamUtil.getString(
			renderRequest, "modelResource");
		String resourcePrimKey = ParamUtil.getString(
			renderRequest, "resourcePrimKey");

		String selResource = portletResource;

		if (Validator.isNotNull(modelResource)) {
			selResource = modelResource;
		}

		try {
			PermissionServiceUtil.checkPermission(
				groupId, selResource, resourcePrimKey);
		}
		catch (PrincipalException pe) {
			SessionErrors.add(
				renderRequest, PrincipalException.class.getName());

			setForward(renderRequest, "portlet.portlet_configuration.error");
		}

		Portlet portlet = PortletLocalServiceUtil.getPortletById(
			themeDisplay.getCompanyId(), portletResource);

		if (portlet != null) {
			renderResponse.setTitle(getTitle(portlet, renderRequest));
		}

		return mapping.findForward(getForward(
			renderRequest, "portlet.portlet_configuration.edit_permissions"));
	}

	protected String[] getActionIds(
		ActionRequest actionRequest, long roleId, boolean includePreselected) {

		List<String> actionIds = getActionIdsList(
			actionRequest, roleId, includePreselected);

		return actionIds.toArray(new String[actionIds.size()]);
	}

	protected List<String> getActionIdsList(
		ActionRequest actionRequest, long roleId, boolean includePreselected) {

		List<String> actionIds = new ArrayList<String>();

		Enumeration<String> enu = actionRequest.getParameterNames();

		while (enu.hasMoreElements()) {
			String name = enu.nextElement();

			if (name.startsWith(roleId + "_ACTION_")) {
				int pos = name.indexOf("_ACTION_");

				String actionId = name.substring(pos + 8);

				actionIds.add(actionId);
			}
			else if (includePreselected &&
					 name.startsWith(roleId + "_PRESELECTED_")) {

				int pos = name.indexOf("_PRESELECTED_");

				String actionId = name.substring(pos + 13);

				actionIds.add(actionId);
			}
		}

		return actionIds;
	}

	protected void updateGroupPermissions(ActionRequest actionRequest)
		throws Exception {

		Layout layout = (Layout)actionRequest.getAttribute(WebKeys.LAYOUT);

		long resourceId = ParamUtil.getLong(actionRequest, "resourceId");
		long groupId = ParamUtil.getLong(actionRequest, "groupId");
		String[] actionIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "groupIdActionIds"));

		PermissionServiceUtil.setGroupPermissions(
			groupId, actionIds, resourceId);

		if (!layout.isPrivateLayout()) {
			Resource resource =
				ResourceLocalServiceUtil.getResource(resourceId);

			if (resource.getPrimKey().startsWith(
					layout.getPlid() + PortletConstants.LAYOUT_SEPARATOR)) {

				CacheUtil.clearCache(layout.getCompanyId());
			}
		}
	}

	protected void updateGuestPermissions(ActionRequest actionRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long resourceId = ParamUtil.getLong(actionRequest, "resourceId");
		String[] actionIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "guestActionIds"));

		PermissionServiceUtil.setUserPermissions(
			themeDisplay.getDefaultUserId(), themeDisplay.getScopeGroupId(),
			actionIds, resourceId);
	}

	protected void updateOrganizationPermissions(ActionRequest actionRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long resourceId = ParamUtil.getLong(actionRequest, "resourceId");
		long organizationId = ParamUtil.getLong(
			actionRequest, "organizationIdsPosValue");
		String[] actionIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "organizationIdActionIds"));
		//boolean organizationIntersection = ParamUtil.getBoolean(
		//	actionRequest, "organizationIntersection");

		//if (!organizationIntersection) {
			PermissionServiceUtil.setGroupPermissions(
				Organization.class.getName(), String.valueOf(organizationId),
				themeDisplay.getScopeGroupId(), actionIds, resourceId);
		/*}
		else {
			PermissionServiceUtil.setOrgGroupPermissions(
				organizationId, layout.getGroupId(), actionIds, resourceId);
		}*/
	}

	protected void updateRolePermissions(ActionRequest actionRequest)
		throws Exception {

		if (PropsValues.PERMISSIONS_USER_CHECK_ALGORITHM == 5) {
			updateRolePermissions_5(actionRequest);
		}
		else if (PropsValues.PERMISSIONS_USER_CHECK_ALGORITHM == 6) {
			updateRolePermissions_6(actionRequest);
		}
		else {
			updateRolePermissions_1to4(actionRequest);
		}
	}

	protected void updateRolePermissions_1to4(ActionRequest actionRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long resourceId = ParamUtil.getLong(actionRequest, "resourceId");
		long roleId = ParamUtil.getLong(actionRequest, "roleIdsPosValue");
		String[] actionIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "roleIdActionIds"));

		PermissionServiceUtil.setRolePermissions(
			roleId, themeDisplay.getScopeGroupId(), actionIds, resourceId);
	}

	protected void updateRolePermissions_5(ActionRequest actionRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long resourceId = ParamUtil.getLong(actionRequest, "resourceId");

		Map<Long, String[]> roleIdsToActionIds = new HashMap<Long, String[]>();

		long[] roleIds = StringUtil.split(
			ParamUtil.getString(
				actionRequest, "rolesSearchContainerPrimaryKeys"), 0L);

		for (long roleId : roleIds) {
			String[] actionIds = getActionIds(actionRequest, roleId, false);

			roleIdsToActionIds.put(roleId, actionIds);
		}

		PermissionServiceUtil.setIndividualPermissions(
			themeDisplay.getScopeGroupId(), themeDisplay.getCompanyId(),
			roleIdsToActionIds, resourceId);
	}

	protected void updateRolePermissions_6(ActionRequest actionRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String portletResource = ParamUtil.getString(
			actionRequest, "portletResource");
		String modelResource = ParamUtil.getString(
			actionRequest, "modelResource");
		long[] roleIds = StringUtil.split(
			ParamUtil.getString(
				actionRequest, "rolesSearchContainerPrimaryKeys"), 0L);

		String selResource = PortletConstants.getRootPortletId(portletResource);

		if (Validator.isNotNull(modelResource)) {
			selResource = modelResource;
		}

		String resourcePrimKey = ParamUtil.getString(
			actionRequest, "resourcePrimKey");

		Map<Long, String[]> roleIdsToActionIds = new HashMap<Long, String[]>();

		if (ResourceBlockLocalServiceUtil.isSupported(selResource)) {
			for (long roleId : roleIds) {
				List<String> actionIds = getActionIdsList(
					actionRequest, roleId, true);

				roleIdsToActionIds.put(
					roleId, actionIds.toArray(new String[actionIds.size()]));
			}

			ResourceBlockServiceUtil.setIndividualScopePermissions(
				themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(),
				selResource, GetterUtil.getLong(resourcePrimKey),
				roleIdsToActionIds);
		}
		else {
			for (long roleId : roleIds) {
				String[] actionIds = getActionIds(actionRequest, roleId, false);

				roleIdsToActionIds.put(roleId, actionIds);
			}

			ResourcePermissionServiceUtil.setIndividualResourcePermissions(
				themeDisplay.getScopeGroupId(), themeDisplay.getCompanyId(),
				selResource, resourcePrimKey, roleIdsToActionIds);
		}

		if (PropsValues.PERMISSIONS_PROPAGATION_ENABLED) {
			Portlet portlet = PortletLocalServiceUtil.getPortletById(
				themeDisplay.getCompanyId(), portletResource);

			PermissionPropagator permissionPropagator =
				portlet.getPermissionPropagatorInstance();

			if (permissionPropagator != null) {
				permissionPropagator.propagateRolePermissions(
					actionRequest, modelResource, resourcePrimKey, roleIds);
			}
		}
	}

	protected void updateUserGroupPermissions(ActionRequest actionRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long resourceId = ParamUtil.getLong(actionRequest, "resourceId");
		long userGroupId = ParamUtil.getLong(
			actionRequest, "userGroupIdsPosValue");
		String[] actionIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "userGroupIdActionIds"));

		PermissionServiceUtil.setGroupPermissions(
			UserGroup.class.getName(), String.valueOf(userGroupId),
			themeDisplay.getScopeGroupId(), actionIds, resourceId);
	}

	protected void updateUserPermissions(ActionRequest actionRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long resourceId = ParamUtil.getLong(actionRequest, "resourceId");
		long userId = ParamUtil.getLong(actionRequest, "userIdsPosValue");
		String[] actionIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "userIdActionIds"));

		PermissionServiceUtil.setUserPermissions(
			userId, themeDisplay.getScopeGroupId(), actionIds, resourceId);
	}

}