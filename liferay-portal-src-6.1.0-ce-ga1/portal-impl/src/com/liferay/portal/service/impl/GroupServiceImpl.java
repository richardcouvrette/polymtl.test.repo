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

package com.liferay.portal.service.impl;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.base.GroupServiceBaseImpl;
import com.liferay.portal.service.permission.GroupPermissionUtil;
import com.liferay.portal.service.permission.PortalPermissionUtil;
import com.liferay.portal.service.permission.PortletPermissionUtil;
import com.liferay.portal.service.permission.RolePermissionUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.util.UniqueList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * The group remote service is responsible for accessing, creating, modifying
 * and deleting groups. For more information on group services and Group, see
 * {@link com.liferay.portal.service.impl.GroupLocalServiceImpl}.
 *
 * @author Brian Wing Shun Chan
 */
public class GroupServiceImpl extends GroupServiceBaseImpl {

	/**
	 * Adds a group.
	 *
	 * @param  liveGroupId the primary key of the live group
	 * @param  name the entity's name
	 * @param  description the group's description (optionally
	 *         <code>null</code>)
	 * @param  type the group's type. For more information see {@link
	 *         com.liferay.portal.model.GroupConstants}
	 * @param  friendlyURL the group's friendlyURL (optionally
	 *         <code>null</code>)
	 * @param  site whether the group is to be associated with a main site
	 * @param  active whether the group is active
	 * @param  serviceContext the service context to be applied (optionally
	 *         <code>null</code>). Can set the asset category IDs and asset tag
	 *         names for the group, and can set whether the group is for staging
	 * @return the group
	 * @throws PortalException if the user did not have permission to add the
	 *         group, if a creator could not be found, if the group's
	 *         information was invalid, if a layout could not be found, or if a
	 *         valid friendly URL could not be created for the group
	 * @throws SystemException if a system exception occurred
	 */
	public Group addGroup(
			long liveGroupId, String name, String description, int type,
			String friendlyURL, boolean site, boolean active,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		GroupPermissionUtil.check(
			getPermissionChecker(), liveGroupId, ActionKeys.UPDATE);

		return groupLocalService.addGroup(
			getUserId(), null, 0, liveGroupId, name, description, type,
			friendlyURL, site, active, serviceContext);
	}

	/**
	 * Adds the group using the group default live group ID.
	 *
	 * @param  name the entity's name
	 * @param  description the group's description (optionally
	 *         <code>null</code>)
	 * @param  type the group's type. For more information see {@link
	 *         com.liferay.portal.model.GroupConstants}
	 * @param  friendlyURL the group's friendlyURL
	 * @param  site whether the group is to be associated with a main site
	 * @param  active whether the group is active
	 * @param  serviceContext the service context to be applied (optionally
	 *         <code>null</code>). Can set asset category IDs and asset tag
	 *         names for the group, and can set whether the group is for staging
	 * @return the group
	 * @throws PortalException if the user did not have permission to add the
	 *         group, if a creator could not be found, if the group's
	 *         information was invalid, if a layout could not be found, or if a
	 *         valid friendly URL could not be created for the group
	 * @throws SystemException if a system exception occurred
	 */
	public Group addGroup(
			String name, String description, int type, String friendlyURL,
			boolean site, boolean active, ServiceContext serviceContext)
		throws PortalException, SystemException {

		PortalPermissionUtil.check(
			getPermissionChecker(), ActionKeys.ADD_COMMUNITY);

		return groupLocalService.addGroup(
			getUserId(), null, 0, name, description, type, friendlyURL, site,
			active, serviceContext);
	}

	/**
	 * Adds the groups to the role.
	 *
	 * @param  roleId the primary key of the role
	 * @param  groupIds the primary keys of the groups
	 * @throws PortalException if the user did not have permission to update the
	 *         role
	 * @throws SystemException if a system exception occurred
	 */
	public void addRoleGroups(long roleId, long[] groupIds)
		throws PortalException, SystemException {

		RolePermissionUtil.check(
			getPermissionChecker(), roleId, ActionKeys.UPDATE);

		groupLocalService.addRoleGroups(roleId, groupIds);
	}

