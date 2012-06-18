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

/**
 * <p>
 * This class is a wrapper for {@link PasswordPolicyService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PasswordPolicyService
 * @generated
 */
public class PasswordPolicyServiceWrapper implements PasswordPolicyService,
	ServiceWrapper<PasswordPolicyService> {
	public PasswordPolicyServiceWrapper(
		PasswordPolicyService passwordPolicyService) {
		_passwordPolicyService = passwordPolicyService;
	}

	public com.liferay.portal.model.PasswordPolicy addPasswordPolicy(
		java.lang.String name, java.lang.String description,
		boolean changeable, boolean changeRequired, long minAge,
		boolean checkSyntax, boolean allowDictionaryWords, int minAlphanumeric,
		int minLength, int minLowerCase, int minNumbers, int minSymbols,
		int minUpperCase, boolean history, int historyCount,
		boolean expireable, long maxAge, long warningTime, int graceLimit,
		boolean lockout, int maxFailure, long lockoutDuration,
		long resetFailureCount, long resetTicketMaxAge)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _passwordPolicyService.addPasswordPolicy(name, description,
			changeable, changeRequired, minAge, checkSyntax,
			allowDictionaryWords, minAlphanumeric, minLength, minLowerCase,
			minNumbers, minSymbols, minUpperCase, history, historyCount,
			expireable, maxAge, warningTime, graceLimit, lockout, maxFailure,
			lockoutDuration, resetFailureCount, resetTicketMaxAge);
	}

	public void deletePasswordPolicy(long passwordPolicyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_passwordPolicyService.deletePasswordPolicy(passwordPolicyId);
	}

	public com.liferay.portal.model.PasswordPolicy updatePasswordPolicy(
		long passwordPolicyId, java.lang.String name,
		java.lang.String description, boolean changeable,
		boolean changeRequired, long minAge, boolean checkSyntax,
		boolean allowDictionaryWords, int minAlphanumeric, int minLength,
		int minLowerCase, int minNumbers, int minSymbols, int minUpperCase,
		boolean history, int historyCount, boolean expireable, long maxAge,
		long warningTime, int graceLimit, boolean lockout, int maxFailure,
		long lockoutDuration, long resetFailureCount, long resetTicketMaxAge)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _passwordPolicyService.updatePasswordPolicy(passwordPolicyId,
			name, description, changeable, changeRequired, minAge, checkSyntax,
			allowDictionaryWords, minAlphanumeric, minLength, minLowerCase,
			minNumbers, minSymbols, minUpperCase, history, historyCount,
			expireable, maxAge, warningTime, graceLimit, lockout, maxFailure,
			lockoutDuration, resetFailureCount, resetTicketMaxAge);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public PasswordPolicyService getWrappedPasswordPolicyService() {
		return _passwordPolicyService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedPasswordPolicyService(
		PasswordPolicyService passwordPolicyService) {
		_passwordPolicyService = passwordPolicyService;
	}

	public PasswordPolicyService getWrappedService() {
		return _passwordPolicyService;
	}

	public void setWrappedService(PasswordPolicyService passwordPolicyService) {
		_passwordPolicyService = passwordPolicyService;
	}

	private PasswordPolicyService _passwordPolicyService;
}