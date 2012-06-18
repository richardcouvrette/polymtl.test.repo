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

package com.liferay.portal.service.impl;

import com.liferay.portal.kernel.concurrent.LockRegistry;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.PortalPreferences;
import com.liferay.portal.model.PortletConstants;
import com.liferay.portal.service.base.PortalPreferencesLocalServiceBaseImpl;
import com.liferay.portlet.BasePreferencesImpl;
import com.liferay.portlet.PortalPreferencesImpl;
import com.liferay.portlet.PortalPreferencesWrapper;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portlet.PortletPreferencesThreadLocal;

import java.io.Serializable;

import java.util.Map;
import java.util.concurrent.locks.Lock;

import javax.portlet.PortletPreferences;

/**
 * @author Alexander Chow
 */
public class PortalPreferencesLocalServiceImpl
	extends PortalPreferencesLocalServiceBaseImpl {

	public PortalPreferences addPortalPreferences(
			long companyId, long ownerId, int ownerType,
			String defaultPreferences)
		throws SystemException {

		long portalPreferencesId = counterLocalService.increment();

		PortalPreferences portalPreferences =
			portalPreferencesPersistence.create(portalPreferencesId);

		portalPreferences.setOwnerId(ownerId);
		portalPreferences.setOwnerType(ownerType);

		if (Validator.isNull(defaultPreferences)) {
			defaultPreferences = PortletConstants.DEFAULT_PREFERENCES;
		}

		portalPreferences.setPreferences(defaultPreferences);

		try {
			portalPreferencesPersistence.update(portalPreferences, false);
		}
		catch (SystemException se) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Add failed, fetch {ownerId=" + ownerId + ", ownerType=" +
						ownerType + "}");
			}

			portalPreferences = portalPreferencesPersistence.fetchByO_O(
				ownerId, ownerType, false);

			if (portalPreferences == null) {
				throw se;
			}
		}

		return portalPreferences;
	}

	public PortletPreferences getPreferences(
			long companyId, long ownerId, int ownerType)
		throws SystemException {

		return getPreferences(companyId, ownerId, ownerType, null);
	}

	public PortletPreferences getPreferences(
			long companyId, long ownerId, int ownerType,
			String defaultPreferences)
		throws SystemException {

		DB db = DBFactoryUtil.getDB();

		String dbType = db.getType();

		if (!dbType.equals(DB.TYPE_HYPERSONIC)) {
			return doGetPreferences(
				companyId, ownerId, ownerType, defaultPreferences);
		}

		StringBundler sb = new StringBundler(4);

		sb.append(ownerId);
		sb.append(StringPool.POUND);
		sb.append(ownerType);
		sb.append(StringPool.POUND);

		String groupName = getClass().getName();
		String key = sb.toString();

		Lock lock = LockRegistry.allocateLock(groupName, key);

		lock.lock();

		try {
			return doGetPreferences(
				companyId, ownerId, ownerType, defaultPreferences);
		}
		finally {
			lock.unlock();

			LockRegistry.freeLock(groupName, key);
		}
	}

	public PortalPreferences updatePreferences(
			long ownerId, int ownerType,
			com.liferay.portlet.PortalPreferences portalPreferences)
		throws SystemException {

		String xml = PortletPreferencesFactoryUtil.toXML(portalPreferences);

		return updatePreferences(ownerId, ownerType, xml);
	}

	public PortalPreferences updatePreferences(
			long ownerId, int ownerType, String xml)
		throws SystemException {

		PortalPreferences portalPreferences =
			portalPreferencesPersistence.fetchByO_O(ownerId, ownerType);

		if (portalPreferences == null) {
			long portalPreferencesId = counterLocalService.increment();

			portalPreferences = portalPreferencesPersistence.create(
				portalPreferencesId);

			portalPreferences.setOwnerId(ownerId);
			portalPreferences.setOwnerType(ownerType);
		}

		portalPreferences.setPreferences(xml);

		portalPreferencesPersistence.update(portalPreferences, false);

		PortletPreferencesLocalUtil.clearPreferencesPool(ownerId, ownerType);

		return portalPreferences;
	}

	protected PortletPreferences doGetPreferences(
			long companyId, long ownerId, int ownerType,
			String defaultPreferences)
		throws SystemException {

		Map<Serializable, BasePreferencesImpl> preferencesPool =
			PortletPreferencesLocalUtil.getPreferencesPool(ownerId, ownerType);

		PortalPreferencesImpl portalPreferencesImpl =
			(PortalPreferencesImpl)preferencesPool.get(companyId);

		if (portalPreferencesImpl == null) {
			PortalPreferences portalPreferences =
				portalPreferencesPersistence.fetchByO_O(ownerId, ownerType);

			if (portalPreferences == null) {
				if (PortletPreferencesThreadLocal.isStrict() &&
					Validator.isNull(defaultPreferences)) {

					return new PortalPreferencesWrapper(
						new PortalPreferencesImpl());
				}

				portalPreferences =
					portalPreferencesLocalService.addPortalPreferences(
						companyId, ownerId, ownerType, defaultPreferences);
			}

			portalPreferencesImpl =
				(PortalPreferencesImpl)PortletPreferencesFactoryUtil.fromXML(
					companyId, ownerId, ownerType,
					portalPreferences.getPreferences());

			synchronized (preferencesPool) {
				preferencesPool.put(companyId, portalPreferencesImpl);
			}
		}

		return new PortalPreferencesWrapper(
			(PortalPreferencesImpl)portalPreferencesImpl.clone());
	}

	private static Log _log = LogFactoryUtil.getLog(
		PortalPreferencesLocalServiceImpl.class);

}