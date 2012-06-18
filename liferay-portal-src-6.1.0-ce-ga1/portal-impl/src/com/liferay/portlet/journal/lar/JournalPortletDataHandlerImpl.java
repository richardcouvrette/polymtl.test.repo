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

package com.liferay.portlet.journal.lar;

import com.liferay.portal.NoSuchImageException;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.BasePortletDataHandler;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.lar.PortletDataHandlerBoolean;
import com.liferay.portal.kernel.lar.PortletDataHandlerControl;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Image;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.persistence.ImageUtil;
import com.liferay.portal.service.persistence.LayoutUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.documentlibrary.lar.DLPortletDataHandlerImpl;
import com.liferay.portlet.documentlibrary.lar.FileEntryUtil;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;
import com.liferay.portlet.journal.FeedTargetLayoutFriendlyUrlException;
import com.liferay.portlet.journal.NoSuchArticleException;
import com.liferay.portlet.journal.NoSuchStructureException;
import com.liferay.portlet.journal.NoSuchTemplateException;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.model.JournalArticleConstants;
import com.liferay.portlet.journal.model.JournalArticleImage;
import com.liferay.portlet.journal.model.JournalArticleResource;
import com.liferay.portlet.journal.model.JournalFeed;
import com.liferay.portlet.journal.model.JournalStructure;
import com.liferay.portlet.journal.model.JournalTemplate;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portlet.journal.service.JournalFeedLocalServiceUtil;
import com.liferay.portlet.journal.service.JournalStructureLocalServiceUtil;
import com.liferay.portlet.journal.service.JournalTemplateLocalServiceUtil;
import com.liferay.portlet.journal.service.persistence.JournalArticleImageUtil;
import com.liferay.portlet.journal.service.persistence.JournalArticleResourceUtil;
import com.liferay.portlet.journal.service.persistence.JournalArticleUtil;
import com.liferay.portlet.journal.service.persistence.JournalFeedUtil;
import com.liferay.portlet.journal.service.persistence.JournalStructureUtil;
import com.liferay.portlet.journal.service.persistence.JournalTemplateUtil;
import com.liferay.portlet.journal.util.comparator.ArticleIDComparator;
import com.liferay.portlet.journal.util.comparator.StructurePKComparator;

import java.io.File;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.portlet.PortletPreferences;

/**
 * <p>
 * Provides the Journal portlet export and import functionality, which is to
 * clone all articles, structures, and templates associated with the layout's
 * group. Upon import, new instances of the corresponding articles, structures,
 * and templates are created or updated according to the DATA_MIRROW strategy
 * The author of the newly created objects are determined by the
 * JournalCreationStrategy class defined in <i>portal.properties</i>. That
 * strategy also allows the text of the journal article to be modified prior to
 * import.
 * </p>
 *
 * <p>
 * This <code>PortletDataHandler</code> differs from
 * <code>JournalContentPortletDataHandlerImpl</code> in that it exports all
 * articles owned by the group whether or not they are actually displayed in a
 * portlet in the layout set.
 * </p>
 *
 * @author Raymond Augé
 * @author Joel Kozikowski
 * @author Brian Wing Shun Chan
 * @author Bruno Farache
 * @author Karthik Sudarshan
 * @author Wesley Gong
 * @author Hugo Huijser
 * @see    com.liferay.portal.kernel.lar.PortletDataHandler
 * @see    com.liferay.portlet.journal.lar.JournalContentPortletDataHandlerImpl
 * @see    com.liferay.portlet.journal.lar.JournalCreationStrategy
 */
public class JournalPortletDataHandlerImpl extends BasePortletDataHandler {

	public static void exportArticle(
			PortletDataContext portletDataContext, Element articlesElement,
			Element structuresElement, Element templatesElement,
			Element dlFileEntryTypesElement, Element dlFoldersElement,
			Element dlFileEntriesElement, Element dlFileRanksElement,
			JournalArticle article, boolean checkDateRange)
		throws Exception {

		if (checkDateRange &&
			!portletDataContext.isWithinDateRange(article.getModifiedDate())) {

			return;
		}

		if ((article.getStatus() != WorkflowConstants.STATUS_APPROVED) &&
			(article.getStatus() != WorkflowConstants.STATUS_EXPIRED)) {

			return;
		}

		String path = getArticlePath(portletDataContext, article);

		if (!portletDataContext.isPathNotProcessed(path)) {
			return;
		}

		// Clone this article to make sure changes to its content are never
		// persisted

		article = (JournalArticle)article.clone();

		Element articleElement = (Element)articlesElement.selectSingleNode(
			"//article[@path='".concat(path).concat("']"));

		if (articleElement == null) {
			articleElement = articlesElement.addElement("article");
		}

		articleElement.addAttribute(
			"article-resource-uuid", article.getArticleResourceUuid());

		Group companyGroup = GroupLocalServiceUtil.getCompanyGroup(
			article.getCompanyId());

		if (Validator.isNotNull(article.getStructureId())) {
			JournalStructure structure = null;

			try {
				structure = JournalStructureLocalServiceUtil.getStructure(
					article.getGroupId(), article.getStructureId());
			}
			catch (NoSuchStructureException nsse) {
				structure = JournalStructureLocalServiceUtil.getStructure(
					companyGroup.getGroupId(), article.getStructureId());
			}

			articleElement.addAttribute("structure-uuid", structure.getUuid());

			exportStructure(portletDataContext, structuresElement, structure);
		}

		if (Validator.isNotNull(article.getTemplateId())) {
			JournalTemplate template = null;

			try {
				template = JournalTemplateLocalServiceUtil.getTemplate(
					article.getGroupId(), article.getTemplateId());
			}
			catch (NoSuchTemplateException nste) {
				template = JournalTemplateLocalServiceUtil.getTemplate(
					companyGroup.getGroupId(), article.getTemplateId());
			}

			articleElement.addAttribute("template-uuid", template.getUuid());

			exportTemplate(
				portletDataContext, templatesElement, dlFileEntryTypesElement,
				dlFoldersElement, dlFileEntriesElement, dlFileRanksElement,
				template);
		}

		Image smallImage = ImageUtil.fetchByPrimaryKey(
			article.getSmallImageId());

		if (article.isSmallImage() && (smallImage != null)) {
			String smallImagePath = getArticleSmallImagePath(
				portletDataContext, article);

			articleElement.addAttribute("small-image-path", smallImagePath);

			article.setSmallImageType(smallImage.getType());

			portletDataContext.addZipEntry(
				smallImagePath, smallImage.getTextObj());
		}

		if (portletDataContext.getBooleanParameter(_NAMESPACE, "images")) {
			String imagePath = getArticleImagePath(portletDataContext, article);

			articleElement.addAttribute("image-path", imagePath);

			List<JournalArticleImage> articleImages =
				JournalArticleImageUtil.findByG_A_V(
					article.getGroupId(), article.getArticleId(),
					article.getVersion());

			for (JournalArticleImage articleImage : articleImages) {
				Image image = null;

				try {
					image = ImageUtil.findByPrimaryKey(
						articleImage.getArticleImageId());
				}
				catch (NoSuchImageException nsie) {
					continue;
				}

				String articleImagePath = getArticleImagePath(
					portletDataContext, article, articleImage, image);

				if (!portletDataContext.isPathNotProcessed(articleImagePath)) {
					continue;
				}

				portletDataContext.addZipEntry(
					articleImagePath, image.getTextObj());
			}
		}

		article.setStatusByUserUuid(article.getStatusByUserUuid());

		if (portletDataContext.getBooleanParameter(
				_NAMESPACE, "embedded-assets")) {

			String content = exportReferencedContent(
				portletDataContext, dlFileEntryTypesElement, dlFoldersElement,
				dlFileEntriesElement, dlFileRanksElement, articleElement,
				article.getContent());

			article.setContent(content);
		}

		portletDataContext.addClassedModel(
			articleElement, path, article, _NAMESPACE);
	}

	public static String exportReferencedContent(
			PortletDataContext portletDataContext,
			Element dlFileEntryTypesElement, Element dlFoldersElement,
			Element dlFileEntriesElement, Element dlFileRanksElement,
			Element entityElement, String content)
		throws Exception {

		content = exportDLFileEntries(
			portletDataContext, dlFileEntryTypesElement, dlFoldersElement,
			dlFileEntriesElement, dlFileRanksElement, entityElement, content,
			false);
		content = exportLayoutFriendlyURLs(portletDataContext, content);
		content = exportLinksToLayout(portletDataContext, content);

		String entityElementName = entityElement.getName();

		if (!entityElementName.equals("article")) {
			content = StringUtil.replace(
				content, StringPool.AMPERSAND_ENCODED, StringPool.AMPERSAND);
		}

		return content;
	}

