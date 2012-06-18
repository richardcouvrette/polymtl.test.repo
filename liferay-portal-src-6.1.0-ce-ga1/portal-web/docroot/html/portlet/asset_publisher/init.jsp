<%--
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
--%>

<%@ include file="/html/portlet/init.jsp" %>

<%@ page import="com.liferay.portal.NoSuchModelException" %><%@
page import="com.liferay.portal.kernel.repository.model.FileEntry" %><%@
page import="com.liferay.portal.kernel.search.Hits" %><%@
page import="com.liferay.portal.kernel.xml.Document" %><%@
page import="com.liferay.portal.kernel.xml.Element" %><%@
page import="com.liferay.portal.kernel.xml.SAXReaderUtil" %><%@
page import="com.liferay.portal.security.permission.comparator.ModelResourceComparator" %><%@
page import="com.liferay.portlet.asset.AssetRendererFactoryRegistryUtil" %><%@
page import="com.liferay.portlet.asset.NoSuchEntryException" %><%@
page import="com.liferay.portlet.asset.NoSuchTagException" %><%@
page import="com.liferay.portlet.asset.NoSuchTagPropertyException" %><%@
page import="com.liferay.portlet.asset.model.AssetCategory" %><%@
page import="com.liferay.portlet.asset.model.AssetEntry" %><%@
page import="com.liferay.portlet.asset.model.AssetRenderer" %><%@
page import="com.liferay.portlet.asset.model.AssetRendererFactory" %><%@
page import="com.liferay.portlet.asset.model.AssetTag" %><%@
page import="com.liferay.portlet.asset.model.AssetTagProperty" %><%@
page import="com.liferay.portlet.asset.model.AssetVocabulary" %><%@
page import="com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil" %><%@
page import="com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil" %><%@
page import="com.liferay.portlet.asset.service.AssetEntryServiceUtil" %><%@
page import="com.liferay.portlet.asset.service.AssetTagLocalServiceUtil" %><%@
page import="com.liferay.portlet.asset.service.AssetTagPropertyLocalServiceUtil" %><%@
page import="com.liferay.portlet.asset.service.AssetVocabularyLocalServiceUtil" %><%@
page import="com.liferay.portlet.asset.service.persistence.AssetEntryQuery" %><%@
page import="com.liferay.portlet.asset.util.AssetUtil" %><%@
page import="com.liferay.portlet.assetpublisher.search.AssetDisplayTerms" %><%@
page import="com.liferay.portlet.assetpublisher.search.AssetSearch" %><%@
page import="com.liferay.portlet.assetpublisher.search.AssetSearchTerms" %><%@
page import="com.liferay.portlet.assetpublisher.util.AssetPublisherUtil" %><%@
page import="com.liferay.portlet.documentlibrary.model.DLFileEntry" %><%@
page import="com.liferay.portlet.documentlibrary.model.DLFileEntryConstants" %><%@
page import="com.liferay.portlet.documentlibrary.model.DLFolderConstants" %><%@
page import="com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil" %><%@
page import="com.liferay.portlet.documentlibrary.util.DocumentConversionUtil" %><%@
page import="com.liferay.portlet.journal.model.JournalArticle" %><%@
page import="com.liferay.portlet.journal.model.JournalStructure" %><%@
page import="com.liferay.portlet.journal.service.JournalStructureLocalServiceUtil" %><%@
page import="com.liferay.util.RSSUtil" %><%@
page import="com.liferay.util.xml.DocUtil" %>

<%
PortletPreferences preferences = renderRequest.getPreferences();

String portletResource = ParamUtil.getString(request, "portletResource");

if (Validator.isNotNull(portletResource)) {
	preferences = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);
}

String selectionStyle = preferences.getValue("selectionStyle", null);

if (Validator.isNull(selectionStyle)) {
	selectionStyle = "dynamic";
}

boolean defaultScope = GetterUtil.getBoolean(preferences.getValue("defaultScope", null), true);

long[] groupIds = AssetPublisherUtil.getGroupIds(preferences, scopeGroupId, layout);

long[] availableClassNameIds = AssetRendererFactoryRegistryUtil.getClassNameIds();

long layoutRevisionClassNameId = PortalUtil.getClassNameId(LayoutRevision.class);

if (ArrayUtil.contains(availableClassNameIds, layoutRevisionClassNameId)) {
	availableClassNameIds = ArrayUtil.remove(availableClassNameIds, layoutRevisionClassNameId);
}

boolean anyAssetType = GetterUtil.getBoolean(preferences.getValue("anyAssetType", Boolean.TRUE.toString()));

