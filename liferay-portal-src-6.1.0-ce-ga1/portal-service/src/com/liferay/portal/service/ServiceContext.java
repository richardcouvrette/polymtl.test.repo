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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.PortletPreferencesIds;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.security.permission.ResourceActionsUtil;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Contains context information about a given API call.
 *
 * <p>
 * The <code>ServiceContext</code> object simplifies method signatures and
 * provides a way to consolidate many different methods with different sets of
 * optional parameters into a single, easier to use method. It also aggregates
 * information necessary for transversal features such as permissioning,
 * tagging, categorization, etc.
 * </p>
 *
 * @author Raymond Augé
 * @author Brian Wing Shun Chan
 * @author Jorge Ferrer
 */
public class ServiceContext implements Cloneable, Serializable {

	/**
	 * Creates a new service context object with an attributes map and an
	 * expando bridge attributes map. The attributes map contains standard
	 * service context parameters and the expando bridge attributes map contains
	 * optional service context parameters.
	 */
	public ServiceContext() {
		_attributes = new LinkedHashMap<String, Serializable>();
		_expandoBridgeAttributes = new LinkedHashMap<String, Serializable>();
	}

	/**
	 * Returns a new service context object identical to this service context
	 * object.
	 *
	 * @return a new service context object
	 */
	@Override
	public Object clone() {
		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(isAddGroupPermissions());
		serviceContext.setAddGuestPermissions(isAddGuestPermissions());
		serviceContext.setAssetCategoryIds(getAssetCategoryIds());
		serviceContext.setAssetEntryVisible(isAssetEntryVisible());
		serviceContext.setAssetLinkEntryIds(getAssetLinkEntryIds());
		serviceContext.setAssetTagNames(getAssetTagNames());
		serviceContext.setAttributes(getAttributes());
		serviceContext.setCommand(getCommand());
		serviceContext.setCompanyId(getCompanyId());
		serviceContext.setCreateDate(getCreateDate());
		serviceContext.setCurrentURL(getCurrentURL());
		serviceContext.setExpandoBridgeAttributes(getExpandoBridgeAttributes());
		serviceContext.setGroupPermissions(getGroupPermissions());
		serviceContext.setGuestPermissions(getGuestPermissions());
		serviceContext.setHeaders(getHeaders());
		serviceContext.setIndexingEnabled(isIndexingEnabled());
		serviceContext.setLanguageId(getLanguageId());
		serviceContext.setLayoutFullURL(getLayoutFullURL());
		serviceContext.setLayoutURL(getLayoutURL());
		serviceContext.setModifiedDate(getModifiedDate());
		serviceContext.setPathMain(getPathMain());
		serviceContext.setPlid(getPlid());
		serviceContext.setPortalURL(getPortalURL());
		serviceContext.setPortletPreferencesIds(getPortletPreferencesIds());
		serviceContext.setRemoteAddr(getRemoteAddr());
		serviceContext.setRemoteHost(getRemoteHost());
		serviceContext.setScopeGroupId(getScopeGroupId());
		serviceContext.setSignedIn(isSignedIn());
		serviceContext.setUserDisplayURL(getUserDisplayURL());
		serviceContext.setUserId(getUserId());
		serviceContext.setUuid(getUuid());
		serviceContext.setWorkflowAction(getWorkflowAction());

		return serviceContext;
	}