	public static String getArticlePath(
			PortletDataContext portletDataContext, JournalArticle article)
		throws Exception {

		StringBundler sb = new StringBundler(8);

		sb.append(portletDataContext.getPortletPath(PortletKeys.JOURNAL));
		sb.append("/articles/");
		sb.append(article.getArticleResourceUuid());
		sb.append(StringPool.SLASH);
		sb.append(article.getVersion());
		sb.append(StringPool.SLASH);
		sb.append("article.xml");

		return sb.toString();
	}

	public static void importArticle(
			PortletDataContext portletDataContext, Element articleElement)
		throws Exception {

		String path = articleElement.attributeValue("path");

		if (!portletDataContext.isPathNotProcessed(path)) {
			return;
		}

		JournalArticle article =
			(JournalArticle)portletDataContext.getZipEntryAsObject(path);

		long userId = portletDataContext.getUserId(article.getUserUuid());

		JournalCreationStrategy creationStrategy =
			JournalCreationStrategyFactory.getInstance();

		long authorId = creationStrategy.getAuthorUserId(
			portletDataContext, article);

		if (authorId != JournalCreationStrategy.USE_DEFAULT_USER_ID_STRATEGY) {
			userId = authorId;
		}

		User user = UserLocalServiceUtil.getUser(userId);

		String articleId = article.getArticleId();
		boolean autoArticleId = false;

		if ((Validator.isNumber(articleId)) ||
			(JournalArticleUtil.fetchByG_A_V(
				portletDataContext.getScopeGroupId(), articleId,
					JournalArticleConstants.VERSION_DEFAULT) != null)) {

			autoArticleId = true;
		}

		Map<String, String> articleIds =
			(Map<String, String>)portletDataContext.getNewPrimaryKeysMap(
				JournalArticle.class + ".articleId");

		String newArticleId = articleIds.get(articleId);

		if (Validator.isNotNull(newArticleId)) {

			// A sibling of a different version was already assigned a new
			// article id

			articleId = newArticleId;
			autoArticleId = false;
		}

		String content = article.getContent();

		content = importDLFileEntries(
			portletDataContext, articleElement, content);

		Group group = GroupLocalServiceUtil.getGroup(
			portletDataContext.getScopeGroupId());

		content = StringUtil.replace(
			content, "@data_handler_group_friendly_url@",
			group.getFriendlyURL());

		content = importLinksToLayout(portletDataContext, content);

		article.setContent(content);

		String newContent = creationStrategy.getTransformedContent(
			portletDataContext, article);

		if (newContent != JournalCreationStrategy.ARTICLE_CONTENT_UNCHANGED) {
			article.setContent(newContent);
		}

		Map<String, String> structureIds =
			(Map<String, String>)portletDataContext.getNewPrimaryKeysMap(
				JournalStructure.class);

		String parentStructureId = MapUtil.getString(
			structureIds, article.getStructureId(), article.getStructureId());

		Map<String, String> templateIds =
			(Map<String, String>)portletDataContext.getNewPrimaryKeysMap(
				JournalTemplate.class);

		String parentTemplateId = MapUtil.getString(
			templateIds, article.getTemplateId(), article.getTemplateId());

		Date displayDate = article.getDisplayDate();

		int displayDateMonth = 0;
		int displayDateDay = 0;
		int displayDateYear = 0;
		int displayDateHour = 0;
		int displayDateMinute = 0;

		if (displayDate != null) {
			Calendar displayCal = CalendarFactoryUtil.getCalendar(
				user.getTimeZone());

			displayCal.setTime(displayDate);

			displayDateMonth = displayCal.get(Calendar.MONTH);
			displayDateDay = displayCal.get(Calendar.DATE);
			displayDateYear = displayCal.get(Calendar.YEAR);
			displayDateHour = displayCal.get(Calendar.HOUR);
			displayDateMinute = displayCal.get(Calendar.MINUTE);

			if (displayCal.get(Calendar.AM_PM) == Calendar.PM) {
				displayDateHour += 12;
			}
		}

		Date expirationDate = article.getExpirationDate();

		int expirationDateMonth = 0;
		int expirationDateDay = 0;
		int expirationDateYear = 0;
		int expirationDateHour = 0;
		int expirationDateMinute = 0;
		boolean neverExpire = true;

		if (expirationDate != null) {
			Calendar expirationCal = CalendarFactoryUtil.getCalendar(
				user.getTimeZone());

			expirationCal.setTime(expirationDate);

			expirationDateMonth = expirationCal.get(Calendar.MONTH);
			expirationDateDay = expirationCal.get(Calendar.DATE);
			expirationDateYear = expirationCal.get(Calendar.YEAR);
			expirationDateHour = expirationCal.get(Calendar.HOUR);
			expirationDateMinute = expirationCal.get(Calendar.MINUTE);
			neverExpire = false;

			if (expirationCal.get(Calendar.AM_PM) == Calendar.PM) {
				expirationDateHour += 12;
			}
		}

		Date reviewDate = article.getReviewDate();

		int reviewDateMonth = 0;
		int reviewDateDay = 0;
		int reviewDateYear = 0;
		int reviewDateHour = 0;
		int reviewDateMinute = 0;
		boolean neverReview = true;

		if (reviewDate != null) {
			Calendar reviewCal = CalendarFactoryUtil.getCalendar(
				user.getTimeZone());

			reviewCal.setTime(reviewDate);

			reviewDateMonth = reviewCal.get(Calendar.MONTH);
			reviewDateDay = reviewCal.get(Calendar.DATE);
			reviewDateYear = reviewCal.get(Calendar.YEAR);
			reviewDateHour = reviewCal.get(Calendar.HOUR);
			reviewDateMinute = reviewCal.get(Calendar.MINUTE);
			neverReview = false;

			if (reviewCal.get(Calendar.AM_PM) == Calendar.PM) {
				reviewDateHour += 12;
			}
		}

		long structurePrimaryKey = 0;

		if (Validator.isNotNull(article.getStructureId())) {
			String structureUuid = articleElement.attributeValue(
				"structure-uuid");

			JournalStructure existingStructure =
				JournalStructureUtil.fetchByUUID_G(
					structureUuid, portletDataContext.getScopeGroupId());

			if (existingStructure == null) {
				Group companyGroup = GroupLocalServiceUtil.getCompanyGroup(
					portletDataContext.getCompanyId());

				long companyGroupId = companyGroup.getGroupId();

				existingStructure = JournalStructureUtil.fetchByUUID_G(
					structureUuid, companyGroupId);
			}

			if (existingStructure == null) {
				String newStructureId = structureIds.get(
					article.getStructureId());

				if (Validator.isNotNull(newStructureId)) {
					existingStructure = JournalStructureUtil.fetchByG_S(
						portletDataContext.getScopeGroupId(),
						String.valueOf(newStructureId));
				}

				if (existingStructure == null) {
					if (_log.isWarnEnabled()) {
						StringBundler sb = new StringBundler();

						sb.append("Structure ");
						sb.append(article.getStructureId());
						sb.append(" is missing for article ");
						sb.append(article.getArticleId());
						sb.append(", skipping this article.");

						_log.warn(sb.toString());
					}

					return;
				}
			}

			structurePrimaryKey = existingStructure.getPrimaryKey();

			parentStructureId = existingStructure.getStructureId();
		}

		if (Validator.isNotNull(article.getTemplateId())) {
			String templateUuid = articleElement.attributeValue(
				"template-uuid");

			JournalTemplate existingTemplate =
				JournalTemplateUtil.fetchByUUID_G(
					templateUuid, portletDataContext.getScopeGroupId());

			if (existingTemplate == null) {
				Group companyGroup = GroupLocalServiceUtil.getCompanyGroup(
					portletDataContext.getCompanyId());

				long companyGroupId = companyGroup.getGroupId();

				existingTemplate = JournalTemplateUtil.fetchByUUID_G(
					templateUuid, companyGroupId);
			}

			if (existingTemplate == null) {
				String newTemplateId = templateIds.get(article.getTemplateId());

				if (Validator.isNotNull(newTemplateId)) {
					existingTemplate = JournalTemplateUtil.fetchByG_T(
						portletDataContext.getScopeGroupId(), newTemplateId);
				}

				if (existingTemplate == null) {
					if (_log.isWarnEnabled()) {
						StringBundler sb = new StringBundler();

						sb.append("Template ");
						sb.append(article.getTemplateId());
						sb.append(" is missing for article ");
						sb.append(article.getArticleId());
						sb.append(", skipping this article.");

						_log.warn(sb.toString());
					}

					return;
				}
			}

			parentTemplateId = existingTemplate.getTemplateId();
		}

		File smallFile = null;

		String smallImagePath = articleElement.attributeValue(
			"small-image-path");

		if (article.isSmallImage() && Validator.isNotNull(smallImagePath)) {
			byte[] bytes = portletDataContext.getZipEntryAsByteArray(
				smallImagePath);

			smallFile = File.createTempFile(
				String.valueOf(article.getSmallImageId()),
				StringPool.PERIOD + article.getSmallImageType());

			FileUtil.write(smallFile, bytes);
		}

		Map<String, byte[]> images = new HashMap<String, byte[]>();

		String imagePath = articleElement.attributeValue("image-path");

		if (portletDataContext.getBooleanParameter(_NAMESPACE, "images") &&
			Validator.isNotNull(imagePath)) {

			List<String> imageFiles = portletDataContext.getZipFolderEntries(
				imagePath);

			for (String imageFile : imageFiles) {
				String fileName = imageFile;

				if (fileName.contains(StringPool.SLASH)) {
					fileName = fileName.substring(
						fileName.lastIndexOf(CharPool.SLASH) + 1);
				}

				if (fileName.endsWith(".xml")) {
					continue;
				}

				int pos = fileName.lastIndexOf(CharPool.PERIOD);

				if (pos != -1) {
					fileName = fileName.substring(0, pos);
				}

				images.put(fileName, portletDataContext.getZipEntryAsByteArray(
					imageFile));
			}
		}

		String articleURL = null;

		boolean addGroupPermissions = creationStrategy.addGroupPermissions(
			portletDataContext, article);
		boolean addGuestPermissions = creationStrategy.addGuestPermissions(
			portletDataContext, article);

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			articleElement, article, _NAMESPACE);

