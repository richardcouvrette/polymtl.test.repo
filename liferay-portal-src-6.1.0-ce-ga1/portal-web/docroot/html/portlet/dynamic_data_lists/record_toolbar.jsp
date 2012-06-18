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

<%@ include file="/html/portlet/dynamic_data_lists/init.jsp" %>

<%
DDLRecord record = (DDLRecord)request.getAttribute(WebKeys.DYNAMIC_DATA_LISTS_RECORD);

long detailDDMTemplateId = ParamUtil.getLong(request, "detailDDMTemplateId");
%>

<div class="record-toolbar" id="<portlet:namespace />recordToolbar"></div>

<aui:script use="aui-toolbar,aui-dialog-iframe,liferay-util-window">
	var permissionPopUp = null;

	new A.Toolbar(
		{
			activeState: false,
			boundingBox: '#<portlet:namespace />recordToolbar',
			children: [
				<c:if test="<%= record != null %>">
					<portlet:renderURL var="viewHistoryURL">
						<portlet:param name="struts_action" value="/dynamic_data_lists/view_record_history" />
						<portlet:param name="backURL" value="<%= currentURL %>" />
						<portlet:param name="recordId" value="<%= String.valueOf(record.getRecordId()) %>" />
						<portlet:param name="detailDDMTemplateId" value="<%= String.valueOf(detailDDMTemplateId) %>" />
					</portlet:renderURL>

					{
						handler: function (event) {
							window.location = '<%= viewHistoryURL %>';
						},
						icon: 'clock',
						label: '<%= UnicodeLanguageUtil.get(pageContext, "view-history") %>'
					}
				</c:if>
			]
		}
	).render();
</aui:script>