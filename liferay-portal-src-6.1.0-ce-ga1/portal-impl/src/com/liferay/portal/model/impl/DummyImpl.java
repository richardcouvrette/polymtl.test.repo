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

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Dummy;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 */
public class DummyImpl extends BaseModelImpl<Dummy> implements Dummy {

	public DummyImpl() {
	}

	@Override
	public Object clone() {
		return new DummyImpl();
	}

	public int compareTo(Dummy dummy) {
		return 0;
	}

	public Class<?> getModelClass() {
		return DummyImpl.class;
	}

	public String getModelClassName() {
		return DummyImpl.class.getName();
	}

	public Serializable getPrimaryKeyObj() {
		return StringPool.BLANK;
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
	}

	@Override
	public void resetOriginalValues() {
	}

	public String toXmlString() {
		return StringPool.BLANK;
	}

}