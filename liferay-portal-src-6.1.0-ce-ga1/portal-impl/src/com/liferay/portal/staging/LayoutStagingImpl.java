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

package com.liferay.portal.staging;

import com.liferay.portal.kernel.staging.LayoutStaging;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutRevision;
import com.liferay.portal.model.LayoutStagingHandler;

import java.lang.reflect.InvocationHandler;

/**
 * @author Raymond Augé
 */
public class LayoutStagingImpl implements LayoutStaging {

	public LayoutRevision getLayoutRevision(Layout layout) {
		LayoutStagingHandler layoutStagingHandler = getLayoutStagingHandler(
			layout);

		if (layoutStagingHandler == null) {
			return null;
		}

		return layoutStagingHandler.getLayoutRevision();
	}

	public LayoutStagingHandler getLayoutStagingHandler(Layout layout) {
		if (!ProxyUtil.isProxyClass(layout.getClass())) {
			return null;
		}

		InvocationHandler invocationHandler = ProxyUtil.getInvocationHandler(
			layout);

		if (!(invocationHandler instanceof LayoutStagingHandler)) {
			return null;
		}

		return (LayoutStagingHandler)invocationHandler;
	}

	public boolean isBranchingLayout(Layout layout) {
		try {
			return isBranchingLayoutSet(
				layout.getGroup(), layout.isPrivateLayout());
		}
		catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	public boolean isBranchingLayoutSet(Group group, boolean privateLayout) {
		boolean isStagingGroup = false;

		if (group.isStagingGroup()) {
			isStagingGroup = true;

			group = group.getLiveGroup();
		}

		UnicodeProperties typeSettingsProperties =
			group.getTypeSettingsProperties();

		boolean branchingEnabled = false;

		if (privateLayout) {
			branchingEnabled = GetterUtil.getBoolean(
				typeSettingsProperties.getProperty("branchingPrivate"));
		}
		else {
			branchingEnabled = GetterUtil.getBoolean(
				typeSettingsProperties.getProperty("branchingPublic"));
		}

		if (group.isStaged() && branchingEnabled) {
			if (!group.isStagedRemotely() && !isStagingGroup) {
				return false;
			}

			return true;
		}

		return false;
	}

}