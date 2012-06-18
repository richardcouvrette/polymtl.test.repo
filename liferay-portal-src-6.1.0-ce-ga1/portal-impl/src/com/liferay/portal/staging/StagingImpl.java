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

package com.liferay.portal.staging;

import com.liferay.portal.LayoutSetBranchNameException;
import com.liferay.portal.NoSuchGroupException;
import com.liferay.portal.NoSuchLayoutBranchException;
import com.liferay.portal.NoSuchLayoutException;
import com.liferay.portal.NoSuchLayoutRevisionException;
import com.liferay.portal.RemoteExportException;
import com.liferay.portal.RemoteOptionsException;
import com.liferay.portal.kernel.cal.DayAndPosition;
import com.liferay.portal.kernel.cal.Duration;
import com.liferay.portal.kernel.cal.Recurrence;
import com.liferay.portal.kernel.cal.RecurrenceSerializer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.lar.PortletDataHandlerKeys;
import com.liferay.portal.kernel.lar.UserIdStrategy;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.MessageStatus;
import com.liferay.portal.kernel.staging.LayoutStagingUtil;
import com.liferay.portal.kernel.staging.Staging;
import com.liferay.portal.kernel.staging.StagingConstants;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.lar.LayoutExporter;
import com.liferay.portal.messaging.LayoutsLocalPublisherRequest;
import com.liferay.portal.messaging.LayoutsRemotePublisherRequest;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutBranch;
import com.liferay.portal.model.LayoutRevision;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.model.LayoutSetBranchConstants;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.HttpPrincipal;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutBranchLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.LayoutRevisionLocalServiceUtil;
import com.liferay.portal.service.LayoutServiceUtil;
import com.liferay.portal.service.LayoutSetBranchLocalServiceUtil;
import com.liferay.portal.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextThreadLocal;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.http.GroupServiceHttp;
import com.liferay.portal.service.http.LayoutServiceHttp;
import com.liferay.portal.service.permission.GroupPermissionUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.SessionClicks;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.PortalPreferences;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

import java.io.File;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Raymond Augé
 * @author Bruno Farache
 * @author Wesley Gong
 */
public class StagingImpl implements Staging {

	public String buildRemoteURL(
		String remoteAddress, int remotePort, boolean secureConnection,
		long remoteGroupId, boolean privateLayout) {

		StringBundler sb = new StringBundler((remoteGroupId > 0) ? 4 : 9);

		if (secureConnection) {
			sb.append(Http.HTTPS_WITH_SLASH);
		}
		else {
			sb.append(Http.HTTP_WITH_SLASH);
		}

		sb.append(remoteAddress);
		sb.append(StringPool.COLON);
		sb.append(remotePort);

		if (remoteGroupId > 0) {
			sb.append("/c/my_sites/view?");
			sb.append("groupId=");
			sb.append(remoteGroupId);
			sb.append("&amp;privateLayout=");
			sb.append(privateLayout);
		}

		return sb.toString();
	}

	public void copyFromLive(PortletRequest portletRequest) throws Exception {
		long stagingGroupId = ParamUtil.getLong(
			portletRequest, "stagingGroupId");

		Group stagingGroup = GroupLocalServiceUtil.getGroup(stagingGroupId);

		long liveGroupId = stagingGroup.getLiveGroupId();

		Map<String, String[]> parameterMap = getStagingParameters(
			portletRequest);

		publishLayouts(
			portletRequest, liveGroupId, stagingGroupId, parameterMap, false);
	}

	public void copyFromLive(PortletRequest portletRequest, Portlet portlet)
		throws Exception {

		long plid = ParamUtil.getLong(portletRequest, "plid");

		Layout targetLayout = LayoutLocalServiceUtil.getLayout(plid);

		Group stagingGroup = targetLayout.getGroup();
		Group liveGroup = stagingGroup.getLiveGroup();

		Layout sourceLayout = LayoutLocalServiceUtil.getLayoutByUuidAndGroupId(
			targetLayout.getUuid(), liveGroup.getGroupId());

		copyPortlet(
			portletRequest, liveGroup.getGroupId(), stagingGroup.getGroupId(),
			sourceLayout.getPlid(), targetLayout.getPlid(),
			portlet.getPortletId());
	}

	public void copyPortlet(
			PortletRequest portletRequest, long sourceGroupId,
			long targetGroupId, long sourcePlid, long targetPlid,
			String portletId)
		throws Exception {

		long userId = PortalUtil.getUserId(portletRequest);

		Map<String, String[]> parameterMap = getStagingParameters(
			portletRequest);

		File file = LayoutLocalServiceUtil.exportPortletInfoAsFile(
			sourcePlid, sourceGroupId, portletId, parameterMap, null, null);

		try {
			LayoutLocalServiceUtil.importPortletInfo(
				userId, targetPlid, targetGroupId, portletId, parameterMap,
				file);
		}
		finally {
			file.delete();
		}
	}

	public void copyRemoteLayouts(
			long sourceGroupId, boolean privateLayout,
			Map<Long, Boolean> layoutIdMap, Map<String, String[]> parameterMap,
			String remoteAddress, int remotePort, boolean secureConnection,
			long remoteGroupId, boolean remotePrivateLayout, Date startDate,
			Date endDate)
		throws Exception {

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		User user = UserLocalServiceUtil.getUser(permissionChecker.getUserId());

		StringBundler sb = new StringBundler(4);

		if (secureConnection) {
			sb.append(Http.HTTPS_WITH_SLASH);
		}
		else {
			sb.append(Http.HTTP_WITH_SLASH);
		}

		sb.append(remoteAddress);
		sb.append(StringPool.COLON);
		sb.append(remotePort);

		String url = sb.toString();

		HttpPrincipal httpPrincipal = new HttpPrincipal(
			url, user.getEmailAddress(), user.getPassword(),
			user.getPasswordEncrypted());

		// Ping remote host and verify that the group exists

		try {
			GroupServiceHttp.getGroup(httpPrincipal, remoteGroupId);
		}
		catch (NoSuchGroupException nsge) {
			RemoteExportException ree = new RemoteExportException(
				RemoteExportException.NO_GROUP);

			ree.setGroupId(remoteGroupId);

			throw ree;
		}
		catch (SystemException se) {
			RemoteExportException ree = new RemoteExportException(
				RemoteExportException.BAD_CONNECTION);

			ree.setURL(url);

			throw ree;
		}

		byte[] bytes = null;

		if (layoutIdMap == null) {
			bytes = LayoutLocalServiceUtil.exportLayouts(
				sourceGroupId, privateLayout, parameterMap, startDate, endDate);
		}
		else {
			List<Layout> layouts = new ArrayList<Layout>();

			Iterator<Map.Entry<Long, Boolean>> itr1 =
				layoutIdMap.entrySet().iterator();

			while (itr1.hasNext()) {
				Entry<Long, Boolean> entry = itr1.next();

				long plid = GetterUtil.getLong(String.valueOf(entry.getKey()));
				boolean includeChildren = entry.getValue();

				Layout layout = LayoutLocalServiceUtil.getLayout(plid);

				if (!layouts.contains(layout)) {
					layouts.add(layout);
				}

				Iterator<Layout> itr2 = getMissingParentLayouts(
					layout, sourceGroupId).iterator();

				while (itr2.hasNext()) {
					Layout parentLayout = itr2.next();

					if (!layouts.contains(parentLayout)) {
						layouts.add(parentLayout);
					}
				}

				if (includeChildren) {
					itr2 = layout.getAllChildren().iterator();

					while (itr2.hasNext()) {
						Layout childLayout = itr2.next();

						if (!layouts.contains(childLayout)) {
							layouts.add(childLayout);
						}
					}
				}
			}

			long[] layoutIds = new long[layouts.size()];

			for (int i = 0; i < layouts.size(); i++) {
				Layout curLayout = layouts.get(i);

				layoutIds[i] = curLayout.getLayoutId();
			}

			if (layoutIds.length <= 0) {
				throw new RemoteExportException(
					RemoteExportException.NO_LAYOUTS);
			}

			bytes = LayoutLocalServiceUtil.exportLayouts(
				sourceGroupId, privateLayout, layoutIds, parameterMap,
				startDate, endDate);
		}

		LayoutServiceHttp.importLayouts(
			httpPrincipal, remoteGroupId, remotePrivateLayout, parameterMap,
			bytes);
	}

