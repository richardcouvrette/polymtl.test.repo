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

import com.liferay.portal.LayoutFriendlyURLException;
import com.liferay.portal.LayoutHiddenException;
import com.liferay.portal.LayoutNameException;
import com.liferay.portal.LayoutParentLayoutIdException;
import com.liferay.portal.LayoutTypeException;
import com.liferay.portal.NoSuchLayoutException;
import com.liferay.portal.RequiredLayoutException;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Junction;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.lar.LayoutExporter;
import com.liferay.portal.lar.LayoutImporter;
import com.liferay.portal.lar.PortletExporter;
import com.liferay.portal.lar.PortletImporter;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.model.LayoutPrototype;
import com.liferay.portal.model.LayoutReference;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.PortletConstants;
import com.liferay.portal.model.PortletPreferences;
import com.liferay.portal.model.Resource;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.ResourcePermission;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.model.impl.LayoutImpl;
import com.liferay.portal.model.impl.PortletPreferencesImpl;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.base.LayoutLocalServiceBaseImpl;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portal.util.PropsValues;
import com.liferay.portal.util.comparator.LayoutComparator;
import com.liferay.portal.util.comparator.LayoutPriorityComparator;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.portlet.PortletException;

/**
 * The implementation of the layout local service.
 *
 * @author Brian Wing Shun Chan
 * @author Jorge Ferrer
 * @author Joel Kozikowski
 * @author Charles May
 * @author Raymond Augé
 * @author Jorge Ferrer
 * @author Bruno Farache
 */
public class LayoutLocalServiceImpl extends LayoutLocalServiceBaseImpl {

	/**
	 * Returns the object counter's name.
	 *
	 * @param  groupId the primary key of the group
	 * @param  privateLayout whether layout is private to the group
	 * @return the object counter's name
	 */
	public static String getCounterName(long groupId, boolean privateLayout) {
		StringBundler sb = new StringBundler();

		sb.append(Layout.class.getName());
		sb.append(StringPool.POUND);
		sb.append(groupId);
		sb.append(StringPool.POUND);
		sb.append(privateLayout);

		return sb.toString();
	}

	/**
	 * Adds a layout with additional parameters.
	 *
	 * <p>
	 * This method handles the creation of the layout including its resources,
	 * metadata, and internal data structures. It is not necessary to make
	 * subsequent calls to any methods to setup default groups, resources, ...
	 * etc.
	 * </p>
	 *
	 * @param  userId the primary key of the user
	 * @param  groupId the primary key of the group
	 * @param  privateLayout whether the layout is private to the group
	 * @param  parentLayoutId the primary key of the parent layout (optionally
	 *         {@link
	 *         com.liferay.portal.model.LayoutConstants#DEFAULT_PARENT_LAYOUT_ID})
	 * @param  nameMap the layout's locales and localized names
	 * @param  titleMap the layout's locales and localized titles
	 * @param  descriptionMap the layout's locales and localized descriptions
	 * @param  keywordsMap the layout's locales and localized keywords
	 * @param  robotsMap the layout's locales and localized robots
	 * @param  type the layout's type (optionally {@link
	 *         com.liferay.portal.model.LayoutConstants#TYPE_PORTLET}). The
	 *         possible types can be found in {@link
	 *         com.liferay.portal.model.LayoutConstants}.
	 * @param  hidden whether the layout is hidden
	 * @param  friendlyURL the layout's friendly URL (optionally {@link
	 *         com.liferay.portal.util.PropsValues#DEFAULT_USER_PRIVATE_LAYOUT_FRIENDLY_URL}
	 *         or {@link
	 *         com.liferay.portal.util.PropsValues#DEFAULT_USER_PUBLIC_LAYOUT_FRIENDLY_URL}).
	 *         The default values can be overridden in
	 *         <code>portal-ext.properties</code> by specifying new values for
	 *         the corresponding properties defined in {@link
	 *         com.liferay.portal.util.PropsValues}. To see how the URL is
	 *         normalized when accessed see {@link
	 *         com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil#normalize(
	 *         String)}.
	 * @param  serviceContext the service context. Must set the universally
	 *         unique identifier (UUID) for the layout. Can set the creation
	 *         date, modification date and the expando bridge attributes for the
	 *         layout. For layouts that belong to a layout set prototype, an
	 *         attribute named 'layoutUpdateable' can be set to specify whether
	 *         site administrators can modify this page within their site. For
	 *         layouts that are created from a layout prototype, attributes
	 *         named 'layoutPrototypeUuid' and 'layoutPrototypeLinkedEnabled'
	 *         can be specified to provide the unique identifier of the source
	 *         prototype and a boolean to determined whether a link to it should
	 *         be enabled to activate propagation of changes made to the linked
	 *         page in the prototype.
	 * @return the layout
	 * @throws PortalException if a group or user with the primary key could not
	 *         be found, or if layout values were invalid
	 * @throws SystemException if a system exception occurred
	 */
	public Layout addLayout(
			long userId, long groupId, boolean privateLayout,
			long parentLayoutId, Map<Locale, String> nameMap,
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			Map<Locale, String> keywordsMap, Map<Locale, String> robotsMap,
			String type, boolean hidden, String friendlyURL,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Layout

		User user = userPersistence.findByPrimaryKey(userId);
		long layoutId = getNextLayoutId(groupId, privateLayout);
		parentLayoutId = getParentLayoutId(
			groupId, privateLayout, parentLayoutId);
		String name = nameMap.get(LocaleUtil.getDefault());
		friendlyURL = getFriendlyURL(
			groupId, privateLayout, layoutId, name, friendlyURL);
		int priority = getNextPriority(groupId, privateLayout, parentLayoutId);

		validate(
			groupId, privateLayout, layoutId, parentLayoutId, name, type,
			hidden, friendlyURL);

		Date now = new Date();

		long plid = counterLocalService.increment();

		Layout layout = layoutPersistence.create(plid);

		layout.setUuid(serviceContext.getUuid());
		layout.setGroupId(groupId);
		layout.setCompanyId(user.getCompanyId());
		layout.setCreateDate(serviceContext.getCreateDate(now));
		layout.setModifiedDate(serviceContext.getModifiedDate(now));
		layout.setPrivateLayout(privateLayout);
		layout.setLayoutId(layoutId);
		layout.setParentLayoutId(parentLayoutId);
		layout.setNameMap(nameMap);
		layout.setTitleMap(titleMap);
		layout.setDescriptionMap(descriptionMap);
		layout.setKeywordsMap(keywordsMap);
		layout.setRobotsMap(robotsMap);
		layout.setType(type);
		layout.setHidden(hidden);
		layout.setFriendlyURL(friendlyURL);
		layout.setPriority(priority);

		boolean layoutUpdateable = ParamUtil.getBoolean(
			serviceContext, "layoutUpdateable", true);

		if (!layoutUpdateable) {
			UnicodeProperties typeSettingsProperties =
				layout.getTypeSettingsProperties();

			typeSettingsProperties.put(
				"layoutUpdateable", String.valueOf(layoutUpdateable));

			layout.setTypeSettingsProperties(typeSettingsProperties);
		}

		String layoutPrototypeUuid = ParamUtil.getString(
			serviceContext, "layoutPrototypeUuid");
		boolean layoutPrototypeLinkEnabled = ParamUtil.getBoolean(
			serviceContext, "layoutPrototypeLinkEnabled", true);

		if (Validator.isNotNull(layoutPrototypeUuid)) {
			layout.setLayoutPrototypeUuid(layoutPrototypeUuid);
			layout.setLayoutPrototypeLinkEnabled(layoutPrototypeLinkEnabled);

			LayoutPrototype layoutPrototype =
				layoutPrototypeLocalService.getLayoutPrototypeByUuid(
					layoutPrototypeUuid);

			Layout layoutPrototypeLayout = layoutPrototype.getLayout();

			layout.setSourcePrototypeLayoutUuid(
				layoutPrototypeLayout.getUuid());
		}

		if (type.equals(LayoutConstants.TYPE_PORTLET)) {
			LayoutTypePortlet layoutTypePortlet =
				(LayoutTypePortlet)layout.getLayoutType();

			layoutTypePortlet.setLayoutTemplateId(
				0, PropsValues.LAYOUT_DEFAULT_TEMPLATE_ID, false);
		}

		layoutPersistence.update(layout, false);

		// Resources

		boolean addGroupPermissions = true;

		Group group = groupLocalService.getGroup(groupId);

		if (privateLayout && group.isUser()) {
			addGroupPermissions = false;
		}

		boolean addGuestPermissions = false;

		if (!privateLayout ||
			type.equals(LayoutConstants.TYPE_CONTROL_PANEL) ||
			group.isLayoutSetPrototype()) {

			addGuestPermissions = true;
		}

		resourceLocalService.addResources(
			user.getCompanyId(), groupId, user.getUserId(),
			Layout.class.getName(), layout.getPlid(), false,
			addGroupPermissions, addGuestPermissions);

		// Group

		groupLocalService.updateSite(groupId, true);

		// Layout set

		layoutSetLocalService.updatePageCount(groupId, privateLayout);

		LayoutSet layoutSet = layoutSetLocalService.getLayoutSet(
			groupId, privateLayout);

		layout.setLayoutSet(layoutSet);

		// Expando

		ExpandoBridge expandoBridge = layout.getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);

		// Message boards

		if (PropsValues.LAYOUT_COMMENTS_ENABLED) {
			mbMessageLocalService.addDiscussionMessage(
				userId, user.getFullName(), groupId, Layout.class.getName(),
				plid, WorkflowConstants.ACTION_PUBLISH);
		}