	/**
	 * Derive default permissions based on the logic found in
	 * portal-web/docroot/html/taglib/ui/input_permissions/page.jsp. Do not
	 * update this logic updating the logic in the JSP.
	 */
	public void deriveDefaultPermissions(long repositoryId, String modelName)
		throws PortalException, SystemException {

		long parentGroupId = PortalUtil.getParentGroupId(repositoryId);

		Group parentGroup = GroupLocalServiceUtil.getGroup(parentGroupId);

		Role defaultGroupRole = RoleLocalServiceUtil.getDefaultGroupRole(
			parentGroupId);

		List<String> groupPermissions = new ArrayList<String>();
		List<String> guestPermissions = new ArrayList<String>();

		String[] roleNames = {RoleConstants.GUEST, defaultGroupRole.getName()};

		List<String> supportedActions =
			ResourceActionsUtil.getModelResourceActions(modelName);
		List<String> groupDefaultActions =
			ResourceActionsUtil.getModelResourceGroupDefaultActions(modelName);
		List<String> guestDefaultActions =
			ResourceActionsUtil.getModelResourceGuestDefaultActions(modelName);
		List<String> guestUnsupportedActions =
			ResourceActionsUtil.getModelResourceGuestUnsupportedActions(
				modelName);

		for (String roleName : roleNames) {
			for (String action: supportedActions) {
				if (roleName.equals(RoleConstants.GUEST) &&
					!guestUnsupportedActions.contains(action) &&
					guestDefaultActions.contains(action) &&
					parentGroup.hasPublicLayouts()) {

					guestPermissions.add(action);
				}
				else if (roleName.equals(defaultGroupRole.getName()) &&
						 groupDefaultActions.contains(action)) {

					groupPermissions.add(action);
				}
			}
		}

		setGroupPermissions(
			groupPermissions.toArray(new String[groupPermissions.size()]));
		setGuestPermissions(
			guestPermissions.toArray(new String[guestPermissions.size()]));
	}

	/**
	 * Returns <code>true</code> if this service context is being passed as a
	 * parameter to a method which manipulates a resource to which default group
	 * permissions apply.
	 *
	 * @return     <code>true</code> if this service context is being passed as
	 *             a parameter to a method which manipulates a resource to which
	 *             default community permissions apply; <code>false</code>
	 *             otherwise
	 * @deprecated As of 6.1, renamed to {@link #isAddGroupPermissions()}
	 */
	public boolean getAddCommunityPermissions() {
		return isAddGroupPermissions();
	}

	/**
	 * Returns the asset category IDs to be applied to an asset entry if the
	 * service context is being passed as a parameter to a method which
	 * manipulates the asset entry.
	 *
	 * @return the asset category IDs
	 */
	public long[] getAssetCategoryIds() {
		return _assetCategoryIds;
	}

	/**
	 * Returns the primary keys of the asset entries linked to an asset entry if
	 * the service context is being passed as a parameter to a method which
	 * manipulates the asset entry.
	 *
	 * @return the primary keys of the asset entries
	 */
	public long[] getAssetLinkEntryIds() {
		return _assetLinkEntryIds;
	}

	/**
	 * Returns the asset tag names to be applied to an asset entry if the
	 * service context is being passed as a parameter to a method which
	 * manipulates the asset entry.
	 *
	 * @return the asset tag names
	 */
	public String[] getAssetTagNames() {
		return _assetTagNames;
	}

	/**
	 * Returns the serializable object associated with the name of the standard
	 * parameter of this service context.
	 *
	 * @param  name the name of the standard parameter
	 * @return the serializable object associated with the name
	 */
	public Serializable getAttribute(String name) {
		return _attributes.get(name);
	}

	/**
	 * Returns the map of name/value pairs that are the standard parameters of
	 * this service context. Each value is serializable.
	 *
	 * @return the map of name/value pairs
	 */
	public Map<String, Serializable> getAttributes() {
		return _attributes;
	}

	/**
	 * Returns the value of the {@link
	 * com.liferay.portal.kernel.util.Constants#CMD} parameter used in most
	 * Liferay forms for internal portlets.
	 *
	 * @return the value of the command parameter
	 */
	public String getCommand() {
		return _command;
	}

	/**
	 * Returns the specific community permissions for a resource if the service
	 * context is being passed as a parameter to a method which manipulates the
	 * resource.
	 *
	 * @return     the community permissions
	 * @deprecated As of 6.1, renamed to {@link #getGroupPermissions()}
	 */
	public String[] getCommunityPermissions() {
		return getGroupPermissions();
	}

