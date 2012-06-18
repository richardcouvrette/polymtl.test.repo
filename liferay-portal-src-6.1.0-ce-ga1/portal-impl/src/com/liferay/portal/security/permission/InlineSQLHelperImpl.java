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

package com.liferay.portal.security.permission;

import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.service.ResourceBlockLocalServiceUtil;
import com.liferay.portal.service.ResourceTypePermissionLocalServiceUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Raymond Augé
 * @author Connor McKay
 */
public class InlineSQLHelperImpl implements InlineSQLHelper {

	public static final String FILTER_BY_RESOURCE_BLOCK_ID =
		InlineSQLHelper.class.getName() + ".filterByResourceBlockId";

	public static final String FILTER_BY_RESOURCE_BLOCK_ID_OWNER =
		InlineSQLHelper.class.getName() + ".filterByResourceBlockIdOwner";

	public static final String JOIN_RESOURCE_PERMISSION =
		InlineSQLHelper.class.getName() + ".joinResourcePermission";

	public boolean isEnabled() {
		return isEnabled(0);
	}

	public boolean isEnabled(long groupId) {
		if (PropsValues.PERMISSIONS_USER_CHECK_ALGORITHM != 6) {
			return false;
		}

		if (!PropsValues.PERMISSIONS_INLINE_SQL_CHECK_ENABLED) {
			return false;
		}

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if (permissionChecker == null) {
			return false;
		}

		if (groupId > 0) {
			if (permissionChecker.isGroupAdmin(groupId) ||
				permissionChecker.isGroupOwner(groupId)) {

				return false;
			}
		}
		else {
			if (permissionChecker.isCompanyAdmin()) {
				return false;
			}
		}

		return true;
	}

	public boolean isEnabled(long[] groupIds) {
		if (PropsValues.PERMISSIONS_USER_CHECK_ALGORITHM != 6) {
			return false;
		}

		if (!PropsValues.PERMISSIONS_INLINE_SQL_CHECK_ENABLED) {
			return false;
		}

		for (long groupId : groupIds) {
			if (isEnabled(groupId)) {
				return true;
			}
		}

		return false;
	}

	public String replacePermissionCheck(
		String sql, String className, String classPKField) {

		return replacePermissionCheck(
			sql, className, classPKField, null, new long[] {0}, null);
	}

	public String replacePermissionCheck(
		String sql, String className, String classPKField, long groupId) {

		return replacePermissionCheck(
			sql, className, classPKField, null, new long[] {groupId}, null);
	}

	public String replacePermissionCheck(
		String sql, String className, String classPKField, long groupId,
		String bridgeJoin) {

		return replacePermissionCheck(
			sql, className, classPKField, null, new long[] {groupId},
			bridgeJoin);
	}

	public String replacePermissionCheck(
		String sql, String className, String classPKField, long[] groupIds) {

		return replacePermissionCheck(
			sql, className, classPKField, null, groupIds, null);
	}

	public String replacePermissionCheck(
		String sql, String className, String classPKField, long[] groupIds,
		String bridgeJoin) {

		return replacePermissionCheck(
			sql, className, classPKField, null, groupIds, bridgeJoin);
	}

	public String replacePermissionCheck(
		String sql, String className, String classPKField, String userIdField) {

		return replacePermissionCheck(
			sql, className, classPKField, userIdField, new long[] {0}, null);
	}

	public String replacePermissionCheck(
		String sql, String className, String classPKField, String userIdField,
		long groupId) {

		return replacePermissionCheck(
			sql, className, classPKField, userIdField, new long[] {groupId},
			null);
	}

	public String replacePermissionCheck(
		String sql, String className, String classPKField, String userIdField,
		long groupId, String bridgeJoin) {

		return replacePermissionCheck(
			sql, className, classPKField, userIdField, new long[] {groupId},
			bridgeJoin);
	}

	public String replacePermissionCheck(
		String sql, String className, String classPKField, String userIdField,
		long[] groupIds) {

		return replacePermissionCheck(
			sql, className, classPKField, userIdField, groupIds, null);
	}

	public String replacePermissionCheck(
		String sql, String className, String classPKField, String userIdField,
		long[] groupIds, String bridgeJoin) {

		if (!isEnabled(groupIds)) {
			return sql;
		}

		if (Validator.isNull(className)) {
			throw new IllegalArgumentException("className is null");
		}

		if (Validator.isNull(sql)) {
			return sql;
		}

		if (ResourceBlockLocalServiceUtil.isSupported(className)) {
			return replacePermissionCheckBlocks(
				sql, className, classPKField, userIdField, groupIds,
				bridgeJoin);
		}
		else {
			return replacePermissionCheckJoin(
				sql, className, classPKField, userIdField, groupIds,
				bridgeJoin);
		}
	}

