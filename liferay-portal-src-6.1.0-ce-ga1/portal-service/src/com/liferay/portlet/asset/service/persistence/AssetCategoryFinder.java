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

package com.liferay.portlet.asset.service.persistence;

/**
 * @author Brian Wing Shun Chan
 */
public interface AssetCategoryFinder {
	public int countByG_C_N(long groupId, long classNameId,
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByG_N_V(long groupId, java.lang.String name,
		long vocabularyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByG_N_P(long groupId, java.lang.String name,
		java.lang.String[] categoryProperties)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int filterCountByG_N_V(long groupId, java.lang.String name,
		long vocabularyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.asset.model.AssetCategory> filterFindByG_N_V(
		long groupId, java.lang.String name, long vocabularyId, int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.asset.model.AssetCategory> findByEntryId(
		long entryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portlet.asset.model.AssetCategory findByG_N(
		long groupId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.asset.NoSuchCategoryException;

	public java.util.List<com.liferay.portlet.asset.model.AssetCategory> findByC_C(
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.asset.model.AssetCategory> findByG_N_V(
		long groupId, java.lang.String name, long vocabularyId, int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.asset.model.AssetCategory> findByG_N_P(
		long groupId, java.lang.String name,
		java.lang.String[] categoryProperties)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.asset.model.AssetCategory> findByG_N_P(
		long groupId, java.lang.String name,
		java.lang.String[] categoryProperties, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;
}