	/**
	 * Returns the company ID of this service context's current portal instance.
	 *
	 * @return the company ID
	 */
	public long getCompanyId() {
		return _companyId;
	}

	/**
	 * Returns the date when an entity was created if this service context is
	 * being passed as a parameter to a method which creates an entity.
	 *
	 * @return the creation date
	 */
	public Date getCreateDate() {
		return _createDate;
	}

	/**
	 * Returns the date when an entity was created (or a default date) if this
	 * service context is being passed as a parameter to a method which creates
	 * an entity.
	 *
	 * @param  defaultCreateDate an optional default create date to use if the
	 *         service context does not have a create date
	 * @return the creation date if available; the default date otherwise
	 */
	public Date getCreateDate(Date defaultCreateDate) {
		if (_createDate != null) {
			return _createDate;
		}
		else if (defaultCreateDate != null) {
			return defaultCreateDate;
		}
		else {
			return new Date();
		}
	}

	/**
	 * Returns the current URL of this service context
	 *
	 * @return the current URL
	 */
	public String getCurrentURL() {
		return _currentURL;
	}

	/**
	 * Returns an arbitrary number of attributes of an entity to be persisted.
	 *
	 * <p>
	 * These attributes only include fields that this service context does not
	 * possess by default.
	 * </p>
	 *
	 * @return the expando bridge attributes
	 */
	public Map<String, Serializable> getExpandoBridgeAttributes() {
		return _expandoBridgeAttributes;
	}

	/**
	 * Returns the specific group permissions for a resource if this service
	 * context is being passed as a parameter to a method which manipulates the
	 * resource.
	 *
	 * @return the specific group permissions
	 */
	public String[] getGroupPermissions() {
		return _groupPermissions;
	}

	/**
	 * Returns this service context's user ID or guest ID if no user ID is
	 * available.
	 *
	 * @return the user ID, or guest ID if there is no user in this service
	 *         context, or <code>0</code> if there is no company in this service
	 *         context
	 * @throws PortalException if a default user for the company could not be
	 *         found
	 * @throws SystemException if a system exception occurred
	 */
	public long getGuestOrUserId() throws PortalException, SystemException {
		long userId = getUserId();

		if (userId > 0) {
			return userId;
		}

		long companyId = getCompanyId();

		if (companyId > 0) {
			return UserLocalServiceUtil.getDefaultUserId(getCompanyId());
		}

		return 0;
	}

	/**
	 * Returns the specific guest permissions for a resource if this service
	 * context is being passed as a parameter to a method which manipulates the
	 * resource.
	 *
	 * @return the specific guest permissions
	 */
	public String[] getGuestPermissions() {
		return _guestPermissions;
	}

	/**
	 * Returns the the map of request header name/value pairs of this service
	 * context.
	 *
	 * @return the the map of request header name/value pairs
	 * @see    com.liferay.portal.kernel.servlet.HttpHeaders
	 */
	public Map<String, String> getHeaders() {
		return _headers;
	}

	/**
	 * Returns the language ID of the locale of this service context's current
	 * user.
	 *
	 * @return the language ID
	 */
	public String getLanguageId() {
		return _languageId;
	}

	/**
	 * Returns the complete URL of the current page if a page context can be
	 * determined for this service context.
	 *
	 * @return the complete URL of the current page
	 */
	public String getLayoutFullURL() {
		return _layoutFullURL;
	}

	/**
	 * Returns the relative URL of the current page if a page context can be
	 * determined for this service context.
	 *
	 * @return the relative URL of the current page
	 */
	public String getLayoutURL() {
		return _layoutURL;
	}

	public Locale getLocale() {
		return LocaleUtil.fromLanguageId(_languageId);
	}

