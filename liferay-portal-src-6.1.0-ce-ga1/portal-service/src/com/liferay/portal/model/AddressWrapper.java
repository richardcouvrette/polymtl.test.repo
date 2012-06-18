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
 * This class is a wrapper for {@link Address}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Address
 * @generated
 */
public class AddressWrapper implements Address, ModelWrapper<Address> {
	public AddressWrapper(Address address) {
		_address = address;
	}

	public Class<?> getModelClass() {
		return Address.class;
	}

	public String getModelClassName() {
		return Address.class.getName();
	}

	/**
	* Returns the primary key of this address.
	*
	* @return the primary key of this address
	*/
	public long getPrimaryKey() {
		return _address.getPrimaryKey();
	}

	/**
	* Sets the primary key of this address.
	*
	* @param primaryKey the primary key of this address
	*/
	public void setPrimaryKey(long primaryKey) {
		_address.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the address ID of this address.
	*
	* @return the address ID of this address
	*/
	public long getAddressId() {
		return _address.getAddressId();
	}

	/**
	* Sets the address ID of this address.
	*
	* @param addressId the address ID of this address
	*/
	public void setAddressId(long addressId) {
		_address.setAddressId(addressId);
	}

	/**
	* Returns the company ID of this address.
	*
	* @return the company ID of this address
	*/
	public long getCompanyId() {
		return _address.getCompanyId();
	}

	/**
	* Sets the company ID of this address.
	*
	* @param companyId the company ID of this address
	*/
	public void setCompanyId(long companyId) {
		_address.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this address.
	*
	* @return the user ID of this address
	*/
	public long getUserId() {
		return _address.getUserId();
	}

	/**
	* Sets the user ID of this address.
	*
	* @param userId the user ID of this address
	*/
	public void setUserId(long userId) {
		_address.setUserId(userId);
	}

	/**
	* Returns the user uuid of this address.
	*
	* @return the user uuid of this address
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _address.getUserUuid();
	}

	/**
	* Sets the user uuid of this address.
	*
	* @param userUuid the user uuid of this address
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_address.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this address.
	*
	* @return the user name of this address
	*/
	public java.lang.String getUserName() {
		return _address.getUserName();
	}

	/**
	* Sets the user name of this address.
	*
	* @param userName the user name of this address
	*/
	public void setUserName(java.lang.String userName) {
		_address.setUserName(userName);
	}

	/**
	* Returns the create date of this address.
	*
	* @return the create date of this address
	*/
	public java.util.Date getCreateDate() {
		return _address.getCreateDate();
	}

	/**
	* Sets the create date of this address.
	*
	* @param createDate the create date of this address
	*/
	public void setCreateDate(java.util.Date createDate) {
		_address.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this address.
	*
	* @return the modified date of this address
	*/
	public java.util.Date getModifiedDate() {
		return _address.getModifiedDate();
	}

	/**
	* Sets the modified date of this address.
	*
	* @param modifiedDate the modified date of this address
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_address.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the fully qualified class name of this address.
	*
	* @return the fully qualified class name of this address
	*/
	public java.lang.String getClassName() {
		return _address.getClassName();
	}

	/**
	* Returns the class name ID of this address.
	*
	* @return the class name ID of this address
	*/
	public long getClassNameId() {
		return _address.getClassNameId();
	}

	/**
	* Sets the class name ID of this address.
	*
	* @param classNameId the class name ID of this address
	*/
	public void setClassNameId(long classNameId) {
		_address.setClassNameId(classNameId);
	}

	/**
	* Returns the class p k of this address.
	*
	* @return the class p k of this address
	*/
	public long getClassPK() {
		return _address.getClassPK();
	}

	/**
	* Sets the class p k of this address.
	*
	* @param classPK the class p k of this address
	*/
	public void setClassPK(long classPK) {
		_address.setClassPK(classPK);
	}

	/**
	* Returns the street1 of this address.
	*
	* @return the street1 of this address
	*/
	public java.lang.String getStreet1() {
		return _address.getStreet1();
	}

	/**
	* Sets the street1 of this address.
	*
	* @param street1 the street1 of this address
	*/
	public void setStreet1(java.lang.String street1) {
		_address.setStreet1(street1);
	}

	/**
	* Returns the street2 of this address.
	*
	* @return the street2 of this address
	*/
	public java.lang.String getStreet2() {
		return _address.getStreet2();
	}

	/**
	* Sets the street2 of this address.
	*
	* @param street2 the street2 of this address
	*/
	public void setStreet2(java.lang.String street2) {
		_address.setStreet2(street2);
	}

	/**
	* Returns the street3 of this address.
	*
	* @return the street3 of this address
	*/
	public java.lang.String getStreet3() {
		return _address.getStreet3();
	}

	/**
	* Sets the street3 of this address.
	*
	* @param street3 the street3 of this address
	*/
	public void setStreet3(java.lang.String street3) {
		_address.setStreet3(street3);
	}

	/**
	* Returns the city of this address.
	*
	* @return the city of this address
	*/
	public java.lang.String getCity() {
		return _address.getCity();
	}

	/**
	* Sets the city of this address.
	*
	* @param city the city of this address
	*/
	public void setCity(java.lang.String city) {
		_address.setCity(city);
	}

	/**
	* Returns the zip of this address.
	*
	* @return the zip of this address
	*/
	public java.lang.String getZip() {
		return _address.getZip();
	}

	/**
	* Sets the zip of this address.
	*
	* @param zip the zip of this address
	*/
	public void setZip(java.lang.String zip) {
		_address.setZip(zip);
	}

	/**
	* Returns the region ID of this address.
	*
	* @return the region ID of this address
	*/
	public long getRegionId() {
		return _address.getRegionId();
	}

	/**
	* Sets the region ID of this address.
	*
	* @param regionId the region ID of this address
	*/
	public void setRegionId(long regionId) {
		_address.setRegionId(regionId);
	}

	/**
	* Returns the country ID of this address.
	*
	* @return the country ID of this address
	*/
	public long getCountryId() {
		return _address.getCountryId();
	}

	/**
	* Sets the country ID of this address.
	*
	* @param countryId the country ID of this address
	*/
	public void setCountryId(long countryId) {
		_address.setCountryId(countryId);
	}

	/**
	* Returns the type ID of this address.
	*
	* @return the type ID of this address
	*/
	public int getTypeId() {
		return _address.getTypeId();
	}

	/**
	* Sets the type ID of this address.
	*
	* @param typeId the type ID of this address
	*/
	public void setTypeId(int typeId) {
		_address.setTypeId(typeId);
	}

	/**
	* Returns the mailing of this address.
	*
	* @return the mailing of this address
	*/
	public boolean getMailing() {
		return _address.getMailing();
	}

	/**
	* Returns <code>true</code> if this address is mailing.
	*
	* @return <code>true</code> if this address is mailing; <code>false</code> otherwise
	*/
	public boolean isMailing() {
		return _address.isMailing();
	}

	/**
	* Sets whether this address is mailing.
	*
	* @param mailing the mailing of this address
	*/
	public void setMailing(boolean mailing) {
		_address.setMailing(mailing);
	}

	/**
	* Returns the primary of this address.
	*
	* @return the primary of this address
	*/
	public boolean getPrimary() {
		return _address.getPrimary();
	}

	/**
	* Returns <code>true</code> if this address is primary.
	*
	* @return <code>true</code> if this address is primary; <code>false</code> otherwise
	*/
	public boolean isPrimary() {
		return _address.isPrimary();
	}

	/**
	* Sets whether this address is primary.
	*
	* @param primary the primary of this address
	*/
	public void setPrimary(boolean primary) {
		_address.setPrimary(primary);
	}

	public boolean isNew() {
		return _address.isNew();
	}

	public void setNew(boolean n) {
		_address.setNew(n);
	}

	public boolean isCachedModel() {
		return _address.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_address.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _address.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _address.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_address.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _address.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_address.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AddressWrapper((Address)_address.clone());
	}

	public int compareTo(com.liferay.portal.model.Address address) {
		return _address.compareTo(address);
	}

	@Override
	public int hashCode() {
		return _address.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portal.model.Address> toCacheModel() {
		return _address.toCacheModel();
	}

	public com.liferay.portal.model.Address toEscapedModel() {
		return new AddressWrapper(_address.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _address.toString();
	}

	public java.lang.String toXmlString() {
		return _address.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_address.persist();
	}

	public com.liferay.portal.model.Region getRegion() {
		return _address.getRegion();
	}

	public com.liferay.portal.model.Country getCountry() {
		return _address.getCountry();
	}

	public com.liferay.portal.model.ListType getType() {
		return _address.getType();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public Address getWrappedAddress() {
		return _address;
	}

	public Address getWrappedModel() {
		return _address;
	}

	public void resetOriginalValues() {
		_address.resetOriginalValues();
	}

	private Address _address;
}