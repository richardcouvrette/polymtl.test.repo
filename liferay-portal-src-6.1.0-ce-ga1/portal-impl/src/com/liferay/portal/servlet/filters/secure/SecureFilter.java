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

package com.liferay.portal.servlet.filters.secure;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.servlet.ProtectedServletRequest;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.servlet.filters.BasePortalFilter;
import com.liferay.portal.util.Portal;
import com.liferay.portal.util.PortalInstances;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PropsUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.portal.util.WebKeys;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 * @author Alexander Chow
 */
public class SecureFilter extends BasePortalFilter {

	@Override
	public void init(FilterConfig filterConfig) {
		super.init(filterConfig);

		_basicAuthEnabled = GetterUtil.getBoolean(
			filterConfig.getInitParameter("basic_auth"));
		_digestAuthEnabled = GetterUtil.getBoolean(
			filterConfig.getInitParameter("digest_auth"));

		String propertyPrefix =
			filterConfig.getInitParameter("portal_property_prefix");

		String[] hostsAllowedArray = null;

		if (Validator.isNull(propertyPrefix)) {
			hostsAllowedArray = StringUtil.split(
				filterConfig.getInitParameter("hosts.allowed"));
			_httpsRequired = GetterUtil.getBoolean(
				filterConfig.getInitParameter("https.required"));
		}
		else {
			hostsAllowedArray = PropsUtil.getArray(
				propertyPrefix + "hosts.allowed");
			_httpsRequired = GetterUtil.getBoolean(
				PropsUtil.get(propertyPrefix + "https.required"));
		}

		for (int i = 0; i < hostsAllowedArray.length; i++) {
			_hostsAllowed.add(hostsAllowedArray[i]);
		}
	}

	protected boolean isAccessAllowed(HttpServletRequest request) {
		if (_hostsAllowed.isEmpty()) {
			return true;
		}

		String remoteAddr = request.getRemoteAddr();

		if (_hostsAllowed.contains(remoteAddr)) {
			return true;
		}

		String computerAddress = PortalUtil.getComputerAddress();

		if (computerAddress.equals(remoteAddr) &&
			_hostsAllowed.contains(_SERVER_IP)) {

			return true;
		}

		return false;
	}

	@Override
	protected void processFilter(
			HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain)
		throws Exception {

		String remoteAddr = request.getRemoteAddr();

		if (isAccessAllowed(request)) {
			if (_log.isDebugEnabled()) {
				_log.debug("Access allowed for " + remoteAddr);
			}
		}
		else {
			if (_log.isWarnEnabled()) {
				_log.warn("Access denied for " + remoteAddr);
			}

			response.sendError(
				HttpServletResponse.SC_FORBIDDEN,
				"Access denied for " + remoteAddr);

			return;
		}

		if (_log.isDebugEnabled()) {
			if (_httpsRequired) {
				_log.debug("https is required");
			}
			else {
				_log.debug("https is not required");
			}
		}

		if (_httpsRequired && !request.isSecure()) {
			if (_log.isDebugEnabled()) {
				String completeURL = HttpUtil.getCompleteURL(request);

				_log.debug("Securing " + completeURL);
			}

			StringBundler redirectURL = new StringBundler(5);

			redirectURL.append(Http.HTTPS_WITH_SLASH);
			redirectURL.append(request.getServerName());
			redirectURL.append(request.getServletPath());

			String queryString = request.getQueryString();

			if (Validator.isNotNull(queryString)) {
				redirectURL.append(StringPool.QUESTION);
				redirectURL.append(request.getQueryString());
			}

			if (_log.isDebugEnabled()) {
				_log.debug("Redirect to " + redirectURL);
			}

			response.sendRedirect(redirectURL.toString());
		}
		else {
			if (_log.isDebugEnabled()) {
				String completeURL = HttpUtil.getCompleteURL(request);

				_log.debug("Not securing " + completeURL);
			}

			// This authentication should only be run if specified by web.xml
			// and JAAS is disabled. Make sure to run this once per session and
			// wrap the request if necessary.

			if (!PropsValues.PORTAL_JAAS_ENABLE) {
				User user = PortalUtil.getUser(request);

				if ((user != null) && !user.isDefaultUser()) {
					request = setCredentials(
						request, request.getSession(), user.getUserId());
				}
				else {
					if (_digestAuthEnabled) {
						request = digestAuth(request, response);
					}
					else if (_basicAuthEnabled) {
						request = basicAuth(request, response);
					}
				}
			}

			if (request != null) {
				processFilter(getClass(), request, response, filterChain);
			}
		}
	}

