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

package com.liferay.portlet.expando.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ExpandoTableLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ExpandoTableLocalService
 * @generated
 */
public class ExpandoTableLocalServiceWrapper implements ExpandoTableLocalService,
	ServiceWrapper<ExpandoTableLocalService> {
	public ExpandoTableLocalServiceWrapper(
		ExpandoTableLocalService expandoTableLocalService) {
		_expandoTableLocalService = expandoTableLocalService;
	}

	/**
	* Adds the expando table to the database. Also notifies the appropriate model listeners.
	*
	* @param expandoTable the expando table
	* @return the expando table that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.expando.model.ExpandoTable addExpandoTable(
		com.liferay.portlet.expando.model.ExpandoTable expandoTable)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _expandoTableLocalService.addExpandoTable(expandoTable);
	}

	/**
	* Creates a new expando table with the primary key. Does not add the expando table to the database.
	*
	* @param tableId the primary key for the new expando table
	* @return the new expando table
	*/
	public com.liferay.portlet.expando.model.ExpandoTable createExpandoTable(
		long tableId) {
		return _expandoTableLocalService.createExpandoTable(tableId);
	}

	/**
	* Deletes the expando table with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param tableId the primary key of the expando table
	* @throws PortalException if a expando table with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteExpandoTable(long tableId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_expandoTableLocalService.deleteExpandoTable(tableId);
	}

	/**
	* Deletes the expando table from the database. Also notifies the appropriate model listeners.
	*
	* @param expandoTable the expando table
	* @throws SystemException if a system exception occurred
	*/
	public void deleteExpandoTable(
		com.liferay.portlet.expando.model.ExpandoTable expandoTable)
		throws com.liferay.portal.kernel.exception.SystemException {
		_expandoTableLocalService.deleteExpandoTable(expandoTable);
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
		return _expandoTableLocalService.dynamicQuery(dynamicQuery);
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
		return _expandoTableLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _expandoTableLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _expandoTableLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.portlet.expando.model.ExpandoTable fetchExpandoTable(
		long tableId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _expandoTableLocalService.fetchExpandoTable(tableId);
	}

	/**
	* Returns the expando table with the primary key.
	*
	* @param tableId the primary key of the expando table
	* @return the expando table
	* @throws PortalException if a expando table with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.expando.model.ExpandoTable getExpandoTable(
		long tableId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _expandoTableLocalService.getExpandoTable(tableId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _expandoTableLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the expando tables.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of expando tables
	* @param end the upper bound of the range of expando tables (not inclusive)
	* @return the range of expando tables
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portlet.expando.model.ExpandoTable> getExpandoTables(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _expandoTableLocalService.getExpandoTables(start, end);
	}

	/**
	* Returns the number of expando tables.
	*
	* @return the number of expando tables
	* @throws SystemException if a system exception occurred
	*/
	public int getExpandoTablesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _expandoTableLocalService.getExpandoTablesCount();
	}

	/**
	* Updates the expando table in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param expandoTable the expando table
	* @return the expando table that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.expando.model.ExpandoTable updateExpandoTable(
		com.liferay.portlet.expando.model.ExpandoTable expandoTable)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _expandoTableLocalService.updateExpandoTable(expandoTable);
	}

	/**
	* Updates the expando table in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param expandoTable the expando table
	* @param merge whether to merge the expando table with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the expando table that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.expando.model.ExpandoTable updateExpandoTable(
		com.liferay.portlet.expando.model.ExpandoTable expandoTable,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _expandoTableLocalService.updateExpandoTable(expandoTable, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _expandoTableLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_expandoTableLocalService.setBeanIdentifier(beanIdentifier);
	}

	public com.liferay.portlet.expando.model.ExpandoTable addDefaultTable(
		long companyId, long classNameId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _expandoTableLocalService.addDefaultTable(companyId, classNameId);
	}

	public com.liferay.portlet.expando.model.ExpandoTable addDefaultTable(
		long companyId, java.lang.String className)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _expandoTableLocalService.addDefaultTable(companyId, className);
	}

	public com.liferay.portlet.expando.model.ExpandoTable addTable(
		long companyId, long classNameId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _expandoTableLocalService.addTable(companyId, classNameId, name);
	}

	/**
	* @deprecated {@link #addTable(long, long, String)}
	*/
	public com.liferay.portlet.expando.model.ExpandoTable addTable(
		long classNameId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _expandoTableLocalService.addTable(classNameId, name);
	}

	public com.liferay.portlet.expando.model.ExpandoTable addTable(
		long companyId, java.lang.String className, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _expandoTableLocalService.addTable(companyId, className, name);
	}

	/**
	* @deprecated {@link #addTable(long, String, String)}
	*/
	public com.liferay.portlet.expando.model.ExpandoTable addTable(
		java.lang.String className, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _expandoTableLocalService.addTable(className, name);
	}

	public void deleteTable(
		com.liferay.portlet.expando.model.ExpandoTable table)
		throws com.liferay.portal.kernel.exception.SystemException {
		_expandoTableLocalService.deleteTable(table);
	}

	public void deleteTable(long tableId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_expandoTableLocalService.deleteTable(tableId);
	}

	public void deleteTable(long companyId, long classNameId,
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_expandoTableLocalService.deleteTable(companyId, classNameId, name);
	}

	public void deleteTable(long companyId, java.lang.String className,
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_expandoTableLocalService.deleteTable(companyId, className, name);
	}

	public void deleteTables(long companyId, long classNameId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_expandoTableLocalService.deleteTables(companyId, classNameId);
	}

	public void deleteTables(long companyId, java.lang.String className)
		throws com.liferay.portal.kernel.exception.SystemException {
		_expandoTableLocalService.deleteTables(companyId, className);
	}

	public com.liferay.portlet.expando.model.ExpandoTable fetchDefaultTable(
		long companyId, long classNameId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _expandoTableLocalService.fetchDefaultTable(companyId,
			classNameId);
	}

	public com.liferay.portlet.expando.model.ExpandoTable fetchDefaultTable(
		long companyId, java.lang.String className)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _expandoTableLocalService.fetchDefaultTable(companyId, className);
	}

	public com.liferay.portlet.expando.model.ExpandoTable fetchTable(
		long companyId, long classNameId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _expandoTableLocalService.fetchTable(companyId, classNameId, name);
	}

	public com.liferay.portlet.expando.model.ExpandoTable getDefaultTable(
		long companyId, long classNameId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _expandoTableLocalService.getDefaultTable(companyId, classNameId);
	}

	public com.liferay.portlet.expando.model.ExpandoTable getDefaultTable(
		long companyId, java.lang.String className)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _expandoTableLocalService.getDefaultTable(companyId, className);
	}

	public com.liferay.portlet.expando.model.ExpandoTable getTable(long tableId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _expandoTableLocalService.getTable(tableId);
	}

	public com.liferay.portlet.expando.model.ExpandoTable getTable(
		long companyId, long classNameId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _expandoTableLocalService.getTable(companyId, classNameId, name);
	}

	/**
	* @deprecated {@link #getTable(long, long, String)}
	*/
	public com.liferay.portlet.expando.model.ExpandoTable getTable(
		long classNameId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _expandoTableLocalService.getTable(classNameId, name);
	}

	public com.liferay.portlet.expando.model.ExpandoTable getTable(
		long companyId, java.lang.String className, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _expandoTableLocalService.getTable(companyId, className, name);
	}

	/**
	* @deprecated {@link #getTable(long, String, String)}
	*/
	public com.liferay.portlet.expando.model.ExpandoTable getTable(
		java.lang.String className, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _expandoTableLocalService.getTable(className, name);
	}

	public java.util.List<com.liferay.portlet.expando.model.ExpandoTable> getTables(
		long companyId, long classNameId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _expandoTableLocalService.getTables(companyId, classNameId);
	}

	public java.util.List<com.liferay.portlet.expando.model.ExpandoTable> getTables(
		long companyId, java.lang.String className)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _expandoTableLocalService.getTables(companyId, className);
	}

	public com.liferay.portlet.expando.model.ExpandoTable updateTable(
		long tableId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _expandoTableLocalService.updateTable(tableId, name);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public ExpandoTableLocalService getWrappedExpandoTableLocalService() {
		return _expandoTableLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedExpandoTableLocalService(
		ExpandoTableLocalService expandoTableLocalService) {
		_expandoTableLocalService = expandoTableLocalService;
	}

	public ExpandoTableLocalService getWrappedService() {
		return _expandoTableLocalService;
	}

	public void setWrappedService(
		ExpandoTableLocalService expandoTableLocalService) {
		_expandoTableLocalService = expandoTableLocalService;
	}

	private ExpandoTableLocalService _expandoTableLocalService;
}