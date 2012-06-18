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

package com.liferay.portal.tools.samplesqlbuilder;

import com.liferay.counter.model.Counter;
import com.liferay.counter.model.impl.CounterModelImpl;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.KeyValuePair;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.model.ClassName;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Contact;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutTypePortletConstants;
import com.liferay.portal.model.ModelHintsUtil;
import com.liferay.portal.model.Permission;
import com.liferay.portal.model.Resource;
import com.liferay.portal.model.ResourceCode;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.ResourcePermission;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.model.impl.ClassNameImpl;
import com.liferay.portal.model.impl.CompanyImpl;
import com.liferay.portal.model.impl.ContactImpl;
import com.liferay.portal.model.impl.GroupImpl;
import com.liferay.portal.model.impl.LayoutImpl;
import com.liferay.portal.model.impl.PermissionImpl;
import com.liferay.portal.model.impl.ResourceCodeImpl;
import com.liferay.portal.model.impl.ResourceImpl;
import com.liferay.portal.model.impl.ResourcePermissionImpl;
import com.liferay.portal.model.impl.RoleImpl;
import com.liferay.portal.model.impl.UserImpl;
import com.liferay.portal.security.permission.ResourceActionsUtil;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.impl.AssetEntryImpl;
import com.liferay.portlet.blogs.model.BlogsEntry;
import com.liferay.portlet.blogs.model.BlogsStatsUser;
import com.liferay.portlet.blogs.model.impl.BlogsEntryImpl;
import com.liferay.portlet.blogs.model.impl.BlogsStatsUserImpl;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFileEntryMetadata;
import com.liferay.portlet.documentlibrary.model.DLFileRank;
import com.liferay.portlet.documentlibrary.model.DLFileVersion;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.model.DLSync;
import com.liferay.portlet.documentlibrary.model.DLSyncConstants;
import com.liferay.portlet.documentlibrary.model.impl.DLFileEntryImpl;
import com.liferay.portlet.documentlibrary.model.impl.DLFileEntryMetadataImpl;
import com.liferay.portlet.documentlibrary.model.impl.DLFileRankImpl;
import com.liferay.portlet.documentlibrary.model.impl.DLFileVersionImpl;
import com.liferay.portlet.documentlibrary.model.impl.DLFolderImpl;
import com.liferay.portlet.documentlibrary.model.impl.DLSyncImpl;
import com.liferay.portlet.dynamicdatamapping.model.DDMContent;
import com.liferay.portlet.dynamicdatamapping.model.DDMStorageLink;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructureLink;
import com.liferay.portlet.dynamicdatamapping.model.impl.DDMContentImpl;
import com.liferay.portlet.dynamicdatamapping.model.impl.DDMStorageLinkImpl;
import com.liferay.portlet.dynamicdatamapping.model.impl.DDMStructureImpl;
import com.liferay.portlet.dynamicdatamapping.model.impl.DDMStructureLinkImpl;
import com.liferay.portlet.messageboards.model.MBCategory;
import com.liferay.portlet.messageboards.model.MBCategoryConstants;
import com.liferay.portlet.messageboards.model.MBDiscussion;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.model.MBStatsUser;
import com.liferay.portlet.messageboards.model.MBThread;
import com.liferay.portlet.messageboards.model.impl.MBCategoryImpl;
import com.liferay.portlet.messageboards.model.impl.MBDiscussionImpl;
import com.liferay.portlet.messageboards.model.impl.MBMessageImpl;
import com.liferay.portlet.messageboards.model.impl.MBStatsUserImpl;
import com.liferay.portlet.messageboards.model.impl.MBThreadImpl;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.impl.SocialActivityImpl;
import com.liferay.portlet.wiki.model.WikiNode;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.portlet.wiki.model.impl.WikiNodeImpl;
import com.liferay.portlet.wiki.model.impl.WikiPageImpl;
import com.liferay.util.SimpleCounter;

import java.io.File;

