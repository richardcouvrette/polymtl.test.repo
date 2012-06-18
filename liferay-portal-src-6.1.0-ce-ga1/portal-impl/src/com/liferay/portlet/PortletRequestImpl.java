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

package com.liferay.portlet;

import com.liferay.portal.ccpp.PortalProfileFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletSession;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.servlet.BrowserSnifferUtil;
import com.liferay.portal.kernel.servlet.ProtectedPrincipal;
import com.liferay.portal.kernel.servlet.ServletContextPool;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ContextPathUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.QName;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.PortletApp;
import com.liferay.portal.model.PortletConstants;
import com.liferay.portal.model.PublicRenderParameter;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.servlet.NamespaceServletRequest;
import com.liferay.portal.servlet.SharedSessionServletRequest;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.portletconfiguration.util.PublicRenderParameterConfiguration;
import com.liferay.util.servlet.DynamicServletRequest;

import java.lang.reflect.Method;

import java.security.Principal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.ccpp.Profile;

import javax.portlet.PortalContext;
import javax.portlet.PortletConfig;
import javax.portlet.PortletContext;
import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletSession;
import javax.portlet.WindowState;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Brian Wing Shun Chan
 * @author Brian Myunghun Kim
 * @author Sergey Ponomarev
 */
public abstract class PortletRequestImpl implements LiferayPortletRequest {

	public static PortletRequestImpl getPortletRequestImpl(
		PortletRequest portletRequest) {

		PortletRequestImpl portletRequestImpl = null;

		if (portletRequest instanceof PortletRequestImpl) {
			portletRequestImpl = (PortletRequestImpl)portletRequest;
		}
		else {

			// LPS-3311

			try {
				Method method = portletRequest.getClass().getMethod(
					"getRequest");

				Object obj = method.invoke(portletRequest, (Object[])null);

				portletRequestImpl = getPortletRequestImpl((PortletRequest)obj);
			}
			catch (Exception e) {
				throw new RuntimeException(
					"Unable to get the portlet request from " +
						portletRequest.getClass().getName());
			}
		}

		return portletRequestImpl;
	}

	public void cleanUp() {
		_request.removeAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
		_request.removeAttribute(JavaConstants.JAVAX_PORTLET_REQUEST);
		_request.removeAttribute(JavaConstants.JAVAX_PORTLET_RESPONSE);
		_request.removeAttribute(PortletRequest.LIFECYCLE_PHASE);
	}

	public void defineObjects(
		PortletConfig portletConfig, PortletResponse portletResponse) {

		PortletConfigImpl portletConfigImpl = (PortletConfigImpl)portletConfig;

		setAttribute(WebKeys.PORTLET_ID, portletConfigImpl.getPortletId());
		setAttribute(JavaConstants.JAVAX_PORTLET_CONFIG, portletConfig);
		setAttribute(JavaConstants.JAVAX_PORTLET_REQUEST, this);
		setAttribute(JavaConstants.JAVAX_PORTLET_RESPONSE, portletResponse);
		setAttribute(PortletRequest.LIFECYCLE_PHASE, getLifecycle());
	}

	public Object getAttribute(String name) {
		if (name == null) {
			throw new IllegalArgumentException();
		}

		if (name.equals(PortletRequest.CCPP_PROFILE)) {
			return getCCPPProfile();
		}
		else if (name.equals(PortletRequest.USER_INFO)) {
			Object value = getUserInfo();

			if (value != null) {
				return value;
			}
		}

		return _request.getAttribute(name);
	}

	public Enumeration<String> getAttributeNames() {
		List<String> names = new ArrayList<String>();

		Enumeration<String> enu = _request.getAttributeNames();

		while (enu.hasMoreElements()) {
			String name = enu.nextElement();

			if (!name.equals(JavaConstants.JAVAX_SERVLET_INCLUDE_PATH_INFO)) {
				names.add(name);
			}
		}

		return Collections.enumeration(names);
	}

	public String getAuthType() {
		return _request.getAuthType();
	}

	public Profile getCCPPProfile() {
		if (_profile == null) {
			_profile = PortalProfileFactory.getCCPPProfile(_request);
		}

		return _profile;
	}