	public void deleteLastImportSettings(Group liveGroup, boolean privateLayout)
		throws Exception {

		List<Layout> layouts = LayoutLocalServiceUtil.getLayouts(
			liveGroup.getGroupId(), privateLayout);

		for (Layout layout : layouts) {
			UnicodeProperties typeSettingsProperties =
				layout.getTypeSettingsProperties();

			Set<String> keys = new HashSet<String>();

			for (String key : typeSettingsProperties.keySet()) {
				if (key.startsWith("last-import-")) {
					keys.add(key);
				}
			}

			if (keys.isEmpty()) {
				continue;
			}

			for (String key : keys) {
				typeSettingsProperties.remove(key);
			}

			LayoutLocalServiceUtil.updateLayout(
				layout.getGroupId(), layout.getPrivateLayout(),
				layout.getLayoutId(), typeSettingsProperties.toString());
		}
	}

	public void deleteRecentLayoutRevisionId(
			HttpServletRequest request, long layoutSetBranchId, long plid)
		throws SystemException {

		PortalPreferences portalPreferences =
			PortletPreferencesFactoryUtil.getPortalPreferences(request);

		deleteRecentLayoutRevisionId(
			portalPreferences, layoutSetBranchId, plid);
	}

	public void deleteRecentLayoutRevisionId(
			User user, long layoutSetBranchId, long plid)
		throws SystemException {

		PortalPreferences portalPreferences = getPortalPreferences(user);

		deleteRecentLayoutRevisionId(
			portalPreferences, layoutSetBranchId, plid);
	}

	public void disableStaging(
			Group scopeGroup, Group liveGroup, ServiceContext serviceContext)
		throws Exception {

		disableStaging(null, scopeGroup, liveGroup, serviceContext);
	}

	public void disableStaging(
			PortletRequest portletRequest, Group scopeGroup, Group liveGroup,
			ServiceContext serviceContext)
		throws Exception {

		UnicodeProperties typeSettingsProperties =
			liveGroup.getTypeSettingsProperties();

		typeSettingsProperties.remove("branchingPrivate");
		typeSettingsProperties.remove("branchingPublic");
		typeSettingsProperties.remove("remoteAddress");
		typeSettingsProperties.remove("remoteGroupId");
		typeSettingsProperties.remove("remotePort");
		typeSettingsProperties.remove("secureConnection");
		typeSettingsProperties.remove("staged");
		typeSettingsProperties.remove("stagedRemotely");

		Set<String> keys = new HashSet<String>();

		for (String key : typeSettingsProperties.keySet()) {
			if (key.startsWith(StagingConstants.STAGED_PORTLET)) {
				keys.add(key);
			}
		}

		for (String key : keys) {
			typeSettingsProperties.remove(key);
		}

		deleteLastImportSettings(liveGroup, true);
		deleteLastImportSettings(liveGroup, false);

		if (liveGroup.hasStagingGroup()) {
			Group stagingGroup = liveGroup.getStagingGroup();

			LayoutSetBranchLocalServiceUtil.deleteLayoutSetBranches(
				stagingGroup.getGroupId(), true, true);
			LayoutSetBranchLocalServiceUtil.deleteLayoutSetBranches(
				stagingGroup.getGroupId(), false, true);

			GroupLocalServiceUtil.deleteGroup(stagingGroup.getGroupId());
		}
		else {
			LayoutSetBranchLocalServiceUtil.deleteLayoutSetBranches(
				liveGroup.getGroupId(), true, true);
			LayoutSetBranchLocalServiceUtil.deleteLayoutSetBranches(
				liveGroup.getGroupId(), false, true);
		}

		GroupLocalServiceUtil.updateGroup(
			liveGroup.getGroupId(), typeSettingsProperties.toString());
	}

	public void enableLocalStaging(
			long userId, Group scopeGroup, Group liveGroup,
			boolean branchingPublic, boolean branchingPrivate,
			ServiceContext serviceContext)
		throws Exception {

		if (liveGroup.isStagedRemotely()) {
			disableStaging(scopeGroup, liveGroup, serviceContext);
		}

		UnicodeProperties typeSettingsProperties =
			liveGroup.getTypeSettingsProperties();

		typeSettingsProperties.setProperty(
			"branchingPrivate", String.valueOf(branchingPrivate));
		typeSettingsProperties.setProperty(
			"branchingPublic", String.valueOf(branchingPublic));
		typeSettingsProperties.setProperty("staged", Boolean.TRUE.toString());
		typeSettingsProperties.setProperty(
			"stagedRemotely", String.valueOf(false));

		setCommonStagingOptions(
			liveGroup, typeSettingsProperties, serviceContext);

		if (!liveGroup.hasStagingGroup()) {
			serviceContext.setAttribute("staging", String.valueOf(true));

			Group stagingGroup = GroupLocalServiceUtil.addGroup(
				userId, liveGroup.getClassName(), liveGroup.getClassPK(),
				liveGroup.getGroupId(), liveGroup.getDescriptiveName(),
				liveGroup.getDescription(), liveGroup.getType(),
				liveGroup.getFriendlyURL(), false, liveGroup.isActive(),
				serviceContext);

			GroupLocalServiceUtil.updateGroup(
				liveGroup.getGroupId(), typeSettingsProperties.toString());

			if (liveGroup.hasPrivateLayouts()) {
				Map<String, String[]> parameterMap = getStagingParameters();

				publishLayouts(
					userId, liveGroup.getGroupId(), stagingGroup.getGroupId(),
					true, parameterMap, null, null);
			}

			if (liveGroup.hasPublicLayouts()) {
				Map<String, String[]> parameterMap = getStagingParameters();

				publishLayouts(
					userId, liveGroup.getGroupId(), stagingGroup.getGroupId(),
					false, parameterMap, null, null);
			}

			checkDefaultLayoutSetBranches(
				userId, liveGroup, branchingPublic, branchingPrivate, false,
				serviceContext);
		}
		else {
			GroupLocalServiceUtil.updateGroup(
				liveGroup.getGroupId(), typeSettingsProperties.toString());

			checkDefaultLayoutSetBranches(
				userId, liveGroup, branchingPublic, branchingPrivate, false,
				serviceContext);

			if (!branchingPublic) {
				LayoutSetBranchLocalServiceUtil.deleteLayoutSetBranches(
					liveGroup.getStagingGroup().getGroupId(), false, true);
			}

			if (!branchingPrivate) {
				LayoutSetBranchLocalServiceUtil.deleteLayoutSetBranches(
					liveGroup.getStagingGroup().getGroupId(), true, true);
			}
		}
	}

	public void enableRemoteStaging(
			long userId, Group scopeGroup, Group liveGroup,
			boolean branchingPublic, boolean branchingPrivate,
			String remoteAddress, long remoteGroupId, int remotePort,
			boolean secureConnection, ServiceContext serviceContext)
		throws Exception {

		validate(remoteAddress, remoteGroupId, remotePort, secureConnection);

		if (liveGroup.hasStagingGroup()) {
			disableStaging(scopeGroup, liveGroup, serviceContext);
		}

		UnicodeProperties typeSettingsProperties =
			liveGroup.getTypeSettingsProperties();

		typeSettingsProperties.setProperty(
			"branchingPrivate", String.valueOf(branchingPrivate));
		typeSettingsProperties.setProperty(
			"branchingPublic", String.valueOf(branchingPublic));
		typeSettingsProperties.setProperty("remoteAddress", remoteAddress);
		typeSettingsProperties.setProperty(
			"remoteGroupId", String.valueOf(remoteGroupId));
		typeSettingsProperties.setProperty(
			"remotePort", String.valueOf(remotePort));
		typeSettingsProperties.setProperty(
			"secureConnection", String.valueOf(secureConnection));
		typeSettingsProperties.setProperty("staged", Boolean.TRUE.toString());
		typeSettingsProperties.setProperty(
			"stagedRemotely", Boolean.TRUE.toString());

		setCommonStagingOptions(
			liveGroup, typeSettingsProperties, serviceContext);

		GroupLocalServiceUtil.updateGroup(
			liveGroup.getGroupId(), typeSettingsProperties.toString());

		checkDefaultLayoutSetBranches(
			userId, liveGroup, branchingPublic, branchingPrivate, true,
			serviceContext);
	}

	public Group getLiveGroup(long groupId)
		throws PortalException, SystemException {

		if (groupId == 0) {
			return null;
		}

		Group group = GroupLocalServiceUtil.getGroup(groupId);

		if (group.isLayout()) {
			group = group.getParentGroup();
		}

		if (group.isStagingGroup()) {
			return group.getLiveGroup();
		}
		else {
			return group;
		}
	}