import java.text.Format;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class DataFactory {

	public DataFactory(
		String baseDir, int maxGroupsCount, int maxUserToGroupCount,
		SimpleCounter counter, SimpleCounter dlDateCounter,
		SimpleCounter permissionCounter, SimpleCounter resourceCounter,
		SimpleCounter resourceCodeCounter,
		SimpleCounter resourcePermissionCounter,
		SimpleCounter socialActivityCounter) {

		try {
			_baseDir = baseDir;
			_maxGroupsCount = maxGroupsCount;
			_maxUserToGroupCount = maxUserToGroupCount;

			_counter = counter;
			_dlDateCounter = dlDateCounter;
			_permissionCounter = permissionCounter;
			_resourceCounter = resourceCounter;
			_resourceCodeCounter = resourceCodeCounter;
			_resourcePermissionCounter = resourcePermissionCounter;
			_socialActivityCounter = socialActivityCounter;

			initClassNames();
			initCompany();
			initDefaultUser();
			initGroups();
			initResourceCodes();
			initRoles();
			initUserNames();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AssetEntry addAssetEntry(
		long groupId, long userId, long classNameId, long classPK,
		boolean visible, String mimeType, String title) {

		AssetEntry assetEntry = new AssetEntryImpl();

		assetEntry.setGroupId(groupId);
		assetEntry.setUserId(userId);
		assetEntry.setClassNameId(classNameId);
		assetEntry.setClassPK(classPK);
		assetEntry.setVisible(visible);
		assetEntry.setMimeType(mimeType);
		assetEntry.setTitle(title);

		return assetEntry;
	}

	public BlogsEntry addBlogsEntry(
		long groupId, long userId, String title, String urlTitle,
		String content) {

		BlogsEntry blogsEntry = new BlogsEntryImpl();

		blogsEntry.setEntryId(_counter.get());
		blogsEntry.setGroupId(groupId);
		blogsEntry.setUserId(userId);
		blogsEntry.setTitle(title);
		blogsEntry.setUrlTitle(urlTitle);
		blogsEntry.setContent(content);

		return blogsEntry;
	}

	public BlogsStatsUser addBlogsStatsUser(long groupId, long userId) {
		BlogsStatsUser blogsStatsUser = new BlogsStatsUserImpl();

		blogsStatsUser.setGroupId(groupId);
		blogsStatsUser.setUserId(userId);

		return blogsStatsUser;
	}

	public Contact addContact(String firstName, String lastName) {
		Contact contact = new ContactImpl();

		contact.setContactId(_counter.get());
		contact.setAccountId(_company.getAccountId());
		contact.setFirstName(firstName);
		contact.setLastName(lastName);

		return contact;
	}

	public DDMContent addDDMContent(long groupId, long companyId, long userId) {
		DDMContent ddmContent = new DDMContentImpl();

		ddmContent.setContentId(_counter.get());
		ddmContent.setGroupId(groupId);
		ddmContent.setCompanyId(companyId);
		ddmContent.setUserId(userId);

		return ddmContent;
	}

	public DDMStorageLink addDDMStorageLink(
		long classNameId, long classPK, long structureId) {

		DDMStorageLink ddmStorageLink = new DDMStorageLinkImpl();

		ddmStorageLink.setStorageLinkId(_counter.get());
		ddmStorageLink.setClassNameId(classNameId);
		ddmStorageLink.setClassPK(classPK);
		ddmStorageLink.setStructureId(structureId);

		return ddmStorageLink;
	}

	public DDMStructure addDDMStructure(
		long groupId, long companyId, long userId, long classNameId) {

		DDMStructure ddmStructure = new DDMStructureImpl();

		ddmStructure.setStructureId(_counter.get());
		ddmStructure.setGroupId(groupId);
		ddmStructure.setCompanyId(companyId);
		ddmStructure.setUserId(userId);
		ddmStructure.setCreateDate(newCreateDate());
		ddmStructure.setClassNameId(classNameId);

		return ddmStructure;
	}

	public DDMStructureLink addDDMStructureLink(
		long classPK, long structureId) {

		DDMStructureLink ddmStructureLink = new DDMStructureLinkImpl();

		ddmStructureLink.setStructureLinkId(_counter.get());
		ddmStructureLink.setClassNameId(_dlFileEntryClassName.getClassNameId());
		ddmStructureLink.setClassPK(classPK);
		ddmStructureLink.setStructureId(structureId);

		return ddmStructureLink;
	}

	public DLFileEntry addDlFileEntry(
		long groupId, long companyId, long userId, long folderId,
		String extension, String mimeType, String name, String title,
		String description) {

		DLFileEntry dlFileEntry = new DLFileEntryImpl();

		dlFileEntry.setFileEntryId(_counter.get());
		dlFileEntry.setGroupId(groupId);
		dlFileEntry.setCompanyId(companyId);
		dlFileEntry.setUserId(userId);
		dlFileEntry.setCreateDate(newCreateDate());
		dlFileEntry.setRepositoryId(groupId);
		dlFileEntry.setFolderId(folderId);
		dlFileEntry.setName(name);
		dlFileEntry.setExtension(extension);
		dlFileEntry.setMimeType(mimeType);
		dlFileEntry.setTitle(title);
		dlFileEntry.setDescription(description);
		dlFileEntry.setSmallImageId(_counter.get());
		dlFileEntry.setLargeImageId(_counter.get());

		return dlFileEntry;
	}

	public DLFileEntryMetadata addDLFileEntryMetadata(
		long ddmStorageId, long ddmStructureId, long fileEntryId,
		long fileVersionId) {

		DLFileEntryMetadata dlFileEntryMetadata = new DLFileEntryMetadataImpl();

		dlFileEntryMetadata.setFileEntryMetadataId(_counter.get());
		dlFileEntryMetadata.setDDMStorageId(ddmStorageId);
		dlFileEntryMetadata.setDDMStructureId(ddmStructureId);
		dlFileEntryMetadata.setFileEntryId(fileEntryId);
		dlFileEntryMetadata.setFileVersionId(fileVersionId);

		return dlFileEntryMetadata;
	}

	public DLFileRank addDLFileRank(
		long groupId, long companyId, long userId, long fileEntryId) {

		DLFileRank dlFileRank = new DLFileRankImpl();

		dlFileRank.setFileRankId(_counter.get());
		dlFileRank.setGroupId(groupId);
		dlFileRank.setCompanyId(companyId);
		dlFileRank.setUserId(userId);
		dlFileRank.setFileEntryId(fileEntryId);

		return dlFileRank;
	}

	public DLFileVersion addDLFileVersion(DLFileEntry dlFileEntry) {
		DLFileVersion dlFileVersion = new DLFileVersionImpl();

		dlFileVersion.setFileVersionId(_counter.get());
		dlFileVersion.setGroupId(dlFileEntry.getGroupId());
		dlFileVersion.setCompanyId(dlFileEntry.getCompanyId());
		dlFileVersion.setUserId(dlFileEntry.getUserId());
		dlFileVersion.setRepositoryId(dlFileEntry.getRepositoryId());
		dlFileVersion.setFileEntryId(dlFileEntry.getFileEntryId());
		dlFileVersion.setExtension(dlFileEntry.getExtension());
		dlFileVersion.setMimeType(dlFileEntry.getMimeType());
		dlFileVersion.setTitle(dlFileEntry.getTitle());
		dlFileVersion.setDescription(dlFileEntry.getDescription());
		dlFileVersion.setSize(dlFileEntry.getSize());

		return dlFileVersion;
	}

	public DLFolder addDLFolder(
		long groupId, long companyId, long userId, long parentFolderId,
		String name, String description) {

		DLFolder dlFolder = new DLFolderImpl();

		dlFolder.setFolderId(_counter.get());
		dlFolder.setGroupId(groupId);
		dlFolder.setCompanyId(companyId);
		dlFolder.setUserId(userId);
		dlFolder.setCreateDate(newCreateDate());
		dlFolder.setRepositoryId(groupId);
		dlFolder.setParentFolderId(parentFolderId);
		dlFolder.setName(name);
		dlFolder.setDescription(description);

		return dlFolder;
	}

	public DLSync addDLSync(
		long companyId, long fileId, long repositoryId, long parentFolderId,
		boolean typeFolder) {

		DLSync dlSync = new DLSyncImpl();

		dlSync.setSyncId(_counter.get());
		dlSync.setCompanyId(companyId);
		dlSync.setFileId(fileId);
		dlSync.setRepositoryId(repositoryId);
		dlSync.setParentFolderId(parentFolderId);
		dlSync.setEvent(DLSyncConstants.EVENT_ADD);

		if (typeFolder) {
			dlSync.setType(DLSyncConstants.TYPE_FOLDER);
		}
		else {
			dlSync.setType(DLSyncConstants.TYPE_FILE);
		}

		return dlSync;
	}

	public Group addGroup(
		long groupId, long classNameId, long classPK, String name,
		String friendlyURL, boolean site) {

		Group group = new GroupImpl();

		group.setGroupId(groupId);
		group.setClassNameId(classNameId);
		group.setClassPK(classPK);
		group.setName(name);
		group.setFriendlyURL(friendlyURL);
		group.setSite(site);

		return group;
	}

	public Layout addLayout(
		int layoutId, String name, String friendlyURL, String column1,
		String column2) {

		Layout layout = new LayoutImpl();

		layout.setPlid(_counter.get());
		layout.setPrivateLayout(false);
		layout.setLayoutId(layoutId);
		layout.setName(name);
		layout.setFriendlyURL(friendlyURL);

		UnicodeProperties typeSettingsProperties = new UnicodeProperties(true);

		typeSettingsProperties.setProperty(
			LayoutTypePortletConstants.LAYOUT_TEMPLATE_ID, "2_columns_ii");
		typeSettingsProperties.setProperty("column-1", column1);
		typeSettingsProperties.setProperty("column-2", column2);

		String typeSettings = StringUtil.replace(
			typeSettingsProperties.toString(), "\n", "\\n");

		layout.setTypeSettings(typeSettings);

		return layout;
	}

	public MBCategory addMBCategory(
		long categoryId, long groupId, long companyId, long userId, String name,
		String description, int threadCount, int messageCount) {

		MBCategory mbCategory = new MBCategoryImpl();

		mbCategory.setCategoryId(categoryId);
		mbCategory.setGroupId(groupId);
		mbCategory.setCompanyId(companyId);
		mbCategory.setUserId(userId);
		mbCategory.setName(name);
		mbCategory.setDescription(description);
		mbCategory.setDisplayStyle(MBCategoryConstants.DEFAULT_DISPLAY_STYLE);
		mbCategory.setThreadCount(threadCount);
		mbCategory.setMessageCount(messageCount);

		return mbCategory;
	}

	public MBDiscussion addMBDiscussion(
		long classNameId, long classPK, long threadId) {

		MBDiscussion mbDiscussion = new MBDiscussionImpl();

		mbDiscussion.setDiscussionId(_counter.get());
		mbDiscussion.setClassNameId(classNameId);
		mbDiscussion.setClassPK(classPK);
		mbDiscussion.setThreadId(threadId);

		return mbDiscussion;
	}

	public MBMessage addMBMessage(
		long messageId, long groupId, long userId, long classNameId,
		long classPK, long categoryId, long threadId, long rootMessageId,
		long parentMessageId, String subject, String body) {

		MBMessage mbMessage = new MBMessageImpl();

		mbMessage.setMessageId(messageId);
		mbMessage.setGroupId(groupId);
		mbMessage.setUserId(userId);
		mbMessage.setClassNameId(classNameId);
		mbMessage.setClassPK(classPK);
		mbMessage.setCategoryId(categoryId);
		mbMessage.setThreadId(threadId);
		mbMessage.setRootMessageId(rootMessageId);
		mbMessage.setParentMessageId(parentMessageId);
		mbMessage.setSubject(subject);
		mbMessage.setBody(body);

		return mbMessage;
	}

	public MBStatsUser addMBStatsUser(long groupId, long userId) {
		MBStatsUser mbStatsUser = new MBStatsUserImpl();

		mbStatsUser.setGroupId(groupId);
		mbStatsUser.setUserId(userId);

		return mbStatsUser;
	}

	public MBThread addMBThread(
		long threadId, long groupId, long companyId, long categoryId,
		long rootMessageId, int messageCount, long lastPostByUserId) {

		MBThread mbThread = new MBThreadImpl();

		mbThread.setThreadId(threadId);
		mbThread.setGroupId(groupId);
		mbThread.setCompanyId(companyId);
		mbThread.setCategoryId(categoryId);
		mbThread.setRootMessageId(rootMessageId);
		mbThread.setRootMessageUserId(lastPostByUserId);
		mbThread.setMessageCount(messageCount);
		mbThread.setLastPostByUserId(lastPostByUserId);

		return mbThread;
	}

	public List<Permission> addPermissions(Resource resource) {
		List<Permission> permissions = new ArrayList<Permission>();

		String name = _individualResourceNames.get(resource.getCodeId());

		List<String> actions = ResourceActionsUtil.getModelResourceActions(
			name);

		for (String action : actions) {
			Permission permission = new PermissionImpl();

			permission.setPermissionId(_permissionCounter.get());
			permission.setCompanyId(_company.getCompanyId());
			permission.setActionId(action);
			permission.setResourceId(resource.getResourceId());

			permissions.add(permission);
		}

		return permissions;
	}

	public Resource addResource(String name, String primKey) {
		Long codeId = _individualResourceCodeIds.get(name);

		Resource resource = new ResourceImpl();

		resource.setResourceId(_resourceCounter.get());
		resource.setCodeId(codeId);
		resource.setPrimKey(primKey);

		return resource;
	}

	public List<ResourcePermission> addResourcePermission(
		long companyId, String name, String primKey) {

		List<ResourcePermission> resourcePermissions =
			new ArrayList<ResourcePermission>(2);

		ResourcePermission resourcePermission = new ResourcePermissionImpl();

		resourcePermission.setResourcePermissionId(
			_resourcePermissionCounter.get());
		resourcePermission.setCompanyId(companyId);
		resourcePermission.setName(name);
		resourcePermission.setScope(ResourceConstants.SCOPE_INDIVIDUAL);
		resourcePermission.setPrimKey(primKey);
		resourcePermission.setRoleId(_ownerRole.getRoleId());
		resourcePermission.setOwnerId(_defaultUser.getUserId());
		resourcePermission.setActionIds(1);

		resourcePermissions.add(resourcePermission);

		resourcePermission = new ResourcePermissionImpl();

		resourcePermission.setResourcePermissionId(
			_resourcePermissionCounter.get());
		resourcePermission.setCompanyId(companyId);
		resourcePermission.setName(name);
		resourcePermission.setScope(ResourceConstants.SCOPE_INDIVIDUAL);
		resourcePermission.setPrimKey(primKey);
		resourcePermission.setRoleId(_guestRole.getRoleId());
		resourcePermission.setOwnerId(0);
		resourcePermission.setActionIds(1);

		resourcePermissions.add(resourcePermission);

		return resourcePermissions;
	}

	public List<KeyValuePair> addRolesPermissions(
		Resource resource, List<Permission> permissions, Role memberRole) {

		List<KeyValuePair> rolesPermissions = new ArrayList<KeyValuePair>();

		for (Permission permission : permissions) {
			KeyValuePair kvp = new KeyValuePair();

			kvp.setKey(String.valueOf(_ownerRole.getRoleId()));
			kvp.setValue(String.valueOf(permission.getPermissionId()));

			rolesPermissions.add(kvp);
		}

		String name = _individualResourceNames.get(resource.getCodeId());

		if (memberRole != null) {
			List<String> groupDefaultActions =
				ResourceActionsUtil.getModelResourceGroupDefaultActions(name);

			for (Permission permission : permissions) {
				if (!groupDefaultActions.contains(permission.getActionId())) {
					continue;
				}

				KeyValuePair kvp = new KeyValuePair();

				kvp.setKey(String.valueOf(memberRole.getRoleId()));
				kvp.setValue(String.valueOf(permission.getPermissionId()));

				rolesPermissions.add(kvp);
			}
		}

		List<String> guestDefaultactions =
			ResourceActionsUtil.getModelResourceGuestDefaultActions(name);

		for (Permission permission : permissions) {
			if (!guestDefaultactions.contains(permission.getActionId())) {
				continue;
			}

			KeyValuePair kvp = new KeyValuePair();

			kvp.setKey(String.valueOf(_guestRole.getRoleId()));
			kvp.setValue(String.valueOf(permission.getPermissionId()));

			rolesPermissions.add(kvp);
		}

		return rolesPermissions;
	}

	public SocialActivity addSocialActivity(
		long groupId, long companyId, long userId, long classNameId,
		long classPK) {

		SocialActivity socialActivity = new SocialActivityImpl();

		socialActivity.setActivityId(_socialActivityCounter.get());
		socialActivity.setGroupId(groupId);
		socialActivity.setCompanyId(companyId);
		socialActivity.setUserId(userId);
		socialActivity.setClassNameId(classNameId);
		socialActivity.setClassPK(classPK);

		return socialActivity;
	}

	public User addUser(boolean defaultUser, String screenName) {
		User user = new UserImpl();

		user.setUserId(_counter.get());
		user.setDefaultUser(defaultUser);

		if (Validator.isNull(screenName)) {
			screenName = String.valueOf(user.getUserId());
		}

		user.setScreenName(screenName);

		String emailAddress = screenName + "@liferay.com";

		user.setEmailAddress(emailAddress);

		return user;
	}

	public List<Long> addUserToGroupIds(long groupId) {
		List<Long> groupIds = new ArrayList<Long>(_maxUserToGroupCount + 1);

		groupIds.add(_guestGroup.getGroupId());

		if ((groupId + _maxUserToGroupCount) > _maxGroupsCount) {
			groupId = groupId - _maxUserToGroupCount + 1;
		}

		for (int i = 0; i < _maxUserToGroupCount; i++) {
			groupIds.add(groupId + i);
		}

		return groupIds;
	}

	public WikiNode addWikiNode(
		long groupId, long userId, String name, String description) {

		WikiNode wikiNode = new WikiNodeImpl();

		wikiNode.setNodeId(_counter.get());
		wikiNode.setGroupId(groupId);
		wikiNode.setUserId(userId);
		wikiNode.setName(name);
		wikiNode.setDescription(description);

		return wikiNode;
	}

	public WikiPage addWikiPage(
		long groupId, long userId, long nodeId, String title, double version,
		String content, boolean head) {

		WikiPage wikiPage = new WikiPageImpl();

		wikiPage.setPageId(_counter.get());
		wikiPage.setResourcePrimKey(_counter.get());
		wikiPage.setGroupId(groupId);
		wikiPage.setUserId(userId);
		wikiPage.setNodeId(nodeId);
		wikiPage.setTitle(title);
		wikiPage.setVersion(version);
		wikiPage.setContent(content);
		wikiPage.setHead(head);

		return wikiPage;
	}

	public Role getAdministratorRole() {
		return _administratorRole;
	}

	public ClassName getBlogsEntryClassName() {
		return _blogsEntryClassName;
	}

	public List<ClassName> getClassNames() {
		return _classNames;
	}

	public Company getCompany() {
		return _company;
	}

	public List<CounterModelImpl> getCounters() {
		return _counters;
	}

	public String getDateLong(Date date) {
		return String.valueOf(date.getTime());
	}

	public String getDateString(Date date) {
		return _simpleDateFormat.format(date);
	}

	public ClassName getDDMContentClassName() {
		return _ddmContentClassName;
	}

	public User getDefaultUser() {
		return _defaultUser;
	}

	public ClassName getDLFileEntryClassName() {
		return _dlFileEntryClassName;
	}

	public ClassName getGroupClassName() {
		return _groupClassName;
	}

	public List<Group> getGroups() {
		return _groups;
	}

	public Group getGuestGroup() {
		return _guestGroup;
	}

	public Role getGuestRole() {
		return _guestRole;
	}

	public ClassName getMBMessageClassName() {
		return _mbMessageClassName;
	}

	public Role getOrganizationAdministratorRole() {
		return _organizationAdministratorRole;
	}

	public Role getOrganizationOwnerRole() {
		return _organizationOwnerRole;
	}

	public Role getOrganizationUserRole() {
		return _organizationUserRole;
	}

	public Role getPowerUserRole() {
		return _powerUserRole;
	}

	public List<ResourceCode> getResourceCodes() {
		return _resourceCodes;
	}

	public ClassName getRoleClassName() {
		return _roleClassName;
	}

	public List<Role> getRoles() {
		return _roles;
	}

	public Role getSiteAdministratorRole() {
		return _siteAdministratorRole;
	}

	public Role getSiteMemberRole() {
		return _siteMemberRole;
	}

	public Role getSiteOwnerRole() {
		return _siteOwnerRole;
	}

	public ClassName getUserClassName() {
		return _userClassName;
	}

	public Object[] getUserNames() {
		return _userNames;
	}

	public Role getUserRole() {
		return _userRole;
	}

	public ClassName getWikiPageClassName() {
		return _wikiPageClassName;
	}

	public void initClassNames() {
		if (_classNames != null) {
			return;
		}

		_classNames = new ArrayList<ClassName>();

		List<String> models = ModelHintsUtil.getModels();

		for (String model : models) {
			ClassName className = new ClassNameImpl();

			className.setClassNameId(_counter.get());
			className.setValue(model);

			_classNames.add(className);

			if (model.equals(BlogsEntry.class.getName())) {
				_blogsEntryClassName = className;
			}
			else if (model.equals(DDMContent.class.getName())) {
				_ddmContentClassName = className;
			}
			else if (model.equals(DLFileEntry.class.getName())) {
				_dlFileEntryClassName = className;
			}
			else if (model.equals(Group.class.getName())) {
				_groupClassName = className;
			}
			else if (model.equals(MBMessage.class.getName())) {
				_mbMessageClassName = className;
			}
			else if (model.equals(Role.class.getName())) {
				_roleClassName = className;
			}
			else if (model.equals(User.class.getName())) {
				_userClassName = className;
			}
			else if (model.equals(WikiPage.class.getName())) {
				_wikiPageClassName = className;
			}
		}
	}

	public void initCompany() {
		_company = new CompanyImpl();

		_company.setCompanyId(_counter.get());
		_company.setAccountId(_counter.get());
	}

	public void initCounters() {
		if (_counters != null) {
			return;
		}

		_counters = new ArrayList<CounterModelImpl>();

		// Counter

		CounterModelImpl counter = new CounterModelImpl();

		counter.setName(Counter.class.getName());
		counter.setCurrentId(_counter.get());

		_counters.add(counter);

		// Permission

		counter = new CounterModelImpl();

		counter.setName(Permission.class.getName());
		counter.setCurrentId(_permissionCounter.get());

		_counters.add(counter);

		// Resource

		counter = new CounterModelImpl();

		counter.setName(Resource.class.getName());
		counter.setCurrentId(_resourceCounter.get());

		_counters.add(counter);

		// ResourceCode

		counter = new CounterModelImpl();

		counter.setName(ResourceCode.class.getName());
		counter.setCurrentId(_resourceCodeCounter.get());

		_counters.add(counter);

		// ResourcePermission

		counter = new CounterModelImpl();

		counter.setName(ResourcePermission.class.getName());
		counter.setCurrentId(_resourcePermissionCounter.get());

		_counters.add(counter);

		// SocialActivity

		counter = new CounterModelImpl();

		counter.setName(SocialActivity.class.getName());
		counter.setCurrentId(_socialActivityCounter.get());

		_counters.add(counter);
	}

	public void initDefaultUser() {
		_defaultUser = new UserImpl();

		_defaultUser.setUserId(_counter.get());
	}

	public void initGroups() {
		if (_groups != null) {
			return;
		}

		_groups = new ArrayList<Group>();

		// Guest

		Group group = new GroupImpl();

		group.setGroupId(_counter.get());
		group.setClassNameId(_groupClassName.getClassNameId());
		group.setClassPK(group.getGroupId());
		group.setName(GroupConstants.GUEST);
		group.setFriendlyURL("/guest");
		group.setSite(true);

		_groups.add(group);

		_guestGroup = group;
	}

	public void initResourceCodes() throws Exception {
		if (_resourceCodes != null) {
			return;
		}

		_resourceCodes = new ArrayList<ResourceCode>();

		_individualResourceCodeIds = new HashMap<String, Long>();
		_individualResourceNames = new HashMap<Long, String>();

		List<String> models = ModelHintsUtil.getModels();

		for (String model : models) {
			initResourceCodes(model);
		}

		Document document = SAXReaderUtil.read(
			new File(
				_baseDir, "../portal-web/docroot/WEB-INF/portlet-custom.xml"),
			false);

		Element rootElement = document.getRootElement();

		List<Element> portletElements = rootElement.elements("portlet");

		for (Element portletElement : portletElements) {
			String portletName = portletElement.elementText("portlet-name");

			initResourceCodes(portletName);
		}
	}

	public void initResourceCodes(String name) {

		// Company

		ResourceCode resourceCode = newResourceCode();

		resourceCode.setName(name);
		resourceCode.setScope(ResourceConstants.SCOPE_COMPANY);

		_resourceCodes.add(resourceCode);

		// Group

		resourceCode = newResourceCode();

		resourceCode.setName(name);
		resourceCode.setScope(ResourceConstants.SCOPE_GROUP);

		_resourceCodes.add(resourceCode);

		// Group template

		resourceCode = newResourceCode();

		resourceCode.setName(name);
		resourceCode.setScope(ResourceConstants.SCOPE_GROUP_TEMPLATE);

		_resourceCodes.add(resourceCode);

		// Individual

		resourceCode = newResourceCode();

		resourceCode.setName(name);
		resourceCode.setScope(ResourceConstants.SCOPE_INDIVIDUAL);

		_resourceCodes.add(resourceCode);

		_individualResourceCodeIds.put(name, resourceCode.getCodeId());
		_individualResourceNames.put(resourceCode.getCodeId(), name);
	}

	public void initRoles() {
		if (_roles != null) {
			return;
		}

		_roles = new ArrayList<Role>();

		// Administrator

		Role role = newRole();

		role.setName(RoleConstants.ADMINISTRATOR);
		role.setType(RoleConstants.TYPE_REGULAR);

		_roles.add(role);

		_administratorRole = role;

		// Guest

		role = newRole();

		role.setName(RoleConstants.GUEST);
		role.setType(RoleConstants.TYPE_REGULAR);

		_roles.add(role);

		_guestRole = role;

		// Organization Administrator

		role = newRole();

		role.setName(RoleConstants.ORGANIZATION_ADMINISTRATOR);
		role.setType(RoleConstants.TYPE_ORGANIZATION);

		_roles.add(role);

		_organizationAdministratorRole = role;

		// Organization Owner

		role = newRole();

		role.setName(RoleConstants.ORGANIZATION_OWNER);
		role.setType(RoleConstants.TYPE_ORGANIZATION);

		_roles.add(role);

		_organizationOwnerRole = role;

		// Organization User

		role = newRole();

		role.setName(RoleConstants.ORGANIZATION_USER);
		role.setType(RoleConstants.TYPE_ORGANIZATION);

		_roles.add(role);

		_organizationUserRole = role;

		// Owner

		role = newRole();

		role.setName(RoleConstants.OWNER);
		role.setType(RoleConstants.TYPE_REGULAR);

		_roles.add(role);

		_ownerRole = role;

		// Power User

		role = newRole();

		role.setName(RoleConstants.POWER_USER);
		role.setType(RoleConstants.TYPE_REGULAR);

		_roles.add(role);

		_powerUserRole = role;

		// Site Administrator

		role = newRole();

		role.setName(RoleConstants.SITE_ADMINISTRATOR);
		role.setType(RoleConstants.TYPE_SITE);

		_roles.add(role);

		_siteAdministratorRole = role;

		// Site Member

		role = newRole();

		role.setName(RoleConstants.SITE_MEMBER);
		role.setType(RoleConstants.TYPE_SITE);

		_roles.add(role);

		_siteMemberRole = role;

		// Site Owner

		role = newRole();

		role.setName(RoleConstants.SITE_OWNER);
		role.setType(RoleConstants.TYPE_SITE);

		_roles.add(role);

		_siteOwnerRole = role;

		// User

		role = newRole();

		role.setName(RoleConstants.USER);
		role.setType(RoleConstants.TYPE_REGULAR);

		_roles.add(role);

		_userRole = role;
	}

	public void initUserNames() throws Exception {
		if (_userNames != null) {
			return;
		}

		_userNames = new Object[2];

		String dependenciesDir =
			"../portal-impl/src/com/liferay/portal/tools/samplesqlbuilder/" +
				"dependencies/";

		List<String> firstNames = ListUtil.fromFile(
			new File(_baseDir, dependenciesDir + "first_names.txt"));
		List<String> lastNames = ListUtil.fromFile(
			new File(_baseDir, dependenciesDir + "last_names.txt"));

		_userNames[0] = firstNames;
		_userNames[1] = lastNames;
	}

	protected Date newCreateDate() {
		return new Date(_baseCreateTime + (_dlDateCounter.get() * Time.SECOND));
	}

	public IntegerWrapper newInteger() {
		return new IntegerWrapper();
	}

	protected ResourceCode newResourceCode() {
		ResourceCode resourceCode = new ResourceCodeImpl();

		resourceCode.setCodeId(_resourceCodeCounter.get());

		return resourceCode;
	}

	protected Role newRole() {
		Role role = new RoleImpl();

		role.setRoleId(_counter.get());
		role.setClassNameId(_roleClassName.getClassNameId());
		role.setClassPK(role.getRoleId());

		return role;
	}

	private Role _administratorRole;
	private long _baseCreateTime = System.currentTimeMillis() + Time.YEAR;
	private String _baseDir;
	private ClassName _blogsEntryClassName;
	private List<ClassName> _classNames;
	private Company _company;
	private SimpleCounter _counter;
	private List<CounterModelImpl> _counters;
	private ClassName _ddmContentClassName;
	private User _defaultUser;
	private SimpleCounter _dlDateCounter;
	private ClassName _dlFileEntryClassName;
	private ClassName _groupClassName;
	private List<Group> _groups;
	private Group _guestGroup;
	private Role _guestRole;
	private Map<String, Long> _individualResourceCodeIds;
	private Map<Long, String> _individualResourceNames;
	private int _maxGroupsCount;
	private int _maxUserToGroupCount;
	private ClassName _mbMessageClassName;
	private Role _organizationAdministratorRole;
	private Role _organizationOwnerRole;
	private Role _organizationUserRole;
	private Role _ownerRole;
	private SimpleCounter _permissionCounter;
	private Role _powerUserRole;
	private SimpleCounter _resourceCodeCounter;
	private List<ResourceCode> _resourceCodes;
	private SimpleCounter _resourceCounter;
	private SimpleCounter _resourcePermissionCounter;
	private ClassName _roleClassName;
	private List<Role> _roles;
	private Format _simpleDateFormat =
		FastDateFormatFactoryUtil.getSimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private Role _siteAdministratorRole;
	private Role _siteMemberRole;
	private Role _siteOwnerRole;
	private SimpleCounter _socialActivityCounter;
	private ClassName _userClassName;
	private Object[] _userNames;
	private Role _userRole;
	private ClassName _wikiPageClassName;

}