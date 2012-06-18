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

package com.liferay.portlet.documentlibrary.store;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.io.ByteArrayFileInputStream;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.TermQuery;
import com.liferay.portal.kernel.search.TermQueryFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.GroupLocalService;
import com.liferay.portal.util.PrefsPropsUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.documentlibrary.DirectoryNameException;
import com.liferay.portlet.documentlibrary.FileExtensionException;
import com.liferay.portlet.documentlibrary.FileNameException;
import com.liferay.portlet.documentlibrary.FileSizeException;
import com.liferay.portlet.documentlibrary.SourceFileNameException;
import com.liferay.portlet.documentlibrary.antivirus.AntivirusScannerUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.service.permission.DLFolderPermission;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Brian Wing Shun Chan
 * @author Alexander Chow
 * @author Edward Han
 */
public class DLStoreImpl implements DLStore {

	public void addDirectory(long companyId, long repositoryId, String dirName)
		throws PortalException, SystemException {

		if (!isValidName(dirName) || dirName.equals("/")) {
			throw new DirectoryNameException(dirName);
		}

		store.addDirectory(companyId, repositoryId, dirName);
	}

	public void addFile(
			long companyId, long repositoryId, String fileName,
			boolean validateFileExtension, byte[] bytes)
		throws PortalException, SystemException {

		validate(fileName, validateFileExtension, bytes);

		if (!PropsValues.DL_STORE_ANTIVIRUS_ENABLED) {
			AntivirusScannerUtil.scan(bytes);
		}

		store.addFile(companyId, repositoryId, fileName, bytes);
	}

	public void addFile(
			long companyId, long repositoryId, String fileName,
			boolean validateFileExtension, File file)
		throws PortalException, SystemException {

		validate(fileName, validateFileExtension, file);

		if (PropsValues.DL_STORE_ANTIVIRUS_ENABLED) {
			AntivirusScannerUtil.scan(file);
		}

		store.addFile(companyId, repositoryId, fileName, file);
	}

	public void addFile(
			long companyId, long repositoryId, String fileName,
			boolean validateFileExtension, InputStream is)
		throws PortalException, SystemException {

		if (is instanceof ByteArrayFileInputStream) {
			ByteArrayFileInputStream byteArrayFileInputStream =
				(ByteArrayFileInputStream)is;

			File file = byteArrayFileInputStream.getFile();

			addFile(
				companyId, repositoryId, fileName, validateFileExtension, file);

			return;
		}

		validate(fileName, validateFileExtension, is);

		if (!PropsValues.DL_STORE_ANTIVIRUS_ENABLED ||
			!AntivirusScannerUtil.isActive()) {

			store.addFile(companyId, repositoryId, fileName, is);
		}
		else {
			File tempFile = null;

			try {
				if (is.markSupported()) {
					is.mark(is.available() + 1);

					AntivirusScannerUtil.scan(is);

					is.reset();

					store.addFile(companyId, repositoryId, fileName, is);
				}
				else {
					tempFile = FileUtil.createTempFile();

					FileUtil.write(tempFile, is);

					AntivirusScannerUtil.scan(tempFile);

					store.addFile(companyId, repositoryId, fileName, tempFile);
				}
			}
			catch (IOException ioe) {
				throw new SystemException(
					"Unable to scan file " + fileName, ioe);
			}
			finally {
				if (tempFile != null) {
					tempFile.delete();
				}
			}
		}
	}

	public void addFile(
			long companyId, long repositoryId, String fileName, byte[] bytes)
		throws PortalException, SystemException {

		addFile(companyId, repositoryId, fileName, true, bytes);
	}

	public void addFile(
			long companyId, long repositoryId, String fileName, File file)
		throws PortalException, SystemException {

		addFile(companyId, repositoryId, fileName, true, file);
	}

	public void addFile(
			long companyId, long repositoryId, String fileName, InputStream is)
		throws PortalException, SystemException {

		addFile(companyId, repositoryId, fileName, true, is);
	}

	public void checkRoot(long companyId) throws SystemException {
		store.checkRoot(companyId);
	}

	public void copyFileVersion(
			long companyId, long repositoryId, String fileName,
			String fromVersionLabel, String toVersionLabel)
		throws PortalException, SystemException {

		store.copyFileVersion(
			companyId, repositoryId, fileName, fromVersionLabel,
			toVersionLabel);
	}

	public void deleteDirectory(
			long companyId, long repositoryId, String dirName)
		throws PortalException, SystemException {

		store.deleteDirectory(companyId, repositoryId, dirName);
	}

	public void deleteFile(long companyId, long repositoryId, String fileName)
		throws PortalException, SystemException {

		store.deleteFile(companyId, repositoryId, fileName);
	}

	public void deleteFile(
			long companyId, long repositoryId, String fileName,
			String versionLabel)
		throws PortalException, SystemException {

		store.deleteFile(companyId, repositoryId, fileName, versionLabel);
	}

	public File getFile(long companyId, long repositoryId, String fileName)
		throws PortalException, SystemException {

		return store.getFile(companyId, repositoryId, fileName);
	}

	public File getFile(
			long companyId, long repositoryId, String fileName,
			String versionLabel)
		throws PortalException, SystemException {

		return store.getFile(companyId, repositoryId, fileName, versionLabel);
	}

	public byte[] getFileAsBytes(
			long companyId, long repositoryId, String fileName)
		throws PortalException, SystemException {

		return store.getFileAsBytes(companyId, repositoryId, fileName);
	}

	public byte[] getFileAsBytes(
			long companyId, long repositoryId, String fileName,
			String versionLabel)
		throws PortalException, SystemException {

		return store.getFileAsBytes(
			companyId, repositoryId, fileName, versionLabel);
	}

	public InputStream getFileAsStream(
			long companyId, long repositoryId, String fileName)
		throws PortalException, SystemException {

		return store.getFileAsStream(companyId, repositoryId, fileName);
	}

	public InputStream getFileAsStream(
			long companyId, long repositoryId, String fileName,
			String versionLabel)
		throws PortalException, SystemException {

		return store.getFileAsStream(
			companyId, repositoryId, fileName, versionLabel);
	}

	public String[] getFileNames(
			long companyId, long repositoryId, String dirName)
		throws PortalException, SystemException {

		return store.getFileNames(companyId, repositoryId, dirName);
	}

	public long getFileSize(long companyId, long repositoryId, String fileName)
		throws PortalException, SystemException {

		return store.getFileSize(companyId, repositoryId, fileName);
	}

	public boolean hasDirectory(
			long companyId, long repositoryId, String dirName)
		throws PortalException, SystemException {

		return store.hasDirectory(companyId, repositoryId, dirName);
	}

	public boolean hasFile(long companyId, long repositoryId, String fileName)
		throws PortalException, SystemException {

		return store.hasFile(companyId, repositoryId, fileName);
	}

	public boolean hasFile(
			long companyId, long repositoryId, String fileName,
			String versionLabel)
		throws PortalException, SystemException {

		return store.hasFile(companyId, repositoryId, fileName, versionLabel);
	}

	public void move(String srcDir, String destDir) throws SystemException {
		store.move(srcDir, destDir);
	}

	public Hits search(
			long companyId, long userId, String portletId, long groupId,
			long[] repositoryIds, String keywords, int start, int end)
		throws SystemException {

		try {
			SearchContext searchContext = new SearchContext();

			searchContext.setSearchEngineId(SearchEngineUtil.SYSTEM_ENGINE_ID);

			BooleanQuery contextQuery = BooleanQueryFactoryUtil.create(
				searchContext);

			contextQuery.addRequiredTerm(Field.PORTLET_ID, portletId);

			if (groupId > 0) {
				Group group = groupLocalService.getGroup(groupId);

				if (group.isLayout()) {
					contextQuery.addRequiredTerm(Field.SCOPE_GROUP_ID, groupId);

					groupId = group.getParentGroupId();
				}

				contextQuery.addRequiredTerm(Field.GROUP_ID, groupId);
			}

			if ((repositoryIds != null) && (repositoryIds.length > 0)) {
				BooleanQuery repositoryIdsQuery =
					BooleanQueryFactoryUtil.create(searchContext);

				for (long repositoryId : repositoryIds) {
					try {
						if (userId > 0) {
							PermissionChecker permissionChecker =
								PermissionThreadLocal.getPermissionChecker();

							DLFolderPermission.check(
								permissionChecker, groupId, repositoryId,
								ActionKeys.VIEW);
						}

						if (repositoryId ==
								DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

							repositoryId = groupId;
						}

						TermQuery termQuery = TermQueryFactoryUtil.create(
							searchContext, "repositoryId", repositoryId);

						repositoryIdsQuery.add(
							termQuery, BooleanClauseOccur.SHOULD);
					}
					catch (Exception e) {
					}
				}

				contextQuery.add(repositoryIdsQuery, BooleanClauseOccur.MUST);
			}

			BooleanQuery searchQuery = BooleanQueryFactoryUtil.create(
				searchContext);

			searchQuery.addTerms(_KEYWORDS_FIELDS, keywords);

			BooleanQuery fullQuery = BooleanQueryFactoryUtil.create(
				searchContext);

			fullQuery.add(contextQuery, BooleanClauseOccur.MUST);

			if (searchQuery.clauses().size() > 0) {
				fullQuery.add(searchQuery, BooleanClauseOccur.MUST);
			}

			return SearchEngineUtil.search(
				companyId, new long[] {groupId}, userId,
				DLFileEntry.class.getName(), fullQuery, start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	public void updateFile(
			long companyId, long repositoryId, long newRepositoryId,
			String fileName)
		throws PortalException, SystemException {

		store.updateFile(companyId, repositoryId, newRepositoryId, fileName);
	}

	public void updateFile(
			long companyId, long repositoryId, String fileName,
			String newFileName)
		throws PortalException, SystemException {

		store.updateFile(companyId, repositoryId, fileName, newFileName);
	}

	public void updateFile(
			long companyId, long repositoryId, String fileName,
			String fileExtension, boolean validateFileExtension,
			String versionLabel, String sourceFileName, File file)
		throws PortalException, SystemException {

		validate(
			fileName, fileExtension, sourceFileName,
			validateFileExtension, file);

		if (!PropsValues.DL_STORE_ANTIVIRUS_ENABLED) {
			AntivirusScannerUtil.scan(file);
		}

		store.updateFile(companyId, repositoryId, fileName, versionLabel, file);
	}

	public void updateFile(
			long companyId, long repositoryId, String fileName,
			String fileExtension, boolean validateFileExtension,
			String versionLabel, String sourceFileName, InputStream is)
		throws PortalException, SystemException {

		if (is instanceof ByteArrayFileInputStream) {
			ByteArrayFileInputStream byteArrayFileInputStream =
				(ByteArrayFileInputStream)is;

			File file = byteArrayFileInputStream.getFile();

			updateFile(
				companyId, repositoryId, fileName, fileExtension,
				validateFileExtension, versionLabel, sourceFileName, file);

			return;
		}

		validate(
			fileName, fileExtension, sourceFileName,
			validateFileExtension, is);

		if (!PropsValues.DL_STORE_ANTIVIRUS_ENABLED ||
			!AntivirusScannerUtil.isActive()) {

			store.updateFile(
				companyId, repositoryId, fileName, versionLabel, is);
		}
		else {
			File tempFile = null;

			try {
				if (is.markSupported()) {
					is.mark(is.available() + 1);

					AntivirusScannerUtil.scan(is);

					is.reset();

					store.updateFile(
						companyId, repositoryId, fileName, versionLabel, is);
				}
				else {
					tempFile = FileUtil.createTempFile();

					FileUtil.write(tempFile, is);

					AntivirusScannerUtil.scan(tempFile);

					store.updateFile(
						companyId, repositoryId, fileName, versionLabel,
						tempFile);
				}
			}
			catch (IOException ioe) {
				throw new SystemException(
					"Unable to scan file " + fileName, ioe);
			}
			finally {
				if (tempFile != null) {
					tempFile.delete();
				}
			}
		}
	}

	public void updateFileVersion(
			long companyId, long repositoryId, String fileName,
			String fromVersionLabel, String toVersionLabel)
		throws PortalException, SystemException {

		store.updateFileVersion(
			companyId, repositoryId, fileName, fromVersionLabel,
			toVersionLabel);
	}

	public void validate(String fileName, boolean validateFileExtension)
		throws PortalException, SystemException {

		if (!isValidName(fileName)) {
			throw new FileNameException(fileName);
		}

		if (validateFileExtension) {
			boolean validFileExtension = false;

			String[] fileExtensions = PrefsPropsUtil.getStringArray(
				PropsKeys.DL_FILE_EXTENSIONS, StringPool.COMMA);

			for (int i = 0; i < fileExtensions.length; i++) {
				if (StringPool.STAR.equals(fileExtensions[i]) ||
					StringUtil.endsWith(fileName, fileExtensions[i])) {

					validFileExtension = true;

					break;
				}
			}

			if (!validFileExtension) {
				throw new FileExtensionException(fileName);
			}
		}
	}

	public void validate(
			String fileName, boolean validateFileExtension, byte[] bytes)
		throws PortalException, SystemException {

		validate(fileName, validateFileExtension);

		if ((PrefsPropsUtil.getLong(PropsKeys.DL_FILE_MAX_SIZE) > 0) &&
			((bytes == null) ||
			 (bytes.length >
				 PrefsPropsUtil.getLong(PropsKeys.DL_FILE_MAX_SIZE)))) {

			throw new FileSizeException(fileName);
		}
	}

	public void validate(
			String fileName, boolean validateFileExtension, File file)
		throws PortalException, SystemException {

		validate(fileName, validateFileExtension);

		if ((PrefsPropsUtil.getLong(PropsKeys.DL_FILE_MAX_SIZE) > 0) &&
			((file == null) ||
			 (file.length() >
				PrefsPropsUtil.getLong(PropsKeys.DL_FILE_MAX_SIZE)))) {

			throw new FileSizeException(fileName);
		}
	}

	public void validate(
			String fileName, boolean validateFileExtension, InputStream is)
		throws PortalException, SystemException {

		validate(fileName, validateFileExtension);

		// LEP-4851

		try {
			if ((is == null) ||
				((PrefsPropsUtil.getLong(PropsKeys.DL_FILE_MAX_SIZE) > 0) &&
				 (is.available() >
					PrefsPropsUtil.getLong(PropsKeys.DL_FILE_MAX_SIZE)))) {

				throw new FileSizeException(fileName);
			}
		}
		catch (IOException ioe) {
			throw new FileSizeException(ioe.getMessage());
		}
	}

	public void validate(
			String fileName, String fileExtension, String sourceFileName,
			boolean validateFileExtension, File file)
		throws PortalException, SystemException {

		validate(
			fileName, fileExtension, sourceFileName, validateFileExtension);

		if ((file != null) &&
			(PrefsPropsUtil.getLong(PropsKeys.DL_FILE_MAX_SIZE) > 0) &&
			(file.length() >
				PrefsPropsUtil.getLong(PropsKeys.DL_FILE_MAX_SIZE))) {

			throw new FileSizeException(fileName);
		}
	}

	public void validate(
			String fileName, String fileExtension, String sourceFileName,
			boolean validateFileExtension, InputStream is)
		throws PortalException, SystemException {

		validate(
			fileName, fileExtension, sourceFileName, validateFileExtension);

		try {
			if ((is != null) &&
				(PrefsPropsUtil.getLong(PropsKeys.DL_FILE_MAX_SIZE) > 0) &&
				(is.available() >
					PrefsPropsUtil.getLong(PropsKeys.DL_FILE_MAX_SIZE))) {

				throw new FileSizeException(fileName);
			}
		}
		catch (IOException ioe) {
			throw new FileSizeException(ioe.getMessage());
		}
	}

	protected boolean isValidName(String name) {
		if ((name == null) ||
			name.contains("\\") ||
			name.contains("\\\\") ||
			name.contains("//") ||
			name.contains(":") ||
			name.contains("*") ||
			name.contains("?") ||
			name.contains("\"") ||
			name.contains("<") ||
			name.contains(">") ||
			name.contains("|") ||
			name.contains("[") ||
			name.contains("]") ||
			name.contains("../") ||
			name.contains("/..")) {

			return false;
		}

		return true;
	}

	protected void validate(
			String fileName, String fileExtension, String sourceFileName,
			boolean validateFileExtension)
		throws PortalException, SystemException {

		String sourceFileExtension = FileUtil.getExtension(sourceFileName);

		if (Validator.isNotNull(sourceFileName) &&
			PropsValues.DL_FILE_EXTENSIONS_STRICT_CHECK &&
			!fileExtension.equals(sourceFileExtension)) {

			throw new SourceFileNameException(sourceFileExtension);
		}

		validate(fileName, validateFileExtension);
	}

	@BeanReference(type = GroupLocalService.class)
	protected GroupLocalService groupLocalService;

	@BeanReference(type = Store.class)
	protected Store store;

	private static final String[] _KEYWORDS_FIELDS = {
		Field.ASSET_TAG_NAMES, Field.CONTENT, Field.PROPERTIES
	};

}