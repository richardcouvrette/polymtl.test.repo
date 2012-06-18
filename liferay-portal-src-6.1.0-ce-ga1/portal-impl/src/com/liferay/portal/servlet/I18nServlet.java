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

package com.liferay.portal.servlet;

import com.liferay.portal.NoSuchLayoutException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.WebKeys;

import java.io.IOException;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Brian Wing Shun Chan
 */
public class I18nServlet extends HttpServlet {

	public static Set<String> getLanguageIds() {
		return _languageIds;
	}

	public static void setLanguageIds(Element root) {
		Iterator<Element> itr = root.elements("servlet-mapping").iterator();

		while (itr.hasNext()) {
			Element el = itr.next();

			String servletName = el.elementText("servlet-name");

			if (servletName.equals("I18n Servlet")) {
				String urlPattern = el.elementText("url-pattern");

				String languageId = urlPattern.substring(
					0, urlPattern.lastIndexOf(CharPool.SLASH));

				_languageIds.add(languageId);
			}
		}

		_languageIds = Collections.unmodifiableSet(_languageIds);
	}

	@Override
	public void service(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		try {
			String[] i18nData = getI18nData(request);

			if ((i18nData == null) ||
				!PortalUtil.isValidResourceId(i18nData[2])) {

				PortalUtil.sendError(
					HttpServletResponse.SC_NOT_FOUND,
					new NoSuchLayoutException(), request, response);
			}
			else {
				String i18nLanguageId = i18nData[0];
				String i18nPath = i18nData[1];
				String redirect = i18nData[2];

				request.setAttribute(WebKeys.I18N_LANGUAGE_ID, i18nLanguageId);
				request.setAttribute(WebKeys.I18N_PATH, i18nPath);

				ServletContext servletContext = getServletContext();

				RequestDispatcher requestDispatcher =
					servletContext.getRequestDispatcher(redirect);

				requestDispatcher.forward(request, response);
			}
		}
		catch (Exception e) {
			_log.error(e, e);

			PortalUtil.sendError(
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e, request,
				response);
		}
	}

	protected String[] getI18nData(HttpServletRequest request) {
		String path = request.getRequestURI();

		String contextPath = request.getContextPath();
		String servletPath = request.getServletPath();

		path = path.substring(contextPath.length() + servletPath.length());

		if (Validator.isNull(path)) {
			return null;
		}

		String i18nLanguageId = servletPath;

		int pos = i18nLanguageId.lastIndexOf(CharPool.SLASH);

		i18nLanguageId = i18nLanguageId.substring(pos + 1);

		if (_log.isDebugEnabled()) {
			_log.debug("Language ID " + i18nLanguageId);
		}

		if (Validator.isNull(i18nLanguageId)) {
			return null;
		}

		String i18nPath = StringPool.SLASH + i18nLanguageId;

		Locale locale = LocaleUtil.fromLanguageId(i18nLanguageId);

		if (Validator.isNull(locale.getCountry())) {

			// Locales must contain the country code

			locale = LanguageUtil.getLocale(locale.getLanguage());

			i18nLanguageId = LocaleUtil.toLanguageId(locale);
		}

		String redirect = path;

		if (_log.isDebugEnabled()) {
			_log.debug("Redirect " + redirect);
		}

		return new String[] {i18nLanguageId, i18nPath, redirect};
	}

	private static Log _log = LogFactoryUtil.getLog(I18nServlet.class);

	private static Set<String> _languageIds = new HashSet<String>();

}