		serviceContext.setAddGroupPermissions(addGroupPermissions);
		serviceContext.setAddGuestPermissions(addGuestPermissions);
		serviceContext.setAttribute("imported", Boolean.TRUE.toString());

		if (article.getStatus() != WorkflowConstants.STATUS_APPROVED) {
			serviceContext.setWorkflowAction(
				WorkflowConstants.ACTION_SAVE_DRAFT);
		}

		JournalArticle importedArticle = null;

		String articleResourceUuid = articleElement.attributeValue(
			"article-resource-uuid");

		if (portletDataContext.isDataStrategyMirror()) {
			JournalArticleResource articleResource =
				JournalArticleResourceUtil.fetchByUUID_G(
					articleResourceUuid, portletDataContext.getScopeGroupId());

			if (articleResource == null) {
				Group companyGroup = GroupLocalServiceUtil.getCompanyGroup(
					portletDataContext.getCompanyId());

				long companyGroupId = companyGroup.getGroupId();

				articleResource = JournalArticleResourceUtil.fetchByUUID_G(
					articleResourceUuid, companyGroupId);
			}

			serviceContext.setUuid(articleResourceUuid);

			JournalArticle existingArticle = null;

			if (articleResource != null) {
				try {
					existingArticle =
						JournalArticleLocalServiceUtil.getLatestArticle(
							articleResource.getResourcePrimKey(),
							WorkflowConstants.STATUS_ANY, false);
				}
				catch (NoSuchArticleException nsae) {
				}
			}

			if (existingArticle == null) {
				existingArticle = JournalArticleUtil.fetchByG_A_V(
					portletDataContext.getScopeGroupId(), newArticleId,
					article.getVersion());
			}

			if (existingArticle == null) {
				importedArticle = JournalArticleLocalServiceUtil.addArticle(
					userId, portletDataContext.getScopeGroupId(),
					article.getClassNameId(), structurePrimaryKey, articleId,
					autoArticleId, article.getVersion(), article.getTitleMap(),
					article.getDescriptionMap(), article.getContent(),
					article.getType(), parentStructureId, parentTemplateId,
					article.getLayoutUuid(), displayDateMonth, displayDateDay,
					displayDateYear, displayDateHour, displayDateMinute,
					expirationDateMonth, expirationDateDay, expirationDateYear,
					expirationDateHour, expirationDateMinute, neverExpire,
					reviewDateMonth, reviewDateDay, reviewDateYear,
					reviewDateHour, reviewDateMinute, neverReview,
					article.isIndexable(), article.isSmallImage(),
					article.getSmallImageURL(), smallFile, images, articleURL,
					serviceContext);
			}
			else {
				importedArticle = JournalArticleLocalServiceUtil.updateArticle(
					userId, existingArticle.getGroupId(),
					existingArticle.getArticleId(),
					existingArticle.getVersion(), article.getTitleMap(),
					article.getDescriptionMap(), article.getContent(),
					article.getType(), parentStructureId, parentTemplateId,
					existingArticle.getLayoutUuid(), displayDateMonth,
					displayDateDay, displayDateYear, displayDateHour,
					displayDateMinute, expirationDateMonth, expirationDateDay,
					expirationDateYear, expirationDateHour,
					expirationDateMinute, neverExpire, reviewDateMonth,
					reviewDateDay, reviewDateYear, reviewDateHour,
					reviewDateMinute, neverReview, article.isIndexable(),
					article.isSmallImage(), article.getSmallImageURL(),
					smallFile, images, articleURL, serviceContext);
			}
		}
		else {
			importedArticle = JournalArticleLocalServiceUtil.addArticle(
				userId, portletDataContext.getScopeGroupId(),
				article.getClassNameId(), structurePrimaryKey, articleId,
				autoArticleId, article.getVersion(), article.getTitleMap(),
				article.getDescriptionMap(), article.getContent(),
				article.getType(), parentStructureId, parentTemplateId,
				article.getLayoutUuid(), displayDateMonth, displayDateDay,
				displayDateYear, displayDateHour, displayDateMinute,
				expirationDateMonth, expirationDateDay, expirationDateYear,
				expirationDateHour, expirationDateMinute, neverExpire,
				reviewDateMonth, reviewDateDay, reviewDateYear, reviewDateHour,
				reviewDateMinute, neverReview, article.isIndexable(),
				article.isSmallImage(), article.getSmallImageURL(), smallFile,
				images, articleURL, serviceContext);
		}

		if (smallFile != null) {
			smallFile.delete();
		}

		portletDataContext.importClassedModel(
			article, importedArticle, _NAMESPACE);

		if (Validator.isNull(newArticleId)) {
			articleIds.put(
				article.getArticleId(), importedArticle.getArticleId());
		}

		articleElement.addAttribute(
			"imported-article-group-id",
			String.valueOf(importedArticle.getGroupId()));