	public String replacePermissionCheck(
		String sql, String className, String classPKField, String userIdField,
		String bridgeJoin) {

		return replacePermissionCheck(
			sql, className, classPKField, userIdField, 0, bridgeJoin);
	}

	protected Set<Long> getOwnerResourceBlockIds(
		long companyId, long[] groupIds, String className) {

		Set<Long> resourceBlockIds = new HashSet<Long>();

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		for (long groupId : groupIds) {
			resourceBlockIds.addAll(
				permissionChecker.getOwnerResourceBlockIds(
				companyId, groupId, className, ActionKeys.VIEW));
		}

		return resourceBlockIds;
	}

	protected Set<Long> getResourceBlockIds(
		long companyId, long[] groupIds, String className) {

		Set<Long> resourceBlockIds = new HashSet<Long>();

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		for (long groupId : groupIds) {
			resourceBlockIds.addAll(
				permissionChecker.getResourceBlockIds(
				companyId, groupId, permissionChecker.getUserId(), className,
				ActionKeys.VIEW));
		}

		return resourceBlockIds;
	}

	protected long[] getRoleIds(long groupId) {
		long[] roleIds = PermissionChecker.DEFAULT_ROLE_IDS;

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if (permissionChecker != null) {
			roleIds = permissionChecker.getRoleIds(
				permissionChecker.getUserId(), groupId);
		}

		return roleIds;
	}

	protected long[] getRoleIds(long[] groupIds) {
		long[] roleIds = PermissionChecker.DEFAULT_ROLE_IDS;

		for (long groupId : groupIds) {
			for (long roleId : getRoleIds(groupId)) {
				if (!ArrayUtil.contains(roleIds, roleId)) {
					roleIds = ArrayUtil.append(roleIds, roleId);
				}
			}
		}

		return roleIds;
	}

	protected long getUserId() {
		long userId = 0;

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if (permissionChecker != null) {
			userId = permissionChecker.getUserId();
		}

		return userId;
	}

	protected String replacePermissionCheckBlocks(
		String sql, String className, String classPKField, String userIdField,
		long[] groupIds, String bridgeJoin) {

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		long checkGroupId = 0;

		if (groupIds.length == 1) {
			checkGroupId = groupIds[0];
		}

		long companyId = permissionChecker.getCompanyId();

		long[] roleIds = permissionChecker.getRoleIds(
			getUserId(), checkGroupId);

		try {
			for (long roleId : roleIds) {
				if (ResourceTypePermissionLocalServiceUtil.
						hasCompanyScopePermission(
							companyId, className, roleId, ActionKeys.VIEW)) {

					return sql;
				}
			}
		}
		catch (Exception e) {
		}

		Set<Long> userResourceBlockIds = getResourceBlockIds(
			companyId, groupIds, className);

		String permissionWhere = StringPool.BLANK;

		if (Validator.isNotNull(bridgeJoin)) {
			permissionWhere = bridgeJoin;
		}

		Set<Long> ownerResourceBlockIds = getOwnerResourceBlockIds(
			companyId, groupIds, className);

		// If a user has regular access to a resource block, it isn't necessary
		// to check owner permissions on it as well.

		ownerResourceBlockIds.removeAll(userResourceBlockIds);

		// A SQL syntax error occurs if there is not at least one resource block
		// ID.

		if (userResourceBlockIds.size() == 0) {
			userResourceBlockIds.add(_NO_RESOURCE_BLOCKS_ID);
		}

		if (Validator.isNotNull(userIdField) &&
			ownerResourceBlockIds.size() > 0) {

			permissionWhere = permissionWhere.concat(
				CustomSQLUtil.get(FILTER_BY_RESOURCE_BLOCK_ID_OWNER));

			permissionWhere = StringUtil.replace(
				permissionWhere,
				new String[] {
					"[$OWNER_RESOURCE_BLOCK_IDS$]",
					"[$USER_ID$]",
					"[$USER_ID_FIELD$]",
					"[$USER_RESOURCE_BLOCK_IDS$]"
				},
				new String[] {
					StringUtil.merge(ownerResourceBlockIds),
					String.valueOf(permissionChecker.getUserId()),
					userIdField,
					StringUtil.merge(userResourceBlockIds)
				});
		}
		else {
			permissionWhere = permissionWhere.concat(
				CustomSQLUtil.get(FILTER_BY_RESOURCE_BLOCK_ID));

			permissionWhere = StringUtil.replace(
				permissionWhere, "[$USER_RESOURCE_BLOCK_IDS$]",
				StringUtil.merge(userResourceBlockIds));
		}

		int pos = sql.indexOf(_WHERE_CLAUSE);

		if (pos != -1) {
			StringBundler sb = new StringBundler(4);

			sb.append(sql.substring(0, pos));
			sb.append(permissionWhere);
			sb.append(" AND ");
			sb.append(sql.substring(pos + 7));

			return sb.toString();
		}

		pos = sql.indexOf(_GROUP_BY_CLAUSE);

		if (pos != -1) {
			return sql.substring(0, pos + 1).concat(permissionWhere).concat(
				sql.substring(pos + 1));
		}

		pos = sql.indexOf(_ORDER_BY_CLAUSE);

		if (pos != -1) {
			return sql.substring(0, pos + 1).concat(permissionWhere).concat(
				sql.substring(pos + 1));
		}

		return sql.concat(StringPool.SPACE).concat(permissionWhere);
	}

