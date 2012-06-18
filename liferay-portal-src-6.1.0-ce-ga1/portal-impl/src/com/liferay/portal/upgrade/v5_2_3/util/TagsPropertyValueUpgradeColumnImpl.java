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

package com.liferay.portal.upgrade.v5_2_3.util;

import com.liferay.portal.kernel.upgrade.util.BaseUpgradeColumnImpl;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Brian Wing Shun Chan
 */
public class TagsPropertyValueUpgradeColumnImpl extends BaseUpgradeColumnImpl {

	public TagsPropertyValueUpgradeColumnImpl(String name) {
		super(name);
	}

	public Object getNewValue(Object oldValue) throws Exception {
		String value = (String)oldValue;

		if (Validator.isNull(value)) {
			return StringPool.BLANK;
		}
		else {
			return StringUtil.shorten(value, 255, StringPool.BLANK);
		}
	}

}