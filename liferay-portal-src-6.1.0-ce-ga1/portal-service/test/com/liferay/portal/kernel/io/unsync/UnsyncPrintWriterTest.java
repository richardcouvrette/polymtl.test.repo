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

package com.liferay.portal.kernel.io.unsync;

import com.liferay.portal.kernel.io.OutputStreamWriter;
import com.liferay.portal.kernel.test.TestCase;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.io.Writer;

import java.lang.reflect.Field;

/**
 * @author Shuyang Zhou
 */
public class UnsyncPrintWriterTest extends TestCase {

	@Override
	public void tearDown() throws Exception {
		File testFile = new File(_TEST_FILE_NAME);

		testFile.delete();
	}

	public void testConstructor() throws Exception {

		// UnsyncPrintWriter(File file)

		UnsyncPrintWriter unsyncPrintWriter = new UnsyncPrintWriter(
			new File(_TEST_FILE_NAME));

		assertTrue(_getOut(unsyncPrintWriter) instanceof FileWriter);

		// UnsyncPrintWriter(File file, String charSequence)

		unsyncPrintWriter = new UnsyncPrintWriter(
			new File(_TEST_FILE_NAME), "UTF8");

		assertTrue(_getOut(unsyncPrintWriter) instanceof OutputStreamWriter);

		OutputStreamWriter outputStreamWriter = (OutputStreamWriter)_getOut(
			unsyncPrintWriter);

		assertEquals("UTF8", outputStreamWriter.getEncoding());

		// UnsyncPrintWriter(OutputStream outputStream)

		ByteArrayOutputStream byteArrayOutputStream =
			new ByteArrayOutputStream();

		unsyncPrintWriter = new UnsyncPrintWriter(byteArrayOutputStream);

		assertTrue(_getOut(unsyncPrintWriter) instanceof OutputStreamWriter);

		// UnsyncPrintWriter(String fileName)

		unsyncPrintWriter = new UnsyncPrintWriter(_TEST_FILE_NAME);

		assertTrue(_getOut(unsyncPrintWriter) instanceof FileWriter);

		// UnsyncPrintWriter(String fileName, String csn)

		unsyncPrintWriter = new UnsyncPrintWriter(_TEST_FILE_NAME, "UTF8");

		assertTrue(_getOut(unsyncPrintWriter) instanceof OutputStreamWriter);

		outputStreamWriter = (OutputStreamWriter)_getOut(unsyncPrintWriter);

		assertEquals("UTF8", outputStreamWriter.getEncoding());

		// UnsyncPrintWriter(Writer writer)

		StringWriter stringWriter = new StringWriter();

		unsyncPrintWriter = new UnsyncPrintWriter(stringWriter);

		assertEquals(stringWriter, _getOut(unsyncPrintWriter));
	}

	public void testFormat() {
		StringWriter stringWriter = new StringWriter();

		UnsyncPrintWriter unsyncPrintWriter = new UnsyncPrintWriter(
			stringWriter);

		unsyncPrintWriter.format("%2$2d %1$2s", "a", 1);

		assertEquals(" 1  a", stringWriter.toString());
	}
	public void testPrintln() {
		StringWriter stringWriter = new StringWriter();

		UnsyncPrintWriter unsyncPrintWriter = new UnsyncPrintWriter(
			stringWriter);

		unsyncPrintWriter.println();

		String lineSeparator = System.getProperty("line.separator");

		assertEquals(lineSeparator, stringWriter.toString());
	}

	public void testWrite() {
		StringWriter stringWriter = new StringWriter();

		UnsyncPrintWriter unsyncPrintWriter = new UnsyncPrintWriter(
			stringWriter);

		// write(int c)

		unsyncPrintWriter.write('a');

		assertEquals("a", stringWriter.toString());

		unsyncPrintWriter.write('b');

		assertEquals("ab", stringWriter.toString());

		// write(char[] chars)

		unsyncPrintWriter.write(new char[] {'c', 'd'});

		assertEquals("abcd", stringWriter.toString());

		unsyncPrintWriter.write(new char[] {'e', 'f'});

		assertEquals("abcdef", stringWriter.toString());

		// write(char[] chars, int offset, int length)

		unsyncPrintWriter.write(
			new char[] {'e', 'f', 'g', 'h', 'i', 'j'}, 2, 2);

		assertEquals("abcdefgh", stringWriter.toString());

		unsyncPrintWriter.write(
			new char[] {'g', 'h', 'i', 'j', 'k', 'l'}, 2, 2);

		assertEquals("abcdefghij", stringWriter.toString());

		// write(String string)

		unsyncPrintWriter.write("kl");

		assertEquals("abcdefghijkl", stringWriter.toString());

		unsyncPrintWriter.write("mn");

		assertEquals("abcdefghijklmn", stringWriter.toString());

		// write(String string, int offset, int length)

		unsyncPrintWriter.write("mnopqr", 2, 2);

		assertEquals("abcdefghijklmnop", stringWriter.toString());

		unsyncPrintWriter.write("opqrst", 2, 2);

		assertEquals("abcdefghijklmnopqr", stringWriter.toString());
	}

	private static Writer _getOut(UnsyncPrintWriter unsyncPrintWriter) {
		try {
			return (Writer) _writerField.get(unsyncPrintWriter);
		}
		catch (Throwable t) {
			throw new RuntimeException(t);
		}
	}

	private static final String _TEST_FILE_NAME =
		"UnsyncPrintWriterTest.testFile";

	private static Field _writerField;

	static {
		try {
			_writerField = UnsyncPrintWriter.class.getDeclaredField("_writer");

			_writerField.setAccessible(true);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}