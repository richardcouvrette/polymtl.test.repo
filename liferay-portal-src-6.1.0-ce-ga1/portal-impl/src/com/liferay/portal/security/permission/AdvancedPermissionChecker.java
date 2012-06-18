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

package com.liferay.portal.security.permission;

import com.liferay.portal.NoSuchResourceException;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.GroupedModel;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.Permission;
import com.liferay.portal.model.PermissionedModel;
import com.liferay.portal.model.PortletConstants;
import com.liferay.portal.model.Resource;
import com.liferay.portal.model.ResourceBlockConstants;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.Team;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.security.permission.comparator.PermissionActionIdComparator;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.PermissionLocalServiceUtil;
import com.liferay.portal.service.ResourceBlockLocalServiceUtil;
import com.liferay.portal.service.ResourceLocalServiceUtil;
import com.liferay.portal.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.TeamLocalServiceUtil;
import com.liferay.portal.service.UserGroupLocalServiceUtil;
import com.liferay.portal.service.permission.PortletPermissionUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.util.UniqueList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.time.StopWatch;

/**
 * @author Charles May
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 * @author Wesley Gong
 * @author Connor McKay
 */
public class AdvancedPermissionChecker extends BasePermissionChecker {

	@Override
	public AdvancedPermissionChecker clone() {
		return new AdvancedPermissionChecker();
	}

	@Override
	public long[] getRoleIds(long userId, long groupId) {
		PermissionCheckerBag bag = null;

		try {
			bag = getUserBag(userId, groupId);
		}
		catch (Exception e) {
		}

		if (bag != null) {
			if (checkGuest) {
				Set<Long> roleIds = SetUtil.fromArray(bag.getRoleIds());

				try {
					PermissionCheckerBag guestBag = getGuestUserBag();

					if (guestBag != null) {
						for (long roleId : guestBag.getRoleIds()) {
							roleIds.add(roleId);
						}
					}
				}
				catch (Exception e) {
				}

				return ArrayUtil.toArray(
					roleIds.toArray(new Long[roleIds.size()]));
			}
			else {
				return bag.getRoleIds();
			}
		}

		return PermissionChecker.DEFAULT_ROLE_IDS;
	}

	@Override
	public List<Long> getGuestResourceBlockIds(
		long companyId, long groupId, String name, String actionId) {

		try {
			ResourceBlockIdsBag resourceBlockIdsBag =
				getGuestResourceBlockIdsBag(companyId, groupId, name);

			return ResourceBlockLocalServiceUtil.getResourceBlockIds(
				resourceBlockIdsBag, name, actionId);
		}
		catch (Exception e) {
		}

		return Collections.emptyList();
	}

	public ResourceBlockIdsBag getGuestResourceBlockIdsBag(
			long companyId, long groupId, String name)
		throws Exception {

		// checkGuest is irrelevant for the guest role, so it is assumed true

		ResourceBlockIdsBag resourceBlockIdsBag =
			PermissionCacheUtil.getResourceBlockIdsBag(
				companyId, groupId, defaultUserId, name, true);

		if (resourceBlockIdsBag != null) {
			return resourceBlockIdsBag;
		}

		try {
			PermissionCheckerBag bag = getGuestUserBag();

			long[] roleIds = bag.getRoleIds();

			resourceBlockIdsBag =
				ResourceBlockLocalServiceUtil.getResourceBlockIdsBag(
					getCompanyId(), groupId, name, roleIds);

			PermissionCacheUtil.putResourceBlockIdsBag(
				companyId, groupId, defaultUserId, name, true,
				resourceBlockIdsBag);

			return resourceBlockIdsBag;
		}
		finally {
			if (resourceBlockIdsBag == null) {
				resourceBlockIdsBag = new ResourceBlockIdsBag();
			}

			PermissionCacheUtil.putResourceBlockIdsBag(
				companyId, defaultUserId, groupId, name, true,
				resourceBlockIdsBag);
		}
	}

