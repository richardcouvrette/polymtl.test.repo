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

package com.liferay.portlet;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.HashCode;
import com.liferay.portal.kernel.util.HashCodeFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.PortalPreferencesLocalServiceUtil;

import java.io.IOException;
import java.io.Serializable;

import java.util.Collections;
import java.util.Map;

import javax.portlet.ReadOnlyException;

/**
 * @author Brian Wing Shun Chan
 * @author Alexander Chow
 */
public class PortalPreferencesImpl
	extends BasePreferencesImpl
	implements Cloneable, PortalPreferences, Serializable {

	public PortalPreferencesImpl() {
		this(0, 0, 0, Collections.<String, Preference>emptyMap(), false);
	}

	public PortalPreferencesImpl(
		long companyId, long ownerId, int ownerType,
		Map<String, Preference> preferences, boolean signedIn) {

		super(companyId, ownerId, ownerType, preferences);

		_signedIn = signedIn;
	}

	@Override
	public Object clone() {
		return new PortalPreferencesImpl(
			getCompanyId(), getOwnerId(), getOwnerType(),
			getOriginalPreferences(), isSignedIn());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		PortalPreferencesImpl portalPreferences = (PortalPreferencesImpl)obj;

		if (this == portalPreferences) {
			return true;
		}

		if ((getCompanyId() == portalPreferences.getCompanyId()) &&
			(getOwnerId() == portalPreferences.getOwnerId()) &&
			(getOwnerType() == portalPreferences.getOwnerType()) &&
			(getMap().equals(portalPreferences.getMap()))) {

			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public String getValue(String namespace, String key) {
		return getValue(namespace, key, null);
	}

	public String getValue(String namespace, String key, String defaultValue) {
		key = _encodeKey(namespace, key);

		return super.getValue(key, defaultValue);
	}

	public String[] getValues(String namespace, String key) {
		return getValues(namespace, key, null);
	}

	public String[] getValues(
		String namespace, String key, String[] defaultValue) {

		key = _encodeKey(namespace, key);

		return super.getValues(key, defaultValue);
	}

	@Override
	public int hashCode() {
		HashCode hashCode = HashCodeFactoryUtil.getHashCode();

		hashCode.append(getCompanyId());
		hashCode.append(getOwnerId());
		hashCode.append(getOwnerType());
		hashCode.append(getPreferences());

		return hashCode.toHashCode();
	}

	public boolean isSignedIn() {
		return _signedIn;
	}

	@Override
	public void reset(String key) throws ReadOnlyException {
		if (isReadOnly(key)) {
			throw new ReadOnlyException(key);
		}

		Map<String, Preference> modifiedPreferences = getModifiedPreferences();

		modifiedPreferences.remove(key);
	}

	public void resetValues(String namespace) {
		try {
			Map<String, Preference> preferences = getPreferences();

			for (Map.Entry<String, Preference> entry : preferences.entrySet()) {
				String key = entry.getKey();

				if (key.startsWith(namespace) && !isReadOnly(key)) {
					reset(key);
				}
			}

			store();
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	public void setSignedIn(boolean signedIn) {
		_signedIn = signedIn;
	}

	public void setValue(String namespace, String key, String value) {
		if (Validator.isNull(key) || (key.equals(_RANDOM_KEY))) {
			return;
		}

		key = _encodeKey(namespace, key);

		try {
			if (value != null) {
				super.setValue(key, value);
			}
			else {
				reset(key);
			}

			if (_signedIn) {
				store();
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	public void setValues(String namespace, String key, String[] values) {
		if (Validator.isNull(key) || (key.equals(_RANDOM_KEY))) {
			return;
		}

		key = _encodeKey(namespace, key);

		try {
			if (values != null) {
				super.setValues(key, values);
			}
			else {
				reset(key);
			}

			if (_signedIn) {
				store();
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	@Override
	public void store() throws IOException {
		try {
			PortalPreferencesLocalServiceUtil.updatePreferences(
				getOwnerId(), getOwnerType(), this);
		}
		catch (SystemException se) {
			throw new IOException(se.getMessage());
		}
	}

	private String _encodeKey(String namespace, String key) {
		if (Validator.isNull(namespace)) {
			return key;
		}
		else {
			return namespace + StringPool.POUND + key;
		}
	}

	private static final String _RANDOM_KEY = "r";

	private static Log _log = LogFactoryUtil.getLog(PortalPreferences.class);

	private boolean _signedIn;

}