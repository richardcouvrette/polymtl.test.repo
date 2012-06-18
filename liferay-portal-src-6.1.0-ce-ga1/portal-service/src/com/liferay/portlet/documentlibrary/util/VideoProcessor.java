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

package com.liferay.portlet.documentlibrary.util;

import com.liferay.portal.kernel.repository.model.FileVersion;

import java.io.InputStream;

import java.util.Set;

/**
 * @author Sergio González
 */
public interface VideoProcessor {

	public static final String THUMBNAIL_TYPE = "jpg";

	public void generateVideo(FileVersion fileVersion) throws Exception;

	public InputStream getPreviewAsStream(FileVersion fileVersion)
		throws Exception;

	public InputStream getPreviewAsStream(FileVersion fileVersion, String type)
		throws Exception;

	public long getPreviewFileSize(FileVersion fileVersion)
		throws Exception;

	public long getPreviewFileSize(FileVersion fileVersion, String type)
		throws Exception;

	public InputStream getThumbnailAsStream(
			FileVersion fileVersion, int thumbnailIndex)
		throws Exception;

	public long getThumbnailFileSize(
			FileVersion fileVersion, int thumbnailIndex)
		throws Exception;

	public Set<String> getVideoMimeTypes();

	public boolean hasVideo(FileVersion fileVersion);

	public boolean isSupported(String mimeType);

	public boolean isVideoSupported(FileVersion fileVersion);

	public boolean isVideoSupported(String mimeType);

	public void trigger(FileVersion fileVersion);

}