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

package com.liferay.portal.lar;

import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.lar.PortletDataHandlerKeys;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.KeyValuePair;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.PrimitiveLongList;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.Permission;
import com.liferay.portal.model.PortletConstants;
import com.liferay.portal.model.Resource;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.ResourceActionsUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.PermissionLocalServiceUtil;
import com.liferay.portal.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.permission.PortletPermissionUtil;
import com.liferay.portal.util.PropsValues;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.time.StopWatch;

/**
 * @author Brian Wing Shun Chan
 * @author Joel Kozikowski
 * @author Charles May
 * @author Raymond Augé
 * @author Jorge Ferrer
 * @author Bruno Farache
 * @author Zsigmond Rab
 * @author Douglas Wong
 */
public class PermissionExporter {

	public static final String ROLE_TEAM_PREFIX = "ROLE_TEAM_,*";

	protected Element exportGroupPermissions(
			long companyId, long groupId, String resourceName,
			String resourcePrimKey, Element parentElement, String elementName)
		throws Exception {

		Element element = parentElement.addElement(elementName);

		List<Permission> permissions =
			PermissionLocalServiceUtil.getGroupPermissions(
				groupId, companyId, resourceName,
				ResourceConstants.SCOPE_INDIVIDUAL, resourcePrimKey);

		List<String> actions = ResourceActionsUtil.getActions(permissions);

		for (String action : actions) {
			Element actionKeyElement = element.addElement("action-key");

			actionKeyElement.addText(action);
		}

		return element;
	}

	protected void exportGroupRoles(
			LayoutCache layoutCache, long companyId, long groupId,
			String resourceName, String entityName, Element parentElement)
		throws Exception {

		List<Role> roles = layoutCache.getGroupRoles_1to4(groupId);

		Element groupElement = exportRoles(
			companyId, resourceName, ResourceConstants.SCOPE_GROUP,
			String.valueOf(groupId), parentElement, entityName + "-roles",
			roles);

		if (groupElement.elements().isEmpty()) {
			parentElement.remove(groupElement);
		}
	}

	protected void exportInheritedPermissions(
			LayoutCache layoutCache, long companyId, String resourceName,
			String resourcePrimKey, Element parentElement, String entityName)
		throws Exception {

		Element entityPermissionsElement = SAXReaderUtil.createElement(
			entityName + "-permissions");

		Map<String, Long> entityMap = layoutCache.getEntityMap(
			companyId, entityName);

		for (Map.Entry<String, Long> entry : entityMap.entrySet()) {
			String name = entry.getKey();

			long entityGroupId = entry.getValue();

			Element entityElement = exportGroupPermissions(
				companyId, entityGroupId, resourceName, resourcePrimKey,
				entityPermissionsElement, entityName + "-actions");

			if (entityElement.elements().isEmpty()) {
				entityPermissionsElement.remove(entityElement);
			}
			else {
				entityElement.addAttribute("name", name);
			}
		}

		if (!entityPermissionsElement.elements().isEmpty()) {
			parentElement.add(entityPermissionsElement);
		}
	}

	protected void exportInheritedRoles(
			LayoutCache layoutCache, long companyId, long groupId,
			String resourceName, String entityName, Element parentElement)
		throws Exception {

		Element entityRolesElement = SAXReaderUtil.createElement(
			entityName + "-roles");

		Map<String, Long> entityMap = layoutCache.getEntityMap(
			companyId, entityName);

		for (Map.Entry<String, Long> entry : entityMap.entrySet()) {
			String name = entry.getKey();

			long entityGroupId = entry.getValue();

			List<Role> entityRoles = layoutCache.getGroupRoles_1to4(
				entityGroupId);

			Element entityElement = exportRoles(
				companyId, resourceName, ResourceConstants.SCOPE_GROUP,
				String.valueOf(groupId), entityRolesElement, entityName,
				entityRoles);

			if (entityElement.elements().isEmpty()) {
				entityRolesElement.remove(entityElement);
			}
			else {
				entityElement.addAttribute("name", name);
			}
		}

		if (!entityRolesElement.elements().isEmpty()) {
			parentElement.add(entityRolesElement);
		}
	}

