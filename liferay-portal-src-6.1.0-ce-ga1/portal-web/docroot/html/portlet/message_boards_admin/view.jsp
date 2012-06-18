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

<%@ include file="/html/portlet/message_boards/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "message-boards-home");

MBCategory category = (MBCategory)request.getAttribute(WebKeys.MESSAGE_BOARDS_CATEGORY);

long categoryId = MBUtil.getCategoryId(request, category);

MBCategoryDisplay categoryDisplay = new MBCategoryDisplayImpl(scopeGroupId, categoryId);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("struts_action", "/message_boards/view");
portletURL.setParameter("tabs1", tabs1);
portletURL.setParameter("mbCategoryId", String.valueOf(categoryId));

request.setAttribute("view.jsp-viewCategory", Boolean.TRUE.toString());
%>

<liferay-ui:tabs
	names="message-boards-home,recent-posts,statistics,banned-users"
	url="<%= portletURL.toString() %>"
/>

<c:choose>
	<c:when test='<%= tabs1.equals("message-boards-home") %>'>
		<liferay-portlet:renderURL varImpl="searchURL">
			<portlet:param name="struts_action" value="/message_boards/search" />
		</liferay-portlet:renderURL>

		<div class="category-search">
			<aui:form action="<%= searchURL %>" method="get" name="searchFm">
				<liferay-portlet:renderURLParams varImpl="searchURL" />
				<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
				<aui:input name="breadcrumbsCategoryId" type="hidden" value="<%= categoryId %>" />
				<aui:input name="searchCategoryId" type="hidden" value="<%= categoryId %>" />

				<span class="aui-search-bar">
					<aui:input id="keywords1" inlineField="<%= true %>" label="" name="keywords" size="30" title="search-messages" type="text" />

					<aui:button type="submit" value="search" />
				</span>
			</aui:form>
		</div>

		<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) && !themeDisplay.isFacebook() %>">
			<aui:script>
				Liferay.Util.focusFormField(document.<portlet:namespace />searchFm.<portlet:namespace />keywords);
			</aui:script>
		</c:if>

		<br />

		<%
		boolean showAddCategoryButton = MBCategoryPermission.contains(permissionChecker, scopeGroupId, categoryId, ActionKeys.ADD_CATEGORY);
		boolean showAddMessageButton = MBCategoryPermission.contains(permissionChecker, scopeGroupId, categoryId, ActionKeys.ADD_MESSAGE);
		boolean showPermissionsButton = GroupPermissionUtil.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS);

		if (showAddMessageButton && !themeDisplay.isSignedIn()) {
			if (!allowAnonymousPosting) {
				showAddMessageButton = false;
			}
		}
		%>

		<c:if test="<%= showAddCategoryButton || showAddMessageButton || showPermissionsButton %>">
			<div class="category-buttons">
				<c:if test="<%= showAddCategoryButton %>">
					<portlet:renderURL var="editCategoryURL">
						<portlet:param name="struts_action" value="/message_boards/edit_category" />
						<portlet:param name="redirect" value="<%= currentURL %>" />
						<portlet:param name="parentCategoryId" value="<%= String.valueOf(categoryId) %>" />
					</portlet:renderURL>

					<aui:button href='<%= editCategoryURL %>' value='<%= (category == null) ? "add-category" : "add-subcategory" %>' />
				</c:if>

				<c:if test="<%= showAddMessageButton %>">
					<portlet:renderURL var="editMessageURL">
						<portlet:param name="struts_action" value="/message_boards/edit_message" />
						<portlet:param name="redirect" value="<%= currentURL %>" />
						<portlet:param name="mbCategoryId" value="<%= String.valueOf(categoryId) %>" />
					</portlet:renderURL>

					<aui:button href='<%= editMessageURL %>' value="post-new-thread" />
				</c:if>

				<c:if test="<%= showPermissionsButton %>">

					<%
					String modelResource = "com.liferay.portlet.messageboards";
					String modelResourceDescription = themeDisplay.getScopeGroupName();
					String resourcePrimKey = String.valueOf(scopeGroupId);

					if (category != null) {
						modelResource = MBCategory.class.getName();
						modelResourceDescription = category.getName();
						resourcePrimKey = String.valueOf(category.getCategoryId());
					}
					%>

					<liferay-security:permissionsURL
						modelResource="<%= modelResource %>"
						modelResourceDescription="<%= HtmlUtil.escape(modelResourceDescription) %>"
						resourcePrimKey="<%= resourcePrimKey %>"
						var="permissionsURL"
					/>

					<aui:button href="<%= permissionsURL %>" value="permissions" />
				</c:if>
			</div>
		</c:if>

		<c:if test="<%= category != null %>">

			<%
			long parentCategoryId = category.getParentCategoryId();
			String parentCategoryName = LanguageUtil.get(pageContext, "message-boards-home");

			if (!category.isRoot()) {
				MBCategory parentCategory = MBCategoryLocalServiceUtil.getCategory(parentCategoryId);

				parentCategoryId = parentCategory.getCategoryId();
				parentCategoryName = parentCategory.getName();
			}
			%>

			<portlet:renderURL var="backURL">
				<portlet:param name="struts_action" value="/message_boards/view" />
				<portlet:param name="mbCategoryId" value="<%= String.valueOf(parentCategoryId) %>" />
			</portlet:renderURL>

			<liferay-ui:header
				backLabel="<%= parentCategoryName %>"
				backURL="<%= backURL.toString() %>"
				localizeTitle="<%= false %>"
				title="<%= category.getName() %>"
			/>
		</c:if>

		<liferay-ui:panel-container cssClass="message-boards-panels" extended="<%= false %>" id="messageBoardsPanelContainer" persistState="<%= true %>">

			<%
			int categoriesCount = MBCategoryServiceUtil.getCategoriesCount(scopeGroupId, categoryId);
			%>

			<c:if test="<%= categoriesCount > 0 %>">
				<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" id="messageBoardsCategoriesPanel" persistState="<%= true %>" title='<%= (category != null) ? "subcategories" : "categories" %>'>
					<aui:form action="<%= portletURL.toString() %>" method="get" name="fm">
						<aui:input name="<%= Constants.CMD %>" type="hidden" />
						<aui:input name="redirect" type="hidden" value="<%= portletURL.toString() %>" />
						<aui:input name="deleteCategoryIds" type="hidden" />

						<liferay-ui:search-container
							curParam="cur1"
							deltaConfigurable="<%= false %>"
							headerNames="category,categories,threads,posts"
							iteratorURL="<%= portletURL %>"
							rowChecker="<%= new RowChecker(renderResponse) %>"
						>
							<liferay-ui:search-container-results
								results="<%= MBCategoryServiceUtil.getCategories(scopeGroupId, categoryId, searchContainer.getStart(), searchContainer.getEnd()) %>"
								total="<%= categoriesCount %>"
							/>

							<liferay-ui:search-container-row
								className="com.liferay.portlet.messageboards.model.MBCategory"
								escapedModel="<%= true %>"
								keyProperty="categoryId"
								modelVar="curCategory"
							>
								<liferay-portlet:renderURL varImpl="rowURL">
									<portlet:param name="struts_action" value="/message_boards/view" />
									<portlet:param name="mbCategoryId" value="<%= String.valueOf(curCategory.getCategoryId()) %>" />
								</liferay-portlet:renderURL>

								<%@ include file="/html/portlet/message_boards/category_columns.jspf" %>
							</liferay-ui:search-container-row>

							<br>

							<aui:button onClick='<%= renderResponse.getNamespace() + "deleteCategories();" %>' value="delete" />

							<div class="separator"><!-- --></div>

							<liferay-ui:search-iterator />
						</liferay-ui:search-container>
					</aui:form>
				</liferay-ui:panel>
			</c:if>

			<liferay-ui:panel collapsible="<%= true %>" cssClass="threads-panel" extended="<%= true %>" id="messageBoardsThreadsPanel" persistState="<%= true %>" title="threads">
				<aui:form action="<%= portletURL.toString() %>" method="get" name="fm1">
					<aui:input name="<%= Constants.CMD %>" type="hidden" />
					<aui:input name="redirect" type="hidden" value="<%= portletURL.toString() %>" />
					<aui:input name="threadIds" type="hidden" />

					<liferay-ui:search-container
						curParam="cur2"
						emptyResultsMessage="there-are-no-threads-in-this-category"
						headerNames="thread,flag,started-by,posts,views,last-post"
						iteratorURL="<%= portletURL %>"
						rowChecker="<%= new RowChecker(renderResponse) %>"
					>
						<liferay-ui:search-container-results
							results="<%= MBThreadServiceUtil.getThreads(scopeGroupId, categoryId, WorkflowConstants.STATUS_APPROVED, searchContainer.getStart(), searchContainer.getEnd()) %>"
							total="<%= MBThreadServiceUtil.getThreadsCount(scopeGroupId, categoryId, WorkflowConstants.STATUS_APPROVED) %>"
						/>

						<liferay-ui:search-container-row
							className="com.liferay.portlet.messageboards.model.MBThread"
							keyProperty="threadId"
							modelVar="thread"
						>

							<%
							MBMessage message = null;

							try {
								message = MBMessageLocalServiceUtil.getMessage(thread.getRootMessageId());
							}
							catch (NoSuchMessageException nsme) {
								_log.error("Thread requires missing root message id " + thread.getRootMessageId());

								message = new MBMessageImpl();

								row.setSkip(true);
							}

							message = message.toEscapedModel();

							row.setBold(!MBThreadFlagLocalServiceUtil.hasThreadFlag(themeDisplay.getUserId(), thread));
							row.setObject(new Object[] {message});
							row.setRestricted(!MBMessagePermission.contains(permissionChecker, message, ActionKeys.VIEW));
							%>

							<liferay-portlet:renderURL varImpl="rowURL">
								<portlet:param name="struts_action" value="/message_boards/view_message" />
								<portlet:param name="messageId" value="<%= String.valueOf(message.getMessageId()) %>" />
							</liferay-portlet:renderURL>

							<liferay-ui:search-container-column-text
								buffer="buffer"
								href="<%= rowURL %>"
								name="thread"
							>

								<%
								String[] threadPriority = MBUtil.getThreadPriority(preferences, themeDisplay.getLanguageId(), thread.getPriority(), themeDisplay);

								if ((threadPriority != null) && (thread.getPriority() > 0)) {
									buffer.append("<img class=\"thread-priority\" alt=\"");
									buffer.append(threadPriority[0]);
									buffer.append("\" src=\"");
									buffer.append(threadPriority[1]);
									buffer.append("\" title=\"");
									buffer.append(threadPriority[0]);
									buffer.append("\" />");
								}

								if (thread.isLocked()) {
									buffer.append("<img class=\"thread-priority\" alt=\"");
									buffer.append(LanguageUtil.get(pageContext, "thread-locked"));
									buffer.append("\" src=\"");
									buffer.append(themeDisplay.getPathThemeImages() + "/common/lock.png");
									buffer.append("\" title=\"");
									buffer.append(LanguageUtil.get(pageContext, "thread-locked"));
									buffer.append("\" />");
								}

								buffer.append(message.getSubject());
								%>

							</liferay-ui:search-container-column-text>

							<liferay-ui:search-container-column-text
								buffer="buffer"
								href="<%= rowURL %>"
								name="flag"
							>

								<%
								if (MBThreadLocalServiceUtil.hasAnswerMessage(thread.getThreadId())) {
									buffer.append(LanguageUtil.get(pageContext, "resolved"));
								}
								else if (thread.isQuestion()) {
									buffer.append(LanguageUtil.get(pageContext, "waiting-for-an-answer"));
								}
								%>

							</liferay-ui:search-container-column-text>

							<liferay-ui:search-container-column-text
								href="<%= rowURL %>"
								name="started-by"
								value='<%= message.isAnonymous() ? LanguageUtil.get(pageContext, "anonymous") : HtmlUtil.escape(PortalUtil.getUserName(message.getUserId(), message.getUserName())) %>'
							/>

							<liferay-ui:search-container-column-text
								href="<%= rowURL %>"
								name="posts"
								value="<%= String.valueOf(thread.getMessageCount()) %>"
							/>

							<liferay-ui:search-container-column-text
								href="<%= rowURL %>"
								name="views"
								value="<%= String.valueOf(thread.getViewCount()) %>"
							/>

							<liferay-ui:search-container-column-text
								buffer="buffer"
								href="<%= rowURL %>"
								name="last-post"
							>

								<%
								if (thread.getLastPostDate() == null) {
									buffer.append(LanguageUtil.get(pageContext, "none"));
								}
								else {
									buffer.append(LanguageUtil.get(pageContext, "date"));
									buffer.append(": ");
									buffer.append(dateFormatDateTime.format(thread.getLastPostDate()));

									String lastPostByUserName = HtmlUtil.escape(PortalUtil.getUserName(thread.getLastPostByUserId(), StringPool.BLANK));

									if (Validator.isNotNull(lastPostByUserName)) {
										buffer.append("<br />");
										buffer.append(LanguageUtil.get(pageContext, "by"));
										buffer.append(": ");
										buffer.append(lastPostByUserName);
									}
								}
								%>

							</liferay-ui:search-container-column-text>

							<liferay-ui:search-container-column-jsp
								align="right"
								path="/html/portlet/message_boards/message_action.jsp"
							/>
						</liferay-ui:search-container-row>

						<br>

						<aui:button onClick='<%= renderResponse.getNamespace() + "deleteThreads();" %>' value="delete" />

						<aui:button onClick='<%= renderResponse.getNamespace() + "lockThreads();" %>' value="lock" />

						<aui:button onClick='<%= renderResponse.getNamespace() + "unlockThreads();" %>' value="unlock" />

						<div class="separator"><!-- --></div>

						<liferay-ui:search-iterator />
					</liferay-ui:search-container>
				</aui:form>
			</liferay-ui:panel>
		</liferay-ui:panel-container>

		<%
		if (category != null) {
			PortalUtil.setPageSubtitle(category.getName(), request);
			PortalUtil.setPageDescription(category.getDescription(), request);

			MBUtil.addPortletBreadcrumbEntries(category, request, renderResponse);
		}
		%>

	</c:when>
	<c:when test='<%= tabs1.equals("recent-posts") %>'>

		<%
		long groupThreadsUserId = ParamUtil.getLong(request, "groupThreadsUserId");

		if (groupThreadsUserId > 0) {
			portletURL.setParameter("groupThreadsUserId", String.valueOf(groupThreadsUserId));
		}
		%>

		<c:if test='<%= (groupThreadsUserId > 0) %>'>
			<div class="portlet-msg-info">
				<liferay-ui:message key="filter-by-user" />: <%= HtmlUtil.escape(PortalUtil.getUserName(groupThreadsUserId, StringPool.BLANK)) %>
			</div>
		</c:if>

		<aui:form action="<%= portletURL.toString() %>" method="get" name="fm1">
			<aui:input name="<%= Constants.CMD %>" type="hidden" />
			<aui:input name="redirect" type="hidden" value="<%= portletURL.toString() %>" />
			<aui:input name="threadIds" type="hidden" />

			<liferay-ui:search-container
				headerNames="thread,started-by,posts,views,last-post"
				iteratorURL="<%= portletURL %>"
				emptyResultsMessage="there-are-no-recent-posts"
				rowChecker="<%= new RowChecker(renderResponse) %>"
			>
				<liferay-ui:search-container-results>

					<%
					results = MBThreadServiceUtil.getGroupThreads(scopeGroupId, groupThreadsUserId, WorkflowConstants.STATUS_APPROVED, false, false, searchContainer.getStart(), searchContainer.getEnd());
					total = MBThreadServiceUtil.getGroupThreadsCount(scopeGroupId, groupThreadsUserId, WorkflowConstants.STATUS_APPROVED, false, false);

					pageContext.setAttribute("results", results);
					pageContext.setAttribute("total", total);
					%>

				</liferay-ui:search-container-results>

				<liferay-ui:search-container-row
					className="com.liferay.portlet.messageboards.model.MBThread"
					keyProperty="threadId"
					modelVar="thread"
				>

					<%
					MBMessage message = null;

					try {
						message = MBMessageLocalServiceUtil.getMessage(thread.getRootMessageId());
					}
					catch (NoSuchMessageException nsme) {
						_log.error("Thread requires missing root message id " + thread.getRootMessageId());

						continue;
					}

					message = message.toEscapedModel();

					row.setBold(!MBThreadFlagLocalServiceUtil.hasThreadFlag(themeDisplay.getUserId(), thread));
					row.setObject(new Object[] {message});
					row.setRestricted(!MBMessagePermission.contains(permissionChecker, message, ActionKeys.VIEW));
					%>

					<liferay-portlet:renderURL varImpl="rowURL">
						<portlet:param name="struts_action" value="/message_boards/view_message" />
						<portlet:param name="messageId" value="<%= String.valueOf(message.getMessageId()) %>" />
					</liferay-portlet:renderURL>

					<liferay-ui:search-container-column-text
						buffer="buffer"
						href="<%= rowURL %>"
						name="thread"
					>

						<%
						String[] threadPriority = MBUtil.getThreadPriority(preferences, themeDisplay.getLanguageId(), thread.getPriority(), themeDisplay);

						if ((threadPriority != null) && (thread.getPriority() > 0)) {
							buffer.append("<img class=\"thread-priority\" alt=\"");
							buffer.append(threadPriority[0]);
							buffer.append("\" src=\"");
							buffer.append(threadPriority[1]);
							buffer.append("\" title=\"");
							buffer.append(threadPriority[0]);
							buffer.append("\" />");
						}

						buffer.append(message.getSubject());
						%>

					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text
						href="<%= rowURL %>"
						name="started-by"
						value='<%= message.isAnonymous() ? LanguageUtil.get(pageContext, "anonymous") : HtmlUtil.escape(PortalUtil.getUserName(message.getUserId(), message.getUserName())) %>'
					/>

					<liferay-ui:search-container-column-text
						href="<%= rowURL %>"
						name="posts"
						value="<%= String.valueOf(thread.getMessageCount()) %>"
					/>

					<liferay-ui:search-container-column-text
						href="<%= rowURL %>"
						name="views"
						value="<%= String.valueOf(thread.getViewCount()) %>"
					/>

					<liferay-ui:search-container-column-text
						buffer="buffer"
						href="<%= rowURL %>"
						name="last-post"
					>

						<%
						if (thread.getLastPostDate() == null) {
							buffer.append(LanguageUtil.get(pageContext, "none"));
						}
						else {
							buffer.append(LanguageUtil.get(pageContext, "date"));
							buffer.append(": ");
							buffer.append(dateFormatDateTime.format(thread.getLastPostDate()));

							String lastPostByUserName = HtmlUtil.escape(PortalUtil.getUserName(thread.getLastPostByUserId(), StringPool.BLANK));

							if (Validator.isNotNull(lastPostByUserName)) {
								buffer.append("<br />");
								buffer.append(LanguageUtil.get(pageContext, "by"));
								buffer.append(": ");
								buffer.append(lastPostByUserName);
							}
						}
						%>

					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-jsp
						align="right"
						path="/html/portlet/message_boards/message_action.jsp"
					/>
				</liferay-ui:search-container-row>

				<br>

				<aui:button onClick='<%= renderResponse.getNamespace() + "deleteThreads();" %>' value="delete" />

				<aui:button onClick='<%= renderResponse.getNamespace() + "lockThreads();" %>' value="lock" />

				<aui:button onClick='<%= renderResponse.getNamespace() + "unlockThreads();" %>' value="unlock" />

				<div class="separator"><!-- --></div>

				<liferay-ui:search-iterator />
			</liferay-ui:search-container>
		</aui:form>

		<%
		PortalUtil.setPageSubtitle(LanguageUtil.get(pageContext, StringUtil.replace(tabs1, StringPool.UNDERLINE, StringPool.DASH)), request);
		PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, TextFormatter.format(tabs1, TextFormatter.O)), portletURL.toString());
		%>

	</c:when>
	<c:when test='<%= tabs1.equals("statistics") %>'>
		<liferay-ui:panel-container cssClass="statistics-panel" extended="<%= false %>" id="messageBoardsStatisticsPanelContainer" persistState="<%= true %>">
			<liferay-ui:panel collapsible="<%= true %>" cssClass="statistics-panel-content" extended="<%= true %>" id="messageBoardsGeneralStatisticsPanel" persistState="<%= true %>" title="general">
				<dl>
					<dt>
						<liferay-ui:message key="num-of-categories" />:
					</dt>
					<dd>
						<%= numberFormat.format(categoryDisplay.getAllCategoriesCount()) %>
					</dd>
					<dt>
						<liferay-ui:message key="num-of-posts" />:
					</dt>
					<dd>
						<%= numberFormat.format(MBMessageServiceUtil.getGroupMessagesCount(scopeGroupId, WorkflowConstants.STATUS_APPROVED)) %>
					</dd>
					<dt>
						<liferay-ui:message key="num-of-participants" />:
					</dt>
					<dd>
						<%= numberFormat.format(MBStatsUserLocalServiceUtil.getStatsUsersByGroupIdCount(scopeGroupId)) %>
					</dd>
				</dl>
			</liferay-ui:panel>

			<liferay-ui:panel collapsible="<%= true %>" cssClass="statistics-panel-content" extended="<%= true %>" id="messageBoardsTopPostersPanel" persistState="<%= true %>" title="top-posters">
				<liferay-ui:search-container
					emptyResultsMessage="there-are-no-top-posters"
					iteratorURL="<%= portletURL %>"
				>
					<liferay-ui:search-container-results
						results="<%= MBStatsUserLocalServiceUtil.getStatsUsersByGroupId(scopeGroupId, searchContainer.getStart(), searchContainer.getEnd()) %>"
						total="<%= MBStatsUserLocalServiceUtil.getStatsUsersByGroupIdCount(scopeGroupId) %>"
					/>

					<liferay-ui:search-container-row
						className="com.liferay.portlet.messageboards.model.MBStatsUser"
						keyProperty="statsUserId"
						modelVar="statsUser"
					>
						<liferay-ui:search-container-column-jsp
							path="/html/portlet/message_boards/top_posters_user_display.jsp"
						/>
					</liferay-ui:search-container-row>

					<liferay-ui:search-iterator />
				</liferay-ui:search-container>
			</liferay-ui:panel>
		</liferay-ui:panel-container>

		<%
		PortalUtil.setPageSubtitle(LanguageUtil.get(pageContext, StringUtil.replace(tabs1, StringPool.UNDERLINE, StringPool.DASH)), request);
		PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, TextFormatter.format(tabs1, TextFormatter.O)), portletURL.toString());
		%>

	</c:when>
	<c:when test='<%= tabs1.equals("banned-users") %>'>
		<liferay-ui:search-container
			emptyResultsMessage="there-are-no-banned-users"
			headerNames="banned-user,banned-by,ban-date"
			iteratorURL="<%= portletURL %>"
		>
			<liferay-ui:search-container-results
				results="<%= MBBanLocalServiceUtil.getBans(scopeGroupId, searchContainer.getStart(), searchContainer.getEnd()) %>"
				total="<%= MBBanLocalServiceUtil.getBansCount(scopeGroupId) %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.portlet.messageboards.model.MBBan"
				keyProperty="banId"
				modelVar="ban"
			>
				<liferay-ui:search-container-column-text
					name="banned-user"
					value="<%= HtmlUtil.escape(PortalUtil.getUserName(ban.getBanUserId(), StringPool.BLANK)) %>"
				/>

				<liferay-ui:search-container-column-text
					name="banned-by"
					value="<%= HtmlUtil.escape(PortalUtil.getUserName(ban.getUserId(), StringPool.BLANK)) %>"
				/>

				<liferay-ui:search-container-column-text
					name="ban-date"
					value="<%= dateFormatDateTime.format(ban.getCreateDate()) %>"
				/>

				<c:if test="<%= PropsValues.MESSAGE_BOARDS_EXPIRE_BAN_INTERVAL > 0 %>">
					<liferay-ui:search-container-column-text
						name="unban-date"
						value="<%= dateFormatDateTime.format(MBUtil.getUnbanDate(ban, PropsValues.MESSAGE_BOARDS_EXPIRE_BAN_INTERVAL)) %>"
					/>
				</c:if>

				<liferay-ui:search-container-column-jsp
					align="right"
					path="/html/portlet/message_boards/ban_user_action.jsp"
				/>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator />
		</liferay-ui:search-container>

		<%
		PortalUtil.setPageSubtitle(LanguageUtil.get(pageContext, StringUtil.replace(tabs1, StringPool.UNDERLINE, StringPool.DASH)), request);
		PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, TextFormatter.format(tabs1, TextFormatter.O)), portletURL.toString());
		%>

	</c:when>
