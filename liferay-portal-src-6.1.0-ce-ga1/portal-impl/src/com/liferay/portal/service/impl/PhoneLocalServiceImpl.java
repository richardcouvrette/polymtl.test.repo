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

import com.liferay.portal.PhoneNumberException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Account;
import com.liferay.portal.model.Contact;
import com.liferay.portal.model.ListTypeConstants;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.Phone;
import com.liferay.portal.model.User;
import com.liferay.portal.service.base.PhoneLocalServiceBaseImpl;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.format.PhoneNumberUtil;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class PhoneLocalServiceImpl extends PhoneLocalServiceBaseImpl {

	public Phone addPhone(
			long userId, String className, long classPK, String number,
			String extension, int typeId, boolean primary)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
		long classNameId = PortalUtil.getClassNameId(className);
		Date now = new Date();

		number = PhoneNumberUtil.strip(number);
		extension = PhoneNumberUtil.strip(extension);

		validate(
			0, user.getCompanyId(), classNameId, classPK, number, typeId,
			primary);

		long phoneId = counterLocalService.increment();

		Phone phone = phonePersistence.create(phoneId);

		phone.setCompanyId(user.getCompanyId());
		phone.setUserId(user.getUserId());
		phone.setUserName(user.getFullName());
		phone.setCreateDate(now);
		phone.setModifiedDate(now);
		phone.setClassNameId(classNameId);
		phone.setClassPK(classPK);
		phone.setNumber(number);
		phone.setExtension(extension);
		phone.setTypeId(typeId);
		phone.setPrimary(primary);

		phonePersistence.update(phone, false);

		return phone;
	}

	@Override
	public void deletePhone(long phoneId)
		throws PortalException, SystemException {

		Phone phone = phonePersistence.findByPrimaryKey(phoneId);

		deletePhone(phone);
	}

	@Override
	public void deletePhone(Phone phone) throws SystemException {
		phonePersistence.remove(phone);
	}

	public void deletePhones(long companyId, String className, long classPK)
		throws SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		List<Phone> phones = phonePersistence.findByC_C_C(
			companyId, classNameId, classPK);

		for (Phone phone : phones) {
			deletePhone(phone);
		}
	}

	@Override
	public Phone getPhone(long phoneId)
		throws PortalException, SystemException {

		return phonePersistence.findByPrimaryKey(phoneId);
	}

	public List<Phone> getPhones() throws SystemException {
		return phonePersistence.findAll();
	}

	public List<Phone> getPhones(long companyId, String className, long classPK)
		throws SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		return phonePersistence.findByC_C_C(companyId, classNameId, classPK);
	}

	public Phone updatePhone(
			long phoneId, String number, String extension, int typeId,
			boolean primary)
		throws PortalException, SystemException {

		number = PhoneNumberUtil.strip(number);
		extension = PhoneNumberUtil.strip(extension);

		validate(phoneId, 0, 0, 0, number, typeId, primary);

		Phone phone = phonePersistence.findByPrimaryKey(phoneId);

		phone.setModifiedDate(new Date());
		phone.setNumber(number);
		phone.setExtension(extension);
		phone.setTypeId(typeId);
		phone.setPrimary(primary);

		phonePersistence.update(phone, false);

		return phone;
	}

	protected void validate(
			long phoneId, long companyId, long classNameId, long classPK,
			String number, int typeId, boolean primary)
		throws PortalException, SystemException {

		if (Validator.isNull(number)) {
			throw new PhoneNumberException();
		}

		if (phoneId > 0) {
			Phone phone = phonePersistence.findByPrimaryKey(phoneId);

			companyId = phone.getCompanyId();
			classNameId = phone.getClassNameId();
			classPK = phone.getClassPK();
		}

		if ((classNameId == PortalUtil.getClassNameId(Account.class)) ||
			(classNameId == PortalUtil.getClassNameId(Contact.class)) ||
			(classNameId == PortalUtil.getClassNameId(Organization.class))) {

			listTypeService.validate(
				typeId, classNameId, ListTypeConstants.PHONE);
		}

		validate(phoneId, companyId, classNameId, classPK, primary);
	}

	protected void validate(
			long phoneId, long companyId, long classNameId, long classPK,
			boolean primary)
		throws SystemException {

		// Check to make sure there isn't another phone with the same company
		// id, class name, and class pk that also has primary set to true

		if (primary) {
			Iterator<Phone> itr = phonePersistence.findByC_C_C_P(
				companyId, classNameId, classPK, primary).iterator();

			while (itr.hasNext()) {
				Phone phone = itr.next();

				if ((phoneId <= 0) || (phone.getPhoneId() != phoneId)) {
					phone.setPrimary(false);

					phonePersistence.update(phone, false);
				}
			}
		}
	}

}