	public String getContextPath() {
		PortletContextImpl portletContextImpl =
			(PortletContextImpl)_portletContext;

		ServletContext servletContext = portletContextImpl.getServletContext();

		String servletContextName = servletContext.getServletContextName();

		if (ServletContextPool.containsKey(servletContextName)) {
			servletContext = ServletContextPool.get(servletContextName);

			return ContextPathUtil.getContextPath(servletContext);
		}

		return StringPool.SLASH.concat(_portletContext.getPortletContextName());
	}

	public Cookie[] getCookies() {
		return _request.getCookies();
	}

	public String getETag() {
		return null;
	}

	public HttpServletRequest getHttpServletRequest() {
		return _request;
	}

	public abstract String getLifecycle();

	public Locale getLocale() {
		Locale locale = _locale;

		if (locale == null) {
			locale = _request.getLocale();
		}

		if (locale == null) {
			locale = LocaleUtil.getDefault();
		}

		return locale;
	}

	public Enumeration<Locale> getLocales() {
		return _request.getLocales();
	}

	public String getMethod() {
		return _request.getMethod();
	}

	public HttpServletRequest getOriginalHttpServletRequest() {
		return _originalRequest;
	}

	public String getParameter(String name) {
		if (name == null) {
			throw new IllegalArgumentException();
		}

		if (_portletRequestDispatcherRequest != null) {
			return _portletRequestDispatcherRequest.getParameter(name);
		}

		return _request.getParameter(name);
	}

	public Map<String, String[]> getParameterMap() {
		if (_portletRequestDispatcherRequest != null) {
			return Collections.unmodifiableMap(
				_portletRequestDispatcherRequest.getParameterMap());
		}

		return Collections.unmodifiableMap(_request.getParameterMap());
	}

	public Enumeration<String> getParameterNames() {
		if (_portletRequestDispatcherRequest != null) {
			return _portletRequestDispatcherRequest.getParameterNames();
		}

		return _request.getParameterNames();
	}

	public String[] getParameterValues(String name) {
		if (name == null) {
			throw new IllegalArgumentException();
		}

		if (_portletRequestDispatcherRequest != null) {
			return _portletRequestDispatcherRequest.getParameterValues(name);
		}

		return _request.getParameterValues(name);
	}

	public PortalContext getPortalContext() {
		return _portalContext;
	}

	public Portlet getPortlet() {
		return _portlet;
	}

	public PortletContext getPortletContext() {
		return _portletContext;
	}

	public PortletMode getPortletMode() {
		return _portletMode;
	}

	public String getPortletName() {
		return _portletName;
	}

	public PortletSession getPortletSession() {
		return _session;
	}

	public PortletSession getPortletSession(boolean create) {
		/*HttpSession httpSes = _req.getSession(create);

		if (httpSes == null) {
			return null;
		}
		else {
			if (create) {
				_session = new PortletSessionImpl(
					_req, _portletName, _portletContext, _portalSessionId,
					_plid);
			}

			return _ses;
		}*/

		/*if ((_session == null) && create) {
			_req.getSession(create);

			_session = new PortletSessionImpl(
				_req, _portletName, _portletContext, _portalSessionId, _plid);
		}*/

		if (!create && _invalidSession) {
			return null;
		}

		return _session;
	}

	public PortletPreferences getPreferences() {
		return new PortletPreferencesWrapper(
			getPreferencesImpl(), getLifecycle());
	}

	public PortletPreferencesImpl getPreferencesImpl() {
		return (PortletPreferencesImpl)_preferences;
	}

	public Map<String, String[]> getPrivateParameterMap() {
		Map<String, String[]> parameterMap = new HashMap<String, String[]>();

		Enumeration<String> enu = getParameterNames();

		while (enu.hasMoreElements()) {
			String name = enu.nextElement();

			if (_portlet.getPublicRenderParameter(name) == null) {
				parameterMap.put(name, getParameterValues(name));
			}
		}

		return parameterMap;
	}

	public Enumeration<String> getProperties(String name) {
		List<String> values = new ArrayList<String>();

		String value = _portalContext.getProperty(name);

		if (value != null) {
			values.add(value);
		}

		return Collections.enumeration(values);
	}

	public String getProperty(String name) {
		return _portalContext.getProperty(name);
	}

