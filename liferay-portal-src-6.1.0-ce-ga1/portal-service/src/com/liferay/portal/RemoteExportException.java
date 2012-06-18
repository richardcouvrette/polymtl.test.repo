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

package com.liferay.portal;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Raymond Augé
 */
public class RemoteExportException extends PortalException {

	public static final int BAD_CONNECTION = 1;

	public static final int NO_GROUP = 2;

	public static final int NO_LAYOUTS = 3;

	public static final int NO_PERMISSIONS = 4;

	public RemoteExportException(int type) {
		_type = type;
	}

	public int getType() {
		return _type;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public String getURL() {
		return _url;
	}

	public void setURL(String url) {
		_url = url;
	}

	private int _type;
	private long _groupId;
	private String _url;

}