	public long getLiveGroupId(long groupId)
		throws PortalException, SystemException {

		if (groupId == 0) {
			return groupId;
		}

		Group group = getLiveGroup(groupId);

		return group.getGroupId();
	}

	public List<Layout> getMissingParentLayouts(Layout layout, long liveGroupId)
		throws Exception {

		List<Layout> missingParentLayouts = new ArrayList<Layout>();

		long parentLayoutId = layout.getParentLayoutId();

		Layout parentLayout = null;

		while (parentLayoutId > 0) {
			parentLayout = LayoutLocalServiceUtil.getLayout(
				layout.getGroupId(), layout.isPrivateLayout(),
				parentLayoutId);

			try {
				LayoutLocalServiceUtil.getLayoutByUuidAndGroupId(
					parentLayout.getUuid(), liveGroupId);

				// If one parent is found all others are assumed to exist

				break;
			}
			catch (NoSuchLayoutException nsle) {
				missingParentLayouts.add(parentLayout);

				parentLayoutId = parentLayout.getParentLayoutId();
			}
		}

		return missingParentLayouts;
	}

	public long getRecentLayoutRevisionId(
			HttpServletRequest request, long layoutSetBranchId, long plid)
		throws PortalException, SystemException {

		PortalPreferences portalPreferences =
			PortletPreferencesFactoryUtil.getPortalPreferences(request);

		return getRecentLayoutRevisionId(
			portalPreferences, layoutSetBranchId, plid);
	}

	public long getRecentLayoutRevisionId(
			User user, long layoutSetBranchId, long plid)
		throws PortalException, SystemException {

		PortalPreferences portalPreferences = getPortalPreferences(user);

		return getRecentLayoutRevisionId(
			portalPreferences, layoutSetBranchId, plid);
	}

	public long getRecentLayoutSetBranchId(
		HttpServletRequest request, long layoutSetId) {

		return GetterUtil.getLong(
			SessionClicks.get(
				request, Staging.class.getName(),
				getRecentLayoutSetBranchIdKey(layoutSetId)));
	}

	public long getRecentLayoutSetBranchId(User user, long layoutSetId)
		throws SystemException {

		PortalPreferences portalPreferences = getPortalPreferences(user);

		return GetterUtil.getLong(
			portalPreferences.getValue(
				Staging.class.getName(),
				getRecentLayoutSetBranchIdKey(layoutSetId)));
	}

	public String getSchedulerGroupName(String destinationName, long groupId) {
		return destinationName.concat(StringPool.SLASH).concat(
			String.valueOf(groupId));
	}

	public Map<String, String[]> getStagingParameters() {
		Map<String, String[]> parameterMap =
			new LinkedHashMap<String, String[]>();

		parameterMap.put(
			PortletDataHandlerKeys.CATEGORIES,
			new String[] {Boolean.TRUE.toString()});
		parameterMap.put(
			PortletDataHandlerKeys.DATA_STRATEGY,
			new String[] {
				PortletDataHandlerKeys.DATA_STRATEGY_MIRROR_OVERWRITE});
		parameterMap.put(
			PortletDataHandlerKeys.DELETE_MISSING_LAYOUTS,
			new String[] {Boolean.TRUE.toString()});
		parameterMap.put(
			PortletDataHandlerKeys.DELETE_PORTLET_DATA,
			new String[] {Boolean.FALSE.toString()});
		parameterMap.put(
			PortletDataHandlerKeys.IGNORE_LAST_PUBLISH_DATE,
			new String[] {Boolean.TRUE.toString()});
		parameterMap.put(
			PortletDataHandlerKeys.PERMISSIONS,
			new String[] {Boolean.TRUE.toString()});
		parameterMap.put(
			PortletDataHandlerKeys.PORTLET_DATA,
			new String[] {Boolean.TRUE.toString()});
		parameterMap.put(
			PortletDataHandlerKeys.PORTLET_DATA_ALL,
			new String[] {Boolean.TRUE.toString()});
		parameterMap.put(
			PortletDataHandlerKeys.PORTLET_SETUP,
			new String[] {Boolean.TRUE.toString()});
		parameterMap.put(
			PortletDataHandlerKeys.PORTLET_USER_PREFERENCES,
			new String[] {Boolean.TRUE.toString()});
		parameterMap.put(
			PortletDataHandlerKeys.THEME,
			new String[] {Boolean.FALSE.toString()});
		parameterMap.put(
			PortletDataHandlerKeys.THEME_REFERENCE,
			new String[] {Boolean.TRUE.toString()});
		parameterMap.put(
			PortletDataHandlerKeys.UPDATE_LAST_PUBLISH_DATE,
			new String[] {Boolean.TRUE.toString()});
		parameterMap.put(
			PortletDataHandlerKeys.USER_ID_STRATEGY,
			new String[] {UserIdStrategy.CURRENT_USER_ID});
		parameterMap.put(
			PortletDataHandlerKeys.USER_PERMISSIONS,
			new String[] {Boolean.FALSE.toString()});

		return parameterMap;
	}

	public Map<String, String[]> getStagingParameters(
		PortletRequest portletRequest) {

		Map<String, String[]> parameterMap =
			new LinkedHashMap<String, String[]>(
				portletRequest.getParameterMap());

		if (!parameterMap.containsKey(PortletDataHandlerKeys.DATA_STRATEGY)) {
			parameterMap.put(
				PortletDataHandlerKeys.DATA_STRATEGY,
				new String[] {
					PortletDataHandlerKeys.DATA_STRATEGY_MIRROR_OVERWRITE});
		}

		/*if (!parameterMap.containsKey(
				PortletDataHandlerKeys.DELETE_MISSING_LAYOUTS)) {

			parameterMap.put(
				PortletDataHandlerKeys.DELETE_MISSING_LAYOUTS,
				new String[] {Boolean.TRUE.toString()});
		}*/

		if (!parameterMap.containsKey(
				PortletDataHandlerKeys.DELETE_PORTLET_DATA)) {

			parameterMap.put(
				PortletDataHandlerKeys.DELETE_PORTLET_DATA,
				new String[] {Boolean.FALSE.toString()});
		}

		if (!parameterMap.containsKey(PortletDataHandlerKeys.PORTLET_DATA)) {
			parameterMap.put(
				PortletDataHandlerKeys.PORTLET_DATA,
				new String[] {Boolean.FALSE.toString()});
		}

		if (!parameterMap.containsKey(
				PortletDataHandlerKeys.PORTLET_DATA_ALL)) {

			parameterMap.put(
				PortletDataHandlerKeys.PORTLET_DATA_ALL,
				new String[] {Boolean.FALSE.toString()});
		}

		if (!parameterMap.containsKey(PortletDataHandlerKeys.PORTLET_SETUP)) {
			parameterMap.put(
				PortletDataHandlerKeys.PORTLET_SETUP,
				new String[] {Boolean.TRUE.toString()});
		}

		if (!parameterMap.containsKey(
				PortletDataHandlerKeys.PORTLET_USER_PREFERENCES)) {

			parameterMap.put(
				PortletDataHandlerKeys.PORTLET_USER_PREFERENCES,
				new String[] {Boolean.TRUE.toString()});
		}

		if (!parameterMap.containsKey(PortletDataHandlerKeys.THEME)) {
			parameterMap.put(
				PortletDataHandlerKeys.THEME,
				new String[] {Boolean.FALSE.toString()});
		}

		if (!parameterMap.containsKey(PortletDataHandlerKeys.THEME_REFERENCE)) {
			parameterMap.put(
				PortletDataHandlerKeys.THEME_REFERENCE,
				new String[] {Boolean.FALSE.toString()});
		}

		if (!parameterMap.containsKey(
				PortletDataHandlerKeys.UPDATE_LAST_PUBLISH_DATE)) {

			parameterMap.put(
				PortletDataHandlerKeys.UPDATE_LAST_PUBLISH_DATE,
				new String[] {Boolean.TRUE.toString()});
		}

		if (!parameterMap.containsKey(
				PortletDataHandlerKeys.USER_ID_STRATEGY)) {

			parameterMap.put(
				PortletDataHandlerKeys.USER_ID_STRATEGY,
				new String[] {UserIdStrategy.CURRENT_USER_ID});
		}

		return parameterMap;
	}

