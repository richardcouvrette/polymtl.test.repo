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

package com.liferay.portlet.mobiledevicerules.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link MDRRuleGroup}.
 * </p>
 *
 * @author    Edward C. Han
 * @see       MDRRuleGroup
 * @generated
 */
public class MDRRuleGroupWrapper implements MDRRuleGroup,
	ModelWrapper<MDRRuleGroup> {
	public MDRRuleGroupWrapper(MDRRuleGroup mdrRuleGroup) {
		_mdrRuleGroup = mdrRuleGroup;
	}

	public Class<?> getModelClass() {
		return MDRRuleGroup.class;
	}

	public String getModelClassName() {
		return MDRRuleGroup.class.getName();
	}

	/**
	* Returns the primary key of this m d r rule group.
	*
	* @return the primary key of this m d r rule group
	*/
	public long getPrimaryKey() {
		return _mdrRuleGroup.getPrimaryKey();
	}

	/**
	* Sets the primary key of this m d r rule group.
	*
	* @param primaryKey the primary key of this m d r rule group
	*/
	public void setPrimaryKey(long primaryKey) {
		_mdrRuleGroup.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this m d r rule group.
	*
	* @return the uuid of this m d r rule group
	*/
	public java.lang.String getUuid() {
		return _mdrRuleGroup.getUuid();
	}

	/**
	* Sets the uuid of this m d r rule group.
	*
	* @param uuid the uuid of this m d r rule group
	*/
	public void setUuid(java.lang.String uuid) {
		_mdrRuleGroup.setUuid(uuid);
	}

	/**
	* Returns the rule group ID of this m d r rule group.
	*
	* @return the rule group ID of this m d r rule group
	*/
	public long getRuleGroupId() {
		return _mdrRuleGroup.getRuleGroupId();
	}

	/**
	* Sets the rule group ID of this m d r rule group.
	*
	* @param ruleGroupId the rule group ID of this m d r rule group
	*/
	public void setRuleGroupId(long ruleGroupId) {
		_mdrRuleGroup.setRuleGroupId(ruleGroupId);
	}

	/**
	* Returns the group ID of this m d r rule group.
	*
	* @return the group ID of this m d r rule group
	*/
	public long getGroupId() {
		return _mdrRuleGroup.getGroupId();
	}

	/**
	* Sets the group ID of this m d r rule group.
	*
	* @param groupId the group ID of this m d r rule group
	*/
	public void setGroupId(long groupId) {
		_mdrRuleGroup.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this m d r rule group.
	*
	* @return the company ID of this m d r rule group
	*/
	public long getCompanyId() {
		return _mdrRuleGroup.getCompanyId();
	}

	/**
	* Sets the company ID of this m d r rule group.
	*
	* @param companyId the company ID of this m d r rule group
	*/
	public void setCompanyId(long companyId) {
		_mdrRuleGroup.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this m d r rule group.
	*
	* @return the user ID of this m d r rule group
	*/
	public long getUserId() {
		return _mdrRuleGroup.getUserId();
	}

	/**
	* Sets the user ID of this m d r rule group.
	*
	* @param userId the user ID of this m d r rule group
	*/
	public void setUserId(long userId) {
		_mdrRuleGroup.setUserId(userId);
	}

	/**
	* Returns the user uuid of this m d r rule group.
	*
	* @return the user uuid of this m d r rule group
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mdrRuleGroup.getUserUuid();
	}

	/**
	* Sets the user uuid of this m d r rule group.
	*
	* @param userUuid the user uuid of this m d r rule group
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_mdrRuleGroup.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this m d r rule group.
	*
	* @return the user name of this m d r rule group
	*/
	public java.lang.String getUserName() {
		return _mdrRuleGroup.getUserName();
	}

	/**
	* Sets the user name of this m d r rule group.
	*
	* @param userName the user name of this m d r rule group
	*/
	public void setUserName(java.lang.String userName) {
		_mdrRuleGroup.setUserName(userName);
	}

	/**
	* Returns the create date of this m d r rule group.
	*
	* @return the create date of this m d r rule group
	*/
	public java.util.Date getCreateDate() {
		return _mdrRuleGroup.getCreateDate();
	}

	/**
	* Sets the create date of this m d r rule group.
	*
	* @param createDate the create date of this m d r rule group
	*/
	public void setCreateDate(java.util.Date createDate) {
		_mdrRuleGroup.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this m d r rule group.
	*
	* @return the modified date of this m d r rule group
	*/
	public java.util.Date getModifiedDate() {
		return _mdrRuleGroup.getModifiedDate();
	}

	/**
	* Sets the modified date of this m d r rule group.
	*
	* @param modifiedDate the modified date of this m d r rule group
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_mdrRuleGroup.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the name of this m d r rule group.
	*
	* @return the name of this m d r rule group
	*/
	public java.lang.String getName() {
		return _mdrRuleGroup.getName();
	}

	/**
	* Returns the localized name of this m d r rule group in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized name of this m d r rule group
	*/
	public java.lang.String getName(java.util.Locale locale) {
		return _mdrRuleGroup.getName(locale);
	}

	/**
	* Returns the localized name of this m d r rule group in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this m d r rule group. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	public java.lang.String getName(java.util.Locale locale, boolean useDefault) {
		return _mdrRuleGroup.getName(locale, useDefault);
	}

	/**
	* Returns the localized name of this m d r rule group in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized name of this m d r rule group
	*/
	public java.lang.String getName(java.lang.String languageId) {
		return _mdrRuleGroup.getName(languageId);
	}

	/**
	* Returns the localized name of this m d r rule group in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this m d r rule group
	*/
	public java.lang.String getName(java.lang.String languageId,
		boolean useDefault) {
		return _mdrRuleGroup.getName(languageId, useDefault);
	}

	public java.lang.String getNameCurrentLanguageId() {
		return _mdrRuleGroup.getNameCurrentLanguageId();
	}

	public java.lang.String getNameCurrentValue() {
		return _mdrRuleGroup.getNameCurrentValue();
	}

	/**
	* Returns a map of the locales and localized names of this m d r rule group.
	*
	* @return the locales and localized names of this m d r rule group
	*/
	public java.util.Map<java.util.Locale, java.lang.String> getNameMap() {
		return _mdrRuleGroup.getNameMap();
	}

	/**
	* Sets the name of this m d r rule group.
	*
	* @param name the name of this m d r rule group
	*/
	public void setName(java.lang.String name) {
		_mdrRuleGroup.setName(name);
	}

	/**
	* Sets the localized name of this m d r rule group in the language.
	*
	* @param name the localized name of this m d r rule group
	* @param locale the locale of the language
	*/
	public void setName(java.lang.String name, java.util.Locale locale) {
		_mdrRuleGroup.setName(name, locale);
	}

	/**
	* Sets the localized name of this m d r rule group in the language, and sets the default locale.
	*
	* @param name the localized name of this m d r rule group
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	public void setName(java.lang.String name, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_mdrRuleGroup.setName(name, locale, defaultLocale);
	}

	public void setNameCurrentLanguageId(java.lang.String languageId) {
		_mdrRuleGroup.setNameCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized names of this m d r rule group from the map of locales and localized names.
	*
	* @param nameMap the locales and localized names of this m d r rule group
	*/
	public void setNameMap(
		java.util.Map<java.util.Locale, java.lang.String> nameMap) {
		_mdrRuleGroup.setNameMap(nameMap);
	}

	/**
	* Sets the localized names of this m d r rule group from the map of locales and localized names, and sets the default locale.
	*
	* @param nameMap the locales and localized names of this m d r rule group
	* @param defaultLocale the default locale
	*/
	public void setNameMap(
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Locale defaultLocale) {
		_mdrRuleGroup.setNameMap(nameMap, defaultLocale);
	}

	/**
	* Returns the description of this m d r rule group.
	*
	* @return the description of this m d r rule group
	*/
	public java.lang.String getDescription() {
		return _mdrRuleGroup.getDescription();
	}

	/**
	* Returns the localized description of this m d r rule group in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized description of this m d r rule group
	*/
	public java.lang.String getDescription(java.util.Locale locale) {
		return _mdrRuleGroup.getDescription(locale);
	}

	/**
	* Returns the localized description of this m d r rule group in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this m d r rule group. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	public java.lang.String getDescription(java.util.Locale locale,
		boolean useDefault) {
		return _mdrRuleGroup.getDescription(locale, useDefault);
	}

	/**
	* Returns the localized description of this m d r rule group in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized description of this m d r rule group
	*/
	public java.lang.String getDescription(java.lang.String languageId) {
		return _mdrRuleGroup.getDescription(languageId);
	}

	/**
	* Returns the localized description of this m d r rule group in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this m d r rule group
	*/
	public java.lang.String getDescription(java.lang.String languageId,
		boolean useDefault) {
		return _mdrRuleGroup.getDescription(languageId, useDefault);
	}

	public java.lang.String getDescriptionCurrentLanguageId() {
		return _mdrRuleGroup.getDescriptionCurrentLanguageId();
	}

	public java.lang.String getDescriptionCurrentValue() {
		return _mdrRuleGroup.getDescriptionCurrentValue();
	}

	/**
	* Returns a map of the locales and localized descriptions of this m d r rule group.
	*
	* @return the locales and localized descriptions of this m d r rule group
	*/
	public java.util.Map<java.util.Locale, java.lang.String> getDescriptionMap() {
		return _mdrRuleGroup.getDescriptionMap();
	}

	/**
	* Sets the description of this m d r rule group.
	*
	* @param description the description of this m d r rule group
	*/
	public void setDescription(java.lang.String description) {
		_mdrRuleGroup.setDescription(description);
	}

	/**
	* Sets the localized description of this m d r rule group in the language.
	*
	* @param description the localized description of this m d r rule group
	* @param locale the locale of the language
	*/
	public void setDescription(java.lang.String description,
		java.util.Locale locale) {
		_mdrRuleGroup.setDescription(description, locale);
	}

	/**
	* Sets the localized description of this m d r rule group in the language, and sets the default locale.
	*
	* @param description the localized description of this m d r rule group
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	public void setDescription(java.lang.String description,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_mdrRuleGroup.setDescription(description, locale, defaultLocale);
	}

	public void setDescriptionCurrentLanguageId(java.lang.String languageId) {
		_mdrRuleGroup.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized descriptions of this m d r rule group from the map of locales and localized descriptions.
	*
	* @param descriptionMap the locales and localized descriptions of this m d r rule group
	*/
	public void setDescriptionMap(
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap) {
		_mdrRuleGroup.setDescriptionMap(descriptionMap);
	}

	/**
	* Sets the localized descriptions of this m d r rule group from the map of locales and localized descriptions, and sets the default locale.
	*
	* @param descriptionMap the locales and localized descriptions of this m d r rule group
	* @param defaultLocale the default locale
	*/
	public void setDescriptionMap(
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Locale defaultLocale) {
		_mdrRuleGroup.setDescriptionMap(descriptionMap, defaultLocale);
	}

	public boolean isNew() {
		return _mdrRuleGroup.isNew();
	}

	public void setNew(boolean n) {
		_mdrRuleGroup.setNew(n);
	}

	public boolean isCachedModel() {
		return _mdrRuleGroup.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_mdrRuleGroup.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _mdrRuleGroup.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _mdrRuleGroup.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_mdrRuleGroup.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _mdrRuleGroup.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_mdrRuleGroup.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new MDRRuleGroupWrapper((MDRRuleGroup)_mdrRuleGroup.clone());
	}

	public int compareTo(
		com.liferay.portlet.mobiledevicerules.model.MDRRuleGroup mdrRuleGroup) {
		return _mdrRuleGroup.compareTo(mdrRuleGroup);
	}

	@Override
	public int hashCode() {
		return _mdrRuleGroup.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portlet.mobiledevicerules.model.MDRRuleGroup> toCacheModel() {
		return _mdrRuleGroup.toCacheModel();
	}

	public com.liferay.portlet.mobiledevicerules.model.MDRRuleGroup toEscapedModel() {
		return new MDRRuleGroupWrapper(_mdrRuleGroup.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _mdrRuleGroup.toString();
	}

	public java.lang.String toXmlString() {
		return _mdrRuleGroup.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_mdrRuleGroup.persist();
	}

	public java.util.List<com.liferay.portlet.mobiledevicerules.model.MDRRule> getRules()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mdrRuleGroup.getRules();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public MDRRuleGroup getWrappedMDRRuleGroup() {
		return _mdrRuleGroup;
	}

	public MDRRuleGroup getWrappedModel() {
		return _mdrRuleGroup;
	}

	public void resetOriginalValues() {
		_mdrRuleGroup.resetOriginalValues();
	}

	private MDRRuleGroup _mdrRuleGroup;
}