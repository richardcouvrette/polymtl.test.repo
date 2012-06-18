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
 * The base model interface for the Organization service. Represents a row in the &quot;Organization_&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portal.model.impl.OrganizationModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portal.model.impl.OrganizationImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Organization
 * @see com.liferay.portal.model.impl.OrganizationImpl
 * @see com.liferay.portal.model.impl.OrganizationModelImpl
 * @generated
 */
public interface OrganizationModel extends BaseModel<Organization> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a organization model instance should use the {@link Organization} interface instead.
	 */

	/**
	 * Returns the primary key of this organization.
	 *
	 * @return the primary key of this organization
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this organization.
	 *
	 * @param primaryKey the primary key of this organization
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the organization ID of this organization.
	 *
	 * @return the organization ID of this organization
	 */
	public long getOrganizationId();

	/**
	 * Sets the organization ID of this organization.
	 *
	 * @param organizationId the organization ID of this organization
	 */
	public void setOrganizationId(long organizationId);

	/**
	 * Returns the company ID of this organization.
	 *
	 * @return the company ID of this organization
	 */
	public long getCompanyId();

	/**
	 * Sets the company ID of this organization.
	 *
	 * @param companyId the company ID of this organization
	 */
	public void setCompanyId(long companyId);

	/**
	 * Returns the parent organization ID of this organization.
	 *
	 * @return the parent organization ID of this organization
	 */
	public long getParentOrganizationId();

	/**
	 * Sets the parent organization ID of this organization.
	 *
	 * @param parentOrganizationId the parent organization ID of this organization
	 */
	public void setParentOrganizationId(long parentOrganizationId);

	/**
	 * Returns the tree path of this organization.
	 *
	 * @return the tree path of this organization
	 */
	@AutoEscape
	public String getTreePath();

	/**
	 * Sets the tree path of this organization.
	 *
	 * @param treePath the tree path of this organization
	 */
	public void setTreePath(String treePath);

	/**
	 * Returns the name of this organization.
	 *
	 * @return the name of this organization
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this organization.
	 *
	 * @param name the name of this organization
	 */
	public void setName(String name);

	/**
	 * Returns the type of this organization.
	 *
	 * @return the type of this organization
	 */
	public String getType();

	/**
	 * Sets the type of this organization.
	 *
	 * @param type the type of this organization
	 */
	public void setType(String type);

	/**
	 * Returns the recursable of this organization.
	 *
	 * @return the recursable of this organization
	 */
	public boolean getRecursable();

	/**
	 * Returns <code>true</code> if this organization is recursable.
	 *
	 * @return <code>true</code> if this organization is recursable; <code>false</code> otherwise
	 */
	public boolean isRecursable();

	/**
	 * Sets whether this organization is recursable.
	 *
	 * @param recursable the recursable of this organization
	 */
	public void setRecursable(boolean recursable);

	/**
	 * Returns the region ID of this organization.
	 *
	 * @return the region ID of this organization
	 */
	public long getRegionId();

	/**
	 * Sets the region ID of this organization.
	 *
	 * @param regionId the region ID of this organization
	 */
	public void setRegionId(long regionId);

	/**
	 * Returns the country ID of this organization.
	 *
	 * @return the country ID of this organization
	 */
	public long getCountryId();

	/**
	 * Sets the country ID of this organization.
	 *
	 * @param countryId the country ID of this organization
	 */
	public void setCountryId(long countryId);

	/**
	 * Returns the status ID of this organization.
	 *
	 * @return the status ID of this organization
	 */
	public int getStatusId();

	/**
	 * Sets the status ID of this organization.
	 *
	 * @param statusId the status ID of this organization
	 */
	public void setStatusId(int statusId);

	/**
	 * Returns the comments of this organization.
	 *
	 * @return the comments of this organization
	 */
	@AutoEscape
	public String getComments();

	/**
	 * Sets the comments of this organization.
	 *
	 * @param comments the comments of this organization
	 */
	public void setComments(String comments);

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

	public int compareTo(Organization organization);

	public int hashCode();

	public CacheModel<Organization> toCacheModel();

	public Organization toEscapedModel();

	public String toString();

	public String toXmlString();
}