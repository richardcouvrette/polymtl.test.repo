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

package com.liferay.portlet.calendar.lar;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.BasePortletDataHandler;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.lar.PortletDataHandlerBoolean;
import com.liferay.portal.kernel.lar.PortletDataHandlerControl;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.calendar.model.CalEvent;
import com.liferay.portlet.calendar.service.CalEventLocalServiceUtil;
import com.liferay.portlet.calendar.service.persistence.CalEventUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.portlet.PortletPreferences;

/**
 * @author Bruno Farache
 * @author Raymond Augé
 */
public class CalendarPortletDataHandlerImpl extends BasePortletDataHandler {

	@Override
	public PortletDataHandlerControl[] getExportControls() {
		return new PortletDataHandlerControl[] {
			_events, _categories, _comments, _ratings, _tags
		};
	}

	@Override
	public PortletDataHandlerControl[] getImportControls() {
		return new PortletDataHandlerControl[] {
			_events, _categories, _comments, _ratings, _tags
		};
	}

	@Override
	public boolean isAlwaysExportable() {
		return _ALWAYS_EXPORTABLE;
	}

	@Override
	public boolean isPublishToLiveByDefault() {
		return _PUBLISH_TO_LIVE_BY_DEFAULT;
	}

	@Override
	protected PortletPreferences doDeleteData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		if (!portletDataContext.addPrimaryKey(
				CalendarPortletDataHandlerImpl.class, "deleteData")) {

			CalEventLocalServiceUtil.deleteEvents(
				portletDataContext.getScopeGroupId());
		}

