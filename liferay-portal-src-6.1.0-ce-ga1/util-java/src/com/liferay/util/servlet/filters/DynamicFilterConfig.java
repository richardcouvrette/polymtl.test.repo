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

package com.liferay.util.servlet.filters;

import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;

/**
 * @author Brian Wing Shun Chan
 * @author Bruno Farache
 */
public class DynamicFilterConfig implements FilterConfig {

	public DynamicFilterConfig(FilterConfig filterConfig) {
		Enumeration<String> enu = filterConfig.getInitParameterNames();

		while (enu.hasMoreElements()) {
			String name = enu.nextElement();

			addInitParameter(name, filterConfig.getInitParameter(name));
		}
	}

	public DynamicFilterConfig(
		String filterName, ServletContext servletContext) {

		_filterName = filterName;
		_servletContext = servletContext;
	}

	public String getFilterName() {
		return _filterName;
	}

	public ServletContext getServletContext() {
		return _servletContext;
	}

	public void addInitParameter(String name, String value) {
		_parameters.put(name, value);
	}

	public String getInitParameter(String name) {
		return _parameters.get(name);
	}

	public Enumeration<String> getInitParameterNames() {
		return Collections.enumeration(_parameters.keySet());
	}

	private String _filterName;
	private ServletContext _servletContext;
	private Map<String, String> _parameters =
		new LinkedHashMap<String, String>();

}