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

package com.liferay.portlet.polls.service;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the polls vote remote service. This utility wraps {@link com.liferay.portlet.polls.service.impl.PollsVoteServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PollsVoteService
 * @see com.liferay.portlet.polls.service.base.PollsVoteServiceBaseImpl
 * @see com.liferay.portlet.polls.service.impl.PollsVoteServiceImpl
 * @generated
 */
public class PollsVoteServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portlet.polls.service.impl.PollsVoteServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portlet.polls.model.PollsVote addVote(
		long questionId, long choiceId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().addVote(questionId, choiceId, serviceContext);
	}

	public static PollsVoteService getService() {
		if (_service == null) {
			_service = (PollsVoteService)PortalBeanLocatorUtil.locate(PollsVoteService.class.getName());

			ReferenceRegistry.registerReference(PollsVoteServiceUtil.class,
				"_service");
			MethodCache.remove(PollsVoteService.class);
		}

		return _service;
	}

	public void setService(PollsVoteService service) {
		MethodCache.remove(PollsVoteService.class);

		_service = service;

		ReferenceRegistry.registerReference(PollsVoteServiceUtil.class,
			"_service");
		MethodCache.remove(PollsVoteService.class);
	}

	private static PollsVoteService _service;
}