	public Enumeration<String> getPropertyNames() {
		return _portalContext.getPropertyNames();
	}

	public Map<String, String[]> getPublicParameterMap() {
		Map<String, String[]> parameterMap = new HashMap<String, String[]>();

		Enumeration<String> enu = getParameterNames();

		while (enu.hasMoreElements()) {
			String name = enu.nextElement();

			if (_portlet.getPublicRenderParameter(name) != null) {
				parameterMap.put(name, getParameterValues(name));
			}
		}

		return parameterMap;
	}

	public String getRemoteUser() {
		return _remoteUser;
	}

	public Map<String, String[]> getRenderParameters() {
		return RenderParametersPool.get(_request, _plid, _portletName);
	}

	public String getRequestedSessionId() {
		if (_session != null) {
			return _session.getId();
		}
		else {
			HttpSession session = _request.getSession(false);

			if (session == null) {
				return StringPool.BLANK;
			}
			else {
				return session.getId();
			}
		}
	}

	public String getResponseContentType() {
		if (_wapTheme) {
			return ContentTypes.XHTML_MP;
		}
		else {
			return ContentTypes.TEXT_HTML;
		}
	}

	public Enumeration<String> getResponseContentTypes() {
		List<String> responseContentTypes = new ArrayList<String>();

		responseContentTypes.add(getResponseContentType());

		return Collections.enumeration(responseContentTypes);
	}

	public String getScheme() {
		return _request.getScheme();
	}

	public String getServerName() {
		return _request.getServerName();
	}

	public int getServerPort() {
		return _request.getServerPort();
	}

	public LinkedHashMap<String, String> getUserInfo() {
		return UserInfoFactory.getUserInfo(_remoteUserId, _portlet);
	}

	public Principal getUserPrincipal() {
		return _userPrincipal;
	}

	public String getWindowID() {
		return _portletName.concat(
			LiferayPortletSession.LAYOUT_SEPARATOR).concat(
				String.valueOf(_plid));
	}

	public WindowState getWindowState() {
		return _windowState;
	}

	public void invalidateSession() {
		_invalidSession = true;
	}

	public boolean isInvalidParameter(String name) {
		if (Validator.isNull(name) ||
			name.startsWith(PortletQName.PUBLIC_RENDER_PARAMETER_NAMESPACE) ||
			name.startsWith(
				PortletQName.REMOVE_PUBLIC_RENDER_PARAMETER_NAMESPACE) ||
			PortalUtil.isReservedParameter(name)) {

			return true;
		}
		else {
			return false;
		}
	}

	public boolean isPortletModeAllowed(PortletMode portletMode) {
		if ((portletMode == null) || Validator.isNull(portletMode.toString())) {
			return true;
		}
		else {
			return _portlet.hasPortletMode(
				getResponseContentType(), portletMode);
		}
	}

	public boolean isPrivateRequestAttributes() {
		return _portlet.isPrivateRequestAttributes();
	}

	public boolean isRequestedSessionIdValid() {
		if (_session != null) {
			return _session.isValid();
		}
		else {
			return _request.isRequestedSessionIdValid();
		}
	}

	public boolean isSecure() {
		return _request.isSecure();
	}

	public boolean isUserInRole(String role) {
		if (_remoteUserId <= 0) {
			return false;
		}
		else {
			try {
				long companyId = PortalUtil.getCompanyId(_request);

				String roleLink = _portlet.getRoleMappers().get(role);

				if (Validator.isNotNull(roleLink)) {
					return RoleLocalServiceUtil.hasUserRole(
						_remoteUserId, companyId, roleLink, true);
				}
				else {
					return RoleLocalServiceUtil.hasUserRole(
						_remoteUserId, companyId, role, true);
				}
			}
			catch (Exception e) {
				_log.error(e);
			}

			return _request.isUserInRole(role);
		}
	}

	public boolean isWindowStateAllowed(WindowState windowState) {
		return PortalContextImpl.isSupportedWindowState(windowState);
	}

	public void removeAttribute(String name) {
		if (name == null) {
			throw new IllegalArgumentException();
		}

		_request.removeAttribute(name);
	}

