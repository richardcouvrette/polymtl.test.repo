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

package com.liferay.portlet.documentlibrary.webdav;

import com.liferay.portal.DuplicateLockException;
import com.liferay.portal.InvalidLockException;
import com.liferay.portal.NoSuchLockException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.webdav.BaseResourceImpl;
import com.liferay.portal.kernel.webdav.BaseWebDAVStorageImpl;
import com.liferay.portal.kernel.webdav.Resource;
import com.liferay.portal.kernel.webdav.Status;
import com.liferay.portal.kernel.webdav.WebDAVException;
import com.liferay.portal.kernel.webdav.WebDAVRequest;
import com.liferay.portal.kernel.webdav.WebDAVUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Lock;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.webdav.LockException;
import com.liferay.portlet.asset.service.AssetTagLocalServiceUtil;
import com.liferay.portlet.documentlibrary.DuplicateFileException;
import com.liferay.portlet.documentlibrary.DuplicateFolderNameException;
import com.liferay.portlet.documentlibrary.NoSuchFileEntryException;
import com.liferay.portlet.documentlibrary.NoSuchFolderException;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.service.DLAppServiceUtil;

import java.io.File;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Brian Wing Shun Chan
 * @author Alexander Chow
 */
public class DLWebDAVStorageImpl extends BaseWebDAVStorageImpl {

	@Override
	public int copyCollectionResource(
			WebDAVRequest webDavRequest, Resource resource, String destination,
			boolean overwrite, long depth)
		throws WebDAVException {

		try {
			String[] destinationArray = WebDAVUtil.getPathArray(
				destination, true);

			long companyId = webDavRequest.getCompanyId();

			long parentFolderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;

			try {
				parentFolderId = getParentFolderId(companyId, destinationArray);
			}
			catch (NoSuchFolderException nsfe) {
				return HttpServletResponse.SC_CONFLICT;
			}

			Folder folder = (Folder)resource.getModel();

			long groupId = WebDAVUtil.getGroupId(companyId, destination);
			String name = WebDAVUtil.getResourceName(destinationArray);
			String description = folder.getDescription();

			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setAddGroupPermissions(
				isAddGroupPermissions(groupId));
			serviceContext.setAddGuestPermissions(true);

			int status = HttpServletResponse.SC_CREATED;

			if (overwrite) {
				if (deleteResource(
						groupId, parentFolderId, name,
						webDavRequest.getLockUuid())) {

					status = HttpServletResponse.SC_NO_CONTENT;
				}
			}

			if (depth == 0) {
				DLAppServiceUtil.addFolder(
					groupId, parentFolderId, name, description, serviceContext);
			}
			else {
				DLAppServiceUtil.copyFolder(
					groupId, folder.getFolderId(), parentFolderId, name,
					description, serviceContext);
			}

			return status;
		}
		catch (DuplicateFolderNameException dfne) {
			return HttpServletResponse.SC_PRECONDITION_FAILED;
		}
		catch (PrincipalException pe) {
			return HttpServletResponse.SC_FORBIDDEN;
		}
		catch (Exception e) {
			throw new WebDAVException(e);
		}
	}

	@Override
	public int copySimpleResource(
			WebDAVRequest webDavRequest, Resource resource, String destination,
			boolean overwrite)
		throws WebDAVException {

		File file = null;

		try {
			String[] destinationArray = WebDAVUtil.getPathArray(
				destination, true);

			long companyId = webDavRequest.getCompanyId();

			long parentFolderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;

			try {
				parentFolderId = getParentFolderId(companyId, destinationArray);
			}
			catch (NoSuchFolderException nsfe) {
				return HttpServletResponse.SC_CONFLICT;
			}

			FileEntry fileEntry = (FileEntry)resource.getModel();

			long groupId = WebDAVUtil.getGroupId(companyId, destination);
			String mimeType = fileEntry.getMimeType();
			String title = WebDAVUtil.getResourceName(destinationArray);
			String description = fileEntry.getDescription();
			String changeLog = StringPool.BLANK;

			InputStream is = fileEntry.getContentStream();

			file = FileUtil.createTempFile(is);

			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setAddGroupPermissions(
				isAddGroupPermissions(groupId));
			serviceContext.setAddGuestPermissions(true);

			int status = HttpServletResponse.SC_CREATED;

			if (overwrite) {
				if (deleteResource(
						groupId, parentFolderId, title,
						webDavRequest.getLockUuid())) {

					status = HttpServletResponse.SC_NO_CONTENT;
				}
			}

			DLAppServiceUtil.addFileEntry(
				groupId, parentFolderId, title, mimeType, title, description,
				changeLog, file, serviceContext);

			return status;
		}
		catch (DuplicateFileException dfe) {
			return HttpServletResponse.SC_PRECONDITION_FAILED;
		}
		catch (DuplicateFolderNameException dfne) {
			return HttpServletResponse.SC_PRECONDITION_FAILED;
		}
		catch (LockException le) {
			return WebDAVUtil.SC_LOCKED;
		}
		catch (PrincipalException pe) {
			return HttpServletResponse.SC_FORBIDDEN;
		}
		catch (Exception e) {
			throw new WebDAVException(e);
		}
		finally {
			FileUtil.delete(file);
		}
	}

