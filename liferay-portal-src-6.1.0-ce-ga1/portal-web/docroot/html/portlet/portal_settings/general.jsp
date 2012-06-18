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

<%@ include file="/html/portlet/portal_settings/init.jsp" %>

<%
VirtualHost virtualHost = null;

try {
	virtualHost = VirtualHostLocalServiceUtil.getVirtualHost(company.getCompanyId(), 0);
}
catch (Exception e) {
}

String cdnHostHttp = PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.CDN_HOST_HTTP, PropsValues.CDN_HOST_HTTP);
String cdnHostHttps = PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.CDN_HOST_HTTPS, PropsValues.CDN_HOST_HTTPS);

String defaultLandingPagePath = PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.DEFAULT_LANDING_PAGE_PATH, PropsValues.DEFAULT_LANDING_PAGE_PATH);
String defaultLogoutPagePath = PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.DEFAULT_LOGOUT_PAGE_PATH, PropsValues.DEFAULT_LOGOUT_PAGE_PATH);
%>

<liferay-ui:error-marker key="errorSection" value="general" />

<h3><liferay-ui:message key="main-configuration" /></h3>

<aui:model-context bean="<%= account %>" model="<%= Account.class %>" />

<aui:fieldset column="<%= true %>" cssClass="aui-w50">
	<liferay-ui:error exception="<%= AccountNameException.class %>" message="please-enter-a-valid-name" />

	<aui:input name="name" />

	<liferay-ui:error exception="<%= CompanyMxException.class %>" message="please-enter-a-valid-mail-domain" />

	<aui:input bean="<%= company %>" disabled="<%= !PropsValues.MAIL_MX_UPDATE %>" label="mail-domain" name="mx" model="<%= Company.class %>" />
</aui:fieldset>

<aui:fieldset column="<%= true %>" cssClass="aui-w50">
	<liferay-ui:error exception="<%= CompanyVirtualHostException.class %>" message="please-enter-a-valid-virtual-host" />

	<aui:input bean="<%= virtualHost %>" fieldParam="virtualHostname" label="virtual-host" model="<%= VirtualHost.class %>" name="hostname" />

	<aui:input label="cdn-host-http" name='<%= "settings--" + PropsKeys.CDN_HOST_HTTP + "--" %>' type="text" value="<%= cdnHostHttp %>" />

	<aui:input label="cdn-host-https" name='<%= "settings--" + PropsKeys.CDN_HOST_HTTPS + "--" %>' type="text" value="<%= cdnHostHttps %>" />
</aui:fieldset>

<h3><liferay-ui:message key="navigation" /></h3>

<aui:fieldset column="<%= true %>" cssClass="aui-w50">
	<aui:input bean="<%= company %>" helpMessage="home-url-help" label="home-url" name="homeURL" model="<%= Company.class %>" />
</aui:fieldset>

<aui:fieldset column="<%= true %>" cssClass="aui-w50">
	<aui:input helpMessage="default-landing-page-help" label="default-landing-page" name='<%= "settings--" + PropsKeys.DEFAULT_LANDING_PAGE_PATH + "--" %>' type="text" value="<%= defaultLandingPagePath %>" />

	<aui:input helpMessage="default-logout-page-help" label="default-logout-page" name='<%= "settings--" + PropsKeys.DEFAULT_LOGOUT_PAGE_PATH + "--" %>' type="text" value="<%= defaultLogoutPagePath %>" />
</aui:fieldset>

<h3><liferay-ui:message key="additional-information" /></h3>

<aui:fieldset column="<%= true %>" cssClass="aui-w50">
	<aui:input name="legalName" />

	<aui:input name="legalId" />

	<aui:input name="legalType" />
</aui:fieldset>

<aui:fieldset column="<%= true %>" cssClass="aui-w50">
	<aui:input name="sicCode" />

	<aui:input name="tickerSymbol" />

	<aui:input name="industry" />

	<aui:input name="type" />
</aui:fieldset>