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

package com.liferay.portlet.dynamicdatalists.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.dynamicdatalists.model.DDLRecord;
import com.liferay.portlet.dynamicdatalists.model.DDLRecordSet;
import com.liferay.portlet.dynamicdatamapping.storage.Fields;

import java.io.InputStream;

import java.util.List;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Eduardo Lundgren
 */
public interface DDL {

	public void addAllReservedEls(
		Element rootElement, Map<String, String> tokens,
		DDLRecordSet recordSet);

	public Fields getFields(
			UploadPortletRequest uploadPortletRequest, long ddmStructureId)
		throws PortalException, SystemException;

	public Fields getFields(
			UploadPortletRequest uploadPortletRequest, long ddmStructureId,
			long ddmTemplateId)
		throws PortalException, SystemException;

	public String getRecordFileUploadPath(DDLRecord record);

	public JSONObject getRecordJSONObject(DDLRecord record) throws Exception;

	public JSONObject getRecordJSONObject(
			DDLRecord record, boolean latestRecordVersion)
		throws Exception;

	public JSONArray getRecordSetJSONArray(DDLRecordSet recordSet)
		throws Exception;

	public JSONArray getRecordsJSONArray(DDLRecordSet recordSet)
		throws Exception;

	public JSONArray getRecordsJSONArray(List<DDLRecord> records)
		throws Exception;

	public JSONArray getRecordsJSONArray(
			List<DDLRecord> records, boolean latestRecordVersion)
		throws Exception;

	public String getTemplateContent(
			long ddmTemplateId, DDLRecordSet recordSet,
			ThemeDisplay themeDisplay, RenderRequest renderRequest,
			RenderResponse renderResponse)
		throws Exception;

	public void sendRecordFileUpload(
			HttpServletRequest request, HttpServletResponse response,
			DDLRecord record, String fieldName)
		throws Exception;

	public void sendRecordFileUpload(
			HttpServletRequest request, HttpServletResponse response,
			long recordId, String fieldName)
		throws Exception;

	public String storeRecordFieldFile(
			DDLRecord record, String fieldName, InputStream inputStream)
		throws Exception;

	public DDLRecord updateRecord(
			UploadPortletRequest uploadPortletRequest, long recordId,
			long recordSetId, boolean mergeFields)
		throws Exception;

	public DDLRecord updateRecord(
			UploadPortletRequest uploadPortletRequest, long recordId,
			long recordSetId, boolean mergeFields, boolean checkPermission)
		throws Exception;

	public void uploadRecordFieldFile(
			DDLRecord record, String fieldName,
			UploadPortletRequest uploadPortletRequest,
			ServiceContext serviceContext)
		throws Exception;

	public void uploadRecordFieldFiles(
			DDLRecord record, UploadPortletRequest uploadPortletRequest,
			ServiceContext serviceContext)
		throws Exception;

}