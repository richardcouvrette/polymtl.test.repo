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

package com.liferay.portal.servlet.filters.virtualhost;

import com.liferay.portal.LayoutFriendlyURLException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.struts.LastPath;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.model.impl.LayoutImpl;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.servlet.I18nServlet;
import com.liferay.portal.servlet.filters.BasePortalFilter;
import com.liferay.portal.util.PortalInstances;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.portal.util.WebKeys;
import com.liferay.portal.webserver.WebServerServlet;

import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * This filter is used to provide virtual host functionality.
 * </p>
 *
 * @author Joel Kozikowski
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 * @author Eduardo Lundgren
 */
public class VirtualHostFilter extends BasePortalFilter {

	@Override
	public void init(FilterConfig filterConfig) {
		super.init(filterConfig);

		_servletContext = filterConfig.getServletContext();

		_slashedKeywords =
			new String[PropsValues.LAYOUT_FRIENDLY_URL_KEYWORDS.length];

		for (int i = 0; i < PropsValues.LAYOUT_FRIENDLY_URL_KEYWORDS.length;
				i++) {

			String keyword = PropsValues.LAYOUT_FRIENDLY_URL_KEYWORDS[i];

			if (keyword.contains(StringPool.PERIOD) ||
				keyword.equals("_vti_") || keyword.equals("display_chart") ||
				keyword.equals("software_catalog")) {

				keyword = StringPool.SLASH + keyword;
			}
			else {
				keyword = StringPool.SLASH + keyword + StringPool.SLASH;
			}

			_slashedKeywords[i] = keyword.toLowerCase();
		}
	}

	@Override
	public boolean isFilterEnabled(
		HttpServletRequest request, HttpServletResponse response) {

		StringBuffer requestURL = request.getRequestURL();

		if (isValidRequestURL(requestURL)) {
			return true;
		}
		else {
			return false;
		}
	}

	protected boolean isValidFriendlyURL(String friendlyURL) {
		friendlyURL = friendlyURL.toLowerCase();

		if (PortalInstances.isVirtualHostsIgnorePath(friendlyURL) ||
			friendlyURL.startsWith(_PRIVATE_GROUP_SERVLET_MAPPING_SLASH) ||
			friendlyURL.startsWith(_PRIVATE_USER_SERVLET_MAPPING_SLASH) ||
			friendlyURL.startsWith(_PUBLIC_GROUP_SERVLET_MAPPING_SLASH)) {

			return false;
		}

		for (String keyword : _slashedKeywords) {
			if (friendlyURL.startsWith(keyword)) {
				return false;
			}
		}

		int code = LayoutImpl.validateFriendlyURL(friendlyURL);

		if ((code > -1) &&
			(code != LayoutFriendlyURLException.ENDS_WITH_SLASH)) {

			return false;
		}

		return true;
	}

	protected boolean isValidRequestURL(StringBuffer requestURL) {
		if (requestURL == null) {
			return false;
		}

		String url = requestURL.toString();

		for (String extension : PropsValues.VIRTUAL_HOSTS_IGNORE_EXTENSIONS) {
			if (url.endsWith(extension)) {
				return false;
			}
		}

		return true;
	}