	/**
	 * Returns the permission checker bag for the guest user.
	 *
	 * @return the permission checker bag for the guest user
	 * @throws Exception if an exception occurred
	 */
	public PermissionCheckerBag getGuestUserBag() throws Exception {
		Group guestGroup = GroupLocalServiceUtil.getGroup(
			getCompanyId(), GroupConstants.GUEST);

		PermissionCheckerBag bag = PermissionCacheUtil.getBag(
			defaultUserId, guestGroup.getGroupId());

		if (bag == null) {
			try {
				List<Group> groups = new ArrayList<Group>();

				groups.add(guestGroup);

				List<Role> roles = RoleLocalServiceUtil.getUserRelatedRoles(
					defaultUserId, groups);

				bag = new PermissionCheckerBagImpl(
					defaultUserId, new ArrayList<Group>(),
					new ArrayList<Organization>(), new ArrayList<Group>(),
					new ArrayList<Group>(), groups, roles);
			}
			finally {
				if (bag == null) {
					bag = new PermissionCheckerBagImpl(
						defaultUserId, new ArrayList<Group>(),
						new ArrayList<Organization>(), new ArrayList<Group>(),
						new ArrayList<Group>(), new ArrayList<Group>(),
						new ArrayList<Role>());
				}

				PermissionCacheUtil.putBag(
					defaultUserId, guestGroup.getGroupId(), bag);
			}
		}

		return bag;
	}

	@Override
	public List<Long> getOwnerResourceBlockIds(
		long companyId, long groupId, String name, String actionId) {

		try {
			ResourceBlockIdsBag resourceBlockIdsBag =
				getOwnerResourceBlockIdsBag(companyId, groupId, name);

			return ResourceBlockLocalServiceUtil.getResourceBlockIds(
				resourceBlockIdsBag, name, actionId);
		}
		catch (Exception e) {
		}

		return Collections.emptyList();
	}

	public ResourceBlockIdsBag getOwnerResourceBlockIdsBag(
			long companyId, long groupId, String name)
		throws SystemException {

		// checkGuest is irrelevant for the owner role, so it is assumed true

		ResourceBlockIdsBag resourceBlockIdsBag =
			PermissionCacheUtil.getResourceBlockIdsBag(
				companyId, groupId, ResourceBlockConstants.OWNER_USER_ID, name,
				true);

		if (resourceBlockIdsBag != null) {
			return resourceBlockIdsBag;
		}

		try {
			long[] roleIds = {getOwnerRoleId()};

			resourceBlockIdsBag =
				ResourceBlockLocalServiceUtil.getResourceBlockIdsBag(
					getCompanyId(), groupId, name, roleIds);

			PermissionCacheUtil.putResourceBlockIdsBag(
				companyId, groupId, ResourceBlockConstants.OWNER_USER_ID, name,
				true, resourceBlockIdsBag);

			return resourceBlockIdsBag;
		}
		finally {
			if (resourceBlockIdsBag == null) {
				resourceBlockIdsBag = new ResourceBlockIdsBag();
			}

			PermissionCacheUtil.putResourceBlockIdsBag(
				companyId, ResourceBlockConstants.OWNER_USER_ID, groupId, name,
				true, resourceBlockIdsBag);
		}
	}

	@Override
	public List<Long> getResourceBlockIds(
		long companyId, long groupId, long userId, String name,
		String actionId) {

		try {
			ResourceBlockIdsBag resourceBlockIdsBag =
				getResourceBlockIdsBag(companyId, groupId, userId, name);

			return ResourceBlockLocalServiceUtil.getResourceBlockIds(
				resourceBlockIdsBag, name, actionId);
		}
		catch (Exception e) {
		}

		return Collections.emptyList();
	}

	public ResourceBlockIdsBag getResourceBlockIdsBag(
			long companyId, long groupId, long userId, String name)
		throws Exception {

		ResourceBlockIdsBag resourceBlockIdsBag =
			PermissionCacheUtil.getResourceBlockIdsBag(
				companyId, groupId, userId, name, checkGuest);

		if (resourceBlockIdsBag != null) {
			return resourceBlockIdsBag;
		}

		try {
			long[] roleIds = getRoleIds(userId, groupId);

			resourceBlockIdsBag =
				ResourceBlockLocalServiceUtil.getResourceBlockIdsBag(
					getCompanyId(), groupId, name, roleIds);

			PermissionCacheUtil.putResourceBlockIdsBag(
				companyId, groupId, userId, name, checkGuest,
				resourceBlockIdsBag);

			return resourceBlockIdsBag;
		}
		finally {
			if (resourceBlockIdsBag == null) {
				resourceBlockIdsBag = new ResourceBlockIdsBag();
			}

			PermissionCacheUtil.putResourceBlockIdsBag(
				companyId, userId, groupId, name, checkGuest,
				resourceBlockIdsBag);
		}
	}

