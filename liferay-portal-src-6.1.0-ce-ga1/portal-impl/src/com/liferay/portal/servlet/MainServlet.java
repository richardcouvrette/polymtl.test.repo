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

package com.liferay.portal.servlet;

import com.liferay.portal.NoSuchLayoutException;
import com.liferay.portal.events.EventsProcessorUtil;
import com.liferay.portal.events.StartupAction;
import com.liferay.portal.kernel.cache.Lifecycle;
import com.liferay.portal.kernel.cache.ThreadLocalCacheManager;
import com.liferay.portal.kernel.deploy.hot.HotDeployUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.plugin.PluginPackage;
import com.liferay.portal.kernel.servlet.PortalSessionThreadLocal;
import com.liferay.portal.kernel.servlet.PortletSessionTracker;
import com.liferay.portal.kernel.servlet.ProtectedServletRequest;
import com.liferay.portal.kernel.servlet.ServletContextPool;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.PortalLifecycleUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.PortletApp;
import com.liferay.portal.model.PortletFilter;
import com.liferay.portal.model.PortletURLListener;
import com.liferay.portal.model.User;
import com.liferay.portal.plugin.PluginPackageUtil;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.security.permission.ResourceActionsUtil;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.LayoutTemplateLocalServiceUtil;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.service.ResourceActionLocalServiceUtil;
import com.liferay.portal.service.ResourceCodeLocalServiceUtil;
import com.liferay.portal.service.ThemeLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.servlet.filters.absoluteredirects.AbsoluteRedirectsResponse;
import com.liferay.portal.servlet.filters.i18n.I18nFilter;
import com.liferay.portal.setup.SetupWizardUtil;
import com.liferay.portal.struts.PortletRequestProcessor;
import com.liferay.portal.struts.StrutsUtil;
import com.liferay.portal.util.ExtRegistry;
import com.liferay.portal.util.MaintenanceUtil;
import com.liferay.portal.util.Portal;
import com.liferay.portal.util.PortalInstances;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PropsUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.portal.util.ShutdownUtil;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.PortletBagFactory;
import com.liferay.portlet.PortletConfigFactoryUtil;
import com.liferay.portlet.PortletFilterFactory;
import com.liferay.portlet.PortletInstanceFactoryUtil;
import com.liferay.portlet.PortletURLListenerFactory;
import com.liferay.portlet.social.util.SocialConfigurationUtil;
import com.liferay.util.ContentUtil;
import com.liferay.util.servlet.DynamicServletRequest;
import com.liferay.util.servlet.EncryptedServletRequest;

import java.io.IOException;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.portlet.PortletConfig;
import javax.portlet.PortletContext;
import javax.portlet.PortletException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.RequestProcessor;
import org.apache.struts.config.ControllerConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.tiles.TilesUtilImpl;

/**
 * @author Brian Wing Shun Chan
 * @author Jorge Ferrer
 * @author Brian Myunghun Kim
 */
public class MainServlet extends ActionServlet {

	@Override
	public void destroy() {
		if (_log.isDebugEnabled()) {
			_log.debug("Destroy plugins");
		}

		PortalLifecycleUtil.flushDestroys();

		List<Portlet> portlets = PortletLocalServiceUtil.getPortlets();

		if (_log.isDebugEnabled()) {
			_log.debug("Destroy portlets");
		}

		try {
			destroyPortlets(portlets);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Destroy companies");
		}

		try {
			destroyCompanies();
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Process global shutdown events");
		}

		try {
			processGlobalShutdownEvents();
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Destroy");
		}

		callParentDestroy();
	}