	/**
	 * Deletes the group.
	 *
	 * <p>
	 * The group is unstaged and its assets and resources including layouts,
	 * membership requests, subscriptions, teams, blogs, bookmarks, calendar
	 * events, image gallery, journals, message boards, polls, shopping related
	 * entities, software catalog, and wikis are also deleted.
	 * </p>
	 *
	 * @param  groupId the primary key of the group
	 * @throws PortalException if the user did not have permission to delete the
	 *         group or its assets or resources, if a group with the primary key
	 *         could not be found, or if the group was a system group
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteGroup(long groupId)
		throws PortalException, SystemException {

		GroupPermissionUtil.check(
			getPermissionChecker(), groupId, ActionKeys.DELETE);

		groupLocalService.deleteGroup(groupId);
	}

	/**
	 * Returns the group with the primary key.
	 *
	 * @param  groupId the primary key of the group
	 * @return the group with the primary key
	 * @throws PortalException if a group with the primary key could not be
	 *         found or if the current user did not have permission to view the
	 *         group
	 * @throws SystemException if a system exception occurred
	 */
	public Group getGroup(long groupId)
		throws PortalException, SystemException {

		GroupPermissionUtil.check(
			getPermissionChecker(), groupId, ActionKeys.VIEW);

		return groupLocalService.getGroup(groupId);
	}

	/**
	 * Returns the group with the name.
	 *
	 * @param  companyId the primary key of the company
	 * @param  name the group's name
	 * @return the group with the name
	 * @throws PortalException if a matching group could not be found or if the
	 *         current user did not have permission to view the group
	 * @throws SystemException if a system exception occurred
	 */
	public Group getGroup(long companyId, String name)
		throws PortalException, SystemException {

		Group group = groupLocalService.getGroup(companyId, name);

		GroupPermissionUtil.check(
			getPermissionChecker(), group.getGroupId(), ActionKeys.VIEW);

		return group;
	}

	/**
	 * Returns a range of all the site groups for which the user has control
	 * panel access.
	 *
	 * @param  portlets the portlets to manage
	 * @param  max the upper bound of the range of groups to consider (not
	 *         inclusive)
	 * @return the range of site groups for which the user has control panel
	 *         access
	 * @throws PortalException if a portal exception occurred
	 * @throws SystemException if a system exception occurred
	 */
	public List<Group> getManageableSites(Collection<Portlet> portlets, int max)
		throws PortalException, SystemException {

		PermissionChecker permissionChecker = getPermissionChecker();

		if (permissionChecker.isCompanyAdmin()) {
			LinkedHashMap<String, Object> params =
				new LinkedHashMap<String, Object>();

			params.put("site", Boolean.TRUE);

			return groupLocalService.search(
				permissionChecker.getCompanyId(), null, null, null, params, 0,
				max);
		}

		List<Group> groups = new UniqueList<Group>();

		groups.addAll(
			userPersistence.getGroups(permissionChecker.getUserId(), 0, max));
		groups.addAll(
			getUserOrganizationsGroups(permissionChecker.getUserId(), 0, max));

		Iterator<Group> itr = groups.iterator();

		while (itr.hasNext()) {
			Group group = itr.next();

			if (!group.isSite() ||
				!PortletPermissionUtil.contains(
					permissionChecker, group.getGroupId(), 0L, portlets,
					ActionKeys.ACCESS_IN_CONTROL_PANEL)) {

				itr.remove();
			}
		}

		return groups;
	}

	/**
	 * Returns the groups associated with the organizations.
	 *
	 * @param  organizations the organizations
	 * @return the groups associated with the organizations
	 * @throws PortalException if a portal exception occurred
	 * @throws SystemException if a system exception occurred
	 */
	public List<Group> getOrganizationsGroups(List<Organization> organizations)
		throws PortalException, SystemException {

		List<Group> groups = groupLocalService.getOrganizationsGroups(
			organizations);

		return filterGroups(groups);
	}

