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

package com.liferay.portal.bean;

import java.lang.Object;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Brian Wing Shun Chan
 */
public class VelocityBeanHandler implements InvocationHandler {

	public VelocityBeanHandler(Object bean, ClassLoader classLoader) {
		_bean = bean;
		_classLoader = classLoader;
	}

	public Object getBean() {
		return _bean;
	}

	public ClassLoader getClassLoader() {
		return _classLoader;
	}

	public Object invoke(Object proxy, Method method, Object[] args)
		throws Throwable {

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			if ((_classLoader != null) &&
				(contextClassLoader != _classLoader)) {

				currentThread.setContextClassLoader(_classLoader);
			}

			return method.invoke(_bean, args);
		}
		catch (InvocationTargetException ite) {
			return null;
		}
		finally {
			if ((_classLoader != null) &&
				(contextClassLoader != _classLoader)) {

				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	private Object _bean;
	private ClassLoader _classLoader;

}