	protected void exportLayoutPermissions(
			PortletDataContext portletDataContext, LayoutCache layoutCache,
			long companyId, long groupId, Layout layout, Element layoutElement,
			boolean exportUserPermissions)
		throws Exception {

		String resourceName = Layout.class.getName();
		String resourcePrimKey = String.valueOf(layout.getPlid());

		Element permissionsElement = layoutElement.addElement("permissions");

		if (PropsValues.PERMISSIONS_USER_CHECK_ALGORITHM == 5) {
			exportPermissions_5(
				layoutCache, companyId, groupId, resourceName, resourcePrimKey,
				permissionsElement, false);
		}
		else if (PropsValues.PERMISSIONS_USER_CHECK_ALGORITHM == 6) {
			exportPermissions_6(
				layoutCache, companyId, groupId, resourceName, resourcePrimKey,
				permissionsElement, false);
		}
		else {
			exportPermissions_1to4(
				layoutCache, companyId, groupId, resourceName, resourcePrimKey,
				permissionsElement, exportUserPermissions);
		}
	}

	protected void exportLayoutRoles(
			LayoutCache layoutCache, long companyId, long groupId,
			Element rolesElement)
		throws Exception {

		String resourceName = Layout.class.getName();

		exportGroupRoles(
			layoutCache, companyId, groupId, resourceName, "community",
			rolesElement);

		exportUserRoles(
			layoutCache, companyId, groupId, resourceName, rolesElement);

		exportInheritedRoles(
			layoutCache, companyId, groupId, resourceName, "organization",
			rolesElement);

		exportInheritedRoles(
			layoutCache, companyId, groupId, resourceName, "user-group",
			rolesElement);
	}

	protected void exportPermissions_1to4(
			LayoutCache layoutCache, long companyId, long groupId,
			String resourceName, String resourcePrimKey,
			Element permissionsElement, boolean exportUserPermissions)
		throws Exception {

		Group guestGroup = GroupLocalServiceUtil.getGroup(
			companyId, GroupConstants.GUEST);

		exportGroupPermissions(
			companyId, groupId, resourceName, resourcePrimKey,
			permissionsElement, "community-actions");

		if (groupId != guestGroup.getGroupId()) {
			exportGroupPermissions(
				companyId, guestGroup.getGroupId(), resourceName,
				resourcePrimKey, permissionsElement, "guest-actions");
		}

		if (exportUserPermissions) {
			exportUserPermissions(
				layoutCache, companyId, groupId, resourceName, resourcePrimKey,
				permissionsElement);
		}

		exportInheritedPermissions(
			layoutCache, companyId, resourceName, resourcePrimKey,
			permissionsElement, "organization");

		exportInheritedPermissions(
			layoutCache, companyId, resourceName, resourcePrimKey,
			permissionsElement, "user-group");
	}

	protected void exportPermissions_5(
			LayoutCache layoutCache, long companyId, long groupId,
			String resourceName, String resourcePrimKey,
			Element permissionsElement, boolean portletActions)
		throws Exception {

		Resource resource = layoutCache.getResource(
			companyId, groupId, resourceName,
			ResourceConstants.SCOPE_INDIVIDUAL, resourcePrimKey,
			portletActions);

		List<Role> roles = layoutCache.getGroupRoles_5(groupId, resourceName);

		for (Role role : roles) {
			if (role.getName().equals(RoleConstants.ADMINISTRATOR)) {
				continue;
			}

			Element roleElement = permissionsElement.addElement("role");

			roleElement.addAttribute("name", role.getName());
			roleElement.addAttribute("description", role.getDescription());
			roleElement.addAttribute("type", String.valueOf(role.getType()));

			List<Permission> permissions =
				PermissionLocalServiceUtil.getRolePermissions(
					role.getRoleId(), resource.getResourceId());

			List<String> actions = ResourceActionsUtil.getActions(permissions);

			for (String action : actions) {
				Element actionKeyElement = roleElement.addElement("action-key");

				actionKeyElement.addText(action);
			}
		}
	}

