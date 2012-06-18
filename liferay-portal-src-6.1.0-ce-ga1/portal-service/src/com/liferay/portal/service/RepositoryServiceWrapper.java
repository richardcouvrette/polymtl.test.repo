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
 * This class is a wrapper for {@link RepositoryService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       RepositoryService
 * @generated
 */
public class RepositoryServiceWrapper implements RepositoryService,
	ServiceWrapper<RepositoryService> {
	public RepositoryServiceWrapper(RepositoryService repositoryService) {
		_repositoryService = repositoryService;
	}

	public long addRepository(long groupId, long classNameId,
		long parentFolderId, java.lang.String name,
		java.lang.String description, java.lang.String portletId,
		com.liferay.portal.kernel.util.UnicodeProperties typeSettingsProperties,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _repositoryService.addRepository(groupId, classNameId,
			parentFolderId, name, description, portletId,
			typeSettingsProperties, serviceContext);
	}

	public void checkRepository(long repositoryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_repositoryService.checkRepository(repositoryId);
	}

	public void deleteRepository(long repositoryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_repositoryService.deleteRepository(repositoryId);
	}

	public com.liferay.portal.kernel.repository.LocalRepository getLocalRepositoryImpl(
		long repositoryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _repositoryService.getLocalRepositoryImpl(repositoryId);
	}

	public com.liferay.portal.kernel.repository.LocalRepository getLocalRepositoryImpl(
		long folderId, long fileEntryId, long fileVersionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _repositoryService.getLocalRepositoryImpl(folderId, fileEntryId,
			fileVersionId);
	}

	public com.liferay.portal.model.Repository getRepository(long repositoryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _repositoryService.getRepository(repositoryId);
	}

	public com.liferay.portal.kernel.repository.Repository getRepositoryImpl(
		long repositoryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _repositoryService.getRepositoryImpl(repositoryId);
	}

	public com.liferay.portal.kernel.repository.Repository getRepositoryImpl(
		long folderId, long fileEntryId, long fileVersionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _repositoryService.getRepositoryImpl(folderId, fileEntryId,
			fileVersionId);
	}

	public java.lang.String[] getSupportedConfigurations(long classNameId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _repositoryService.getSupportedConfigurations(classNameId);
	}

	public java.lang.String[] getSupportedParameters(long classNameId,
		java.lang.String configuration)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _repositoryService.getSupportedParameters(classNameId,
			configuration);
	}

	public com.liferay.portal.kernel.util.UnicodeProperties getTypeSettingsProperties(
		long repositoryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _repositoryService.getTypeSettingsProperties(repositoryId);
	}

	public void updateRepository(long repositoryId, java.lang.String name,
		java.lang.String description)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_repositoryService.updateRepository(repositoryId, name, description);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public RepositoryService getWrappedRepositoryService() {
		return _repositoryService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedRepositoryService(RepositoryService repositoryService) {
		_repositoryService = repositoryService;
	}

	public RepositoryService getWrappedService() {
		return _repositoryService;
	}

	public void setWrappedService(RepositoryService repositoryService) {
		_repositoryService = repositoryService;
	}

	private RepositoryService _repositoryService;
}