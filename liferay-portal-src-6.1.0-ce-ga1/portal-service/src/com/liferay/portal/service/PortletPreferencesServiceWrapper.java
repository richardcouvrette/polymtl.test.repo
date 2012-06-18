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
 * This class is a wrapper for {@link PortletPreferencesService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PortletPreferencesService
 * @generated
 */
public class PortletPreferencesServiceWrapper
	implements PortletPreferencesService,
		ServiceWrapper<PortletPreferencesService> {
	public PortletPreferencesServiceWrapper(
		PortletPreferencesService portletPreferencesService) {
		_portletPreferencesService = portletPreferencesService;
	}

	public void deleteArchivedPreferences(long portletItemId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_portletPreferencesService.deleteArchivedPreferences(portletItemId);
	}

	public void restoreArchivedPreferences(long groupId,
		com.liferay.portal.model.Layout layout, java.lang.String portletId,
		long portletItemId, javax.portlet.PortletPreferences preferences)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_portletPreferencesService.restoreArchivedPreferences(groupId, layout,
			portletId, portletItemId, preferences);
	}

	public void restoreArchivedPreferences(long groupId,
		com.liferay.portal.model.Layout layout, java.lang.String portletId,
		com.liferay.portal.model.PortletItem portletItem,
		javax.portlet.PortletPreferences preferences)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_portletPreferencesService.restoreArchivedPreferences(groupId, layout,
			portletId, portletItem, preferences);
	}

	public void restoreArchivedPreferences(long groupId, java.lang.String name,
		com.liferay.portal.model.Layout layout, java.lang.String portletId,
		javax.portlet.PortletPreferences preferences)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_portletPreferencesService.restoreArchivedPreferences(groupId, name,
			layout, portletId, preferences);
	}

	public void updateArchivePreferences(long userId, long groupId,
		java.lang.String name, java.lang.String portletId,
		javax.portlet.PortletPreferences preferences)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_portletPreferencesService.updateArchivePreferences(userId, groupId,
			name, portletId, preferences);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public PortletPreferencesService getWrappedPortletPreferencesService() {
		return _portletPreferencesService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedPortletPreferencesService(
		PortletPreferencesService portletPreferencesService) {
		_portletPreferencesService = portletPreferencesService;
	}

	public PortletPreferencesService getWrappedService() {
		return _portletPreferencesService;
	}

	public void setWrappedService(
		PortletPreferencesService portletPreferencesService) {
		_portletPreferencesService = portletPreferencesService;
	}

	private PortletPreferencesService _portletPreferencesService;
}