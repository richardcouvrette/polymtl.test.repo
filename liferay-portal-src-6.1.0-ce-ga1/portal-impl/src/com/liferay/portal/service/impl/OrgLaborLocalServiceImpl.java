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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.ListTypeConstants;
import com.liferay.portal.model.OrgLabor;
import com.liferay.portal.service.base.OrgLaborLocalServiceBaseImpl;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class OrgLaborLocalServiceImpl extends OrgLaborLocalServiceBaseImpl {

	public OrgLabor addOrgLabor(
			long organizationId, int typeId, int sunOpen, int sunClose,
			int monOpen, int monClose, int tueOpen, int tueClose, int wedOpen,
			int wedClose, int thuOpen, int thuClose, int friOpen, int friClose,
			int satOpen, int satClose)
		throws PortalException, SystemException {

		validate(typeId);

		long orgLaborId = counterLocalService.increment();

		OrgLabor orgLabor = orgLaborPersistence.create(orgLaborId);

		orgLabor.setOrganizationId(organizationId);
		orgLabor.setTypeId(typeId);
		orgLabor.setSunOpen(sunOpen);
		orgLabor.setSunClose(sunClose);
		orgLabor.setMonOpen(monOpen);
		orgLabor.setMonClose(monClose);
		orgLabor.setTueOpen(tueOpen);
		orgLabor.setTueClose(tueClose);
		orgLabor.setWedOpen(wedOpen);
		orgLabor.setWedClose(wedClose);
		orgLabor.setThuOpen(thuOpen);
		orgLabor.setThuClose(thuClose);
		orgLabor.setFriOpen(friOpen);
		orgLabor.setFriClose(friClose);
		orgLabor.setSatOpen(satOpen);
		orgLabor.setSatClose(satClose);

		orgLaborPersistence.update(orgLabor, false);

		return orgLabor;
	}

	@Override
	public void deleteOrgLabor(long orgLaborId)
		throws PortalException, SystemException {

		OrgLabor orgLabor = orgLaborPersistence.findByPrimaryKey(orgLaborId);

		deleteOrgLabor(orgLabor);
	}

	@Override
	public void deleteOrgLabor(OrgLabor orgLabor) throws SystemException {
		orgLaborPersistence.remove(orgLabor);
	}

	@Override
	public OrgLabor getOrgLabor(long orgLaborId)
		throws PortalException, SystemException {

		return orgLaborPersistence.findByPrimaryKey(orgLaborId);
	}

	public List<OrgLabor> getOrgLabors(long organizationId)
		throws SystemException {

		return orgLaborPersistence.findByOrganizationId(organizationId);
	}

	public OrgLabor updateOrgLabor(
			long orgLaborId, int typeId, int sunOpen, int sunClose, int monOpen,
			int monClose, int tueOpen, int tueClose, int wedOpen, int wedClose,
			int thuOpen, int thuClose, int friOpen, int friClose, int satOpen,
			int satClose)
		throws PortalException, SystemException {

		validate(typeId);

		OrgLabor orgLabor = orgLaborPersistence.findByPrimaryKey(orgLaborId);

		orgLabor.setTypeId(typeId);
		orgLabor.setSunOpen(sunOpen);
		orgLabor.setSunClose(sunClose);
		orgLabor.setMonOpen(monOpen);
		orgLabor.setMonClose(monClose);
		orgLabor.setTueOpen(tueOpen);
		orgLabor.setTueClose(tueClose);
		orgLabor.setWedOpen(wedOpen);
		orgLabor.setWedClose(wedClose);
		orgLabor.setThuOpen(thuOpen);
		orgLabor.setThuClose(thuClose);
		orgLabor.setFriOpen(friOpen);
		orgLabor.setFriClose(friClose);
		orgLabor.setSatOpen(satOpen);
		orgLabor.setSatClose(satClose);

		orgLaborPersistence.update(orgLabor, false);

		return orgLabor;
	}

	protected void validate(int typeId)
		throws PortalException, SystemException {

		listTypeService.validate(
			typeId, ListTypeConstants.ORGANIZATION_SERVICE);
	}

}