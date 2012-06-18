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

package com.liferay.portlet.messageboards.pop;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.pop.MessageListener;
import com.liferay.portal.kernel.pop.MessageListenerException;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionCheckerUtil;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.messageboards.NoSuchCategoryException;
import com.liferay.portlet.messageboards.NoSuchMessageException;
import com.liferay.portlet.messageboards.model.MBCategory;
import com.liferay.portlet.messageboards.model.MBCategoryConstants;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.model.MBMessageConstants;
import com.liferay.portlet.messageboards.service.MBCategoryLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBMessageServiceUtil;
import com.liferay.portlet.messageboards.util.MBMailMessage;
import com.liferay.portlet.messageboards.util.MBUtil;

import java.io.InputStream;

import java.util.List;

import javax.mail.Message;

import org.apache.commons.lang.time.StopWatch;

/**
 * @author Brian Wing Shun Chan
 * @author Jorge Ferrer
 * @author Michael C. Han
 */
public class MessageListenerImpl implements MessageListener {

	public boolean accept(String from, String recipient, Message message) {
		try {
			String messageId = getMessageId(recipient, message);

			if ((messageId == null) ||
				(!messageId.startsWith(
					MBUtil.MESSAGE_POP_PORTLET_PREFIX, getOffset()))) {

				return false;
			}

			Company company = getCompany(messageId);
			long categoryId = getCategoryId(messageId);

			MBCategory category = MBCategoryLocalServiceUtil.getCategory(
				categoryId);

			if (category.getCompanyId() != company.getCompanyId()) {
				return false;
			}

			if (_log.isDebugEnabled()) {
				_log.debug("Check to see if user " + from + " exists");
			}

			UserLocalServiceUtil.getUserByEmailAddress(
				company.getCompanyId(), from);

			return true;
		}
		catch (Exception e) {
			if (_log.isErrorEnabled()) {
				_log.error("Unable to process message: " + message, e);
			}

			return false;
		}
	}

	public void deliver(String from, String recipient, Message message)
		throws MessageListenerException {

		List<ObjectValuePair<String, InputStream>> inputStreamOVPs = null;

		try {
			StopWatch stopWatch = null;

			if (_log.isDebugEnabled()) {
				stopWatch = new StopWatch();

				stopWatch.start();

				_log.debug("Deliver message from " + from + " to " + recipient);
			}

			String messageId = getMessageId(recipient, message);

			Company company = getCompany(messageId);

			if (_log.isDebugEnabled()) {
				_log.debug("Message id " + messageId);
			}

			long groupId = 0;
			long categoryId = getCategoryId(messageId);

			try {
				MBCategory category = MBCategoryLocalServiceUtil.getCategory(
					categoryId);

				groupId = category.getGroupId();
		 	}
		 	catch (NoSuchCategoryException nsce) {
		 		groupId = categoryId;
		 		categoryId = MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID;
		 	}

		 	if (_log.isDebugEnabled()) {
		 		_log.debug("Group id " + groupId);
		 		_log.debug("Category id " + categoryId);
		 	}

			User user = UserLocalServiceUtil.getUserByEmailAddress(
				company.getCompanyId(), from);

			long parentMessageId = getParentMessageId(recipient, message);

			if (_log.isDebugEnabled()) {
				_log.debug("Parent message id " + parentMessageId);
			}

			MBMessage parentMessage = null;

			try {
				if (parentMessageId > 0) {
					parentMessage = MBMessageLocalServiceUtil.getMessage(
						parentMessageId);
				}
			}
			catch (NoSuchMessageException nsme) {

				// If the parent message does not exist we ignore it and post
				// the message as a new thread.

			}

			if (_log.isDebugEnabled()) {
				_log.debug("Parent message " + parentMessage);
			}

			String subject = MBUtil.getSubjectWithoutMessageId(message);

			MBMailMessage mbMailMessage = new MBMailMessage();

			MBUtil.collectPartContent(message, mbMailMessage);

			inputStreamOVPs = mbMailMessage.getInputStreamOVPs();

			PermissionCheckerUtil.setThreadValues(user);

			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setAddGroupPermissions(true);
			serviceContext.setAddGuestPermissions(true);
			serviceContext.setLayoutFullURL(
				PortalUtil.getLayoutFullURL(
					groupId, PortletKeys.MESSAGE_BOARDS));
			serviceContext.setScopeGroupId(groupId);

			if (parentMessage == null) {
				MBMessageServiceUtil.addMessage(
					groupId, categoryId, subject, mbMailMessage.getBody(),
					MBMessageConstants.DEFAULT_FORMAT, inputStreamOVPs, false,
					0.0, true, serviceContext);
			}
			else {
				MBMessageServiceUtil.addMessage(
					groupId, categoryId, parentMessage.getThreadId(),
					parentMessage.getMessageId(), subject,
					mbMailMessage.getBody(), MBMessageConstants.DEFAULT_FORMAT,
					inputStreamOVPs, false, 0.0, true, serviceContext);
			}

			if (_log.isDebugEnabled()) {
				_log.debug(
					"Delivering message takes " + stopWatch.getTime() + " ms");
			}
		}
		catch (PrincipalException pe) {
			if (_log.isDebugEnabled()) {
				_log.debug("Prevented unauthorized post from " + from);
			}

			throw new MessageListenerException(pe);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new MessageListenerException(e);
		}
		finally {
			if (inputStreamOVPs != null) {
				for (ObjectValuePair<String, InputStream> inputStreamOVP :
						inputStreamOVPs) {

					InputStream inputStream = inputStreamOVP.getValue();

					StreamUtil.cleanUp(inputStream);
				}
			}

			PermissionCheckerUtil.setThreadValues(null);
		}
	}

