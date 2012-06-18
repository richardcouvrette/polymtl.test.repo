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

package com.liferay.portal.kernel.security.jaas;

import java.io.Serializable;

import java.security.Principal;
import java.security.acl.Group;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class PortalGroup
	extends PortalPrincipal implements Group, Serializable {

	public PortalGroup(String groupName) {
		super(groupName);
	}

	public boolean addMember(Principal user) {
		if (!_members.containsKey(user)) {
			_members.put(user, user);

			return true;
		}
		else {
			return false;
		}
	}

	public boolean isMember(Principal member) {
		boolean isMember = _members.containsKey(member);

		if (!isMember) {
			Iterator<Principal> itr = _members.values().iterator();

			while (!isMember && itr.hasNext()) {
				Principal principal = itr.next();

				if (principal instanceof Group) {
					Group group = (Group)principal;

					isMember = group.isMember(member);
				}
			}
		}

		return isMember;
	}

	public Enumeration<Principal> members() {
		return Collections.enumeration(_members.values());
	}

	public boolean removeMember(Principal user) {
		Principal principal = _members.remove(user);

		if (principal != null) {
			return true;
		}
		else {
			return false;
		}
	}

	private Map<Principal, Principal> _members =
		new HashMap<Principal, Principal>();

}