</c:choose>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />deleteCategories',
		function() {
			var deleteCategories = true;

			var deleteCategoryIds = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, "<portlet:namespace />allRowIds");

			if (!deleteCategoryIds) {
				deleteCategories = false;
			}

			if (deleteCategories) {
				if (confirm('<%= UnicodeLanguageUtil.get(pageContext, "are-you-sure-you-want-to-delete-this") %>')) {
					document.<portlet:namespace />fm.method = "post";
					document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = "<%= Constants.DELETE %>";
					document.<portlet:namespace />fm.<portlet:namespace />deleteCategoryIds.value = deleteCategoryIds;
					submitForm(document.<portlet:namespace />fm, "<portlet:actionURL><portlet:param name="struts_action" value="/message_boards_admin/edit_category" /></portlet:actionURL>");
				}
			}
		},
		['liferay-util-list-fields']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />deleteThreads',
		function() {
			if (confirm('<%= UnicodeLanguageUtil.get(pageContext, "are-you-sure-you-want-to-delete-this") %>')) {
				document.<portlet:namespace />fm1.method = "post";
				document.<portlet:namespace />fm1.<portlet:namespace /><%= Constants.CMD %>.value = "<%= Constants.DELETE %>";
				document.<portlet:namespace />fm1.<portlet:namespace />threadIds.value = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm1, "<portlet:namespace />allRowIds");
				submitForm(document.<portlet:namespace />fm1, "<portlet:actionURL><portlet:param name="struts_action" value="/message_boards_admin/delete_thread" /></portlet:actionURL>");
			}
		},
		['liferay-util-list-fields']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />lockThreads',
		function() {
			document.<portlet:namespace />fm1.method = "post";
			document.<portlet:namespace />fm1.<portlet:namespace /><%= Constants.CMD %>.value = "<%= Constants.LOCK %>";
			document.<portlet:namespace />fm1.<portlet:namespace />threadIds.value = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm1, "<portlet:namespace />allRowIds");
			submitForm(document.<portlet:namespace />fm1, "<portlet:actionURL><portlet:param name="struts_action" value="/message_boards_admin/edit_message" /></portlet:actionURL>");
		},
		['liferay-util-list-fields']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />unlockThreads',
		function() {
			document.<portlet:namespace />fm1.method = "post";
			document.<portlet:namespace />fm1.<portlet:namespace /><%= Constants.CMD %>.value = "<%= Constants.UNLOCK %>";
			document.<portlet:namespace />fm1.<portlet:namespace />threadIds.value = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm1, "<portlet:namespace />allRowIds");
			submitForm(document.<portlet:namespace />fm1, "<portlet:actionURL><portlet:param name="struts_action" value="/message_boards_admin/edit_message" /></portlet:actionURL>");
		},
		['liferay-util-list-fields']
	);
</aui:script>

<%!
private static Log _log = LogFactoryUtil.getLog("portal-web.docroot.html.portlet.message_boards.view_jsp");
%>