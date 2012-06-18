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

import com.liferay.portal.RegionCodeException;
import com.liferay.portal.RegionNameException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Region;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.base.RegionServiceBaseImpl;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class RegionServiceImpl extends RegionServiceBaseImpl {

	public Region addRegion(
			long countryId, String regionCode, String name, boolean active)
		throws PortalException, SystemException {

		if (!getPermissionChecker().isOmniadmin()) {
			throw new PrincipalException();
		}

		countryPersistence.findByPrimaryKey(countryId);

		if (Validator.isNull(regionCode)) {
			throw new RegionCodeException();
		}

		if (Validator.isNull(name)) {
			throw new RegionNameException();
		}

		long regionId = counterLocalService.increment();

		Region region = regionPersistence.create(regionId);

		region.setCountryId(countryId);
		region.setRegionCode(regionCode);
		region.setName(name);
		region.setActive(active);

		regionPersistence.update(region, false);

		return region;
	}

	public Region getRegion(long regionId)
		throws PortalException, SystemException {

		return regionPersistence.findByPrimaryKey(regionId);
	}

	public List<Region> getRegions() throws SystemException {
		return regionPersistence.findAll();
	}

	public List<Region> getRegions(long countryId) throws SystemException {
		return regionPersistence.findByCountryId(countryId);
	}

	public List<Region> getRegions(boolean active) throws SystemException {
		return regionPersistence.findByActive(active);
	}

	public List<Region> getRegions(long countryId, boolean active)
		throws SystemException {

		return regionPersistence.findByC_A(countryId, active);
	}

}