	public String getId() {
		return MessageListenerImpl.class.getName();
	}

	protected long getCategoryId(String recipient) {
		int pos = recipient.indexOf(CharPool.AT);

		String target = recipient.substring(
			MBUtil.MESSAGE_POP_PORTLET_PREFIX.length() + getOffset(), pos);

		String[] parts = StringUtil.split(target, CharPool.PERIOD);

		return GetterUtil.getLong(parts[0]);
	}

	protected Company getCompany(String messageId) throws Exception {
		int pos =
			messageId.indexOf(CharPool.AT) +
				PropsValues.POP_SERVER_SUBDOMAIN.length() + 1;

		if (PropsValues.POP_SERVER_SUBDOMAIN.length() > 0) {
			pos++;
		}

		String mx = messageId.substring(pos, messageId.length() - 1);

		return CompanyLocalServiceUtil.getCompanyByMx(mx);
	}

	protected String getMessageId(String recipient, Message message)
		throws Exception {

		if (PropsValues.POP_SERVER_SUBDOMAIN.length() > 0) {
			return recipient;
		}
		else {
			return MBUtil.getParentMessageIdString(message);
		}
	}

	protected int getOffset() {
		if (PropsValues.POP_SERVER_SUBDOMAIN.length() == 0) {
			return 1;
		}

		return 0;
	}

	protected long getParentMessageId(String recipient, Message message)
		throws Exception {

		if (!StringUtil.startsWith(
				recipient, MBUtil.MESSAGE_POP_PORTLET_PREFIX)) {

			return MBUtil.getParentMessageId(message);
		}

		int pos = recipient.indexOf(CharPool.AT);

		if (pos < 0) {
			return MBUtil.getParentMessageId(message);
		}

		String target = recipient.substring(
			MBUtil.MESSAGE_POP_PORTLET_PREFIX.length(), pos);

		String[] parts = StringUtil.split(target, CharPool.PERIOD);

		long parentMessageId = 0;

		if (parts.length == 2) {
			parentMessageId = GetterUtil.getLong(parts[1]);
		}

		if (parentMessageId > 0) {
			return parentMessageId;
		}

		return MBUtil.getParentMessageId(message);
	}

	private static Log _log = LogFactoryUtil.getLog(MessageListenerImpl.class);

}