	protected String replacePermissionCheckJoin(
		String sql, String className, String classPKField, String userIdField,
		long[] groupIds, String bridgeJoin) {

		if (Validator.isNull(classPKField)) {
			throw new IllegalArgumentException("classPKField is null");
		}

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		long checkGroupId = 0;

		if (groupIds.length == 1) {
			checkGroupId = groupIds[0];
		}

		if (permissionChecker.hasPermission(
				checkGroupId, className, 0, ActionKeys.VIEW)) {

			return sql;
		}

		String permissionJoin = StringPool.BLANK;

		if (Validator.isNotNull(bridgeJoin)) {
			permissionJoin = bridgeJoin;
		}

		permissionJoin += CustomSQLUtil.get(JOIN_RESOURCE_PERMISSION);

		StringBundler sb = new StringBundler();

		sb.append("(InlineSQLResourcePermission.scope = ");
		sb.append(ResourceConstants.SCOPE_INDIVIDUAL);
		sb.append(" AND ");
		sb.append("InlineSQLResourcePermission.primKey = CAST_TEXT(");
		sb.append(classPKField);
		sb.append(") AND (");

		long userId = getUserId();

		boolean hasPreviousViewableGroup = false;

		for (int j = 0; j < groupIds.length; j++) {
			long groupId = groupIds[j];

			if (!permissionChecker.hasPermission(
					groupId, className, 0, ActionKeys.VIEW)) {

				if ((j > 0) && hasPreviousViewableGroup) {
					sb.append(" OR ");
				}

				hasPreviousViewableGroup = true;

				sb.append("(");
				sb.append(
					classPKField.substring(
						0, classPKField.lastIndexOf(CharPool.PERIOD)));
				sb.append(".groupId = ");
				sb.append(groupId);
				sb.append(")");

				long[] roleIds = getRoleIds(groupId);

				if (roleIds.length == 0) {
					roleIds = _NO_ROLE_IDS;
				}

				sb.append(" AND (");

				for (int i = 0; i < roleIds.length; i++) {
					if (i > 0) {
						sb.append(" OR ");
					}

					sb.append("InlineSQLResourcePermission.roleId = ");
					sb.append(roleIds[i]);
				}

				if (permissionChecker.isSignedIn()) {
					sb.append(" OR ");

					if (Validator.isNotNull(userIdField)) {
						sb.append("(");
						sb.append(userIdField);
						sb.append(" = ");
						sb.append(userId);
						sb.append(")");
					}
					else {
						sb.append("(InlineSQLResourcePermission.ownerId = ");
						sb.append(userId);
						sb.append(")");
					}
				}

				sb.append(")");
			}
		}

		sb.append("))");

		permissionJoin = StringUtil.replace(
			permissionJoin,
			new String[] {
				"[$CLASS_NAME$]",
				"[$COMPANY_ID$]",
				"[$PRIM_KEYS$]"
			},
			new String[] {
				className,
				String.valueOf(permissionChecker.getCompanyId()),
				sb.toString()
			});

		int pos = sql.indexOf(_WHERE_CLAUSE);

		if (pos != -1) {
			return sql.substring(0, pos + 1).concat(permissionJoin).concat(
				sql.substring(pos + 1));
		}

		pos = sql.indexOf(_GROUP_BY_CLAUSE);

		if (pos != -1) {
			return sql.substring(0, pos + 1).concat(permissionJoin).concat(
				sql.substring(pos + 1));
		}

		pos = sql.indexOf(_ORDER_BY_CLAUSE);

		if (pos != -1) {
			return sql.substring(0, pos + 1).concat(permissionJoin).concat(
				sql.substring(pos + 1));
		}

		return sql.concat(StringPool.SPACE).concat(permissionJoin);
	}

	private static final String _GROUP_BY_CLAUSE = " GROUP BY ";

	private static final long _NO_RESOURCE_BLOCKS_ID = -1;

	private static final long[] _NO_ROLE_IDS = {0};

	private static final String _ORDER_BY_CLAUSE = " ORDER BY ";

	private static final String _WHERE_CLAUSE = " WHERE ";

}