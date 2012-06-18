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

package com.liferay.portal.service.impl;

import com.liferay.portal.kernel.cache.CacheRegistryItem;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.spring.aop.Skip;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ClassName;
import com.liferay.portal.model.ModelHintsUtil;
import com.liferay.portal.model.impl.ClassNameImpl;
import com.liferay.portal.service.base.ClassNameLocalServiceBaseImpl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Brian Wing Shun Chan
 */
public class ClassNameLocalServiceImpl
	extends ClassNameLocalServiceBaseImpl implements CacheRegistryItem {

	public ClassName addClassName(String value) throws SystemException {
		ClassName className = classNamePersistence.fetchByValue(value);

		if (className == null) {
			long classNameId = counterLocalService.increment();

			className = classNamePersistence.create(classNameId);

			className.setValue(value);

			classNamePersistence.update(className, false);
		}

		return className;
	}

	@Override
	public void afterPropertiesSet() {
		super.afterPropertiesSet();

		CacheRegistryUtil.register(this);
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public void checkClassNames() throws SystemException {
		List<ClassName> classNames = classNamePersistence.findAll();

		for (ClassName className : classNames) {
			_classNames.put(className.getValue(), className);
		}

		List<String> models = ModelHintsUtil.getModels();

		for (String model : models) {
			getClassName(model);
		}
	}

	@Override
	public ClassName getClassName(long classNameId)
		throws PortalException, SystemException {

		return classNamePersistence.findByPrimaryKey(classNameId);
	}

	@Skip
	public ClassName getClassName(String value) throws SystemException {
		if (Validator.isNull(value)) {
			return _nullClassName;
		}

		// Always cache the class name. This table exists to improve
		// performance. Create the class name if one does not exist.

		ClassName className = _classNames.get(value);

		if (className == null) {
			className = classNameLocalService.addClassName(value);

			_classNames.put(value, className);
		}

		return className;
	}

	@Skip
	public long getClassNameId(Class<?> clazz) {
		return getClassNameId(clazz.getName());
	}

	@Skip
	public long getClassNameId(String value) {
		try {
			ClassName className = getClassName(value);

			return className.getClassNameId();
		}
		catch (Exception e) {
			throw new RuntimeException(
				"Unable to get class name from value " + value, e);
		}
	}

	public String getRegistryName() {
		return ClassNameLocalServiceImpl.class.getName();
	}

	public void invalidate() {
		_classNames.clear();
	}

	private static ClassName _nullClassName = new ClassNameImpl();
	private static Map<String, ClassName> _classNames =
		new ConcurrentHashMap<String, ClassName>();

}