	public boolean isIncomplete(Layout layout, long layoutSetBranchId) {
		LayoutRevision layoutRevision = LayoutStagingUtil.getLayoutRevision(
			layout);

		if (layoutRevision == null) {
			try {
				layoutRevision =
					LayoutRevisionLocalServiceUtil.getLayoutRevision(
						layoutSetBranchId, layout.getPlid(), true);

				return false;
			}
			catch (Exception e) {
			}
		}

		try {
			layoutRevision = LayoutRevisionLocalServiceUtil.getLayoutRevision(
				layoutSetBranchId, layout.getPlid(), false);
		}
		catch (Exception e) {
		}

		if (layoutRevision == null ||
			(layoutRevision.getStatus() ==
				WorkflowConstants.STATUS_INCOMPLETE)) {

			return true;
		}

		return false;
	}

	public void publishLayout(
			long userId, long plid, long liveGroupId, boolean includeChildren)
		throws Exception {

		Map<String, String[]> parameterMap = getStagingParameters();

		parameterMap.put(
			PortletDataHandlerKeys.DELETE_MISSING_LAYOUTS,
			new String[] {Boolean.FALSE.toString()});

		Layout layout = LayoutLocalServiceUtil.getLayout(plid);

		List<Layout> layouts = new ArrayList<Layout>();

		layouts.add(layout);

		layouts.addAll(getMissingParentLayouts(layout, liveGroupId));

		if (includeChildren) {
			layouts.addAll(layout.getAllChildren());
		}

		Iterator<Layout> itr = layouts.iterator();

		long[] layoutIds = new long[layouts.size()];

		for (int i = 0; itr.hasNext(); i++) {
			Layout curLayout = itr.next();

			layoutIds[i] = curLayout.getLayoutId();
		}

		publishLayouts(
			userId, layout.getGroupId(), liveGroupId, layout.isPrivateLayout(),
			layoutIds, parameterMap, null, null);
	}

	public void publishLayouts(
			long userId, long sourceGroupId, long targetGroupId,
			boolean privateLayout, long[] layoutIds,
			Map<String, String[]> parameterMap, Date startDate, Date endDate)
		throws Exception {

		parameterMap.put(
			PortletDataHandlerKeys.PERFORM_DIRECT_BINARY_IMPORT,
			new String[] {Boolean.TRUE.toString()});

		File file = LayoutLocalServiceUtil.exportLayoutsAsFile(
			sourceGroupId, privateLayout, layoutIds, parameterMap, startDate,
			endDate);

		try {
			LayoutLocalServiceUtil.importLayouts(
				userId, targetGroupId, privateLayout, parameterMap, file);
		}
		finally {
			file.delete();
		}
	}

	public void publishLayouts(
			long userId, long sourceGroupId, long targetGroupId,
			boolean privateLayout, Map<Long, Boolean> layoutIdMap,
			Map<String, String[]> parameterMap, Date startDate, Date endDate)
		throws Exception {

		List<Layout> layouts = new ArrayList<Layout>();

		Iterator<Map.Entry<Long, Boolean>> itr1 =
			layoutIdMap.entrySet().iterator();

		while (itr1.hasNext()) {
			Entry<Long, Boolean> entry = itr1.next();

			long plid = GetterUtil.getLong(String.valueOf(entry.getKey()));
			boolean includeChildren = entry.getValue();

			Layout layout = LayoutLocalServiceUtil.getLayout(plid);

			if (!layouts.contains(layout)) {
				layouts.add(layout);
			}

			Iterator<Layout> itr2 = getMissingParentLayouts(
				layout, targetGroupId).iterator();

			while (itr2.hasNext()) {
				Layout parentLayout = itr2.next();

				if (!layouts.contains(parentLayout)) {
					layouts.add(parentLayout);
				}
			}

			if (includeChildren) {
				itr2 = layout.getAllChildren().iterator();

				while (itr2.hasNext()) {
					Layout childLayout = itr2.next();

					if (!layouts.contains(childLayout)) {
						layouts.add(childLayout);
					}
				}
			}
		}

		long[] layoutIds = new long[layouts.size()];

		for (int i = 0; i < layouts.size(); i++) {
			Layout curLayout = layouts.get(i);

			layoutIds[i] = curLayout.getLayoutId();
		}

		publishLayouts(
			userId, sourceGroupId, targetGroupId, privateLayout, layoutIds,
			parameterMap, startDate, endDate);
	}

	public void publishLayouts(
			long userId, long sourceGroupId, long targetGroupId,
			boolean privateLayout, Map<String, String[]> parameterMap,
			Date startDate, Date endDate)
		throws Exception {

		publishLayouts(
			userId, sourceGroupId, targetGroupId, privateLayout, (long[])null,
			parameterMap, startDate, endDate);
	}

	public void publishToLive(PortletRequest portletRequest) throws Exception {
		long groupId = ParamUtil.getLong(portletRequest, "groupId");

		Group liveGroup = GroupLocalServiceUtil.getGroup(groupId);

		Map<String, String[]> parameterMap = getStagingParameters(
			portletRequest);

		if (liveGroup.isStaged()) {
			if (liveGroup.isStagedRemotely()) {
				publishToRemote(portletRequest);
			}
			else {
				Group stagingGroup = liveGroup.getStagingGroup();

				publishLayouts(
					portletRequest, stagingGroup.getGroupId(), groupId,
					parameterMap, false);
			}
		}
	}

	public void publishToLive(PortletRequest portletRequest, Portlet portlet)
		throws Exception {

		long plid = ParamUtil.getLong(portletRequest, "plid");

		Layout sourceLayout = LayoutLocalServiceUtil.getLayout(plid);

		Group stagingGroup = null;
		Group liveGroup = null;

		Layout targetLayout = null;

		long scopeGroupId = PortalUtil.getScopeGroupId(portletRequest);

		if (sourceLayout.hasScopeGroup() &&
			(sourceLayout.getScopeGroup().getGroupId() == scopeGroupId)) {

			stagingGroup = sourceLayout.getScopeGroup();
			liveGroup = stagingGroup.getLiveGroup();

			targetLayout = LayoutLocalServiceUtil.getLayout(
				liveGroup.getClassPK());
		}
		else {
			stagingGroup = sourceLayout.getGroup();
			liveGroup = stagingGroup.getLiveGroup();

			targetLayout = LayoutLocalServiceUtil.getLayoutByUuidAndGroupId(
				sourceLayout.getUuid(), liveGroup.getGroupId());
		}

		copyPortlet(
			portletRequest, stagingGroup.getGroupId(), liveGroup.getGroupId(),
			sourceLayout.getPlid(), targetLayout.getPlid(),
			portlet.getPortletId());
	}

	public void publishToRemote(PortletRequest portletRequest)
		throws Exception {

		publishToRemote(portletRequest, false);
	}

	public void scheduleCopyFromLive(PortletRequest portletRequest)
		throws Exception {

		long stagingGroupId = ParamUtil.getLong(
			portletRequest, "stagingGroupId");

		Group stagingGroup = GroupLocalServiceUtil.getGroup(stagingGroupId);

		long liveGroupId = stagingGroup.getLiveGroupId();

		Map<String, String[]> parameterMap = getStagingParameters(
			portletRequest);

		publishLayouts(
			portletRequest, liveGroupId, stagingGroupId, parameterMap, true);
	}

	public void schedulePublishToLive(PortletRequest portletRequest)
		throws Exception {

		long stagingGroupId = ParamUtil.getLong(
			portletRequest, "stagingGroupId");

		Group stagingGroup = GroupLocalServiceUtil.getGroup(stagingGroupId);

		long liveGroupId = stagingGroup.getLiveGroupId();

		Map<String, String[]> parameterMap = getStagingParameters(
			portletRequest);

		publishLayouts(
			portletRequest, stagingGroupId, liveGroupId, parameterMap, true);
	}

	public void schedulePublishToRemote(PortletRequest portletRequest)
		throws Exception {

		publishToRemote(portletRequest, true);
	}

	public void setRecentLayoutBranchId(
		HttpServletRequest request, long layoutSetBranchId, long plid,
		long layoutBranchId)
		throws SystemException {

		PortalPreferences portalPreferences =
			PortletPreferencesFactoryUtil.getPortalPreferences(request);

		setRecentLayoutBranchId(
			portalPreferences, layoutSetBranchId, plid, layoutBranchId);
	}

	public void setRecentLayoutBranchId(
		User user, long layoutSetBranchId, long plid, long layoutBranchId)
		throws SystemException {

		PortalPreferences portalPreferences = getPortalPreferences(user);

		setRecentLayoutBranchId(
			portalPreferences, layoutSetBranchId, plid, layoutBranchId);
	}

