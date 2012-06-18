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

package com.liferay.portal.upgrade.v5_2_3;

import com.liferay.portal.kernel.upgrade.BaseUpgradePortletPreferences;
import com.liferay.portal.kernel.upgrade.util.UpgradeTable;
import com.liferay.portal.kernel.upgrade.util.UpgradeTableFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.upgrade.v5_2_3.util.DLFileEntryTable;
import com.liferay.portal.upgrade.v5_2_3.util.DLFileRankTable;
import com.liferay.portal.upgrade.v5_2_3.util.DLFileShortcutTable;
import com.liferay.portal.upgrade.v5_2_3.util.DLFileVersionTable;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

import javax.portlet.PortletPreferences;

/**
 * @author Samuel Kong
 * @author Brian Wing Shun Chan
 * @author Douglas Wong
 */
public class UpgradeDocumentLibrary extends BaseUpgradePortletPreferences {

	@Override
	protected void doUpgrade() throws Exception {
		try {
			runSQL("alter_column_type DLFileEntry name VARCHAR(255) null");
		}
		catch (Exception e) {

			// DLFileEntry

			UpgradeTable upgradeTable = UpgradeTableFactoryUtil.getUpgradeTable(
				DLFileEntryTable.TABLE_NAME, DLFileEntryTable.TABLE_COLUMNS);

			upgradeTable.setCreateSQL(DLFileEntryTable.TABLE_SQL_CREATE);
			upgradeTable.setIndexesSQL(DLFileEntryTable.TABLE_SQL_ADD_INDEXES);

			upgradeTable.updateTable();
		}

		try {
			runSQL("alter_column_type DLFileRank name VARCHAR(255) null");
		}
		catch (Exception e) {

			// DLFileRank

			UpgradeTable upgradeTable = UpgradeTableFactoryUtil.getUpgradeTable(
				DLFileRankTable.TABLE_NAME, DLFileRankTable.TABLE_COLUMNS);

			upgradeTable.setCreateSQL(DLFileRankTable.TABLE_SQL_CREATE);
			upgradeTable.setIndexesSQL(DLFileRankTable.TABLE_SQL_ADD_INDEXES);

			upgradeTable.updateTable();
		}

		try {
			runSQL("alter_column_type DLFileShortcut toName VARCHAR(255) null");
		}
		catch (Exception e) {

			// DLFileShortcut

			UpgradeTable upgradeTable = UpgradeTableFactoryUtil.getUpgradeTable(
				DLFileShortcutTable.TABLE_NAME,
				DLFileShortcutTable.TABLE_COLUMNS);

			upgradeTable.setCreateSQL(DLFileShortcutTable.TABLE_SQL_CREATE);
			upgradeTable.setIndexesSQL(
				DLFileShortcutTable.TABLE_SQL_ADD_INDEXES);

			upgradeTable.updateTable();
		}

		try {
			runSQL("alter_column_type DLFileVersion name VARCHAR(255) null");
		}
		catch (Exception e) {

			// DLFileVersion

			UpgradeTable upgradeTable = UpgradeTableFactoryUtil.getUpgradeTable(
				DLFileVersionTable.TABLE_NAME,
				DLFileVersionTable.TABLE_COLUMNS);

			upgradeTable.setCreateSQL(DLFileVersionTable.TABLE_SQL_CREATE);
			upgradeTable.setIndexesSQL(
				DLFileVersionTable.TABLE_SQL_ADD_INDEXES);

			upgradeTable.updateTable();
		}

		// groupId

		updateGroupId();

		// PortletPreferences

		updatePortletPreferences();
	}

	@Override
	protected String getUpdatePortletPreferencesWhereClause() {
		return "portletId = '20' and preferences like " +
			"'%<name>fileEntryColumns</name><value></value>%'";
	}

	protected void updateGroupId() throws Exception {
		StringBundler sb = new StringBundler(3);

		sb.append("update DLFileEntry set groupId = (select groupId from ");
		sb.append("DLFolder where DLFolder.folderId = DLFileEntry.folderId)");

		runSQL(sb.toString());

		sb.setIndex(0);

		sb.append("update DLFileRank set groupId = (select groupId from ");
		sb.append("DLFolder where DLFolder.folderId = DLFileRank.folderId)");

		runSQL(sb.toString());

		sb.setIndex(0);

		sb.append("update DLFileShortcut set groupId = (select groupId from ");
		sb.append("DLFolder where DLFolder.folderId = ");
		sb.append("DLFileShortcut.folderId)");

		runSQL(sb.toString());

		sb.setIndex(0);

		sb.append("update DLFileVersion set groupId = (select groupId from ");
		sb.append("DLFolder where DLFolder.folderId = DLFileVersion.folderId)");

		runSQL(sb.toString());
	}

	@Override
	protected String upgradePreferences(
			long companyId, long ownerId, int ownerType, long plid,
			String portletId, String xml)
		throws Exception {

		PortletPreferences portletPreferences =
			PortletPreferencesFactoryUtil.fromXML(
				companyId, ownerId, ownerType, plid, portletId, xml);

		String fileEntryColumns = portletPreferences.getValue(
			"fileEntryColumns", StringPool.BLANK);

		if (Validator.isNull(fileEntryColumns)) {
			portletPreferences.reset("fileEntryColumns");
		}

		return PortletPreferencesFactoryUtil.toXML(portletPreferences);
	}

}