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

package com.liferay.util.mail;

import com.liferay.mail.model.FileAttachment;
import com.liferay.mail.service.MailServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.log.LogUtil;
import com.liferay.portal.kernel.mail.Account;
import com.liferay.portal.kernel.mail.MailMessage;
import com.liferay.portal.kernel.mail.SMTPAccount;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InfrastructureUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;

import java.net.SocketException;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.lang.time.StopWatch;

/**
 * @author Brian Wing Shun Chan
 * @author Brian Myunghun Kim
 * @author Jorge Ferrer
 * @author Neil Griffin
 * @author Thiago Moreira
 * @author Brett Swaim
 */
public class MailEngine {

	public static Session getSession() {
		return getSession(false);
	}

	public static Session getSession(Account account) {
		Properties properties = _getProperties(account);

		Session session = Session.getInstance(properties);

		if (_log.isDebugEnabled()) {
			session.setDebug(true);

			session.getProperties().list(System.out);
		}

		return session;
	}

	public static Session getSession(boolean cache) {
		Session session = null;

		try {
			session = MailServiceUtil.getSession();
		}
		catch (SystemException se) {
			if (_log.isWarnEnabled()) {
				_log.warn(se, se);
			}

			session = InfrastructureUtil.getMailSession();
		}

		if (_log.isDebugEnabled()) {
			session.setDebug(true);

			session.getProperties().list(System.out);
		}

		return session;
	}

	public static void send(byte[] bytes) throws MailEngineException {
		try {
			Session session = getSession();

			Message message = new MimeMessage(
				session, new UnsyncByteArrayInputStream(bytes));

			_send(session, message, null);
		}
		catch (Exception e) {
			throw new MailEngineException(e);
		}
	}

	public static void send(
			InternetAddress from, InternetAddress to, String subject,
			String body)
		throws MailEngineException {

		send(
			from, new InternetAddress[] {to}, null, null, subject, body, false,
			null, null, null);
	}

	public static void send(
			InternetAddress from, InternetAddress to, String subject,
			String body, boolean htmlFormat)
		throws MailEngineException {

		send(
			from, new InternetAddress[] {to}, null, null, subject, body,
			htmlFormat, null, null, null);
	}

	public static void send(
			InternetAddress from, InternetAddress[] to, InternetAddress[] cc,
			InternetAddress[] bcc, InternetAddress[] bulkAddresses,
			String subject, String body, boolean htmlFormat,
			InternetAddress[] replyTo, String messageId, String inReplyTo)
		throws MailEngineException {

		send(
			from, to, cc, bcc, bulkAddresses, subject, body, htmlFormat,
			replyTo, messageId, inReplyTo, null);
	}

	public static void send(
			InternetAddress from, InternetAddress[] to, InternetAddress[] cc,
			InternetAddress[] bcc, InternetAddress[] bulkAddresses,
			String subject, String body, boolean htmlFormat,
			InternetAddress[] replyTo, String messageId, String inReplyTo,
			List<FileAttachment> fileAttachments)
		throws MailEngineException {

		send(
			from, to, cc, bcc, bulkAddresses, subject, body, htmlFormat,
			replyTo, messageId, inReplyTo, fileAttachments, null);
	}

