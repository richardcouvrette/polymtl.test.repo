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

import java.io.IOException;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;

/**
 * @author Shuyang Zhou
 */
public class SocketUtil {

	public static BindInfo getBindInfo(String host, int port)
		throws IOException {

		Socket socket = null;

		try {
			socket = new Socket(host, port);

			InetAddress inetAddress = socket.getLocalAddress();
			NetworkInterface networkInterface =
				NetworkInterface.getByInetAddress(inetAddress);

			BindInfo bindInfo = new BindInfo();

			bindInfo.setInetAddress(inetAddress);
			bindInfo.setNetworkInterface(networkInterface);

			return bindInfo;
		}
		finally {
			if (socket != null) {
				try {
					socket.close();
				}
				catch (IOException ioe) {
				}
			}
		}
	}

	public static class BindInfo {

		public InetAddress getInetAddress() {
			return _inetAddress;
		}

		public NetworkInterface getNetworkInterface() {
			return _networkInterface;
		}

		public void setInetAddress(InetAddress inetAddress) {
			_inetAddress = inetAddress;
		}

		public void setNetworkInterface(NetworkInterface networkInterface) {
			_networkInterface = networkInterface;
		}

		private InetAddress _inetAddress;
		private NetworkInterface _networkInterface;

	}

}