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

package com.liferay.portal.kernel.process.log;

import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;
import com.liferay.portal.kernel.process.ProcessCallable;

import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @author Shuyang Zhou
 */
public class ProcessOutputStream extends UnsyncByteArrayOutputStream {

	public ProcessOutputStream(ObjectOutputStream objectOutputStream) {
		this(objectOutputStream, false);
	}

	public ProcessOutputStream(
		ObjectOutputStream objectOutputStream, boolean error) {

		_objectOutputStream = objectOutputStream;
		_error = error;
	}

	@Override
	public void close() throws IOException {
		_objectOutputStream.close();
	}

	@Override
	public void flush() throws IOException {
		if (index > 0) {
			byte[] bytes = toByteArray();

			LoggingProcessCallable loggingProcessCallable =
				new LoggingProcessCallable(bytes, _error);

			writeProcessCallable(loggingProcessCallable);

			reset();
		}
	}

	public void writeProcessCallable(ProcessCallable<?> processCallable)
		throws IOException {

		_objectOutputStream.writeObject(processCallable);

		_objectOutputStream.flush();
	}

	private final boolean _error;
	private final ObjectOutputStream _objectOutputStream;

}