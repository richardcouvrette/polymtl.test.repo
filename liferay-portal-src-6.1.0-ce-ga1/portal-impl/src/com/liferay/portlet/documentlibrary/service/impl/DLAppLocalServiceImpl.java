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

package com.liferay.portlet.documentlibrary.service.impl;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.repository.LocalRepository;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.repository.liferayrepository.model.LiferayFolder;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.documentlibrary.NoSuchFileEntryException;
import com.liferay.portlet.documentlibrary.model.DLFileRank;
import com.liferay.portlet.documentlibrary.model.DLFileShortcut;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.service.base.DLAppLocalServiceBaseImpl;
import com.liferay.portlet.documentlibrary.util.DLProcessorRegistryUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.util.List;

/**
 * The document library local service. All portlets should interact with the
 * document library through this class or through {@link DLAppServiceImpl},
 * rather than through the individual document library service classes.
 *
 * <p>
 * This class provides a unified interface to all Liferay and third party
 * repositories. While the method signatures are universal for all repositories.
 * Additional implementation-specific parameters may be specified in the
 * serviceContext.
 * </p>
 *
 * <p>
 * The <code>repositoryId</code> parameter used by most of the methods is the
 * primary key of the specific repository. If the repository is a default
 * Liferay repository, the <code>repositoryId</code> is the <code>groupId</code>
 * or <code>scopeGroupId</code>. Otherwise, the <code>repositoryId</code> will
 * correspond to values obtained from {@link RepositoryLocalServiceUtil}.
 * </p>
 *
 * @author Alexander Chow
 * @author Mika Koivisto
 * @see    DLAppServiceImpl
 */
public class DLAppLocalServiceImpl extends DLAppLocalServiceBaseImpl {

	/**
	 * Adds a file entry and associated metadata based on a byte array.
	 *
	 * <p>
	 * This method takes two file names, the <code>sourceFileName</code> and the
	 * <code>title</code>. The <code>sourceFileName</code> corresponds to the
	 * name of the actual file being uploaded. The <code>title</code>
	 * corresponds to a name the client wishes to assign this file after it has
	 * been uploaded to the portal. If it is <code>null</code>, the <code>
	 * sourceFileName</code> will be used.
	 * </p>
	 *
	 * @param  userId the primary key of the file entry's creator/owner
	 * @param  repositoryId the primary key of the file entry's repository
	 * @param  folderId the primary key of the file entry's parent folder
	 * @param  sourceFileName the original file's name
	 * @param  mimeType the file's MIME type
	 * @param  title the name to be assigned to the file (optionally <code>null
	 *         </code>)
	 * @param  description the file's description
	 * @param  changeLog the file's version change log
	 * @param  bytes the file's data (optionally <code>null</code>)
	 * @param  serviceContext the service context to be applied. Can set the
	 *         asset category IDs, asset tag names, and expando bridge
	 *         attributes for the file entry. In a Liferay repository, it may
	 *         include:  <ul> <li> fileEntryTypeId - ID for a custom file entry
	 *         type </li> <li> fieldsMap - mapping for fields associated with a
	 *         custom file entry type </li> </ul>
	 * @return the file entry
	 * @throws PortalException if the parent folder could not be found or if the
	 *         file entry's information was invalid
	 * @throws SystemException if a system exception occurred
	 */
	public FileEntry addFileEntry(
			long userId, long repositoryId, long folderId,
			String sourceFileName, String mimeType, String title,
			String description, String changeLog, byte[] bytes,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		File file = null;

		try {
			if ((bytes != null) && (bytes.length > 0)) {
				file = FileUtil.createTempFile(bytes);
			}

			return addFileEntry(
				userId, repositoryId, folderId, sourceFileName, mimeType, title,
				description, changeLog, file, serviceContext);
		}
		catch (IOException ioe) {
			throw new SystemException("Unable to write temporary file", ioe);
		}
		finally {
			FileUtil.delete(file);
		}
	}

	/**
	 * Adds a file entry and associated metadata based on a {@link File} object.
	 *
	 * <p>
	 * This method takes two file names, the <code>sourceFileName</code> and the
	 * <code>title</code>. The <code>sourceFileName</code> corresponds to the
	 * name of the actual file being uploaded. The <code>title</code>
	 * corresponds to a name the client wishes to assign this file after it has
	 * been uploaded to the portal. If it is <code>null</code>, the <code>
	 * sourceFileName</code> will be used.
	 * </p>
	 *
	 * @param  userId the primary key of the file entry's creator/owner
	 * @param  repositoryId the primary key of the repository
	 * @param  folderId the primary key of the file entry's parent folder
	 * @param  sourceFileName the original file's name
	 * @param  mimeType the file's MIME type
	 * @param  title the name to be assigned to the file (optionally <code>null
	 *         </code>)
	 * @param  description the file's description
	 * @param  changeLog the file's version change log
	 * @param  file the file's data (optionally <code>null</code>)
	 * @param  serviceContext the service context to be applied. Can set the
	 *         asset category IDs, asset tag names, and expando bridge
	 *         attributes for the file entry. In a Liferay repository, it may
	 *         include:  <ul> <li> fileEntryTypeId - ID for a custom file entry
	 *         type </li> <li> fieldsMap - mapping for fields associated with a
	 *         custom file entry type </li> </ul>
	 * @return the file entry
	 * @throws PortalException if the parent folder could not be found or if the
	 *         file entry's information was invalid
	 * @throws SystemException if a system exception occurred
	 */
	public FileEntry addFileEntry(
			long userId, long repositoryId, long folderId,
			String sourceFileName, String mimeType, String title,
			String description, String changeLog, File file,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		if (file == null || !file.exists() || (file.length() == 0)) {
			return addFileEntry(
				userId, repositoryId, folderId, sourceFileName, mimeType, title,
				description, changeLog, null, 0, serviceContext);
		}

		LocalRepository localRepository = getLocalRepository(repositoryId);

		FileEntry fileEntry = localRepository.addFileEntry(
			userId, folderId, sourceFileName, mimeType, title, description,
			changeLog, file, serviceContext);

		dlAppHelperLocalService.addFileEntry(
			userId, fileEntry, fileEntry.getFileVersion(), serviceContext);

		return fileEntry;
	}

