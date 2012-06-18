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

package com.liferay.portal.cache.ehcache;

import com.liferay.portal.kernel.cache.CacheListener;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.Serializable;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.event.CacheEventListener;

/**
 * @author Edward C. Han
 */
public class PortalCacheCacheEventListener implements CacheEventListener {

	public PortalCacheCacheEventListener(
		CacheListener cacheListener, PortalCache portalCache) {

		_cacheListener = cacheListener;
		_portalCache = portalCache;
	}

	@Override
	public Object clone() {
		return new PortalCacheCacheEventListener(_cacheListener, _portalCache);
	}

	public void dispose() {
	}

	public void notifyElementEvicted(Ehcache ehcache, Element element) {
		Serializable key = element.getKey();

		_cacheListener.notifyEntryEvicted(
			_portalCache, String.valueOf(key), element.getObjectValue());

		if (_log.isDebugEnabled()) {
			_log.debug("Evicted " + key + " from " + ehcache.getName());
		}
	}

	public void notifyElementExpired(Ehcache ehcache, Element element) {
		Serializable key = element.getKey();

		_cacheListener.notifyEntryExpired(
			_portalCache, String.valueOf(key), element.getObjectValue());

		if (_log.isDebugEnabled()) {
			_log.debug("Expired " + key + " from " + ehcache.getName());
		}
	}

	public void notifyElementPut(Ehcache ehcache, Element element)
		throws CacheException {

		Serializable key = element.getKey();

		_cacheListener.notifyEntryPut(
			_portalCache, String.valueOf(key), element.getObjectValue());

		if (_log.isDebugEnabled()) {
			_log.debug("Inserted " + key + " into " + ehcache.getName());
		}
	}

	public void notifyElementRemoved(Ehcache ehcache, Element element)
		throws CacheException {

		Serializable key = element.getKey();

		_cacheListener.notifyEntryRemoved(
			_portalCache, String.valueOf(key), element.getObjectValue());

		if (_log.isDebugEnabled()) {
			_log.debug("Removed " + key + " from " + ehcache.getName());
		}
	}

	public void notifyElementUpdated(Ehcache ehcache, Element element)
		throws CacheException {

		Serializable key = element.getKey();

		_cacheListener.notifyEntryUpdated(
			_portalCache, String.valueOf(key), element.getObjectValue());

		if (_log.isDebugEnabled()) {
			_log.debug("Updated " + key + " in " + ehcache.getName());
		}
	}

	public void notifyRemoveAll(Ehcache ehcache) {
		_cacheListener.notifyRemoveAll(_portalCache);

		if (_log.isDebugEnabled()) {
			_log.debug("Cleared " + ehcache.getName());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		PortalCacheCacheEventListener.class);

	private CacheListener _cacheListener;
	private PortalCache _portalCache;

}