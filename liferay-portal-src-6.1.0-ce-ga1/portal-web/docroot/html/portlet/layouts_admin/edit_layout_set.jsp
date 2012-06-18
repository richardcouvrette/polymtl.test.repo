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
String closeRedirect = ParamUtil.getString(request, "closeRedirect");

Group selGroup = (Group)request.getAttribute(WebKeys.GROUP);

Group group = (Group)request.getAttribute("edit_pages.jsp-group");
Group liveGroup = (Group)request.getAttribute("edit_pages.jsp-liveGroup");
long groupId = (Long)request.getAttribute("edit_pages.jsp-groupId");
long liveGroupId = (Long)request.getAttribute("edit_pages.jsp-liveGroupId");
long stagingGroupId = (Long)request.getAttribute("edit_pages.jsp-stagingGroupId");
long selPlid = ((Long)request.getAttribute("edit_pages.jsp-selPlid")).longValue();
boolean privateLayout = ((Boolean)request.getAttribute("edit_pages.jsp-privateLayout")).booleanValue();
UnicodeProperties liveGroupTypeSettings = (UnicodeProperties)request.getAttribute("edit_pages.jsp-liveGroupTypeSettings");
LayoutSet selLayoutSet = ((LayoutSet)request.getAttribute("edit_pages.jsp-selLayoutSet"));

String rootNodeName = (String)request.getAttribute("edit_pages.jsp-rootNodeName");

PortletURL redirectURL = (PortletURL)request.getAttribute("edit_pages.jsp-redirectURL");

int pagesCount = 0;

if (selGroup.isLayoutSetPrototype()) {
	privateLayout = true;
}

if (privateLayout) {
	if (group != null) {
		pagesCount = group.getPrivateLayoutsPageCount();
	}
}
else {
	if (group != null) {
		pagesCount = group.getPublicLayoutsPageCount();
	}
}

String[] mainSections = PropsValues.LAYOUT_SET_FORM_UPDATE;

if (!company.isSiteLogo()) {
	mainSections = ArrayUtil.remove(mainSections, "logo");
}

String[][] categorySections = {mainSections};
%>

<div class="lfr-header-row">
	<div class="lfr-header-row-content">
		<liferay-util:include page="/html/portlet/layouts_admin/add_layout.jsp">
			<liferay-util:param name="firstLayout" value="<%= String.valueOf(selLayoutSet.getPageCount() == 0) %>" />
		</liferay-util:include>

		<aui:button-row cssClass="edit-toolbar" id='<%= liferayPortletResponse.getNamespace() + "layoutSetToolbar" %>'>
			<c:if test="<%= GroupPermissionUtil.contains(permissionChecker, liveGroupId, ActionKeys.EXPORT_IMPORT_LAYOUTS) %>">
				<c:if test="<%= SessionErrors.contains(liferayPortletRequest, LayoutImportException.class.getName()) || SessionErrors.contains(liferayPortletRequest, LARFileException.class.getName()) || SessionErrors.contains(liferayPortletRequest, LARTypeException.class.getName()) %>">
					<liferay-util:html-top>
						<div class="aui-helper-hidden" id="<portlet:namespace />importPage">
							<liferay-util:include page="/html/portlet/layouts_admin/export_import.jsp">
								<liferay-util:param name="<%= Constants.CMD %>" value="<%= Constants.IMPORT %>" />
								<liferay-util:param name="groupId" value="<%= String.valueOf(groupId) %>" />
								<liferay-util:param name="liveGroupId" value="<%= String.valueOf(liveGroupId) %>" />
								<liferay-util:param name="privateLayout" value="<%= String.valueOf(privateLayout) %>" />
								<liferay-util:param name="rootNodeName" value="<%= rootNodeName %>" />
							</liferay-util:include>
						</div>
					</liferay-util:html-top>

					<aui:script use="aui-dialog">
						new A.Dialog(
							{
								bodyContent: A.one('#<portlet:namespace />importPage').show(),
								centered: true,
								modal: true,
								title: '<%= UnicodeLanguageUtil.get(pageContext, "import") %>',
								width: 600
							}
						).render();
					</aui:script>
				</c:if>
			</c:if>
		</aui:button-row>
	</div>
</div>

