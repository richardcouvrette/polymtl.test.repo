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

package com.liferay.portal.kernel.util;

import java.text.Format;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author Brian Wing Shun Chan
 */
public class Time {

	public static final long SECOND = 1000;

	public static final long MINUTE = SECOND * 60;

	public static final long HOUR = MINUTE * 60;

	public static final long DAY = HOUR * 24;

	public static final long WEEK = DAY * 7;

	public static final long MONTH = DAY * 30;

	public static final long YEAR = DAY * 365;

	public static final String RFC822_FORMAT = "EEE, dd MMM yyyy HH:mm:ss Z";

	public static final String TIMESTAMP_FORMAT = "yyyyMMddkkmmssSSS";

	public static final String SHORT_TIMESTAMP_FORMAT = "yyyyMMddkkmm";

	public static Date getDate(Calendar cal) {
		Calendar adjustedCal = CalendarFactoryUtil.getCalendar();

		adjustedCal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
		adjustedCal.set(Calendar.MONTH, cal.get(Calendar.MONTH));
		adjustedCal.set(Calendar.DATE, cal.get(Calendar.DATE));
		adjustedCal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY));
		adjustedCal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE));
		adjustedCal.set(Calendar.SECOND, cal.get(Calendar.SECOND));
		adjustedCal.set(Calendar.MILLISECOND, cal.get(Calendar.MILLISECOND));

		return adjustedCal.getTime();
	}

	public static Date getDate(TimeZone tz) {
		Calendar cal = CalendarFactoryUtil.getCalendar(tz);

		return getDate(cal);
	}

	public static Date getDate(Date date, TimeZone tz) {
		Calendar cal = CalendarFactoryUtil.getCalendar(tz);

		cal.setTime(date);

		return getDate(cal);
	}

	public static String getDescription(long milliseconds) {
		return getDescription(milliseconds, false);
	}

	public static String getDescription(
		long milliseconds, boolean approximate) {

		String s = StringPool.BLANK;

		int x = 0;

		if (approximate) {
			if (milliseconds <= 0) {
				s = "0 Seconds";
			}
			else if (milliseconds < MINUTE) {
				x = (int)(milliseconds / SECOND);

				s = x + " Second";
			}
			else if (milliseconds < HOUR) {
				x = (int)(milliseconds / MINUTE);

				s = x + " Minute";
			}
			else if (milliseconds < DAY) {
				x = (int)(milliseconds / HOUR);

				s = x + " Hour";
			}
			else if (milliseconds < MONTH) {
				x = (int)(milliseconds / DAY);

				s = x + " Day";
			}
			else if (milliseconds < YEAR) {
				x = (int)(milliseconds / MONTH);

				s = x + " Month";
			}
			else if (milliseconds >= YEAR) {
				x = (int)(milliseconds / YEAR);

				s = x + " Year";
			}
		}
		else {
			if (milliseconds % WEEK == 0) {
				x = (int)(milliseconds / WEEK);

				s = x + " Week";
			}
			else if (milliseconds % DAY == 0) {
				x = (int)(milliseconds / DAY);

				s = x + " Day";
			}
			else if (milliseconds % HOUR == 0) {
				x = (int)(milliseconds / HOUR);

				s = x + " Hour";
			}
			else if (milliseconds % MINUTE == 0) {
				x = (int)(milliseconds / MINUTE);

				s = x + " Minute";
			}
			else if (milliseconds % SECOND == 0) {
				x = (int)(milliseconds / SECOND);

				s = x + " Second";
			}
			else {
				x = (int)milliseconds;

				s = x + " Millisecond";
			}
		}

		if (x > 1) {
			s += "s";
		}

		return s;
	}

	public static String getRFC822() {
		return getRFC822(new Date());
	}

	public static String getRFC822(Date date) {
		return getSimpleDate(date, RFC822_FORMAT);
	}

	public static String getShortTimestamp() {
		return getShortTimestamp(new Date());
	}

	public static String getShortTimestamp(Date date) {
		return getSimpleDate(date, SHORT_TIMESTAMP_FORMAT);
	}

	public static String getSimpleDate(Date date, String format) {
		String s = StringPool.BLANK;

		if (date != null) {
			Format dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(
				format);

			s = dateFormat.format(date);
		}

		return s;
	}

	public static String getTimestamp() {
		return getTimestamp(new Date());
	}

	public static String getTimestamp(Date date) {
		return getSimpleDate(date, TIMESTAMP_FORMAT);
	}

}