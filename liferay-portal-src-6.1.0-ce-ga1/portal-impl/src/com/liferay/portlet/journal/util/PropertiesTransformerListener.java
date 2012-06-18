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

package com.liferay.portlet.journal.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.templateparser.BaseTransformerListener;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.PropertiesUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * @author Brian Wing Shun Chan
 */
public class PropertiesTransformerListener extends BaseTransformerListener {

	@Override
	public String onOutput(String s) {
		if (_log.isDebugEnabled()) {
			_log.debug("onOutput");
		}

		s = replace(s);

		return s;
	}

	@Override
	public String onScript(String s) {
		if (_log.isDebugEnabled()) {
			_log.debug("onScript");
		}

		s = replace(s);

		return s;
	}

	@Override
	public String onXml(String s) {
		if (_log.isDebugEnabled()) {
			_log.debug("onXml");
		}

		return s;
	}

	/**
	 * Replace the properties in a given string with their values fetched from
	 * the template GLOBAL-PROPERTIES.
	 *
	 * @return the processed string
	 */
	protected String replace(String s) {
		Map<String, String> tokens = getTokens();

		String templateId = tokens.get("template_id");

		if ((templateId == null) ||
			((templateId != null) && (templateId.equals(_GLOBAL_PROPERTIES)))) {

			// Return the original string if no template ID is specified or if
			// the template ID is GLOBAL-PROPERTIES to prevent an infinite loop.

			return s;
		}

		Properties properties = new Properties();

		try {
			Map<String, String> newTokens = new HashMap<String, String>();

			MapUtil.copy(tokens, newTokens);

			newTokens.put("template_id", _GLOBAL_PROPERTIES);

			long groupId = GetterUtil.getLong(tokens.get("group_id"));

			String script = JournalUtil.getTemplateScript(
				groupId, _GLOBAL_PROPERTIES, newTokens, getLanguageId());

			PropertiesUtil.load(properties, script);
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e);
			}
		}

		if (properties.isEmpty()) {
			return s;
		}

		String[] escapedKeys = new String[properties.size()];
		String[] escapedValues = new String[properties.size()];

		String[] keys = new String[properties.size()];
		String[] values = new String[properties.size()];

		String[] tempEscapedKeys = new String[properties.size()];
		String[] tempEscapedValues = new String[properties.size()];

		int counter = 0;

		Iterator<Map.Entry<Object, Object>> itr =
			properties.entrySet().iterator();

		while (itr.hasNext()) {
			Map.Entry<Object, Object> entry = itr.next();

			String key = (String)entry.getKey();
			String value = (String)entry.getValue();

			String escapedKey =
				StringPool.AT + StringPool.AT + key + StringPool.AT +
					StringPool.AT;

			String actualKey = StringPool.AT + key + StringPool.AT;

			String tempEscapedKey =
				TokensTransformerListener.TEMP_ESCAPED_AT_OPEN +
					key + TokensTransformerListener.TEMP_ESCAPED_AT_CLOSE;

			escapedKeys[counter] = escapedKey;
			escapedValues[counter] = tempEscapedKey;

			keys[counter] = actualKey;
			values[counter] = value;

			tempEscapedKeys[counter] = tempEscapedKey;
			tempEscapedValues[counter] = actualKey;

			counter++;
		}

		s = StringUtil.replace(s, escapedKeys, escapedValues);

		s = StringUtil.replace(s, keys, values);

		s = StringUtil.replace(s, tempEscapedKeys, tempEscapedValues);

		return s;
	}

	private static final String _GLOBAL_PROPERTIES = "GLOBAL-PROPERTIES";

	private static Log _log = LogFactoryUtil.getLog(
		PropertiesTransformerListener.class);

}