<c:if test="<%= liveGroup.isStaged() %>">
	<liferay-ui:error exception="<%= RemoteExportException.class %>">

		<%
		RemoteExportException ree = (RemoteExportException)errorException;
		%>

		<c:if test="<%= ree.getType() == RemoteExportException.BAD_CONNECTION %>">
			<%= LanguageUtil.format(pageContext, "could-not-connect-to-address-x.-please-verify-that-the-specified-port-is-correct-and-that-the-remote-server-is-configured-to-accept-requests-from-this-server", "<em>" + ree.getURL() + "</em>") %>
		</c:if>

		<c:if test="<%= ree.getType() == RemoteExportException.NO_GROUP %>">
			<%= LanguageUtil.format(pageContext, "remote-group-with-id-x-does-not-exist", ree.getGroupId()) %>
		</c:if>

		<c:if test="<%= ree.getType() == RemoteExportException.NO_LAYOUTS %>">
			<liferay-ui:message key="no-pages-are-selected-for-export" />
		</c:if>

		<c:if test="<%= ree.getType() == RemoteExportException.NO_PERMISSIONS %>">
			<liferay-ui:message arguments="<%= ree.getGroupId() %>" key="you-do-not-have-permissions-to-edit-the-site-with-id-x-on-the-remote-server" />
		</c:if>
	</liferay-ui:error>

	<div class="portlet-msg-alert">
		<liferay-ui:message key="the-staging-environment-is-activated-changes-have-to-be-published-to-make-them-available-to-end-users" />
	</div>
</c:if>

<aui:script use="aui-dialog,aui-toolbar">
	var popup;
	var exportPopup;
	var importPopup;

	var layoutSetToolbar = new A.Toolbar(
		{
			activeState: false,
			boundingBox: '#<portlet:namespace />layoutSetToolbar',
			children: [
				<c:if test="<%= !group.isLayoutPrototype() && GroupPermissionUtil.contains(permissionChecker, groupId, ActionKeys.ADD_LAYOUT) %>">
					{
						handler: function(event) {
							var content = A.one('#<portlet:namespace />addLayout');

							if (!popup) {
								popup = new A.Dialog(
									{
										bodyContent: content.show(),
										centered: true,
										title: '<%= UnicodeLanguageUtil.get(pageContext, "add-page") %>',
										modal: true,
										width: 500
									}
								).render();
							}

							popup.show();

							Liferay.Util.focusFormField(content.one('input:text'));
						},
						icon: 'add',
						label: '<%= UnicodeLanguageUtil.get(pageContext, "add-page") %>'
					},
				</c:if>

				<c:if test="<%= (pagesCount > 0) && (liveGroup.isStaged() || selGroup.isLayoutSetPrototype() || selGroup.isStagingGroup() || portletName.equals(PortletKeys.MY_SITES) || portletName.equals(PortletKeys.GROUP_PAGES) || portletName.equals(PortletKeys.SITES_ADMIN) || portletName.equals(PortletKeys.USERS_ADMIN)) %>">
					<liferay-portlet:actionURL plid="<%= selPlid %>" portletName="<%= PortletKeys.SITE_REDIRECTOR %>" var="viewPagesURL">
						<portlet:param name="struts_action" value="/my_sites/view" />
						<portlet:param name="groupId" value="<%= String.valueOf(groupId) %>" />
						<portlet:param name="privateLayout" value="<%= String.valueOf(privateLayout) %>" />
					</liferay-portlet:actionURL>

					{
						handler: function(event) {
							window.open('<%= viewPagesURL %>').focus();
						},
						icon: 'search',
						label: '<%= UnicodeLanguageUtil.get(pageContext, "view-pages") %>'
					},
				</c:if>

				<c:if test="<%= GroupPermissionUtil.contains(permissionChecker, liveGroupId, ActionKeys.EXPORT_IMPORT_LAYOUTS) %>">
					{
						type: 'ToolbarSpacer'
					},
					{
						handler: function(event) {
							<portlet:renderURL var="exportPagesURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
								<portlet:param name="struts_action" value="/layouts_admin/export_layouts" />
								<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.EXPORT %>" />
								<portlet:param name="groupId" value="<%= String.valueOf(groupId) %>" />
								<portlet:param name="liveGroupId" value="<%= String.valueOf(liveGroupId) %>" />
								<portlet:param name="privateLayout" value="<%= String.valueOf(privateLayout) %>" />
								<portlet:param name="rootNodeName" value="<%= rootNodeName %>" />
							</portlet:renderURL>

							Liferay.Util.openWindow(
								{
									dialog:
										{
											centered: true,
											constrain: true,
											modal: true,
											width: 600
										},
									id: '<portlet:namespace />exportDialog',
									title: '<%= UnicodeLanguageUtil.get(pageContext, "export") %>',
									uri: '<%= exportPagesURL.toString() %>'
								}
							);
						},
						icon: 'export',
						label: '<%= UnicodeLanguageUtil.get(pageContext, "export") %>'
					},
					{
						handler: function(event) {
							<portlet:renderURL var="importPagesURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
								<portlet:param name="struts_action" value="/layouts_admin/import_layouts" />
								<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.IMPORT %>" />
								<portlet:param name="groupId" value="<%= String.valueOf(groupId) %>" />
								<portlet:param name="liveGroupId" value="<%= String.valueOf(liveGroupId) %>" />
								<portlet:param name="privateLayout" value="<%= String.valueOf(privateLayout) %>" />
								<portlet:param name="rootNodeName" value="<%= rootNodeName %>" />
							</portlet:renderURL>

							Liferay.Util.openWindow(
								{
									dialog:
										{
											centered: true,
											constrain: true,
											modal: true,
											width: 600
										},
									id: '<portlet:namespace />importDialog',
									title: '<%= UnicodeLanguageUtil.get(pageContext, "import") %>',
									uri: '<%= importPagesURL.toString() %>'
								}
							);


						},
						icon: 'arrowthick-1-t',
						label: '<%= UnicodeLanguageUtil.get(pageContext, "import") %>'
					}
				</c:if>
			]
		}
	).render();
