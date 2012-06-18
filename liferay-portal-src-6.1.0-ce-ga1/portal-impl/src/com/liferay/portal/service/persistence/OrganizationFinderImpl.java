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

package com.liferay.portal.service.persistence;

import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Organization;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Amos Fong
 * @author Brian Wing Shun Chan
 * @author Jorge Ferrer
 * @author Connor McKay
 * @author Shuyang Zhou
 */
public class OrganizationFinderImpl
	extends BasePersistenceImpl<Organization> implements OrganizationFinder {

	public static String COUNT_BY_ORGANIZATION_ID =
		OrganizationFinder.class.getName() + ".countByOrganizationId";

	public static String COUNT_BY_C_PO_N_S_C_Z_R_C =
		OrganizationFinder.class.getName() + ".countByC_PO_N_S_C_Z_R_C";

	public static String COUNT_BY_C_PO_N_L_S_C_Z_R_C =
		OrganizationFinder.class.getName() + ".countByC_PO_N_L_S_C_Z_R_C";

	public static String FIND_BY_COMPANY_ID =
		OrganizationFinder.class.getName() + ".findByCompanyId";

	public static String FIND_BY_C_PO_N_S_C_Z_R_C =
		OrganizationFinder.class.getName() + ".findByC_PO_N_S_C_Z_R_C";

	public static String FIND_BY_C_PO_N_L_S_C_Z_R_C =
		OrganizationFinder.class.getName() + ".findByC_PO_N_L_S_C_Z_R_C";

	public static String JOIN_BY_GROUPS_PERMISSIONS =
		OrganizationFinder.class.getName() + ".joinByGroupsPermissions";

	public static String JOIN_BY_ORGANIZATIONS_GROUPS =
		OrganizationFinder.class.getName() + ".joinByOrganizationsGroups";

	public static String JOIN_BY_ORGANIZATIONS_PASSWORD_POLICIES =
		OrganizationFinder.class.getName() +
			".joinByOrganizationsPasswordPolicies";

	public static String JOIN_BY_ORGANIZATIONS_ROLES =
		OrganizationFinder.class.getName() + ".joinByOrganizationsRoles";

	public static String JOIN_BY_ORGANIZATIONS_USERS =
		OrganizationFinder.class.getName() + ".joinByOrganizationsUsers";

	public static String JOIN_BY_ORG_GROUP_PERMISSION =
		OrganizationFinder.class.getName() + ".joinByOrgGroupPermission";

	public static String JOIN_BY_USERS_ORGS =
		OrganizationFinder.class.getName() + ".joinByUsersOrgs";

	public int countByKeywords(
			long companyId, long parentOrganizationId,
			String parentOrganizationIdComparator, String keywords,
			String type, Long regionId, Long countryId,
			LinkedHashMap<String, Object> params)
		throws SystemException {

		String[] names = null;
		String[] streets = null;
		String[] cities = null;
		String[] zips = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			names = CustomSQLUtil.keywords(keywords);
			streets = CustomSQLUtil.keywords(keywords);
			cities = CustomSQLUtil.keywords(keywords);
			zips = CustomSQLUtil.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		return countByC_PO_N_T_S_C_Z_R_C(
			companyId, parentOrganizationId, parentOrganizationIdComparator,
			names, type, streets, cities, zips, regionId, countryId, params,
			andOperator);
	}

	public int countByO_U(long organizationId, long userId)
		throws SystemException {

		LinkedHashMap<String, Object> params1 =
			new LinkedHashMap<String, Object>();

		params1.put("usersOrgs", userId);

		Session session = null;

		try {
			session = openSession();

			int count = countByOrganizationId(session, organizationId, params1);

			return count;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public int countByC_PO_N_T_S_C_Z_R_C(
			long companyId, long parentOrganizationId,
			String parentOrganizationIdComparator, String name, String type,
			String street, String city, String zip, Long regionId,
			Long countryId, LinkedHashMap<String, Object> params,
			boolean andOperator)
		throws SystemException {

		String[] names = CustomSQLUtil.keywords(name);
		String[] streets = CustomSQLUtil.keywords(street);
		String[] cities = CustomSQLUtil.keywords(city);
		String[] zips = CustomSQLUtil.keywords(zip);

		return countByC_PO_N_T_S_C_Z_R_C(
			companyId, parentOrganizationId, parentOrganizationIdComparator,
			names, type, streets, cities, zips, regionId, countryId, params,
			andOperator);
	}

	public int countByC_PO_N_T_S_C_Z_R_C(
			long companyId, long parentOrganizationId,
			String parentOrganizationIdComparator, String[] names,
			String type, String[] streets, String[] cities, String[] zips,
			Long regionId, Long countryId, LinkedHashMap<String, Object> params,
			boolean andOperator)
		throws SystemException {

		names = CustomSQLUtil.keywords(names);
		streets = CustomSQLUtil.keywords(streets);
		cities = CustomSQLUtil.keywords(cities);
		zips = CustomSQLUtil.keywords(zips);

		if (params != null) {
			Long resourceId = (Long)params.get("permissionsResourceId");
			Long groupId = (Long)params.get("permissionsGroupId");

			if (Validator.isNotNull(groupId) &&
				Validator.isNotNull(resourceId)) {

				return countByPermissions(
					companyId, parentOrganizationId,
					parentOrganizationIdComparator, names, type, streets,
					cities, zips, regionId, countryId, resourceId.longValue(),
					groupId.longValue(), andOperator);
			}
		}

		Session session = null;

		try {
			session = openSession();

			String sql = null;

			if (Validator.isNotNull(type)) {
				sql = CustomSQLUtil.get(COUNT_BY_C_PO_N_L_S_C_Z_R_C);
			}
			else {
				sql = CustomSQLUtil.get(COUNT_BY_C_PO_N_S_C_Z_R_C);
			}

			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(Organization_.name)", StringPool.LIKE, false,
				names);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(Address.street1)", StringPool.LIKE, true,
				streets);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(Address.street2)", StringPool.LIKE, true,
				streets);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(Address.street3)", StringPool.LIKE, true,
				streets);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(Address.city)", StringPool.LIKE, false,
				cities);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(Address.zip)", StringPool.LIKE, true,
				zips);

			if (regionId == null) {
				sql = StringUtil.replace(sql, REGION_ID_SQL, StringPool.BLANK);
			}

			if (countryId == null) {
				sql = StringUtil.replace(sql, COUNTRY_ID_SQL, StringPool.BLANK);
			}

			sql = StringUtil.replace(sql, "[$JOIN$]", getJoin(params));
			sql = StringUtil.replace(sql, "[$WHERE$]", getWhere(params));
			sql = StringUtil.replace(
				sql, "[$PARENT_ORGANIZATION_ID_COMPARATOR$]",
				parentOrganizationIdComparator);
			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			setJoin(qPos, params);

			qPos.add(companyId);
			qPos.add(parentOrganizationId);

			if (Validator.isNotNull(type)) {
				qPos.add(type);
			}

			qPos.add(names, 2);
			qPos.add(streets, 6);

			if (regionId != null) {
				qPos.add(regionId);
				qPos.add(regionId);
			}

			if (countryId != null) {
				qPos.add(countryId);
				qPos.add(countryId);
			}

			qPos.add(cities, 2);
			qPos.add(zips, 2);

			Iterator<Long> itr = q.iterate();

			if (itr.hasNext()) {
				Long count = itr.next();

				if (count != null) {
					return count.intValue();
				}
			}

			return 0;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<Organization> findByCompanyId(
			long companyId, LinkedHashMap<String, Object> params, int start,
			int end, OrderByComparator obc)
		throws SystemException {

		if (params == null) {
			params = new LinkedHashMap<String, Object>();
		}

		StringBundler sb = new StringBundler();

		sb.append("(");

		String sql = CustomSQLUtil.get(FIND_BY_COMPANY_ID);

		sql = StringUtil.replace(sql, "[$JOIN$]", getJoin(params));
		sql = StringUtil.replace(sql, "[$WHERE$]", getWhere(params));

		sb.append(sql);
		sb.append(")");

		sql = sb.toString();

		sql = CustomSQLUtil.replaceAndOperator(sql, true);
		sql = CustomSQLUtil.replaceOrderBy(sql, obc);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar("orgId", Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			setJoin(qPos, params);

			qPos.add(companyId);

			List<Organization> organizations = new ArrayList<Organization>();

			Iterator<Long> itr = (Iterator<Long>)QueryUtil.iterate(
				q, getDialect(), start, end);

			while (itr.hasNext()) {
				Long organizationId = itr.next();

				Organization organization = OrganizationUtil.findByPrimaryKey(
					organizationId.longValue());

				organizations.add(organization);
			}

			return organizations;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<Organization> findByKeywords(
			long companyId, long parentOrganizationId,
			String parentOrganizationIdComparator, String keywords,
			String type, Long regionId, Long countryId,
			LinkedHashMap<String, Object> params, int start, int end,
			OrderByComparator obc)
		throws SystemException {

		String[] names = null;
		String[] streets = null;
		String[] cities = null;
		String[] zips = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			names = CustomSQLUtil.keywords(keywords);
			streets = CustomSQLUtil.keywords(keywords);
			cities = CustomSQLUtil.keywords(keywords);
			zips = CustomSQLUtil.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		return findByC_PO_N_T_S_C_Z_R_C(
			companyId, parentOrganizationId, parentOrganizationIdComparator,
			names, type, streets, cities, zips, regionId, countryId, params,
			andOperator, start, end, obc);
	}

	public List<Organization> findByC_PO_N_T_S_C_Z_R_C(
			long companyId, long parentOrganizationId,
			String parentOrganizationIdComparator, String name, String type,
			String street, String city, String zip, Long regionId,
			Long countryId, LinkedHashMap<String, Object> params,
			boolean andOperator, int start, int end, OrderByComparator obc)
		throws SystemException {

		String[] names = CustomSQLUtil.keywords(name);
		String[] streets = CustomSQLUtil.keywords(street);
		String[] cities = CustomSQLUtil.keywords(city);
		String[] zips = CustomSQLUtil.keywords(zip);

		return findByC_PO_N_T_S_C_Z_R_C(
			companyId, parentOrganizationId, parentOrganizationIdComparator,
			names, type, streets, cities, zips, regionId, countryId, params,
			andOperator, start, end, obc);
	}

	public List<Organization> findByC_PO_N_T_S_C_Z_R_C(
			long companyId, long parentOrganizationId,
			String parentOrganizationIdComparator, String[] names,
			String type, String[] streets, String[] cities, String[] zips,
			Long regionId, Long countryId, LinkedHashMap<String, Object> params,
			boolean andOperator, int start, int end, OrderByComparator obc)
		throws SystemException {

		names = CustomSQLUtil.keywords(names);
		streets = CustomSQLUtil.keywords(streets);
		cities = CustomSQLUtil.keywords(cities);
		zips = CustomSQLUtil.keywords(zips);

		if (params != null) {
			Long resourceId = (Long)params.get("permissionsResourceId");
			Long groupId = (Long)params.get("permissionsGroupId");

			if (Validator.isNotNull(groupId) &&
				Validator.isNotNull(resourceId)) {

				return findByPermissions(
					companyId, parentOrganizationId,
					parentOrganizationIdComparator, names, type, streets,
					cities, zips, regionId, countryId, resourceId.longValue(),
					groupId.longValue(), andOperator, start, end, obc);
			}
		}
		else {
			params = new LinkedHashMap<String, Object>();
		}

		StringBundler sb = new StringBundler();

		sb.append("(");

		if (Validator.isNotNull(type)) {
			sb.append(CustomSQLUtil.get(FIND_BY_C_PO_N_L_S_C_Z_R_C));
		}
		else {
			sb.append(CustomSQLUtil.get(FIND_BY_C_PO_N_S_C_Z_R_C));
		}

		String sql = sb.toString();

		sql = StringUtil.replace(sql, "[$JOIN$]", getJoin(params));
		sql = StringUtil.replace(sql, "[$WHERE$]", getWhere(params));
		sql = sql.concat(StringPool.CLOSE_PARENTHESIS);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(Organization_.name)", StringPool.LIKE, false,
			names);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(Address.street1)", StringPool.LIKE, true,
			streets);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(Address.street2)", StringPool.LIKE, true,
			streets);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(Address.street3)", StringPool.LIKE, true,
			streets);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(Address.city)", StringPool.LIKE, false,
			cities);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "lower(Address.zip)", StringPool.LIKE, true,
			zips);
		sql = StringUtil.replace(
			sql, "[$PARENT_ORGANIZATION_ID_COMPARATOR$]",
			parentOrganizationIdComparator);

		if (regionId == null) {
			sql = StringUtil.replace(sql, REGION_ID_SQL, StringPool.BLANK);
		}

		if (countryId == null) {
			sql = StringUtil.replace(sql, COUNTRY_ID_SQL, StringPool.BLANK);
		}

		sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);
		sql = CustomSQLUtil.replaceOrderBy(sql, obc);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar("orgId", Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			setJoin(qPos, params);

			qPos.add(companyId);
			qPos.add(parentOrganizationId);

			if (Validator.isNotNull(type)) {
				qPos.add(type);
			}

			qPos.add(names, 2);
			qPos.add(streets, 6);

			if (regionId != null) {
				qPos.add(regionId);
				qPos.add(regionId);
			}

			if (countryId != null) {
				qPos.add(countryId);
				qPos.add(countryId);
			}

			qPos.add(cities, 2);
			qPos.add(zips, 2);

			List<Organization> organizations = new ArrayList<Organization>();

			Iterator<Long> itr = (Iterator<Long>)QueryUtil.iterate(
				q, getDialect(), start, end);

			while (itr.hasNext()) {
				Long organizationId = itr.next();

				Organization organization = OrganizationUtil.findByPrimaryKey(
					organizationId.longValue());

				organizations.add(organization);
			}

			return organizations;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected int countByOrganizationId(
		Session session, long organizationId,
		LinkedHashMap<String, Object> params) {

		String sql = CustomSQLUtil.get(COUNT_BY_ORGANIZATION_ID);

		sql = StringUtil.replace(sql, "[$JOIN$]", getJoin(params));
		sql = StringUtil.replace(sql, "[$WHERE$]", getWhere(params));

		SQLQuery q = session.createSQLQuery(sql);

		q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

		QueryPos qPos = QueryPos.getInstance(q);

		setJoin(qPos, params);

		qPos.add(organizationId);

		Iterator<Long> itr = q.iterate();

		if (itr.hasNext()) {
			Long count = itr.next();

			if (count != null) {
				return count.intValue();
			}
		}

		return 0;
	}

	protected int countByPermissions(
			long companyId, long parentOrganizationId,
			String parentOrganizationIdComparator, String[] names,
			String type, String[] streets, String[] cities, String[] zips,
			Long regionId, Long countryId, long resourceId, long groupId,
			boolean andOperator)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			StringBundler sb = new StringBundler();

			sb.append("(");

			if (Validator.isNotNull(type)) {
				sb.append(CustomSQLUtil.get(COUNT_BY_C_PO_N_L_S_C_Z_R_C));
			}
			else {
				sb.append(CustomSQLUtil.get(COUNT_BY_C_PO_N_S_C_Z_R_C));
			}

			String sql = sb.toString();

			if (regionId == null) {
				sql = StringUtil.replace(sql, REGION_ID_SQL, StringPool.BLANK);
			}

			if (countryId == null) {
				sql = StringUtil.replace(sql, COUNTRY_ID_SQL, StringPool.BLANK);
			}

			sql = StringUtil.replace(
				sql, "[$JOIN$]", getJoin("groupsPermissions"));
			sql = StringUtil.replace(
				sql, "[$WHERE$]", getWhere("groupsPermissions"));

			sb.setIndex(0);

			sb.append(sql);
			sb.append(") UNION (");

			if (Validator.isNotNull(type)) {
				sb.append(CustomSQLUtil.get(COUNT_BY_C_PO_N_L_S_C_Z_R_C));
			}
			else {
				sb.append(CustomSQLUtil.get(COUNT_BY_C_PO_N_S_C_Z_R_C));
			}

			sql = sb.toString();

			if (regionId == null) {
				sql = StringUtil.replace(sql, REGION_ID_SQL, StringPool.BLANK);
			}

			if (countryId == null) {
				sql = StringUtil.replace(sql, COUNTRY_ID_SQL, StringPool.BLANK);
			}

			sql = StringUtil.replace(
				sql, "[$JOIN$]", getJoin("orgGroupPermission"));
			sql = StringUtil.replace(
				sql, "[$WHERE$]", getWhere("orgGroupPermission"));
			sql = StringUtil.replace(
				sql, "[$PARENT_ORGANIZATION_ID_COMPARATOR$]",
				parentOrganizationIdComparator);
			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

			sb.setIndex(0);

			sb.append(sql);
			sb.append(")");

			sql = sb.toString();

			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(Organization_.name)", StringPool.LIKE, false,
				names);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(Address.street1)", StringPool.LIKE, true,
				streets);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(Address.street2)", StringPool.LIKE, true,
				streets);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(Address.street3)", StringPool.LIKE, true,
				streets);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(Address.city)", StringPool.LIKE, false,
				cities);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(Address.zip)", StringPool.LIKE, true,
				zips);

			if (regionId == null) {
				sql = StringUtil.replace(sql, REGION_ID_SQL, StringPool.BLANK);
			}

			if (countryId == null) {
				sql = StringUtil.replace(sql, COUNTRY_ID_SQL, StringPool.BLANK);
			}

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			for (int i = 0; i < 2; i++) {
				qPos.add(resourceId);

				if (i == 1) {
					qPos.add(groupId);
				}

				qPos.add(companyId);
				qPos.add(parentOrganizationId);

				if (Validator.isNotNull(type)) {
					qPos.add(type);
				}

				qPos.add(names, 2);
				qPos.add(streets, 6);

				if (regionId != null) {
					qPos.add(regionId);
					qPos.add(regionId);
				}

				if (countryId != null) {
					qPos.add(countryId);
					qPos.add(countryId);
				}

				qPos.add(cities, 2);
				qPos.add(zips, 2);
			}

			int count = 0;

			Iterator<Long> itr = q.iterate();

			while (itr.hasNext()) {
				Long l = itr.next();

				if (l != null) {
					count += l.intValue();
				}
			}

			return count;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected List<Organization> findByPermissions(
			long companyId, long parentOrganizationId,
			String parentOrganizationIdComparator, String[] names,
			String type, String[] streets, String[] cities, String[] zips,
			Long regionId, Long countryId, long resourceId, long groupId,
			boolean andOperator, int start, int end, OrderByComparator obc)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			StringBundler sb = new StringBundler();

			sb.append("(");

			if (Validator.isNotNull(type)) {
				sb.append(CustomSQLUtil.get(FIND_BY_C_PO_N_L_S_C_Z_R_C));
			}
			else {
				sb.append(CustomSQLUtil.get(FIND_BY_C_PO_N_S_C_Z_R_C));
			}

			String sql = sb.toString();

			if (regionId == null) {
				sql = StringUtil.replace(sql, REGION_ID_SQL, StringPool.BLANK);
			}

			if (countryId == null) {
				sql = StringUtil.replace(sql, COUNTRY_ID_SQL, StringPool.BLANK);
			}

			sql = StringUtil.replace(
				sql, "[$JOIN$]", getJoin("groupsPermissions"));
			sql = StringUtil.replace(
				sql, "[$WHERE$]", getWhere("groupsPermissions"));

			sb.setIndex(0);

			sb.append(sql);
			sb.append(") UNION (");

			if (Validator.isNotNull(type)) {
				sb.append(CustomSQLUtil.get(FIND_BY_C_PO_N_L_S_C_Z_R_C));
			}
			else {
				sb.append(CustomSQLUtil.get(FIND_BY_C_PO_N_S_C_Z_R_C));
			}

			sql = sb.toString();

			if (regionId == null) {
				sql = StringUtil.replace(sql, REGION_ID_SQL, StringPool.BLANK);
			}

			if (countryId == null) {
				sql = StringUtil.replace(sql, COUNTRY_ID_SQL, StringPool.BLANK);
			}

			sql = StringUtil.replace(
				sql, "[$JOIN$]", getJoin("orgGroupPermission"));
			sql = StringUtil.replace(
				sql, "[$WHERE$]", getWhere("orgGroupPermission"));
			sql = StringUtil.replace(
				sql, "[$PARENT_ORGANIZATION_ID_COMPARATOR$]",
				parentOrganizationIdComparator);
			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

			sb.setIndex(0);

			sb.append(sql);

			sb.append(") ");

			sql = sb.toString();

			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(Organization_.name)", StringPool.LIKE, false,
				names);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(Address.street1)", StringPool.LIKE, true,
				streets);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(Address.street2)", StringPool.LIKE, true,
				streets);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(Address.street3)", StringPool.LIKE, true,
				streets);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(Address.city)", StringPool.LIKE, false,
				cities);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(Address.zip)", StringPool.LIKE, true,
				zips);

			if (regionId == null) {
				sql = StringUtil.replace(sql, REGION_ID_SQL, StringPool.BLANK);
			}

			if (countryId == null) {
				sql = StringUtil.replace(sql, COUNTRY_ID_SQL, StringPool.BLANK);
			}

			sql = CustomSQLUtil.replaceOrderBy(sql, obc);

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar("orgId", Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			for (int i = 0; i < 2; i++) {
				qPos.add(resourceId);

				if (i == 1) {
					qPos.add(groupId);
				}

				qPos.add(companyId);
				qPos.add(parentOrganizationId);

				if (Validator.isNotNull(type)) {
					qPos.add(type);
				}

				qPos.add(names, 2);
				qPos.add(streets, 6);

				if (regionId != null) {
					qPos.add(regionId);
					qPos.add(regionId);
				}

				if (countryId != null) {
					qPos.add(countryId);
					qPos.add(countryId);
				}

				qPos.add(cities, 2);
				qPos.add(zips, 2);
			}

			List<Organization> organizations = new ArrayList<Organization>();

			Iterator<Long> itr = (Iterator<Long>)QueryUtil.iterate(
				q, getDialect(), start, end);

			while (itr.hasNext()) {
				Long organizationId = itr.next();

				Organization organization = OrganizationUtil.findByPrimaryKey(
					organizationId.longValue());

				organizations.add(organization);
			}

			return organizations;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected String getJoin(LinkedHashMap<String, Object> params) {
		if ((params == null) || params.isEmpty()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(params.size());

		Iterator<Map.Entry<String, Object>> itr = params.entrySet().iterator();

		while (itr.hasNext()) {
			Map.Entry<String, Object> entry = itr.next();

			String key = entry.getKey();

			if (key.equals("expandoAttributes")) {
				continue;
			}

			Object value = entry.getValue();

			if (Validator.isNotNull(value)) {
				sb.append(getJoin(key));
			}
		}

		return sb.toString();
	}

	protected String getJoin(String key) {
		String join = StringPool.BLANK;

		if (key.equals("groupsPermissions")) {
			join = CustomSQLUtil.get(JOIN_BY_GROUPS_PERMISSIONS);
		}
		else if (key.equals("organizationsGroups")) {
			join = CustomSQLUtil.get(JOIN_BY_ORGANIZATIONS_GROUPS);
		}
		else if (key.equals("organizationsPasswordPolicies")) {
			join = CustomSQLUtil.get(JOIN_BY_ORGANIZATIONS_PASSWORD_POLICIES);
		}
		else if (key.equals("organizationsRoles")) {
			join = CustomSQLUtil.get(JOIN_BY_ORGANIZATIONS_ROLES);
		}
		else if (key.equals("organizationsUsers")) {
			join = CustomSQLUtil.get(JOIN_BY_ORGANIZATIONS_USERS);
		}
		else if (key.equals("orgGroupPermission")) {
			join = CustomSQLUtil.get(JOIN_BY_ORG_GROUP_PERMISSION);
		}
		else if (key.equals("usersOrgs")) {
			join = CustomSQLUtil.get(JOIN_BY_USERS_ORGS);
		}

		if (Validator.isNotNull(join)) {
			int pos = join.indexOf("WHERE");

			if (pos != -1) {
				join = join.substring(0, pos);
			}
		}

		return join;
	}

	protected String getWhere(LinkedHashMap<String, Object> params) {
		if ((params == null) || params.isEmpty()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(params.size());

		Iterator<Map.Entry<String, Object>> itr = params.entrySet().iterator();

		while (itr.hasNext()) {
			Map.Entry<String, Object> entry = itr.next();

			String key = entry.getKey();

			if (key.equals("expandoAttributes")) {
				continue;
			}

			Object value = entry.getValue();

			if (Validator.isNotNull(value)) {
				sb.append(getWhere(key, value));
			}
		}

		return sb.toString();
	}

	protected String getWhere(String key) {
		return getWhere(key, null);
	}

	protected String getWhere(String key, Object value) {
		String join = StringPool.BLANK;

		if (key.equals("groupsPermissions")) {
			join = CustomSQLUtil.get(JOIN_BY_GROUPS_PERMISSIONS);
		}
		else if (key.equals("organizations")) {
			Long[] organizationIds = (Long[])value;

			if (organizationIds.length == 0) {
				join = "WHERE ((Organization_.organizationId = -1) )";
			}
			else {
				StringBundler sb = new StringBundler(
					organizationIds.length * 2 + 1);

				sb.append("WHERE (");

				for (int i = 0; i < organizationIds.length; i++) {
					sb.append("(Organization_.organizationId = ?) ");

					if ((i + 1) < organizationIds.length) {
						sb.append("OR ");
					}
				}

				sb.append(")");

				join = sb.toString();
			}
		}
		else if (key.equals("organizationsGroups")) {
			join = CustomSQLUtil.get(JOIN_BY_ORGANIZATIONS_GROUPS);
		}
		else if (key.equals("organizationsPasswordPolicies")) {
			join = CustomSQLUtil.get(JOIN_BY_ORGANIZATIONS_PASSWORD_POLICIES);
		}
		else if (key.equals("organizationsRoles")) {
			join = CustomSQLUtil.get(JOIN_BY_ORGANIZATIONS_ROLES);
		}
		else if (key.equals("organizationsTree")) {
			List<Organization> organizationsTree = (List<Organization>)value;

			int size = organizationsTree.size();

			if (!organizationsTree.isEmpty()) {
				StringBundler sb = new StringBundler(size * 2 + 1);

				sb.append("WHERE (");

				for (int i = 0; i < size; i++) {
					sb.append("(Organization_.treePath LIKE ?) ");

					if ((i + 1) < size) {
						sb.append("OR ");
					}
				}

				sb.append(")");

				join = sb.toString();
			}
		}
		else if (key.equals("organizationsUsers")) {
			join = CustomSQLUtil.get(JOIN_BY_ORGANIZATIONS_USERS);
		}
		else if (key.equals("orgGroupPermission")) {
			join = CustomSQLUtil.get(JOIN_BY_ORG_GROUP_PERMISSION);
		}
		else if (key.equals("usersOrgs")) {
			join = CustomSQLUtil.get(JOIN_BY_USERS_ORGS);
		}

		if (Validator.isNotNull(join)) {
			int pos = join.indexOf("WHERE");

			if (pos != -1) {
				join = join.substring(pos + 5, join.length()).concat(" AND ");
			}
			else {
				join = StringPool.BLANK;
			}
		}

		return join;
	}

	protected void setJoin(
		QueryPos qPos, LinkedHashMap<String, Object> params) {

		if (params == null) {
			return;
		}

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			String key = entry.getKey();

			if (key.equals("expandoAttributes")) {
				continue;
			}

			Object value = entry.getValue();

			if (key.equals("organizationsTree")) {
				List<Organization> organizationsTree =
					(List<Organization>)value;

				if (!organizationsTree.isEmpty()) {
					for (Organization organization : organizationsTree) {
						StringBundler sb = new StringBundler(5);

						sb.append(StringPool.PERCENT);
						sb.append(StringPool.SLASH);
						sb.append(organization.getOrganizationId());
						sb.append(StringPool.SLASH);
						sb.append(StringPool.PERCENT);

						qPos.add(sb.toString());
					}
				}
			}
			else if (value instanceof Long) {
				Long valueLong = (Long)value;

				if (Validator.isNotNull(valueLong)) {
					qPos.add(valueLong);
				}
			}
			else if (value instanceof Long[]) {
				Long[] valueArray = (Long[])value;

				for (Long element : valueArray) {
					if (Validator.isNotNull(element)) {
						qPos.add(element);
					}
				}
			}
			else if (value instanceof Long[][]) {
				Long[][] valueDoubleArray = (Long[][])value;

				for (Long[] valueArray : valueDoubleArray) {
					for (Long valueLong : valueArray) {
						qPos.add(valueLong);
					}
				}
			}
			else if (value instanceof String) {
				String valueString = (String)value;

				if (Validator.isNotNull(valueString)) {
					qPos.add(valueString);
				}
			}
		}
	}

	protected static String COUNTRY_ID_SQL =
		"((Organization_.countryId = ?) OR (Address.countryId = ?)) " +
			"[$AND_OR_CONNECTOR$]";

	protected static String REGION_ID_SQL =
		"((Organization_.regionId = ?) OR (Address.regionId = ?)) " +
			"[$AND_OR_CONNECTOR$]";

}