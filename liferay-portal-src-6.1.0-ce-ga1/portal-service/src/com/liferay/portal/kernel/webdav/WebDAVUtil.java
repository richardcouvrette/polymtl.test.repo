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

package com.liferay.portal.kernel.webdav;

import com.liferay.portal.NoSuchGroupException;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Namespace;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.comparator.GroupFriendlyURLComparator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 * @author Alexander Chow
 */
public class WebDAVUtil {

	public static final Namespace DAV_URI = SAXReaderUtil.createNamespace(
		"D", "DAV:");

	public static final int SC_MULTI_STATUS = 207;

	public static final int SC_LOCKED = 423;

	public static final String TOKEN_PREFIX = "opaquelocktoken:";

	public static void addStorage(WebDAVStorage storage) {
		_instance._addStorage(storage);
	}

	public static Namespace createNamespace(String prefix, String uri) {
		Namespace namespace = null;

		if (uri.equals(WebDAVUtil.DAV_URI.getURI())) {
			namespace = WebDAVUtil.DAV_URI;
		}
		else if (Validator.isNull(prefix)) {
			namespace = SAXReaderUtil.createNamespace(uri);
		}
		else {
			namespace = SAXReaderUtil.createNamespace(prefix, uri);
		}

		return namespace;
	}

	public static void deleteStorage(WebDAVStorage storage) {
		_instance._deleteStorage(storage);
	}

	public static long getDepth(HttpServletRequest request) {
		String value = GetterUtil.getString(request.getHeader("Depth"));

		if (_log.isDebugEnabled()) {
			_log.debug("\"Depth\" header is " + value);
		}

		if (value.equals("0")) {
			return 0;
		}
		else {
			return -1;
		}
	}

	public static String getDestination(
		HttpServletRequest request, String rootPath) {

		String headerDestination = request.getHeader("Destination");
		String[] pathSegments = StringUtil.split(headerDestination, rootPath);

		String destination = pathSegments[pathSegments.length - 1];

		destination = HttpUtil.decodePath(destination);

		if (_log.isDebugEnabled()) {
			_log.debug("Destination " + destination);
		}

		return destination;
	}

	public static long getGroupId(long companyId, String path)
		throws WebDAVException {

		String[] pathArray = getPathArray(path);

		return getGroupId(companyId, pathArray);
	}

	public static long getGroupId(long companyId, String[] pathArray)
		throws WebDAVException {

		try {
			if (pathArray.length == 0) {
				return 0;
			}

			String name = pathArray[0];

			try {
				Group group = GroupLocalServiceUtil.getGroup(companyId, name);

				return group.getGroupId();
			}
			catch (NoSuchGroupException nsge) {
			}

			try {
				Group group = GroupLocalServiceUtil.getFriendlyURLGroup(
					companyId, StringPool.SLASH + name);

				return group.getGroupId();
			}
			catch (NoSuchGroupException nsge) {
			}

			try {
				User user = UserLocalServiceUtil.getUserByScreenName(
					companyId, name);

				Group group = user.getGroup();

				return group.getGroupId();
			}
			catch (NoSuchUserException nsue) {
			}
		}
		catch (Exception e) {
			throw new WebDAVException(e);
		}

		return 0;
	}

	public static List<Group> getGroups(long userId) throws Exception {
		User user = UserLocalServiceUtil.getUser(userId);

		return getGroups(user);
	}

	public static List<Group> getGroups(User user) throws Exception {

		// Guest

		if (user.isDefaultUser()) {
			List<Group> groups = new ArrayList<Group>();

			Group group = GroupLocalServiceUtil.getGroup(
				user.getCompanyId(), GroupConstants.GUEST);

			groups.add(group);

			return groups;
		}

		// Communities

		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		params.put("usersGroups", user.getUserId());

		OrderByComparator orderByComparator = new GroupFriendlyURLComparator(
			true);

		List<Group> groups = GroupLocalServiceUtil.search(
			user.getCompanyId(), null, null, params, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, orderByComparator);

		// Organizations

		groups.addAll(
			GroupLocalServiceUtil.getUserOrganizationsGroups(
				user.getUserId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS));

		// User

		if (!user.isDefaultUser()) {
			groups.add(user.getGroup());
		}

		Collections.sort(groups, orderByComparator);

		return groups;
	}

	public static String getLockUuid(HttpServletRequest request)
		throws WebDAVException {

		String token = StringPool.BLANK;

		String value = GetterUtil.getString(request.getHeader("If"));

		if (_log.isDebugEnabled()) {
			_log.debug("\"If\" header is " + value);
		}

		if (value.contains("(<DAV:no-lock>)")) {
			if (_log.isWarnEnabled()) {
				_log.warn("Lock tokens can never be <DAV:no-lock>");
			}

			throw new WebDAVException();
		}

		int beg = value.indexOf(TOKEN_PREFIX);

		if (beg >= 0) {
			beg += TOKEN_PREFIX.length();

			if (beg < value.length()) {
				int end = value.indexOf(CharPool.GREATER_THAN, beg);

				token = GetterUtil.getString(value.substring(beg, end));
			}
		}

		return token;
	}

	public static String[] getPathArray(String path) {
		return getPathArray(path, false);
	}

	public static String[] getPathArray(String path, boolean fixTrailing) {
		path = HttpUtil.fixPath(path, true, fixTrailing);

		return StringUtil.split(path, CharPool.SLASH);
	}

	public static String getResourceName(String[] pathArray) {
		if (pathArray.length <= 2) {
			return StringPool.BLANK;
		}
		else {
			return pathArray[pathArray.length - 1];
		}
	}

	public static WebDAVStorage getStorage(String token) {
		return _instance._getStorage(token);
	}

	public static Collection<String> getStorageTokens() {
		return _instance._getStorageTokens();
	}

	public static long getTimeout(HttpServletRequest request) {
		final String TIME_PREFIX = "Second-";

		long timeout = 0;

		String value = GetterUtil.getString(request.getHeader("Timeout"));

		if (_log.isDebugEnabled()) {
			_log.debug("\"Timeout\" header is " + value);
		}

		int index = value.indexOf(TIME_PREFIX);

		if (index >= 0) {
			index += TIME_PREFIX.length();

			if (index < value.length()) {
				timeout = GetterUtil.getLong(value.substring(index));
			}
		}

		return timeout * Time.SECOND;
	}

	public static boolean isOverwrite(HttpServletRequest request) {
		return _instance._isOverwrite(request);
	}

	private WebDAVUtil() {
		_storageMap = new TreeMap<String, WebDAVStorage>();
	}

	private void _addStorage(WebDAVStorage storage) {
		_storageMap.put(storage.getToken(), storage);
	}

	private void _deleteStorage(WebDAVStorage storage) {
		if (storage != null) {
			_storageMap.remove(storage.getToken());
		}
	}

	private WebDAVStorage _getStorage(String token) {
		return _storageMap.get(token);
	}

	private Collection<String> _getStorageTokens() {
		return _storageMap.keySet();
	}

	private boolean _isOverwrite(HttpServletRequest request) {
		String value = GetterUtil.getString(request.getHeader("Overwrite"));

		if (value.equalsIgnoreCase("F") || !GetterUtil.getBoolean(value)) {
			return false;
		}
		else {
			return true;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(WebDAVUtil.class);

	private static WebDAVUtil _instance = new WebDAVUtil();

	private Map<String, WebDAVStorage> _storageMap;

}