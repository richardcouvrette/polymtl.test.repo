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

package com.liferay.portal.kernel.lar;

import javax.portlet.PortletPreferences;

/**
 * <p>
 * A <code>PortletDataHandler</code> is a special class capable of exporting and
 * importing portlet specific data to a Liferay Archive file (LAR) when a site's
 * layouts are exported or imported. <code>PortletDataHandler</code>s are
 * defined by placing a <code>portlet-data-handler-class</code> element in the
 * <code>portlet</code> section of the <b>liferay-portlet.xml</b> file.
 * </p>
 *
 * @author Raymond Augé
 * @author Joel Kozikowski
 * @author Bruno Farache
 */
public interface PortletDataHandler {

	/**
	 * Deletes the data created by the portlet. Can optionally return a modified
	 * version of <code>preferences</code> if it contains reference to data that
	 * does not exist anymore.
	 *
	 * @param  portletDataContext the context of the data deletion
	 * @param  portletId the portlet ID of the portlet
	 * @param  portletPreferences the portlet preferences of the portlet
	 * @return A modified version of portlet preferences that should be saved.
	 *         <code>Null</code> if the portlet preferences were unmodified by
	 *         this data handler.
	 */
	public PortletPreferences deleteData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws PortletDataException;

	/**
	 * Returns a string of data to be placed in the &lt;portlet-data&gt; section
	 * of the LAR file. This data will be passed as the <code>data</code>
	 * parameter of <code>importData()</code>.
	 *
	 * @param  portletDataContext the context of the data export
	 * @param  portletId the portlet ID of the portlet
	 * @param  portletPreferences the portlet preferences of the portlet
	 * @return A string of data to be placed in the LAR. It may be XML, but not
	 *         necessarily. <code>Null</code> should be returned if no portlet
	 *         data is to be written out.
	 */
	public String exportData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws PortletDataException;

	/**
	 * Returns an array of the controls defined for this data handler. These
	 * controls enable the developer to create fine grained controls over export
	 * behavior. The controls are rendered in the export UI.
	 *
	 * @return an array of PortletDataHandlerControls
	 */
	public PortletDataHandlerControl[] getExportControls()
		throws PortletDataException;

	/**
	 * Returns an array of the controls defined for this data handler. These
	 * controls enable the developer to create fine grained controls over import
	 * behavior. The controls are rendered in the import UI.
	 *
	 * @return An array of PortletDataHandlerControls
	 */
	public PortletDataHandlerControl[] getImportControls()
		throws PortletDataException;

	/**
	 * Handles any special processing of the data when the portlet is imported
	 * into a new layout. Can optionally return a modified version of
	 * <code>preferences</code> to be saved in the new portlet.
	 *
	 * @param  portletDataContext the context of the data import
	 * @param  portletId the portlet ID of the portlet
	 * @param  portletPreferences the portlet preferences of the portlet
	 * @param  data the string data that was returned by
	 *         <code>exportData()</code>
	 * @return A modified version of portlet preferences that should be saved.
	 *         <code>Null</code> if the portlet preferences were unmodified by
	 *         this data handler.
	 */
	public PortletPreferences importData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences, String data)
		throws PortletDataException;

	/**
	 * Returns <code>true</code> to allow the user to export data for this
	 * portlet even though it may not belong to any pages. See LPS-1624.
	 *
	 * @return <code>true</code> to allow the user to export data for this
	 *         portlet even though it may not belong to any pages
	 */
	public boolean isAlwaysExportable();

	public boolean isAlwaysStaged();

	/**
	 * Returns whether the data exported by this handler should be included by
	 * default when publishing to live. This should only be <code>true</code>
	 * for data that is meant to be managed in an staging environment such as
	 * CMS content, but not for data meant to be input by users such as wiki
	 * pages or message board posts.
	 *
	 * @return <code>true</code> to publish to live by default
	 */
	public boolean isPublishToLiveByDefault();

}