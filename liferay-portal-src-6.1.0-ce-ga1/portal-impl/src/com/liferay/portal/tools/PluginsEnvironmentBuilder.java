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

package com.liferay.portal.tools;

import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.FileImpl;
import com.liferay.portal.util.PropsValues;
import com.liferay.util.UniqueList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.oro.io.GlobFilenameFilter;
import org.apache.tools.ant.DirectoryScanner;

/**
 * @author Alexander Chow
 * @author Brian Wing Shun Chan
 */
public class PluginsEnvironmentBuilder {

	public static void main(String[] args) throws Exception {
		try {
			File dir = new File(System.getProperty("plugins.env.dir"));

			new PluginsEnvironmentBuilder(dir);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public PluginsEnvironmentBuilder(File dir) throws Exception {
		DirectoryScanner ds = new DirectoryScanner();

		ds.setBasedir(dir);
		ds.setIncludes(new String[] {"**\\liferay-plugin-package.properties"});

		ds.scan();

		String dirName = dir.getCanonicalPath();

		String[] fileNames = ds.getIncludedFiles();

		for (String fileName : fileNames) {
			setupProject(dirName, fileName);
		}
	}

	protected void addClasspathEntry(StringBundler sb, String jar) {
		addClasspathEntry(sb, jar, null);
	}

	protected void addClasspathEntry(
		StringBundler sb, String jar, Map<String, String> attributes) {

		sb.append("\t<classpathentry kind=\"lib\" path=\"");
		sb.append(jar);

		if ((attributes == null) || attributes.isEmpty()) {
			sb.append("\" />\n");

			return;
		}

		sb.append("\">\n\t\t<attributes>\n");

		Iterator<Map.Entry<String, String>> itr =
			attributes.entrySet().iterator();

		while (itr.hasNext()) {
			Map.Entry<String, String> entry = itr.next();

			sb.append("\t\t\t<attribute name=\"");
			sb.append(entry.getKey());
			sb.append("\" value=\"");
			sb.append(entry.getValue());
			sb.append("\" />\n");
		}

		sb.append("\t\t</attributes>\n\t</classpathentry>\n");
	}

	protected void setupProject(String dirName, String fileName)
		throws Exception {

		File propertiesFile = new File(dirName + "/" + fileName);

		File libDir = new File(propertiesFile.getParent() + "/lib");

		String libDirPath = StringUtil.replace(
			libDir.getPath(), StringPool.BACK_SLASH, StringPool.SLASH);

		if (libDirPath.contains("/themes/")) {
			return;
		}

		File projectDir = new File(propertiesFile.getParent() + "/../..");

		Properties properties = new Properties();

		properties.load(new FileInputStream(propertiesFile));

		String[] dependencyJars = StringUtil.split(
			properties.getProperty(
				"portal-dependency-jars",
				properties.getProperty("portal.dependency.jars")));

		List<String> jars = ListUtil.toList(dependencyJars);

		jars.add("commons-logging.jar");
		jars.add("log4j.jar");
		jars.add("util-bridges.jar");
		jars.add("util-java.jar");
		jars.add("util-taglib.jar");

		Collections.sort(jars);

		writeEclipseFiles(libDir, projectDir, dependencyJars);

		List<String> ignores = ListUtil.fromFile(
			libDir.getCanonicalPath() + "/../.gitignore");

		if (!libDirPath.contains("/ext/") && !ignores.contains("/lib")) {
			File gitignoreFile = new File(
				libDir.getCanonicalPath() + "/.gitignore");

			System.out.println("Updating " + gitignoreFile);

			String[] gitIgnores = jars.toArray(new String[jars.size()]);

			for (int i = 0; i < gitIgnores.length; i++) {
				String gitIgnore = gitIgnores[i];

				if (Validator.isNotNull(gitIgnore) &&
					!gitIgnore.startsWith("/")) {

					gitIgnores[i] = "/" + gitIgnore;
				}
			}

			_fileUtil.write(gitignoreFile, StringUtil.merge(gitIgnores, "\n"));
		}
	}

	protected void writeClasspathFile(
			File libDir, String[] dependencyJars, String projectDirName,
			String projectName, boolean javaProject)
		throws Exception {

		File classpathFile = new File(projectDirName + "/.classpath");

		if (!javaProject) {
			classpathFile.delete();

			return;
		}

		List<String> globalJars = new UniqueList<String>();
		List<String> portalJars = new UniqueList<String>();

		List<String> extGlobalJars = new UniqueList<String>();
		List<String> extPortalJars = new UniqueList<String>();

		String libDirPath = StringUtil.replace(
			libDir.getPath(), StringPool.BACK_SLASH, StringPool.SLASH);

		if (libDirPath.contains("/ext/")) {
			FilenameFilter filenameFilter = new GlobFilenameFilter("*.jar");

			for (String dirName : new String[] {"global", "portal"}) {
				File file = new File(libDirPath + "/../ext-lib/" + dirName);

				List<String> jars = ListUtil.toList(file.list(filenameFilter));

				if (dirName.equals("global")) {
					extGlobalJars.addAll(ListUtil.sort(jars));

					File dir = new File(PropsValues.LIFERAY_LIB_GLOBAL_DIR);

					String[] fileNames = dir.list(filenameFilter);

					globalJars.addAll(
						ListUtil.sort(ListUtil.toList(fileNames)));
					globalJars.removeAll(extGlobalJars);
				}
				else if (dirName.equals("portal")) {
					extPortalJars.addAll(ListUtil.sort(jars));

					File dir = new File(PropsValues.LIFERAY_LIB_PORTAL_DIR);

					String[] fileNames = dir.list(filenameFilter);

					portalJars.addAll(
						ListUtil.sort(ListUtil.toList(fileNames)));
					portalJars.removeAll(extPortalJars);
				}
			}
		}
		else {
			globalJars.add("portlet.jar");

			portalJars.addAll(ListUtil.toList(dependencyJars));
			portalJars.add("commons-logging.jar");
			portalJars.add("log4j.jar");

			Collections.sort(portalJars);
		}

		String[] customJarsArray = libDir.list(new GlobFilenameFilter("*.jar"));

		List<String> customJars = null;

		if (customJarsArray != null) {
			customJars = ListUtil.toList(customJarsArray);

			for (String jar : portalJars) {
				customJars.remove(jar);
			}

			customJars.remove(projectName + "-service.jar");
			customJars.remove("util-bridges.jar");
			customJars.remove("util-java.jar");
			customJars.remove("util-taglib.jar");

			Collections.sort(customJars);
		}
		else {
			customJars = new ArrayList<String>();
		}

		StringBundler sb = new StringBundler();

		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n\n");
		sb.append("<classpath>\n");

		for (String sourceDirName : _SOURCE_DIR_NAMES) {
			if (_fileUtil.exists(projectDirName + "/" + sourceDirName)) {
				sb.append("\t<classpathentry excluding=\"**/.svn/**|.svn/\" ");
				sb.append("kind=\"src\" path=\"" + sourceDirName + "\" />\n");
			}
		}

		sb.append("\t<classpathentry kind=\"src\" path=\"/portal\" />\n");
		sb.append("\t<classpathentry kind=\"con\" ");
		sb.append("path=\"org.eclipse.jdt.launching.JRE_CONTAINER\" />\n");

		if (_fileUtil.exists(projectDirName + "/test")) {
			sb.append("\t<classpathentry excluding=\"**/.svn/**|.svn/\" ");
			sb.append("kind=\"src\" path=\"test\" />\n");

			addClasspathEntry(sb, "/portal/lib/development/junit.jar");
			addClasspathEntry(sb, "/portal/lib/portal/commons-io.jar");
		}

		addClasspathEntry(sb, "/portal/lib/development/activation.jar");
		addClasspathEntry(sb, "/portal/lib/development/annotations.jar");
		addClasspathEntry(sb, "/portal/lib/development/jsp-api.jar");
		addClasspathEntry(sb, "/portal/lib/development/mail.jar");
		addClasspathEntry(sb, "/portal/lib/development/servlet-api.jar");

		Map<String, String> attributes = new HashMap<String, String>();

		if (libDirPath.contains("/ext/")) {
			attributes.put("optional", "true");
		}

		for (String jar : globalJars) {
			addClasspathEntry(sb, "/portal/lib/global/" + jar, attributes);
		}

		for (String jar : portalJars) {
			addClasspathEntry(sb, "/portal/lib/portal/" + jar, attributes);
		}

		addClasspathEntry(sb, "/portal/portal-service/portal-service.jar");
		addClasspathEntry(sb, "/portal/util-bridges/util-bridges.jar");
		addClasspathEntry(sb, "/portal/util-java/util-java.jar");
		addClasspathEntry(sb, "/portal/util-taglib/util-taglib.jar");

		for (String jar : extGlobalJars) {
			addClasspathEntry(sb, "docroot/WEB-INF/ext-lib/global/" + jar);
		}

		for (String jar : extPortalJars) {
			addClasspathEntry(sb, "docroot/WEB-INF/ext-lib/portal/" + jar);
		}

		for (String jar : customJars) {
			if (libDirPath.contains("/tmp/WEB-INF/lib")) {
				addClasspathEntry(sb, "tmp/WEB-INF/lib/" + jar);
			}
			else {
				addClasspathEntry(sb, "docroot/WEB-INF/lib/" + jar);
			}
		}

		sb.append("\t<classpathentry kind=\"output\" path=\"bin\" />\n");
		sb.append("</classpath>");

		System.out.println("Updating " + classpathFile);

		String content = StringUtil.replace(
			sb.toString(), "\"/portal", "\"/portal-" + _BRANCH);

		_fileUtil.write(classpathFile, content);
	}

	protected void writeEclipseFiles(
			File libDir, File projectDir, String[] dependencyJars)
		throws Exception {

		String projectDirName = projectDir.getCanonicalPath();

		String projectName = StringUtil.extractLast(
			projectDirName, File.separatorChar);

		boolean javaProject = false;

		for (String sourceDirName : _SOURCE_DIR_NAMES) {
			if (_fileUtil.exists(projectDirName + "/" + sourceDirName)) {
				javaProject = true;

				break;
			}
		}

		if (!javaProject) {
			System.out.println(
				"Eclipse Java project will not be used because a source " +
					"folder does not exist");
		}

		writeProjectFile(projectDirName, projectName, javaProject);

		writeClasspathFile(
			libDir, dependencyJars, projectDirName, projectName, javaProject);

		for (String sourceDirName : _SOURCE_DIR_NAMES) {
			if (_fileUtil.exists(projectDirName + "/" + sourceDirName)) {
				List<String> gitIgnores = new ArrayList<String>();

				if (sourceDirName.endsWith("ext-impl/src")) {
					gitIgnores.add("/classes");
					gitIgnores.add("/ext-impl.jar");
				}
				else if (sourceDirName.endsWith("ext-service/src")) {
					gitIgnores.add("/classes");
					gitIgnores.add("/ext-service.jar");
				}
				else if (sourceDirName.endsWith("ext-util-bridges/src")) {
					gitIgnores.add("/classes");
					gitIgnores.add("/ext-util-bridges.jar");
				}
				else if (sourceDirName.endsWith("ext-util-java/src")) {
					gitIgnores.add("/classes");
					gitIgnores.add("/ext-util-java.jar");
				}
				else if (sourceDirName.endsWith("ext-util-taglib/src")) {
					gitIgnores.add("/classes");
					gitIgnores.add("/ext-util-taglib.jar");
				}
				else {
					continue;
				}

				String dirName = projectDirName + "/" + sourceDirName + "/../";

				if (gitIgnores.isEmpty()) {
					_fileUtil.delete(dirName + ".gitignore");
				}
				else {
					String gitIgnoresString = StringUtil.merge(
						gitIgnores, "\n");

					_fileUtil.write(dirName + ".gitignore", gitIgnoresString);
				}
			}
		}

		if (_fileUtil.exists(projectDirName + "/test")) {
			_fileUtil.write(
				projectDirName + "/.gitignore", "/test-classes\n/test-results");
		}
		else {
			_fileUtil.delete(projectDirName + "/.gitignore");
		}
	}

	protected void writeProjectFile(
			String projectDirName, String projectName, boolean javaProject)
		throws Exception {

		StringBundler sb = new StringBundler(17);

		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n\n");
		sb.append("<projectDescription>\n");
		sb.append("\t<name>");
		sb.append(projectName);
		sb.append("-");
		sb.append(_BRANCH);
		sb.append("</name>\n");
		sb.append("\t<comment></comment>\n");
		sb.append("\t<projects></projects>\n");
		sb.append("\t<buildSpec>\n");

		if (javaProject) {
			sb.append("\t\t<buildCommand>\n");
			sb.append("\t\t\t<name>org.eclipse.jdt.core.javabuilder</name>\n");
			sb.append("\t\t\t<arguments></arguments>\n");
			sb.append("\t\t</buildCommand>\n");
		}

		sb.append("\t</buildSpec>\n");
		sb.append("\t<natures>\n");

		if (javaProject) {
			sb.append("\t\t<nature>org.eclipse.jdt.core.javanature</nature>\n");
		}

		sb.append("\t</natures>\n");
		sb.append("</projectDescription>");

		File projectFile = new File(projectDirName + "/.project");

		System.out.println("Updating " + projectFile);

		_fileUtil.write(projectFile, sb.toString());
	}

	private static final String _BRANCH = "trunk";

	private static final String[] _SOURCE_DIR_NAMES = new String[] {
		"docroot/WEB-INF/ext-impl/src", "docroot/WEB-INF/ext-service/src",
		"docroot/WEB-INF/ext-util-bridges/src",
		"docroot/WEB-INF/ext-util-java/src",
		"docroot/WEB-INF/ext-util-taglib/src", "docroot/WEB-INF/service",
		"docroot/WEB-INF/src"
	};

	private static FileImpl _fileUtil = FileImpl.getInstance();

}