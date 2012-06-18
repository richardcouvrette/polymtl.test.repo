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

package com.liferay.portal.spring.aop;

import java.lang.annotation.Annotation;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.aopalliance.intercept.MethodInvocation;

/**
 * @author Shuyang Zhou
 */
public class ServiceMethodAnnotationCache {

	public static <T> T get(
		MethodInvocation methodInvocation,
		Class<? extends Annotation> annotationType, T defaultValue) {

		Annotation[] annotations = _annotations.get(methodInvocation);

		if (annotations == _nullAnnotations) {
			return defaultValue;
		}

		if (annotations == null) {
			return null;
		}

		for (Annotation annotation : annotations) {
			if (annotation.annotationType() == annotationType) {
				return (T)annotation;
			}
		}

		return defaultValue;
	}

	public static void put(
		MethodInvocation methodInvocation, Annotation[] annotations) {

		if ((annotations == null) || (annotations.length == 0)) {
			annotations = _nullAnnotations;
		}

		if (methodInvocation instanceof ServiceBeanMethodInvocation) {
			ServiceBeanMethodInvocation serviceBeanMethodInvocation =
				(ServiceBeanMethodInvocation)methodInvocation;

			methodInvocation = serviceBeanMethodInvocation.toCacheKeyModel();
		}

		_annotations.put(methodInvocation, annotations);
	}

	private static Map<MethodInvocation, Annotation[]> _annotations =
		new ConcurrentHashMap<MethodInvocation, Annotation[]>();
	private static Annotation[] _nullAnnotations = new Annotation[0];

}