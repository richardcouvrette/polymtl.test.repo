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

/**
 * <p>
 * This class is a wrapper for {@link WorkflowInstanceLink}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       WorkflowInstanceLink
 * @generated
 */
public class WorkflowInstanceLinkWrapper implements WorkflowInstanceLink,
	ModelWrapper<WorkflowInstanceLink> {
	public WorkflowInstanceLinkWrapper(
		WorkflowInstanceLink workflowInstanceLink) {
		_workflowInstanceLink = workflowInstanceLink;
	}

	public Class<?> getModelClass() {
		return WorkflowInstanceLink.class;
	}

	public String getModelClassName() {
		return WorkflowInstanceLink.class.getName();
	}

	/**
	* Returns the primary key of this workflow instance link.
	*
	* @return the primary key of this workflow instance link
	*/
	public long getPrimaryKey() {
		return _workflowInstanceLink.getPrimaryKey();
	}

	/**
	* Sets the primary key of this workflow instance link.
	*
	* @param primaryKey the primary key of this workflow instance link
	*/
	public void setPrimaryKey(long primaryKey) {
		_workflowInstanceLink.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the workflow instance link ID of this workflow instance link.
	*
	* @return the workflow instance link ID of this workflow instance link
	*/
	public long getWorkflowInstanceLinkId() {
		return _workflowInstanceLink.getWorkflowInstanceLinkId();
	}

	/**
	* Sets the workflow instance link ID of this workflow instance link.
	*
	* @param workflowInstanceLinkId the workflow instance link ID of this workflow instance link
	*/
	public void setWorkflowInstanceLinkId(long workflowInstanceLinkId) {
		_workflowInstanceLink.setWorkflowInstanceLinkId(workflowInstanceLinkId);
	}

	/**
	* Returns the group ID of this workflow instance link.
	*
	* @return the group ID of this workflow instance link
	*/
	public long getGroupId() {
		return _workflowInstanceLink.getGroupId();
	}

	/**
	* Sets the group ID of this workflow instance link.
	*
	* @param groupId the group ID of this workflow instance link
	*/
	public void setGroupId(long groupId) {
		_workflowInstanceLink.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this workflow instance link.
	*
	* @return the company ID of this workflow instance link
	*/
	public long getCompanyId() {
		return _workflowInstanceLink.getCompanyId();
	}

	/**
	* Sets the company ID of this workflow instance link.
	*
	* @param companyId the company ID of this workflow instance link
	*/
	public void setCompanyId(long companyId) {
		_workflowInstanceLink.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this workflow instance link.
	*
	* @return the user ID of this workflow instance link
	*/
	public long getUserId() {
		return _workflowInstanceLink.getUserId();
	}

	/**
	* Sets the user ID of this workflow instance link.
	*
	* @param userId the user ID of this workflow instance link
	*/
	public void setUserId(long userId) {
		_workflowInstanceLink.setUserId(userId);
	}

	/**
	* Returns the user uuid of this workflow instance link.
	*
	* @return the user uuid of this workflow instance link
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _workflowInstanceLink.getUserUuid();
	}

	/**
	* Sets the user uuid of this workflow instance link.
	*
	* @param userUuid the user uuid of this workflow instance link
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_workflowInstanceLink.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this workflow instance link.
	*
	* @return the user name of this workflow instance link
	*/
	public java.lang.String getUserName() {
		return _workflowInstanceLink.getUserName();
	}

	/**
	* Sets the user name of this workflow instance link.
	*
	* @param userName the user name of this workflow instance link
	*/
	public void setUserName(java.lang.String userName) {
		_workflowInstanceLink.setUserName(userName);
	}

	/**
	* Returns the create date of this workflow instance link.
	*
	* @return the create date of this workflow instance link
	*/
	public java.util.Date getCreateDate() {
		return _workflowInstanceLink.getCreateDate();
	}

	/**
	* Sets the create date of this workflow instance link.
	*
	* @param createDate the create date of this workflow instance link
	*/
	public void setCreateDate(java.util.Date createDate) {
		_workflowInstanceLink.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this workflow instance link.
	*
	* @return the modified date of this workflow instance link
	*/
	public java.util.Date getModifiedDate() {
		return _workflowInstanceLink.getModifiedDate();
	}

	/**
	* Sets the modified date of this workflow instance link.
	*
	* @param modifiedDate the modified date of this workflow instance link
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_workflowInstanceLink.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the fully qualified class name of this workflow instance link.
	*
	* @return the fully qualified class name of this workflow instance link
	*/
	public java.lang.String getClassName() {
		return _workflowInstanceLink.getClassName();
	}

	/**
	* Returns the class name ID of this workflow instance link.
	*
	* @return the class name ID of this workflow instance link
	*/
	public long getClassNameId() {
		return _workflowInstanceLink.getClassNameId();
	}

	/**
	* Sets the class name ID of this workflow instance link.
	*
	* @param classNameId the class name ID of this workflow instance link
	*/
	public void setClassNameId(long classNameId) {
		_workflowInstanceLink.setClassNameId(classNameId);
	}

	/**
	* Returns the class p k of this workflow instance link.
	*
	* @return the class p k of this workflow instance link
	*/
	public long getClassPK() {
		return _workflowInstanceLink.getClassPK();
	}

	/**
	* Sets the class p k of this workflow instance link.
	*
	* @param classPK the class p k of this workflow instance link
	*/
	public void setClassPK(long classPK) {
		_workflowInstanceLink.setClassPK(classPK);
	}

	/**
	* Returns the workflow instance ID of this workflow instance link.
	*
	* @return the workflow instance ID of this workflow instance link
	*/
	public long getWorkflowInstanceId() {
		return _workflowInstanceLink.getWorkflowInstanceId();
	}

	/**
	* Sets the workflow instance ID of this workflow instance link.
	*
	* @param workflowInstanceId the workflow instance ID of this workflow instance link
	*/
	public void setWorkflowInstanceId(long workflowInstanceId) {
		_workflowInstanceLink.setWorkflowInstanceId(workflowInstanceId);
	}

	public boolean isNew() {
		return _workflowInstanceLink.isNew();
	}

	public void setNew(boolean n) {
		_workflowInstanceLink.setNew(n);
	}

	public boolean isCachedModel() {
		return _workflowInstanceLink.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_workflowInstanceLink.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _workflowInstanceLink.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _workflowInstanceLink.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_workflowInstanceLink.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _workflowInstanceLink.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_workflowInstanceLink.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new WorkflowInstanceLinkWrapper((WorkflowInstanceLink)_workflowInstanceLink.clone());
	}

	public int compareTo(
		com.liferay.portal.model.WorkflowInstanceLink workflowInstanceLink) {
		return _workflowInstanceLink.compareTo(workflowInstanceLink);
	}

	@Override
	public int hashCode() {
		return _workflowInstanceLink.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portal.model.WorkflowInstanceLink> toCacheModel() {
		return _workflowInstanceLink.toCacheModel();
	}

	public com.liferay.portal.model.WorkflowInstanceLink toEscapedModel() {
		return new WorkflowInstanceLinkWrapper(_workflowInstanceLink.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _workflowInstanceLink.toString();
	}

	public java.lang.String toXmlString() {
		return _workflowInstanceLink.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_workflowInstanceLink.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public WorkflowInstanceLink getWrappedWorkflowInstanceLink() {
		return _workflowInstanceLink;
	}

	public WorkflowInstanceLink getWrappedModel() {
		return _workflowInstanceLink;
	}

	public void resetOriginalValues() {
		_workflowInstanceLink.resetOriginalValues();
	}

	private WorkflowInstanceLink _workflowInstanceLink;
}