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

package com.liferay.portal.scripting.javascript;

import com.liferay.mozilla.javascript.Context;
import com.liferay.mozilla.javascript.Script;
import com.liferay.mozilla.javascript.Scriptable;
import com.liferay.mozilla.javascript.ScriptableObject;
import com.liferay.portal.kernel.cache.SingleVMPoolUtil;
import com.liferay.portal.kernel.scripting.BaseScriptingExecutor;
import com.liferay.portal.kernel.scripting.ScriptingException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Alberto Montero
 */
public class JavaScriptExecutor extends BaseScriptingExecutor {

	@Override
	public void clearCache() {
		SingleVMPoolUtil.clear(_CACHE_NAME);
	}

	public Map<String, Object> eval(
			Set<String> allowedClasses, Map<String, Object> inputObjects,
			Set<String> outputNames, String script)
		throws ScriptingException {

		Script compiledScript = getCompiledScript(script);

		try {
			Context context = Context.enter();

			Scriptable scriptable = context.initStandardObjects();

			for (Map.Entry<String, Object> entry : inputObjects.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();

				ScriptableObject.putProperty(
					scriptable, key, Context.javaToJS(value, scriptable));
			}

			if (allowedClasses != null) {
				context.setClassShutter(
					new JavaScriptClassVisibilityChecker(allowedClasses));
			}

			compiledScript.exec(context, scriptable);

			if (outputNames == null) {
				return null;
			}

			Map<String, Object> outputObjects = new HashMap<String, Object>();

			for (String outputName : outputNames) {
				outputObjects.put(
					outputName,
					ScriptableObject.getProperty(scriptable, outputName));
			}

			return outputObjects;
		}
		catch (Exception e) {
			throw new ScriptingException(e.getMessage() + "\n\n", e);
		}
		finally {
			Context.exit();
		}
	}

	public String getLanguage() {
		return _LANGUAGE;
	}

	protected Script getCompiledScript(String script) {
		String key = String.valueOf(script.hashCode());

		Script compiledScript = (Script)SingleVMPoolUtil.get(_CACHE_NAME, key);

		if (compiledScript == null) {
			try {
				Context context = Context.enter();

				compiledScript = context.compileString(
					script, "script", 0, null);
			}
			finally {
				Context.exit();
			}

			SingleVMPoolUtil.put(_CACHE_NAME, key, compiledScript);
		}

		return compiledScript;
	}

	private static final String _CACHE_NAME =
		JavaScriptExecutor.class.getName();

	private static final String _LANGUAGE = "javascript";

}