</aui:script>

<portlet:actionURL var="editLayoutSetURL">
	<portlet:param name="struts_action" value="/layouts_admin/edit_layout_set" />
</portlet:actionURL>

<aui:form action="<%= editLayoutSetURL %>" cssClass="edit-layoutset-form" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + liferayPortletResponse.getNamespace() + "saveLayoutset();" %>'>
	<aui:input name="<%= Constants.CMD %>" type="hidden" />
	<aui:input name="redirect" type="hidden" value="<%= redirectURL.toString() %>" />
	<aui:input name="closeRedirect" type="hidden" value="<%= closeRedirect %>" />
	<aui:input name="groupId" type="hidden" value="<%= groupId %>" />
	<aui:input name="liveGroupId" type="hidden" value="<%= liveGroupId %>" />
	<aui:input name="stagingGroupId" type="hidden" value="<%= stagingGroupId %>" />
	<aui:input name="selPlid" type="hidden" value="<%= selPlid %>" />
	<aui:input name="privateLayout" type="hidden" value="<%= privateLayout %>" />
	<aui:input name="layoutSetId" type="hidden" value="<%= selLayoutSet.getLayoutSetId() %>" />
	<aui:input name="<%= PortletDataHandlerKeys.SELECTED_LAYOUTS %>" type="hidden" />

	<liferay-ui:form-navigator
		categoryNames="<%= _CATEGORY_NAMES %>"
		categorySections="<%= categorySections %>"
		jspPath="/html/portlet/layouts_admin/layout_set/"
		showButtons="<%= GroupPermissionUtil.contains(permissionChecker, liveGroupId, ActionKeys.UPDATE) && SitesUtil.isLayoutSetPrototypeUpdateable(selLayoutSet) %>"
	/>
</aui:form>

<aui:script>
	function <portlet:namespace />saveLayoutset(action) {
		document.<portlet:namespace />fm.encoding = 'multipart/form-data';

		if (action) {
			document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = action;
		}
		else {
			document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = 'update';
		}

		document.<portlet:namespace />fm.<portlet:namespace />redirect.value += Liferay.Util.getHistoryParam('<portlet:namespace />');

		submitForm(document.<portlet:namespace />fm);
	}

	function <portlet:namespace />updateLogo() {
		document.<portlet:namespace />fm.encoding = 'multipart/form-data';
		document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = 'logo';
		submitForm(document.<portlet:namespace />fm);
	}

	function <portlet:namespace />updateRobots() {
		document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = 'robots';
		submitForm(document.<portlet:namespace />fm);
	}

	Liferay.provide(
		window,
		'<portlet:namespace />removePage',
		function(box) {
			var A = AUI();

			var selectEl = A.one(box);

			var currentValue = selectEl.val() || null;

			Liferay.Util.removeItem(box);
		},
		['aui-base']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />updateDisplayOrder',
		function() {
			document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = 'display_order';
			document.<portlet:namespace />fm.<portlet:namespace />layoutIds.value = Liferay.Util.listSelect(document.<portlet:namespace />fm.<portlet:namespace />layoutIdsBox);
			submitForm(document.<portlet:namespace />fm);
		},
		['liferay-util-list-fields']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />updateStaging',
		function() {
			var A = AUI();

			var selectEl = A.one('#<portlet:namespace />stagingType');

			var currentValue = selectEl.val() || null;

			var ok = false;

			if (currentValue == 0) {
				ok = confirm('<%= UnicodeLanguageUtil.format(pageContext, "are-you-sure-you-want-to-deactivate-staging-for-x", liveGroup.getDescriptiveName(locale)) %>');
			}
			else if (currentValue == 1) {
				ok = confirm('<%= UnicodeLanguageUtil.format(pageContext, "are-you-sure-you-want-to-activate-local-staging-for-x", liveGroup.getDescriptiveName(locale)) %>');
			}
			else if (currentValue == 2) {
				ok = confirm('<%= UnicodeLanguageUtil.format(pageContext, "are-you-sure-you-want-to-activate-remote-staging-for-x", liveGroup.getDescriptiveName(locale)) %>');
			}

			if (ok) {
				document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = 'staging';
				submitForm(document.<portlet:namespace />fm);
			}
		},
		['aui-base']
	);
</aui:script>

<%!
private static String[] _CATEGORY_NAMES = {""};
%>