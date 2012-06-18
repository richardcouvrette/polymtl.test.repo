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

import com.liferay.portal.LARFileException;
import com.liferay.portal.LARTypeException;
import com.liferay.portal.LayoutImportException;
import com.liferay.portal.NoSuchLayoutException;
import com.liferay.portal.PortletIdException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.ImportExportThreadLocal;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.lar.PortletDataHandler;
import com.liferay.portal.kernel.lar.PortletDataHandlerKeys;
import com.liferay.portal.kernel.lar.UserIdStrategy;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.kernel.zip.ZipReader;
import com.liferay.portal.kernel.zip.ZipReaderFactoryUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.Lock;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.PortletConstants;
import com.liferay.portal.model.PortletItem;
import com.liferay.portal.model.PortletPreferences;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionCacheUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.PortletItemLocalServiceUtil;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.persistence.PortletPreferencesUtil;
import com.liferay.portal.service.persistence.UserUtil;
import com.liferay.portal.servlet.filters.cache.CacheUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portlet.PortletPreferencesImpl;
import com.liferay.portlet.asset.NoSuchCategoryException;
import com.liferay.portlet.asset.NoSuchEntryException;
import com.liferay.portlet.asset.NoSuchTagException;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetCategoryConstants;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetTag;
import com.liferay.portlet.asset.model.AssetVocabulary;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetLinkLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetTagLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetVocabularyLocalServiceUtil;
import com.liferay.portlet.asset.service.persistence.AssetCategoryUtil;
import com.liferay.portlet.asset.service.persistence.AssetTagUtil;
import com.liferay.portlet.asset.service.persistence.AssetVocabularyUtil;
import com.liferay.portlet.expando.NoSuchTableException;
import com.liferay.portlet.expando.model.ExpandoColumn;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.portlet.expando.util.ExpandoConverterUtil;
import com.liferay.portlet.journalcontent.util.JournalContentUtil;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.ratings.model.RatingsEntry;

import java.io.File;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
public class PortletImporter {

	public void importPortletInfo(
			long userId, long plid, long groupId, String portletId,
			Map<String, String[]> parameterMap, File file)
		throws Exception {

		try {
			ImportExportThreadLocal.setPortletImportInProcess(true);

			doImportPortletInfo(
				userId, plid, groupId, portletId, parameterMap, file);
		}
		finally {
			ImportExportThreadLocal.setPortletImportInProcess(false);

			CacheUtil.clearCache();
			JournalContentUtil.clearCache();
			PermissionCacheUtil.clearCache();
		}
	}

	protected String checkPortletPreferences(
			PortletDataContext portletDataContext, long companyId,
			long ownerId, int ownerType, long plid, String portletId,
			String xml)
		throws Exception {

		String rootPotletId = PortletConstants.getRootPortletId(portletId);

		if (!rootPotletId.equals(PortletKeys.ASSET_PUBLISHER)) {
			return xml;
		}

		PortletPreferencesImpl portletPreferences =
			(PortletPreferencesImpl)PortletPreferencesFactoryUtil.
				fromXML(companyId, ownerId, ownerType, plid, portletId, xml);

		 Enumeration<String> enu = portletPreferences.getNames();

		 while (enu.hasMoreElements()) {
			String name = enu.nextElement();

			String value = GetterUtil.getString(
				portletPreferences.getValue(name, StringPool.BLANK));

			String prefix = "queryName";

			if (value.equalsIgnoreCase("assetCategories") &&
				name.startsWith(prefix)) {

				String index = name.substring(prefix.length(), name.length());

				String queryValuesName = "queryValues" + index;

				String[] importedAssetCategoryPKs =
					portletPreferences.getValues(queryValuesName, null);

				Map<Long, Long> assetCategoryPKs =
					(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
						AssetCategory.class);

				String[] newAssetCategoryPKs = new String[
					importedAssetCategoryPKs.length];

				int i = 0;

				for (String importedAssetCategoryPK :
						importedAssetCategoryPKs) {

					String newAssetCategoryPK = StringUtil.valueOf(
						assetCategoryPKs.get(
							new Long(importedAssetCategoryPK)));

					if (Validator.isNull(newAssetCategoryPK)) {
						newAssetCategoryPK = importedAssetCategoryPK;
					}

					newAssetCategoryPKs[i++] = newAssetCategoryPK;
				}

				portletPreferences.setValues(
					queryValuesName, newAssetCategoryPKs);
			}
		}

		return PortletPreferencesFactoryUtil.toXML(portletPreferences);
	}

	protected void deletePortletData(
			PortletDataContext portletDataContext, String portletId, long plid)
		throws Exception {

		long ownerId = PortletKeys.PREFS_OWNER_ID_DEFAULT;
		int ownerType = PortletKeys.PREFS_OWNER_TYPE_LAYOUT;

		PortletPreferences portletPreferences =
			PortletPreferencesUtil.fetchByO_O_P_P(
				ownerId, ownerType, plid, portletId);

		if (portletPreferences == null) {
			portletPreferences =
				new com.liferay.portal.model.impl.PortletPreferencesImpl();
		}

		String xml = deletePortletData(
			portletDataContext, portletId, portletPreferences);

		if (xml != null) {
			PortletPreferencesLocalServiceUtil.updatePreferences(
				ownerId, ownerType, plid, portletId, xml);
		}
	}

	protected String deletePortletData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		Portlet portlet = PortletLocalServiceUtil.getPortletById(
			portletDataContext.getCompanyId(), portletId);

