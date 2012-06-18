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
DDLRecordSet recordSet = (DDLRecordSet)request.getAttribute(WebKeys.DYNAMIC_DATA_LISTS_RECORD_SET);

long detailDDMTemplateId = ParamUtil.getLong(request, "detailDDMTemplateId");

boolean editable = ParamUtil.getBoolean(request, "editable", true);

if (portletName.equals(PortletKeys.DYNAMIC_DATA_LISTS)) {
	editable = true;
}

if (!DDLRecordSetPermission.contains(permissionChecker, recordSet.getRecordSetId(), ActionKeys.UPDATE)) {
	editable = false;
}

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("struts_action", "/dynamic_data_lists/view_record_set");
portletURL.setParameter("recordSetId", String.valueOf(recordSet.getRecordSetId()));

DDMStructure ddmStructure = recordSet.getDDMStructure(detailDDMTemplateId);

String languageId = LanguageUtil.getLanguageId(request);

Map<String, Map<String, String>> fieldsMap = ddmStructure.getFieldsMap(languageId);

List<String> headerNames = new ArrayList<String>();

for (Map<String, String> fields : fieldsMap.values()) {
	String label = fields.get(FieldConstants.LABEL);

	headerNames.add(label);
}

if (editable) {
	headerNames.add("status");
	headerNames.add("modified-date");
	headerNames.add("author");
	headerNames.add(StringPool.BLANK);
}

SearchContainer searchContainer = new SearchContainer(renderRequest, portletURL, headerNames, "no-records-were-found");

int status = WorkflowConstants.STATUS_APPROVED;

if (DDLRecordSetPermission.contains(permissionChecker, recordSet, ActionKeys.ADD_RECORD)) {
	status = WorkflowConstants.STATUS_ANY;
}

int total = DDLRecordLocalServiceUtil.getRecordsCount(recordSet.getRecordSetId(), status);

searchContainer.setTotal(total);

List<DDLRecord> results = DDLRecordLocalServiceUtil.getRecords(recordSet.getRecordSetId(), status, searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator());

searchContainer.setResults(results);

List resultRows = searchContainer.getResultRows();

for (int i = 0; i < results.size(); i++) {
	DDLRecord record = results.get(i);

	DDLRecordVersion recordVersion = record.getRecordVersion();

	if (editable) {
		recordVersion = record.getLatestRecordVersion();
	}

	Fields fieldsModel = StorageEngineUtil.getFields(recordVersion.getDDMStorageId());

	ResultRow row = new ResultRow(record, record.getRecordId(), i);

	row.setParameter("detailDDMTemplateId", String.valueOf(detailDDMTemplateId));
	row.setParameter("editable", String.valueOf(editable));

	PortletURL rowURL = renderResponse.createRenderURL();

	rowURL.setParameter("struts_action", "/dynamic_data_lists/view_record");
	rowURL.setParameter("redirect", currentURL);
	rowURL.setParameter("recordId", String.valueOf(record.getRecordId()));
	rowURL.setParameter("detailDDMTemplateId", String.valueOf(detailDDMTemplateId));
	rowURL.setParameter("editable", String.valueOf(editable));

	// Columns

	for (Map<String, String> fields : fieldsMap.values()) {
		String dataType = fields.get(FieldConstants.DATA_TYPE);
		String name = fields.get(FieldConstants.NAME);

		String value = null;

		if (fieldsModel.contains(name)) {
			com.liferay.portlet.dynamicdatamapping.storage.Field field = fieldsModel.get(name);

			value = field.getRenderedValue(themeDisplay);
		}
		else {
			value = StringPool.BLANK;
		}

		if (editable) {
			row.addText(value, rowURL);
		}
		else {
			row.addText(value);
		}
	}

	if (editable) {
		row.addText(LanguageUtil.get(pageContext, WorkflowConstants.toLabel(recordVersion.getStatus())), rowURL);
		row.addText(dateFormatDateTime.format(record.getModifiedDate()), rowURL);
		row.addText(HtmlUtil.escape(PortalUtil.getUserName(recordVersion.getUserId(), recordVersion.getUserName())), rowURL);

		// Action

		row.addJSP("right", SearchEntry.DEFAULT_VALIGN, "/html/portlet/dynamic_data_lists/record_action.jsp");
	}

	// Add result row

	resultRows.add(row);
}
%>

<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />