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

package com.liferay.portal.repository;

import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.TestPropsValues;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntryConstants;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.service.DLAppServiceUtil;

import java.io.InputStream;

import junit.framework.TestCase;

/**
 * @author Mika Koivisto
 */
public class CheckInCheckOutTest extends TestCase {

	@Override
	public void setUp() throws Exception {
		long repositoryId = TestPropsValues.getGroupId();

		_folder = createFolder(repositoryId, "CheckInCheckOutTest");

		_serviceContext = new ServiceContext();

		_serviceContext.setWorkflowAction(WorkflowConstants.ACTION_PUBLISH);

		_fileEntry = createFileEntry(
			repositoryId, _folder.getFolderId(), "test1.txt", _serviceContext);
	}

	@Override
	public void tearDown() throws Exception {
		DLAppServiceUtil.deleteFolder(_folder.getFolderId());
	}

	public void testCancelCheckIn() throws Exception {
		DLAppServiceUtil.checkOutFileEntry(_fileEntry.getFileEntryId());

		FileEntry fileEntry = DLAppServiceUtil.getFileEntry(
			_fileEntry.getFileEntryId());

		FileVersion fileVersion = fileEntry.getLatestFileVersion();

		assertEquals("PWC", fileVersion.getVersion());

		getAssetEntry(fileVersion.getFileVersionId(), true);

		DLAppServiceUtil.cancelCheckOut(_fileEntry.getFileEntryId());

		fileEntry = DLAppServiceUtil.getFileEntry(_fileEntry.getFileEntryId());

		assertEquals("1.0", fileEntry.getVersion());
	}

	public void testCheckIn() throws Exception {
		DLAppServiceUtil.checkOutFileEntry(_fileEntry.getFileEntryId());

		FileVersion fileVersion = _fileEntry.getLatestFileVersion();

		assertEquals("PWC", fileVersion.getVersion());

		getAssetEntry(fileVersion.getFileVersionId(), true);

		DLAppServiceUtil.checkInFileEntry(
			_fileEntry.getFileEntryId(), false, StringPool.BLANK,
			_serviceContext);

		FileEntry fileEntry = DLAppServiceUtil.getFileEntry(
			_fileEntry.getFileEntryId());

		assertEquals("1.1", fileEntry.getVersion());

		getAssetEntry(fileVersion.getFileVersionId(), false);
	}

	public void testCheckOut() throws Exception {
		DLAppServiceUtil.checkOutFileEntry(_fileEntry.getFileEntryId());

		FileVersion fileVersion = _fileEntry.getLatestFileVersion();

		assertEquals("PWC", fileVersion.getVersion());

		getAssetEntry(fileVersion.getFileVersionId(), true);
	}

	public void testUpdateFileEntry() throws Exception {
		FileEntry fileEntry = updateFileEntry(
			_fileEntry.getFileEntryId(), _serviceContext);

		assertEquals("1.1", fileEntry.getVersion());

		getAssetEntry(fileEntry.getFileEntryId(), true);
	}

	public void testUpdateFileEntry2() throws Exception {
		DLAppServiceUtil.checkOutFileEntry(_fileEntry.getFileEntryId());

		FileEntry fileEntry = updateFileEntry(
			_fileEntry.getFileEntryId(), _serviceContext);

		assertEquals("1.0" , fileEntry.getVersion());

		FileVersion fileVersion = fileEntry.getLatestFileVersion();

		assertEquals("PWC", fileVersion.getVersion());

		DLAppServiceUtil.checkInFileEntry(
			_fileEntry.getFileEntryId(), false, StringPool.BLANK,
			_serviceContext);

		fileEntry = DLAppServiceUtil.getFileEntry(_fileEntry.getFileEntryId());

		assertEquals("1.1", fileEntry.getVersion());

		getAssetEntry(fileVersion.getFileVersionId(), false);
	}

	protected FileEntry createFileEntry(
			long repositoryId, long folderId, String fileName,
			ServiceContext serviceContext)
		throws Exception {

		InputStream inputStream = new UnsyncByteArrayInputStream(
			_TEST_CONTENT.getBytes());

		FileEntry fileEntry = DLAppServiceUtil.addFileEntry(
			repositoryId, folderId, fileName,
			ContentTypes.TEXT_PLAIN, fileName, null, null, inputStream,
			_TEST_CONTENT.length(), serviceContext);

		assertNotNull(fileEntry);

		assertEquals("1.0", fileEntry.getVersion());

		AssetEntry assetEntry = getAssetEntry(fileEntry.getFileEntryId(), true);

		assertNotNull(assetEntry);

		return fileEntry;
	}

	protected Folder createFolder(long repositoryId, String folderName)
		throws Exception {

		Folder folder = DLAppServiceUtil.addFolder(
			repositoryId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			folderName, StringPool.BLANK, new ServiceContext());

		assertNotNull(folder);

		folder = DLAppServiceUtil.getFolder(folder.getFolderId());

		assertNotNull(folder);

		return folder;
	}

	protected AssetEntry getAssetEntry(long assetClassPk, boolean expectExists)
		throws Exception {

		AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchEntry(
			DLFileEntryConstants.getClassName(), assetClassPk);

		if (expectExists) {
			assertNotNull(assetEntry);
		}
		else {
			assertNull(assetEntry);
		}

		return assetEntry;
	}

	protected FileEntry updateFileEntry(
			long fileEntryId, ServiceContext serviceContext)
		throws Exception {

		InputStream inputStream = new UnsyncByteArrayInputStream(
			_TEST_CONTENT.getBytes());

		return DLAppServiceUtil.updateFileEntry(
			fileEntryId, "test1.txt", ContentTypes.TEXT_PLAIN, "test1.txt",
			null, null, false, inputStream, _TEST_CONTENT.length(),
			serviceContext);
	}

	private static final String _TEST_CONTENT =
		"LIFERAY\nEnterprise. Open Source. For Life.";

	private FileEntry _fileEntry;
	private Folder _folder;
	private ServiceContext _serviceContext;

}