long[] classNameIds = AssetPublisherUtil.getClassNameIds(preferences, availableClassNameIds);

long[] classTypeIds = GetterUtil.getLongValues(portletPreferences.getValues("classTypeIds", null));

String customUserAttributes = GetterUtil.getString(preferences.getValue("customUserAttributes", StringPool.BLANK));

AssetEntryQuery assetEntryQuery = new AssetEntryQuery();

String[] allAssetTagNames = new String[0];

if (selectionStyle.equals("dynamic")) {
	if (!ArrayUtil.contains(groupIds, scopeGroupId)) {
		assetEntryQuery = AssetPublisherUtil.getAssetEntryQuery(preferences, ArrayUtil.append(groupIds, scopeGroupId));
	}
	else {
		assetEntryQuery = AssetPublisherUtil.getAssetEntryQuery(preferences, groupIds);
	}

	allAssetTagNames = AssetPublisherUtil.getAssetTagNames(preferences, scopeGroupId);

	assetEntryQuery.setClassTypeIds(classTypeIds);

	AssetPublisherUtil.addUserAttributes(user, StringUtil.split(customUserAttributes), assetEntryQuery);
}

long assetVocabularyId = GetterUtil.getLong(preferences.getValue("assetVocabularyId", StringPool.BLANK));

long assetCategoryId = ParamUtil.getLong(request, "categoryId");

String assetCategoryTitle = null;
String assetVocabularyTitle = null;

if (assetCategoryId > 0) {
	assetEntryQuery.setAllCategoryIds(new long[] {assetCategoryId});

	AssetCategory assetCategory = AssetCategoryLocalServiceUtil.getCategory(assetCategoryId);

	assetCategory = assetCategory.toEscapedModel();

	assetCategoryTitle = assetCategory.getTitle(locale);

	AssetVocabulary assetVocabulary = AssetVocabularyLocalServiceUtil.getAssetVocabulary(assetCategory.getVocabularyId());

	assetVocabulary = assetVocabulary.toEscapedModel();

	assetVocabularyTitle = assetVocabulary.getTitle(locale);

	PortalUtil.setPageKeywords(assetCategoryTitle, request);
}

String assetTagName = ParamUtil.getString(request, "tag");

if (Validator.isNotNull(assetTagName)) {
	allAssetTagNames = new String[] {assetTagName};

	long[] assetTagIds = AssetTagLocalServiceUtil.getTagIds(scopeGroupId, allAssetTagNames);

	assetEntryQuery.setAllTagIds(assetTagIds);

	PortalUtil.setPageKeywords(assetTagName, request);
}

boolean showLinkedAssets = GetterUtil.getBoolean(preferences.getValue("showLinkedAssets", null), false);
boolean showOnlyLayoutAssets = GetterUtil.getBoolean(preferences.getValue("showOnlyLayoutAssets", null));

if (showOnlyLayoutAssets) {
	assetEntryQuery.setLayout(layout);
}

if (portletName.equals(PortletKeys.RELATED_ASSETS)) {
	AssetEntry layoutAssetEntry = (AssetEntry)request.getAttribute(WebKeys.LAYOUT_ASSET_ENTRY);

	if (layoutAssetEntry != null) {
		assetEntryQuery.setLinkedAssetEntryId(layoutAssetEntry.getEntryId());
	}
}

boolean mergeUrlTags = GetterUtil.getBoolean(preferences.getValue("mergeUrlTags", null), true);
boolean mergeLayoutTags = GetterUtil.getBoolean(preferences.getValue("mergeLayoutTags", null), false);

String displayStyle = GetterUtil.getString(preferences.getValue("displayStyle", "abstracts"));

if (Validator.isNull(displayStyle)) {
	displayStyle = "abstracts";
}

boolean showAssetTitle = GetterUtil.getBoolean(preferences.getValue("showAssetTitle", null), true);
boolean showContextLink = GetterUtil.getBoolean(preferences.getValue("showContextLink", null), true);
int abstractLength = GetterUtil.getInteger(preferences.getValue("abstractLength", null), 200);
String assetLinkBehavior = GetterUtil.getString(preferences.getValue("assetLinkBehavior", "showFullContent"));
String orderByColumn1 = GetterUtil.getString(preferences.getValue("orderByColumn1", "modifiedDate"));
String orderByColumn2 = GetterUtil.getString(preferences.getValue("orderByColumn2", "title"));
String orderByType1 = GetterUtil.getString(preferences.getValue("orderByType1", "DESC"));
String orderByType2 = GetterUtil.getString(preferences.getValue("orderByType2", "ASC"));
boolean excludeZeroViewCount = GetterUtil.getBoolean(preferences.getValue("excludeZeroViewCount", "0"));
int delta = GetterUtil.getInteger(preferences.getValue("delta", StringPool.BLANK), SearchContainer.DEFAULT_DELTA);
String paginationType = GetterUtil.getString(preferences.getValue("paginationType", "none"));
boolean showAvailableLocales = GetterUtil.getBoolean(preferences.getValue("showAvailableLocales", null));
boolean showMetadataDescriptions = GetterUtil.getBoolean(preferences.getValue("showMetadataDescriptions", null), true);

