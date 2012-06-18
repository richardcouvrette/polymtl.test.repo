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

<%@ page import="com.liferay.portal.kernel.parsers.bbcode.BBCodeTranslatorUtil" %><%@ page import="com.liferay.portal.kernel.search.Document" %><%@ page import="com.liferay.portal.kernel.search.Hits" %><%@ page import="com.liferay.portal.kernel.search.Indexer" %><%@ page import="com.liferay.portal.kernel.search.IndexerRegistryUtil" %><%@ page import="com.liferay.portal.kernel.search.SearchContext" %><%@ page import="com.liferay.portal.kernel.search.SearchContextFactory" %><%@ page import="com.liferay.portlet.asset.model.AssetTag" %><%@ page import="com.liferay.portlet.asset.service.AssetTagLocalServiceUtil" %><%@ page import="com.liferay.portlet.asset.util.AssetUtil" %><%@ page import="com.liferay.portlet.documentlibrary.FileExtensionException" %><%@ page import="com.liferay.portlet.documentlibrary.FileNameException" %><%@ page import="com.liferay.portlet.documentlibrary.FileSizeException" %><%@ page import="com.liferay.portlet.documentlibrary.store.DLStoreUtil" %><%@ page import="com.liferay.portlet.messageboards.BannedUserException" %><%@ page import="com.liferay.portlet.messageboards.CategoryNameException" %><%@ page import="com.liferay.portlet.messageboards.LockedThreadException" %><%@ page import="com.liferay.portlet.messageboards.MailingListEmailAddressException" %><%@ page import="com.liferay.portlet.messageboards.MailingListInServerNameException" %><%@ page import="com.liferay.portlet.messageboards.MailingListInUserNameException" %><%@ page import="com.liferay.portlet.messageboards.MailingListOutEmailAddressException" %><%@ page import="com.liferay.portlet.messageboards.MailingListOutServerNameException" %><%@ page import="com.liferay.portlet.messageboards.MailingListOutUserNameException" %><%@ page import="com.liferay.portlet.messageboards.MessageBodyException" %><%@ page import="com.liferay.portlet.messageboards.MessageSubjectException" %><%@ page import="com.liferay.portlet.messageboards.NoSuchCategoryException" %><%@ page import="com.liferay.portlet.messageboards.NoSuchMailingListException" %><%@ page import="com.liferay.portlet.messageboards.NoSuchMessageException" %><%@ page import="com.liferay.portlet.messageboards.RequiredMessageException" %><%@ page import="com.liferay.portlet.messageboards.SplitThreadException" %><%@ page import="com.liferay.portlet.messageboards.model.MBBan" %><%@ page import="com.liferay.portlet.messageboards.model.MBCategory" %><%@ page import="com.liferay.portlet.messageboards.model.MBCategoryConstants" %><%@ page import="com.liferay.portlet.messageboards.model.MBCategoryDisplay" %><%@ page import="com.liferay.portlet.messageboards.model.MBMailingList" %><%@ page import="com.liferay.portlet.messageboards.model.MBMessage" %><%@ page import="com.liferay.portlet.messageboards.model.MBMessageConstants" %><%@ page import="com.liferay.portlet.messageboards.model.MBMessageDisplay" %><%@ page import="com.liferay.portlet.messageboards.model.MBStatsUser" %><%@ page import="com.liferay.portlet.messageboards.model.MBThread" %><%@ page import="com.liferay.portlet.messageboards.model.MBThreadConstants" %><%@ page import="com.liferay.portlet.messageboards.model.MBThreadFlag" %><%@ page import="com.liferay.portlet.messageboards.model.MBTreeWalker" %><%@ page import="com.liferay.portlet.messageboards.model.impl.MBCategoryDisplayImpl" %><%@ page import="com.liferay.portlet.messageboards.model.impl.MBMessageImpl" %><%@ page import="com.liferay.portlet.messageboards.service.MBBanLocalServiceUtil" %><%@ page import="com.liferay.portlet.messageboards.service.MBCategoryLocalServiceUtil" %><%@ page import="com.liferay.portlet.messageboards.service.MBCategoryServiceUtil" %><%@ page import="com.liferay.portlet.messageboards.service.MBMailingListLocalServiceUtil" %><%@ page import="com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil" %><%@ page import="com.liferay.portlet.messageboards.service.MBMessageServiceUtil" %><%@ page import="com.liferay.portlet.messageboards.service.MBStatsUserLocalServiceUtil" %><%@ page import="com.liferay.portlet.messageboards.service.MBThreadFlagLocalServiceUtil" %><%@ page import="com.liferay.portlet.messageboards.service.MBThreadLocalServiceUtil" %><%@ page import="com.liferay.portlet.messageboards.service.MBThreadServiceUtil" %><%@ page import="com.liferay.portlet.messageboards.service.permission.MBCategoryPermission" %><%@ page import="com.liferay.portlet.messageboards.service.permission.MBMessagePermission" %><%@ page import="com.liferay.portlet.messageboards.service.permission.MBPermission" %><%@ page import="com.liferay.portlet.messageboards.util.MBUtil" %><%@ page import="com.liferay.portlet.messageboards.util.comparator.MessageCreateDateComparator" %><%@ page import="com.liferay.portlet.ratings.model.RatingsStats" %><%@ page import="com.liferay.portlet.ratings.service.RatingsStatsLocalServiceUtil" %><%@ page import="com.liferay.util.RSSUtil" %>