		if (!articleId.equals(importedArticle.getArticleId())) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"An article with the ID " + articleId + " already " +
						"exists. The new generated ID is " +
							importedArticle.getArticleId());
			}
		}
	}

	public static void importFeed(
			PortletDataContext portletDataContext, Element feedElement)
		throws Exception {

		String path = feedElement.attributeValue("path");

		if (!portletDataContext.isPathNotProcessed(path)) {
			return;
		}

		JournalFeed feed = (JournalFeed)portletDataContext.getZipEntryAsObject(
			path);

		long userId = portletDataContext.getUserId(feed.getUserUuid());

		JournalCreationStrategy creationStrategy =
			JournalCreationStrategyFactory.getInstance();

		long authorId = creationStrategy.getAuthorUserId(
			portletDataContext, feed);

		if (authorId != JournalCreationStrategy.USE_DEFAULT_USER_ID_STRATEGY) {
			userId = authorId;
		}

		Group group = GroupLocalServiceUtil.getGroup(
			portletDataContext.getScopeGroupId());

		String newGroupFriendlyURL = group.getFriendlyURL().substring(1);

		String[] friendlyUrlParts = StringUtil.split(
			feed.getTargetLayoutFriendlyUrl(), '/');

		String oldGroupFriendlyURL = friendlyUrlParts[2];

		if (oldGroupFriendlyURL.equals("@data_handler_group_friendly_url@")) {
			feed.setTargetLayoutFriendlyUrl(
				StringUtil.replace(
					feed.getTargetLayoutFriendlyUrl(),
					"@data_handler_group_friendly_url@",
					newGroupFriendlyURL));
		}

		String feedId = feed.getFeedId();
		boolean autoFeedId = false;

		if ((Validator.isNumber(feedId)) ||
			(JournalFeedUtil.fetchByG_F(
				portletDataContext.getScopeGroupId(), feedId) != null)) {

			autoFeedId = true;
		}

		Map<String, String> structureIds =
			(Map<String, String>)portletDataContext.getNewPrimaryKeysMap(
				JournalStructure.class + ".structureId");

		String parentStructureId = MapUtil.getString(
			structureIds, feed.getStructureId(), feed.getStructureId());

		Map<String, String> templateIds =
			(Map<String, String>)portletDataContext.getNewPrimaryKeysMap(
				JournalTemplate.class + ".templateId");

		String parentTemplateId = MapUtil.getString(
			templateIds, feed.getTemplateId(), feed.getTemplateId());
		String parentRenderTemplateId = MapUtil.getString(
			templateIds, feed.getRendererTemplateId(),
			feed.getRendererTemplateId());

		boolean addGroupPermissions = creationStrategy.addGroupPermissions(
			portletDataContext, feed);
		boolean addGuestPermissions = creationStrategy.addGuestPermissions(
			portletDataContext, feed);

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			feedElement, feed, _NAMESPACE);

		serviceContext.setAddGroupPermissions(addGroupPermissions);
		serviceContext.setAddGuestPermissions(addGuestPermissions);

		JournalFeed importedFeed = null;

		try {
			if (portletDataContext.isDataStrategyMirror()) {
				JournalFeed existingFeed = JournalFeedUtil.fetchByUUID_G(
					feed.getUuid(), portletDataContext.getScopeGroupId());

				if (existingFeed == null) {
					serviceContext.setUuid(feed.getUuid());

					importedFeed = JournalFeedLocalServiceUtil.addFeed(
						userId, portletDataContext.getScopeGroupId(), feedId,
						autoFeedId, feed.getName(), feed.getDescription(),
						feed.getType(), parentStructureId, parentTemplateId,
						parentRenderTemplateId, feed.getDelta(),
						feed.getOrderByCol(), feed.getOrderByType(),
						feed.getTargetLayoutFriendlyUrl(),
						feed.getTargetPortletId(), feed.getContentField(),
						feed.getFeedType(), feed.getFeedVersion(),
						serviceContext);
				}
				else {
					importedFeed = JournalFeedLocalServiceUtil.updateFeed(
						existingFeed.getGroupId(), existingFeed.getFeedId(),
						feed.getName(), feed.getDescription(), feed.getType(),
						parentStructureId, parentTemplateId,
						parentRenderTemplateId, feed.getDelta(),
						feed.getOrderByCol(), feed.getOrderByType(),
						feed.getTargetLayoutFriendlyUrl(),
						feed.getTargetPortletId(), feed.getContentField(),
						feed.getFeedType(), feed.getFeedVersion(),
						serviceContext);
				}
			}
			else {
				importedFeed = JournalFeedLocalServiceUtil.addFeed(
					userId, portletDataContext.getScopeGroupId(), feedId,
					autoFeedId, feed.getName(), feed.getDescription(),
					feed.getType(), parentStructureId, parentTemplateId,
					parentRenderTemplateId, feed.getDelta(),
					feed.getOrderByCol(), feed.getOrderByType(),
					feed.getTargetLayoutFriendlyUrl(),
					feed.getTargetPortletId(), feed.getContentField(),
					feed.getFeedType(), feed.getFeedVersion(), serviceContext);
			}

			portletDataContext.importClassedModel(
				feed, importedFeed, _NAMESPACE);

			if (!feedId.equals(importedFeed.getFeedId())) {
				if (_log.isWarnEnabled()) {
					StringBundler sb = new StringBundler(5);

					sb.append("A feed with the ID ");
					sb.append(feedId);
					sb.append(" already exists. The new generated ID is ");
					sb.append(importedFeed.getFeedId());
					sb.append(".");

					_log.warn(sb.toString());
				}
			}
		}
		catch (FeedTargetLayoutFriendlyUrlException ftlfurle) {
			if (_log.isWarnEnabled()) {
				StringBundler sb = new StringBundler(6);

				sb.append("A feed with the ID ");
				sb.append(feedId);
				sb.append(" cannot be imported because layout with friendly ");
				sb.append("URL ");
				sb.append(feed.getTargetLayoutFriendlyUrl());
				sb.append(" does not exist");

				_log.warn(sb.toString());
			}
		}
	}

	public static String importReferencedContent(
			PortletDataContext portletDataContext, Element parentElement,
			String content)
		throws Exception {

		content = importDLFileEntries(
			portletDataContext, parentElement, content);

		Group group = GroupLocalServiceUtil.getGroup(
			portletDataContext.getScopeGroupId());

		content = StringUtil.replace(
			content, "@data_handler_group_friendly_url@",
			group.getFriendlyURL());

		content = importLinksToLayout(portletDataContext, content);

		return content;
	}

	public static void importReferencedData(
			PortletDataContext portletDataContext, Element entityElement)
		throws Exception {

		Element dlFoldersElement = entityElement.element("dl-folders");

		List<Element> dlFolderElements = Collections.emptyList();

		if (dlFoldersElement != null) {
			dlFolderElements = dlFoldersElement.elements("folder");
		}

		for (Element folderElement : dlFolderElements) {
			DLPortletDataHandlerImpl.importFolder(
				portletDataContext, folderElement);
		}

		Element dlFileEntriesElement = entityElement.element("dl-file-entries");

		List<Element> dlFileEntryElements = Collections.emptyList();

		if (dlFileEntriesElement != null) {
			dlFileEntryElements = dlFileEntriesElement.elements("file-entry");
		}

		for (Element fileEntryElement : dlFileEntryElements) {
			DLPortletDataHandlerImpl.importFileEntry(
				portletDataContext, fileEntryElement);
		}

		Element dlFileRanksElement = entityElement.element("dl-file-ranks");

		List<Element> dlFileRankElements = Collections.emptyList();

		if (dlFileRanksElement != null) {
			dlFileRankElements = dlFileRanksElement.elements("file-rank");
		}

		for (Element fileRankElement : dlFileRankElements) {
			DLPortletDataHandlerImpl.importFileRank(
				portletDataContext, fileRankElement);
		}
	}

	public static void importStructure(
			PortletDataContext portletDataContext, Element structureElement)
		throws Exception {

		String path = structureElement.attributeValue("path");

		if (!portletDataContext.isPathNotProcessed(path)) {
			return;
		}

		JournalStructure structure =
			(JournalStructure)portletDataContext.getZipEntryAsObject(path);

		long userId = portletDataContext.getUserId(structure.getUserUuid());

		JournalCreationStrategy creationStrategy =
			JournalCreationStrategyFactory.getInstance();

		long authorId = creationStrategy.getAuthorUserId(
			portletDataContext, structure);

		if (authorId != JournalCreationStrategy.USE_DEFAULT_USER_ID_STRATEGY) {
			userId = authorId;
		}

		String structureId = structure.getStructureId();
		boolean autoStructureId = false;

		if ((Validator.isNumber(structureId)) ||
			(JournalStructureUtil.fetchByG_S(
				portletDataContext.getScopeGroupId(), structureId) != null)) {

			autoStructureId = true;
		}

		Map<String, String> structureIds =
			(Map<String, String>)portletDataContext.getNewPrimaryKeysMap(
				JournalStructure.class + ".structureId");

		String parentStructureId = MapUtil.getString(
			structureIds, structure.getParentStructureId(),
			structure.getParentStructureId());

		Document document = structureElement.getDocument();

		Element rootElement = document.getRootElement();

		Element parentStructureElement = (Element)rootElement.selectSingleNode(
			"./structures/structure[@structure-id='" + parentStructureId +
				"']");

		String parentStructureUuid = GetterUtil.getString(
			structureElement.attributeValue("parent-structure-uuid"));

		if ((parentStructureElement != null) &&
			Validator.isNotNull(parentStructureId)) {

			importStructure(portletDataContext, parentStructureElement);

			parentStructureId = structureIds.get(parentStructureId);
		}
		else if (Validator.isNotNull(parentStructureUuid)) {
			JournalStructure parentStructure =
				JournalStructureLocalServiceUtil.
					getJournalStructureByUuidAndGroupId(
						parentStructureUuid, portletDataContext.getGroupId());

			parentStructureId = parentStructure.getStructureId();
		}

		boolean addGroupPermissions = creationStrategy.addGroupPermissions(
			portletDataContext, structure);
		boolean addGuestPermissions = creationStrategy.addGuestPermissions(
			portletDataContext, structure);

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			structureElement, structure, _NAMESPACE);

		serviceContext.setAddGroupPermissions(addGroupPermissions);
		serviceContext.setAddGuestPermissions(addGuestPermissions);

		JournalStructure importedStructure = null;

		if (portletDataContext.isDataStrategyMirror()) {
			JournalStructure existingStructure =
				JournalStructureUtil.fetchByUUID_G(
					structure.getUuid(), portletDataContext.getScopeGroupId());

			if (existingStructure == null) {
				Group companyGroup = GroupLocalServiceUtil.getCompanyGroup(
					portletDataContext.getCompanyId());

				long companyGroupId = companyGroup.getGroupId();

				existingStructure = JournalStructureUtil.fetchByUUID_G(
					structure.getUuid(), companyGroupId);
			}

			if (existingStructure == null) {
				serviceContext.setUuid(structure.getUuid());

				importedStructure =
					JournalStructureLocalServiceUtil.addStructure(
						userId, portletDataContext.getScopeGroupId(),
						structureId, autoStructureId, parentStructureId,
						structure.getNameMap(), structure.getDescriptionMap(),
						structure.getXsd(), serviceContext);
			}
			else {
				importedStructure =
					JournalStructureLocalServiceUtil.updateStructure(
						existingStructure.getGroupId(),
						existingStructure.getStructureId(), parentStructureId,
						structure.getNameMap(), structure.getDescriptionMap(),
						structure.getXsd(), serviceContext);
			}
		}
		else {
			importedStructure = JournalStructureLocalServiceUtil.addStructure(
				userId, portletDataContext.getScopeGroupId(), structureId,
				autoStructureId, parentStructureId, structure.getNameMap(),
				structure.getDescriptionMap(), structure.getXsd(),
				serviceContext);
		}

		portletDataContext.importClassedModel(
			structure, importedStructure, _NAMESPACE);

		structureIds.put(structureId, importedStructure.getStructureId());

		if (!structureId.equals(importedStructure.getStructureId())) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"A structure with the ID " + structureId + " already " +
						"exists. The new generated ID is " +
							importedStructure.getStructureId());
			}
		}
	}

	public static void importTemplate(
			PortletDataContext portletDataContext, Element templateElement)
		throws Exception {

		String path = templateElement.attributeValue("path");

		if (!portletDataContext.isPathNotProcessed(path)) {
			return;
		}

		JournalTemplate template =
			(JournalTemplate)portletDataContext.getZipEntryAsObject(path);

		long userId = portletDataContext.getUserId(template.getUserUuid());

		JournalCreationStrategy creationStrategy =
			JournalCreationStrategyFactory.getInstance();

		long authorId = creationStrategy.getAuthorUserId(
			portletDataContext, template);

		if (authorId != JournalCreationStrategy.USE_DEFAULT_USER_ID_STRATEGY) {
			userId = authorId;
		}

		String templateId = template.getTemplateId();
		boolean autoTemplateId = false;

		if ((Validator.isNumber(templateId)) ||
			(JournalTemplateUtil.fetchByG_T(
				portletDataContext.getScopeGroupId(), templateId) != null)) {

			autoTemplateId = true;
		}

		Map<String, String> structureIds =
			(Map<String, String>)portletDataContext.getNewPrimaryKeysMap(
				JournalStructure.class + ".structureId");

		String parentStructureId = MapUtil.getString(
			structureIds, template.getStructureId(), template.getStructureId());

		String xsl = template.getXsl();

		xsl = importDLFileEntries(portletDataContext, templateElement, xsl);

		Group group = GroupLocalServiceUtil.getGroup(
			portletDataContext.getScopeGroupId());

		xsl = StringUtil.replace(
			xsl, "@data_handler_group_friendly_url@", group.getFriendlyURL());

		template.setXsl(xsl);

		boolean formatXsl = false;

		boolean addGroupPermissions = creationStrategy.addGroupPermissions(
			portletDataContext, template);
		boolean addGuestPermissions = creationStrategy.addGuestPermissions(
			portletDataContext, template);

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			templateElement, template, _NAMESPACE);

		serviceContext.setAddGroupPermissions(addGroupPermissions);
		serviceContext.setAddGuestPermissions(addGuestPermissions);

		File smallFile = null;

		String smallImagePath = templateElement.attributeValue(
			"small-image-path");

		if (template.isSmallImage() && Validator.isNotNull(smallImagePath)) {
			if (smallImagePath.endsWith(StringPool.PERIOD)) {
				smallImagePath += template.getSmallImageType();
			}

			byte[] bytes = portletDataContext.getZipEntryAsByteArray(
				smallImagePath);

			if (bytes != null) {
				smallFile = File.createTempFile(
					String.valueOf(template.getSmallImageId()),
					StringPool.PERIOD + template.getSmallImageType());

				FileUtil.write(smallFile, bytes);
			}
		}

		JournalTemplate importedTemplate = null;

		if (portletDataContext.isDataStrategyMirror()) {
			JournalTemplate existingTemplate =
				JournalTemplateUtil.fetchByUUID_G(
					template.getUuid(), portletDataContext.getScopeGroupId());

			if (existingTemplate == null) {
				Group companyGroup = GroupLocalServiceUtil.getCompanyGroup(
					portletDataContext.getCompanyId());

				long companyGroupId = companyGroup.getGroupId();

				existingTemplate = JournalTemplateUtil.fetchByUUID_G(
					template.getUuid(), companyGroupId);
			}

			if (existingTemplate == null) {
				serviceContext.setUuid(template.getUuid());

				importedTemplate = JournalTemplateLocalServiceUtil.addTemplate(
					userId, portletDataContext.getScopeGroupId(), templateId,
					autoTemplateId, parentStructureId, template.getNameMap(),
					template.getDescriptionMap(), template.getXsl(), formatXsl,
					template.getLangType(), template.getCacheable(),
					template.isSmallImage(), template.getSmallImageURL(),
					smallFile, serviceContext);
			}
			else {
				importedTemplate =
					JournalTemplateLocalServiceUtil.updateTemplate(
						existingTemplate.getGroupId(),
						existingTemplate.getTemplateId(),
						existingTemplate.getStructureId(),
						template.getNameMap(), template.getDescriptionMap(),
						template.getXsl(), formatXsl, template.getLangType(),
						template.getCacheable(), template.isSmallImage(),
						template.getSmallImageURL(), smallFile, serviceContext);
			}
		}
		else {
			importedTemplate = JournalTemplateLocalServiceUtil.addTemplate(
				userId, portletDataContext.getScopeGroupId(), templateId,
				autoTemplateId, parentStructureId, template.getNameMap(),
				template.getDescriptionMap(), template.getXsl(), formatXsl,
				template.getLangType(), template.getCacheable(),
				template.isSmallImage(), template.getSmallImageURL(), smallFile,
				serviceContext);
		}

		if (smallFile != null) {
			smallFile.delete();
		}

		portletDataContext.importClassedModel(
			template, importedTemplate, _NAMESPACE);

		Map<String, String> templateIds =
			(Map<String, String>)portletDataContext.getNewPrimaryKeysMap(
				JournalTemplate.class + ".templateId");

		templateIds.put(
			template.getTemplateId(), importedTemplate.getTemplateId());

		if (!templateId.equals(importedTemplate.getTemplateId())) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"A template with the ID " + templateId + " already " +
						"exists. The new generated ID is " +
							importedTemplate.getTemplateId());
			}
		}
	}

	protected static String exportDLFileEntries(
			PortletDataContext portletDataContext,
			Element dlFileEntryTypesElement, Element dlFoldersElement,
			Element dlFileEntriesElement, Element dlFileRanksElement,
			Element entityElement, String content, boolean checkDateRange)
		throws Exception {

		Group group = GroupLocalServiceUtil.getGroup(
			portletDataContext.getGroupId());

		if (group.isStagingGroup()) {
			group = group.getLiveGroup();
		}

		if (group.isStaged() && !group.isStagedRemotely() &&
			!group.isStagedPortlet(PortletKeys.DOCUMENT_LIBRARY)) {

			return content;
		}

		StringBuilder sb = new StringBuilder(content);

		int beginPos = content.length();
		int currentLocation = -1;

		while (true) {
			currentLocation = content.lastIndexOf(
				"/c/document_library/get_file?", beginPos);

			if (currentLocation == -1) {
				currentLocation = content.lastIndexOf("/documents/", beginPos);
			}

			if (currentLocation == -1) {
				return sb.toString();
			}

			beginPos = currentLocation;

			int endPos1 = content.indexOf(CharPool.APOSTROPHE, beginPos);
			int endPos2 = content.indexOf(CharPool.CLOSE_BRACKET, beginPos);
			int endPos3 = content.indexOf(CharPool.CLOSE_CURLY_BRACE, beginPos);
			int endPos4 = content.indexOf(CharPool.CLOSE_PARENTHESIS, beginPos);
			int endPos5 = content.indexOf(CharPool.LESS_THAN, beginPos);
			int endPos6 = content.indexOf(CharPool.QUOTE, beginPos);
			int endPos7 = content.indexOf(CharPool.SPACE, beginPos);

			int endPos = endPos1;

			if ((endPos == -1) || ((endPos2 != -1) && (endPos2 < endPos))) {
				endPos = endPos2;
			}

			if ((endPos == -1) || ((endPos3 != -1) && (endPos3 < endPos))) {
				endPos = endPos3;
			}

			if ((endPos == -1) || ((endPos4 != -1) && (endPos4 < endPos))) {
				endPos = endPos4;
			}

			if ((endPos == -1) || ((endPos5 != -1) && (endPos5 < endPos))) {
				endPos = endPos5;
			}

			if ((endPos == -1) || ((endPos6 != -1) && (endPos6 < endPos))) {
				endPos = endPos6;
			}

			if ((endPos == -1) || ((endPos7 != -1) && (endPos7 < endPos))) {
				endPos = endPos7;
			}

			if ((beginPos == -1) || (endPos == -1)) {
				break;
			}

			try {
				String oldParameters = content.substring(beginPos, endPos);

				while (oldParameters.contains(StringPool.AMPERSAND_ENCODED)) {
					oldParameters = oldParameters.replace(
						StringPool.AMPERSAND_ENCODED, StringPool.AMPERSAND);
				}

				Map<String, String[]> map = new HashMap<String, String[]>();

				if (oldParameters.startsWith("/documents/")) {
					String[] pathArray = oldParameters.split(StringPool.SLASH);

					map.put("groupId", new String[] {pathArray[2]});

					if (pathArray.length == 4) {
						map.put("uuid", new String[] {pathArray[3]});
					}
					else if (pathArray.length > 4) {
						map.put("folderId", new String[] {pathArray[3]});

						String name = HttpUtil.decodeURL(pathArray[4]);

						int pos = name.indexOf(StringPool.QUESTION);

						if (pos != -1) {
							name = name.substring(0, pos);
						}

						map.put("name", new String[] {name});
					}
				}
				else {
					oldParameters = oldParameters.substring(
						oldParameters.indexOf(CharPool.QUESTION) + 1);

					map = HttpUtil.parameterMapFromString(oldParameters);
				}

				FileEntry fileEntry = null;

				String uuid = MapUtil.getString(map, "uuid");

				if (Validator.isNotNull(uuid)) {
					String groupIdString = MapUtil.getString(map, "groupId");

					long groupId = GetterUtil.getLong(groupIdString);

					if (groupIdString.equals("@group_id@")) {
						groupId = portletDataContext.getScopeGroupId();
					}

					fileEntry =
						DLAppLocalServiceUtil.getFileEntryByUuidAndGroupId(
							uuid, groupId);
				}
				else {
					String folderIdString = MapUtil.getString(map, "folderId");

					if (Validator.isNotNull(folderIdString)) {
						long folderId = GetterUtil.getLong(folderIdString);
						String name = MapUtil.getString(map, "name");

						String groupIdString = MapUtil.getString(
							map, "groupId");

						long groupId = GetterUtil.getLong(groupIdString);

						if (groupIdString.equals("@group_id@")) {
							groupId = portletDataContext.getScopeGroupId();
						}

						fileEntry = DLAppLocalServiceUtil.getFileEntry(
							groupId, folderId, name);
					}
				}

				if (fileEntry == null) {
					beginPos--;

					continue;
				}

				String path = DLPortletDataHandlerImpl.getFileEntryPath(
					portletDataContext, fileEntry);

				Element dlReferenceElement = entityElement.addElement(
					"dl-reference");

				dlReferenceElement.addAttribute("path", path);

				DLPortletDataHandlerImpl.exportFileEntry(
					portletDataContext, dlFileEntryTypesElement,
					dlFoldersElement, dlFileEntriesElement, dlFileRanksElement,
					fileEntry, checkDateRange);

				String dlReference = "[$dl-reference=" + path + "$]";

				sb.replace(beginPos, endPos, dlReference);
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(e);
				}
			}

			beginPos--;
		}

		return sb.toString();
	}

	protected static void exportFeed(
			PortletDataContext portletDataContext, Element feedsElement,
			JournalFeed feed)
		throws Exception {

		if (!portletDataContext.isWithinDateRange(feed.getModifiedDate())) {
			return;
		}

		String path = getFeedPath(portletDataContext, feed);

		if (!portletDataContext.isPathNotProcessed(path)) {
			return;
		}

		feed = (JournalFeed)feed.clone();

		Element feedElement = feedsElement.addElement("feed");

		Group group = GroupLocalServiceUtil.getGroup(
			portletDataContext.getScopeGroupId());

		String newGroupFriendlyURL = group.getFriendlyURL().substring(1);

		String[] friendlyUrlParts = StringUtil.split(
			feed.getTargetLayoutFriendlyUrl(), '/');

		String oldGroupFriendlyURL = friendlyUrlParts[2];

		if (newGroupFriendlyURL.equals(oldGroupFriendlyURL)) {
			String targetLayoutFriendlyUrl = StringUtil.replaceFirst(
				feed.getTargetLayoutFriendlyUrl(),
				StringPool.SLASH + newGroupFriendlyURL + StringPool.SLASH,
				"/@data_handler_group_friendly_url@/");

			feed.setTargetLayoutFriendlyUrl(targetLayoutFriendlyUrl);
		}

		portletDataContext.addClassedModel(feedElement, path, feed, _NAMESPACE);
	}

	protected static String exportLayoutFriendlyURLs(
		PortletDataContext portletDataContext, String content) {

		Group group = null;

		try {
			group = GroupLocalServiceUtil.getGroup(
				portletDataContext.getScopeGroupId());
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e);
			}

			return content;
		}

		StringBuilder sb = new StringBuilder(content);

		String friendlyURLPrivateGroupPath =
			PropsValues.LAYOUT_FRIENDLY_URL_PRIVATE_GROUP_SERVLET_MAPPING;
		String friendlyURLPrivateUserPath =
			PropsValues.LAYOUT_FRIENDLY_URL_PRIVATE_USER_SERVLET_MAPPING;
		String friendlyURLPublicPath =
			PropsValues.LAYOUT_FRIENDLY_URL_PUBLIC_SERVLET_MAPPING;

		String href = "href=";

		int beginPos = content.length();

		while (true) {
			int hrefLength = href.length();

			beginPos = content.lastIndexOf(href, beginPos);

			if (beginPos == -1) {
				break;
			}

			char c = content.charAt(beginPos + hrefLength);

			if ((c == CharPool.APOSTROPHE) || (c == CharPool.QUOTE)) {
				hrefLength++;
			}

			int endPos1 = content.indexOf(
				CharPool.APOSTROPHE, beginPos + hrefLength);
			int endPos2 = content.indexOf(
				CharPool.CLOSE_BRACKET, beginPos + hrefLength);
			int endPos3 = content.indexOf(
				CharPool.CLOSE_PARENTHESIS, beginPos + hrefLength);
			int endPos4 = content.indexOf(
				CharPool.LESS_THAN, beginPos + hrefLength);
			int endPos5 = content.indexOf(
				CharPool.QUOTE, beginPos + hrefLength);
			int endPos6 = content.indexOf(
				CharPool.SPACE, beginPos + hrefLength);

			int endPos = endPos1;

			if ((endPos == -1) || ((endPos2 != -1) && (endPos2 < endPos))) {
				endPos = endPos2;
			}

			if ((endPos == -1) || ((endPos3 != -1) && (endPos3 < endPos))) {
				endPos = endPos3;
			}

			if ((endPos == -1) || ((endPos4 != -1) && (endPos4 < endPos))) {
				endPos = endPos4;
			}

			if ((endPos == -1) || ((endPos5 != -1) && (endPos5 < endPos))) {
				endPos = endPos5;
			}

			if ((endPos == -1) || ((endPos6 != -1) && (endPos6 < endPos))) {
				endPos = endPos6;
			}

			if (endPos == -1) {
				beginPos--;

				continue;
			}

			String url = content.substring(beginPos + hrefLength, endPos);

			if (!url.startsWith(friendlyURLPrivateGroupPath) &&
				!url.startsWith(friendlyURLPrivateUserPath) &&
				!url.startsWith(friendlyURLPublicPath)) {

				beginPos--;

				continue;
			}

			int beginGroupPos = content.indexOf(
				CharPool.SLASH, beginPos + hrefLength + 1);

			if (beginGroupPos == -1) {
				beginPos--;

				continue;
			}

			int endGroupPos = content.indexOf(
				CharPool.SLASH, beginGroupPos + 1);

			if (endGroupPos == -1) {
				beginPos--;

				continue;
			}

			String groupFriendlyURL = content.substring(
				beginGroupPos, endGroupPos);

			if (groupFriendlyURL.equals(group.getFriendlyURL())) {
				sb.replace(
					beginGroupPos, endGroupPos,
					"@data_handler_group_friendly_url@");
			}

			beginPos--;
		}

		return sb.toString();
	}

	protected static String exportLinksToLayout(
			PortletDataContext portletDataContext, String content)
		throws Exception {

		List<String> oldLinksToLayout = new ArrayList<String>();
		List<String> newLinksToLayout = new ArrayList<String>();

		Matcher matcher = _exportLinksToLayoutPattern.matcher(content);

		while (matcher.find()) {
			long layoutId = GetterUtil.getLong(matcher.group(1));

			String type = matcher.group(2);

			boolean privateLayout = type.startsWith("private");

			try {
				Layout layout = LayoutLocalServiceUtil.getLayout(
					portletDataContext.getScopeGroupId(), privateLayout,
					layoutId);

				String oldLinkToLayout = matcher.group(0);

				StringBundler sb = new StringBundler(5);

				sb.append(type);
				sb.append(StringPool.AT);
				sb.append(layout.getUuid());
				sb.append(StringPool.AT);
				sb.append(layout.getFriendlyURL());

				String newLinkToLayout = StringUtil.replace(
					oldLinkToLayout, type, sb.toString());

				oldLinksToLayout.add(oldLinkToLayout);
				newLinksToLayout.add(newLinkToLayout);
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to get layout with id " + layoutId +
							" in group " + portletDataContext.getScopeGroupId(),
						e);
				}
			}
		}

		content = StringUtil.replace(
			content, ArrayUtil.toStringArray(oldLinksToLayout.toArray()),
			ArrayUtil.toStringArray(newLinksToLayout.toArray()));

		return content;
	}

	protected static void exportStructure(
			PortletDataContext portletDataContext, Element structuresElement,
			JournalStructure structure)
		throws Exception {

		String path = getStructurePath(portletDataContext, structure);

		if (!portletDataContext.isPathNotProcessed(path)) {
			return;
		}

		Element structureElement = structuresElement.addElement("structure");

		String parentStructureId = structure.getParentStructureId();

		if (Validator.isNotNull(parentStructureId)) {
			try {
				JournalStructure parentStructure =
					JournalStructureLocalServiceUtil.getStructure(
						structure.getGroupId(), parentStructureId);

				structureElement.addAttribute(
					"parent-structure-uuid", parentStructure.getUuid());

				exportStructure(
					portletDataContext, structuresElement, parentStructure);
			}
			catch (NoSuchStructureException nsse) {
			}
		}

		portletDataContext.addClassedModel(
			structureElement, path, structure, _NAMESPACE);
	}

	protected static void exportTemplate(
			PortletDataContext portletDataContext, Element templatesElement,
			Element dlFileEntryTypesElement, Element dlFoldersElement,
			Element dlFileEntriesElement, Element dlFileRanksElement,
			JournalTemplate template)
		throws Exception {

		String path = getTemplatePath(portletDataContext, template);

		if (!portletDataContext.isPathNotProcessed(path)) {
			return;
		}

		// Clone this template to make sure changes to its content are never
		// persisted

		template = (JournalTemplate)template.clone();

		Element templateElement = templatesElement.addElement("template");

		if (template.isSmallImage()) {
			String smallImagePath = getTemplateSmallImagePath(
				portletDataContext, template);

			templateElement.addAttribute("small-image-path", smallImagePath);

			Image smallImage = ImageUtil.fetchByPrimaryKey(
				template.getSmallImageId());

			template.setSmallImageType(smallImage.getType());

			portletDataContext.addZipEntry(
				smallImagePath, smallImage.getTextObj());
		}

		if (portletDataContext.getBooleanParameter(
				_NAMESPACE, "embedded-assets")) {

			String content = exportReferencedContent(
				portletDataContext, dlFileEntryTypesElement, dlFoldersElement,
				dlFileEntriesElement, dlFileRanksElement, templateElement,
				template.getXsl());

			template.setXsl(content);
		}

		portletDataContext.addClassedModel(
			templateElement, path, template, _NAMESPACE);
	}

	protected static String getArticleImagePath(
			PortletDataContext portletDataContext, JournalArticle article)
		throws Exception {

		StringBundler sb = new StringBundler(6);

		sb.append(portletDataContext.getPortletPath(PortletKeys.JOURNAL));
		sb.append("/articles/");
		sb.append(article.getArticleResourceUuid());
		sb.append(StringPool.SLASH);
		sb.append(article.getVersion());
		sb.append(StringPool.SLASH);

		return sb.toString();
	}

	protected static String getArticleImagePath(
			PortletDataContext portletDataContext, JournalArticle article,
			JournalArticleImage articleImage, Image image)
		throws Exception {

		StringBundler sb = new StringBundler(13);

		sb.append(portletDataContext.getPortletPath(PortletKeys.JOURNAL));
		sb.append("/articles/");
		sb.append(article.getArticleResourceUuid());
		sb.append(StringPool.SLASH);
		sb.append(article.getVersion());
		sb.append(StringPool.SLASH);
		sb.append(articleImage.getElInstanceId());
		sb.append(StringPool.UNDERLINE);
		sb.append(articleImage.getElName());

		if (Validator.isNotNull(articleImage.getLanguageId())) {
			sb.append(StringPool.UNDERLINE);
			sb.append(articleImage.getLanguageId());
		}

		sb.append(StringPool.PERIOD);
		sb.append(image.getType());

		return sb.toString();
	}

	protected static String getArticleSmallImagePath(
			PortletDataContext portletDataContext, JournalArticle article)
		throws Exception {

		StringBundler sb = new StringBundler(6);

		sb.append(portletDataContext.getPortletPath(PortletKeys.JOURNAL));
		sb.append("/articles/");
		sb.append(article.getArticleResourceUuid());
		sb.append("/thumbnail");
		sb.append(StringPool.PERIOD);
		sb.append(article.getSmallImageType());

		return sb.toString();
	}

	protected static String getFeedPath(
		PortletDataContext portletDataContext, JournalFeed feed) {

		StringBundler sb = new StringBundler(4);

		sb.append(portletDataContext.getPortletPath(PortletKeys.JOURNAL));
		sb.append("/feeds/");
		sb.append(feed.getUuid());
		sb.append(".xml");

		return sb.toString();
	}

	protected static String getStructurePath(
		PortletDataContext portletDataContext, JournalStructure structure) {

		StringBundler sb = new StringBundler(4);

		sb.append(portletDataContext.getPortletPath(PortletKeys.JOURNAL));
		sb.append("/structures/");
		sb.append(structure.getUuid());
		sb.append(".xml");

		return sb.toString();
	}

	protected static String getTemplatePath(
		PortletDataContext portletDataContext, JournalTemplate template) {

		StringBundler sb = new StringBundler(4);

		sb.append(portletDataContext.getPortletPath(PortletKeys.JOURNAL));
		sb.append("/templates/");
		sb.append(template.getUuid());
		sb.append(".xml");

		return sb.toString();
	}

	protected static String getTemplateSmallImagePath(
			PortletDataContext portletDataContext, JournalTemplate template)
		throws Exception {

		StringBundler sb = new StringBundler(5);

		sb.append(portletDataContext.getPortletPath(PortletKeys.JOURNAL));
		sb.append("/templates/thumbnail-");
		sb.append(template.getUuid());
		sb.append(StringPool.PERIOD);
		sb.append(template.getSmallImageType());

		return sb.toString();
	}

	protected static String importDLFileEntries(
			PortletDataContext portletDataContext, Element parentElement,
			String content)
		throws Exception {

		List<Element> dlReferenceElements = parentElement.elements(
			"dl-reference");

		for (Element dlReferenceElement : dlReferenceElements) {
			String dlReferencePath = dlReferenceElement.attributeValue("path");

			FileEntry fileEntry = null;

			try {
				fileEntry = (FileEntry)portletDataContext.getZipEntryAsObject(
					dlReferencePath);
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(e);
				}
			}

			if (fileEntry == null) {
				continue;
			}

			fileEntry = FileEntryUtil.fetchByUUID_R(
				fileEntry.getUuid(), portletDataContext.getGroupId());

			if (fileEntry == null) {
				continue;
			}

			String dlReference = "[$dl-reference=" + dlReferencePath + "$]";

			StringBundler sb = new StringBundler(6);

			sb.append("/documents/");
			sb.append(portletDataContext.getScopeGroupId());
			sb.append(StringPool.SLASH);
			sb.append(fileEntry.getFolderId());
			sb.append(StringPool.SLASH);
			sb.append(
				HttpUtil.encodeURL(
					HtmlUtil.unescape(fileEntry.getTitle()), true));

			content = StringUtil.replace(content, dlReference, sb.toString());
		}

		return content;
	}

	protected static String importLinksToLayout(
			PortletDataContext portletDataContext, String content)
		throws Exception {

		List<String> oldLinksToLayout = new ArrayList<String>();
		List<String> newLinksToLayout = new ArrayList<String>();

		Matcher matcher = _importLinksToLayoutPattern.matcher(content);

		while (matcher.find()) {
			long oldLayoutId = GetterUtil.getLong(matcher.group(1));

			long newLayoutId = oldLayoutId;

			String type = matcher.group(2);

			boolean privateLayout = type.startsWith("private");

			String layoutUuid = matcher.group(3);

			String friendlyURL = matcher.group(4);

			try {
				Layout layout = LayoutUtil.fetchByUUID_G(
					layoutUuid, portletDataContext.getScopeGroupId());

				if (layout == null) {
					layout = LayoutUtil.fetchByG_P_F(
						portletDataContext.getScopeGroupId(), privateLayout,
						friendlyURL);
				}

				if (layout == null) {
					layout = LayoutUtil.fetchByG_P_L(
						portletDataContext.getScopeGroupId(), privateLayout,
						oldLayoutId);
				}

				if (layout == null) {
					if (_log.isWarnEnabled()) {
						StringBundler sb = new StringBundler(9);

						sb.append("Unable to get layout with UUID ");
						sb.append(layoutUuid);
						sb.append(", friendly URL ");
						sb.append(friendlyURL);
						sb.append(", or ");
						sb.append("layoutId ");
						sb.append(oldLayoutId);
						sb.append(" in group ");
						sb.append(portletDataContext.getScopeGroupId());

						_log.warn(sb.toString());
					}
				}
				else {
					newLayoutId = layout.getLayoutId();
				}
			}
			catch (SystemException e) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to get layout in group " +
							portletDataContext.getScopeGroupId(), e);
				}
			}

			String oldLinkToLayout = matcher.group(0);

			StringBundler sb = new StringBundler(4);

			sb.append(StringPool.AT);
			sb.append(layoutUuid);
			sb.append(StringPool.AT);
			sb.append(friendlyURL);

			String newLinkToLayout = StringUtil.replace(
				oldLinkToLayout,
				new String[] {sb.toString(), String.valueOf(oldLayoutId)},
				new String[] {StringPool.BLANK, String.valueOf(newLayoutId)});

			oldLinksToLayout.add(oldLinkToLayout);
			newLinksToLayout.add(newLinkToLayout);
		}

		content = StringUtil.replace(
			content, ArrayUtil.toStringArray(oldLinksToLayout.toArray()),
			ArrayUtil.toStringArray(newLinksToLayout.toArray()));

		return content;
	}

	@Override
	public PortletDataHandlerControl[] getExportControls() {
		return new PortletDataHandlerControl[] {
			_articles, _structuresTemplatesAndFeeds, _embeddedAssets, _images,
			_categories, _comments, _ratings, _tags
		};
	}

	@Override
	public PortletDataHandlerControl[] getImportControls() {
		return new PortletDataHandlerControl[] {
			_articles, _structuresTemplatesAndFeeds, _images, _categories,
			_comments, _ratings, _tags
		};
	}

	@Override
	public boolean isAlwaysExportable() {
		return _ALWAYS_EXPORTABLE;
	}

	@Override
	public boolean isPublishToLiveByDefault() {
		return PropsValues.JOURNAL_PUBLISH_TO_LIVE_BY_DEFAULT;
	}

	@Override
	protected PortletPreferences doDeleteData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		if (!portletDataContext.addPrimaryKey(
				JournalPortletDataHandlerImpl.class, "deleteData")) {

			JournalArticleLocalServiceUtil.deleteArticles(
				portletDataContext.getScopeGroupId());

			JournalTemplateLocalServiceUtil.deleteTemplates(
				portletDataContext.getScopeGroupId());

			JournalStructureLocalServiceUtil.deleteStructures(
				portletDataContext.getScopeGroupId());
		}

		return portletPreferences;
	}

	@Override
	protected String doExportData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		portletDataContext.addPermissions(
			"com.liferay.portlet.journal",
			portletDataContext.getScopeGroupId());

		Document document = SAXReaderUtil.createDocument();

		Element rootElement = document.addElement("journal-data");

		rootElement.addAttribute(
			"group-id", String.valueOf(portletDataContext.getScopeGroupId()));

		Element structuresElement = rootElement.addElement("structures");

		List<JournalStructure> structures = JournalStructureUtil.findByGroupId(
			portletDataContext.getScopeGroupId(), QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, new StructurePKComparator(true));

		for (JournalStructure structure : structures) {
			if (portletDataContext.isWithinDateRange(
					structure.getModifiedDate())) {

				exportStructure(
					portletDataContext, structuresElement, structure);
			}
		}

		Element templatesElement = rootElement.addElement("templates");
		Element dlFileEntryTypesElement = rootElement.addElement(
			"dl-file-entry-types");
		Element dlFoldersElement = rootElement.addElement("dl-folders");
		Element dlFilesElement = rootElement.addElement("dl-file-entries");
		Element dlFileRanksElement = rootElement.addElement("dl-file-ranks");

		List<JournalTemplate> templates = JournalTemplateUtil.findByGroupId(
			portletDataContext.getScopeGroupId());

		for (JournalTemplate template : templates) {
			if (portletDataContext.isWithinDateRange(
					template.getModifiedDate())) {

				exportTemplate(
					portletDataContext, templatesElement,
					dlFileEntryTypesElement, dlFoldersElement, dlFilesElement,
					dlFileRanksElement, template);
			}
		}

		Element feedsElement = rootElement.addElement("feeds");

		List<JournalFeed> feeds = JournalFeedUtil.findByGroupId(
			portletDataContext.getScopeGroupId());

		for (JournalFeed feed : feeds) {
			if (portletDataContext.isWithinDateRange(feed.getModifiedDate())) {
				exportFeed(portletDataContext, feedsElement, feed);
			}
		}

		Element articlesElement = rootElement.addElement("articles");

		if (portletDataContext.getBooleanParameter(_NAMESPACE, "articles")) {
			List<JournalArticle> articles = JournalArticleUtil.findByGroupId(
				portletDataContext.getScopeGroupId(), QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, new ArticleIDComparator(true));

			for (JournalArticle article : articles) {
				exportArticle(
					portletDataContext, articlesElement, structuresElement,
					templatesElement, dlFileEntryTypesElement, dlFoldersElement,
					dlFilesElement, dlFileRanksElement, article, true);
			}
		}

		return document.formattedString();
	}

	@Override
	protected PortletPreferences doImportData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences, String data)
		throws Exception {

		portletDataContext.importPermissions(
			"com.liferay.portlet.journal",
			portletDataContext.getSourceGroupId(),
			portletDataContext.getScopeGroupId());

		Document document = SAXReaderUtil.read(data);

		Element rootElement = document.getRootElement();

		importReferencedData(portletDataContext, rootElement);

		Element structuresElement = rootElement.element("structures");

		List<Element> structureElements = structuresElement.elements(
			"structure");

		for (Element structureElement : structureElements) {
			importStructure(portletDataContext, structureElement);
		}

		Element templatesElement = rootElement.element("templates");

		List<Element> templateElements = templatesElement.elements("template");

		for (Element templateElement : templateElements) {
			importTemplate(portletDataContext, templateElement);
		}

		Element feedsElement = rootElement.element("feeds");

		List<Element> feedElements = feedsElement.elements("feed");

		for (Element feedElement : feedElements) {
			importFeed(portletDataContext, feedElement);
		}

		if (portletDataContext.getBooleanParameter(_NAMESPACE, "articles")) {
			Element articlesElement = rootElement.element("articles");

			List<Element> articleElements = articlesElement.elements("article");

			for (Element articleElement : articleElements) {
				importArticle(portletDataContext, articleElement);
			}
		}

		return portletPreferences;
	}

	private static final boolean _ALWAYS_EXPORTABLE = true;

	private static final String _NAMESPACE = "journal";

	private static Log _log = LogFactoryUtil.getLog(
		JournalPortletDataHandlerImpl.class);

	private static PortletDataHandlerBoolean _categories =
		new PortletDataHandlerBoolean(_NAMESPACE, "categories");

	private static PortletDataHandlerBoolean _comments =
		new PortletDataHandlerBoolean(_NAMESPACE, "comments");

	private static PortletDataHandlerBoolean _embeddedAssets =
		new PortletDataHandlerBoolean(_NAMESPACE, "embedded-assets");

	private static PortletDataHandlerBoolean _images =
		new PortletDataHandlerBoolean(_NAMESPACE, "images");

	private static PortletDataHandlerBoolean _ratings =
		new PortletDataHandlerBoolean(_NAMESPACE, "ratings");

	private static PortletDataHandlerBoolean
		_structuresTemplatesAndFeeds = new PortletDataHandlerBoolean(
			_NAMESPACE, "structures-templates-and-feeds", true, true);

	private static PortletDataHandlerBoolean _tags =
		new PortletDataHandlerBoolean(_NAMESPACE, "tags");

	private static PortletDataHandlerBoolean _articles =
		new PortletDataHandlerBoolean(_NAMESPACE, "articles", true, false,
		new PortletDataHandlerControl[] {_images, _comments, _ratings, _tags});

	private static Pattern _exportLinksToLayoutPattern = Pattern.compile(
		"\\[([0-9]+)@(public|private\\-[a-z]*)\\]");
	private static Pattern _importLinksToLayoutPattern = Pattern.compile(
		"\\[([0-9]+)@(public|private\\-[a-z]*)@(\\p{XDigit}{8}\\-" +
		"(?:\\p{XDigit}{4}\\-){3}\\p{XDigit}{12})@([^\\]]*)\\]");

}