	/**
	 * Adds a file entry and associated metadata based on an {@link InputStream}
	 * object.
	 *
	 * <p>
	 * This method takes two file names, the <code>sourceFileName</code> and the
	 * <code>title</code>. The <code>sourceFileName</code> corresponds to the
	 * name of the actual file being uploaded. The <code>title</code>
	 * corresponds to a name the client wishes to assign this file after it has
	 * been uploaded to the portal. If it is <code>null</code>, the <code>
	 * sourceFileName</code> will be used.
	 * </p>
	 *
	 * @param  userId the primary key of the file entry's creator/owner
	 * @param  repositoryId the primary key of the repository
	 * @param  folderId the primary key of the file entry's parent folder
	 * @param  sourceFileName the original file's name
	 * @param  mimeType the file's MIME type
	 * @param  title the name to be assigned to the file (optionally <code>null
	 *         </code>)
	 * @param  description the file's description
	 * @param  changeLog the file's version change log
	 * @param  is the file's data (optionally <code>null</code>)
	 * @param  size the file's size (optionally <code>0</code>)
	 * @param  serviceContext the service context to be applied. Can set the
	 *         asset category IDs, asset tag names, and expando bridge
	 *         attributes for the file entry. In a Liferay repository, it may
	 *         include:  <ul> <li> fileEntryTypeId - ID for a custom file entry
	 *         type </li> <li> fieldsMap - mapping for fields associated with a
	 *         custom file entry type </li> </ul>
	 * @return the file entry
	 * @throws PortalException if the parent folder could not be found or if the
	 *         file entry's information was invalid
	 * @throws SystemException if a system exception occurred
	 */
	public FileEntry addFileEntry(
			long userId, long repositoryId, long folderId,
			String sourceFileName, String mimeType, String title,
			String description, String changeLog, InputStream is, long size,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		if (is == null) {
			is = new UnsyncByteArrayInputStream(new byte[0]);
			size = 0;
		}

		LocalRepository localRepository = getLocalRepository(repositoryId);

		FileEntry fileEntry = localRepository.addFileEntry(
			userId, folderId, sourceFileName, mimeType, title, description,
			changeLog, is, size, serviceContext);

		dlAppHelperLocalService.addFileEntry(
			userId, fileEntry, fileEntry.getFileVersion(), serviceContext);

		return fileEntry;
	}

	/**
	 * Adds the file rank to the existing file entry. This method is only
	 * supported by the Liferay repository.
	 *
	 * @param  repositoryId the primary key of the repository
	 * @param  companyId the primary key of the company
	 * @param  userId the primary key of the file rank's creator/owner
	 * @param  fileEntryId the primary key of the file entry
	 * @param  serviceContext the service context to be applied
	 * @return the file rank
	 * @throws SystemException if a system exception occurred
	 */
	public DLFileRank addFileRank(
			long repositoryId, long companyId, long userId, long fileEntryId,
			ServiceContext serviceContext)
		throws SystemException {

		return dlFileRankLocalService.addFileRank(
			repositoryId, companyId, userId, fileEntryId, serviceContext);
	}

	/**
	 * Adds the file shortcut to the existing file entry. This method is only
	 * supported by the Liferay repository.
	 *
	 * @param  userId the primary key of the file shortcut's creator/owner
	 * @param  repositoryId the primary key of the repository
	 * @param  folderId the primary key of the file shortcut's parent folder
	 * @param  toFileEntryId the primary key of the file entry to point to
	 * @param  serviceContext the service context to be applied. Can set the
	 *         asset category IDs, asset tag names, and expando bridge
	 *         attributes for the file entry.
	 * @return the file shortcut
	 * @throws PortalException if the parent folder or file entry could not be
	 *         found, or if the file shortcut's information was invalid
	 * @throws SystemException if a system exception occurred
	 */
	public DLFileShortcut addFileShortcut(
			long userId, long repositoryId, long folderId, long toFileEntryId,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		return dlFileShortcutLocalService.addFileShortcut(
			userId, repositoryId, folderId, toFileEntryId, serviceContext);
	}

	/**
	 * Adds a folder.
	 *
	 * @param  userId the primary key of the folder's creator/owner
	 * @param  repositoryId the primary key of the repository
	 * @param  parentFolderId the primary key of the folder's parent folder
	 * @param  name the folder's name
	 * @param  description the folder's description
	 * @param  serviceContext the service context to be applied. In a Liferay
	 *         repository, it may include mountPoint which is a boolean
	 *         specifying whether the folder is a facade for mounting a
	 *         third-party repository
	 * @return the folder
	 * @throws PortalException if the parent folder could not be found or if the
	 *         new folder's information was invalid
	 * @throws SystemException if a system exception occurred
	 */
	public Folder addFolder(
			long userId, long repositoryId, long parentFolderId, String name,
			String description, ServiceContext serviceContext)
		throws PortalException, SystemException {

		LocalRepository localRepository = getLocalRepository(repositoryId);

		return localRepository.addFolder(
			userId, parentFolderId, name, description, serviceContext);
	}

