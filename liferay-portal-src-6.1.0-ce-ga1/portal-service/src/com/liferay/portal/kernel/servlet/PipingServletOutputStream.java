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

package com.liferay.portal.kernel.servlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;

/**
 * @author Shuyang Zhou
 */
public class PipingServletOutputStream extends ServletOutputStream {

	public PipingServletOutputStream(OutputStream outputStream) {
		_outputStream = outputStream;
	}

	@Override
	public void close() throws IOException {
		super.close();

		_closed = true;
	}

	public boolean isClosed() {
		return _closed;
	}

	@Override
	public void write(byte[] bytes) throws IOException {
		_outputStream.write(bytes);
	}

	@Override
	public void write(byte[] bytes, int offset, int length)
		throws IOException {

		_outputStream.write(bytes, offset, length);
	}

	@Override
	public void write(int b) throws IOException {
		_outputStream.write(b);
	}

	private boolean _closed;
	private OutputStream _outputStream;

}