	/**
	 * Returns the group associated with the user.
	 *
	 * @param  companyId the primary key of the company
	 * @param  userId the primary key of the user
	 * @return the group associated with the user
	 * @throws PortalException if a matching group could not be found or if the
	 *         current user did not have permission to view the group
	 * @throws SystemException if a system exception occurred
	 */
	public Group getUserGroup(long companyId, long userId)
		throws PortalException, SystemException {

		Group group = groupLocalService.getUserGroup(companyId, userId);

		GroupPermissionUtil.check(
			getPermissionChecker(), group.getGroupId(), ActionKeys.VIEW);

		return group;
	}

	/**
	 * Returns the groups associated with the user groups.
	 *
	 * @param  userGroups the user groups
	 * @return the groups associated with the user groups
	 * @throws PortalException if any one of the user group's group could not be
	 *         found
	 * @throws SystemException if a system exception occurred
	 */
	public List<Group> getUserGroupsGroups(List<UserGroup> userGroups)
		throws PortalException, SystemException {

		List<Group> groups = groupLocalService.getUserGroupsGroups(userGroups);

		return filterGroups(groups);
	}

	/**
	 * Returns the range of all groups associated with the user's organization
	 * groups, including the ancestors of the organization groups, unless portal
	 * property <code>organizations.membership.strict</code> is set to
	 * <code>true</code>.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end -
	 * start</code> instances. <code>start</code> and <code>end</code> are not
	 * primary keys, they are indexes in the result set. Thus, <code>0</code>
	 * refers to the first result in the set. Setting both <code>start</code>
	 * and <code>end</code> to {@link
	 * com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	 * result set.
	 * </p>
	 *
	 * @param  userId the primary key of the user
	 * @param  start the lower bound of the range of groups to consider
	 * @param  end the upper bound of the range of groups to consider (not
	 *         inclusive)
	 * @return the range of groups associated with the user's organizations
	 * @throws PortalException if a user with the primary key could not be found
	 *         or if another portal exception occurred
	 * @throws SystemException if a system exception occurred
	 */
	public List<Group> getUserOrganizationsGroups(
			long userId, int start, int end)
		throws PortalException, SystemException {

		List<Group> groups = groupLocalService.getUserOrganizationsGroups(
			userId, start, end);

		return filterGroups(groups);
	}

