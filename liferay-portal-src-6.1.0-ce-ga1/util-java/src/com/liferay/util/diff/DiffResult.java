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

package com.liferay.util.diff;

import com.liferay.portal.kernel.util.StringBundler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 * Represents a change between one or several lines. <code>changeType</code>
 * tells if the change happened in source or target. <code>lineNumber</code>
 * holds the line number of the first modified line. This line number refers to
 * a line in source or target, depending on the <code>changeType</code> value.
 * <code>changedLines</code> is a list of strings, each string is a line that is
 * already highlighted, indicating where the changes are.
 * </p>
 *
 * @author     Bruno Farache
 * @deprecated Moved to {@link com.liferay.portal.kernel.util.DiffResult}
 */
public class DiffResult {

	public static final String SOURCE = "SOURCE";

	public static final String TARGET = "TARGET";

	public DiffResult(int linePos, List<String> changedLines) {
		_lineNumber = linePos + 1;
		_changedLines = changedLines;
	}

	public DiffResult(int linePos, String changedLine) {
		_lineNumber = linePos + 1;
		_changedLines = new ArrayList<String>();
		_changedLines.add(changedLine);
	}

	public List<String> getChangedLines() {
		return _changedLines;
	}

	public void setChangedLines(List<String> changedLines) {
		_changedLines = changedLines;
	}

	public int getLineNumber() {
		return _lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		_lineNumber = lineNumber;
	}

	@Override
	public boolean equals(Object obj) {
		DiffResult diffResult = (DiffResult)obj;

		if ((diffResult.getLineNumber() == _lineNumber) &&
			(diffResult.getChangedLines().equals(_changedLines))) {

			return true;
		}

		return false;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(_changedLines.size() * 2 + 3);

		sb.append("Line: ");
		sb.append(_lineNumber);
		sb.append("\n");

		Iterator<String> itr = _changedLines.iterator();

		while (itr.hasNext()) {
			sb.append(itr.next());

			if (itr.hasNext()) {
				sb.append("\n");
			}
		}

		return sb.toString();
	}

	private int _lineNumber;
	private List<String> _changedLines;

}