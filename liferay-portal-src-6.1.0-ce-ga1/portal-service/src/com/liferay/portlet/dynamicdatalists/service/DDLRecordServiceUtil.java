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

package com.liferay.portlet.dynamicdatalists.service;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the d d l record remote service. This utility wraps {@link com.liferay.portlet.dynamicdatalists.service.impl.DDLRecordServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DDLRecordService
 * @see com.liferay.portlet.dynamicdatalists.service.base.DDLRecordServiceBaseImpl
 * @see com.liferay.portlet.dynamicdatalists.service.impl.DDLRecordServiceImpl
 * @generated
 */
public class DDLRecordServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portlet.dynamicdatalists.service.impl.DDLRecordServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portlet.dynamicdatalists.model.DDLRecord addRecord(
		long groupId, long recordSetId, int displayIndex,
		com.liferay.portlet.dynamicdatamapping.storage.Fields fields,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addRecord(groupId, recordSetId, displayIndex, fields,
			serviceContext);
	}

	public static com.liferay.portlet.dynamicdatalists.model.DDLRecord addRecord(
		long groupId, long recordSetId, int displayIndex,
		java.util.Map<java.lang.String, java.io.Serializable> fieldsMap,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addRecord(groupId, recordSetId, displayIndex, fieldsMap,
			serviceContext);
	}

	public static com.liferay.portlet.dynamicdatalists.model.DDLRecord getRecord(
		long recordId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getRecord(recordId);
	}

	public static com.liferay.portlet.dynamicdatalists.model.DDLRecord updateRecord(
		long recordId, boolean majorVersion, int displayIndex,
		com.liferay.portlet.dynamicdatamapping.storage.Fields fields,
		boolean mergeFields,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateRecord(recordId, majorVersion, displayIndex, fields,
			mergeFields, serviceContext);
	}

	public static com.liferay.portlet.dynamicdatalists.model.DDLRecord updateRecord(
		long recordId, int displayIndex,
		java.util.Map<java.lang.String, java.io.Serializable> fieldsMap,
		boolean mergeFields,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateRecord(recordId, displayIndex, fieldsMap,
			mergeFields, serviceContext);
	}

	public static DDLRecordService getService() {
		if (_service == null) {
			_service = (DDLRecordService)PortalBeanLocatorUtil.locate(DDLRecordService.class.getName());

			ReferenceRegistry.registerReference(DDLRecordServiceUtil.class,
				"_service");
			MethodCache.remove(DDLRecordService.class);
		}

		return _service;
	}

	public void setService(DDLRecordService service) {
		MethodCache.remove(DDLRecordService.class);

		_service = service;

		ReferenceRegistry.registerReference(DDLRecordServiceUtil.class,
			"_service");
		MethodCache.remove(DDLRecordService.class);
	}

	private static DDLRecordService _service;
}