	/**
	 * Returns the date when an entity was modified if this service context is
	 * being passed as a parameter to a method which updates an entity.
	 *
	 * @return the date when an entity was modified if this service context is
	 *         being passed as a parameter to a method which updates an entity
	 */
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	/**
	 * Returns the date when an entity was modified if this service context is
	 * being passed as a parameter to a method which modifies an entity.
	 *
	 * @param  defaultModifiedDate an optional default modified date to use if
	 *         this service context does not have a modified date
	 * @return the modified date if available; the default date otherwise
	 */
	public Date getModifiedDate(Date defaultModifiedDate) {
		if (_modifiedDate != null) {
			return _modifiedDate;
		}
		else if (defaultModifiedDate != null) {
			return defaultModifiedDate;
		}
		else {
			return new Date();
		}
	}

	/**
	 * Returns the main context path of the portal, concatenated with
	 * <code>/c</code>.
	 *
	 * @return the main context path of the portal
	 */
	public String getPathMain() {
		return _pathMain;
	}

	/**
	 * Returns the portal layout ID of the current page of this service context.
	 *
	 * @return the portal layout ID of the current page
	 */
	public long getPlid() {
		return _plid;
	}

	/**
	 * Returns the URL of this service context's portal, including the protocol,
	 * domain, and non-default port relative to the company instance and any
	 * virtual host.
	 *
	 * <p>
	 * The URL returned does not include the port if a default port is used.
	 * </p>
	 *
	 * @return the URL of this service context's portal, including the protocol,
	 *         domain, and non-default port relative to company instance and any
	 *         virtual host
	 */
	public String getPortalURL() {
		return _portalURL;
	}

