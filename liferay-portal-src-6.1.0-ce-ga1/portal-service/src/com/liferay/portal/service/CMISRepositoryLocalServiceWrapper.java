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
 * This class is a wrapper for {@link CMISRepositoryLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       CMISRepositoryLocalService
 * @generated
 */
public class CMISRepositoryLocalServiceWrapper
	implements CMISRepositoryLocalService,
		ServiceWrapper<CMISRepositoryLocalService> {
	public CMISRepositoryLocalServiceWrapper(
		CMISRepositoryLocalService cmisRepositoryLocalService) {
		_cmisRepositoryLocalService = cmisRepositoryLocalService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _cmisRepositoryLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_cmisRepositoryLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object getSession(long repositoryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _cmisRepositoryLocalService.getSession(repositoryId);
	}

	public com.liferay.portal.kernel.repository.model.FileEntry toFileEntry(
		long repositoryId, java.lang.Object object)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _cmisRepositoryLocalService.toFileEntry(repositoryId, object);
	}

	public com.liferay.portal.kernel.repository.model.FileVersion toFileVersion(
		long repositoryId, java.lang.Object object)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _cmisRepositoryLocalService.toFileVersion(repositoryId, object);
	}

	public com.liferay.portal.kernel.repository.model.Folder toFolder(
		long repositoryId, java.lang.Object object)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _cmisRepositoryLocalService.toFolder(repositoryId, object);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public CMISRepositoryLocalService getWrappedCMISRepositoryLocalService() {
		return _cmisRepositoryLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedCMISRepositoryLocalService(
		CMISRepositoryLocalService cmisRepositoryLocalService) {
		_cmisRepositoryLocalService = cmisRepositoryLocalService;
	}

	public CMISRepositoryLocalService getWrappedService() {
		return _cmisRepositoryLocalService;
	}

	public void setWrappedService(
		CMISRepositoryLocalService cmisRepositoryLocalService) {
		_cmisRepositoryLocalService = cmisRepositoryLocalService;
	}

	private CMISRepositoryLocalService _cmisRepositoryLocalService;
}