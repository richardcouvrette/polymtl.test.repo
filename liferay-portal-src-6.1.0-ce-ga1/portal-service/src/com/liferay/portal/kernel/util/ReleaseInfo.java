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

import java.text.DateFormat;

import java.util.Date;

/**
 * @author Brian Wing Shun Chan
 */
public class ReleaseInfo {

	static String name = "Liferay Portal Community Edition";

	static String vendor = "Liferay, Inc.";

	static String version = "6.1.0";

	static String versionDisplayName = "6.1.0 CE";

	static String codeName = "Paton";

	static String build = "6100";

	static int buildNumber = Integer.parseInt(build);

	static String date = "January 6, 2012";

	static String releaseInfoPrefix = System.getProperty(
		"liferay.release.info.prefix" , StringPool.BLANK);

	static String releaseInfoSuffix = System.getProperty(
		"liferay.release.info.suffix" , StringPool.BLANK);

	static String releaseInfo =
		releaseInfoPrefix + name + " " + versionDisplayName + " (" + codeName +
			" / Build " + build + " / " + date + ")" + releaseInfoSuffix;

	static String serverInfo = name + " / " + version;

	public static int RELEASE_5_0_0_BUILD_NUMBER = 5000;

	public static int RELEASE_5_0_1_BUILD_NUMBER = 5001;

	public static int RELEASE_5_1_0_BUILD_NUMBER = 5100;

	public static int RELEASE_5_1_1_BUILD_NUMBER = 5101;

	public static int RELEASE_5_1_2_BUILD_NUMBER = 5102;

	public static int RELEASE_5_2_0_BUILD_NUMBER = 5200;

	public static int RELEASE_5_2_1_BUILD_NUMBER = 5201;

	public static int RELEASE_5_2_2_BUILD_NUMBER = 5202;

	public static int RELEASE_5_2_3_BUILD_NUMBER = 5203;

	public static int RELEASE_6_0_0_BUILD_NUMBER = 6000;

	public static int RELEASE_6_0_1_BUILD_NUMBER = 6001;

	public static int RELEASE_6_0_2_BUILD_NUMBER = 6002;

	public static int RELEASE_6_0_3_BUILD_NUMBER = 6003;

	public static int RELEASE_6_0_4_BUILD_NUMBER = 6004;

	public static int RELEASE_6_0_5_BUILD_NUMBER = 6005;

	public static int RELEASE_6_0_6_BUILD_NUMBER = 6006;

	public static int RELEASE_6_1_0_BUILD_NUMBER = 6100;

	public static final String getName() {
		return name;
	}

	public static String getVendor() {
		return vendor;
	}

	public static final String getVersion() {
		return version;
	}

	public static final String getCodeName() {
		return codeName;
	}

	public static final int getBuildNumber() {
		return buildNumber;
	}

	public static final Date getBuildDate() {
		DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);

		return GetterUtil.getDate(date, df);
	}

	public static final String getReleaseInfo() {
		return releaseInfo;
	}

	public static final String getServerInfo() {
		return serverInfo;
	}

}