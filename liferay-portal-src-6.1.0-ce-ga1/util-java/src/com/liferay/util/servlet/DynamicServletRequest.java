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

package com.liferay.util.servlet;

import com.liferay.portal.kernel.util.ArrayUtil;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author Brian Wing Shun Chan
 */
public class DynamicServletRequest extends HttpServletRequestWrapper {

	public DynamicServletRequest(HttpServletRequest request) {
		this(request, new HashMap<String, String[]>(), true);
	}

	public DynamicServletRequest(HttpServletRequest request, boolean inherit) {
		this(request, new HashMap<String, String[]>(), inherit);
	}

	public DynamicServletRequest(
		HttpServletRequest request, Map<String, String[]> params) {

		this(request, params, true);
	}

	public DynamicServletRequest(
		HttpServletRequest request, Map<String, String[]> params,
		boolean inherit) {

		super(request);

		_params = new HashMap<String, String[]>();
		_inherit = inherit;

		if (params != null) {
			for (Map.Entry<String, String[]> entry : params.entrySet()) {
				_params.put(entry.getKey(), entry.getValue());
			}
		}

		if (_inherit && (request instanceof DynamicServletRequest)) {
			DynamicServletRequest dynamicRequest =
				(DynamicServletRequest)request;

			setRequest(dynamicRequest.getRequest());

			params = dynamicRequest.getDynamicParameterMap();

			if (params != null) {
				for (Map.Entry<String, String[]> entry : params.entrySet()) {
					String name = entry.getKey();
					String[] oldValues = entry.getValue();

					String[] curValues = _params.get(name);

					if (curValues == null) {
						_params.put(name, oldValues);
					}
					else {
						String[] newValues = ArrayUtil.append(
							oldValues, curValues);

						_params.put(name, newValues);
					}
				}
			}
		}
	}

	public Map<String, String[]> getDynamicParameterMap() {
		return _params;
	}

	@Override
	public String getParameter(String name) {
		String[] values = _params.get(name);

		if (_inherit && (values == null)) {
			return super.getParameter(name);
		}

		if ((values != null) && (values.length > 0)) {
			return values[0];
		}
		else {
			return null;
		}
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		Map<String, String[]> map = new HashMap<String, String[]>();

		Enumeration<String> enu = getParameterNames();

		while (enu.hasMoreElements()) {
			String s = enu.nextElement();

			map.put(s, getParameterValues(s));
		}

		return map;
	}

	@Override
	public Enumeration<String> getParameterNames() {
		Set<String> names = new LinkedHashSet<String>();

		if (_inherit) {
			Enumeration<String> enu = super.getParameterNames();

			while (enu.hasMoreElements()) {
				names.add(enu.nextElement());
			}
		}

		names.addAll(_params.keySet());

		return Collections.enumeration(names);
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] values = _params.get(name);

		if (_inherit && (values == null)) {
			return super.getParameterValues(name);
		}

		return values;
	}

	public void setParameter(String name, String value) {
		_params.put(name, new String[] {value});
	}

	public void setParameterValues(String name, String[] values) {
		_params.put(name, values);
	}

	private boolean _inherit;
	private Map<String, String[]> _params;

}