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

import com.liferay.portal.NoSuchWorkflowDefinitionLinkException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.spring.aop.Skip;
import com.liferay.portal.kernel.staging.StagingUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowEngineManagerUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.model.WorkflowDefinitionLink;
import com.liferay.portal.service.base.WorkflowDefinitionLinkLocalServiceBaseImpl;
import com.liferay.portal.util.PortalUtil;

import java.util.Date;
import java.util.List;

/**
 * @author Jorge Ferrer
 * @author Bruno Farache
 * @author Brian Wing Shun Chan
 * @author Juan Fernández
 * @author Marcellus Tavares
 */
public class WorkflowDefinitionLinkLocalServiceImpl
	extends WorkflowDefinitionLinkLocalServiceBaseImpl {

	public WorkflowDefinitionLink addWorkflowDefinitionLink(
			long userId, long companyId, long groupId, String className,
			long classPK, long typePK, String workflowDefinitionName,
			int workflowDefinitionVersion)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
		long classNameId = PortalUtil.getClassNameId(className);
		Date now = new Date();

		long workflowDefinitionLinkId = counterLocalService.increment();

		WorkflowDefinitionLink workflowDefinitionLink =
			workflowDefinitionLinkPersistence.create(workflowDefinitionLinkId);

		workflowDefinitionLink.setCreateDate(now);
		workflowDefinitionLink.setModifiedDate(now);
		workflowDefinitionLink.setUserId(userId);
		workflowDefinitionLink.setUserName(user.getFullName());
		workflowDefinitionLink.setGroupId(groupId);
		workflowDefinitionLink.setCompanyId(companyId);
		workflowDefinitionLink.setClassNameId(classNameId);
		workflowDefinitionLink.setClassPK(classPK);
		workflowDefinitionLink.setTypePK(typePK);
		workflowDefinitionLink.setWorkflowDefinitionName(
			workflowDefinitionName);
		workflowDefinitionLink.setWorkflowDefinitionVersion(
			workflowDefinitionVersion);

		workflowDefinitionLinkPersistence.update(workflowDefinitionLink, false);

		return workflowDefinitionLink;
	}

	public void deleteWorkflowDefinitionLink(
			long companyId, long groupId, String className, long classPK,
			long typePK)
		throws PortalException, SystemException {

		try {
			WorkflowDefinitionLink workflowDefinitionLink =
				getWorkflowDefinitionLink(
					companyId, groupId, className, classPK, typePK, true);

			deleteWorkflowDefinitionLink(workflowDefinitionLink);
		}
		catch (NoSuchWorkflowDefinitionLinkException nswdle) {
		}
	}

	public WorkflowDefinitionLink getDefaultWorkflowDefinitionLink(
			long companyId, String className, long classPK, long typePK)
		throws PortalException, SystemException {

		if (!WorkflowEngineManagerUtil.isDeployed()) {
			throw new NoSuchWorkflowDefinitionLinkException();
		}

		long classNameId = PortalUtil.getClassNameId(className);

		return workflowDefinitionLinkPersistence.findByG_C_C_C_T(
			WorkflowConstants.DEFAULT_GROUP_ID, companyId, classNameId,
			classPK, typePK);
	}

	public WorkflowDefinitionLink getWorkflowDefinitionLink(
			long companyId, long groupId, String className, long classPK,
			long typePK)
		throws PortalException, SystemException {

		return getWorkflowDefinitionLink(
			companyId, groupId, className, classPK, typePK, false);
	}

	public WorkflowDefinitionLink getWorkflowDefinitionLink(
			long companyId, long groupId, String className, long classPK,
			long typePK, boolean strict)
		throws PortalException, SystemException {

		if (!WorkflowEngineManagerUtil.isDeployed()) {
			throw new NoSuchWorkflowDefinitionLinkException();
		}

		long classNameId = PortalUtil.getClassNameId(className);

		WorkflowDefinitionLink workflowDefinitionLink = null;

		if (groupId > 0) {
			groupId = StagingUtil.getLiveGroupId(groupId);
		}

		workflowDefinitionLink =
			workflowDefinitionLinkPersistence.fetchByG_C_C_C_T(
				groupId, companyId, classNameId, classPK, typePK);

		if (!strict && (workflowDefinitionLink == null)) {
			workflowDefinitionLink =
				workflowDefinitionLinkPersistence.fetchByG_C_C_C_T(
					WorkflowConstants.DEFAULT_GROUP_ID, companyId, classNameId,
					classPK, typePK);
		}

		if (workflowDefinitionLink == null) {
			throw new NoSuchWorkflowDefinitionLinkException(
				"No workflow for groupId=" + groupId + ", companyId=" +
					companyId + " and classNameId=" + classNameId);
		}

		return workflowDefinitionLink;
	}

	public int getWorkflowDefinitionLinksCount(
			long companyId, String workflowDefinitionName,
			int workflowDefinitionVersion)
		throws SystemException{

		if (!WorkflowEngineManagerUtil.isDeployed()) {
			return 0;
		}

		return workflowDefinitionLinkPersistence.countByC_W_W(
			companyId, workflowDefinitionName, workflowDefinitionVersion);
	}

	@Skip
	public boolean hasWorkflowDefinitionLink(
			long companyId, long groupId, String className)
		throws PortalException, SystemException {

		return hasWorkflowDefinitionLink(companyId, groupId, className, 0);
	}

	@Skip
	public boolean hasWorkflowDefinitionLink(
			long companyId, long groupId, String className, long classPK)
		throws PortalException, SystemException {

		return hasWorkflowDefinitionLink(
			companyId, groupId, className, classPK, 0);
	}

	@Skip
	public boolean hasWorkflowDefinitionLink(
			long companyId, long groupId, String className, long classPK,
			long typePK)
		throws PortalException, SystemException {

		if (!WorkflowEngineManagerUtil.isDeployed()) {
			return false;
		}

		try {
			workflowDefinitionLinkLocalService.getWorkflowDefinitionLink(
				companyId, groupId, className, classPK, typePK);

			return true;
		}
		catch (NoSuchWorkflowDefinitionLinkException nswdle) {
			return false;
		}
	}

	public void updateWorkflowDefinitionLink(
			long userId, long companyId, long groupId, String className,
			long classPK, long typePK, String workflowDefinition)
		throws PortalException, SystemException {

		if (Validator.isNull(workflowDefinition)) {
			deleteWorkflowDefinitionLink(
				companyId, groupId, className, classPK, typePK);
		}
		else {
			String[] workflowDefinitionParts = StringUtil.split(
				workflowDefinition, CharPool.AT);

			String workflowDefinitionName = workflowDefinitionParts[0];
			int workflowDefinitionVersion = GetterUtil.getInteger(
				workflowDefinitionParts[1]);

			updateWorkflowDefinitionLink(
				userId, companyId, groupId, className, classPK, typePK,
				workflowDefinitionName, workflowDefinitionVersion);
		}
	}

	public WorkflowDefinitionLink updateWorkflowDefinitionLink(
			long userId, long companyId, long groupId, String className,
			long classPK, long typePK, String workflowDefinitionName,
			int workflowDefinitionVersion)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
		long classNameId = PortalUtil.getClassNameId(className);
		Date now = new Date();

		WorkflowDefinitionLink workflowDefinitionLink =
			workflowDefinitionLinkPersistence.fetchByG_C_C_C_T(
				groupId, companyId, classNameId, classPK, typePK);

		if (workflowDefinitionLink == null) {
			workflowDefinitionLink = addWorkflowDefinitionLink(
				userId, companyId, groupId, className, classPK, typePK,
				workflowDefinitionName, workflowDefinitionVersion);
		}

		workflowDefinitionLink.setModifiedDate(now);
		workflowDefinitionLink.setUserId(userId);
		workflowDefinitionLink.setUserName(user.getFullName());
		workflowDefinitionLink.setGroupId(groupId);
		workflowDefinitionLink.setCompanyId(companyId);
		workflowDefinitionLink.setClassNameId(classNameId);
		workflowDefinitionLink.setClassPK(classPK);
		workflowDefinitionLink.setTypePK(typePK);
		workflowDefinitionLink.setWorkflowDefinitionName(
			workflowDefinitionName);
		workflowDefinitionLink.setWorkflowDefinitionVersion(
			workflowDefinitionVersion);

		workflowDefinitionLinkPersistence.update(workflowDefinitionLink, false);

		return workflowDefinitionLink;
	}

	public void updateWorkflowDefinitionLinks(
			long userId, long companyId, long groupId, String className,
			long classPK,
			List<ObjectValuePair<Long, String>> workflowDefinitions)
		throws PortalException, SystemException {

		for (ObjectValuePair<Long, String> workflowDefinition :
				workflowDefinitions) {

			long typePK = workflowDefinition.getKey();
			String workflowDefinitionName = workflowDefinition.getValue();

			if (Validator.isNull(workflowDefinitionName)) {
				deleteWorkflowDefinitionLink(
					companyId, groupId, className, classPK, typePK);
			}
			else {
				updateWorkflowDefinitionLink(
					userId, companyId, groupId, className, classPK, typePK,
					workflowDefinitionName);
			}
		}
	}

}