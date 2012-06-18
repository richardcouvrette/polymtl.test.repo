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

<%@ include file="/html/portlet/bookmarks/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");
String backURL = ParamUtil.getString(request, "backURL");

String referringPortletResource = ParamUtil.getString(request, "referringPortletResource");

BookmarksEntry entry = (BookmarksEntry)request.getAttribute(WebKeys.BOOKMARKS_ENTRY);

long entryId = BeanParamUtil.getLong(entry, request, "entryId");

long folderId = BeanParamUtil.getLong(entry, request, "folderId");
%>

<c:if test="<%= Validator.isNull(referringPortletResource) %>">
	<liferay-util:include page="/html/portlet/bookmarks/top_links.jsp" />
</c:if>

<portlet:actionURL var="editEntryURL">
	<portlet:param name="struts_action" value="/bookmarks/edit_entry" />
</portlet:actionURL>

<aui:form action="<%= editEntryURL %>" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveEntry();" %>'>
	<aui:input name="<%= Constants.CMD %>" type="hidden" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
	<aui:input name="referringPortletResource" type="hidden" value="<%= referringPortletResource %>" />
	<aui:input name="entryId" type="hidden" value="<%= entryId %>" />
	<aui:input name="folderId" type="hidden" value="<%= folderId %>" />

	<liferay-ui:header
		backURL="<%= backURL %>"
		localizeTitle="<%= (entry == null) %>"
		title='<%= (entry == null) ? "new-bookmark" : entry.getName() %>'
	/>

	<liferay-ui:error exception="<%= EntryURLException.class %>" message="please-enter-a-valid-url" />
	<liferay-ui:error exception="<%= NoSuchFolderException.class %>" message="please-enter-a-valid-folder" />

	<liferay-ui:asset-categories-error />

	<liferay-ui:asset-tags-error />

	<aui:model-context bean="<%= entry %>" model="<%= BookmarksEntry.class %>" />

	<aui:fieldset>
		<c:if test="<%= ((entry != null) || (folderId <= 0) || Validator.isNotNull(referringPortletResource)) %>">
			<aui:field-wrapper label="folder">

					<%
					String folderName = StringPool.BLANK;

					if (folderId > 0) {
						BookmarksFolder folder = BookmarksFolderServiceUtil.getFolder(folderId);

						folderId = folder.getFolderId();
						folderName = folder.getName();
					}
					%>

					<portlet:renderURL var="viewFolderURL">
						<portlet:param name="struts_action" value="/bookmarks/view" />
						<portlet:param name="folderId" value="<%= String.valueOf(folderId) %>" />
					</portlet:renderURL>

					<aui:a href="<%= viewFolderURL %>" id="folderName"><%= folderName %></aui:a>

					<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>" var="selectFolderURL">
						<portlet:param name="struts_action" value="/bookmarks/select_folder" />
						<portlet:param name="folderId" value="<%= String.valueOf(folderId) %>" />
					</portlet:renderURL>

					<%
					String taglibOpenFolderWindow = "var folderWindow = window.open('" + selectFolderURL + "','folder', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680'); void(''); folderWindow.focus();";
					%>

					<aui:button onClick="<%= taglibOpenFolderWindow %>" value="select" />

					<%
					String taglibRemoveFolder = "Liferay.Util.removeFolderSelection('folderId', 'folderName', '" + renderResponse.getNamespace() + "');";
					%>

					<aui:button name="removeFolderButton" onClick="<%= taglibRemoveFolder %>" value="remove" />
			</aui:field-wrapper>
		</c:if>

		<aui:input name="name" />

		<aui:input name="url" />

		<aui:input name="description" />

		<liferay-ui:custom-attributes-available className="<%= BookmarksEntry.class.getName() %>">
			<liferay-ui:custom-attribute-list
				className="<%= BookmarksEntry.class.getName() %>"
				classPK="<%= entryId %>"
				editable="<%= true %>"
				label="<%= true %>"
			/>
		</liferay-ui:custom-attributes-available>

		<c:if test="<%= entry == null %>">
			<aui:field-wrapper label="permissions">
				<liferay-ui:input-permissions
					modelName="<%= BookmarksEntry.class.getName() %>"
				/>
			</aui:field-wrapper>
		</c:if>

		<liferay-ui:panel defaultState="closed" extended="<%= false %>" id="bookmarksEntryCategorizationPanel" persistState="<%= true %>" title="categorization">
			<aui:input name="categories" type="assetCategories" />

			<aui:input name="tags" type="assetTags" />
		</liferay-ui:panel>

		<liferay-ui:panel defaultState="closed" extended="<%= false %>" id="bookmarksEntryAssetLinksPanel" persistState="<%= true %>" title="related-assets">
			<aui:fieldset>
				<liferay-ui:input-asset-links
					className="<%= BookmarksEntry.class.getName() %>"
					classPK="<%= entryId %>"
				/>
			</aui:fieldset>
		</liferay-ui:panel>
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>

<aui:script>
	function <portlet:namespace />saveEntry() {
		document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = "<%= (entry == null) ? Constants.ADD : Constants.UPDATE %>";
		submitForm(document.<portlet:namespace />fm);
	}

	function <portlet:namespace />selectFolder(folderId, folderName) {
		var folderData = {
			idString: 'folderId',
			idValue: folderId,
			nameString: 'folderName',
			nameValue: folderName
		};

		Liferay.Util.selectFolder(folderData, '<portlet:renderURL><portlet:param name="struts_action" value="/bookmarks/view" /></portlet:renderURL>', '<portlet:namespace />');
	}

	<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />name);
	</c:if>
</aui:script>

<%
if (entry != null) {
	BookmarksUtil.addPortletBreadcrumbEntries(entry, request, renderResponse);

	PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "edit"), currentURL);
}
else {
	BookmarksUtil.addPortletBreadcrumbEntries(folderId, request, renderResponse);

	PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "add-entry"), currentURL);
}
%>