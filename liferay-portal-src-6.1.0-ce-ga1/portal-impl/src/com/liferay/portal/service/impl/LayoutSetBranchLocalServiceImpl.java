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

import com.liferay.portal.LayoutSetBranchNameException;
import com.liferay.portal.NoSuchLayoutSetBranchException;
import com.liferay.portal.RequiredLayoutSetBranchException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.staging.StagingUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutBranch;
import com.liferay.portal.model.LayoutBranchConstants;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.model.LayoutRevision;
import com.liferay.portal.model.LayoutRevisionConstants;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.model.LayoutSetBranch;
import com.liferay.portal.model.LayoutSetBranchConstants;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.base.LayoutSetBranchLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;

/**
 * @author Raymond Augé
 * @author Brian Wing Shun Chan
 */
public class LayoutSetBranchLocalServiceImpl
	extends LayoutSetBranchLocalServiceBaseImpl {

	public LayoutSetBranch addLayoutSetBranch(
			long userId, long groupId, boolean privateLayout, String name,
			String description, boolean master, long copyLayoutSetBranchId,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Layout branch

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		validate(0, groupId, privateLayout, name, master);

		long layoutSetBranchId = counterLocalService.increment();

		LayoutSetBranch layoutSetBranch = layoutSetBranchPersistence.create(
			layoutSetBranchId);

		layoutSetBranch.setGroupId(groupId);
		layoutSetBranch.setCompanyId(user.getCompanyId());
		layoutSetBranch.setUserId(user.getUserId());
		layoutSetBranch.setUserName(user.getFullName());
		layoutSetBranch.setCreateDate(serviceContext.getCreateDate(now));
		layoutSetBranch.setModifiedDate(serviceContext.getModifiedDate(now));
		layoutSetBranch.setPrivateLayout(privateLayout);
		layoutSetBranch.setName(name);
		layoutSetBranch.setDescription(description);
		layoutSetBranch.setMaster(master);

		layoutSetBranchPersistence.update(layoutSetBranch, false);

		// Resources

		resourceLocalService.addResources(
			user.getCompanyId(), layoutSetBranch.getGroupId(), user.getUserId(),
			LayoutSetBranch.class.getName(),
			layoutSetBranch.getLayoutSetBranchId(), false, true, false);

		// Layout revisions

		if (layoutSetBranch.isMaster() ||
			(copyLayoutSetBranchId == LayoutSetBranchConstants.ALL_BRANCHES)) {

			List<Layout> layouts = layoutPersistence.findByG_P(
				layoutSetBranch.getGroupId(),
				layoutSetBranch.getPrivateLayout());

			for (Layout layout : layouts) {
				LayoutBranch layoutBranch =
					layoutBranchLocalService.addLayoutBranch(
						layoutSetBranchId, layout.getPlid(),
						LayoutBranchConstants.MASTER_BRANCH_NAME,
						LayoutBranchConstants.MASTER_BRANCH_DESCRIPTION, true,
						serviceContext);

				LayoutRevision lastLayoutRevision =
					layoutRevisionLocalService.fetchLastLayoutRevision(
						layout.getPlid(), true);

				if (lastLayoutRevision != null) {
					layoutRevisionLocalService.addLayoutRevision(
						userId, layoutSetBranchId,
						layoutBranch.getLayoutBranchId(),
						LayoutRevisionConstants.
							DEFAULT_PARENT_LAYOUT_REVISION_ID,
						true, lastLayoutRevision.getPlid(),
						lastLayoutRevision.getLayoutRevisionId(),
						lastLayoutRevision.getPrivateLayout(),
						lastLayoutRevision.getName(),
						lastLayoutRevision.getTitle(),
						lastLayoutRevision.getDescription(),
						lastLayoutRevision.getKeywords(),
						lastLayoutRevision.getRobots(),
						lastLayoutRevision.getTypeSettings(),
						lastLayoutRevision.isIconImage(),
						lastLayoutRevision.getIconImageId(),
						lastLayoutRevision.getThemeId(),
						lastLayoutRevision.getColorSchemeId(),
						lastLayoutRevision.getWapThemeId(),
						lastLayoutRevision.getWapColorSchemeId(),
						lastLayoutRevision.getCss(), serviceContext);
				}
				else {
					layoutRevisionLocalService.addLayoutRevision(
						userId, layoutSetBranchId,
						layoutBranch.getLayoutBranchId(),
						LayoutRevisionConstants.
							DEFAULT_PARENT_LAYOUT_REVISION_ID,
						false, layout.getPlid(), LayoutConstants.DEFAULT_PLID,
						layout.getPrivateLayout(), layout.getName(),
						layout.getTitle(), layout.getDescription(),
						layout.getKeywords(), layout.getRobots(),
						layout.getTypeSettings(), layout.isIconImage(),
						layout.getIconImageId(), layout.getThemeId(),
						layout.getColorSchemeId(), layout.getWapThemeId(),
						layout.getWapColorSchemeId(), layout.getCss(),
						serviceContext);
				}
			}
		}
		else if (copyLayoutSetBranchId > 0) {
			List<LayoutRevision> layoutRevisions =
				layoutRevisionLocalService.getLayoutRevisions(
					copyLayoutSetBranchId, true);

			for (LayoutRevision layoutRevision : layoutRevisions) {
				LayoutBranch layoutBranch =
					layoutBranchLocalService.addLayoutBranch(
						layoutSetBranchId, layoutRevision.getPlid(),
						LayoutBranchConstants.MASTER_BRANCH_NAME,
						LayoutBranchConstants.MASTER_BRANCH_DESCRIPTION, true,
						serviceContext);

				layoutRevisionLocalService.addLayoutRevision(
					userId, layoutSetBranchId,
					layoutBranch.getLayoutBranchId(),
					LayoutRevisionConstants.DEFAULT_PARENT_LAYOUT_REVISION_ID,
					true, layoutRevision.getPlid(),
					layoutRevision.getLayoutRevisionId(),
					layoutRevision.getPrivateLayout(), layoutRevision.getName(),
					layoutRevision.getTitle(), layoutRevision.getDescription(),
					layoutRevision.getKeywords(), layoutRevision.getRobots(),
					layoutRevision.getTypeSettings(),
					layoutRevision.isIconImage(),
					layoutRevision.getIconImageId(),
					layoutRevision.getThemeId(),
					layoutRevision.getColorSchemeId(),
					layoutRevision.getWapThemeId(),
					layoutRevision.getWapColorSchemeId(),
					layoutRevision.getCss(), serviceContext);
			}
		}

		return layoutSetBranch;
	}

	@Override
	public void deleteLayoutSetBranch(LayoutSetBranch layoutSetBranch)
		throws PortalException, SystemException {

		deleteLayoutSetBranch(layoutSetBranch, false);
	}

	public void deleteLayoutSetBranch(
			LayoutSetBranch layoutSetBranch, boolean includeMaster)
		throws PortalException, SystemException {

		// Layout branch

		if (!includeMaster && layoutSetBranch.isMaster()) {
			throw new RequiredLayoutSetBranchException();
		}

		layoutSetBranchPersistence.remove(layoutSetBranch);

		// Resources

		resourceLocalService.deleteResource(
			layoutSetBranch.getCompanyId(), LayoutSetBranch.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL,
			layoutSetBranch.getLayoutSetBranchId());

		// Layout branches

		layoutBranchLocalService.deleteLayoutSetBranchLayoutBranches(
			layoutSetBranch.getLayoutSetBranchId());

		// Layout revisions

		layoutRevisionLocalService.deleteLayoutSetBranchLayoutRevisions(
			layoutSetBranch.getLayoutSetBranchId());
	}

	@Override
	public void deleteLayoutSetBranch(long layoutSetBranchId)
		throws PortalException, SystemException {

		LayoutSetBranch layoutSetBranch =
			layoutSetBranchPersistence.findByPrimaryKey(layoutSetBranchId);

		deleteLayoutSetBranch(layoutSetBranch);
	}

	public void deleteLayoutSetBranches(long groupId, boolean privateLayout)
		throws PortalException, SystemException {

		deleteLayoutSetBranches(groupId, privateLayout, false);
	}

	public void deleteLayoutSetBranches(
			long groupId, boolean privateLayout, boolean includeMaster)
		throws PortalException, SystemException {

		List<LayoutSetBranch> layoutSetBranches =
			layoutSetBranchPersistence.findByG_P(groupId, privateLayout);

		for (LayoutSetBranch layoutSetBranch : layoutSetBranches) {
			deleteLayoutSetBranch(layoutSetBranch, includeMaster);
		}
	}

	public LayoutSetBranch getLayoutSetBranch(
			long groupId, boolean privateLayout, String name)
		throws PortalException, SystemException {

		return layoutSetBranchPersistence.findByG_P_N(
			groupId, privateLayout, name);
	}

	public List<LayoutSetBranch> getLayoutSetBranches(
			long groupId, boolean privateLayout)
		throws SystemException {

		return layoutSetBranchPersistence.findByG_P(groupId, privateLayout);
	}

	public LayoutSetBranch getMasterLayoutSetBranch(
			long groupId, boolean privateLayout)
		throws PortalException, SystemException {

		return layoutSetBranchFinder.findByMaster(groupId, privateLayout);
	}

	public LayoutSetBranch getUserLayoutSetBranch(
			long userId, long groupId, boolean privateLayout,
			long layoutSetBranchId)
		throws PortalException, SystemException {

		if (layoutSetBranchId <= 0) {
			User user = userPersistence.findByPrimaryKey(userId);

			LayoutSet layoutSet = layoutSetLocalService.getLayoutSet(
				groupId, privateLayout);

			layoutSetBranchId = StagingUtil.getRecentLayoutSetBranchId(
				user, layoutSet.getLayoutSetId());
		}

		if (layoutSetBranchId > 0) {
			try {
				return getLayoutSetBranch(layoutSetBranchId);
			}
			catch (NoSuchLayoutSetBranchException nslsbe) {
			}
		}

		return getMasterLayoutSetBranch(groupId, privateLayout);
	}

	public LayoutSetBranch mergeLayoutSetBranch(
			long layoutSetBranchId, long mergeLayoutSetBranchId,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		LayoutSetBranch layoutSetBranch =
			layoutSetBranchPersistence.findByPrimaryKey(layoutSetBranchId);

		List<LayoutRevision> layoutRevisions =
			layoutRevisionLocalService.getLayoutRevisions(
				mergeLayoutSetBranchId, true);

		serviceContext.setWorkflowAction(WorkflowConstants.ACTION_PUBLISH);

		for (LayoutRevision layoutRevision : layoutRevisions) {
			layoutRevisionLocalService.addLayoutRevision(
				layoutRevision.getUserId(),
				layoutSetBranch.getLayoutSetBranchId(),
				layoutRevision.getLayoutBranchId(),
				LayoutRevisionConstants.DEFAULT_PARENT_LAYOUT_REVISION_ID,
				false, layoutRevision.getPlid(),
				layoutRevision.getLayoutRevisionId(),
				layoutRevision.isPrivateLayout(), layoutRevision.getName(),
				layoutRevision.getTitle(), layoutRevision.getDescription(),
				layoutRevision.getKeywords(), layoutRevision.getRobots(),
				layoutRevision.getTypeSettings(), layoutRevision.getIconImage(),
				layoutRevision.getIconImageId(), layoutRevision.getThemeId(),
				layoutRevision.getColorSchemeId(),
				layoutRevision.getWapThemeId(),
				layoutRevision.getWapColorSchemeId(), layoutRevision.getCss(),
				serviceContext);
		}

		return layoutSetBranch;
	}

	public LayoutSetBranch updateLayoutSetBranch(
			long layoutSetBranchId, String name, String description,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		LayoutSetBranch layoutSetBranch =
			layoutSetBranchPersistence.findByPrimaryKey(layoutSetBranchId);

		validate(
			layoutSetBranch.getLayoutSetBranchId(),
			layoutSetBranch.getGroupId(), layoutSetBranch.getPrivateLayout(),
			name, layoutSetBranch.isMaster());

		layoutSetBranch.setName(name);
		layoutSetBranch.setDescription(description);

		layoutSetBranchPersistence.update(layoutSetBranch, false);

		return layoutSetBranch;
	}

	protected void validate(
			long layoutSetBranchId, long groupId, boolean privateLayout,
			String name, boolean master)
		throws PortalException, SystemException {

		if (Validator.isNull(name) || (name.length() < 4)) {
			throw new LayoutSetBranchNameException(
				LayoutSetBranchNameException.TOO_SHORT);
		}

		if (name.length() > 100) {
			throw new LayoutSetBranchNameException(
				LayoutSetBranchNameException.TOO_LONG);
		}

		try {
			LayoutSetBranch layoutSetBranch =
				layoutSetBranchPersistence.findByG_P_N(
					groupId, privateLayout, name);

			if (layoutSetBranch.getLayoutSetBranchId() != layoutSetBranchId) {
				throw new LayoutSetBranchNameException(
					LayoutSetBranchNameException.DUPLICATE);
			}
		}
		catch (NoSuchLayoutSetBranchException nsbe) {
		}

		if (master) {
			try {
				LayoutSetBranch masterLayoutSetBranch =
					layoutSetBranchFinder.findByMaster(groupId, privateLayout);

				if (layoutSetBranchId !=
						masterLayoutSetBranch.getLayoutSetBranchId()) {

					throw new LayoutSetBranchNameException(
						LayoutSetBranchNameException.MASTER);
				}
			}
			catch (NoSuchLayoutSetBranchException nsbe) {
			}
		}
	}

}