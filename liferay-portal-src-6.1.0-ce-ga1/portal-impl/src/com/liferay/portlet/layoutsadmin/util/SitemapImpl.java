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

package com.liferay.portlet.layoutsadmin.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.model.JournalArticleConstants;
import com.liferay.portlet.journal.service.JournalArticleServiceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jorge Ferrer
 */
public class SitemapImpl implements Sitemap {

	public String encodeXML(String input) {
		return StringUtil.replace(
			input,
			new String[] {"&", "<", ">", "'", "\""},
			new String[] {"&amp;", "&lt;", "&gt;", "&apos;", "&quot;"});
	}

	public String getSitemap(
			long groupId, boolean privateLayout, ThemeDisplay themeDisplay)
		throws PortalException, SystemException {

		Document document = SAXReaderUtil.createDocument();

		document.setXMLEncoding(StringPool.UTF8);

		Element rootElement = document.addElement(
			"urlset", "http://www.google.com/schemas/sitemap/0.84");

		List<Layout> layouts = LayoutLocalServiceUtil.getLayouts(
			groupId, privateLayout, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID);

		visitLayouts(rootElement, layouts, themeDisplay);

		return document.asXML();
	}

	protected void visitArticles(
			Element element, Layout layout, ThemeDisplay themeDisplay)
		throws PortalException, SystemException {

		List<JournalArticle> journalArticles =
			JournalArticleServiceUtil.getArticlesByLayoutUuid(
				layout.getGroupId(), layout.getUuid());

		if (journalArticles.isEmpty()) {
			return;
		}

		List<String> processedArticleIds = new ArrayList<String>();

		for (JournalArticle journalArticle : journalArticles) {
			if (processedArticleIds.contains(
					journalArticle.getArticleId()) ||
				(journalArticle.getStatus() !=
					WorkflowConstants.STATUS_APPROVED)) {

				continue;
			}

			String portalURL = PortalUtil.getPortalURL(layout, themeDisplay);

			String groupFriendlyURL = PortalUtil.getGroupFriendlyURL(
				GroupLocalServiceUtil.getGroup(journalArticle.getGroupId()),
				false, themeDisplay);

			StringBundler sb = new StringBundler(4);

			sb.append(portalURL);
			sb.append(groupFriendlyURL);
			sb.append(JournalArticleConstants.CANONICAL_URL_SEPARATOR);
			sb.append(journalArticle.getUrlTitle());

			String articleURL = sb.toString();

			Element urlElement = element.addElement("url");

			Element locElement = urlElement.addElement("loc");

			locElement.addText(encodeXML(articleURL));

			processedArticleIds.add(journalArticle.getArticleId());
		}
	}

	protected void visitLayout(
			Element element, Layout layout, ThemeDisplay themeDisplay)
		throws PortalException, SystemException {

		UnicodeProperties typeSettingsProperties =
			layout.getTypeSettingsProperties();

		if (layout.isHidden() || !PortalUtil.isLayoutSitemapable(layout) ||
			!GetterUtil.getBoolean(
				typeSettingsProperties.getProperty("sitemap-include"),
				true)) {

			return;
		}

		Element urlElement = element.addElement("url");

		String layoutFullURL = PortalUtil.getLayoutFullURL(
			layout, themeDisplay);

		Element locElement = urlElement.addElement("loc");

		locElement.addText(encodeXML(layoutFullURL));

		String changefreq = typeSettingsProperties.getProperty(
			"sitemap-changefreq");

		if (Validator.isNotNull(changefreq)) {
			Element changefreqElement = urlElement.addElement("changefreq");

			changefreqElement.addText(changefreq);
		}

		String priority = typeSettingsProperties.getProperty(
			"sitemap-priority");

		if (Validator.isNotNull(priority)) {
			Element priorityElement = urlElement.addElement("priority");

			priorityElement.addText(priority);
		}

		visitArticles(element, layout, themeDisplay);

		List<Layout> children = layout.getChildren();

		visitLayouts(element, children, themeDisplay);
	}

	protected void visitLayouts(
			Element element, List<Layout> layouts, ThemeDisplay themeDisplay)
		throws PortalException, SystemException {

		for (Layout layout : layouts) {
			visitLayout(element, layout, themeDisplay);
		}
	}

}