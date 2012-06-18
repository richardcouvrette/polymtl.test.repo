;(function(A, Liferay) {
	var AArray = A.Array;
	var Browser = Liferay.Browser;
	var Lang = A.Lang;

	var isArray = Lang.isArray;
	var arrayIndexOf = AArray.indexOf;

	var htmlEscapedValues = [];
	var htmlUnescapedValues = [];

	var MAP_HTML_CHARS_ESCAPED = {
		'&': '&amp;',
		'<': '&lt;',
		'>': '&gt;',
		'"': '&#034;',
		'\'': '&#039;',
		'/': '&#047;',
		'`': '&#096;'
	};

	var MAP_HTML_CHARS_UNESCAPED = {};

	for (var i in MAP_HTML_CHARS_ESCAPED) {
		if (MAP_HTML_CHARS_ESCAPED.hasOwnProperty(i)) {
			var escapedValue = MAP_HTML_CHARS_ESCAPED[i];

			MAP_HTML_CHARS_UNESCAPED[escapedValue] = i;

			htmlEscapedValues.push(escapedValue);
			htmlUnescapedValues.push(i);
		}
	}

	var REGEX_DASH = /-([a-z])/gi;

	var REGEX_HTML_ESCAPE = new RegExp('[' + htmlUnescapedValues.join('') + ']', 'g');

	var REGEX_HTML_UNESCAPE = new RegExp(htmlEscapedValues.join('|'), 'gi');

	var SRC_HIDE_LINK = {
		src: 'hideLink'
	};

	var Window = {
		ALIGN_CENTER: {
			points: ['tc', 'tc']
		},
		XY: [50, 100],
		XY_INCREMENTOR: 50,
		_map: {}
	};

	var Util = {
		submitCountdown: 0,

		actsAsAspect: function(object) {
			object.yield = null;
			object.rv = {};

			object.before = function(method, f) {
				var original = eval('this.' + method);

				this[method] = function() {
					f.apply(this, arguments);

					return original.apply(this, arguments);
				};
			};

			object.after = function(method, f) {
				var original = eval('this.' + method);

				this[method] = function() {
					this.rv[method] = original.apply(this, arguments);

					return f.apply(this, arguments);
				};
			};

			object.around = function(method, f) {
				var original = eval('this.' + method);

				this[method] = function() {
					this.yield = original;

					return f.apply(this, arguments);
				};
			};
		},

		addInputFocus: function() {
			A.use(
				'aui-base',
				function(A) {
					var handleFocus = function(event) {
						var target = event.target;

						var tagName = target.get('tagName');

						if (tagName) {
							tagName = tagName.toLowerCase();
						}

						var nodeType = target.get('type');

						if (((tagName == 'input') && (/text|password/).test(nodeType)) ||
							(tagName == 'textarea')) {

							var action = 'addClass';

							if (/blur|focusout/.test(event.type)) {
								action = 'removeClass';
							}

							target[action]('focus');
						}
					};

					A.on('focus', handleFocus, document);
					A.on('blur', handleFocus, document);
				}
			);

			Util.addInputFocus = function(){};
		},

		addInputType: function(el) {
			Util.addInputType = Lang.emptyFn;

			if (Browser.isIe() && Browser.getMajorVersion() < 7) {
				Util.addInputType = function(el) {
					var item;

					if (el) {
						el = A.one(el);
					}
					else {
						el = A.one(document.body);
					}

					var defaultType = 'text';

					el.all('input').each(
						function(item, index, collection) {
							var type = item.get('type') || defaultType;

							item.addClass(type);
						}
					);
				};
			}

			return Util.addInputType(el);
		},

		addParams: function(params, url) {
			A.use('querystring-stringify-simple');

			if (Lang.isObject(params)) {
				params = A.QueryString.stringify(params);
			}
			else {
				params = Lang.trim(params);
			}

			if (params) {
				var loc = url || location.href;
				var anchorHash, finalUrl;

				if (loc.indexOf('#') > -1) {
					var locationPieces = loc.split('#');
					loc = locationPieces[0];
					anchorHash = locationPieces[1];
				}

				if (loc.indexOf('?') == -1) {
					params = '?' + params;
				}
				else {
					params = '&' + params;
				}

				if (loc.indexOf(params) == -1) {
					finalUrl = loc + params;

					if (anchorHash) {
						finalUrl += '#' + anchorHash;
					}
					if (!url) {
						location.href = finalUrl;
					}

					return finalUrl;
				}
			}
		},

		camelize: function(value, separator) {
			var regex = REGEX_DASH;

			if (separator) {
				regex = new RegExp(separator + '([a-z])', 'gi');
			}

			value = value.replace(
				regex,
				function(match0, match1) {
					return match1.toUpperCase();
				}
			);

			return value;
		},

		checkTab: function(box) {
			if ((document.all) && (event.keyCode == 9)) {
				box.selection = document.selection.createRange();

				setTimeout(
					function() {
						Util.processTab(box.id);
					},
					0
				);
			}
		},

		clamp: function(value, min, max) {
			return Math.min(Math.max(value, min), max);
		},

		disableEsc: function() {
			if ((document.all) && (event.keyCode == 27)) {
				event.returnValue = false;
			}
		},

		disableFormButtons: function(inputs, form) {
			inputs.set('disabled', true);
			inputs.setStyle('opacity', 0.5);
		},

		enableFormButtons: function(inputs, form) {
			Util._submitLocked = null;

			document.body.style.cursor = 'auto';

			inputs.set('disabled', false);
			inputs.setStyle('opacity', 1);
		},

		endsWith: function(str, x) {
			return (str.lastIndexOf(x) === (str.length - x.length));
		},

		escapeCDATA: function(str) {
			return str.replace(
				/<!\[CDATA\[|\]\]>/gi,
				function(match) {
					var str = '';

					if (match == ']]>') {
						str = ']]&gt;';
					}
					else if (match == '<![CDATA[') {
						str = '&lt;![CDATA[';
					}

					return str;
				}
			);
		},

		escapeHTML: function(str) {
			return str.replace(REGEX_HTML_ESCAPE, Util._escapeHTML);
		},

		getColumnId: function(str) {
			var columnId = str.replace(/layout-column_/, '');

			return columnId;
		},

		getHistoryParam: function(portletNamespace) {
			var historyKey = '&' + portletNamespace + 'historyKey=';
			var historyParam = '';

			if (location.hash) {
				historyParam = location.hash.replace('#_LFR_FN_', historyKey);
			}
			else if (location.href.indexOf(historyKey) > -1) {
				var historyParamRE = new RegExp(historyKey + '([^#&]+)');

				historyParam = location.href.match(historyParamRE);

				historyParam = historyParam && historyParam[0];
			}

			return historyParam;
		},

		getOpener: function() {
			var openingWindow = Window._opener;

			if (!openingWindow) {
				var topUtil = Liferay.Util.getTop().Liferay.Util;

				var windowName = Liferay.Util.getWindowName();

				var dialog = topUtil.Window._map[windowName];

				if (dialog) {
					openingWindow = topUtil.Window._map[windowName]._opener;

					Window._opener = openingWindow;
				}
			}

			return openingWindow || window.opener || window.parent;
		},

		getPortletId: function(portletId) {
			portletId = portletId.replace(/^p_p_id_/i, '');
			portletId = portletId.replace(/_$/, '');

			return portletId;
		},

		getPortletNamespace: function(portletId) {
			return '_' + portletId + '_';
		},

		getTop: function() {
			var topWindow = Util._topWindow;

			if (!topWindow) {
				var parentWindow = window.parent;

				var parentThemeDisplay;

				while (parentWindow != window) {
					try {
						if (typeof parentWindow.location.href == 'undefined') {
							break;
						}
					}
					catch (e) {
						break;
					}

					parentThemeDisplay = parentWindow.themeDisplay;

					if (!parentThemeDisplay) {
						break;
					}
					else if (!parentThemeDisplay.isStatePopUp() || (parentWindow == parentWindow.parent)) {
						topWindow = parentWindow;

						break;
					}

					parentWindow = parentWindow.parent;
				}

				if (!topWindow) {
					topWindow = window;
				}

				Util._topWindow = topWindow;
			}

			return topWindow;
		},

		getWindow: function(id) {
			if (!id) {
				id = Util.getWindowName();
			}

			return Util.getTop().Liferay.Util.Window._map[id];
		},

		getWindowName: function() {
			return window.name || Window._name || '';
		},

		getURLWithSessionId: function(url) {
			if (!themeDisplay.isAddSessionIdToURL()) {
				return url;
			}

			// LEP-4787

			var x = url.indexOf(';');

			if (x > -1) {
				return url;
			}

			var sessionId = ';jsessionid=' + themeDisplay.getSessionId();

			x = url.indexOf('?');

			if (x > -1) {
				return url.substring(0, x) + sessionId + url.substring(x);
			}

			// In IE6, http://www.abc.com;jsessionid=XYZ does not work, but
			// http://www.abc.com/;jsessionid=XYZ does work.

			x = url.indexOf('//');

			if (x > -1) {
				var y = url.lastIndexOf('/');

				if (x + 1 == y) {
					return url + '/' + sessionId;
				}
			}

			return url + sessionId;
		},

		isArray: function(object) {
			return !!(window.Array && object.constructor == window.Array);
		},

		isEditorPresent: function(editorImpl) {
			return Liferay.EDITORS && Liferay.EDITORS[editorImpl];
		},

		openWindow: function(config) {
			config.openingWindow = window;

			var top = Util.getTop();

			var topUtil = top.Liferay.Util;
			var topAUI = top.AUI;

			topUtil._openWindowProvider(config);
		},

		processTab: function(id) {
			document.all[id].selection.text = String.fromCharCode(9);
			document.all[id].focus();
		},

		randomInt: function() {
			return (Math.ceil(Math.random() * (new Date).getTime()));
		},

		randomMinMax: function(min, max) {
			return (Math.round(Math.random() * (max - min))) + min;
		},

		selectAndCopy: function(el) {
			el.focus();
			el.select();

			if (document.all) {
				var textRange = el.createTextRange();

				textRange.execCommand('copy');
			}
		},

		setBox: function(oldBox, newBox) {
			for (var i = oldBox.length - 1; i > -1; i--) {
				oldBox.options[i] = null;
			}

			for (var i = 0; i < newBox.length; i++) {
				oldBox.options[i] = new Option(newBox[i].value, i);
			}

			oldBox.options[0].selected = true;
		},

		showCapsLock: function(event, span) {
			var keyCode = event.keyCode ? event.keyCode : event.which;
			var shiftKey = event.shiftKey ? event.shiftKey : ((keyCode == 16) ? true : false);

			if (((keyCode >= 65 && keyCode <= 90) && !shiftKey) ||
				((keyCode >= 97 && keyCode <= 122) && shiftKey)) {

				document.getElementById(span).style.display = '';
			}
			else {
				document.getElementById(span).style.display = 'none';
			}
		},

		sortByAscending: function(a, b) {
			a = a[1].toLowerCase();
			b = b[1].toLowerCase();

			if (a > b) {
				return 1;
			}

			if (a < b) {
				return -1;
			}

			return 0;
		},

		startsWith: function(str, x) {
			return (str.indexOf(x) === 0);
		},

		textareaTabs: function(event) {
			var el = event.currentTarget.getDOM();
			var pressedKey = event.keyCode;

			if (event.isKey('TAB')) {
				event.halt();

				var oldscroll = el.scrollTop;

				if (el.setSelectionRange) {
					var caretPos = el.selectionStart + 1;
					var elValue = el.value;

					el.value = elValue.substring(0, el.selectionStart) + '\t' + elValue.substring(el.selectionEnd, elValue.length);

					setTimeout(
						function() {
							el.focus();
							el.setSelectionRange(caretPos, caretPos);
						}, 0);

				}
				else {
					document.selection.createRange().text='\t';
				}

				el.scrollTop = oldscroll;

				return false;
			}
		},

		toCharCode: A.cached(
			function(name) {
				var buffer = [];

				name = unescape(escape(name).replace(/%u/g, '\\u'));

				for (var i = 0; i < name.length; i++) {
					buffer[i] = name.charCodeAt(i);
				}

				return buffer.join('');
			}
		),

		toNumber: function(value) {
			return parseInt(value, 10) || 0;
		},

		uncamelize: function(value, separator) {
			separator = separator || ' ';

			value = value.replace(/([a-zA-Z][a-zA-Z])([A-Z])([a-z])/g, '$1' + separator + '$2$3');
			value = value.replace(/([a-z])([A-Z])/g, '$1' + separator + '$2');

			return value;
		},

		unescapeHTML: function(str) {
			return str.replace(REGEX_HTML_UNESCAPE, Util._unescapeHTML);
		},

		_defaultSubmitFormFn: function(event) {
			var form = event.form;
			var action = event.action;
			var singleSubmit = event.singleSubmit;

			var inputs = form.all('input[type=button], input[type=reset], input[type=submit]');

			Util.disableFormButtons(inputs, form);

			if (singleSubmit === false) {
				Util._submitLocked = A.later(
					1000,
					Util,
					Util.enableFormButtons,
					[inputs, form]
				);
			}
			else {
				Util._submitLocked = true;
			}

			if (action != null) {
				form.attr('action', action);
			}

			form.submit();

			form.attr('target', '');
		},

		_escapeHTML: function(match) {
			return MAP_HTML_CHARS_ESCAPED[match];
		},

		_getEditableInstance: function(title) {
			var editable = Util._EDITABLE;

			if (!editable) {
				editable = new A.Editable(
					{
						after: {
							contentTextChange: function(event) {
								var instance = this;

								if (!event.initial) {
									var title = instance.get('node');

									var portletTitleEditOptions = title.getData('portletTitleEditOptions');

									Util.savePortletTitle(
										{
											doAsUserId: portletTitleEditOptions.doAsUserId,
											plid: portletTitleEditOptions.plid,
											portletId: portletTitleEditOptions.portletId,
											title: event.newVal
										}
									);
								}
							},
							startEditing: function(event) {
								var instance = this;

								var Layout = Liferay.Layout;

								if (Layout) {
									instance._dragListener = Layout.getLayoutHandler().on(
										'drag:start',
										function(event) {
											instance.fire('save');
										}
									);
								}
							},
							stopEditing: function(event) {
								var instance = this;

								if (instance._dragListener) {
									instance._dragListener.detach();
								}
							}
						},
						cssClass: 'lfr-portlet-title-editable',
						node: title
					}
				);

				Util._EDITABLE = editable;
			}

			return editable;
		},

		_unescapeHTML: function(match) {
			return MAP_HTML_CHARS_UNESCAPED[match];
		}
	};

	Liferay.provide(
		Util,
		'afterIframeLoaded',
		function(event) {
			var iframeBody = A.one(event.doc.body);

			iframeBody.addClass('aui-dialog-iframe-popup');

			var closeButton = iframeBody.one('.aui-button-input-cancel');
			var hideLink = iframeBody.one('.lfr-hide-dialog');

			var dialog = event.dialog;

			if (closeButton) {
				closeButton.on('click', dialog.close, dialog);
			}

			if (hideLink) {
				hideLink.on(
					'click',
					function(){
						dialog.set('visible', false, SRC_HIDE_LINK);
					}
				);
			}

			var rolesSearchContainer = iframeBody.one('#rolesSearchContainerSearchContainer');

			if (rolesSearchContainer) {
				rolesSearchContainer.delegate(
					'click',
					function(event){
						event.preventDefault();

						submitForm(document.hrefFm, event.currentTarget.attr('href'));
					},
					'a'
				);
			}
		},
		['aui-base']
	);

	Liferay.provide(
		Util,
		'check',
		function(form, name, checked) {
			var checkbox = A.one(form[name]);

			if (checkbox) {
				checkbox.set('checked', checked);
			}
		},
		['aui-base']
	);

	Liferay.provide(
		Util,
		'checkAll',
		function(form, name, allBox, selectClassName) {
			var selector;

			if (isArray(name)) {
				selector = 'input[name='+ name.join('], input[name=') + ']';
			}
			else {
				selector = 'input[name=' + name + ']';
			}

			form = A.one(form);

			form.all(selector).set('checked', A.one(allBox).get('checked'));

			if (selectClassName) {
				form.all(selectClassName).toggleClass('selected', A.one(allBox).get('checked'));
			}
		},
		['aui-base']
	);

	Liferay.provide(
		Util,
		'checkAllBox',
		function(form, name, allBox) {
			var totalBoxes = 0;
			var totalOn = 0;
			var inputs = A.one(form).all('input[type=checkbox]');

			allBox = A.one(allBox) || A.one(form).one('input[name=' + allBox + ']');

			if (!isArray(name)) {
				name = [name];
			}

			inputs.each(
				function(item, index, collection) {
					if (!item.compareTo(allBox)) {
						if (arrayIndexOf(name, item.getAttribute('name')) > -1) {
							totalBoxes++;
						}

						if (item.get('checked')) {
							totalOn++;
						}
					}
				}
			);

			allBox.set('checked', (totalBoxes == totalOn));
		},
		['aui-base']
	);

	Liferay.provide(
		Util,
		'createFlyouts',
		function(options) {
			options = options || {};

			var flyout = A.one(options.container);
			var containers = [];

			if (flyout) {
				var lis = flyout.all('li');

				lis.each(
					function(item, index, collection) {
						var childUL = item.one('ul');

						if (childUL) {
							childUL.hide();

							item.addClass('lfr-flyout');
							item.addClass('has-children lfr-flyout-has-children');
						}
					}
				);

				var hideTask = A.debounce(
					function(event) {
						showTask.cancel();

						var li = event.currentTarget;

						if (li.hasClass('has-children')) {
							var childUL = event.currentTarget.one('> ul');

							if (childUL) {
								childUL.hide();

								if (options.mouseOut) {
									options.mouseOut.apply(event.currentTarget, [event]);
								}
							}
						}
					},
					300
				);

				var showTask = A.debounce(
					function(event) {
						hideTask.cancel();

						var li = event.currentTarget;

						if (li.hasClass('has-children')) {
							var childUL = event.currentTarget.one('> ul');

							if (childUL) {
								childUL.show();

								if (options.mouseOver) {
									options.mouseOver.apply(event.currentTarget, [event]);
								}
							}
						}
					},
					0
				);

				lis.on('mouseenter', showTask, 'li');
				lis.on('mouseleave', hideTask, 'li');
			}
		},
		['aui-base']
	);

	Liferay.provide(
		Util,
		'disableElements',
		function(obj) {
			var el = A.one(obj);

			if (el) {
				el = el.getDOM();

				var children = el.getElementsByTagName('*');

				var emptyFnFalse = Lang.emptyFnFalse;
				var Event = A.Event;

				for (var i = children.length - 1; i >= 0; i--) {
					var item = children[i];

					item.style.cursor = 'default';

					el.onclick = emptyFnFalse;
					el.onmouseover = emptyFnFalse;
					el.onmouseout = emptyFnFalse;
					el.onmouseenter = emptyFnFalse;
					el.onmouseleave = emptyFnFalse;

					Event.purgeElement(el, false);

					item.href = 'javascript:;';
					item.disabled = true;
					item.action = '';
					item.onsubmit = emptyFnFalse;
				}
			}
		},
		['aui-base']
	);

	Liferay.provide(
		Util,
		'disableTextareaTabs',
		function(textarea) {
			textarea = A.one(textarea);

			if (textarea && textarea.attr('textareatabs') != 'enabled') {
				textarea.attr('textareatabs', 'disabled');

				textarea.detach('keydown', Util.textareaTabs);
			}
		},
		['aui-base']
	);

	Liferay.provide(
		Util,
		'disableToggleBoxes',
		function(checkBoxId, toggleBoxId, checkDisabled) {
			var checkBox = A.one('#' + checkBoxId);
			var toggleBox = A.one('#' + toggleBoxId);

			if (checkBox && toggleBox) {
				if (checkBox.get('checked') && checkDisabled) {
					toggleBox.set('disabled', true);
				}
				else {
					toggleBox.set('disabled', false);
				}

				checkBox.on(
					'click',
					function() {
						toggleBox.set('disabled', !toggleBox.get('disabled'));
					}
				);
			}
		},
		['aui-base']
	);

	Liferay.provide(
		Util,
		'enableTextareaTabs',
		function(textarea) {
			textarea = A.one(textarea);

			if (textarea && textarea.attr('textareatabs') != 'enabled') {
				textarea.attr('textareatabs', 'disabled');

				textarea.on('keydown', Util.textareaTabs);
			}
		},
		['aui-base']
	);

	Liferay.provide(
		Util,
		'focusFormField',
		function(el, caretPosition) {
			Util.addInputFocus();

			var interacting = false;

			var clickHandle = A.getDoc().on(
				'click',
				function(event) {
					interacting = true;

					clickHandle.detach();
				}
			);

			if (!interacting) {
				el = A.one(el);

				try {
					el.focus();
				}
				catch (e) {
				}
			}
		},
		['aui-base']
	);

	Liferay.provide(
		Util,
		'forcePost',
		function(link) {
			link = A.one(link);

			if (link) {
				var url = link.attr('href');

				var newWindow = (link.attr('target') == '_blank');

				if (newWindow) {
					A.one(document.hrefFm).attr('target', '_blank');
				}

				submitForm(document.hrefFm, url, !newWindow);

				Util._submitLocked = null;
			}
		},
		['aui-base']
	);

	/**
	 * OPTIONS
	 *
	 * Required
	 * button {string|object}: The button that opens the popup when clicked.
	 * height {number}: The height to set the popup to.
	 * textarea {string}: the name of the textarea to auto-resize.
	 * url {string}: The url to open that sets the editor.
	 * width {number}: The width to set the popup to.
	 */

	Liferay.provide(
		Util,
		'inlineEditor',
		function(options) {
			if (options.uri && options.button) {
				var button = options.button;
				var height = options.height || 640;
				var textarea = options.textarea;
				var uri = options.uri;
				var width = options.width || 680;

				var editorButton = A.one(button);

				if (editorButton) {
					delete options.button;

					editorButton.on(
						'click',
						function(event) {
							Util.openWindow(options);
						}
					);
				}
			}
		},
		['aui-dialog', 'aui-io']
	);

	Liferay.provide(
		Util,
		'moveItem',
		function(fromBox, toBox, sort) {
			fromBox = A.one(fromBox);
			toBox = A.one(toBox);

			var selectedIndex = fromBox.get('selectedIndex');

			var selectedOption;

			if (selectedIndex >= 0) {
				var options = fromBox.all('option');

				selectedOption = options.item(selectedIndex);

				options.each(
					function(item, index, collection) {
						if (item.get('selected')) {
							toBox.append(item);
						}
					}
				);
			}

			if (selectedOption && selectedOption.text() != '' && sort == true) {
				Util.sortBox(toBox);
			}
		},
		['aui-base']
	);

	Liferay.provide(
		Util,
		'openDDMPortlet',
		function(config) {
			var instance = this;

			var defaultValues = {
				availableFields: 'Liferay.FormBuilder.AVAILABLE_FIELDS.DDM_STRUCTURE',
				structureName: 'structures'
			};

			config = A.merge(defaultValues,	config);

			var ddmURL = Liferay.PortletURL.createRenderURL();

			ddmURL.setEscapeXML(false);

			ddmURL.setDoAsGroupId(config.doAsGroupId || themeDisplay.getScopeGroupId());

			ddmURL.setParameter('chooseCallback', config.chooseCallback);
			ddmURL.setParameter('ddmResource', config.ddmResource);
			ddmURL.setParameter('saveCallback', config.saveCallback);
			ddmURL.setParameter('scopeAvailableFields', config.availableFields);
			ddmURL.setParameter('scopeStorageType', config.storageType);
			ddmURL.setParameter('scopeStructureName', config.structureName);
			ddmURL.setParameter('scopeStructureType', config.structureType);
			ddmURL.setParameter('scopeTemplateMode', config.templateMode);
			ddmURL.setParameter('scopeTemplateType', config.templateType);

			if ('showGlobalScope' in config) {
				ddmURL.setParameter('showGlobalScope', config.showGlobalScope);
			}

			if ('showManageTemplates' in config) {
				ddmURL.setParameter('showManageTemplates', config.showManageTemplates);
			}

			if ('showToolbar' in config) {
				ddmURL.setParameter('showToolbar', config.showToolbar);
			}

			ddmURL.setParameter('structureId', config.structureId);

			if (config.struts_action) {
				ddmURL.setParameter('struts_action', config.struts_action);
			}
			else {
				ddmURL.setParameter('struts_action', '/dynamic_data_mapping/view');
			}

			ddmURL.setParameter('templateHeaderTitle', config.templateHeaderTitle);
			ddmURL.setParameter('templateId', config.templateId);

			ddmURL.setPortletId(166);
			ddmURL.setWindowState('pop_up');

			config.uri = ddmURL.toString();

			var dialogConfig = config.dialog;

			if (!dialogConfig) {
				dialogConfig = {};

				config.dialog = dialogConfig;
			}

			if (!('align' in dialogConfig)) {
				dialogConfig.align = Util.Window.ALIGN_CENTER;
			}

			Util.openWindow(config);
		},
		['liferay-portlet-url']
	);

	Liferay.provide(
		Util,
		'portletTitleEdit',
		function(options) {
			var obj = options.obj;

			if (obj && !obj.hasClass('portlet-borderless')) {
				var title = obj.one('.portlet-title-text');

				if (title && !title.hasClass('not-editable')) {
					title.setData('portletTitleEditOptions', options);

					title.on(
						'click',
						function(event) {
							var editable = Util._getEditableInstance(title);

							var rendered = editable.get('rendered');

							if (rendered) {
								editable.fire('stopEditing');
							}

							editable.set('node', event.currentTarget);

							if (rendered) {
								editable.syncUI();
							}

							editable._startEditing(event);
						}
					);
				}
			}
		},
		['aui-editable']
	);

	Liferay.provide(
		Util,
		'removeFolderSelection',
		function(folderIdString, folderNameString, namespace) {
			A.byIdNS(namespace, folderIdString).val(0);

			var nameEl = A.byIdNS(namespace, folderNameString);

			nameEl.attr('href', '');

			nameEl.empty();

			var button = A.byIdNS(namespace, 'removeFolderButton');

			if (button) {
				button.attr('disabled', true);

				button.ancestor('.aui-button').addClass('aui-button-disabled');
			}
		},
		['aui-base']
	);

	Liferay.provide(
		Util,
		'removeItem',
		function(box, value) {
			box = A.one(box);

			var selectedIndex = box.get('selectedIndex');

			if (!value) {
				box.all('option').item(selectedIndex).remove(true);
			}
			else {
				box.all('option[value=' + value + ']').item(selectedIndex).remove(true);
			}
		},
		['aui-base']
	);

	Liferay.provide(
		Util,
		'reorder',
		function(box, down) {
			box = A.one(box);

			var selectedIndex = box.get('selectedIndex');

			if (selectedIndex == -1) {
				box.set('selectedIndex', 0);
			}
			else {
				var selectedItems = box.all(':selected');

				var lastIndex = box.get('options').size() - 1;

				var length = selectedItems.size();

				if (down) {
					while (length--) {
						var item = selectedItems.item(length);

						var itemIndex = item.get('index');

						var referenceNode = box.get('firstChild');

						if (itemIndex != lastIndex) {
							var nextSibling = item.next();

							if (nextSibling) {
								referenceNode = nextSibling.next();
							}
						}

						box.insertBefore(item, referenceNode);
					}
				}
				else {
					for (var i = 0; i < length; i++) {
						var item = selectedItems.item(i);

						var itemIndex = item.get('index');

						if (itemIndex == 0) {
							box.append(item);
						}
						else {
							box.insertBefore(item, item.previous());
						}
					}
				}
			}
		},
		['aui-base']
	);

	Liferay.provide(
		Util,
		'resizeTextarea',
		function(elString, usingRichEditor) {
			var el = A.one('#' + elString);

			if (!el) {
				el = A.one('textarea[name=' + elString + ']');
			}

			if (el) {
				var pageBody = A.getBody();

				var diff;

				var resize = function(event) {
					var pageBodyHeight = pageBody.get('winHeight');

					if (usingRichEditor) {
						try {
							if (el.get('nodeName').toLowerCase() != 'iframe') {
								el = window[elString];
							}
						}
						catch (e) {
						}
					}

					if (!diff) {
						var buttonRow = pageBody.one('.aui-button-holder');
						var templateEditor = pageBody.one('.lfr-template-editor');

						if (buttonRow && templateEditor) {
							var region = templateEditor.getXY();

							diff = (buttonRow.outerHeight(true) + region[1]) + 25;
						}
						else {
							diff = 170;
						}
					}

					el = A.one(el);

					var styles = {
						width: '98%'
					};

					if (event) {
						styles.height = (pageBodyHeight - diff);
					}

					if (usingRichEditor) {
						if (!el || !A.DOM.inDoc(el)) {
							A.on(
								'available',
								function(event) {
									el = A.one(window[elString]);

									if (el) {
										el.setStyles(styles);
									}
								},
								'#' + elString + '_cp'
							);

							return;
						}
					}

					if (el) {
						el.setStyles(styles);
					}
				};

				resize();

				var dialog = Liferay.Util.getWindow();

				if (dialog) {
					var resizeEventHandle = dialog.iframe.after('resizeiframe:heightChange', resize);

					A.getWin().on('unload', resizeEventHandle.detach, resizeEventHandle);
				}
			}
		},
		['aui-base']
	);

	Liferay.provide(
		Util,
		'savePortletTitle',
		function(params) {
			A.mix(
				params,
				{
					doAsUserId: 0,
					plid: 0,
					portletId: 0,
					title: '',
					url: themeDisplay.getPathMain() + '/portlet_configuration/update_title'
				}
			);

			A.io.request(
				params.url,
				{
					data: {
						doAsUserId: params.doAsUserId,
						p_l_id: params.plid,
						portletId: params.portletId,
						title: params.title
					}
				}
			);
		},
		['aui-io']
	);

	Liferay.provide(
		Util,
		'selectFolder',
		function(folderData, folderHref, namespace) {
			A.byIdNS(namespace, folderData['idString']).val(folderData['idValue']);

			var nameEl = A.byIdNS(namespace, folderData['nameString']);

			Liferay.Util.addParams(namespace + 'folderId=' + folderData['idValue'], folderHref);

			nameEl.attr('href', folderHref);

			nameEl.setContent(folderData['nameValue'] + '&nbsp;');

			var button = A.byIdNS(namespace, 'removeFolderButton');

			if (button) {
				button.set('disabled', false);

				button.ancestor('.aui-button').removeClass('aui-button-disabled');
			}
		},
		['aui-base']
	);

	Liferay.provide(
		Util,
		'setSelectedValue',
		function(col, value) {
			var option = A.one(col).one('option[value=' + value + ']');

			if (option) {
				option.set('selected', true);
			}
		},
		['aui-base']
	);

	Liferay.provide(
		Util,
		'sortBox',
		function(box) {
			var newBox = [];

			var options = box.all('option');

			for (var i = 0; i < options.size(); i++) {
				newBox[i] = [options.item(i).val(), options.item(i).text()];
			}

			newBox.sort(Util.sortByAscending);

			var boxObj = A.one(box);

			boxObj.all('option').remove(true);

			A.each(
				newBox,
				function(item, index, collection) {
					boxObj.append('<option value="' + item[0] + '">' + item[1] + '</option>');
				}
			);

			if (Browser.isIe()) {
				var currentWidth = boxObj.getStyle('width');

				if (currentWidth == 'auto') {
					boxObj.setStyle('width', 'auto');
				}
			}
		},
		['aui-base']
	);

	/**
	 * OPTIONS
	 *
	 * Required
	 * uri {string}: The url to open that sets the editor.
	 */

	Liferay.provide(
		Util,
		'switchEditor',
		function(options) {
			var uri = options.uri;

			var windowName = Liferay.Util.getWindowName();

			var dialog = Liferay.Util.getWindow(windowName);

			if (dialog) {
				dialog.iframe.set('uri', uri);
			}
		},
		['aui-io']
	);

	Liferay.provide(
		Util,
		'toggleBoxes',
		function(checkBoxId, toggleBoxId, displayWhenUnchecked) {
			var checkBox = A.one('#' + checkBoxId);
			var toggleBox = A.one('#' + toggleBoxId);

			if (checkBox && toggleBox) {
				var checked = checkBox.get('checked');

				if (checked) {
					toggleBox.show();
				}
				else {
					toggleBox.hide();
				}

				if (displayWhenUnchecked) {
					toggleBox.toggle();
				}

				checkBox.on(
					'click',
					function() {
						toggleBox.toggle();
					}
				);
			}
		},
		['aui-base']
	);

	Liferay.provide(
		Util,
		'toggleControls',
		function(node) {
			var docBody = A.getBody();

			node = node || docBody;

			var trigger = node.one('.toggle-controls');

			if (trigger) {
				var hiddenClass = 'controls-hidden';
				var visibleClass = 'controls-visible';
				var currentClass = visibleClass;

				if (Liferay._editControlsState != 'visible') {
					currentClass = hiddenClass;
				}

				docBody.addClass(currentClass);

				trigger.on(
					'click',
					function(event) {
						docBody.toggleClass(visibleClass).toggleClass(hiddenClass);

						Liferay._editControlsState = (docBody.hasClass(visibleClass) ? 'visible' : 'hidden');

						A.io.request(
							themeDisplay.getPathMain() + '/portal/session_click',
							{
								data: {
									'liferay_toggle_controls': Liferay._editControlsState
								}
							}
						);
					}
				);
			}
		},
		['aui-io']
	);

	Liferay.provide(
		Util,
		'toggleRadio',
		function(radioId, showBoxId, hideBoxIds) {
			var radioButton = A.one('#' + radioId);
			var showBox = A.one('#' + showBoxId);

			if (radioButton) {
				var checked = radioButton.get('checked');

				if (showBox) {
					showBox.toggle(checked);
				}

				radioButton.on(
					'change',
					function() {
						if (showBox) {
							showBox.show();
						}

						if (Lang.isValue(hideBoxIds)) {
							if (Lang.isArray(hideBoxIds)) {
								hideBoxIds = hideBoxIds.join(',#');
							}

							A.all('#' + hideBoxIds).hide();
						}
					}
				);
			}
		},
		['aui-base']
	);

	Liferay.provide(
		Util,
		'toggleSelectBox',
		function(selectBoxId, value, toggleBoxId) {
			var selectBox = A.one('#' + selectBoxId);
			var toggleBox = A.one('#' + toggleBoxId);

			if (selectBox && toggleBox) {
				var dynamicValue = Lang.isFunction(value);

				var toggle = function() {
					var currentValue = selectBox.val();

					var visible = (value == currentValue);

					if (dynamicValue) {
						visible = value(currentValue, value);
					}

					toggleBox.toggle(visible);
				};

				toggle();

				selectBox.on('change', toggle);
			}
		},
		['aui-base']
	);

	Liferay.provide(
		Util,
		'updateCheckboxValue',
		function(checkbox) {
			checkbox = A.one(checkbox);

			if (checkbox) {
				var checked = checkbox.attr('checked');

				var value = 'false';

				if (checked) {
					value = checkbox.val();
				}

				checkbox.previous().val(value);
			}
		},
		['aui-base']
	);

	Liferay.provide(
		window,
		'submitForm',
		function(form, action, singleSubmit) {
			if (!Util._submitLocked) {
				Liferay.fire(
					'submitForm',
					{
						form: A.one(form),
						action: action,
						singleSubmit: singleSubmit
					}
				);
			}
		},
		['aui-base']
	);

	Liferay.publish(
		'submitForm',
		{
			defaultFn: Util._defaultSubmitFormFn
		}
	);

	Liferay.provide(
		Util,
		'_openWindowProvider',
		function(config) {
			Util._openWindow(config);
		},
		['liferay-util-window']
	);

	Liferay.after(
		'closeWindow',
		function(event) {
			var id = event.id;

			var dialog = Liferay.Util.getTop().Liferay.Util.Window._map[id];

			if (dialog && dialog.iframe) {
				var dialogWindow = dialog.iframe.node.get('contentWindow').getDOM();

				var openingWindow = dialogWindow.Liferay.Util.getOpener();
				var refresh = event.refresh;

				if (refresh && openingWindow) {
					var data;

					if (!event.portletAjaxable) {
						data = {
							portletAjaxable: false
						};
					}

					openingWindow.Liferay.Portlet.refresh('#p_p_id_' + refresh + '_', data);
				}

				dialog.close();
			}
		}
	);

	Util.Window = Window;

	Liferay.Util = Util;

	// 0-200: Theme Developer
	// 200-400: Portlet Developer
	// 400+: Liferay

	Liferay.zIndex = {
		DOCK: 10,
		DOCK_PARENT: 20,
		ALERT: 430,
		DROP_AREA: 440,
		DROP_POSITION: 450,
		DRAG_ITEM: 460,
		TOOLTIP: 470,
		WINDOW: 1000,
		MENU: 5000
	};
})(AUI(), Liferay);