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

package com.liferay.portlet.ratings.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link RatingsEntryService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       RatingsEntryService
 * @generated
 */
public class RatingsEntryServiceWrapper implements RatingsEntryService,
	ServiceWrapper<RatingsEntryService> {
	public RatingsEntryServiceWrapper(RatingsEntryService ratingsEntryService) {
		_ratingsEntryService = ratingsEntryService;
	}

	public void deleteEntry(java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_ratingsEntryService.deleteEntry(className, classPK);
	}

	public com.liferay.portlet.ratings.model.RatingsEntry updateEntry(
		java.lang.String className, long classPK, double score)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ratingsEntryService.updateEntry(className, classPK, score);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public RatingsEntryService getWrappedRatingsEntryService() {
		return _ratingsEntryService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedRatingsEntryService(
		RatingsEntryService ratingsEntryService) {
		_ratingsEntryService = ratingsEntryService;
	}

	public RatingsEntryService getWrappedService() {
		return _ratingsEntryService;
	}

	public void setWrappedService(RatingsEntryService ratingsEntryService) {
		_ratingsEntryService = ratingsEntryService;
	}

	private RatingsEntryService _ratingsEntryService;
}