	/**
	 * Delete all data associated to the given repository. This method is only
	 * supported by the Liferay repository.
	 *
	 * @param  repositoryId the primary key of the data's repository
	 * @throws PortalException if the repository could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteAll(long repositoryId)
		throws PortalException, SystemException {

		LocalRepository localRepository = getLocalRepository(repositoryId);

		localRepository.deleteAll();
	}

	/**
	 * Deletes the file entry.
	 *
	 * @param  fileEntryId the primary key of the file entry
	 * @throws PortalException if the file entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteFileEntry(long fileEntryId)
		throws PortalException, SystemException {

		LocalRepository localRepository = getLocalRepository(0, fileEntryId, 0);

		FileEntry fileEntry = localRepository.getFileEntry(fileEntryId);

		localRepository.deleteFileEntry(fileEntryId);

		dlAppHelperLocalService.deleteFileEntry(fileEntry);
	}

	/**
	 * Deletes the file ranks associated to a given file entry. This method is
	 * only supported by the Liferay repository.
	 *
	 * @param  fileEntryId the primary key of the file entry
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteFileRanksByFileEntryId(long fileEntryId)
		throws SystemException {

		dlFileRankLocalService.deleteFileRanksByFileEntryId(fileEntryId);
	}

	/**
	 * Deletes the file ranks associated to a given user. This method is only
	 * supported by the Liferay repository.
	 *
	 * @param  userId the primary key of the user
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteFileRanksByUserId(long userId) throws SystemException {
		dlFileRankLocalService.deleteFileRanksByUserId(userId);
	}

	/**
	 * Deletes the file shortcut. This method is only supported by the Liferay
	 * repository.
	 *
	 * @param  dlFileShortcut the file shortcut
	 * @throws PortalException if the file shortcut could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteFileShortcut(DLFileShortcut dlFileShortcut)
		throws PortalException, SystemException {

		dlFileShortcutLocalService.deleteFileShortcut(dlFileShortcut);
	}

	/**
	 * Deletes the file shortcut. This method is only supported by the Liferay
	 * repository.
	 *
	 * @param  fileShortcutId the primary key of the file shortcut
	 * @throws PortalException if the file shortcut could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteFileShortcut(long fileShortcutId)
		throws PortalException, SystemException {

		dlFileShortcutLocalService.deleteDLFileShortcut(fileShortcutId);
	}

	/**
	 * Deletes all file shortcuts associated to the file entry. This method is
	 * only supported by the Liferay repository.
	 *
	 * @param  toFileEntryId the primary key of the associated file entry
	 * @throws PortalException if the file shortcut for the file entry could not
	 *         be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteFileShortcuts(long toFileEntryId)
		throws PortalException, SystemException {

		dlFileShortcutLocalService.deleteFileShortcuts(toFileEntryId);
	}

	/**
	 * Deletes the folder and all of its subfolders and file entries.
	 *
	 * @param  folderId the primary key of the folder
	 * @throws PortalException if the folder could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteFolder(long folderId)
		throws PortalException, SystemException {

		LocalRepository localRepository = getLocalRepository(folderId, 0, 0);

		localRepository.deleteFolder(folderId);
	}

	/**
	 * Returns the file entries in the folder.
	 *
	 * @param  repositoryId the primary key of the file entry's repository
	 * @param  folderId the primary key of the file entry's folder
	 * @return the file entries in the folder
	 * @throws PortalException if the folder could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public List<FileEntry> getFileEntries(long repositoryId, long folderId)
		throws PortalException, SystemException {

		return getFileEntries(
			repositoryId, folderId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the file entries in the folder.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end -
	 * start</code> instances. <code>start</code> and <code>end</code> are not
	 * primary keys, they are indexes in the result set. Thus, <code>0</code>
	 * refers to the first result in the set. Setting both <code>start</code>
	 * and <code>end</code> to {@link
	 * com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	 * result set.
	 * </p>
	 *
	 * @param  repositoryId the primary key of the file entry's repository
	 * @param  folderId the primary key of the file entry's folder
	 * @param  start the lower bound of the range of results
	 * @param  end the upper bound of the range of results (not inclusive)
	 * @return the range of file entries in the folder
	 * @throws PortalException if the folder could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public List<FileEntry> getFileEntries(
			long repositoryId, long folderId, int start, int end)
		throws PortalException, SystemException {

		return getFileEntries(repositoryId, folderId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the file entries in the folder.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end -
	 * start</code> instances. <code>start</code> and <code>end</code> are not
	 * primary keys, they are indexes in the result set. Thus, <code>0</code>
	 * refers to the first result in the set. Setting both <code>start</code>
	 * and <code>end</code> to {@link
	 * com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	 * result set.
	 * </p>
	 *
	 * @param  repositoryId the primary key of the file entry's repository
	 * @param  folderId the primary key of the file entry's folder
	 * @param  start the lower bound of the range of results
	 * @param  end the upper bound of the range of results (not inclusive)
	 * @param  obc the comparator to order the file entries (optionally
	 *         <code>null</code>)
	 * @return the range of file entries in the folder ordered by comparator
	 *         <code>obc</code>
	 * @throws PortalException if the folder could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public List<FileEntry> getFileEntries(
			long repositoryId, long folderId, int start, int end,
			OrderByComparator obc)
		throws PortalException, SystemException {

		LocalRepository localRepository = getLocalRepository(repositoryId);

		return localRepository.getFileEntries(folderId, start, end, obc);
	}

	/**
	 * Returns a range of all the file entries and shortcuts in the folder.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end -
	 * start</code> instances. <code>start</code> and <code>end</code> are not
	 * primary keys, they are indexes in the result set. Thus, <code>0</code>
	 * refers to the first result in the set. Setting both <code>start</code>
	 * and <code>end</code> to {@link
	 * com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	 * result set.
	 * </p>
	 *
	 * @param  repositoryId the primary key of the repository
	 * @param  folderId the primary key of the folder
	 * @param  status the workflow status
	 * @param  start the lower bound of the range of results
	 * @param  end the upper bound of the range of results (not inclusive)
	 * @return the range of file entries and shortcuts in the folder
	 * @throws PortalException if the folder could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public List<Object> getFileEntriesAndFileShortcuts(
			long repositoryId, long folderId, int status, int start,
			int end)
		throws PortalException, SystemException {

		LocalRepository localRepository = getLocalRepository(repositoryId);

		return localRepository.getFileEntriesAndFileShortcuts(
			folderId, status, start, end);
	}

	/**
	 * Returns the number of file entries and shortcuts in the folder.
	 *
	 * @param  repositoryId the primary key of the repository
	 * @param  folderId the primary key of the folder
	 * @param  status the workflow status
	 * @return the number of file entries and shortcuts in the folder
	 * @throws PortalException if the folder could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public int getFileEntriesAndFileShortcutsCount(
			long repositoryId, long folderId, int status)
		throws PortalException, SystemException {

		LocalRepository localRepository = getLocalRepository(repositoryId);

		return localRepository.getFileEntriesAndFileShortcutsCount(
			folderId, status);
	}

	/**
	 * Returns the number of file entries in the folder.
	 *
	 * @param  repositoryId the primary key of the file entry's repository
	 * @param  folderId the primary key of the file entry's folder
	 * @return the number of file entries in the folder
	 * @throws PortalException if the folder could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public int getFileEntriesCount(long repositoryId, long folderId)
		throws PortalException, SystemException {

		LocalRepository localRepository = getLocalRepository(repositoryId);

		return localRepository.getFileEntriesCount(folderId);
	}

	/**
	 * Returns the file entry with the primary key.
	 *
	 * @param  fileEntryId the primary key of the file entry
	 * @return the file entry with the primary key
	 * @throws PortalException if the file entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FileEntry getFileEntry(long fileEntryId)
		throws PortalException, SystemException {

		LocalRepository localRepository = getLocalRepository(0, fileEntryId, 0);

		return localRepository.getFileEntry(fileEntryId);
	}

	/**
	 * Returns the file entry with the title in the folder.
	 *
	 * @param  groupId the primary key of the file entry's group
	 * @param  folderId the primary key of the file entry's folder
	 * @param  title the file entry's title
	 * @return the file entry with the title in the folder
	 * @throws PortalException if the file entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FileEntry getFileEntry(long groupId, long folderId, String title)
		throws PortalException, SystemException {

		try {
			LocalRepository localRepository = getLocalRepository(groupId);

			return localRepository.getFileEntry(folderId, title);
		}
		catch (NoSuchFileEntryException nsfee) {
		}

		LocalRepository localRepository = getLocalRepository(folderId, 0, 0);

		return localRepository.getFileEntry(folderId, title);
	}

	/**
	 * Returns the file entry with the UUID and group.
	 *
	 * @param  uuid the file entry's universally unique identifier
	 * @param  groupId the primary key of the file entry's group
	 * @return the file entry with the UUID and group
	 * @throws PortalException if the file entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FileEntry getFileEntryByUuidAndGroupId(String uuid, long groupId)
		throws PortalException, SystemException {

		try {
			LocalRepository localRepository = getLocalRepository(groupId);

			return localRepository.getFileEntryByUuid(uuid);
		}
		catch (NoSuchFileEntryException nsfee) {
			List<com.liferay.portal.model.Repository> repositories =
				repositoryPersistence.findByGroupId(groupId);

			for (int i = 0; i < repositories.size(); i++) {
				try {
					long repositoryId = repositories.get(i).getRepositoryId();

					LocalRepository localRepository = getLocalRepository(
						repositoryId);

					return localRepository.getFileEntryByUuid(uuid);
				}
				catch (NoSuchFileEntryException nsfee2) {
				}
			}
		}

		StringBundler msg = new StringBundler(6);

		msg.append("No DLFileEntry exists with the key {");
		msg.append("uuid=");
		msg.append(uuid);
		msg.append(", groupId=");
		msg.append(groupId);
		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFileEntryException(msg.toString());
	}

	/**
	 * Returns the file ranks from the user. This method is only supported by
	 * the Liferay repository.
	 *
	 * @param  repositoryId the primary key of the repository
	 * @param  userId the primary key of the user
	 * @return the file ranks from the user
	 * @throws SystemException if a system exception occurred
	 */
	public List<DLFileRank> getFileRanks(long repositoryId, long userId)
		throws SystemException {

		return dlFileRankLocalService.getFileRanks(repositoryId, userId);
	}