	public void setAttribute(String name, Object obj) {
		if (name == null) {
			throw new IllegalArgumentException();
		}

		if (obj == null) {
			removeAttribute(name);
		}
		else {
			_request.setAttribute(name, obj);
		}
	}

	public void setPortletMode(PortletMode portletMode) {
		_portletMode = portletMode;
	}

	public void setPortletRequestDispatcherRequest(HttpServletRequest request) {
		_portletRequestDispatcherRequest = request;
	}

	public void setWindowState(WindowState windowState) {
		_windowState = windowState;
	}

	public boolean isTriggeredByActionURL() {
		return _triggeredByActionURL;
	}

	protected void init(
		HttpServletRequest request, Portlet portlet,
		InvokerPortlet invokerPortlet, PortletContext portletContext,
		WindowState windowState, PortletMode portletMode,
		PortletPreferences preferences, long plid) {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		_portlet = portlet;
		_portletName = portlet.getPortletId();
		_publicRenderParameters = PublicRenderParametersPool.get(request, plid);

		String portletNamespace = PortalUtil.getPortletNamespace(_portletName);

		boolean portalSessionShared = false;

		PortletApp portletApp = portlet.getPortletApp();

		if (portletApp.isWARFile() && !portlet.isPrivateSessionAttributes()) {
			portalSessionShared = true;
		}

		request = new SharedSessionServletRequest(request, portalSessionShared);

		DynamicServletRequest dynamicRequest = null;

		if (portlet.isPrivateRequestAttributes()) {
			dynamicRequest = new NamespaceServletRequest(
				request, portletNamespace, portletNamespace, false);
		}
		else {
			dynamicRequest = new DynamicServletRequest(request, false);
		}

		boolean portletFocus = false;

		String ppid = ParamUtil.getString(request, "p_p_id");

		boolean windowStateRestoreCurrentView = ParamUtil.getBoolean(
			request, "p_p_state_rcv");

		if (_portletName.equals(ppid) &&
			!(windowStateRestoreCurrentView &&
			  portlet.isRestoreCurrentView())) {

			// Request was targeted to this portlet

			if (themeDisplay.isLifecycleRender() ||
				themeDisplay.isLifecycleResource()) {

				// Request was triggered by a render or resource URL

				portletFocus = true;
			}
			else if (themeDisplay.isLifecycleAction()) {
				_triggeredByActionURL = true;

				if (getLifecycle().equals(PortletRequest.ACTION_PHASE)) {

					// Request was triggered by an action URL and is being
					// processed by com.liferay.portlet.ActionRequestImpl

					portletFocus = true;
				}
			}
		}

		Map<String, String[]> renderParameters = RenderParametersPool.get(
			request, plid, _portletName);

		if (portletFocus) {
			renderParameters = new HashMap<String, String[]>();

			if (getLifecycle().equals(PortletRequest.RENDER_PHASE) &&
				!LiferayWindowState.isExclusive(request) &&
				!LiferayWindowState.isPopUp(request)) {

				RenderParametersPool.put(
					request, plid, _portletName, renderParameters);
			}

			Map<String, String[]> parameters = request.getParameterMap();

			for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
				String name = entry.getKey();

				if (isInvalidParameter(name)) {
					continue;
				}

				String[] values = entry.getValue();

				if (themeDisplay.isLifecycleRender()) {
					renderParameters.put(name, values);
				}

				if (values == null) {
					continue;
				}

				name = removePortletNamespace(
					invokerPortlet, portletNamespace, name);

				dynamicRequest.setParameterValues(name, values);
			}
		}
		else {
			for (Map.Entry<String, String[]> entry :
					renderParameters.entrySet()) {

				String name = entry.getKey();
				String[] values = entry.getValue();

				name = removePortletNamespace(
					invokerPortlet, portletNamespace, name);

				dynamicRequest.setParameterValues(name, values);
			}
		}

		mergePublicRenderParameters(dynamicRequest, preferences, plid);

		_request = dynamicRequest;
		_originalRequest = request;
		_wapTheme = BrowserSnifferUtil.isWap(_request);
		_portlet = portlet;
		_portalContext = new PortalContextImpl();
		_portletContext = portletContext;
		_windowState = windowState;
		_portletMode = portletMode;
		_preferences = preferences;
		_portalSessionId = _request.getRequestedSessionId();
		_session = new PortletSessionImpl(
			_request, _portletName, _portletContext, _portalSessionId, plid);

