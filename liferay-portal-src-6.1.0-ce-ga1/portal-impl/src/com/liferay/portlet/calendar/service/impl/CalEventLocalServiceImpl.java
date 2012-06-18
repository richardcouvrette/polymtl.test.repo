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

package com.liferay.portlet.calendar.service.impl;

import com.liferay.portal.im.AIMConnector;
import com.liferay.portal.im.ICQConnector;
import com.liferay.portal.im.MSNConnector;
import com.liferay.portal.im.YMConnector;
import com.liferay.portal.kernel.cal.DayAndPosition;
import com.liferay.portal.kernel.cal.Recurrence;
import com.liferay.portal.kernel.cal.TZSRecurrence;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.io.unsync.UnsyncBufferedOutputStream;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.mail.MailMessage;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.CalendarUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Contact;
import com.liferay.portal.model.ModelHintsUtil;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetLinkConstants;
import com.liferay.portlet.calendar.EventDurationException;
import com.liferay.portlet.calendar.EventEndDateException;
import com.liferay.portlet.calendar.EventStartDateException;
import com.liferay.portlet.calendar.EventTitleException;
import com.liferay.portlet.calendar.model.CalEvent;
import com.liferay.portlet.calendar.model.CalEventConstants;
import com.liferay.portlet.calendar.service.base.CalEventLocalServiceBaseImpl;
import com.liferay.portlet.calendar.social.CalendarActivityKeys;
import com.liferay.portlet.calendar.util.CalUtil;
import com.liferay.util.TimeZoneSensitive;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.text.Format;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.mail.internet.InternetAddress;

import javax.portlet.PortletPreferences;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Dur;
import net.fortuna.ical4j.model.Parameter;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyList;
import net.fortuna.ical4j.model.Recur;
import net.fortuna.ical4j.model.WeekDay;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.Comment;
import net.fortuna.ical4j.model.property.DateProperty;
import net.fortuna.ical4j.model.property.Description;
import net.fortuna.ical4j.model.property.DtEnd;
import net.fortuna.ical4j.model.property.DtStart;
import net.fortuna.ical4j.model.property.Location;
import net.fortuna.ical4j.model.property.Method;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.RRule;
import net.fortuna.ical4j.model.property.Summary;
import net.fortuna.ical4j.model.property.Uid;
import net.fortuna.ical4j.model.property.Version;

/**
 * @author Brian Wing Shun Chan
 * @author Bruno Farache
 * @author Samuel Kong
 * @author Ganesh Ram
 * @author Brett Swaim
 */
public class CalEventLocalServiceImpl extends CalEventLocalServiceBaseImpl {

	public CalEvent addEvent(
			long userId, String title, String description, String location,
			int startDateMonth, int startDateDay, int startDateYear,
			int startDateHour, int startDateMinute, int endDateMonth,
			int endDateDay, int endDateYear, int durationHour,
			int durationMinute, boolean allDay, boolean timeZoneSensitive,
			String type, boolean repeating, TZSRecurrence recurrence,
			int remindBy, int firstReminder, int secondReminder,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Event

		User user = userPersistence.findByPrimaryKey(userId);
		long groupId = serviceContext.getScopeGroupId();
		Date now = new Date();

		Locale locale = null;
		TimeZone timeZone = null;

		if (timeZoneSensitive) {
			locale = user.getLocale();
			timeZone = user.getTimeZone();
		}
		else {
			locale = LocaleUtil.getDefault();
			timeZone = TimeZoneUtil.getDefault();
		}

		Calendar startDate = CalendarFactoryUtil.getCalendar(timeZone, locale);

		startDate.set(Calendar.MONTH, startDateMonth);
		startDate.set(Calendar.DATE, startDateDay);
		startDate.set(Calendar.YEAR, startDateYear);
		startDate.set(Calendar.HOUR_OF_DAY, startDateHour);
		startDate.set(Calendar.MINUTE, startDateMinute);
		startDate.set(Calendar.SECOND, 0);
		startDate.set(Calendar.MILLISECOND, 0);

		Calendar endDate = CalendarFactoryUtil.getCalendar(timeZone, locale);

		endDate.set(Calendar.MONTH, endDateMonth);
		endDate.set(Calendar.DATE, endDateDay);
		endDate.set(Calendar.YEAR, endDateYear);
		endDate.set(Calendar.HOUR_OF_DAY, 23);
		endDate.set(Calendar.MINUTE, 59);
		endDate.set(Calendar.SECOND, 59);
		endDate.set(Calendar.MILLISECOND, 990);

		if (allDay) {
			startDate.set(Calendar.HOUR_OF_DAY, 0);
			startDate.set(Calendar.MINUTE, 0);

			durationHour = 24;
			durationMinute = 0;
		}

		validate(
			title, startDateMonth, startDateDay, startDateYear, endDateMonth,
			endDateDay, endDateYear, durationHour, durationMinute, allDay,
			repeating, recurrence);

		long eventId = counterLocalService.increment();

		CalEvent event = calEventPersistence.create(eventId);

		event.setUuid(serviceContext.getUuid());
		event.setGroupId(groupId);
		event.setCompanyId(user.getCompanyId());
		event.setUserId(user.getUserId());
		event.setUserName(user.getFullName());
		event.setCreateDate(serviceContext.getCreateDate(now));
		event.setModifiedDate(serviceContext.getModifiedDate(now));
		event.setTitle(title);
		event.setDescription(description);
		event.setLocation(location);
		event.setStartDate(startDate.getTime());
		event.setEndDate(endDate.getTime());
		event.setDurationHour(durationHour);
		event.setDurationMinute(durationMinute);
		event.setAllDay(allDay);
		event.setTimeZoneSensitive(timeZoneSensitive);
		event.setType(type);
		event.setRepeating(repeating);
		event.setRecurrenceObj(recurrence);
		event.setRemindBy(remindBy);
		event.setFirstReminder(firstReminder);
		event.setSecondReminder(secondReminder);
		event.setExpandoBridgeAttributes(serviceContext);

		calEventPersistence.update(event, false);

		// Resources

		if (serviceContext.isAddGroupPermissions() ||
			serviceContext.isAddGuestPermissions()) {

			addEventResources(
				event, serviceContext.isAddGroupPermissions(),
				serviceContext.isAddGuestPermissions());
		}
		else {
			addEventResources(
				event, serviceContext.getGroupPermissions(),
				serviceContext.getGuestPermissions());
		}

		// Asset

		updateAsset(
			userId, event, serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames(),
			serviceContext.getAssetLinkEntryIds());

		// Social

		socialActivityLocalService.addActivity(
			userId, groupId, CalEvent.class.getName(), eventId,
			CalendarActivityKeys.ADD_EVENT, StringPool.BLANK, 0);

		// Indexer

		Indexer indexer = IndexerRegistryUtil.getIndexer(CalEvent.class);

		indexer.reindex(event);

		// Pool

		CalEventLocalUtil.clearEventsPool(event.getGroupId());

		return event;
	}

