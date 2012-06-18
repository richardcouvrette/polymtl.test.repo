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

package com.liferay.portal.service;

/**
 * <p>
 * This class is a wrapper for {@link PhoneService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PhoneService
 * @generated
 */
public class PhoneServiceWrapper implements PhoneService,
	ServiceWrapper<PhoneService> {
	public PhoneServiceWrapper(PhoneService phoneService) {
		_phoneService = phoneService;
	}

	public com.liferay.portal.model.Phone addPhone(java.lang.String className,
		long classPK, java.lang.String number, java.lang.String extension,
		int typeId, boolean primary)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _phoneService.addPhone(className, classPK, number, extension,
			typeId, primary);
	}

	public void deletePhone(long phoneId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_phoneService.deletePhone(phoneId);
	}

	public com.liferay.portal.model.Phone getPhone(long phoneId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _phoneService.getPhone(phoneId);
	}

	public java.util.List<com.liferay.portal.model.Phone> getPhones(
		java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _phoneService.getPhones(className, classPK);
	}

	public com.liferay.portal.model.Phone updatePhone(long phoneId,
		java.lang.String number, java.lang.String extension, int typeId,
		boolean primary)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _phoneService.updatePhone(phoneId, number, extension, typeId,
			primary);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public PhoneService getWrappedPhoneService() {
		return _phoneService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedPhoneService(PhoneService phoneService) {
		_phoneService = phoneService;
	}

	public PhoneService getWrappedService() {
		return _phoneService;
	}

	public void setWrappedService(PhoneService phoneService) {
		_phoneService = phoneService;
	}

	private PhoneService _phoneService;
}