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

package com.liferay.portal.dao.shard;

import com.liferay.portal.kernel.dao.shard.ShardUtil;

import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.logging.Logger;

import javax.sql.DataSource;

/**
 * @author Alexander Chow
 */
public class ShardDataSource implements DataSource {

	public static DataSource getInstance() {
		return _instance;
	}

	public Connection getConnection() throws SQLException {
		return getDataSource().getConnection();
	}

	public Connection getConnection(String username, String password)
		throws SQLException {

		return getDataSource().getConnection(username, password);
	}

	public int getLoginTimeout() throws SQLException {
		return getDataSource().getLoginTimeout();
	}

	public PrintWriter getLogWriter() throws SQLException {
		return getDataSource().getLogWriter();
	}

	public Logger getParentLogger() {

		// JDK 7

		throw new UnsupportedOperationException();
	}

	public boolean isWrapperFor(Class<?> clazz) {

		// Directly implement this method for JDK 5 compatibility. Logic is
		// copied from org.springframework.jdbc.datasource.AbstractDataSource.

		return DataSource.class.equals(clazz);
	}

	public void setLoginTimeout(int seconds) throws SQLException {
		getDataSource().setLoginTimeout(seconds);
	}

	public void setLogWriter(PrintWriter printWriter) throws SQLException {
		getDataSource().setLogWriter(printWriter);
	}

	public <T> T unwrap(Class<T> clazz) throws SQLException {

		// Directly implement this method for JDK 5 compatibility. Logic is
		// copied from org.springframework.jdbc.datasource.AbstractDataSource.

		if (!DataSource.class.equals(clazz)) {
			throw new SQLException("Invalid class " + clazz);
		}

		return (T)this;
	}

	protected DataSource getDataSource() {
		return ShardUtil.getDataSource();
	}

	private static ShardDataSource _instance = new ShardDataSource();

}