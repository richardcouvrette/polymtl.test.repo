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

package com.liferay.portal.service.impl;

import com.liferay.portal.DuplicateOrganizationException;
import com.liferay.portal.OrganizationNameException;
import com.liferay.portal.OrganizationParentException;
import com.liferay.portal.OrganizationTypeException;
import com.liferay.portal.RequiredOrganizationException;
import com.liferay.portal.kernel.cache.ThreadLocalCachable;
import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.model.ListTypeConstants;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.OrganizationConstants;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.model.impl.OrganizationImpl;
import com.liferay.portal.security.permission.PermissionCacheUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.base.OrganizationLocalServiceBaseImpl;
import com.liferay.portal.util.PropsUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.portal.util.comparator.OrganizationNameComparator;
import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * The implementation of the organization local service.
 *
 * @author Brian Wing Shun Chan
 * @author Jorge Ferrer
 * @author Julio Camarero
 * @author Hugo Huijser
 * @author Juan Fernández
 */
public class OrganizationLocalServiceImpl
	extends OrganizationLocalServiceBaseImpl {

	/**
	 * Adds the organizations to the group.
	 *
	 * @param  groupId the primary key of the group
	 * @param  organizationIds the primary keys of the organizations
	 * @throws PortalException if a group or organization with the primary key
	 *         could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void addGroupOrganizations(long groupId, long[] organizationIds)
		throws PortalException, SystemException {

		groupPersistence.addOrganizations(groupId, organizationIds);

		Indexer indexer = IndexerRegistryUtil.getIndexer(Organization.class);

		indexer.reindex(organizationIds);

		PermissionCacheUtil.clearCache();
	}

	/**
	 * Adds an organization.
	 *
	 * <p>
	 * This method handles the creation and bookkeeping of the organization
	 * including its resources, metadata, and internal data structures. It is
	 * not necessary to make a subsequent call to {@link
	 * #addOrganizationResources(long, Organization)}.
	 * </p>
	 *
	 * @param  userId the primary key of the creator/owner of the organization
	 * @param  parentOrganizationId the primary key of the organization's parent
	 *         organization
	 * @param  name the organization's name
	 * @param  type the organization's type
	 * @param  recursable whether the permissions of the organization are to be
	 *         inherited by its sub-organizations
	 * @param  regionId the primary key of the organization's region
	 * @param  countryId the primary key of the organization's country
	 * @param  statusId the organization's workflow status
	 * @param  comments the comments about the organization
	 * @param  site whether the organization is to be associated with a main
	 *         site
	 * @param  serviceContext the organization's service context (optionally
	 *         <code>null</code>). Can set asset category IDs, asset tag names,
	 *         and expando bridge attributes for the organization.
	 * @return the organization
	 * @throws PortalException if a creator or parent organization with the
	 *         primary key could not be found or if the organization's
	 *         information was invalid
	 * @throws SystemException if a system exception occurred
	 */
	public Organization addOrganization(
			long userId, long parentOrganizationId, String name, String type,
			boolean recursable, long regionId, long countryId, int statusId,
			String comments, boolean site, ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Organization

		User user = userPersistence.findByPrimaryKey(userId);
		parentOrganizationId = getParentOrganizationId(
			user.getCompanyId(), parentOrganizationId);
		recursable = true;

		validate(
			user.getCompanyId(), parentOrganizationId, name, type, countryId,
			statusId);

		long organizationId = counterLocalService.increment();

		Organization organization = organizationPersistence.create(
			organizationId);

		organization.setCompanyId(user.getCompanyId());
		organization.setParentOrganizationId(parentOrganizationId);

		String treePath = organization.buildTreePath();

		organization.setTreePath(treePath);

		organization.setName(name);
		organization.setType(type);
		organization.setRecursable(recursable);
		organization.setRegionId(regionId);
		organization.setCountryId(countryId);
		organization.setStatusId(statusId);
		organization.setComments(comments);

		organizationPersistence.update(organization, false);

		// Group

		Group group = groupLocalService.addGroup(
			userId, Organization.class.getName(), organizationId, name, null,
			GroupConstants.TYPE_SITE_PRIVATE, null, site, true, null);

		if (PropsValues.ORGANIZATIONS_ASSIGNMENT_AUTO) {

			// Role

			Role role = roleLocalService.getRole(
				organization.getCompanyId(), RoleConstants.ORGANIZATION_OWNER);

			userGroupRoleLocalService.addUserGroupRoles(
				userId, group.getGroupId(), new long[] {role.getRoleId()});

			// User

			userPersistence.addOrganization(userId, organizationId);
		}

		// Resources

		addOrganizationResources(userId, organization);

		// Asset

		if (serviceContext != null) {
			updateAsset(
				userId, organization, serviceContext.getAssetCategoryIds(),
				serviceContext.getAssetTagNames());
		}

		// Expando

		ExpandoBridge expandoBridge = organization.getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);

		// Indexer

		if (serviceContext.isIndexingEnabled()) {
			Indexer indexer = IndexerRegistryUtil.getIndexer(
				Organization.class);

			indexer.reindex(organization);
		}

		return organization;
	}

	/**
	 * Adds a resource for each type of permission available on the
	 * organization.
	 *
	 * @param  userId the primary key of the creator/owner of the organization
	 * @param  organization the organization
	 * @throws PortalException if a portal exception occurred
	 * @throws SystemException if a system exception occurred
	 */
	public void addOrganizationResources(long userId, Organization organization)
		throws PortalException, SystemException {

		String name = Organization.class.getName();

		resourceLocalService.addResources(
			organization.getCompanyId(), 0, userId, name,
			organization.getOrganizationId(), false, false, false);
	}

	/**
	 * Assigns the password policy to the organizations, removing any other
	 * currently assigned password policies.
	 *
	 * @param  passwordPolicyId the primary key of the password policy
	 * @param  organizationIds the primary keys of the organizations
	 * @throws SystemException if a system exception occurred
	 */
	public void addPasswordPolicyOrganizations(
			long passwordPolicyId, long[] organizationIds)
		throws SystemException {

		passwordPolicyRelLocalService.addPasswordPolicyRels(
			passwordPolicyId, Organization.class.getName(), organizationIds);
	}

	/**
	 * Deletes the logo of the organization.
	 *
	 * @param  organizationId the primary key of the organization
	 * @throws PortalException if an organization or parent organization with
	 *         the primary key could not be found or if the organization's logo
	 *         could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteLogo(long organizationId)
		throws PortalException, SystemException {

		Organization organization = getOrganization(organizationId);

		Group group = organization.getGroup();

		LayoutSet publicLayoutSet = layoutSetLocalService.getLayoutSet(
			group.getGroupId(), false);

		if (publicLayoutSet.isLogo()) {
			long logoId = publicLayoutSet.getLogoId();

			publicLayoutSet.setLogo(false);
			publicLayoutSet.setLogoId(0);

			layoutSetPersistence.update(publicLayoutSet, false);

			imageLocalService.deleteImage(logoId);
		}

		LayoutSet privateLayoutSet = layoutSetLocalService.getLayoutSet(
			group.getGroupId(), true);

		if (privateLayoutSet.isLogo()) {
			long logoId = privateLayoutSet.getLogoId();

			privateLayoutSet.setLogo(false);
			privateLayoutSet.setLogoId(0);

			layoutSetPersistence.update(privateLayoutSet, false);

			if (imageLocalService.getImage(logoId) != null) {
				imageLocalService.deleteImage(logoId);
			}
		}
	}

	/**
	 * Deletes the organization. The organization's associated resources and
	 * assets are also deleted.
	 *
	 * @param  organizationId the primary key of the organization
	 * @throws PortalException if an organization with the primary key could not
	 *         be found, if the organization had a workflow in approved status,
	 *         or if the organization was a parent organization
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void deleteOrganization(long organizationId)
		throws PortalException, SystemException {

		Organization organization = organizationPersistence.findByPrimaryKey(
			organizationId);

		deleteOrganization(organization);
	}

	/**
	 * Deletes the organization. The organization's associated resources and
	 * assets are also deleted.
	 *
	 * @param  organization the organization
	 * @throws PortalException if the organization had a workflow in approved
	 *         status or if the organization was a parent organization
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void deleteOrganization(Organization organization)
		throws PortalException, SystemException {

		if ((userLocalService.getOrganizationUsersCount(
				organization.getOrganizationId(),
				WorkflowConstants.STATUS_APPROVED) > 0) ||
			(organizationPersistence.countByC_P(
				organization.getCompanyId(),
				organization.getOrganizationId()) > 0)) {

			throw new RequiredOrganizationException();
		}

		// Asset

		assetEntryLocalService.deleteEntry(
			Organization.class.getName(), organization.getOrganizationId());

		// Addresses

		addressLocalService.deleteAddresses(
			organization.getCompanyId(), Organization.class.getName(),
			organization.getOrganizationId());

		// Email addresses

		emailAddressLocalService.deleteEmailAddresses(
			organization.getCompanyId(), Organization.class.getName(),
			organization.getOrganizationId());

		// Expando

		expandoValueLocalService.deleteValues(
			Organization.class.getName(), organization.getOrganizationId());

		// Password policy relation

		passwordPolicyRelLocalService.deletePasswordPolicyRel(
			Organization.class.getName(), organization.getOrganizationId());

		// Phone

		phoneLocalService.deletePhones(
			organization.getCompanyId(), Organization.class.getName(),
			organization.getOrganizationId());

		// Website

		websiteLocalService.deleteWebsites(
			organization.getCompanyId(), Organization.class.getName(),
			organization.getOrganizationId());

		// Group

		Group group = organization.getGroup();

		if (group.isSite()) {
			group.setSite(false);

			groupPersistence.update(group, false);
		}

		groupLocalService.deleteGroup(group);

		// Resources

		String name = Organization.class.getName();

		resourceLocalService.deleteResource(
			organization.getCompanyId(), name,
			ResourceConstants.SCOPE_INDIVIDUAL,
			organization.getOrganizationId());

		// Organization

		organizationPersistence.remove(organization);

		// Permission cache

		PermissionCacheUtil.clearCache();

		// Indexer

		Indexer indexer = IndexerRegistryUtil.getIndexer(Organization.class);

		indexer.delete(organization);
	}

	/**
	 * Returns all the organizations belonging to the group.
	 *
	 * @param  groupId the primary key of the group
	 * @return the organizations belonging to the group
	 * @throws SystemException if a system exception occurred
	 */
	public List<Organization> getGroupOrganizations(long groupId)
		throws SystemException {

		return groupPersistence.getOrganizations(groupId);
	}

	/**
	 * Returns the organization with the primary key.
	 *
	 * @param  organizationId the primary key of the organization
	 * @return the organization with the primary key
	 * @throws PortalException if an organization with the primary key could not
	 *         be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Organization getOrganization(long organizationId)
		throws PortalException, SystemException {

		return organizationPersistence.findByPrimaryKey(organizationId);
	}

	/**
	 * Returns the organization with the name.
	 *
	 * @param  companyId the primary key of the organization's company
	 * @param  name the organization's name
	 * @return the organization with the name
	 * @throws PortalException if the organization with the name could not be
	 *         found
	 * @throws SystemException if a system exception occurred
	 */
	public Organization getOrganization(long companyId, String name)
		throws PortalException, SystemException {

		return organizationPersistence.findByC_N(companyId, name);
	}

	/**
	 * Returns the primary key of the organization with the name.
	 *
	 * @param  companyId the primary key of the organization's company
	 * @param  name the organization's name
	 * @return the primary key of the organization with the name, or
	 *         <code>0</code> if the organization could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public long getOrganizationId(long companyId, String name)
		throws SystemException {

		Organization organization = organizationPersistence.fetchByC_N(
			companyId, name);

		if (organization != null) {
			return organization.getOrganizationId();
		}
		else {
			return 0;
		}
	}

	/**
	 * Returns all the organizations belonging to the parent organization.
	 *
	 * @param  companyId the primary key of the organization's company
	 * @param  parentOrganizationId the primary key of the organization's parent
	 *         organization
	 * @return the organizations belonging to the parent organization
	 * @throws SystemException if a system exception occurred
	 */
	public List<Organization> getOrganizations(
			long companyId, long parentOrganizationId)
		throws SystemException {

		if (parentOrganizationId ==
				OrganizationConstants.ANY_PARENT_ORGANIZATION_ID) {

			return organizationPersistence.findByCompanyId(companyId);
		}
		else {
			return organizationPersistence.findByC_P(
				companyId, parentOrganizationId);
		}
	}

	/**
	 * Returns a range of all the organizations belonging to the parent
	 * organization.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end -
	 * start</code> instances. <code>start</code> and <code>end</code> are not
	 * primary keys, they are indexes in the result set. Thus, <code>0</code>
	 * refers to the first result in the set. Setting both <code>start</code>
	 * and <code>end</code> to {@link
	 * com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	 * result set.
	 * </p>
	 *
	 * @param  companyId the primary key of the organization's company
	 * @param  parentOrganizationId the primary key of the organization's parent
	 *         organization
	 * @param  start the lower bound of the range of organizations to return
	 * @param  end the upper bound of the range of organizations to return (not
	 *         inclusive)
	 * @return the range of organizations belonging to the parent organization
	 * @throws SystemException if a system exception occurred
	 * @see    com.liferay.portal.service.persistence.OrganizationPersistence#findByC_P(
	 *         long, long, int, int)
	 */
	public List<Organization> getOrganizations(
			long companyId, long parentOrganizationId, int start, int end)
		throws SystemException {

		return organizationPersistence.findByC_P(
			companyId, parentOrganizationId, start, end);
	}

	/**
	 * Returns the organizations with the primary keys.
	 *
	 * @param  organizationIds the primary keys of the organizations
	 * @return the organizations with the primary keys
	 * @throws PortalException if any one of the organizations could not be
	 *         found
	 * @throws SystemException if a system exception occurred
	 */
	public List<Organization> getOrganizations(long[] organizationIds)
		throws PortalException, SystemException {

		List<Organization> organizations = new ArrayList<Organization>(
			organizationIds.length);

		for (long organizationId : organizationIds) {
			Organization organization = getOrganization(organizationId);

			organizations.add(organization);
		}

		return organizations;
	}

	/**
	 * Returns the number of organizations belonging to the parent organization.
	 *
	 * @param  companyId the primary key of the organization's company
	 * @param  parentOrganizationId the primary key of the organization's parent
	 *         organization
	 * @return the number of organizations belonging to the parent organization
	 * @throws SystemException if a system exception occurred
	 */
	public int getOrganizationsCount(long companyId, long parentOrganizationId)
		throws SystemException {

		if (parentOrganizationId ==
				OrganizationConstants.ANY_PARENT_ORGANIZATION_ID) {

			return organizationPersistence.countByCompanyId(companyId);
		}
		else {
			return organizationPersistence.countByC_P(
				companyId, parentOrganizationId);
		}
	}

	/**
	 * Returns the parent organizations in order by closest ancestor. The list
	 * starts with the organization itself.
	 *
	 * @param  organizationId the primary key of the organization
	 * @return the parent organizations in order by closest ancestor
	 * @throws PortalException if an organization with the primary key could not
	 *         be found
	 * @throws SystemException if a system exception occurred
	 */
	public List<Organization> getParentOrganizations(long organizationId)
		throws PortalException, SystemException {

		if (organizationId ==
				OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID) {

			return new ArrayList<Organization>();
		}

		Organization organization =
			organizationPersistence.findByPrimaryKey(organizationId);

		return getParentOrganizations(organization, true);
	}

	/**
	 * Returns the sub-organizations of the organizations.
	 *
	 * @param  organizations the organizations from which to get
	 *         sub-organizations
	 * @return the sub-organizations of the organizations
	 * @throws SystemException if a system exception occurred
	 */
	public List<Organization> getSuborganizations(
			List<Organization> organizations)
		throws SystemException {

		List<Organization> allSuborganizations = new ArrayList<Organization>();

		for (int i = 0; i < organizations.size(); i++) {
			Organization organization = organizations.get(i);

			List<Organization> suborganizations =
				organizationPersistence.findByC_P(
					organization.getCompanyId(),
					organization.getOrganizationId());

			addSuborganizations(allSuborganizations, suborganizations);
		}

		return allSuborganizations;
	}

	/**
	 * Returns the intersection of <code>allOrganizations</code> and
	 * <code>availableOrganizations</code>.
	 *
	 * @param  allOrganizations the organizations to check for availability
	 * @param  availableOrganizations the available organizations
	 * @return the intersection of <code>allOrganizations</code> and
	 *         <code>availableOrganizations</code>
	 */
	public List<Organization> getSubsetOrganizations(
		List<Organization> allOrganizations,
		List<Organization> availableOrganizations) {

		List<Organization> subsetOrganizations = new ArrayList<Organization>();

		Iterator<Organization> itr = allOrganizations.iterator();

		while (itr.hasNext()) {
			Organization organization = itr.next();

			if (availableOrganizations.contains(organization)) {
				subsetOrganizations.add(organization);
			}
		}

		return subsetOrganizations;
	}

	/**
	 * Returns all the organizations associated with the user.
	 *
	 * @param  userId the primary key of the user
	 * @return the organizations associated with the user
	 * @throws PortalException if a user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public List<Organization> getUserOrganizations(long userId)
		throws PortalException, SystemException {

		return getUserOrganizations(
			userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the organizations associated with the user.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end -
	 * start</code> instances. <code>start</code> and <code>end</code> are not
	 * primary keys, they are indexes in the result set. Thus, <code>0</code>
	 * refers to the first result in the set. Setting both <code>start</code>
	 * and <code>end</code> to {@link
	 * com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	 * result set.
	 * </p>
	 *
	 * @param  userId the primary key of the user
	 * @param  start the lower bound of the range of organizations to return
	 * @param  end the upper bound of the range of organizations to return (not
	 *         inclusive)
	 * @return the range organizations associated with the user
	 * @throws PortalException if a user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public List<Organization> getUserOrganizations(
			long userId, int start, int end)
		throws PortalException, SystemException {

		return userPersistence.getOrganizations(userId, start, end);
	}

	/**
	 * Returns the number of organizations associated with the user.
	 *
	 * @param  userId the primary key of the user
	 * @return the number of organizations associated with the user
	 * @throws SystemException if a system exception occurred
	 */
	@ThreadLocalCachable
	public int getUserOrganizationsCount(long userId) throws SystemException {
		return userPersistence.getOrganizationsSize(userId);
	}

	/**
	 * Returns <code>true</code> if the organization belongs to the group.
	 *
	 * @param  groupId the primary key of the group
	 * @param  organizationId the primary key of the organization
	 * @return <code>true</code> if the organization belongs to the group;
	 *         <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean hasGroupOrganization(long groupId, long organizationId)
		throws SystemException {

		return groupPersistence.containsOrganization(groupId, organizationId);
	}

	/**
	 * Returns <code>true</code> if the password policy has been assigned to the
	 * organization.
	 *
	 * @param  passwordPolicyId the primary key of the password policy
	 * @param  organizationId the primary key of the organization
	 * @return <code>true</code> if the password policy has been assigned to the
	 *         organization; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean hasPasswordPolicyOrganization(
			long passwordPolicyId, long organizationId)
		throws SystemException {

		return passwordPolicyRelLocalService.hasPasswordPolicyRel(
			passwordPolicyId, Organization.class.getName(), organizationId);
	}

	/**
	 * Returns <code>true</code> if the user is a member of the organization.
	 * This method is usually called to determine if the user has view access to
	 * a resource belonging to the organization.
	 *
	 * @param  userId the primary key of the user
	 * @param  organizationId the primary key of the organization
	 * @return <code>true</code> if the user has access to the organization;
	 *         <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean hasUserOrganization(long userId, long organizationId)
		throws SystemException {

		return userPersistence.containsOrganization(userId, organizationId);
	}

	/**
	 * Returns <code>true</code> if the user is a member of the organization,
	 * optionally focusing on sub-organizations or the specified organization.
	 * This method is usually called to determine if the user has view access to
	 * a resource belonging to the organization.
	 *
	 * <p>
	 *
	 * <ol>
	 * <li>
	 * If <code>inheritSuborganizations=<code>false</code></code>:
	 * the method checks whether the user belongs to the organization specified
	 * by <code>organizationId</code>. The parameter
	 * <code>includeSpecifiedOrganization</code> is ignored.
	 * </li>
	 * <li>
	 * The parameter <code>includeSpecifiedOrganization</code> is
	 * ignored unless <code>inheritSuborganizations</code> is also
	 * <code>true</code>.
	 * </li>
	 * <li>
	 * If <code>inheritSuborganizations=<code>true</code></code> and
	 * <code>includeSpecifiedOrganization=<code>false</code></code>: the method
	 * checks
	 * whether the user belongs to one of the child organizations of the one
	 * specified by <code>organizationId</code>.
	 * </li>
	 * <li>
	 * If <code>inheritSuborganizations=<code>true</code></code> and
	 * <code>includeSpecifiedOrganization=<code>true</code></code>: the method
	 * checks whether
	 * the user belongs to the organization specified by
	 * <code>organizationId</code> or any of
	 * its child organizations.
	 * </li>
	 * </ol>
	 *
	 * <p>
	 *
	 * @param  userId the primary key of the organization's user
	 * @param  organizationId the primary key of the organization
	 * @param  inheritSuborganizations if <code>true</code> sub-organizations
	 *         are considered in the determination
	 * @param  includeSpecifiedOrganization if <code>true</code> the
	 *         organization specified by <code>organizationId</code> is
	 *         considered in the determination
	 * @return <code>true</code> if the user has access to the organization;
	 *         <code>false</code> otherwise
	 * @throws PortalException if an organization with the primary key could not
	 *         be found
	 * @throws SystemException if a system exception occurred
	 * @see    com.liferay.portal.service.persistence.OrganizationFinder
	 */
	public boolean hasUserOrganization(
			long userId, long organizationId, boolean inheritSuborganizations,
			boolean includeSpecifiedOrganization)
		throws PortalException, SystemException {

		if (!inheritSuborganizations) {
			return userPersistence.containsOrganization(userId, organizationId);
		}

		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		List<Organization> organizationsTree = new ArrayList<Organization>();

		Organization organization =
			organizationPersistence.findByPrimaryKey(organizationId);

		if (!includeSpecifiedOrganization) {
			organizationsTree.add(organization);
		}
		else {
			organizationsTree.add(organization.getParentOrganization());
		}

		params.put("usersOrgsTree", organizationsTree);

		if (userFinder.countByUser(userId, params) > 0) {
			return true;
		}

		return false;
	}

	/**
	 * Rebuilds the organizations tree.
	 *
	 * <p>
	 * Only call this method if the tree has become stale through operations
	 * other than normal CRUD. Under normal circumstances the tree is
	 * automatically rebuilt whenever necessary.
	 * </p>
	 *
	 * @param  companyId the primary key of the organization's company
	 * @throws PortalException if an organization with the primary key could not
	 *         be found
	 * @throws SystemException if a system exception occurred
	 * @see    com.liferay.portal.service.persistence.OrganizationPersistence#rebuildTree(
	 *         long, boolean)
	 */
	public void rebuildTree(long companyId)
		throws PortalException, SystemException {

		List<Organization> organizations =
			organizationPersistence.findByCompanyId(companyId);

		for (Organization organization : organizations) {
			String treePath = organization.buildTreePath();

			organization.setTreePath(treePath);

			organizationPersistence.update(organization, false);
		}
	}

	/**
	 * Returns a range of all the organizations of the company.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end -
	 * start</code> instances. <code>start</code> and <code>end</code> are not
	 * primary keys, they are indexes in the result set. Thus, <code>0</code>
	 * refers to the first result in the set. Setting both <code>start</code>
	 * and <code>end</code> to {@link
	 * com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	 * result set.
	 * </p>
	 *
	 * @param  companyId the primary key of the company
	 * @param  params the finder parameters (optionally <code>null</code>). For
	 *         more information see {@link
	 *         com.liferay.portlet.enterpriseadmin.util.OrganizationIndexer}
	 * @param  start the lower bound of the range of organizations to return
	 * @param  end the upper bound of the range of organizations to return (not
	 *         inclusive)
	 * @return the range of all the organizations of the company
	 * @throws SystemException if a system exception occurred
	 */
	public List<Organization> search(
			long companyId, LinkedHashMap<String, Object> params, int start,
			int end)
		throws SystemException {

		return organizationFinder.findByCompanyId(
			companyId, params, start, end,
			new OrganizationNameComparator(true));
	}

	/**
	 * Returns an ordered range of all the organizations that match the
	 * keywords, using the indexer. It is preferable to use this method instead
	 * of the non-indexed version whenever possible for performance reasons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end -
	 * start</code> instances. <code>start</code> and <code>end</code> are not
	 * primary keys, they are indexes in the result set. Thus, <code>0</code>
	 * refers to the first result in the set. Setting both <code>start</code>
	 * and <code>end</code> to {@link
	 * com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	 * result set.
	 * </p>
	 *
	 * @param  companyId the primary key of the organization's company
	 * @param  parentOrganizationId the primary key of the organization's parent
	 *         organization
	 * @param  keywords the keywords (space separated), which may occur in the
	 *         organization's name, street, city, zipcode, type, region or
	 *         country (optionally <code>null</code>)
	 * @param  params the finder parameters (optionally <code>null</code>). For
	 *         more information see {@link
	 *         com.liferay.portlet.enterpriseadmin.util.OrganizationIndexer}
	 * @param  start the lower bound of the range of organizations to return
	 * @param  end the upper bound of the range of organizations to return (not
	 *         inclusive)
	 * @param  sort the field and direction by which to sort (optionally
	 *         <code>null</code>)
	 * @return the matching organizations ordered by name
	 * @throws SystemException if a system exception occurred
	 * @see    com.liferay.portlet.enterpriseadmin.util.OrganizationIndexer
	 */
	public Hits search(
			long companyId, long parentOrganizationId, String keywords,
			LinkedHashMap<String, Object> params, int start, int end, Sort sort)
		throws SystemException {

		String name = null;
		String type = null;
		String street = null;
		String city = null;
		String zip = null;
		String region = null;
		String country = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			name = keywords;
			type = keywords;
			street = keywords;
			city = keywords;
			zip = keywords;
			region = keywords;
			country = keywords;
		}
		else {
			andOperator = true;
		}

		params.put("keywords", keywords);

		return search(
			companyId, parentOrganizationId, name, type, street, city, zip,
			region, country, params, andOperator, start, end, sort);
	}

	/**
	 * Returns a name ordered range of all the organizations that match the
	 * keywords, type, region, and country, without using the indexer. It is
	 * preferable to use the indexed version {@link #search(long, long, String,
	 * LinkedHashMap, int, int, Sort)} instead of this method wherever possible
	 * for performance reasons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end -
	 * start</code> instances. <code>start</code> and <code>end</code> are not
	 * primary keys, they are indexes in the result set. Thus, <code>0</code>
	 * refers to the first result in the set. Setting both <code>start</code>
	 * and <code>end</code> to {@link
	 * com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	 * result set.
	 * </p>
	 *
	 * @param  companyId the primary key of the organization's company
	 * @param  parentOrganizationId the primary key of the organization's parent
	 *         organization
	 * @param  keywords the keywords (space separated), which may occur in the
	 *         organization's name, street, city, or zipcode (optionally
	 *         <code>null</code>)
	 * @param  type the organization's type (optionally <code>null</code>)
	 * @param  regionId the primary key of the organization's region (optionally
	 *         <code>null</code>)
	 * @param  countryId the primary key of the organization's country
	 *         (optionally <code>null</code>)
	 * @param  params the finder params. For more information see {@link
	 *         com.liferay.portal.service.persistence.OrganizationFinder}
	 * @param  start the lower bound of the range of organizations to return
	 * @param  end the upper bound of the range of organizations to return (not
	 *         inclusive)
	 * @return the matching organizations ordered by name
	 * @throws SystemException if a system exception occurred
	 * @see    com.liferay.portal.service.persistence.OrganizationFinder
	 */
	public List<Organization> search(
			long companyId, long parentOrganizationId, String keywords,
			String type, Long regionId, Long countryId,
			LinkedHashMap<String, Object> params,
			int start, int end)
		throws SystemException {

		return search(
			companyId, parentOrganizationId, keywords, type, regionId,
			countryId, params, start, end,
			new OrganizationNameComparator(true));
	}

	/**
	 * Returns an ordered range of all the organizations that match the
	 * keywords, type, region, and country, without using the indexer. It is
	 * preferable to use the indexed version {@link #search(long, long, String,
	 * String, String, String, String, String, String, LinkedHashMap, boolean,
	 * int, int, Sort)} instead of this method wherever possible for performance
	 * reasons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end -
	 * start</code> instances. <code>start</code> and <code>end</code> are not
	 * primary keys, they are indexes in the result set. Thus, <code>0</code>
	 * refers to the first result in the set. Setting both <code>start</code>
	 * and <code>end</code> to {@link
	 * com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	 * result set.
	 * </p>
	 *
	 * @param  companyId the primary key of the organization's company
	 * @param  parentOrganizationId the primary key of the organization's parent
	 *         organization
	 * @param  keywords the keywords (space separated), which may occur in the
	 *         organization's name, street, city, or zipcode (optionally
	 *         <code>null</code>)
	 * @param  type the organization's type (optionally <code>null</code>)
	 * @param  regionId the primary key of the organization's region (optionally
	 *         <code>null</code>)
	 * @param  countryId the primary key of the organization's country
	 *         (optionally <code>null</code>)
	 * @param  params the finder params. For more information see {@link
	 *         com.liferay.portal.service.persistence.OrganizationFinder}
	 * @param  start the lower bound of the range of organizations to return
	 * @param  end the upper bound of the range of organizations to return (not
	 *         inclusive)
	 * @param  obc the comparator to order the organizations (optionally
	 *         <code>null</code>)
	 * @return the matching organizations ordered by comparator <code>obc</code>
	 * @throws SystemException if a system exception occurred
	 * @see    com.liferay.portal.service.persistence.OrganizationFinder
	 */
	public List<Organization> search(
			long companyId, long parentOrganizationId, String keywords,
			String type, Long regionId, Long countryId,
			LinkedHashMap<String, Object> params,
			int start, int end, OrderByComparator obc)
		throws SystemException {

		String parentOrganizationIdComparator = StringPool.EQUAL;

		if (parentOrganizationId ==
				OrganizationConstants.ANY_PARENT_ORGANIZATION_ID) {

			parentOrganizationIdComparator = StringPool.NOT_EQUAL;
		}

		return organizationFinder.findByKeywords(
			companyId, parentOrganizationId, parentOrganizationIdComparator,
			keywords, type, regionId, countryId, params, start, end,
			obc);
	}

	/**
	 * Returns a name ordered range of all the organizations with the type,
	 * region, and country, and whose name, street, city, and zipcode match the
	 * keywords specified for them, without using the indexer. It is preferable
	 * to use the indexed version {@link #search(long, long, String, String,
	 * String, String, String, String, String, LinkedHashMap, boolean, int, int,
	 * Sort)} instead of this method wherever possible for performance reasons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end -
	 * start</code> instances. <code>start</code> and <code>end</code> are not
	 * primary keys, they are indexes in the result set. Thus, <code>0</code>
	 * refers to the first result in the set. Setting both <code>start</code>
	 * and <code>end</code> to {@link
	 * com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	 * result set.
	 * </p>
	 *
	 * @param  companyId the primary key of the organization's company
	 * @param  parentOrganizationId the primary key of the organization's parent
	 * @param  name the name keywords (space separated, optionally
	 *         <code>null</code>)
	 * @param  type the organization's type (optionally <code>null</code>)
	 * @param  street the street keywords (optionally <code>null</code>)
	 * @param  city the city keywords (optionally <code>null</code>)
	 * @param  zip the zipcode keywords (optionally <code>null</code>)
	 * @param  regionId the primary key of the organization's region (optionally
	 *         <code>null</code>)
	 * @param  countryId the primary key of the organization's country
	 *         (optionally <code>null</code>)
	 * @param  params the finder parameters (optionally <code>null</code>). For
	 *         more information see {@link
	 *         com.liferay.portal.service.persistence.OrganizationFinder}
	 * @param  andOperator whether every field must match its keywords, or just
	 *         one field. For example, &quot;organizations with the name
	 *         'Employees' and city 'Chicago'&quot; vs &quot;organizations with
	 *         the name 'Employees' or the city 'Chicago'&quot;.
	 * @param  start the lower bound of the range of organizations to return
	 * @param  end the upper bound of the range of organizations to return (not
	 *         inclusive)
	 * @return the matching organizations ordered by name
	 * @throws SystemException if a system exception occurred
	 * @see    com.liferay.portal.service.persistence.OrganizationFinder
	 */
	public List<Organization> search(
			long companyId, long parentOrganizationId, String name, String type,
			String street, String city, String zip,
			Long regionId, Long countryId,
			LinkedHashMap<String, Object> params, boolean andOperator,
			int start, int end)
		throws SystemException {

		return search(
			companyId, parentOrganizationId, name, type, street, city, zip,
			regionId, countryId, params, andOperator, start, end,
			new OrganizationNameComparator(true));
	}

	/**
	 * Returns an ordered range of all the organizations with the type, region,
	 * and country, and whose name, street, city, and zipcode match the keywords
	 * specified for them, without using the indexer. It is preferable to use
	 * the indexed version {@link #search(long, long, String, String, String,
	 * String, String, String, String, LinkedHashMap, boolean, int, int, Sort)}
	 * instead of this method wherever possible for performance reasons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end -
	 * start</code> instances. <code>start</code> and <code>end</code> are not
	 * primary keys, they are indexes in the result set. Thus, <code>0</code>
	 * refers to the first result in the set. Setting both <code>start</code>
	 * and <code>end</code> to {@link
	 * com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	 * result set.
	 * </p>
	 *
	 * @param  companyId the primary key of the organization's company
	 * @param  parentOrganizationId the primary key of the organization's parent
	 *         organization
	 * @param  name the name keywords (space separated, optionally
	 *         <code>null</code>)
	 * @param  type the organization's type (optionally <code>null</code>)
	 * @param  street the street keywords (optionally <code>null</code>)
	 * @param  city the city keywords (optionally <code>null</code>)
	 * @param  zip the zipcode keywords (optionally <code>null</code>)
	 * @param  regionId the primary key of the organization's region (optionally
	 *         <code>null</code>)
	 * @param  countryId the primary key of the organization's country
	 *         (optionally <code>null</code>)
	 * @param  params the finder parameters (optionally <code>null</code>). For
	 *         more information see {@link
	 *         com.liferay.portal.service.persistence.OrganizationFinder}
	 * @param  andOperator whether every field must match its keywords, or just
	 *         one field. For example, &quot;organizations with the name
	 *         'Employees' and city 'Chicago'&quot; vs &quot;organizations with
	 *         the name 'Employees' or the city 'Chicago'&quot;.
	 * @param  start the lower bound of the range of organizations to return
	 * @param  end the upper bound of the range of organizations to return (not
	 *         inclusive)
	 * @param  obc the comparator to order the organizations (optionally
	 *         <code>null</code>)
	 * @return the matching organizations ordered by comparator <code>obc</code>
	 * @throws SystemException if a system exception occurred
	 * @see    com.liferay.portal.service.persistence.OrganizationFinder
	 */
	public List<Organization> search(
			long companyId, long parentOrganizationId, String name, String type,
			String street, String city, String zip,
			Long regionId, Long countryId, LinkedHashMap<String, Object> params,
			boolean andOperator, int start, int end, OrderByComparator obc)
		throws SystemException {

		String parentOrganizationIdComparator = StringPool.EQUAL;

		if (parentOrganizationId ==
				OrganizationConstants.ANY_PARENT_ORGANIZATION_ID) {

			parentOrganizationIdComparator = StringPool.NOT_EQUAL;
		}

		return organizationFinder.findByC_PO_N_T_S_C_Z_R_C(
			companyId, parentOrganizationId, parentOrganizationIdComparator,
			name, type, street, city, zip, regionId, countryId, params,
			andOperator, start, end, obc);
	}

	/**
	 * Returns an ordered range of all the organizations whose name, type, or
	 * location fields match the keywords specified for them, using the indexer.
	 * It is preferable to use this method instead of the non-indexed version
	 * whenever possible for performance reasons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end -
	 * start</code> instances. <code>start</code> and <code>end</code> are not
	 * primary keys, they are indexes in the result set. Thus, <code>0</code>
	 * refers to the first result in the set. Setting both <code>start</code>
	 * and <code>end</code> to {@link
	 * com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	 * result set.
	 * </p>
	 *
	 * @param  companyId the primary key of the organization's company
	 * @param  parentOrganizationId the primary key of the organization's parent
	 *         organization
	 * @param  name the name keywords (space separated, optionally
	 *         <code>null</code>)
	 * @param  type the type keywords (optionally <code>null</code>)
	 * @param  street the street keywords (optionally <code>null</code>)
	 * @param  city the city keywords (optionally <code>null</code>)
	 * @param  zip the zipcode keywords (optionally <code>null</code>)
	 * @param  region the region keywords (optionally <code>null</code>)
	 * @param  country the country keywords (optionally <code>null</code>)
	 * @param  params the finder parameters (optionally <code>null</code>). For
	 *         more information see {@link
	 *         com.liferay.portlet.enterpriseadmin.util.OrganizationIndexer}.
	 * @param  andSearch whether every field must match its keywords or just one
	 *         field
	 * @param  start the lower bound of the range of organizations to return
	 * @param  end the upper bound of the range of organizations to return (not
	 *         inclusive)
	 * @param  sort the field and direction by which to sort (optionally
	 *         <code>null</code>)
	 * @return the matching organizations ordered by <code>sort</code>
	 * @throws SystemException if a system exception occurred
	 * @see    com.liferay.portlet.enterpriseadmin.util.OrganizationIndexer
	 */
	public Hits search(
			long companyId, long parentOrganizationId, String name, String type,
			String street, String city, String zip, String region,
			String country, LinkedHashMap<String, Object> params,
			boolean andSearch, int start, int end, Sort sort)
		throws SystemException {

		try {
			Map<String, Serializable> attributes =
				new HashMap<String, Serializable>();

			attributes.put("city", city);
			attributes.put("country", country);
			attributes.put("name", name);
			attributes.put("params", params);

			if (parentOrganizationId !=
					OrganizationConstants.ANY_PARENT_ORGANIZATION_ID) {

				attributes.put(
					"parentOrganizationId",
					String.valueOf(parentOrganizationId));
			}

			attributes.put("region", region);
			attributes.put("street", street);
			attributes.put("type", type);
			attributes.put("zip", zip);

			SearchContext searchContext = new SearchContext();

			searchContext.setAndSearch(andSearch);
			searchContext.setAttributes(attributes);
			searchContext.setCompanyId(companyId);
			searchContext.setEnd(end);

			String keywords = (String)params.remove("keywords");

			if (Validator.isNotNull(keywords)) {
				searchContext.setKeywords(keywords);
			}

			searchContext.setSorts(new Sort[] {sort});

			QueryConfig queryConfig = new QueryConfig();

			queryConfig.setHighlightEnabled(false);
			queryConfig.setScoreEnabled(false);

			searchContext.setQueryConfig(queryConfig);

			searchContext.setStart(start);

			Indexer indexer = IndexerRegistryUtil.getIndexer(
				Organization.class);

			return indexer.search(searchContext);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * Returns the number of organizations that match the keywords, type,
	 * region, and country.
	 *
	 * @param  companyId the primary key of the organization's company
	 * @param  parentOrganizationId the primary key of the organization's parent
	 *         organization
	 * @param  keywords the keywords (space separated), which may occur in the
	 *         organization's name, street, city, or zipcode (optionally
	 *         <code>null</code>)
	 * @param  type the organization's type (optionally <code>null</code>)
	 * @param  regionId the primary key of the organization's region (optionally
	 *         <code>null</code>)
	 * @param  countryId the primary key of the organization's country
	 *         (optionally <code>null</code>)
	 * @param  params the finder parameters (optionally <code>null</code>). For
	 *         more information see {@link
	 *         com.liferay.portal.service.persistence.OrganizationFinder}
	 * @return the number of matching organizations
	 * @throws SystemException if a system exception occurred
	 * @see    com.liferay.portal.service.persistence.OrganizationFinder
	 */
	public int searchCount(
			long companyId, long parentOrganizationId, String keywords,
			String type, Long regionId, Long countryId,
			LinkedHashMap<String, Object> params)
		throws SystemException {

		String parentOrganizationIdComparator = StringPool.EQUAL;

		if (parentOrganizationId ==
				OrganizationConstants.ANY_PARENT_ORGANIZATION_ID) {

			parentOrganizationIdComparator = StringPool.NOT_EQUAL;
		}

		return organizationFinder.countByKeywords(
			companyId, parentOrganizationId, parentOrganizationIdComparator,
			keywords, type, regionId, countryId, params);
	}

	/**
	 * Returns the number of organizations with the type, region, and country,
	 * and whose name, street, city, and zipcode match the keywords specified
	 * for them.
	 *
	 * @param  companyId the primary key of the organization's company
	 * @param  parentOrganizationId the primary key of the organization's parent
	 *         organization
	 * @param  name the name keywords (space separated, optionally
	 *         <code>null</code>)
	 * @param  type the organization's type (optionally <code>null</code>)
	 * @param  street the street keywords (optionally <code>null</code>)
	 * @param  city the city keywords (optionally <code>null</code>)
	 * @param  zip the zipcode keywords (optionally <code>null</code>)
	 * @param  regionId the primary key of the organization's region (optionally
	 *         <code>null</code>)
	 * @param  countryId the primary key of the organization's country
	 *         (optionally <code>null</code>)
	 * @param  params the finder parameters (optionally <code>null</code>). For
	 *         more information see {@link
	 *         com.liferay.portal.service.persistence.OrganizationFinder}
	 * @param  andOperator whether every field must match its keywords, or just
	 *         one field. For example, &quot;organizations with the name
	 *         'Employees' and city 'Chicago'&quot; vs &quot;organizations with
	 *         the name 'Employees' or the city 'Chicago'&quot;.
	 * @return the number of matching organizations
	 * @throws SystemException if a system exception occurred
	 * @see    com.liferay.portal.service.persistence.OrganizationFinder
	 */
	public int searchCount(
			long companyId, long parentOrganizationId, String name, String type,
			String street, String city, String zip,
			Long regionId, Long countryId, LinkedHashMap<String, Object> params,
			boolean andOperator)
		throws SystemException {

		String parentOrganizationIdComparator = StringPool.EQUAL;

		if (parentOrganizationId ==
				OrganizationConstants.ANY_PARENT_ORGANIZATION_ID) {

			parentOrganizationIdComparator = StringPool.NOT_EQUAL;
		}

		return organizationFinder.countByC_PO_N_T_S_C_Z_R_C(
			companyId, parentOrganizationId, parentOrganizationIdComparator,
			name, type, street, city, zip, regionId, countryId, params,
			andOperator);
	}

	/**
	 * Sets the organizations in the group, removing and adding organizations to
	 * the group as necessary.
	 *
	 * @param  groupId the primary key of the group
	 * @param  organizationIds the primary keys of the organizations
	 * @throws PortalException if a portal exception occurred
	 * @throws SystemException if a system exception occurred
	 */
	public void setGroupOrganizations(long groupId, long[] organizationIds)
		throws PortalException, SystemException {

		groupPersistence.setOrganizations(groupId, organizationIds);

		Indexer indexer = IndexerRegistryUtil.getIndexer(Organization.class);

		indexer.reindex(organizationIds);

		PermissionCacheUtil.clearCache();
	}

	/**
	 * Removes the organizations from the group.
	 *
	 * @param  groupId the primary key of the group
	 * @param  organizationIds the primary keys of the organizations
	 * @throws PortalException if a portal exception occurred
	 * @throws SystemException if a system exception occurred
	 */
	public void unsetGroupOrganizations(long groupId, long[] organizationIds)
		throws PortalException, SystemException {

		groupPersistence.removeOrganizations(groupId, organizationIds);

		Indexer indexer = IndexerRegistryUtil.getIndexer(Organization.class);

		indexer.reindex(organizationIds);

		PermissionCacheUtil.clearCache();
	}

	/**
	 * Removes the organizations from the password policy.
	 *
	 * @param  passwordPolicyId the primary key of the password policy
	 * @param  organizationIds the primary keys of the organizations
	 * @throws SystemException if a system exception occurred
	 */
	public void unsetPasswordPolicyOrganizations(
			long passwordPolicyId, long[] organizationIds)
		throws SystemException {

		passwordPolicyRelLocalService.deletePasswordPolicyRels(
			passwordPolicyId, Organization.class.getName(), organizationIds);
	}

	/**
	 * Updates the organization's asset with the new asset categories and tag
	 * names, removing and adding asset categories and tag names as necessary.
	 *
	 * @param  userId the primary key of the user
	 * @param  organization the organization
	 * @param  assetCategoryIds the primary keys of the asset categories
	 * @param  assetTagNames the asset tag names
	 * @throws PortalException if a user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void updateAsset(
			long userId, Organization organization, long[] assetCategoryIds,
			String[] assetTagNames)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);

		Company company = companyPersistence.findByPrimaryKey(
			user.getCompanyId());

		Group companyGroup = company.getGroup();

		assetEntryLocalService.updateEntry(
			userId, companyGroup.getGroupId(), Organization.class.getName(),
			organization.getOrganizationId(), null, 0, assetCategoryIds,
			assetTagNames, false, null, null, null, null, null,
			organization.getName(), StringPool.BLANK, null, null, null, 0, 0,
			null, false);
	}

	/**
	 * Updates the organization.
	 *
	 * @param  companyId the primary key of the organization's company
	 * @param  organizationId the primary key of the organization
	 * @param  parentOrganizationId the primary key of organization's parent
	 *         organization
	 * @param  name the organization's name
	 * @param  type the organization's type
	 * @param  recursable whether permissions of the organization are to be
	 *         inherited by its sub-organizations
	 * @param  regionId the primary key of the organization's region
	 * @param  countryId the primary key of the organization's country
	 * @param  statusId the organization's workflow status
	 * @param  comments the comments about the organization
	 * @param  site whether the organization is to be associated with a main
	 *         site
	 * @param  serviceContext the organization's service context (optionally
	 *         <code>null</code>). Can set asset category IDs and asset tag
	 *         names for the organization, and merge expando bridge attributes
	 *         for the organization.
	 * @return the organization
	 * @throws PortalException if an organization or parent organization with
	 *         the primary key could not be found or if the new information was
	 *         invalid
	 * @throws SystemException if a system exception occurred
	 */
	public Organization updateOrganization(
			long companyId, long organizationId, long parentOrganizationId,
			String name, String type, boolean recursable, long regionId,
			long countryId, int statusId, String comments, boolean site,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Organization

		parentOrganizationId = getParentOrganizationId(
			companyId, parentOrganizationId);
		recursable = true;

		validate(
			companyId, organizationId, parentOrganizationId, name, type,
			countryId, statusId);

		Organization organization = organizationPersistence.findByPrimaryKey(
			organizationId);

		long oldParentOrganizationId = organization.getParentOrganizationId();
		String oldName = organization.getName();

		organization.setParentOrganizationId(parentOrganizationId);

		String treePath = organization.buildTreePath();

		organization.setTreePath(treePath);

		organization.setName(name);
		organization.setType(type);
		organization.setRecursable(recursable);
		organization.setRegionId(regionId);
		organization.setCountryId(countryId);
		organization.setStatusId(statusId);
		organization.setComments(comments);

		organizationPersistence.update(organization, false);

		// Group

		Group group = organization.getGroup();

		if (!oldName.equals(name)) {
			groupLocalService.updateGroup(
				group.getGroupId(), name, group.getDescription(),
				group.getType(), group.getFriendlyURL(), group.isActive(),
				null);
		}

		if (group.isSite() != site) {
			groupLocalService.updateSite(group.getGroupId(), site);
		}

		// Asset

		if (serviceContext != null) {
			updateAsset(
				serviceContext.getUserId(), organization,
				serviceContext.getAssetCategoryIds(),
				serviceContext.getAssetTagNames());
		}

		// Expando

		ExpandoBridge expandoBridge = organization.getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);

		// Indexer

		Indexer indexer = IndexerRegistryUtil.getIndexer(Organization.class);

		if (oldParentOrganizationId != parentOrganizationId) {
			long[] organizationIds = getReindexOrganizationIds(organization);

			indexer.reindex(organizationIds);
		}
		else {
			indexer.reindex(organization);
		}

		return organization;
	}

	protected void addSuborganizations(
			List<Organization> allSuborganizations,
			List<Organization> organizations)
		throws SystemException {

		for (Organization organization : organizations) {
			if (!allSuborganizations.contains(organization)) {
				allSuborganizations.add(organization);

				List<Organization> suborganizations =
					organizationPersistence.findByC_P(
						organization.getCompanyId(),
						organization.getOrganizationId());

				addSuborganizations(allSuborganizations, suborganizations);
			}
		}
	}

	protected long getParentOrganizationId(
			long companyId, long parentOrganizationId)
		throws SystemException {

		if (parentOrganizationId !=
				OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID) {

			// Ensure parent organization exists and belongs to the proper
			// company

			Organization parentOrganization =
				organizationPersistence.fetchByPrimaryKey(parentOrganizationId);

			if ((parentOrganization == null) ||
				(companyId != parentOrganization.getCompanyId())) {

				parentOrganizationId =
					OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID;
			}
		}

		return parentOrganizationId;
	}

	protected List<Organization> getParentOrganizations(
			Organization organization, boolean lastOrganization)
		throws PortalException, SystemException {

		List<Organization> organizations = new ArrayList<Organization>();

		if (!lastOrganization) {
			organizations.add(organization);
		}

		long parentOrganizationId = organization.getParentOrganizationId();

		if (parentOrganizationId ==
				OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID) {

			return organizations;
		}

		Organization parentOrganization =
			organizationPersistence.findByPrimaryKey(parentOrganizationId);

		List<Organization> parentOrganizatons = getParentOrganizations(
			parentOrganization, false);

		organizations.addAll(parentOrganizatons);

		return organizations;
	}

	protected long[] getReindexOrganizationIds(Organization organization)
		throws PortalException, SystemException {

		List<Organization> organizationsTree = new ArrayList<Organization>();

		organizationsTree.add(organization);

		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		params.put("organizationsTree", organizationsTree);

		List<Organization> organizations = search(
			organization.getCompanyId(), params, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS);

		long[] organizationIds = new long[organizations.size()];

		for (int i = 0; i < organizations.size(); i++) {
			Organization curOrganization = organizations.get(i);

			String treePath = curOrganization.buildTreePath();

			curOrganization.setTreePath(treePath.toString());

			organizationPersistence.update(curOrganization, false);

			organizationIds[i] = curOrganization.getOrganizationId();
		}

		if (!ArrayUtil.contains(
				organizationIds, organization.getOrganizationId())) {

			organizationIds = ArrayUtil.append(
				organizationIds, organization.getOrganizationId());
		}

		return organizationIds;
	}

	protected boolean isParentOrganization(
			long parentOrganizationId, long organizationId)
		throws PortalException, SystemException {

		// Return true if parentOrganizationId is among the parent organizatons
		// of organizationId

		Organization parentOrganization =
			organizationPersistence.findByPrimaryKey(parentOrganizationId);

		List<Organization> parentOrganizations = getParentOrganizations(
			organizationId);

		if (parentOrganizations.contains(parentOrganization)) {
			return true;
		}
		else {
			return false;
		}
	}

	protected void validate(
			long companyId, long organizationId, long parentOrganizationId,
			String name, String type, long countryId, int statusId)
		throws PortalException, SystemException {

		if (!ArrayUtil.contains(PropsValues.ORGANIZATIONS_TYPES, type)) {
			throw new OrganizationTypeException(
				"Invalid organization type " + type);
		}

		if ((parentOrganizationId ==
				OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID)) {

			if (!OrganizationImpl.isRootable(type)) {
				throw new OrganizationParentException(
					"Organization of type " + type + " cannot be a root");
			}
		}
		else {
			Organization parentOrganization =
				organizationPersistence.fetchByPrimaryKey(parentOrganizationId);

			if (parentOrganization == null) {
				throw new OrganizationParentException(
					"Organization " + parentOrganizationId + " doesn't exist");
			}

			String[] childrenTypes = OrganizationImpl.getChildrenTypes(
				parentOrganization.getType());

			if (childrenTypes.length == 0) {
				throw new OrganizationParentException(
					"Organization of type " + type + " cannot have children");
			}

			if ((companyId != parentOrganization.getCompanyId()) ||
				(parentOrganizationId == organizationId)) {

				throw new OrganizationParentException();
			}

			if (!ArrayUtil.contains(childrenTypes, type)) {
				throw new OrganizationParentException(
					"Type " + type + " not allowed as child of " +
						parentOrganization.getType());
			}
		}

		if ((organizationId > 0) &&
			(parentOrganizationId !=
				OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID)) {

			// Prevent circular organizational references

			if (isParentOrganization(organizationId, parentOrganizationId)) {
				throw new OrganizationParentException();
			}
		}

		if (Validator.isNull(name)) {
			throw new OrganizationNameException();
		}
		else {
			Organization organization = organizationPersistence.fetchByC_N(
				companyId, name);

			if ((organization != null) &&
				(organization.getName().equalsIgnoreCase(name))) {

				if ((organizationId <= 0) ||
					(organization.getOrganizationId() != organizationId)) {

					throw new DuplicateOrganizationException();
				}
			}
		}

		boolean countryRequired = GetterUtil.getBoolean(
			PropsUtil.get(
				PropsKeys.ORGANIZATIONS_COUNTRY_REQUIRED, new Filter(type)));

		if (countryRequired || (countryId > 0)) {
			countryPersistence.findByPrimaryKey(countryId);
		}

		listTypeService.validate(
			statusId, ListTypeConstants.ORGANIZATION_STATUS);
	}

	protected void validate(
			long companyId, long parentOrganizationId, String name, String type,
			long countryId, int statusId)
		throws PortalException, SystemException {

		validate(
			companyId, 0, parentOrganizationId, name, type, countryId,
			statusId);
	}

}