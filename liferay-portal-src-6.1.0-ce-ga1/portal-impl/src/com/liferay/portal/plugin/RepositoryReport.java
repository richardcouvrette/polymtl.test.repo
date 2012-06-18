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

package com.liferay.portal.plugin;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author Jorge Ferrer
 */
public class RepositoryReport {

	public static final String SUCCESS = "success";

	public void addError(String repositoryURL, PluginPackageException ppe) {
		StringBundler sb = new StringBundler(3);

		if (Validator.isNotNull(ppe.getMessage())) {
			sb.append(ppe.getMessage());
		}

		if ((ppe.getCause() != null) &&
			Validator.isNull(ppe.getCause().getMessage())) {

			sb.append(ppe.getCause().getMessage());
		}

		if (sb.length() == 0) {
			sb.append(ppe.toString());
		}

		_reportMap.put(repositoryURL, sb.toString());
	}

	public void addSuccess(String repositoryURL) {
		_reportMap.put(repositoryURL, SUCCESS);
	}

	public Set<String> getRepositoryURLs() {
		return _reportMap.keySet();
	}

	public String getState(String repositoryURL) {
		return _reportMap.get(repositoryURL);
	}

	@Override
	public String toString() {
		Iterator<String> itr = getRepositoryURLs().iterator();

		if (getRepositoryURLs().isEmpty()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(getRepositoryURLs().size() * 3);

		while (itr.hasNext()) {
			String repositoryURL = itr.next();

			sb.append(repositoryURL);
			sb.append(": ");
			sb.append(_reportMap.get(repositoryURL));
		}

		return sb.toString();
	}

	private Map<String, String> _reportMap = new TreeMap<String, String>();

}