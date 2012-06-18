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

package com.liferay.portal.kernel.util;

import com.liferay.portal.kernel.test.TestCase;

import java.net.URLEncoder;

/**
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class URLCodecTest extends TestCase {

	public void testDecodeURL() throws Exception {
		for (int i = 0; i < _RAW_URLS.length; i++) {
			String result = URLCodec.decodeURL(
				_ENCODED_URLS[i], StringPool.UTF8, false);

			assertEquals(_RAW_URLS[i], result);

			result = URLCodec.decodeURL(
				_ESCAPE_SPACES_ENCODED_URLS[i], StringPool.UTF8, true);

			assertEquals(_RAW_URLS[i], result);
		}
	}

	public void testEncodeURL() throws Exception {
		for (int i = 0; i < _RAW_URLS.length; i++) {
			String result = URLCodec.encodeURL(
				_RAW_URLS[i], StringPool.UTF8, false);

			assertTrue(_ENCODED_URLS[i].equalsIgnoreCase(result));

			result = URLCodec.encodeURL(_RAW_URLS[i], StringPool.UTF8, true);

			assertTrue(_ESCAPE_SPACES_ENCODED_URLS[i].equalsIgnoreCase(result));
		}
	}

	private static final String[] _ENCODED_URLS = new String[8];

	private static final String[] _ESCAPE_SPACES_ENCODED_URLS = new String[8];

	private static final String[] _RAW_URLS = {
		"abcdefghijklmnopqrstuvwxyz", "ABCDEFGHIJKLMNOPQRSTUVWXYZ",
		"0123456789", ".-*_", " ", "~`!@#$%^&()+={[}]|\\:;\"'<,>?/", "中文测试",
		"/abc/def"
	};

	static {
		try {
			for (int i = 0; i < _RAW_URLS.length; i++) {
				_ENCODED_URLS[i] = URLEncoder.encode(
					_RAW_URLS[i], StringPool.UTF8);

				_ESCAPE_SPACES_ENCODED_URLS[i] = StringUtil.replace(
					_ENCODED_URLS[i], StringPool.PLUS, "%20");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}