	public void addEventResources(
			CalEvent event, boolean addGroupPermissions,
			boolean addGuestPermissions)
		throws PortalException, SystemException {

		resourceLocalService.addResources(
			event.getCompanyId(), event.getGroupId(), event.getUserId(),
			CalEvent.class.getName(), event.getEventId(), false,
			addGroupPermissions, addGuestPermissions);
	}

	public void addEventResources(
			CalEvent event, String[] groupPermissions,
			String[] guestPermissions)
		throws PortalException, SystemException {

		resourceLocalService.addModelResources(
			event.getCompanyId(), event.getGroupId(), event.getUserId(),
			CalEvent.class.getName(), event.getEventId(), groupPermissions,
			guestPermissions);
	}

	public void addEventResources(
			long eventId, boolean addGroupPermissions,
			boolean addGuestPermissions)
		throws PortalException, SystemException {

		CalEvent event = calEventPersistence.findByPrimaryKey(eventId);

		addEventResources(event, addGroupPermissions, addGuestPermissions);
	}

	public void addEventResources(
			long eventId, String[] groupPermissions, String[] guestPermissions)
		throws PortalException, SystemException {

		CalEvent event = calEventPersistence.findByPrimaryKey(eventId);

		addEventResources(event, groupPermissions, guestPermissions);
	}

	public void checkEvents() throws PortalException, SystemException {
		List<CalEvent> events = calEventFinder.findByFutureReminders();

		for (CalEvent event : events) {
			User user = userPersistence.fetchByPrimaryKey(event.getUserId());

			if (user == null) {
				deleteEvent(event);

				continue;
			}

			Calendar now = CalendarFactoryUtil.getCalendar(
				user.getTimeZone(), user.getLocale());

			if (!event.isTimeZoneSensitive()) {
				Calendar temp = CalendarFactoryUtil.getCalendar();

				temp.setTime(Time.getDate(now));

				now = temp;
			}

			Calendar startDate = null;

			if (event.isTimeZoneSensitive()) {
				startDate = CalendarFactoryUtil.getCalendar(
					user.getTimeZone(), user.getLocale());
			}
			else {
				startDate = CalendarFactoryUtil.getCalendar();
			}

			if (event.isRepeating()) {
				double daysToCheck = Math.ceil(
					CalEventConstants.REMINDERS[
						CalEventConstants.REMINDERS.length - 1] /
					Time.DAY);

				Calendar cal = (Calendar)now.clone();

				for (int i = 0; i <= daysToCheck; i++) {
					Recurrence recurrence = event.getRecurrenceObj();

					Calendar tzICal = CalendarFactoryUtil.getCalendar(
						TimeZoneUtil.getTimeZone(StringPool.UTC));

					tzICal.set(
						cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
						cal.get(Calendar.DATE));

					Calendar recurrenceCal = getRecurrenceCal(
						cal, tzICal, event);

					if (recurrence.isInRecurrence(recurrenceCal)) {
						remindUser(event, user, recurrenceCal, now);
					}

					cal.add(Calendar.DAY_OF_YEAR, 1);
				}
			}
			else {
				startDate.setTime(event.getStartDate());

				remindUser(event, user, startDate, now);
			}
		}
	}

	public void deleteEvent(CalEvent event)
		throws PortalException, SystemException {

		// Event

		calEventPersistence.remove(event);

		// Resources

		resourceLocalService.deleteResource(
			event.getCompanyId(), CalEvent.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL, event.getEventId());

		// Asset

		assetEntryLocalService.deleteEntry(
			CalEvent.class.getName(), event.getEventId());

		// Expando

		expandoValueLocalService.deleteValues(
			CalEvent.class.getName(), event.getEventId());

		// Indexer

		Indexer indexer = IndexerRegistryUtil.getIndexer(CalEvent.class);

		indexer.delete(event);

		// Pool

		CalEventLocalUtil.clearEventsPool(event.getGroupId());
	}

	public void deleteEvent(long eventId)
		throws PortalException, SystemException {

		CalEvent event = calEventPersistence.findByPrimaryKey(eventId);

		deleteEvent(event);
	}

	public void deleteEvents(long groupId)
		throws PortalException, SystemException {

		Iterator<CalEvent> itr = calEventPersistence.findByGroupId(
			groupId).iterator();

		while (itr.hasNext()) {
			CalEvent event = itr.next();

			deleteEvent(event);
		}
	}

	public File exportEvent(long userId, long eventId)
		throws PortalException, SystemException {

		List<CalEvent> events = new ArrayList<CalEvent>();

		CalEvent event = calEventPersistence.findByPrimaryKey(eventId);

		events.add(event);

		return exportICal4j(toICalCalendar(userId, events), null);
	}

	public File exportGroupEvents(long userId, long groupId, String fileName)
		throws PortalException, SystemException {

		List<CalEvent> events = calEventPersistence.findByGroupId(groupId);

		return exportICal4j(toICalCalendar(userId, events), fileName);
	}

	public List<CalEvent> getCompanyEvents(long companyId, int start, int end)
		throws SystemException {

		return calEventPersistence.findByCompanyId(companyId, start, end);
	}

	public int getCompanyEventsCount(long companyId) throws SystemException {
		return calEventPersistence.countByCompanyId(companyId);
	}

	public CalEvent getEvent(long eventId)
		throws PortalException, SystemException {

		return calEventPersistence.findByPrimaryKey(eventId);
	}