	protected HttpServletRequest basicAuth(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		HttpSession session = request.getSession();

		session.setAttribute(WebKeys.BASIC_AUTH_ENABLED, Boolean.TRUE);

		long userId = GetterUtil.getLong(
			(String)session.getAttribute(_AUTHENTICATED_USER));

		if (userId > 0) {
			request = new ProtectedServletRequest(
				request, String.valueOf(userId));
		}
		else {
			try {
				userId = PortalUtil.getBasicAuthUserId(request);
			}
			catch (Exception e) {
				_log.error(e, e);
			}

			if (userId > 0) {
				request = setCredentials(request, session, userId);
			}
			else {
				response.setHeader(HttpHeaders.WWW_AUTHENTICATE, _BASIC_REALM);
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

				return null;
			}
		}

		return request;
	}

	protected HttpServletRequest digestAuth(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		HttpSession session = request.getSession();

		long userId = GetterUtil.getLong(
			(String)session.getAttribute(_AUTHENTICATED_USER));

		if (userId > 0) {
			request = new ProtectedServletRequest(
				request, String.valueOf(userId));
		}
		else {
			try {
				userId = PortalUtil.getDigestAuthUserId(request);
			}
			catch (Exception e) {
				_log.error(e, e);
			}

			if (userId > 0) {
				request = setCredentials(request, session, userId);
			}
			else {

				// Must generate a new nonce for each 401 (RFC2617, 3.2.1)

				long companyId = PortalInstances.getCompanyId(request);

				String remoteAddress = request.getRemoteAddr();

				String nonce = NonceUtil.generate(companyId, remoteAddress);

				StringBundler sb = new StringBundler(4);

				sb.append(_DIGEST_REALM);
				sb.append(", nonce=\"");
				sb.append(nonce);
				sb.append("\"");

				response.setHeader(HttpHeaders.WWW_AUTHENTICATE, sb.toString());
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

				return null;
			}
		}

		return request;
	}

	protected HttpServletRequest setCredentials(
			HttpServletRequest request, HttpSession session, long userId)
		throws Exception {

		User user = UserLocalServiceUtil.getUser(userId);

		String userIdString = String.valueOf(userId);

		request = new ProtectedServletRequest(request, userIdString);

		session.setAttribute(WebKeys.USER, user);
		session.setAttribute(_AUTHENTICATED_USER, userIdString);

		if (_usePermissionChecker) {
			PrincipalThreadLocal.setName(userId);
			PrincipalThreadLocal.setPassword(
				PortalUtil.getUserPassword(request));

			PermissionChecker permissionChecker =
				PermissionCheckerFactoryUtil.create(user, false);

			PermissionThreadLocal.setPermissionChecker(permissionChecker);
		}

		return request;
	}

	protected void setUsePermissionChecker(boolean usePermissionChecker) {
		_usePermissionChecker = usePermissionChecker;
	}

	public static final String _AUTHENTICATED_USER =
		SecureFilter.class + "_AUTHENTICATED_USER";

	private static final String _BASIC_REALM =
		"Basic realm=\"" + Portal.PORTAL_REALM + "\"";

	private static final String _DIGEST_REALM =
		"Digest realm=\"" + Portal.PORTAL_REALM + "\"";

	private static final String _SERVER_IP = "SERVER_IP";

	private static Log _log = LogFactoryUtil.getLog(SecureFilter.class);

	private boolean _basicAuthEnabled;
	private boolean _digestAuthEnabled;
	private Set<String> _hostsAllowed = new HashSet<String>();
	private boolean _httpsRequired;
	private boolean _usePermissionChecker;

}