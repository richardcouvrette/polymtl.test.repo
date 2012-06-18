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

import com.liferay.portal.DuplicatePasswordPolicyException;
import com.liferay.portal.NoSuchPasswordPolicyRelException;
import com.liferay.portal.PasswordPolicyNameException;
import com.liferay.portal.RequiredPasswordPolicyException;
import com.liferay.portal.kernel.cache.ThreadLocalCachable;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.PasswordPolicy;
import com.liferay.portal.model.PasswordPolicyRel;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.security.ldap.LDAPSettingsUtil;
import com.liferay.portal.service.base.PasswordPolicyLocalServiceBaseImpl;
import com.liferay.portal.util.PropsValues;

import java.util.Date;
import java.util.List;

/**
 * @author Scott Lee
 */
public class PasswordPolicyLocalServiceImpl
	extends PasswordPolicyLocalServiceBaseImpl {

	public PasswordPolicy addPasswordPolicy(
			long userId, boolean defaultPolicy, String name, String description,
			boolean changeable, boolean changeRequired, long minAge,
			boolean checkSyntax, boolean allowDictionaryWords,
			int minAlphanumeric, int minLength, int minLowerCase,
			int minNumbers, int minSymbols, int minUpperCase, boolean history,
			int historyCount, boolean expireable, long maxAge, long warningTime,
			int graceLimit, boolean lockout, int maxFailure,
			long lockoutDuration, long resetFailureCount,
			long resetTicketMaxAge)
		throws PortalException, SystemException {

		// Password policy

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		validate(0, user.getCompanyId(), name);

		long passwordPolicyId = counterLocalService.increment();

		PasswordPolicy passwordPolicy = passwordPolicyPersistence.create(
			passwordPolicyId);

		passwordPolicy.setUserId(userId);
		passwordPolicy.setCompanyId(user.getCompanyId());
		passwordPolicy.setUserName(user.getFullName());
		passwordPolicy.setCreateDate(now);
		passwordPolicy.setModifiedDate(now);
		passwordPolicy.setDefaultPolicy(defaultPolicy);
		passwordPolicy.setName(name);
		passwordPolicy.setDescription(description);
		passwordPolicy.setChangeable(changeable);
		passwordPolicy.setChangeRequired(changeRequired);
		passwordPolicy.setMinAge(minAge);
		passwordPolicy.setCheckSyntax(checkSyntax);
		passwordPolicy.setAllowDictionaryWords(allowDictionaryWords);
		passwordPolicy.setMinAlphanumeric(minAlphanumeric);
		passwordPolicy.setMinLength(minLength);
		passwordPolicy.setMinLowerCase(minLowerCase);
		passwordPolicy.setMinNumbers(minNumbers);
		passwordPolicy.setMinSymbols(minSymbols);
		passwordPolicy.setMinUpperCase(minUpperCase);
		passwordPolicy.setHistory(history);
		passwordPolicy.setHistoryCount(historyCount);
		passwordPolicy.setExpireable(expireable);
		passwordPolicy.setMaxAge(maxAge);
		passwordPolicy.setWarningTime(warningTime);
		passwordPolicy.setGraceLimit(graceLimit);
		passwordPolicy.setLockout(lockout);
		passwordPolicy.setMaxFailure(maxFailure);
		passwordPolicy.setLockoutDuration(lockoutDuration);
		passwordPolicy.setRequireUnlock(lockoutDuration == 0);
		passwordPolicy.setResetFailureCount(resetFailureCount);
		passwordPolicy.setResetTicketMaxAge(resetTicketMaxAge);

		passwordPolicyPersistence.update(passwordPolicy, false);

		// Resources

		if (!user.isDefaultUser()) {
			resourceLocalService.addResources(
				user.getCompanyId(), 0, userId, PasswordPolicy.class.getName(),
				passwordPolicy.getPasswordPolicyId(), false, false, false);
		}

		return passwordPolicy;
	}

	public void checkDefaultPasswordPolicy(long companyId)
		throws PortalException, SystemException {

		String defaultPasswordPolicyName =
			PropsValues.PASSWORDS_DEFAULT_POLICY_NAME;

		PasswordPolicy defaultPasswordPolicy =
			passwordPolicyPersistence.fetchByC_N(
				companyId, defaultPasswordPolicyName);

		if (defaultPasswordPolicy == null) {
			long defaultUserId = userLocalService.getDefaultUserId(companyId);

			addPasswordPolicy(
				defaultUserId, true, defaultPasswordPolicyName,
				defaultPasswordPolicyName, true, true, 0, false, true, 0, 6,
				0, 1, 0, 1, false, 6, false, 8640000, 86400, 0, false, 3, 0,
				600, 86400);
		}
	}

	@Override
	public void deletePasswordPolicy(long passwordPolicyId)
		throws PortalException, SystemException {

		PasswordPolicy passwordPolicy =
			passwordPolicyPersistence.findByPrimaryKey(passwordPolicyId);

		deletePasswordPolicy(passwordPolicy);
	}

	@Override
	public void deletePasswordPolicy(PasswordPolicy passwordPolicy)
		throws PortalException, SystemException {

		if (passwordPolicy.isDefaultPolicy()) {
			throw new RequiredPasswordPolicyException();
		}

		// Password policy relations

		passwordPolicyRelLocalService.deletePasswordPolicyRels(
			passwordPolicy.getPasswordPolicyId());

		// Resources

		resourceLocalService.deleteResource(
			passwordPolicy.getCompanyId(), PasswordPolicy.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL,
			passwordPolicy.getPasswordPolicyId());

		// Password policy

		passwordPolicyPersistence.remove(passwordPolicy);
	}

	public PasswordPolicy getDefaultPasswordPolicy(long companyId)
		throws PortalException, SystemException {

		if (LDAPSettingsUtil.isPasswordPolicyEnabled(companyId)) {
			return null;
		}

		return passwordPolicyPersistence.findByC_DP(companyId, true);
	}

	@Override
	public PasswordPolicy getPasswordPolicy(long passwordPolicyId)
		throws PortalException, SystemException {

		return passwordPolicyPersistence.findByPrimaryKey(passwordPolicyId);
	}

	/**
	 * @deprecated
	 */
	public PasswordPolicy getPasswordPolicy(
			long companyId, long organizationId, long locationId)
		throws PortalException, SystemException {

		return getPasswordPolicy(
			companyId, new long[] {organizationId, locationId});
	}

	public PasswordPolicy getPasswordPolicy(
			long companyId, long[] organizationIds)
		throws PortalException, SystemException {

		if (LDAPSettingsUtil.isPasswordPolicyEnabled(companyId)) {
			return null;
		}

		if ((organizationIds == null) || (organizationIds.length == 0)) {
			return getDefaultPasswordPolicy(companyId);
		}

		PasswordPolicyRel passwordPolicyRel = null;

		for (int i = 0; i < organizationIds.length; i++) {
			long organizationId = organizationIds[i];

			try {
				passwordPolicyRel =
					passwordPolicyRelLocalService.getPasswordPolicyRel(
						Organization.class.getName(), organizationId);

				return getPasswordPolicy(
					passwordPolicyRel.getPasswordPolicyId());
			}
			catch (NoSuchPasswordPolicyRelException nsppre) {
			}
		}

		return getDefaultPasswordPolicy(companyId);
	}

	@ThreadLocalCachable
	public PasswordPolicy getPasswordPolicyByUserId(long userId)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);

		if (LDAPSettingsUtil.isPasswordPolicyEnabled(user.getCompanyId())) {
			return null;
		}

		PasswordPolicyRel passwordPolicyRel =
			passwordPolicyRelLocalService.fetchPasswordPolicyRel(
				User.class.getName(), userId);

		if (passwordPolicyRel != null) {
			return getPasswordPolicy(passwordPolicyRel.getPasswordPolicyId());
		}
		else {
			long[] organizationIds = user.getOrganizationIds();

			return getPasswordPolicy(user.getCompanyId(), organizationIds);
		}
	}

	public List<PasswordPolicy> search(
			long companyId, String name, int start, int end,
			OrderByComparator obc)
		throws SystemException {

		return passwordPolicyFinder.findByC_N(companyId, name, start, end, obc);
	}

	public int searchCount(long companyId, String name)
		throws SystemException {

		return passwordPolicyFinder.countByC_N(companyId, name);
	}

	public PasswordPolicy updatePasswordPolicy(
			long passwordPolicyId, String name, String description,
			boolean changeable, boolean changeRequired, long minAge,
			boolean checkSyntax, boolean allowDictionaryWords,
			int minAlphanumeric, int minLength, int minLowerCase,
			int minNumbers, int minSymbols, int minUpperCase, boolean history,
			int historyCount, boolean expireable, long maxAge,
			long warningTime, int graceLimit, boolean lockout, int maxFailure,
			long lockoutDuration, long resetFailureCount,
			long resetTicketMaxAge)
		throws PortalException, SystemException {

		Date now = new Date();

		PasswordPolicy passwordPolicy =
			passwordPolicyPersistence.findByPrimaryKey(passwordPolicyId);

		if (!passwordPolicy.getDefaultPolicy()) {
			validate(passwordPolicyId, passwordPolicy.getCompanyId(), name);

			passwordPolicy.setName(name);
		}

		passwordPolicy.setModifiedDate(now);
		passwordPolicy.setDescription(description);
		passwordPolicy.setChangeable(changeable);
		passwordPolicy.setChangeRequired(changeRequired);
		passwordPolicy.setMinAge(minAge);
		passwordPolicy.setCheckSyntax(checkSyntax);
		passwordPolicy.setAllowDictionaryWords(allowDictionaryWords);
		passwordPolicy.setMinAlphanumeric(minAlphanumeric);
		passwordPolicy.setMinLength(minLength);
		passwordPolicy.setMinLowerCase(minLowerCase);
		passwordPolicy.setMinNumbers(minNumbers);
		passwordPolicy.setMinSymbols(minSymbols);
		passwordPolicy.setMinUpperCase(minUpperCase);
		passwordPolicy.setHistory(history);
		passwordPolicy.setHistoryCount(historyCount);
		passwordPolicy.setExpireable(expireable);
		passwordPolicy.setMaxAge(maxAge);
		passwordPolicy.setWarningTime(warningTime);
		passwordPolicy.setGraceLimit(graceLimit);
		passwordPolicy.setLockout(lockout);
		passwordPolicy.setMaxFailure(maxFailure);
		passwordPolicy.setLockoutDuration(lockoutDuration);
		passwordPolicy.setRequireUnlock(lockoutDuration == 0);
		passwordPolicy.setResetFailureCount(resetFailureCount);
		passwordPolicy.setResetTicketMaxAge(resetTicketMaxAge);

		passwordPolicyPersistence.update(passwordPolicy, false);

		return passwordPolicy;
	}

	protected void validate(long passwordPolicyId, long companyId, String name)
		throws PortalException, SystemException {

		if ((Validator.isNull(name)) || (Validator.isNumber(name)) ||
			(name.indexOf(CharPool.COMMA) != -1) ||
			(name.indexOf(CharPool.STAR) != -1)) {

			throw new PasswordPolicyNameException();
		}

		PasswordPolicy passwordPolicy = passwordPolicyPersistence.fetchByC_N(
			companyId, name);

		if (passwordPolicy != null) {
			if ((passwordPolicyId <= 0) ||
				(passwordPolicy.getPasswordPolicyId() != passwordPolicyId)) {

				throw new DuplicatePasswordPolicyException();
			}
		}
	}

}