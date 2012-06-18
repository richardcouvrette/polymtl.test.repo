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

package com.liferay.portlet.social.service;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the social activity setting local service. This utility wraps {@link com.liferay.portlet.social.service.impl.SocialActivitySettingLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SocialActivitySettingLocalService
 * @see com.liferay.portlet.social.service.base.SocialActivitySettingLocalServiceBaseImpl
 * @see com.liferay.portlet.social.service.impl.SocialActivitySettingLocalServiceImpl
 * @generated
 */
public class SocialActivitySettingLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portlet.social.service.impl.SocialActivitySettingLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the social activity setting to the database. Also notifies the appropriate model listeners.
	*
	* @param socialActivitySetting the social activity setting
	* @return the social activity setting that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.social.model.SocialActivitySetting addSocialActivitySetting(
		com.liferay.portlet.social.model.SocialActivitySetting socialActivitySetting)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addSocialActivitySetting(socialActivitySetting);
	}

	/**
	* Creates a new social activity setting with the primary key. Does not add the social activity setting to the database.
	*
	* @param activitySettingId the primary key for the new social activity setting
	* @return the new social activity setting
	*/
	public static com.liferay.portlet.social.model.SocialActivitySetting createSocialActivitySetting(
		long activitySettingId) {
		return getService().createSocialActivitySetting(activitySettingId);
	}

	/**
	* Deletes the social activity setting with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param activitySettingId the primary key of the social activity setting
	* @throws PortalException if a social activity setting with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteSocialActivitySetting(long activitySettingId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteSocialActivitySetting(activitySettingId);
	}

	/**
	* Deletes the social activity setting from the database. Also notifies the appropriate model listeners.
	*
	* @param socialActivitySetting the social activity setting
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteSocialActivitySetting(
		com.liferay.portlet.social.model.SocialActivitySetting socialActivitySetting)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteSocialActivitySetting(socialActivitySetting);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	public static com.liferay.portlet.social.model.SocialActivitySetting fetchSocialActivitySetting(
		long activitySettingId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchSocialActivitySetting(activitySettingId);
	}

	/**
	* Returns the social activity setting with the primary key.
	*
	* @param activitySettingId the primary key of the social activity setting
	* @return the social activity setting
	* @throws PortalException if a social activity setting with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.social.model.SocialActivitySetting getSocialActivitySetting(
		long activitySettingId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getSocialActivitySetting(activitySettingId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the social activity settings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of social activity settings
	* @param end the upper bound of the range of social activity settings (not inclusive)
	* @return the range of social activity settings
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.social.model.SocialActivitySetting> getSocialActivitySettings(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSocialActivitySettings(start, end);
	}

	/**
	* Returns the number of social activity settings.
	*
	* @return the number of social activity settings
	* @throws SystemException if a system exception occurred
	*/
	public static int getSocialActivitySettingsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSocialActivitySettingsCount();
	}

	/**
	* Updates the social activity setting in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param socialActivitySetting the social activity setting
	* @return the social activity setting that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.social.model.SocialActivitySetting updateSocialActivitySetting(
		com.liferay.portlet.social.model.SocialActivitySetting socialActivitySetting)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateSocialActivitySetting(socialActivitySetting);
	}

	/**
	* Updates the social activity setting in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param socialActivitySetting the social activity setting
	* @param merge whether to merge the social activity setting with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the social activity setting that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.social.model.SocialActivitySetting updateSocialActivitySetting(
		com.liferay.portlet.social.model.SocialActivitySetting socialActivitySetting,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateSocialActivitySetting(socialActivitySetting, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static com.liferay.portlet.social.model.SocialActivityDefinition getActivityDefinition(
		long groupId, java.lang.String className, int activityType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getActivityDefinition(groupId, className, activityType);
	}

	public static java.util.List<com.liferay.portlet.social.model.SocialActivityDefinition> getActivityDefinitions(
		long groupId, java.lang.String className)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getActivityDefinitions(groupId, className);
	}

	public static java.util.List<com.liferay.portlet.social.model.SocialActivitySetting> getActivitySettings(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getActivitySettings(groupId);
	}

	public static boolean isEnabled(long groupId, long classNameId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().isEnabled(groupId, classNameId);
	}

	public static void updateActivitySetting(long groupId,
		java.lang.String className, boolean enabled)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().updateActivitySetting(groupId, className, enabled);
	}

	public static void updateActivitySetting(long groupId,
		java.lang.String className, int activityType,
		com.liferay.portlet.social.model.SocialActivityCounterDefinition activityCounterDefinition)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.updateActivitySetting(groupId, className, activityType,
			activityCounterDefinition);
	}

	public static void updateActivitySettings(long groupId,
		java.lang.String className, int activityType,
		java.util.List<com.liferay.portlet.social.model.SocialActivityCounterDefinition> activityCounterDefinitions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.updateActivitySettings(groupId, className, activityType,
			activityCounterDefinitions);
	}

	public static SocialActivitySettingLocalService getService() {
		if (_service == null) {
			_service = (SocialActivitySettingLocalService)PortalBeanLocatorUtil.locate(SocialActivitySettingLocalService.class.getName());

			ReferenceRegistry.registerReference(SocialActivitySettingLocalServiceUtil.class,
				"_service");
			MethodCache.remove(SocialActivitySettingLocalService.class);
		}

		return _service;
	}

	public void setService(SocialActivitySettingLocalService service) {
		MethodCache.remove(SocialActivitySettingLocalService.class);

		_service = service;

		ReferenceRegistry.registerReference(SocialActivitySettingLocalServiceUtil.class,
			"_service");
		MethodCache.remove(SocialActivitySettingLocalService.class);
	}

	private static SocialActivitySettingLocalService _service;
}