	public List<Group> getUserPlaces(
			long userId, String[] classNames, boolean includeControlPanel,
			int max)
		throws PortalException, SystemException {

		User user = userPersistence.fetchByPrimaryKey(userId);

		if (user.isDefaultUser()) {
			return Collections.emptyList();
		}

		List<Group> userPlaces = new UniqueList<Group>();

		int start = QueryUtil.ALL_POS;
		int end = QueryUtil.ALL_POS;

		if (max != QueryUtil.ALL_POS) {
			start = 0;
			end = max;
		}

		if ((classNames == null) ||
			ArrayUtil.contains(classNames, Group.class.getName())) {

			LinkedHashMap<String, Object> groupParams =
				new LinkedHashMap<String, Object>();

			groupParams.put("active", Boolean.TRUE);
			groupParams.put("usersGroups", new Long(userId));

			userPlaces.addAll(
				groupLocalService.search(
					user.getCompanyId(), groupParams, start, end));
		}

		if ((classNames == null) ||
			ArrayUtil.contains(classNames, Organization.class.getName())) {

			LinkedHashMap<String, Object> organizationParams =
				new LinkedHashMap<String, Object>();

			organizationParams.put("usersOrgs", new Long(userId));

			List<Organization> userOrgs = organizationLocalService.search(
				user.getCompanyId(), organizationParams, start, end);

			for (Organization organization : userOrgs) {
				userPlaces.add(0, organization.getGroup());

				if (!PropsValues.ORGANIZATIONS_MEMBERSHIP_STRICT) {
					for (Organization ancestorOrganization :
							organization.getAncestors()) {

						userPlaces.add(0, ancestorOrganization.getGroup());
					}
				}
			}
		}

		if ((classNames == null) ||
			ArrayUtil.contains(classNames, User.class.getName())) {

			if (PropsValues.LAYOUT_USER_PRIVATE_LAYOUTS_ENABLED ||
				PropsValues.LAYOUT_USER_PUBLIC_LAYOUTS_ENABLED) {

				Group userGroup = user.getGroup();

				userPlaces.add(0, userGroup);
			}
		}

		PermissionChecker permissionChecker = getPermissionChecker();

		if (permissionChecker.getUserId() != userId) {
			try {
				permissionChecker = PermissionCheckerFactoryUtil.create(
					user, true);
			}
			catch (Exception e) {
				throw new PrincipalException(e);
			}
		}

		if (includeControlPanel &&
			PortalPermissionUtil.contains(
				permissionChecker, ActionKeys.VIEW_CONTROL_PANEL)) {

			Group controlPanelGroup = groupLocalService.getGroup(
				user.getCompanyId(), GroupConstants.CONTROL_PANEL);

			userPlaces.add(0, controlPanelGroup);
		}

		if ((max != QueryUtil.ALL_POS) && (userPlaces.size() > max)) {
			userPlaces = ListUtil.subList(userPlaces, start, end);
		}

		return Collections.unmodifiableList(userPlaces);
	}

	/**
	 * Returns the user's group &quot;places&quot; associated with the group
	 * entity class names, including the control panel group if the user is
	 * permitted to view the control panel.
	 *
	 * <p>
	 * <ul> <li> Class name &quot;User&quot; includes the user's layout set
	 * group. </li> <li> Class name &quot;Organization&quot; includes the user's
	 * immediate organization groups and inherited organization groups. </li>
	 * <li> Class name &quot;Group&quot; includes the user's immediate
	 * organization groups and site groups. </li> <li> A <code>classNames</code>
	 * value of <code>null</code> includes the user's layout set group,
	 * organization groups, inherited organization groups, and site groups.
	 * </li> </ul>
	 * </p>
	 *
	 * @param  userId the primary key of the user
	 * @param  classNames the group entity class names (optionally
	 *         <code>null</code>). For more information see {@link
	 *         #getUserPlaces(long, String[], int)}
	 * @param  max the maximum number of groups to return
	 * @return the user's group &quot;places&quot;
	 * @throws PortalException if a portal exception occurred
	 * @throws SystemException if a system exception occurred
	 */
	public List<Group> getUserPlaces(long userId, String[] classNames, int max)
		throws PortalException, SystemException {

		return getUserPlaces(userId, classNames, false, max);
	}

	/**
	 * Returns the guest or current user's group &quot;places&quot; associated
	 * with the group entity class names, including the control panel group if
	 * the user is permitted to view the control panel.
	 *
	 * <p>
	 * <ul> <li> Class name &quot;User&quot; includes the user's layout set
	 * group. </li> <li> Class name &quot;Organization&quot; includes the user's
	 * immediate organization groups and inherited organization groups. </li>
	 * <li> Class name &quot;Group&quot; includes the user's immediate
	 * organization groups and site groups. </li> <li> A <code>classNames</code>
	 * value of <code>null</code> includes the user's layout set group,
	 * organization groups, inherited organization groups, and site groups.
	 * </li> </ul>
	 * </p>
	 *
	 * @param  classNames the group entity class names (optionally
	 *         <code>null</code>). For more information see {@link
	 *         #getUserPlaces(String[], int)}
	 * @param  max the maximum number of groups to return
	 * @return the user's group &quot;places&quot;
	 * @throws PortalException if a portal exception occurred
	 * @throws SystemException if a system exception occurred
	 */
	public List<Group> getUserPlaces(String[] classNames, int max)
		throws PortalException, SystemException {

		return getUserPlaces(getGuestOrUserId(), classNames, false, max);
	}

