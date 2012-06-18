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

package com.liferay.portlet.usersadmin.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Tuple;
import com.liferay.portal.model.Address;
import com.liferay.portal.model.EmailAddress;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.OrgLabor;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.Phone;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.model.UserGroupRole;
import com.liferay.portal.model.Website;
import com.liferay.portal.security.permission.PermissionChecker;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 * @author Jorge Ferrer
 * @author Julio Camarero
 */
public class UsersAdminUtil {

	public static final String CUSTOM_QUESTION = "write-my-own-question";

	public static void addPortletBreadcrumbEntries(
			Organization organization, HttpServletRequest request,
			RenderResponse renderResponse)
		throws Exception {

		getUsersAdmin().addPortletBreadcrumbEntries(
			organization, request, renderResponse);
	}

	public static long[] addRequiredRoles(long userId, long[] roleIds)
		throws PortalException, SystemException {

		return getUsersAdmin().addRequiredRoles(userId, roleIds);
	}

	public static long[] addRequiredRoles(User user, long[] roleIds)
		throws PortalException, SystemException {

		return getUsersAdmin().addRequiredRoles(user, roleIds);
	}

	public static List<Role> filterGroupRoles(
			PermissionChecker permissionChecker, long groupId, List<Role> roles)
		throws PortalException, SystemException {

		return getUsersAdmin().filterGroupRoles(
			permissionChecker, groupId, roles);
	}

	public static List<Group> filterGroups(
			PermissionChecker permissionChecker, List<Group> groups)
		throws PortalException, SystemException {

		return getUsersAdmin().filterGroups(permissionChecker, groups);
	}

	public static List<Organization> filterOrganizations(
			PermissionChecker permissionChecker,
			List<Organization> organizations)
		throws PortalException, SystemException {

		return getUsersAdmin().filterOrganizations(
			permissionChecker, organizations);
	}

	public static List<Role> filterRoles(
		PermissionChecker permissionChecker, List<Role> roles) {

		return getUsersAdmin().filterRoles(permissionChecker, roles);
	}

	public static List<UserGroupRole> filterUserGroupRoles(
			PermissionChecker permissionChecker,
			List<UserGroupRole> userGroupRoles)
		throws PortalException, SystemException {

		return getUsersAdmin().filterUserGroupRoles(
			permissionChecker, userGroupRoles);
	}

	public static List<UserGroup> filterUserGroups(
		PermissionChecker permissionChecker, List<UserGroup> userGroups) {

		return getUsersAdmin().filterUserGroups(permissionChecker, userGroups);
	}

	public static List<Address> getAddresses(ActionRequest actionRequest) {
		return getUsersAdmin().getAddresses(actionRequest);
	}

	public static List<EmailAddress> getEmailAddresses(
		ActionRequest actionRequest) {

		return getUsersAdmin().getEmailAddresses(actionRequest);
	}

	public static OrderByComparator getGroupOrderByComparator(
		String orderByCol, String orderByType) {

		return getUsersAdmin().getGroupOrderByComparator(
			orderByCol, orderByType);
	}

	public static Long[] getOrganizationIds(List<Organization> organizations) {
		return getUsersAdmin().getOrganizationIds(organizations);
	}

	public static OrderByComparator getOrganizationOrderByComparator(
		String orderByCol, String orderByType) {

		return getUsersAdmin().getOrganizationOrderByComparator(
			orderByCol, orderByType);
	}

	public static Tuple getOrganizations(Hits hits)
		throws PortalException, SystemException {

		return getUsersAdmin().getOrganizations(hits);
	}

	public static List<OrgLabor> getOrgLabors(ActionRequest actionRequest) {
		return getUsersAdmin().getOrgLabors(actionRequest);
	}

	public static List<Phone> getPhones(ActionRequest actionRequest) {
		return getUsersAdmin().getPhones(actionRequest);
	}

	public static OrderByComparator getRoleOrderByComparator(
		String orderByCol, String orderByType) {

		return getUsersAdmin().getRoleOrderByComparator(
			orderByCol, orderByType);
	}

	public static OrderByComparator getUserGroupOrderByComparator(
		String orderByCol, String orderByType) {

		return getUsersAdmin().getUserGroupOrderByComparator(
			orderByCol, orderByType);
	}

	public static List<UserGroupRole> getUserGroupRoles(
			PortletRequest portletRequest)
		throws PortalException, SystemException {

		return getUsersAdmin().getUserGroupRoles(portletRequest);
	}

	public static OrderByComparator getUserOrderByComparator(
		String orderByCol, String orderByType) {

		return getUsersAdmin().getUserOrderByComparator(
			orderByCol, orderByType);
	}

	public static Tuple getUsers(Hits hits)
		throws PortalException, SystemException {

		return getUsersAdmin().getUsers(hits);
	}

	public static List<Website> getWebsites(ActionRequest actionRequest) {
		return getUsersAdmin().getWebsites(actionRequest);
	}

	public static boolean hasUpdateEmailAddress(
			PermissionChecker permissionChecker, User user)
		throws PortalException, SystemException {

		return getUsersAdmin().hasUpdateEmailAddress(permissionChecker, user);
	}

	public static boolean hasUpdateScreenName(
			PermissionChecker permissionChecker, User user)
		throws PortalException, SystemException {

		return getUsersAdmin().hasUpdateScreenName(permissionChecker, user);
	}

	public static long[] removeRequiredRoles(long userId, long[] roleIds)
		throws PortalException, SystemException {

		return getUsersAdmin().removeRequiredRoles(userId, roleIds);
	}

	public static long[] removeRequiredRoles(User user, long[] roleIds)
		throws PortalException, SystemException {

		return getUsersAdmin().removeRequiredRoles(user, roleIds);
	}

	public static void updateAddresses(
			String className, long classPK, List<Address> addresses)
		throws PortalException, SystemException {

		getUsersAdmin().updateAddresses(className, classPK, addresses);
	}

	public static void updateEmailAddresses(
			String className, long classPK, List<EmailAddress> emailAddresses)
		throws PortalException, SystemException {

		getUsersAdmin().updateEmailAddresses(
			className, classPK, emailAddresses);
	}

	public static void updateOrgLabors(long classPK, List<OrgLabor> orgLabors)
		throws PortalException, SystemException {

		getUsersAdmin().updateOrgLabors(classPK, orgLabors);
	}

	public static void updatePhones(
			String className, long classPK, List<Phone> phones)
		throws PortalException, SystemException {

		getUsersAdmin().updatePhones(className, classPK, phones);
	}

	public static void updateWebsites(
			String className, long classPK, List<Website> websites)
		throws PortalException, SystemException {

		getUsersAdmin().updateWebsites(className, classPK, websites);
	}

	public static UsersAdmin getUsersAdmin() {
		return _usersAdmin;
	}

	public void setUsersAdmin(UsersAdmin usersAdmin) {
		_usersAdmin = usersAdmin;
	}

	private static UsersAdmin _usersAdmin;

}