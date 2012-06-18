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

package com.liferay.portlet.documentlibrary.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link DLFileEntryTypeService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       DLFileEntryTypeService
 * @generated
 */
public class DLFileEntryTypeServiceWrapper implements DLFileEntryTypeService,
	ServiceWrapper<DLFileEntryTypeService> {
	public DLFileEntryTypeServiceWrapper(
		DLFileEntryTypeService dlFileEntryTypeService) {
		_dlFileEntryTypeService = dlFileEntryTypeService;
	}

	public com.liferay.portlet.documentlibrary.model.DLFileEntryType addFileEntryType(
		long groupId, java.lang.String name, java.lang.String description,
		long[] ddmStructureIds,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlFileEntryTypeService.addFileEntryType(groupId, name,
			description, ddmStructureIds, serviceContext);
	}

	public void deleteFileEntryType(long fileEntryTypeId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_dlFileEntryTypeService.deleteFileEntryType(fileEntryTypeId);
	}

	public com.liferay.portlet.documentlibrary.model.DLFileEntryType getFileEntryType(
		long fileEntryTypeId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlFileEntryTypeService.getFileEntryType(fileEntryTypeId);
	}

	public java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntryType> getFileEntryTypes(
		long[] groupIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlFileEntryTypeService.getFileEntryTypes(groupIds);
	}

	public int getFileEntryTypesCount(long[] groupIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlFileEntryTypeService.getFileEntryTypesCount(groupIds);
	}

	public void updateFileEntryType(long fileEntryTypeId,
		java.lang.String name, java.lang.String description,
		long[] ddmStructureIds,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_dlFileEntryTypeService.updateFileEntryType(fileEntryTypeId, name,
			description, ddmStructureIds, serviceContext);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public DLFileEntryTypeService getWrappedDLFileEntryTypeService() {
		return _dlFileEntryTypeService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedDLFileEntryTypeService(
		DLFileEntryTypeService dlFileEntryTypeService) {
		_dlFileEntryTypeService = dlFileEntryTypeService;
	}

	public DLFileEntryTypeService getWrappedService() {
		return _dlFileEntryTypeService;
	}

	public void setWrappedService(DLFileEntryTypeService dlFileEntryTypeService) {
		_dlFileEntryTypeService = dlFileEntryTypeService;
	}

	private DLFileEntryTypeService _dlFileEntryTypeService;
}