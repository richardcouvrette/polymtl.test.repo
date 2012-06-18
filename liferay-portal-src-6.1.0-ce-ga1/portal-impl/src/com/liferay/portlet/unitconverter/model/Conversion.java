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

package com.liferay.portlet.unitconverter.model;

/**
 * @author James Lefeu
 */
public class Conversion {

	public Conversion(int type, int fromId, int toId,
					  double fromValue) {
		_type = type;
		_fromId = fromId;
		_toId = toId;
		_fromValue = fromValue;
	}

	public Conversion(int type, int fromId, int toId,
					  double fromValue, double toValue) {
		_type = type;
		_fromId = fromId;
		_toId = toId;
		_fromValue = fromValue;
		_toValue = toValue;
	}

	public int getType() {
		return _type;
	}

	public int getFromId() {
		return _fromId;
	}

	public int getToId() {
		return _toId;
	}

	public double getFromValue() {
		return _fromValue;
	}

	public void setFromValue(double fromValue) {
		_fromValue = fromValue;
	}

	public double getToValue() {
		return _toValue;
	}

	public void setToValue(double toValue) {
		_toValue = toValue;
	}

	private int _type;
	private int _fromId;
	private int _toId;
	private double _fromValue;
	private double _toValue;

}