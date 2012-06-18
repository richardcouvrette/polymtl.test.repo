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

package com.liferay.portal.upgrade.util;

import com.liferay.portal.kernel.upgrade.util.ValueMapper;

import java.util.Collections;
import java.util.Iterator;

/**
 * @author Alexander Chow
 * @author Brian Wing Shun Chan
 */
public class DatabaseValueMapper implements ValueMapper {

	public DatabaseValueMapper() {

		// Delete

	}

	public void appendException(Object exception) {

		// Exceptions

	}

	public Object getNewValue(Object oldValue) throws Exception {

		// Select

		return null;
	}

	public Iterator<Object> iterator() throws Exception {
		return Collections.emptyList().iterator();
	}

	public void mapValue(Object oldValue, Object newValue) throws Exception {

		// Insert

	}

	public int size() throws Exception {
		return 0;
	}

}