	@Override
	public int deleteResource(WebDAVRequest webDavRequest)
		throws WebDAVException {

		try {
			Resource resource = getResource(webDavRequest);

			if (resource == null) {
				if (webDavRequest.isAppleDoubleRequest()) {
					return HttpServletResponse.SC_NO_CONTENT;
				}
				else {
					return HttpServletResponse.SC_NOT_FOUND;
				}
			}

			Object model = resource.getModel();

			if (model instanceof Folder) {
				Folder folder = (Folder)model;

				DLAppServiceUtil.deleteFolder(folder.getFolderId());
			}
			else {
				FileEntry fileEntry = (FileEntry)model;

				if (!hasLock(fileEntry, webDavRequest.getLockUuid()) &&
					(fileEntry.getLock() != null)) {

					return WebDAVUtil.SC_LOCKED;
				}

				DLAppServiceUtil.deleteFileEntry(fileEntry.getFileEntryId());
			}

			return HttpServletResponse.SC_NO_CONTENT;
		}
		catch (PrincipalException pe) {
			return HttpServletResponse.SC_FORBIDDEN;
		}
		catch (Exception e) {
			throw new WebDAVException(e);
		}
	}

	public Resource getResource(WebDAVRequest webDavRequest)
		throws WebDAVException {

		try {
			String[] pathArray = webDavRequest.getPathArray();

			long companyId = webDavRequest.getCompanyId();
			long parentFolderId = getParentFolderId(companyId, pathArray);
			String name = WebDAVUtil.getResourceName(pathArray);

			if (Validator.isNull(name)) {
				String path = getRootPath() + webDavRequest.getPath();

				return new BaseResourceImpl(path, StringPool.BLANK, getToken());
			}

			try {
				Folder folder = DLAppServiceUtil.getFolder(
					webDavRequest.getGroupId(), parentFolderId, name);

				if ((folder.getParentFolderId() != parentFolderId) ||
					(webDavRequest.getGroupId() != folder.getRepositoryId())) {

					throw new NoSuchFolderException();
				}

				return toResource(webDavRequest, folder, false);
			}
			catch (NoSuchFolderException nsfe) {
				try {
					String titleWithExtension = name;

					FileEntry fileEntry = DLAppServiceUtil.getFileEntry(
						webDavRequest.getGroupId(), parentFolderId,
						titleWithExtension);

					return toResource(webDavRequest, fileEntry, false);
				}
				catch (NoSuchFileEntryException nsfee) {
					return null;
				}
			}
		}
		catch (Exception e) {
			throw new WebDAVException(e);
		}
	}

	public List<Resource> getResources(WebDAVRequest webDavRequest)
		throws WebDAVException {

		try {
			long folderId = getFolderId(
				webDavRequest.getCompanyId(), webDavRequest.getPathArray());

			List<Resource> folders = getFolders(webDavRequest, folderId);
			List<Resource> fileEntries = getFileEntries(
				webDavRequest, folderId);

			List<Resource> resources = new ArrayList<Resource>(
				folders.size() + fileEntries.size());

			resources.addAll(folders);
			resources.addAll(fileEntries);

			return resources;
		}
		catch (Exception e) {
			throw new WebDAVException(e);
		}
	}

	@Override
	public boolean isSupportsClassTwo() {
		return true;
	}

