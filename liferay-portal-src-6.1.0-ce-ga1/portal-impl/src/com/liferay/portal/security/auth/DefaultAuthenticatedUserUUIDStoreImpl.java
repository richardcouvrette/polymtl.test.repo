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

import com.liferay.portal.kernel.concurrent.ConcurrentHashSet;

import java.util.Set;

/**
 * @author Michael C. Han
 */
public class DefaultAuthenticatedUserUUIDStoreImpl
	implements AuthenticatedUserUUIDStore {

	public boolean exists(String userUUID) {
		return _userUUIDStore.contains(userUUID);
	}

	public boolean register(String userUUID) {
		return _userUUIDStore.add(userUUID);
	}

	public boolean unregister(String userUUID) {
		return _userUUIDStore.remove(userUUID);
	}

	private Set<String> _userUUIDStore = new ConcurrentHashSet<String>();

}