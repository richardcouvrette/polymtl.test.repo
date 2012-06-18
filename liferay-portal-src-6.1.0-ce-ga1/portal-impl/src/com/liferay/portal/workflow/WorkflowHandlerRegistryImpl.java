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

import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.workflow.WorkflowHandler;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistry;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Bruno Farache
 * @author Marcellus Tavares
 */
public class WorkflowHandlerRegistryImpl implements WorkflowHandlerRegistry {

	public List<WorkflowHandler> getScopeableWorkflowHandlers() {
		return ListUtil.fromMapValues(_scopeableWorkflowHandlerMap);
	}

	public WorkflowHandler getWorkflowHandler(String className) {
		return _workflowHandlerMap.get(className);
	}

	public List<WorkflowHandler> getWorkflowHandlers() {
		return ListUtil.fromMapValues(_workflowHandlerMap);
	}

	public void register(WorkflowHandler workflowHandler) {
		synchronized (this) {
			_workflowHandlerMap.put(
				workflowHandler.getClassName(), workflowHandler);

			if (workflowHandler.isScopeable()) {
				_scopeableWorkflowHandlerMap.put(
					workflowHandler.getClassName(), workflowHandler);
			}
		}
	}

	public void unregister(WorkflowHandler workflowHandler) {
		synchronized (this) {
			_workflowHandlerMap.remove(workflowHandler.getClassName());

			if (workflowHandler.isScopeable()) {
				_scopeableWorkflowHandlerMap.remove(
					workflowHandler.getClassName());
			}
		}
	}

	private Map<String, WorkflowHandler> _scopeableWorkflowHandlerMap =
		//new ConcurrentSkipListMap<String, WorkflowHandler>();
		new TreeMap<String, WorkflowHandler>();
	private Map<String, WorkflowHandler> _workflowHandlerMap =
		new TreeMap<String, WorkflowHandler>();

}