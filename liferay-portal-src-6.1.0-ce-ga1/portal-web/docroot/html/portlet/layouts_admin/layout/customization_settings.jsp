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

<%@ include file="/html/portlet/layouts_admin/init.jsp" %>

<%
Layout selLayout = (Layout)request.getAttribute("edit_pages.jsp-selLayout");

String content = StringPool.BLANK;

boolean curFreeformLayout = false;
boolean prototypeGroup = false;

if (selLayout != null) {
	Group group = selLayout.getGroup();
	Theme curTheme = selLayout.getTheme();

	String themeId = curTheme.getThemeId();

	LayoutTypePortlet curLayoutTypePortlet = (LayoutTypePortlet)selLayout.getLayoutType();

	String layoutTemplateId = curLayoutTypePortlet.getLayoutTemplateId();

	if (Validator.isNull(layoutTemplateId)) {
		layoutTemplateId = PropsValues.DEFAULT_LAYOUT_TEMPLATE_ID;
	}

	curFreeformLayout = layoutTemplateId.equals("freeform");

	if (group.isLayoutPrototype() || group.isLayoutSetPrototype()) {
		prototypeGroup = true;
	}

	if (!curFreeformLayout && !prototypeGroup) {
		LayoutTemplate layoutTemplate = LayoutTemplateLocalServiceUtil.getLayoutTemplate(layoutTemplateId, false, themeId);

		if (layoutTemplate != null) {
			themeId = layoutTemplate.getThemeId();

			String velocityTemplateId = themeId + LayoutTemplateConstants.CUSTOM_SEPARATOR + curLayoutTypePortlet.getLayoutTemplateId();

			String velocityTemplateContent = LayoutTemplateLocalServiceUtil.getContent(curLayoutTypePortlet.getLayoutTemplateId(), false, themeId);

			ServletContext layoutTemplateServletContext = ServletContextPool.get(layoutTemplate.getServletContextName());

			content = RuntimePortletUtil.processCustomizationSettings(layoutTemplateServletContext, request, response, pageContext, velocityTemplateId, velocityTemplateContent);
		}
	}
}
%>

<liferay-ui:error-marker key="errorSection" value="customization-settings" />

<aui:model-context bean="<%= selLayout %>" model="<%= Layout.class %>" />

<h3><liferay-ui:message key="customization-settings" /></h3>

<c:choose>
	<c:when test="<%= curFreeformLayout %>">
		<div class="portlet-msg-alert">
			<liferay-ui:message key="it-is-not-possible-to-specify-customization-settings-for-freeform-layouts" />
		</div>
	</c:when>
	<c:when test="<%= prototypeGroup %>">
		<div class="portlet-msg-alert">
			<liferay-ui:message key="it-is-not-possible-to-specify-customization-settings-for-pages-in-site-templates-or-page-templates" />
		</div>
	</c:when>
	<c:otherwise>
		<div class="portlet-msg-info">
			<liferay-ui:message key="customizable-help" />
		</div>
	</c:otherwise>
</c:choose>

<div class="customization-settings">
	<%= content %>
</div>