	public static void send(
			InternetAddress from, InternetAddress[] to, InternetAddress[] cc,
			InternetAddress[] bcc, InternetAddress[] bulkAddresses,
			String subject, String body, boolean htmlFormat,
			InternetAddress[] replyTo, String messageId, String inReplyTo,
			List<FileAttachment> fileAttachments, SMTPAccount smtpAccount)
		throws MailEngineException {

		StopWatch stopWatch = null;

		if (_log.isDebugEnabled()) {
			stopWatch = new StopWatch();

			stopWatch.start();

			_log.debug("From: " + from);
			_log.debug("To: " + Arrays.toString(to));
			_log.debug("CC: " + Arrays.toString(cc));
			_log.debug("BCC: " + Arrays.toString(bcc));
			_log.debug("List Addresses: " + Arrays.toString(bulkAddresses));
			_log.debug("Subject: " + subject);
			_log.debug("Body: " + body);
			_log.debug("HTML Format: " + htmlFormat);
			_log.debug("Reply to: " + Arrays.toString(replyTo));
			_log.debug("Message ID: " + messageId);
			_log.debug("In Reply To: " + inReplyTo);

			if ((fileAttachments != null) && _log.isDebugEnabled()) {
				for (int i = 0; i < fileAttachments.size(); i++) {
					FileAttachment fileAttachment = fileAttachments.get(i);

					File file = fileAttachment.getFile();

					if (file == null) {
						continue;
					}

					_log.debug(
						"Attachment " + i + " file " + file.getAbsolutePath() +
							" and file name " + fileAttachment.getFileName());
				}
			}
		}

		try {
			Session session = null;

			if (smtpAccount == null) {
				session = getSession();
			}
			else {
				session = getSession(smtpAccount);
			}

			Message message = new LiferayMimeMessage(session);

			message.setFrom(from);
			message.setRecipients(Message.RecipientType.TO, to);

			if (cc != null) {
				message.setRecipients(Message.RecipientType.CC, cc);
			}

			if (bcc != null) {
				message.setRecipients(Message.RecipientType.BCC, bcc);
			}

			subject = GetterUtil.getString(subject);

			message.setSubject(subject);

			if ((fileAttachments != null) && (fileAttachments.size() > 0)) {
				MimeMultipart rootMultipart = new MimeMultipart(
					_MULTIPART_TYPE_MIXED);

				MimeMultipart messageMultipart = new MimeMultipart(
					_MULTIPART_TYPE_ALTERNATIVE);

				MimeBodyPart messageBodyPart = new MimeBodyPart();

				messageBodyPart.setContent(messageMultipart);

				rootMultipart.addBodyPart(messageBodyPart);

				if (htmlFormat) {
					MimeBodyPart bodyPart = new MimeBodyPart();

					bodyPart.setContent(body, _TEXT_HTML);

					messageMultipart.addBodyPart(bodyPart);
				}
				else {
					MimeBodyPart bodyPart = new MimeBodyPart();

					bodyPart.setText(body);

					messageMultipart.addBodyPart(bodyPart);
				}

				for (int i = 0; i < fileAttachments.size(); i++) {
					FileAttachment fileAttachment = fileAttachments.get(i);

					File file = fileAttachment.getFile();

					if (file == null) {
						continue;
					}

					MimeBodyPart mimeBodyPart = new MimeBodyPart();

					DataSource dataSource = new FileDataSource(file);

					mimeBodyPart.setDataHandler(new DataHandler(dataSource));
					mimeBodyPart.setDisposition(Part.ATTACHMENT);

					if (fileAttachment.getFileName() != null) {
						mimeBodyPart.setFileName(fileAttachment.getFileName());
					}
					else {
						mimeBodyPart.setFileName(file.getName());
					}

					rootMultipart.addBodyPart(mimeBodyPart);
				}

				message.setContent(rootMultipart);

				message.saveChanges();
			}
			else {
				if (htmlFormat) {
					message.setContent(body, _TEXT_HTML);
				}
				else {
					message.setContent(body, _TEXT_PLAIN);
				}
			}

			message.setSentDate(new Date());

			if (replyTo != null) {
				message.setReplyTo(replyTo);
			}

			if (messageId != null) {
				message.setHeader("Message-ID", messageId);
			}

			if (inReplyTo != null) {
				message.setHeader("In-Reply-To", inReplyTo);
				message.setHeader("References", inReplyTo);
			}

			_send(session, message, bulkAddresses);
		}
		catch (SendFailedException sfe) {
			_log.error(sfe);
		}
		catch (Exception e) {
			throw new MailEngineException(e);
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Sending mail takes " + stopWatch.getTime() + " ms");
		}
	}

	public static void send(
			InternetAddress from, InternetAddress[] to, InternetAddress[] cc,
			InternetAddress[] bcc, String subject, String body)
		throws MailEngineException {

		send(from, to, cc, bcc, subject, body, false, null, null, null);
	}

	public static void send(
			InternetAddress from, InternetAddress[] to, InternetAddress[] cc,
			InternetAddress[] bcc, String subject, String body,
			boolean htmlFormat, InternetAddress[] replyTo, String messageId,
			String inReplyTo)
		throws MailEngineException {

		send(
			from, to, cc, bcc, null, subject, body, htmlFormat, replyTo,
			messageId, inReplyTo, null);
	}

	public static void send(
			InternetAddress from, InternetAddress[] to, InternetAddress[] cc,
			String subject, String body)
		throws MailEngineException {

		send(from, to, cc, null, subject, body, false, null, null, null);
	}

	public static void send(
			InternetAddress from, InternetAddress[] to, InternetAddress[] cc,
			String subject, String body, boolean htmlFormat)
		throws MailEngineException {

		send(from, to, cc, null, subject, body, htmlFormat, null, null, null);
	}

