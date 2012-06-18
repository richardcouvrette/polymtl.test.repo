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

<%@ include file="/html/portlet/layouts_admin/init_attributes.jspf" %>

<%
SitesUtil.addPortletBreadcrumbEntries(group, pagesName, redirectURL, request, renderResponse);
%>

<liferay-ui:error exception="<%= LayoutTypeException.class %>">

	<%
	LayoutTypeException lte = (LayoutTypeException)errorException;

	String type = BeanParamUtil.getString(selLayout, request, "type");
	%>

	<c:if test="<%= lte.getType() == LayoutTypeException.FIRST_LAYOUT %>">
		<liferay-ui:message arguments='<%= Validator.isNull(lte.getLayoutType()) ? type : "layout.types." + lte.getLayoutType() %>' key="the-first-page-cannot-be-of-type-x" />
	</c:if>

	<c:if test="<%= lte.getType() == LayoutTypeException.NOT_PARENTABLE %>">
		<liferay-ui:message arguments="<%= type %>" key="pages-of-type-x-cannot-have-child-pages" />
	</c:if>
</liferay-ui:error>

<liferay-ui:error exception="<%= LayoutNameException.class %>" message="please-enter-a-valid-name" />

<c:choose>
	<c:when test="<%= portletName.equals(PortletKeys.MY_SITES) || portletName.equals(PortletKeys.GROUP_PAGES) || portletName.equals(PortletKeys.MY_PAGES) || portletName.equals(PortletKeys.SITES_ADMIN) || portletName.equals(PortletKeys.USER_GROUPS_ADMIN) || portletName.equals(PortletKeys.USERS_ADMIN) %>">
		<c:if test="<%= portletName.equals(PortletKeys.MY_SITES) || portletName.equals(PortletKeys.GROUP_PAGES) || portletName.equals(PortletKeys.SITES_ADMIN) || portletName.equals(PortletKeys.USER_GROUPS_ADMIN) || portletName.equals(PortletKeys.USERS_ADMIN) %>">
			<liferay-ui:header
				backURL="<%= backURL %>"
				localizeTitle="<%= false %>"
				title="<%= liveGroup.getDescriptiveName(locale) %>"
			/>
		</c:if>

		<%
		String tabs1URL = redirectURL.toString();

		if (liveGroup.isUser()) {
			PortletURL userTabs1URL = renderResponse.createRenderURL();

			userTabs1URL.setParameter("struts_action", "/my_pages/edit_layouts");
			userTabs1URL.setParameter("tabs1", tabs1);
			userTabs1URL.setParameter("backURL", backURL);
			userTabs1URL.setParameter("groupId", String.valueOf(liveGroupId));

			tabs1URL = userTabs1URL.toString();
		}
		%>

		<liferay-ui:tabs
			names="<%= tabs1Names %>"
			param="tabs1"
			value="<%= tabs1 %>"
			url="<%= tabs1URL %>"
		/>

		<%
		PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, TextFormatter.format(tabs1, TextFormatter.O)), redirectURL.toString());

		if ((selLayout != null) && !group.isLayoutPrototype()) {
			redirectURL.setParameter("selPlid", String.valueOf(selLayout.getPlid()));

			PortalUtil.addPortletBreadcrumbEntry(request, selLayout.getName(locale), currentURL);
		}
		%>
	</c:when>
	<c:otherwise>

		<%
		if ((selLayout != null) && !group.isLayoutPrototype()) {
			redirectURL.setParameter("selPlid", String.valueOf(selLayout.getPlid()));

			PortalUtil.addPortletBreadcrumbEntry(request, selLayout.getName(locale), redirectURL.toString());
		}
		%>

		<div class="layout-breadcrumb">
			<liferay-ui:breadcrumb displayStyle="horizontal" showPortletBreadcrumb="<%= true %>" showGuestGroup="<%= false %>" showLayout="<%= false %>" showParentGroups="<%= false %>" />
		</div>
	</c:otherwise>
</c:choose>

