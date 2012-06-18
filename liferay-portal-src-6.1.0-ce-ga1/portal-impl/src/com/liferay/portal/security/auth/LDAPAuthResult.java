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

import com.liferay.portal.kernel.util.StringPool;

import javax.naming.ldap.Control;

/**
 * @author Scott Lee
 */
public class LDAPAuthResult {

	public boolean isAuthenticated() {
		return _authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		_authenticated = authenticated;
	}

	public String getErrorMessage() {
		return _errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		_errorMessage = errorMessage;
	}

	public String getResponseControl() {
		return _responseControl;
	}

	public void setResponseControl(Control[] response) {
		if ((response != null) && (response.length > 0)) {
			_responseControl = response[0].getID();
		}
	}

	private boolean _authenticated;
	private String _errorMessage;
	private String _responseControl = StringPool.BLANK;

}