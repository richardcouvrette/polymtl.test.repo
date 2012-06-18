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

package com.liferay.portlet.asset.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link AssetVocabulary}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AssetVocabulary
 * @generated
 */
public class AssetVocabularyWrapper implements AssetVocabulary,
	ModelWrapper<AssetVocabulary> {
	public AssetVocabularyWrapper(AssetVocabulary assetVocabulary) {
		_assetVocabulary = assetVocabulary;
	}

	public Class<?> getModelClass() {
		return AssetVocabulary.class;
	}

	public String getModelClassName() {
		return AssetVocabulary.class.getName();
	}

	/**
	* Returns the primary key of this asset vocabulary.
	*
	* @return the primary key of this asset vocabulary
	*/
	public long getPrimaryKey() {
		return _assetVocabulary.getPrimaryKey();
	}

	/**
	* Sets the primary key of this asset vocabulary.
	*
	* @param primaryKey the primary key of this asset vocabulary
	*/
	public void setPrimaryKey(long primaryKey) {
		_assetVocabulary.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this asset vocabulary.
	*
	* @return the uuid of this asset vocabulary
	*/
	public java.lang.String getUuid() {
		return _assetVocabulary.getUuid();
	}

	/**
	* Sets the uuid of this asset vocabulary.
	*
	* @param uuid the uuid of this asset vocabulary
	*/
	public void setUuid(java.lang.String uuid) {
		_assetVocabulary.setUuid(uuid);
	}

	/**
	* Returns the vocabulary ID of this asset vocabulary.
	*
	* @return the vocabulary ID of this asset vocabulary
	*/
	public long getVocabularyId() {
		return _assetVocabulary.getVocabularyId();
	}

	/**
	* Sets the vocabulary ID of this asset vocabulary.
	*
	* @param vocabularyId the vocabulary ID of this asset vocabulary
	*/
	public void setVocabularyId(long vocabularyId) {
		_assetVocabulary.setVocabularyId(vocabularyId);
	}

	/**
	* Returns the group ID of this asset vocabulary.
	*
	* @return the group ID of this asset vocabulary
	*/
	public long getGroupId() {
		return _assetVocabulary.getGroupId();
	}

	/**
	* Sets the group ID of this asset vocabulary.
	*
	* @param groupId the group ID of this asset vocabulary
	*/
	public void setGroupId(long groupId) {
		_assetVocabulary.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this asset vocabulary.
	*
	* @return the company ID of this asset vocabulary
	*/
	public long getCompanyId() {
		return _assetVocabulary.getCompanyId();
	}

	/**
	* Sets the company ID of this asset vocabulary.
	*
	* @param companyId the company ID of this asset vocabulary
	*/
	public void setCompanyId(long companyId) {
		_assetVocabulary.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this asset vocabulary.
	*
	* @return the user ID of this asset vocabulary
	*/
	public long getUserId() {
		return _assetVocabulary.getUserId();
	}

	/**
	* Sets the user ID of this asset vocabulary.
	*
	* @param userId the user ID of this asset vocabulary
	*/
	public void setUserId(long userId) {
		_assetVocabulary.setUserId(userId);
	}

	/**
	* Returns the user uuid of this asset vocabulary.
	*
	* @return the user uuid of this asset vocabulary
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetVocabulary.getUserUuid();
	}

	/**
	* Sets the user uuid of this asset vocabulary.
	*
	* @param userUuid the user uuid of this asset vocabulary
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_assetVocabulary.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this asset vocabulary.
	*
	* @return the user name of this asset vocabulary
	*/
	public java.lang.String getUserName() {
		return _assetVocabulary.getUserName();
	}

	/**
	* Sets the user name of this asset vocabulary.
	*
	* @param userName the user name of this asset vocabulary
	*/
	public void setUserName(java.lang.String userName) {
		_assetVocabulary.setUserName(userName);
	}

	/**
	* Returns the create date of this asset vocabulary.
	*
	* @return the create date of this asset vocabulary
	*/
	public java.util.Date getCreateDate() {
		return _assetVocabulary.getCreateDate();
	}

	/**
	* Sets the create date of this asset vocabulary.
	*
	* @param createDate the create date of this asset vocabulary
	*/
	public void setCreateDate(java.util.Date createDate) {
		_assetVocabulary.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this asset vocabulary.
	*
	* @return the modified date of this asset vocabulary
	*/
	public java.util.Date getModifiedDate() {
		return _assetVocabulary.getModifiedDate();
	}

	/**
	* Sets the modified date of this asset vocabulary.
	*
	* @param modifiedDate the modified date of this asset vocabulary
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_assetVocabulary.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the name of this asset vocabulary.
	*
	* @return the name of this asset vocabulary
	*/
	public java.lang.String getName() {
		return _assetVocabulary.getName();
	}

	/**
	* Sets the name of this asset vocabulary.
	*
	* @param name the name of this asset vocabulary
	*/
	public void setName(java.lang.String name) {
		_assetVocabulary.setName(name);
	}

	/**
	* Returns the title of this asset vocabulary.
	*
	* @return the title of this asset vocabulary
	*/
	public java.lang.String getTitle() {
		return _assetVocabulary.getTitle();
	}

	/**
	* Returns the localized title of this asset vocabulary in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized title of this asset vocabulary
	*/
	public java.lang.String getTitle(java.util.Locale locale) {
		return _assetVocabulary.getTitle(locale);
	}

	/**
	* Returns the localized title of this asset vocabulary in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this asset vocabulary. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	public java.lang.String getTitle(java.util.Locale locale, boolean useDefault) {
		return _assetVocabulary.getTitle(locale, useDefault);
	}

	/**
	* Returns the localized title of this asset vocabulary in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized title of this asset vocabulary
	*/
	public java.lang.String getTitle(java.lang.String languageId) {
		return _assetVocabulary.getTitle(languageId);
	}

	/**
	* Returns the localized title of this asset vocabulary in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this asset vocabulary
	*/
	public java.lang.String getTitle(java.lang.String languageId,
		boolean useDefault) {
		return _assetVocabulary.getTitle(languageId, useDefault);
	}

	public java.lang.String getTitleCurrentLanguageId() {
		return _assetVocabulary.getTitleCurrentLanguageId();
	}

	public java.lang.String getTitleCurrentValue() {
		return _assetVocabulary.getTitleCurrentValue();
	}

	/**
	* Returns a map of the locales and localized titles of this asset vocabulary.
	*
	* @return the locales and localized titles of this asset vocabulary
	*/
	public java.util.Map<java.util.Locale, java.lang.String> getTitleMap() {
		return _assetVocabulary.getTitleMap();
	}

	/**
	* Sets the title of this asset vocabulary.
	*
	* @param title the title of this asset vocabulary
	*/
	public void setTitle(java.lang.String title) {
		_assetVocabulary.setTitle(title);
	}

	/**
	* Sets the localized title of this asset vocabulary in the language.
	*
	* @param title the localized title of this asset vocabulary
	* @param locale the locale of the language
	*/
	public void setTitle(java.lang.String title, java.util.Locale locale) {
		_assetVocabulary.setTitle(title, locale);
	}

	/**
	* Sets the localized title of this asset vocabulary in the language, and sets the default locale.
	*
	* @param title the localized title of this asset vocabulary
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	public void setTitle(java.lang.String title, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_assetVocabulary.setTitle(title, locale, defaultLocale);
	}

	public void setTitleCurrentLanguageId(java.lang.String languageId) {
		_assetVocabulary.setTitleCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized titles of this asset vocabulary from the map of locales and localized titles.
	*
	* @param titleMap the locales and localized titles of this asset vocabulary
	*/
	public void setTitleMap(
		java.util.Map<java.util.Locale, java.lang.String> titleMap) {
		_assetVocabulary.setTitleMap(titleMap);
	}

	/**
	* Sets the localized titles of this asset vocabulary from the map of locales and localized titles, and sets the default locale.
	*
	* @param titleMap the locales and localized titles of this asset vocabulary
	* @param defaultLocale the default locale
	*/
	public void setTitleMap(
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Locale defaultLocale) {
		_assetVocabulary.setTitleMap(titleMap, defaultLocale);
	}

	/**
	* Returns the description of this asset vocabulary.
	*
	* @return the description of this asset vocabulary
	*/
	public java.lang.String getDescription() {
		return _assetVocabulary.getDescription();
	}

	/**
	* Returns the localized description of this asset vocabulary in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized description of this asset vocabulary
	*/
	public java.lang.String getDescription(java.util.Locale locale) {
		return _assetVocabulary.getDescription(locale);
	}

	/**
	* Returns the localized description of this asset vocabulary in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this asset vocabulary. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	public java.lang.String getDescription(java.util.Locale locale,
		boolean useDefault) {
		return _assetVocabulary.getDescription(locale, useDefault);
	}

	/**
	* Returns the localized description of this asset vocabulary in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized description of this asset vocabulary
	*/
	public java.lang.String getDescription(java.lang.String languageId) {
		return _assetVocabulary.getDescription(languageId);
	}

	/**
	* Returns the localized description of this asset vocabulary in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this asset vocabulary
	*/
	public java.lang.String getDescription(java.lang.String languageId,
		boolean useDefault) {
		return _assetVocabulary.getDescription(languageId, useDefault);
	}

	public java.lang.String getDescriptionCurrentLanguageId() {
		return _assetVocabulary.getDescriptionCurrentLanguageId();
	}

	public java.lang.String getDescriptionCurrentValue() {
		return _assetVocabulary.getDescriptionCurrentValue();
	}

	/**
	* Returns a map of the locales and localized descriptions of this asset vocabulary.
	*
	* @return the locales and localized descriptions of this asset vocabulary
	*/
	public java.util.Map<java.util.Locale, java.lang.String> getDescriptionMap() {
		return _assetVocabulary.getDescriptionMap();
	}

	/**
	* Sets the description of this asset vocabulary.
	*
	* @param description the description of this asset vocabulary
	*/
	public void setDescription(java.lang.String description) {
		_assetVocabulary.setDescription(description);
	}

	/**
	* Sets the localized description of this asset vocabulary in the language.
	*
	* @param description the localized description of this asset vocabulary
	* @param locale the locale of the language
	*/
	public void setDescription(java.lang.String description,
		java.util.Locale locale) {
		_assetVocabulary.setDescription(description, locale);
	}

	/**
	* Sets the localized description of this asset vocabulary in the language, and sets the default locale.
	*
	* @param description the localized description of this asset vocabulary
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	public void setDescription(java.lang.String description,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_assetVocabulary.setDescription(description, locale, defaultLocale);
	}

	public void setDescriptionCurrentLanguageId(java.lang.String languageId) {
		_assetVocabulary.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized descriptions of this asset vocabulary from the map of locales and localized descriptions.
	*
	* @param descriptionMap the locales and localized descriptions of this asset vocabulary
	*/
	public void setDescriptionMap(
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap) {
		_assetVocabulary.setDescriptionMap(descriptionMap);
	}

	/**
	* Sets the localized descriptions of this asset vocabulary from the map of locales and localized descriptions, and sets the default locale.
	*
	* @param descriptionMap the locales and localized descriptions of this asset vocabulary
	* @param defaultLocale the default locale
	*/
	public void setDescriptionMap(
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Locale defaultLocale) {
		_assetVocabulary.setDescriptionMap(descriptionMap, defaultLocale);
	}

	/**
	* Returns the settings of this asset vocabulary.
	*
	* @return the settings of this asset vocabulary
	*/
	public java.lang.String getSettings() {
		return _assetVocabulary.getSettings();
	}

	/**
	* Sets the settings of this asset vocabulary.
	*
	* @param settings the settings of this asset vocabulary
	*/
	public void setSettings(java.lang.String settings) {
		_assetVocabulary.setSettings(settings);
	}

	public boolean isNew() {
		return _assetVocabulary.isNew();
	}

	public void setNew(boolean n) {
		_assetVocabulary.setNew(n);
	}

	public boolean isCachedModel() {
		return _assetVocabulary.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_assetVocabulary.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _assetVocabulary.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _assetVocabulary.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_assetVocabulary.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _assetVocabulary.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_assetVocabulary.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AssetVocabularyWrapper((AssetVocabulary)_assetVocabulary.clone());
	}

	public int compareTo(
		com.liferay.portlet.asset.model.AssetVocabulary assetVocabulary) {
		return _assetVocabulary.compareTo(assetVocabulary);
	}

	@Override
	public int hashCode() {
		return _assetVocabulary.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portlet.asset.model.AssetVocabulary> toCacheModel() {
		return _assetVocabulary.toCacheModel();
	}

	public com.liferay.portlet.asset.model.AssetVocabulary toEscapedModel() {
		return new AssetVocabularyWrapper(_assetVocabulary.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _assetVocabulary.toString();
	}

	public java.lang.String toXmlString() {
		return _assetVocabulary.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_assetVocabulary.persist();
	}

	public com.liferay.portal.kernel.util.UnicodeProperties getSettingsProperties() {
		return _assetVocabulary.getSettingsProperties();
	}

	public boolean isMultiValued() {
		return _assetVocabulary.isMultiValued();
	}

	public boolean isRequired(long classNameId) {
		return _assetVocabulary.isRequired(classNameId);
	}

	public void setSettingsProperties(
		com.liferay.portal.kernel.util.UnicodeProperties settingsProperties) {
		_assetVocabulary.setSettingsProperties(settingsProperties);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public AssetVocabulary getWrappedAssetVocabulary() {
		return _assetVocabulary;
	}

	public AssetVocabulary getWrappedModel() {
		return _assetVocabulary;
	}

	public void resetOriginalValues() {
		_assetVocabulary.resetOriginalValues();
	}

	private AssetVocabulary _assetVocabulary;
}