	public List<CalEvent> getEvents(long groupId, Calendar cal)
		throws SystemException {

		return getEvents(groupId, cal, new String[0]);
	}

	public List<CalEvent> getEvents(long groupId, Calendar cal, String type)
		throws SystemException {

		return getEvents(groupId, cal, new String[] {type});
	}

	public List<CalEvent> getEvents(long groupId, Calendar cal, String[] types)
		throws SystemException {

		if (types != null) {
			types = ArrayUtil.distinct(types);

			Arrays.sort(types);
		}

		Map<String, List<CalEvent>> eventsPool =
			CalEventLocalUtil.getEventsPool(groupId);

		String key = CalUtil.toString(cal, types);

		List<CalEvent> events = eventsPool.get(key);

		if (events == null) {

			// Time zone sensitive

			List<CalEvent> events1 = calEventFinder.findByG_SD_T(
				groupId, CalendarUtil.getGTDate(cal),
				CalendarUtil.getLTDate(cal), true, types);

			// Time zone insensitive

			Calendar tzICal = CalendarFactoryUtil.getCalendar(
				TimeZoneUtil.getTimeZone(StringPool.UTC));

			tzICal.set(
				cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
				cal.get(Calendar.DATE));

			List<CalEvent> events2 = calEventFinder.findByG_SD_T(
				groupId, CalendarUtil.getGTDate(tzICal),
				CalendarUtil.getLTDate(tzICal), false, types);

			// Create new list

			events = new ArrayList<CalEvent>();

			events.addAll(events1);
			events.addAll(events2);

			// Add repeating events

			events.addAll(getRepeatingEvents(groupId, cal, types));

			events = new UnmodifiableList<CalEvent>(events);

			eventsPool.put(key, events);
		}

		return events;
	}

	public List<CalEvent> getEvents(
			long groupId, String type, int start, int end)
		throws SystemException {

		return getEvents(groupId, new String[] {type}, start, end);
	}

	public List<CalEvent> getEvents(
			long groupId, String[] types, int start, int end)
		throws SystemException {

		if ((types != null) && (types.length > 0) &&
			((types.length > 1) || Validator.isNotNull(types[0]))) {

			return calEventPersistence.findByG_T(groupId, types, start, end);
		}
		else {
			return calEventPersistence.findByGroupId(groupId, start, end);
		}
	}

	public int getEventsCount(long groupId, String type)
		throws SystemException {

		return getEventsCount(groupId, new String[] {type});
	}

	public int getEventsCount(long groupId, String[] types)
		throws SystemException {

		if ((types != null) && (types.length > 0) &&
			((types.length > 1) || Validator.isNotNull(types[0]))) {

			return calEventPersistence.countByG_T(groupId, types);
		}
		else {
			return calEventPersistence.countByGroupId(groupId);
		}
	}

	public List<CalEvent> getNoAssetEvents() throws SystemException {
		return calEventFinder.findByNoAssets();
	}

	public List<CalEvent> getRepeatingEvents(long groupId)
		throws SystemException {

		return getRepeatingEvents(groupId, null, null);
	}

	public List<CalEvent> getRepeatingEvents(
			long groupId, Calendar cal, String[] types)
		throws SystemException {

		Map<String, List<CalEvent>> eventsPool =
			CalEventLocalUtil.getEventsPool(groupId);

		String key = "recurrence".concat(CalUtil.toString(null, types));

		List<CalEvent> events = eventsPool.get(key);

		if (events == null) {
			if ((types != null) && (types.length > 0) &&
				((types.length > 1) || Validator.isNotNull(types[0]))) {

				events = calEventPersistence.findByG_T_R(groupId, types, true);
			}
			else {
				events = calEventPersistence.findByG_R(groupId, true);
			}

			events = new UnmodifiableList<CalEvent>(events);

			eventsPool.put(key, events);
		}

		if (cal != null) {

			// Time zone insensitive

			Calendar tzICal = CalendarFactoryUtil.getCalendar(
				TimeZoneUtil.getTimeZone(StringPool.UTC));

			tzICal.set(
				cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
				cal.get(Calendar.DATE));

			Iterator<CalEvent> itr = events.iterator();

			List<CalEvent> repeatingEvents = new ArrayList<CalEvent>();

			while (itr.hasNext()) {
				CalEvent event = itr.next();

				TZSRecurrence recurrence = event.getRecurrenceObj();

				try {

					// LEP-3468

					if ((recurrence.getFrequency() !=
							Recurrence.NO_RECURRENCE) &&
						(recurrence.getInterval() <= 0)) {

						recurrence.setInterval(1);

						event.setRecurrenceObj(recurrence);

						event = calEventPersistence.update(event, false);

						recurrence = event.getRecurrenceObj();
					}

					if (recurrence.isInRecurrence(
							getRecurrenceCal(cal, tzICal, event))) {

						repeatingEvents.add(event);
					}
				}
				catch (Exception e) {
					_log.error(e, e);
				}
			}

			events = new UnmodifiableList<CalEvent>(repeatingEvents);
		}

		return events;
	}

	public boolean hasEvents(long groupId, Calendar cal)
		throws SystemException {

		return hasEvents(groupId, cal, new String[0]);
	}

	public boolean hasEvents(long groupId, Calendar cal, String type)
		throws SystemException {

		return hasEvents(groupId, cal, new String[] {type});
	}

	public boolean hasEvents(long groupId, Calendar cal, String[] types)
		throws SystemException {

		List<CalEvent> events = getEvents(groupId, cal, types);

		if (events.isEmpty()) {
			return false;
		}
		else {
			return true;
		}
	}

	public void importICal4j(long userId, long groupId, InputStream inputStream)
		throws PortalException, SystemException {

		try {

			CalendarBuilder builder = new CalendarBuilder();

			net.fortuna.ical4j.model.Calendar calendar = builder.build(
				inputStream);

			Iterator<VEvent> itr = calendar.getComponents(
				Component.VEVENT).iterator();

			while (itr.hasNext()) {
				VEvent vEvent = itr.next();

				importICal4j(userId, groupId, vEvent);
			}
		}
		catch (IOException ioe) {
			throw new SystemException(ioe.getMessage(), ioe);
		}
		catch (ParserException pe) {
			throw new SystemException(pe.getMessage(), pe);
		}
	}

