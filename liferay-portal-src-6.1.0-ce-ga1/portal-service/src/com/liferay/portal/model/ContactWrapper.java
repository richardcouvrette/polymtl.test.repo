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

package com.liferay.portal.model;

/**
 * <p>
 * This class is a wrapper for {@link Contact}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Contact
 * @generated
 */
public class ContactWrapper implements Contact, ModelWrapper<Contact> {
	public ContactWrapper(Contact contact) {
		_contact = contact;
	}

	public Class<?> getModelClass() {
		return Contact.class;
	}

	public String getModelClassName() {
		return Contact.class.getName();
	}

	/**
	* Returns the primary key of this contact.
	*
	* @return the primary key of this contact
	*/
	public long getPrimaryKey() {
		return _contact.getPrimaryKey();
	}

	/**
	* Sets the primary key of this contact.
	*
	* @param primaryKey the primary key of this contact
	*/
	public void setPrimaryKey(long primaryKey) {
		_contact.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the contact ID of this contact.
	*
	* @return the contact ID of this contact
	*/
	public long getContactId() {
		return _contact.getContactId();
	}

	/**
	* Sets the contact ID of this contact.
	*
	* @param contactId the contact ID of this contact
	*/
	public void setContactId(long contactId) {
		_contact.setContactId(contactId);
	}

	/**
	* Returns the company ID of this contact.
	*
	* @return the company ID of this contact
	*/
	public long getCompanyId() {
		return _contact.getCompanyId();
	}

	/**
	* Sets the company ID of this contact.
	*
	* @param companyId the company ID of this contact
	*/
	public void setCompanyId(long companyId) {
		_contact.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this contact.
	*
	* @return the user ID of this contact
	*/
	public long getUserId() {
		return _contact.getUserId();
	}

	/**
	* Sets the user ID of this contact.
	*
	* @param userId the user ID of this contact
	*/
	public void setUserId(long userId) {
		_contact.setUserId(userId);
	}

	/**
	* Returns the user uuid of this contact.
	*
	* @return the user uuid of this contact
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _contact.getUserUuid();
	}

	/**
	* Sets the user uuid of this contact.
	*
	* @param userUuid the user uuid of this contact
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_contact.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this contact.
	*
	* @return the user name of this contact
	*/
	public java.lang.String getUserName() {
		return _contact.getUserName();
	}

	/**
	* Sets the user name of this contact.
	*
	* @param userName the user name of this contact
	*/
	public void setUserName(java.lang.String userName) {
		_contact.setUserName(userName);
	}

	/**
	* Returns the create date of this contact.
	*
	* @return the create date of this contact
	*/
	public java.util.Date getCreateDate() {
		return _contact.getCreateDate();
	}

	/**
	* Sets the create date of this contact.
	*
	* @param createDate the create date of this contact
	*/
	public void setCreateDate(java.util.Date createDate) {
		_contact.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this contact.
	*
	* @return the modified date of this contact
	*/
	public java.util.Date getModifiedDate() {
		return _contact.getModifiedDate();
	}

	/**
	* Sets the modified date of this contact.
	*
	* @param modifiedDate the modified date of this contact
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_contact.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the account ID of this contact.
	*
	* @return the account ID of this contact
	*/
	public long getAccountId() {
		return _contact.getAccountId();
	}

	/**
	* Sets the account ID of this contact.
	*
	* @param accountId the account ID of this contact
	*/
	public void setAccountId(long accountId) {
		_contact.setAccountId(accountId);
	}

	/**
	* Returns the parent contact ID of this contact.
	*
	* @return the parent contact ID of this contact
	*/
	public long getParentContactId() {
		return _contact.getParentContactId();
	}

	/**
	* Sets the parent contact ID of this contact.
	*
	* @param parentContactId the parent contact ID of this contact
	*/
	public void setParentContactId(long parentContactId) {
		_contact.setParentContactId(parentContactId);
	}

	/**
	* Returns the first name of this contact.
	*
	* @return the first name of this contact
	*/
	public java.lang.String getFirstName() {
		return _contact.getFirstName();
	}

	/**
	* Sets the first name of this contact.
	*
	* @param firstName the first name of this contact
	*/
	public void setFirstName(java.lang.String firstName) {
		_contact.setFirstName(firstName);
	}

	/**
	* Returns the middle name of this contact.
	*
	* @return the middle name of this contact
	*/
	public java.lang.String getMiddleName() {
		return _contact.getMiddleName();
	}

	/**
	* Sets the middle name of this contact.
	*
	* @param middleName the middle name of this contact
	*/
	public void setMiddleName(java.lang.String middleName) {
		_contact.setMiddleName(middleName);
	}

	/**
	* Returns the last name of this contact.
	*
	* @return the last name of this contact
	*/
	public java.lang.String getLastName() {
		return _contact.getLastName();
	}

	/**
	* Sets the last name of this contact.
	*
	* @param lastName the last name of this contact
	*/
	public void setLastName(java.lang.String lastName) {
		_contact.setLastName(lastName);
	}

	/**
	* Returns the prefix ID of this contact.
	*
	* @return the prefix ID of this contact
	*/
	public int getPrefixId() {
		return _contact.getPrefixId();
	}

	/**
	* Sets the prefix ID of this contact.
	*
	* @param prefixId the prefix ID of this contact
	*/
	public void setPrefixId(int prefixId) {
		_contact.setPrefixId(prefixId);
	}

	/**
	* Returns the suffix ID of this contact.
	*
	* @return the suffix ID of this contact
	*/
	public int getSuffixId() {
		return _contact.getSuffixId();
	}

	/**
	* Sets the suffix ID of this contact.
	*
	* @param suffixId the suffix ID of this contact
	*/
	public void setSuffixId(int suffixId) {
		_contact.setSuffixId(suffixId);
	}

	/**
	* Returns the male of this contact.
	*
	* @return the male of this contact
	*/
	public boolean getMale() {
		return _contact.getMale();
	}

	/**
	* Returns <code>true</code> if this contact is male.
	*
	* @return <code>true</code> if this contact is male; <code>false</code> otherwise
	*/
	public boolean isMale() {
		return _contact.isMale();
	}

	/**
	* Sets whether this contact is male.
	*
	* @param male the male of this contact
	*/
	public void setMale(boolean male) {
		_contact.setMale(male);
	}

	/**
	* Returns the birthday of this contact.
	*
	* @return the birthday of this contact
	*/
	public java.util.Date getBirthday() {
		return _contact.getBirthday();
	}

	/**
	* Sets the birthday of this contact.
	*
	* @param birthday the birthday of this contact
	*/
	public void setBirthday(java.util.Date birthday) {
		_contact.setBirthday(birthday);
	}

	/**
	* Returns the sms sn of this contact.
	*
	* @return the sms sn of this contact
	*/
	public java.lang.String getSmsSn() {
		return _contact.getSmsSn();
	}

	/**
	* Sets the sms sn of this contact.
	*
	* @param smsSn the sms sn of this contact
	*/
	public void setSmsSn(java.lang.String smsSn) {
		_contact.setSmsSn(smsSn);
	}

	/**
	* Returns the aim sn of this contact.
	*
	* @return the aim sn of this contact
	*/
	public java.lang.String getAimSn() {
		return _contact.getAimSn();
	}

	/**
	* Sets the aim sn of this contact.
	*
	* @param aimSn the aim sn of this contact
	*/
	public void setAimSn(java.lang.String aimSn) {
		_contact.setAimSn(aimSn);
	}

	/**
	* Returns the facebook sn of this contact.
	*
	* @return the facebook sn of this contact
	*/
	public java.lang.String getFacebookSn() {
		return _contact.getFacebookSn();
	}

	/**
	* Sets the facebook sn of this contact.
	*
	* @param facebookSn the facebook sn of this contact
	*/
	public void setFacebookSn(java.lang.String facebookSn) {
		_contact.setFacebookSn(facebookSn);
	}

	/**
	* Returns the icq sn of this contact.
	*
	* @return the icq sn of this contact
	*/
	public java.lang.String getIcqSn() {
		return _contact.getIcqSn();
	}

	/**
	* Sets the icq sn of this contact.
	*
	* @param icqSn the icq sn of this contact
	*/
	public void setIcqSn(java.lang.String icqSn) {
		_contact.setIcqSn(icqSn);
	}

	/**
	* Returns the jabber sn of this contact.
	*
	* @return the jabber sn of this contact
	*/
	public java.lang.String getJabberSn() {
		return _contact.getJabberSn();
	}

	/**
	* Sets the jabber sn of this contact.
	*
	* @param jabberSn the jabber sn of this contact
	*/
	public void setJabberSn(java.lang.String jabberSn) {
		_contact.setJabberSn(jabberSn);
	}

	/**
	* Returns the msn sn of this contact.
	*
	* @return the msn sn of this contact
	*/
	public java.lang.String getMsnSn() {
		return _contact.getMsnSn();
	}

	/**
	* Sets the msn sn of this contact.
	*
	* @param msnSn the msn sn of this contact
	*/
	public void setMsnSn(java.lang.String msnSn) {
		_contact.setMsnSn(msnSn);
	}

	/**
	* Returns the my space sn of this contact.
	*
	* @return the my space sn of this contact
	*/
	public java.lang.String getMySpaceSn() {
		return _contact.getMySpaceSn();
	}

	/**
	* Sets the my space sn of this contact.
	*
	* @param mySpaceSn the my space sn of this contact
	*/
	public void setMySpaceSn(java.lang.String mySpaceSn) {
		_contact.setMySpaceSn(mySpaceSn);
	}

	/**
	* Returns the skype sn of this contact.
	*
	* @return the skype sn of this contact
	*/
	public java.lang.String getSkypeSn() {
		return _contact.getSkypeSn();
	}

	/**
	* Sets the skype sn of this contact.
	*
	* @param skypeSn the skype sn of this contact
	*/
	public void setSkypeSn(java.lang.String skypeSn) {
		_contact.setSkypeSn(skypeSn);
	}

	/**
	* Returns the twitter sn of this contact.
	*
	* @return the twitter sn of this contact
	*/
	public java.lang.String getTwitterSn() {
		return _contact.getTwitterSn();
	}

	/**
	* Sets the twitter sn of this contact.
	*
	* @param twitterSn the twitter sn of this contact
	*/
	public void setTwitterSn(java.lang.String twitterSn) {
		_contact.setTwitterSn(twitterSn);
	}

	/**
	* Returns the ym sn of this contact.
	*
	* @return the ym sn of this contact
	*/
	public java.lang.String getYmSn() {
		return _contact.getYmSn();
	}

	/**
	* Sets the ym sn of this contact.
	*
	* @param ymSn the ym sn of this contact
	*/
	public void setYmSn(java.lang.String ymSn) {
		_contact.setYmSn(ymSn);
	}

	/**
	* Returns the employee status ID of this contact.
	*
	* @return the employee status ID of this contact
	*/
	public java.lang.String getEmployeeStatusId() {
		return _contact.getEmployeeStatusId();
	}

	/**
	* Sets the employee status ID of this contact.
	*
	* @param employeeStatusId the employee status ID of this contact
	*/
	public void setEmployeeStatusId(java.lang.String employeeStatusId) {
		_contact.setEmployeeStatusId(employeeStatusId);
	}

	/**
	* Returns the employee number of this contact.
	*
	* @return the employee number of this contact
	*/
	public java.lang.String getEmployeeNumber() {
		return _contact.getEmployeeNumber();
	}

	/**
	* Sets the employee number of this contact.
	*
	* @param employeeNumber the employee number of this contact
	*/
	public void setEmployeeNumber(java.lang.String employeeNumber) {
		_contact.setEmployeeNumber(employeeNumber);
	}

	/**
	* Returns the job title of this contact.
	*
	* @return the job title of this contact
	*/
	public java.lang.String getJobTitle() {
		return _contact.getJobTitle();
	}

	/**
	* Sets the job title of this contact.
	*
	* @param jobTitle the job title of this contact
	*/
	public void setJobTitle(java.lang.String jobTitle) {
		_contact.setJobTitle(jobTitle);
	}

	/**
	* Returns the job class of this contact.
	*
	* @return the job class of this contact
	*/
	public java.lang.String getJobClass() {
		return _contact.getJobClass();
	}

	/**
	* Sets the job class of this contact.
	*
	* @param jobClass the job class of this contact
	*/
	public void setJobClass(java.lang.String jobClass) {
		_contact.setJobClass(jobClass);
	}

	/**
	* Returns the hours of operation of this contact.
	*
	* @return the hours of operation of this contact
	*/
	public java.lang.String getHoursOfOperation() {
		return _contact.getHoursOfOperation();
	}

	/**
	* Sets the hours of operation of this contact.
	*
	* @param hoursOfOperation the hours of operation of this contact
	*/
	public void setHoursOfOperation(java.lang.String hoursOfOperation) {
		_contact.setHoursOfOperation(hoursOfOperation);
	}

	public boolean isNew() {
		return _contact.isNew();
	}

	public void setNew(boolean n) {
		_contact.setNew(n);
	}

	public boolean isCachedModel() {
		return _contact.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_contact.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _contact.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _contact.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_contact.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _contact.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_contact.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new ContactWrapper((Contact)_contact.clone());
	}

	public int compareTo(com.liferay.portal.model.Contact contact) {
		return _contact.compareTo(contact);
	}

	@Override
	public int hashCode() {
		return _contact.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portal.model.Contact> toCacheModel() {
		return _contact.toCacheModel();
	}

	public com.liferay.portal.model.Contact toEscapedModel() {
		return new ContactWrapper(_contact.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _contact.toString();
	}

	public java.lang.String toXmlString() {
		return _contact.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_contact.persist();
	}

	public java.lang.String getFullName() {
		return _contact.getFullName();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public Contact getWrappedContact() {
		return _contact;
	}

	public Contact getWrappedModel() {
		return _contact;
	}

	public void resetOriginalValues() {
		_contact.resetOriginalValues();
	}

	private Contact _contact;
}