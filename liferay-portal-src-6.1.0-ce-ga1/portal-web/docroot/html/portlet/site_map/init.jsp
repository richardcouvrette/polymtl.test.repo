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

<%@ page import="com.liferay.portal.util.LayoutLister" %><%@
page import="com.liferay.portal.util.LayoutView" %>

<%
PortletPreferences preferences = renderRequest.getPreferences();

String portletResource = ParamUtil.getString(request, "portletResource");

if (Validator.isNotNull(portletResource)) {
	preferences = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);
}

String rootLayoutUuid = GetterUtil.getString(preferences.getValue("rootLayoutUuid", StringPool.BLANK));
int displayDepth = GetterUtil.getInteger(preferences.getValue("displayDepth", StringPool.BLANK));
boolean includeRootInTree = GetterUtil.getBoolean(preferences.getValue("includeRootInTree", StringPool.BLANK));
boolean showCurrentPage = GetterUtil.getBoolean(preferences.getValue("showCurrentPage", StringPool.BLANK));
boolean useHtmlTitle = GetterUtil.getBoolean(preferences.getValue("useHtmlTitle", StringPool.BLANK));
boolean showHiddenPages = GetterUtil.getBoolean(preferences.getValue("showHiddenPages", StringPool.BLANK));

Layout rootLayout = null;

long rootLayoutId = LayoutConstants.DEFAULT_PARENT_LAYOUT_ID;

if (Validator.isNotNull(rootLayoutUuid)) {
	rootLayout = LayoutLocalServiceUtil.getLayoutByUuidAndGroupId(rootLayoutUuid, scopeGroupId);

	if (rootLayout != null) {
		rootLayoutId = rootLayout.getLayoutId();
	}
}
else {
	includeRootInTree = false;
}

if (rootLayoutId == LayoutConstants.DEFAULT_PARENT_LAYOUT_ID) {
	includeRootInTree = false;
}
%>