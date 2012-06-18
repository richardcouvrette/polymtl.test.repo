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

package com.liferay.portlet.asset.service;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the asset tag local service. This utility wraps {@link com.liferay.portlet.asset.service.impl.AssetTagLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetTagLocalService
 * @see com.liferay.portlet.asset.service.base.AssetTagLocalServiceBaseImpl
 * @see com.liferay.portlet.asset.service.impl.AssetTagLocalServiceImpl
 * @generated
 */
public class AssetTagLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portlet.asset.service.impl.AssetTagLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the asset tag to the database. Also notifies the appropriate model listeners.
	*
	* @param assetTag the asset tag
	* @return the asset tag that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.asset.model.AssetTag addAssetTag(
		com.liferay.portlet.asset.model.AssetTag assetTag)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addAssetTag(assetTag);
	}

	/**
	* Creates a new asset tag with the primary key. Does not add the asset tag to the database.
	*
	* @param tagId the primary key for the new asset tag
	* @return the new asset tag
	*/
	public static com.liferay.portlet.asset.model.AssetTag createAssetTag(
		long tagId) {
		return getService().createAssetTag(tagId);
	}

	/**
	* Deletes the asset tag with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param tagId the primary key of the asset tag
	* @throws PortalException if a asset tag with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteAssetTag(long tagId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteAssetTag(tagId);
	}

	/**
	* Deletes the asset tag from the database. Also notifies the appropriate model listeners.
	*
	* @param assetTag the asset tag
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteAssetTag(
		com.liferay.portlet.asset.model.AssetTag assetTag)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteAssetTag(assetTag);
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

	public static com.liferay.portlet.asset.model.AssetTag fetchAssetTag(
		long tagId) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchAssetTag(tagId);
	}

	/**
	* Returns the asset tag with the primary key.
	*
	* @param tagId the primary key of the asset tag
	* @return the asset tag
	* @throws PortalException if a asset tag with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.asset.model.AssetTag getAssetTag(
		long tagId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getAssetTag(tagId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the asset tags.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of asset tags
	* @param end the upper bound of the range of asset tags (not inclusive)
	* @return the range of asset tags
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.asset.model.AssetTag> getAssetTags(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAssetTags(start, end);
	}

	/**
	* Returns the number of asset tags.
	*
	* @return the number of asset tags
	* @throws SystemException if a system exception occurred
	*/
	public static int getAssetTagsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAssetTagsCount();
	}

	/**
	* Updates the asset tag in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetTag the asset tag
	* @return the asset tag that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.asset.model.AssetTag updateAssetTag(
		com.liferay.portlet.asset.model.AssetTag assetTag)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAssetTag(assetTag);
	}

	/**
	* Updates the asset tag in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetTag the asset tag
	* @param merge whether to merge the asset tag with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the asset tag that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.asset.model.AssetTag updateAssetTag(
		com.liferay.portlet.asset.model.AssetTag assetTag, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAssetTag(assetTag, merge);
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

	public static com.liferay.portlet.asset.model.AssetTag addTag(long userId,
		java.lang.String name, java.lang.String[] tagProperties,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().addTag(userId, name, tagProperties, serviceContext);
	}

	public static void addTagResources(
		com.liferay.portlet.asset.model.AssetTag tag,
		boolean addGroupPermissions, boolean addGuestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addTagResources(tag, addGroupPermissions, addGuestPermissions);
	}

	public static void addTagResources(
		com.liferay.portlet.asset.model.AssetTag tag,
		java.lang.String[] groupPermissions, java.lang.String[] guestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().addTagResources(tag, groupPermissions, guestPermissions);
	}

	public static void checkTags(long userId, long groupId,
		java.lang.String[] names)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().checkTags(userId, groupId, names);
	}

	public static com.liferay.portlet.asset.model.AssetTag decrementAssetCount(
		long tagId, long classNameId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().decrementAssetCount(tagId, classNameId);
	}

	public static void deleteTag(com.liferay.portlet.asset.model.AssetTag tag)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteTag(tag);
	}

	public static void deleteTag(long tagId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteTag(tagId);
	}

	public static java.util.List<com.liferay.portlet.asset.model.AssetTag> getEntryTags(
		long entryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getEntryTags(entryId);
	}

	public static java.util.List<com.liferay.portlet.asset.model.AssetTag> getGroupsTags(
		long[] groupIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getGroupsTags(groupIds);
	}

	public static java.util.List<com.liferay.portlet.asset.model.AssetTag> getGroupTags(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getGroupTags(groupId);
	}

	public static java.util.List<com.liferay.portlet.asset.model.AssetTag> getGroupTags(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getGroupTags(groupId, start, end);
	}

	public static int getGroupTagsCount(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getGroupTagsCount(groupId);
	}

	public static java.util.List<com.liferay.portlet.asset.model.AssetTag> getSocialActivityCounterOffsetTags(
		long groupId, java.lang.String socialActivityCounterName,
		int startOffset, int endOffset)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getSocialActivityCounterOffsetTags(groupId,
			socialActivityCounterName, startOffset, endOffset);
	}

	public static java.util.List<com.liferay.portlet.asset.model.AssetTag> getSocialActivityCounterPeriodTags(
		long groupId, java.lang.String socialActivityCounterName,
		int startPeriod, int endPeriod)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getSocialActivityCounterPeriodTags(groupId,
			socialActivityCounterName, startPeriod, endPeriod);
	}

	public static com.liferay.portlet.asset.model.AssetTag getTag(long tagId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getTag(tagId);
	}

	public static com.liferay.portlet.asset.model.AssetTag getTag(
		long groupId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getTag(groupId, name);
	}

	public static long[] getTagIds(long groupId, java.lang.String[] names)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getTagIds(groupId, names);
	}

	public static long[] getTagIds(long[] groupIds, java.lang.String[] names)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getTagIds(groupIds, names);
	}

	public static java.lang.String[] getTagNames()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTagNames();
	}

	public static java.lang.String[] getTagNames(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTagNames(classNameId, classPK);
	}

	public static java.lang.String[] getTagNames(java.lang.String className,
		long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTagNames(className, classPK);
	}

	public static java.util.List<com.liferay.portlet.asset.model.AssetTag> getTags()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTags();
	}

	public static java.util.List<com.liferay.portlet.asset.model.AssetTag> getTags(
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTags(classNameId, classPK);
	}

	public static java.util.List<com.liferay.portlet.asset.model.AssetTag> getTags(
		long groupId, long classNameId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTags(groupId, classNameId, name);
	}

	public static java.util.List<com.liferay.portlet.asset.model.AssetTag> getTags(
		long groupId, long classNameId, java.lang.String name, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTags(groupId, classNameId, name, start, end);
	}

	public static java.util.List<com.liferay.portlet.asset.model.AssetTag> getTags(
		java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTags(className, classPK);
	}

	public static int getTagsSize(long groupId, long classNameId,
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTagsSize(groupId, classNameId, name);
	}

	public static boolean hasTag(long groupId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().hasTag(groupId, name);
	}

	public static com.liferay.portlet.asset.model.AssetTag incrementAssetCount(
		long tagId, long classNameId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().incrementAssetCount(tagId, classNameId);
	}

	public static void mergeTags(long fromTagId, long toTagId,
		boolean overrideProperties)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().mergeTags(fromTagId, toTagId, overrideProperties);
	}

	public static java.util.List<com.liferay.portlet.asset.model.AssetTag> search(
		long groupId, java.lang.String name, java.lang.String[] tagProperties,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().search(groupId, name, tagProperties, start, end);
	}

	public static com.liferay.portlet.asset.model.AssetTag updateTag(
		long userId, long tagId, java.lang.String name,
		java.lang.String[] tagProperties,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateTag(userId, tagId, name, tagProperties, serviceContext);
	}

	public static AssetTagLocalService getService() {
		if (_service == null) {
			_service = (AssetTagLocalService)PortalBeanLocatorUtil.locate(AssetTagLocalService.class.getName());

			ReferenceRegistry.registerReference(AssetTagLocalServiceUtil.class,
				"_service");
			MethodCache.remove(AssetTagLocalService.class);
		}

		return _service;
	}

	public void setService(AssetTagLocalService service) {
		MethodCache.remove(AssetTagLocalService.class);

		_service = service;

		ReferenceRegistry.registerReference(AssetTagLocalServiceUtil.class,
			"_service");
		MethodCache.remove(AssetTagLocalService.class);
	}

	private static AssetTagLocalService _service;
}