	protected void exportPermissions_6(
			LayoutCache layoutCache, long companyId, long groupId,
			String resourceName, String resourcePrimKey,
			Element permissionsElement, boolean portletActions)
		throws Exception {

		List<Role> roles = layoutCache.getGroupRoles_5(groupId, resourceName);

		List<String> actionIds = null;

		if (portletActions) {
			actionIds = ResourceActionsUtil.getPortletResourceActions(
				resourceName);
		}
		else {
			actionIds = ResourceActionsUtil.getModelResourceActions(
				resourceName);
		}

		if (actionIds.isEmpty()) {
			return;
		}

		PrimitiveLongList roleIds = new PrimitiveLongList(roles.size());
		Map<Long, Role> roleIdsToRoles = new HashMap<Long, Role>();

		for (Role role : roles) {
			String name = role.getName();

			if (name.equals(RoleConstants.ADMINISTRATOR)) {
				continue;
			}

			roleIds.add(role.getRoleId());
			roleIdsToRoles.put(role.getRoleId(), role);
		}

		Map<Long, Set<String>> roleIdsToActionIds =
			ResourcePermissionLocalServiceUtil.
				getAvailableResourcePermissionActionIds(
					companyId, resourceName, ResourceConstants.SCOPE_INDIVIDUAL,
					resourcePrimKey, roleIds.getArray(), actionIds);

		for (Role role : roleIdsToRoles.values()) {
			Set<String> availableActionIds = roleIdsToActionIds.get(
				role.getRoleId());

			Element roleElement = permissionsElement.addElement("role");

			roleElement.addAttribute("name", role.getName());
			roleElement.addAttribute("description", role.getDescription());
			roleElement.addAttribute("type", String.valueOf(role.getType()));

			if ((availableActionIds == null) || availableActionIds.isEmpty()) {
				continue;
			}

			for (String action : availableActionIds) {
				Element actionKeyElement = roleElement.addElement("action-key");

				actionKeyElement.addText(action);
			}
		}
	}

	protected void exportPortletDataPermissions(
			PortletDataContext portletDataContext)
		throws Exception {

		Document document = SAXReaderUtil.createDocument();

		Element rootElement = document.addElement("portlet-data-permissions");

		Map<String, List<KeyValuePair>> permissionsMap =
			portletDataContext.getPermissions();

		for (Map.Entry<String, List<KeyValuePair>> entry :
				permissionsMap.entrySet()) {

			String[] permissionParts = StringUtil.split(
				entry.getKey(), CharPool.POUND);

			String resourceName = permissionParts[0];
			long resourcePK = GetterUtil.getLong(permissionParts[1]);

			Element portletDataElement = rootElement.addElement("portlet-data");

			portletDataElement.addAttribute("resource-name", resourceName);
			portletDataElement.addAttribute(
				"resource-pk", String.valueOf(resourcePK));

			List<KeyValuePair> permissions = entry.getValue();

			for (KeyValuePair permission : permissions) {
				String roleName = permission.getKey();
				String actions = permission.getValue();

				Element permissionsElement = portletDataElement.addElement(
					"permissions");

				permissionsElement.addAttribute("role-name", roleName);
				permissionsElement.addAttribute("actions", actions);
			}
		}

		portletDataContext.addZipEntry(
			portletDataContext.getRootPath() + "/portlet-data-permissions.xml",
			document.formattedString());
	}

	protected void exportPortletPermissions(
			PortletDataContext portletDataContext, LayoutCache layoutCache,
			String portletId, Layout layout, Element portletElement)
		throws Exception {

		long companyId = portletDataContext.getCompanyId();
		long groupId = portletDataContext.getGroupId();

		String resourceName = PortletConstants.getRootPortletId(portletId);
		String resourcePrimKey = PortletPermissionUtil.getPrimaryKey(
			layout.getPlid(), portletId);

		Element permissionsElement = portletElement.addElement("permissions");

		if (PropsValues.PERMISSIONS_USER_CHECK_ALGORITHM == 5) {
			exportPermissions_5(
				layoutCache, companyId, groupId, resourceName, resourcePrimKey,
				permissionsElement, true);
		}
		else if (PropsValues.PERMISSIONS_USER_CHECK_ALGORITHM == 6) {
			exportPermissions_6(
				layoutCache, companyId, groupId, resourceName, resourcePrimKey,
				permissionsElement, true);
		}
		else {
			boolean exportUserPermissions = MapUtil.getBoolean(
				portletDataContext.getParameterMap(),
				PortletDataHandlerKeys.USER_PERMISSIONS);

			exportPermissions_1to4(
				layoutCache, companyId, groupId, resourceName, resourcePrimKey,
				permissionsElement, exportUserPermissions);

			Element rolesElement = portletElement.addElement("roles");

			exportPortletRoles(
				layoutCache, companyId, groupId, portletId, rolesElement);
		}
	}