	public static void send(
			InternetAddress from, InternetAddress[] to, String subject,
			String body)
		throws MailEngineException {

		send(from, to, null, null, subject, body, false, null, null, null);
	}

	public static void send(
			InternetAddress from, InternetAddress[] to, String subject,
			String body, boolean htmlFormat)
		throws MailEngineException {

		send(from, to, null, null, subject, body, htmlFormat, null, null, null);
	}

	public static void send(MailMessage mailMessage)
		throws MailEngineException {

		send(
			mailMessage.getFrom(), mailMessage.getTo(), mailMessage.getCC(),
			mailMessage.getBCC(), mailMessage.getBulkAddresses(),
			mailMessage.getSubject(), mailMessage.getBody(),
			mailMessage.isHTMLFormat(), mailMessage.getReplyTo(),
			mailMessage.getMessageId(), mailMessage.getInReplyTo(),
			mailMessage.getFileAttachments(), mailMessage.getSMTPAccount());
	}

	public static void send(String from, String to, String subject, String body)
		throws MailEngineException {

		try {
			send(
				new InternetAddress(from), new InternetAddress(to), subject,
				body);
		}
		catch (AddressException ae) {
			throw new MailEngineException(ae);
		}
	}

	private static Properties _getProperties(Account account) {
		Properties properties = new Properties();

		String protocol = account.getProtocol();

		properties.setProperty("mail.transport.protocol", protocol);
		properties.setProperty("mail." + protocol + ".host", account.getHost());
		properties.setProperty(
			"mail." + protocol + ".port", String.valueOf(account.getPort()));

		if (account.isRequiresAuthentication()) {
			properties.setProperty("mail." + protocol + ".auth", "true");
			properties.setProperty(
				"mail." + protocol + ".user", account.getUser());
			properties.setProperty(
				"mail." + protocol + ".password", account.getPassword());
		}

		if (account.isSecure()) {
			properties.setProperty(
				"mail." + protocol + ".socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
			properties.setProperty(
				"mail." + protocol + ".socketFactory.fallback", "false");
			properties.setProperty(
				"mail." + protocol + ".socketFactory.port",
				String.valueOf(account.getPort()));
		}

		return properties;
	}

	private static String _getSMTPProperty(Session session, String suffix) {
		String protocol = GetterUtil.getString(
			session.getProperty("mail.transport.protocol"));

		if (protocol.equals(Account.PROTOCOL_SMTPS)) {
			return session.getProperty("mail.smtps." + suffix);
		}
		else {
			return session.getProperty("mail.smtp." + suffix);
		}
	}

	private static void _send(
		Session session, Message message, InternetAddress[] bulkAddresses) {

		try {
			boolean smtpAuth = GetterUtil.getBoolean(
				_getSMTPProperty(session, "auth"), false);
			String smtpHost = _getSMTPProperty(session, "host");
			int smtpPort = GetterUtil.getInteger(
				_getSMTPProperty(session, "port"), Account.PORT_SMTP);
			String user = _getSMTPProperty(session, "user");
			String password = _getSMTPProperty(session, "password");

			if (smtpAuth && Validator.isNotNull(user) &&
				Validator.isNotNull(password)) {

				String protocol = GetterUtil.getString(
					session.getProperty("mail.transport.protocol"),
					Account.PROTOCOL_SMTP);

				Transport transport = session.getTransport(protocol);

				transport.connect(smtpHost, smtpPort, user, password);

				if ((bulkAddresses != null) && (bulkAddresses.length > 0)) {
					transport.sendMessage(message, bulkAddresses);
				}
				else {
					transport.sendMessage(message, message.getAllRecipients());
				}

				transport.close();
			}
			else {
				if ((bulkAddresses != null) && (bulkAddresses.length > 0)) {
					Transport.send(message, bulkAddresses);
				}
				else {
					Transport.send(message);
				}
			}
		}
		catch (MessagingException me) {
			if (me.getNextException() instanceof SocketException) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Failed to connect to a valid mail server. Please " +
							"make sure one is properly configured. " +
								me.getMessage());
				}
			}
			else {
				_log.error(me.getMessage());

				LogUtil.log(_log, me);
			}
		}
	}

	private static final String _MULTIPART_TYPE_ALTERNATIVE = "alternative";

	private static final String _MULTIPART_TYPE_MIXED = "mixed";

	private static final String _TEXT_HTML = "text/html;charset=\"UTF-8\"";

	private static final String _TEXT_PLAIN = "text/plain;charset=\"UTF-8\"";

	private static Log _log = LogFactoryUtil.getLog(MailEngine.class);

}