	public void updateAsset(
			long userId, CalEvent event, long[] assetCategoryIds,
			String[] assetTagNames, long[] assetLinkEntryIds)
		throws PortalException, SystemException {

		AssetEntry assetEntry = assetEntryLocalService.updateEntry(
			userId, event.getGroupId(), CalEvent.class.getName(),
			event.getEventId(), event.getUuid(), 0, assetCategoryIds,
			assetTagNames, true, null, null, null, null, ContentTypes.TEXT_HTML,
			event.getTitle(), event.getDescription(), null, null, null, 0, 0,
			null, false);

		assetLinkLocalService.updateLinks(
			userId, assetEntry.getEntryId(), assetLinkEntryIds,
			AssetLinkConstants.TYPE_RELATED);
	}

	public CalEvent updateEvent(
			long userId, long eventId, String title, String description,
			String location, int startDateMonth, int startDateDay,
			int startDateYear, int startDateHour, int startDateMinute,
			int endDateMonth, int endDateDay, int endDateYear, int durationHour,
			int durationMinute, boolean allDay, boolean timeZoneSensitive,
			String type, boolean repeating, TZSRecurrence recurrence,
			int remindBy, int firstReminder, int secondReminder,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Event

		User user = userPersistence.findByPrimaryKey(userId);

		Locale locale = null;
		TimeZone timeZone = null;

		if (timeZoneSensitive) {
			locale = user.getLocale();
			timeZone = user.getTimeZone();
		}
		else {
			locale = LocaleUtil.getDefault();
			timeZone = TimeZoneUtil.getDefault();
		}

		Calendar startDate = CalendarFactoryUtil.getCalendar(timeZone, locale);

		startDate.set(Calendar.MONTH, startDateMonth);
		startDate.set(Calendar.DATE, startDateDay);
		startDate.set(Calendar.YEAR, startDateYear);
		startDate.set(Calendar.HOUR_OF_DAY, startDateHour);
		startDate.set(Calendar.MINUTE, startDateMinute);
		startDate.set(Calendar.SECOND, 0);
		startDate.set(Calendar.MILLISECOND, 0);

		Calendar endDate = CalendarFactoryUtil.getCalendar(timeZone, locale);

		endDate.set(Calendar.MONTH, endDateMonth);
		endDate.set(Calendar.DATE, endDateDay);
		endDate.set(Calendar.YEAR, endDateYear);
		endDate.set(Calendar.HOUR_OF_DAY, 23);
		endDate.set(Calendar.MINUTE, 59);
		endDate.set(Calendar.SECOND, 59);
		endDate.set(Calendar.MILLISECOND, 990);

		if (allDay) {
			startDate.set(Calendar.HOUR_OF_DAY, 0);
			startDate.set(Calendar.MINUTE, 0);

			durationHour = 24;
			durationMinute = 0;
		}

		validate(
			title, startDateMonth, startDateDay, startDateYear, endDateMonth,
			endDateDay, endDateYear, durationHour, durationMinute, allDay,
			repeating, recurrence);

		CalEvent event = calEventPersistence.findByPrimaryKey(eventId);

		event.setModifiedDate(serviceContext.getModifiedDate(null));
		event.setTitle(title);
		event.setDescription(description);
		event.setLocation(location);
		event.setStartDate(startDate.getTime());
		event.setEndDate(endDate.getTime());
		event.setDurationHour(durationHour);
		event.setDurationMinute(durationMinute);
		event.setAllDay(allDay);
		event.setTimeZoneSensitive(timeZoneSensitive);
		event.setType(type);
		event.setRepeating(repeating);
		event.setRecurrenceObj(recurrence);
		event.setRemindBy(remindBy);
		event.setFirstReminder(firstReminder);
		event.setSecondReminder(secondReminder);
		event.setExpandoBridgeAttributes(serviceContext);

		calEventPersistence.update(event, false);

		// Asset

		updateAsset(
			userId, event, serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames(),
			serviceContext.getAssetLinkEntryIds());

		// Social

		socialActivityLocalService.addActivity(
			userId, event.getGroupId(), CalEvent.class.getName(), eventId,
			CalendarActivityKeys.UPDATE_EVENT, StringPool.BLANK, 0);

		// Indexer

		Indexer indexer = IndexerRegistryUtil.getIndexer(CalEvent.class);

		indexer.reindex(event);

		// Pool

		CalEventLocalUtil.clearEventsPool(event.getGroupId());

		return event;
	}

	protected File exportICal4j(
			net.fortuna.ical4j.model.Calendar cal, String fileName)
		throws SystemException {

		OutputStream os = null;

		try {
			String extension = ".ics";

			if (Validator.isNull(fileName)) {
				fileName = "liferay.";
			}
			else {
				int pos = fileName.lastIndexOf(CharPool.PERIOD);

				if (pos != -1) {
					extension = fileName.substring(pos);
					fileName = fileName.substring(0, pos);
				}
			}

			fileName = FileUtil.getShortFileName(fileName);

			File file = File.createTempFile(fileName, extension);

			os = new UnsyncBufferedOutputStream(
				new FileOutputStream(file.getPath()));

			CalendarOutputter calOutput = new CalendarOutputter();

			if (cal.getComponents().isEmpty()) {
				calOutput.setValidating(false);
			}

			calOutput.output(cal, os);

			return file;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new SystemException(e);
		}
		finally {
			StreamUtil.cleanUp(os);
		}
	}

