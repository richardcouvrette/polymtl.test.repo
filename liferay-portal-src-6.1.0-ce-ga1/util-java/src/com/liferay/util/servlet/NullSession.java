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

package com.liferay.util.servlet;

import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.util.PwdGenerator;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

/**
 * @author Brian Wing Shun Chan
 */
public class NullSession implements HttpSession {

	public NullSession() {
		_attributes = new HashMap<String, Object>();
		_creationTime = System.currentTimeMillis();
		_id =
			NullSession.class.getName() + StringPool.POUND +
				PwdGenerator.getPinNumber();
		_lastAccessedTime = _creationTime;
		_maxInactiveInterval = 0;
		_servletContext = null;
		_new = true;
	}

	public Object getAttribute(String name) {
		return _attributes.get(name);
	}

	public Enumeration<String> getAttributeNames() {
		return Collections.enumeration(_attributes.keySet());
	}

	public long getCreationTime() {
		return _creationTime;
	}

	public String getId() {
		return _id;
	}

	public long getLastAccessedTime() {
		return _lastAccessedTime;
	}

	public int getMaxInactiveInterval() {
		return _maxInactiveInterval;
	}

	public ServletContext getServletContext() {
		return _servletContext;
	}

	/**
	 * @deprecated
	 */
	public javax.servlet.http.HttpSessionContext getSessionContext() {
		return null;
	}

	public Object getValue(String name) {
		return getAttribute(name);
	}

	public String[] getValueNames() {
		List<String> names = ListUtil.fromEnumeration(getAttributeNames());

		return names.toArray(new String[0]);
	}

	public void invalidate() {
		_attributes.clear();
	}

	public boolean isNew() {
		return _new;
	}

	public void putValue(String name, Object value) {
		setAttribute(name, value);
	}

	public void removeAttribute(String name) {
		_attributes.remove(name);
	}

	public void removeValue(String name) {
		removeAttribute(name);
	}

	public void setAttribute(String name, Object value) {
		_attributes.put(name, value);
	}

	public void setMaxInactiveInterval(int maxInactiveInterval) {
		_maxInactiveInterval = maxInactiveInterval;
	}

	private Map<String, Object> _attributes;
	private long _creationTime;
	private String _id;
	private long _lastAccessedTime;
	private int _maxInactiveInterval;
	private ServletContext _servletContext;
	private boolean _new;

}