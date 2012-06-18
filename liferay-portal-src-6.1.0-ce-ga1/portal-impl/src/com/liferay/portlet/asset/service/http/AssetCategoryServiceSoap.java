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

package com.liferay.portlet.asset.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import com.liferay.portlet.asset.service.AssetCategoryServiceUtil;

import java.rmi.RemoteException;

/**
 * <p>
 * This class provides a SOAP utility for the
 * {@link com.liferay.portlet.asset.service.AssetCategoryServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 * </p>
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.portlet.asset.model.AssetCategorySoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.portlet.asset.model.AssetCategory}, that is translated to a
 * {@link com.liferay.portlet.asset.model.AssetCategorySoap}. Methods that SOAP cannot
 * safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at
 * http://localhost:8080/api/secure/axis. Set the property
 * <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AssetCategoryServiceHttp
 * @see       com.liferay.portlet.asset.model.AssetCategorySoap
 * @see       com.liferay.portlet.asset.service.AssetCategoryServiceUtil
 * @generated
 */
public class AssetCategoryServiceSoap {
	public static void deleteCategories(long[] categoryIds)
		throws RemoteException {
		try {
			AssetCategoryServiceUtil.deleteCategories(categoryIds);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteCategory(long categoryId)
		throws RemoteException {
		try {
			AssetCategoryServiceUtil.deleteCategory(categoryId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.asset.model.AssetCategorySoap[] getCategories(
		java.lang.String className, long classPK) throws RemoteException {
		try {
			java.util.List<com.liferay.portlet.asset.model.AssetCategory> returnValue =
				AssetCategoryServiceUtil.getCategories(className, classPK);

			return com.liferay.portlet.asset.model.AssetCategorySoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.asset.model.AssetCategorySoap getCategory(
		long categoryId) throws RemoteException {
		try {
			com.liferay.portlet.asset.model.AssetCategory returnValue = AssetCategoryServiceUtil.getCategory(categoryId);

			return com.liferay.portlet.asset.model.AssetCategorySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.asset.model.AssetCategorySoap[] getChildCategories(
		long parentCategoryId) throws RemoteException {
		try {
			java.util.List<com.liferay.portlet.asset.model.AssetCategory> returnValue =
				AssetCategoryServiceUtil.getChildCategories(parentCategoryId);

			return com.liferay.portlet.asset.model.AssetCategorySoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.asset.model.AssetCategorySoap[] getChildCategories(
		long parentCategoryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws RemoteException {
		try {
			java.util.List<com.liferay.portlet.asset.model.AssetCategory> returnValue =
				AssetCategoryServiceUtil.getChildCategories(parentCategoryId,
					start, end, obc);

			return com.liferay.portlet.asset.model.AssetCategorySoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static java.lang.String getJSONVocabularyCategories(long groupId,
		java.lang.String name, long vocabularyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONObject returnValue = AssetCategoryServiceUtil.getJSONVocabularyCategories(groupId,
					name, vocabularyId, start, end, obc);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.asset.model.AssetCategorySoap[] getVocabularyCategories(
		long vocabularyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws RemoteException {
		try {
			java.util.List<com.liferay.portlet.asset.model.AssetCategory> returnValue =
				AssetCategoryServiceUtil.getVocabularyCategories(vocabularyId,
					start, end, obc);

			return com.liferay.portlet.asset.model.AssetCategorySoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.asset.model.AssetCategorySoap[] getVocabularyCategories(
		long parentCategoryId, long vocabularyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws RemoteException {
		try {
			java.util.List<com.liferay.portlet.asset.model.AssetCategory> returnValue =
				AssetCategoryServiceUtil.getVocabularyCategories(parentCategoryId,
					vocabularyId, start, end, obc);

			return com.liferay.portlet.asset.model.AssetCategorySoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.asset.model.AssetCategorySoap[] getVocabularyCategories(
		long groupId, java.lang.String name, long vocabularyId, int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws RemoteException {
		try {
			java.util.List<com.liferay.portlet.asset.model.AssetCategory> returnValue =
				AssetCategoryServiceUtil.getVocabularyCategories(groupId, name,
					vocabularyId, start, end, obc);

			return com.liferay.portlet.asset.model.AssetCategorySoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getVocabularyCategoriesCount(long groupId,
		long vocabularyId) throws RemoteException {
		try {
			int returnValue = AssetCategoryServiceUtil.getVocabularyCategoriesCount(groupId,
					vocabularyId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getVocabularyCategoriesCount(long groupId,
		java.lang.String name, long vocabularyId) throws RemoteException {
		try {
			int returnValue = AssetCategoryServiceUtil.getVocabularyCategoriesCount(groupId,
					name, vocabularyId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.asset.model.AssetCategorySoap[] getVocabularyRootCategories(
		long vocabularyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws RemoteException {
		try {
			java.util.List<com.liferay.portlet.asset.model.AssetCategory> returnValue =
				AssetCategoryServiceUtil.getVocabularyRootCategories(vocabularyId,
					start, end, obc);

			return com.liferay.portlet.asset.model.AssetCategorySoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.asset.model.AssetCategorySoap moveCategory(
		long categoryId, long parentCategoryId, long vocabularyId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.portlet.asset.model.AssetCategory returnValue = AssetCategoryServiceUtil.moveCategory(categoryId,
					parentCategoryId, vocabularyId, serviceContext);

			return com.liferay.portlet.asset.model.AssetCategorySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.asset.model.AssetCategorySoap[] search(
		long groupId, java.lang.String keywords, long vocabularyId, int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws RemoteException {
		try {
			java.util.List<com.liferay.portlet.asset.model.AssetCategory> returnValue =
				AssetCategoryServiceUtil.search(groupId, keywords,
					vocabularyId, start, end, obc);

			return com.liferay.portlet.asset.model.AssetCategorySoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static java.lang.String search(long groupId, java.lang.String name,
		java.lang.String[] categoryProperties, int start, int end)
		throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONArray returnValue = AssetCategoryServiceUtil.search(groupId,
					name, categoryProperties, start, end);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(AssetCategoryServiceSoap.class);
}