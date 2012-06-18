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

package com.liferay.portlet.dynamicdatamapping.util;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;
import com.liferay.portlet.dynamicdatamapping.storage.Fields;

import java.util.Locale;

import javax.servlet.jsp.PageContext;

/**
 * @author Eduardo Lundgren
 * @author Brian Wing Shun Chan
 */
public class DDMXSDUtil {

	public static DDMXSD getDDMXSD() {
		return _ddmXSD;
	}

	public static String getHTML(
			PageContext pageContext, DDMStructure ddmStructure, Fields fields,
			String namespace, boolean readOnly, Locale locale)
		throws Exception {

		return getDDMXSD().getHTML(
			pageContext, ddmStructure, fields, namespace, readOnly, locale);
	}

	public static String getHTML(
			PageContext pageContext, DDMTemplate ddmTemplate, Fields fields,
			String namespace, boolean readOnly, Locale locale)
		throws Exception {

		return getDDMXSD().getHTML(
			pageContext, ddmTemplate, fields, namespace, readOnly, locale);
	}

	public static String getHTML(
			PageContext pageContext, String xml, Fields fields, Locale locale)
		throws Exception {

		return getDDMXSD().getHTML(pageContext, xml, fields, locale);
	}

	public static String getHTML(
			PageContext pageContext, String xml, Fields fields,
			String namespace, boolean readOnly, Locale locale)
		throws Exception {

		return getDDMXSD().getHTML(
			pageContext, xml, fields, namespace, readOnly, locale);
	}

	public static String getHTML(
			PageContext pageContext, String xml, Fields fields,
			String namespace, Locale locale)
		throws Exception {

		return getDDMXSD().getHTML(pageContext, xml, fields, namespace, locale);
	}

	public static String getHTML(
			PageContext pageContext, String xml, Locale locale)
		throws Exception {

		return getDDMXSD().getHTML(pageContext, xml, locale);
	}

	public static JSONArray getJSONArray(Document document)
		throws JSONException {

		return getDDMXSD().getJSONArray(document);
	}

	public static JSONArray getJSONArray(String xml)
		throws DocumentException, JSONException {

		return getDDMXSD().getJSONArray(xml);
	}

	public void setDDMXSD(DDMXSD ddmXSD) {
		_ddmXSD = ddmXSD;
	}

	private static DDMXSD _ddmXSD;

}