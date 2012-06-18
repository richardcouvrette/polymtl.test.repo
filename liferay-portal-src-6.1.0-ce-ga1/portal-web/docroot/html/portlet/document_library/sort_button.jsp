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
long folderId = GetterUtil.getLong((String)request.getAttribute("view.jsp-folderId"));

String orderByCol = ParamUtil.getString(request, "orderByCol");
String orderByType = ParamUtil.getString(request, "orderByType");

String reverseOrderByType = "asc";

if (orderByType.equals("asc")) {
	reverseOrderByType = "desc";
}
%>

<liferay-ui:icon-menu align="left" direction="down" icon="" message="sort-by" showExpanded="<%= false %>" showWhenSingleIcon="<%= false %>">

	<%
	String taglibUrl = "javascript:" + liferayPortletResponse.getNamespace() + "sortEntries('" + folderId + "', 'title','" + reverseOrderByType + "')";
	%>

	<liferay-ui:icon
		message="title"
		url="<%= taglibUrl %>"
	/>

	<%
	taglibUrl = "javascript:" + liferayPortletResponse.getNamespace() + "sortEntries('" + folderId + "', 'creationDate','" + reverseOrderByType + "')";
	%>

	<liferay-ui:icon
		message="create-date"
		url="<%= taglibUrl %>"
	/>

	<%
	taglibUrl = "javascript:" + liferayPortletResponse.getNamespace() + "sortEntries('" + folderId + "', 'modifiedDate','" + reverseOrderByType + "')";
	%>

	<liferay-ui:icon
		message="modified-date"
		url="<%= taglibUrl %>"
	/>

	<%
	taglibUrl = "javascript:" + liferayPortletResponse.getNamespace() + "sortEntries('" + folderId + "', 'downloads','" + reverseOrderByType + "')";
	%>

	<liferay-ui:icon
		message="downloads"
		url="<%= taglibUrl %>"
	/>

	<%
	taglibUrl = "javascript:" + liferayPortletResponse.getNamespace() + "sortEntries('" + folderId + "', 'readCount','" + reverseOrderByType + "')";
	%>

	<liferay-ui:icon
		message="read-count"
		url="<%= taglibUrl %>"
	/>

	<%
	taglibUrl = "javascript:" + liferayPortletResponse.getNamespace() + "sortEntries('" + folderId + "', 'size','" + reverseOrderByType + "')";
	%>

	<liferay-ui:icon
		message="size"
		url="<%= taglibUrl %>"
	/>
</liferay-ui:icon-menu>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />sortEntries',
		function(folderId, orderByCol, reverseOrderByType) {
			Liferay.fire(
				'<portlet:namespace />dataRequest',
				{
					requestParams: {
						'<portlet:namespace />folderId': folderId,
						'<portlet:namespace />struts_action': '/document_library/view',
						'<portlet:namespace />viewEntries': <%= Boolean.TRUE.toString() %>,
						'<portlet:namespace />orderByCol': orderByCol,
						'<portlet:namespace />orderByType': reverseOrderByType
					}
				}
			);
		},
		['aui-base']
	);
</aui:script>