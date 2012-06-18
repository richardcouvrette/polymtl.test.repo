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

package com.liferay.portal.kernel.messaging;

import com.liferay.portal.kernel.concurrent.ThreadPoolExecutor;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CentralizedThreadLocal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.auth.CompanyThreadLocal;
import com.liferay.portal.security.auth.PrincipalThreadLocal;

import java.util.Set;

/**
 * <p>
 * Destination that delivers a message to a list of message listeners one at a
 * time.
 * </p>
 *
 * @author Michael C. Han
 */
public class SerialDestination extends BaseAsyncDestination {

	public SerialDestination() {
		super();

		setWorkersCoreSize(_WORKERS_CORE_SIZE);
		setWorkersMaxSize(_WORKERS_MAX_SIZE);
	}

	/**
	 * @deprecated
	 */
	public SerialDestination(String name) {
		super(name, _WORKERS_CORE_SIZE, _WORKERS_MAX_SIZE);
	}

	@Override
	protected void dispatch(
		final Set<MessageListener> messageListeners, final Message message) {

		if (!message.contains("companyId")) {
			message.put("companyId", CompanyThreadLocal.getCompanyId());
		}

		if (!message.contains("principalName")) {
			message.put("principalName", PrincipalThreadLocal.getName());
		}

		if (!message.contains("principalPassword")) {
			message.put(
				"principalPassword", PrincipalThreadLocal.getPassword());
		}

		ThreadPoolExecutor threadPoolExecutor = getThreadPoolExecutor();

		Runnable runnable = new MessageRunnable(message) {

			public void run() {
				long companyId = CompanyThreadLocal.getCompanyId();
				String principalName = PrincipalThreadLocal.getName();
				String principalPassword = PrincipalThreadLocal.getPassword();

				try {
					long messageCompanyId = message.getLong("companyId");

					if (messageCompanyId > 0) {
						CompanyThreadLocal.setCompanyId(messageCompanyId);
					}

					String messagePrincipalName = message.getString(
						"principalName");

					if (Validator.isNotNull(messagePrincipalName)) {
						PrincipalThreadLocal.setName(messagePrincipalName);
					}

					String messagePrincipalPassword = message.getString(
						"principalPassword");

					if (Validator.isNotNull(messagePrincipalPassword)) {
						PrincipalThreadLocal.setPassword(
							messagePrincipalPassword);
					}

					for (MessageListener messageListener : messageListeners) {
						try {
							messageListener.receive(message);
						}
						catch (MessageListenerException mle) {
							_log.error(
								"Unable to process message " + message, mle);
						}
					}
				}
				finally {
					CompanyThreadLocal.setCompanyId(companyId);
					PrincipalThreadLocal.setName(principalName);
					PrincipalThreadLocal.setPassword(principalPassword);

					CentralizedThreadLocal.clearShortLivedThreadLocals();
				}
			}

		};

		threadPoolExecutor.execute(runnable);
	}

	private static final int _WORKERS_CORE_SIZE = 1;

	private static final int _WORKERS_MAX_SIZE = 1;

	private static Log _log = LogFactoryUtil.getLog(SerialDestination.class);

}