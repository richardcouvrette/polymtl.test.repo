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

package com.liferay.portal.spring.context;

import javax.servlet.ServletContext;

import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.ContextLoader;

/**
 * @author Brian Wing Shun Chan
 * @see    PortletApplicationContext
 * @see    PortletContextLoaderListener
 */
public class PortletContextLoader extends ContextLoader {

	public static final String PORTAL_CONFIG_LOCATION_PARAM =
		"portalContextConfigLocation";

	@Override
	protected void customizeContext(
		ServletContext servletContext,
		ConfigurableWebApplicationContext applicationContext) {

		String configLocation = servletContext.getInitParameter(
			PORTAL_CONFIG_LOCATION_PARAM);

		applicationContext.setConfigLocation(configLocation);
	}

	@Override
	protected Class<?> determineContextClass(ServletContext servletContext) {
		return PortletApplicationContext.class;
	}

}