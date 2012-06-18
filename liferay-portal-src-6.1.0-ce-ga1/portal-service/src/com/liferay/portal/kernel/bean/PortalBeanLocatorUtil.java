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

package com.liferay.portal.kernel.bean;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class PortalBeanLocatorUtil {

	public static BeanLocator getBeanLocator() {
		return _beanLocator;
	}

	public static Object locate(String name) throws BeanLocatorException {
		if (_beanLocator == null) {
			_log.error("BeanLocator is null");

			throw new BeanLocatorException("BeanLocator has not been set");
		}
		else {
			Thread currentThread = Thread.currentThread();

			ClassLoader contextClassLoader =
				currentThread.getContextClassLoader();

			ClassLoader beanClassLoader = _beanLocator.getClassLoader();

			try {
				if (contextClassLoader != beanClassLoader) {
					currentThread.setContextClassLoader(beanClassLoader);
				}

				return _beanLocator.locate(name);
			}
			finally {
				if (contextClassLoader != beanClassLoader) {
					currentThread.setContextClassLoader(contextClassLoader);
				}
			}
		}
	}

	public static void setBeanLocator(BeanLocator beanLocator) {
		if (_log.isDebugEnabled()) {
			_log.debug("Setting BeanLocator " + beanLocator.hashCode());
		}

		_beanLocator = beanLocator;
	}

	private static Log _log = LogFactoryUtil.getLog(
		PortalBeanLocatorUtil.class);

	private static BeanLocator _beanLocator;

}