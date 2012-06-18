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

package com.liferay.portal.kernel.workflow;

import com.liferay.portal.NoSuchWorkflowDefinitionLinkException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.service.ServiceContext;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Bruno Farache
 * @author Marcellus Tavares
 */
public class WorkflowHandlerRegistryUtil {

	public static List<WorkflowHandler> getScopeableWorkflowHandlers() {
		return getWorkflowHandlerRegistry().getScopeableWorkflowHandlers();
	}

	public static WorkflowHandler getWorkflowHandler(String className) {
		return getWorkflowHandlerRegistry().getWorkflowHandler(className);
	}

	public static WorkflowHandlerRegistry getWorkflowHandlerRegistry() {
		return _workflowHandlerRegistry;
	}

	public static List<WorkflowHandler> getWorkflowHandlers() {
		return getWorkflowHandlerRegistry().getWorkflowHandlers();
	}

	public static void register(List<WorkflowHandler> workflowHandlers) {
		for (WorkflowHandler workflowHandler : workflowHandlers) {
			register(workflowHandler);
		}
	}

	public static void register(WorkflowHandler workflowHandler) {
		getWorkflowHandlerRegistry().register(workflowHandler);
	}

	public static void startWorkflowInstance(
			long companyId, long groupId, long userId, String className,
			long classPK, Object model, ServiceContext serviceContext)
		throws PortalException, SystemException {

		Map<String, Serializable> workflowContext =
			(Map<String, Serializable>)serviceContext.removeAttribute(
				"workflowContext");

		if (workflowContext == null) {
			workflowContext = Collections.emptyMap();
		}

		startWorkflowInstance(
			companyId, groupId, userId, className, classPK, model,
			serviceContext, workflowContext);
	}

	public static void startWorkflowInstance(
			long companyId, long groupId, long userId, String className,
			long classPK, Object model, ServiceContext serviceContext,
			Map<String, Serializable> workflowContext)
		throws PortalException, SystemException {

		if (serviceContext.getWorkflowAction() !=
				WorkflowConstants.ACTION_PUBLISH) {

			return;
		}

		WorkflowHandler workflowHandler = getWorkflowHandler(className);

		if (workflowHandler == null) {
			throw new WorkflowException(
				"No workflow handler found for " + className);
		}

		int status = WorkflowConstants.STATUS_PENDING;

		if (!WorkflowThreadLocal.isEnabled() ||
			!WorkflowEngineManagerUtil.isDeployed()) {

			status = WorkflowConstants.STATUS_APPROVED;
		}

		workflowContext = new HashMap<String, Serializable>(workflowContext);

		workflowContext.put(
			WorkflowConstants.CONTEXT_COMPANY_ID, String.valueOf(companyId));
		workflowContext.put(
			WorkflowConstants.CONTEXT_GROUP_ID, String.valueOf(groupId));
		workflowContext.put(
			WorkflowConstants.CONTEXT_USER_ID, String.valueOf(userId));
		workflowContext.put(
			WorkflowConstants.CONTEXT_ENTRY_CLASS_NAME, className);
		workflowContext.put(
			WorkflowConstants.CONTEXT_ENTRY_CLASS_PK, String.valueOf(classPK));
		workflowContext.put(
			WorkflowConstants.CONTEXT_ENTRY_TYPE,
			workflowHandler.getType(LocaleUtil.getDefault()));
		workflowContext.put(
			WorkflowConstants.CONTEXT_SERVICE_CONTEXT, serviceContext);

		workflowHandler.updateStatus(status, workflowContext);

		if (WorkflowThreadLocal.isEnabled() &&
			WorkflowEngineManagerUtil.isDeployed()) {

			try {
				workflowHandler.startWorkflowInstance(
					companyId, groupId, userId, classPK, model,
					workflowContext);
			}
			catch (NoSuchWorkflowDefinitionLinkException nswdle) {
				workflowHandler.updateStatus(
					WorkflowConstants.STATUS_APPROVED, workflowContext);
			}
		}
	}

	public static void startWorkflowInstance(
			long companyId, long userId, String className, long classPK,
			Object model, ServiceContext serviceContext)
		throws PortalException, SystemException {

		Map<String, Serializable> workflowContext =
			(Map<String, Serializable>)serviceContext.removeAttribute(
				"workflowContext");

		if (workflowContext == null) {
			workflowContext = Collections.emptyMap();
		}

		startWorkflowInstance(
			companyId, WorkflowConstants.DEFAULT_GROUP_ID, userId, className,
			classPK, model, serviceContext, workflowContext);
	}

	public static void startWorkflowInstance(
			long companyId, long userId, String className, long classPK,
			Object model, ServiceContext serviceContext,
			Map<String, Serializable> workflowContext)
		throws PortalException, SystemException {

		startWorkflowInstance(
			companyId, WorkflowConstants.DEFAULT_GROUP_ID, userId, className,
			classPK, model, serviceContext, workflowContext);
	}

	public static void unregister(List<WorkflowHandler> workflowHandlers) {
		for (WorkflowHandler workflowHandler : workflowHandlers) {
			unregister(workflowHandler);
		}
	}

	public static void unregister(WorkflowHandler workflowHandler) {
		getWorkflowHandlerRegistry().unregister(workflowHandler);
	}

	public static Object updateStatus(
			int status, Map<String, Serializable> workflowContext)
		throws PortalException, SystemException {

		String className = (String)workflowContext.get(
			WorkflowConstants.CONTEXT_ENTRY_CLASS_NAME);

		WorkflowHandler workflowHandler = getWorkflowHandler(className);

		if (workflowHandler != null) {
			return workflowHandler.updateStatus(status, workflowContext);
		}

		return null;
	}

	public void setWorkflowHandlerRegistry(
		WorkflowHandlerRegistry workflowHandlerRegistry) {

		_workflowHandlerRegistry = workflowHandlerRegistry;
	}

	private static WorkflowHandlerRegistry _workflowHandlerRegistry;

}