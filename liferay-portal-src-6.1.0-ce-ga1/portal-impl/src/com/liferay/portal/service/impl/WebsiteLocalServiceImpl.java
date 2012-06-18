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

import com.liferay.portal.WebsiteURLException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ListTypeConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.model.Website;
import com.liferay.portal.service.base.WebsiteLocalServiceBaseImpl;
import com.liferay.portal.util.PortalUtil;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class WebsiteLocalServiceImpl extends WebsiteLocalServiceBaseImpl {

	public Website addWebsite(
			long userId, String className, long classPK, String url, int typeId,
			boolean primary)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
		long classNameId = PortalUtil.getClassNameId(className);
		Date now = new Date();

		validate(
			0, user.getCompanyId(), classNameId, classPK, url, typeId,
			primary);

		long websiteId = counterLocalService.increment();

		Website website = websitePersistence.create(websiteId);

		website.setCompanyId(user.getCompanyId());
		website.setUserId(user.getUserId());
		website.setUserName(user.getFullName());
		website.setCreateDate(now);
		website.setModifiedDate(now);
		website.setClassNameId(classNameId);
		website.setClassPK(classPK);
		website.setUrl(url);
		website.setTypeId(typeId);
		website.setPrimary(primary);

		websitePersistence.update(website, false);

		return website;
	}

	@Override
	public void deleteWebsite(long websiteId)
		throws PortalException, SystemException {

		Website website = websitePersistence.findByPrimaryKey(websiteId);

		deleteWebsite(website);
	}

	@Override
	public void deleteWebsite(Website website) throws SystemException {
		websitePersistence.remove(website);
	}

	public void deleteWebsites(long companyId, String className, long classPK)
		throws SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		List<Website> websites = websitePersistence.findByC_C_C(
			companyId, classNameId, classPK);

		for (Website website : websites) {
			deleteWebsite(website);
		}
	}

	@Override
	public Website getWebsite(long websiteId)
		throws PortalException, SystemException {

		return websitePersistence.findByPrimaryKey(websiteId);
	}

	public List<Website> getWebsites() throws SystemException {
		return websitePersistence.findAll();
	}

	public List<Website> getWebsites(
			long companyId, String className, long classPK)
		throws SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		return websitePersistence.findByC_C_C(companyId, classNameId, classPK);
	}

	public Website updateWebsite(
			long websiteId, String url, int typeId, boolean primary)
		throws PortalException, SystemException {

		validate(websiteId, 0, 0, 0, url, typeId, primary);

		Website website = websitePersistence.findByPrimaryKey(websiteId);

		website.setModifiedDate(new Date());
		website.setUrl(url);
		website.setTypeId(typeId);
		website.setPrimary(primary);

		websitePersistence.update(website, false);

		return website;
	}

	protected void validate(
			long websiteId, long companyId, long classNameId, long classPK,
			String url, int typeId, boolean primary)
		throws PortalException, SystemException {

		if (!Validator.isUrl(url)) {
			throw new WebsiteURLException();
		}

		if (websiteId > 0) {
			Website website = websitePersistence.findByPrimaryKey(websiteId);

			companyId = website.getCompanyId();
			classNameId = website.getClassNameId();
			classPK = website.getClassPK();
		}

		listTypeService.validate(
			typeId, classNameId, ListTypeConstants.WEBSITE);

		validate(websiteId, companyId, classNameId, classPK, primary);
	}

	protected void validate(
			long websiteId, long companyId, long classNameId, long classPK,
			boolean primary)
		throws SystemException {

		// Check to make sure there isn't another website with the same company
		// id, class name, and class pk that also has primary set to true

		if (primary) {
			Iterator<Website> itr = websitePersistence.findByC_C_C_P(
				companyId, classNameId, classPK, primary).iterator();

			while (itr.hasNext()) {
				Website website = itr.next();

				if ((websiteId <= 0) || (website.getWebsiteId() != websiteId)) {
					website.setPrimary(false);

					websitePersistence.update(website, false);
				}
			}
		}
	}

}