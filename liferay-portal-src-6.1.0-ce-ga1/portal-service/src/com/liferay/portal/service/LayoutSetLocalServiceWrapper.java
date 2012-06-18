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
 * This class is a wrapper for {@link LayoutSetLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       LayoutSetLocalService
 * @generated
 */
public class LayoutSetLocalServiceWrapper implements LayoutSetLocalService,
	ServiceWrapper<LayoutSetLocalService> {
	public LayoutSetLocalServiceWrapper(
		LayoutSetLocalService layoutSetLocalService) {
		_layoutSetLocalService = layoutSetLocalService;
	}

	/**
	* Adds the layout set to the database. Also notifies the appropriate model listeners.
	*
	* @param layoutSet the layout set
	* @return the layout set that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.LayoutSet addLayoutSet(
		com.liferay.portal.model.LayoutSet layoutSet)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _layoutSetLocalService.addLayoutSet(layoutSet);
	}

	/**
	* Creates a new layout set with the primary key. Does not add the layout set to the database.
	*
	* @param layoutSetId the primary key for the new layout set
	* @return the new layout set
	*/
	public com.liferay.portal.model.LayoutSet createLayoutSet(long layoutSetId) {
		return _layoutSetLocalService.createLayoutSet(layoutSetId);
	}

	/**
	* Deletes the layout set with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param layoutSetId the primary key of the layout set
	* @throws PortalException if a layout set with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteLayoutSet(long layoutSetId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_layoutSetLocalService.deleteLayoutSet(layoutSetId);
	}

	/**
	* Deletes the layout set from the database. Also notifies the appropriate model listeners.
	*
	* @param layoutSet the layout set
	* @throws SystemException if a system exception occurred
	*/
	public void deleteLayoutSet(com.liferay.portal.model.LayoutSet layoutSet)
		throws com.liferay.portal.kernel.exception.SystemException {
		_layoutSetLocalService.deleteLayoutSet(layoutSet);
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
		return _layoutSetLocalService.dynamicQuery(dynamicQuery);
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
		return _layoutSetLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _layoutSetLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _layoutSetLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.portal.model.LayoutSet fetchLayoutSet(long layoutSetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _layoutSetLocalService.fetchLayoutSet(layoutSetId);
	}

	/**
	* Returns the layout set with the primary key.
	*
	* @param layoutSetId the primary key of the layout set
	* @return the layout set
	* @throws PortalException if a layout set with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.LayoutSet getLayoutSet(long layoutSetId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutSetLocalService.getLayoutSet(layoutSetId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutSetLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the layout sets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of layout sets
	* @param end the upper bound of the range of layout sets (not inclusive)
	* @return the range of layout sets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.LayoutSet> getLayoutSets(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _layoutSetLocalService.getLayoutSets(start, end);
	}

	/**
	* Returns the number of layout sets.
	*
	* @return the number of layout sets
	* @throws SystemException if a system exception occurred
	*/
	public int getLayoutSetsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _layoutSetLocalService.getLayoutSetsCount();
	}

	/**
	* Updates the layout set in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param layoutSet the layout set
	* @return the layout set that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.LayoutSet updateLayoutSet(
		com.liferay.portal.model.LayoutSet layoutSet)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _layoutSetLocalService.updateLayoutSet(layoutSet);
	}

	/**
	* Updates the layout set in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param layoutSet the layout set
	* @param merge whether to merge the layout set with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the layout set that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.LayoutSet updateLayoutSet(
		com.liferay.portal.model.LayoutSet layoutSet, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _layoutSetLocalService.updateLayoutSet(layoutSet, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _layoutSetLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_layoutSetLocalService.setBeanIdentifier(beanIdentifier);
	}

	public com.liferay.portal.model.LayoutSet addLayoutSet(long groupId,
		boolean privateLayout)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutSetLocalService.addLayoutSet(groupId, privateLayout);
	}

	public void deleteLayoutSet(long groupId, boolean privateLayout,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_layoutSetLocalService.deleteLayoutSet(groupId, privateLayout,
			serviceContext);
	}

	public com.liferay.portal.model.LayoutSet fetchLayoutSet(
		java.lang.String virtualHostname)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _layoutSetLocalService.fetchLayoutSet(virtualHostname);
	}

	public com.liferay.portal.model.LayoutSet getLayoutSet(long groupId,
		boolean privateLayout)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutSetLocalService.getLayoutSet(groupId, privateLayout);
	}

	public com.liferay.portal.model.LayoutSet getLayoutSet(
		java.lang.String virtualHostname)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutSetLocalService.getLayoutSet(virtualHostname);
	}

	public java.util.List<com.liferay.portal.model.LayoutSet> getLayoutSetsByLayoutSetPrototypeUuid(
		java.lang.String layoutSetPrototypeUuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _layoutSetLocalService.getLayoutSetsByLayoutSetPrototypeUuid(layoutSetPrototypeUuid);
	}

	public void updateLayoutSetPrototypeLinkEnabled(long groupId,
		boolean privateLayout, boolean layoutSetPrototypeLinkEnabled)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_layoutSetLocalService.updateLayoutSetPrototypeLinkEnabled(groupId,
			privateLayout, layoutSetPrototypeLinkEnabled);
	}

	public void updateLogo(long groupId, boolean privateLayout, boolean logo,
		java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_layoutSetLocalService.updateLogo(groupId, privateLayout, logo, file);
	}

	public void updateLogo(long groupId, boolean privateLayout, boolean logo,
		java.io.InputStream is)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_layoutSetLocalService.updateLogo(groupId, privateLayout, logo, is);
	}

	public void updateLogo(long groupId, boolean privateLayout, boolean logo,
		java.io.InputStream is, boolean cleanUpStream)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_layoutSetLocalService.updateLogo(groupId, privateLayout, logo, is,
			cleanUpStream);
	}

	public void updateLookAndFeel(long groupId, java.lang.String themeId,
		java.lang.String colorSchemeId, java.lang.String css, boolean wapTheme)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_layoutSetLocalService.updateLookAndFeel(groupId, themeId,
			colorSchemeId, css, wapTheme);
	}

	public com.liferay.portal.model.LayoutSet updateLookAndFeel(long groupId,
		boolean privateLayout, java.lang.String themeId,
		java.lang.String colorSchemeId, java.lang.String css, boolean wapTheme)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutSetLocalService.updateLookAndFeel(groupId, privateLayout,
			themeId, colorSchemeId, css, wapTheme);
	}

	public com.liferay.portal.model.LayoutSet updatePageCount(long groupId,
		boolean privateLayout)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutSetLocalService.updatePageCount(groupId, privateLayout);
	}

	public com.liferay.portal.model.LayoutSet updateSettings(long groupId,
		boolean privateLayout, java.lang.String settings)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutSetLocalService.updateSettings(groupId, privateLayout,
			settings);
	}

	public com.liferay.portal.model.LayoutSet updateVirtualHost(long groupId,
		boolean privateLayout, java.lang.String virtualHostname)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutSetLocalService.updateVirtualHost(groupId, privateLayout,
			virtualHostname);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public LayoutSetLocalService getWrappedLayoutSetLocalService() {
		return _layoutSetLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedLayoutSetLocalService(
		LayoutSetLocalService layoutSetLocalService) {
		_layoutSetLocalService = layoutSetLocalService;
	}

	public LayoutSetLocalService getWrappedService() {
		return _layoutSetLocalService;
	}

	public void setWrappedService(LayoutSetLocalService layoutSetLocalService) {
		_layoutSetLocalService = layoutSetLocalService;
	}

	private LayoutSetLocalService _layoutSetLocalService;
}