	public void setRecentLayoutRevisionId(
			HttpServletRequest request, long layoutSetBranchId, long plid,
			long layoutRevisionId)
		throws SystemException {

		PortalPreferences portalPreferences =
			PortletPreferencesFactoryUtil.getPortalPreferences(request);

		setRecentLayoutRevisionId(
			portalPreferences, layoutSetBranchId, plid, layoutRevisionId);
	}

	public void setRecentLayoutRevisionId(
			User user, long layoutSetBranchId, long plid, long layoutRevisionId)
		throws SystemException {

		PortalPreferences portalPreferences = getPortalPreferences(user);

		setRecentLayoutRevisionId(
			portalPreferences, layoutSetBranchId, plid, layoutRevisionId);
	}

	public void setRecentLayoutSetBranchId(
		HttpServletRequest request, long layoutSetId, long layoutSetBranchId) {

		SessionClicks.put(
			request, Staging.class.getName(),
			getRecentLayoutSetBranchIdKey(layoutSetId),
			String.valueOf(layoutSetBranchId));
	}

	public void setRecentLayoutSetBranchId(
			User user, long layoutSetId, long layoutSetBranchId)
		throws SystemException {

		PortalPreferences portalPreferences = getPortalPreferences(user);

		portalPreferences.setValue(
			Staging.class.getName(), getRecentLayoutSetBranchIdKey(layoutSetId),
			String.valueOf(layoutSetBranchId));
	}

	public void unscheduleCopyFromLive(PortletRequest portletRequest)
		throws Exception {

		long stagingGroupId = ParamUtil.getLong(
			portletRequest, "stagingGroupId");

		String jobName = ParamUtil.getString(portletRequest, "jobName");
		String groupName = getSchedulerGroupName(
			DestinationNames.LAYOUTS_LOCAL_PUBLISHER, stagingGroupId);

		LayoutServiceUtil.unschedulePublishToLive(
			stagingGroupId, jobName, groupName);
	}

	public void unschedulePublishToLive(PortletRequest portletRequest)
		throws Exception {

		long stagingGroupId = ParamUtil.getLong(
			portletRequest, "stagingGroupId");

		Group stagingGroup = GroupLocalServiceUtil.getGroup(stagingGroupId);

		long liveGroupId = stagingGroup.getLiveGroupId();

		String jobName = ParamUtil.getString(portletRequest, "jobName");
		String groupName = getSchedulerGroupName(
			DestinationNames.LAYOUTS_LOCAL_PUBLISHER, liveGroupId);

		LayoutServiceUtil.unschedulePublishToLive(
			liveGroupId, jobName, groupName);
	}

	public void unschedulePublishToRemote(PortletRequest portletRequest)
		throws Exception {

		long groupId = ParamUtil.getLong(portletRequest, "groupId");

		String jobName = ParamUtil.getString(portletRequest, "jobName");
		String groupName = getSchedulerGroupName(
			DestinationNames.LAYOUTS_REMOTE_PUBLISHER, groupId);

		LayoutServiceUtil.unschedulePublishToRemote(
			groupId, jobName, groupName);
	}

	public void updateLastImportSettings(
			Element layoutElement, Layout layout,
			PortletDataContext portletDataContext)
		throws Exception {

		Map<String, String[]> parameterMap =
			portletDataContext.getParameterMap();

		String cmd = MapUtil.getString(parameterMap, "cmd");

		if (!cmd.equals("publish_to_live")) {
			return;
		}

		UnicodeProperties typeSettingsProperties =
			layout.getTypeSettingsProperties();

		typeSettingsProperties.setProperty(
			"last-import-date", String.valueOf(System.currentTimeMillis()));

		String layoutRevisionId = GetterUtil.getString(
			layoutElement.attributeValue("layout-revision-id"));

		typeSettingsProperties.setProperty(
			"last-import-layout-revision-id", layoutRevisionId);

		String layoutSetBranchId = MapUtil.getString(
			parameterMap, "layoutSetBranchId");

		typeSettingsProperties.setProperty(
			"last-import-layout-set-branch-id", layoutSetBranchId);

		String layoutSetBranchName = MapUtil.getString(
			parameterMap, "layoutSetBranchName");

		typeSettingsProperties.setProperty(
			"last-import-layout-set-branch-name", layoutSetBranchName);

		String lastImportUserName = MapUtil.getString(
			parameterMap, "lastImportUserName");

		typeSettingsProperties.setProperty(
			"last-import-user-name", lastImportUserName);

		String lastImportUserUuid = MapUtil.getString(
			parameterMap, "lastImportUserUuid");

		typeSettingsProperties.setProperty(
			"last-import-user-uuid", lastImportUserUuid);

		String layoutBranchId = GetterUtil.getString(
			layoutElement.attributeValue("layout-branch-id"));

		typeSettingsProperties.setProperty(
			"last-import-layout-branch-id", layoutBranchId);

		String layoutBranchName = GetterUtil.getString(
			layoutElement.attributeValue("layout-branch-name"));

		typeSettingsProperties.setProperty(
			"last-import-layout-branch-name", layoutBranchName);

		layout.setTypeSettingsProperties(typeSettingsProperties);
	}

	public void updateStaging(PortletRequest portletRequest, Group liveGroup)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		long userId = permissionChecker.getUserId();

		Group scopeGroup = themeDisplay.getScopeGroup();

		if (!GroupPermissionUtil.contains(
				permissionChecker, liveGroup.getGroupId(),
				ActionKeys.MANAGE_STAGING)) {

			return;
		}

		int stagingType = ParamUtil.getInteger(portletRequest, "stagingType");