		if (portlet == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Do not delete portlet data for " + portletId +
						" because the portlet does not exist");
			}

			return null;
		}

		PortletDataHandler portletDataHandler =
			portlet.getPortletDataHandlerInstance();

		if (portletDataHandler == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Do not delete portlet data for " + portletId +
						" because the portlet does not have a " +
							"PortletDataHandler");
			}

			return null;
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Deleting data for " + portletId);
		}

		PortletPreferencesImpl portletPreferencesImpl =
			(PortletPreferencesImpl)
				PortletPreferencesFactoryUtil.fromDefaultXML(
					portletPreferences.getPreferences());

		try {
			portletPreferencesImpl =
				(PortletPreferencesImpl)portletDataHandler.deleteData(
					portletDataContext, portletId, portletPreferencesImpl);
		}
		finally {
			portletDataContext.setGroupId(portletDataContext.getScopeGroupId());
		}

		if (portletPreferencesImpl == null) {
			return null;
		}

		return PortletPreferencesFactoryUtil.toXML(portletPreferencesImpl);
	}

	protected void doImportPortletInfo(
			long userId, long plid, long groupId, String portletId,
			Map<String, String[]> parameterMap, File file)
		throws Exception {

		boolean deletePortletData = MapUtil.getBoolean(
			parameterMap, PortletDataHandlerKeys.DELETE_PORTLET_DATA);
		boolean importPermissions = MapUtil.getBoolean(
			parameterMap, PortletDataHandlerKeys.PERMISSIONS);
		boolean importUserPermissions = MapUtil.getBoolean(
			parameterMap, PortletDataHandlerKeys.PERMISSIONS);
		boolean importPortletData = MapUtil.getBoolean(
			parameterMap, PortletDataHandlerKeys.PORTLET_DATA);
		boolean importPortletArchivedSetups = MapUtil.getBoolean(
			parameterMap, PortletDataHandlerKeys.PORTLET_ARCHIVED_SETUPS);
		boolean importPortletSetup = MapUtil.getBoolean(
			parameterMap, PortletDataHandlerKeys.PORTLET_SETUP);
		boolean importPortletUserPreferences = MapUtil.getBoolean(
			parameterMap, PortletDataHandlerKeys.PORTLET_USER_PREFERENCES);
		String userIdStrategyString = MapUtil.getString(
			parameterMap, PortletDataHandlerKeys.USER_ID_STRATEGY);

		StopWatch stopWatch = null;

		if (_log.isInfoEnabled()) {
			stopWatch = new StopWatch();

			stopWatch.start();
		}

		Layout layout = LayoutLocalServiceUtil.getLayout(plid);

		User user = UserUtil.findByPrimaryKey(userId);

		UserIdStrategy userIdStrategy = getUserIdStrategy(
			user, userIdStrategyString);

		ZipReader zipReader = ZipReaderFactoryUtil.getZipReader(file);

		PortletDataContext portletDataContext = new PortletDataContextImpl(
			layout.getCompanyId(), groupId, parameterMap, new HashSet<String>(),
			userIdStrategy, zipReader);

		portletDataContext.setPortetDataContextListener(
			new PortletDataContextListenerImpl(portletDataContext));

		portletDataContext.setPlid(plid);
		portletDataContext.setPrivateLayout(layout.isPrivateLayout());

		// Manifest

		String xml = portletDataContext.getZipEntryAsString("/manifest.xml");

		Element rootElement = null;

		try {
			Document document = SAXReaderUtil.read(xml);

			rootElement = document.getRootElement();
		}
		catch (Exception e) {
			throw new LARFileException("Unable to read /manifest.xml");
		}

		// Build compatibility

		Element headerElement = rootElement.element("header");

		int buildNumber = ReleaseInfo.getBuildNumber();

		int importBuildNumber = GetterUtil.getInteger(
			headerElement.attributeValue("build-number"));

		if (buildNumber != importBuildNumber) {
			throw new LayoutImportException(
				"LAR build number " + importBuildNumber + " does not match " +
					"portal build number " + buildNumber);
		}

		// Type compatibility

		String type = headerElement.attributeValue("type");

		if (!type.equals("portlet")) {
			throw new LARTypeException(
				"Invalid type of LAR file (" + type + ")");
		}

		// Portlet compatibility

		String rootPortletId = headerElement.attributeValue("root-portlet-id");

		if (!PortletConstants.getRootPortletId(portletId).equals(
				rootPortletId)) {

			throw new PortletIdException("Invalid portlet id " + rootPortletId);
		}

		// Import group id

		long sourceGroupId = GetterUtil.getLong(
			headerElement.attributeValue("group-id"));

		portletDataContext.setSourceGroupId(sourceGroupId);

		// Read asset categories, asset tags, comments, locks, and ratings
		// entries to make them available to the data handlers through the
		// context

		if (importPermissions) {
			_permissionImporter.readPortletDataPermissions(portletDataContext);
		}

		readAssetCategories(portletDataContext);
		readAssetTags(portletDataContext);
		readComments(portletDataContext);
		readExpandoTables(portletDataContext);
		readLocks(portletDataContext);
		readRatingsEntries(portletDataContext);

		// Delete portlet data

		if (_log.isDebugEnabled()) {
			_log.debug("Deleting portlet data");
		}

		if (deletePortletData) {
			deletePortletData(portletDataContext, portletId, plid);
		}

		Element portletElement = null;

		try {
			portletElement = rootElement.element("portlet");

			Document portletDocument = SAXReaderUtil.read(
				portletDataContext.getZipEntryAsString(
					portletElement.attributeValue("path")));

			portletElement = portletDocument.getRootElement();
		}
		catch (DocumentException de) {
			throw new SystemException(de);
		}

		setPortletScope(portletDataContext, portletElement);

		try {

			// Portlet preferences

			importPortletPreferences(
				portletDataContext, layout.getCompanyId(), groupId, layout,
				portletId, portletElement, importPortletSetup,
				importPortletArchivedSetups, importPortletUserPreferences,
				true);

			// Portlet data

			Element portletDataElement = portletElement.element("portlet-data");

			if (importPortletData && (portletDataElement != null)) {
				if (_log.isDebugEnabled()) {
					_log.debug("Importing portlet data");
				}

				importPortletData(
					portletDataContext, portletId, plid, portletDataElement);
			}
		}
		finally {
			resetPortletScope(portletDataContext, groupId);
		}

		// Portlet permissions

		if (importPermissions) {
			if (_log.isDebugEnabled()) {
				_log.debug("Importing portlet permissions");
			}

			LayoutCache layoutCache = new LayoutCache();

			_permissionImporter.importPortletPermissions(
				layoutCache, layout.getCompanyId(), groupId, userId, layout,
				portletElement, portletId, importUserPermissions);

			if ((userId > 0) &&
				((PropsValues.PERMISSIONS_USER_CHECK_ALGORITHM == 5) ||
				 (PropsValues.PERMISSIONS_USER_CHECK_ALGORITHM == 6))) {

				Indexer indexer = IndexerRegistryUtil.getIndexer(User.class);

				indexer.reindex(userId);
			}
		}

		// Asset links

		if (_log.isDebugEnabled()) {
			_log.debug("Importing asset links");
		}

		readAssetLinks(portletDataContext);

		if (_log.isInfoEnabled()) {
			_log.info("Importing portlet takes " + stopWatch.getTime() + " ms");
		}

		zipReader.close();
	}

	/**
	 * @see {@link DLPortletDataHandlerImpl#getFileEntryTypeName(String, long,
	 *      long, String, int)}
	 * @see {@link DLPortletDataHandlerImpl#getFolderName(String, long, long,
	 *      String, int)}
	 */
	protected String getAssetCategoryName(
			String uuid, long parentCategoryId, String name, int count)
		throws Exception {

		AssetCategory assetCategory = null;

		try {
			assetCategory = AssetCategoryUtil.findByP_N_First(
				parentCategoryId, name, null);
		}
		catch (NoSuchCategoryException nsce) {
			return name;
		}

		if (Validator.isNotNull(uuid) && uuid.equals(assetCategory.getUuid())) {
			return name;
		}

		name = StringUtil.appendParentheticalSuffix(name, count);

		return getAssetCategoryName(uuid, parentCategoryId, name, ++count);
	}

	protected String getAssetCategoryPath(
		PortletDataContext portletDataContext, long assetCategoryId) {

		StringBundler sb = new StringBundler(6);

		sb.append(portletDataContext.getSourceRootPath());
		sb.append("/categories/");
		sb.append(assetCategoryId);
		sb.append(".xml");

		return sb.toString();
	}

	protected Map<Locale, String> getAssetCategoryTitleMap(
		AssetCategory assetCategory, String name) {

		Map<Locale, String> titleMap = assetCategory.getTitleMap();

		if (titleMap == null) {
			titleMap = new HashMap<Locale, String>();
		}

		Locale locale = LocaleUtil.getDefault();

		titleMap.put(locale, name);

		return titleMap;
	}

	/**
	 * @see {@link DLPortletDataHandlerImpl#getFileEntryTypeName(String, long,
	 *      long, String, int)}
	 * @see {@link DLPortletDataHandlerImpl#getFolderName(String, long, long,
	 *      String, int)}
	 */
	protected String getAssetVocabularyName(
			String uuid, long groupId, String name, int count)
		throws Exception {

		AssetVocabulary assetVocabulary = AssetVocabularyUtil.fetchByG_N(
			groupId, name);

		if (assetVocabulary == null) {
			return name;
		}

		if (Validator.isNotNull(uuid) &&
			uuid.equals(assetVocabulary.getUuid())) {

			return name;
		}

		name = StringUtil.appendParentheticalSuffix(name, count);

		return getAssetVocabularyName(uuid, groupId, name, ++count);
	}

	protected Map<Locale, String> getAssetVocabularyTitleMap(
		AssetVocabulary assetVocabulary, String name) {

		Map<Locale, String> titleMap = assetVocabulary.getTitleMap();

		if (titleMap == null) {
			titleMap = new HashMap<Locale, String>();
		}

		Locale locale = LocaleUtil.getDefault();

		titleMap.put(locale, name);

		return titleMap;
	}

	protected UserIdStrategy getUserIdStrategy(
		User user, String userIdStrategy) {

		if (UserIdStrategy.ALWAYS_CURRENT_USER_ID.equals(userIdStrategy)) {
			return new AlwaysCurrentUserIdStrategy(user);
		}

		return new CurrentUserIdStrategy(user);
	}

	protected void importAssetCategory(
			PortletDataContext portletDataContext,
			Map<Long, Long> assetVocabularyPKs,
			Map<Long, Long> assetCategoryPKs,
			Map<String, String> assetCategoryUuids,
			Element assetCategoryElement, AssetCategory assetCategory)
		throws Exception {

		long userId = portletDataContext.getUserId(assetCategory.getUserUuid());
		long assetVocabularyId = MapUtil.getLong(
			assetVocabularyPKs, assetCategory.getVocabularyId(),
			assetCategory.getVocabularyId());
		long parentAssetCategoryId = MapUtil.getLong(
			assetCategoryPKs, assetCategory.getParentCategoryId(),
			assetCategory.getParentCategoryId());

		if ((parentAssetCategoryId !=
				AssetCategoryConstants.DEFAULT_PARENT_CATEGORY_ID) &&
			(parentAssetCategoryId == assetCategory.getParentCategoryId())) {

			String path = getAssetCategoryPath(
				portletDataContext, parentAssetCategoryId);

			AssetCategory parentAssetCategory =
				(AssetCategory)portletDataContext.getZipEntryAsObject(path);

			Node parentCategoryNode =
				assetCategoryElement.getParent().selectSingleNode(
					"./category[@path='" + path + "']");

			if (parentCategoryNode != null) {
				importAssetCategory(
					portletDataContext, assetVocabularyPKs, assetCategoryPKs,
					assetCategoryUuids, (Element)parentCategoryNode,
					parentAssetCategory);

				parentAssetCategoryId = MapUtil.getLong(
					assetCategoryPKs, assetCategory.getParentCategoryId(),
					assetCategory.getParentCategoryId());
			}
		}

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);
		serviceContext.setCreateDate(assetCategory.getCreateDate());
		serviceContext.setModifiedDate(assetCategory.getModifiedDate());
		serviceContext.setScopeGroupId(portletDataContext.getScopeGroupId());

		AssetCategory importedAssetCategory = null;

		try {
			if (parentAssetCategoryId !=
					AssetCategoryConstants.DEFAULT_PARENT_CATEGORY_ID) {

				AssetCategoryUtil.findByPrimaryKey(parentAssetCategoryId);
			}

			List<Element> propertyElements = assetCategoryElement.elements(
				"property");

			String[] properties = new String[propertyElements.size()];

			for (int i = 0; i < propertyElements.size(); i++) {
				Element propertyElement = propertyElements.get(i);

				String key = propertyElement.attributeValue("key");
				String value = propertyElement.attributeValue("value");

				properties[i] = key.concat(StringPool.COLON).concat(value);
			}

			AssetCategory existingAssetCategory =
				AssetCategoryUtil.fetchByUUID_G(
					assetCategory.getUuid(),
					portletDataContext.getScopeGroupId());

			if (existingAssetCategory == null) {
				String name = getAssetCategoryName(
					null, parentAssetCategoryId, assetCategory.getName(), 2);

				serviceContext.setUuid(assetCategory.getUuid());

				importedAssetCategory =
					AssetCategoryLocalServiceUtil.addCategory(
						userId, parentAssetCategoryId,
						getAssetCategoryTitleMap(assetCategory, name),
						assetCategory.getDescriptionMap(), assetVocabularyId,
						properties, serviceContext);
			}
			else {
				String name = getAssetCategoryName(
					assetCategory.getUuid(), parentAssetCategoryId,
					assetCategory.getName(), 2);

				importedAssetCategory =
					AssetCategoryLocalServiceUtil.updateCategory(
						userId, existingAssetCategory.getCategoryId(),
						parentAssetCategoryId,
						getAssetCategoryTitleMap(assetCategory, name),
						assetCategory.getDescriptionMap(), assetVocabularyId,
						properties, serviceContext);
			}

			assetCategoryPKs.put(
				assetCategory.getCategoryId(),
				importedAssetCategory.getCategoryId());

			assetCategoryUuids.put(
				assetCategory.getUuid(), importedAssetCategory.getUuid());

			portletDataContext.importPermissions(
				AssetCategory.class, assetCategory.getCategoryId(),
				importedAssetCategory.getCategoryId());
		}
		catch (NoSuchCategoryException nsce) {
			_log.error(
				"Could not find the parent category for category " +
					assetCategory.getCategoryId());
		}
	}

	protected void importAssetTag(
			PortletDataContext portletDataContext, Map<Long, Long> assetTagPKs,
			Element assetTagElement, AssetTag assetTag)
		throws SystemException, PortalException {

		long userId = portletDataContext.getUserId(assetTag.getUserUuid());

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);
		serviceContext.setCreateDate(assetTag.getCreateDate());
		serviceContext.setModifiedDate(assetTag.getModifiedDate());
		serviceContext.setScopeGroupId(portletDataContext.getScopeGroupId());

		AssetTag importedAssetTag = null;

		List<Element> propertyElements = assetTagElement.elements("property");

		String[] properties = new String[propertyElements.size()];

		for (int i = 0; i < propertyElements.size(); i++) {
			Element propertyElement = propertyElements.get(i);

			String key = propertyElement.attributeValue("key");
			String value = propertyElement.attributeValue("value");

			properties[i] = key.concat(StringPool.COLON).concat(value);
		}

		AssetTag existingAssetTag = null;

		try {
			existingAssetTag = AssetTagUtil.findByG_N(
				portletDataContext.getScopeGroupId(), assetTag.getName());
		}
		catch (NoSuchTagException nste) {
			if (_log.isDebugEnabled()) {
				StringBundler sb = new StringBundler(5);

				sb.append("No AssetTag exists with the key {groupId=");
				sb.append(portletDataContext.getScopeGroupId());
				sb.append(", name=");
				sb.append(assetTag.getName());
				sb.append("}");

				_log.debug(sb.toString());
			}
		}

		try {
			if (existingAssetTag == null) {
				importedAssetTag = AssetTagLocalServiceUtil.addTag(
					userId, assetTag.getName(), properties, serviceContext);
			}
			else {
				importedAssetTag = AssetTagLocalServiceUtil.updateTag(
					userId, existingAssetTag.getTagId(), assetTag.getName(),
					properties, serviceContext);
			}

			assetTagPKs.put(assetTag.getTagId(), importedAssetTag.getTagId());

			portletDataContext.importPermissions(
				AssetTag.class, assetTag.getTagId(),
				importedAssetTag.getTagId());
		}
		catch (NoSuchTagException nste) {
			_log.error(
				"Could not find the parent category for category " +
					assetTag.getTagId());
		}
	}

	protected void importAssetVocabulary(
			PortletDataContext portletDataContext,
			Map<Long, Long> assetVocabularyPKs, Element assetVocabularyElement,
			AssetVocabulary assetVocabulary)
		throws Exception {

		long userId = portletDataContext.getUserId(
			assetVocabulary.getUserUuid());
		long groupId = portletDataContext.getScopeGroupId();

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);
		serviceContext.setCreateDate(assetVocabulary.getCreateDate());
		serviceContext.setModifiedDate(assetVocabulary.getModifiedDate());
		serviceContext.setScopeGroupId(portletDataContext.getScopeGroupId());

		AssetVocabulary importedAssetVocabulary = null;

		AssetVocabulary existingAssetVocabulary =
			AssetVocabularyUtil.fetchByUUID_G(
				assetVocabulary.getUuid(), groupId);

		if (existingAssetVocabulary == null) {
			Group companyGroup = GroupLocalServiceUtil.getCompanyGroup(
				portletDataContext.getCompanyId());

			existingAssetVocabulary = AssetVocabularyUtil.fetchByUUID_G(
				assetVocabulary.getUuid(), companyGroup.getGroupId());
		}

		if (existingAssetVocabulary == null) {
			String name = getAssetVocabularyName(
				null, groupId, assetVocabulary.getName(), 2);

			serviceContext.setUuid(assetVocabulary.getUuid());

			importedAssetVocabulary =
				AssetVocabularyLocalServiceUtil.addVocabulary(
					userId, StringPool.BLANK,
					getAssetVocabularyTitleMap(assetVocabulary, name),
					assetVocabulary.getDescriptionMap(),
					assetVocabulary.getSettings(), serviceContext);
		}
		else {
			String name = getAssetVocabularyName(
				assetVocabulary.getUuid(), groupId, assetVocabulary.getName(),
				2);

			importedAssetVocabulary =
				AssetVocabularyLocalServiceUtil.updateVocabulary(
					existingAssetVocabulary.getVocabularyId(), StringPool.BLANK,
					getAssetVocabularyTitleMap(assetVocabulary, name),
					assetVocabulary.getDescriptionMap(),
					assetVocabulary.getSettings(), serviceContext);
		}

		assetVocabularyPKs.put(
			assetVocabulary.getVocabularyId(),
			importedAssetVocabulary.getVocabularyId());

		portletDataContext.importPermissions(
			AssetVocabulary.class, assetVocabulary.getVocabularyId(),
			importedAssetVocabulary.getVocabularyId());
	}

	protected void importPortletData(
			PortletDataContext portletDataContext, String portletId, long plid,
			Element portletDataElement)
		throws Exception {

		long ownerId = PortletKeys.PREFS_OWNER_ID_DEFAULT;
		int ownerType = PortletKeys.PREFS_OWNER_TYPE_LAYOUT;

		PortletPreferences portletPreferences =
			PortletPreferencesUtil.fetchByO_O_P_P(
				ownerId, ownerType, plid, portletId);

		if (portletPreferences == null) {
			portletPreferences =
				new com.liferay.portal.model.impl.PortletPreferencesImpl();
		}

		String xml = importPortletData(
			portletDataContext, portletId, portletPreferences,
			portletDataElement);

		if (xml != null) {
			PortletPreferencesLocalServiceUtil.updatePreferences(
				ownerId, ownerType, plid, portletId, xml);
		}
	}

	protected String importPortletData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences, Element portletDataElement)
		throws Exception {

		Portlet portlet = PortletLocalServiceUtil.getPortletById(
			portletDataContext.getCompanyId(), portletId);

		if (portlet == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Do not import portlet data for " + portletId +
						" because the portlet does not exist");
			}

			return null;
		}

		PortletDataHandler portletDataHandler =
			portlet.getPortletDataHandlerInstance();

		if (portletDataHandler == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Do not import portlet data for " + portletId +
						" because the portlet does not have a " +
							"PortletDataHandler");
			}

			return null;
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Importing data for " + portletId);
		}

		PortletPreferencesImpl portletPreferencesImpl = null;

		if (portletPreferences != null) {
			portletPreferencesImpl =
				(PortletPreferencesImpl)
					PortletPreferencesFactoryUtil.fromDefaultXML(
						portletPreferences.getPreferences());
		}

		String portletData = portletDataContext.getZipEntryAsString(
			portletDataElement.attributeValue("path"));

		try {
			portletPreferencesImpl =
				(PortletPreferencesImpl)portletDataHandler.importData(
					portletDataContext, portletId, portletPreferencesImpl,
					portletData);
		}
		catch (Exception e) {
			throw e;
		}

		if (portletPreferencesImpl == null) {
			return null;
		}

		return PortletPreferencesFactoryUtil.toXML(portletPreferencesImpl);
	}

	protected void importPortletPreferences(
			PortletDataContext portletDataContext, long companyId, long groupId,
			Layout layout, String portletId, Element parentElement,
			boolean importPortletSetup, boolean importPortletArchivedSetups,
			boolean importPortletUserPreferences, boolean preserveScopeLayoutId)
		throws Exception {

		long defaultUserId = UserLocalServiceUtil.getDefaultUserId(companyId);
		long plid = 0;
		String scopeType = StringPool.BLANK;
		String scopeLayoutUuid = StringPool.BLANK;

		if (layout != null) {
			plid = layout.getPlid();

			if (preserveScopeLayoutId && (portletId != null)) {
				javax.portlet.PortletPreferences jxPreferences =
					PortletPreferencesFactoryUtil.getLayoutPortletSetup(
						layout, portletId);

				scopeType = GetterUtil.getString(
					jxPreferences.getValue("lfrScopeType", null));
				scopeLayoutUuid = GetterUtil.getString(
					jxPreferences.getValue("lfrScopeLayoutUuid", null));

				portletDataContext.setScopeType(scopeType);
				portletDataContext.setScopeLayoutUuid(scopeLayoutUuid);
			}
		}

		List<Element> portletPreferencesElements = parentElement.elements(
			"portlet-preferences");

		for (Element portletPreferencesElement : portletPreferencesElements) {
			String path = portletPreferencesElement.attributeValue("path");

			if (portletDataContext.isPathNotProcessed(path)) {
				String xml = null;

				Element element = null;

				try {
					xml = portletDataContext.getZipEntryAsString(path);

					Document preferencesDocument = SAXReaderUtil.read(xml);

					element = preferencesDocument.getRootElement();
				}
				catch (DocumentException de) {
					throw new SystemException(de);
				}

				long ownerId = GetterUtil.getLong(
					element.attributeValue("owner-id"));
				int ownerType = GetterUtil.getInteger(
					element.attributeValue("owner-type"));

				if (ownerType == PortletKeys.PREFS_OWNER_TYPE_COMPANY) {
					continue;
				}

				if (((ownerType == PortletKeys.PREFS_OWNER_TYPE_GROUP) ||
					 (ownerType == PortletKeys.PREFS_OWNER_TYPE_LAYOUT)) &&
					!importPortletSetup) {

					continue;
				}

				if ((ownerType == PortletKeys.PREFS_OWNER_TYPE_ARCHIVED) &&
					!importPortletArchivedSetups) {

					continue;
				}

				if ((ownerType == PortletKeys.PREFS_OWNER_TYPE_USER) &&
					(ownerId != PortletKeys.PREFS_OWNER_ID_DEFAULT) &&
					!importPortletUserPreferences) {

					continue;
				}

				if (ownerType == PortletKeys.PREFS_OWNER_TYPE_GROUP) {
					plid = PortletKeys.PREFS_PLID_SHARED;
					ownerId = portletDataContext.getScopeGroupId();
				}

				boolean defaultUser = GetterUtil.getBoolean(
					element.attributeValue("default-user"));

				if (portletId == null) {
					portletId = element.attributeValue("portlet-id");
				}

				if (ownerType == PortletKeys.PREFS_OWNER_TYPE_ARCHIVED) {
					portletId = PortletConstants.getRootPortletId(portletId);

					String userUuid = element.attributeValue(
						"archive-user-uuid");
					String name = element.attributeValue("archive-name");

					long userId = portletDataContext.getUserId(userUuid);

					PortletItem portletItem =
						PortletItemLocalServiceUtil.updatePortletItem(
							userId, groupId, name, portletId,
							PortletPreferences.class.getName());

					plid = 0;
					ownerId = portletItem.getPortletItemId();
				}
				else if (ownerType == PortletKeys.PREFS_OWNER_TYPE_USER) {
					String userUuid = element.attributeValue("user-uuid");

					ownerId = portletDataContext.getUserId(userUuid);
				}

				if (defaultUser) {
					ownerId = defaultUserId;
				}

				xml = checkPortletPreferences(
					portletDataContext, companyId, ownerId, ownerType, plid,
					portletId, xml);

				PortletPreferencesLocalServiceUtil.updatePreferences(
					ownerId, ownerType, plid, portletId, xml);
			}
		}

		if (preserveScopeLayoutId && (layout != null)) {
			javax.portlet.PortletPreferences jxPreferences =
				PortletPreferencesFactoryUtil.getLayoutPortletSetup(
					layout, portletId);

			try {
				jxPreferences.setValue("lfrScopeType", scopeType);
				jxPreferences.setValue("lfrScopeLayoutUuid", scopeLayoutUuid);

				jxPreferences.store();
			}
			finally {
				portletDataContext.setScopeType(scopeType);
				portletDataContext.setScopeLayoutUuid(scopeLayoutUuid);
			}
		}
	}

	protected void readAssetCategories(PortletDataContext portletDataContext)
		throws Exception {

		String xml = portletDataContext.getZipEntryAsString(
			portletDataContext.getSourceRootPath() +
				"/categories-hierarchy.xml");

		if (xml == null) {
			return;
		}

		Document document = SAXReaderUtil.read(xml);

		Element rootElement = document.getRootElement();

		Element assetVocabulariesElement = rootElement.element("vocabularies");

		List<Element> assetVocabularyElements =
			assetVocabulariesElement.elements("vocabulary");

		Map<Long, Long> assetVocabularyPKs =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				AssetVocabulary.class);

		for (Element assetVocabularyElement : assetVocabularyElements) {
			String path = assetVocabularyElement.attributeValue("path");

			if (!portletDataContext.isPathNotProcessed(path)) {
				continue;
			}

			AssetVocabulary assetVocabulary =
				(AssetVocabulary)portletDataContext.getZipEntryAsObject(path);

			importAssetVocabulary(
				portletDataContext, assetVocabularyPKs, assetVocabularyElement,
				assetVocabulary);
		}

		Element assetCategoriesElement = rootElement.element("categories");

		List<Element> assetCategoryElements = assetCategoriesElement.elements(
			"category");

		Map<Long, Long> assetCategoryPKs =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				AssetCategory.class);

		Map<String, String> assetCategoryUuids =
			(Map<String, String>)portletDataContext.getNewPrimaryKeysMap(
				AssetCategory.class.getName() + "uuid");

		for (Element assetCategoryElement : assetCategoryElements) {
			String path = assetCategoryElement.attributeValue("path");

			if (!portletDataContext.isPathNotProcessed(path)) {
				continue;
			}

			AssetCategory assetCategory =
				(AssetCategory)portletDataContext.getZipEntryAsObject(path);

			importAssetCategory(
				portletDataContext, assetVocabularyPKs, assetCategoryPKs,
				assetCategoryUuids, assetCategoryElement, assetCategory);
		}

		Element assetsElement = rootElement.element("assets");

		List<Element> assetElements = assetsElement.elements("asset");

		for (Element assetElement : assetElements) {
			String className = GetterUtil.getString(
				assetElement.attributeValue("class-name"));
			long classPK = GetterUtil.getLong(
				assetElement.attributeValue("class-pk"));
			String[] assetCategoryUuidArray = StringUtil.split(
				GetterUtil.getString(
					assetElement.attributeValue("category-uuids")));

			long[] assetCategoryIds = new long[0];

			for (String assetCategoryUuid : assetCategoryUuidArray) {
				assetCategoryUuid = MapUtil.getString(
					assetCategoryUuids, assetCategoryUuid,
					assetCategoryUuid);

				AssetCategory assetCategory = AssetCategoryUtil.fetchByUUID_G(
					assetCategoryUuid, portletDataContext.getScopeGroupId());

				if (assetCategory == null) {
					Group companyGroup = GroupLocalServiceUtil.getCompanyGroup(
						portletDataContext.getCompanyId());

					assetCategory = AssetCategoryUtil.fetchByUUID_G(
						assetCategoryUuid, companyGroup.getGroupId());
				}

				if (assetCategory != null) {
					assetCategoryIds = ArrayUtil.append(
						assetCategoryIds, assetCategory.getCategoryId());
				}
			}

			portletDataContext.addAssetCategories(
				className, classPK, assetCategoryIds);
		}
	}

	protected void readAssetLinks(PortletDataContext portletDataContext)
		throws Exception {

		String xml = portletDataContext.getZipEntryAsString(
			portletDataContext.getSourceRootPath() + "/links.xml");

		if (xml == null) {
			return;
		}

		Document document = SAXReaderUtil.read(xml);

		Element rootElement = document.getRootElement();

		List<Element> assetLinkElements = rootElement.elements("asset-link");

		for (Element assetLinkElement : assetLinkElements) {
			String sourceUuid = GetterUtil.getString(
				assetLinkElement.attributeValue("source-uuid"));
			String[] assetEntryUuidArray = StringUtil.split(
				GetterUtil.getString(
					assetLinkElement.attributeValue("target-uuids")));
			int assetLinkType = GetterUtil.getInteger(
				assetLinkElement.attributeValue("type"));

			List<Long> assetEntryIds = new ArrayList<Long>();

			for (String assetEntryUuid : assetEntryUuidArray) {
				try {
					AssetEntry assetEntry = AssetEntryLocalServiceUtil.getEntry(
						portletDataContext.getScopeGroupId(), assetEntryUuid);

					assetEntryIds.add(assetEntry.getEntryId());
				}
				catch (NoSuchEntryException nsee) {
				}
			}

			if (assetEntryIds.isEmpty()) {
				continue;
			}

			long[] assetEntryIdsArray = ArrayUtil.toArray(
				assetEntryIds.toArray(new Long[assetEntryIds.size()]));

			try {
				AssetEntry assetEntry = AssetEntryLocalServiceUtil.getEntry(
					portletDataContext.getScopeGroupId(), sourceUuid);

				AssetLinkLocalServiceUtil.updateLinks(
					assetEntry.getUserId(), assetEntry.getEntryId(),
					assetEntryIdsArray, assetLinkType);
			}
			catch (NoSuchEntryException nsee) {
			}
		}
	}

	protected void readAssetTags(PortletDataContext portletDataContext)
		throws Exception {

		String xml = portletDataContext.getZipEntryAsString(
			portletDataContext.getSourceRootPath() + "/tags.xml");

		if (xml == null) {
			return;
		}

		Document document = SAXReaderUtil.read(xml);

		Element rootElement = document.getRootElement();

		List<Element> assetTagElements = rootElement.elements("tag");

		for (Element assetTagElement : assetTagElements) {
			String path = assetTagElement.attributeValue("path");

			if (!portletDataContext.isPathNotProcessed(path)) {
				continue;
			}

			AssetTag assetTag =
				(AssetTag)portletDataContext.getZipEntryAsObject(path);

			Map<Long, Long> assetTagPKs =
				(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
					AssetTag.class);

			importAssetTag(
				portletDataContext, assetTagPKs, assetTagElement, assetTag);
		}

		List<Element> assetElements = rootElement.elements("asset");

		for (Element assetElement : assetElements) {
			String className = GetterUtil.getString(
				assetElement.attributeValue("class-name"));
			long classPK = GetterUtil.getLong(
				assetElement.attributeValue("class-pk"));
			String assetTagNames = GetterUtil.getString(
				assetElement.attributeValue("tags"));

			portletDataContext.addAssetTags(
				className, classPK, StringUtil.split(assetTagNames));
		}
	}

	protected void readComments(PortletDataContext portletDataContext)
		throws Exception {

		String xml = portletDataContext.getZipEntryAsString(
			portletDataContext.getSourceRootPath() + "/comments.xml");

		if (xml == null) {
			return;
		}

		Document document = SAXReaderUtil.read(xml);

		Element rootElement = document.getRootElement();

		List<Element> assetElements = rootElement.elements("asset");

		for (Element assetElement : assetElements) {
			String path = assetElement.attributeValue("path");
			String className = assetElement.attributeValue("class-name");
			long classPK = GetterUtil.getLong(
				assetElement.attributeValue("class-pk"));

			List<String> zipFolderEntries =
				portletDataContext.getZipFolderEntries(path);

			List<MBMessage> mbMessages = new ArrayList<MBMessage>();

			for (String zipFolderEntry : zipFolderEntries) {
				MBMessage mbMessage =
					(MBMessage)portletDataContext.getZipEntryAsObject(
						zipFolderEntry);

				if (mbMessage != null) {
					mbMessages.add(mbMessage);
				}
			}

			portletDataContext.addComments(className, classPK, mbMessages);
		}
	}

	protected void readExpandoTables(PortletDataContext portletDataContext)
		throws Exception {

		String xml = portletDataContext.getZipEntryAsString(
			portletDataContext.getSourceRootPath() + "/expando-tables.xml");

		if (xml == null) {
			return;
		}

		Document document = SAXReaderUtil.read(xml);

		Element rootElement = document.getRootElement();

		List<Element> expandoTableElements = rootElement.elements(
			"expando-table");

		for (Element expandoTableElement : expandoTableElements) {
			String className = expandoTableElement.attributeValue("class-name");

			ExpandoTable expandoTable = null;

			try {
				expandoTable = ExpandoTableLocalServiceUtil.getDefaultTable(
					portletDataContext.getCompanyId(), className);
			}
			catch (NoSuchTableException nste) {
				expandoTable =
					ExpandoTableLocalServiceUtil.addDefaultTable(
						portletDataContext.getCompanyId(), className);
			}

			List<Element> expandoColumnElements =
				expandoTableElement.elements("expando-column");

			for (Element expandoColumnElement : expandoColumnElements) {
				long columnId = GetterUtil.getLong(
					expandoColumnElement.attributeValue("column-id"));
				String name = expandoColumnElement.attributeValue("name");
				int type = GetterUtil.getInteger(
					expandoColumnElement.attributeValue("type"));
				String defaultData = expandoColumnElement.elementText(
					"default-data");
				String typeSettings = expandoColumnElement.elementText(
					"type-settings");

				Serializable defaultDataObject =
					ExpandoConverterUtil.getAttributeFromString(
						type, defaultData);

				ExpandoColumn expandoColumn =
					ExpandoColumnLocalServiceUtil.getColumn(
						expandoTable.getTableId(), name);

				if (expandoColumn != null) {
					ExpandoColumnLocalServiceUtil.updateColumn(
						expandoColumn.getColumnId(), name, type,
						defaultDataObject);
				}
				else {
					expandoColumn = ExpandoColumnLocalServiceUtil.addColumn(
						expandoTable.getTableId(), name, type,
						defaultDataObject);
				}

				ExpandoColumnLocalServiceUtil.updateTypeSettings(
					expandoColumn.getColumnId(), typeSettings);

				portletDataContext.importPermissions(
					ExpandoColumn.class, columnId, expandoColumn.getColumnId());
			}
		}
	}

	protected void readLocks(PortletDataContext portletDataContext)
		throws Exception {

		String xml = portletDataContext.getZipEntryAsString(
			portletDataContext.getSourceRootPath() + "/locks.xml");

		if (xml == null) {
			return;
		}

		Document document = SAXReaderUtil.read(xml);

		Element rootElement = document.getRootElement();

		List<Element> assetElements = rootElement.elements("asset");

		for (Element assetElement : assetElements) {
			String path = assetElement.attributeValue("path");
			String className = assetElement.attributeValue("class-name");
			String key = assetElement.attributeValue("key");

			Lock lock = (Lock)portletDataContext.getZipEntryAsObject(path);

			if (lock != null) {
				portletDataContext.addLocks(className, key, lock);
			}
		}
	}

	protected void readRatingsEntries(PortletDataContext portletDataContext)
		throws Exception {

		String xml = portletDataContext.getZipEntryAsString(
			portletDataContext.getSourceRootPath() + "/ratings.xml");

		if (xml == null) {
			return;
		}

		Document document = SAXReaderUtil.read(xml);

		Element rootElement = document.getRootElement();

		List<Element> assetElements = rootElement.elements("asset");

		for (Element assetElement : assetElements) {
			String path = assetElement.attributeValue("path");
			String className = assetElement.attributeValue("class-name");
			long classPK = GetterUtil.getLong(
				assetElement.attributeValue("class-pk"));

			List<String> zipFolderEntries =
				portletDataContext.getZipFolderEntries(path);

			List<RatingsEntry> ratingsEntries = new ArrayList<RatingsEntry>();

			for (String zipFolderEntry : zipFolderEntries) {
				RatingsEntry ratingsEntry =
					(RatingsEntry)portletDataContext.getZipEntryAsObject(
						zipFolderEntry);

				if (ratingsEntry != null) {
					ratingsEntries.add(ratingsEntry);
				}
			}

			portletDataContext.addRatingsEntries(
				className, classPK, ratingsEntries);
		}
	}

	protected void resetPortletScope(
		PortletDataContext portletDataContext, long groupId) {

		portletDataContext.setScopeGroupId(groupId);
		portletDataContext.setScopeLayoutUuid(StringPool.BLANK);
		portletDataContext.setScopeType(StringPool.BLANK);
	}

	protected void setPortletScope(
		PortletDataContext portletDataContext, Element portletElement) {

		// Portlet data scope

		String scopeLayoutUuid = GetterUtil.getString(
			portletElement.attributeValue("scope-layout-uuid"));
		String scopeLayoutType = GetterUtil.getString(
			portletElement.attributeValue("scope-layout-type"));

		portletDataContext.setScopeLayoutUuid(scopeLayoutUuid);
		portletDataContext.setScopeType(scopeLayoutType);

		// Layout scope

		try {
			Group scopeGroup = null;

			if (scopeLayoutType.equals("company")) {
				scopeGroup = GroupLocalServiceUtil.getCompanyGroup(
					portletDataContext.getCompanyId());
			}
			else if (Validator.isNotNull(scopeLayoutUuid)) {
				Layout scopeLayout =
					LayoutLocalServiceUtil.getLayoutByUuidAndGroupId(
						scopeLayoutUuid, portletDataContext.getGroupId());

				if (scopeLayout.hasScopeGroup()) {
					scopeGroup = scopeLayout.getScopeGroup();
				}
				else {
					String name = String.valueOf(scopeLayout.getPlid());

					scopeGroup = GroupLocalServiceUtil.addGroup(
						portletDataContext.getUserId(null),
						Layout.class.getName(), scopeLayout.getPlid(), name,
						null, 0, null, false, true, null);
				}

				Group group = scopeLayout.getGroup();

				if (group.isStaged() && !group.isStagedRemotely()) {
					try {
						Layout oldLayout =
							LayoutLocalServiceUtil.getLayoutByUuidAndGroupId(
								scopeLayoutUuid,
								portletDataContext.getSourceGroupId());

						Group oldScopeGroup = oldLayout.getScopeGroup();

						oldScopeGroup.setLiveGroupId(scopeGroup.getGroupId());

						GroupLocalServiceUtil.updateGroup(oldScopeGroup, true);
					}
					catch (NoSuchLayoutException nsle) {
						if (_log.isWarnEnabled()) {
							_log.warn(nsle);
						}
					}
				}

				portletDataContext.setScopeGroupId(scopeGroup.getGroupId());
			}
		}
		catch (PortalException pe) {
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(PortletImporter.class);

	private PermissionImporter _permissionImporter = new PermissionImporter();

}