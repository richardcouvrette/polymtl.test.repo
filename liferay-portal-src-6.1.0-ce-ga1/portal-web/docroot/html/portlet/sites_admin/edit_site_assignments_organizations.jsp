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

<%@ include file="/html/portlet/sites_admin/init.jsp" %>

<%
String tabs1 = (String)request.getAttribute("edit_site_assignments.jsp-tabs1");
String tabs2 = (String)request.getAttribute("edit_site_assignments.jsp-tabs2");

int cur = (Integer)request.getAttribute("edit_site_assignments.jsp-cur");

String redirect = ParamUtil.getString(request, "redirect");

Group group = (Group)request.getAttribute("edit_site_assignments.jsp-group");

PortletURL portletURL = (PortletURL)request.getAttribute("edit_site_assignments.jsp-portletURL");

PortletURL viewOrganizationsURL = renderResponse.createRenderURL();

viewOrganizationsURL.setParameter("struts_action", "/sites_admin/edit_site_assignments");
viewOrganizationsURL.setParameter("tabs1", "organizations");
viewOrganizationsURL.setParameter("tabs2", tabs2);
viewOrganizationsURL.setParameter("redirect", redirect);
viewOrganizationsURL.setParameter("groupId", String.valueOf(group.getGroupId()));

OrganizationGroupChecker organizationGroupChecker = null;

if (!tabs1.equals("summary") && !tabs2.equals("current")) {
	organizationGroupChecker = new OrganizationGroupChecker(renderResponse, group);
}

String emptyResultsMessage = OrganizationSearch.EMPTY_RESULTS_MESSAGE;

if (tabs2.equals("current")) {
	emptyResultsMessage ="no-organization-was-found-that-is-a-member-of-this-site";
}

OrganizationSearch organizationSearch = new OrganizationSearch(renderRequest, viewOrganizationsURL);

organizationSearch.setEmptyResultsMessage(emptyResultsMessage);
%>

<aui:input name="tabs1" type="hidden" value="organizations" />
<aui:input name="addOrganizationIds" type="hidden" />
<aui:input name="removeOrganizationIds" type="hidden" />

<liferay-ui:search-container
	rowChecker="<%= organizationGroupChecker %>"
	searchContainer="<%= organizationSearch %>"
>
	<c:if test='<%= !tabs1.equals("summary") %>'>
		<liferay-ui:search-form
			page="/html/portlet/users_admin/organization_search.jsp"
		/>

		<div class="separator"><!-- --></div>
	</c:if>

	<%
	OrganizationSearchTerms searchTerms = (OrganizationSearchTerms)searchContainer.getSearchTerms();

	long parentOrganizationId = OrganizationConstants.ANY_PARENT_ORGANIZATION_ID;

	LinkedHashMap organizationParams = new LinkedHashMap();

	if (tabs1.equals("summary") || tabs2.equals("current")) {
		organizationParams.put("organizationsGroups", new Long(group.getGroupId()));
	}
	%>

	<liferay-ui:search-container-results>
		<%@ include file="/html/portlet/users_admin/organization_search_results.jspf" %>
	</liferay-ui:search-container-results>

	<liferay-ui:search-container-row
		className="com.liferay.portal.model.Organization"
		escapedModel="<%= true %>"
		keyProperty="organizationId"
		modelVar="organization"
	>
		<liferay-ui:search-container-column-text
			name="name"
			orderable="<%= true %>"
			property="name"
		/>

		<liferay-ui:search-container-column-text
			buffer="buffer"
			name="parent-organization"
		>

			<%
			if (organization.getParentOrganizationId() > 0) {
				try {
					Organization parentOrganization = OrganizationLocalServiceUtil.getOrganization(organization.getParentOrganizationId());

					buffer.append(HtmlUtil.escape(parentOrganization.getName()));
				}
				catch (Exception e) {
				}
			}
			%>

		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			name="type"
			orderable="<%= true %>"
			value="<%= LanguageUtil.get(pageContext, organization.getType()) %>"
		/>

		<liferay-ui:search-container-column-text
			name="city"
			value="<%= HtmlUtil.escape(organization.getAddress().getCity()) %>"
		/>

		<liferay-ui:search-container-column-text
			name="region"
		>
			<liferay-ui:write bean="<%= organization %>" property="region" />
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			name="country"
		>
			<liferay-ui:write bean="<%= organization %>" property="country" />
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>

	<liferay-util:buffer var="formButton">
		<c:choose>
			<c:when test='<%= tabs2.equals("current") %>'>

				<%
				viewOrganizationsURL.setParameter("tabs2", "available");
				%>

				<aui:button-row>
					<aui:button href="<%= viewOrganizationsURL.toString() %>" value="assign-organizations" />
				</aui:button-row>
			</c:when>
			<c:otherwise>

				<%
				portletURL.setParameter("tabs2", "current");

				String taglibOnClick = renderResponse.getNamespace() + "updateGroupOrganizations('" + portletURL.toString() + StringPool.AMPERSAND + renderResponse.getNamespace() + "cur=" + cur + "');";
				%>

				<aui:button-row>
					<aui:button onClick="<%= taglibOnClick %>" value="save" />
				</aui:button-row>
			</c:otherwise>
		</c:choose>
	</liferay-util:buffer>

	<c:choose>
		<c:when test='<%= tabs1.equals("summary") && (total > 0) %>'>
			<liferay-ui:panel collapsible="<%= true %>" extended="<%= false %>" persistState="<%= true %>" title='<%= LanguageUtil.format(pageContext, (total > 1) ? "x-organizations" : "x-organization", total) %>'>
				<aui:input inlineField="<%= true %>" label="" name='<%= DisplayTerms.KEYWORDS + "_organizations" %>' size="30" value="" />

				<aui:button type="submit" value="search" />

				<br /><br />

				<liferay-ui:search-iterator paginate="<%= false %>" />

				<c:if test="<%= total > organizationSearch.getDelta() %>">
					<a href="<%= viewOrganizationsURL %>"><liferay-ui:message key="view-more" /> &raquo;</a>
				</c:if>
			</liferay-ui:panel>

			<div class="separator"><!-- --></div>
		</c:when>
		<c:when test='<%= !tabs1.equals("summary") %>'>

			<%
			Organization groupOrganization = null;

			if (group.isOrganization()) {
				groupOrganization = OrganizationLocalServiceUtil.getOrganization(group.getOrganizationId());
			}
			%>

			<c:if test='<%= tabs2.equals("current") && (groupOrganization != null) %>'>
				<div class="organizations-msg-info portlet-msg">
					<liferay-ui:message key="this-site-belongs-to-x-which-is-an-organization-of-type-x" arguments="<%= new String[] {groupOrganization.getName(), LanguageUtil.get(pageContext, groupOrganization.getType())} %>" />
				</div>
			</c:if>

			<c:if test="<%= total > organizationSearch.getDelta() %>">
				<%= formButton %>
			</c:if>

			<liferay-ui:search-iterator />

			<%= formButton %>
		</c:when>
	</c:choose>
</liferay-ui:search-container>