		boolean branchingPublic = ParamUtil.getBoolean(
			portletRequest, "branchingPublic");
		boolean branchingPrivate = ParamUtil.getBoolean(
			portletRequest, "branchingPrivate");

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		if (stagingType == StagingConstants.TYPE_NOT_STAGED) {
			if (liveGroup.hasStagingGroup() || liveGroup.isStagedRemotely()) {
				disableStaging(
					portletRequest, scopeGroup, liveGroup, serviceContext);
			}
		}
		else if (stagingType == StagingConstants.TYPE_LOCAL_STAGING) {
			enableLocalStaging(
				userId, scopeGroup, liveGroup, branchingPublic,
				branchingPrivate, serviceContext);
		}
		else if (stagingType == StagingConstants.TYPE_REMOTE_STAGING) {
			String remoteAddress = ParamUtil.getString(
				portletRequest, "remoteAddress");

			remoteAddress = stripProtocolFromRemoteAddress(remoteAddress);

			long remoteGroupId = ParamUtil.getLong(
				portletRequest, "remoteGroupId");
			int remotePort = ParamUtil.getInteger(portletRequest, "remotePort");
			boolean secureConnection = ParamUtil.getBoolean(
				portletRequest, "secureConnection");

			enableRemoteStaging(
				userId, scopeGroup, liveGroup, branchingPublic,
				branchingPrivate, remoteAddress, remoteGroupId, remotePort,
				secureConnection, serviceContext);
		}
	}

	protected void addWeeklyDayPos(
		PortletRequest portletRequest, List<DayAndPosition> list, int day) {

		if (ParamUtil.getBoolean(portletRequest, "weeklyDayPos" + day)) {
			list.add(new DayAndPosition(day, 0));
		}
	}

	protected void checkDefaultLayoutSetBranches(
			long userId, Group liveGroup, boolean branchingPublic,
			boolean branchingPrivate, boolean remote,
			ServiceContext serviceContext)
		throws Exception {

		long targetGroupId = 0;

		if (remote) {
			targetGroupId = liveGroup.getGroupId();
		}
		else {
			Group stagingGroup = liveGroup.getStagingGroup();

			if (stagingGroup == null) {
				return;
			}

			targetGroupId = stagingGroup.getGroupId();
		}

		if (branchingPublic) {
			Locale locale = LocaleUtil.getDefault();

			String description = LanguageUtil.format(
				locale,
				LayoutSetBranchConstants.MASTER_BRANCH_DESCRIPTION_PUBLIC,
				liveGroup.getDescriptiveName());

			try {
				LayoutSetBranchLocalServiceUtil.addLayoutSetBranch(
					userId, targetGroupId, false,
					LayoutSetBranchConstants.MASTER_BRANCH_NAME,
					description, true, LayoutSetBranchConstants.ALL_BRANCHES,
					serviceContext);
			}
			catch (LayoutSetBranchNameException lsbne) {
			}
		}

		if (branchingPrivate) {
			Locale locale = LocaleUtil.getDefault();

			String description = LanguageUtil.format(
				locale,
				LayoutSetBranchConstants.MASTER_BRANCH_DESCRIPTION_PRIVATE,
				liveGroup.getDescriptiveName());

			try {
				LayoutSetBranchLocalServiceUtil.addLayoutSetBranch(
					userId, targetGroupId, true,
					LayoutSetBranchConstants.MASTER_BRANCH_NAME,
					description, true, LayoutSetBranchConstants.ALL_BRANCHES,
					serviceContext);
			}
			catch (LayoutSetBranchNameException lsbne) {
			}
		}
	}

	protected void deleteRecentLayoutRevisionId(
		PortalPreferences portalPreferences, long layoutSetBranchId,
		long plid) {

		portalPreferences.setValue(
			Staging.class.getName(),
			getRecentLayoutRevisionIdKey(layoutSetBranchId, plid), null);
	}

	protected String getCronText(
			PortletRequest portletRequest, Calendar startDate,
			boolean timeZoneSensitive, int recurrenceType)
		throws Exception {

		Calendar startCal = null;

		if (timeZoneSensitive) {
			startCal = CalendarFactoryUtil.getCalendar();

			startCal.setTime(startDate.getTime());
		}
		else {
			startCal = (Calendar)startDate.clone();
		}

		Recurrence recurrence = new Recurrence(
			startCal, new Duration(1, 0, 0, 0), recurrenceType);

		recurrence.setWeekStart(Calendar.SUNDAY);

		if (recurrenceType == Recurrence.DAILY) {
			int dailyType = ParamUtil.getInteger(portletRequest, "dailyType");

			if (dailyType == 0) {
				int dailyInterval = ParamUtil.getInteger(
					portletRequest, "dailyInterval", 1);

				recurrence.setInterval(dailyInterval);
			}
			else {
				DayAndPosition[] dayPos = {
					new DayAndPosition(Calendar.MONDAY, 0),
					new DayAndPosition(Calendar.TUESDAY, 0),
					new DayAndPosition(Calendar.WEDNESDAY, 0),
					new DayAndPosition(Calendar.THURSDAY, 0),
					new DayAndPosition(Calendar.FRIDAY, 0)};

				recurrence.setByDay(dayPos);
			}
		}
		else if (recurrenceType == Recurrence.WEEKLY) {
			int weeklyInterval = ParamUtil.getInteger(
				portletRequest, "weeklyInterval", 1);

			recurrence.setInterval(weeklyInterval);

			List<DayAndPosition> dayPos = new ArrayList<DayAndPosition>();

			addWeeklyDayPos(portletRequest, dayPos, Calendar.SUNDAY);
			addWeeklyDayPos(portletRequest, dayPos, Calendar.MONDAY);
			addWeeklyDayPos(portletRequest, dayPos, Calendar.TUESDAY);
			addWeeklyDayPos(portletRequest, dayPos, Calendar.WEDNESDAY);
			addWeeklyDayPos(portletRequest, dayPos, Calendar.THURSDAY);
			addWeeklyDayPos(portletRequest, dayPos, Calendar.FRIDAY);
			addWeeklyDayPos(portletRequest, dayPos, Calendar.SATURDAY);

			if (dayPos.size() == 0) {
				dayPos.add(new DayAndPosition(Calendar.MONDAY, 0));
			}

			recurrence.setByDay(dayPos.toArray(new DayAndPosition[0]));
		}
		else if (recurrenceType == Recurrence.MONTHLY) {
			int monthlyType = ParamUtil.getInteger(
				portletRequest, "monthlyType");

			if (monthlyType == 0) {
				int monthlyDay = ParamUtil.getInteger(
					portletRequest, "monthlyDay0", 1);

				recurrence.setByMonthDay(new int[] {monthlyDay});

				int monthlyInterval = ParamUtil.getInteger(
					portletRequest, "monthlyInterval0", 1);

				recurrence.setInterval(monthlyInterval);
			}
			else {
				int monthlyPos = ParamUtil.getInteger(
					portletRequest, "monthlyPos");
				int monthlyDay = ParamUtil.getInteger(
					portletRequest, "monthlyDay1");

				DayAndPosition[] dayPos = {
					new DayAndPosition(monthlyDay, monthlyPos)};

				recurrence.setByDay(dayPos);

				int monthlyInterval = ParamUtil.getInteger(
					portletRequest, "monthlyInterval1", 1);

				recurrence.setInterval(monthlyInterval);
			}
		}
		else if (recurrenceType == Recurrence.YEARLY) {
			int yearlyType = ParamUtil.getInteger(portletRequest, "yearlyType");

			if (yearlyType == 0) {
				int yearlyMonth = ParamUtil.getInteger(
					portletRequest, "yearlyMonth0");
				int yearlyDay = ParamUtil.getInteger(
					portletRequest, "yearlyDay0", 1);

				recurrence.setByMonth(new int[] {yearlyMonth});
				recurrence.setByMonthDay(new int[] {yearlyDay});

				int yearlyInterval = ParamUtil.getInteger(
					portletRequest, "yearlyInterval0", 1);

				recurrence.setInterval(yearlyInterval);
			}
			else {
				int yearlyPos = ParamUtil.getInteger(
					portletRequest, "yearlyPos");
				int yearlyDay = ParamUtil.getInteger(
					portletRequest, "yearlyDay1");
				int yearlyMonth = ParamUtil.getInteger(
					portletRequest, "yearlyMonth1");

				DayAndPosition[] dayPos = {
					new DayAndPosition(yearlyDay, yearlyPos)};

				recurrence.setByDay(dayPos);

				recurrence.setByMonth(new int[] {yearlyMonth});

				int yearlyInterval = ParamUtil.getInteger(
					portletRequest, "yearlyInterval1", 1);

				recurrence.setInterval(yearlyInterval);
			}
		}

		return RecurrenceSerializer.toCronText(recurrence);
	}

	protected Calendar getDate(
			PortletRequest portletRequest, String paramPrefix,
			boolean timeZoneSensitive)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		int dateMonth = ParamUtil.getInteger(
			portletRequest, paramPrefix + "Month");
		int dateDay = ParamUtil.getInteger(portletRequest, paramPrefix + "Day");
		int dateYear = ParamUtil.getInteger(
			portletRequest, paramPrefix + "Year");
		int dateHour = ParamUtil.getInteger(
			portletRequest, paramPrefix + "Hour");
		int dateMinute = ParamUtil.getInteger(
			portletRequest, paramPrefix + "Minute");
		int dateAmPm = ParamUtil.getInteger(
			portletRequest, paramPrefix + "AmPm");

		if (dateAmPm == Calendar.PM) {
			dateHour += 12;
		}

		Locale locale = null;
		TimeZone timeZone = null;

		if (timeZoneSensitive) {
			locale = themeDisplay.getLocale();
			timeZone = themeDisplay.getTimeZone();
		}
		else {
			locale = LocaleUtil.getDefault();
			timeZone = TimeZoneUtil.getDefault();
		}

		Calendar cal = CalendarFactoryUtil.getCalendar(timeZone, locale);

		cal.set(Calendar.MONTH, dateMonth);
		cal.set(Calendar.DATE, dateDay);
		cal.set(Calendar.YEAR, dateYear);
		cal.set(Calendar.HOUR_OF_DAY, dateHour);
		cal.set(Calendar.MINUTE, dateMinute);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal;
	}

	protected PortalPreferences getPortalPreferences(User user)
		throws SystemException {

		boolean signedIn = !user.isDefaultUser();

		PortalPreferences portalPreferences =
			PortletPreferencesFactoryUtil.getPortalPreferences(
				user.getCompanyId(), user.getUserId(), signedIn);

		return portalPreferences;
	}

	protected long getRecentLayoutBranchId(
		PortalPreferences portalPreferences, long layoutSetBranchId,
		long plid) {

		return GetterUtil.getLong(
			portalPreferences.getValue(
				Staging.class.getName(),
				getRecentLayoutBranchIdKey(layoutSetBranchId, plid)));
	}

	protected String getRecentLayoutBranchIdKey(
		long layoutSetBranchId, long plid) {

		StringBundler sb = new StringBundler(4);

		sb.append("layoutBranchId-");
		sb.append(layoutSetBranchId);
		sb.append(StringPool.DASH);
		sb.append(plid);

		return sb.toString();
	}

	protected long getRecentLayoutRevisionId(
			PortalPreferences portalPreferences, long layoutSetBranchId,
			long plid)
		throws PortalException, SystemException {

		long layoutRevisionId = GetterUtil.getLong(
			portalPreferences.getValue(
				Staging.class.getName(),
				getRecentLayoutRevisionIdKey(layoutSetBranchId, plid)));

		if (layoutRevisionId > 0) {
			return layoutRevisionId;
		}

		long layoutBranchId = getRecentLayoutBranchId(
			portalPreferences, layoutSetBranchId, plid);

		if (layoutBranchId > 0) {
			try {
				LayoutBranchLocalServiceUtil.getLayoutBranch(layoutBranchId);
			}
			catch (NoSuchLayoutBranchException nlbe) {
				LayoutBranch layoutBranch =
					LayoutBranchLocalServiceUtil.getMasterLayoutBranch(
						layoutSetBranchId, plid);

				layoutBranchId = layoutBranch.getLayoutBranchId();
			}
		}

		if (layoutBranchId > 0) {
			try {
				LayoutRevision layoutRevision =
					LayoutRevisionLocalServiceUtil.getLayoutRevision(
						layoutSetBranchId, layoutBranchId, plid);

				if (layoutRevision != null) {
					layoutRevisionId = layoutRevision.getLayoutRevisionId();
				}
			}
			catch (NoSuchLayoutRevisionException nslre) {
			}
		}

		return layoutRevisionId;
	}

	protected String getRecentLayoutRevisionIdKey(
		long layoutSetBranchId, long plid) {

		StringBundler sb = new StringBundler(4);

		sb.append("layoutRevisionId-");
		sb.append(layoutSetBranchId);
		sb.append(StringPool.DASH);
		sb.append(plid);

		return sb.toString();
	}

	protected String getRecentLayoutSetBranchIdKey(long layoutSetId) {
		return "layoutSetBranchId_" + layoutSetId;
	}

	protected void publishLayouts(
			PortletRequest portletRequest, long sourceGroupId,
			long targetGroupId, Map<String, String[]> parameterMap,
			boolean schedule)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String tabs1 = ParamUtil.getString(portletRequest, "tabs1");

		boolean privateLayout = true;

		if (tabs1.equals("public-pages")) {
			privateLayout = false;
		}

		String scope = ParamUtil.getString(portletRequest, "scope");

		Map<Long, Boolean> layoutIdMap = new LinkedHashMap<Long, Boolean>();

		if (scope.equals("selected-pages")) {
			long[] rowIds = ParamUtil.getLongValues(portletRequest, "rowIds");

			for (long selPlid : rowIds) {
				boolean includeChildren = ParamUtil.getBoolean(
					portletRequest, "includeChildren_" + selPlid);

				layoutIdMap.put(selPlid, includeChildren);
			}
		}

		String range = ParamUtil.getString(portletRequest, "range");

		Date startDate = null;
		Date endDate = null;

		if (range.equals("dateRange")) {
			startDate = getDate(portletRequest, "startDate", true).getTime();

			endDate = getDate(portletRequest, "endDate", true).getTime();
		}
		else if (range.equals("fromLastPublishDate")) {
			LayoutSet layoutSet = LayoutSetLocalServiceUtil.getLayoutSet(
				sourceGroupId, privateLayout);

			UnicodeProperties settingsProperties =
				layoutSet.getSettingsProperties();

			long lastPublishDate = GetterUtil.getLong(
				settingsProperties.getProperty("last-publish-date"));

			if (lastPublishDate > 0) {
				Calendar cal = Calendar.getInstance(
					themeDisplay.getTimeZone(), themeDisplay.getLocale());

				endDate = cal.getTime();

				cal.setTimeInMillis(lastPublishDate);

				startDate = cal.getTime();
			}
		}
		else if (range.equals("last")) {
			int rangeLast = ParamUtil.getInteger(portletRequest, "last");

			Date now = new Date();

			startDate = new Date(now.getTime() - (rangeLast * Time.HOUR));

			endDate = now;
		}

		if (schedule) {
			String groupName = getSchedulerGroupName(
				DestinationNames.LAYOUTS_LOCAL_PUBLISHER, targetGroupId);

			int recurrenceType = ParamUtil.getInteger(
				portletRequest, "recurrenceType");

			Calendar startCal = getDate(
				portletRequest, "schedulerStartDate", true);

			String cronText = getCronText(
				portletRequest, startCal, true, recurrenceType);

			Date schedulerEndDate = null;

			int endDateType = ParamUtil.getInteger(
				portletRequest, "endDateType");

			if (endDateType == 1) {
				Calendar endCal = getDate(
					portletRequest, "schedulerEndDate", true);

				schedulerEndDate = endCal.getTime();
			}

			String description = ParamUtil.getString(
				portletRequest, "description");

			LayoutServiceUtil.schedulePublishToLive(
				sourceGroupId, targetGroupId, privateLayout, layoutIdMap,
				parameterMap, scope, startDate, endDate, groupName, cronText,
				startCal.getTime(), schedulerEndDate, description);
		}
		else {
			MessageStatus messageStatus = new MessageStatus();

			messageStatus.startTimer();

			String command =
				LayoutsLocalPublisherRequest.COMMAND_SELECTED_PAGES;

			try {
				if (scope.equals("all-pages")) {
					command = LayoutsLocalPublisherRequest.COMMAND_ALL_PAGES;

					publishLayouts(
						themeDisplay.getUserId(), sourceGroupId, targetGroupId,
						privateLayout, parameterMap, startDate, endDate);
				}
				else {
					publishLayouts(
						themeDisplay.getUserId(), sourceGroupId, targetGroupId,
						privateLayout, layoutIdMap, parameterMap, startDate,
						endDate);
				}
			}
			catch (Exception e) {
				messageStatus.setException(e);

				throw e;
			}
			finally {
				messageStatus.stopTimer();

				LayoutsLocalPublisherRequest publisherRequest =
					new LayoutsLocalPublisherRequest(
						command, themeDisplay.getUserId(), sourceGroupId,
						targetGroupId, privateLayout, layoutIdMap, parameterMap,
						startDate, endDate);

				messageStatus.setPayload(publisherRequest);

				MessageBusUtil.sendMessage(
					DestinationNames.MESSAGE_BUS_MESSAGE_STATUS, messageStatus);
			}
		}
	}

	protected void publishToRemote(
			PortletRequest portletRequest, boolean schedule)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String tabs1 = ParamUtil.getString(portletRequest, "tabs1");

		long groupId = ParamUtil.getLong(portletRequest, "groupId");

		boolean privateLayout = true;

		if (tabs1.equals("public-pages")) {
			privateLayout = false;
		}

		String scope = ParamUtil.getString(portletRequest, "scope");

		if (Validator.isNull(scope)) {
			scope = "all-pages";
		}

		Map<Long, Boolean> layoutIdMap = null;

		if (scope.equals("selected-pages")) {
			layoutIdMap = new LinkedHashMap<Long, Boolean>();

			long[] rowIds = ParamUtil.getLongValues(portletRequest, "rowIds");

			for (long selPlid : rowIds) {
				boolean includeChildren = ParamUtil.getBoolean(
					portletRequest, "includeChildren_" + selPlid);

				layoutIdMap.put(selPlid, includeChildren);
			}
		}

		Map<String, String[]> parameterMap = getStagingParameters(
			portletRequest);

		parameterMap.put(
			PortletDataHandlerKeys.PUBLISH_TO_REMOTE,
			new String[] {Boolean.TRUE.toString()});

		Group group = GroupLocalServiceUtil.getGroup(groupId);

		UnicodeProperties groupTypeSettingsProperties =
			group.getTypeSettingsProperties();

		String remoteAddress = ParamUtil.getString(
			portletRequest, "remoteAddress",
			groupTypeSettingsProperties.getProperty("remoteAddress"));

		remoteAddress = stripProtocolFromRemoteAddress(remoteAddress);

		long remoteGroupId = ParamUtil.getLong(
			portletRequest, "remoteGroupId",
			GetterUtil.getLong(
				groupTypeSettingsProperties.getProperty("remoteGroupId")));
		int remotePort = ParamUtil.getInteger(
			portletRequest, "remotePort",
			GetterUtil.getInteger(
				groupTypeSettingsProperties.getProperty("remotePort")));
		boolean remotePrivateLayout = ParamUtil.getBoolean(
			portletRequest, "remotePrivateLayout");
		boolean secureConnection = ParamUtil.getBoolean(
			portletRequest, "secureConnection",
			GetterUtil.getBoolean(
				groupTypeSettingsProperties.getProperty("secureConnection")));

		validate(remoteAddress, remoteGroupId, remotePort, secureConnection);

		String range = ParamUtil.getString(portletRequest, "range");

		Date startDate = null;
		Date endDate = null;

		if (range.equals("dateRange")) {
			startDate = getDate(portletRequest, "startDate", true).getTime();

			endDate = getDate(portletRequest, "endDate", true).getTime();
		}
		else if (range.equals("fromLastPublishDate")) {
			LayoutSet layoutSet = LayoutSetLocalServiceUtil.getLayoutSet(
				groupId, privateLayout);

			UnicodeProperties layoutTypeSettingsProperties =
				layoutSet.getSettingsProperties();

			long lastPublishDate = GetterUtil.getLong(
				layoutTypeSettingsProperties.getProperty("last-publish-date"));

			if (lastPublishDate > 0) {
				Calendar cal = Calendar.getInstance(
					themeDisplay.getTimeZone(), themeDisplay.getLocale());

				endDate = cal.getTime();

				cal.setTimeInMillis(lastPublishDate);

				startDate = cal.getTime();
			}
		}
		else if (range.equals("last")) {
			int rangeLast = ParamUtil.getInteger(portletRequest, "last");

			Date now = new Date();

			startDate = new Date(now.getTime() - (rangeLast * Time.HOUR));

			endDate = now;
		}

		if (schedule) {
			String groupName = getSchedulerGroupName(
				DestinationNames.LAYOUTS_REMOTE_PUBLISHER, groupId);

			int recurrenceType = ParamUtil.getInteger(
				portletRequest, "recurrenceType");

			Calendar startCal = getDate(
				portletRequest, "schedulerStartDate", true);

			String cronText = getCronText(
				portletRequest, startCal, true, recurrenceType);

			Date schedulerEndDate = null;

			int endDateType = ParamUtil.getInteger(
				portletRequest, "endDateType");

			if (endDateType == 1) {
				Calendar endCal = getDate(
					portletRequest, "schedulerEndDate", true);

				schedulerEndDate = endCal.getTime();
			}

			String description = ParamUtil.getString(
				portletRequest, "description");

			LayoutServiceUtil.schedulePublishToRemote(
				groupId, privateLayout, layoutIdMap, parameterMap,
				remoteAddress, remotePort, secureConnection, remoteGroupId,
				remotePrivateLayout, startDate, endDate, groupName, cronText,
				startCal.getTime(), schedulerEndDate, description);
		}
		else {
			MessageStatus messageStatus = new MessageStatus();

			messageStatus.startTimer();

			try {
				copyRemoteLayouts(
					groupId, privateLayout, layoutIdMap, parameterMap,
					remoteAddress, remotePort, secureConnection, remoteGroupId,
					remotePrivateLayout, startDate, endDate);
			}
			catch (Exception e) {
				messageStatus.setException(e);

				throw e;
			}
			finally {
				messageStatus.stopTimer();

				LayoutsRemotePublisherRequest publisherRequest =
					new LayoutsRemotePublisherRequest(
						themeDisplay.getUserId(), groupId, privateLayout,
						layoutIdMap, parameterMap, remoteAddress, remotePort,
						secureConnection, remoteGroupId, remotePrivateLayout,
						startDate, endDate);

				messageStatus.setPayload(publisherRequest);

				MessageBusUtil.sendMessage(
					DestinationNames.MESSAGE_BUS_MESSAGE_STATUS, messageStatus);
			}
		}
	}

	protected void setCommonStagingOptions(
			Group liveGroup, UnicodeProperties typeSettingsProperties,
			ServiceContext serviceContext)
		throws Exception {

		LayoutExporter.updateLastPublishDate(
			liveGroup.getPrivateLayoutSet(), 0);
		LayoutExporter.updateLastPublishDate(liveGroup.getPublicLayoutSet(), 0);

		Set<String> parameterNames = serviceContext.getAttributes().keySet();

		for (String parameterName : parameterNames) {
			boolean staged = ParamUtil.getBoolean(
				serviceContext, parameterName);

			if (parameterName.startsWith(StagingConstants.STAGED_PORTLET) &&
				!parameterName.endsWith("Checkbox")) {

				typeSettingsProperties.setProperty(
					parameterName, String.valueOf(staged));
			}
		}
	}

	protected void setRecentLayoutBranchId(
		PortalPreferences portalPreferences, long layoutSetBranchId, long plid,
		long layoutBranchId) {

		portalPreferences.setValue(
			Staging.class.getName(),
			getRecentLayoutBranchIdKey(layoutSetBranchId, plid),
			String.valueOf(layoutBranchId));
	}

	protected void setRecentLayoutRevisionId(
			PortalPreferences portalPreferences, long layoutSetBranchId,
			long plid, long layoutRevisionId)
		throws SystemException {

		long layoutBranchId = 0;

		try {
			LayoutRevision layoutRevision =
				LayoutRevisionLocalServiceUtil.getLayoutRevision(
					layoutRevisionId);

			layoutBranchId = layoutRevision.getLayoutBranchId();

			LayoutRevision lastLayoutRevision =
				LayoutRevisionLocalServiceUtil.getLayoutRevision(
					layoutSetBranchId, layoutBranchId, plid);

			if (lastLayoutRevision.getLayoutRevisionId() == layoutRevisionId) {
				deleteRecentLayoutRevisionId(
					portalPreferences, layoutSetBranchId, plid);
			}
			else {
				portalPreferences.setValue(
					Staging.class.getName(),
					getRecentLayoutRevisionIdKey(layoutSetBranchId, plid),
					String.valueOf(layoutRevisionId));
			}
		}
		catch (PortalException pe) {
		}

		portalPreferences.setValue(
			Staging.class.getName(),
			getRecentLayoutBranchIdKey(layoutSetBranchId, plid),
			String.valueOf(layoutBranchId));
	}

	protected String stripProtocolFromRemoteAddress(String remoteAddress) {
		if (remoteAddress.startsWith(Http.HTTP_WITH_SLASH)) {
			remoteAddress = remoteAddress.substring(
				Http.HTTP_WITH_SLASH.length());
		}
		else if (remoteAddress.startsWith(Http.HTTPS_WITH_SLASH)) {
			remoteAddress = remoteAddress.substring(
				Http.HTTPS_WITH_SLASH.length());
		}

		return remoteAddress;
	}

	protected void validate(
			String remoteAddress, long remoteGroupId, int remotePort,
			boolean secureConnection)
		throws Exception {

		RemoteOptionsException roe = null;

		if (!Validator.isDomain(remoteAddress) &&
			!Validator.isIPAddress(remoteAddress)) {

			roe = new RemoteOptionsException(
				RemoteOptionsException.REMOTE_ADDRESS);

			roe.setRemoteAddress(remoteAddress);

			throw roe;
		}

		if ((remotePort < 1) || (remotePort > 65535)) {
			roe = new RemoteOptionsException(
				RemoteOptionsException.REMOTE_PORT);

			roe.setRemotePort(remotePort);

			throw roe;
		}

		if (remoteGroupId <= 0) {
			roe = new RemoteOptionsException(
				RemoteOptionsException.REMOTE_GROUP_ID);

			roe.setRemoteGroupId(remoteGroupId);

			throw roe;
		}

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		User user = UserLocalServiceUtil.getUser(permissionChecker.getUserId());

		String url = buildRemoteURL(
			remoteAddress, remotePort, secureConnection,
			GroupConstants.DEFAULT_LIVE_GROUP_ID, false);

		HttpPrincipal httpPrincipal = new HttpPrincipal(
			url, user.getEmailAddress(), user.getPassword(),
			user.getPasswordEncrypted());

		// Ping remote host and verify that the group exists

		try {
			GroupServiceHttp.getGroup(httpPrincipal, remoteGroupId);
		}
		catch (NoSuchGroupException nsge) {
			RemoteExportException ree = new RemoteExportException(
				RemoteExportException.NO_GROUP);

			ree.setGroupId(remoteGroupId);

			throw ree;
		}
		catch (PrincipalException pe) {
			RemoteExportException ree = new RemoteExportException(
				RemoteExportException.NO_PERMISSIONS);

			ree.setGroupId(remoteGroupId);

			throw ree;
		}
		catch (SystemException se) {
			RemoteExportException ree = new RemoteExportException(
				RemoteExportException.BAD_CONNECTION);

			ree.setURL(url);

			throw ree;
		}

	}

}