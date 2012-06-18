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

package com.liferay.portal.lar;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.UserIdStrategy;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.persistence.UserUtil;

import java.util.List;

/**
 * @author Bruno Farache
 */
public class CurrentUserIdStrategy implements UserIdStrategy {

	public CurrentUserIdStrategy(User user) {
		_user = user;
	}

	public long getUserId(String userUuid) throws SystemException {
		if (Validator.isNull(userUuid)) {
			return _user.getUserId();
		}

		List<User> users = UserUtil.findByUuid(userUuid);

		for (User user : users) {
			if (user.getCompanyId() == _user.getCompanyId()) {
				return user.getUserId();
			}
		}

		return _user.getUserId();
	}

	private User _user;

}