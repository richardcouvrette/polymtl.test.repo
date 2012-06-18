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

package com.liferay.portlet.dynamicdatamapping.storage;

import java.util.Map;

/**
 * @author Bruno Basto
 */
public class FieldRendererFactory {

	public static FieldRenderer getFieldRenderer(String dataType) {
		FieldRenderer fieldRenderer = _fieldRenderers.get(dataType);

		if (fieldRenderer == null) {
			fieldRenderer = _fieldRenderers.get(FieldConstants.STRING);
		}

		return fieldRenderer;
	}

	public void setFieldRenderers(Map<String, FieldRenderer> fieldRenderers) {
		_fieldRenderers = fieldRenderers;
	}

	public static Map<String, FieldRenderer> _fieldRenderers;

}