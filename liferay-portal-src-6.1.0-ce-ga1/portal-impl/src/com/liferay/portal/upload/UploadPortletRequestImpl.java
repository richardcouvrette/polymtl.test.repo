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

package com.liferay.portal.upload;

import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.upload.UploadServletRequest;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author Brian Wing Shun Chan
 * @author Harry Mark
 */
public class UploadPortletRequestImpl
	extends HttpServletRequestWrapper implements UploadPortletRequest {

	public UploadPortletRequestImpl(
		UploadServletRequest uploadServletRequest, String namespace) {

		super(uploadServletRequest);

		_uploadServletRequest = uploadServletRequest;
		_namespace = namespace;
	}

	public void cleanUp() {
		_uploadServletRequest.cleanUp();
	}

	public String getContentType(String name) {
		String contentType = _uploadServletRequest.getContentType(
			_namespace.concat(name));

		if (contentType == null) {
			contentType = _uploadServletRequest.getContentType(name);
		}

		if (Validator.isNull(contentType) ||
			contentType.equals(ContentTypes.APPLICATION_OCTET_STREAM)) {

			contentType = MimeTypesUtil.getContentType(getFile(name));
		}

		return contentType;
	}

	public File getFile(String name) {
		return getFile(name, false);
	}

	public File getFile(String name, boolean forceCreate) {
		File file = _uploadServletRequest.getFile(
			_namespace.concat(name), forceCreate);

		if (file == null) {
			file = _uploadServletRequest.getFile(name, forceCreate);
		}

		return file;
	}

	public InputStream getFileAsStream(String name) throws IOException {
		return getFileAsStream(name, true);
	}

	public InputStream getFileAsStream(String name, boolean deleteOnClose)
		throws IOException {

		InputStream inputStream = _uploadServletRequest.getFileAsStream(
			_namespace.concat(name), deleteOnClose);

		if (inputStream == null) {
			inputStream = _uploadServletRequest.getFileAsStream(
				name, deleteOnClose);
		}

		return inputStream;
	}

	public String getFileName(String name) {
		String fileName = _uploadServletRequest.getFileName(
			_namespace.concat(name));

		if (fileName == null) {
			fileName = _uploadServletRequest.getFileName(name);
		}

		return fileName;
	}

	public String[] getFileNames(String name) {
		String[] fileNames = _uploadServletRequest.getFileNames(
			_namespace.concat(name));

		if (fileNames == null) {
			fileNames = _uploadServletRequest.getFileNames(name);
		}

		return fileNames;
	}

	public File[] getFiles(String name) {
		File[] files = _uploadServletRequest.getFiles(_namespace.concat(name));

		if (files == null) {
			files = _uploadServletRequest.getFiles(name);
		}

		return files;
	}

	public InputStream[] getFilesAsStream(String name) throws IOException {
		return getFilesAsStream(name, true);
	}

	public InputStream[] getFilesAsStream(String name, boolean deleteOnClose)
		throws IOException {

		InputStream[] inputStreams = _uploadServletRequest.getFilesAsStream(
			_namespace.concat(name), deleteOnClose);

		if (inputStreams == null) {
			inputStreams = _uploadServletRequest.getFilesAsStream(
				name, deleteOnClose);
		}

		return inputStreams;
	}

	public String getFullFileName(String name) {
		String fullFileName = _uploadServletRequest.getFullFileName(
			_namespace.concat(name));

		if (fullFileName == null) {
			fullFileName = _uploadServletRequest.getFullFileName(name);
		}

		return fullFileName;
	}

	@Override
	public String getParameter(String name) {
		String parameter = _uploadServletRequest.getParameter(
			_namespace.concat(name));

		if (parameter == null) {
			parameter = _uploadServletRequest.getParameter(name);
		}

		return parameter;
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		Map<String, String[]> map = new HashMap<String, String[]>();

		Enumeration<String> enu = getParameterNames();

		while (enu.hasMoreElements()) {
			String name = enu.nextElement();

			map.put(name, getParameterValues(name));
		}

		return map;
	}

	@Override
	public Enumeration<String> getParameterNames() {
		List<String> parameterNames = new ArrayList<String>();

		Enumeration<String> enu = _uploadServletRequest.getParameterNames();

		while (enu.hasMoreElements()) {
			String name = enu.nextElement();

			if (name.startsWith(_namespace)) {
				parameterNames.add(
					name.substring(_namespace.length(), name.length()));
			}
			else {
				parameterNames.add(name);
			}
		}

		return Collections.enumeration(parameterNames);
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] parameterValues = _uploadServletRequest.getParameterValues(
			_namespace.concat(name));

		if (parameterValues == null) {
			parameterValues = _uploadServletRequest.getParameterValues(name);
		}

		return parameterValues;
	}

	public long getSize(String name) {
		Long size = _uploadServletRequest.getSize(_namespace.concat(name));

		if (size == null) {
			size = _uploadServletRequest.getSize(name);
		}

		if (size == null) {
			return 0;
		}

		return size;
	}

	public boolean isFormField(String name) {
		Boolean formField = _uploadServletRequest.isFormField(
			_namespace.concat(name));

		if (formField == null) {
			formField = _uploadServletRequest.isFormField(name);
		}

		if (formField == null) {
			return true;
		}
		else {
			return formField.booleanValue();
		}
	}

	private String _namespace;
	private UploadServletRequest _uploadServletRequest;

}