	protected void exportPortletRoles(
			LayoutCache layoutCache, long companyId, long groupId,
			String portletId, Element rolesElement)
		throws Exception {

		String resourceName = PortletConstants.getRootPortletId(portletId);

		Element portletElement = rolesElement.addElement("portlet");

		portletElement.addAttribute("portlet-id", portletId);

		exportGroupRoles(
			layoutCache, companyId, groupId, resourceName, "community",
			portletElement);

		exportUserRoles(
			layoutCache, companyId, groupId, resourceName, portletElement);

		exportInheritedRoles(
			layoutCache, companyId, groupId, resourceName, "organization",
			portletElement);

		exportInheritedRoles(
			layoutCache, companyId, groupId, resourceName, "user-group",
			portletElement);

		if (portletElement.elements().isEmpty()) {
			rolesElement.remove(portletElement);
		}
	}

	protected Element exportRoles(
			long companyId, String resourceName, int scope,
			String resourcePrimKey, Element parentElement, String elName,
			List<Role> roles)
		throws Exception {

		Element element = parentElement.addElement(elName);

		Map<String, List<String>> resourceRoles =
			RoleLocalServiceUtil.getResourceRoles(
				companyId, resourceName, scope, resourcePrimKey);

		for (Map.Entry<String, List<String>> entry : resourceRoles.entrySet()) {
			String roleName = entry.getKey();

			if (!hasRole(roles, roleName)) {
				continue;
			}

			Element roleElement = element.addElement("role");

			roleElement.addAttribute("name", roleName);

			List<String> actions = entry.getValue();

			for (String action : actions) {
				Element actionKeyElement = roleElement.addElement("action-key");

				actionKeyElement.addText(action);
				actionKeyElement.addAttribute("scope", String.valueOf(scope));
			}
		}

		return element;
	}

	protected void exportUserPermissions(
			LayoutCache layoutCache, long companyId, long groupId,
			String resourceName, String resourcePrimKey, Element parentElement)
		throws Exception {

		StopWatch stopWatch = null;

		if (_log.isDebugEnabled()) {
			stopWatch = new StopWatch();

			stopWatch.start();
		}

		Element userPermissionsElement = SAXReaderUtil.createElement(
			"user-permissions");

		List<User> users = layoutCache.getGroupUsers(groupId);

		for (User user : users) {
			String uuid = user.getUuid();

			Element userActionsElement = SAXReaderUtil.createElement(
				"user-actions");

			List<Permission> permissions =
				PermissionLocalServiceUtil.getUserPermissions(
					user.getUserId(), companyId, resourceName,
					ResourceConstants.SCOPE_INDIVIDUAL, resourcePrimKey);

			List<String> actions = ResourceActionsUtil.getActions(permissions);

			for (String action : actions) {
				Element actionKeyElement = userActionsElement.addElement(
					"action-key");

				actionKeyElement.addText(action);
			}

			if (!userActionsElement.elements().isEmpty()) {
				userActionsElement.addAttribute("uuid", uuid);
				userPermissionsElement.add(userActionsElement);
			}
		}

		if (!userPermissionsElement.elements().isEmpty()) {
			parentElement.add(userPermissionsElement);
		}

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Export user permissions for {" + resourceName + ", " +
					resourcePrimKey + "} with " + users.size() +
						" users takes " + stopWatch.getTime() + " ms");
		}
	}

	protected void exportUserRoles(
			LayoutCache layoutCache, long companyId, long groupId,
			String resourceName, Element parentElement)
		throws Exception {

		Element userRolesElement = SAXReaderUtil.createElement("user-roles");

		List<User> users = layoutCache.getGroupUsers(groupId);

		for (User user : users) {
			long userId = user.getUserId();
			String uuid = user.getUuid();

			List<Role> userRoles = layoutCache.getUserRoles(userId);

			Element userElement = exportRoles(
				companyId, resourceName, ResourceConstants.SCOPE_GROUP,
				String.valueOf(groupId), userRolesElement, "user", userRoles);

			if (userElement.elements().isEmpty()) {
				userRolesElement.remove(userElement);
			}
			else {
				userElement.addAttribute("uuid", uuid);
			}
		}

		if (!userRolesElement.elements().isEmpty()) {
			parentElement.add(userRolesElement);
		}
	}

	protected boolean hasRole(List<Role> roles, String roleName) {
		if ((roles == null) || (roles.size() == 0)) {
			return false;
		}

		for (Role role : roles) {
			if (roleName.equals(role.getName())) {
				return true;
			}
		}

		return false;
	}

	private static Log _log = LogFactoryUtil.getLog(PermissionExporter.class);

}