	/**
	 * Returns the guest or current user's layout set group, organization
	 * groups, inherited organization groups, and site groups.
	 *
	 * @return the user's layout set group, organization groups, and inherited
	 *         organization groups, and site groups
	 * @throws PortalException if a portal exception occurred
	 * @throws SystemException if a system exception occurred
	 */
	public List<Group> getUserSites() throws PortalException, SystemException {
		return getUserPlaces(null, QueryUtil.ALL_POS);
	}

	/**
	 * Returns <code>true</code> if the user is associated with the group,
	 * including the user's inherited organizations and user groups. System and
	 * staged groups are not included.
	 *
	 * @param  userId the primary key of the user
	 * @param  groupId the primary key of the group
	 * @return <code>true</code> if the user is associated with the group;
	 *         <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean hasUserGroup(long userId, long groupId)
		throws SystemException {

		return groupLocalService.hasUserGroup(userId, groupId);
	}

	/**
	 * Returns a name ordered range of all the site groups and organization
	 * groups that match the name and description, optionally including the
	 * user's inherited organization groups and user groups. System and staged
	 * groups are not included.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end -
	 * start</code> instances. <code>start</code> and <code>end</code> are not
	 * primary keys, they are indexes in the result set. Thus, <code>0</code>
	 * refers to the first result in the set. Setting both <code>start</code>
	 * and <code>end</code> to {@link
	 * com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	 * result set.
	 * </p>
	 *
	 * @param  companyId the primary key of the company
	 * @param  name the group's name (optionally <code>null</code>)
	 * @param  description the group's description (optionally
	 *         <code>null</code>)
	 * @param  params the finder params (optionally <code>null</code>). To
	 *         include the user's inherited organizations and user groups in the
	 *         search, add entries having &quot;usersGroups&quot; and
	 *         &quot;inherit&quot; as keys mapped to the the user's ID. For more
	 *         information see {@link
	 *         com.liferay.portal.service.persistence.GroupFinder}
	 * @param  start the lower bound of the range of groups to return
	 * @param  end the upper bound of the range of groups to return (not
	 *         inclusive)
	 * @return the matching groups ordered by name
	 * @throws PortalException if a portal exception occurred
	 * @throws SystemException if a system exception occurred
	 */
	public List<Group> search(
			long companyId, String name, String description, String[] params,
			int start, int end)
		throws PortalException, SystemException {

		LinkedHashMap<String, Object> paramsObj = MapUtil.toLinkedHashMap(
			params);

		List<Group> groups = groupLocalService.search(
			companyId, name, description, paramsObj, start, end);

		return filterGroups(groups);
	}

	/**
	 * Returns the number of groups and organization groups that match the name
	 * and description, optionally including the user's inherited organizations
	 * and user groups. System and staged groups are not included.
	 *
	 * @param  companyId the primary key of the company
	 * @param  name the group's name (optionally <code>null</code>)
	 * @param  description the group's description (optionally
	 *         <code>null</code>)
	 * @param  params the finder params (optionally <code>null</code>). To
	 *         include the user's inherited organizations and user groups in the
	 *         search, add entries having &quot;usersGroups&quot; and
	 *         &quot;inherit&quot; as keys mapped to the the user's ID. For more
	 *         information see {@link
	 *         com.liferay.portal.service.persistence.GroupFinder}
	 * @return the number of matching groups
	 * @throws SystemException if a system exception occurred
	 */
	public int searchCount(
			long companyId, String name, String description, String[] params)
		throws SystemException {

		LinkedHashMap<String, Object> paramsObj = MapUtil.toLinkedHashMap(
			params);

		return groupLocalService.searchCount(
			companyId, name, description, paramsObj);
	}

