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

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Account;
import com.liferay.portal.model.CacheField;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.model.Shard;
import com.liferay.portal.model.User;
import com.liferay.portal.model.VirtualHost;
import com.liferay.portal.service.AccountLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.service.ShardLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.VirtualHostLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PrefsPropsUtil;
import com.liferay.portal.util.PropsValues;

import java.security.Key;

import java.util.Locale;
import java.util.TimeZone;

/**
 * @author Brian Wing Shun Chan
 */
public class CompanyImpl extends CompanyBaseImpl {

	public CompanyImpl() {
	}

	@Override
	public int compareTo(Company company) {
		String webId1 = getWebId();
		String webId2 = company.getWebId();

		if (webId1.equals(PropsValues.COMPANY_DEFAULT_WEB_ID)) {
			return -1;
		}
		else if (webId2.equals(PropsValues.COMPANY_DEFAULT_WEB_ID)) {
			return 1;
		}
		else {
			return webId1.compareTo(webId2);
		}
	}

	public Account getAccount() throws PortalException, SystemException {
		return AccountLocalServiceUtil.getAccount(
			getCompanyId(), getAccountId());
	}

	public String getAdminName() {
		return "Administrator";
	}

	public String getAuthType() throws SystemException {
		return PrefsPropsUtil.getString(
			getCompanyId(), PropsKeys.COMPANY_SECURITY_AUTH_TYPE,
			PropsValues.COMPANY_SECURITY_AUTH_TYPE);
	}

	public User getDefaultUser() throws PortalException, SystemException {
		return UserLocalServiceUtil.getDefaultUser(getCompanyId());
	}

	public String getDefaultWebId() {
		return PropsValues.COMPANY_DEFAULT_WEB_ID;
	}

	public String getEmailAddress() {

		// Primary email address

		return "admin@" + getMx();
	}

	public Group getGroup() throws PortalException, SystemException {
		if (getCompanyId() > CompanyConstants.SYSTEM) {
			return GroupLocalServiceUtil.getCompanyGroup(getCompanyId());
		}

		return new GroupImpl();
	}

	@Override
	public Key getKeyObj() {
		if (_keyObj == null) {
			String key = getKey();

			if (Validator.isNotNull(key)) {
				_keyObj = (Key)Base64.stringToObjectSilent(key);
			}
		}

		return _keyObj;
	}

	public Locale getLocale() throws PortalException, SystemException {
		return getDefaultUser().getLocale();
	}

	public String getName() throws PortalException, SystemException {
		return getAccount().getName();
	}

	public String getPortalURL(long groupId)
		throws PortalException, SystemException {

		String portalURL = PortalUtil.getPortalURL(
			getVirtualHostname(), Http.HTTP_PORT, false);

		if (groupId <= 0) {
			return portalURL;
		}

		Group group = GroupLocalServiceUtil.getGroup(groupId);

		if (group.hasPublicLayouts()) {
			LayoutSet layoutSet = LayoutSetLocalServiceUtil.getLayoutSet(
				groupId, false);

			if (Validator.isNotNull(layoutSet.getVirtualHostname())) {
				portalURL = PortalUtil.getPortalURL(
					layoutSet.getVirtualHostname(), Http.HTTP_PORT, false);
			}
		}
		else if (group.hasPrivateLayouts()) {
			LayoutSet layoutSet = LayoutSetLocalServiceUtil.getLayoutSet(
				groupId, true);

			if (Validator.isNotNull(layoutSet.getVirtualHostname())) {
				portalURL = PortalUtil.getPortalURL(
					layoutSet.getVirtualHostname(), Http.HTTP_PORT, false);
			}
		}

		return portalURL;
	}

	public String getShardName() throws PortalException, SystemException {
		Shard shard = ShardLocalServiceUtil.getShard(
			Company.class.getName(), getCompanyId());

		return shard.getName();
	}

	public String getShortName() throws PortalException, SystemException {
		return getName();
	}

	public TimeZone getTimeZone() throws PortalException, SystemException {
		return getDefaultUser().getTimeZone();
	}

	public String getVirtualHostname() {
		try {
			VirtualHost virtualHost =
				VirtualHostLocalServiceUtil.fetchVirtualHost(getCompanyId(), 0);

			if (virtualHost == null) {
				return StringPool.BLANK;
			}
			else {
				return virtualHost.getHostname();
			}
		}
		catch (Exception e) {
			return StringPool.BLANK;
		}
	}

	public boolean hasCompanyMx(String emailAddress)
		throws SystemException {

		emailAddress = emailAddress.trim().toLowerCase();

		int pos = emailAddress.indexOf(CharPool.AT);

		if (pos == -1) {
			return false;
		}

		String mx = emailAddress.substring(pos + 1, emailAddress.length());

		if (mx.equals(getMx())) {
			return true;
		}

		String[] mailHostNames = PrefsPropsUtil.getStringArray(
			getCompanyId(), PropsKeys.ADMIN_MAIL_HOST_NAMES,
			StringPool.NEW_LINE, PropsValues.ADMIN_MAIL_HOST_NAMES);

		for (int i = 0; i < mailHostNames.length; i++) {
			if (mx.equalsIgnoreCase(mailHostNames[i])) {
				return true;
			}
		}

		return false;
	}

	public boolean isAutoLogin() throws SystemException {
		return PrefsPropsUtil.getBoolean(
			getCompanyId(), PropsKeys.COMPANY_SECURITY_AUTO_LOGIN,
			PropsValues.COMPANY_SECURITY_AUTO_LOGIN);
	}

	public boolean isSendPassword() throws SystemException {
		return PrefsPropsUtil.getBoolean(
			getCompanyId(), PropsKeys.COMPANY_SECURITY_SEND_PASSWORD,
			PropsValues.COMPANY_SECURITY_SEND_PASSWORD);
	}

	public boolean isSendPasswordResetLink() throws SystemException {
		return PrefsPropsUtil.getBoolean(
			getCompanyId(), PropsKeys.COMPANY_SECURITY_SEND_PASSWORD_RESET_LINK,
			PropsValues.COMPANY_SECURITY_SEND_PASSWORD_RESET_LINK);
	}

	public boolean isSiteLogo() throws SystemException {
		return PrefsPropsUtil.getBoolean(
			getCompanyId(), PropsKeys.COMPANY_SECURITY_SITE_LOGO,
			PropsValues.COMPANY_SECURITY_SITE_LOGO);
	}

	public boolean isStrangers() throws SystemException {
		return PrefsPropsUtil.getBoolean(
			getCompanyId(), PropsKeys.COMPANY_SECURITY_STRANGERS,
			PropsValues.COMPANY_SECURITY_STRANGERS);
	}

	public boolean isStrangersVerify() throws SystemException {
		return PrefsPropsUtil.getBoolean(
			getCompanyId(), PropsKeys.COMPANY_SECURITY_STRANGERS_VERIFY,
			PropsValues.COMPANY_SECURITY_STRANGERS_VERIFY);
	}

	public boolean isStrangersWithMx() throws SystemException {
		return PrefsPropsUtil.getBoolean(
			getCompanyId(), PropsKeys.COMPANY_SECURITY_STRANGERS_WITH_MX,
			PropsValues.COMPANY_SECURITY_STRANGERS_WITH_MX);
	}

	@Override
	public void setKey(String key) {
		_keyObj = null;

		super.setKey(key);
	}

	@Override
	public void setKeyObj(Key keyObj) {
		_keyObj = keyObj;
	}

	@CacheField
	private Key _keyObj;

}