boolean defaultAssetPublisher = false;

UnicodeProperties typeSettingsProperties = layout.getTypeSettingsProperties();

String defaultAssetPublisherPortletId = typeSettingsProperties.getProperty(LayoutTypePortletConstants.DEFAULT_ASSET_PUBLISHER_PORTLET_ID, StringPool.BLANK);

if (defaultAssetPublisherPortletId.equals(portletDisplay.getId()) || defaultAssetPublisherPortletId.equals(portletResource)) {
	defaultAssetPublisher = true;
}

boolean enablePermissions = GetterUtil.getBoolean(preferences.getValue("enablePermissions", null));

assetEntryQuery.setEnablePermissions(enablePermissions);

boolean enableRelatedAssets = GetterUtil.getBoolean(preferences.getValue("enableRelatedAssets", null), true);
boolean enableRatings = GetterUtil.getBoolean(preferences.getValue("enableRatings", null));
boolean enableComments = GetterUtil.getBoolean(preferences.getValue("enableComments", null));
boolean enableCommentRatings = GetterUtil.getBoolean(preferences.getValue("enableCommentRatings", null));
boolean enableTagBasedNavigation = GetterUtil.getBoolean(preferences.getValue("enableTagBasedNavigation", null));

String[] conversions = DocumentConversionUtil.getConversions("html");
String[] extensions = preferences.getValues("extensions", new String[0]);
boolean openOfficeServerEnabled = PrefsPropsUtil.getBoolean(PropsKeys.OPENOFFICE_SERVER_ENABLED, PropsValues.OPENOFFICE_SERVER_ENABLED);
boolean enableConversions = openOfficeServerEnabled && (extensions != null) && (extensions.length > 0);
boolean enablePrint = GetterUtil.getBoolean(preferences.getValue("enablePrint", null));
boolean enableFlags = GetterUtil.getBoolean(preferences.getValue("enableFlags", null));
boolean enableSocialBookmarks = GetterUtil.getBoolean(preferences.getValue("enableSocialBookmarks", null), true);
String socialBookmarksDisplayStyle = preferences.getValue("socialBookmarksDisplayStyle", "horizontal");
String socialBookmarksDisplayPosition = preferences.getValue("socialBookmarksDisplayPosition", "bottom");

String defaultMetadataFields = StringPool.BLANK;
String allMetadataFields = "create-date,modified-date,publish-date,expiration-date,priority,author,view-count,categories,tags,ratings";

String[] metadataFields = StringUtil.split(preferences.getValue("metadataFields", defaultMetadataFields));

boolean enableRSS = GetterUtil.getBoolean(preferences.getValue("enableRss", null));
int rssDelta = GetterUtil.getInteger(preferences.getValue("rssDelta", "20"));
String rssDisplayStyle = preferences.getValue("rssDisplayStyle", RSSUtil.DISPLAY_STYLE_ABSTRACT);
String rssFormat = preferences.getValue("rssFormat", "atom10");
String rssName = preferences.getValue("rssName", portletDisplay.getTitle());

String[] assetEntryXmls = preferences.getValues("assetEntryXml", new String[0]);

boolean viewInContext = assetLinkBehavior.equals("viewInPortlet");

boolean showPortletWithNoResults = false;
boolean groupByClass = (assetVocabularyId == -1);
boolean allowEmptyResults = false;

Map<String, PortletURL> addPortletURLs = null;

Format dateFormatDate = FastDateFormatFactoryUtil.getDate(locale, timeZone);
%>

<%@ include file="/html/portlet/asset_publisher/init-ext.jsp" %>

<%!
private String _checkViewURL(String viewURL, String currentURL, ThemeDisplay themeDisplay) {
	if (Validator.isNotNull(viewURL) && viewURL.startsWith(themeDisplay.getURLPortal())) {
		viewURL = HttpUtil.setParameter(viewURL, "redirect", currentURL);
	}

	return viewURL;
}
%>