	protected Calendar getRecurrenceCal(
		Calendar cal, Calendar tzICal, CalEvent event) {

		Calendar eventCal = CalendarFactoryUtil.getCalendar(
			TimeZoneUtil.getTimeZone(StringPool.UTC));

		eventCal.setTime(event.getStartDate());

		Calendar recurrenceCal = (Calendar)tzICal.clone();
		recurrenceCal.set(
			Calendar.HOUR_OF_DAY, eventCal.get(Calendar.HOUR_OF_DAY));
		recurrenceCal.set(Calendar.MINUTE, eventCal.get(Calendar.MINUTE));
		recurrenceCal.set(Calendar.SECOND, 0);
		recurrenceCal.set(Calendar.MILLISECOND, 0);

		if (event.isTimeZoneSensitive()) {
			int gmtDate = eventCal.get(Calendar.DATE);
			long gmtMills = eventCal.getTimeInMillis();

			eventCal.setTimeZone(cal.getTimeZone());

			int tziDate = eventCal.get(Calendar.DATE);
			long tziMills = Time.getDate(eventCal).getTime();

			if (gmtDate != tziDate) {
				int diffDate = 0;

				if (gmtMills > tziMills) {
					diffDate = (int)Math.ceil(
						(double)(gmtMills - tziMills) / Time.DAY);
				}
				else {
					diffDate = (int)Math.floor(
						(double)(gmtMills - tziMills) / Time.DAY);
				}

				recurrenceCal.add(Calendar.DATE, diffDate);
			}
		}

		return recurrenceCal;
	}

