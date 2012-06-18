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

package com.liferay.portal.tools.deploy;

import com.liferay.portal.kernel.plugin.PluginPackage;
import com.liferay.portal.kernel.util.ServerDetector;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.Plugin;
import com.liferay.portal.util.InitUtil;

import java.io.File;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class HookDeployer extends BaseDeployer {

	public static void main(String[] args) {
		InitUtil.initWithSpring();

		List<String> wars = new ArrayList<String>();
		List<String> jars = new ArrayList<String>();

		for (String arg : args) {
			if (arg.endsWith(".war")) {
				wars.add(arg);
			}
			else if (arg.endsWith(".jar")) {
				jars.add(arg);
			}
		}

		new HookDeployer(wars, jars);
	}

	public HookDeployer() {
	}

	public HookDeployer(List<String> wars, List<String> jars) {
		super(wars, jars);
	}

	@Override
	public void copyXmls(
			File srcFile, String displayName, PluginPackage pluginPackage)
		throws Exception {

		super.copyXmls(srcFile, displayName, pluginPackage);

		if (appServerType.equals(ServerDetector.TOMCAT_ID)) {
			copyDependencyXml("context.xml", srcFile + "/META-INF");
		}
	}

	@Override
	public String getExtraContent(
			double webXmlVersion, File srcFile, String displayName)
		throws Exception {

		StringBundler sb = new StringBundler(6);

		String extraContent = super.getExtraContent(
			webXmlVersion, srcFile, displayName);

		sb.append(extraContent);

		// HookContextListener

		sb.append("<listener>");
		sb.append("<listener-class>");
		sb.append("com.liferay.portal.kernel.servlet.HookContextListener");
		sb.append("</listener-class>");
		sb.append("</listener>");

		return sb.toString();
	}

	@Override
	public String getPluginType() {
		return Plugin.TYPE_HOOK;
	}

}