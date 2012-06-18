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

<%@ include file="/html/portlet/document_library/init.jsp" %>

<%
String strutsAction = ParamUtil.getString(request, "struts_action");

String tabs2 = ParamUtil.getString(request, "tabs2", "version-history");

String redirect = ParamUtil.getString(request, "redirect");

DLFileShortcut fileShortcut = (DLFileShortcut)request.getAttribute(WebKeys.DOCUMENT_LIBRARY_FILE_SHORTCUT);

long fileShortcutId = BeanParamUtil.getLong(fileShortcut, request, "fileShortcutId");

long toGroupId = ParamUtil.getLong(request, "toGroupId");

Group toGroup = null;

long repositoryId = BeanParamUtil.getLong(fileShortcut, request, "repositoryId");
long folderId = BeanParamUtil.getLong(fileShortcut, request, "folderId");

Folder toFolder = null;

long toFileEntryId = BeanParamUtil.getLong(fileShortcut, request, "toFileEntryId");

FileEntry toFileEntry = null;

if (toFileEntryId > 0) {
	try {
		toFileEntry = DLAppLocalServiceUtil.getFileEntry(toFileEntryId);

		toFileEntry = toFileEntry.toEscapedModel();

		toFolder = toFileEntry.getFolder();

		toGroupId = toFolder.getRepositoryId();

		toGroup = GroupLocalServiceUtil.getGroup(toGroupId);

		toGroup = toGroup.toEscapedModel();
	}
	catch (Exception e) {
	}
}

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("struts_action", strutsAction);
portletURL.setParameter("tabs2", tabs2);
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("fileShortcutId", String.valueOf(fileShortcutId));
%>

<liferay-util:include page="/html/portlet/document_library/top_links.jsp" />

<portlet:actionURL var="editFileShortcutURL">
	<portlet:param name="struts_action" value="/document_library/edit_file_shortcut" />
</portlet:actionURL>

<aui:form action="<%= editFileShortcutURL %>" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveFileShortcut();" %>'>
	<aui:input name="<%= Constants.CMD %>" type="hidden" />
	<aui:input name="tabs2" type="hidden" value="<%= tabs2 %>" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="fileShortcutId" type="hidden" value="<%= fileShortcutId %>" />
	<aui:input name="repositoryId" type="hidden" value="<%= repositoryId %>" />
	<aui:input name="folderId" type="hidden" value="<%= folderId %>" />
	<aui:input name="toGroupId" type="hidden" value="<%= toGroupId %>" />
	<aui:input name="toFileEntryId" type="hidden" value="<%= toFileEntryId %>" />

	<liferay-ui:header
		backURL="<%= redirect %>"
		title='<%= (fileShortcut != null)? LanguageUtil.format(pageContext, "shortcut-to-x", fileShortcut.getToTitle()) : "new-file-shortcut" %>'
	/>

	<liferay-ui:error exception="<%= FileShortcutPermissionException.class %>" message="you-do-not-have-permission-to-create-a-shortcut-to-the-selected-document" />
	<liferay-ui:error exception="<%= NoSuchFileEntryException.class %>" message="the-document-could-not-be-found" />

	<aui:fieldset>
		<div class="portlet-msg-info">
			<liferay-ui:message key="you-can-create-a-shortcut-to-any-document-that-you-have-read-access-for" />
		</div>

		<aui:field-wrapper label="site">

			<%
			String toGroupName = BeanPropertiesUtil.getString(toGroup, "name");
			%>

			<span id="<portlet:namespace />toGroupName">
			<%= toGroupName %>
			</span>

			<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>" var="selectGroupURL">
				<portlet:param name="struts_action" value="/document_library/select_group" />
			</portlet:renderURL>

			<%
			String taglibOpenGroupWindow = "var toGroupWindow = window.open('" + selectGroupURL + "','toGroup', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680'); void(''); toGroupWindow.focus();";
			%>

			<aui:button onClick='<%= taglibOpenGroupWindow %>' value="select" />
		</aui:field-wrapper>

		<aui:field-wrapper label="document">

			<%
			String toFileEntryTitle = BeanPropertiesUtil.getString(toFileEntry, "title");
			%>

			<span id="<portlet:namespace />toFileEntryTitle">
			<%= toFileEntryTitle %>
			</span>

			<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>" var="selectFileEntryURL">
				<portlet:param name="struts_action" value="/document_library/select_file_entry" />
			</portlet:renderURL>

			<%
			String taglibOpenFileEntryWindow = "var toFileEntryWindow = window.open(" + renderResponse.getNamespace() + "createSelectFileEntryURL('" + selectFileEntryURL.toString() + "'),'toGroup', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680'); void(''); toFileEntryWindow.focus();";
			%>

			<aui:button disabled="<%= (toGroup == null) %>" name="selectToFileEntryButton" onClick='<%= taglibOpenFileEntryWindow %>' value="select" />
		</aui:field-wrapper>

		<c:if test="<%= fileShortcut == null %>">
			<aui:field-wrapper label="permissions">
				<liferay-ui:input-permissions
					modelName="<%= DLFileShortcut.class.getName() %>"
				/>
			</aui:field-wrapper>
		</c:if>

		<aui:button-row>
			<aui:button type="submit" />

			<aui:button href="<%= redirect %>" type="cancel" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>

<aui:script>
	function <portlet:namespace />createSelectFileEntryURL(url) {
		url += '&<portlet:namespace />groupId=' + document.<portlet:namespace />fm.<portlet:namespace />toGroupId.value;
		url += '&<portlet:namespace />fileEntryId=' + document.<portlet:namespace />fm.<portlet:namespace />toFileEntryId.value;

		return url;
	}

	function <portlet:namespace />saveFileShortcut() {
		document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = "<%= (fileShortcut == null) ? Constants.ADD : Constants.UPDATE %>";
		submitForm(document.<portlet:namespace />fm);
	}

	function <portlet:namespace />selectFileEntry(fileEntryId, title) {
		document.<portlet:namespace />fm.<portlet:namespace />toFileEntryId.value = fileEntryId;

		var titleEl = document.getElementById("<portlet:namespace />toFileEntryTitle");

		if (title != "") {
			title += "&nbsp;";
		}

		titleEl.innerHTML = title;
	}

	Liferay.provide(
		window,
		'<portlet:namespace />selectGroup',
		function(groupId, groupName) {
			var A = AUI();

			if (document.<portlet:namespace />fm.<portlet:namespace />toGroupId.value != groupId) {
				<portlet:namespace />selectFileEntry("", "");
			}

			document.<portlet:namespace />fm.<portlet:namespace />toGroupId.value = groupId;
			document.<portlet:namespace />fm.<portlet:namespace />toFileEntryId.value = 0;

			var nameEl = document.getElementById("<portlet:namespace />toGroupName");

			nameEl.innerHTML = groupName + "&nbsp;";

			var button = A.one('#<portlet:namespace />selectToFileEntryButton');

			if (button) {
				button.set('disabled', false);
				button.ancestor('.aui-button').removeClass('aui-button-disabled');
			}
		},
		['aui-base']
	);
</aui:script>

<%
if (fileShortcut != null) {
	DLUtil.addPortletBreadcrumbEntries(fileShortcut, request, renderResponse);

	PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "edit"), currentURL);
}
else {
	DLUtil.addPortletBreadcrumbEntries(folderId, request, renderResponse);

	PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "add-file-shortcut"), currentURL);
}
%>