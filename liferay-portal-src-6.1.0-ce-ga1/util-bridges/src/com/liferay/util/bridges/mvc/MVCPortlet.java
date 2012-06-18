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

package com.liferay.util.bridges.mvc;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortlet;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalClassInvoker;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.util.PortalUtil;

import java.io.IOException;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletContext;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.PortletResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.WindowState;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public class MVCPortlet extends LiferayPortlet {

	@Override
	public void doAbout(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		include(aboutTemplate, renderRequest, renderResponse);
	}

	@Override
	public void doConfig(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		include(configTemplate, renderRequest, renderResponse);
	}

	@Override
	public void doEdit(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		PortletPreferences portletPreferences = renderRequest.getPreferences();

		if (portletPreferences == null) {
			super.doEdit(renderRequest, renderResponse);
		}
		else {
			include(editTemplate, renderRequest, renderResponse);
		}
	}

	@Override
	public void doEditDefaults(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		PortletPreferences portletPreferences = renderRequest.getPreferences();

		if (portletPreferences == null) {
			super.doEdit(renderRequest, renderResponse);
		}
		else {
			include(editDefaultsTemplate, renderRequest, renderResponse);
		}
	}

	@Override
	public void doEditGuest(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		PortletPreferences portletPreferences = renderRequest.getPreferences();

		if (portletPreferences == null) {
			super.doEdit(renderRequest, renderResponse);
		}
		else {
			include(editGuestTemplate, renderRequest, renderResponse);
		}
	}

	@Override
	public void doHelp(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		include(helpTemplate, renderRequest, renderResponse);
	}

	@Override
	public void doPreview(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		include(previewTemplate, renderRequest, renderResponse);
	}

	@Override
	public void doPrint(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		include(printTemplate, renderRequest, renderResponse);
	}

	@Override
	public void doView(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		include(viewTemplate, renderRequest, renderResponse);
	}

	@Override
	public void init() throws PortletException {
		super.init();

		templatePath = _getInitParameter("template-path");

		if (Validator.isNull(templatePath)) {
			templatePath = StringPool.SLASH;
		}
		else if (templatePath.contains(StringPool.BACK_SLASH) ||
				 templatePath.contains(StringPool.DOUBLE_SLASH) ||
				 templatePath.contains(StringPool.PERIOD) ||
				 templatePath.contains(StringPool.SPACE)) {

			throw new PortletException(
				"template-path " + templatePath + " has invalid characters");
		}
		else if (!templatePath.startsWith(StringPool.SLASH) ||
				 !templatePath.endsWith(StringPool.SLASH)) {

			throw new PortletException(
				"template-path " + templatePath +
					" must start and end with a /");
		}

		aboutTemplate = _getInitParameter("about-template");
		configTemplate = _getInitParameter("config-template");
		editTemplate = _getInitParameter("edit-template");
		editDefaultsTemplate = _getInitParameter("edit-defaults-template");
		editGuestTemplate = _getInitParameter("edit-guest-template");
		helpTemplate = _getInitParameter("help-template");
		previewTemplate = _getInitParameter("preview-template");
		printTemplate = _getInitParameter("print-template");
		viewTemplate = _getInitParameter("view-template");

		clearRequestParameters = GetterUtil.getBoolean(
			getInitParameter("clear-request-parameters"));
		copyRequestParameters = GetterUtil.getBoolean(
			getInitParameter("copy-request-parameters"));

		String packagePrefix = getInitParameter(
			ActionCommandCache.ACTION_PACKAGE_NAME);

		if (Validator.isNotNull(packagePrefix)) {
			_actionCommandCache = new ActionCommandCache(packagePrefix);
		}
	}

	public void invokeTaglibDiscussion(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		PortletConfig portletConfig = getPortletConfig();

		PortalClassInvoker.invoke(
			true,
			"com.liferay.portlet.messageboards.action.EditDiscussionAction",
			"processAction",
			new String[] {
				"org.apache.struts.action.ActionMapping",
				"org.apache.struts.action.ActionForm",
				PortletConfig.class.getName(), ActionRequest.class.getName(),
				ActionResponse.class.getName()
			},
			null, null, portletConfig, actionRequest, actionResponse);
	}

	@Override
	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {

		super.processAction(actionRequest, actionResponse);

		if (copyRequestParameters) {
			PortalUtil.copyRequestParameters(actionRequest, actionResponse);
		}
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException, PortletException {

		String path = getPath(resourceRequest);

		if (path != null) {
			include(
				path, resourceRequest, resourceResponse,
				PortletRequest.RESOURCE_PHASE);
		}
		else {
			super.serveResource(resourceRequest, resourceResponse);
		}
	}

	@Override
	protected boolean callActionMethod(
			ActionRequest request, ActionResponse response)
		throws PortletException {

		if (_actionCommandCache == null) {
			return super.callActionMethod(request, response);
		}

		String actionName = ParamUtil.getString(
			request, ActionRequest.ACTION_NAME);

		if (!actionName.contains(StringPool.COMMA)) {
			ActionCommand actionCommand = _actionCommandCache.getActionCommand(
				actionName);

			if (actionCommand != ActionCommandCache.EMPTY) {
				return actionCommand.processCommand(request, response);
			}
		}
		else {
			List<ActionCommand> actionCommands =
				_actionCommandCache.getActionCommandChain(actionName);

			if (actionCommands.isEmpty()) {
				return false;
			}

			for (ActionCommand actionCommand : actionCommands) {
				if (!actionCommand.processCommand(request, response)) {
					return false;
				}
			}

			return true;
		}

		return false;
	}

	protected void checkPath(String path) throws PortletException {
		if (!path.startsWith(templatePath) ||
			path.contains(StringPool.DOUBLE_PERIOD) ||
			!PortalUtil.isValidResourceId(path)) {

			throw new PortletException(
				"Path " + path + " is not accessible by this portlet");
		}
	}

	@Override
	protected void doDispatch(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		String path = getPath(renderRequest);

		if (path != null) {
			if (!isProcessRenderRequest(renderRequest)) {
				renderRequest.setAttribute(
					WebKeys.PORTLET_DECORATE, Boolean.FALSE);

				return;
			}

			WindowState windowState = renderRequest.getWindowState();

			if (windowState.equals(WindowState.MINIMIZED)) {
				return;
			}

			include(path, renderRequest, renderResponse);
		}
		else {
			super.doDispatch(renderRequest, renderResponse);
		}
	}

	protected String getPath(PortletRequest portletRequest) {
		String mvcPath = portletRequest.getParameter("mvcPath");

		// Check deprecated parameter

		if (mvcPath == null) {
			mvcPath = portletRequest.getParameter("jspPage");
		}

		return mvcPath;
	}

	protected void include(
			String path, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws IOException, PortletException {

		include(
			path, actionRequest, actionResponse, PortletRequest.ACTION_PHASE);
	}

	protected void include(
			String path, EventRequest eventRequest, EventResponse eventResponse)
		throws IOException, PortletException {

		include(path, eventRequest, eventResponse, PortletRequest.EVENT_PHASE);
	}

	protected void include(
			String path, PortletRequest portletRequest,
			PortletResponse portletResponse, String lifecycle)
		throws IOException, PortletException {

		PortletContext portletContext = getPortletContext();

		PortletRequestDispatcher portletRequestDispatcher =
			portletContext.getRequestDispatcher(path);

		if (portletRequestDispatcher == null) {
			_log.error(path + " is not a valid include");
		}
		else {
			checkPath(path);

			portletRequestDispatcher.include(portletRequest, portletResponse);
		}

		if (clearRequestParameters) {
			if (lifecycle.equals(PortletRequest.RENDER_PHASE)) {
				portletResponse.setProperty(
					"clear-request-parameters", Boolean.TRUE.toString());
			}
		}
	}

	protected void include(
			String path, RenderRequest renderRequest,
			RenderResponse renderResponse)
		throws IOException, PortletException {

		include(
			path, renderRequest, renderResponse, PortletRequest.RENDER_PHASE);
	}

	protected void include(
			String path, ResourceRequest resourceRequest,
			ResourceResponse resourceResponse)
		throws IOException, PortletException {

		include(
			path, resourceRequest, resourceResponse,
			PortletRequest.RESOURCE_PHASE);
	}

	protected String aboutTemplate;
	protected boolean clearRequestParameters;
	protected String configTemplate;
	protected boolean copyRequestParameters;
	protected String editDefaultsTemplate;
	protected String editGuestTemplate;
	protected String editTemplate;
	protected String helpTemplate;
	protected String templatePath;
	protected String previewTemplate;
	protected String printTemplate;
	protected String viewTemplate;

	private String _getInitParameter(String name) {
		String value = getInitParameter(name);

		if (value != null) {
			return value;
		}

		// Check deprecated parameter

		if (name.equals("template-path")) {
			return getInitParameter("jsp-path");
		}
		else if (name.endsWith("-template")) {
			name = name.substring(0, name.length() - 9) + "-jsp";

			return getInitParameter(name);
		}

		return null;
	}

	private static Log _log = LogFactoryUtil.getLog(MVCPortlet.class);

	private ActionCommandCache _actionCommandCache;

}