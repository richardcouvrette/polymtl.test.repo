<%--
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
--%>

<%@ include file="/html/portlet/layouts_admin/init.jsp" %>

<%
Layout selLayout = (Layout)request.getAttribute("edit_pages.jsp-selLayout");
LayoutSet selLayoutSet = (LayoutSet)request.getAttribute("edit_pages.jsp-selLayoutSet");

List<Theme> themes = (List<Theme>)request.getAttribute("edit_pages.jsp-themes");
List<ColorScheme> colorSchemes = (List<ColorScheme>)request.getAttribute("edit_pages.jsp-colorSchemes");
Theme selTheme = (Theme)request.getAttribute("edit_pages.jsp-selTheme");
ColorScheme selColorScheme = (ColorScheme)request.getAttribute("edit_pages.jsp-selColorScheme");
String device = (String)request.getAttribute("edit_pages.jsp-device");
boolean editable = (Boolean)request.getAttribute("edit_pages.jsp-editable");

Map<String, ThemeSetting> configurableSettings = selTheme.getConfigurableSettings();
%>

<div class="lfr-theme-list">
	<div class="float-container lfr-current-theme">
		<h3><liferay-ui:message key="current-theme" /></h3>

		<div>
			<img alt="<%= selTheme.getName() %>" class="theme-screenshot" onclick="document.getElementById('<portlet:namespace /><%= device %>SelTheme').checked = true;" src="<%= selTheme.getStaticResourcePath() %><%= selTheme.getImagesPath() %>/thumbnail.png" title="<%= selTheme.getName() %>" />

			<div class="theme-details">
				<c:choose>
					<c:when test="<%= editable %>">
						<aui:input checked="<%= true %>" cssClass="selected-theme theme-title" id='<%= device + "SelTheme" %>' label="<%= selTheme.getName() %>" name='<%= device + "ThemeId" %>' type="radio" value="<%= selTheme.getThemeId() %>" />
					</c:when>
					<c:otherwise>
						<div class="selected-theme theme-title"><%= selTheme.getName() %></div>
					</c:otherwise>
				</c:choose>

				<dl class="theme-fields">

					<%
					PluginPackage selPluginPackage = selTheme.getPluginPackage();
					%>

					<c:if test="<%= (selPluginPackage != null) && Validator.isNotNull(selPluginPackage.getShortDescription()) %>">
						<dt>
							<liferay-ui:message key="description" />
						</dt>
						<dd>
							<%= selPluginPackage.getShortDescription() %>
						</dd>
					</c:if>

					<c:if test="<%= editable && (selPluginPackage != null) && Validator.isNotNull(selPluginPackage.getAuthor()) %>">
						<dt>
							<liferay-ui:message key="author" />
						</dt>
						<dd>
							<a href="<%= selPluginPackage.getPageURL() %>"><%= selPluginPackage.getAuthor() %></a>
						</dd>
					</c:if>

					<c:if test="<%= !editable && !colorSchemes.isEmpty() && Validator.isNotNull(selColorScheme) %>">
						<dt class="current-color-scheme">
							<liferay-ui:message key="color-scheme" />
						</dt>
						<dd>
							<%= selColorScheme.getName() %>
						</dd>
					</c:if>

					<c:if test="<%= !editable && !configurableSettings.isEmpty() %>">

						<%
						for (String name : configurableSettings.keySet()) {
						%>

							<dt class="theme-setting">
								<liferay-ui:message key="<%= name %>" />
							</dt>
							<dd>
								<%= selLayoutSet.getThemeSetting(name, device) %>
							</dd>

						<%
						}
						%>

					</c:if>
				</dl>
			</div>
		</div>

		<c:if test="<%= editable %>">
			<c:if test="<%= !colorSchemes.isEmpty() || !configurableSettings.isEmpty() %>">
				<liferay-ui:panel-container extended="<%= true %>" id='<%= device + "layoutsAdminLookAndFeelPanelContainer" %>' persistState="<%= true %>">
					<c:if test="<%= !colorSchemes.isEmpty() %>">
						<liferay-ui:panel collapsible="<%= true %>" extended="<%= false %>" id='<%= device + "layoutsAdminLookAndFeelColorsPanel" %>' persistState="<%= true %>" title='<%= LanguageUtil.format(pageContext, "color-schemes-x", colorSchemes.size()) %>'>
							<aui:fieldset cssCclass="color-schemes">
								<div class="lfr-component lfr-theme-list">

									<%
									for (int i = 0; i < colorSchemes.size(); i++) {
										ColorScheme curColorScheme = colorSchemes.get(i);

										String cssClass = StringPool.BLANK;

										if (selColorScheme.getColorSchemeId().equals(curColorScheme.getColorSchemeId())) {
											cssClass = "selected-color-scheme";
										}
									%>

								<div class="<%= cssClass %> theme-entry">
									<img alt="" class="modify-link theme-thumbnail" onclick="document.getElementById('<portlet:namespace /><%= device %>ColorSchemeId<%= i %>').checked = true;" src="<%= selTheme.getStaticResourcePath() %><%= curColorScheme.getColorSchemeThumbnailPath() %>/thumbnail.png" title="<%= curColorScheme.getName() %>" />

										<aui:input cssClass="theme-title" checked="<%= selColorScheme.getColorSchemeId().equals(curColorScheme.getColorSchemeId()) %>" id='<%= device + "ColorSchemeId" + i %>' label="<%= curColorScheme.getName() %>" name='<%= device + "ColorSchemeId" %>' type="radio" value="<%= curColorScheme.getColorSchemeId() %>" />
									</div>

									<%
									}
									%>

								</div>
							</aui:fieldset>
						</liferay-ui:panel>
					</c:if>

					<c:if test="<%= !configurableSettings.isEmpty() %>">
						<liferay-ui:panel collapsible="<%= true %>" extended="<%= false %>" id='<%= device + "layoutsAdminLookAndFeelSettingsPanel" %>' persistState="<%= true %>" title="settings">
							<aui:fieldset>

								<%
								for (String name : configurableSettings.keySet()) {
									ThemeSetting themeSetting = configurableSettings.get(name);

									String type = GetterUtil.getString(themeSetting.getType(), "text");
									String value = StringPool.BLANK;

									if (selLayout != null) {
										value = selLayout.getThemeSetting(name, device);
									}
									else {
										value = selLayoutSet.getThemeSetting(name, device);
									}

									String propertyName = device + "ThemeSettingsProperties--" + name + StringPool.DOUBLE_DASH;
								%>

									<c:choose>
										<c:when test='<%= type.equals("checkbox") || type.equals("text") || type.equals("textarea") %>'>
											<aui:input label="<%= name %>" name="<%= propertyName %>" type="<%= type %>" value="<%= value %>" />
										</c:when>
										<c:when test='<%= type.equals("select") %>'>
											<aui:select label="<%= name %>" name="<%= propertyName %>">

												<%
												for (String option : themeSetting.getOptions()) {
												%>

													<aui:option label="<%= option %>" selected="<%= option.equals(value) %>" />

												<%
												}
												%>

											</aui:select>
										</c:when>
									</c:choose>

									<c:if test="<%= Validator.isNotNull(themeSetting.getScript()) %>">
										<aui:script position="inline">
											<%= StringUtil.replace(themeSetting.getScript(), "[@NAMESPACE@]", renderResponse.getNamespace()) %>
										</aui:script>
									</c:if>

								<%
								}
								%>

							</aui:fieldset>
						</liferay-ui:panel>
					</c:if>
				</liferay-ui:panel-container>
			</c:if>
		</c:if>
	</div>

	<c:if test="<%= editable %>">
		<div class="float-container lfr-available-themes">
			<h3>
				<span class="header-title">
					<%= LanguageUtil.format(pageContext, "available-themes-x", (themes.size() - 1)) %>
				</span>

				<c:if test="<%= permissionChecker.isOmniadmin() && PrefsPropsUtil.getBoolean(PropsKeys.AUTO_DEPLOY_ENABLED, PropsValues.AUTO_DEPLOY_ENABLED) %>">

					<%
					PortletURL installPluginsURL = PortletURLFactoryUtil.create(request, PortletKeys.PLUGIN_INSTALLER, themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);

					installPluginsURL.setWindowState(LiferayWindowState.MAXIMIZED);
					installPluginsURL.setPortletMode(PortletMode.VIEW);

					installPluginsURL.setParameter("struts_action", "/plugin_installer/view");
					installPluginsURL.setParameter("backURL", currentURL);
					installPluginsURL.setParameter("tabs2", "theme-plugins");
					%>

					<span class="install-themes">
						<a href="<%= installPluginsURL %>" id="<portlet:namespace />installMore"><liferay-ui:message key="install-more" /></a>
					</span>
				</c:if>
			</h3>

			<c:if test="<%= themes.size() > 1 %>">
				<ul class="lfr-component lfr-theme-list">

					<%
					for (int i = 0; i < themes.size(); i++) {
						Theme curTheme = themes.get(i);

						if (!selTheme.getThemeId().equals(curTheme.getThemeId())) {
					%>

							<li>
								<div class="theme-entry">
									<img alt="" class="modify-link theme-thumbnail" onclick="document.getElementById('<portlet:namespace /><%= device %>ThemeId<%= i %>').checked = true;" src="<%= curTheme.getStaticResourcePath() %><%= curTheme.getImagesPath() %>/thumbnail.png" title="<%= curTheme.getName() %>" />

									<aui:input cssClass="theme-title" id='<%= device + "ThemeId" + i %>' label="<%= curTheme.getName() %>" name='<%= device + "ThemeId" %>' type="radio" value="<%= curTheme.getThemeId() %>" />
								</div>
							</li>

					<%
						}
					}
					%>

				</ul>
			</c:if>
		</div>
	</c:if>
</div>

<c:if test="<%= editable && permissionChecker.isOmniadmin() && PrefsPropsUtil.getBoolean(PropsKeys.AUTO_DEPLOY_ENABLED, PropsValues.AUTO_DEPLOY_ENABLED) %>">
	<aui:script use="aui-base">
		if (window.parent) {
			var installMoreLink = A.one('#<portlet:namespace />installMore');

			if (installMoreLink) {
				installMoreLink.on(
					'click',
					function(event) {
						event.preventDefault();

						window.parent.location = installMoreLink.attr('href');
					}
				);
			}
		}
	</aui:script>
</c:if>