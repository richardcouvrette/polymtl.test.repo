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

package com.liferay.portal.spring.transaction;

import com.liferay.portal.kernel.util.AutoResetThreadLocal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author Shuyang Zhou
 */
public class TransactionCommitCallbackUtil {

	public static void registerCallback(Callable<?> callable) {
		List<List<Callable<?>>> callbackListList =
			_callbackListListThreadLocal.get();

		int index = callbackListList.size() - 1;

		List<Callable<?>> callableList = callbackListList.get(index);

		if (callableList == Collections.EMPTY_LIST) {
			callableList = new ArrayList<Callable<?>>();

			callbackListList.set(index, callableList);
		}

		callableList.add(callable);
	}

	protected static List<Callable<?>> popCallbackList() {
		List<List<Callable<?>>> callbackListList =
			_callbackListListThreadLocal.get();

		return callbackListList.remove(callbackListList.size() - 1);
	}

	protected static void pushCallbackList() {
		List<List<Callable<?>>> callbackListList =
			_callbackListListThreadLocal.get();

		callbackListList.add(Collections.EMPTY_LIST);
	}

	private static ThreadLocal<List<List<Callable<?>>>>
		_callbackListListThreadLocal =
			new AutoResetThreadLocal<List<List<Callable<?>>>>(
				TransactionCommitCallbackUtil.class +
					"._callbackListListThreadLocal") {

				@Override
				protected List<List<Callable<?>>> initialValue() {
					return new ArrayList<List<Callable<?>>>();
				}

			};

}