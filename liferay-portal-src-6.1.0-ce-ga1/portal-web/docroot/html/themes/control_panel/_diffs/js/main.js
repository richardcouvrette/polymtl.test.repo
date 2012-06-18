Liferay.Util.portletTitleEdit = function() {
};

if (!themeDisplay.isStatePopUp()) {
	AUI().ready('aui-io-request', 'aui-live-search', 'aui-overlay-context-panel', 'event-mouseenter', 'node-focusmanager', 'transition',
		function(A) {
			var body = A.getBody();

			var portletInformationEl = A.one('#cpContextPanelTemplate');
			var portletInformationIcon = A.one('#cpPortletTitleHelpIcon');

			var portletId = '';
			var visible = false;

			if (portletInformationEl) {
				portletId = portletInformationEl.attr('data-portlet-id');
				visible = (portletInformationEl.attr('data-visible-panel') == 'true');
			}

			var sessionData = {};
			var sessionKey = 'show-portlet-description-' + portletId;

			if (themeDisplay.isImpersonated()) {
				sessionData.doAsUserId = themeDisplay.getDoAsUserIdEncoded();
			}

			var trim = A.Lang.trim;

			var ATTR_DATA_NODE_STATUS = 'data-nodeStatus';

			var CSS_DISPLAY_PANEL_COLUMNS = 'display-panel-columns';

			var CSS_ICON_CLOSE = 'aui-icon-closethick';

			var CSS_ICON_HELP = 'aui-icon-help';

			var CSS_PANELS_MINIMIZED = 'panels-minimized';

			var CSS_SEARCH_PANEL_ACTIVE = 'search-panel-active';

			var EVENT_DATA_SIDEBAR = {
				persist: true
			};

			var SELECTOR_SEARCH_NODES = '#controlPanelMenuAddContentPanelContainer ul li a';

			var TPL_CANCEL_SEARCH_BUTTON = '<a class="cancel-search" href="javascript:;"></a>';

			var TPL_TOGGLE_PANELS_BUTTON = '<div id="minimize-panels"><a href="javascript:;"><span>' + Liferay.Language.get('minimize-panels') + '</span></a></div>';

			var helpBox = A.Component.create(
				{
					NAME: 'helpbox',
					ATTRS: {
						trigger: {
							setter: A.one
						}
					},
					prototype: {
						renderUI: function() {
							var instance = this;

							var contentBox = instance.get('contentBox');

							instance._icon = contentBox.one('.aui-icon');

							instance._hideNoticesControl = contentBox.one('.hide-notices-control');
							instance._hideAllNotices = instance._hideNoticesControl.one('.hide-all-notices');
							instance._hideAllNoticesControl = instance._hideNoticesControl.one('a');
						},

						bindUI: function() {
							var instance = this;

							instance.on(['mouseenter', 'mouseleave'], instance._onBoxMouseToggle);
							instance.after('visibleChange', instance._afterVisibleChange);

							instance._icon.on('click', instance._onIconClick, instance);

							instance.get('trigger').on('click', instance._onTriggerClick, instance);

							instance._hideNoticesControl.on(['mouseenter', 'mouseleave'], instance._onIconMouseToggle, instance);
							instance._hideAllNoticesControl.on('click', instance._onHideAllClick, instance);
						},

						_afterVisibleChange: function(event) {
							var instance = this;

							var action = 'show';
							var panelAction = 'hide';

							if (event.newVal) {
								action = 'hide';
								panelAction = 'show';
							}

							var trigger = instance.get('trigger');

							trigger[action]();

							var contentBox = instance.get('contentBox');

							contentBox[panelAction]();

							if (event.enablePortletDescriptions === false) {
								sessionData['enable-portlet-descriptions'] = false;
							}

							sessionData[sessionKey] = event.newVal;

							A.io.request(
								themeDisplay.getPathMain() + '/portal/session_click',
								{
									data: sessionData
								}
							);
						},

						_onBoxMouseToggle: function(event) {
							var instance = this;

							var from = CSS_ICON_HELP;
							var to = CSS_ICON_CLOSE;

							var mouseenter = event.type.indexOf('mouseenter') > -1;

							if (!mouseenter) {
								from = CSS_ICON_CLOSE;
								to = CSS_ICON_HELP;
							}

							instance._icon.replaceClass(from, to);
						},

						_onHideAllClick: function(event) {
							var instance = this;

							instance.set(
								'visible',
								false,
								{
									enablePortletDescriptions: false
								}
							);
						},

						_onIconClick: function(event) {
							var instance = this;

							instance.hide();
						},

						_onIconMouseToggle: function(event) {
							var instance = this;

							instance._hideNoticesControl.toggleClass('hide-notices-hover', (event.type == 'mouseenter'));

							instance._hideAllNotices.toggle();
						},

						_onTriggerClick: function(event) {
							var instance = this;

							instance.show();
						}
					}
				}
			);

			var ControlPanel = {
				init: function() {
					var instance = this;

					instance._renderUI();

					instance._bindUI();

					instance._createDataConnection();

					instance._createCancelButton();

					instance._createFocusManager();

					instance._createLiveSearch();
				},

				_afterHiddenChange: function(event) {
					var instance = this;

					instance._uiSetHidden(event.newVal, event.persist);
				},

				_bindUI: function() {
					var instance = this;

					Liferay.set('controlPanelSidebarHidden', body.hasClass(CSS_PANELS_MINIMIZED));

					Liferay.after('controlPanelSidebarHiddenChange', instance._afterHiddenChange, instance);

					instance._panelToggleButton.on('click', instance._toggleHidden, instance);

					instance._searchPanelHolder.on('click', instance._setVisible, instance);

					instance._togglePanelsAction();

					instance._panelHolder.on(['mouseenter', 'mouseleave'], instance._togglePanelsAction, instance);

					var sidebarPanel = Liferay.Panel.get('addContentPanelContainer');

					if (sidebarPanel) {
						sidebarPanel.on(
							'collapse',
							function(event) {
								if (Liferay.get('controlPanelSidebarHidden')) {
									event.preventDefault();
								}
							}
						);
					}

					Liferay.publish(
						'focusSearchBar',
						{
							defaultFn: A.bind(instance._focusSearchBar, instance)
						}
					);

					A.getDoc().on(
						'keyup',
						function(event) {
							if (event.isKey('ESC')) {
								Liferay.fire('focusSearchBar');
							}
						}
					);
				},

				_createCancelButton: function() {
					var instance = this;

					var cancelSearch = A.Node.create(TPL_CANCEL_SEARCH_BUTTON);

					instance._searchPanelHolder.append(cancelSearch);

					var searchNodes = A.all(SELECTOR_SEARCH_NODES);

					cancelSearch.on(
						'click',
						function(event) {
							body.removeClass(CSS_SEARCH_PANEL_ACTIVE);

							instance._searchPanelInput.val('').focus();

							searchNodes.show();

							instance._searchActive = false;
						}
					);
				},

				_createDataConnection: function() {
					var instance = this;

					instance._saveData = A.io.request(
						themeDisplay.getPathMain() + '/portal/session_click',
						{
							autoLoad: false
						}
					);
				},

				_createFocusManager: function() {
					var instance = this;

					var addContentPanelContainer = A.one('#controlPanelMenuAddContentPanelContainer');

					addContentPanelContainer.plug(
						A.Plugin.NodeFocusManager,
						{
							descendants: '.lfr-panel-content ul li a:visible',
							keys: {
								next: 'down:40',
								previous: 'down:38'
							}
						}
					);

					var focusManager = addContentPanelContainer.focusManager;

					instance._refreshFocusManagerTask = A.debounce(focusManager.refresh, 50, focusManager);

					instance._searchPanelInput.on(
						'key',
						function(event) {
							focusManager.focus(0);
						},
						'down:40'
					);
				},

				_createLiveSearch: function() {
					var instance = this;

					var searchPanelInput = instance._searchPanelInput;

					var liveSearch = new A.LiveSearch(
						{
							input: searchPanelInput,
							nodes: SELECTOR_SEARCH_NODES,

							data: function(node) {
								return node.text();
							},

							on: {
								search: function(event) {
									if (trim(liveSearch.get('searchValue'))) {
										body.addClass(CSS_SEARCH_PANEL_ACTIVE);

										instance._searchActive = true;
									}
								}
							},

							after: {
								search: function(event) {
									instance._searchActive = true;

									instance._refreshFocusManagerTask();

									if (!trim(liveSearch.get('searchValue'))) {
										body.removeClass(CSS_SEARCH_PANEL_ACTIVE);

										instance._searchActive = false;
									}
								}
							}
						}
					);

					instance._liveSearch = liveSearch;
				},

				_focusSearchBar: function(event) {
					var instance = this;

					instance._searchPanelInput.selectText();

					Liferay.set('controlPanelSidebarHidden', false, EVENT_DATA_SIDEBAR);
				},

				_renderUI: function() {
					var instance = this;

					var searchPanelHolder = A.one('.search-panels');
					var searchPanelInput = searchPanelHolder.one('#_160_searchPanel');

					var controlPanelTools = A.one('.control-panel-tools');

					var togglePanels = A.Node.create(TPL_TOGGLE_PANELS_BUTTON);

					var panelHolder = A.one('.panel-page-menu');

					var panelToggleButton = togglePanels.one('a');

					controlPanelTools.append(togglePanels);

					searchPanelInput.attr('autocomplete', 'off');

					if (portletInformationEl) {
						instance._helpBox = new helpBox(
							{
								contentBox: portletInformationEl,
								trigger: portletInformationIcon,
								visible: portletInformationEl.test(':visible')
							}
						).render();
					}

					instance._controlPanelTools = controlPanelTools;
					instance._panelHolder = panelHolder;
					instance._panelToggleButton = panelToggleButton;
					instance._searchPanelHolder = searchPanelHolder;
					instance._searchPanelInput = searchPanelInput;
					instance._togglePanels = togglePanels;
				},

				_setVisible: function(event) {
					var instance = this;

					Liferay.set('controlPanelSidebarHidden', false, EVENT_DATA_SIDEBAR);
				},

				_toggleHidden: function(event) {
					var instance = this;

					Liferay.set('controlPanelSidebarHidden', !Liferay.get('controlPanelSidebarHidden'), EVENT_DATA_SIDEBAR);
				},

				_togglePanelsAction: function(event) {
					var instance = this;

					var opacity = 0;

					if (event) {
						opacity = instance._eventOpacities[event.type];
					}

					instance._togglePanels.transition(
						{
							opacity: opacity
						}
					);
				},

				_uiSetHidden: function(newVal, persist) {
					var instance = this;

					var liveSearch = instance._liveSearch;

					var panelCfg = instance._panelCfg;

					var toggleValue = panelCfg.closeValue;

					var searchValue = instance._searchValue;

					if (!newVal) {
						toggleValue = panelCfg.openValue;
					}
					else {
						searchValue = '';

						instance._searchValue = liveSearch.get('searchValue');
					}

					liveSearch.search(searchValue);

					body.removeClass(CSS_DISPLAY_PANEL_COLUMNS);

					instance._panelHolder.transition(
						{
							left: toggleValue,
							easing: 'ease-out',
							duration: 0.2
						},
						function() {
							if (persist) {
								instance._saveData.set(
									'data',
									{
										'control-panel-sidebar-minimized': newVal
									}
								).start();
							}

							body.addClass(CSS_DISPLAY_PANEL_COLUMNS);

							body.toggleClass(CSS_PANELS_MINIMIZED, newVal);

							if (!newVal) {
								instance._searchPanelInput.selectText();
							}
						}
					);

					body.toggleClass(CSS_SEARCH_PANEL_ACTIVE, (instance._searchActive && !newVal));
				},

				_eventOpacities: {
					mouseenter: 1,
					mouseleave: 0
				},

				_panelCfg: {
					closeValue: '-198px',
					openValue: '0'
				},

				_searchActive: false,
				_searchValue: ''
			};

			ControlPanel.init();
		}
	);
}