	@Override
	protected void processFilter(
			HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain)
		throws Exception {

		long companyId = PortalInstances.getCompanyId(request);

		String contextPath = PortalUtil.getPathContext();

		String originalFriendlyURL = request.getRequestURI();

		String friendlyURL = originalFriendlyURL;

		if ((Validator.isNotNull(contextPath)) &&
			(friendlyURL.indexOf(contextPath) != -1)) {

			friendlyURL = friendlyURL.substring(contextPath.length());
		}

		friendlyURL = StringUtil.replace(
			friendlyURL, StringPool.DOUBLE_SLASH, StringPool.SLASH);

		String i18nLanguageId = null;

		Set<String> languageIds = I18nServlet.getLanguageIds();

		for (String languageId : languageIds) {
			if (StringUtil.startsWith(friendlyURL, languageId)) {
				int pos = friendlyURL.indexOf(CharPool.SLASH, 1);

				if (((pos != -1) && (pos != languageId.length())) ||
					((pos == -1) &&
					 !friendlyURL.equalsIgnoreCase(languageId))) {

					continue;
				}

				if (pos == -1) {
					i18nLanguageId = languageId;
					friendlyURL = StringPool.SLASH;
				}
				else {
					i18nLanguageId = languageId.substring(0, pos);
					friendlyURL = friendlyURL.substring(pos);
				}

				break;
			}
		}

		friendlyURL = StringUtil.replace(
			friendlyURL, PropsValues.WIDGET_SERVLET_MAPPING, StringPool.BLANK);

		if (_log.isDebugEnabled()) {
			_log.debug("Friendly URL " + friendlyURL);
		}

		if (!friendlyURL.equals(StringPool.SLASH) &&
			!isValidFriendlyURL(friendlyURL)) {

			_log.debug("Friendly URL is not valid");

			processFilter(
				VirtualHostFilter.class, request, response, filterChain);

			return;
		}
		else if (friendlyURL.startsWith(_PATH_DOCUMENTS)) {
			if (WebServerServlet.hasFiles(request)) {
				processFilter(
					VirtualHostFilter.class, request, response, filterChain);

				return;
			}
		}

		LayoutSet layoutSet = (LayoutSet)request.getAttribute(
			WebKeys.VIRTUAL_HOST_LAYOUT_SET);

		if (_log.isDebugEnabled()) {
			_log.debug("Layout set " + layoutSet);
		}

		if (layoutSet == null) {
			processFilter(
				VirtualHostFilter.class, request, response, filterChain);

			return;
		}

		try {
			LastPath lastPath = new LastPath(
				contextPath, friendlyURL, request.getParameterMap());

			request.setAttribute(WebKeys.LAST_PATH, lastPath);

			StringBundler forwardURL = new StringBundler(5);

			if (i18nLanguageId != null) {
				forwardURL.append(i18nLanguageId);
			}

			if (originalFriendlyURL.startsWith(
					PropsValues.WIDGET_SERVLET_MAPPING)) {

				forwardURL.append(PropsValues.WIDGET_SERVLET_MAPPING);

				friendlyURL = StringUtil.replaceFirst(
					friendlyURL, PropsValues.WIDGET_SERVLET_MAPPING,
					StringPool.BLANK);
			}

			long plid = PortalUtil.getPlidFromFriendlyURL(
				companyId, friendlyURL);

			if (plid <= 0) {
				Group group = GroupLocalServiceUtil.getGroup(
					layoutSet.getGroupId());

				if (group.isGuest() && friendlyURL.equals(StringPool.SLASH)) {
					String homeURL = PortalUtil.getRelativeHomeURL(request);

					if (Validator.isNotNull(homeURL)) {
						friendlyURL = homeURL;
					}
				}
				else {
					if (layoutSet.isPrivateLayout()) {
						if (group.isUser()) {
							forwardURL.append(_PRIVATE_USER_SERVLET_MAPPING);
						}
						else {
							forwardURL.append(_PRIVATE_GROUP_SERVLET_MAPPING);
						}
					}
					else {
						forwardURL.append(_PUBLIC_GROUP_SERVLET_MAPPING);
					}

					forwardURL.append(group.getFriendlyURL());
				}
			}

			forwardURL.append(friendlyURL);

			if (_log.isDebugEnabled()) {
				_log.debug("Forward to " + forwardURL);
			}

			RequestDispatcher requestDispatcher =
				_servletContext.getRequestDispatcher(forwardURL.toString());

			requestDispatcher.forward(request, response);
		}
		catch (Exception e) {
			_log.error(e, e);

			processFilter(
				VirtualHostFilter.class, request, response, filterChain);
		}
	}

	private static final String _PATH_DOCUMENTS = "/documents/";

	private static final String _PRIVATE_GROUP_SERVLET_MAPPING =
		PropsValues.LAYOUT_FRIENDLY_URL_PRIVATE_GROUP_SERVLET_MAPPING;

	private static final String _PRIVATE_GROUP_SERVLET_MAPPING_SLASH =
		_PRIVATE_GROUP_SERVLET_MAPPING + StringPool.SLASH;

	private static final String _PRIVATE_USER_SERVLET_MAPPING =
		PropsValues.LAYOUT_FRIENDLY_URL_PRIVATE_USER_SERVLET_MAPPING;

	private static final String _PRIVATE_USER_SERVLET_MAPPING_SLASH =
		_PRIVATE_USER_SERVLET_MAPPING + StringPool.SLASH;

	private static final String _PUBLIC_GROUP_SERVLET_MAPPING =
		PropsValues.LAYOUT_FRIENDLY_URL_PUBLIC_SERVLET_MAPPING;

	private static final String _PUBLIC_GROUP_SERVLET_MAPPING_SLASH =
		_PUBLIC_GROUP_SERVLET_MAPPING + StringPool.SLASH;

	private static Log _log = LogFactoryUtil.getLog(VirtualHostFilter.class);

	private ServletContext _servletContext;
	private String[] _slashedKeywords;

}