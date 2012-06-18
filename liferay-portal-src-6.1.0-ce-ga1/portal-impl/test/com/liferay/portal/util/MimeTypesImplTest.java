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

package com.liferay.portal.util;

import com.liferay.portal.kernel.util.MimeTypesUtil;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author Alexander Chow
 * @see    FileImplExtractTest
 */
public class MimeTypesImplTest extends BaseTestCase {

	public void testDoc() throws Exception {
		String validContentType = "application/msword";

		String contentTypeStream = getContentType("test.doc", true);
		String contentTypeName = getContentType("test.doc", false);

		assertEquals(validContentType, contentTypeStream);
		assertEquals(validContentType, contentTypeName);
	}

	public void testDocx() throws Exception {
		String validContentType =
			"application/" +
				"vnd.openxmlformats-officedocument.wordprocessingml.document";

		String contentTypeStream = getContentType("test-2007.docx", true);
		String contentTypeName = getContentType("test-2007.docx", false);

		assertEquals(validContentType, contentTypeStream);
		assertEquals(validContentType, contentTypeName);

		contentTypeStream = getContentType("test-2010.docx", true);
		contentTypeName = getContentType("test-2010.docx", false);

		assertEquals(validContentType, contentTypeStream);
		assertEquals(validContentType, contentTypeName);
	}

	public void testHtml() throws Exception {
		String validContentType = "text/html";

		String contentTypeStream = getContentType("test.html", true);
		String contentTypeName = getContentType("test.html", false);

		assertEquals(validContentType, contentTypeStream);
		assertEquals(validContentType, contentTypeName);
	}

	public void testJpg() throws Exception {
		String validContentType = "image/jpeg";

		String contentTypeStream = getContentType("test.jpg", true);
		String contentTypeName = getContentType("test.jpg", false);

		assertEquals(validContentType, contentTypeStream);
		assertEquals(validContentType, contentTypeName);
	}

	public void testOdt() throws Exception {
		String validContentType = "application/vnd.oasis.opendocument.text";

		String contentTypeStream = getContentType("test.odt", true);
		String contentTypeName = getContentType("test.odt", false);

		assertEquals(validContentType, contentTypeStream);
		assertEquals(validContentType, contentTypeName);
	}

	public void testPdf() throws Exception {
		String validContentType = "application/pdf";

		String contentTypeStream = getContentType("test.pdf", true);
		String contentTypeName = getContentType("test.pdf", false);

		assertEquals(validContentType, contentTypeStream);
		assertEquals(validContentType, contentTypeName);

		contentTypeStream = getContentType("test-2010.pdf", true);
		contentTypeName = getContentType("test-2010.pdf", false);

		assertEquals(validContentType, contentTypeStream);
		assertEquals(validContentType, contentTypeName);
	}

	public void testPpt() throws Exception {
		String validContentType = "application/vnd.ms-powerpoint";

		String contentTypeStream = getContentType("test.ppt", true);
		String contentTypeName = getContentType("test.ppt", false);

		assertEquals(validContentType, contentTypeStream);
		assertEquals(validContentType, contentTypeName);
	}

	public void testPptx() throws Exception {
		String validContentType =
			"application/" +
				"vnd.openxmlformats-officedocument.presentationml.presentation";

		String contentTypeStream = getContentType("test-2010.pptx", true);
		String contentTypeName = getContentType("test-2010.pptx", false);

		assertEquals(validContentType, contentTypeStream);
		assertEquals(validContentType, contentTypeName);
	}

	public void testRtf() throws Exception {
		String validContentType = "application/rtf";

		String contentTypeStream = getContentType("test.rtf", true);
		String contentTypeName = getContentType("test.rtf", false);

		assertEquals(validContentType, contentTypeStream);
		assertEquals(validContentType, contentTypeName);
	}

	public void testTxt() throws Exception {
		String validContentType = "text/plain";

		String contentTypeStream = getContentType("test.txt", true);
		String contentTypeName = getContentType("test.txt", false);

		assertEquals(validContentType, contentTypeStream);
		assertEquals(validContentType, contentTypeName);
	}

	public void testXls() throws Exception {
		String validContentType = "application/vnd.ms-excel";

		String contentTypeStream = getContentType("test.xls", true);
		String contentTypeName = getContentType("test.xls", false);

		assertEquals(validContentType, contentTypeStream);
		assertEquals(validContentType, contentTypeName);
	}

	public void testXlsx() throws Exception {
		String validContentType =
			"application/" +
				"vnd.openxmlformats-officedocument.spreadsheetml.sheet";

		String contentTypeStream = getContentType("test-2010.xlsx", true);
		String contentTypeName = getContentType("test-2010.xlsx", false);

		assertEquals(validContentType, contentTypeStream);
		assertEquals(validContentType, contentTypeName);
	}

	public void testXml() throws Exception {
		String validContentType = "application/xml";

		String contentTypeStream = getContentType("test.xml", true);
		String contentTypeName = getContentType("test.xml", false);

		assertEquals(validContentType, contentTypeStream);
		assertEquals(validContentType, contentTypeName);
	}

	protected String getContentType(String fileName, boolean checkStream)
		throws Exception {

		if (checkStream) {
			InputStream inputStream = getInputStream(fileName);

			return MimeTypesUtil.getContentType(inputStream, fileName);
		}
		else {
			return MimeTypesUtil.getContentType(fileName);
		}
	}

	protected InputStream getInputStream(String fileName) throws Exception {
		return new FileInputStream(
			"portal-impl/test/com/liferay/portal/util/dependencies/" +
				fileName);
	}

}