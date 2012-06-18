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

package com.liferay.portlet.social.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link SocialActivitySetting}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SocialActivitySetting
 * @generated
 */
public class SocialActivitySettingWrapper implements SocialActivitySetting,
	ModelWrapper<SocialActivitySetting> {
	public SocialActivitySettingWrapper(
		SocialActivitySetting socialActivitySetting) {
		_socialActivitySetting = socialActivitySetting;
	}

	public Class<?> getModelClass() {
		return SocialActivitySetting.class;
	}

	public String getModelClassName() {
		return SocialActivitySetting.class.getName();
	}

	/**
	* Returns the primary key of this social activity setting.
	*
	* @return the primary key of this social activity setting
	*/
	public long getPrimaryKey() {
		return _socialActivitySetting.getPrimaryKey();
	}

	/**
	* Sets the primary key of this social activity setting.
	*
	* @param primaryKey the primary key of this social activity setting
	*/
	public void setPrimaryKey(long primaryKey) {
		_socialActivitySetting.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the activity setting ID of this social activity setting.
	*
	* @return the activity setting ID of this social activity setting
	*/
	public long getActivitySettingId() {
		return _socialActivitySetting.getActivitySettingId();
	}

	/**
	* Sets the activity setting ID of this social activity setting.
	*
	* @param activitySettingId the activity setting ID of this social activity setting
	*/
	public void setActivitySettingId(long activitySettingId) {
		_socialActivitySetting.setActivitySettingId(activitySettingId);
	}

	/**
	* Returns the group ID of this social activity setting.
	*
	* @return the group ID of this social activity setting
	*/
	public long getGroupId() {
		return _socialActivitySetting.getGroupId();
	}

	/**
	* Sets the group ID of this social activity setting.
	*
	* @param groupId the group ID of this social activity setting
	*/
	public void setGroupId(long groupId) {
		_socialActivitySetting.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this social activity setting.
	*
	* @return the company ID of this social activity setting
	*/
	public long getCompanyId() {
		return _socialActivitySetting.getCompanyId();
	}

	/**
	* Sets the company ID of this social activity setting.
	*
	* @param companyId the company ID of this social activity setting
	*/
	public void setCompanyId(long companyId) {
		_socialActivitySetting.setCompanyId(companyId);
	}

	/**
	* Returns the fully qualified class name of this social activity setting.
	*
	* @return the fully qualified class name of this social activity setting
	*/
	public java.lang.String getClassName() {
		return _socialActivitySetting.getClassName();
	}

	/**
	* Returns the class name ID of this social activity setting.
	*
	* @return the class name ID of this social activity setting
	*/
	public long getClassNameId() {
		return _socialActivitySetting.getClassNameId();
	}

	/**
	* Sets the class name ID of this social activity setting.
	*
	* @param classNameId the class name ID of this social activity setting
	*/
	public void setClassNameId(long classNameId) {
		_socialActivitySetting.setClassNameId(classNameId);
	}

	/**
	* Returns the activity type of this social activity setting.
	*
	* @return the activity type of this social activity setting
	*/
	public int getActivityType() {
		return _socialActivitySetting.getActivityType();
	}

	/**
	* Sets the activity type of this social activity setting.
	*
	* @param activityType the activity type of this social activity setting
	*/
	public void setActivityType(int activityType) {
		_socialActivitySetting.setActivityType(activityType);
	}

	/**
	* Returns the name of this social activity setting.
	*
	* @return the name of this social activity setting
	*/
	public java.lang.String getName() {
		return _socialActivitySetting.getName();
	}

	/**
	* Sets the name of this social activity setting.
	*
	* @param name the name of this social activity setting
	*/
	public void setName(java.lang.String name) {
		_socialActivitySetting.setName(name);
	}

	/**
	* Returns the value of this social activity setting.
	*
	* @return the value of this social activity setting
	*/
	public java.lang.String getValue() {
		return _socialActivitySetting.getValue();
	}

	/**
	* Sets the value of this social activity setting.
	*
	* @param value the value of this social activity setting
	*/
	public void setValue(java.lang.String value) {
		_socialActivitySetting.setValue(value);
	}

	public boolean isNew() {
		return _socialActivitySetting.isNew();
	}

	public void setNew(boolean n) {
		_socialActivitySetting.setNew(n);
	}

	public boolean isCachedModel() {
		return _socialActivitySetting.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_socialActivitySetting.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _socialActivitySetting.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _socialActivitySetting.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_socialActivitySetting.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _socialActivitySetting.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_socialActivitySetting.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new SocialActivitySettingWrapper((SocialActivitySetting)_socialActivitySetting.clone());
	}

	public int compareTo(
		com.liferay.portlet.social.model.SocialActivitySetting socialActivitySetting) {
		return _socialActivitySetting.compareTo(socialActivitySetting);
	}

	@Override
	public int hashCode() {
		return _socialActivitySetting.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portlet.social.model.SocialActivitySetting> toCacheModel() {
		return _socialActivitySetting.toCacheModel();
	}

	public com.liferay.portlet.social.model.SocialActivitySetting toEscapedModel() {
		return new SocialActivitySettingWrapper(_socialActivitySetting.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _socialActivitySetting.toString();
	}

	public java.lang.String toXmlString() {
		return _socialActivitySetting.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_socialActivitySetting.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public SocialActivitySetting getWrappedSocialActivitySetting() {
		return _socialActivitySetting;
	}

	public SocialActivitySetting getWrappedModel() {
		return _socialActivitySetting;
	}

	public void resetOriginalValues() {
		_socialActivitySetting.resetOriginalValues();
	}

	private SocialActivitySetting _socialActivitySetting;
}