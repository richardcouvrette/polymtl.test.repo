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

package com.liferay.portal.security.auth;

import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.facebook.FacebookConnectUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.WebKeys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Wilson Man
 */
public class FacebookAutoLogin implements AutoLogin {

	public String[] login(
		HttpServletRequest request, HttpServletResponse response) {

		String[] credentials = null;

		try {
			long companyId = PortalUtil.getCompanyId(request);

			if (!FacebookConnectUtil.isEnabled(companyId)) {
				return credentials;
			}

			HttpSession session = request.getSession();

			String emailAddress = (String)session.getAttribute(
				WebKeys.FACEBOOK_USER_EMAIL_ADDRESS);

			User user = null;

			if (Validator.isNotNull(emailAddress)) {
				session.removeAttribute(WebKeys.FACEBOOK_USER_EMAIL_ADDRESS);

				try {
					user = UserLocalServiceUtil.getUserByEmailAddress(
						companyId, emailAddress);
				}
				catch (NoSuchUserException nsue) {
				}
			}
			else {
				long facebookId = GetterUtil.getLong(
					(String)session.getAttribute(WebKeys.FACEBOOK_USER_ID));

				if (facebookId > 0) {
					try {
						user = UserLocalServiceUtil.getUserByFacebookId(
							companyId, facebookId);
					}
					catch (NoSuchUserException nsue) {
						return credentials;
					}
				}
				else {
					return credentials;
				}
			}

			credentials = new String[3];

			credentials[0] = String.valueOf(user.getUserId());
			credentials[1] = user.getPassword();
			credentials[2] = Boolean.FALSE.toString();
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return credentials;
	}

	private static Log _log = LogFactoryUtil.getLog(FacebookAutoLogin.class);

}