<%
PortletPreferences preferences = renderRequest.getPreferences();

String portletResource = ParamUtil.getString(request, "portletResource");

if (Validator.isNotNull(portletResource)) {
	preferences = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);
}

String currentLanguageId = LanguageUtil.getLanguageId(request);
Locale currentLocale = LocaleUtil.fromLanguageId(currentLanguageId);
Locale defaultLocale = LocaleUtil.getDefault();
String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

Locale[] locales = LanguageUtil.getAvailableLocales();

String[] priorities = LocalizationUtil.getPreferencesValues(preferences, "priorities", currentLanguageId);

int rssDelta = GetterUtil.getInteger(preferences.getValue("rssDelta", StringPool.BLANK), SearchContainer.DEFAULT_DELTA);
String rssDisplayStyle = preferences.getValue("rssDisplayStyle", RSSUtil.DISPLAY_STYLE_FULL_CONTENT);
String rssFormat = preferences.getValue("rssFormat", "atom10");
boolean allowAnonymousPosting = MBUtil.isAllowAnonymousPosting(preferences);
boolean subscribeByDefault = GetterUtil.getBoolean(preferences.getValue("subscribeByDefault", null), PropsValues.MESSAGE_BOARDS_SUBSCRIBE_BY_DEFAULT);
String messageFormat = MBUtil.getMessageFormat(preferences);
boolean enableFlags = GetterUtil.getBoolean(preferences.getValue("enableFlags", null), true);
boolean enableRatings = GetterUtil.getBoolean(preferences.getValue("enableRatings", null), true);
boolean threadAsQuestionByDefault = GetterUtil.getBoolean(preferences.getValue("threadAsQuestionByDefault", null));

String rssFormatType = RSSUtil.getFormatType(rssFormat);
double rssFormatVersion = RSSUtil.getFormatVersion(rssFormat);

ResourceURL rssURL = liferayPortletResponse.createResourceURL();

rssURL.setCacheability(ResourceURL.FULL);
rssURL.setParameter("struts_action", "/message_boards/rss");

if ((rssDelta != SearchContainer.DEFAULT_DELTA) || !rssFormatType.equals(RSSUtil.TYPE_DEFAULT) || (rssFormatVersion != RSSUtil.VERSION_DEFAULT) || !rssDisplayStyle.equals(RSSUtil.DISPLAY_STYLE_FULL_CONTENT)) {
	if (rssDelta != SearchContainer.DEFAULT_DELTA) {
		rssURL.setParameter("max", String.valueOf(rssDelta));
	}

	if (!rssFormatType.equals(RSSUtil.TYPE_DEFAULT)) {
		rssURL.setParameter("type", rssFormatType);
	}

	if (rssFormatVersion != RSSUtil.VERSION_DEFAULT) {
		rssURL.setParameter("version", String.valueOf(rssFormatVersion));
	}

	if (!rssDisplayStyle.equals(RSSUtil.DISPLAY_STYLE_FULL_CONTENT)) {
		rssURL.setParameter("displayStyle", rssDisplayStyle);
	}
}

rssURL.setParameter("p_l_id", String.valueOf(plid));
rssURL.setParameter("mbCategoryId", String.valueOf(scopeGroupId));

boolean categoriesPanelCollapsible = true;
boolean categoriesPanelExtended = true;
boolean threadsPanelCollapsible = true;
boolean threadsPanelExtended = true;

boolean childrenMessagesTaggable = true;
boolean includeFormTag = true;
boolean showSearch = true;

Format dateFormatDate = FastDateFormatFactoryUtil.getDate(locale, timeZone);
Format dateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(locale, timeZone);

NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
%>

<%@ include file="/html/portlet/message_boards/init-ext.jsp" %>