		return layout;
	}

	/**
	 * Adds a layout.
	 *
	 * <p>
	 * This method handles the creation of the layout including its resources,
	 * metadata, and internal data structures. It is not necessary to make
	 * subsequent calls to any methods to setup default groups, resources, ...
	 * etc.
	 * </p>
	 *
	 * @param  userId the primary key of the user
	 * @param  groupId the primary key of the group
	 * @param  privateLayout whether the layout is private to the group
	 * @param  parentLayoutId the primary key of the parent layout (optionally
	 *         {@link
	 *         com.liferay.portal.model.LayoutConstants#DEFAULT_PARENT_LAYOUT_ID}).
	 *         The possible values can be found in {@link
	 *         com.liferay.portal.model.LayoutConstants}.
	 * @param  name the layout's name (optionally {@link
	 *         com.liferay.portal.util.PropsValues#DEFAULT_USER_PRIVATE_LAYOUT_NAME}
	 *         or {@link
	 *         com.liferay.portal.util.PropsValues#DEFAULT_USER_PUBLIC_LAYOUT_NAME}).
	 *         The default values can be overridden in
	 *         <code>portal-ext.properties</code> by specifying new values for
	 *         the corresponding properties defined in {@link
	 *         com.liferay.portal.util.PropsValues}
	 * @param  title the layout's title
	 * @param  description the layout's description
	 * @param  type the layout's type (optionally {@link
	 *         com.liferay.portal.model.LayoutConstants#TYPE_PORTLET}). The
	 *         possible types can be found in {@link
	 *         com.liferay.portal.model.LayoutConstants}.
	 * @param  hidden whether the layout is hidden
	 * @param  friendlyURL the friendly URL of the layout (optionally {@link
	 *         com.liferay.portal.util.PropsValues#DEFAULT_USER_PRIVATE_LAYOUT_FRIENDLY_URL}
	 *         or {@link
	 *         com.liferay.portal.util.PropsValues#DEFAULT_USER_PUBLIC_LAYOUT_FRIENDLY_URL}).
	 *         The default values can be overridden in
	 *         <code>portal-ext.properties</code> by specifying new values for
	 *         the corresponding properties defined in {@link
	 *         com.liferay.portal.util.PropsValues}. To see how the URL is
	 *         normalized when accessed see {@link
	 *         com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil#normalize(
	 *         String)}.
	 * @param  serviceContext the service context. Must set the universally
	 *         unique identifier (UUID) for the layout. Can set the creation
	 *         date and modification date for the layout. For layouts that
	 *         belong to a layout set prototype, an attribute named
	 *         'layoutUpdateable' can be set to specify whether site
	 *         administrators can modify this page within their site.
	 * @return the layout
	 * @throws PortalException if a group or user with the primary key could not
	 *         be found
	 * @throws SystemException if a system exception occurred
	 */
	public Layout addLayout(
			long userId, long groupId, boolean privateLayout,
			long parentLayoutId, String name, String title, String description,
			String type, boolean hidden, String friendlyURL,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		Map<Locale, String> localeNamesMap = new HashMap<Locale, String>();

		Locale defaultLocale = LocaleUtil.getDefault();

		localeNamesMap.put(defaultLocale, name);

		return addLayout(
			userId, groupId, privateLayout, parentLayoutId, localeNamesMap,
			new HashMap<Locale, String>(), new HashMap<Locale, String>(),
			new HashMap<Locale, String>(), new HashMap<Locale, String>(), type,
			hidden, friendlyURL, serviceContext);
	}

	/**
	 * Deletes the layout, its child layouts, and its associated resources.
	 *
	 * @param  layout the layout
	 * @param  updateLayoutSet whether the layout set's page counter needs to be
	 *         updated
	 * @param  serviceContext the service context
	 * @throws PortalException if a portal exception occurred
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteLayout(
			Layout layout, boolean updateLayoutSet,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// First layout validation

		if (layout.getParentLayoutId() ==
				LayoutConstants.DEFAULT_PARENT_LAYOUT_ID) {

			List<Layout> rootLayouts = layoutPersistence.findByG_P_P(
				layout.getGroupId(), layout.isPrivateLayout(),
				LayoutConstants.DEFAULT_PARENT_LAYOUT_ID, 0, 2);

			if (rootLayouts.size() > 1) {
				Layout firstLayout = rootLayouts.get(0);

				if (firstLayout.getLayoutId() == layout.getLayoutId()) {
					Layout secondLayout = rootLayouts.get(1);

					validateFirstLayout(secondLayout.getType());
				}
			}
		}

		// Child layouts

		List<Layout> childLayouts = layoutPersistence.findByG_P_P(
			layout.getGroupId(), layout.isPrivateLayout(),
			layout.getLayoutId());

		for (Layout childLayout : childLayouts) {
			deleteLayout(childLayout, updateLayoutSet, serviceContext);
		}

		// Portlet preferences

		portletPreferencesLocalService.deletePortletPreferences(
			PortletKeys.PREFS_OWNER_ID_DEFAULT,
			PortletKeys.PREFS_OWNER_TYPE_LAYOUT, layout.getPlid());

		// Ratings

		ratingsStatsLocalService.deleteStats(
			Layout.class.getName(), layout.getPlid());

		// Message boards

		mbMessageLocalService.deleteDiscussionMessages(
			Layout.class.getName(), layout.getPlid());

		// Journal articles

		journalArticleLocalService.deleteLayoutArticleReferences(
			layout.getGroupId(), layout.getUuid());

		// Journal content searches

		journalContentSearchLocalService.deleteLayoutContentSearches(
			layout.getGroupId(), layout.isPrivateLayout(),
			layout.getLayoutId());

		// Expando

		expandoValueLocalService.deleteValues(
			Layout.class.getName(), layout.getPlid());

		// Icon

		imageLocalService.deleteImage(layout.getIconImageId());

		// Scope group

		Group scopeGroup = layout.getScopeGroup();

		if (scopeGroup != null) {
			groupLocalService.deleteGroup(scopeGroup.getGroupId());
		}

		// Resources

		String primKey =
			layout.getPlid() + PortletConstants.LAYOUT_SEPARATOR + "%";

		if (PropsValues.PERMISSIONS_USER_CHECK_ALGORITHM == 6) {
			List<ResourcePermission> resourcePermissions =
				resourcePermissionFinder.findByC_P(
					layout.getCompanyId(), primKey);

			for (ResourcePermission resourcePermission : resourcePermissions) {
				resourcePermissionLocalService.deleteResourcePermission(
					resourcePermission);
			}
		}
		else {
			List<Resource> resources = resourceFinder.findByC_P(
				layout.getCompanyId(), primKey);

			for (Resource resource : resources) {
				resourceLocalService.deleteResource(resource);
			}
		}

		resourceLocalService.deleteResource(
			layout.getCompanyId(), Layout.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL, layout.getPlid());

		// Layout

		layoutPersistence.remove(layout);

		// Layout set

		if (updateLayoutSet) {
			layoutSetLocalService.updatePageCount(
				layout.getGroupId(), layout.isPrivateLayout());
		}
	}

	/**
	 * Deletes the layout with the primary key, also deleting the layout's child
	 * layouts, and associated resources.
	 *
	 * @param  groupId the primary key of the group
	 * @param  privateLayout whether the layout is private to the group
	 * @param  layoutId the primary key of the layout
	 * @param  serviceContext the service context
	 * @throws PortalException if a matching layout could not be found , or if
	 *         some other portal exception occurred
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteLayout(
			long groupId, boolean privateLayout, long layoutId,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		Layout layout = layoutPersistence.findByG_P_L(
			groupId, privateLayout, layoutId);

		deleteLayout(layout, true, serviceContext);
	}

	/**
	 * Deletes the layout with the plid, also deleting the layout's child
	 * layouts, and associated resources.
	 *
	 * @param  plid the primary key of the layout
	 * @param  serviceContext the service context
	 * @throws PortalException if a layout with the primary key could not be
	 *         found , or if some other portal exception occurred
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteLayout(long plid, ServiceContext serviceContext)
		throws PortalException, SystemException {

		Layout layout = layoutPersistence.findByPrimaryKey(plid);

		deleteLayout(layout, true, serviceContext);
	}

	/**
	 * Deletes the group's private or non-private layouts, also deleting the
	 * layouts' child layouts, and associated resources.
	 *
	 * @param  groupId the primary key of the group
	 * @param  privateLayout whether the layout is private to the group
	 * @param  serviceContext the service context
	 * @throws PortalException if a group with the primary key could not be
	 *         found or if a layout set for the group and privacy could not be
	 *         found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteLayouts(
			long groupId, boolean privateLayout, ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Layouts

		List<Layout> layouts = layoutPersistence.findByG_P_P(
			groupId, privateLayout, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID);

		for (Layout layout : layouts) {
			try {
				deleteLayout(layout, false, serviceContext);
			}
			catch (NoSuchLayoutException nsle) {
			}
		}

		// Layout set

		layoutSetLocalService.updatePageCount(groupId, privateLayout);

		// Counter

		counterLocalService.reset(getCounterName(groupId, privateLayout));
	}

	/**
	 * Exports layouts with the primary keys and criteria as a byte array.
	 *
	 * @param  groupId the primary key of the group
	 * @param  privateLayout whether the layout is private to the group
	 * @param  layoutIds the primary keys of the layouts to be exported
	 * @param  parameterMap the mapping of parameters indicating which
	 *         information to export. For information on the keys used in the
	 *         map see {@link
	 *         com.liferay.portal.kernel.lar.PortletDataHandlerKeys}.
	 * @param  startDate the export's start date
	 * @param  endDate the export's end date
	 * @return the layouts as a byte array
	 * @throws PortalException if a group or any layout with the primary key
	 *         could not be found, or if some other portal exception occurred
	 * @throws SystemException if a system exception occurred
	 */
	public byte[] exportLayouts(
			long groupId, boolean privateLayout, long[] layoutIds,
			Map<String, String[]> parameterMap, Date startDate, Date endDate)
		throws PortalException, SystemException {

		File file = exportLayoutsAsFile(
			groupId, privateLayout, layoutIds, parameterMap, startDate,
			endDate);

		try {
			return FileUtil.getBytes(file);
		}
		catch (IOException ioe) {
			throw new SystemException(ioe);
		}
		finally {
			file.delete();
		}
	}

	/**
	 * Exports all layouts that match the criteria as a byte array.
	 *
	 * @param  groupId the primary key of the group
	 * @param  privateLayout whether the layout is private to the group
	 * @param  parameterMap the mapping of parameters indicating which
	 *         information to export. For information on the keys used in the
	 *         map see {@link
	 *         com.liferay.portal.kernel.lar.PortletDataHandlerKeys}.
	 * @param  startDate the export's start date
	 * @param  endDate the export's end date
	 * @return the layout as a byte array
	 * @throws PortalException if a group with the primary key could not be
	 *         found or if some other portal exception occurred
	 * @throws SystemException if a system exception occurred
	 */
	public byte[] exportLayouts(
			long groupId, boolean privateLayout,
			Map<String, String[]> parameterMap, Date startDate, Date endDate)
		throws PortalException, SystemException {

		return exportLayouts(
			groupId, privateLayout, null, parameterMap, startDate, endDate);
	}

	/**
	 * Exports the layouts that match the primary keys and criteria as a file.
	 *
	 * @param  groupId the primary key of the group
	 * @param  privateLayout whether the layout is private to the group
	 * @param  layoutIds the primary keys of the layouts to be exported
	 *         (optionally <code>null</code>)
	 * @param  parameterMap the mapping of parameters indicating which
	 *         information to export. For information on the keys used in the
	 *         map see {@link
	 *         com.liferay.portal.kernel.lar.PortletDataHandlerKeys}.
	 * @param  startDate the export's start date
	 * @param  endDate the export's end date
	 * @return the layouts as a File
	 * @throws PortalException if a group or any layout with the primary key
	 *         could not be found, or if some other portal exception occurred
	 * @throws SystemException if a system exception occurred
	 */
	public File exportLayoutsAsFile(
			long groupId, boolean privateLayout, long[] layoutIds,
			Map<String, String[]> parameterMap, Date startDate, Date endDate)
		throws PortalException, SystemException {

		try {
			LayoutExporter layoutExporter = new LayoutExporter();

			return layoutExporter.exportLayoutsAsFile(
				groupId, privateLayout, layoutIds, parameterMap, startDate,
				endDate);
		}
		catch (PortalException pe) {
			throw pe;
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * Exports the portlet information (categories, permissions, ... etc.) as a
	 * byte array.
	 *
	 * @param  plid the primary key of the layout
	 * @param  groupId the primary key of the group
	 * @param  portletId the primary key of the portlet
	 * @param  parameterMap the mapping of parameters indicating which
	 *         information to export. For information on the keys used in the
	 *         map see {@link
	 *         com.liferay.portal.kernel.lar.PortletDataHandlerKeys}.
	 * @param  startDate the export's start date
	 * @param  endDate the export's end date
	 * @return the portlet information as a byte array
	 * @throws PortalException if a group or portlet with the primary key could
	 *         not be found, or if some other portal exception occurred
	 * @throws SystemException if a system exception occurred
	 */
	public byte[] exportPortletInfo(
			long plid, long groupId, String portletId,
			Map<String, String[]> parameterMap, Date startDate, Date endDate)
		throws PortalException, SystemException {

		File file = exportPortletInfoAsFile(
			plid, groupId, portletId, parameterMap, startDate, endDate);

		try {
			return FileUtil.getBytes(file);
		}
		catch (IOException ioe) {
			throw new SystemException(ioe);
		}
		finally {
			file.delete();
		}
	}

	/**
	 * Exports the portlet information (categories, permissions, ... etc.) as a
	 * file.
	 *
	 * @param  plid the primary key of the layout
	 * @param  groupId the primary key of the group
	 * @param  portletId the primary key of the portlet
	 * @param  parameterMap the mapping of parameters indicating which
	 *         information to export. For information on the keys used in the
	 *         map see {@link
	 *         com.liferay.portal.kernel.lar.PortletDataHandlerKeys}.
	 * @param  startDate the export's start date
	 * @param  endDate the export's end date
	 * @return the portlet information as a file
	 * @throws PortalException if a group or portlet with the primary key could
	 *         not be found, or if some other portal exception occurred
	 * @throws SystemException if a system exception occurred
	 */
	public File exportPortletInfoAsFile(
			long plid, long groupId, String portletId,
			Map<String, String[]> parameterMap, Date startDate, Date endDate)
		throws PortalException, SystemException {

		try {
			PortletExporter portletExporter = new PortletExporter();

			return portletExporter.exportPortletInfoAsFile(
				plid, groupId, portletId, parameterMap, startDate, endDate);
		}
		catch (PortalException pe) {
			throw pe;
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	public Layout fetchFirstLayout(
			long groupId, boolean privateLayout, long parentLayoutId)
		throws SystemException {

		Layout firstLayout = null;

		try {
			firstLayout = layoutPersistence.findByG_P_P_First(
				groupId, privateLayout, parentLayoutId,
				new LayoutPriorityComparator());
		}
		catch (NoSuchLayoutException nsle) {
		}

		return firstLayout;
	}

	/**
	 * Returns the layout matching the universally unique identifier and group
	 * ID
	 *
	 * @param  uuid the universally unique identifier of the scope layout
	 * @param  groupId the primary key of the group
	 * @return the layout, or <code>null</code> if a matching layout could not
	 *         be found
	 * @throws SystemException if a system exception occurred
	 */
	public Layout fetchLayoutByUuidAndGroupId(String uuid, long groupId)
		throws SystemException {

		return layoutPersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the primary key of the default layout for the group
	 *
	 * @param  groupId the primary key of the group
	 * @return the primary key of the default layout for the group (optionally
	 *         {@link com.liferay.portal.model.LayoutConstants#DEFAULT_PLID})
	 * @throws SystemException if a system exception occurred
	 */
	public long getDefaultPlid(long groupId) throws SystemException {
		if (groupId > 0) {
			List<Layout> layouts = layoutPersistence.findByGroupId(
				groupId, 0, 1);

			if (layouts.size() > 0) {
				Layout layout = layouts.get(0);

				return layout.getPlid();
			}
		}

		return LayoutConstants.DEFAULT_PLID;
	}

	/**
	 * Returns primary key of the matching default layout for the group
	 *
	 * @param  groupId the primary key of the group
	 * @param  privateLayout whether the layout is private to the group
	 * @return the primary key of the default layout for the group; {@link
	 *         com.liferay.portal.model.LayoutConstants#DEFAULT_PLID}) otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public long getDefaultPlid(long groupId, boolean privateLayout)
		throws SystemException {

		if (groupId > 0) {
			List<Layout> layouts = layoutPersistence.findByG_P(
				groupId, privateLayout, 0, 1);

			if (layouts.size() > 0) {
				Layout layout = layouts.get(0);

				return layout.getPlid();
			}
		}

		return LayoutConstants.DEFAULT_PLID;
	}

	/**
	 * Returns primary key of the default portlet layout for the group
	 *
	 * @param  groupId the primary key of the group
	 * @param  privateLayout whether the layout is private to the group
	 * @param  portletId the primary key of the portlet
	 * @return the primary key of the default portlet layout for the group;
	 *         {@link com.liferay.portal.model.LayoutConstants#DEFAULT_PLID}
	 *         otherwise
	 * @throws PortalException if a portlet with the primary key could not be
	 *         found
	 * @throws SystemException if a system exception occurred
	 */
	public long getDefaultPlid(
			long groupId, boolean privateLayout, String portletId)
		throws PortalException, SystemException {

		if (groupId > 0) {
			List<Layout> layouts = layoutPersistence.findByG_P(
				groupId, privateLayout);

			for (Layout layout : layouts) {
				if (layout.isTypePortlet()) {
					LayoutTypePortlet layoutTypePortlet =
						(LayoutTypePortlet)layout.getLayoutType();

					if (layoutTypePortlet.hasPortletId(portletId)) {
						return layout.getPlid();
					}
				}
			}
		}

		return LayoutConstants.DEFAULT_PLID;
	}

	/**
	 * Returns the layout for the friendly URL
	 *
	 * @param  groupId the primary key of the group
	 * @param  privateLayout whether the layout is private to the group
	 * @param  friendlyURL the friendly URL of the layout
	 * @return the layout for the friendly URL
	 * @throws PortalException if the friendly URL is <code>null</code> or a
	 *         matching layout could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Layout getFriendlyURLLayout(
			long groupId, boolean privateLayout, String friendlyURL)
		throws PortalException, SystemException {

		if (Validator.isNull(friendlyURL)) {
			throw new NoSuchLayoutException();
		}

		friendlyURL = getFriendlyURL(friendlyURL);

		Layout layout = layoutPersistence.fetchByG_P_F(
			groupId, privateLayout, friendlyURL);

		if ((layout == null) &&
			(friendlyURL.startsWith(StringPool.SLASH)) &&
			(Validator.isNumber(friendlyURL.substring(1)))) {

			long layoutId = GetterUtil.getLong(friendlyURL.substring(1));

			layout = layoutPersistence.fetchByG_P_L(
				groupId, privateLayout, layoutId);
		}

		if (layout == null) {
			throw new NoSuchLayoutException();
		}

		return layout;
	}

	/**
	 * Returns the layout matching the primary key; throws a {@link
	 * com.liferay.portal.NoSuchLayoutException} otherwise.
	 *
	 * @param  plid the primary key of the layout
	 * @return the matching layout
	 * @throws PortalException if a layout with the primary key could not be
	 *         found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Layout getLayout(long plid)
		throws PortalException, SystemException {

		return layoutPersistence.findByPrimaryKey(plid);
	}

	/**
	 * Returns the layout matching the primary key, group, and privacy; throws a
	 * {@link com.liferay.portal.NoSuchLayoutException} otherwise.
	 *
	 * @param  groupId the primary key of the group
	 * @param  privateLayout whether the layout is private to the group
	 * @param  layoutId the primary key of the layout
	 * @return the matching layout
	 * @throws PortalException if a matching layout could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Layout getLayout(long groupId, boolean privateLayout, long layoutId)
		throws PortalException, SystemException {

		return layoutPersistence.findByG_P_L(groupId, privateLayout, layoutId);
	}

	/**
	 * Returns the layout for the icon image; throws a {@link
	 * com.liferay.portal.NoSuchLayoutException} otherwise.
	 *
	 * @param  iconImageId the primary key of the icon image
	 * @return Returns the layout for the icon image
	 * @throws PortalException if an icon image with the primary key could not
	 *         be found
	 * @throws SystemException if a system exception occurred
	 */
	public Layout getLayoutByIconImageId(long iconImageId)
		throws PortalException, SystemException {

		return layoutPersistence.findByIconImageId(iconImageId);
	}

	/**
	 * Returns the layout with the universally unique identifier and the group.
	 *
	 * @param  uuid the universally unique identifier of the layout
	 * @param  groupId the primary key of the group
	 * @return the layout with the universally unique identifier and the group
	 * @throws PortalException if a group with the primary key could not be
	 *         found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Layout getLayoutByUuidAndGroupId(String uuid, long groupId)
		throws PortalException, SystemException {

		return layoutPersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the layouts belonging to the group.
	 *
	 * @param  groupId the primary key of the group
	 * @param  privateLayout whether the layout is private to the group
	 * @return the matching layouts, or <code>null</code> if no matches were
	 *         found
	 * @throws SystemException if a system exception occurred
	 */
	public List<Layout> getLayouts(long groupId, boolean privateLayout)
		throws SystemException {

		return layoutPersistence.findByG_P(groupId, privateLayout);
	}

	/**
	 * Returns all the layouts belonging to the group that are children of the
	 * parent layout.
	 *
	 * @param  groupId the primary key of the group
	 * @param  privateLayout whether the layout is private to the group
	 * @param  parentLayoutId the primary key of the parent layout
	 * @return the matching layouts, or <code>null</code> if no matches were
	 *         found
	 * @throws SystemException if a system exception occurred
	 */
	public List<Layout> getLayouts(
			long groupId, boolean privateLayout, long parentLayoutId)
		throws SystemException {

		return layoutPersistence.findByG_P_P(
			groupId, privateLayout, parentLayoutId);
	}

	/**
	 * Returns a range of all the layouts belonging to the group that are
	 * children of the parent layout.
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
	 * @param  groupId the primary key of the group
	 * @param  privateLayout whether the layout is private to the group
	 * @param  parentLayoutId the primary key of the parent layout
	 * @param  incomplete whether the layout is incomplete
	 * @param  start the lower bound of the range of layouts
	 * @param  end the upper bound of the range of layouts (not inclusive)
	 * @return the matching layouts, or <code>null</code> if no matches were
	 *         found
	 * @throws SystemException if a system exception occurred
	 */
	public List<Layout> getLayouts(
			long groupId, boolean privateLayout, long parentLayoutId,
			boolean incomplete, int start, int end)
		throws SystemException {

		return layoutPersistence.findByG_P_P(
			groupId, privateLayout, parentLayoutId, start, end);
	}

	/**
	 * Returns all the layouts that match the layout IDs and belong to the
	 * group.
	 *
	 * @param  groupId the primary key of the group
	 * @param  privateLayout whether the layout is private to the group
	 * @param  layoutIds the primary keys of the layouts
	 * @return the matching layouts, or <code>null</code> if no matches were
	 *         found
	 * @throws PortalException if a group or layout with the primary key could
	 *         not be found
	 * @throws SystemException if a system exception occurred
	 */
	public List<Layout> getLayouts(
			long groupId, boolean privateLayout, long[] layoutIds)
		throws PortalException, SystemException {

		List<Layout> layouts = new ArrayList<Layout>();

		for (long layoutId : layoutIds) {
			Layout layout = getLayout(groupId, privateLayout, layoutId);

			layouts.add(layout);
		}

		return layouts;
	}

	/**
	 * Returns all the layouts that match the type and belong to the group.
	 *
	 * @param  groupId the primary key of the group
	 * @param  privateLayout whether the layout is private to the group
	 * @param  type the type of the layouts (optionally {@link
	 *         com.liferay.portal.model.LayoutConstants#TYPE_PORTLET})
	 * @return the matching layouts, or <code>null</code> if no matches were
	 *         found
	 * @throws SystemException if a system exception occurred
	 */
	public List<Layout> getLayouts(
			long groupId, boolean privateLayout, String type)
		throws SystemException {

		return layoutPersistence.findByG_P_T(groupId, privateLayout, type);
	}

	/**
	 * Returns the layout references for all the layouts that belong to the
	 * company and belong to the portlet that matches the preferences.
	 *
	 * @param  companyId the primary key of the company
	 * @param  portletId the primary key of the portlet
	 * @param  preferencesKey the portlet's preference key
	 * @param  preferencesValue the portlet's preference value
	 * @return the layout references of the matching layouts
	 * @throws SystemException if a system exception occurred
	 */
	public LayoutReference[] getLayouts(
			long companyId, String portletId, String preferencesKey,
			String preferencesValue)
		throws SystemException {

		List<LayoutReference> layoutReferences = layoutFinder.findByC_P_P(
			companyId, portletId, preferencesKey, preferencesValue);

		return layoutReferences.toArray(
			new LayoutReference[layoutReferences.size()]);
	}

	public int getLayoutsCount(Group group, boolean privateLayout)
		throws PortalException, SystemException {

		LayoutSet layoutSet = layoutSetPersistence.findByG_P(
			group.getGroupId(), privateLayout);

		int count = layoutSet.getPageCount();

		if (group.isUser()) {
			List<UserGroup> userGroups = userPersistence.getUserGroups(
				group.getClassPK());

			if (!userGroups.isEmpty()) {
				long userGroupClassNameId =
					classNameLocalService.getClassNameId(UserGroup.class);

				for (UserGroup userGroup : userGroups) {
					Group userGroupGroup = groupPersistence.findByC_C_C(
						group.getCompanyId(), userGroupClassNameId,
						userGroup.getUserGroupId());

					layoutSet = layoutSetPersistence.findByG_P(
						userGroupGroup.getGroupId(), privateLayout);

					count += layoutSet.getPageCount();
				}
			}
		}

		return count;
	}

	public int getLayoutsCount(User user, boolean privateLayout)
		throws PortalException, SystemException {

		long classNameId = classNameLocalService.getClassNameId(User.class);

		Group group = groupPersistence.findByC_C_C(
			user.getCompanyId(), classNameId, user.getUserId());

		return getLayoutsCount(group, privateLayout);
	}

	/**
	 * Returns the primary key to use for the next layout.
	 *
	 * @param  groupId the primary key of the group
	 * @param  privateLayout whether the layout is private to the group
	 * @return the primary key to use for the next layout
	 * @throws SystemException if a system exception occurred
	 */
	public long getNextLayoutId(long groupId, boolean privateLayout)
		throws SystemException {

		long nextLayoutId = counterLocalService.increment(
			getCounterName(groupId, privateLayout));

		if (nextLayoutId == 1) {
			List<Layout> layouts = layoutPersistence.findByG_P(
				groupId, privateLayout, 0, 1, new LayoutComparator());

			if (!layouts.isEmpty()) {
				Layout layout = layouts.get(0);

				nextLayoutId = layout.getLayoutId() + 1;

				counterLocalService.reset(
					getCounterName(groupId, privateLayout), nextLayoutId);
			}
		}

		return nextLayoutId;
	}

	/**
	 * Returns all the layouts whose friendly URLs are <code>null</code>
	 *
	 * @return all the layouts whose friendly URLs are <code>null</code>
	 * @throws SystemException if a system exception occurred
	 */
	public List<Layout> getNullFriendlyURLLayouts() throws SystemException {
		return layoutFinder.findByNullFriendlyURL();
	}

	/**
	 * Returns all the layouts within scope of the group
	 *
	 * @param  groupId the primary key of the group
	 * @param  privateLayout whether the layout is private to the group
	 * @return the layouts within scope of the group
	 * @throws SystemException if a system exception occurred
	 */
	public List<Layout> getScopeGroupLayouts(
			long groupId, boolean privateLayout)
		throws SystemException {

		return layoutFinder.findByScopeGroup(groupId, privateLayout);
	}

	public boolean hasLayouts(Group group, boolean privateLayout)
		throws PortalException, SystemException {

		LayoutSet layoutSet = layoutSetPersistence.findByG_P(
			group.getGroupId(), privateLayout);

		if (layoutSet.getPageCount() > 0) {
			return true;
		}

		if (group.isUser()) {
			List<UserGroup> userGroups = userPersistence.getUserGroups(
				group.getClassPK());

			if (!userGroups.isEmpty()) {
				long userGroupClassNameId =
					classNameLocalService.getClassNameId(UserGroup.class);

				for (UserGroup userGroup : userGroups) {
					Group userGroupGroup = groupPersistence.findByC_C_C(
						group.getCompanyId(), userGroupClassNameId,
						userGroup.getUserGroupId());

					layoutSet = layoutSetPersistence.findByG_P(
						userGroupGroup.getGroupId(), privateLayout);

					if (layoutSet.getPageCount() > 0) {
						return true;
					}
				}
			}
		}

		return false;
	}

	/**
	 * Returns <code>true</code> if the group has any layouts;
	 * <code>false</code> otherwise.
	 *
	 * @param  groupId the primary key of the group
	 * @param  privateLayout whether the layout is private to the group
	 * @param  parentLayoutId the primary key of the parent layout
	 * @return <code>true</code> if the group has any layouts;
	 *         <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean hasLayouts(
			long groupId, boolean privateLayout, long parentLayoutId)
		throws SystemException {

		return layoutPersistence.countByG_P_P(
			groupId, privateLayout, parentLayoutId) > 0;
	}

	public boolean hasLayouts(User user, boolean privateLayout)
		throws PortalException, SystemException {

		long classNameId = classNameLocalService.getClassNameId(User.class);

		Group group = groupPersistence.findByC_C_C(
			user.getCompanyId(), classNameId, user.getUserId());

		return hasLayouts(group, privateLayout);
	}

	/**
	 * Imports the layouts from the byte array.
	 *
	 * @param  userId the primary key of the user
	 * @param  groupId the primary key of the group
	 * @param  privateLayout whether the layout is private to the group
	 * @param  parameterMap the mapping of parameters indicating which
	 *         information will be imported. For information on the keys used in
	 *         the map see {@link
	 *         com.liferay.portal.kernel.lar.PortletDataHandlerKeys}.
	 * @param  bytes the byte array with the data
	 * @throws PortalException if a group or user with the primary key could not
	 *         be found, or if some other portal exception occurred
	 * @throws SystemException if a system exception occurred
	 * @see    com.liferay.portal.lar.LayoutImporter
	 */
	public void importLayouts(
			long userId, long groupId, boolean privateLayout,
			Map<String, String[]> parameterMap, byte[] bytes)
		throws PortalException, SystemException {

		importLayouts(
			userId, groupId, privateLayout, parameterMap,
			new UnsyncByteArrayInputStream(bytes));
	}

	/**
	 * Imports the layouts from the file.
	 *
	 * @param  userId the primary key of the user
	 * @param  groupId the primary key of the group
	 * @param  privateLayout whether the layout is private to the group
	 * @param  parameterMap the mapping of parameters indicating which
	 *         information will be imported. For information on the keys used in
	 *         the map see {@link
	 *         com.liferay.portal.kernel.lar.PortletDataHandlerKeys}.
	 * @param  file the LAR file with the data
	 * @throws PortalException if a group or user with the primary key could not
	 *         be found, or if some other portal exception occurred
	 * @throws SystemException if a system exception occurred
	 * @see    com.liferay.portal.lar.LayoutImporter
	 */
	public void importLayouts(
			long userId, long groupId, boolean privateLayout,
			Map<String, String[]> parameterMap, File file)
		throws PortalException, SystemException {

		try {
			LayoutImporter layoutImporter = new LayoutImporter();

			layoutImporter.importLayouts(
				userId, groupId, privateLayout, parameterMap, file);
		}
		catch (PortalException pe) {
			throw pe;
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * Imports the layouts from the input stream.
	 *
	 * @param  userId the primary key of the user
	 * @param  groupId the primary key of the group
	 * @param  privateLayout whether the layout is private to the group
	 * @param  parameterMap the mapping of parameters indicating which
	 *         information will be imported. For information on the keys used in
	 *         the map see {@link
	 *         com.liferay.portal.kernel.lar.PortletDataHandlerKeys}.
	 * @param  is the input stream
	 * @throws PortalException if a group or user with the primary key could not
	 *         be found, or if some other portal exception occurred
	 * @throws SystemException if a system exception occurred
	 * @see    com.liferay.portal.lar.LayoutImporter
	 */
	public void importLayouts(
			long userId, long groupId, boolean privateLayout,
			Map<String, String[]> parameterMap, InputStream is)
		throws PortalException, SystemException {

		try {
			File file = FileUtil.createTempFile("lar");

			FileUtil.write(file, is);

			importLayouts(userId, groupId, privateLayout, parameterMap, file);
		}
		catch (IOException e) {
			throw new SystemException(e);
		}
	}

	/**
	 * Imports the portlet information (categories, permissions, ... etc.) from
	 * the file.
	 *
	 * @param  userId the primary key of the user
	 * @param  plid the primary key of the target layout
	 * @param  groupId the primary key of the target group
	 * @param  portletId the primary key of the portlet
	 * @param  parameterMap the mapping of parameters indicating which
	 *         information will be imported. For information on the keys used in
	 *         the map see {@link
	 *         com.liferay.portal.kernel.lar.PortletDataHandlerKeys}.
	 * @param  file the LAR file with the data
	 * @throws PortalException if a group, layout, portlet or user with the
	 *         primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void importPortletInfo(
			long userId, long plid, long groupId, String portletId,
			Map<String, String[]> parameterMap, File file)
		throws PortalException, SystemException {

		try {
			PortletImporter portletImporter = new PortletImporter();

			portletImporter.importPortletInfo(
				userId, plid, groupId, portletId, parameterMap, file);
		}
		catch (PortalException pe) {
			throw pe;
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * Imports the portlet information (categories, permissions, ... etc.) from
	 * the input stream.
	 *
	 * @param  userId the primary key of the user
	 * @param  plid the primary key of the layout
	 * @param  groupId the primary key of the group
	 * @param  portletId the primary key of the portlet
	 * @param  parameterMap the mapping of parameters indicating which
	 *         information will be imported. For information on the keys used in
	 *         the map see {@link
	 *         com.liferay.portal.kernel.lar.PortletDataHandlerKeys}.
	 * @param  is the input stream
	 * @throws PortalException if a group, portlet, layout or user with the
	 *         primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void importPortletInfo(
			long userId, long plid, long groupId, String portletId,
			Map<String, String[]> parameterMap, InputStream is)
		throws PortalException, SystemException {

		try {
			File file = FileUtil.createTempFile("lar");

			FileUtil.write(file, is);

			importPortletInfo(
				userId, plid, groupId, portletId, parameterMap, file);
		}
		catch (IOException e) {
			throw new SystemException(e);
		}
	}

	/**
	 * Sets the layouts for the group, replacing and prioritizing all layouts of
	 * the parent layout.
	 *
	 * @param  groupId the primary key of the group
	 * @param  privateLayout whether the layout is private to the group
	 * @param  parentLayoutId the primary key of the parent layout
	 * @param  layoutIds the primary keys of the layouts
	 * @param  serviceContext the service context
	 * @throws PortalException if a group or layout with the primary key could
	 *         not be found, if no layouts were specified, if the first layout
	 *         was not page-able, if the first layout was hidden, or if some
	 *         other portal exception occurred
	 * @throws SystemException if a system exception occurred
	 */
	public void setLayouts(
			long groupId, boolean privateLayout, long parentLayoutId,
			long[] layoutIds, ServiceContext serviceContext)
		throws PortalException, SystemException {

		if (layoutIds == null) {
			return;
		}

		if (parentLayoutId == LayoutConstants.DEFAULT_PARENT_LAYOUT_ID) {
			if (layoutIds.length < 1) {
				throw new RequiredLayoutException(
					RequiredLayoutException.AT_LEAST_ONE);
			}

			Layout layout = layoutPersistence.findByG_P_L(
				groupId, privateLayout, layoutIds[0]);

			if (!PortalUtil.isLayoutFirstPageable(layout.getType())) {
				throw new RequiredLayoutException(
					RequiredLayoutException.FIRST_LAYOUT_TYPE);
			}

			if (layout.isHidden()) {
				throw new RequiredLayoutException(
					RequiredLayoutException.FIRST_LAYOUT_HIDDEN);
			}
		}

		Set<Long> layoutIdsSet = new LinkedHashSet<Long>();

		for (long layoutId : layoutIds) {
			layoutIdsSet.add(layoutId);
		}

		Set<Long> newLayoutIdsSet = new HashSet<Long>();

		List<Layout> layouts = layoutPersistence.findByG_P_P(
			groupId, privateLayout, parentLayoutId);

		for (Layout layout : layouts) {
			if (!layoutIdsSet.contains(layout.getLayoutId())) {
				deleteLayout(layout, true, serviceContext);
			}
			else {
				newLayoutIdsSet.add(layout.getLayoutId());
			}
		}

		int priority = 0;

		for (long layoutId : layoutIdsSet) {
			Layout layout = layoutPersistence.findByG_P_L(
				groupId, privateLayout, layoutId);

			layout.setPriority(priority++);

			layoutPersistence.update(layout, false);
		}

		layoutSetLocalService.updatePageCount(groupId, privateLayout);
	}

	/**
	 * Updates the friendly URL of the layout.
	 *
	 * @param  plid the primary key of the layout
	 * @param  friendlyURL the friendly URL to be assigned
	 * @return the updated layout
	 * @throws PortalException if a group or layout with the primary key could
	 *         not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Layout updateFriendlyURL(long plid, String friendlyURL)
		throws PortalException, SystemException {

		Date now = new Date();

		Layout layout = layoutPersistence.findByPrimaryKey(plid);

		friendlyURL = getFriendlyURL(
			layout.getGroupId(), layout.isPrivateLayout(),
			layout.getLayoutId(), StringPool.BLANK, friendlyURL);

		validateFriendlyURL(
			layout.getGroupId(), layout.isPrivateLayout(), layout.getLayoutId(),
			friendlyURL);

		layout.setModifiedDate(now);
		layout.setFriendlyURL(friendlyURL);

		layoutPersistence.update(layout, false);

		return layout;
	}

	/**
	 * Updates the layout.
	 *
	 * @param  groupId the primary key of the group
	 * @param  privateLayout whether the layout is private to the group
	 * @param  layoutId the primary key of the layout
	 * @param  parentLayoutId the primary key of the layout's new parent layout
	 * @param  nameMap the locales and localized names to merge (optionally
	 *         <code>null</code>)
	 * @param  titleMap the locales and localized titles to merge (optionally
	 *         <code>null</code>)
	 * @param  descriptionMap the locales and localized descriptions to merge
	 *         (optionally <code>null</code>)
	 * @param  keywordsMap the locales and localized keywords to merge
	 *         (optionally <code>null</code>)
	 * @param  robotsMap the locales and localized robots to merge (optionally
	 *         <code>null</code>)
	 * @param  type the layout's new type (optionally {@link
	 *         com.liferay.portal.model.LayoutConstants#TYPE_PORTLET})
	 * @param  hidden whether the layout is hidden
	 * @param  friendlyURL the layout's new friendly URL (optionally {@link
	 *         com.liferay.portal.util.PropsValues#DEFAULT_USER_PRIVATE_LAYOUT_FRIENDLY_URL}
	 *         or {@link
	 *         com.liferay.portal.util.PropsValues#DEFAULT_USER_PRIVATE_LAYOUT_FRIENDLY_URL}).
	 *         The default values can be overridden in
	 *         <code>portal-ext.properties</code> by specifying new values for
	 *         the corresponding properties defined in {@link
	 *         com.liferay.portal.util.PropsValues}. To see how the URL is
	 *         normalized when accessed see {@link
	 *         com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil#normalize(
	 *         String)}.
	 * @param  iconImage whether the icon image will be updated
	 * @param  iconBytes the byte array of the layout's new icon image
	 * @param  serviceContext the service context. Can set the modification date
	 *         and expando bridge attributes for the layout. For layouts that
	 *         are linked to a layout prototype, attributes named
	 *         'layoutPrototypeUuid' and 'layoutPrototypeLinkedEnabled' can be
	 *         specified to provide the unique identifier of the source
	 *         prototype and a boolean to determined whether a link to it should
	 *         be enabled to activate propagation of changes made to the linked
	 *         page in the prototype.
	 * @return the updated layout
	 * @throws PortalException if a group or layout with the primary key could
	 *         not be found, if a unique friendly URL could not be generated, if
	 *         a valid parent layout ID to use could not be found, or if the
	 *         layout parameters were invalid
	 * @throws SystemException if a system exception occurred
	 */
	public Layout updateLayout(
			long groupId, boolean privateLayout, long layoutId,
			long parentLayoutId, Map<Locale, String> nameMap,
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			Map<Locale, String> keywordsMap, Map<Locale, String> robotsMap,
			String type, boolean hidden, String friendlyURL, Boolean iconImage,
			byte[] iconBytes, ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Layout

		parentLayoutId = getParentLayoutId(
			groupId, privateLayout, parentLayoutId);
		String name = nameMap.get(LocaleUtil.getDefault());
		friendlyURL = getFriendlyURL(
			groupId, privateLayout, layoutId, StringPool.BLANK, friendlyURL);

		validate(
			groupId, privateLayout, layoutId, parentLayoutId, name, type,
			hidden, friendlyURL);

		validateParentLayoutId(
			groupId, privateLayout, layoutId, parentLayoutId);

		Date now = new Date();

		Layout layout = layoutPersistence.findByG_P_L(
			groupId, privateLayout, layoutId);

		List<Locale> modifiedLocales = LocalizationUtil.getModifiedLocales(
			layout.getNameMap(), nameMap);

		if (parentLayoutId != layout.getParentLayoutId()) {
			layout.setPriority(
				getNextPriority(groupId, privateLayout, parentLayoutId));
		}

		layout.setModifiedDate(serviceContext.getModifiedDate(now));
		layout.setParentLayoutId(parentLayoutId);
		layout.setNameMap(nameMap);
		layout.setTitleMap(titleMap);
		layout.setDescriptionMap(descriptionMap);
		layout.setKeywordsMap(keywordsMap);
		layout.setRobotsMap(robotsMap);
		layout.setType(type);
		layout.setHidden(hidden);
		layout.setFriendlyURL(friendlyURL);

		if (iconImage != null) {
			layout.setIconImage(iconImage.booleanValue());

			if (iconImage.booleanValue()) {
				long iconImageId = layout.getIconImageId();

				if (iconImageId <= 0) {
					iconImageId = counterLocalService.increment();

					layout.setIconImageId(iconImageId);
				}
			}
		}

		boolean layoutUpdateable = GetterUtil.getBoolean(
			serviceContext.getAttribute("layoutUpdateable"), true);

		UnicodeProperties typeSettingsProperties =
			layout.getTypeSettingsProperties();

		typeSettingsProperties.put(
			"layoutUpdateable", String.valueOf(layoutUpdateable));

		layout.setTypeSettingsProperties(typeSettingsProperties);

		String layoutPrototypeUuid = ParamUtil.getString(
			serviceContext, "layoutPrototypeUuid");
		boolean layoutPrototypeLinkEnabled = ParamUtil.getBoolean(
			serviceContext, "layoutPrototypeLinkEnabled");

		if (Validator.isNotNull(layoutPrototypeUuid)) {
			layout.setLayoutPrototypeUuid(layoutPrototypeUuid);
			layout.setLayoutPrototypeLinkEnabled(layoutPrototypeLinkEnabled);

			LayoutPrototype layoutPrototype =
				layoutPrototypeLocalService.getLayoutPrototypeByUuid(
					layoutPrototypeUuid);

			Layout layoutPrototypeLayout = layoutPrototype.getLayout();

			layout.setSourcePrototypeLayoutUuid(
				layoutPrototypeLayout.getUuid());
		}

		layoutPersistence.update(layout, false);

		// Icon

		if (iconImage != null) {
			if (!iconImage.booleanValue()) {
				imageLocalService.deleteImage(layout.getIconImageId());
			}
			else if ((iconBytes != null) && (iconBytes.length > 0)) {
				imageLocalService.updateImage(
					layout.getIconImageId(), iconBytes);
			}
		}

		// Portlet preferences

		if (!modifiedLocales.isEmpty()) {
			updateScopedPortletNames(
				groupId, privateLayout, layoutId, nameMap, modifiedLocales);
		}

		// Expando

		ExpandoBridge expandoBridge = layout.getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);

		return layout;
	}

	/**
	 * Updates the layout replacing its type settings.
	 *
	 * @param  groupId the primary key of the group
	 * @param  privateLayout whether the layout is private to the group
	 * @param  layoutId the primary key of the layout
	 * @param  typeSettings the settings to load the unicode properties object.
	 *         See {@link com.liferay.portal.kernel.util.UnicodeProperties
	 *         #fastLoad(String)}.
	 * @return the updated layout
	 * @throws PortalException if a matching layout could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Layout updateLayout(
			long groupId, boolean privateLayout, long layoutId,
			String typeSettings)
		throws PortalException, SystemException {

		Date now = new Date();

		UnicodeProperties typeSettingsProperties = new UnicodeProperties();

		DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
			PropsValues.INDEX_DATE_FORMAT_PATTERN);

		typeSettingsProperties.fastLoad(typeSettings);

		typeSettingsProperties.setProperty(
			"modifiedDate", dateFormat.format(now));

		Layout layout = layoutPersistence.findByG_P_L(
			groupId, privateLayout, layoutId);

		layout.setModifiedDate(now);
		layout.setTypeSettings(typeSettingsProperties.toString());

		layoutPersistence.update(layout, false);

		return layout;
	}

	/**
	 * Updates the look and feel of the layout.
	 *
	 * @param  groupId the primary key of the group
	 * @param  privateLayout whether the layout is private to the group
	 * @param  layoutId the primary key of the layout
	 * @param  themeId the primary key of the layout's new theme
	 * @param  colorSchemeId the primary key of the layout's new color scheme
	 * @param  css the layout's new CSS
	 * @param  wapTheme whether the theme is for WAP browsers
	 * @return the updated layout
	 * @throws PortalException if a matching layout could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Layout updateLookAndFeel(
			long groupId, boolean privateLayout, long layoutId, String themeId,
			String colorSchemeId, String css, boolean wapTheme)
		throws PortalException, SystemException {

		Date now = new Date();

		Layout layout = layoutPersistence.findByG_P_L(
			groupId, privateLayout, layoutId);

		layout.setModifiedDate(now);

		if (wapTheme) {
			layout.setWapThemeId(themeId);
			layout.setWapColorSchemeId(colorSchemeId);
		}
		else {
			layout.setThemeId(themeId);
			layout.setColorSchemeId(colorSchemeId);
			layout.setCss(css);
		}

		layoutPersistence.update(layout, false);

		return layout;
	}

	/**
	 * Updates the name of the layout.
	 *
	 * @param  layout the layout to be updated
	 * @param  name the layout's new name
	 * @param  languageId the primary key of the language. For more information
	 *         see {@link java.util.Locale}.
	 * @return the updated layout
	 * @throws PortalException if the new name was <code>null</code>
	 * @throws SystemException if a system exception occurred
	 */
	public Layout updateName(Layout layout, String name, String languageId)
		throws PortalException, SystemException {

		Date now = new Date();

		validateName(name, languageId);

		layout.setModifiedDate(now);
		layout.setName(name, LocaleUtil.fromLanguageId(languageId));

		layoutPersistence.update(layout, false);

		return layout;
	}

	/**
	 * Updates the name of the layout matching the group, layout ID, and
	 * privacy.
	 *
	 * @param  groupId the primary key of the group
	 * @param  privateLayout whether the layout is private to the group
	 * @param  layoutId the primary key of the layout
	 * @param  name the layout's new name
	 * @param  languageId the primary key of the language. For more information
	 *         see {@link java.util.Locale}.
	 * @return the updated layout
	 * @throws PortalException if a matching layout could not be found or if the
	 *         new name was <code>null</code>
	 * @throws SystemException if a system exception occurred
	 */
	public Layout updateName(
			long groupId, boolean privateLayout, long layoutId, String name,
			String languageId)
		throws PortalException, SystemException {

		Layout layout = layoutPersistence.findByG_P_L(
			groupId, privateLayout, layoutId);

		return layoutLocalService.updateName(layout, name, languageId);
	}

	/**
	 * Updates the name of the layout matching the primary key.
	 *
	 * @param  plid the primary key of the layout
	 * @param  name the name to be assigned
	 * @param  languageId the primary key of the language. For more information
	 *         see {@link java.util.Locale}.
	 * @return the updated layout
	 * @throws PortalException if a layout with the primary key could not be
	 *         found or if the name was <code>null</code>
	 * @throws SystemException if a system exception occurred
	 */
	public Layout updateName(long plid, String name, String languageId)
		throws PortalException, SystemException {

		Layout layout = layoutPersistence.findByPrimaryKey(plid);

		return layoutLocalService.updateName(layout, name, languageId);
	}

	/**
	 * Updates the parent layout ID of the layout matching the group, layout ID,
	 * and privacy.
	 *
	 * @param  groupId the primary key of the group
	 * @param  privateLayout whether the layout is private to the group
	 * @param  layoutId the primary key of the layout
	 * @param  parentLayoutId the primary key to be assigned to the parent
	 *         layout
	 * @return the matching layout
	 * @throws PortalException if a valid parent layout ID to use could not be
	 *         found or if a matching layout could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Layout updateParentLayoutId(
			long groupId, boolean privateLayout, long layoutId,
			long parentLayoutId)
		throws PortalException, SystemException {

		parentLayoutId = getParentLayoutId(
			groupId, privateLayout, parentLayoutId);

		validateParentLayoutId(
			groupId, privateLayout, layoutId, parentLayoutId);

		Date now = new Date();

		Layout layout = layoutPersistence.findByG_P_L(
			groupId, privateLayout, layoutId);

		if (parentLayoutId != layout.getParentLayoutId()) {
			layout.setPriority(
				getNextPriority(groupId, privateLayout, parentLayoutId));
		}

		layout.setModifiedDate(now);
		layout.setParentLayoutId(parentLayoutId);

		layoutPersistence.update(layout, false);

		return layout;
	}

	/**
	 * Updates the parent layout ID of the layout matching the primary key. If a
	 * layout matching the parent primary key is found, the layout ID of that
	 * layout is assigned, otherwise {@link
	 * com.liferay.portal.model.LayoutConstants#DEFAULT_PARENT_LAYOUT_ID} is
	 * assigned.
	 *
	 * @param  plid the primary key of the layout
	 * @param  parentPlid the primary key of the parent layout
	 * @return the layout matching the primary key
	 * @throws PortalException if a layout with the primary key could not be
	 *         found or if a valid parent layout ID to use could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Layout updateParentLayoutId(long plid, long parentPlid)
		throws PortalException, SystemException {

		Date now = new Date();

		Layout layout = layoutPersistence.findByPrimaryKey(plid);

		long parentLayoutId = LayoutConstants.DEFAULT_PARENT_LAYOUT_ID;

		if (parentPlid > 0) {
			Layout parentLayout = layoutPersistence.fetchByPrimaryKey(
				parentPlid);

			if (parentLayout != null) {
				parentLayoutId = parentLayout.getLayoutId();
			}
		}

		parentLayoutId = getParentLayoutId(
			layout.getGroupId(), layout.isPrivateLayout(), parentLayoutId);

		validateParentLayoutId(
			layout.getGroupId(), layout.isPrivateLayout(), layout.getLayoutId(),
			parentLayoutId);

		if (parentLayoutId != layout.getParentLayoutId()) {
			int priority = getNextPriority(
				layout.getGroupId(), layout.isPrivateLayout(), parentLayoutId);

			layout.setPriority(priority);
		}

		layout.setModifiedDate(now);
		layout.setParentLayoutId(parentLayoutId);

		layoutPersistence.update(layout, false);

		return layout;
	}

	/**
	 * Updates the priority of the layout.
	 *
	 * @param  layout the layout to be updated
	 * @param  priority the layout's new priority
	 * @return the updated layout
	 * @throws SystemException if a system exception occurred
	 */
	public Layout updatePriority(Layout layout, int priority)
		throws SystemException {

		if (layout.getPriority() == priority) {
			return layout;
		}

		Date now = new Date();

		boolean lessThan = false;

		if (layout.getPriority() < priority) {
			lessThan = true;
		}

		layout.setModifiedDate(now);
		layout.setPriority(priority);

		layoutPersistence.update(layout, false);

		priority = 0;

		List<Layout> layouts = layoutPersistence.findByG_P_P(
			layout.getGroupId(), layout.isPrivateLayout(),
			layout.getParentLayoutId());

		layouts = ListUtil.sort(
			layouts, new LayoutPriorityComparator(layout, lessThan));

		for (Layout curLayout : layouts) {
			curLayout.setModifiedDate(now);
			curLayout.setPriority(priority++);

			layoutPersistence.update(curLayout, false);

			if (curLayout.equals(layout)) {
				layout = curLayout;
			}
		}

		return layout;
	}

	/**
	 * Updates the priority of the layout matching the group, layout ID, and
	 * privacy.
	 *
	 * @param  groupId the primary key of the group
	 * @param  privateLayout whether the layout is private to the group
	 * @param  layoutId the primary key of the layout
	 * @param  priority the layout's new priority
	 * @return the updated layout
	 * @throws PortalException if a matching layout could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Layout updatePriority(
			long groupId, boolean privateLayout, long layoutId, int priority)
		throws PortalException, SystemException {

		Layout layout = layoutPersistence.findByG_P_L(
			groupId, privateLayout, layoutId);

		return updatePriority(layout, priority);
	}

	/**
	 * Updates the priority of the layout matching the primary key.
	 *
	 * @param  plid the primary key of the layout
	 * @param  priority the layout's new priority
	 * @return the updated layout
	 * @throws PortalException if a layout with the primary key could not be
	 *         found
	 * @throws SystemException if a system exception occurred
	 */
	public Layout updatePriority(long plid, int priority)
		throws PortalException, SystemException {

		Layout layout = layoutPersistence.findByPrimaryKey(plid);

		return updatePriority(layout, priority);
	}

	public void updateScopedPortletNames(
			long groupId, boolean privateLayout, long layoutId,
			Map<Locale, String> nameMap, List<Locale> nameMapModifiedLocales)
		throws PortalException, SystemException {

		Layout layout = layoutPersistence.findByG_P_L(
			groupId, privateLayout, layoutId);

		DynamicQuery portletPreferencesDynamicQuery =
			DynamicQueryFactoryUtil.forClass(
				PortletPreferences.class, PortletPreferencesImpl.TABLE_NAME);

		Property plidProperty = PropertyFactoryUtil.forName("plid");

		DynamicQuery layoutDynamicQuery = DynamicQueryFactoryUtil.forClass(
			Layout.class, LayoutImpl.TABLE_NAME);

		Projection plidProjection = ProjectionFactoryUtil.property("plid");

		layoutDynamicQuery.setProjection(plidProjection);

		Property groupIdProperty = PropertyFactoryUtil.forName("groupId");

		layoutDynamicQuery.add(groupIdProperty.eq(groupId));

		Property privateLayoutProperty = PropertyFactoryUtil.forName(
			"privateLayout");

		layoutDynamicQuery.add(privateLayoutProperty.eq(privateLayout));

		portletPreferencesDynamicQuery.add(plidProperty.in(layoutDynamicQuery));

		Junction junction = RestrictionsFactoryUtil.disjunction();

		List<Portlet> scopablePortlets =
			portletLocalService.getScopablePortlets();

		for (Portlet scopablePortlet :scopablePortlets) {
			if (scopablePortlet.isInstanceable()) {
				Criterion criterion = RestrictionsFactoryUtil.like(
					"portletId",
					scopablePortlet.getPortletId() +
						PortletConstants.INSTANCE_SEPARATOR +
							StringPool.PERCENT);

				junction.add(criterion);
			}
			else{
				Criterion criterion = RestrictionsFactoryUtil.eq(
					"portletId", scopablePortlet.getPortletId());

				junction.add(criterion);
			}
		}

		portletPreferencesDynamicQuery.add(junction);

		List<PortletPreferences> portletPreferencesList =
			portletPreferencesLocalService.dynamicQuery(
				portletPreferencesDynamicQuery);

		for (PortletPreferences portletPreferences : portletPreferencesList) {
			if ((portletPreferences.getPortletId() == null)) {
				continue;
			}

			Layout curLayout = layoutPersistence.findByPrimaryKey(
				portletPreferences.getPlid());

			javax.portlet.PortletPreferences jxPreferences =
				PortletPreferencesFactoryUtil.getLayoutPortletSetup(
					curLayout, portletPreferences.getPortletId());

			String scopeLayoutUuid = GetterUtil.getString(
				jxPreferences.getValue("lfrScopeLayoutUuid", null));

			if (!scopeLayoutUuid.equals(layout.getUuid())) {
				continue;
			}

			for (Locale locale : nameMapModifiedLocales) {
				String languageId = LanguageUtil.getLanguageId(locale);

				String portletTitle = PortalUtil.getPortletTitle(
					PortletConstants.getRootPortletId(
						portletPreferences.getPortletId()), languageId);

				String newPortletTitle = PortalUtil.getNewPortletTitle(
					portletTitle, curLayout.getName(languageId),
					nameMap.get(locale));

				if (newPortletTitle.equals(portletTitle)) {
					continue;
				}

				try {
					jxPreferences.setValue(
						"portletSetupTitle_" + languageId, newPortletTitle);
					jxPreferences.setValue(
						"portletSetupUseCustomTitle", Boolean.TRUE.toString());

					jxPreferences.store();
				}
				catch (IOException ioe) {
					throw new SystemException(ioe);
				}
				catch (PortletException pe) {
					throw new SystemException(pe);
				}
			}
		}
	}

	/**
	 * Updates the names of the portlets within scope of the group, the scope of
	 * the layout's universally unique identifier, and the privacy.
	 *
	 * @param  groupId the primary key of the group
	 * @param  privateLayout whether the layout is private to the group
	 * @param  layoutId the primary key of the layout whose universally unique
	 *         identifier to match
	 * @param  name the new name for the portlets
	 * @param  languageId the primary key of the language
	 * @throws PortalException if a matching layout could not be found
	 * @throws SystemException if a system exception occurred
	 * @see    com.liferay.portlet.portletconfiguration.action.EditScopeAction
	 */
	public void updateScopedPortletNames(
			long groupId, boolean privateLayout, long layoutId, String name,
			String languageId)
		throws PortalException, SystemException {

		Map<Locale, String> map = new HashMap<Locale, String>();

		Locale locale = LocaleUtil.fromLanguageId(languageId);

		map.put(locale, name);

		List<Locale> locales = new ArrayList<Locale>();

		locales.add(locale);

		updateScopedPortletNames(
			groupId, privateLayout, layoutId, map, locales);
	}

	protected String getFriendlyURL(
			long groupId, boolean privateLayout, long layoutId,
			String name, String friendlyURL)
		throws PortalException, SystemException {

		friendlyURL = getFriendlyURL(friendlyURL);

		if (Validator.isNull(friendlyURL)) {
			friendlyURL = StringPool.SLASH + getFriendlyURL(name);

			String originalFriendlyURL = friendlyURL;

			for (int i = 1;; i++) {
				try {
					validateFriendlyURL(
						groupId, privateLayout, layoutId, friendlyURL);

					break;
				}
				catch (LayoutFriendlyURLException lfurle) {
					int type = lfurle.getType();

					if (type == LayoutFriendlyURLException.DUPLICATE) {
						friendlyURL = originalFriendlyURL + i;
					}
					else {
						friendlyURL = StringPool.SLASH + layoutId;

						break;
					}
				}
			}
		}

		return friendlyURL;
	}

	protected String getFriendlyURL(String friendlyURL) {
		return FriendlyURLNormalizerUtil.normalize(friendlyURL);
	}

	protected int getNextPriority(
			long groupId, boolean privateLayout, long parentLayoutId)
		throws SystemException {

		List<Layout> layouts = layoutPersistence.findByG_P_P(
			groupId, privateLayout, parentLayoutId);

		if (layouts.size() == 0) {
			return 0;
		}

		Layout layout = layouts.get(layouts.size() - 1);

		return layout.getPriority() + 1;
	}

	protected long getParentLayoutId(
			long groupId, boolean privateLayout, long parentLayoutId)
		throws SystemException {

		if (parentLayoutId != LayoutConstants.DEFAULT_PARENT_LAYOUT_ID) {

			// Ensure parent layout exists

			Layout parentLayout = layoutPersistence.fetchByG_P_L(
				groupId, privateLayout, parentLayoutId);

			if (parentLayout == null) {
				parentLayoutId = LayoutConstants.DEFAULT_PARENT_LAYOUT_ID;
			}
		}

		return parentLayoutId;
	}

	protected void validate(
			long groupId, boolean privateLayout, long layoutId,
			long parentLayoutId, String name, String type, boolean hidden,
			String friendlyURL)
		throws PortalException, SystemException {

		validateName(name);

		boolean firstLayout = false;

		if (parentLayoutId == LayoutConstants.DEFAULT_PARENT_LAYOUT_ID) {
			List<Layout> layouts = layoutPersistence.findByG_P_P(
				groupId, privateLayout, parentLayoutId, 0, 1);

			if (layouts.size() == 0) {
				firstLayout = true;
			}
			else {
				long firstLayoutId = layouts.get(0).getLayoutId();

				if (firstLayoutId == layoutId) {
					firstLayout = true;
				}
			}
		}

		if (firstLayout) {
			validateFirstLayout(type);
		}

		if (!PortalUtil.isLayoutParentable(type)) {
			if (layoutPersistence.countByG_P_P(
					groupId, privateLayout, layoutId) > 0) {

				throw new LayoutTypeException(
					LayoutTypeException.NOT_PARENTABLE);
			}
		}

		validateFriendlyURL(groupId, privateLayout, layoutId, friendlyURL);
	}

	protected void validateFirstLayout(String type)
		throws PortalException {

		if (Validator.isNull(type) || !PortalUtil.isLayoutFirstPageable(type)) {
			LayoutTypeException lte = new LayoutTypeException(
				LayoutTypeException.FIRST_LAYOUT);

			lte.setLayoutType(type);

			throw lte;
		}
	}

	protected void validateFriendlyURL(
			long groupId, boolean privateLayout, long layoutId,
			String friendlyURL)
		throws PortalException, SystemException {

		if (Validator.isNull(friendlyURL)) {
			return;
		}

		int exceptionType = LayoutImpl.validateFriendlyURL(friendlyURL);

		if (exceptionType != -1) {
			throw new LayoutFriendlyURLException(exceptionType);
		}

		Layout layout = layoutPersistence.fetchByG_P_F(
			groupId, privateLayout, friendlyURL);

		if ((layout != null) && (layout.getLayoutId() != layoutId)) {
			throw new LayoutFriendlyURLException(
				LayoutFriendlyURLException.DUPLICATE);
		}

		LayoutImpl.validateFriendlyURLKeyword(friendlyURL);

		/*List<FriendlyURLMapper> friendlyURLMappers =
			portletLocalService.getFriendlyURLMappers();

		for (FriendlyURLMapper friendlyURLMapper : friendlyURLMappers) {
			if (friendlyURL.indexOf(friendlyURLMapper.getMapping()) != -1) {
				LayoutFriendlyURLException lfurle =
					new LayoutFriendlyURLException(
						LayoutFriendlyURLException.KEYWORD_CONFLICT);

				lfurle.setKeywordConflict(friendlyURLMapper.getMapping());

				throw lfurle;
			}
		}*/

		String layoutIdFriendlyURL = friendlyURL.substring(1);

		if (Validator.isNumber(layoutIdFriendlyURL) &&
			!layoutIdFriendlyURL.equals(String.valueOf(layoutId))) {

			LayoutFriendlyURLException lfurle = new LayoutFriendlyURLException(
				LayoutFriendlyURLException.POSSIBLE_DUPLICATE);

			lfurle.setKeywordConflict(layoutIdFriendlyURL);

			throw lfurle;
		}
	}

	protected void validateName(String name) throws PortalException {
		if (Validator.isNull(name)) {
			throw new LayoutNameException();
		}
	}

	protected void validateName(String name, String languageId)
		throws PortalException {

		String defaultLanguageId = LocaleUtil.toLanguageId(
			LocaleUtil.getDefault());

		if (defaultLanguageId.equals(languageId)) {
			validateName(name);
		}
	}

	protected void validateParentLayoutId(
			long groupId, boolean privateLayout, long layoutId,
			long parentLayoutId)
		throws PortalException, SystemException {

		Layout layout = layoutPersistence.findByG_P_L(
			groupId, privateLayout, layoutId);

		if (parentLayoutId != layout.getParentLayoutId()) {

			// Layouts can always be moved to the root level

			if (parentLayoutId == LayoutConstants.DEFAULT_PARENT_LAYOUT_ID) {
				return;
			}

			// Layout cannot become a child of a layout that is not parentable

			Layout parentLayout = layoutPersistence.findByG_P_L(
				groupId, privateLayout, parentLayoutId);

			if (!PortalUtil.isLayoutParentable(parentLayout)) {
				throw new LayoutParentLayoutIdException(
					LayoutParentLayoutIdException.NOT_PARENTABLE);
			}

			// Layout cannot become descendant of itself

			if (PortalUtil.isLayoutDescendant(layout, parentLayoutId)) {
				throw new LayoutParentLayoutIdException(
					LayoutParentLayoutIdException.SELF_DESCENDANT);
			}

			// If layout is moved, the new first layout must be valid

			if (layout.getParentLayoutId() ==
					LayoutConstants.DEFAULT_PARENT_LAYOUT_ID) {

				List<Layout> layouts = layoutPersistence.findByG_P_P(
					groupId, privateLayout,
					LayoutConstants.DEFAULT_PARENT_LAYOUT_ID, 0, 2);

				// You can only reach this point if there are more than two
				// layouts at the root level because of the descendant check

				long firstLayoutId = layouts.get(0).getLayoutId();

				if (firstLayoutId == layoutId) {
					Layout secondLayout = layouts.get(1);

					try {
						validateFirstLayout(secondLayout.getType());
					}
					catch (LayoutHiddenException lhe) {
						throw new LayoutParentLayoutIdException(
							LayoutParentLayoutIdException.FIRST_LAYOUT_HIDDEN);
					}
					catch (LayoutTypeException lte) {
						throw new LayoutParentLayoutIdException(
							LayoutParentLayoutIdException.FIRST_LAYOUT_TYPE);
					}
				}
			}
		}
	}

}