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

package com.liferay.portal.upgrade.v5_2_3.util;

import java.sql.Types;

/**
 * @author	  Brian Wing Shun Chan
 * @generated
 */
public class SCProductEntryTable {

	public static final String TABLE_NAME = "SCProductEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"productEntryId", Types.BIGINT},
		{"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT},
		{"userId", Types.BIGINT},
		{"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP},
		{"name", Types.VARCHAR},
		{"type_", Types.VARCHAR},
		{"tags", Types.VARCHAR},
		{"shortDescription", Types.VARCHAR},
		{"longDescription", Types.VARCHAR},
		{"pageURL", Types.VARCHAR},
		{"author", Types.VARCHAR},
		{"repoGroupId", Types.VARCHAR},
		{"repoArtifactId", Types.VARCHAR}
	};

	public static final String TABLE_SQL_CREATE = "create table SCProductEntry (productEntryId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,name VARCHAR(75) null,type_ VARCHAR(75) null,tags VARCHAR(255) null,shortDescription STRING null,longDescription STRING null,pageURL STRING null,author VARCHAR(75) null,repoGroupId VARCHAR(75) null,repoArtifactId VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP = "drop table SCProductEntry";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
		"create index IX_5D25244F on SCProductEntry (companyId)",
		"create index IX_72F87291 on SCProductEntry (groupId)",
		"create index IX_98E6A9CB on SCProductEntry (groupId, userId)",
		"create index IX_7311E812 on SCProductEntry (repoGroupId, repoArtifactId)"
	};

}