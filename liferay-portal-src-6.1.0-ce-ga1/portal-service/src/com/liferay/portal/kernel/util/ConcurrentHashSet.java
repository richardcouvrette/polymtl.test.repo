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

import java.util.Set;

/**
 * @author     Brian Wing Shun Chan
 * @deprecated {@link com.liferay.portal.kernel.concurrent.ConcurrentHashSet}
 */
public class ConcurrentHashSet<E>
	extends com.liferay.portal.kernel.concurrent.ConcurrentHashSet<E> {

	public ConcurrentHashSet() {
		super();
	}

	public ConcurrentHashSet(int capacity) {
		super(capacity);
	}

	public ConcurrentHashSet(Set<E> set) {
		super(set);
	}

}