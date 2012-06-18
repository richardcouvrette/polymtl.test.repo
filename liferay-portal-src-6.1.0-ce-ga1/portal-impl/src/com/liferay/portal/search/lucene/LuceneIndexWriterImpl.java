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

package com.liferay.portal.search.lucene;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriter;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import java.util.Collection;
import java.util.Locale;
import java.util.Map;

import org.apache.lucene.index.Term;

/**
 * @author Bruno Farache
 * @author Brian Wing Shun Chan
 * @author Allen Chiang
 * @author Alex Wallace
 */
public class LuceneIndexWriterImpl implements IndexWriter {

	public void addDocument(SearchContext searchContext, Document document)
		throws SearchException {

		try {
			LuceneHelperUtil.addDocument(
				searchContext.getCompanyId(), _getLuceneDocument(document));

			if (_log.isDebugEnabled()) {
				_log.debug("Added document " + document.get(Field.UID));
			}
		}
		catch (IOException ioe) {
			throw new SearchException(ioe);
		}
	}

	public void addDocuments(
			SearchContext searchContext, Collection<Document> documents)
		throws SearchException {

		for (Document document : documents) {
			addDocument(searchContext, document);
		}
	}

	public void deleteDocument(SearchContext searchContext, String uid)
		throws SearchException {

		try {
			LuceneHelperUtil.deleteDocuments(
				searchContext.getCompanyId(), new Term(Field.UID, uid));

			if (_log.isDebugEnabled()) {
				_log.debug("Deleted document " + uid);
			}
		}
		catch (IOException ioe) {
			throw new SearchException(ioe);
		}
	}

	public void deleteDocuments(
			SearchContext searchContext, Collection<String> uids)
		throws SearchException {

		for (String uid : uids) {
			deleteDocument(searchContext, uid);
		}
	}

	public void deletePortletDocuments(
			SearchContext searchContext, String portletId)
		throws SearchException {

		try {
			LuceneHelperUtil.deleteDocuments(
				searchContext.getCompanyId(), new Term(Field.PORTLET_ID,
				portletId));
		}
		catch (IOException ioe) {
			throw new SearchException(ioe);
		}
	}

	public void updateDocument(SearchContext searchContext, Document document)
		throws SearchException {

		try {
			LuceneHelperUtil.updateDocument(
				searchContext.getCompanyId(),
				new Term(Field.UID, document.getUID()),
				_getLuceneDocument(document));

			if (_log.isDebugEnabled()) {
				_log.debug("Updated document " + document.get(Field.UID));
			}
		}
		catch (IOException ioe) {
			throw new SearchException(ioe);
		}
	}

	public void updateDocuments(
			SearchContext searchContext, Collection<Document> documents)
		throws SearchException {

		for (Document document : documents) {
			updateDocument(searchContext, document);
		}
	}

	private void _addLuceneFieldable(
		org.apache.lucene.document.Document luceneDocument, String name,
		boolean numeric, boolean tokenized, float boost, String value) {

		org.apache.lucene.document.Fieldable luceneFieldable = null;

		if (numeric) {
			luceneFieldable = LuceneFields.getNumber(name, value);
		}
		else {
			if (tokenized) {
				luceneFieldable = LuceneFields.getText(name, value);
			}
			else {
				luceneFieldable = LuceneFields.getKeyword(name, value);
			}
		}

		luceneFieldable.setBoost(boost);

		luceneDocument.add(luceneFieldable);
	}

	private org.apache.lucene.document.Document _getLuceneDocument(
		Document document) {

		org.apache.lucene.document.Document luceneDocument =
			new org.apache.lucene.document.Document();

		Collection<Field> fields = document.getFields().values();

		for (Field field : fields) {
			String name = field.getName();
			boolean numeric = field.isNumeric();
			boolean tokenized = field.isTokenized();
			float boost = field.getBoost();

			if (!field.isLocalized()) {
				for (String value : field.getValues()) {
					if (Validator.isNull(value)) {
						continue;
					}

					_addLuceneFieldable(
						luceneDocument, name, numeric, tokenized, boost, value);
				}
			}
			else {
				Map<Locale, String> localizedValues =
					field.getLocalizedValues();

				for (Map.Entry<Locale, String> entry :
						localizedValues.entrySet()) {

					String value = entry.getValue();

					if (Validator.isNull(value)) {
						continue;
					}

					Locale locale = entry.getKey();

					String languageId = LocaleUtil.toLanguageId(locale);

					String defaultLanguageId = LocaleUtil.toLanguageId(
						LocaleUtil.getDefault());

					if (languageId.equals(defaultLanguageId)) {
						_addLuceneFieldable(
							luceneDocument, name, numeric, tokenized, boost,
							value);
					}

					String localizedName = DocumentImpl.getLocalizedName(
						locale, name);

					_addLuceneFieldable(
						luceneDocument, localizedName, numeric, tokenized,
						boost, value);
				}
			}

		}

		return luceneDocument;
	}

	private static Log _log = LogFactoryUtil.getLog(
		LuceneIndexWriterImpl.class);

}