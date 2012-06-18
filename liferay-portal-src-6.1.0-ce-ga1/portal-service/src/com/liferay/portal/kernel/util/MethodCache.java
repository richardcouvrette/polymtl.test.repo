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

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public class MethodCache {

	public static Method get(String className, String methodName)
		throws ClassNotFoundException, NoSuchMethodException {

		return get(null, null, className, methodName);
	}

	public static Method get(
			String className, String methodName, Class<?>[] parameterTypes)
		throws ClassNotFoundException, NoSuchMethodException {

		return get(null, null, className, methodName, parameterTypes);
	}

	public static Method get(
			Map<String, Class<?>> classesMap, Map<MethodKey, Method> methodsMap,
			String className, String methodName)
		throws ClassNotFoundException, NoSuchMethodException {

		return get(className, methodName, new Class[0]);
	}

	public static Method get(
			Map<String, Class<?>> classesMap, Map<MethodKey, Method> methodsMap,
			String className, String methodName, Class<?>[] parameterTypes)
		throws ClassNotFoundException, NoSuchMethodException {

		MethodKey methodKey = new MethodKey(
			className, methodName, parameterTypes);

		return _instance._get(classesMap, methodsMap, methodKey);
	}

	public static Method get(MethodKey methodKey)
		throws ClassNotFoundException, NoSuchMethodException {

		return _instance._get(null, null, methodKey);
	}

	public static Method put(MethodKey methodKey, Method method) {
		return _instance._put(methodKey, method);
	}

	public static void remove(Class<?> clazz) {
		_instance._remove(clazz);
	}

	public static void reset() {
		_instance._reset();
	}

	private MethodCache() {
		_classesMap = new HashMap<String, Class<?>>();
		_methodsMap = new HashMap<MethodKey, Method>();
	}

	private Method _get(
			Map<String, Class<?>> classesMap, Map<MethodKey, Method> methodsMap,
			MethodKey methodKey)
		throws ClassNotFoundException, NoSuchMethodException {

		if (classesMap == null) {
			classesMap = _classesMap;
		}

		if (methodsMap == null) {
			methodsMap = _methodsMap;
		}

		Method method = methodsMap.get(methodKey);

		if (method == null) {
			String className = methodKey.getClassName();
			String methodName = methodKey.getMethodName();
			Class<?>[] parameterTypes = methodKey.getParameterTypes();

			Class<?> clazz = classesMap.get(className);

			if (clazz == null) {
				Thread currentThread = Thread.currentThread();

				ClassLoader contextClassLoader =
					currentThread.getContextClassLoader();

				clazz = contextClassLoader.loadClass(className);

				classesMap.put(className, clazz);
			}

			method = clazz.getMethod(methodName, parameterTypes);

			methodsMap.put(methodKey, method);
		}

		return method;
	}

	private Method _put(MethodKey methodKey, Method method) {
		return _methodsMap.put(methodKey, method);
	}

	private void _remove(Class<?> clazz) {
		_classesMap.remove(clazz.getName());

		for (Method method : clazz.getMethods()) {
			_methodsMap.remove(new MethodKey(method));
		}
	}

	private void _reset() {
		_classesMap.clear();
		_methodsMap.clear();
	}

	private static MethodCache _instance = new MethodCache();

	private Map<String, Class<?>> _classesMap;
	private Map<MethodKey, Method> _methodsMap;

}