	/**
	 * Returns the permission checker bag for the user and group. Users can have
	 * different roles and permissions in different groups.
	 *
	 * @param  userId the primary key of the user
	 * @param  groupId the primary key of the group
	 * @return the permission checker bag for the user and group
	 * @throws Exception if a user or group with the primary key could not be
	 *         found
	 */
	public PermissionCheckerBag getUserBag(long userId, long groupId)
		throws Exception {

		PermissionCheckerBag bag = PermissionCacheUtil.getBag(userId, groupId);

		if (bag != null) {
			return bag;
		}

		try {
			Group group = null;

			if (groupId > 0) {
				group = GroupLocalServiceUtil.getGroup(groupId);

				if (group.isLayout()) {
					long parentGroupId = group.getParentGroupId();

					if (parentGroupId > 0) {
						group = GroupLocalServiceUtil.getGroup(parentGroupId);
					}
				}
			}

			List<Group> userGroups = GroupLocalServiceUtil.getUserGroups(
				userId, true);

			List<Organization> userOrgs = getUserOrgs(userId);

			List<Group> userOrgGroups =
				GroupLocalServiceUtil.getOrganizationsGroups(userOrgs);

			List<UserGroup> userUserGroups =
				UserGroupLocalServiceUtil.getUserUserGroups(userId);

			List<Group> userUserGroupGroups =
				GroupLocalServiceUtil.getUserGroupsGroups(userUserGroups);

			List<Group> groups = new ArrayList<Group>(
				userGroups.size() + userOrgGroups.size() +
					userUserGroupGroups.size());

			groups.addAll(userGroups);
			groups.addAll(userOrgGroups);
			groups.addAll(userUserGroupGroups);

			List<Role> roles = new UniqueList<Role>();

			if ((PropsValues.PERMISSIONS_USER_CHECK_ALGORITHM == 3) ||
				(PropsValues.PERMISSIONS_USER_CHECK_ALGORITHM == 4) ||
				(PropsValues.PERMISSIONS_USER_CHECK_ALGORITHM == 5) ||
				(PropsValues.PERMISSIONS_USER_CHECK_ALGORITHM == 6)) {

				if (groups.size() > 0) {
					List<Role> userRelatedRoles=
						RoleLocalServiceUtil.getUserRelatedRoles(
							userId, groups);

					roles.addAll(userRelatedRoles);
				}
				else {
					roles.addAll(RoleLocalServiceUtil.getUserRoles(userId));
				}

				List<Role> userGroupRoles =
					RoleLocalServiceUtil.getUserGroupRoles(userId, groupId);

				roles.addAll(userGroupRoles);

				List<Role> userGroupGroupRoles =
					RoleLocalServiceUtil.getUserGroupGroupRoles(
						userId, groupId);

				roles.addAll(userGroupGroupRoles);

				if (group != null) {
					if (group.isOrganization() &&
						userOrgGroups.contains(group)) {

						Role organizationUserRole =
							RoleLocalServiceUtil.getRole(
								group.getCompanyId(),
								RoleConstants.ORGANIZATION_USER);

						roles.add(organizationUserRole);
					}

					if (group.isSite() &&
						(userGroups.contains(group) ||
						 userOrgGroups.contains(group))) {

						Role siteMemberRole = RoleLocalServiceUtil.getRole(
							group.getCompanyId(), RoleConstants.SITE_MEMBER);

						roles.add(siteMemberRole);
					}

					if ((group.isOrganization() &&
						 userOrgGroups.contains(group)) ||
						(group.isSite() && userGroups.contains(group))) {

						addTeamRoles(userId, group, roles);
					}
				}
			}
			else {
				roles = new ArrayList<Role>();
			}

			bag = new PermissionCheckerBagImpl(
				userId, userGroups, userOrgs, userOrgGroups,
				userUserGroupGroups, groups, roles);

			return bag;
		}
		finally {
			if (bag == null) {
				bag = new PermissionCheckerBagImpl(
					userId, new ArrayList<Group>(),
					new ArrayList<Organization>(), new ArrayList<Group>(),
					new ArrayList<Group>(), new ArrayList<Group>(),
					new ArrayList<Role>());
			}

			PermissionCacheUtil.putBag(userId, groupId, bag);
		}
	}

