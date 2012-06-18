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

package com.liferay.portal.webdav;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.webdav.WebDAVException;
import com.liferay.portal.kernel.webdav.WebDAVRequest;
import com.liferay.portal.kernel.webdav.WebDAVStorage;
import com.liferay.portal.kernel.webdav.WebDAVUtil;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.util.PortalUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Brian Wing Shun Chan
 */
public class WebDAVRequestImpl implements WebDAVRequest {

	public WebDAVRequestImpl(
			WebDAVStorage storage, HttpServletRequest request,
			HttpServletResponse response, String userAgent,
			PermissionChecker permissionChecker)
		throws WebDAVException {

		_storage = storage;
		_request = request;
		_response = response;
		_userAgent = userAgent;
		_lockUuid = WebDAVUtil.getLockUuid(request);
		_path = HttpUtil.fixPath(_request.getPathInfo(), false, true);
		_companyId = PortalUtil.getCompanyId(request);
		_groupId = WebDAVUtil.getGroupId(_companyId, _path);
		_userId = GetterUtil.getLong(_request.getRemoteUser());
		_permissionChecker = permissionChecker;
	}

	public WebDAVStorage getWebDAVStorage() {
		return _storage;
	}

	public HttpServletRequest getHttpServletRequest() {
		return _request;
	}

	public HttpServletResponse getHttpServletResponse() {
		return _response;
	}

	public String getRootPath() {
		return _storage.getRootPath();
	}

	public String getPath() {
		return _path;
	}

	public String[] getPathArray() {
		return WebDAVUtil.getPathArray(_path);
	}

	public long getCompanyId() {
		return _companyId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public long getUserId() {
		return _userId;
	}

	public String getLockUuid() {
		return _lockUuid;
	}

	public PermissionChecker getPermissionChecker() {
		return _permissionChecker;
	}

	public boolean isAppleDoubleRequest() {
		String[] pathArray = getPathArray();

		String name = WebDAVUtil.getResourceName(pathArray);

		if (isMac() && name.startsWith(_APPLE_DOUBLE_PREFIX)) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isLitmus() {
		return _userAgent.contains("litmus");
	}

	public boolean isMac() {
		return _userAgent.contains("WebDAVFS");
	}

	public boolean isWindows() {
		return _userAgent.contains(
			"Microsoft Data Access Internet Publishing Provider");
	}

	public static final String _APPLE_DOUBLE_PREFIX = "._";

	private WebDAVStorage _storage;
	private HttpServletRequest _request;
	private HttpServletResponse _response;
	private String _userAgent;
	private String _path = StringPool.BLANK;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _lockUuid;
	private PermissionChecker _permissionChecker;

}