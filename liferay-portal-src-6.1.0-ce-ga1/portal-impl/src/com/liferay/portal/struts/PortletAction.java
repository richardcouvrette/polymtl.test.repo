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

package com.liferay.portal.struts;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.servlet.BrowserSnifferUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.WebKeys;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.MimeResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletContext;
import javax.portlet.PortletRequest;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.PortletResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.util.MessageResources;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletAction extends Action {

	public static String getForwardKey(HttpServletRequest request) {
		String portletId = (String)request.getAttribute(WebKeys.PORTLET_ID);

		String portletNamespace = PortalUtil.getPortletNamespace(portletId);

		return portletNamespace.concat(WebKeys.PORTLET_STRUTS_FORWARD);
	}

	public static String getForwardKey(PortletRequest portletRequest) {
		String portletId = (String)portletRequest.getAttribute(
			WebKeys.PORTLET_ID);

		String portletNamespace = PortalUtil.getPortletNamespace(portletId);

		return portletNamespace.concat(WebKeys.PORTLET_STRUTS_FORWARD);
	}

	@Override
	public ActionForward execute(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
		throws Exception {

		PortletConfig portletConfig = (PortletConfig)request.getAttribute(
			JavaConstants.JAVAX_PORTLET_CONFIG);

		PortletRequest portletRequest = (PortletRequest)request.getAttribute(
			JavaConstants.JAVAX_PORTLET_REQUEST);

		PortletResponse portletResponse = (PortletResponse)request.getAttribute(
			JavaConstants.JAVAX_PORTLET_RESPONSE);

		Boolean strutsExecute = (Boolean)request.getAttribute(
			WebKeys.PORTLET_STRUTS_EXECUTE);

		if ((strutsExecute != null) && strutsExecute.booleanValue()) {
			return strutsExecute(mapping, form, request, response);
		}
		else if (portletRequest instanceof RenderRequest) {
			return render(
				mapping, form, portletConfig, (RenderRequest)portletRequest,
				(RenderResponse)portletResponse);
		}
		else {
			serveResource(
				mapping, form, portletConfig, (ResourceRequest)portletRequest,
				(ResourceResponse)portletResponse);

			return mapping.findForward(ActionConstants.COMMON_NULL);
		}
	}

	public ActionForward strutsExecute(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
		throws Exception {

		return super.execute(mapping, form, request, response);
	}

	public void processAction(
			ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {
	}

	public ActionForward render(
			ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws Exception {

		if (_log.isDebugEnabled()) {
			_log.debug("Forward to " + getForward(renderRequest));
		}

		return mapping.findForward(getForward(renderRequest));
	}

	public void serveResource(
			ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		String resourceId = resourceRequest.getResourceID();

		if (Validator.isNull(resourceId)) {
			return;
		}

		PortletContext portletContext = portletConfig.getPortletContext();

		PortletRequestDispatcher portletRequestDispatcher =
			portletContext.getRequestDispatcher(resourceId);

		if (portletRequestDispatcher == null) {
			return;
		}

		portletRequestDispatcher.forward(resourceRequest, resourceResponse);
	}

	protected void addSuccessMessage(
		ActionRequest actionRequest, ActionResponse actionResponse) {

		PortletConfig portletConfig = (PortletConfig)actionRequest.getAttribute(
			JavaConstants.JAVAX_PORTLET_CONFIG);

		boolean addProcessActionSuccessMessage = GetterUtil.getBoolean(
			portletConfig.getInitParameter("add-process-action-success-action"),
			true);

		if (!addProcessActionSuccessMessage) {
			return;
		}

		String successMessage = ParamUtil.getString(
			actionRequest, "successMessage");

		SessionMessages.add(actionRequest, "request_processed", successMessage);
	}

	protected String getForward(PortletRequest portletRequest) {
		return getForward(portletRequest, null);
	}

	protected String getForward(
		PortletRequest portletRequest, String defaultValue) {

		String forward = (String)portletRequest.getAttribute(
			getForwardKey(portletRequest));

		if (forward == null) {
			return defaultValue;
		}
		else {
			return forward;
		}
	}

	protected void setForward(PortletRequest portletRequest, String forward) {
		portletRequest.setAttribute(getForwardKey(portletRequest), forward);
	}

	protected ModuleConfig getModuleConfig(PortletRequest portletRequest) {
		return (ModuleConfig)portletRequest.getAttribute(Globals.MODULE_KEY);
	}

	protected MessageResources getResources() {
		ServletContext servletContext = getServlet().getServletContext();

		return (MessageResources)servletContext.getAttribute(
			Globals.MESSAGES_KEY);
	}

	@Override
	protected MessageResources getResources(HttpServletRequest request) {
		return getResources();
	}

	protected MessageResources getResources(PortletRequest portletRequest) {
		return getResources();
	}

	protected boolean isCheckMethodOnProcessAction() {
		return _CHECK_METHOD_ON_PROCESS_ACTION;
	}

	protected void sendRedirect(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException {

		sendRedirect(actionRequest, actionResponse, null);
	}

	protected void sendRedirect(
			ActionRequest actionRequest, ActionResponse actionResponse,
			String redirect)
		throws IOException {

		if (SessionErrors.isEmpty(actionRequest)) {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			LayoutTypePortlet layoutTypePortlet =
				themeDisplay.getLayoutTypePortlet();

			boolean hasPortletId = false;

			String portletId = (String)actionRequest.getAttribute(
				WebKeys.PORTLET_ID);

			try {
				hasPortletId = layoutTypePortlet.hasPortletId(portletId);
			}
			catch (Exception e) {
			}

			Portlet portlet = PortletLocalServiceUtil.getPortletById(portletId);

			if (hasPortletId || portlet.isAddDefaultResource()) {
				addSuccessMessage(actionRequest, actionResponse);
			}
		}

		if (Validator.isNull(redirect)) {
			redirect = (String)actionRequest.getAttribute(WebKeys.REDIRECT);
		}

		if (Validator.isNull(redirect)) {
			redirect = ParamUtil.getString(actionRequest, "redirect");
		}

		if (Validator.isNotNull(redirect)) {

			// LPS-1928

			HttpServletRequest request = PortalUtil.getHttpServletRequest(
				actionRequest);

			if ((BrowserSnifferUtil.isIe(request)) &&
				(BrowserSnifferUtil.getMajorVersion(request) == 6.0) &&
				(redirect.contains(StringPool.POUND))) {

				String redirectToken = "&#";

				if (!redirect.contains(StringPool.QUESTION)) {
					redirectToken = StringPool.QUESTION + redirectToken;
				}

				redirect = StringUtil.replace(
					redirect, StringPool.POUND, redirectToken);
			}

			redirect = PortalUtil.escapeRedirect(redirect);

			if (Validator.isNotNull(redirect)) {
				actionResponse.sendRedirect(redirect);
			}
		}
	}

	protected boolean redirectToLogin(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException {

		if (actionRequest.getRemoteUser() == null) {
			HttpServletRequest request = PortalUtil.getHttpServletRequest(
				actionRequest);

			SessionErrors.add(request, PrincipalException.class.getName());

			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
				WebKeys.THEME_DISPLAY);

			actionResponse.sendRedirect(themeDisplay.getURLSignIn());

			return true;
		}
		else {
			return false;
		}
	}

	protected void writeJSON(
			PortletRequest portletRequest, ActionResponse actionResponse,
			Object json)
		throws IOException {

		HttpServletResponse response = PortalUtil.getHttpServletResponse(
			actionResponse);

		response.setContentType(ContentTypes.TEXT_JAVASCRIPT);

		ServletResponseUtil.write(response, json.toString());

		setForward(portletRequest, ActionConstants.COMMON_NULL);
	}

	protected void writeJSON(
			PortletRequest portletRequest, MimeResponse mimeResponse,
			Object json)
		throws IOException {

		mimeResponse.setContentType(ContentTypes.TEXT_JAVASCRIPT);

		PortletResponseUtil.write(mimeResponse, json.toString());
	}

	private static final boolean _CHECK_METHOD_ON_PROCESS_ACTION = true;

	private static Log _log = LogFactoryUtil.getLog(PortletAction.class);

}