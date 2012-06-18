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

package com.liferay.portal.kernel.search.facet;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BooleanClause;
import com.liferay.portal.kernel.search.BooleanClauseFactoryUtil;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.TermQuery;
import com.liferay.portal.kernel.search.TermQueryFactoryUtil;
import com.liferay.portal.kernel.search.facet.config.FacetConfiguration;
import com.liferay.portal.kernel.search.facet.util.FacetValueValidator;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Raymond Augé
 */
public class MultiValueFacet extends BaseFacet {

	public MultiValueFacet(SearchContext searchContext) {
		super(searchContext);
	}

	@Override
	protected BooleanClause doGetFacetClause() {
		SearchContext searchContext = getSearchContext();

		FacetConfiguration facetConfiguration = getFacetConfiguration();

		JSONObject dataJSONObject = facetConfiguration.getData();

		String[] values = null;

		if (isStatic() && dataJSONObject.has("values")) {
			JSONArray valuesJSONArray = dataJSONObject.getJSONArray("values");

			values = new String[valuesJSONArray.length()];

			for (int i = 0; i < valuesJSONArray.length(); i++) {
				values[i] = valuesJSONArray.getString(i);
			}
		}

		String[] valuesParam = StringUtil.split(
			GetterUtil.getString(searchContext.getAttribute(getFieldName())));

		if (!isStatic() && (valuesParam != null) && (valuesParam.length > 0)) {
			values = valuesParam;
		}

		if ((values == null) || (values.length == 0)) {
			return null;
		}

		BooleanQuery facetQuery = BooleanQueryFactoryUtil.create(searchContext);

		for (String value : values) {
			FacetValueValidator facetValueValidator = getFacetValueValidator();

			if ((searchContext.getUserId() > 0) &&
				(!facetValueValidator.check(searchContext, value))) {

				continue;
			}

			TermQuery termQuery = TermQueryFactoryUtil.create(
				searchContext, getFieldName(), value);

			try {
				facetQuery.add(termQuery, BooleanClauseOccur.SHOULD);
			}
			catch (ParseException pe) {
				_log.error(pe, pe);
			}
		}

		if (!facetQuery.hasClauses()) {
			return null;
		}

		return BooleanClauseFactoryUtil.create(
			facetQuery, BooleanClauseOccur.MUST.getName());
	}

	private static Log _log = LogFactoryUtil.getLog(MultiValueFacet.class);

}