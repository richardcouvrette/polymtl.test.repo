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

package com.liferay.portlet.asset.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link AssetTagService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AssetTagService
 * @generated
 */
public class AssetTagServiceWrapper implements AssetTagService,
	ServiceWrapper<AssetTagService> {
	public AssetTagServiceWrapper(AssetTagService assetTagService) {
		_assetTagService = assetTagService;
	}

	public com.liferay.portlet.asset.model.AssetTag addTag(
		java.lang.String name, java.lang.String[] tagProperties,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetTagService.addTag(name, tagProperties, serviceContext);
	}

	public void deleteTag(long tagId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_assetTagService.deleteTag(tagId);
	}

	public void deleteTags(long[] tagIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_assetTagService.deleteTags(tagIds);
	}

	public java.util.List<com.liferay.portlet.asset.model.AssetTag> getGroupsTags(
		long[] groupIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetTagService.getGroupsTags(groupIds);
	}

	public java.util.List<com.liferay.portlet.asset.model.AssetTag> getGroupTags(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetTagService.getGroupTags(groupId);
	}

	public java.util.List<com.liferay.portlet.asset.model.AssetTag> getGroupTags(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetTagService.getGroupTags(groupId, start, end, obc);
	}

	public int getGroupTagsCount(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetTagService.getGroupTagsCount(groupId);
	}

	public com.liferay.portal.kernel.json.JSONObject getJSONGroupTags(
		long groupId, java.lang.String name, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetTagService.getJSONGroupTags(groupId, name, start, end);
	}

	public com.liferay.portlet.asset.model.AssetTag getTag(long tagId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetTagService.getTag(tagId);
	}

	public java.util.List<com.liferay.portlet.asset.model.AssetTag> getTags(
		long groupId, long classNameId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetTagService.getTags(groupId, classNameId, name);
	}

	public java.util.List<com.liferay.portlet.asset.model.AssetTag> getTags(
		long groupId, long classNameId, java.lang.String name, int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetTagService.getTags(groupId, classNameId, name, start, end,
			obc);
	}

	public java.util.List<com.liferay.portlet.asset.model.AssetTag> getTags(
		long groupId, java.lang.String name, java.lang.String[] tagProperties,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetTagService.getTags(groupId, name, tagProperties, start, end);
	}

	public java.util.List<com.liferay.portlet.asset.model.AssetTag> getTags(
		java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetTagService.getTags(className, classPK);
	}

	public int getTagsCount(long groupId, long classNameId,
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetTagService.getTagsCount(groupId, classNameId, name);
	}

	public int getTagsCount(long groupId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetTagService.getTagsCount(groupId, name);
	}

	public int getTagsCount(long groupId, java.lang.String name,
		java.lang.String[] tagProperties)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetTagService.getTagsCount(groupId, name, tagProperties);
	}

	public void mergeTags(long fromTagId, long toTagId,
		boolean overrideProperties)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_assetTagService.mergeTags(fromTagId, toTagId, overrideProperties);
	}

	public void mergeTags(long[] fromTagIds, long toTagId,
		boolean overrideProperties)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_assetTagService.mergeTags(fromTagIds, toTagId, overrideProperties);
	}

	public com.liferay.portal.kernel.json.JSONArray search(long groupId,
		java.lang.String name, java.lang.String[] tagProperties, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _assetTagService.search(groupId, name, tagProperties, start, end);
	}

	public com.liferay.portlet.asset.model.AssetTag updateTag(long tagId,
		java.lang.String name, java.lang.String[] tagProperties,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetTagService.updateTag(tagId, name, tagProperties,
			serviceContext);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AssetTagService getWrappedAssetTagService() {
		return _assetTagService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAssetTagService(AssetTagService assetTagService) {
		_assetTagService = assetTagService;
	}

	public AssetTagService getWrappedService() {
		return _assetTagService;
	}

	public void setWrappedService(AssetTagService assetTagService) {
		_assetTagService = assetTagService;
	}

	private AssetTagService _assetTagService;
}