	/**
	 * Sets the groups associated with the role, removing and adding
	 * associations as necessary.
	 *
	 * @param  roleId the primary key of the role
	 * @param  groupIds the primary keys of the groups
	 * @throws PortalException if the user did not have permission to update
	 *         update the role
	 * @throws SystemException if a system exception occurred
	 */
	public void setRoleGroups(long roleId, long[] groupIds)
		throws PortalException, SystemException {

		RolePermissionUtil.check(
			getPermissionChecker(), roleId, ActionKeys.UPDATE);

		groupLocalService.setRoleGroups(roleId, groupIds);
	}

	/**
	 * Removes the groups from the role.
	 *
	 * @param  roleId the primary key of the role
	 * @param  groupIds the primary keys of the groups
	 * @throws PortalException if the user did not have permission to update the
	 *         role
	 * @throws SystemException if a system exception occurred
	 */
	public void unsetRoleGroups(long roleId, long[] groupIds)
		throws PortalException, SystemException {

		RolePermissionUtil.check(
			getPermissionChecker(), roleId, ActionKeys.UPDATE);

		groupLocalService.unsetRoleGroups(roleId, groupIds);
	}

	/**
	 * Updates the group's friendly URL.
	 *
	 * @param  groupId the primary key of the group
	 * @param  friendlyURL the group's new friendlyURL (optionally
	 *         <code>null</code>)
	 * @return the group
	 * @throws PortalException if the user did not have permission to update the
	 *         group, if a group with the primary key could not be found, or if
	 *         a valid friendly URL could not be created for the group
	 * @throws SystemException if a system exception occurred
	 */
	public Group updateFriendlyURL(long groupId, String friendlyURL)
		throws PortalException, SystemException {

		GroupPermissionUtil.check(
			getPermissionChecker(), groupId, ActionKeys.UPDATE);

		return groupLocalService.updateFriendlyURL(groupId, friendlyURL);
	}

	/**
	 * Updates the group's type settings.
	 *
	 * @param  groupId the primary key of the group
	 * @param  typeSettings the group's new type settings (optionally
	 *         <code>null</code>)
	 * @return the group
	 * @throws PortalException if the user did not have permission to update the
	 *         group or if a group with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Group updateGroup(long groupId, String typeSettings)
		throws PortalException, SystemException {

		GroupPermissionUtil.check(
			getPermissionChecker(), groupId, ActionKeys.UPDATE);

		return groupLocalService.updateGroup(groupId, typeSettings);
	}

	/**
	 * Updates the group.
	 *
	 * @param  groupId the primary key of the group
	 * @param  name the group's new name
	 * @param  description the group's new description (optionally
	 *         <code>null</code>)
	 * @param  type the group's new type. For more information see {@link
	 *         com.liferay.portal.model.GroupConstants}
	 * @param  friendlyURL the group's new friendlyURL (optionally
	 *         <code>null</code>)
	 * @param  active whether the group is active
	 * @param  serviceContext the service context to be applied (optionally
	 *         <code>null</code>). Can set the asset category IDs and asset tag
	 *         names for the group.
	 * @return the group
	 * @throws PortalException if the user did not have permission to update the
	 *         group, if a group with the primary key could not be found, if the
	 *         friendly URL was invalid or could one not be created
	 * @throws SystemException if a system exception occurred
	 */
	public Group updateGroup(
			long groupId, String name, String description, int type,
			String friendlyURL, boolean active, ServiceContext serviceContext)
		throws PortalException, SystemException {

		GroupPermissionUtil.check(
			getPermissionChecker(), groupId, ActionKeys.UPDATE);

		return groupLocalService.updateGroup(
			groupId, name, description, type, friendlyURL, active,
			serviceContext);
	}

	protected List<Group> filterGroups(List<Group> groups)
		throws PortalException, SystemException {

		List<Group> filteredGroups = new ArrayList<Group>();

		for (Group group : groups) {
			if (GroupPermissionUtil.contains(
					getPermissionChecker(), group.getGroupId(),
					ActionKeys.VIEW)) {

				filteredGroups.add(group);
			}
		}

		return filteredGroups;
	}

}