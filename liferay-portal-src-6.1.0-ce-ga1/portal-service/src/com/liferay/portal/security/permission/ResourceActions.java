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

import com.liferay.portal.NoSuchResourceActionException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Permission;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.Role;

import java.io.InputStream;

import java.util.List;
import java.util.Locale;

import javax.servlet.jsp.PageContext;

/**
 * @author Brian Wing Shun Chan
 * @author Daeyoung Song
 */
public interface ResourceActions {

	public void checkAction(String name, String actionId)
		throws NoSuchResourceActionException;

	public String getAction(Locale locale, String action);

	public String getAction(PageContext pageContext, String action);

	public String getActionNamePrefix();

	public List<String> getActions(List<Permission> permissions);

	public List<String> getActionsNames(
		PageContext pageContext, List<String> actions);

	public List<String> getActionsNames(
		PageContext pageContext, String name, long actionIds);

	public List<String> getModelNames();

	public List<String> getModelPortletResources(String name);

	public String getModelResource(Locale locale, String name);

	public String getModelResource(PageContext pageContext, String name);

	public List<String> getModelResourceActions(String name);

	public List<String> getModelResourceGroupDefaultActions(String name);

	public List<String> getModelResourceGuestDefaultActions(String name);

	public List<String> getModelResourceGuestUnsupportedActions(String name);

	public String getModelResourceNamePrefix();

	public List<String> getModelResourceOwnerDefaultActions(String name);

	public String[] getOrganizationModelResources();

	public String[] getPortalModelResources();

	public String getPortletBaseResource(String portletName);

	public List<String> getPortletModelResources(String portletName);

	public List<String> getPortletNames();

	public List<String> getPortletResourceActions(Portlet portlet);

	public List<String> getPortletResourceActions(String name);

	public List<String> getPortletResourceGroupDefaultActions(String name);

	public List<String> getPortletResourceGuestDefaultActions(String name);

	public List<String> getPortletResourceGuestUnsupportedActions(String name);

	public List<String> getPortletResourceLayoutManagerActions(String name);

	public List<String> getResourceActions(String name);

	public List<String> getResourceActions(
		String portletResource, String modelResource);

	public List<String> getResourceGroupDefaultActions(String name);

	public List<String> getResourceGuestUnsupportedActions(
		String portletResource, String modelResource);

	/**
	 * @deprecated {@link #getRoles(long, Group, String, int[])}
	 */
	public List<Role> getRoles(
			long companyId, Group group, String modelResource)
			throws SystemException;

	public List<Role> getRoles(
			long companyId, Group group, String modelResource, int[] roleTypes)
		throws SystemException;

	public boolean hasModelResourceActions(String name);

	public boolean isOrganizationModelResource(String modelResource);

	public boolean isPortalModelResource(String modelResource);

	public void read(
			String servletContextName, ClassLoader classLoader, String source)
		throws Exception;

	public void read(String servletContextName, InputStream inputStream)
		throws Exception;

}