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
 * This class is a wrapper for {@link PluginSettingLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PluginSettingLocalService
 * @generated
 */
public class PluginSettingLocalServiceWrapper
	implements PluginSettingLocalService,
		ServiceWrapper<PluginSettingLocalService> {
	public PluginSettingLocalServiceWrapper(
		PluginSettingLocalService pluginSettingLocalService) {
		_pluginSettingLocalService = pluginSettingLocalService;
	}

	/**
	* Adds the plugin setting to the database. Also notifies the appropriate model listeners.
	*
	* @param pluginSetting the plugin setting
	* @return the plugin setting that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.PluginSetting addPluginSetting(
		com.liferay.portal.model.PluginSetting pluginSetting)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pluginSettingLocalService.addPluginSetting(pluginSetting);
	}

	/**
	* Creates a new plugin setting with the primary key. Does not add the plugin setting to the database.
	*
	* @param pluginSettingId the primary key for the new plugin setting
	* @return the new plugin setting
	*/
	public com.liferay.portal.model.PluginSetting createPluginSetting(
		long pluginSettingId) {
		return _pluginSettingLocalService.createPluginSetting(pluginSettingId);
	}

	/**
	* Deletes the plugin setting with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param pluginSettingId the primary key of the plugin setting
	* @throws PortalException if a plugin setting with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deletePluginSetting(long pluginSettingId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_pluginSettingLocalService.deletePluginSetting(pluginSettingId);
	}

	/**
	* Deletes the plugin setting from the database. Also notifies the appropriate model listeners.
	*
	* @param pluginSetting the plugin setting
	* @throws SystemException if a system exception occurred
	*/
	public void deletePluginSetting(
		com.liferay.portal.model.PluginSetting pluginSetting)
		throws com.liferay.portal.kernel.exception.SystemException {
		_pluginSettingLocalService.deletePluginSetting(pluginSetting);
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
		return _pluginSettingLocalService.dynamicQuery(dynamicQuery);
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
		return _pluginSettingLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _pluginSettingLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
		return _pluginSettingLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.portal.model.PluginSetting fetchPluginSetting(
		long pluginSettingId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pluginSettingLocalService.fetchPluginSetting(pluginSettingId);
	}

	/**
	* Returns the plugin setting with the primary key.
	*
	* @param pluginSettingId the primary key of the plugin setting
	* @return the plugin setting
	* @throws PortalException if a plugin setting with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.PluginSetting getPluginSetting(
		long pluginSettingId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _pluginSettingLocalService.getPluginSetting(pluginSettingId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _pluginSettingLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the plugin settings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of plugin settings
	* @param end the upper bound of the range of plugin settings (not inclusive)
	* @return the range of plugin settings
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.PluginSetting> getPluginSettings(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pluginSettingLocalService.getPluginSettings(start, end);
	}

	/**
	* Returns the number of plugin settings.
	*
	* @return the number of plugin settings
	* @throws SystemException if a system exception occurred
	*/
	public int getPluginSettingsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pluginSettingLocalService.getPluginSettingsCount();
	}

	/**
	* Updates the plugin setting in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param pluginSetting the plugin setting
	* @return the plugin setting that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.PluginSetting updatePluginSetting(
		com.liferay.portal.model.PluginSetting pluginSetting)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pluginSettingLocalService.updatePluginSetting(pluginSetting);
	}

	/**
	* Updates the plugin setting in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param pluginSetting the plugin setting
	* @param merge whether to merge the plugin setting with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the plugin setting that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.PluginSetting updatePluginSetting(
		com.liferay.portal.model.PluginSetting pluginSetting, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pluginSettingLocalService.updatePluginSetting(pluginSetting,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _pluginSettingLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_pluginSettingLocalService.setBeanIdentifier(beanIdentifier);
	}

	public void checkPermission(long userId, java.lang.String pluginId,
		java.lang.String pluginType)
		throws com.liferay.portal.kernel.exception.PortalException {
		_pluginSettingLocalService.checkPermission(userId, pluginId, pluginType);
	}

	public com.liferay.portal.model.PluginSetting getDefaultPluginSetting() {
		return _pluginSettingLocalService.getDefaultPluginSetting();
	}

	public com.liferay.portal.model.PluginSetting getPluginSetting(
		long companyId, java.lang.String pluginId, java.lang.String pluginType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pluginSettingLocalService.getPluginSetting(companyId, pluginId,
			pluginType);
	}

	public boolean hasPermission(long userId, java.lang.String pluginId,
		java.lang.String pluginType) {
		return _pluginSettingLocalService.hasPermission(userId, pluginId,
			pluginType);
	}

	public com.liferay.portal.model.PluginSetting updatePluginSetting(
		long companyId, java.lang.String pluginId, java.lang.String pluginType,
		java.lang.String roles, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pluginSettingLocalService.updatePluginSetting(companyId,
			pluginId, pluginType, roles, active);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public PluginSettingLocalService getWrappedPluginSettingLocalService() {
		return _pluginSettingLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedPluginSettingLocalService(
		PluginSettingLocalService pluginSettingLocalService) {
		_pluginSettingLocalService = pluginSettingLocalService;
	}

	public PluginSettingLocalService getWrappedService() {
		return _pluginSettingLocalService;
	}

	public void setWrappedService(
		PluginSettingLocalService pluginSettingLocalService) {
		_pluginSettingLocalService = pluginSettingLocalService;
	}

	private PluginSettingLocalService _pluginSettingLocalService;
}