		return null;
	}

	@Override
	protected String doExportData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		portletDataContext.addPermissions(
			"com.liferay.portlet.calendar",
			portletDataContext.getScopeGroupId());

		Document document = SAXReaderUtil.createDocument();

		Element rootElement = document.addElement("calendar-data");

		rootElement.addAttribute(
			"group-id", String.valueOf(portletDataContext.getScopeGroupId()));

		List<CalEvent> events = CalEventUtil.findByGroupId(
			portletDataContext.getScopeGroupId());

		for (CalEvent event : events) {
			exportEvent(portletDataContext, rootElement, event);
		}

		return document.formattedString();
	}

	@Override
	protected PortletPreferences doImportData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences, String data)
		throws Exception {

		portletDataContext.importPermissions(
			"com.liferay.portlet.calendar",
			portletDataContext.getSourceGroupId(),
			portletDataContext.getScopeGroupId());

		Document document = SAXReaderUtil.read(data);

		Element rootElement = document.getRootElement();

		for (Element eventElement : rootElement.elements("event")) {
			String path = eventElement.attributeValue("path");

			if (!portletDataContext.isPathNotProcessed(path)) {
				continue;
			}

			CalEvent event = (CalEvent)portletDataContext.getZipEntryAsObject(
				path);

			importEvent(portletDataContext, eventElement, event);
		}

		return null;
	}

	protected void exportEvent(
			PortletDataContext portletDataContext, Element rootElement,
			CalEvent event)
		throws PortalException, SystemException {

		if (!portletDataContext.isWithinDateRange(event.getModifiedDate())) {
			return;
		}

		String path = getEventPath(portletDataContext, event);

		if (!portletDataContext.isPathNotProcessed(path)) {
			return;
		}

		Element eventElement = rootElement.addElement("event");

		portletDataContext.addClassedModel(
			eventElement, path, event, _NAMESPACE);
	}

	protected String getEventPath(
		PortletDataContext portletDataContext, CalEvent event) {

		StringBundler sb = new StringBundler(4);

		sb.append(portletDataContext.getPortletPath(PortletKeys.CALENDAR));
		sb.append("/events/");
		sb.append(event.getEventId());
		sb.append(".xml");

		return sb.toString();
	}

	protected void importEvent(
			PortletDataContext portletDataContext, Element eventElement,
			CalEvent event)
		throws Exception {

		long userId = portletDataContext.getUserId(event.getUserUuid());

		Date startDate = event.getStartDate();

		int startDateMonth = 0;
		int startDateDay = 0;
		int startDateYear = 0;
		int startDateHour = 0;
		int startDateMinute = 0;

		if (startDate != null) {
			Calendar startCal = CalendarFactoryUtil.getCalendar();

			startCal.setTime(startDate);

			startDateMonth = startCal.get(Calendar.MONTH);
			startDateDay = startCal.get(Calendar.DATE);
			startDateYear = startCal.get(Calendar.YEAR);
			startDateHour = startCal.get(Calendar.HOUR);
			startDateMinute = startCal.get(Calendar.MINUTE);

			if (startCal.get(Calendar.AM_PM) == Calendar.PM) {
				startDateHour += 12;
			}
		}

		Date endDate = event.getEndDate();

		int endDateMonth = 0;
		int endDateDay = 0;
		int endDateYear = 0;

		if (endDate != null) {
			Calendar endCal = CalendarFactoryUtil.getCalendar();

			endCal.setTime(endDate);

			endDateMonth = endCal.get(Calendar.MONTH);
			endDateDay = endCal.get(Calendar.DATE);
			endDateYear = endCal.get(Calendar.YEAR);
		}

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			eventElement, event, _NAMESPACE);

		CalEvent importedEvent = null;

		if (portletDataContext.isDataStrategyMirror()) {
			CalEvent existingEvent = CalEventUtil.fetchByUUID_G(
				event.getUuid(), portletDataContext.getScopeGroupId());

			if (existingEvent == null) {
				serviceContext.setUuid(event.getUuid());

				importedEvent = CalEventLocalServiceUtil.addEvent(
					userId, event.getTitle(), event.getDescription(),
					event.getLocation(), startDateMonth, startDateDay,
					startDateYear, startDateHour, startDateMinute, endDateMonth,
					endDateDay, endDateYear, event.getDurationHour(),
					event.getDurationMinute(), event.getAllDay(),
					event.getTimeZoneSensitive(), event.getType(),
					event.getRepeating(), event.getRecurrenceObj(),
					event.getRemindBy(), event.getFirstReminder(),
					event.getSecondReminder(), serviceContext);
			}
			else {
				importedEvent = CalEventLocalServiceUtil.updateEvent(
					userId, existingEvent.getEventId(), event.getTitle(),
					event.getDescription(), event.getLocation(), startDateMonth,
					startDateDay, startDateYear, startDateHour, startDateMinute,
					endDateMonth, endDateDay, endDateYear,
					event.getDurationHour(), event.getDurationMinute(),
					event.getAllDay(), event.getTimeZoneSensitive(),
					event.getType(), event.getRepeating(),
					event.getRecurrenceObj(), event.getRemindBy(),
					event.getFirstReminder(), event.getSecondReminder(),
					serviceContext);
			}
		}
		else {
			importedEvent = CalEventLocalServiceUtil.addEvent(
				userId, event.getTitle(), event.getDescription(),
				event.getLocation(), startDateMonth, startDateDay,
				startDateYear, startDateHour, startDateMinute, endDateMonth,
				endDateDay, endDateYear, event.getDurationHour(),
				event.getDurationMinute(), event.getAllDay(),
				event.getTimeZoneSensitive(), event.getType(),
				event.getRepeating(), event.getRecurrenceObj(),
				event.getRemindBy(), event.getFirstReminder(),
				event.getSecondReminder(), serviceContext);
		}

		portletDataContext.importClassedModel(event, importedEvent, _NAMESPACE);
	}

	private static final boolean _ALWAYS_EXPORTABLE = true;

	private static final String _NAMESPACE = "calendar";

	private static final boolean _PUBLISH_TO_LIVE_BY_DEFAULT = true;

	private static PortletDataHandlerBoolean _categories =
		new PortletDataHandlerBoolean(_NAMESPACE, "categories");

	private static PortletDataHandlerBoolean _comments =
		new PortletDataHandlerBoolean(_NAMESPACE, "comments");

	private static PortletDataHandlerBoolean _events =
		new PortletDataHandlerBoolean(_NAMESPACE, "events", true, true);

	private static PortletDataHandlerBoolean _ratings =
		new PortletDataHandlerBoolean(_NAMESPACE, "ratings");

	private static PortletDataHandlerBoolean _tags =
		new PortletDataHandlerBoolean(_NAMESPACE, "tags");

}