	@Override
	public Status lockResource(
			WebDAVRequest webDavRequest, String owner, long timeout)
		throws WebDAVException {

		Resource resource = getResource(webDavRequest);

		Lock lock = null;
		int status = HttpServletResponse.SC_OK;

		try {
			if (resource == null) {
				status = HttpServletResponse.SC_CREATED;

				HttpServletRequest request =
					webDavRequest.getHttpServletRequest();

				String[] pathArray = webDavRequest.getPathArray();

				long companyId = webDavRequest.getCompanyId();
				long groupId = webDavRequest.getGroupId();
				long parentFolderId = getParentFolderId(companyId, pathArray);
				String title = WebDAVUtil.getResourceName(pathArray);

				String contentType = GetterUtil.get(
					request.getHeader(HttpHeaders.CONTENT_TYPE),
					ContentTypes.APPLICATION_OCTET_STREAM);

				if (contentType.equals(ContentTypes.APPLICATION_OCTET_STREAM)) {
					contentType = MimeTypesUtil.getContentType(
						request.getInputStream(), title);
				}

				String description = StringPool.BLANK;
				String changeLog = StringPool.BLANK;

				File file = FileUtil.createTempFile(
					FileUtil.getExtension(title));

				file.createNewFile();

				ServiceContext serviceContext = new ServiceContext();

				serviceContext.setAddGroupPermissions(
					isAddGroupPermissions(groupId));
				serviceContext.setAddGuestPermissions(true);

				FileEntry fileEntry = DLAppServiceUtil.addFileEntry(
					groupId, parentFolderId, title, contentType, title,
					description, changeLog, file, serviceContext);

				resource = toResource(webDavRequest, fileEntry, false);
			}

			if (resource instanceof DLFileEntryResourceImpl) {
				FileEntry fileEntry = (FileEntry)resource.getModel();

				lock = DLAppServiceUtil.lockFileEntry(
					fileEntry.getFileEntryId(), owner, timeout);
			}
			else {
				boolean inheritable = false;

				long depth = WebDAVUtil.getDepth(
					webDavRequest.getHttpServletRequest());

				if (depth != 0) {
					inheritable = true;
				}

				Folder folder = (Folder)resource.getModel();

				lock = DLAppServiceUtil.lockFolder(
					folder.getRepositoryId(), folder.getFolderId(), owner,
					inheritable, timeout);
			}
		}
		catch (Exception e) {

			// DuplicateLock is 423 not 501

			if (!(e instanceof DuplicateLockException)) {
				throw new WebDAVException(e);
			}

			status = WebDAVUtil.SC_LOCKED;
		}

		return new Status(lock, status);
	}

