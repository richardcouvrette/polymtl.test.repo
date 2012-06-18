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

package com.liferay.portal.servlet.filters.sso.ntlm;

import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.cache.SingleVMPoolUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.BrowserSnifferUtil;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.auth.AuthSettingsUtil;
import com.liferay.portal.security.ntlm.NtlmManager;
import com.liferay.portal.security.ntlm.NtlmUserAccount;
import com.liferay.portal.servlet.filters.BasePortalFilter;
import com.liferay.portal.util.PortalInstances;
import com.liferay.portal.util.PrefsPropsUtil;
import com.liferay.portal.util.PropsUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.portal.util.WebKeys;

import java.security.SecureRandom;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jcifs.Config;

import jcifs.http.NtlmHttpFilter;

import jcifs.util.Base64;

/**
 * @author Bruno Farache
 * @author Marcus Schmidke
 * @author Brian Wing Shun Chan
 * @author Wesley Gong
 * @author Marcellus Tavares
 * @author Michael C. Han
 */
public class NtlmFilter extends BasePortalFilter {

	@Override
	public void init(FilterConfig filterConfig) {
		try {
			NtlmHttpFilter ntlmFilter = new NtlmHttpFilter();

			ntlmFilter.init(filterConfig);

			Properties properties = PropsUtil.getProperties("jcifs.", false);

			Iterator<Map.Entry<Object, Object>> itr =
				properties.entrySet().iterator();

			while (itr.hasNext()) {
				Map.Entry<Object, Object> entry = itr.next();

				String key = (String)entry.getKey();
				String value = (String)entry.getValue();

				Config.setProperty(key, value);
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	@Override
	public boolean isFilterEnabled(
		HttpServletRequest request, HttpServletResponse response) {

		try {
			long companyId = PortalInstances.getCompanyId(request);

			if (BrowserSnifferUtil.isIe(request) &&
				AuthSettingsUtil.isNtlmEnabled(companyId)) {

				return true;
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return false;
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	protected NtlmManager getNtlmManager(long companyId)
		throws SystemException {

		String domain = PrefsPropsUtil.getString(
			companyId, PropsKeys.NTLM_DOMAIN, PropsValues.NTLM_DOMAIN);
		String domainController = PrefsPropsUtil.getString(
			companyId, PropsKeys.NTLM_DOMAIN_CONTROLLER,
			PropsValues.NTLM_DOMAIN_CONTROLLER);
		String domainControllerName = PrefsPropsUtil.getString(
			companyId, PropsKeys.NTLM_DOMAIN_CONTROLLER_NAME,
			PropsValues.NTLM_DOMAIN_CONTROLLER_NAME);
		String serviceAccount = PrefsPropsUtil.getString(
			companyId, PropsKeys.NTLM_SERVICE_ACCOUNT,
			PropsValues.NTLM_SERVICE_ACCOUNT);
		String servicePassword = PrefsPropsUtil.getString(
			companyId, PropsKeys.NTLM_SERVICE_PASSWORD,
			PropsValues.NTLM_SERVICE_PASSWORD);

		NtlmManager ntlmManager = _ntlmManagers.get(companyId);

		if (ntlmManager == null) {
			ntlmManager = new NtlmManager(
				domain, domainController, domainControllerName, serviceAccount,
				servicePassword);

			_ntlmManagers.put(companyId, ntlmManager);
		}
		else {
			if (!Validator.equals(ntlmManager.getDomain(), domain) ||
				!Validator.equals(
					ntlmManager.getDomainController(), domainController) ||
				!Validator.equals(
					ntlmManager.getDomainControllerName(),
					domainControllerName) ||
				!Validator.equals(
					ntlmManager.getServiceAccount(), serviceAccount) ||
				!Validator.equals(
					 ntlmManager.getServicePassword(), servicePassword)) {

				ntlmManager.setConfiguration(
					domain, domainController, domainControllerName,
					serviceAccount, servicePassword);
			}
		}

		return ntlmManager;
	}

	@Override
	protected void processFilter(
			HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain)
		throws Exception {

		// Type 1 NTLM requests from browser can (and should) always immediately
		// be replied to with an Type 2 NTLM response, no matter whether we're
		// yet logging in or whether it is much later in the session.

		HttpSession session = request.getSession(false);

		long companyId = PortalInstances.getCompanyId(request);

		String authorization = GetterUtil.getString(
			request.getHeader(HttpHeaders.AUTHORIZATION));

		if (authorization.startsWith("NTLM")) {
			NtlmManager ntlmManager = getNtlmManager(companyId);

			byte[] src = Base64.decode(authorization.substring(5));

			if (src[8] == 1) {
				byte[] serverChallenge = new byte[8];

				_secureRandom.nextBytes(serverChallenge);

				byte[] challengeMessage = ntlmManager.negotiate(
					src, serverChallenge);

				authorization = Base64.encode(challengeMessage);

				response.setContentLength(0);
				response.setHeader(
					HttpHeaders.WWW_AUTHENTICATE, "NTLM " + authorization);
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

				response.flushBuffer();

				_portalCache.put(request.getRemoteAddr(), serverChallenge);

				// Interrupt filter chain, send response. Browser will
				// immediately post a new request.

				return;
			}

			byte[] serverChallenge = (byte[])_portalCache.get(
				request.getRemoteAddr());

			if (serverChallenge == null) {
				response.setContentLength(0);
				response.setHeader(HttpHeaders.WWW_AUTHENTICATE, "NTLM");
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

				response.flushBuffer();

				return;
			}

			NtlmUserAccount ntlmUserAccount = null;

			try {
				ntlmUserAccount = ntlmManager.authenticate(
					src, serverChallenge);
			}
			catch (Exception e) {
				if (_log.isErrorEnabled()) {
					_log.error("Unable to perform NTLM authentication", e);
				}
			}
			finally {
				_portalCache.remove(request.getRemoteAddr());
			}

			if (ntlmUserAccount == null) {
				response.setContentLength(0);
				response.setHeader(HttpHeaders.WWW_AUTHENTICATE, "NTLM");
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

				response.flushBuffer();

				return;
			}

			if (_log.isDebugEnabled()) {
				_log.debug("NTLM remote user " + ntlmUserAccount.getUserName());
			}

			request.setAttribute(
				WebKeys.NTLM_REMOTE_USER, ntlmUserAccount.getUserName());

			if (session != null) {
				session.setAttribute(
					WebKeys.NTLM_USER_ACCOUNT, ntlmUserAccount);
			}
		}

		String path = request.getPathInfo();

		if ((path != null) && path.endsWith("/login")) {
			NtlmUserAccount ntlmUserAccount = null;

			if (session != null) {
				ntlmUserAccount = (NtlmUserAccount)session.getAttribute(
					WebKeys.NTLM_USER_ACCOUNT);
			}

			if (ntlmUserAccount == null) {
				response.setContentLength(0);
				response.setHeader(HttpHeaders.WWW_AUTHENTICATE, "NTLM");
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

				response.flushBuffer();

				return;
			}
		}

		processFilter(NtlmPostFilter.class, request, response, filterChain);
	}

	private static Log _log = LogFactoryUtil.getLog(NtlmFilter.class);

	private Map<Long, NtlmManager> _ntlmManagers =
		new ConcurrentHashMap<Long, NtlmManager>();
	private PortalCache _portalCache = SingleVMPoolUtil.getCache(
		NtlmFilter.class.getName());
	private SecureRandom _secureRandom = new SecureRandom();

}