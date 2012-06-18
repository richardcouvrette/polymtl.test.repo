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

package com.liferay.portal.service;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the country remote service. This utility wraps {@link com.liferay.portal.service.impl.CountryServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CountryService
 * @see com.liferay.portal.service.base.CountryServiceBaseImpl
 * @see com.liferay.portal.service.impl.CountryServiceImpl
 * @generated
 */
public class CountryServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portal.service.impl.CountryServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.model.Country addCountry(
		java.lang.String name, java.lang.String a2, java.lang.String a3,
		java.lang.String number, java.lang.String idd, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().addCountry(name, a2, a3, number, idd, active);
	}

	public static com.liferay.portal.model.Country fetchCountry(long countryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchCountry(countryId);
	}

	public static java.util.List<com.liferay.portal.model.Country> getCountries()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCountries();
	}

	public static java.util.List<com.liferay.portal.model.Country> getCountries(
		boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCountries(active);
	}

	public static com.liferay.portal.model.Country getCountry(long countryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getCountry(countryId);
	}

	public static com.liferay.portal.model.Country getCountryByA2(
		java.lang.String a2)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getCountryByA2(a2);
	}

	public static com.liferay.portal.model.Country getCountryByA3(
		java.lang.String a3)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getCountryByA3(a3);
	}

	public static com.liferay.portal.model.Country getCountryByName(
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getCountryByName(name);
	}

	public static CountryService getService() {
		if (_service == null) {
			_service = (CountryService)PortalBeanLocatorUtil.locate(CountryService.class.getName());

			ReferenceRegistry.registerReference(CountryServiceUtil.class,
				"_service");
			MethodCache.remove(CountryService.class);
		}

		return _service;
	}

	public void setService(CountryService service) {
		MethodCache.remove(CountryService.class);

		_service = service;

		ReferenceRegistry.registerReference(CountryServiceUtil.class, "_service");
		MethodCache.remove(CountryService.class);
	}

	private static CountryService _service;
}