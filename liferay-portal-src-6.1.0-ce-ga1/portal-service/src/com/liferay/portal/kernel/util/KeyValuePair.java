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

package com.liferay.portal.kernel.util;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 */
public class KeyValuePair implements Comparable<KeyValuePair>, Serializable {

	public KeyValuePair() {
		this(null, null);
	}

	public KeyValuePair(String key, String value) {
		_key = key;
		_value = value;
	}

	public String getKey() {
		return _key;
	}

	public void setKey(String key) {
		_key = key;
	}

	public String getValue() {
		return _value;
	}

	public void setValue(String value) {
		_value = value;
	}

	public int compareTo(KeyValuePair kvp) {
		return _key.compareTo(kvp.getKey());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof KeyValuePair)) {
			return false;
		}

		KeyValuePair kvp = (KeyValuePair)obj;

		if (Validator.equals(_key, kvp._key)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		if (_key != null) {
			return _key.hashCode();
		}
		else {
			return 0;
		}
	}

	private String _key;
	private String _value;

}