	@Override
	public void init() throws ServletException {
		if (_log.isDebugEnabled()) {
			_log.debug("Initialize");
		}

		ServletContext servletContext = getServletContext();

		callParentInit();

		if (_log.isDebugEnabled()) {
			_log.debug("Process startup events");
		}

		try {
			processStartupEvents();
		}
		catch (Exception e) {
			_log.error(e, e);

			System.out.println(
				"Stopping the server due to unexpected startup errors");

			System.exit(0);
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Initialize servlet context pool");
		}

		try {
			initServletContextPool();
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Initialize plugin package");
		}

		PluginPackage pluginPackage = null;

		try {
			pluginPackage = initPluginPackage();
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Initialize portlets");
		}

		List<Portlet> portlets = null;

		try {
			portlets = initPortlets(pluginPackage);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Initialize layout templates");
		}

		try {
			initLayoutTemplates(pluginPackage, portlets);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Initialize social");
		}

		try {
			initSocial(pluginPackage);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Initialize themes");
		}

		try {
			initThemes(pluginPackage, portlets);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Initialize web settings");
		}

		try {
			initWebSettings();
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Initialize extension environment");
		}

		try {
			initExt();
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Process global startup events");
		}

		try {
			processGlobalStartupEvents();
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Initialize resource actions");
		}

		try {
			initResourceActions(portlets);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Initialize resource codes");
		}

		try {
			initResourceCodes(portlets);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Initialize companies");
		}

		try {
			initCompanies();
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Initialize plugins");
		}

		try {
			initPlugins();
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		servletContext.setAttribute(WebKeys.STARTUP_FINISHED, true);
	}

	@Override
	public void service(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		if (_log.isDebugEnabled()) {
			_log.debug("Process service request");
		}

		if (processShutdownRequest(request, response)) {
			if (_log.isDebugEnabled()) {
				_log.debug("Processed shutdown request");
			}

			return;
		}

		if (processMaintenanceRequest(request, response)) {
			if (_log.isDebugEnabled()) {
				_log.debug("Processed maintenance request");
			}

			return;
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Get company id");
		}

		long companyId = getCompanyId(request);

		if (processCompanyInactiveRequest(request, response, companyId)) {
			if (_log.isDebugEnabled()) {
				_log.debug("Processed company inactive request");
			}

			return;
		}

		try {
			if (processGroupInactiveRequest(request, response)) {
				if (_log.isDebugEnabled()) {
					_log.debug("Processed site inactive request");
				}

				return;
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Set portal port");
		}

		setPortalPort(request);

		if (_log.isDebugEnabled()) {
			_log.debug("Check variables");
		}

		checkServletContext(request);
		checkPortletSessionTracker(request);
		checkPortletRequestProcessor(request);
		checkTilesDefinitionsFactory();

		if (_log.isDebugEnabled()) {
			_log.debug("Encrypt request");
		}

		request = encryptRequest(request, companyId);

		long userId = getUserId(request);

		String remoteUser = getRemoteUser(request, userId);

		if (_log.isDebugEnabled()) {
			_log.debug("Protect request");
		}

		request = protectRequest(request, remoteUser);

		if (_log.isDebugEnabled()) {
			_log.debug("Set principal");
		}

		String password = getPassword(request);

		setPrincipal(userId, remoteUser, password);

		try {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Authenticate user id " + userId + " and remote user " +
						remoteUser);
			}

			userId = loginUser(request, response, userId, remoteUser);

			if (_log.isDebugEnabled()) {
				_log.debug("Authenticated user id " + userId);
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Set session thread local");
		}

		PortalSessionThreadLocal.setHttpSession(request.getSession());

		if (_log.isDebugEnabled()) {
			_log.debug("Process service pre events");
		}

		if (processServicePre(request, response, userId)) {
			if (_log.isDebugEnabled()) {
				_log.debug("Processing service pre events has errors");
			}

			return;
		}

		if (hasAbsoluteRedirect(request)) {
			if (_log.isDebugEnabled()) {
				String currentURL = PortalUtil.getCurrentURL(request);

				_log.debug(
					"Current URL " + currentURL + " has absolute redirect");
			}

			return;
		}

		if (!hasThemeDisplay(request)) {
			if (_log.isDebugEnabled()) {
				String currentURL = PortalUtil.getCurrentURL(request);

				_log.debug(
					"Current URL " + currentURL +
						" does not have a theme display");
			}

			return;
		}

		try {
			if (_log.isDebugEnabled()) {
				_log.debug("Call parent service");
			}

			callParentService(request, response);
		}
		finally {
			if (_log.isDebugEnabled()) {
				_log.debug("Process service post events");
			}

			processServicePost(request, response);
		}
	}

	protected void callParentDestroy() {
		super.destroy();
	}

	protected void callParentInit() throws ServletException {
		super.init();
	}

	protected void callParentService(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		super.service(request, response);
	}

	protected void checkPortletRequestProcessor(HttpServletRequest request)
		throws ServletException {

		ServletContext servletContext = getServletContext();

		PortletRequestProcessor portletReqProcessor =
			(PortletRequestProcessor)servletContext.getAttribute(
				WebKeys.PORTLET_STRUTS_PROCESSOR);

		if (portletReqProcessor == null) {
			ModuleConfig moduleConfig = getModuleConfig(request);

			portletReqProcessor =
				PortletRequestProcessor.getInstance(this, moduleConfig);

			servletContext.setAttribute(
				WebKeys.PORTLET_STRUTS_PROCESSOR, portletReqProcessor);
		}
	}

	protected void checkPortletSessionTracker(HttpServletRequest request) {
		HttpSession session = request.getSession();

		if (session.getAttribute(WebKeys.PORTLET_SESSION_TRACKER) != null) {
			return;
		}

		session.setAttribute(
			WebKeys.PORTLET_SESSION_TRACKER,
			PortletSessionTracker.getInstance());
	}

	protected void checkServletContext(HttpServletRequest request) {
		ServletContext servletContext = getServletContext();

		request.setAttribute(WebKeys.CTX, servletContext);

		String contextPath = request.getContextPath();

		servletContext.setAttribute(WebKeys.CTX_PATH, contextPath);
	}

	protected void checkTilesDefinitionsFactory() {
		ServletContext servletContext = getServletContext();

		if (servletContext.getAttribute(
				TilesUtilImpl.DEFINITIONS_FACTORY) != null) {

			return;
		}

		servletContext.setAttribute(
			TilesUtilImpl.DEFINITIONS_FACTORY,
			servletContext.getAttribute(TilesUtilImpl.DEFINITIONS_FACTORY));
	}

	protected void checkWebSettings(String xml) throws DocumentException {
		Document doc = SAXReaderUtil.read(xml);

		Element root = doc.getRootElement();

		int timeout = PropsValues.SESSION_TIMEOUT;

		Element sessionConfig = root.element("session-config");

		if (sessionConfig != null) {
			String sessionTimeout = sessionConfig.elementText(
				"session-timeout");

			timeout = GetterUtil.getInteger(sessionTimeout, timeout);
		}

		PropsUtil.set(PropsKeys.SESSION_TIMEOUT, String.valueOf(timeout));

		PropsValues.SESSION_TIMEOUT = timeout;

		I18nServlet.setLanguageIds(root);
		I18nFilter.setLanguageIds(I18nServlet.getLanguageIds());
	}

	protected void destroyCompanies() throws Exception {
		long[] companyIds = PortalInstances.getCompanyIds();

		for (int i = 0; i < companyIds.length; i++) {
			destroyCompany(companyIds[i]);
		}
	}

	protected void destroyCompany(long companyId) {
		if (_log.isDebugEnabled()) {
			_log.debug("Process shutdown events");
		}

		try {
			EventsProcessorUtil.process(
				PropsKeys.APPLICATION_SHUTDOWN_EVENTS,
				PropsValues.APPLICATION_SHUTDOWN_EVENTS,
				new String[] {String.valueOf(companyId)});
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	protected void destroyPortlets(List<Portlet> portlets) throws Exception {
		Iterator<Portlet> itr = portlets.iterator();

		while (itr.hasNext()) {
			Portlet portlet = itr.next();

			PortletInstanceFactoryUtil.destroy(portlet);
		}
	}

	protected HttpServletRequest encryptRequest(
		HttpServletRequest request, long companyId) {

		boolean encryptRequest = ParamUtil.getBoolean(request, WebKeys.ENCRYPT);

		if (!encryptRequest) {
			return request;
		}

		try {
			Company company = CompanyLocalServiceUtil.getCompanyById(companyId);

			request = new EncryptedServletRequest(request, company.getKeyObj());
		}
		catch (Exception e) {
		}

		return request;
	}

	protected long getCompanyId(HttpServletRequest request) {
		return PortalInstances.getCompanyId(request);
	}

	protected String getPassword(HttpServletRequest request) {
		return PortalUtil.getUserPassword(request);
	}

	protected String getRemoteUser(HttpServletRequest request, long userId) {
		String remoteUser = request.getRemoteUser();

		if (!PropsValues.PORTAL_JAAS_ENABLE) {
			HttpSession session = request.getSession();

			String jRemoteUser = (String)session.getAttribute("j_remoteuser");

			if (jRemoteUser != null) {
				remoteUser = jRemoteUser;

				session.removeAttribute("j_remoteuser");
			}
		}

		if ((userId > 0) && (remoteUser == null)) {
			remoteUser = String.valueOf(userId);
		}

		return remoteUser;
	}

	@Override
	protected synchronized RequestProcessor getRequestProcessor(
			ModuleConfig moduleConfig)
		throws ServletException {

		ServletContext servletContext = getServletContext();

		String key = Globals.REQUEST_PROCESSOR_KEY + moduleConfig.getPrefix();

		RequestProcessor requestProcessor =
			(RequestProcessor)servletContext.getAttribute(key);

		if (requestProcessor == null) {
			ControllerConfig controllerConfig =
				moduleConfig.getControllerConfig();

			String processorClass = controllerConfig.getProcessorClass();

			ClassLoader classLoader = PortalClassLoaderUtil.getClassLoader();

			try {
				requestProcessor = (RequestProcessor)classLoader.loadClass(
					processorClass).newInstance();
			}
			catch (Exception e) {
				throw new ServletException(e);
			}

			requestProcessor.init(this, moduleConfig);

			servletContext.setAttribute(key, requestProcessor);
		}

		return requestProcessor;
	}

	protected long getUserId(HttpServletRequest request) {
		return PortalUtil.getUserId(request);
	}

	protected boolean hasAbsoluteRedirect(HttpServletRequest request) {
		if (request.getAttribute(
				AbsoluteRedirectsResponse.class.getName()) == null) {

			return false;
		}
		else {
			return true;
		}
	}

	protected boolean hasThemeDisplay(HttpServletRequest request) {
		if (request.getAttribute(WebKeys.THEME_DISPLAY) == null) {
			return false;
		}
		else {
			return true;
		}
	}

	protected void initCompanies() throws Exception {
		ServletContext servletContext = getServletContext();

		String[] webIds = PortalInstances.getWebIds();

		for (int i = 0; i < webIds.length; i++) {
			PortalInstances.initCompany(servletContext, webIds[i]);
		}
	}

	protected void initExt() throws Exception {
		ServletContext servletContext = getServletContext();

		ExtRegistry.registerPortal(servletContext);
	}

	protected void initLayoutTemplates(
			PluginPackage pluginPackage, List<Portlet> portlets)
		throws Exception {

		ServletContext servletContext = getServletContext();

		String[] xmls = new String[] {
			HttpUtil.URLtoString(
				servletContext.getResource(
					"/WEB-INF/liferay-layout-templates.xml")),
			HttpUtil.URLtoString(
				servletContext.getResource(
					"/WEB-INF/liferay-layout-templates-ext.xml"))
		};

		LayoutTemplateLocalServiceUtil.init(
			servletContext, xmls, pluginPackage);
	}

	protected PluginPackage initPluginPackage() throws Exception {
		ServletContext servletContext = getServletContext();

		return PluginPackageUtil.readPluginPackageServletContext(
			servletContext);
	}

	/**
	 * @see {@link SetupWizardUtil#_initPlugins}
	 */
	protected void initPlugins() throws Exception {

		// See LEP-2885. Don't flush hot deploy events until after the portal
		// has initialized.

		if (SetupWizardUtil.isSetupFinished()) {
			HotDeployUtil.setCapturePrematureEvents(false);

			PortalLifecycleUtil.flushInits();
		}
	}

	protected void initPortletApp(
			Portlet portlet, ServletContext servletContext)
		throws PortletException {

		PortletApp portletApp = portlet.getPortletApp();

		PortletConfig portletConfig = PortletConfigFactoryUtil.create(
			portlet, servletContext);

		PortletContext portletContext = portletConfig.getPortletContext();

		Set<PortletFilter> portletFilters = portletApp.getPortletFilters();

		for (PortletFilter portletFilter : portletFilters) {
			PortletFilterFactory.create(portletFilter, portletContext);
		}

		Set<PortletURLListener> portletURLListeners =
			portletApp.getPortletURLListeners();

		for (PortletURLListener portletURLListener : portletURLListeners) {
			PortletURLListenerFactory.create(portletURLListener);
		}
	}

	protected List<Portlet> initPortlets(PluginPackage pluginPackage)
		throws Exception {

		ServletContext servletContext = getServletContext();

		String[] xmls = new String[] {
			HttpUtil.URLtoString(
				servletContext.getResource(
					"/WEB-INF/" + Portal.PORTLET_XML_FILE_NAME_CUSTOM)),
			HttpUtil.URLtoString(
				servletContext.getResource("/WEB-INF/portlet-ext.xml")),
			HttpUtil.URLtoString(
				servletContext.getResource("/WEB-INF/liferay-portlet.xml")),
			HttpUtil.URLtoString(
				servletContext.getResource("/WEB-INF/liferay-portlet-ext.xml")),
			HttpUtil.URLtoString(
				servletContext.getResource("/WEB-INF/web.xml"))
		};

		PortletLocalServiceUtil.initEAR(servletContext, xmls, pluginPackage);

		PortletBagFactory portletBagFactory = new PortletBagFactory();

		portletBagFactory.setClassLoader(
			PortalClassLoaderUtil.getClassLoader());
		portletBagFactory.setServletContext(servletContext);
		portletBagFactory.setWARFile(false);

		List<Portlet> portlets = PortletLocalServiceUtil.getPortlets();

		for (int i = 0; i < portlets.size(); i++) {
			Portlet portlet = portlets.get(i);

			portletBagFactory.create(portlet);

			if (i == 0) {
				initPortletApp(portlet, servletContext);
			}
		}

		return portlets;
	}

	protected void initResourceActions(List<Portlet> portlets)
		throws Exception {

		if (PropsValues.PERMISSIONS_USER_CHECK_ALGORITHM < 6) {
			if (_log.isWarnEnabled()) {
				StringBundler sb = new StringBundler(8);

				sb.append("Liferay is configured to use permission algorithm ");
				sb.append(PropsValues.PERMISSIONS_USER_CHECK_ALGORITHM);
				sb.append(". Versions after 6.1 will only support algorithm ");
				sb.append("6 and above. Please sign in as an administrator, ");
				sb.append("go to the Control Panel, select \"Server ");
				sb.append("Administration\", select the \"Data Migration\" ");
				sb.append("tab, and convert from this legacy permission ");
				sb.append("algorithm as soon as possible.");

				_log.warn(sb.toString());
			}

			return;
		}

		Iterator<Portlet> itr = portlets.iterator();

		while (itr.hasNext()) {
			Portlet portlet = itr.next();

			List<String> portletActions =
				ResourceActionsUtil.getPortletResourceActions(portlet);

			ResourceActionLocalServiceUtil.checkResourceActions(
				portlet.getPortletId(), portletActions);

			List<String> modelNames =
				ResourceActionsUtil.getPortletModelResources(
					portlet.getPortletId());

			for (String modelName : modelNames) {
				List<String> modelActions =
					ResourceActionsUtil.getModelResourceActions(modelName);

				ResourceActionLocalServiceUtil.checkResourceActions(
					modelName, modelActions);
			}
		}
	}

	protected void initResourceCodes(List<Portlet> portlets) throws Exception {
		long[] companyIds = PortalInstances.getCompanyIdsBySQL();

		Iterator<Portlet> itr = portlets.iterator();

		while (itr.hasNext()) {
			Portlet portlet = itr.next();

			List<String> modelNames =
				ResourceActionsUtil.getPortletModelResources(
					portlet.getPortletId());

			for (long companyId : companyIds) {
				ResourceCodeLocalServiceUtil.checkResourceCodes(
					companyId, portlet.getPortletId());

				for (String modelName : modelNames) {
					ResourceCodeLocalServiceUtil.checkResourceCodes(
						companyId, modelName);
				}
			}
		}
	}

	protected void initServletContextPool() throws Exception {
		ServletContext servletContext = getServletContext();

		String contextPath = PortalUtil.getPathContext();

		ServletContextPool.put(contextPath, servletContext);
	}

	protected void initSocial(PluginPackage pluginPackage) throws Exception {
		ClassLoader classLoader = PortalClassLoaderUtil.getClassLoader();

		ServletContext servletContext = getServletContext();

		String[] xmls = new String[] {
			HttpUtil.URLtoString(
				servletContext.getResource("/WEB-INF/liferay-social.xml")),
			HttpUtil.URLtoString(
				servletContext.getResource("/WEB-INF/liferay-social-ext.xml"))
		};

		SocialConfigurationUtil.read(classLoader, xmls);
	}

	protected void initThemes(
			PluginPackage pluginPackage, List<Portlet> portlets)
		throws Exception {

		ServletContext servletContext = getServletContext();

		String[] xmls = new String[] {
			HttpUtil.URLtoString(
				servletContext.getResource(
					"/WEB-INF/liferay-look-and-feel.xml")),
			HttpUtil.URLtoString(
				servletContext.getResource(
					"/WEB-INF/liferay-look-and-feel-ext.xml"))
		};

		ThemeLocalServiceUtil.init(
			servletContext, null, true, xmls, pluginPackage);
	}

	protected void initWebSettings() throws Exception {
		ServletContext servletContext = getServletContext();

		String xml = HttpUtil.URLtoString(
			servletContext.getResource("/WEB-INF/web.xml"));

		checkWebSettings(xml);
	}

	protected long loginUser(
			HttpServletRequest request, HttpServletResponse response,
			long userId, String remoteUser)
		throws PortalException, SystemException {

		if ((userId > 0) || (remoteUser == null)) {
			return userId;
		}

		userId = GetterUtil.getLong(remoteUser);

		EventsProcessorUtil.process(
			PropsKeys.LOGIN_EVENTS_PRE, PropsValues.LOGIN_EVENTS_PRE, request,
			response);

		User user = UserLocalServiceUtil.getUserById(userId);

		if (PropsValues.USERS_UPDATE_LAST_LOGIN) {
			UserLocalServiceUtil.updateLastLogin(
				userId, request.getRemoteAddr());
		}

		HttpSession session = request.getSession();

		session.setAttribute(WebKeys.USER, user);
		session.setAttribute(WebKeys.USER_ID, new Long(userId));
		session.setAttribute(Globals.LOCALE_KEY, user.getLocale());

		EventsProcessorUtil.process(
			PropsKeys.LOGIN_EVENTS_POST, PropsValues.LOGIN_EVENTS_POST,
			request, response);

		return userId;
	}

	protected boolean processCompanyInactiveRequest(
			HttpServletRequest request, HttpServletResponse response,
			long companyId)
		throws IOException {

		if (PortalInstances.isCompanyActive(companyId)) {
			return false;
		}

		processInactiveRequest(
			request, response,
			"this-instance-is-inactive-please-contact-the-administrator");

		return true;
	}

	protected boolean processGroupInactiveRequest(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException, PortalException, SystemException {

		long plid = ParamUtil.getLong(request, "p_l_id");

		if (plid <= 0) {
			return false;
		}

		Layout layout = LayoutLocalServiceUtil.getLayout(plid);

		Group group = layout.getGroup();

		if (group.isActive()) {
			return false;
		}

		processInactiveRequest(
			request, response,
			"this-site-is-inactive-please-contact-the-administrator");

		return true;
	}

	protected void processGlobalShutdownEvents() throws Exception {
		EventsProcessorUtil.process(
			PropsKeys.GLOBAL_SHUTDOWN_EVENTS,
			PropsValues.GLOBAL_SHUTDOWN_EVENTS);

		super.destroy();
	}

	protected void processGlobalStartupEvents() throws Exception {
		EventsProcessorUtil.process(
			PropsKeys.GLOBAL_STARTUP_EVENTS, PropsValues.GLOBAL_STARTUP_EVENTS);
	}

	protected void processInactiveRequest(
			HttpServletRequest request, HttpServletResponse response,
			String messageKey)
		throws IOException {

		response.setContentType(ContentTypes.TEXT_HTML_UTF8);

		Locale locale = LocaleUtil.getDefault();

		String message = LanguageUtil.get(locale, messageKey);

		String html = ContentUtil.get(
			"com/liferay/portal/dependencies/inactive.html");

		html = StringUtil.replace(html, "[$MESSAGE$]", message);

		ServletOutputStream servletOutputStream = response.getOutputStream();

		servletOutputStream.print(html);
	}

	protected boolean processMaintenanceRequest(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		if (!MaintenanceUtil.isMaintaining()) {
			return false;
		}

		RequestDispatcher requestDispatcher = request.getRequestDispatcher(
			"/html/portal/maintenance.jsp");

		requestDispatcher.include(request, response);

		return true;
	}

	protected void processServicePost(
		HttpServletRequest request, HttpServletResponse response) {

		try {
			EventsProcessorUtil.process(
				PropsKeys.SERVLET_SERVICE_EVENTS_POST,
				PropsValues.SERVLET_SERVICE_EVENTS_POST, request, response);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		if (_HTTP_HEADER_VERSION_VERBOSITY_DEFAULT) {
		}
		else if (_HTTP_HEADER_VERSION_VERBOSITY_PARTIAL) {
			response.addHeader(
				_LIFERAY_PORTAL_REQUEST_HEADER, ReleaseInfo.getName());
		}
		else {
			response.addHeader(
				_LIFERAY_PORTAL_REQUEST_HEADER, ReleaseInfo.getReleaseInfo());
		}

		ThreadLocalCacheManager.clearAll(Lifecycle.REQUEST);
	}

	protected boolean processServicePre(
			HttpServletRequest request, HttpServletResponse response,
			long userId)
		throws IOException, ServletException {

		try {
			EventsProcessorUtil.process(
				PropsKeys.SERVLET_SERVICE_EVENTS_PRE,
				PropsValues.SERVLET_SERVICE_EVENTS_PRE, request, response);
		}
		catch (Exception e) {
			Throwable cause = e.getCause();

			if (cause instanceof NoSuchLayoutException) {
				sendError(
					HttpServletResponse.SC_NOT_FOUND, cause, request, response);

				return true;
			}
			else if (cause instanceof PrincipalException) {
				processServicePrePrincipalException(
					cause, userId, request, response);

				return true;
			}

			_log.error(e, e);

			request.setAttribute(PageContext.EXCEPTION, e);

			ServletContext servletContext = getServletContext();

			StrutsUtil.forward(
				PropsValues.SERVLET_SERVICE_EVENTS_PRE_ERROR_PAGE,
				servletContext, request, response);

			return true;
		}

		return false;
	}

	protected void processServicePrePrincipalException(
			Throwable t, long userId, HttpServletRequest request,
			HttpServletResponse response)
		throws IOException, ServletException {

		if (userId > 0) {
			sendError(
				HttpServletResponse.SC_UNAUTHORIZED, t, request, response);

			return;
		}

		String redirect = PortalUtil.getPathMain().concat("/portal/login");

		String currentURL = PortalUtil.getCurrentURL(request);

		redirect = HttpUtil.addParameter(redirect, "redirect", currentURL);

		long plid = ParamUtil.getLong(request, "p_l_id");

		if (plid > 0) {
			try {
				Layout layout = LayoutLocalServiceUtil.getLayout(plid);

				if (layout.getGroup().isStagingGroup()) {
					Group group = GroupLocalServiceUtil.getGroup(
						layout.getCompanyId(), GroupConstants.GUEST);

					plid = group.getDefaultPublicPlid();
				}
				else if (layout.isPrivateLayout()) {
					plid = LayoutLocalServiceUtil.getDefaultPlid(
						layout.getGroupId(), false);
				}

				redirect = HttpUtil.addParameter(redirect, "p_l_id", plid);
			}
			catch (Exception e) {
			}
		}

		response.sendRedirect(redirect);
	}

	protected boolean processShutdownRequest(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		if (!ShutdownUtil.isShutdown()) {
			return false;
		}

		String messageKey = ShutdownUtil.getMessage();

		if (Validator.isNull(messageKey)) {
			messageKey = "the-system-is-shutdown-please-try-again-later";
		}

		processInactiveRequest(request, response, messageKey);

		return true;
	}

	protected void processStartupEvents() throws Exception {
		StartupAction startupAction = new StartupAction();

		startupAction.run(null);
	}

	protected HttpServletRequest protectRequest(
		HttpServletRequest request, String remoteUser) {

		// WebSphere will not return the remote user unless you are
		// authenticated AND accessing a protected path. Other servers will
		// return the remote user for all threads associated with an
		// authenticated user. We use ProtectedServletRequest to ensure we get
		// similar behavior across all servers.

		return new ProtectedServletRequest(request, remoteUser);
	}

	protected void sendError(
			int status, Throwable t, HttpServletRequest request,
			HttpServletResponse response)
		throws IOException, ServletException {

		DynamicServletRequest dynamicRequest = new DynamicServletRequest(
			request);

		// Reset layout params or there will be an infinite loop

		dynamicRequest.setParameter("p_l_id", StringPool.BLANK);

		dynamicRequest.setParameter("groupId", StringPool.BLANK);
		dynamicRequest.setParameter("layoutId", StringPool.BLANK);
		dynamicRequest.setParameter("privateLayout", StringPool.BLANK);

		PortalUtil.sendError(status, (Exception)t, dynamicRequest, response);
	}

	protected void setPortalPort(HttpServletRequest request) {
		PortalUtil.setPortalPort(request);
	}

	protected void setPrincipal(
		long userId, String remoteUser, String password) {

		if ((userId == 0) && (remoteUser == null)) {
			return;
		}

		String name = String.valueOf(userId);

		if (!PropsValues.PORTAL_JAAS_ENABLE) {
			if (remoteUser != null) {
				name = remoteUser;
			}
		}

		PrincipalThreadLocal.setName(name);

		PrincipalThreadLocal.setPassword(password);
	}

	private static final boolean _HTTP_HEADER_VERSION_VERBOSITY_DEFAULT =
		PropsValues.HTTP_HEADER_VERSION_VERBOSITY.equalsIgnoreCase(
			ReleaseInfo.getName());

	private static final boolean _HTTP_HEADER_VERSION_VERBOSITY_PARTIAL =
		PropsValues.HTTP_HEADER_VERSION_VERBOSITY.equalsIgnoreCase("partial");

	private static final String _LIFERAY_PORTAL_REQUEST_HEADER =
		"Liferay-Portal";

	private static Log _log = LogFactoryUtil.getLog(MainServlet.class);

}