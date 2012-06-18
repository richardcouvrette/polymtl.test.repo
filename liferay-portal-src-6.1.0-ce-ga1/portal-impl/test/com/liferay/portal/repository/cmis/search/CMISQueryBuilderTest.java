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

package com.liferay.portal.repository.cmis.search;

import com.liferay.portal.kernel.repository.cmis.search.CMISSearchQueryBuilderUtil;
import com.liferay.portal.kernel.repository.search.RepositorySearchQueryBuilderUtil;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.test.TestCase;

/**
 * @author Mika Koivisto
 */
public class CMISQueryBuilderTest extends TestCase {

	public void testBooleanQuery() throws Exception {
		SearchContext searchContext = getSearchContext();

		searchContext.setKeywords("+test* -test.doc");

		BooleanQuery searchQuery =
			RepositorySearchQueryBuilderUtil.getFullQuery(searchContext);

		String cmisQuery = CMISSearchQueryBuilderUtil.buildQuery(
			searchContext, searchQuery);

		assertQueryEquals(
			"(cmis:name LIKE 'test%' AND NOT(cmis:name = 'test.doc')) OR " +
				"(cmis:createdBy LIKE 'test%' AND NOT(cmis:createdBy = " +
					"'test.doc'))",
			cmisQuery);
	}

	public void testExactFilenameQuery() throws Exception {
		SearchContext searchContext = getSearchContext();

		searchContext.setKeywords("test.jpg");

		BooleanQuery searchQuery =
			RepositorySearchQueryBuilderUtil.getFullQuery(searchContext);

		String cmisQuery = CMISSearchQueryBuilderUtil.buildQuery(
			searchContext, searchQuery);

		assertQueryEquals(
			"(cmis:name = 'test.jpg') OR (cmis:createdBy = 'test.jpg')",
			cmisQuery);
	}

	public void testFuzzyQuery() throws Exception {
		SearchContext searchContext = getSearchContext();

		searchContext.setKeywords("test~");

		BooleanQuery searchQuery =
			RepositorySearchQueryBuilderUtil.getFullQuery(searchContext);

		String cmisQuery = CMISSearchQueryBuilderUtil.buildQuery(
			searchContext, searchQuery);

		assertQueryEquals(
			"(cmis:name LIKE 'test%') OR (cmis:createdBy LIKE 'test%')",
			cmisQuery);
	}

	public void testPhraseQuery() throws Exception {
		SearchContext searchContext = getSearchContext();

		searchContext.setKeywords("\"My test document.jpg\"");

		BooleanQuery searchQuery =
			RepositorySearchQueryBuilderUtil.getFullQuery(searchContext);

		String cmisQuery = CMISSearchQueryBuilderUtil.buildQuery(
			searchContext, searchQuery);

		assertQueryEquals(
			"(cmis:name = 'My test document.jpg') OR " +
			"(cmis:createdBy = 'My test document.jpg')", cmisQuery);
	}

	public void testPrefixQuery() throws Exception {
		SearchContext searchContext = getSearchContext();

		searchContext.setKeywords("Test*");

		BooleanQuery searchQuery =
			RepositorySearchQueryBuilderUtil.getFullQuery(searchContext);

		String cmisQuery = CMISSearchQueryBuilderUtil.buildQuery(
			searchContext, searchQuery);

		assertQueryEquals(
			"(cmis:name LIKE 'Test%') OR (cmis:createdBy LIKE 'Test%')",
			cmisQuery);
	}

	public void testProximityQuery() throws Exception {
		SearchContext searchContext = getSearchContext();

		searchContext.setKeywords("\"test document\"~10");

		BooleanQuery searchQuery =
			RepositorySearchQueryBuilderUtil.getFullQuery(searchContext);

		String cmisQuery = CMISSearchQueryBuilderUtil.buildQuery(
			searchContext, searchQuery);

		assertQueryEquals(
			"(cmis:name = 'test document') OR " +
			"(cmis:createdBy = 'test document')", cmisQuery);
	}

	public void testRangeQuery() throws Exception {
		SearchContext searchContext = getSearchContext();

		searchContext.setKeywords(
			"createDate:[20091011000000 TO 20091110235959]");

		BooleanQuery searchQuery =
			RepositorySearchQueryBuilderUtil.getFullQuery(searchContext);

		String cmisQuery = CMISSearchQueryBuilderUtil.buildQuery(
			searchContext, searchQuery);

		assertQueryEquals(
			"(cmis:creationDate >= 2009-10-11T00:00:00.000Z AND " +
			"cmis:creationDate <= 2009-11-10T23:59:59.000Z)", cmisQuery);
	}

	public void testWildcardFieldQuery() throws Exception {
		SearchContext searchContext = getSearchContext();

		searchContext.setKeywords("+title:test*.jpg +userName:bar*");

		BooleanQuery searchQuery =
			RepositorySearchQueryBuilderUtil.getFullQuery(searchContext);

		String cmisQuery = CMISSearchQueryBuilderUtil.buildQuery(
			searchContext, searchQuery);

		assertQueryEquals(
			"(cmis:name LIKE 'test%.jpg' AND cmis:createdBy LIKE 'bar%')",
			cmisQuery);
	}

	public void testWildcardQuery() throws Exception {
		SearchContext searchContext = getSearchContext();

		searchContext.setKeywords("test*.jpg");

		BooleanQuery searchQuery =
			RepositorySearchQueryBuilderUtil.getFullQuery(searchContext);

		String cmisQuery = CMISSearchQueryBuilderUtil.buildQuery(
			searchContext, searchQuery);

		assertQueryEquals(
			"(cmis:name LIKE 'test%.jpg') OR (cmis:createdBy LIKE 'test%.jpg')",
			cmisQuery);
	}

	protected void assertQueryEquals(String where, String query) {
		assertEquals(_QUERY_PREFIX +  where + _QUERY_POSTFIX, query);
	}

	protected SearchContext getSearchContext() {
		SearchContext searchContext = new SearchContext();

		searchContext.setSearchEngineId(SearchEngineUtil.GENERIC_ENGINE_ID);

		return searchContext;
	}

	private static String _QUERY_POSTFIX = ") ORDER BY HITS DESC";

	private static String _QUERY_PREFIX =
		"SELECT cmis:objectId, SCORE() AS HITS FROM cmis:document WHERE (";

}