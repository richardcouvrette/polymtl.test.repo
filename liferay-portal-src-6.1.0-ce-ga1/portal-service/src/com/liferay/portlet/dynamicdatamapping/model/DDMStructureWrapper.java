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

package com.liferay.portlet.dynamicdatamapping.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link DDMStructure}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       DDMStructure
 * @generated
 */
public class DDMStructureWrapper implements DDMStructure,
	ModelWrapper<DDMStructure> {
	public DDMStructureWrapper(DDMStructure ddmStructure) {
		_ddmStructure = ddmStructure;
	}

	public Class<?> getModelClass() {
		return DDMStructure.class;
	}

	public String getModelClassName() {
		return DDMStructure.class.getName();
	}

	/**
	* Returns the primary key of this d d m structure.
	*
	* @return the primary key of this d d m structure
	*/
	public long getPrimaryKey() {
		return _ddmStructure.getPrimaryKey();
	}

	/**
	* Sets the primary key of this d d m structure.
	*
	* @param primaryKey the primary key of this d d m structure
	*/
	public void setPrimaryKey(long primaryKey) {
		_ddmStructure.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this d d m structure.
	*
	* @return the uuid of this d d m structure
	*/
	public java.lang.String getUuid() {
		return _ddmStructure.getUuid();
	}

	/**
	* Sets the uuid of this d d m structure.
	*
	* @param uuid the uuid of this d d m structure
	*/
	public void setUuid(java.lang.String uuid) {
		_ddmStructure.setUuid(uuid);
	}

	/**
	* Returns the structure ID of this d d m structure.
	*
	* @return the structure ID of this d d m structure
	*/
	public long getStructureId() {
		return _ddmStructure.getStructureId();
	}

	/**
	* Sets the structure ID of this d d m structure.
	*
	* @param structureId the structure ID of this d d m structure
	*/
	public void setStructureId(long structureId) {
		_ddmStructure.setStructureId(structureId);
	}

	/**
	* Returns the group ID of this d d m structure.
	*
	* @return the group ID of this d d m structure
	*/
	public long getGroupId() {
		return _ddmStructure.getGroupId();
	}

	/**
	* Sets the group ID of this d d m structure.
	*
	* @param groupId the group ID of this d d m structure
	*/
	public void setGroupId(long groupId) {
		_ddmStructure.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this d d m structure.
	*
	* @return the company ID of this d d m structure
	*/
	public long getCompanyId() {
		return _ddmStructure.getCompanyId();
	}

	/**
	* Sets the company ID of this d d m structure.
	*
	* @param companyId the company ID of this d d m structure
	*/
	public void setCompanyId(long companyId) {
		_ddmStructure.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this d d m structure.
	*
	* @return the user ID of this d d m structure
	*/
	public long getUserId() {
		return _ddmStructure.getUserId();
	}

	/**
	* Sets the user ID of this d d m structure.
	*
	* @param userId the user ID of this d d m structure
	*/
	public void setUserId(long userId) {
		_ddmStructure.setUserId(userId);
	}

	/**
	* Returns the user uuid of this d d m structure.
	*
	* @return the user uuid of this d d m structure
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ddmStructure.getUserUuid();
	}

	/**
	* Sets the user uuid of this d d m structure.
	*
	* @param userUuid the user uuid of this d d m structure
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_ddmStructure.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this d d m structure.
	*
	* @return the user name of this d d m structure
	*/
	public java.lang.String getUserName() {
		return _ddmStructure.getUserName();
	}

	/**
	* Sets the user name of this d d m structure.
	*
	* @param userName the user name of this d d m structure
	*/
	public void setUserName(java.lang.String userName) {
		_ddmStructure.setUserName(userName);
	}

	/**
	* Returns the create date of this d d m structure.
	*
	* @return the create date of this d d m structure
	*/
	public java.util.Date getCreateDate() {
		return _ddmStructure.getCreateDate();
	}

	/**
	* Sets the create date of this d d m structure.
	*
	* @param createDate the create date of this d d m structure
	*/
	public void setCreateDate(java.util.Date createDate) {
		_ddmStructure.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this d d m structure.
	*
	* @return the modified date of this d d m structure
	*/
	public java.util.Date getModifiedDate() {
		return _ddmStructure.getModifiedDate();
	}

	/**
	* Sets the modified date of this d d m structure.
	*
	* @param modifiedDate the modified date of this d d m structure
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_ddmStructure.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the fully qualified class name of this d d m structure.
	*
	* @return the fully qualified class name of this d d m structure
	*/
	public java.lang.String getClassName() {
		return _ddmStructure.getClassName();
	}

	/**
	* Returns the class name ID of this d d m structure.
	*
	* @return the class name ID of this d d m structure
	*/
	public long getClassNameId() {
		return _ddmStructure.getClassNameId();
	}

	/**
	* Sets the class name ID of this d d m structure.
	*
	* @param classNameId the class name ID of this d d m structure
	*/
	public void setClassNameId(long classNameId) {
		_ddmStructure.setClassNameId(classNameId);
	}

	/**
	* Returns the structure key of this d d m structure.
	*
	* @return the structure key of this d d m structure
	*/
	public java.lang.String getStructureKey() {
		return _ddmStructure.getStructureKey();
	}

	/**
	* Sets the structure key of this d d m structure.
	*
	* @param structureKey the structure key of this d d m structure
	*/
	public void setStructureKey(java.lang.String structureKey) {
		_ddmStructure.setStructureKey(structureKey);
	}

	/**
	* Returns the name of this d d m structure.
	*
	* @return the name of this d d m structure
	*/
	public java.lang.String getName() {
		return _ddmStructure.getName();
	}

	/**
	* Returns the localized name of this d d m structure in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized name of this d d m structure
	*/
	public java.lang.String getName(java.util.Locale locale) {
		return _ddmStructure.getName(locale);
	}

	/**
	* Returns the localized name of this d d m structure in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this d d m structure. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	public java.lang.String getName(java.util.Locale locale, boolean useDefault) {
		return _ddmStructure.getName(locale, useDefault);
	}

	/**
	* Returns the localized name of this d d m structure in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized name of this d d m structure
	*/
	public java.lang.String getName(java.lang.String languageId) {
		return _ddmStructure.getName(languageId);
	}

	/**
	* Returns the localized name of this d d m structure in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this d d m structure
	*/
	public java.lang.String getName(java.lang.String languageId,
		boolean useDefault) {
		return _ddmStructure.getName(languageId, useDefault);
	}

	public java.lang.String getNameCurrentLanguageId() {
		return _ddmStructure.getNameCurrentLanguageId();
	}

	public java.lang.String getNameCurrentValue() {
		return _ddmStructure.getNameCurrentValue();
	}

	/**
	* Returns a map of the locales and localized names of this d d m structure.
	*
	* @return the locales and localized names of this d d m structure
	*/
	public java.util.Map<java.util.Locale, java.lang.String> getNameMap() {
		return _ddmStructure.getNameMap();
	}

	/**
	* Sets the name of this d d m structure.
	*
	* @param name the name of this d d m structure
	*/
	public void setName(java.lang.String name) {
		_ddmStructure.setName(name);
	}

	/**
	* Sets the localized name of this d d m structure in the language.
	*
	* @param name the localized name of this d d m structure
	* @param locale the locale of the language
	*/
	public void setName(java.lang.String name, java.util.Locale locale) {
		_ddmStructure.setName(name, locale);
	}

	/**
	* Sets the localized name of this d d m structure in the language, and sets the default locale.
	*
	* @param name the localized name of this d d m structure
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	public void setName(java.lang.String name, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_ddmStructure.setName(name, locale, defaultLocale);
	}

	public void setNameCurrentLanguageId(java.lang.String languageId) {
		_ddmStructure.setNameCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized names of this d d m structure from the map of locales and localized names.
	*
	* @param nameMap the locales and localized names of this d d m structure
	*/
	public void setNameMap(
		java.util.Map<java.util.Locale, java.lang.String> nameMap) {
		_ddmStructure.setNameMap(nameMap);
	}

	/**
	* Sets the localized names of this d d m structure from the map of locales and localized names, and sets the default locale.
	*
	* @param nameMap the locales and localized names of this d d m structure
	* @param defaultLocale the default locale
	*/
	public void setNameMap(
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Locale defaultLocale) {
		_ddmStructure.setNameMap(nameMap, defaultLocale);
	}

	/**
	* Returns the description of this d d m structure.
	*
	* @return the description of this d d m structure
	*/
	public java.lang.String getDescription() {
		return _ddmStructure.getDescription();
	}

	/**
	* Returns the localized description of this d d m structure in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized description of this d d m structure
	*/
	public java.lang.String getDescription(java.util.Locale locale) {
		return _ddmStructure.getDescription(locale);
	}

	/**
	* Returns the localized description of this d d m structure in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this d d m structure. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	public java.lang.String getDescription(java.util.Locale locale,
		boolean useDefault) {
		return _ddmStructure.getDescription(locale, useDefault);
	}

	/**
	* Returns the localized description of this d d m structure in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized description of this d d m structure
	*/
	public java.lang.String getDescription(java.lang.String languageId) {
		return _ddmStructure.getDescription(languageId);
	}

	/**
	* Returns the localized description of this d d m structure in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this d d m structure
	*/
	public java.lang.String getDescription(java.lang.String languageId,
		boolean useDefault) {
		return _ddmStructure.getDescription(languageId, useDefault);
	}

	public java.lang.String getDescriptionCurrentLanguageId() {
		return _ddmStructure.getDescriptionCurrentLanguageId();
	}

	public java.lang.String getDescriptionCurrentValue() {
		return _ddmStructure.getDescriptionCurrentValue();
	}

	/**
	* Returns a map of the locales and localized descriptions of this d d m structure.
	*
	* @return the locales and localized descriptions of this d d m structure
	*/
	public java.util.Map<java.util.Locale, java.lang.String> getDescriptionMap() {
		return _ddmStructure.getDescriptionMap();
	}

	/**
	* Sets the description of this d d m structure.
	*
	* @param description the description of this d d m structure
	*/
	public void setDescription(java.lang.String description) {
		_ddmStructure.setDescription(description);
	}

	/**
	* Sets the localized description of this d d m structure in the language.
	*
	* @param description the localized description of this d d m structure
	* @param locale the locale of the language
	*/
	public void setDescription(java.lang.String description,
		java.util.Locale locale) {
		_ddmStructure.setDescription(description, locale);
	}

	/**
	* Sets the localized description of this d d m structure in the language, and sets the default locale.
	*
	* @param description the localized description of this d d m structure
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	public void setDescription(java.lang.String description,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_ddmStructure.setDescription(description, locale, defaultLocale);
	}

	public void setDescriptionCurrentLanguageId(java.lang.String languageId) {
		_ddmStructure.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized descriptions of this d d m structure from the map of locales and localized descriptions.
	*
	* @param descriptionMap the locales and localized descriptions of this d d m structure
	*/
	public void setDescriptionMap(
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap) {
		_ddmStructure.setDescriptionMap(descriptionMap);
	}

	/**
	* Sets the localized descriptions of this d d m structure from the map of locales and localized descriptions, and sets the default locale.
	*
	* @param descriptionMap the locales and localized descriptions of this d d m structure
	* @param defaultLocale the default locale
	*/
	public void setDescriptionMap(
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Locale defaultLocale) {
		_ddmStructure.setDescriptionMap(descriptionMap, defaultLocale);
	}

	/**
	* Returns the xsd of this d d m structure.
	*
	* @return the xsd of this d d m structure
	*/
	public java.lang.String getXsd() {
		return _ddmStructure.getXsd();
	}

	/**
	* Sets the xsd of this d d m structure.
	*
	* @param xsd the xsd of this d d m structure
	*/
	public void setXsd(java.lang.String xsd) {
		_ddmStructure.setXsd(xsd);
	}

	/**
	* Returns the storage type of this d d m structure.
	*
	* @return the storage type of this d d m structure
	*/
	public java.lang.String getStorageType() {
		return _ddmStructure.getStorageType();
	}

	/**
	* Sets the storage type of this d d m structure.
	*
	* @param storageType the storage type of this d d m structure
	*/
	public void setStorageType(java.lang.String storageType) {
		_ddmStructure.setStorageType(storageType);
	}

	/**
	* Returns the type of this d d m structure.
	*
	* @return the type of this d d m structure
	*/
	public int getType() {
		return _ddmStructure.getType();
	}

	/**
	* Sets the type of this d d m structure.
	*
	* @param type the type of this d d m structure
	*/
	public void setType(int type) {
		_ddmStructure.setType(type);
	}

	public boolean isNew() {
		return _ddmStructure.isNew();
	}

	public void setNew(boolean n) {
		_ddmStructure.setNew(n);
	}

	public boolean isCachedModel() {
		return _ddmStructure.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_ddmStructure.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _ddmStructure.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _ddmStructure.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_ddmStructure.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _ddmStructure.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_ddmStructure.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new DDMStructureWrapper((DDMStructure)_ddmStructure.clone());
	}

	public int compareTo(
		com.liferay.portlet.dynamicdatamapping.model.DDMStructure ddmStructure) {
		return _ddmStructure.compareTo(ddmStructure);
	}

	@Override
	public int hashCode() {
		return _ddmStructure.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portlet.dynamicdatamapping.model.DDMStructure> toCacheModel() {
		return _ddmStructure.toCacheModel();
	}

	public com.liferay.portlet.dynamicdatamapping.model.DDMStructure toEscapedModel() {
		return new DDMStructureWrapper(_ddmStructure.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _ddmStructure.toString();
	}

	public java.lang.String toXmlString() {
		return _ddmStructure.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_ddmStructure.persist();
	}

	public java.util.List<java.lang.String> getAvailableLocales() {
		return _ddmStructure.getAvailableLocales();
	}

	public java.lang.String getDefaultLocale() {
		return _ddmStructure.getDefaultLocale();
	}

	public com.liferay.portal.kernel.xml.Document getDocument() {
		return _ddmStructure.getDocument();
	}

	public java.lang.String getFieldDataType(java.lang.String fieldName)
		throws com.liferay.portlet.dynamicdatamapping.StructureFieldException {
		return _ddmStructure.getFieldDataType(fieldName);
	}

	public java.lang.String getFieldLabel(java.lang.String fieldName,
		java.util.Locale locale)
		throws com.liferay.portlet.dynamicdatamapping.StructureFieldException {
		return _ddmStructure.getFieldLabel(fieldName, locale);
	}

	public java.lang.String getFieldLabel(java.lang.String fieldName,
		java.lang.String locale)
		throws com.liferay.portlet.dynamicdatamapping.StructureFieldException {
		return _ddmStructure.getFieldLabel(fieldName, locale);
	}

	public java.util.Set<java.lang.String> getFieldNames() {
		return _ddmStructure.getFieldNames();
	}

	public java.lang.String getFieldProperty(java.lang.String fieldName,
		java.lang.String property)
		throws com.liferay.portlet.dynamicdatamapping.StructureFieldException {
		return _ddmStructure.getFieldProperty(fieldName, property);
	}

	public java.lang.String getFieldProperty(java.lang.String fieldName,
		java.lang.String property, java.lang.String locale)
		throws com.liferay.portlet.dynamicdatamapping.StructureFieldException {
		return _ddmStructure.getFieldProperty(fieldName, property, locale);
	}

	public boolean getFieldRequired(java.lang.String fieldName)
		throws com.liferay.portlet.dynamicdatamapping.StructureFieldException {
		return _ddmStructure.getFieldRequired(fieldName);
	}

	public java.util.Map<java.lang.String, java.lang.String> getFields(
		java.lang.String fieldName, java.lang.String attributeName,
		java.lang.String attributeValue) {
		return _ddmStructure.getFields(fieldName, attributeName, attributeValue);
	}

	public java.util.Map<java.lang.String, java.lang.String> getFields(
		java.lang.String fieldName, java.lang.String attributeName,
		java.lang.String attributeValue, java.lang.String locale) {
		return _ddmStructure.getFields(fieldName, attributeName,
			attributeValue, locale);
	}

	public java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.String>> getFieldsMap() {
		return _ddmStructure.getFieldsMap();
	}

	public java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.String>> getFieldsMap(
		java.lang.String locale) {
		return _ddmStructure.getFieldsMap(locale);
	}

	public java.lang.String getFieldType(java.lang.String fieldName)
		throws com.liferay.portlet.dynamicdatamapping.StructureFieldException {
		return _ddmStructure.getFieldType(fieldName);
	}

	public boolean hasField(java.lang.String fieldName) {
		return _ddmStructure.hasField(fieldName);
	}

	public void setDocument(com.liferay.portal.kernel.xml.Document document) {
		_ddmStructure.setDocument(document);
	}

	public void setFieldsMap(
		java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.String>> fieldsMap) {
		_ddmStructure.setFieldsMap(fieldsMap);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public DDMStructure getWrappedDDMStructure() {
		return _ddmStructure;
	}

	public DDMStructure getWrappedModel() {
		return _ddmStructure;
	}

	public void resetOriginalValues() {
		_ddmStructure.resetOriginalValues();
	}

	private DDMStructure _ddmStructure;
}