	protected void importICal4j(long userId, long groupId, VEvent event)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);

		TimeZone timeZone = user.getTimeZone();

		// X iCal property

		Property timeZoneXProperty = event.getProperty(
			TimeZoneSensitive.PROPERTY_NAME);

		boolean timeZoneXPropertyValue = true;

		if ((timeZoneXProperty != null) &&
			timeZoneXProperty.getValue().equals("FALSE")) {

			timeZoneXPropertyValue = false;
		}

		// Title

		String title = StringPool.BLANK;

		if (event.getSummary() != null) {
			title = ModelHintsUtil.trimString(
				CalEvent.class.getName(), "title",
				event.getSummary().getValue());
		}

		// Description

		String description = StringPool.BLANK;

		if (event.getDescription() != null) {
			description = event.getDescription().getValue();
		}

		// Location

		String location = StringPool.BLANK;

		if (event.getLocation() != null) {
			location = event.getLocation().getValue();
		}

		// Start date

		DtStart dtStart = event.getStartDate();

		Calendar startDate = toCalendar(
			dtStart, timeZone, timeZoneXPropertyValue);

		startDate.setTime(dtStart.getDate());

		// End date

		Calendar endDate = null;

		DtEnd dtEnd = event.getEndDate(true);

		RRule rrule = (RRule)event.getProperty(Property.RRULE);

		if (dtEnd != null) {
			endDate = toCalendar(dtEnd, timeZone, timeZoneXPropertyValue);

			endDate.setTime(dtEnd.getDate());
		}
		else {
			endDate = (Calendar)startDate.clone();
			endDate.add(Calendar.DATE, 1);
		}

		// Duration

		long diffMillis = 0;
		long durationHours = 24;
		long durationMins = 0;
		boolean multiDayEvent = false;

		if (dtEnd != null) {
			diffMillis =
				dtEnd.getDate().getTime() - startDate.getTimeInMillis();
			durationHours = diffMillis / Time.HOUR;
			durationMins = (diffMillis / Time.MINUTE) - (durationHours * 60);

			if ((durationHours > 24) ||
				((durationHours == 24) && (durationMins > 0))) {

				durationHours = 24;
				durationMins = 0;
				multiDayEvent = true;
			}
		}

		// All day

		boolean allDay = false;

		if (isICal4jDateOnly(event.getStartDate()) || multiDayEvent) {
			allDay = true;
		}

		// Time zone sensitive

		boolean timeZoneSensitive = true;

		if (allDay || !timeZoneXPropertyValue) {
			timeZoneSensitive = false;
		}

		// Type

		String type = StringPool.BLANK;

		Property comment = event.getProperty(Property.COMMENT);

		if ((comment != null) &&
			ArrayUtil.contains(CalEventConstants.TYPES, comment.getValue())) {

			type = comment.getValue();
		}

		// Recurrence

		boolean repeating = false;
		TZSRecurrence recurrence = null;

		if (multiDayEvent) {
			repeating = true;

			Calendar recStartCal = CalendarFactoryUtil.getCalendar(
				TimeZoneUtil.getTimeZone(StringPool.UTC));

			recStartCal.setTime(startDate.getTime());

			com.liferay.portal.kernel.cal.Duration duration =
				new com.liferay.portal.kernel.cal.Duration(1, 0, 0, 0);

			recurrence = new TZSRecurrence(
				recStartCal, duration, Recurrence.DAILY);

			Calendar until = CalendarFactoryUtil.getCalendar(
				TimeZoneUtil.getTimeZone(StringPool.UTC));

			until.setTimeInMillis(until.getTimeInMillis() + diffMillis);

			recurrence.setUntil(until);

			endDate.setTime(recurrence.getUntil().getTime());
		}
		else if (rrule != null) {
			repeating = true;
			recurrence = toRecurrence(rrule, startDate);

			if (recurrence.getUntil() != null) {
				endDate.setTime(recurrence.getUntil().getTime());
			}
		}

		// Reminder

		int remindBy = CalEventConstants.REMIND_BY_NONE;
		int firstReminder = 300000;
		int secondReminder = 300000;

		// Permissions

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);
		serviceContext.setScopeGroupId(groupId);

		// Merge event

		String uuid = null;

		CalEvent existingEvent = null;

		if (event.getUid() != null) {
			Uid uid = event.getUid();

			if (existingEvent == null) {

				// VEvent exported by Liferay portal

				uuid = uid.getValue();

				existingEvent = calEventPersistence.fetchByUUID_G(
					uuid, groupId);
			}

			if (existingEvent == null) {

				// VEvent exported by external application

				uuid = PortalUUIDUtil.generate(uid.getValue().getBytes());

				existingEvent = calEventPersistence.fetchByUUID_G(
					uuid, groupId);
			}
		}

		int startDateMonth = startDate.get(Calendar.MONTH);
		int startDateDay = startDate.get(Calendar.DAY_OF_MONTH);
		int startDateYear = startDate.get(Calendar.YEAR);
		int startDateHour = startDate.get(Calendar.HOUR_OF_DAY);
		int startDateMinute = startDate.get(Calendar.MINUTE);
		int endDateMonth = endDate.get(Calendar.MONTH);
		int endDateDay = endDate.get(Calendar.DAY_OF_MONTH);
		int endDateYear = endDate.get(Calendar.YEAR);
		int durationHour = (int)durationHours;
		int durationMinute = (int)durationMins;

		if (existingEvent == null) {
			serviceContext.setUuid(uuid);

			addEvent(
				userId, title, description, location, startDateMonth,
				startDateDay, startDateYear, startDateHour, startDateMinute,
				endDateMonth, endDateDay, endDateYear, durationHour,
				durationMinute, allDay, timeZoneSensitive, type, repeating,
				recurrence, remindBy, firstReminder, secondReminder,
				serviceContext);
		}
		else {
			updateEvent(
				userId, existingEvent.getEventId(), title, description,
				location, startDateMonth, startDateDay, startDateYear,
				startDateHour, startDateMinute, endDateMonth, endDateDay,
				endDateYear, durationHour, durationMinute, allDay,
				timeZoneSensitive, type, repeating, recurrence, remindBy,
				firstReminder, secondReminder, serviceContext);
		}
	}

	protected boolean isICal4jDateOnly(DateProperty dateProperty) {
		Parameter valueParameter = dateProperty.getParameter(Parameter.VALUE);

		if ((valueParameter != null) &&
			valueParameter.getValue().equals("DATE")) {

			return true;
		}

		return false;
	}

	protected void remindUser(CalEvent event, User user, Calendar startDate) {
		int remindBy = event.getRemindBy();

		if (remindBy == CalEventConstants.REMIND_BY_NONE) {
			return;
		}

		try {
			long ownerId = event.getGroupId();
			int ownerType = PortletKeys.PREFS_OWNER_TYPE_GROUP;
			long plid = PortletKeys.PREFS_PLID_SHARED;
			String portletId = PortletKeys.CALENDAR;

			PortletPreferences preferences =
				portletPreferencesLocalService.getPreferences(
					event.getCompanyId(), ownerId, ownerType, plid, portletId);

			Company company = companyPersistence.findByPrimaryKey(
				user.getCompanyId());

			Contact contact = user.getContact();

			String portletName = PortalUtil.getPortletTitle(
				PortletKeys.CALENDAR, user);

			String fromName = CalUtil.getEmailFromName(
				preferences, event.getCompanyId());
			String fromAddress = CalUtil.getEmailFromAddress(
				preferences, event.getCompanyId());

			String toName = user.getFullName();
			String toAddress = user.getEmailAddress();

			if (remindBy == CalEventConstants.REMIND_BY_SMS) {
				toAddress = contact.getSmsSn();
			}

			String subject = CalUtil.getEmailEventReminderSubject(preferences);
			String body = CalUtil.getEmailEventReminderBody(preferences);

			Format dateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(
				user.getLocale(), user.getTimeZone());

			subject = StringUtil.replace(
				subject,
				new String[] {
					"[$EVENT_LOCATION$]",
					"[$EVENT_START_DATE$]",
					"[$EVENT_TITLE$]",
					"[$FROM_ADDRESS$]",
					"[$FROM_NAME$]",
					"[$PORTAL_URL$]",
					"[$PORTLET_NAME$]",
					"[$TO_ADDRESS$]",
					"[$TO_NAME$]"
				},
				new String[] {
					event.getLocation(),
					dateFormatDateTime.format(startDate.getTime()),
					event.getTitle(),
					fromAddress,
					fromName,
					company.getPortalURL(event.getGroupId()),
					portletName,
					HtmlUtil.escape(toAddress),
					HtmlUtil.escape(toName),
				});

			body = StringUtil.replace(
				body,
				new String[] {
					"[$EVENT_LOCATION$]",
					"[$EVENT_START_DATE$]",
					"[$EVENT_TITLE$]",
					"[$FROM_ADDRESS$]",
					"[$FROM_NAME$]",
					"[$PORTAL_URL$]",
					"[$PORTLET_NAME$]",
					"[$TO_ADDRESS$]",
					"[$TO_NAME$]"
				},
				new String[] {
					event.getLocation(),
					dateFormatDateTime.format(startDate.getTime()),
					event.getTitle(),
					fromAddress,
					fromName,
					company.getPortalURL(event.getGroupId()),
					portletName,
					HtmlUtil.escape(toAddress),
					HtmlUtil.escape(toName),
				});

			if ((remindBy == CalEventConstants.REMIND_BY_EMAIL) ||
				(remindBy == CalEventConstants.REMIND_BY_SMS)) {

				InternetAddress from = new InternetAddress(
					fromAddress, fromName);

				InternetAddress to = new InternetAddress(toAddress, toName);

				MailMessage message = new MailMessage(
					from, to, subject, body, true);

				mailService.sendEmail(message);
			}
			else if ((remindBy == CalEventConstants.REMIND_BY_AIM) &&
					 (Validator.isNotNull(contact.getAimSn()))) {

				AIMConnector.send(contact.getAimSn(), body);
			}
			else if ((remindBy == CalEventConstants.REMIND_BY_ICQ) &&
					 (Validator.isNotNull(contact.getIcqSn()))) {

				ICQConnector.send(contact.getIcqSn(), body);
			}
			else if ((remindBy == CalEventConstants.REMIND_BY_MSN) &&
					 (Validator.isNotNull(contact.getMsnSn()))) {

				MSNConnector.send(contact.getMsnSn(), body);
			}
			else if ((remindBy == CalEventConstants.REMIND_BY_YM) &&
					 (Validator.isNotNull(contact.getYmSn()))) {

				YMConnector.send(contact.getYmSn(), body);
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	protected void remindUser(
		CalEvent event, User user, Calendar startDate, Calendar now) {

		long diff =
			(startDate.getTime().getTime() - now.getTime().getTime()) /
				_CALENDAR_EVENT_CHECK_INTERVAL;

		if ((diff ==
				(event.getFirstReminder() / _CALENDAR_EVENT_CHECK_INTERVAL)) ||
			(diff ==
				(event.getSecondReminder() / _CALENDAR_EVENT_CHECK_INTERVAL))) {

			remindUser(event, user, startDate);
		}
	}

	protected Calendar toCalendar(
		DateProperty date, TimeZone timeZone, boolean timeZoneSensitive) {

		Calendar cal = null;

		if (isICal4jDateOnly(date)) {
			cal = Calendar.getInstance();
		}
		else if (!timeZoneSensitive) {
			cal = Calendar.getInstance(
				TimeZoneUtil.getTimeZone(StringPool.UTC));
		}
		else {
			cal = Calendar.getInstance(timeZone);
		}

		return cal;
	}

	protected int toCalendarWeekDay(WeekDay weekDay) {
		int dayOfWeeek = 0;

		if (weekDay.getDay().equals(WeekDay.SU.getDay())) {
			dayOfWeeek = Calendar.SUNDAY;
		}
		else if (weekDay.getDay().equals(WeekDay.MO.getDay())) {
			dayOfWeeek = Calendar.MONDAY;
		}
		else if (weekDay.getDay().equals(WeekDay.TU.getDay())) {
			dayOfWeeek = Calendar.TUESDAY;
		}
		else if (weekDay.getDay().equals(WeekDay.WE.getDay())) {
			dayOfWeeek = Calendar.WEDNESDAY;
		}
		else if (weekDay.getDay().equals(WeekDay.TH.getDay())) {
			dayOfWeeek = Calendar.THURSDAY;
		}
		else if (weekDay.getDay().equals(WeekDay.FR.getDay())) {
			dayOfWeeek = Calendar.FRIDAY;
		}
		else if (weekDay.getDay().equals(WeekDay.SA.getDay())) {
			dayOfWeeek = Calendar.SATURDAY;
		}

		return dayOfWeeek;
	}

	protected net.fortuna.ical4j.model.Calendar toICalCalendar(
		long userId, List<CalEvent> events)
		throws PortalException, SystemException {

		net.fortuna.ical4j.model.Calendar iCal =
			new net.fortuna.ical4j.model.Calendar();

		ProdId prodId = new ProdId(
			"-//Liferay Inc//Liferay Portal " + ReleaseInfo.getVersion() +
			"//EN");

		PropertyList propertiesList = iCal.getProperties();

		propertiesList.add(prodId);
		propertiesList.add(Version.VERSION_2_0);
		propertiesList.add(CalScale.GREGORIAN);

		// LPS-6058

		propertiesList.add(Method.PUBLISH);

		User user = userPersistence.findByPrimaryKey(userId);
		TimeZone timeZone = user.getTimeZone();

		List<VEvent> components = iCal.getComponents();

		Iterator<CalEvent> itr = events.iterator();

		while (itr.hasNext()) {
			CalEvent event = itr.next();

			components.add(toICalVEvent(event, timeZone));
		}

		return iCal;
	}

	protected Recur toICalRecurrence(TZSRecurrence recurrence) {
		Recur recur = null;

		int recurrenceType = recurrence.getFrequency();

		int interval = recurrence.getInterval();

		if (recurrenceType == Recurrence.DAILY) {
			recur = new Recur(Recur.DAILY, -1);

			if (interval >= 1) {
				recur.setInterval(interval);
			}

			DayAndPosition[] byDay = recurrence.getByDay();

			if (byDay != null) {
				for (int i = 0; i < byDay.length; i++) {
					WeekDay weekDay = toICalWeekDay(byDay[i].getDayOfWeek());

					recur.getDayList().add(weekDay);
				}
			}

		}
		else if (recurrenceType == Recurrence.WEEKLY) {
			recur = new Recur(Recur.WEEKLY, -1);

			recur.setInterval(interval);

			DayAndPosition[] byDay = recurrence.getByDay();

			if (byDay != null) {
				for (int i = 0; i < byDay.length; i++) {
					WeekDay weekDay = toICalWeekDay(byDay[i].getDayOfWeek());

					recur.getDayList().add(weekDay);
				}
			}
		}
		else if (recurrenceType == Recurrence.MONTHLY) {
			recur = new Recur(Recur.MONTHLY, -1);

			recur.setInterval(interval);

			int[] byMonthDay = recurrence.getByMonthDay();

			if (byMonthDay != null) {
				Integer monthDay = new Integer(byMonthDay[0]);

				recur.getMonthDayList().add(monthDay);
			}
			else if (recurrence.getByDay() != null) {
				DayAndPosition[] byDay = recurrence.getByDay();

				WeekDay weekDay = toICalWeekDay(byDay[0].getDayOfWeek());

				recur.getDayList().add(weekDay);

				Integer position = new Integer(byDay[0].getDayPosition());

				recur.getSetPosList().add(position);
			}
		}
		else if (recurrenceType == Recurrence.YEARLY) {
			recur = new Recur(Recur.YEARLY, -1);

			recur.setInterval(interval);
		}

		Calendar until = recurrence.getUntil();

		if (until != null) {
			DateTime dateTime = new DateTime(until.getTime());

			recur.setUntil(dateTime);
		}

		return recur;
	}

	protected VEvent toICalVEvent(CalEvent event, TimeZone timeZone) {
		VEvent vEvent = new VEvent();

		PropertyList eventProps = vEvent.getProperties();

		// UID

		Uid uid = new Uid(event.getUuid());

		eventProps.add(uid);

		if (event.isAllDay()) {

			// Start date

			DtStart dtStart = new DtStart(
				new net.fortuna.ical4j.model.Date(event.getStartDate()));

			eventProps.add(dtStart);
		}
		else {

			// Start date

			DtStart dtStart = new DtStart(new DateTime(event.getStartDate()));

			eventProps.add(dtStart);

			// Duration

			Dur dur = new Dur(
				0, event.getDurationHour(), event.getDurationMinute(), 0);

			DtEnd dtEnd = new DtEnd(new DateTime(dur.getTime(
				event.getStartDate())));

			eventProps.add(dtEnd);
		}

		// Summary

		Summary summary = new Summary(event.getTitle());

		eventProps.add(summary);

		// Description

		Description description = new Description(event.getDescription());

		eventProps.add(description);

		// Location

		Location location = new Location(event.getLocation());

		eventProps.add(location);

		// Comment

		Comment comment = new Comment(event.getType());

		eventProps.add(comment);

		// Recurrence rule

		if (event.isRepeating()) {
			Recur recur = toICalRecurrence(event.getRecurrenceObj());

			RRule rRule = new RRule(recur);

			eventProps.add(rRule);
		}

		// Time zone sensitive

		if (!event.getTimeZoneSensitive()) {
			eventProps.add(new TimeZoneSensitive("FALSE"));
		}

		return vEvent;
	}

	protected WeekDay toICalWeekDay(int dayOfWeek) {
		WeekDay weekDay = null;

		if (dayOfWeek == Calendar.SUNDAY) {
			weekDay = WeekDay.SU;
		}
		else if (dayOfWeek == Calendar.MONDAY) {
			weekDay = WeekDay.MO;
		}
		else if (dayOfWeek == Calendar.TUESDAY) {
			weekDay = WeekDay.TU;
		}
		else if (dayOfWeek == Calendar.WEDNESDAY) {
			weekDay = WeekDay.WE;
		}
		else if (dayOfWeek == Calendar.THURSDAY) {
			weekDay = WeekDay.TH;
		}
		else if (dayOfWeek == Calendar.FRIDAY) {
			weekDay = WeekDay.FR;
		}
		else if (dayOfWeek == Calendar.SATURDAY) {
			weekDay = WeekDay.SA;
		}

		return weekDay;
	}

	protected TZSRecurrence toRecurrence(RRule rRule, Calendar startDate) {
		Recur recur = rRule.getRecur();

		Calendar recStartCal = CalendarFactoryUtil.getCalendar(
			TimeZoneUtil.getTimeZone(StringPool.UTC));

		recStartCal.setTime(startDate.getTime());

		TZSRecurrence recurrence = new TZSRecurrence(
			recStartCal,
			new com.liferay.portal.kernel.cal.Duration(1, 0, 0, 0));

		recurrence.setWeekStart(Calendar.SUNDAY);

		if (recur.getInterval() > 1) {
			recurrence.setInterval(recur.getInterval());
		}

		Calendar until = Calendar.getInstance(
			TimeZoneUtil.getTimeZone(StringPool.UTC));

		String frequency = recur.getFrequency();

		if (recur.getUntil() != null) {
			until.setTime(recur.getUntil());

			recurrence.setUntil(until);
		}
		else if (rRule.getValue().indexOf("COUNT") >= 0) {
			until.setTimeInMillis(startDate.getTimeInMillis());

			int addField = 0;

			if (Recur.DAILY.equals(frequency)) {
				addField = Calendar.DAY_OF_YEAR;
			}
			else if (Recur.WEEKLY.equals(frequency)) {
				addField = Calendar.WEEK_OF_YEAR;
			}
			else if (Recur.MONTHLY.equals(frequency)) {
				addField = Calendar.MONTH;
			}
			else if (Recur.YEARLY.equals(frequency)) {
				addField = Calendar.YEAR;
			}

			int addAmount = recurrence.getInterval() * recur.getCount();

			until.add(addField, addAmount);
			until.add(Calendar.DAY_OF_YEAR, -1);

			recurrence.setUntil(until);
		}

		if (Recur.DAILY.equals(frequency)) {
			recurrence.setFrequency(Recurrence.DAILY);

			List<DayAndPosition> dayPosList = new ArrayList<DayAndPosition>();

			Iterator<WeekDay> itr = recur.getDayList().iterator();

			while (itr.hasNext()) {
				WeekDay weekDay = itr.next();

				dayPosList.add(
					new DayAndPosition(toCalendarWeekDay(weekDay), 0));
			}

			if (!dayPosList.isEmpty()) {
				recurrence.setByDay(
					dayPosList.toArray(new DayAndPosition[dayPosList.size()]));
			}
		}
		else if (Recur.WEEKLY.equals(frequency)) {
			recurrence.setFrequency(Recurrence.WEEKLY);

			List<DayAndPosition> dayPosList = new ArrayList<DayAndPosition>();

			Iterator<WeekDay> itr = recur.getDayList().iterator();

			while (itr.hasNext()) {
				WeekDay weekDay = itr.next();

				dayPosList.add(
					new DayAndPosition(toCalendarWeekDay(weekDay), 0));
			}

			if (!dayPosList.isEmpty()) {
				recurrence.setByDay(
					dayPosList.toArray(new DayAndPosition[dayPosList.size()]));
			}
		}
		else if (Recur.MONTHLY.equals(frequency)) {
			recurrence.setFrequency(Recurrence.MONTHLY);

			Iterator<Integer> monthDayListItr =
				recur.getMonthDayList().iterator();

			if (monthDayListItr.hasNext()) {
				Integer monthDay = monthDayListItr.next();

				recurrence.setByMonthDay(new int[] {monthDay.intValue()});
			}

			Iterator<WeekDay> dayListItr = recur.getDayList().iterator();

			if (dayListItr.hasNext()) {
				WeekDay weekDay = dayListItr.next();

				DayAndPosition[] dayPos = {
					new DayAndPosition(toCalendarWeekDay(weekDay),
					weekDay.getOffset())
				};

				recurrence.setByDay(dayPos);
			}
		}
		else if (Recur.YEARLY.equals(frequency)) {
			recurrence.setFrequency(Recurrence.YEARLY);
		}

		return recurrence;
	}

	protected void validate(
			String title, int startDateMonth, int startDateDay,
			int startDateYear, int endDateMonth, int endDateDay,
			int endDateYear, int durationHour, int durationMinute,
			boolean allDay, boolean repeating, TZSRecurrence recurrence)
		throws PortalException {

		if (Validator.isNull(title)) {
			throw new EventTitleException();
		}
		else if (!Validator.isDate(
				startDateMonth, startDateDay, startDateYear)) {

			throw new EventStartDateException();
		}
		else if (!Validator.isDate(endDateMonth, endDateDay, endDateYear)) {
			throw new EventEndDateException();
		}

		if (!allDay && durationHour <= 0 && durationMinute <= 0) {
			throw new EventDurationException();
		}

		Calendar startDate = CalendarFactoryUtil.getCalendar(
			startDateYear, startDateMonth, startDateDay);

		if (repeating) {
			Calendar until = recurrence.getUntil();

			if (startDate.after(until)) {
				throw new EventEndDateException();
			}
		}
	}

	private long _CALENDAR_EVENT_CHECK_INTERVAL =
		PropsValues.CALENDAR_EVENT_CHECK_INTERVAL * Time.MINUTE;

	private static Log _log = LogFactoryUtil.getLog(
		CalEventLocalServiceImpl.class);

}