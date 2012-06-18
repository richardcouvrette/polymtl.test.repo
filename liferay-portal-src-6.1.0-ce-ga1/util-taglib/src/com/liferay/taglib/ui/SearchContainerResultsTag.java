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

package com.liferay.taglib.ui;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.util.ServerDetector;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author Raymond Augé
 */
public class SearchContainerResultsTag<R> extends TagSupport {

	public static final String DEFAULT_RESULTS_VAR = "results";

	public static final String DEFAULT_TOTAL_VAR = "total";

	@Override
	public int doEndTag() throws JspException {
		try {
			if (_results == null) {
				_results = (List<R>)pageContext.getAttribute(_resultsVar);
				_total = (Integer)pageContext.getAttribute(_totalVar);
			}

			if (_results != null) {
				if (_total < _results.size()) {
					_total = _results.size();
				}
			}

			SearchContainerTag<R> searchContainerTag =
				(SearchContainerTag<R>)findAncestorWithClass(
					this, SearchContainerTag.class);

			SearchContainer<R> searchContainer =
				searchContainerTag.getSearchContainer();

			searchContainer.setResults(_results);
			searchContainer.setTotal(_total);

			searchContainerTag.setHasResults(true);

			pageContext.setAttribute(_resultsVar, _results);
			pageContext.setAttribute(_totalVar, _total);

			return EVAL_PAGE;
		}
		catch (Exception e) {
			throw new JspException(e);
		}
		finally {
			if (!ServerDetector.isResin()) {
				_results = null;
				_resultsVar = DEFAULT_RESULTS_VAR;
				_total = 0;
				_totalVar = DEFAULT_TOTAL_VAR;
			}
		}
	}

	@Override
	public int doStartTag() throws JspException {
		SearchContainerTag<R> searchContainerTag =
			(SearchContainerTag<R>)findAncestorWithClass(
				this, SearchContainerTag.class);

		if (searchContainerTag == null) {
			throw new JspTagException("Requires liferay-ui:search-container");
		}

		if (_results == null) {
			pageContext.setAttribute(_resultsVar, new ArrayList<R>());
			pageContext.setAttribute(_totalVar, 0);
		}

		return EVAL_BODY_INCLUDE;
	}

	public List<R> getResults() {
		return _results;
	}

	public String getResultsVar() {
		return _resultsVar;
	}

	public int getTotal() {
		return _total;
	}

	public String getTotalVar() {
		return _totalVar;
	}

	public void setResults(List<R> results) {
		_results = results;
	}

	public void setResultsVar(String resultsVar) {
		_resultsVar = resultsVar;
	}

	public void setTotal(int total) {
		_total = total;
	}

	public void setTotalVar(String totalVar) {
		_totalVar = totalVar;
	}

	private List<R> _results;
	private String _resultsVar = DEFAULT_RESULTS_VAR;
	private int _total;
	private String _totalVar = DEFAULT_TOTAL_VAR;

}