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

package com.liferay.portal.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

/**
 * The base model interface for the PortalPreferences service. Represents a row in the &quot;PortalPreferences&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portal.model.impl.PortalPreferencesModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portal.model.impl.PortalPreferencesImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PortalPreferences
 * @see com.liferay.portal.model.impl.PortalPreferencesImpl
 * @see com.liferay.portal.model.impl.PortalPreferencesModelImpl
 * @generated
 */
public interface PortalPreferencesModel extends BaseModel<PortalPreferences> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a portal preferences model instance should use the {@link PortalPreferences} interface instead.
	 */

	/**
	 * Returns the primary key of this portal preferences.
	 *
	 * @return the primary key of this portal preferences
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this portal preferences.
	 *
	 * @param primaryKey the primary key of this portal preferences
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the portal preferences ID of this portal preferences.
	 *
	 * @return the portal preferences ID of this portal preferences
	 */
	public long getPortalPreferencesId();

	/**
	 * Sets the portal preferences ID of this portal preferences.
	 *
	 * @param portalPreferencesId the portal preferences ID of this portal preferences
	 */
	public void setPortalPreferencesId(long portalPreferencesId);

	/**
	 * Returns the owner ID of this portal preferences.
	 *
	 * @return the owner ID of this portal preferences
	 */
	public long getOwnerId();

	/**
	 * Sets the owner ID of this portal preferences.
	 *
	 * @param ownerId the owner ID of this portal preferences
	 */
	public void setOwnerId(long ownerId);

	/**
	 * Returns the owner type of this portal preferences.
	 *
	 * @return the owner type of this portal preferences
	 */
	public int getOwnerType();

	/**
	 * Sets the owner type of this portal preferences.
	 *
	 * @param ownerType the owner type of this portal preferences
	 */
	public void setOwnerType(int ownerType);

	/**
	 * Returns the preferences of this portal preferences.
	 *
	 * @return the preferences of this portal preferences
	 */
	@AutoEscape
	public String getPreferences();

	/**
	 * Sets the preferences of this portal preferences.
	 *
	 * @param preferences the preferences of this portal preferences
	 */
	public void setPreferences(String preferences);

	public boolean isNew();

	public void setNew(boolean n);

	public boolean isCachedModel();

	public void setCachedModel(boolean cachedModel);

	public boolean isEscapedModel();

	public Serializable getPrimaryKeyObj();

	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	public ExpandoBridge getExpandoBridge();

	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	public Object clone();

	public int compareTo(PortalPreferences portalPreferences);

	public int hashCode();

	public CacheModel<PortalPreferences> toCacheModel();

	public PortalPreferences toEscapedModel();

	public String toString();

	public String toXmlString();
}