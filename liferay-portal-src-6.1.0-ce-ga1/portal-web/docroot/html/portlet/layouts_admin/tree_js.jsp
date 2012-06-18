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

<%@ include file="/html/portlet/layouts_admin/init_attributes.jspf" %>

<%
boolean incomplete = ParamUtil.getBoolean(request, "incomplete", true);

String treeLoading = PortalUtil.generateRandomKey(request, "treeLoading");

String treeId = ParamUtil.getString(request, "treeId");
boolean checkContentDisplayPage = ParamUtil.getBoolean(request, "checkContentDisplayPage", false);
boolean expandFirstNode = ParamUtil.getBoolean(request, "expandFirstNode", true);
boolean saveState = ParamUtil.getBoolean(request, "saveState", true);
boolean selectableTree = ParamUtil.getBoolean(request, "selectableTree");

boolean rootNodeExpanded = GetterUtil.getBoolean(SessionClicks.get(request, treeId + "RootNode", null), true);

String modules = "aui-io-request,aui-tree-view,dataschema-xml,datatype-xml";

if (!selectableTree) {
	modules += ",liferay-history-manager";
}
%>

<aui:script use="<%= modules %>">
	var Lang = A.Lang;

	var Util = Liferay.Util;

	var LAYOUT_URL = '<%= portletURL + StringPool.AMPERSAND + portletDisplay.getNamespace() + "selPlid={selPlid}" + StringPool.AMPERSAND + portletDisplay.getNamespace() + "historyKey={historyKey}" %>';

	var TreeUtil = {
		DEFAULT_PARENT_LAYOUT_ID: <%= LayoutConstants.DEFAULT_PARENT_LAYOUT_ID %>,
		OPEN_NODES: '<%= SessionTreeJSClicks.getOpenNodes(request, treeId) %>'.split(','),
		PREFIX_LAYOUT_ID: '_layoutId_',
		PREFIX_PLID: '_plid_',
		SELECTED_NODES: '<%= SessionTreeJSClicks.getOpenNodes(request, treeId + "SelectedNode") %>'.split(','),

		afterRenderTree: function(event) {
			var treeInstance = event.target;

			var rootNode = treeInstance.item(0);

			var loadingEl = A.one('#<portlet:namespace />treeLoading<%= treeLoading %>');

			loadingEl.hide();

			<c:choose>
				<c:when test="<%= saveState %>">
					TreeUtil.restoreNodeState(rootNode);
				</c:when>
				<c:when test="<%= expandFirstNode %>">
					rootNode.expand();
				</c:when>
			</c:choose>
		},

		createId: function(layoutId, plid) {
			return '<%= HtmlUtil.escape(treeId) %>' + TreeUtil.PREFIX_LAYOUT_ID + layoutId + TreeUtil.PREFIX_PLID + plid;
		},

		createLink: function(data) {
			var className = 'layout-tree';

			if (data.cssClass) {
				className += ' ' + data.cssClass;
			}

			if (<%= checkContentDisplayPage %> && !data.contentDisplayPage) {
				className += ' layout-page-invalid';
			}

			var href = Lang.sub(
				LAYOUT_URL,
				{
					historyKey: data.historyKey,
					selPlid: data.plid
				}
			);

			return '<a class="' + className + '" data-uuid="' + data.uuid + '" href="' + href + '" title="' + data.title + '">' + data.label + '</a>';
		},

		extractLayoutId: function(node) {
			return node.get('id').match(/layoutId_(\d+)/)[1];
		},

		extractPlid: function(node) {
			return node.get('id').match(/plid_(\d+)/)[1];
		},

		formatJSONResults: function(json) {
			var output = [];

			A.each(
				json,
				function(node) {
					var newNode = {
						<c:if test="<%= saveState %>">
							after: {
								checkedChange: function(event) {
									var plid = TreeUtil.extractPlid(event.target);

									TreeUtil.updateSessionTreeClick(plid, event.newVal, '<%= HtmlUtil.escape(treeId) %>SelectedNode');
								}
							},
						</c:if>
						alwaysShowHitArea: node.hasChildren,
						draggable: node.updateable,
						expanded : node.selLayoutAncestor,
						id: TreeUtil.createId(node.layoutId, node.plid),
						type: '<%= selectableTree ? "task" : "io" %>'
					};

					var cssClass = '';

					var title = '';

					newNode.label = Util.escapeHTML(node.name);

					if (node.layoutRevisionId) {
						if (node.layoutBranchName) {
							node.layoutBranchName = Util.escapeHTML(node.layoutBranchName);

							newNode.label += Lang.sub(' <span class="layout-branch-name" title="<%= UnicodeLanguageUtil.get(pageContext, "this-is-the-page-variation-that-is-marked-as-ready-for-publication") %>">[{layoutBranchName}]</span>', node);
						}

						if (node.incomplete) {
							cssClass = 'incomplete-layout';

							title = '<%= UnicodeLanguageUtil.get(pageContext, "this-page-is-not-enabled-in-this-site-pages-variation,-but-is-available-in-other-variations") %>';
						}
					}

					if (!node.updateable) {
						newNode.cssClass = 'lfr-page-locked';
					}

					if (!<%= selectableTree %>) {
						newNode.label = TreeUtil.createLink(
							{
								cssClass: cssClass,
								label: newNode.label,
								plid: node.plid,
								title: title,
								uuid: node.uuid,
								contentDisplayPage: node.contentDisplayPage
							}
						);
					}

					output.push(newNode);
				}
			);

			return output;
		},

		restoreNodeState: function(node) {
			var instance = this;

			var id = node.get('id');
			var plid = TreeUtil.extractPlid(node);

			if (plid == '<%= selPlid %>') {
				node.select();
			}

			if (A.Array.indexOf(TreeUtil.OPEN_NODES, id) > -1) {
				node.expand();
			}

			if (A.Array.indexOf(TreeUtil.SELECTED_NODES, plid) > -1) {
				if (node.check) {
					node.check();
				}
			}
		},

		updateLayout: function(data) {
			var updateURL = themeDisplay.getPathMain() + '/layouts_admin/update_page';

			A.io.request(
				updateURL,
				{
					data: data
				}
			);
		},

		updateLayoutParent: function(dragPlid, dropPlid, index) {
			TreeUtil.updateLayout(
				{
					cmd: 'parent_layout_id',
					parentPlid: dropPlid,
					plid: dragPlid,
					priority: index
				}
			);
		}

		<c:if test="<%= saveState %>">
			, updateSessionTreeClick: function(id, open, treeId) {
				var sessionClickURL = themeDisplay.getPathMain() + '/portal/session_tree_js_click';

				var data = {
					nodeId: id,
					openNode: open || false,
					treeId: treeId
				};

				A.io.request(
					sessionClickURL,
					{
						data: data
					}
				);
			}
		</c:if>
	};

	var getLayoutsURL = themeDisplay.getPathMain() + '/layouts_admin/get_layouts';
	var rootId = TreeUtil.createId(TreeUtil.DEFAULT_PARENT_LAYOUT_ID, 0);
	var rootLabel = '<%= HtmlUtil.escapeJS(rootNodeName) %>';
	var treeElId = '<portlet:namespace /><%= HtmlUtil.escape(treeId) %>Output';

	var RootNodeType = A.TreeNodeTask;
	var TreeViewType = A.TreeView;

	<c:if test="<%= !selectableTree %>">
		RootNodeType = A.TreeNodeIO;
		TreeViewType = A.TreeViewDD;

		<c:if test="<%= !checkContentDisplayPage %>">
		rootLabel = TreeUtil.createLink(
			{
				label: Util.escapeHTML(rootLabel),
				plid: TreeUtil.DEFAULT_PARENT_LAYOUT_ID
			}
		);
		</c:if>
	</c:if>

	var rootNode = new RootNodeType(
		{
			after: {
				expandedChange: function(event) {
					var sessionClickURL = themeDisplay.getPathMain() + '/portal/session_click';

					A.io.request(
						sessionClickURL,
						{
							data: {
								'<%= HtmlUtil.escape(treeId) %>RootNode': event.newVal
							}
						}
					);
				}
			},
			alwaysShowHitArea: true,
			draggable: false,
			expanded: <%= rootNodeExpanded %>,
			id: rootId,
			label: rootLabel,
			leaf: false
		}
	);

	rootNode.get('contentBox').addClass('lfr-root-node');

	var treeview = new TreeViewType(
		{
			after: {
				<c:if test="<%= saveState %>">
				collapse: function(event) {
					var id = event.tree.node.get('id');

					TreeUtil.updateSessionTreeClick(id, false, '<%= HtmlUtil.escape(treeId) %>');
				},
				expand: function(event) {
					var id = event.tree.node.get('id');

					TreeUtil.updateSessionTreeClick(id, true, '<%= HtmlUtil.escape(treeId) %>');
				},
				</c:if>
				render: TreeUtil.afterRenderTree
			},
			boundingBox: '#' + treeElId,
			children: [rootNode],
			io: {
				cfg: {
					data: function(node) {
						var parentLayoutId = TreeUtil.extractLayoutId(node);

						return {
							groupId: <%= groupId %>,
							incomplete: <%= incomplete %>,
							privateLayout: <%= privateLayout %>,
							parentLayoutId: parentLayoutId,
							selPlid: '<%= selPlid %>'
						};
					},
					method: AUI.defaults.io.method
				},
				formatter: TreeUtil.formatJSONResults,
				url: getLayoutsURL
			},
			on: {
				<c:if test="<%= saveState %>">
				append: function(event) {
					TreeUtil.restoreNodeState(event.tree.node);
				},
				</c:if>
				dropAppend: function(event) {
					var tree = event.tree;

					var index = tree.dragNode.get('parentNode').indexOf(tree.dragNode);

					TreeUtil.updateLayoutParent(
						TreeUtil.extractPlid(tree.dragNode),
						TreeUtil.extractPlid(tree.dropNode),
						index
					);
				},
				dropInsert: function(event) {
					var tree = event.tree;

					var index = tree.dragNode.get('parentNode').indexOf(tree.dragNode);

					TreeUtil.updateLayoutParent(
						TreeUtil.extractPlid(tree.dragNode),
						TreeUtil.extractPlid(tree.dropNode.get('parentNode')),
						index
					);
				}
			},
			paginator: {
				limit: <%= PropsValues.LAYOUT_MANAGE_PAGES_INITIAL_CHILDREN %>,
				offsetParam: 'start'
			},
			type: 'pages'
		}
	).render();

	<c:if test="<%= !saveState && checkContentDisplayPage %>">
		treeview.on(
			'append',
			function(event) {
				var node = event.tree.node;

				var plid = TreeUtil.extractPlid(node);

				if (plid == '<%= selPlid %>') {
					node.select();
				}
			}
		);
	</c:if>

	A.one('#' + treeElId).setData('treeInstance', treeview);

	<c:if test="<%= !selectableTree %>">
		var History = Liferay.HistoryManager;

		var DEFAULT_PLID = '0';

		var HISTORY_SELECTED_PLID = '<portlet:namespace />selPlid';

		var layoutsContainer = A.one('#<portlet:namespace />layoutsContainer');

		treeview.after(
			'lastSelectedChange',
			function(event) {
				var node = event.newVal;

				var plid = TreeUtil.extractPlid(node);

				var currentValue = History.get(HISTORY_SELECTED_PLID);

				if (plid != currentValue) {
					if (plid == DEFAULT_PLID && Lang.isValue(currentValue)) {
						plid = null;
					}

					History.add(
						{
							'<portlet:namespace />selPlid': plid
						}
					);
				}
			}
		);

		function compareItemId(item, id) {
			return (TreeUtil.extractPlid(item) == id);
		}

		function findNodeByPlid(node, plid) {
			var foundItem = null;

			if (node) {
				if (compareItemId(node, plid)) {
					foundItem = node;
				}
			}

			if (!foundItem) {
				var children = (node || treeview).get('children');

				var length = children.length;

				for (var i = 0; i < length; i++) {
					var item = children[i];

					if (item.isLeaf()) {
						if (compareItemId(item, plid)) {
							foundItem = item;
						}
					}
					else {
						foundItem = findNodeByPlid(item, plid);
					}

					if (foundItem) {
						break;
					}
				}
			}

			return foundItem;
		}

		History.after(
			'stateChange',
			function(event) {
				var nodePlid = event.newVal[HISTORY_SELECTED_PLID];

				if (Lang.isValue(nodePlid)) {
					var node = findNodeByPlid(null, nodePlid);

					if (node) {
						var lastSelected = treeview.get('lastSelected');

						if (lastSelected) {
							lastSelected.unselect();
						}

						node.select();

						var io = layoutsContainer.io;

						var uri = Lang.sub(
							LAYOUT_URL,
							{
								historyKey: '',
								selPlid: nodePlid
							}
						);

						io.set('uri', uri);

						io.start();
					}
				}
			}
		);
	</c:if>
</aui:script>

<div class="lfr-tree-loading" id="<portlet:namespace />treeLoading<%= treeLoading %>">
	<span class="aui-icon aui-icon-loading lfr-tree-loading-icon"></span>
</div>

<div class="lfr-tree" id="<portlet:namespace /><%= HtmlUtil.escape(treeId) %>Output"></div>