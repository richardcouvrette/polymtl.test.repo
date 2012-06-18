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
 * The base model interface for the Permission service. Represents a row in the &quot;Permission_&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portal.model.impl.PermissionModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portal.model.impl.PermissionImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Permission
 * @see com.liferay.portal.model.impl.PermissionImpl
 * @see com.liferay.portal.model.impl.PermissionModelImpl
 * @generated
 */
public interface PermissionModel extends BaseModel<Permission> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a permission model instance should use the {@link Permission} interface instead.
	 */

	/**
	 * Returns the primary key of this permission.
	 *
	 * @return the primary key of this permission
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this permission.
	 *
	 * @param primaryKey the primary key of this permission
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the permission ID of this permission.
	 *
	 * @return the permission ID of this permission
	 */
	public long getPermissionId();

	/**
	 * Sets the permission ID of this permission.
	 *
	 * @param permissionId the permission ID of this permission
	 */
	public void setPermissionId(long permissionId);

	/**
	 * Returns the company ID of this permission.
	 *
	 * @return the company ID of this permission
	 */
	public long getCompanyId();

	/**
	 * Sets the company ID of this permission.
	 *
	 * @param companyId the company ID of this permission
	 */
	public void setCompanyId(long companyId);

	/**
	 * Returns the action ID of this permission.
	 *
	 * @return the action ID of this permission
	 */
	@AutoEscape
	public String getActionId();

	/**
	 * Sets the action ID of this permission.
	 *
	 * @param actionId the action ID of this permission
	 */
	public void setActionId(String actionId);

	/**
	 * Returns the resource ID of this permission.
	 *
	 * @return the resource ID of this permission
	 */
	public long getResourceId();

	/**
	 * Sets the resource ID of this permission.
	 *
	 * @param resourceId the resource ID of this permission
	 */
	public void setResourceId(long resourceId);

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

	public int compareTo(Permission permission);

	public int hashCode();

	public CacheModel<Permission> toCacheModel();

	public Permission toEscapedModel();

	public String toString();

	public String toXmlString();
}