	public boolean hasOwnerPermission(
		long companyId, String name, String primKey, long ownerId,
		String actionId) {

		if (ownerId != getUserId()) {
			return false;
		}

		if (ownerId == defaultUserId) {
			if (actionId.equals(ActionKeys.VIEW)) {
				return true;
			}
			else {
				return false;
			}
		}

		try {
			if (PropsValues.PERMISSIONS_USER_CHECK_ALGORITHM == 6) {
				if (ResourceBlockLocalServiceUtil.isSupported(name)) {
					PermissionedModel permissionedModel =
						ResourceBlockLocalServiceUtil.getPermissionedModel(
							name, GetterUtil.getLong(primKey));

					long groupId = 0;

					if (permissionedModel instanceof GroupedModel) {
						GroupedModel groupedModel =
							(GroupedModel)permissionedModel;

						groupId = groupedModel.getGroupId();
					}

					ResourceBlockIdsBag resourceBlockIdsBag =
						getOwnerResourceBlockIdsBag(companyId, groupId, name);

					return ResourceBlockLocalServiceUtil.hasPermission(
						name, permissionedModel, actionId, resourceBlockIdsBag);
				}

				return ResourcePermissionLocalServiceUtil.hasResourcePermission(
					companyId, name, ResourceConstants.SCOPE_INDIVIDUAL,
					primKey, getOwnerRoleId(), actionId);
			}

			ResourceActionsUtil.checkAction(name, actionId);

			Resource resource = ResourceLocalServiceUtil.getResource(
				companyId, name, ResourceConstants.SCOPE_INDIVIDUAL, primKey);

			List<Permission> permissions =
				PermissionLocalServiceUtil.getRolePermissions(
					getOwnerRoleId(), resource.getResourceId());

			int pos = Collections.binarySearch(
				permissions, actionId, new PermissionActionIdComparator());

			if (pos >= 0) {
				return true;
			}
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}

		return false;
	}

