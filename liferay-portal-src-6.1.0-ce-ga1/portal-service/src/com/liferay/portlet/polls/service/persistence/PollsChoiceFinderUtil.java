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

package com.liferay.portlet.polls.service.persistence;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
public class PollsChoiceFinderUtil {
	public static com.liferay.portlet.polls.model.PollsChoice fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().fetchByUUID_G(uuid, groupId);
	}

	public static com.liferay.portlet.polls.model.PollsChoice findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.polls.NoSuchChoiceException {
		return getFinder().findByUUID_G(uuid, groupId);
	}

	public static PollsChoiceFinder getFinder() {
		if (_finder == null) {
			_finder = (PollsChoiceFinder)PortalBeanLocatorUtil.locate(PollsChoiceFinder.class.getName());

			ReferenceRegistry.registerReference(PollsChoiceFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(PollsChoiceFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(PollsChoiceFinderUtil.class,
			"_finder");
	}

	private static PollsChoiceFinder _finder;
}