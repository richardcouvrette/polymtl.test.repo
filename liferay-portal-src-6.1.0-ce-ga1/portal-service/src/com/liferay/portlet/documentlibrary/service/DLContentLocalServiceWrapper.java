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
 * This class is a wrapper for {@link DLContentLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       DLContentLocalService
 * @generated
 */
public class DLContentLocalServiceWrapper implements DLContentLocalService,
	ServiceWrapper<DLContentLocalService> {
	public DLContentLocalServiceWrapper(
		DLContentLocalService dlContentLocalService) {
		_dlContentLocalService = dlContentLocalService;
	}

	/**
	* Adds the document library content to the database. Also notifies the appropriate model listeners.
	*
	* @param dlContent the document library content
	* @return the document library content that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.documentlibrary.model.DLContent addDLContent(
		com.liferay.portlet.documentlibrary.model.DLContent dlContent)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlContentLocalService.addDLContent(dlContent);
	}

	/**
	* Creates a new document library content with the primary key. Does not add the document library content to the database.
	*
	* @param contentId the primary key for the new document library content
	* @return the new document library content
	*/
	public com.liferay.portlet.documentlibrary.model.DLContent createDLContent(
		long contentId) {
		return _dlContentLocalService.createDLContent(contentId);
	}

	/**
	* Deletes the document library content with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param contentId the primary key of the document library content
	* @throws PortalException if a document library content with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteDLContent(long contentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_dlContentLocalService.deleteDLContent(contentId);
	}

	/**
	* Deletes the document library content from the database. Also notifies the appropriate model listeners.
	*
	* @param dlContent the document library content
	* @throws SystemException if a system exception occurred
	*/
	public void deleteDLContent(
		com.liferay.portlet.documentlibrary.model.DLContent dlContent)
		throws com.liferay.portal.kernel.exception.SystemException {
		_dlContentLocalService.deleteDLContent(dlContent);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlContentLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _dlContentLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlContentLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlContentLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.portlet.documentlibrary.model.DLContent fetchDLContent(
		long contentId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlContentLocalService.fetchDLContent(contentId);
	}

	/**
	* Returns the document library content with the primary key.
	*
	* @param contentId the primary key of the document library content
	* @return the document library content
	* @throws PortalException if a document library content with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.documentlibrary.model.DLContent getDLContent(
		long contentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlContentLocalService.getDLContent(contentId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlContentLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the document library contents.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of document library contents
	* @param end the upper bound of the range of document library contents (not inclusive)
	* @return the range of document library contents
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portlet.documentlibrary.model.DLContent> getDLContents(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlContentLocalService.getDLContents(start, end);
	}

	/**
	* Returns the number of document library contents.
	*
	* @return the number of document library contents
	* @throws SystemException if a system exception occurred
	*/
	public int getDLContentsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlContentLocalService.getDLContentsCount();
	}

	/**
	* Updates the document library content in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dlContent the document library content
	* @return the document library content that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.documentlibrary.model.DLContent updateDLContent(
		com.liferay.portlet.documentlibrary.model.DLContent dlContent)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlContentLocalService.updateDLContent(dlContent);
	}

	/**
	* Updates the document library content in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dlContent the document library content
	* @param merge whether to merge the document library content with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the document library content that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.documentlibrary.model.DLContent updateDLContent(
		com.liferay.portlet.documentlibrary.model.DLContent dlContent,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlContentLocalService.updateDLContent(dlContent, merge);
	}

	public com.liferay.portlet.documentlibrary.model.DLContentDataBlobModel getDataBlobModel(
		java.io.Serializable primaryKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlContentLocalService.getDataBlobModel(primaryKey);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _dlContentLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_dlContentLocalService.setBeanIdentifier(beanIdentifier);
	}

	public com.liferay.portlet.documentlibrary.model.DLContent addContent(
		long companyId, long repositoryId, java.lang.String path,
		java.lang.String version, byte[] bytes)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlContentLocalService.addContent(companyId, repositoryId, path,
			version, bytes);
	}

	public com.liferay.portlet.documentlibrary.model.DLContent addContent(
		long companyId, long repositoryId, java.lang.String path,
		java.lang.String version, java.io.InputStream inputStream, long size)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlContentLocalService.addContent(companyId, repositoryId, path,
			version, inputStream, size);
	}

	public void deleteContent(long companyId, long repositoryId,
		java.lang.String path, java.lang.String version)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_dlContentLocalService.deleteContent(companyId, repositoryId, path,
			version);
	}

	public void deleteContents(long companyId, long repositoryId,
		java.lang.String path)
		throws com.liferay.portal.kernel.exception.SystemException {
		_dlContentLocalService.deleteContents(companyId, repositoryId, path);
	}

	public void deleteContentsByDirectory(long companyId, long repositoryId,
		java.lang.String dirName)
		throws com.liferay.portal.kernel.exception.SystemException {
		_dlContentLocalService.deleteContentsByDirectory(companyId,
			repositoryId, dirName);
	}

	public com.liferay.portlet.documentlibrary.model.DLContent getContent(
		long companyId, long repositoryId, java.lang.String path)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchContentException {
		return _dlContentLocalService.getContent(companyId, repositoryId, path);
	}

	public com.liferay.portlet.documentlibrary.model.DLContent getContent(
		long companyId, long repositoryId, java.lang.String path,
		java.lang.String version)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchContentException {
		return _dlContentLocalService.getContent(companyId, repositoryId, path,
			version);
	}

	public java.util.List<com.liferay.portlet.documentlibrary.model.DLContent> getContents(
		long companyId, long repositoryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlContentLocalService.getContents(companyId, repositoryId);
	}

	public java.util.List<com.liferay.portlet.documentlibrary.model.DLContent> getContents(
		long companyId, long repositoryId, java.lang.String path)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlContentLocalService.getContents(companyId, repositoryId, path);
	}

	public java.util.List<com.liferay.portlet.documentlibrary.model.DLContent> getContentsByDirectory(
		long companyId, long repositoryId, java.lang.String dirName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlContentLocalService.getContentsByDirectory(companyId,
			repositoryId, dirName);
	}

	public boolean hasContent(long companyId, long repositoryId,
		java.lang.String path, java.lang.String version)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlContentLocalService.hasContent(companyId, repositoryId, path,
			version);
	}

	public void updateDLContent(long companyId, long oldRepositoryId,
		long newRepositoryId, java.lang.String oldPath, java.lang.String newPath)
		throws com.liferay.portal.kernel.exception.SystemException {
		_dlContentLocalService.updateDLContent(companyId, oldRepositoryId,
			newRepositoryId, oldPath, newPath);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public DLContentLocalService getWrappedDLContentLocalService() {
		return _dlContentLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedDLContentLocalService(
		DLContentLocalService dlContentLocalService) {
		_dlContentLocalService = dlContentLocalService;
	}

	public DLContentLocalService getWrappedService() {
		return _dlContentLocalService;
	}

	public void setWrappedService(DLContentLocalService dlContentLocalService) {
		_dlContentLocalService = dlContentLocalService;
	}

	private DLContentLocalService _dlContentLocalService;
}