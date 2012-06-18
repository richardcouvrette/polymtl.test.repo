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

package com.liferay.portlet.journal.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link JournalArticleImageLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       JournalArticleImageLocalService
 * @generated
 */
public class JournalArticleImageLocalServiceWrapper
	implements JournalArticleImageLocalService,
		ServiceWrapper<JournalArticleImageLocalService> {
	public JournalArticleImageLocalServiceWrapper(
		JournalArticleImageLocalService journalArticleImageLocalService) {
		_journalArticleImageLocalService = journalArticleImageLocalService;
	}

	/**
	* Adds the journal article image to the database. Also notifies the appropriate model listeners.
	*
	* @param journalArticleImage the journal article image
	* @return the journal article image that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.journal.model.JournalArticleImage addJournalArticleImage(
		com.liferay.portlet.journal.model.JournalArticleImage journalArticleImage)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _journalArticleImageLocalService.addJournalArticleImage(journalArticleImage);
	}

	/**
	* Creates a new journal article image with the primary key. Does not add the journal article image to the database.
	*
	* @param articleImageId the primary key for the new journal article image
	* @return the new journal article image
	*/
	public com.liferay.portlet.journal.model.JournalArticleImage createJournalArticleImage(
		long articleImageId) {
		return _journalArticleImageLocalService.createJournalArticleImage(articleImageId);
	}

	/**
	* Deletes the journal article image with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param articleImageId the primary key of the journal article image
	* @throws PortalException if a journal article image with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteJournalArticleImage(long articleImageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_journalArticleImageLocalService.deleteJournalArticleImage(articleImageId);
	}

	/**
	* Deletes the journal article image from the database. Also notifies the appropriate model listeners.
	*
	* @param journalArticleImage the journal article image
	* @throws SystemException if a system exception occurred
	*/
	public void deleteJournalArticleImage(
		com.liferay.portlet.journal.model.JournalArticleImage journalArticleImage)
		throws com.liferay.portal.kernel.exception.SystemException {
		_journalArticleImageLocalService.deleteJournalArticleImage(journalArticleImage);
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
		return _journalArticleImageLocalService.dynamicQuery(dynamicQuery);
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
		return _journalArticleImageLocalService.dynamicQuery(dynamicQuery,
			start, end);
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
		return _journalArticleImageLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
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
		return _journalArticleImageLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.portlet.journal.model.JournalArticleImage fetchJournalArticleImage(
		long articleImageId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _journalArticleImageLocalService.fetchJournalArticleImage(articleImageId);
	}

	/**
	* Returns the journal article image with the primary key.
	*
	* @param articleImageId the primary key of the journal article image
	* @return the journal article image
	* @throws PortalException if a journal article image with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.journal.model.JournalArticleImage getJournalArticleImage(
		long articleImageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _journalArticleImageLocalService.getJournalArticleImage(articleImageId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _journalArticleImageLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the journal article images.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of journal article images
	* @param end the upper bound of the range of journal article images (not inclusive)
	* @return the range of journal article images
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portlet.journal.model.JournalArticleImage> getJournalArticleImages(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _journalArticleImageLocalService.getJournalArticleImages(start,
			end);
	}

	/**
	* Returns the number of journal article images.
	*
	* @return the number of journal article images
	* @throws SystemException if a system exception occurred
	*/
	public int getJournalArticleImagesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _journalArticleImageLocalService.getJournalArticleImagesCount();
	}

	/**
	* Updates the journal article image in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param journalArticleImage the journal article image
	* @return the journal article image that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.journal.model.JournalArticleImage updateJournalArticleImage(
		com.liferay.portlet.journal.model.JournalArticleImage journalArticleImage)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _journalArticleImageLocalService.updateJournalArticleImage(journalArticleImage);
	}

	/**
	* Updates the journal article image in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param journalArticleImage the journal article image
	* @param merge whether to merge the journal article image with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the journal article image that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.journal.model.JournalArticleImage updateJournalArticleImage(
		com.liferay.portlet.journal.model.JournalArticleImage journalArticleImage,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _journalArticleImageLocalService.updateJournalArticleImage(journalArticleImage,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _journalArticleImageLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_journalArticleImageLocalService.setBeanIdentifier(beanIdentifier);
	}

	public void addArticleImageId(long articleImageId, long groupId,
		java.lang.String articleId, double version,
		java.lang.String elInstanceId, java.lang.String elName,
		java.lang.String languageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_journalArticleImageLocalService.addArticleImageId(articleImageId,
			groupId, articleId, version, elInstanceId, elName, languageId);
	}

	public void deleteArticleImage(long articleImageId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_journalArticleImageLocalService.deleteArticleImage(articleImageId);
	}

	public void deleteArticleImage(
		com.liferay.portlet.journal.model.JournalArticleImage articleImage)
		throws com.liferay.portal.kernel.exception.SystemException {
		_journalArticleImageLocalService.deleteArticleImage(articleImage);
	}

	public void deleteArticleImage(long groupId, java.lang.String articleId,
		double version, java.lang.String elInstanceId, java.lang.String elName,
		java.lang.String languageId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_journalArticleImageLocalService.deleteArticleImage(groupId, articleId,
			version, elInstanceId, elName, languageId);
	}

	public void deleteImages(long groupId, java.lang.String articleId,
		double version)
		throws com.liferay.portal.kernel.exception.SystemException {
		_journalArticleImageLocalService.deleteImages(groupId, articleId,
			version);
	}

	public com.liferay.portlet.journal.model.JournalArticleImage getArticleImage(
		long articleImageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _journalArticleImageLocalService.getArticleImage(articleImageId);
	}

	public long getArticleImageId(long groupId, java.lang.String articleId,
		double version, java.lang.String elInstanceId, java.lang.String elName,
		java.lang.String languageId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _journalArticleImageLocalService.getArticleImageId(groupId,
			articleId, version, elInstanceId, elName, languageId);
	}

	public long getArticleImageId(long groupId, java.lang.String articleId,
		double version, java.lang.String elInstanceId, java.lang.String elName,
		java.lang.String languageId, boolean tempImage)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _journalArticleImageLocalService.getArticleImageId(groupId,
			articleId, version, elInstanceId, elName, languageId, tempImage);
	}

	public java.util.List<com.liferay.portlet.journal.model.JournalArticleImage> getArticleImages(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _journalArticleImageLocalService.getArticleImages(groupId);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public JournalArticleImageLocalService getWrappedJournalArticleImageLocalService() {
		return _journalArticleImageLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedJournalArticleImageLocalService(
		JournalArticleImageLocalService journalArticleImageLocalService) {
		_journalArticleImageLocalService = journalArticleImageLocalService;
	}

	public JournalArticleImageLocalService getWrappedService() {
		return _journalArticleImageLocalService;
	}

	public void setWrappedService(
		JournalArticleImageLocalService journalArticleImageLocalService) {
		_journalArticleImageLocalService = journalArticleImageLocalService;
	}

	private JournalArticleImageLocalService _journalArticleImageLocalService;
}