		String remoteUser = request.getRemoteUser();

		String userPrincipalStrategy = portlet.getUserPrincipalStrategy();

		if (userPrincipalStrategy.equals(
				PortletConstants.USER_PRINCIPAL_STRATEGY_SCREEN_NAME)) {

			try {
				User user = PortalUtil.getUser(request);

				if (user != null) {
					_remoteUser = user.getScreenName();
					_remoteUserId = user.getUserId();
					_userPrincipal = new ProtectedPrincipal(_remoteUser);
				}
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
		else {
			long userId = PortalUtil.getUserId(request);

			if ((userId > 0) && (remoteUser == null)) {
				_remoteUser = String.valueOf(userId);
				_remoteUserId = userId;
				_userPrincipal = new ProtectedPrincipal(_remoteUser);
			}
			else {
				_remoteUser = remoteUser;
				_remoteUserId = GetterUtil.getLong(remoteUser);
				_userPrincipal = request.getUserPrincipal();
			}
		}

		_locale = themeDisplay.getLocale();
		_plid = plid;
	}

	protected void mergePublicRenderParameters(
		DynamicServletRequest dynamicRequest, PortletPreferences preferences,
		long plid) {

		Enumeration<PublicRenderParameter> publicRenderParameters =
			Collections.enumeration(_portlet.getPublicRenderParameters());

		while (publicRenderParameters.hasMoreElements()) {
			PublicRenderParameter publicRenderParameter =
				publicRenderParameters.nextElement();

			String ignoreKey = PublicRenderParameterConfiguration.getIgnoreKey(
				publicRenderParameter);

			boolean ignoreValue = GetterUtil.getBoolean(
				preferences.getValue(ignoreKey, null));

			if (ignoreValue) {
				continue;
			}

			String mappingKey =
				PublicRenderParameterConfiguration.getMappingKey(
					publicRenderParameter);

			String mappingValue = GetterUtil.getString(
				preferences.getValue(mappingKey, null));

			HttpServletRequest request =
				(HttpServletRequest)dynamicRequest.getRequest();

			String[] newValues = request.getParameterValues(mappingValue);

			if ((newValues != null) && (newValues.length != 0)) {
				newValues = ArrayUtil.remove(newValues, StringPool.NULL);
			}

			String name = publicRenderParameter.getIdentifier();

			if ((newValues == null) || (newValues.length == 0)) {
				QName qName = publicRenderParameter.getQName();

				String[] values = _publicRenderParameters.get(
					PortletQNameUtil.getPublicRenderParameterName(qName));

				if ((values) == null || (values.length == 0) ||
					(Validator.isNull(values[0]))) {

					continue;
				}

				if (dynamicRequest.getParameter(name) == null) {
					dynamicRequest.setParameterValues(name, values);
				}
			}
			else {
				dynamicRequest.setParameterValues(name, newValues);
			}
		}
	}

	protected String removePortletNamespace(
		InvokerPortlet invokerPortlet, String portletNamespace, String name) {

		if (name.startsWith(portletNamespace) &&
			!invokerPortlet.isFacesPortlet()) {

			name = name.substring(portletNamespace.length());
		}

		return name;
	}

	private static Log _log = LogFactoryUtil.getLog(PortletRequestImpl.class);

	private HttpServletRequest _request;
	private HttpServletRequest _originalRequest;
	private HttpServletRequest _portletRequestDispatcherRequest;
	private boolean _wapTheme;
	private Portlet _portlet;
	private String _portletName;
	private PortalContext _portalContext;
	private PortletContext _portletContext;
	private WindowState _windowState;
	private PortletMode _portletMode;
	private PortletPreferences _preferences;
	private PortletSessionImpl _session;
	private String _portalSessionId;
	private boolean _invalidSession;
	private String _remoteUser;
	private long _remoteUserId;
	private Principal _userPrincipal;
	private Profile _profile;
	private Locale _locale;
	private long _plid;
	private Map<String, String[]> _publicRenderParameters;
	private boolean _triggeredByActionURL;

}