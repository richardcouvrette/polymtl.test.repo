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

package com.liferay.portlet.flags.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Transactional;

/**
 * The interface for the flags entry remote service.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FlagsEntryServiceUtil
 * @see com.liferay.portlet.flags.service.base.FlagsEntryServiceBaseImpl
 * @see com.liferay.portlet.flags.service.impl.FlagsEntryServiceImpl
 * @generated
 */
@JSONWebService
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface FlagsEntryService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link FlagsEntryServiceUtil} to access the flags entry remote service. Add custom service methods to {@link com.liferay.portlet.flags.service.impl.FlagsEntryServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public void addEntry(java.lang.String className, long classPK,
		java.lang.String reporterEmailAddress, long reportedUserId,
		java.lang.String contentTitle, java.lang.String contentURL,
		java.lang.String reason,
		com.liferay.portal.service.ServiceContext serviceContext);
}