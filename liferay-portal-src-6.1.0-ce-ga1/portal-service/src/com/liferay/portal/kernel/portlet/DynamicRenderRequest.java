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

package com.liferay.portal.kernel.portlet;

import com.liferay.portal.kernel.util.ArrayUtil;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.portlet.RenderRequest;
import javax.portlet.filter.RenderRequestWrapper;

/**
 * @author Brian Wing Shun Chan
 */
public class DynamicRenderRequest extends RenderRequestWrapper {

	public DynamicRenderRequest(RenderRequest renderRequest) {
		this(renderRequest, new HashMap<String, String[]>(), true);
	}

	public DynamicRenderRequest(
		RenderRequest renderRequest, Map<String, String[]> params) {

		this(renderRequest, params, true);
	}

	public DynamicRenderRequest(RenderRequest renderRequest, boolean inherit) {
		this(renderRequest, new HashMap<String, String[]>(), inherit);
	}

	public DynamicRenderRequest(
		RenderRequest renderRequest, Map<String, String[]> params,
		boolean inherit) {

		super(renderRequest);

		_params = new HashMap<String, String[]>();
		_inherit = inherit;

		if (params != null) {
			for (Map.Entry<String, String[]> entry : params.entrySet()) {
				_params.put(entry.getKey(), entry.getValue());
			}
		}

		if (_inherit && (renderRequest instanceof DynamicRenderRequest)) {
			DynamicRenderRequest dynamicRenderRequest =
				(DynamicRenderRequest)renderRequest;

			setRequest(dynamicRenderRequest.getRequest());

			params = dynamicRenderRequest.getDynamicParameterMap();

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

	public Map<String, String[]> getDynamicParameterMap() {
		return _params;
	}

	private Map<String, String[]> _params;
	private boolean _inherit;

}