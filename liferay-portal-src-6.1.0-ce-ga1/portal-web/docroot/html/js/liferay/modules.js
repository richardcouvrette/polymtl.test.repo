;(function(A, Liferay) {
	var LiferayAUI = Liferay.AUI;

	var COMBINE = LiferayAUI.getCombine();

	var GROUPS = AUI.defaults.groups;

	var PATH_COMBO = LiferayAUI.getComboPath();

	var PATH_JAVASCRIPT = LiferayAUI.getJavaScriptRootPath();

	var PATH_LIFERAY = PATH_JAVASCRIPT + '/liferay/';

	var PATH_MISC = PATH_JAVASCRIPT + '/misc/';

	var REGEX_DASH = /-/g;

	var STR_UNDERSCORE = '_';

	var addPlugin = function(config) {
		var group = config.group || 'liferay';
		var trigger = config.trigger;
		var name = config.name;

		delete config.group;

		var module = GROUPS[group].modules[trigger];

		var pluginObj = module.plugins;

		if (!pluginObj) {
			pluginObj = {};

			module.plugins = pluginObj;
		}

		pluginObj[name] = {
			condition: config
		};
	};

	var createLiferayModules = function() {
		var modules = {};

		var moduleList = {
			'asset-categories-selector': ['aui-tree', 'liferay-asset-tags-selector'],
			'asset-tags-selector': ['array-extras', 'async-queue', 'aui-autocomplete', 'aui-dialog', 'aui-io-request', 'aui-live-search', 'aui-textboxlist', 'aui-form-textfield', 'datasource-cache', 'liferay-service-datasource'],
			'auto-fields': ['aui-base', 'aui-data-set', 'aui-io-request', 'aui-parse-content', 'sortable', 'base', 'liferay-undo-manager'],
			'dockbar': ['aui-node', 'event-touch'],
			'dockbar-underlay': ['aui-button-item', 'aui-io-plugin', 'aui-overlay-manager'],
			'dynamic-select': ['aui-base'],
			'form': ['aui-base', 'aui-form-validator'],
			'form-placeholders': ['liferay-form', 'plugin'],
			'form-navigator': ['aui-base'],
			'history': getHistoryRequirements(),
			'history-html5': ['liferay-history', 'history-html5', 'querystring-stringify-simple'],
			'history-manager': ['liferay-history'],
			'hudcrumbs': ['aui-base', 'plugin'],
			'icon': ['aui-base'],
			'input-move-boxes': ['aui-base', 'aui-toolbar'],
			'layout': [],
			'layout-column': ['aui-portal-layout', 'dd'],
			'layout-configuration': ['aui-live-search', 'dd', 'liferay-layout'],
			'layout-freeform': ['aui-resize', 'liferay-layout-column'],
			'list-view': ['aui-base', 'transition'],
			'logo-selector': ['aui-base'],
			'look-and-feel': ['aui-color-picker', 'aui-dialog', 'aui-io-request', 'aui-tabs-base'],
			'menu': ['aui-live-search', 'aui-overlay', 'node-focusmanager'],
			'navigation': ['aui-form-combobox', 'aui-io-request', 'dd-constrain', 'event-touch', 'json-parse', 'node-event-simulate', 'overlay', 'selector-css3', 'sortable'],
			'navigation-touch': ['liferay-navigation'],
			'navigation-interaction': ['node-focusmanager', 'plugin'],
			'notice': ['aui-base'],
			'panel': ['aui-base', 'aui-io-request'],
			'panel-floating': ['aui-paginator', 'liferay-panel'],
			'poller': ['aui-base', 'io', 'json'],
			'portlet-base': ['aui-base'],
			'portlet-url': ['aui-base', 'aui-io-request', 'querystring-stringify-simple'],
			'ratings': ['aui-io-request', 'aui-rating'],
			'search-container': ['aui-base', 'event-mouseenter'],
			'session': ['aui-io', 'aui-task-manager', 'cookie', 'liferay-notice'],
			'service-datasource': ['aui-base', 'datasource-local'],
			'staging': ['aui-dialog', 'aui-io-plugin'],
			'staging-branch': ['liferay-staging'],
			'staging-version': ['aui-button-item', 'liferay-staging'],
			'translation-manager': ['aui-base'],
			'undo-manager': ['aui-data-set', 'base'],
			'upload': ['aui-base', 'aui-swf', 'collection', 'swfupload'],
			'util-list-fields': ['aui-base'],
			'util-window': ['aui-dialog', 'aui-dialog-iframe']
		};

		for (var i in moduleList) {
			modules['liferay-' + i] = {
				path: i.replace(REGEX_DASH, STR_UNDERSCORE) + '.js',
				requires: moduleList[i]
			};
		}

		return modules;
	};

	var getHistoryRequirements = function() {
		var WIN = A.config.win;

		var HISTORY = WIN.history;

		var module = 'history-hash';

		if (HISTORY &&
			HISTORY.pushState &&
			HISTORY.replaceState &&
			('onpopstate' in WIN || A.UA.gecko >= 2)) {

			module = 'liferay-history-html5';
		}

		return ['querystring-parse-simple', module];
	};

	GROUPS.liferay = {
		base: PATH_LIFERAY,
		root: PATH_LIFERAY,
		combine: COMBINE,
		comboBase: PATH_COMBO,
		modules: createLiferayModules(),
		patterns: {
			'liferay-': {
				configFn: function(config) {
					var path = config.path;

					var nameRE = new RegExp(config.name + '/liferay-([A-Za-z0-9-]+)-min(\.js)');

					path = path.replace(nameRE, '$1$2');
					path = path.replace(REGEX_DASH, STR_UNDERSCORE);

					config.path = path;
				}
			}
		}
	};

	GROUPS.misc = {
		base: PATH_MISC,
		root: PATH_MISC,
		combine: COMBINE,
		comboBase: PATH_COMBO,
		modules: {
			swfupload: {
				path : 'swfupload/swfupload.js'
			},
			swfobject: {
				path: 'swfobject.js'
			}
		}
	};

	GROUPS.portal = {
		base: PATH_LIFERAY,
		combine: false,
		modules: {
			'portal-aui-lang': {
				requires: ['aui-calendar'],
				path: LiferayAUI.getLangPath()
			}
		}
	};

	addPlugin(
		{
			name: 'liferay-form-placeholders',
			test: function(A) {
				return !A.Object.owns(document.createElement('input'), 'placeholder');
			},
			trigger: 'liferay-form'
		}
	);

	addPlugin(
		{
			name: 'liferay-navigation-touch',
			test: function(A) {
				return A.UA.touch;
			},
			trigger: 'liferay-navigation'
		}
	);

	addPlugin(
		{
			group: 'alloy',
			name: 'portal-aui-lang',
			test: function(A) {
				return true;
			},
			trigger: 'aui-calendar'
		}
	);

	var loader = A.Env._loader;

	loader.addGroup(GROUPS.liferay, 'liferay');
	loader.addGroup(GROUPS.misc, 'misc');
	loader.addGroup(GROUPS.portal, 'portal');
})(AUI(), Liferay);