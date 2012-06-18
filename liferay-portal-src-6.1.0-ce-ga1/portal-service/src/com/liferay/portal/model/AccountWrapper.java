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
 * This class is a wrapper for {@link Account}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Account
 * @generated
 */
public class AccountWrapper implements Account, ModelWrapper<Account> {
	public AccountWrapper(Account account) {
		_account = account;
	}

	public Class<?> getModelClass() {
		return Account.class;
	}

	public String getModelClassName() {
		return Account.class.getName();
	}

	/**
	* Returns the primary key of this account.
	*
	* @return the primary key of this account
	*/
	public long getPrimaryKey() {
		return _account.getPrimaryKey();
	}

	/**
	* Sets the primary key of this account.
	*
	* @param primaryKey the primary key of this account
	*/
	public void setPrimaryKey(long primaryKey) {
		_account.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the account ID of this account.
	*
	* @return the account ID of this account
	*/
	public long getAccountId() {
		return _account.getAccountId();
	}

	/**
	* Sets the account ID of this account.
	*
	* @param accountId the account ID of this account
	*/
	public void setAccountId(long accountId) {
		_account.setAccountId(accountId);
	}

	/**
	* Returns the company ID of this account.
	*
	* @return the company ID of this account
	*/
	public long getCompanyId() {
		return _account.getCompanyId();
	}

	/**
	* Sets the company ID of this account.
	*
	* @param companyId the company ID of this account
	*/
	public void setCompanyId(long companyId) {
		_account.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this account.
	*
	* @return the user ID of this account
	*/
	public long getUserId() {
		return _account.getUserId();
	}

	/**
	* Sets the user ID of this account.
	*
	* @param userId the user ID of this account
	*/
	public void setUserId(long userId) {
		_account.setUserId(userId);
	}

	/**
	* Returns the user uuid of this account.
	*
	* @return the user uuid of this account
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _account.getUserUuid();
	}

	/**
	* Sets the user uuid of this account.
	*
	* @param userUuid the user uuid of this account
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_account.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this account.
	*
	* @return the user name of this account
	*/
	public java.lang.String getUserName() {
		return _account.getUserName();
	}

	/**
	* Sets the user name of this account.
	*
	* @param userName the user name of this account
	*/
	public void setUserName(java.lang.String userName) {
		_account.setUserName(userName);
	}

	/**
	* Returns the create date of this account.
	*
	* @return the create date of this account
	*/
	public java.util.Date getCreateDate() {
		return _account.getCreateDate();
	}

	/**
	* Sets the create date of this account.
	*
	* @param createDate the create date of this account
	*/
	public void setCreateDate(java.util.Date createDate) {
		_account.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this account.
	*
	* @return the modified date of this account
	*/
	public java.util.Date getModifiedDate() {
		return _account.getModifiedDate();
	}

	/**
	* Sets the modified date of this account.
	*
	* @param modifiedDate the modified date of this account
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_account.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the parent account ID of this account.
	*
	* @return the parent account ID of this account
	*/
	public long getParentAccountId() {
		return _account.getParentAccountId();
	}

	/**
	* Sets the parent account ID of this account.
	*
	* @param parentAccountId the parent account ID of this account
	*/
	public void setParentAccountId(long parentAccountId) {
		_account.setParentAccountId(parentAccountId);
	}

	/**
	* Returns the name of this account.
	*
	* @return the name of this account
	*/
	public java.lang.String getName() {
		return _account.getName();
	}

	/**
	* Sets the name of this account.
	*
	* @param name the name of this account
	*/
	public void setName(java.lang.String name) {
		_account.setName(name);
	}

	/**
	* Returns the legal name of this account.
	*
	* @return the legal name of this account
	*/
	public java.lang.String getLegalName() {
		return _account.getLegalName();
	}

	/**
	* Sets the legal name of this account.
	*
	* @param legalName the legal name of this account
	*/
	public void setLegalName(java.lang.String legalName) {
		_account.setLegalName(legalName);
	}

	/**
	* Returns the legal ID of this account.
	*
	* @return the legal ID of this account
	*/
	public java.lang.String getLegalId() {
		return _account.getLegalId();
	}

	/**
	* Sets the legal ID of this account.
	*
	* @param legalId the legal ID of this account
	*/
	public void setLegalId(java.lang.String legalId) {
		_account.setLegalId(legalId);
	}

	/**
	* Returns the legal type of this account.
	*
	* @return the legal type of this account
	*/
	public java.lang.String getLegalType() {
		return _account.getLegalType();
	}

	/**
	* Sets the legal type of this account.
	*
	* @param legalType the legal type of this account
	*/
	public void setLegalType(java.lang.String legalType) {
		_account.setLegalType(legalType);
	}

	/**
	* Returns the sic code of this account.
	*
	* @return the sic code of this account
	*/
	public java.lang.String getSicCode() {
		return _account.getSicCode();
	}

	/**
	* Sets the sic code of this account.
	*
	* @param sicCode the sic code of this account
	*/
	public void setSicCode(java.lang.String sicCode) {
		_account.setSicCode(sicCode);
	}

	/**
	* Returns the ticker symbol of this account.
	*
	* @return the ticker symbol of this account
	*/
	public java.lang.String getTickerSymbol() {
		return _account.getTickerSymbol();
	}

	/**
	* Sets the ticker symbol of this account.
	*
	* @param tickerSymbol the ticker symbol of this account
	*/
	public void setTickerSymbol(java.lang.String tickerSymbol) {
		_account.setTickerSymbol(tickerSymbol);
	}

	/**
	* Returns the industry of this account.
	*
	* @return the industry of this account
	*/
	public java.lang.String getIndustry() {
		return _account.getIndustry();
	}

	/**
	* Sets the industry of this account.
	*
	* @param industry the industry of this account
	*/
	public void setIndustry(java.lang.String industry) {
		_account.setIndustry(industry);
	}

	/**
	* Returns the type of this account.
	*
	* @return the type of this account
	*/
	public java.lang.String getType() {
		return _account.getType();
	}

	/**
	* Sets the type of this account.
	*
	* @param type the type of this account
	*/
	public void setType(java.lang.String type) {
		_account.setType(type);
	}

	/**
	* Returns the size of this account.
	*
	* @return the size of this account
	*/
	public java.lang.String getSize() {
		return _account.getSize();
	}

	/**
	* Sets the size of this account.
	*
	* @param size the size of this account
	*/
	public void setSize(java.lang.String size) {
		_account.setSize(size);
	}

	public boolean isNew() {
		return _account.isNew();
	}

	public void setNew(boolean n) {
		_account.setNew(n);
	}

	public boolean isCachedModel() {
		return _account.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_account.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _account.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _account.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_account.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _account.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_account.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AccountWrapper((Account)_account.clone());
	}

	public int compareTo(com.liferay.portal.model.Account account) {
		return _account.compareTo(account);
	}

	@Override
	public int hashCode() {
		return _account.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portal.model.Account> toCacheModel() {
		return _account.toCacheModel();
	}

	public com.liferay.portal.model.Account toEscapedModel() {
		return new AccountWrapper(_account.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _account.toString();
	}

	public java.lang.String toXmlString() {
		return _account.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_account.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public Account getWrappedAccount() {
		return _account;
	}

	public Account getWrappedModel() {
		return _account;
	}

	public void resetOriginalValues() {
		_account.resetOriginalValues();
	}

	private Account _account;
}