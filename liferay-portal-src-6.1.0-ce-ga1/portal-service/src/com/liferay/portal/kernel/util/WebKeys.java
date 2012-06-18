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

package com.liferay.portal.kernel.util;

/**
 * @author Brian Wing Shun Chan
 */
public interface WebKeys {

	public static final String ASSET_RENDERER = "ASSET_RENDERER";

	public static final String ASSET_RENDERER_FACTORY = "ASSET_RENDERER_FACTORY";

	public static final String ASSET_RENDERER_FACTORY_CLASS_TYPE_ID = "ASSET_RENDERER_FACTORY_CLASS_TYPE_ID";

	public static final String AUI_SCRIPT_DATA = "LIFERAY_SHARED_AUI_SCRIPT_DATA";

	public static final String AUTHENTICATION_TOKEN = "LIFERAY_SHARED_AUTHENTICATION_TOKEN";

	public static final String CLP_MESSAGE_LISTENERS = "CLP_MESSAGE_LISTENERS";

	public static final String CTX = "CTX";

	public static final String CTX_PATH = "CTX_PATH";

	public static final String CURRENT_COMPLETE_URL = "CURRENT_COMPLETE_URL";

	public static final String CURRENT_URL = "CURRENT_URL";

	public static final String FILE_ITEM_THRESHOLD_SIZE_EXCEEDED = "FILE_ITEM_THRESHOLD_SIZE_EXCEEDED";

	public static final String FORGOT_PASSWORD_REMINDER_ATTEMPTS = "FORGOT_PASSWORD_REMINDER_ATTEMPTS";

	public static final String FORGOT_PASSWORD_REMINDER_USER = "FORGOT_PASSWORD_REMINDER_USER";

	public static final String FORGOT_PASSWORD_REMINDER_USER_EMAIL_ADDRESS = "FORGOT_PASSWORD_REMINDER_USER_EMAIL_ADDRESS";

	public static final String INVOKER_FILTER_URI = "INVOKER_FILTER_URI";

	public static final String JOURNAL_ARTICLE_DISPLAY = "JOURNAL_ARTICLE_DISPLAY";

	public static final String LAST_PATH = "LAST_PATH";

	public static final String LAYOUT = "LAYOUT";

	public static final String LAYOUTS = "LAYOUTS";

	/**
	 * @deprecated {@link #VISITED_GROUP_ID_PREVIOUS}
	 */
	public static final String LIFERAY_SHARED_VISITED_GROUP_ID_PREVIOUS = "LIFERAY_SHARED_VISITED_GROUP_ID_PREVIOUS";

	/**
	 * @deprecated {@link #VISITED_GROUP_ID_RECENT}
	 */
	public static final String LIFERAY_SHARED_VISITED_GROUP_ID_RECENT = "LIFERAY_SHARED_VISITED_GROUP_ID_RECENT";

	public static final String PAGE_BODY_BOTTOM = "LIFERAY_SHARED_PAGE_BODY_BOTTOM";

	public static final String PAGE_BODY_TOP = "LIFERAY_SHARED_PAGE_BODY_TOP";

	public static final String PAGE_BOTTOM = "LIFERAY_SHARED_PAGE_BOTTOM";

	public static final String PAGE_DESCRIPTION = "LIFERAY_SHARED_PAGE_DESCRIPTION";

	public static final String PAGE_KEYWORDS = "LIFERAY_SHARED_PAGE_KEYWORDS";

	public static final String PAGE_SUBTITLE = "LIFERAY_SHARED_PAGE_SUBTITLE";

	public static final String PAGE_TITLE = "LIFERAY_SHARED_PAGE_TITLE";

	public static final String PAGE_TOP = "LIFERAY_SHARED_PAGE_TOP";

	public static final String PORTLET_CONFIGURATOR_VISIBILITY = "PORTLET_CONFIGURATOR_VISIBILITY";

	public static final String PORTLET_DECORATE = "PORTLET_DECORATE";

	public static final String PORTLET_ID = "PORTLET_ID";

	public static final String PORTLET_PREFERENCES_MAP = "PORTLET_PREFERENCES_MAP";

	public static final String PORTLET_RESOURCE_STATIC_URLS = "PORTLET_RESOURCE_STATIC_URLS";

	public static final String REDIRECT = "REDIRECT";

	public static final String RENDER_PORTLET = "RENDER_PORTLET";

	public static final String RENDER_PORTLET_COLUMN_COUNT = "RENDER_PORTLET_COLUMN_COUNT";

	public static final String RENDER_PORTLET_COLUMN_ID = "RENDER_PORTLET_COLUMN_ID";

	public static final String RENDER_PORTLET_COLUMN_POS = "RENDER_PORTLET_COLUMN_POS";

	public static final String RENDER_PORTLET_QUERY_STRING = "RENDER_PORTLET_QUERY_STRING";

	public static final String RENDER_PORTLET_RESOURCE = "RENDER_PORTLET_RESOURCE";

	public static final String RUNTIME_PORTLET_IDS = "LIFERAY_SHARED_RUNTIME_PORTLET_IDS";

	public static final String SEARCH_CONTAINER = "SEARCH_CONTAINER";

	public static final String SEARCH_CONTAINER_RESULT_ROW = "SEARCH_CONTAINER_RESULT_ROW";

	public static final String SEARCH_CONTAINER_RESULT_ROW_ENTRY = "SEARCH_CONTAINER_RESULT_ROW_ENTRY";

	public static final String SEARCH_SEARCH_RESULTS = "SEARCH_SEARCH_RESULTS";

	public static final String SERVLET_CONTEXT_INCLUDE_FILTER_PATH = "SERVLET_CONTEXT_INCLUDE_FILTER_PATH";

	public static final String SERVLET_CONTEXT_INCLUDE_FILTER_STRICT = "SERVLET_CONTEXT_INCLUDE_FILTER_STRICT";

	public static final String SERVLET_CONTEXT_INCLUDE_FILTER_THEME = "SERVLET_CONTEXT_INCLUDE_FILTER_THEME";

	public static final String SERVLET_PATH = "SERVLET_PATH";

	public static final String THEME = "THEME";

	public static final String THEME_DISPLAY = "THEME_DISPLAY";

	public static final String UPLOAD_EXCEPTION = "UPLOAD_EXCEPTION";

	public static final String USER = "USER";

	public static final String USER_ID = "USER_ID";

	public static final String USER_PASSWORD = "USER_PASSWORD";

	public static final String USER_UUID = "USER_UUID";

	public static final String VELOCITY_TAGLIB = "VELOCITY_TAGLIB";

	public static final String VISITED_GROUP_ID_PREVIOUS = "LIFERAY_SHARED_VISITED_GROUP_ID_PREVIOUS";

	public static final String VISITED_GROUP_ID_RECENT = "LIFERAY_SHARED_VISITED_GROUP_ID_RECENT";

	public static final String VM_VARIABLES = "VM_VARIABLES";

	public static final String WINDOW_STATE = "WINDOW_STATE";

}