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

package com.liferay.portlet.journal.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.templateparser.BaseTransformerListener;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portlet.dynamicdatamapping.util.DDMXMLUtil;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;

import java.util.List;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class ContentTransformerListener extends BaseTransformerListener {

	@Override
	public String onOutput(String s) {
		if (_log.isDebugEnabled()) {
			_log.debug("onOutput");
		}

		return s;
	}

	@Override
	public String onScript(String s) {
		if (_log.isDebugEnabled()) {
			_log.debug("onScript");
		}

		s = injectEditInPlace(_xml, s);

		return s;
	}

	@Override
	public String onXml(String s) {
		if (_log.isDebugEnabled()) {
			_log.debug("onXml");
		}

		_xml = replace(s);

		return _xml;
	}

	protected String getDynamicContent(String xml, String elementName) {
		String content = null;

		try {
			Document document = SAXReaderUtil.read(xml);

			Element rootElement = document.getRootElement();

			for (Element element : rootElement.elements()) {
				String curElementName = element.attributeValue(
					"name", StringPool.BLANK);

				if (curElementName.equals(elementName)) {
					content = element.elementText("dynamic-content");

					break;
				}
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return GetterUtil.getString(content);
	}

	protected String injectEditInPlace(String xml, String script) {
		try {
			Document document = SAXReaderUtil.read(xml);

			List<Node> nodes = document.selectNodes("//dynamic-element");

			for (Node node : nodes) {
				Element element = (Element)node;

				String name = GetterUtil.getString(
					element.attributeValue("name"));
				String type = GetterUtil.getString(
					element.attributeValue("type"));

				if ((!name.startsWith("reserved-")) &&
					(type.equals("text") || type.equals("text_area") ||
					 type.equals("text_box"))) {

					script = wrapEditInPlaceField(script, name, type, "data");
					script = wrapEditInPlaceField(
						script, name, type, "getData()");
				}
			}
		}
		catch (Exception e) {
			_log.warn(e.getMessage());
		}

		return script;
	}

	protected void replace(Element root) throws Exception {
		Map<String, String> tokens = getTokens();

		long groupId = GetterUtil.getLong(tokens.get("group_id"));

		for (Element el : root.elements()) {
			Element dynamicContent = el.element("dynamic-content");

			if (dynamicContent != null) {
				String text = dynamicContent.getText();

				text = HtmlUtil.stripComments(text);
				text = HtmlUtil.stripHtml(text);
				text = text.trim();

				// [@articleId;elementName@]

				if (Validator.isNotNull(text) && text.length() >= 7 &&
					text.startsWith("[@") && text.endsWith("@]")) {

					text = text.substring(2, text.length() - 2);

					int pos = text.indexOf(";");

					if (pos != -1) {
						String articleId = text.substring(0, pos);
						String elementName =
							text.substring(pos + 1, text.length());

						JournalArticle article =
							JournalArticleLocalServiceUtil.getArticle(
								groupId, articleId);

						dynamicContent.clearContent();
						dynamicContent.addCDATA(
							getDynamicContent(
								article.getContent(), elementName));
					}
				}

				// Make sure to point images to the full path

				else if ((text != null) &&
						 (text.startsWith("/image/journal/article?img_id"))) {

					dynamicContent.setText("@cdn_host@@root_path@" + text);
				}
			}

			replace(el);
		}
	}

	/**
	 * Fill one article with content from another approved article. See the
	 * article DOCUMENTATION-INSTALLATION-BORLAND for a sample use case.
	 *
	 * @return the processed string
	 */
	protected String replace(String xml) {
		try {
			Document document = SAXReaderUtil.read(xml);

			Element rootElement = document.getRootElement();

			replace(rootElement);

			xml = DDMXMLUtil.formatXML(document);
		}
		catch (Exception e) {
			_log.warn(e.getMessage());
		}

		return xml;
	}

	protected String wrapEditInPlaceField(
		String script, String name, String type, String call) {

		String field = "$" + name + "." + call;
		String wrappedField =
			"<span class=\"journal-content-eip-" + type + "\" " +
				"id=\"journal-content-field-name-" + name + "\">" + field +
					"</span>";

		return StringUtil.replace(
			script, "$editInPlace(" + field + ")", wrappedField);
	}

	private static Log _log = LogFactoryUtil.getLog(
		ContentTransformerListener.class);

	private String _xml;

}