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
 * This class is a wrapper for {@link WebsiteLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       WebsiteLocalService
 * @generated
 */
public class WebsiteLocalServiceWrapper implements WebsiteLocalService,
	ServiceWrapper<WebsiteLocalService> {
	public WebsiteLocalServiceWrapper(WebsiteLocalService websiteLocalService) {
		_websiteLocalService = websiteLocalService;
	}

	/**
	* Adds the website to the database. Also notifies the appropriate model listeners.
	*
	* @param website the website
	* @return the website that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.Website addWebsite(
		com.liferay.portal.model.Website website)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _websiteLocalService.addWebsite(website);
	}

	/**
	* Creates a new website with the primary key. Does not add the website to the database.
	*
	* @param websiteId the primary key for the new website
	* @return the new website
	*/
	public com.liferay.portal.model.Website createWebsite(long websiteId) {
		return _websiteLocalService.createWebsite(websiteId);
	}

	/**
	* Deletes the website with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param websiteId the primary key of the website
	* @throws PortalException if a website with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteWebsite(long websiteId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_websiteLocalService.deleteWebsite(websiteId);
	}

	/**
	* Deletes the website from the database. Also notifies the appropriate model listeners.
	*
	* @param website the website
	* @throws SystemException if a system exception occurred
	*/
	public void deleteWebsite(com.liferay.portal.model.Website website)
		throws com.liferay.portal.kernel.exception.SystemException {
		_websiteLocalService.deleteWebsite(website);
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
		return _websiteLocalService.dynamicQuery(dynamicQuery);
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
		return _websiteLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _websiteLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _websiteLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.portal.model.Website fetchWebsite(long websiteId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _websiteLocalService.fetchWebsite(websiteId);
	}

	/**
	* Returns the website with the primary key.
	*
	* @param websiteId the primary key of the website
	* @return the website
	* @throws PortalException if a website with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.Website getWebsite(long websiteId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _websiteLocalService.getWebsite(websiteId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _websiteLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the websites.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of websites
	* @param end the upper bound of the range of websites (not inclusive)
	* @return the range of websites
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.Website> getWebsites(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _websiteLocalService.getWebsites(start, end);
	}

	/**
	* Returns the number of websites.
	*
	* @return the number of websites
	* @throws SystemException if a system exception occurred
	*/
	public int getWebsitesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _websiteLocalService.getWebsitesCount();
	}

	/**
	* Updates the website in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param website the website
	* @return the website that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.Website updateWebsite(
		com.liferay.portal.model.Website website)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _websiteLocalService.updateWebsite(website);
	}

	/**
	* Updates the website in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param website the website
	* @param merge whether to merge the website with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the website that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.Website updateWebsite(
		com.liferay.portal.model.Website website, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _websiteLocalService.updateWebsite(website, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _websiteLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_websiteLocalService.setBeanIdentifier(beanIdentifier);
	}

	public com.liferay.portal.model.Website addWebsite(long userId,
		java.lang.String className, long classPK, java.lang.String url,
		int typeId, boolean primary)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _websiteLocalService.addWebsite(userId, className, classPK, url,
			typeId, primary);
	}

	public void deleteWebsites(long companyId, java.lang.String className,
		long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		_websiteLocalService.deleteWebsites(companyId, className, classPK);
	}

	public java.util.List<com.liferay.portal.model.Website> getWebsites()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _websiteLocalService.getWebsites();
	}

	public java.util.List<com.liferay.portal.model.Website> getWebsites(
		long companyId, java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _websiteLocalService.getWebsites(companyId, className, classPK);
	}

	public com.liferay.portal.model.Website updateWebsite(long websiteId,
		java.lang.String url, int typeId, boolean primary)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _websiteLocalService.updateWebsite(websiteId, url, typeId,
			primary);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public WebsiteLocalService getWrappedWebsiteLocalService() {
		return _websiteLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedWebsiteLocalService(
		WebsiteLocalService websiteLocalService) {
		_websiteLocalService = websiteLocalService;
	}

	public WebsiteLocalService getWrappedService() {
		return _websiteLocalService;
	}

	public void setWrappedService(WebsiteLocalService websiteLocalService) {
		_websiteLocalService = websiteLocalService;
	}

	private WebsiteLocalService _websiteLocalService;
}