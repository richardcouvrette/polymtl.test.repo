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

package com.liferay.portal.model;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletConstants {

	/**
	 * War file separator.
	 */
	public static final String WAR_SEPARATOR = "_WAR_";

	/**
	 * Instance separator.
	 */
	public static final String INSTANCE_SEPARATOR = "_INSTANCE_";

	/**
	 * Layout separator.
	 */
	public static final String LAYOUT_SEPARATOR = "_LAYOUT_";

	/**
	 * Default preferences.
	 */
	public static final String DEFAULT_PREFERENCES = "<portlet-preferences />";

	/**
	 * User principal strategy for screen name.
	 */
	public static final String USER_PRINCIPAL_STRATEGY_SCREEN_NAME =
		"screenName";

	/**
	 * User principal strategy for screen name.
	 */
	public static final String USER_PRINCIPAL_STRATEGY_USER_ID = "userId";

	/**
	 * Facebook integration method for FBML.
	 */
	public static final String FACEBOOK_INTEGRATION_FBML = "fbml";

	/**
	 * Facebook integration method for IFrame.
	 */
	public static final String FACEBOOK_INTEGRATION_IFRAME = "iframe";

	/**
	 * Returns the root portlet ID of the portlet.
	 *
	 * @return the root portlet ID of the portlet
	 */
	public static String getRootPortletId(String portletId) {
		int pos = portletId.indexOf(INSTANCE_SEPARATOR);

		if (pos == -1) {
			return portletId;
		}
		else {
			return portletId.substring(0, pos);
		}
	}

	/**
	 * Returns the instance ID of the portlet.
	 *
	 * @return the instance ID of the portlet
	 */
	public static String getInstanceId(String portletId) {
		int pos = portletId.indexOf(INSTANCE_SEPARATOR);

		if (pos == -1) {
			return null;
		}
		else {
			return portletId.substring(
				pos + INSTANCE_SEPARATOR.length(), portletId.length());
		}
	}

}