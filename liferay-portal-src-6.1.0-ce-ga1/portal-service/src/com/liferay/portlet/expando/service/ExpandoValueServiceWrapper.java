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

package com.liferay.portlet.expando.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ExpandoValueService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ExpandoValueService
 * @generated
 */
public class ExpandoValueServiceWrapper implements ExpandoValueService,
	ServiceWrapper<ExpandoValueService> {
	public ExpandoValueServiceWrapper(ExpandoValueService expandoValueService) {
		_expandoValueService = expandoValueService;
	}

	public com.liferay.portlet.expando.model.ExpandoValue addValue(
		long companyId, java.lang.String className, java.lang.String tableName,
		java.lang.String columnName, long classPK, java.lang.Object data)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _expandoValueService.addValue(companyId, className, tableName,
			columnName, classPK, data);
	}

	public com.liferay.portlet.expando.model.ExpandoValue addValue(
		long companyId, java.lang.String className, java.lang.String tableName,
		java.lang.String columnName, long classPK, java.lang.String data)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _expandoValueService.addValue(companyId, className, tableName,
			columnName, classPK, data);
	}

	public void addValues(long companyId, java.lang.String className,
		java.lang.String tableName, long classPK,
		java.util.Map<java.lang.String, java.io.Serializable> attributeValues)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_expandoValueService.addValues(companyId, className, tableName,
			classPK, attributeValues);
	}

	public java.io.Serializable getData(long companyId,
		java.lang.String className, java.lang.String tableName,
		java.lang.String columnName, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _expandoValueService.getData(companyId, className, tableName,
			columnName, classPK);
	}

	public java.util.Map<java.lang.String, java.io.Serializable> getData(
		long companyId, java.lang.String className, java.lang.String tableName,
		java.util.Collection<java.lang.String> columnNames, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _expandoValueService.getData(companyId, className, tableName,
			columnNames, classPK);
	}

	public com.liferay.portal.kernel.json.JSONObject getJSONData(
		long companyId, java.lang.String className, java.lang.String tableName,
		java.lang.String columnName, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _expandoValueService.getJSONData(companyId, className,
			tableName, columnName, classPK);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public ExpandoValueService getWrappedExpandoValueService() {
		return _expandoValueService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedExpandoValueService(
		ExpandoValueService expandoValueService) {
		_expandoValueService = expandoValueService;
	}

	public ExpandoValueService getWrappedService() {
		return _expandoValueService;
	}

	public void setWrappedService(ExpandoValueService expandoValueService) {
		_expandoValueService = expandoValueService;
	}

	private ExpandoValueService _expandoValueService;
}