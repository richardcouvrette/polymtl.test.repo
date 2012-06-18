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

import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.persistence.OrgGroupPermissionPK;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

/**
 * The base model interface for the OrgGroupPermission service. Represents a row in the &quot;OrgGroupPermission&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portal.model.impl.OrgGroupPermissionModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portal.model.impl.OrgGroupPermissionImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OrgGroupPermission
 * @see com.liferay.portal.model.impl.OrgGroupPermissionImpl
 * @see com.liferay.portal.model.impl.OrgGroupPermissionModelImpl
 * @generated
 */
public interface OrgGroupPermissionModel extends BaseModel<OrgGroupPermission> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a org group permission model instance should use the {@link OrgGroupPermission} interface instead.
	 */

	/**
	 * Returns the primary key of this org group permission.
	 *
	 * @return the primary key of this org group permission
	 */
	public OrgGroupPermissionPK getPrimaryKey();

	/**
	 * Sets the primary key of this org group permission.
	 *
	 * @param primaryKey the primary key of this org group permission
	 */
	public void setPrimaryKey(OrgGroupPermissionPK primaryKey);

	/**
	 * Returns the organization ID of this org group permission.
	 *
	 * @return the organization ID of this org group permission
	 */
	public long getOrganizationId();

	/**
	 * Sets the organization ID of this org group permission.
	 *
	 * @param organizationId the organization ID of this org group permission
	 */
	public void setOrganizationId(long organizationId);

	/**
	 * Returns the group ID of this org group permission.
	 *
	 * @return the group ID of this org group permission
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this org group permission.
	 *
	 * @param groupId the group ID of this org group permission
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the permission ID of this org group permission.
	 *
	 * @return the permission ID of this org group permission
	 */
	public long getPermissionId();

	/**
	 * Sets the permission ID of this org group permission.
	 *
	 * @param permissionId the permission ID of this org group permission
	 */
	public void setPermissionId(long permissionId);

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

	public int compareTo(OrgGroupPermission orgGroupPermission);

	public int hashCode();

	public CacheModel<OrgGroupPermission> toCacheModel();

	public OrgGroupPermission toEscapedModel();

	public String toString();

	public String toXmlString();
}