	/**
	 * Returns the ID of the current portlet if this service context is being
	 * passed as a parameter to a portlet.
	 *
	 * @return the ID of the current portlet
	 * @see    com.liferay.portal.model.PortletPreferencesIds
	 */
	public String getPortletId() {
		if (_portletPreferencesIds != null) {
			return _portletPreferencesIds.getPortletId();
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the portlet preferences IDs of the current portlet if the service
	 * context is being passed as a parameter to a portlet.
	 *
	 * <p>
	 * The {@link com.liferay.portal.model.PortletPreferencesIds} can be used to
	 * look up portlet preferences of the current portlet.
	 * </p>
	 *
	 * @return the portlet preferences IDs of the current portlet
	 * @see    com.liferay.portal.model.PortletPreferencesIds
	 */
	public PortletPreferencesIds getPortletPreferencesIds() {
		return _portletPreferencesIds;
	}

	/**
	 * Returns the remote address of the user making the request in this service
	 * context.
	 *
	 * @return the remote address of the user making the request
	 */
	public String getRemoteAddr() {
		return _remoteAddr;
	}

	/**
	 * Returns the remote host name of the user making the request in this
	 * service context.
	 *
	 * @return the remote host name of the user making the request
	 */
	public String getRemoteHost() {
		return _remoteHost;
	}

	/**
	 * Returns the ID of the group corresponding to the current data scope of
	 * this service context.
	 *
	 * @return the ID of the group corresponding to the current data scope
	 * @see    com.liferay.portal.model.Group
	 */
	public long getScopeGroupId() {
		return _scopeGroupId;
	}

	/**
	 * Returns the user-agent request header of this service context.
	 *
	 * @return the user-agent request header
	 * @see    com.liferay.portal.kernel.servlet.HttpHeaders
	 */
	public String getUserAgent() {
		if (_headers == null) {
			return null;
		}

		return _headers.get(HttpHeaders.USER_AGENT);
	}

	/**
	 * Returns the complete URL of this service context's current user's profile
	 * page.
	 *
	 * @return the complete URL of this service context's current user's profile
	 *         page
	 */
	public String getUserDisplayURL() {
		return _userDisplayURL;
	}

	/**
	 * Returns the ID of this service context's current user.
	 *
	 * @return the ID of this service context's current user
	 */
	public long getUserId() {
		return _userId;
	}

	/**
	 * Returns the UUID (universally unique identifier) of this service
	 * context's current entity.
	 *
	 * @return the UUID (universally unique identifier) of this service
	 *         context's current entity
	 */
	public String getUuid() {
		String uuid = _uuid;

		_uuid = null;

		return uuid;
	}

	/**
	 * Returns the workflow action to take if this service context is being
	 * passed as a parameter to a method that processes a workflow action.
	 *
	 * @return the workflow action to take
	 */
	public int getWorkflowAction() {
		return _workflowAction;
	}

	/**
	 * Returns <code>true</code> if this service context is being passed as a
	 * parameter to a method which manipulates a resource to which default guest
	 * permissions apply.
	 *
	 * @return <code>true</code> if this service context is being passed as a
	 *         parameter to a method which manipulates a resource to which
	 *         default guest permissions apply; <code>false</code> otherwise
	 */
	public boolean isAddGuestPermissions() {
		return _addGuestPermissions;
	}

	/**
	 * Returns <code>true</code> if this service context is being passed as a
	 * parameter to a method which manipulates a resource to which default group
	 * permissions apply.
	 *
	 * @return <code>true</code> if this service context is being passed as a
	 *         parameter to a method which manipulates a resource to which
	 *         default group permissions apply; <code>false</code> otherwise
	 */
	public boolean isAddGroupPermissions() {
		return _addGroupPermissions;
	}

	public boolean isAssetEntryVisible() {
		return _assetEntryVisible;
	}

	/**
	 * Returns <code>true</code> if this service context contains an add command
	 * (i.e. has command value {@link
	 * com.liferay.portal.kernel.util.Constants#ADD})
	 *
	 * @return <code>true</code> if this service context contains an add
	 *         command; <code>false</code> otherwise
	 */
	public boolean isCommandAdd() {
		if (Validator.equals(_command, Constants.ADD)) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Returns <code>true</code> if this service context contains an update
	 * command (i.e. has command value {@link
	 * com.liferay.portal.kernel.util.Constants#UPDATE})
	 *
	 * @return <code>true</code> if this service context contains an update
	 *         command; <code>false</code> otherwise
	 */
	public boolean isCommandUpdate() {
		if (Validator.equals(_command, Constants.UPDATE)) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isDeriveDefaultPermissions() {
		return _deriveDefaultPermissions;
	}

	/**
	 * Returns whether the primary entity of this service context is to be
	 * indexed/re-indexed.
	 *
	 * @return <code>true</code> the primary entity of this service context is
	 *         to be indexed/re-indexed; <code>false</code> otherwise
	 */
	public boolean isIndexingEnabled() {
		return _indexingEnabled;
	}

	/**
	 * Returns <code>true</code> if the sender of this service context's request
	 * is signed in.
	 *
	 * @return <code>true</code> if the sender of this service context's request
	 *         is signed in; <code>false</code> otherwise
	 */
	public boolean isSignedIn() {
		return _signedIn;
	}

	/**
	 * Removes the mapping of the serializable object to the name of the
	 * standard parameter of this service context.
	 *
	 * @param  name the name of the standard parameter
	 * @return the serializable object associated to the name
	 */
	public Serializable removeAttribute(String name) {
		return _attributes.remove(name);
	}

	/**
	 * Sets whether or not default community permissions should apply to a
	 * resource being manipulated by a method to which this service context is
	 * passed as a parameter.
	 *
	 * @param      addCommunityPermissions indicates whether or not to apply
	 *             default community permissions
	 * @deprecated As of 6.1, renamed to {@link
	 *             #setAddGroupPermissions(boolean)}
	 */
	public void setAddCommunityPermissions(boolean addCommunityPermissions) {
		setAddGroupPermissions(addCommunityPermissions);
	}

	/**
	 * Sets whether or not default group permissions should apply to a resource
	 * being manipulated by a method to which this service context is passed as
	 * a parameter.
	 *
	 * @param addGroupPermissions indicates whether or not to apply default
	 *        group permissions
	 */
	public void setAddGroupPermissions(boolean addGroupPermissions) {
		_addGroupPermissions = addGroupPermissions;
	}

	/**
	 * Sets whether or not default guest permissions should apply to a resource
	 * being manipulated by a method to which this service context is passed as
	 * a parameter.
	 *
	 * @param addGuestPermissions indicates whether or not to apply default
	 *        guest permissions
	 */
	public void setAddGuestPermissions(boolean addGuestPermissions) {
		_addGuestPermissions = addGuestPermissions;
	}

	/**
	 * Sets an array of asset category IDs to be applied to an asset entry if
	 * this service context is being passed as a parameter to a method which
	 * manipulates the asset entry.
	 *
	 * @param assetCategoryIds the primary keys of the asset categories
	 */
	public void setAssetCategoryIds(long[] assetCategoryIds) {
		_assetCategoryIds = assetCategoryIds;
	}

	public void setAssetEntryVisible(boolean assetEntryVisible) {
		_assetEntryVisible = assetEntryVisible;
	}

	/**
	 * Sets an array of the primary keys of asset entries to be linked to an
	 * asset entry if this service context is being passed as a parameter to a
	 * method which manipulates the asset entry.
	 *
	 * @param assetLinkEntryIds the primary keys of the asset entries to be
	 *        linked to an asset entry
	 */
	public void setAssetLinkEntryIds(long[] assetLinkEntryIds) {
		_assetLinkEntryIds = assetLinkEntryIds;
	}

	/**
	 * Sets an array of asset tag names to be applied to an asset entry if this
	 * service context is being passed as a parameter to a method which
	 * manipulates the asset entry.
	 *
	 * @param assetTagNames the tag names to be applied to an asset entry
	 */
	public void setAssetTagNames(String[] assetTagNames) {
		_assetTagNames = assetTagNames;
	}

	/**
	 * Sets a mapping of a standard parameter's name to its serializable object.
	 *
	 * @param name the standard parameter name to associate with the value
	 * @param value the serializable object to be associated with the name
	 */
	public void setAttribute(String name, Serializable value) {
		_attributes.put(name, value);
	}

	/**
	 * Sets the map of the name/value pairs that are the standard parameters of
	 * this service context. Each value must be serializable.
	 *
	 * @param attributes the map of the name/value pairs that are the standard
	 *        parameters of this service context
	 */
	public void setAttributes(Map<String, Serializable> attributes) {
		_attributes = attributes;
	}

	/**
	 * Sets the value of the {@link
	 * com.liferay.portal.kernel.util.Constants#CMD} parameter used in most
	 * Liferay forms for internal portlets.
	 *
	 * @param command the value of the {@link
	 *        com.liferay.portal.kernel.util.Constants#CMD} parameter
	 */
	public void setCommand(String command) {
		_command = command;
	}

	/**
	 * Sets an array containing specific community permissions for a resource if
	 * this service context is being passed as a parameter to a method which
	 * manipulates the resource.
	 *
	 * @param      communityPermissions the community permissions (optionally
	 *             <code>null</code>)
	 * @deprecated As of 6.1, renamed to {@link #setGroupPermissions(String[])}
	 */
	public void setCommunityPermissions(String[] communityPermissions) {
		setGroupPermissions(communityPermissions);
	}

	/**
	 * Sets the company ID of this service context's current portal instance.
	 *
	 * @param companyId the primary key of this service context's current portal
	 *        instance
	 */
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	/**
	 * Sets the date when an entity was created if this service context is being
	 * passed as a parameter to a method which creates an entity.
	 *
	 * @param createDate the date the entity was created
	 */
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	/**
	 * Sets the current URL of this service context
	 *
	 * @param currentURL the current URL of this service context
	 */
	public void setCurrentURL(String currentURL) {
		_currentURL = currentURL;
	}

	public void setDeriveDefaultPermissions(boolean deriveDefaultPermissions) {
		_deriveDefaultPermissions = deriveDefaultPermissions;
	}

	/**
	 * Sets an arbitrary number of attributes of an entity to be persisted.
	 *
	 * <p>
	 * These attributes should only include fields that {@link
	 * com.liferay.portal.service.ServiceContext} does not possess by default.
	 * </p>
	 *
	 * @param expandoBridgeAttributes the expando bridge attributes (optionally
	 *        <code>null</code>)
	 */
	public void setExpandoBridgeAttributes(
		Map<String, Serializable> expandoBridgeAttributes) {

		_expandoBridgeAttributes = expandoBridgeAttributes;
	}

	/**
	 * Sets an array containing specific group permissions for a resource if
	 * this service context is being passed as a parameter to a method which
	 * manipulates the resource.
	 *
	 * @param groupPermissions the permissions (optionally <code>null</code>)
	 */
	public void setGroupPermissions(String[] groupPermissions) {
		_groupPermissions = groupPermissions;
	}

	/**
	 * Sets an array containing specific guest permissions for a resource if
	 * this service context is being passed as a parameter to a method which
	 * manipulates the resource.
	 *
	 * @param guestPermissions the guest permissions (optionally
	 *        <code>null</code>)
	 */
	public void setGuestPermissions(String[] guestPermissions) {
		_guestPermissions = guestPermissions;
	}

	/**
	 * Sets the map of request header name/value pairs of this service context.
	 *
	 * @param headers map of request header name/value pairs of this service
	 *        context
	 * @see   com.liferay.portal.kernel.servlet.HttpHeaders
	 */
	public void setHeaders(Map<String, String> headers) {
		_headers = headers;
	}

	/**
	 * Sets whether the primary entity of this service context is to be
	 * indexed/re-indexed.
	 *
	 * <p>
	 * The entity is only indexed/re-indexed if the method receiving this
	 * service context as a parameter does indexing.
	 * </p>
	 *
	 * @param indexingEnabled whether the primary entity of this service context
	 *        is to be indexed/re-indexed (default is <code>true</code>)
	 */
	public void setIndexingEnabled(boolean indexingEnabled) {
		_indexingEnabled = indexingEnabled;
	}

	/**
	 * Sets the language ID of the locale of this service context.
	 *
	 * @param languageId the language ID of the locale of this service context's
	 *        current user
	 */
	public void setLanguageId(String languageId) {
		_languageId = languageId;
	}

	/**
	 * Sets the complete URL of the current page for this service context.
	 *
	 * @param layoutFullURL the complete URL of the current page if a page
	 *        context can be determined for this service context
	 */
	public void setLayoutFullURL(String layoutFullURL) {
		_layoutFullURL = layoutFullURL;
	}

	/**
	 * Sets the relative URL of the current page for this service context.
	 *
	 * @param layoutURL the relative URL of the current page if a page context
	 *        can be determined for this service context
	 */
	public void setLayoutURL(String layoutURL) {
		_layoutURL = layoutURL;
	}

	/**
	 * Sets the date when an entity was modified in this service context.
	 *
	 * @param modifiedDate the date when an entity was modified in this service
	 *        context
	 */
	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	/**
	 * Sets the main context path of the portal, concatenated with
	 * <code>/c</code>.
	 *
	 * @param pathMain the main context path of the portal
	 */
	public void setPathMain(String pathMain) {
		_pathMain = pathMain;
	}

	/**
	 * Sets the portal layout ID of the current page in this service context.
	 *
	 * @param plid the portal layout ID of the current page
	 */
	public void setPlid(long plid) {
		_plid = plid;
	}

	/**
	 * Sets the URL of this service context's portal, including the protocol,
	 * domain, and non-default port relative to the company instance and any
	 * virtual host.
	 *
	 * <p>
	 * The URL should not include the port if a default port is used.
	 * </p>
	 *
	 * @param portalURL the portal URL
	 */
	public void setPortalURL(String portalURL) {
		_portalURL = portalURL;
	}

	/**
	 * Sets the portlet preferences IDs of the current portlet if this service
	 * context is being passed as a parameter to a portlet.
	 *
	 * <p>
	 * The {@link com.liferay.portal.model.PortletPreferencesIds} can be used to
	 * look up portlet preferences of the current portlet.
	 * </p>
	 *
	 * @param portletPreferencesIds the portlet preferences
	 * @see   com.liferay.portal.model.PortletPreferencesIds
	 */
	public void setPortletPreferencesIds(
		PortletPreferencesIds portletPreferencesIds) {

		_portletPreferencesIds = portletPreferencesIds;
	}

	/**
	 * Sets the remote address of the user making the request in this service
	 * context.
	 *
	 * @param remoteAddr the remote address of the user making the request in
	 *        this service context
	 */
	public void setRemoteAddr(String remoteAddr) {
		_remoteAddr = remoteAddr;
	}

	/**
	 * Sets the remote host name of the user making the request in this service
	 * context.
	 *
	 * @param remoteHost the remote host name of the user making the request in
	 *        this service context
	 */
	public void setRemoteHost(String remoteHost) {
		_remoteHost = remoteHost;
	}

	/**
	 * Sets the ID of the group corresponding to the current data scope of this
	 * service context.
	 *
	 * @param scopeGroupId the ID of the group corresponding to the current data
	 *        scope of this service context
	 * @see   com.liferay.portal.model.Group
	 */
	public void setScopeGroupId(long scopeGroupId) {
		_scopeGroupId = scopeGroupId;
	}

	/**
	 * Sets whether the sender of this service context's request is signed in.
	 *
	 * @param signedIn whether the sender of this service context's request is
	 *        signed in
	 */
	public void setSignedIn(boolean signedIn) {
		_signedIn = signedIn;
	}

	/**
	 * Sets the complete URL of this service context's current user's profile
	 * page.
	 *
	 * @param userDisplayURL the complete URL of the current user's profile page
	 */
	public void setUserDisplayURL(String userDisplayURL) {
		_userDisplayURL = userDisplayURL;
	}

	/**
	 * Sets the ID of this service context's current user.
	 *
	 * @param userId the ID of the current user
	 */
	public void setUserId(long userId) {
		_userId = userId;
	}

	/**
	 * Sets the UUID (universally unique identifier) of this service context's
	 * current entity.
	 *
	 * @param uuid the UUID (universally unique identifier) of the current
	 *        entity
	 */
	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	/**
	 * Sets the workflow action to take if this service context is being passed
	 * as parameter to a method that processes a workflow action.
	 *
	 * @param workflowAction workflow action to take (default is {@link
	 *        com.liferay.portal.kernel.workflow.WorkflowConstants.ACTION_PUBLISH})
	 */
	public void setWorkflowAction(int workflowAction) {
		_workflowAction = workflowAction;
	}

	public String translate(String pattern, Object... arguments) {
		Locale locale = getLocale();

		return LanguageUtil.format(locale, pattern, arguments);
	}

	private boolean _addGroupPermissions;
	private boolean _addGuestPermissions;
	private long[] _assetCategoryIds;
	private boolean _assetEntryVisible = true;
	private long[] _assetLinkEntryIds;
	private String[] _assetTagNames;
	private Map<String, Serializable> _attributes;
	private String _command;
	private long _companyId;
	private Date _createDate;
	private String _currentURL;
	private boolean _deriveDefaultPermissions;
	private Map<String, Serializable> _expandoBridgeAttributes;
	private String[] _groupPermissions;
	private String[] _guestPermissions;
	private Map<String, String> _headers;
	private boolean _indexingEnabled = true;
	private String _languageId;
	private String _layoutFullURL;
	private String _layoutURL;
	private Date _modifiedDate;
	private String _pathMain;
	private String _portalURL;
	private PortletPreferencesIds _portletPreferencesIds;
	private String _remoteAddr;
	private String _remoteHost;
	private long _scopeGroupId;
	private boolean _signedIn;
	private String _userDisplayURL;
	private long _plid;
	private int _workflowAction = WorkflowConstants.ACTION_PUBLISH;
	private long _userId;
	private String _uuid;

}