	public boolean hasPermission(
		long groupId, String name, String primKey, String actionId) {

		StopWatch stopWatch = null;

		if (_log.isDebugEnabled()) {
			stopWatch = new StopWatch();

			stopWatch.start();
		}

		Group group = null;

		// If the current group is a staging group, check the live group. If the
		// current group is a scope group for a layout, check the original
		// group.

		try {
			if (groupId > 0) {
				group = GroupLocalServiceUtil.getGroup(groupId);

				if (group.isUser() && (group.getClassPK() == getUserId())) {
					group = GroupLocalServiceUtil.getGroup(
						getCompanyId(), GroupConstants.USER_PERSONAL_SITE);

					groupId = group.getGroupId();
				}

				if (group.isLayout()) {
					Layout layout = LayoutLocalServiceUtil.getLayout(
						group.getClassPK());

					groupId = layout.getGroupId();

					group = GroupLocalServiceUtil.getGroup(groupId);
				}

				if (group.isStagingGroup()) {
					if (primKey.equals(String.valueOf(groupId))) {
						primKey = String.valueOf(group.getLiveGroupId());
					}

					groupId = group.getLiveGroupId();
					group = group.getLiveGroup();
				}
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		Boolean value = PermissionCacheUtil.getPermission(
			user.getUserId(), signedIn, checkGuest, groupId, name, primKey,
			actionId);

		if (value == null) {
			try {
				value = Boolean.valueOf(
					hasPermissionImpl(groupId, name, primKey, actionId));

				if (_log.isDebugEnabled()) {
					_log.debug(
						"Checking permission for " + groupId + " " + name +
							" " + primKey + " " + actionId + " takes " +
								stopWatch.getTime() + " ms");
				}
			}
			finally {
				if (value == null) {
					value = Boolean.FALSE;
				}

				PermissionCacheUtil.putPermission(
					user.getUserId(), signedIn, checkGuest, groupId, name,
					primKey, actionId, value);
			}
		}

		return value.booleanValue();
	}

	public boolean hasUserPermission(
		long groupId, String name, String primKey, String actionId,
		boolean checkAdmin) {

		try {
			return hasUserPermissionImpl(
				groupId, name, primKey, actionId, checkAdmin);
		}
		catch (Exception e) {
			_log.error(e, e);

			return false;
		}
	}

	public boolean isCompanyAdmin() {
		try {
			return isCompanyAdminImpl();
		}
		catch (Exception e) {
			_log.error(e, e);

			return false;
		}
	}

	public boolean isCompanyAdmin(long companyId) {
		try {
			return isCompanyAdminImpl(companyId);
		}
		catch (Exception e) {
			_log.error(e, e);

			return false;
		}
	}

	public boolean isGroupAdmin(long groupId) {
		try {
			return isGroupAdminImpl(groupId);
		}
		catch (Exception e) {
			_log.error(e, e);

			return false;
		}
	}

	public boolean isGroupOwner(long groupId) {
		try {
			return isGroupOwnerImpl(groupId);
		}
		catch (Exception e) {
			_log.error(e, e);

			return false;
		}
	}

	protected void addTeamRoles(long userId, Group group, List<Role> roles)
		throws Exception {

		if ((PropsValues.PERMISSIONS_USER_CHECK_ALGORITHM == 5) ||
			(PropsValues.PERMISSIONS_USER_CHECK_ALGORITHM == 6)) {

			List<Team> userTeams = TeamLocalServiceUtil.getUserTeams(
				userId, group.getGroupId());

			for (Team team : userTeams) {
				Role role = RoleLocalServiceUtil.getTeamRole(
					team.getCompanyId(), team.getTeamId());

				roles.add(role);
			}

			LinkedHashMap<String, Object> teamParams =
				new LinkedHashMap<String, Object>();

			teamParams.put("usersUserGroups", userId);

			List<Team> userGroupTeams = TeamLocalServiceUtil.search(
				group.getGroupId(), null, null, teamParams, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

			for (Team team : userGroupTeams) {
				Role role = RoleLocalServiceUtil.getTeamRole(
					team.getCompanyId(), team.getTeamId());

				roles.add(role);
			}
		}
	}

	/**
	 * Returns representations of the resource at each scope level.
	 *
	 * <p>
	 * For example, if the class name and primary key of a blog entry were
	 * passed to this method, it would return a resource for the blog entry
	 * itself (individual scope), a resource representing all blog entries
	 * within its group (group scope), a resource standing for all blog entries
	 * within a group the user has a suitable role in (group-template scope),
	 * and a resource signifying all blog entries within the company (company
	 * scope).
	 * </p>
	 *
	 * @param  companyId the primary key of the company
	 * @param  groupId the primary key of the group containing the resource
	 * @param  name the resource's name, which can be either a class name or a
	 *         portlet ID
	 * @param  primKey the primary key of the resource
	 * @param  actionId unused
	 * @return representations of the resource at each scope level
	 * @throws Exception if an exception occurred
	 */
	protected List<Resource> getResources(
			long companyId, long groupId, String name, String primKey,
			String actionId)
		throws Exception {

		// Individual

		List<Resource> resources = new ArrayList<Resource>(4);

		try {
			Resource resource = ResourceLocalServiceUtil.getResource(
				companyId, name, ResourceConstants.SCOPE_INDIVIDUAL, primKey);

			resources.add(resource);
		}
		catch (NoSuchResourceException nsre) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Resource " + companyId + " " + name + " " +
						ResourceConstants.SCOPE_INDIVIDUAL + " " + primKey +
							" does not exist");
			}
		}

		// Group

		try {
			if (groupId > 0) {
				Resource resource = ResourceLocalServiceUtil.getResource(
					companyId, name, ResourceConstants.SCOPE_GROUP,
					String.valueOf(groupId));

				resources.add(resource);
			}
		}
		catch (NoSuchResourceException nsre) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Resource " + companyId + " " + name + " " +
						ResourceConstants.SCOPE_GROUP + " " + groupId +
							" does not exist");
			}
		}

		// Group template

		try {
			if (signedIn && (groupId > 0)) {
				Resource resource = ResourceLocalServiceUtil.getResource(
					companyId, name, ResourceConstants.SCOPE_GROUP_TEMPLATE,
					String.valueOf(GroupConstants.DEFAULT_PARENT_GROUP_ID));

				resources.add(resource);
			}
		}
		catch (NoSuchResourceException nsre) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Resource " + companyId + " " + name + " " +
						ResourceConstants.SCOPE_GROUP_TEMPLATE + " " +
							GroupConstants.DEFAULT_PARENT_GROUP_ID +
								" does not exist");
			}
		}

		// Company

		try {
			Resource resource = ResourceLocalServiceUtil.getResource(
				companyId, name, ResourceConstants.SCOPE_COMPANY,
				String.valueOf(companyId));

			resources.add(resource);
		}
		catch (NoSuchResourceException nsre) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Resource " + companyId + " " + name + " " +
						ResourceConstants.SCOPE_COMPANY + " " + companyId +
							" does not exist");
			}
		}

		return resources;
	}

	/**
	 * Returns all of the organizations that the user is a member of, including
	 * their parent organizations.
	 *
	 * @param  userId the primary key of the user
	 * @return all of the organizations that the user is a member of, including
	 *         their parent organizations
	 * @throws Exception if a user with the primary key could not be found
	 */
	protected List<Organization> getUserOrgs(long userId) throws Exception {
		List<Organization> userOrgs =
			OrganizationLocalServiceUtil.getUserOrganizations(userId);

		if (userOrgs.size() == 0) {
			return userOrgs;
		}

		List<Organization> organizations = new UniqueList<Organization>();

		for (Organization organization : userOrgs) {
			if (!organizations.contains(organization)) {
				organizations.add(organization);

				List<Organization> ancestorOrganizations =
					OrganizationLocalServiceUtil.getParentOrganizations(
						organization.getOrganizationId());

				organizations.addAll(ancestorOrganizations);
			}
		}

		return organizations;
	}

	protected boolean hasGuestPermission(
			long groupId, String name, String primKey, String actionId)
		throws Exception {

		ResourceActionsUtil.checkAction(name, actionId);

		if (name.indexOf(CharPool.PERIOD) != -1) {

			// Check unsupported model actions

			List<String> actions = ResourceActionsUtil.
				getModelResourceGuestUnsupportedActions(name);

			if (actions.contains(actionId)) {
				return false;
			}
		}
		else {

			// Check unsupported portlet actions

			List<String> actions = ResourceActionsUtil.
				getPortletResourceGuestUnsupportedActions(name);

			if (actions.contains(actionId)) {
				return false;
			}
		}

		long companyId = user.getCompanyId();

		List<Resource> resources = getResources(
			companyId, groupId, name, primKey, actionId);

		PermissionCheckerBag bag = getGuestUserBag();

		try {
			if ((PropsValues.PERMISSIONS_USER_CHECK_ALGORITHM == 6) &&
				ResourceBlockLocalServiceUtil.isSupported(name)) {

				ResourceBlockIdsBag resourceBlockIdsBag =
					getGuestResourceBlockIdsBag(companyId, groupId, name);

				return ResourceBlockLocalServiceUtil.hasPermission(
					name, GetterUtil.getLong(primKey), actionId,
					resourceBlockIdsBag);
			}

			return PermissionLocalServiceUtil.hasUserPermissions(
				defaultUserId, groupId, resources, actionId, bag);
		}
		catch (Exception e) {
			_log.error(e, e);

			return false;
		}
	}

	protected boolean hasPermissionImpl(
		long groupId, String name, String primKey, String actionId) {

		try {
			if (!signedIn) {
				return hasGuestPermission(groupId, name, primKey, actionId);
			}

			if ((PropsValues.PERMISSIONS_USER_CHECK_ALGORITHM == 6) &&
				ResourceBlockLocalServiceUtil.isSupported(name)) {

				// It is not necessary to check guest permissions separately,
				// as the user's resource block IDs bag will already have the
				// guest permissions in it if checkGuest is true.

				return hasUserPermission(
					groupId, name, primKey, actionId, true);
			}

			boolean value = false;

			if (checkGuest) {
				value = hasGuestPermission(groupId, name, primKey, actionId);
			}

			if (!value) {
				value = hasUserPermission(
					groupId, name, primKey, actionId, true);
			}

			return value;
		}
		catch (Exception e) {
			_log.error(e, e);

			return false;
		}
	}

	protected boolean hasUserPermissionImpl(
			long groupId, String name, String primKey, String actionId,
			boolean checkAdmin)
		throws Exception {

		StopWatch stopWatch = null;

		if (_log.isDebugEnabled()) {
			stopWatch = new StopWatch();

			stopWatch.start();
		}

		long companyId = user.getCompanyId();

		boolean hasLayoutManagerPermission = true;

		// Check if the layout manager has permission to do this action for the
		// current portlet

		if ((Validator.isNotNull(name)) && (Validator.isNotNull(primKey)) &&
			(primKey.indexOf(PortletConstants.LAYOUT_SEPARATOR) != -1)) {

			hasLayoutManagerPermission =
				PortletPermissionUtil.hasLayoutManagerPermission(
					name, actionId);
		}

		if (checkAdmin &&
			(isCompanyAdminImpl(companyId) ||
			 (isGroupAdminImpl(groupId) && hasLayoutManagerPermission))) {

			return true;
		}

		logHasUserPermission(groupId, name, primKey, actionId, stopWatch, 1);

		if ((PropsValues.PERMISSIONS_USER_CHECK_ALGORITHM == 6) &&
			ResourceBlockLocalServiceUtil.isSupported(name)) {

			ResourceBlockIdsBag resourceBlockIdsBag =
				getResourceBlockIdsBag(companyId, groupId, getUserId(), name);

			boolean value = ResourceBlockLocalServiceUtil.hasPermission(
				name, GetterUtil.getLong(primKey), actionId,
				resourceBlockIdsBag);

			logHasUserPermission(
				groupId, name, primKey, actionId, stopWatch, 2);

			return value;
		}

		List<Resource> resources = getResources(
			companyId, groupId, name, primKey, actionId);

		logHasUserPermission(groupId, name, primKey, actionId, stopWatch, 3);

		// Check if user has access to perform the action on the given
		// resource scopes. The resources are scoped to check first for an
		// individual class, then for the group that the class may belong
		// to, and then for the company that the class belongs to.

		PermissionCheckerBag bag = getUserBag(user.getUserId(), groupId);

		boolean value = PermissionLocalServiceUtil.hasUserPermissions(
			user.getUserId(), groupId, resources, actionId, bag);

		logHasUserPermission(groupId, name, primKey, actionId, stopWatch, 4);

		return value;
	}

	protected boolean isCompanyAdminImpl() throws Exception {
		return isCompanyAdminImpl(user.getCompanyId());
	}

	protected boolean isCompanyAdminImpl(long companyId) throws Exception {
		if (!signedIn) {
			return false;
		}

		if (isOmniadmin()) {
			return true;
		}

		Boolean value = companyAdmins.get(companyId);

		if (value == null) {
			boolean hasAdminRole = RoleLocalServiceUtil.hasUserRole(
				user.getUserId(), companyId, RoleConstants.ADMINISTRATOR, true);

			value = Boolean.valueOf(hasAdminRole);

			companyAdmins.put(companyId, value);
		}

		return value.booleanValue();
	}

	protected boolean isGroupAdminImpl(long groupId) throws Exception {
		if (!signedIn) {
			return false;
		}

		if (isOmniadmin()) {
			return true;
		}

		if (groupId <= 0) {
			return false;
		}

		Group group = GroupLocalServiceUtil.getGroup(groupId);

		if (isCompanyAdmin(group.getCompanyId())) {
			return true;
		}

		PermissionCheckerBag bag = getUserBag(user.getUserId(), groupId);

		if (bag == null) {
			_log.error("Bag should never be null");
		}

		if (bag.isGroupAdmin(this, group)) {
			return true;
		}
		else {
			return false;
		}
	}

	protected boolean isGroupOwnerImpl(long groupId) throws Exception {
		if (!signedIn) {
			return false;
		}

		if (isOmniadmin()) {
			return true;
		}

		if (groupId <= 0) {
			return false;
		}

		Group group = GroupLocalServiceUtil.getGroup(groupId);

		if (isCompanyAdmin(group.getCompanyId())) {
			return true;
		}

		PermissionCheckerBag bag = getUserBag(user.getUserId(), groupId);

		if (bag == null) {
			_log.error("Bag should never be null");
		}

		if (bag.isGroupOwner(this, group)) {
			return true;
		}
		else {
			return false;
		}
	}

	protected void logHasUserPermission(
		long groupId, String name, String primKey, String actionId,
		StopWatch stopWatch, int block) {

		if (!_log.isDebugEnabled()) {
			return;
		}

		_log.debug(
			"Checking user permission block " + block + " for " + groupId +
				" " + name + " " + primKey + " " + actionId + " takes " +
					stopWatch.getTime() + " ms");
	}

	/**
	 * @deprecated
	 */
	protected static final String RESULTS_SEPARATOR = "_RESULTS_SEPARATOR_";

	protected Map<Long, Boolean> companyAdmins = new HashMap<Long, Boolean>();

	private static Log _log = LogFactoryUtil.getLog(
		AdvancedPermissionChecker.class);

}