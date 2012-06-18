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

package com.liferay.portal.security.pwd;

import com.liferay.portal.kernel.test.TestCase;
import com.liferay.portal.model.PasswordPolicy;
import com.liferay.portal.model.impl.PasswordPolicyImpl;

/**
 * @author Mika Koivisto
 */
public class PasswordPolicyToolkitTest extends TestCase {

	@Override
	public void setUp() throws Exception {
		_passwordPolicyToolkit = new PasswordPolicyToolkit();

		_passwordPolicy = new PasswordPolicyImpl();

		_passwordPolicy.setAllowDictionaryWords(true);
		_passwordPolicy.setChangeable(true);
		_passwordPolicy.setCheckSyntax(true);
		_passwordPolicy.setMinAlphanumeric(5);
		_passwordPolicy.setMinLength(8);
		_passwordPolicy.setMinLowerCase(2);
		_passwordPolicy.setMinUpperCase(2);
		_passwordPolicy.setMinNumbers(1);
		_passwordPolicy.setMinSymbols(1);
	}

	public void testGeneratePassword() {
		String password = _passwordPolicyToolkit.generate(_passwordPolicy);

		try {
			_passwordPolicyToolkit.validate(
				password, password, _passwordPolicy);
		}
		catch (Exception e) {
			fail("Generated password does not validate against policy");
		}
	}

	public void testValidateLength() {
		assertEquals(false, validate("xH9fxM@"));
	}

	public void testValidateMinAlphanumeric() {
		assertEquals(false, validate("xH9f.,@-"));
	}

	public void testValidateMinLowerChars() {
		assertEquals(false, validate("xHFXM@W"));
	}

	public void testValidateMinNumbers() {
		assertEquals(false, validate("xHafxMkw"));
	}

	public void testValidateMinSpecial() {
		assertEquals(false, validate("xH9fxMkw"));
	}

	public void testValidateMinUpperChars() {
		assertEquals(false, validate("xh9fxM@w"));
	}

	public void testValidateValid() {
		assertEquals(true, validate("xH9fxM@w"));
	}

	protected boolean validate(String password) {
		try {
			_passwordPolicyToolkit.validate(
				password, password, _passwordPolicy);
		}
		catch (Exception e) {
			return false;
		}

		return true;
	}

	private PasswordPolicy _passwordPolicy;
	private PasswordPolicyToolkit _passwordPolicyToolkit;

}