	/**
	 * Returns the file shortcut with the primary key. This method is only
	 * supported by the Liferay repository.
	 *
	 * @param  fileShortcutId the primary key of the file shortcut
	 * @return the file shortcut with the primary key
	 * @throws PortalException if the file shortcut could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DLFileShortcut getFileShortcut(long fileShortcutId)
		throws PortalException, SystemException {

		return dlFileShortcutLocalService.getFileShortcut(fileShortcutId);
	}

	/**
	 * Returns the file version with the primary key.
	 *
	 * @param  fileVersionId the primary key of the file version
	 * @return the file version with the primary key
	 * @throws PortalException if the file version could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FileVersion getFileVersion(long fileVersionId)
		throws PortalException, SystemException {

		LocalRepository localRepository = getLocalRepository(
			0, 0, fileVersionId);

		return localRepository.getFileVersion(fileVersionId);
	}

	/**
	 * Returns the folder with the primary key.
	 *
	 * @param  folderId the primary key of the folder
	 * @return the folder with the primary key
	 * @throws PortalException if the folder could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Folder getFolder(long folderId)
		throws PortalException, SystemException {

		LocalRepository localRepository = getLocalRepository(folderId, 0, 0);

		return localRepository.getFolder(folderId);
	}

	/**
	 * Returns the folder with the name in the parent folder.
	 *
	 * @param  repositoryId the primary key of the folder's repository
	 * @param  parentFolderId the primary key of the folder's parent folder
	 * @param  name the folder's name
	 * @return the folder with the name in the parent folder
	 * @throws PortalException if the folder could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Folder getFolder(long repositoryId, long parentFolderId, String name)
		throws PortalException, SystemException {

		LocalRepository localRepository = getLocalRepository(repositoryId);

		return localRepository.getFolder(parentFolderId, name);
	}

	/**
	 * Returns all immediate subfolders of the parent folder.
	 *
	 * @param  repositoryId the primary key of the folder's repository
	 * @param  parentFolderId the primary key of the folder's parent folder
	 * @return the immediate subfolders of the parent folder
	 * @throws PortalException if the parent folder could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public List<Folder> getFolders(long repositoryId, long parentFolderId)
		throws PortalException, SystemException {

		return getFolders(repositoryId, parentFolderId, true);
	}

	/**
	 * Returns all immediate subfolders of the parent folder, optionally
	 * including mount folders for third-party repositories.
	 *
	 * @param  repositoryId the primary key of the folder's repository
	 * @param  parentFolderId the primary key of the folder's parent folder
	 * @param  includeMountFolders whether to include mount folders for
	 *         third-party repositories
	 * @return the immediate subfolders of the parent folder
	 * @throws PortalException if the parent folder could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public List<Folder> getFolders(
			long repositoryId, long parentFolderId, boolean includeMountFolders)
		throws PortalException, SystemException {

		return getFolders(
			repositoryId, parentFolderId, includeMountFolders,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the immediate subfolders of the parent folder,
	 * optionally including mount folders for third-party repositories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end -
	 * start</code> instances. <code>start</code> and <code>end</code> are not
	 * primary keys, they are indexes in the result set. Thus, <code>0</code>
	 * refers to the first result in the set. Setting both <code>start</code>
	 * and <code>end</code> to {@link
	 * com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	 * result set.
	 * </p>
	 *
	 * @param  repositoryId the primary key of the folder's repository
	 * @param  parentFolderId the primary key of the folder's parent folder
	 * @param  includeMountFolders whether to include mount folders for
	 *         third-party repositories
	 * @param  start the lower bound of the range of results
	 * @param  end the upper bound of the range of results (not inclusive)
	 * @return the range of immediate subfolders of the parent folder
	 * @throws PortalException if the parent folder could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public List<Folder> getFolders(
			long repositoryId, long parentFolderId, boolean includeMountFolders,
			int start, int end)
		throws PortalException, SystemException {

		return getFolders(
			repositoryId, parentFolderId, includeMountFolders,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns an ordered range of all the immediate subfolders of the parent
	 * folder.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end -
	 * start</code> instances. <code>start</code> and <code>end</code> are not
	 * primary keys, they are indexes in the result set. Thus, <code>0</code>
	 * refers to the first result in the set. Setting both <code>start</code>
	 * and <code>end</code> to {@link
	 * com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	 * result set.
	 * </p>
	 *
	 * @param  repositoryId the primary key of the folder's repository
	 * @param  parentFolderId the primary key of the folder's parent folder
	 * @param  includeMountFolders whether to include mount folders for
	 *         third-party repositories
	 * @param  start the lower bound of the range of results
	 * @param  end the upper bound of the range of results (not inclusive)
	 * @param  obc the comparator to order the folders (optionally
	 *         <code>null</code>)
	 * @return the range of immediate subfolders of the parent folder ordered by
	 *         comparator <code>obc</code>
	 * @throws PortalException if the parent folder could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public List<Folder> getFolders(
			long repositoryId, long parentFolderId, boolean includeMountFolders,
			int start, int end, OrderByComparator obc)
		throws PortalException, SystemException {

		LocalRepository localRepository = getLocalRepository(repositoryId);

		return localRepository.getFolders(
			parentFolderId, includeMountFolders, start, end, obc);
	}

	/**
	 * Returns a range of all the immediate subfolders of the parent folder.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end -
	 * start</code> instances. <code>start</code> and <code>end</code> are not
	 * primary keys, they are indexes in the result set. Thus, <code>0</code>
	 * refers to the first result in the set. Setting both <code>start</code>
	 * and <code>end</code> to {@link
	 * com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	 * result set.
	 * </p>
	 *
	 * @param  repositoryId the primary key of the folder's repository
	 * @param  parentFolderId the primary key of the folder's parent folder
	 * @param  start the lower bound of the range of results
	 * @param  end the upper bound of the range of results (not inclusive)
	 * @return the range of immediate subfolders of the parent folder
	 * @throws PortalException if the parent folder could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public List<Folder> getFolders(
			long repositoryId, long parentFolderId, int start, int end)
		throws PortalException, SystemException {

		return getFolders(repositoryId, parentFolderId, true, start, end);
	}

	/**
	 * Returns an ordered range of all the immediate subfolders of the parent
	 * folder.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end -
	 * start</code> instances. <code>start</code> and <code>end</code> are not
	 * primary keys, they are indexes in the result set. Thus, <code>0</code>
	 * refers to the first result in the set. Setting both <code>start</code>
	 * and <code>end</code> to {@link
	 * com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	 * result set.
	 * </p>
	 *
	 * @param  repositoryId the primary key of the folder's repository
	 * @param  parentFolderId the primary key of the folder's parent folder
	 * @param  start the lower bound of the range of results
	 * @param  end the upper bound of the range of results (not inclusive)
	 * @param  obc the comparator to order the folders (optionally
	 *         <code>null</code>)
	 * @return the range of immediate subfolders of the parent folder ordered by
	 *         comparator <code>obc</code>
	 * @throws PortalException if the parent folder could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public List<Folder> getFolders(
			long repositoryId, long parentFolderId, int start, int end,
			OrderByComparator obc)
		throws PortalException, SystemException {

		return getFolders(repositoryId, parentFolderId, true, start, end, obc);
	}

	/**
	 * Returns an ordered range of all the immediate subfolders, file entries,
	 * and file shortcuts in the parent folder.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end -
	 * start</code> instances. <code>start</code> and <code>end</code> are not
	 * primary keys, they are indexes in the result set. Thus, <code>0</code>
	 * refers to the first result in the set. Setting both <code>start</code>
	 * and <code>end</code> to {@link
	 * com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	 * result set.
	 * </p>
	 *
	 * @param  repositoryId the primary key of the repository
	 * @param  folderId the primary key of the parent folder
	 * @param  status the workflow status
	 * @param  includeMountFolders whether to include mount folders for
	 *         third-party repositories
	 * @param  start the lower bound of the range of results
	 * @param  end the upper bound of the range of results (not inclusive)
	 * @param  obc the comparator to order the results (optionally
	 *         <code>null</code>)
	 * @return the range of immediate subfolders, file entries, and file
	 *         shortcuts in the parent folder ordered by comparator
	 *         <code>obc</code>
	 * @throws PortalException if the folder could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public List<Object> getFoldersAndFileEntriesAndFileShortcuts(
			long repositoryId, long folderId, int status,
			boolean includeMountFolders, int start, int end,
			OrderByComparator obc)
		throws PortalException, SystemException {

		return getFoldersAndFileEntriesAndFileShortcuts(
			repositoryId, folderId, status, null, includeMountFolders, start,
			end, obc);
	}

	public List<Object> getFoldersAndFileEntriesAndFileShortcuts(
			long repositoryId, long folderId, int status, String[] mimeTypes,
			boolean includeMountFolders, int start, int end,
			OrderByComparator obc)
		throws PortalException, SystemException {

		LocalRepository localRepository = getLocalRepository(repositoryId);

		return localRepository.getFoldersAndFileEntriesAndFileShortcuts(
			folderId, status, mimeTypes, includeMountFolders, start, end, obc);
	}

	/**
	 * Returns the number of immediate subfolders, file entries, and file
	 * shortcuts in the parent folder.
	 *
	 * @param  repositoryId the primary key of the repository
	 * @param  folderId the primary key of the parent folder
	 * @param  status the workflow status
	 * @param  includeMountFolders whether to include mount folders for
	 *         third-party repositories
	 * @return the number of immediate subfolders, file entries, and file
	 *         shortcuts in the parent folder
	 * @throws PortalException if the folder could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public int getFoldersAndFileEntriesAndFileShortcutsCount(
			long repositoryId, long folderId, int status,
			boolean includeMountFolders)
		throws PortalException, SystemException {

		return getFoldersAndFileEntriesAndFileShortcutsCount(
			repositoryId, folderId, status, null, includeMountFolders);
	}

	public int getFoldersAndFileEntriesAndFileShortcutsCount(
			long repositoryId, long folderId, int status, String[] mimeTypes,
			boolean includeMountFolders)
		throws PortalException, SystemException {

		LocalRepository localRepository = getLocalRepository(repositoryId);

		return localRepository.getFoldersAndFileEntriesAndFileShortcutsCount(
			folderId, status, mimeTypes, includeMountFolders);
	}

	/**
	 * Returns the number of immediate subfolders of the parent folder.
	 *
	 * @param  repositoryId the primary key of the folder's repository
	 * @param  parentFolderId the primary key of the folder's parent folder
	 * @return the number of immediate subfolders of the parent folder
	 * @throws PortalException if the parent folder could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public int getFoldersCount(long repositoryId, long parentFolderId)
		throws PortalException, SystemException {

		return getFoldersCount(repositoryId, parentFolderId, true);
	}

	/**
	 * Returns the number of immediate subfolders of the parent folder,
	 * optionally including mount folders for third-party repositories.
	 *
	 * @param  repositoryId the primary key of the folder's repository
	 * @param  parentFolderId the primary key of the folder's parent folder
	 * @param  includeMountFolders whether to include mount folders for
	 *         third-party repositories
	 * @return the number of immediate subfolders of the parent folder
	 * @throws PortalException if the parent folder could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public int getFoldersCount(
			long repositoryId, long parentFolderId, boolean includeMountFolders)
		throws PortalException, SystemException {

		LocalRepository localRepository = getLocalRepository(repositoryId);

		return localRepository.getFoldersCount(
			parentFolderId, includeMountFolders);
	}

	/**
	 * Returns the number of immediate subfolders and file entries across the
	 * folders.
	 *
	 * @param  repositoryId the primary key of the repository
	 * @param  folderIds the primary keys of folders from which to count
	 *         immediate subfolders and file entries
	 * @param  status the workflow status
	 * @return the number of immediate subfolders and file entries across the
	 *         folders
	 * @throws PortalException if the repository could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public int getFoldersFileEntriesCount(
			long repositoryId, List<Long> folderIds, int status)
		throws PortalException, SystemException {

		LocalRepository localRepository = getLocalRepository(repositoryId);

		return localRepository.getFoldersFileEntriesCount(folderIds, status);
	}

	/**
	 * Returns the mount folder of the repository with the primary key. This
	 * method is only supported by the Liferay repository.
	 *
	 * @param  repositoryId the primary key of the repository
	 * @return the folder used for mounting third-party repositories
	 * @throws PortalException if the repository or mount folder could not be
	 *         found
	 * @throws SystemException if a system exception occurred
	 */
	public Folder getMountFolder(long repositoryId)
		throws PortalException, SystemException {

		DLFolder dlFolder = dlFolderLocalService.getMountFolder(repositoryId);

		return new LiferayFolder(dlFolder);
	}

