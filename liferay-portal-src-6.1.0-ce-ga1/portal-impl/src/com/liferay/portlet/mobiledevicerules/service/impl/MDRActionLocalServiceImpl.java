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

package com.liferay.portlet.mobiledevicerules.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.mobiledevicerules.model.MDRAction;
import com.liferay.portlet.mobiledevicerules.model.MDRRuleGroupInstance;
import com.liferay.portlet.mobiledevicerules.service.base.MDRActionLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Edward C. Han
 */
public class MDRActionLocalServiceImpl extends MDRActionLocalServiceBaseImpl {

	public MDRAction addAction(
			long ruleGroupInstanceId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String type,
			String typeSettings, ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(
			serviceContext.getUserId());
		MDRRuleGroupInstance ruleGroupInstance =
			mdrRuleGroupInstancePersistence.findByPrimaryKey(
				ruleGroupInstanceId);
		Date now = new Date();

		long actionId = counterLocalService.increment();

		MDRAction action = mdrActionLocalService.createMDRAction(actionId);

		action.setUuid(serviceContext.getUuid());
		action.setGroupId(ruleGroupInstance.getGroupId());
		action.setCompanyId(serviceContext.getCompanyId());
		action.setCreateDate(serviceContext.getCreateDate(now));
		action.setModifiedDate(serviceContext.getModifiedDate(now));
		action.setUserId(serviceContext.getUserId());
		action.setUserName(user.getFullName());
		action.setClassNameId(ruleGroupInstance.getClassNameId());
		action.setClassPK(ruleGroupInstance.getClassPK());
		action.setRuleGroupInstanceId(ruleGroupInstanceId);
		action.setNameMap(nameMap);
		action.setDescriptionMap(descriptionMap);
		action.setType(type);
		action.setTypeSettings(typeSettings);

		return updateMDRAction(action, false);
	}

	public MDRAction addAction(
			long ruleGroupInstanceId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String type,
			UnicodeProperties typeSettingsProperties,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		return addAction(
			ruleGroupInstanceId, nameMap, descriptionMap, type,
			typeSettingsProperties.toString(), serviceContext);
	}

	public void deleteAction(long actionId) throws SystemException {
		MDRAction action = mdrActionPersistence.fetchByPrimaryKey(actionId);

		if (action != null) {
			deleteAction(action);
		}
	}

	public void deleteAction(MDRAction action) throws SystemException {
		mdrActionPersistence.remove(action);
	}

	public void deleteActions(long ruleGroupInstanceId)
		throws SystemException {

		List<MDRAction> actions =
			mdrActionPersistence.findByRuleGroupInstanceId(ruleGroupInstanceId);

		for (MDRAction action : actions) {
			deleteAction(action);
		}
	}

	public MDRAction fetchAction(long actionId) throws SystemException {
		return mdrActionPersistence.fetchByPrimaryKey(actionId);
	}

	public MDRAction getAction(long actionId)
		throws PortalException, SystemException {

		return mdrActionPersistence.findByPrimaryKey(actionId);
	}

	public List<MDRAction> getActions(long ruleGroupInstanceId)
		throws SystemException {

		return mdrActionPersistence.findByRuleGroupInstanceId(
			ruleGroupInstanceId);
	}

	public List<MDRAction> getActions(
			long ruleGroupInstanceId, int start, int end)
		throws SystemException {

		return mdrActionPersistence.findByRuleGroupInstanceId(
			ruleGroupInstanceId, start, end);
	}

	public int getActionsCount(long ruleGroupInstanceId)
		throws SystemException {

		return mdrActionPersistence.countByRuleGroupInstanceId(
			ruleGroupInstanceId);
	}

	public MDRAction updateAction(
			long actionId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String type,
			String typeSettings, ServiceContext serviceContext)
		throws PortalException, SystemException {

		MDRAction action = mdrActionPersistence.findByPrimaryKey(actionId);

		action.setModifiedDate(serviceContext.getModifiedDate(null));
		action.setNameMap(nameMap);
		action.setDescriptionMap(descriptionMap);
		action.setType(type);
		action.setTypeSettings(typeSettings);

		mdrActionPersistence.update(action, false);

		return action;
	}

	public MDRAction updateAction(
			long actionId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String type,
			UnicodeProperties typeSettingsProperties,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		return updateAction(
			actionId, nameMap, descriptionMap, type,
			typeSettingsProperties.toString(), serviceContext);
	}

}