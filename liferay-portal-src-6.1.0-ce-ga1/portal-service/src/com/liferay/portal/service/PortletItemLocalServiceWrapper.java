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
 * This class is a wrapper for {@link PortletItemLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PortletItemLocalService
 * @generated
 */
public class PortletItemLocalServiceWrapper implements PortletItemLocalService,
	ServiceWrapper<PortletItemLocalService> {
	public PortletItemLocalServiceWrapper(
		PortletItemLocalService portletItemLocalService) {
		_portletItemLocalService = portletItemLocalService;
	}

	/**
	* Adds the portlet item to the database. Also notifies the appropriate model listeners.
	*
	* @param portletItem the portlet item
	* @return the portlet item that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.PortletItem addPortletItem(
		com.liferay.portal.model.PortletItem portletItem)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _portletItemLocalService.addPortletItem(portletItem);
	}

	/**
	* Creates a new portlet item with the primary key. Does not add the portlet item to the database.
	*
	* @param portletItemId the primary key for the new portlet item
	* @return the new portlet item
	*/
	public com.liferay.portal.model.PortletItem createPortletItem(
		long portletItemId) {
		return _portletItemLocalService.createPortletItem(portletItemId);
	}

	/**
	* Deletes the portlet item with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param portletItemId the primary key of the portlet item
	* @throws PortalException if a portlet item with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deletePortletItem(long portletItemId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_portletItemLocalService.deletePortletItem(portletItemId);
	}

	/**
	* Deletes the portlet item from the database. Also notifies the appropriate model listeners.
	*
	* @param portletItem the portlet item
	* @throws SystemException if a system exception occurred
	*/
	public void deletePortletItem(
		com.liferay.portal.model.PortletItem portletItem)
		throws com.liferay.portal.kernel.exception.SystemException {
		_portletItemLocalService.deletePortletItem(portletItem);
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
		return _portletItemLocalService.dynamicQuery(dynamicQuery);
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
		return _portletItemLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _portletItemLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _portletItemLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.portal.model.PortletItem fetchPortletItem(
		long portletItemId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _portletItemLocalService.fetchPortletItem(portletItemId);
	}

	/**
	* Returns the portlet item with the primary key.
	*
	* @param portletItemId the primary key of the portlet item
	* @return the portlet item
	* @throws PortalException if a portlet item with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.PortletItem getPortletItem(
		long portletItemId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _portletItemLocalService.getPortletItem(portletItemId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _portletItemLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the portlet items.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of portlet items
	* @param end the upper bound of the range of portlet items (not inclusive)
	* @return the range of portlet items
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.PortletItem> getPortletItems(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _portletItemLocalService.getPortletItems(start, end);
	}

	/**
	* Returns the number of portlet items.
	*
	* @return the number of portlet items
	* @throws SystemException if a system exception occurred
	*/
	public int getPortletItemsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _portletItemLocalService.getPortletItemsCount();
	}

	/**
	* Updates the portlet item in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param portletItem the portlet item
	* @return the portlet item that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.PortletItem updatePortletItem(
		com.liferay.portal.model.PortletItem portletItem)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _portletItemLocalService.updatePortletItem(portletItem);
	}

	/**
	* Updates the portlet item in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param portletItem the portlet item
	* @param merge whether to merge the portlet item with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the portlet item that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.PortletItem updatePortletItem(
		com.liferay.portal.model.PortletItem portletItem, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _portletItemLocalService.updatePortletItem(portletItem, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _portletItemLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_portletItemLocalService.setBeanIdentifier(beanIdentifier);
	}

	public com.liferay.portal.model.PortletItem addPortletItem(long userId,
		long groupId, java.lang.String name, java.lang.String portletId,
		java.lang.String className)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _portletItemLocalService.addPortletItem(userId, groupId, name,
			portletId, className);
	}

	public com.liferay.portal.model.PortletItem getPortletItem(long groupId,
		java.lang.String name, java.lang.String portletId,
		java.lang.String className)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _portletItemLocalService.getPortletItem(groupId, name,
			portletId, className);
	}

	public java.util.List<com.liferay.portal.model.PortletItem> getPortletItems(
		long groupId, java.lang.String className)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _portletItemLocalService.getPortletItems(groupId, className);
	}

	public java.util.List<com.liferay.portal.model.PortletItem> getPortletItems(
		long groupId, java.lang.String portletId, java.lang.String className)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _portletItemLocalService.getPortletItems(groupId, portletId,
			className);
	}

	public com.liferay.portal.model.PortletItem updatePortletItem(long userId,
		long groupId, java.lang.String name, java.lang.String portletId,
		java.lang.String className)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _portletItemLocalService.updatePortletItem(userId, groupId,
			name, portletId, className);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public PortletItemLocalService getWrappedPortletItemLocalService() {
		return _portletItemLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedPortletItemLocalService(
		PortletItemLocalService portletItemLocalService) {
		_portletItemLocalService = portletItemLocalService;
	}

	public PortletItemLocalService getWrappedService() {
		return _portletItemLocalService;
	}

	public void setWrappedService(
		PortletItemLocalService portletItemLocalService) {
		_portletItemLocalService = portletItemLocalService;
	}

	private PortletItemLocalService _portletItemLocalService;
}