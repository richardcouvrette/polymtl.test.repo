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

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.io.ByteArrayFileInputStream;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upload.UploadException;
import com.liferay.portal.kernel.upload.UploadServletRequest;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.SystemProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PrefsPropsUtil;
import com.liferay.portal.util.WebKeys;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * @author Brian Wing Shun Chan
 * @author Zongliang Li
 * @author Harry Mark
 * @author Raymond Augé
 */
public class UploadServletRequestImpl
	extends HttpServletRequestWrapper implements UploadServletRequest {

	public static File getTempDir() throws SystemException {
		if (_tempDir == null) {
			_tempDir = new File(
				PrefsPropsUtil.getString(
					PropsKeys.UPLOAD_SERVLET_REQUEST_IMPL_TEMP_DIR,
					SystemProperties.get(SystemProperties.TMP_DIR)));
		}

		return _tempDir;
	}

	public static void setTempDir(File tempDir) {
		_tempDir = tempDir;
	}

	public UploadServletRequestImpl(HttpServletRequest request) {
		super(request);

		_params = new LinkedHashMap<String, LiferayFileItem[]>();

		try {
			ServletFileUpload servletFileUpload = new LiferayFileUpload(
				new LiferayFileItemFactory(getTempDir()), request);

			servletFileUpload.setSizeMax(
				PrefsPropsUtil.getLong(
					PropsKeys.UPLOAD_SERVLET_REQUEST_IMPL_MAX_SIZE));

			_liferayServletRequest = new LiferayServletRequest(request);

			List<LiferayFileItem> liferayFileItemsList =
				servletFileUpload.parseRequest(_liferayServletRequest);

			for (LiferayFileItem liferayFileItem : liferayFileItemsList) {
				if (liferayFileItem.isFormField()) {
					liferayFileItem.setString(request.getCharacterEncoding());
				}

				LiferayFileItem[] liferayFileItems = _params.get(
					liferayFileItem.getFieldName());

				if (liferayFileItems == null) {
					liferayFileItems = new LiferayFileItem[] {liferayFileItem};
				}
				else {
					LiferayFileItem[] newLiferayFileItems =
						new LiferayFileItem[liferayFileItems.length + 1];

					System.arraycopy(
						liferayFileItems, 0, newLiferayFileItems, 0,
						liferayFileItems.length);

					newLiferayFileItems[newLiferayFileItems.length - 1] =
						liferayFileItem;

					liferayFileItems = newLiferayFileItems;
				}

				_params.put(liferayFileItem.getFieldName(), liferayFileItems);
			}
		}
		catch (Exception e) {
			UploadException uploadException = new UploadException(e);

			if (e instanceof FileUploadBase.FileSizeLimitExceededException ||
				e instanceof FileUploadBase.SizeLimitExceededException ) {

				uploadException.setExceededSizeLimit(true);
			}

			request.setAttribute(WebKeys.UPLOAD_EXCEPTION, uploadException);

			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	public void cleanUp() {
		if ((_params != null) && !_params.isEmpty()) {
			for (LiferayFileItem[] liferayFileItems : _params.values()) {
				for (LiferayFileItem liferayFileItem : liferayFileItems) {
					liferayFileItem.delete();
				}
			}
		}
	}

	public String getContentType(String name) {
		LiferayFileItem[] liferayFileItems = _params.get(name);

		if ((liferayFileItems != null) && (liferayFileItems.length > 0)) {
			LiferayFileItem liferayFileItem = liferayFileItems[0];

			return liferayFileItem.getContentType();
		}
		else {
			return null;
		}
	}

	public File getFile(String name) {
		return getFile(name, false);
	}

	public File getFile(String name, boolean forceCreate) {
		if (getFileName(name) == null) {
			return null;
		}

		LiferayFileItem[] liferayFileItems = _params.get(name);

		File file = null;

		if ((liferayFileItems != null) && (liferayFileItems.length > 0)) {
			LiferayFileItem liferayFileItem = liferayFileItems[0];

			file = liferayFileItem.getStoreLocation();

			if (liferayFileItem.isInMemory() && forceCreate) {
				try {
					FileUtil.write(file, liferayFileItem.getInputStream());
				}
				catch (IOException ioe) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							"Unable to write temporary file " +
								file.getAbsolutePath(),
							ioe);
					}
				}
			}
		}

		return file;
	}

	public InputStream getFileAsStream(String name) throws IOException {
		return getFileAsStream(name, true);
	}

	public InputStream getFileAsStream(String name, boolean deleteOnClose)
		throws IOException {

		if (getFileName(name) == null) {
			return null;
		}

		InputStream inputStream = null;

		LiferayFileItem[] liferayFileItems = _params.get(name);

		if ((liferayFileItems != null) && (liferayFileItems.length > 0)) {
			LiferayFileItem liferayFileItem = liferayFileItems[0];

			inputStream = getInputStream(liferayFileItem, deleteOnClose);
		}

		return inputStream;
	}

	public String getFileName(String name) {
		LiferayFileItem[] liferayFileItems = _params.get(name);

		if ((liferayFileItems != null) && (liferayFileItems.length > 0)) {
			LiferayFileItem liferayFileItem = liferayFileItems[0];

			return liferayFileItem.getFileName();
		}
		else {
			return null;
		}
	}

	public String[] getFileNames(String name) {
		LiferayFileItem[] liferayFileItems = _params.get(name);

		if ((liferayFileItems != null) && (liferayFileItems.length > 0)) {
			String[] fileNames = new String[liferayFileItems.length];

			for (int i = 0; i < liferayFileItems.length; i++) {
				LiferayFileItem liferayFileItem = liferayFileItems[i];

				fileNames[i] = liferayFileItem.getFileName();
			}

			return fileNames;
		}
		else {
			return null;
		}
	}

	public File[] getFiles(String name) {
		String[] fileNames = getFileNames(name);

		if (fileNames == null) {
			return null;
		}

		LiferayFileItem[] liferayFileItems = _params.get(name);

		if ((liferayFileItems != null) && (liferayFileItems.length > 0)) {
			File[] files = new File[liferayFileItems.length];

			for (int i = 0; i < liferayFileItems.length; i++) {
				LiferayFileItem liferayFileItem = liferayFileItems[i];

				if (Validator.isNotNull(liferayFileItem.getFileName())) {
					files[i] = liferayFileItem.getStoreLocation();
				}
			}

			return files;
		}
		else {
			return null;
		}
	}

	public InputStream[] getFilesAsStream(String name) throws IOException {
		return getFilesAsStream(name, true);
	}

	public InputStream[] getFilesAsStream(String name, boolean deleteOnClose)
		throws IOException {

		String[] fileNames = getFileNames(name);

		if (fileNames == null) {
			return null;
		}

		InputStream[] inputStreams = null;

		LiferayFileItem[] liferayFileItems = _params.get(name);

		if ((liferayFileItems != null) && (liferayFileItems.length > 0)) {
			inputStreams = new InputStream[liferayFileItems.length];

			for (int i = 0; i < liferayFileItems.length; i++) {
				LiferayFileItem liferayFileItem = liferayFileItems[i];

				if (Validator.isNotNull(liferayFileItem.getFileName())) {
					inputStreams[i] = getInputStream(
						liferayFileItem, deleteOnClose);
				}
			}
		}

		return inputStreams;
	}

	public String getFullFileName(String name) {
		LiferayFileItem[] liferayFileItems = _params.get(name);

		if ((liferayFileItems != null) && (liferayFileItems.length > 0)) {
			LiferayFileItem liferayFileItem = liferayFileItems[0];

			return liferayFileItem.getFullFileName();
		}
		else {
			return null;
		}
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		return _liferayServletRequest.getInputStream();
	}

	public Map<String, LiferayFileItem[]> getMultipartParameterMap() {
		return _params;
	}

	@Override
	public String getParameter(String name) {
		LiferayFileItem[] liferayFileItems = _params.get(name);

		if ((liferayFileItems != null) && (liferayFileItems.length > 0)) {
			LiferayFileItem liferayFileItem = liferayFileItems[0];

			File storeLocationFile = liferayFileItem.getStoreLocation();

			if (storeLocationFile.length() > LiferayFileItem.THRESHOLD_SIZE) {
				_liferayServletRequest.setAttribute(
					WebKeys.FILE_ITEM_THRESHOLD_SIZE_EXCEEDED, Boolean.TRUE);

				return liferayFileItem.getEncodedString();
			}

			return liferayFileItem.getString();
		}
		else {
			return super.getParameter(name);
		}
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
		Set<String> parameterNames = new LinkedHashSet<String>();

		Enumeration<String> enu = super.getParameterNames();

		while (enu.hasMoreElements()) {
			String name = enu.nextElement();

			if (!_params.containsKey(name)) {
				parameterNames.add(name);
			}
		}

		parameterNames.addAll(_params.keySet());

		return Collections.enumeration(parameterNames);
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] parentValues = super.getParameterValues(name);

		LiferayFileItem[] liferayFileItems = _params.get(name);

		if ((liferayFileItems == null) || (liferayFileItems.length == 0)) {
			return parentValues;
		}
		else if ((parentValues == null) || (parentValues.length == 0)) {
			String[] values = new String[liferayFileItems.length];

			for (int i = 0; i < values.length; i++) {
				LiferayFileItem liferayFileItem = liferayFileItems[i];

				values[i] = liferayFileItem.getString();
			}

			return values;
		}
		else {
			String[] values = new String[
				parentValues.length + liferayFileItems.length];

			System.arraycopy(parentValues, 0, values, 0, parentValues.length);

			for (int i = parentValues.length; i < values.length; i++) {
				values[i] =
					liferayFileItems[i - parentValues.length].getString();
			}

			return values;
		}
	}

	public Long getSize(String name) {
		LiferayFileItem[] liferayFileItems = _params.get(name);

		if ((liferayFileItems != null) && (liferayFileItems.length > 0)) {
			LiferayFileItem liferayFileItem = liferayFileItems[0];

			return new Long(liferayFileItem.getSize());
		}
		else {
			return null;
		}
	}

	public Boolean isFormField(String name) {
		LiferayFileItem[] liferayFileItems = _params.get(name);

		if ((liferayFileItems != null) && (liferayFileItems.length > 0)) {
			LiferayFileItem liferayFileItem = liferayFileItems[0];

			return new Boolean(liferayFileItem.isFormField());
		}
		else {
			return null;
		}
	}

	protected InputStream getInputStream(
			LiferayFileItem liferayFileItem, boolean deleteOnClose)
		throws IOException {

		InputStream inputStream = null;

		if (liferayFileItem.isInMemory() && (liferayFileItem.getSize() > 0)) {
			inputStream = liferayFileItem.getInputStream();
		}
		else if (!liferayFileItem.isInMemory()) {
			inputStream = new ByteArrayFileInputStream(
				liferayFileItem.getStoreLocation(),
				liferayFileItem.getSizeThreshold(), deleteOnClose);
		}

		return inputStream;
	}

	private static Log _log = LogFactoryUtil.getLog(
		UploadServletRequestImpl.class);

	private static File _tempDir;

	private LiferayServletRequest _liferayServletRequest;
	private Map<String, LiferayFileItem[]> _params;

}