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

<%@ include file="/html/portlet/staging_bar/init.jsp" %>

<%
long layoutSetBranchId = ParamUtil.getLong(request, "layoutSetBranchId");

LayoutRevision recentLayoutRevision = null;

long currentLayoutRevisionId = StagingUtil.getRecentLayoutRevisionId(request, layoutSetBranchId, plid);

if (currentLayoutRevisionId > 0) {
	recentLayoutRevision = LayoutRevisionLocalServiceUtil.getLayoutRevision(currentLayoutRevisionId);
}
else {
	recentLayoutRevision = LayoutStagingUtil.getLayoutRevision(layout);

	currentLayoutRevisionId = recentLayoutRevision.getLayoutRevisionId();
}

List<LayoutRevision> rootLayoutRevisions = LayoutRevisionLocalServiceUtil.getChildLayoutRevisions(layoutSetBranchId, LayoutRevisionConstants.DEFAULT_PARENT_LAYOUT_REVISION_ID, plid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new LayoutRevisionIdComparator(true));
%>

<c:if test="<%= !rootLayoutRevisions.isEmpty() %>">
	<c:if test="<%= rootLayoutRevisions.size() > 1 %>">
		<aui:select cssClass="variation-selector" inlineLabel="left" label="page-variation" name="variationsSelector">

			<%
			for (LayoutRevision rootLayoutRevision : rootLayoutRevisions) {
				LayoutBranch layoutBranch = rootLayoutRevision.getLayoutBranch();
			%>

				<aui:option label="<%= layoutBranch.getName() %>" selected="<%= recentLayoutRevision.getLayoutBranchId() == rootLayoutRevision.getLayoutBranchId() %>" value="<%= rootLayoutRevision.getLayoutRevisionId() %>" />

			<%
			}
			%>

			<aui:option label="all-page-variations" value="all" />
		</aui:select>
	</c:if>

	<div class="layout-revision-container" id="<portlet:namespace/>layoutRevisionsContainer">

		<%
		for (LayoutRevision rootLayoutRevision : rootLayoutRevisions) {
		%>

			<div class="layout-variation-container <%= (recentLayoutRevision.getLayoutBranchId() == rootLayoutRevision.getLayoutBranchId()) ? StringPool.BLANK : "aui-helper-hidden" %>" id="<portlet:namespace/><%= rootLayoutRevision.getLayoutRevisionId() %>">
				<c:if test="<%= rootLayoutRevisions.size() > 1 %>">

					<%
					LayoutBranch layoutBranch = rootLayoutRevision.getLayoutBranch();
					%>

					<h3 class="layout-variation-name"><liferay-ui:message key="<%= layoutBranch.getName() %>" /></h3>
				</c:if>

				<liferay-ui:search-container>
					<liferay-ui:search-container-results
						results="<%= LayoutRevisionLocalServiceUtil.getLayoutRevisions(rootLayoutRevision.getLayoutSetBranchId(), rootLayoutRevision.getLayoutBranchId(), rootLayoutRevision.getPlid(), QueryUtil.ALL_POS, QueryUtil.ALL_POS, new LayoutRevisionIdComparator(false)) %>"
						total="<%= LayoutRevisionLocalServiceUtil.getLayoutRevisionsCount(rootLayoutRevision.getLayoutSetBranchId(), rootLayoutRevision.getLayoutBranchId(), rootLayoutRevision.getPlid()) %>"
					/>

					<liferay-ui:search-container-row
						className="com.liferay.portal.model.LayoutRevision"
						escapedModel="<%= true %>"
						keyProperty="layoutRevisionId"
						modelVar="curLayoutRevision"
					>
						<liferay-ui:search-container-column-text
							buffer="buffer"
							cssClass='<%= (curLayoutRevision.getLayoutRevisionId() == currentLayoutRevisionId) ? "layout-revision-current" : StringPool.BLANK %>'
							name="date"
						>

						<%
						Date now = new Date();

						long timeAgo = now.getTime() - curLayoutRevision.getCreateDate().getTime();

						if (curLayoutRevision.getLayoutRevisionId() == currentLayoutRevisionId) {
							buffer.append("<div class=\"current-version-pointer\"><img alt=\"");
							buffer.append(LanguageUtil.get(pageContext, "current-version"));
							buffer.append("\" src=\"");
							buffer.append(themeDisplay.getPathThemeImages());
							buffer.append("/arrows/01_right.png\" title=\"");
							buffer.append(LanguageUtil.get(pageContext, "current-version"));
							buffer.append("\" /></div>");
						}

						buffer.append("<span class=\"aproximate-date\">");
						buffer.append(LanguageUtil.format(pageContext, "x-ago", LanguageUtil.getTimeDescription(pageContext, timeAgo, true)));
						buffer.append("</span><span class=\"real-date\">");
						buffer.append(dateFormatDateTime.format(curLayoutRevision.getCreateDate()));
						buffer.append("</span>");
						%>

						</liferay-ui:search-container-column-text>

						<liferay-ui:search-container-column-text
							buffer="buffer"
							name="status"
						>

							<%
							String statusMessage = null;
							String additionalText = StringPool.BLANK;

							if (curLayoutRevision.isHead()) {
								statusMessage = "ready-for-publication";
							}
							else {
								int status = curLayoutRevision.getStatus();

								statusMessage = WorkflowConstants.toLabel(status);

								if (status == WorkflowConstants.STATUS_PENDING) {
									StringBundler sb = new StringBundler(4);

									try {
										String workflowStatus = WorkflowInstanceLinkLocalServiceUtil.getState(curLayoutRevision.getCompanyId(), curLayoutRevision.getGroupId(), LayoutRevision.class.getName(), curLayoutRevision.getLayoutRevisionId());

										sb.append(StringPool.SPACE);
										sb.append(StringPool.OPEN_PARENTHESIS);
										sb.append(LanguageUtil.get(pageContext, workflowStatus));
										sb.append(StringPool.CLOSE_PARENTHESIS);

										additionalText = sb.toString();
									}
									catch (NoSuchWorkflowInstanceLinkException nswile) {
									}
								}
							}

							buffer.append("<span class=\"taglib-workflow-status\"><span class=\"workflow-status\"><span class=\"workflow-status-");
							buffer.append(statusMessage);
							buffer.append("\">");
							buffer.append(LanguageUtil.get(pageContext, statusMessage));
							buffer.append(additionalText);
							buffer.append("</span></span></span>");
							%>

						</liferay-ui:search-container-column-text>

						<liferay-ui:search-container-column-text
							buffer="buffer"
							name="version"
						>

							<%
							if (curLayoutRevision.getLayoutRevisionId() == currentLayoutRevisionId) {
								buffer.append("<span class=\"layout-revision-current\">");
								buffer.append(curLayoutRevision.getLayoutRevisionId());
								buffer.append("</span><span class=\"current-version\">");
								buffer.append(LanguageUtil.get(pageContext, "current-version"));
								buffer.append("</span>");
							}
							else {
								buffer.append("<a class=\"layout-revision selection-handle\" data-layoutRevisionId=\"");
								buffer.append(curLayoutRevision.getLayoutRevisionId());
								buffer.append("\" data-layoutSetBranchId=\"");
								buffer.append(curLayoutRevision.getLayoutSetBranchId());
								buffer.append("\" href=\"#\" title=\"");
								buffer.append(LanguageUtil.get(pageContext, "go-to-this-version"));
								buffer.append("\">");
								buffer.append(curLayoutRevision.getLayoutRevisionId());
								buffer.append("</a>");
							}
							%>

						</liferay-ui:search-container-column-text>

						<liferay-ui:search-container-column-text
							buffer="buffer"
							name="user"
						>

							<%
							User curUser = UserLocalServiceUtil.getUserById(curLayoutRevision.getUserId());

							buffer.append("<a class=\"user-handle\" href=\"");
							buffer.append(curUser.getDisplayURL(themeDisplay));
							buffer.append("\">");
							buffer.append(curUser.getFullName());
							buffer.append("</a>");
							%>

						</liferay-ui:search-container-column-text>

						<liferay-ui:search-container-column-jsp
							path="/html/portlet/staging_bar/layout_revision_action.jsp"
						/>
					</liferay-ui:search-container-row>

					<liferay-ui:search-iterator paginate="<%= false %>" searchContainer="<%= searchContainer %>" />
				</liferay-ui:search-container>
			</div>

		<%
		}
		%>

	</div>
</c:if>

<aui:script use="aui-base">
	var variationsSelector = A.one('#<portlet:namespace/>variationsSelector');
	var layoutRevisionsContainer = A.one('#<portlet:namespace/>layoutRevisionsContainer');

	var layoutBranchesContainer = A.all('.layout-variation-container');

	if (variationsSelector) {
		variationsSelector.on(
			'change',
			function() {
				if (variationsSelector.val() == 'all') {
					layoutBranchesContainer.show();
				}
				else {
					layoutBranchesContainer.hide();

					var layoutBranch = A.one('#<portlet:namespace/>' + variationsSelector.val());

					layoutBranch.show();
				}
			}
		);
	}
</aui:script>