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

package com.liferay.portal.bean;

import com.liferay.portal.kernel.bean.BeanProperties;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.WebKeys;

import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import jodd.bean.BeanTool;
import jodd.bean.BeanUtil;

import jodd.typeconverter.Convert;

/**
 * @author Brian Wing Shun Chan
 */
public class BeanPropertiesImpl implements BeanProperties {

	public void copyProperties(Object source, Object target) {
		try {
			BeanTool.copyProperties(source, target);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	public void copyProperties(
		Object source, Object target, Class<?> editable) {

		try {
			BeanTool.copyProperties(source, target, editable);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	public void copyProperties(
		Object source, Object target, String[] ignoreProperties) {

		try {
			BeanTool.copyProperties(source, target, ignoreProperties, false);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	public boolean getBoolean(Object bean, String param) {
		return getBoolean(bean, param, GetterUtil.DEFAULT_BOOLEAN);
	}

	public boolean getBoolean(Object bean, String param, boolean defaultValue) {
		boolean beanValue = defaultValue;

		if (bean != null) {
			try {
				Object value = BeanUtil.getProperty(bean, param);

				beanValue = Convert.toBoolean(value, defaultValue);
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		return beanValue;
	}

	public boolean getBooleanSilent(Object bean, String param) {
		return getBooleanSilent(bean, param, GetterUtil.DEFAULT_BOOLEAN);
	}

	public boolean getBooleanSilent(
		Object bean, String param, boolean defaultValue) {

		boolean beanValue = defaultValue;

		if (bean != null) {
			try {
				Object value = BeanUtil.getProperty(bean, param);

				beanValue = Convert.toBoolean(value, defaultValue);
			}
			catch (Exception e) {
			}
		}

		return beanValue;
	}

	public byte getByte(Object bean, String param) {
		return getByte(bean, param, GetterUtil.DEFAULT_BYTE);
	}

	public byte getByte(Object bean, String param, byte defaultValue) {
		byte beanValue = defaultValue;

		if (bean != null) {
			try {
				Object value = BeanUtil.getProperty(bean, param);

				beanValue = Convert.toByte(value, defaultValue);
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		return beanValue;
	}

	public byte getByteSilent(Object bean, String param) {
		return getByteSilent(bean, param, GetterUtil.DEFAULT_BYTE);
	}

	public byte getByteSilent(Object bean, String param, byte defaultValue) {
		byte beanValue = defaultValue;

		if (bean != null) {
			try {
				Object value = BeanUtil.getProperty(bean, param);

				beanValue = Convert.toByte(value, defaultValue);
			}
			catch (Exception e) {
			}
		}

		return beanValue;
	}

	public double getDouble(Object bean, String param) {
		return getDouble(bean, param, GetterUtil.DEFAULT_DOUBLE);
	}

	public double getDouble(Object bean, String param, double defaultValue) {
		double beanValue = defaultValue;

		if (bean != null) {
			try {
				Object value = BeanUtil.getProperty(bean, param);

				beanValue = Convert.toDouble(value, defaultValue);
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		return beanValue;
	}

	public double getDoubleSilent(Object bean, String param) {
		return getDoubleSilent(bean, param, GetterUtil.DEFAULT_DOUBLE);
	}

	public double getDoubleSilent(
		Object bean, String param, double defaultValue) {

		double beanValue = defaultValue;

		if (bean != null) {
			try {
				Object value = BeanUtil.getProperty(bean, param);

				beanValue = Convert.toDouble(value, defaultValue);
			}
			catch (Exception e) {
			}
		}

		return beanValue;
	}

	public float getFloat(Object bean, String param) {
		return getFloat(bean, param, GetterUtil.DEFAULT_FLOAT);
	}

	public float getFloat(Object bean, String param, float defaultValue) {
		float beanValue = defaultValue;

		if (bean != null) {
			try {
				Object value = BeanUtil.getProperty(bean, param);

				beanValue = Convert.toFloat(value, defaultValue);
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		return beanValue;
	}

	public float getFloatSilent(Object bean, String param) {
		return getFloatSilent(bean, param, GetterUtil.DEFAULT_FLOAT);
	}

	public float getFloatSilent(Object bean, String param, float defaultValue) {
		float beanValue = defaultValue;

		if (bean != null) {
			try {
				Object value = BeanUtil.getProperty(bean, param);

				beanValue = Convert.toFloat(value, defaultValue);
			}
			catch (Exception e) {
			}
		}

		return beanValue;
	}

	public int getInteger(Object bean, String param) {
		return getInteger(bean, param, GetterUtil.DEFAULT_INTEGER);
	}

	public int getInteger(Object bean, String param, int defaultValue) {
		int beanValue = defaultValue;

		if (bean != null) {
			try {
				Object value = BeanUtil.getProperty(bean, param);

				beanValue = Convert.toInteger(value, defaultValue);
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		return beanValue;
	}

	public int getIntegerSilent(Object bean, String param) {
		return getIntegerSilent(bean, param, GetterUtil.DEFAULT_INTEGER);
	}

	public int getIntegerSilent(Object bean, String param, int defaultValue) {
		int beanValue = defaultValue;

		if (bean != null) {
			try {
				Object value = BeanUtil.getProperty(bean, param);

				beanValue = Convert.toInteger(value, defaultValue);
			}
			catch (Exception e) {
			}
		}

		return beanValue;
	}

	public long getLong(Object bean, String param) {
		return getLong(bean, param, GetterUtil.DEFAULT_LONG);
	}

	public long getLong(Object bean, String param, long defaultValue) {
		long beanValue = defaultValue;

		if (bean != null) {
			try {
				Object value = BeanUtil.getProperty(bean, param);

				beanValue = Convert.toLong(value, defaultValue);
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		return beanValue;
	}

	public long getLongSilent(Object bean, String param) {
		return getLongSilent(bean, param, GetterUtil.DEFAULT_LONG);
	}

	public long getLongSilent(Object bean, String param, long defaultValue) {
		long beanValue = defaultValue;

		if (bean != null) {
			try {
				Object value = BeanUtil.getProperty(bean, param);

				beanValue = Convert.toLong(value, defaultValue);
			}
			catch (Exception e) {
			}
		}

		return beanValue;
	}

	public Object getObject(Object bean, String param) {
		return getObject(bean, param, null);
	}

	public Object getObject(Object bean, String param, Object defaultValue) {
		Object beanValue = null;

		if (bean != null) {
			try {
				beanValue = BeanUtil.getProperty(bean, param);
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		if (beanValue == null) {
			return defaultValue;
		}
		else {
			return beanValue;
		}
	}

	public Object getObjectSilent(Object bean, String param) {
		return getObjectSilent(bean, param, null);
	}

	public Object getObjectSilent(
		Object bean, String param, Object defaultValue) {

		Object beanValue = null;

		if (bean != null) {
			try {
				beanValue = BeanUtil.getProperty(bean, param);
			}
			catch (Exception e) {
			}
		}

		if (beanValue == null) {
			return defaultValue;
		}
		else {
			return beanValue;
		}
	}

	public short getShort(Object bean, String param) {
		return getShort(bean, param, GetterUtil.DEFAULT_SHORT);
	}

	public short getShort(Object bean, String param, short defaultValue) {
		short beanValue = defaultValue;

		if (bean != null) {
			try {
				Object value = BeanUtil.getProperty(bean, param);

				beanValue = Convert.toShort(value, defaultValue);
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		return beanValue;
	}

	public short getShortSilent(Object bean, String param) {
		return getShortSilent(bean, param, GetterUtil.DEFAULT_SHORT);
	}

	public short getShortSilent(Object bean, String param, short defaultValue) {
		short beanValue = defaultValue;

		if (bean != null) {
			try {
				Object value = BeanUtil.getProperty(bean, param);

				beanValue = Convert.toShort(value, defaultValue);
			}
			catch (Exception e) {
			}
		}

		return beanValue;
	}

	public String getString(Object bean, String param) {
		return getString(bean, param, GetterUtil.DEFAULT_STRING);
	}

	public String getString(Object bean, String param, String defaultValue) {
		String beanValue = defaultValue;

		if (bean != null) {
			try {
				Object value = BeanUtil.getProperty(bean, param);

				beanValue = Convert.toString(value, defaultValue);
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		return beanValue;
	}

	public String getStringSilent(Object bean, String param) {
		return getStringSilent(bean, param, GetterUtil.DEFAULT_STRING);
	}

	public String getStringSilent(
		Object bean, String param, String defaultValue) {

		String beanValue = defaultValue;

		if (bean != null) {
			try {
				Object value = BeanUtil.getProperty(bean, param);

				beanValue = Convert.toString(value, defaultValue);
			}
			catch (Exception e) {
			}
		}

		return beanValue;
	}

	public void setProperties(Object bean, HttpServletRequest request) {
		Enumeration<String> enu = request.getParameterNames();

		while (enu.hasMoreElements()) {
			String name = enu.nextElement();

			String value = request.getParameter(name);

			BeanUtil.setPropertyForcedSilent(bean, name, value);

			if (name.endsWith("Month")) {
				String dateParam = name.substring(0, name.lastIndexOf("Month"));

				if (request.getParameter(dateParam) != null) {
					continue;
				}

				Class<?> propertyTypeClass = BeanUtil.getPropertyType(
					bean, dateParam);

				if ((propertyTypeClass == null) ||
					!propertyTypeClass.equals(Date.class)) {

					continue;
				}

				Date date = getDate(dateParam, request);

				if (date != null) {
					BeanUtil.setPropertyForcedSilent(bean, dateParam, date);
				}
			}
		}
	}

	public void setProperty(Object bean, String param, Object value) {
		try {
			BeanUtil.setProperty(bean, param, value);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	protected Date getDate(String param, HttpServletRequest request) {
		int month = ParamUtil.getInteger(request, param + "Month");
		int day = ParamUtil.getInteger(request, param + "Day");
		int year = ParamUtil.getInteger(request, param + "Year");
		int hour = ParamUtil.getInteger(request, param + "Hour", -1);
		int minute = ParamUtil.getInteger(request, param + "Minute");

		int amPm = ParamUtil.getInteger(request, param + "AmPm");

		if (amPm == Calendar.PM) {
			hour += 12;
		}

		if (hour == -1) {
			return PortalUtil.getDate(month, day, year);
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		User user = themeDisplay.getUser();

		try {
			return PortalUtil.getDate(
				month, day, year, hour, minute, user.getTimeZone(),
				new PortalException());
		}
		catch (PortalException pe) {
			return null;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(BeanPropertiesImpl.class);

}