	/**
	 * Returns all immediate subfolders of the parent folder that are used for
	 * mounting third-party repositories. This method is only supported by the
	 * Liferay repository.
	 *
	 * @param  repositoryId the primary key of the folder's repository
	 * @param  parentFolderId the primary key of the folder's parent folder
	 * @return the immediate subfolders of the parent folder that are used for
	 *         mounting third-party repositories
	 * @throws PortalException if the repository or parent folder could not be
	 *         found
	 * @throws SystemException if a system exception occurred
	 */
	public List<Folder> getMountFolders(long repositoryId, long parentFolderId)
		throws PortalException, SystemException {

		return getMountFolders(
			repositoryId, parentFolderId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the immediate subfolders of the parent folder that
	 * are used for mounting third-party repositories. This method is only
	 * supported by the Liferay repository.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end -
	 * start</code> instances. <code>start</code> and <code>end</code> are not
	 * primary keys, they are indexes in the result set. Thus, <code>0</code>
	 * refers to the first result in the set. Setting both <code>start</code>
	 * and <code>end</code> to {@link
	 * com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	 * result set.
	 * </p>
	 *
	 * @param  repositoryId the primary key of the repository
	 * @param  parentFolderId the primary key of the parent folder
	 * @param  start the lower bound of the range of results
	 * @param  end the upper bound of the range of results (not inclusive)
	 * @return the range of immediate subfolders of the parent folder that are
	 *         used for mounting third-party repositories
	 * @throws PortalException if the repository or parent folder could not be
	 *         found
	 * @throws SystemException if a system exception occurred
	 */
	public List<Folder> getMountFolders(
			long repositoryId, long parentFolderId, int start, int end)
		throws PortalException, SystemException {

		return getMountFolders(repositoryId, parentFolderId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the immediate subfolders of the parent
	 * folder that are used for mounting third-party repositories. This method
	 * is only supported by the Liferay repository.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end -
	 * start</code> instances. <code>start</code> and <code>end</code> are not
	 * primary keys, they are indexes in the result set. Thus, <code>0</code>
	 * refers to the first result in the set. Setting both <code>start</code>
	 * and <code>end</code> to {@link
	 * com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	 * result set.
	 * </p>
	 *
	 * @param  repositoryId the primary key of the folder's repository
	 * @param  parentFolderId the primary key of the folder's parent folder
	 * @param  start the lower bound of the range of results
	 * @param  end the upper bound of the range of results (not inclusive)
	 * @param  obc the comparator to order the folders (optionally
	 *         <code>null</code>)
	 * @return the range of immediate subfolders of the parent folder that are
	 *         used for mounting third-party repositories ordered by comparator
	 *         <code>obc</code>
	 * @throws PortalException if the repository or parent folder could not be
	 *         found
	 * @throws SystemException if a system exception occurred
	 */
	public List<Folder> getMountFolders(
			long repositoryId, long parentFolderId, int start, int end,
			OrderByComparator obc)
		throws PortalException, SystemException {

		LocalRepository localRepository = getLocalRepository(repositoryId);

		return localRepository.getMountFolders(parentFolderId, start, end, obc);
	}

	/**
	 * Returns the number of immediate subfolders of the parent folder that are
	 * used for mounting third-party repositories. This method is only supported
	 * by the Liferay repository.
	 *
	 * @param  repositoryId the primary key of the repository
	 * @param  parentFolderId the primary key of the parent folder
	 * @return the number of folders of the parent folder that are used for
	 *         mounting third-party repositories
	 * @throws PortalException if the repository or parent folder could not be
	 *         found
	 * @throws SystemException if a system exception occurred
	 */
	public int getMountFoldersCount(long repositoryId, long parentFolderId)
		throws PortalException, SystemException {

		LocalRepository localRepository = getLocalRepository(repositoryId);

		return localRepository.getMountFoldersCount(parentFolderId);
	}

	/**
	 * Moves the file entry to the new folder.
	 *
	 * @param  userId the primary key of the user
	 * @param  fileEntryId the primary key of the file entry
	 * @param  newFolderId the primary key of the new folder
	 * @param  serviceContext the service context to be applied
	 * @return the file entry
	 * @throws PortalException if the file entry or the new folder could not be
	 *         found
	 * @throws SystemException if a system exception occurred
	 */
	public FileEntry moveFileEntry(
			long userId, long fileEntryId, long newFolderId,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		LocalRepository fromLocalRepository = getLocalRepository(
			0, fileEntryId, 0);
		LocalRepository toLocalRepository = getLocalRepository(
			newFolderId, serviceContext);

		if (fromLocalRepository.getRepositoryId() ==
				toLocalRepository.getRepositoryId()) {

			// Move file entries within repository

			FileEntry fileEntry = fromLocalRepository.moveFileEntry(
				userId, fileEntryId, newFolderId, serviceContext);

			return fileEntry;
		}

		// Move file entries between repositories

		return moveFileEntries(
			userId, fileEntryId, newFolderId, fromLocalRepository,
			toLocalRepository, serviceContext);
	}

	/**
	 * Updates the file entry's asset replacing its asset categories, tags, and
	 * links.
	 *
	 * @param  userId the primary key of the user
	 * @param  fileEntry the file entry to update
	 * @param  fileVersion the file version to update
	 * @param  assetCategoryIds the primary keys of the new asset categories
	 * @param  assetTagNames the new asset tag names
	 * @param  assetLinkEntryIds the primary keys of the new asset link entries
	 * @throws PortalException if the file entry or version could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void updateAsset(
			long userId, FileEntry fileEntry, FileVersion fileVersion,
			long[] assetCategoryIds, String[] assetTagNames,
			long[] assetLinkEntryIds)
		throws PortalException, SystemException {

		LocalRepository localRepository = getLocalRepository(
			0, fileEntry.getFileEntryId(), 0);

		localRepository.updateAsset(
			userId, fileEntry, fileVersion, assetCategoryIds, assetTagNames,
			assetLinkEntryIds);
	}

	/**
	 * Updates a file entry and associated metadata based on a byte array
	 * object. If the file data is <code>null</code>, then only the associated
	 * metadata (i.e., <code>title</code>, <code>description</code>, and
	 * parameters in the <code>serviceContext</code>) will be updated.
	 *
	 * <p>
	 * This method takes two file names, the <code>sourceFileName</code> and the
	 * <code>title</code>. The <code>sourceFileName</code> corresponds to the
	 * name of the actual file being uploaded. The <code>title</code>
	 * corresponds to a name the client wishes to assign this file after it has
	 * been uploaded to the portal.
	 * </p>
	 *
	 * @param  userId the primary key of the user
	 * @param  fileEntryId the primary key of the file entry
	 * @param  sourceFileName the original file's name (optionally
	 *         <code>null</code>)
	 * @param  mimeType the file's MIME type (optionally <code>null</code>)
	 * @param  title the new name to be assigned to the file (optionally <code>
	 *         <code>null</code></code>)
	 * @param  description the file's new description
	 * @param  changeLog the file's version change log (optionally
	 *         <code>null</code>)
	 * @param  majorVersion whether the new file version is a major version
	 * @param  bytes the file's data (optionally <code>null</code>)
	 * @param  serviceContext the service context to be applied. Can set the
	 *         asset category IDs, asset tag names, and expando bridge
	 *         attributes for the file entry. In a Liferay repository, it may
	 *         include:  <ul> <li> fileEntryTypeId - ID for a custom file entry
	 *         type </li> <li> fieldsMap - mapping for fields associated with a
	 *         custom file entry type </li> </ul>
	 * @return the file entry
	 * @throws PortalException if the file entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FileEntry updateFileEntry(
			long userId, long fileEntryId, String sourceFileName,
			String mimeType, String title, String description, String changeLog,
			boolean majorVersion, byte[] bytes, ServiceContext serviceContext)
		throws PortalException, SystemException {

		File file = null;

		try {
			if ((bytes != null) && (bytes.length > 0)) {
				file = FileUtil.createTempFile(bytes);
			}

			return updateFileEntry(
				userId, fileEntryId, sourceFileName, mimeType, title,
				description, changeLog, majorVersion, file, serviceContext);
		}
		catch (IOException ioe) {
			throw new SystemException("Unable to write temporary file", ioe);
		}
		finally {
			FileUtil.delete(file);
		}
	}

	/**
	 * Updates a file entry and associated metadata based on a {@link File}
	 * object. If the file data is <code>null</code>, then only the associated
	 * metadata (i.e., <code>title</code>, <code>description</code>, and
	 * parameters in the <code>serviceContext</code>) will be updated.
	 *
	 * <p>
	 * This method takes two file names, the <code>sourceFileName</code> and the
	 * <code>title</code>. The <code>sourceFileName</code> corresponds to the
	 * name of the actual file being uploaded. The <code>title</code>
	 * corresponds to a name the client wishes to assign this file after it has
	 * been uploaded to the portal.
	 * </p>
	 *
	 * @param  userId the primary key of the user
	 * @param  fileEntryId the primary key of the file entry
	 * @param  sourceFileName the original file's name (optionally
	 *         <code>null</code>)
	 * @param  mimeType the file's MIME type (optionally <code>null</code>)
	 * @param  title the new name to be assigned to the file (optionally <code>
	 *         <code>null</code></code>)
	 * @param  description the file's new description
	 * @param  changeLog the file's version change log (optionally
	 *         <code>null</code>)
	 * @param  majorVersion whether the new file version is a major version
	 * @param  file EntryId the primary key of the file entry
	 * @param  serviceContext the service context to be applied. Can set the
	 *         asset category IDs, asset tag names, and expando bridge
	 *         attributes for the file entry. In a Liferay repository, it may
	 *         include:  <ul> <li> fileEntryTypeId - ID for a custom file entry
	 *         type </li> <li> fieldsMap - mapping for fields associated with a
	 *         custom file entry type </li> </ul>
	 * @return the file entry
	 * @throws PortalException if the file entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FileEntry updateFileEntry(
			long userId, long fileEntryId, String sourceFileName,
			String mimeType, String title, String description, String changeLog,
			boolean majorVersion, File file, ServiceContext serviceContext)
		throws PortalException, SystemException {

		if (file == null || !file.exists() || file.length() == 0) {
			return updateFileEntry(
				userId, fileEntryId, sourceFileName, mimeType, title,
				description, changeLog, majorVersion, null, 0, serviceContext);
		}

		LocalRepository localRepository = getLocalRepository(0, fileEntryId, 0);

		FileEntry fileEntry = localRepository.updateFileEntry(
			userId, fileEntryId, sourceFileName, mimeType, title, description,
			changeLog, majorVersion, file, serviceContext);

		DLProcessorRegistryUtil.cleanUp(fileEntry.getLatestFileVersion());

		dlAppHelperLocalService.updateFileEntry(
			userId, fileEntry, fileEntry.getFileVersion(), serviceContext);

		return fileEntry;
	}

	/**
	 * Updates a file entry and associated metadata based on an {@link
	 * InputStream} object. If the file data is <code>null</code>, then only the
	 * associated metadata (i.e., <code>title</code>, <code>description</code>,
	 * and parameters in the <code>serviceContext</code>) will be updated.
	 *
	 * <p>
	 * This method takes two file names, the <code>sourceFileName</code> and the
	 * <code>title</code>. The <code>sourceFileName</code> corresponds to the
	 * name of the actual file being uploaded. The <code>title</code>
	 * corresponds to a name the client wishes to assign this file after it has
	 * been uploaded to the portal.
	 * </p>
	 *
	 * @param  userId the primary key of the user
	 * @param  fileEntryId the primary key of the file entry
	 * @param  sourceFileName the original file's name (optionally
	 *         <code>null</code>)
	 * @param  mimeType the file's MIME type (optionally <code>null</code>)
	 * @param  title the new name to be assigned to the file (optionally <code>
	 *         <code>null</code></code>)
	 * @param  description the file's new description
	 * @param  changeLog the file's version change log (optionally
	 *         <code>null</code>)
	 * @param  majorVersion whether the new file version is a major version
	 * @param  is the file's data (optionally <code>null</code>)
	 * @param  size the file's size (optionally <code>0</code>)
	 * @param  serviceContext the service context to be applied. Can set the
	 *         asset category IDs, asset tag names, and expando bridge
	 *         attributes for the file entry. In a Liferay repository, it may
	 *         include:  <ul> <li> fileEntryTypeId - ID for a custom file entry
	 *         type </li> <li> fieldsMap - mapping for fields associated with a
	 *         custom file entry type </li> </ul>
	 * @return the file entry
	 * @throws PortalException if the file entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FileEntry updateFileEntry(
			long userId, long fileEntryId, String sourceFileName,
			String mimeType, String title, String description, String changeLog,
			boolean majorVersion, InputStream is, long size,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		LocalRepository localRepository = getLocalRepository(0, fileEntryId, 0);

		FileEntry fileEntry = localRepository.updateFileEntry(
			userId, fileEntryId, sourceFileName, mimeType, title, description,
			changeLog, majorVersion, is, size, serviceContext);

		if (is != null) {
			DLProcessorRegistryUtil.cleanUp(fileEntry.getLatestFileVersion());
		}

		dlAppHelperLocalService.updateFileEntry(
			userId, fileEntry, fileEntry.getFileVersion(), serviceContext);

		return fileEntry;
	}

	/**
	 * Updates a file rank to the existing file entry. This method is only
	 * supported by the Liferay repository.
	 *
	 * @param  repositoryId the primary key of the file rank's repository
	 * @param  companyId the primary key of the file rank's company
	 * @param  userId the primary key of the file rank's creator/owner
	 * @param  fileEntryId the primary key of the file rank's file entry
	 * @param  serviceContext the service context to be applied
	 * @return the file rank
	 * @throws SystemException if a system exception occurred
	 */
	public DLFileRank updateFileRank(
			long repositoryId, long companyId, long userId, long fileEntryId,
			ServiceContext serviceContext)
		throws SystemException {

		return dlFileRankLocalService.updateFileRank(
			repositoryId, companyId, userId, fileEntryId, serviceContext);
	}

	/**
	 * Updates a file shortcut to the existing file entry. This method is only
	 * supported by the Liferay repository.
	 *
	 * @param  userId the primary key of the file shortcut's creator/owner
	 * @param  fileShortcutId the primary key of the file shortcut
	 * @param  folderId the primary key of the file shortcut's parent folder
	 * @param  toFileEntryId the primary key of the file shortcut's file entry
	 * @param  serviceContext the service context to be applied. Can set the
	 *         asset category IDs, asset tag names, and expando bridge
	 *         attributes for the file entry.
	 * @return the file shortcut
	 * @throws PortalException if the file shortcut, folder, or file entry could
	 *         not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DLFileShortcut updateFileShortcut(
			long userId, long fileShortcutId, long folderId, long toFileEntryId,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		return dlFileShortcutLocalService.updateFileShortcut(
			userId, fileShortcutId, folderId, toFileEntryId, serviceContext);
	}

	/**
	 * Updates all file shortcuts to the existing file entry to the new file
	 * entry. This method is only supported by the Liferay repository.
	 *
	 * @param  toRepositoryId the primary key of the repository
	 * @param  oldToFileEntryId the primary key of the old file entry pointed to
	 * @param  newToFileEntryId the primary key of the new file entry to point
	 *         to
	 * @throws SystemException if a system exception occurred
	 */
	public void updateFileShortcuts(
			long toRepositoryId, long oldToFileEntryId, long newToFileEntryId)
		throws SystemException {

		dlFileShortcutLocalService.updateFileShortcuts(
			oldToFileEntryId, newToFileEntryId);
	}

	/**
	 * Updates the folder.
	 *
	 * @param  folderId the primary key of the folder
	 * @param  parentFolderId the primary key of the folder's new parent folder
	 * @param  name the folder's new name
	 * @param  description the folder's new description
	 * @param  serviceContext the service context to be applied. In a Liferay
	 *         repository, it may include:  <ul> <li> defaultFileEntryTypeId -
	 *         the file entry type to default all Liferay file entries to </li>
	 *         <li> fileEntryTypeSearchContainerPrimaryKeys - a comma-delimited
	 *         list of file entry type primary keys allowed in the given folder
	 *         and all descendants </li> <li> mountPoint - boolean specifying
	 *         whether folder is a facade for mounting a third-party repository
	 *         </li> <li> overrideFileEntryTypes - boolean specifying whether to
	 *         override ancestral folder's restriction of file entry types
	 *         allowed </li> <li> workflowDefinitionXYZ - the workflow
	 *         definition name specified per file entry type. The parameter name
	 *         must be the string <code>workflowDefinition</code> appended by
	 *         the <code>fileEntryTypeId</code> (optionally <code>0</code>).
	 *         </li> </ul>
	 * @return the folder
	 * @throws PortalException if the current or new parent folder could not be
	 *         found, or if the new parent folder's information was invalid
	 * @throws SystemException if a system exception occurred
	 */
	public Folder updateFolder(
			long folderId, long parentFolderId, String name, String description,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		LocalRepository localRepository = getLocalRepository(folderId, 0, 0);

		return localRepository.updateFolder(
			folderId, parentFolderId, name, description, serviceContext);
	}

	protected FileEntry copyFileEntry(
			long userId, LocalRepository toLocalRepository, FileEntry fileEntry,
			long newFolderId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		List<FileVersion> fileVersions = fileEntry.getFileVersions(
			WorkflowConstants.STATUS_ANY);

		FileVersion latestFileVersion = fileVersions.get(
			fileVersions.size() - 1);

		FileEntry destinationFileEntry = toLocalRepository.addFileEntry(
			userId, newFolderId, fileEntry.getTitle(),
			latestFileVersion.getMimeType(), latestFileVersion.getTitle(),
			latestFileVersion.getDescription(), StringPool.BLANK,
			latestFileVersion.getContentStream(false),
			latestFileVersion.getSize(), serviceContext);

		for (int i = fileVersions.size() - 2; i >= 0 ; i--) {
			FileVersion fileVersion = fileVersions.get(i);

			FileVersion previousFileVersion = fileVersions.get(i + 1);

			try {
				destinationFileEntry = toLocalRepository.updateFileEntry(
					userId, destinationFileEntry.getFileEntryId(),
					fileEntry.getTitle(), destinationFileEntry.getMimeType(),
					destinationFileEntry.getTitle(),
					destinationFileEntry.getDescription(), StringPool.BLANK,
					isMajorVersion(fileVersion, previousFileVersion),
					fileVersion.getContentStream(false), fileVersion.getSize(),
					serviceContext);
			}
			catch (PortalException pe) {
				toLocalRepository.deleteFileEntry(
					destinationFileEntry.getFileEntryId());

				throw pe;
			}
		}

		dlAppHelperLocalService.addFileEntry(
			userId, destinationFileEntry, destinationFileEntry.getFileVersion(),
			serviceContext);

		return destinationFileEntry;
	}

	protected void deleteFileEntry(
			long oldFileEntryId, long newFileEntryId,
			LocalRepository fromLocalRepository,
			LocalRepository toLocalRepository)
		throws PortalException, SystemException {

		try {
			FileEntry fileEntry = fromLocalRepository.getFileEntry(
				oldFileEntryId);

			fromLocalRepository.deleteFileEntry(oldFileEntryId);

			dlAppHelperLocalService.deleteFileEntry(fileEntry);
		}
		catch (PortalException pe) {
			FileEntry fileEntry = toLocalRepository.getFileEntry(
				newFileEntryId);

			toLocalRepository.deleteFileEntry(newFileEntryId);

			dlAppHelperLocalService.deleteFileEntry(fileEntry);

			throw pe;
		}
	}

	protected LocalRepository getLocalRepository(long repositoryId)
		throws PortalException, SystemException {

		return repositoryLocalService.getLocalRepositoryImpl(repositoryId);
	}

	protected LocalRepository getLocalRepository(
			long folderId, long fileEntryId, long fileVersionId)
		throws PortalException, SystemException {

		return repositoryLocalService.getLocalRepositoryImpl(
			folderId, fileEntryId, fileVersionId);
	}

	protected LocalRepository getLocalRepository(
			long folderId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		LocalRepository localRepository = null;

		if (folderId == DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			localRepository =
				getLocalRepository(serviceContext.getScopeGroupId());
		}
		else {
			localRepository = getLocalRepository(folderId, 0, 0);
		}

		return localRepository;
	}

	protected boolean isMajorVersion(
		FileVersion previousFileVersion, FileVersion currentFileVersion) {

		long currentVersion = GetterUtil.getLong(
			currentFileVersion.getVersion());
		long previousVersion = GetterUtil.getLong(
			previousFileVersion.getVersion());

		return (currentVersion - previousVersion) >= 1;
	}

	protected FileEntry moveFileEntries(
			long userId, long fileEntryId, long newFolderId,
			LocalRepository fromLocalRepository,
			LocalRepository toLocalRepository, ServiceContext serviceContext)
		throws SystemException, PortalException {

		FileEntry sourceFileEntry = fromLocalRepository.getFileEntry(
			fileEntryId);

		FileEntry destinationFileEntry = copyFileEntry(
			userId, toLocalRepository, sourceFileEntry, newFolderId,
			serviceContext);

		deleteFileEntry(
			fileEntryId, destinationFileEntry.getFileEntryId(),
			fromLocalRepository, toLocalRepository);

		return destinationFileEntry;
	}

}