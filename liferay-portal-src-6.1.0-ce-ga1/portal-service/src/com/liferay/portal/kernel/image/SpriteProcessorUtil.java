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

package com.liferay.portal.kernel.image;

import java.io.File;
import java.io.IOException;

import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;

/**
 * @author Brian Wing Shun Chan
 */
public class SpriteProcessorUtil {

	public static Properties generate(
			ServletContext servletContext, List<File> imageFiles,
			String spriteFileName, String spritePropertiesFileName,
			String spritePropertiesRootPath, int maxHeight, int maxWidth,
			int maxSize)
		throws IOException {

		return getSpriteProcessor().generate(
			servletContext, imageFiles, spriteFileName,
			spritePropertiesFileName, spritePropertiesRootPath, maxHeight,
			maxWidth, maxSize);
	}

	public static SpriteProcessor getSpriteProcessor() {
		return _spriteProcessor;
	}

	public void setSpriteProcessor(SpriteProcessor spriteProcessor) {
		_spriteProcessor = spriteProcessor;
	}

	private static SpriteProcessor _spriteProcessor;

}