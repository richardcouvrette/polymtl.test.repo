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

package com.liferay.portlet.journal.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import com.liferay.portlet.journal.service.JournalArticleServiceUtil;

import java.rmi.RemoteException;

/**
 * <p>
 * This class provides a SOAP utility for the
 * {@link com.liferay.portlet.journal.service.JournalArticleServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 * </p>
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.portlet.journal.model.JournalArticleSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.portlet.journal.model.JournalArticle}, that is translated to a
 * {@link com.liferay.portlet.journal.model.JournalArticleSoap}. Methods that SOAP cannot
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
 * @see       JournalArticleServiceHttp
 * @see       com.liferay.portlet.journal.model.JournalArticleSoap
 * @see       com.liferay.portlet.journal.service.JournalArticleServiceUtil
 * @generated
 */
public class JournalArticleServiceSoap {
	public static com.liferay.portlet.journal.model.JournalArticleSoap copyArticle(
		long groupId, java.lang.String oldArticleId,
		java.lang.String newArticleId, boolean autoArticleId, double version)
		throws RemoteException {
		try {
			com.liferay.portlet.journal.model.JournalArticle returnValue = JournalArticleServiceUtil.copyArticle(groupId,
					oldArticleId, newArticleId, autoArticleId, version);

			return com.liferay.portlet.journal.model.JournalArticleSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteArticle(long groupId, java.lang.String articleId,
		double version, java.lang.String articleURL,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			JournalArticleServiceUtil.deleteArticle(groupId, articleId,
				version, articleURL, serviceContext);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteArticle(long groupId, java.lang.String articleId,
		java.lang.String articleURL,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			JournalArticleServiceUtil.deleteArticle(groupId, articleId,
				articleURL, serviceContext);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.journal.model.JournalArticleSoap expireArticle(
		long groupId, java.lang.String articleId, double version,
		java.lang.String articleURL,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.portlet.journal.model.JournalArticle returnValue = JournalArticleServiceUtil.expireArticle(groupId,
					articleId, version, articleURL, serviceContext);

			return com.liferay.portlet.journal.model.JournalArticleSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void expireArticle(long groupId, java.lang.String articleId,
		java.lang.String articleURL,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			JournalArticleServiceUtil.expireArticle(groupId, articleId,
				articleURL, serviceContext);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.journal.model.JournalArticleSoap getArticle(
		long id) throws RemoteException {
		try {
			com.liferay.portlet.journal.model.JournalArticle returnValue = JournalArticleServiceUtil.getArticle(id);

			return com.liferay.portlet.journal.model.JournalArticleSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.journal.model.JournalArticleSoap getArticle(
		long groupId, java.lang.String articleId) throws RemoteException {
		try {
			com.liferay.portlet.journal.model.JournalArticle returnValue = JournalArticleServiceUtil.getArticle(groupId,
					articleId);

			return com.liferay.portlet.journal.model.JournalArticleSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.journal.model.JournalArticleSoap getArticle(
		long groupId, java.lang.String articleId, double version)
		throws RemoteException {
		try {
			com.liferay.portlet.journal.model.JournalArticle returnValue = JournalArticleServiceUtil.getArticle(groupId,
					articleId, version);

			return com.liferay.portlet.journal.model.JournalArticleSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.journal.model.JournalArticleSoap getArticle(
		long groupId, java.lang.String className, long classPK)
		throws RemoteException {
		try {
			com.liferay.portlet.journal.model.JournalArticle returnValue = JournalArticleServiceUtil.getArticle(groupId,
					className, classPK);

			return com.liferay.portlet.journal.model.JournalArticleSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.journal.model.JournalArticleSoap getArticleByUrlTitle(
		long groupId, java.lang.String urlTitle) throws RemoteException {
		try {
			com.liferay.portlet.journal.model.JournalArticle returnValue = JournalArticleServiceUtil.getArticleByUrlTitle(groupId,
					urlTitle);

			return com.liferay.portlet.journal.model.JournalArticleSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.journal.model.JournalArticleSoap[] getArticlesByArticleId(
		long groupId, java.lang.String articleId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws RemoteException {
		try {
			java.util.List<com.liferay.portlet.journal.model.JournalArticle> returnValue =
				JournalArticleServiceUtil.getArticlesByArticleId(groupId,
					articleId, start, end, obc);

			return com.liferay.portlet.journal.model.JournalArticleSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.journal.model.JournalArticleSoap[] getArticlesByLayoutUuid(
		long groupId, java.lang.String layoutUuid) throws RemoteException {
		try {
			java.util.List<com.liferay.portlet.journal.model.JournalArticle> returnValue =
				JournalArticleServiceUtil.getArticlesByLayoutUuid(groupId,
					layoutUuid);

			return com.liferay.portlet.journal.model.JournalArticleSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getArticlesCountByArticleId(long groupId,
		java.lang.String articleId) throws RemoteException {
		try {
			int returnValue = JournalArticleServiceUtil.getArticlesCountByArticleId(groupId,
					articleId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.journal.model.JournalArticleSoap getDisplayArticleByUrlTitle(
		long groupId, java.lang.String urlTitle) throws RemoteException {
		try {
			com.liferay.portlet.journal.model.JournalArticle returnValue = JournalArticleServiceUtil.getDisplayArticleByUrlTitle(groupId,
					urlTitle);

			return com.liferay.portlet.journal.model.JournalArticleSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.journal.model.JournalArticleSoap getLatestArticle(
		long resourcePrimKey) throws RemoteException {
		try {
			com.liferay.portlet.journal.model.JournalArticle returnValue = JournalArticleServiceUtil.getLatestArticle(resourcePrimKey);

			return com.liferay.portlet.journal.model.JournalArticleSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.journal.model.JournalArticleSoap getLatestArticle(
		long groupId, java.lang.String articleId, int status)
		throws RemoteException {
		try {
			com.liferay.portlet.journal.model.JournalArticle returnValue = JournalArticleServiceUtil.getLatestArticle(groupId,
					articleId, status);

			return com.liferay.portlet.journal.model.JournalArticleSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.journal.model.JournalArticleSoap getLatestArticle(
		long groupId, java.lang.String className, long classPK)
		throws RemoteException {
		try {
			com.liferay.portlet.journal.model.JournalArticle returnValue = JournalArticleServiceUtil.getLatestArticle(groupId,
					className, classPK);

			return com.liferay.portlet.journal.model.JournalArticleSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void removeArticleLocale(long companyId,
		java.lang.String languageId) throws RemoteException {
		try {
			JournalArticleServiceUtil.removeArticleLocale(companyId, languageId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.journal.model.JournalArticleSoap removeArticleLocale(
		long groupId, java.lang.String articleId, double version,
		java.lang.String languageId) throws RemoteException {
		try {
			com.liferay.portlet.journal.model.JournalArticle returnValue = JournalArticleServiceUtil.removeArticleLocale(groupId,
					articleId, version, languageId);

			return com.liferay.portlet.journal.model.JournalArticleSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.journal.model.JournalArticleSoap[] search(
		long companyId, long groupId, long classNameId,
		java.lang.String keywords, java.lang.Double version,
		java.lang.String type, java.lang.String structureId,
		java.lang.String templateId, java.util.Date displayDateGT,
		java.util.Date displayDateLT, int status, java.util.Date reviewDate,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws RemoteException {
		try {
			java.util.List<com.liferay.portlet.journal.model.JournalArticle> returnValue =
				JournalArticleServiceUtil.search(companyId, groupId,
					classNameId, keywords, version, type, structureId,
					templateId, displayDateGT, displayDateLT, status,
					reviewDate, start, end, obc);

			return com.liferay.portlet.journal.model.JournalArticleSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.journal.model.JournalArticleSoap[] search(
		long companyId, long groupId, long classNameId,
		java.lang.String articleId, java.lang.Double version,
		java.lang.String title, java.lang.String description,
		java.lang.String content, java.lang.String type,
		java.lang.String structureId, java.lang.String templateId,
		java.util.Date displayDateGT, java.util.Date displayDateLT, int status,
		java.util.Date reviewDate, boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws RemoteException {
		try {
			java.util.List<com.liferay.portlet.journal.model.JournalArticle> returnValue =
				JournalArticleServiceUtil.search(companyId, groupId,
					classNameId, articleId, version, title, description,
					content, type, structureId, templateId, displayDateGT,
					displayDateLT, status, reviewDate, andOperator, start, end,
					obc);

			return com.liferay.portlet.journal.model.JournalArticleSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.journal.model.JournalArticleSoap[] search(
		long companyId, long groupId, long classNameId,
		java.lang.String articleId, java.lang.Double version,
		java.lang.String title, java.lang.String description,
		java.lang.String content, java.lang.String type,
		java.lang.String[] structureIds, java.lang.String[] templateIds,
		java.util.Date displayDateGT, java.util.Date displayDateLT, int status,
		java.util.Date reviewDate, boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws RemoteException {
		try {
			java.util.List<com.liferay.portlet.journal.model.JournalArticle> returnValue =
				JournalArticleServiceUtil.search(companyId, groupId,
					classNameId, articleId, version, title, description,
					content, type, structureIds, templateIds, displayDateGT,
					displayDateLT, status, reviewDate, andOperator, start, end,
					obc);

			return com.liferay.portlet.journal.model.JournalArticleSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int searchCount(long companyId, long groupId,
		long classNameId, java.lang.String keywords, java.lang.Double version,
		java.lang.String type, java.lang.String structureId,
		java.lang.String templateId, java.util.Date displayDateGT,
		java.util.Date displayDateLT, int status, java.util.Date reviewDate)
		throws RemoteException {
		try {
			int returnValue = JournalArticleServiceUtil.searchCount(companyId,
					groupId, classNameId, keywords, version, type, structureId,
					templateId, displayDateGT, displayDateLT, status, reviewDate);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int searchCount(long companyId, long groupId,
		long classNameId, java.lang.String articleId, java.lang.Double version,
		java.lang.String title, java.lang.String description,
		java.lang.String content, java.lang.String type,
		java.lang.String structureId, java.lang.String templateId,
		java.util.Date displayDateGT, java.util.Date displayDateLT, int status,
		java.util.Date reviewDate, boolean andOperator)
		throws RemoteException {
		try {
			int returnValue = JournalArticleServiceUtil.searchCount(companyId,
					groupId, classNameId, articleId, version, title,
					description, content, type, structureId, templateId,
					displayDateGT, displayDateLT, status, reviewDate,
					andOperator);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int searchCount(long companyId, long groupId,
		long classNameId, java.lang.String articleId, java.lang.Double version,
		java.lang.String title, java.lang.String description,
		java.lang.String content, java.lang.String type,
		java.lang.String[] structureIds, java.lang.String[] templateIds,
		java.util.Date displayDateGT, java.util.Date displayDateLT, int status,
		java.util.Date reviewDate, boolean andOperator)
		throws RemoteException {
		try {
			int returnValue = JournalArticleServiceUtil.searchCount(companyId,
					groupId, classNameId, articleId, version, title,
					description, content, type, structureIds, templateIds,
					displayDateGT, displayDateLT, status, reviewDate,
					andOperator);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void subscribe(long groupId) throws RemoteException {
		try {
			JournalArticleServiceUtil.subscribe(groupId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void unsubscribe(long groupId) throws RemoteException {
		try {
			JournalArticleServiceUtil.unsubscribe(groupId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.journal.model.JournalArticleSoap updateArticle(
		long groupId, java.lang.String articleId, double version,
		java.lang.String content,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.portlet.journal.model.JournalArticle returnValue = JournalArticleServiceUtil.updateArticle(groupId,
					articleId, version, content, serviceContext);

			return com.liferay.portlet.journal.model.JournalArticleSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.journal.model.JournalArticleSoap updateContent(
		long groupId, java.lang.String articleId, double version,
		java.lang.String content) throws RemoteException {
		try {
			com.liferay.portlet.journal.model.JournalArticle returnValue = JournalArticleServiceUtil.updateContent(groupId,
					articleId, version, content);

			return com.liferay.portlet.journal.model.JournalArticleSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(JournalArticleServiceSoap.class);
}