<aui:layout cssClass="manage-view lfr-app-column-view">
	<c:if test="<%= !group.isLayoutPrototype() %>">
		<aui:column columnWidth="25" cssClass="manage-sitemap">
			<div class="lfr-header-row">
				<div class="lfr-header-row-content">
					<c:if test="<%= stagingGroup != null %>">

						<%
						long layoutSetBranchId = ParamUtil.getLong(request, "layoutSetBranchId");

						if (layoutSetBranchId <= 0) {
							layoutSetBranchId = StagingUtil.getRecentLayoutSetBranchId(user, selLayoutSet.getLayoutSetId());
						}

						LayoutSetBranch layoutSetBranch = null;

						if (layoutSetBranchId > 0) {
							try {
								layoutSetBranch = LayoutSetBranchLocalServiceUtil.getLayoutSetBranch(layoutSetBranchId);
							}
							catch (NoSuchLayoutSetBranchException nslsbe) {
							}
						}

						if (layoutSetBranch == null) {
							try {
								layoutSetBranch = LayoutSetBranchLocalServiceUtil.getMasterLayoutSetBranch(stagingGroup.getGroupId(), privateLayout);
							}
							catch (NoSuchLayoutSetBranchException nslsbe) {
							}
						}

						List<LayoutSetBranch> layoutSetBranches = LayoutSetBranchLocalServiceUtil.getLayoutSetBranches(stagingGroup.getGroupId(), privateLayout);
						%>

						<c:choose>
							<c:when test="<%= layoutSetBranches.size() > 1 %>">
								<liferay-ui:icon-menu align="left" cssClass="layoutset-branches-menu" direction="down" extended="<%= true %>" message="<%= layoutSetBranch.getName() %>" icon='<%= themeDisplay.getPathThemeImages() + "/dock/staging.png" %>' >

									<%
									for (int i = 0; i < layoutSetBranches.size(); i++) {
										LayoutSetBranch curLayoutSetBranch = layoutSetBranches.get(i);

										boolean selected = (curLayoutSetBranch.getLayoutSetBranchId() == layoutSetBranch.getLayoutSetBranchId());
									%>

										<portlet:actionURL var="layoutSetBranchURL">
											<portlet:param name="struts_action" value="/dockbar/edit_layouts" />
											<portlet:param name="<%= Constants.CMD %>" value="select_layout_set_branch" />
											<portlet:param name="redirect" value="<%= redirectURL.toString() %>" />
											<portlet:param name="groupId" value="<%= String.valueOf(curLayoutSetBranch.getGroupId()) %>" />
											<portlet:param name="privateLayout" value="<%= String.valueOf(privateLayout) %>" />
											<portlet:param name="layoutSetBranchId" value="<%= String.valueOf(curLayoutSetBranch.getLayoutSetBranchId()) %>" />
										</portlet:actionURL>

										<liferay-ui:icon
											cssClass='<%= selected ? "disabled" : StringPool.BLANK %>'
											image='<%= selected ? "../arrows/01_right" : "copy"  %>'
											message="<%= curLayoutSetBranch.getName() %>"
											url="<%= selected ? null : layoutSetBranchURL %>"
										/>

									<%
									}
									%>

								</liferay-ui:icon-menu>
							</c:when>
							<c:otherwise>
								<liferay-ui:icon
									cssClass="layoutset-branch"
									image="../dock/staging"
									label="<%= true %>"
									message='<%= (layoutSetBranch == null || (layoutSetBranches.size() == 1)) ? "staging" : layoutSetBranch.getName() %>'
								/>
							</c:otherwise>
						</c:choose>

						<liferay-ui:staging cssClass="manage-pages-branch-menu" extended="<%= true %>" groupId="<%= groupId %>" icon="/common/tool.png" message="" privateLayout="<%= privateLayout %>" selPlid="<%= selPlid %>" showManageBranches="<%= true %>"  />
					</c:if>
				</div>
			</div>

			<liferay-util:include page="/html/portlet/layouts_admin/tree_js.jsp">
				<liferay-util:param name="treeId" value="layoutsTree" />
			</liferay-util:include>
		</aui:column>
	</c:if>

	<aui:column columnWidth="<%= group.isLayoutPrototype() ? 100 : 75 %>" cssClass="manage-layout">
		<div id="<portlet:namespace />layoutsContainer">
			<c:choose>
				<c:when test="<%= selPlid > 0 %>">
					<liferay-util:include page="/html/portlet/layouts_admin/edit_layout.jsp" />
				</c:when>
				<c:otherwise>
					<liferay-util:include page="/html/portlet/layouts_admin/edit_layout_set.jsp" />
				</c:otherwise>
			</c:choose>
		</div>
	</aui:column>
</aui:layout>

<c:if test="<%= !group.isLayoutPrototype() %>">
	<aui:script use="aui-io-plugin">
		var layoutsContainer = A.one('#<portlet:namespace />layoutsContainer');

		layoutsContainer.plug(
			A.Plugin.IO,
			{
				autoLoad: false
			}
		);

		A.one('#<portlet:namespace />layoutsTreeOutput').delegate(
			'click',
			function(event) {
				event.preventDefault();

				var requestUri = A.Lang.sub(
					event.currentTarget.get('href'),
					{
						historyKey: location.hash.replace('#_LFR_FN_<portlet:namespace />', '')
					}
				);

				layoutsContainer.io.set('uri', requestUri);

				layoutsContainer.io.start();
			},
			'.layout-tree'
		);
	</aui:script>
</c:if>