	@Override
	public Status makeCollection(WebDAVRequest webDavRequest)
		throws WebDAVException {

		try {
			HttpServletRequest request = webDavRequest.getHttpServletRequest();

			if (request.getContentLength() > 0) {
				return new Status(
					HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
			}

			String[] pathArray = webDavRequest.getPathArray();

			long companyId = webDavRequest.getCompanyId();
			long groupId = webDavRequest.getGroupId();
			long parentFolderId = getParentFolderId(companyId, pathArray);
			String name = WebDAVUtil.getResourceName(pathArray);
			String description = StringPool.BLANK;

			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setAddGroupPermissions(
				isAddGroupPermissions(groupId));
			serviceContext.setAddGuestPermissions(true);

			DLAppServiceUtil.addFolder(
				groupId, parentFolderId, name, description, serviceContext);

			String location = StringUtil.merge(pathArray, StringPool.SLASH);

			return new Status(location, HttpServletResponse.SC_CREATED);
		}
		catch (DuplicateFolderNameException dfne) {
			return new Status(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		}
		catch (DuplicateFileException dfe) {
			return new Status(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		}
		catch (NoSuchFolderException nsfe) {
			return new Status(HttpServletResponse.SC_CONFLICT);
		}
		catch (PrincipalException pe) {
			return new Status(HttpServletResponse.SC_FORBIDDEN);
		}
		catch (Exception e) {
			throw new WebDAVException(e);
		}
	}

	@Override
	public int moveCollectionResource(
			WebDAVRequest webDavRequest, Resource resource, String destination,
			boolean overwrite)
		throws WebDAVException {

		try {
			String[] destinationArray = WebDAVUtil.getPathArray(
				destination, true);

			Folder folder = (Folder)resource.getModel();

			long companyId = webDavRequest.getCompanyId();
			long groupId = WebDAVUtil.getGroupId(companyId, destinationArray);
			long folderId = folder.getFolderId();
			long parentFolderId = getParentFolderId(
				companyId, destinationArray);
			String name = WebDAVUtil.getResourceName(destinationArray);
			String description = folder.getDescription();

			ServiceContext serviceContext = new ServiceContext();

			int status = HttpServletResponse.SC_CREATED;

			if (overwrite) {
				if (deleteResource(
						groupId, parentFolderId, name,
						webDavRequest.getLockUuid())) {

					status = HttpServletResponse.SC_NO_CONTENT;
				}
			}

			if (parentFolderId != folder.getParentFolderId()) {
				DLAppServiceUtil.moveFolder(
					folderId, parentFolderId, serviceContext);
			}

			if (!name.equals(folder.getName())) {
				DLAppServiceUtil.updateFolder(
					folderId, name, description, serviceContext);
			}

			return status;
		}
		catch (PrincipalException pe) {
			return HttpServletResponse.SC_FORBIDDEN;
		}
		catch (DuplicateFolderNameException dfne) {
			return HttpServletResponse.SC_PRECONDITION_FAILED;
		}
		catch (Exception e) {
			throw new WebDAVException(e);
		}
	}

	@Override
	public int moveSimpleResource(
			WebDAVRequest webDavRequest, Resource resource, String destination,
			boolean overwrite)
		throws WebDAVException {

		File file = null;

		try {
			String[] destinationArray = WebDAVUtil.getPathArray(
				destination, true);

			FileEntry fileEntry = (FileEntry)resource.getModel();

			if (!hasLock(fileEntry, webDavRequest.getLockUuid()) &&
				(fileEntry.getLock() != null)) {

				return WebDAVUtil.SC_LOCKED;
			}

			long companyId = webDavRequest.getCompanyId();
			long groupId = WebDAVUtil.getGroupId(companyId, destinationArray);
			long newParentFolderId = getParentFolderId(
				companyId, destinationArray);
			String sourceFileName = WebDAVUtil.getResourceName(
				destinationArray);
			String title = WebDAVUtil.getResourceName(destinationArray);
			String description = fileEntry.getDescription();
			String changeLog = StringPool.BLANK;

			String[] assetTagNames = AssetTagLocalServiceUtil.getTagNames(
				FileEntry.class.getName(), fileEntry.getFileEntryId());

			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setAssetTagNames(assetTagNames);

			int status = HttpServletResponse.SC_CREATED;

			if (overwrite) {
				if (deleteResource(
						groupId, newParentFolderId, title,
						webDavRequest.getLockUuid())) {

					status = HttpServletResponse.SC_NO_CONTENT;
				}
			}

			// LPS-5415

			if (webDavRequest.isMac()) {
				try {
					FileEntry destFileEntry =
						DLAppServiceUtil.getFileEntry(
							groupId, newParentFolderId, title);

					InputStream is = fileEntry.getContentStream();

					file = FileUtil.createTempFile(is);

					DLAppServiceUtil.updateFileEntry(
						destFileEntry.getFileEntryId(),
						destFileEntry.getTitle(), destFileEntry.getMimeType(),
						destFileEntry.getTitle(),
						destFileEntry.getDescription(), changeLog, false, file,
						serviceContext);

					DLAppServiceUtil.deleteFileEntry(
						fileEntry.getFileEntryId());

					return status;
				}
				catch (NoSuchFileEntryException nsfee) {
				}
			}

			DLAppServiceUtil.updateFileEntry(
				fileEntry.getFileEntryId(), sourceFileName,
				fileEntry.getMimeType(), title, description, changeLog, false,
				file, serviceContext);

			if (fileEntry.getFolderId() != newParentFolderId) {
				fileEntry = DLAppServiceUtil.moveFileEntry(
					fileEntry.getFileEntryId(), newParentFolderId,
					serviceContext);
			}

			return status;
		}
		catch (PrincipalException pe) {
			return HttpServletResponse.SC_FORBIDDEN;
		}
		catch (DuplicateFileException dfe) {
			return HttpServletResponse.SC_PRECONDITION_FAILED;
		}
		catch (DuplicateFolderNameException dfne) {
			return HttpServletResponse.SC_PRECONDITION_FAILED;
		}
		catch (LockException le) {
			return WebDAVUtil.SC_LOCKED;
		}
		catch (Exception e) {
			throw new WebDAVException(e);
		}
		finally {
			FileUtil.delete(file);
		}
	}

	@Override
	public int putResource(WebDAVRequest webDavRequest) throws WebDAVException {
		File file = null;

		try {
			HttpServletRequest request = webDavRequest.getHttpServletRequest();

			String[] pathArray = webDavRequest.getPathArray();

			long companyId = webDavRequest.getCompanyId();
			long groupId = webDavRequest.getGroupId();
			long parentFolderId = getParentFolderId(companyId, pathArray);
			String title = WebDAVUtil.getResourceName(pathArray);
			String description = StringPool.BLANK;
			String changeLog = StringPool.BLANK;

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				request);

			serviceContext.setAddGroupPermissions(
				isAddGroupPermissions(groupId));
			serviceContext.setAddGuestPermissions(true);

			String contentType = GetterUtil.get(
				request.getHeader(HttpHeaders.CONTENT_TYPE),
				ContentTypes.APPLICATION_OCTET_STREAM);

			String extension = FileUtil.getExtension(title);

			file = FileUtil.createTempFile(extension);

			FileUtil.write(file, request.getInputStream());

			if (contentType.equals(ContentTypes.APPLICATION_OCTET_STREAM)) {
				contentType = MimeTypesUtil.getContentType(file, title);
			}

			try {
				FileEntry fileEntry = DLAppServiceUtil.getFileEntry(
					groupId, parentFolderId, title);

				if (!hasLock(fileEntry, webDavRequest.getLockUuid()) &&
					(fileEntry.getLock() != null)) {

					return WebDAVUtil.SC_LOCKED;
				}

				long fileEntryId = fileEntry.getFileEntryId();

				description = fileEntry.getDescription();

				String[] assetTagNames = AssetTagLocalServiceUtil.getTagNames(
					FileEntry.class.getName(), fileEntry.getFileEntryId());

				serviceContext.setAssetTagNames(assetTagNames);

				DLAppServiceUtil.updateFileEntry(
					fileEntryId, title, contentType, title, description,
					changeLog, false, file, serviceContext);
			}
			catch (NoSuchFileEntryException nsfee) {
				if (file.length() == 0) {
					serviceContext.setWorkflowAction(
						WorkflowConstants.ACTION_SAVE_DRAFT);
				}

				DLAppServiceUtil.addFileEntry(
					groupId, parentFolderId, title, contentType, title,
					description, changeLog, file, serviceContext);
			}

			if (_log.isInfoEnabled()) {
				_log.info(
					"Added " + StringUtil.merge(pathArray, StringPool.SLASH));
			}

			return HttpServletResponse.SC_CREATED;
		}
		catch (PrincipalException pe) {
			return HttpServletResponse.SC_FORBIDDEN;
		}
		catch (NoSuchFolderException nsfe) {
			return HttpServletResponse.SC_CONFLICT;
		}
		catch (PortalException pe) {
			if (_log.isWarnEnabled()) {
				_log.warn(pe, pe);
			}

			return HttpServletResponse.SC_CONFLICT;
		}
		catch (Exception e) {
			throw new WebDAVException(e);
		}
		finally {
			FileUtil.delete(file);
		}
	}

	@Override
	public Lock refreshResourceLock(
			WebDAVRequest webDavRequest, String uuid, long timeout)
		throws WebDAVException {

		Resource resource = getResource(webDavRequest);

		Lock lock = null;

		try {
			if (resource instanceof DLFileEntryResourceImpl) {
				lock = DLAppServiceUtil.refreshFileEntryLock(uuid, timeout);
			}
			else {
				lock = DLAppServiceUtil.refreshFolderLock(uuid, timeout);
			}
		}
		catch (Exception e) {
			throw new WebDAVException(e);
		}

		return lock;
	}

	@Override
	public boolean unlockResource(WebDAVRequest webDavRequest, String token)
		throws WebDAVException {

		Resource resource = getResource(webDavRequest);

		try {
			if (resource instanceof DLFileEntryResourceImpl) {
				FileEntry fileEntry = (FileEntry)resource.getModel();

				DLAppServiceUtil.unlockFileEntry(
					fileEntry.getFileEntryId(), token);

				if (webDavRequest.isAppleDoubleRequest()) {
					DLAppServiceUtil.deleteFileEntry(
						fileEntry.getFileEntryId());
				}
			}
			else {
				Folder folder = (Folder)resource.getModel();

				DLAppServiceUtil.unlockFolder(
					folder.getRepositoryId(), folder.getParentFolderId(),
					folder.getName(), token);
			}

			return true;
		}
		catch (Exception e) {
			if (e instanceof InvalidLockException) {
				if (_log.isWarnEnabled()) {
					_log.warn(e.getMessage());
				}
			}
			else {
				if (_log.isWarnEnabled()) {
					_log.warn("Unable to unlock file entry", e);
				}
			}
		}

		return false;
	}

	protected boolean deleteResource(
			long groupId, long parentFolderId, String name, String lockUuid)
		throws Exception {

		try {
			Folder folder = DLAppServiceUtil.getFolder(
				groupId, parentFolderId, name);

			DLAppServiceUtil.deleteFolder(folder.getFolderId());

			return true;
		}
		catch (NoSuchFolderException nsfe) {
			try {
				FileEntry fileEntry = DLAppServiceUtil.getFileEntry(
					groupId, parentFolderId, name);

				if (!hasLock(fileEntry, lockUuid) &&
					(fileEntry.getLock() != null)) {

					throw new LockException();
				}

				DLAppServiceUtil.deleteFileEntryByTitle(
					groupId, parentFolderId, name);

				return true;
			}
			catch (NoSuchFileEntryException nsfee) {
			}
		}

		return false;
	}

	protected List<Resource> getFileEntries(
			WebDAVRequest webDavRequest, long parentFolderId)
		throws Exception {

		List<Resource> resources = new ArrayList<Resource>();

		List<FileEntry> fileEntries = DLAppServiceUtil.getFileEntries(
			webDavRequest.getGroupId(), parentFolderId);

		for (FileEntry fileEntry : fileEntries) {
			Resource resource = toResource(webDavRequest, fileEntry, true);

			resources.add(resource);
		}

		return resources;
	}

	protected long getFolderId(long companyId, String[] pathArray)
		throws Exception {

		return getFolderId(companyId, pathArray, false);
	}

	protected long getFolderId(
			long companyId, String[] pathArray, boolean parent)
		throws Exception {

		long folderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;

		if (pathArray.length <= 1) {
			return folderId;
		}
		else {
			long groupId = WebDAVUtil.getGroupId(companyId, pathArray);

			int x = pathArray.length;

			if (parent) {
				x--;
			}

			for (int i = 2; i < x; i++) {
				String name = pathArray[i];

				Folder folder = DLAppServiceUtil.getFolder(
					groupId, folderId, name);

				if (groupId == folder.getRepositoryId()) {
					folderId = folder.getFolderId();
				}
			}
		}

		return folderId;
	}

	protected List<Resource> getFolders(
			WebDAVRequest webDavRequest, long parentFolderId)
		throws Exception {

		List<Resource> resources = new ArrayList<Resource>();

		long groupId = webDavRequest.getGroupId();

		List<Folder> folders = DLAppServiceUtil.getFolders(
			groupId, parentFolderId, false);

		for (Folder folder : folders) {
			Resource resource = toResource(webDavRequest, folder, true);

			resources.add(resource);
		}

		return resources;
	}

	protected long getParentFolderId(long companyId, String[] pathArray)
		throws Exception {

		return getFolderId(companyId, pathArray, true);
	}

	protected boolean hasLock(FileEntry fileEntry, String lockUuid)
		throws Exception {

		if (Validator.isNull(lockUuid)) {

			// Client does not claim to know of a lock

			return fileEntry.hasLock();
		}
		else {

			// Client claims to know of a lock. Verify the lock UUID.

			try {
				return DLAppServiceUtil.verifyFileEntryLock(
					fileEntry.getRepositoryId(), fileEntry.getFileEntryId(),
					lockUuid);
			}
			catch (NoSuchLockException nsle) {
				return false;
			}
		}
	}

	protected Resource toResource(
		WebDAVRequest webDavRequest, FileEntry fileEntry,
		boolean appendPath) {

		String parentPath = getRootPath() + webDavRequest.getPath();
		String name = StringPool.BLANK;

		if (appendPath) {
			name = fileEntry.getTitle();
		}

		return new DLFileEntryResourceImpl(
			webDavRequest, fileEntry, parentPath, name);
	}

	protected Resource toResource(
		WebDAVRequest webDavRequest, Folder folder, boolean appendPath) {

		String parentPath = getRootPath() + webDavRequest.getPath();
		String name = StringPool.BLANK;

		if (appendPath) {
			name = folder.getName();
		}

		Resource resource = new BaseResourceImpl(
			parentPath, name, folder.getName(), folder.getCreateDate(),
			folder.getModifiedDate());

		resource.setModel(folder);
		resource.setClassName(Folder.class.getName());
		resource.setPrimaryKey(folder.getPrimaryKey());

		return resource;
	}

	private static Log _log = LogFactoryUtil.getLog(DLWebDAVStorageImpl.class);

}