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

package com.liferay.portal.workflow;

import com.liferay.portal.kernel.messaging.proxy.BaseProxyBean;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.workflow.WorkflowTask;
import com.liferay.portal.kernel.workflow.WorkflowTaskManager;

import java.io.Serializable;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Micha Kiener
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 * @author Marcellus Tavares
 */
public class WorkflowTaskManagerProxyBean
	extends BaseProxyBean implements WorkflowTaskManager {

	public WorkflowTask assignWorkflowTaskToRole(
		long companyId, long userId, long workflowTaskId, long roleId,
		String comment, Date dueDate,
		Map<String, Serializable> workflowContext) {

		throw new UnsupportedOperationException();
	}

	public WorkflowTask assignWorkflowTaskToUser(
		long companyId, long userId, long workflowTaskId, long assigneeUserId,
		String comment, Date dueDate,
		Map<String, Serializable> workflowContext) {

		throw new UnsupportedOperationException();
	}

	public WorkflowTask completeWorkflowTask(
		long companyId, long userId, long workflowTaskId, String transitionName,
		String comment, Map<String, Serializable> workflowContext) {

		throw new UnsupportedOperationException();
	}

	public List<String> getNextTransitionNames(
		long companyId, long userId, long workflowTaskId) {

		throw new UnsupportedOperationException();
	}

	public long[] getPooledActorsIds(long companyId, long workflowTaskId) {
		throw new UnsupportedOperationException();
	}

	public WorkflowTask getWorkflowTask(long companyId, long workflowTaskId) {
		throw new UnsupportedOperationException();
	}

	public int getWorkflowTaskCount(long companyId, Boolean completed) {
		throw new UnsupportedOperationException();
	}

	public int getWorkflowTaskCountByRole(
		long companyId, long roleId, Boolean completed) {

		throw new UnsupportedOperationException();
	}

	public int getWorkflowTaskCountBySubmittingUser(
		long companyId, long userId, Boolean completed) {

		throw new UnsupportedOperationException();
	}

	public int getWorkflowTaskCountByUser(
		long companyId, long userId, Boolean completed) {

		throw new UnsupportedOperationException();
	}

	public int getWorkflowTaskCountByUserRoles(
		long companyId, long userId, Boolean completed) {

		throw new UnsupportedOperationException();
	}

	public int getWorkflowTaskCountByWorkflowInstance(
		long companyId, Long userId, long workflowInstanceId,
		Boolean completed) {

		throw new UnsupportedOperationException();
	}

	public List<WorkflowTask> getWorkflowTasks(
		long companyId, Boolean completed, int start, int end,
		OrderByComparator orderByComparator) {

		throw new UnsupportedOperationException();
	}

	public List<WorkflowTask> getWorkflowTasksByRole(
		long companyId, long roleId, Boolean completed, int start, int end,
		OrderByComparator orderByComparator) {

		throw new UnsupportedOperationException();
	}

	public List<WorkflowTask> getWorkflowTasksBySubmittingUser(
		long companyId, long userId, Boolean completed, int start, int end,
		OrderByComparator orderByComparator) {

		throw new UnsupportedOperationException();
	}

	public List<WorkflowTask> getWorkflowTasksByUser(
		long companyId, long userId, Boolean completed, int start, int end,
		OrderByComparator orderByComparator) {

		throw new UnsupportedOperationException();
	}

	public List<WorkflowTask> getWorkflowTasksByUserRoles(
		long companyId, long userId, Boolean completed, int start, int end,
		OrderByComparator orderByComparator) {

		throw new UnsupportedOperationException();
	}

	public List<WorkflowTask> getWorkflowTasksByWorkflowInstance(
		long companyId, Long userId, long workflowInstanceId, Boolean completed,
		int start, int end, OrderByComparator orderByComparator) {

		throw new UnsupportedOperationException();
	}

	public List<WorkflowTask> search(
		long companyId, long userId, String keywords, Boolean completed,
		Boolean searchByUserRoles, int start, int end,
		OrderByComparator orderByComparator) {

		throw new UnsupportedOperationException();
	}

	public List<WorkflowTask> search(
		long companyId, long userId, String taskName, String assetType,
		Long[] assetPrimaryKey, Date dueDateGT, Date dueDateLT,
		Boolean completed, Boolean searchByUserRoles, boolean andOperator,
		int start, int end, OrderByComparator orderByComparator) {

		throw new UnsupportedOperationException();
	}

	public List<WorkflowTask> search(
		long companyId, long userId, String keywords, String[] assetTypes,
		Boolean completed, Boolean searchByUserRoles, int start, int end,
		OrderByComparator orderByComparator) {

		throw new UnsupportedOperationException();
	}

	public int searchCount(
		long companyId, long userId, String keywords, Boolean completed,
		Boolean searchByUserRoles) {

		throw new UnsupportedOperationException();
	}

	public int searchCount(
		long companyId, long userId, String taskName, String assetType,
		Long[] assetPrimaryKey, Date dueDateGT, Date dueDateLT,
		Boolean completed, Boolean searchByUserRoles, boolean andOperator) {

		throw new UnsupportedOperationException();
	}

	public int searchCount(
		long companyId, long userId, String keywords, String[] assetTypes,
		Boolean completed, Boolean searchByUserRoles) {

		throw new UnsupportedOperationException();
	}

	public WorkflowTask updateDueDate(
		long companyId, long userId, long workflowTaskId, String comment,
		Date dueDate) {

		throw new UnsupportedOperationException();
	}

}