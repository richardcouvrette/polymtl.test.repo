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

package com.liferay.portlet.journal.model.impl;

import com.liferay.portlet.journal.model.JournalArticleDisplay;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public class JournalArticleDisplayImpl implements JournalArticleDisplay {

	public JournalArticleDisplayImpl(
		long companyId, long id, long resourcePrimKey, long groupId,
		long userId, String articleId, double version, String title,
		String urlTitle, String description, String[] availableLocales,
		String content, String type, String structureId, String templateId,
		boolean smallImage, long smallImageId, String smallImageURL,
		int numberOfPages, int currentPage, boolean paginate,
		boolean cacheable) {

		_companyId = companyId;
		_id = id;
		_resourcePrimKey = resourcePrimKey;
		_groupId = groupId;
		_userId = userId;
		_articleId = articleId;
		_version = version;
		_title = title;
		_urlTitle = urlTitle;
		_description = description;
		_availableLocales = availableLocales;
		_content = content;
		_type = type;
		_structureId = structureId;
		_templateId = templateId;
		_smallImage = smallImage;
		_smallImageId = smallImageId;
		_smallImageURL = smallImageURL;
		_numberOfPages = numberOfPages;
		_currentPage = currentPage;
		_paginate = paginate;
		_cacheable = cacheable;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public long getId() {
		return _id;
	}

	public long getResourcePrimKey() {
		return _resourcePrimKey;
	}

	public long getGroupId() {
		return _groupId;
	}

	public long getUserId() {
		return _userId;
	}

	public String getArticleId() {
		return _articleId;
	}

	public double getVersion() {
		return _version;
	}

	public String getTitle() {
		return _title;
	}

	public String getUrlTitle() {
		return _urlTitle;
	}

	public String getDescription() {
		return _description;
	}

	public String[] getAvailableLocales() {
		return _availableLocales;
	}

	public String getContent() {
		return _content;
	}

	public void setContent(String content) {
		_content = content;
	}

	public String getType() {
		return _type;
	}

	public String getStructureId() {
		return _structureId;
	}

	public void setStructureId(String structureId) {
		_structureId = structureId;
	}

	public String getTemplateId() {
		return _templateId;
	}

	public void setTemplateId(String templateId) {
		_templateId = templateId;
	}

	public boolean isSmallImage() {
		return _smallImage;
	}

	public void setSmallImage(boolean smallImage) {
		_smallImage = smallImage;
	}

	public long getSmallImageId() {
		return _smallImageId;
	}

	public void setSmallImageId(long smallImageId) {
		_smallImageId = smallImageId;
	}

	public String getSmallImageURL() {
		return _smallImageURL;
	}

	public void setSmallImageURL(String smallImageURL) {
		_smallImageURL = smallImageURL;
	}

	public int getNumberOfPages() {
		return _numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		_numberOfPages = numberOfPages;
	}

	public int getCurrentPage() {
		return _currentPage;
	}

	public void setCurrentPage(int currentPage) {
		_currentPage = currentPage;
	}

	public boolean isPaginate() {
		return _paginate;
	}

	public void setPaginate(boolean paginate) {
		_paginate = paginate;
	}

	public boolean isCacheable() {
		return _cacheable;
	}

	public void setCacheable(boolean cacheable) {
		_cacheable = cacheable;
	}

	private long _companyId;
	private long _id;
	private long _resourcePrimKey;
	private long _groupId;
	private long _userId;
	private String _articleId;
	private double _version;
	private String _title;
	private String _urlTitle;
	private String _description;
	private String[] _availableLocales;
	private String _content;
	private String _type;
	private String _structureId;
	private String _templateId;
	private boolean _smallImage;
	private long _smallImageId;
	private String _smallImageURL;
	private int _numberOfPages;
	private int _currentPage;
	private boolean _paginate;
	private boolean _cacheable;

}