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

package com.liferay.portlet.journal.service.persistence;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.journal.model.JournalContentSearch;

import java.util.List;

/**
 * The persistence utility for the journal content search service. This utility wraps {@link JournalContentSearchPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JournalContentSearchPersistence
 * @see JournalContentSearchPersistenceImpl
 * @generated
 */
public class JournalContentSearchUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(JournalContentSearch journalContentSearch) {
		getPersistence().clearCache(journalContentSearch);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<JournalContentSearch> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<JournalContentSearch> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<JournalContentSearch> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static JournalContentSearch update(
		JournalContentSearch journalContentSearch, boolean merge)
		throws SystemException {
		return getPersistence().update(journalContentSearch, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static JournalContentSearch update(
		JournalContentSearch journalContentSearch, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence()
				   .update(journalContentSearch, merge, serviceContext);
	}

	/**
	* Caches the journal content search in the entity cache if it is enabled.
	*
	* @param journalContentSearch the journal content search
	*/
	public static void cacheResult(
		com.liferay.portlet.journal.model.JournalContentSearch journalContentSearch) {
		getPersistence().cacheResult(journalContentSearch);
	}

	/**
	* Caches the journal content searchs in the entity cache if it is enabled.
	*
	* @param journalContentSearchs the journal content searchs
	*/
	public static void cacheResult(
		java.util.List<com.liferay.portlet.journal.model.JournalContentSearch> journalContentSearchs) {
		getPersistence().cacheResult(journalContentSearchs);
	}

	/**
	* Creates a new journal content search with the primary key. Does not add the journal content search to the database.
	*
	* @param contentSearchId the primary key for the new journal content search
	* @return the new journal content search
	*/
	public static com.liferay.portlet.journal.model.JournalContentSearch create(
		long contentSearchId) {
		return getPersistence().create(contentSearchId);
	}

	/**
	* Removes the journal content search with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param contentSearchId the primary key of the journal content search
	* @return the journal content search that was removed
	* @throws com.liferay.portlet.journal.NoSuchContentSearchException if a journal content search with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.journal.model.JournalContentSearch remove(
		long contentSearchId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.journal.NoSuchContentSearchException {
		return getPersistence().remove(contentSearchId);
	}

	public static com.liferay.portlet.journal.model.JournalContentSearch updateImpl(
		com.liferay.portlet.journal.model.JournalContentSearch journalContentSearch,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(journalContentSearch, merge);
	}

	/**
	* Returns the journal content search with the primary key or throws a {@link com.liferay.portlet.journal.NoSuchContentSearchException} if it could not be found.
	*
	* @param contentSearchId the primary key of the journal content search
	* @return the journal content search
	* @throws com.liferay.portlet.journal.NoSuchContentSearchException if a journal content search with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.journal.model.JournalContentSearch findByPrimaryKey(
		long contentSearchId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.journal.NoSuchContentSearchException {
		return getPersistence().findByPrimaryKey(contentSearchId);
	}

	/**
	* Returns the journal content search with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param contentSearchId the primary key of the journal content search
	* @return the journal content search, or <code>null</code> if a journal content search with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.journal.model.JournalContentSearch fetchByPrimaryKey(
		long contentSearchId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(contentSearchId);
	}

	/**
	* Returns all the journal content searchs where articleId = &#63;.
	*
	* @param articleId the article ID
	* @return the matching journal content searchs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.journal.model.JournalContentSearch> findByArticleId(
		java.lang.String articleId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByArticleId(articleId);
	}

	/**
	* Returns a range of all the journal content searchs where articleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param articleId the article ID
	* @param start the lower bound of the range of journal content searchs
	* @param end the upper bound of the range of journal content searchs (not inclusive)
	* @return the range of matching journal content searchs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.journal.model.JournalContentSearch> findByArticleId(
		java.lang.String articleId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByArticleId(articleId, start, end);
	}

	/**
	* Returns an ordered range of all the journal content searchs where articleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param articleId the article ID
	* @param start the lower bound of the range of journal content searchs
	* @param end the upper bound of the range of journal content searchs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching journal content searchs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.journal.model.JournalContentSearch> findByArticleId(
		java.lang.String articleId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByArticleId(articleId, start, end, orderByComparator);
	}

	/**
	* Returns the first journal content search in the ordered set where articleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param articleId the article ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching journal content search
	* @throws com.liferay.portlet.journal.NoSuchContentSearchException if a matching journal content search could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.journal.model.JournalContentSearch findByArticleId_First(
		java.lang.String articleId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.journal.NoSuchContentSearchException {
		return getPersistence()
				   .findByArticleId_First(articleId, orderByComparator);
	}

	/**
	* Returns the last journal content search in the ordered set where articleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param articleId the article ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching journal content search
	* @throws com.liferay.portlet.journal.NoSuchContentSearchException if a matching journal content search could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.journal.model.JournalContentSearch findByArticleId_Last(
		java.lang.String articleId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.journal.NoSuchContentSearchException {
		return getPersistence()
				   .findByArticleId_Last(articleId, orderByComparator);
	}

	/**
	* Returns the journal content searchs before and after the current journal content search in the ordered set where articleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param contentSearchId the primary key of the current journal content search
	* @param articleId the article ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next journal content search
	* @throws com.liferay.portlet.journal.NoSuchContentSearchException if a journal content search with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.journal.model.JournalContentSearch[] findByArticleId_PrevAndNext(
		long contentSearchId, java.lang.String articleId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.journal.NoSuchContentSearchException {
		return getPersistence()
				   .findByArticleId_PrevAndNext(contentSearchId, articleId,
			orderByComparator);
	}

	/**
	* Returns all the journal content searchs where groupId = &#63; and privateLayout = &#63;.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @return the matching journal content searchs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.journal.model.JournalContentSearch> findByG_P(
		long groupId, boolean privateLayout)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_P(groupId, privateLayout);
	}

	/**
	* Returns a range of all the journal content searchs where groupId = &#63; and privateLayout = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param start the lower bound of the range of journal content searchs
	* @param end the upper bound of the range of journal content searchs (not inclusive)
	* @return the range of matching journal content searchs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.journal.model.JournalContentSearch> findByG_P(
		long groupId, boolean privateLayout, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_P(groupId, privateLayout, start, end);
	}

	/**
	* Returns an ordered range of all the journal content searchs where groupId = &#63; and privateLayout = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param start the lower bound of the range of journal content searchs
	* @param end the upper bound of the range of journal content searchs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching journal content searchs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.journal.model.JournalContentSearch> findByG_P(
		long groupId, boolean privateLayout, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P(groupId, privateLayout, start, end,
			orderByComparator);
	}

	/**
	* Returns the first journal content search in the ordered set where groupId = &#63; and privateLayout = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching journal content search
	* @throws com.liferay.portlet.journal.NoSuchContentSearchException if a matching journal content search could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.journal.model.JournalContentSearch findByG_P_First(
		long groupId, boolean privateLayout,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.journal.NoSuchContentSearchException {
		return getPersistence()
				   .findByG_P_First(groupId, privateLayout, orderByComparator);
	}

	/**
	* Returns the last journal content search in the ordered set where groupId = &#63; and privateLayout = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching journal content search
	* @throws com.liferay.portlet.journal.NoSuchContentSearchException if a matching journal content search could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.journal.model.JournalContentSearch findByG_P_Last(
		long groupId, boolean privateLayout,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.journal.NoSuchContentSearchException {
		return getPersistence()
				   .findByG_P_Last(groupId, privateLayout, orderByComparator);
	}

	/**
	* Returns the journal content searchs before and after the current journal content search in the ordered set where groupId = &#63; and privateLayout = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param contentSearchId the primary key of the current journal content search
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next journal content search
	* @throws com.liferay.portlet.journal.NoSuchContentSearchException if a journal content search with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.journal.model.JournalContentSearch[] findByG_P_PrevAndNext(
		long contentSearchId, long groupId, boolean privateLayout,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.journal.NoSuchContentSearchException {
		return getPersistence()
				   .findByG_P_PrevAndNext(contentSearchId, groupId,
			privateLayout, orderByComparator);
	}

	/**
	* Returns all the journal content searchs where groupId = &#63; and articleId = &#63;.
	*
	* @param groupId the group ID
	* @param articleId the article ID
	* @return the matching journal content searchs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.journal.model.JournalContentSearch> findByG_A(
		long groupId, java.lang.String articleId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_A(groupId, articleId);
	}

	/**
	* Returns a range of all the journal content searchs where groupId = &#63; and articleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param articleId the article ID
	* @param start the lower bound of the range of journal content searchs
	* @param end the upper bound of the range of journal content searchs (not inclusive)
	* @return the range of matching journal content searchs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.journal.model.JournalContentSearch> findByG_A(
		long groupId, java.lang.String articleId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_A(groupId, articleId, start, end);
	}

	/**
	* Returns an ordered range of all the journal content searchs where groupId = &#63; and articleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param articleId the article ID
	* @param start the lower bound of the range of journal content searchs
	* @param end the upper bound of the range of journal content searchs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching journal content searchs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.journal.model.JournalContentSearch> findByG_A(
		long groupId, java.lang.String articleId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_A(groupId, articleId, start, end, orderByComparator);
	}

	/**
	* Returns the first journal content search in the ordered set where groupId = &#63; and articleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param articleId the article ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching journal content search
	* @throws com.liferay.portlet.journal.NoSuchContentSearchException if a matching journal content search could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.journal.model.JournalContentSearch findByG_A_First(
		long groupId, java.lang.String articleId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.journal.NoSuchContentSearchException {
		return getPersistence()
				   .findByG_A_First(groupId, articleId, orderByComparator);
	}

	/**
	* Returns the last journal content search in the ordered set where groupId = &#63; and articleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param articleId the article ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching journal content search
	* @throws com.liferay.portlet.journal.NoSuchContentSearchException if a matching journal content search could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.journal.model.JournalContentSearch findByG_A_Last(
		long groupId, java.lang.String articleId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.journal.NoSuchContentSearchException {
		return getPersistence()
				   .findByG_A_Last(groupId, articleId, orderByComparator);
	}

	/**
	* Returns the journal content searchs before and after the current journal content search in the ordered set where groupId = &#63; and articleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param contentSearchId the primary key of the current journal content search
	* @param groupId the group ID
	* @param articleId the article ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next journal content search
	* @throws com.liferay.portlet.journal.NoSuchContentSearchException if a journal content search with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.journal.model.JournalContentSearch[] findByG_A_PrevAndNext(
		long contentSearchId, long groupId, java.lang.String articleId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.journal.NoSuchContentSearchException {
		return getPersistence()
				   .findByG_A_PrevAndNext(contentSearchId, groupId, articleId,
			orderByComparator);
	}

	/**
	* Returns all the journal content searchs where groupId = &#63; and privateLayout = &#63; and layoutId = &#63;.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param layoutId the layout ID
	* @return the matching journal content searchs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.journal.model.JournalContentSearch> findByG_P_L(
		long groupId, boolean privateLayout, long layoutId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_P_L(groupId, privateLayout, layoutId);
	}

	/**
	* Returns a range of all the journal content searchs where groupId = &#63; and privateLayout = &#63; and layoutId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param layoutId the layout ID
	* @param start the lower bound of the range of journal content searchs
	* @param end the upper bound of the range of journal content searchs (not inclusive)
	* @return the range of matching journal content searchs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.journal.model.JournalContentSearch> findByG_P_L(
		long groupId, boolean privateLayout, long layoutId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_L(groupId, privateLayout, layoutId, start, end);
	}

	/**
	* Returns an ordered range of all the journal content searchs where groupId = &#63; and privateLayout = &#63; and layoutId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param layoutId the layout ID
	* @param start the lower bound of the range of journal content searchs
	* @param end the upper bound of the range of journal content searchs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching journal content searchs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.journal.model.JournalContentSearch> findByG_P_L(
		long groupId, boolean privateLayout, long layoutId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_L(groupId, privateLayout, layoutId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first journal content search in the ordered set where groupId = &#63; and privateLayout = &#63; and layoutId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param layoutId the layout ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching journal content search
	* @throws com.liferay.portlet.journal.NoSuchContentSearchException if a matching journal content search could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.journal.model.JournalContentSearch findByG_P_L_First(
		long groupId, boolean privateLayout, long layoutId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.journal.NoSuchContentSearchException {
		return getPersistence()
				   .findByG_P_L_First(groupId, privateLayout, layoutId,
			orderByComparator);
	}

	/**
	* Returns the last journal content search in the ordered set where groupId = &#63; and privateLayout = &#63; and layoutId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param layoutId the layout ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching journal content search
	* @throws com.liferay.portlet.journal.NoSuchContentSearchException if a matching journal content search could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.journal.model.JournalContentSearch findByG_P_L_Last(
		long groupId, boolean privateLayout, long layoutId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.journal.NoSuchContentSearchException {
		return getPersistence()
				   .findByG_P_L_Last(groupId, privateLayout, layoutId,
			orderByComparator);
	}

	/**
	* Returns the journal content searchs before and after the current journal content search in the ordered set where groupId = &#63; and privateLayout = &#63; and layoutId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param contentSearchId the primary key of the current journal content search
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param layoutId the layout ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next journal content search
	* @throws com.liferay.portlet.journal.NoSuchContentSearchException if a journal content search with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.journal.model.JournalContentSearch[] findByG_P_L_PrevAndNext(
		long contentSearchId, long groupId, boolean privateLayout,
		long layoutId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.journal.NoSuchContentSearchException {
		return getPersistence()
				   .findByG_P_L_PrevAndNext(contentSearchId, groupId,
			privateLayout, layoutId, orderByComparator);
	}

	/**
	* Returns all the journal content searchs where groupId = &#63; and privateLayout = &#63; and articleId = &#63;.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param articleId the article ID
	* @return the matching journal content searchs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.journal.model.JournalContentSearch> findByG_P_A(
		long groupId, boolean privateLayout, java.lang.String articleId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_P_A(groupId, privateLayout, articleId);
	}

	/**
	* Returns a range of all the journal content searchs where groupId = &#63; and privateLayout = &#63; and articleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param articleId the article ID
	* @param start the lower bound of the range of journal content searchs
	* @param end the upper bound of the range of journal content searchs (not inclusive)
	* @return the range of matching journal content searchs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.journal.model.JournalContentSearch> findByG_P_A(
		long groupId, boolean privateLayout, java.lang.String articleId,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_A(groupId, privateLayout, articleId, start, end);
	}

	/**
	* Returns an ordered range of all the journal content searchs where groupId = &#63; and privateLayout = &#63; and articleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param articleId the article ID
	* @param start the lower bound of the range of journal content searchs
	* @param end the upper bound of the range of journal content searchs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching journal content searchs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.journal.model.JournalContentSearch> findByG_P_A(
		long groupId, boolean privateLayout, java.lang.String articleId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_A(groupId, privateLayout, articleId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first journal content search in the ordered set where groupId = &#63; and privateLayout = &#63; and articleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param articleId the article ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching journal content search
	* @throws com.liferay.portlet.journal.NoSuchContentSearchException if a matching journal content search could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.journal.model.JournalContentSearch findByG_P_A_First(
		long groupId, boolean privateLayout, java.lang.String articleId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.journal.NoSuchContentSearchException {
		return getPersistence()
				   .findByG_P_A_First(groupId, privateLayout, articleId,
			orderByComparator);
	}

	/**
	* Returns the last journal content search in the ordered set where groupId = &#63; and privateLayout = &#63; and articleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param articleId the article ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching journal content search
	* @throws com.liferay.portlet.journal.NoSuchContentSearchException if a matching journal content search could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.journal.model.JournalContentSearch findByG_P_A_Last(
		long groupId, boolean privateLayout, java.lang.String articleId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.journal.NoSuchContentSearchException {
		return getPersistence()
				   .findByG_P_A_Last(groupId, privateLayout, articleId,
			orderByComparator);
	}

	/**
	* Returns the journal content searchs before and after the current journal content search in the ordered set where groupId = &#63; and privateLayout = &#63; and articleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param contentSearchId the primary key of the current journal content search
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param articleId the article ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next journal content search
	* @throws com.liferay.portlet.journal.NoSuchContentSearchException if a journal content search with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.journal.model.JournalContentSearch[] findByG_P_A_PrevAndNext(
		long contentSearchId, long groupId, boolean privateLayout,
		java.lang.String articleId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.journal.NoSuchContentSearchException {
		return getPersistence()
				   .findByG_P_A_PrevAndNext(contentSearchId, groupId,
			privateLayout, articleId, orderByComparator);
	}

	/**
	* Returns all the journal content searchs where groupId = &#63; and privateLayout = &#63; and layoutId = &#63; and portletId = &#63;.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param layoutId the layout ID
	* @param portletId the portlet ID
	* @return the matching journal content searchs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.journal.model.JournalContentSearch> findByG_P_L_P(
		long groupId, boolean privateLayout, long layoutId,
		java.lang.String portletId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_L_P(groupId, privateLayout, layoutId, portletId);
	}

	/**
	* Returns a range of all the journal content searchs where groupId = &#63; and privateLayout = &#63; and layoutId = &#63; and portletId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param layoutId the layout ID
	* @param portletId the portlet ID
	* @param start the lower bound of the range of journal content searchs
	* @param end the upper bound of the range of journal content searchs (not inclusive)
	* @return the range of matching journal content searchs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.journal.model.JournalContentSearch> findByG_P_L_P(
		long groupId, boolean privateLayout, long layoutId,
		java.lang.String portletId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_L_P(groupId, privateLayout, layoutId, portletId,
			start, end);
	}

	/**
	* Returns an ordered range of all the journal content searchs where groupId = &#63; and privateLayout = &#63; and layoutId = &#63; and portletId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param layoutId the layout ID
	* @param portletId the portlet ID
	* @param start the lower bound of the range of journal content searchs
	* @param end the upper bound of the range of journal content searchs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching journal content searchs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.journal.model.JournalContentSearch> findByG_P_L_P(
		long groupId, boolean privateLayout, long layoutId,
		java.lang.String portletId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_L_P(groupId, privateLayout, layoutId, portletId,
			start, end, orderByComparator);
	}

	/**
	* Returns the first journal content search in the ordered set where groupId = &#63; and privateLayout = &#63; and layoutId = &#63; and portletId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param layoutId the layout ID
	* @param portletId the portlet ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching journal content search
	* @throws com.liferay.portlet.journal.NoSuchContentSearchException if a matching journal content search could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.journal.model.JournalContentSearch findByG_P_L_P_First(
		long groupId, boolean privateLayout, long layoutId,
		java.lang.String portletId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.journal.NoSuchContentSearchException {
		return getPersistence()
				   .findByG_P_L_P_First(groupId, privateLayout, layoutId,
			portletId, orderByComparator);
	}

	/**
	* Returns the last journal content search in the ordered set where groupId = &#63; and privateLayout = &#63; and layoutId = &#63; and portletId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param layoutId the layout ID
	* @param portletId the portlet ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching journal content search
	* @throws com.liferay.portlet.journal.NoSuchContentSearchException if a matching journal content search could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.journal.model.JournalContentSearch findByG_P_L_P_Last(
		long groupId, boolean privateLayout, long layoutId,
		java.lang.String portletId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.journal.NoSuchContentSearchException {
		return getPersistence()
				   .findByG_P_L_P_Last(groupId, privateLayout, layoutId,
			portletId, orderByComparator);
	}

	/**
	* Returns the journal content searchs before and after the current journal content search in the ordered set where groupId = &#63; and privateLayout = &#63; and layoutId = &#63; and portletId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param contentSearchId the primary key of the current journal content search
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param layoutId the layout ID
	* @param portletId the portlet ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next journal content search
	* @throws com.liferay.portlet.journal.NoSuchContentSearchException if a journal content search with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.journal.model.JournalContentSearch[] findByG_P_L_P_PrevAndNext(
		long contentSearchId, long groupId, boolean privateLayout,
		long layoutId, java.lang.String portletId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.journal.NoSuchContentSearchException {
		return getPersistence()
				   .findByG_P_L_P_PrevAndNext(contentSearchId, groupId,
			privateLayout, layoutId, portletId, orderByComparator);
	}

	/**
	* Returns the journal content search where groupId = &#63; and privateLayout = &#63; and layoutId = &#63; and portletId = &#63; and articleId = &#63; or throws a {@link com.liferay.portlet.journal.NoSuchContentSearchException} if it could not be found.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param layoutId the layout ID
	* @param portletId the portlet ID
	* @param articleId the article ID
	* @return the matching journal content search
	* @throws com.liferay.portlet.journal.NoSuchContentSearchException if a matching journal content search could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.journal.model.JournalContentSearch findByG_P_L_P_A(
		long groupId, boolean privateLayout, long layoutId,
		java.lang.String portletId, java.lang.String articleId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.journal.NoSuchContentSearchException {
		return getPersistence()
				   .findByG_P_L_P_A(groupId, privateLayout, layoutId,
			portletId, articleId);
	}

	/**
	* Returns the journal content search where groupId = &#63; and privateLayout = &#63; and layoutId = &#63; and portletId = &#63; and articleId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param layoutId the layout ID
	* @param portletId the portlet ID
	* @param articleId the article ID
	* @return the matching journal content search, or <code>null</code> if a matching journal content search could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.journal.model.JournalContentSearch fetchByG_P_L_P_A(
		long groupId, boolean privateLayout, long layoutId,
		java.lang.String portletId, java.lang.String articleId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_P_L_P_A(groupId, privateLayout, layoutId,
			portletId, articleId);
	}

	/**
	* Returns the journal content search where groupId = &#63; and privateLayout = &#63; and layoutId = &#63; and portletId = &#63; and articleId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param layoutId the layout ID
	* @param portletId the portlet ID
	* @param articleId the article ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching journal content search, or <code>null</code> if a matching journal content search could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.journal.model.JournalContentSearch fetchByG_P_L_P_A(
		long groupId, boolean privateLayout, long layoutId,
		java.lang.String portletId, java.lang.String articleId,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_P_L_P_A(groupId, privateLayout, layoutId,
			portletId, articleId, retrieveFromCache);
	}

	/**
	* Returns all the journal content searchs.
	*
	* @return the journal content searchs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.journal.model.JournalContentSearch> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the journal content searchs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of journal content searchs
	* @param end the upper bound of the range of journal content searchs (not inclusive)
	* @return the range of journal content searchs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.journal.model.JournalContentSearch> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the journal content searchs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of journal content searchs
	* @param end the upper bound of the range of journal content searchs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of journal content searchs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.journal.model.JournalContentSearch> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the journal content searchs where articleId = &#63; from the database.
	*
	* @param articleId the article ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByArticleId(java.lang.String articleId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByArticleId(articleId);
	}

	/**
	* Removes all the journal content searchs where groupId = &#63; and privateLayout = &#63; from the database.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_P(long groupId, boolean privateLayout)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_P(groupId, privateLayout);
	}

	/**
	* Removes all the journal content searchs where groupId = &#63; and articleId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param articleId the article ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_A(long groupId, java.lang.String articleId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_A(groupId, articleId);
	}

	/**
	* Removes all the journal content searchs where groupId = &#63; and privateLayout = &#63; and layoutId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param layoutId the layout ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_P_L(long groupId, boolean privateLayout,
		long layoutId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_P_L(groupId, privateLayout, layoutId);
	}

	/**
	* Removes all the journal content searchs where groupId = &#63; and privateLayout = &#63; and articleId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param articleId the article ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_P_A(long groupId, boolean privateLayout,
		java.lang.String articleId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_P_A(groupId, privateLayout, articleId);
	}

	/**
	* Removes all the journal content searchs where groupId = &#63; and privateLayout = &#63; and layoutId = &#63; and portletId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param layoutId the layout ID
	* @param portletId the portlet ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_P_L_P(long groupId, boolean privateLayout,
		long layoutId, java.lang.String portletId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByG_P_L_P(groupId, privateLayout, layoutId, portletId);
	}

	/**
	* Removes the journal content search where groupId = &#63; and privateLayout = &#63; and layoutId = &#63; and portletId = &#63; and articleId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param layoutId the layout ID
	* @param portletId the portlet ID
	* @param articleId the article ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_P_L_P_A(long groupId, boolean privateLayout,
		long layoutId, java.lang.String portletId, java.lang.String articleId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.journal.NoSuchContentSearchException {
		getPersistence()
			.removeByG_P_L_P_A(groupId, privateLayout, layoutId, portletId,
			articleId);
	}

	/**
	* Removes all the journal content searchs from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of journal content searchs where articleId = &#63;.
	*
	* @param articleId the article ID
	* @return the number of matching journal content searchs
	* @throws SystemException if a system exception occurred
	*/
	public static int countByArticleId(java.lang.String articleId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByArticleId(articleId);
	}

	/**
	* Returns the number of journal content searchs where groupId = &#63; and privateLayout = &#63;.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @return the number of matching journal content searchs
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_P(long groupId, boolean privateLayout)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_P(groupId, privateLayout);
	}

	/**
	* Returns the number of journal content searchs where groupId = &#63; and articleId = &#63;.
	*
	* @param groupId the group ID
	* @param articleId the article ID
	* @return the number of matching journal content searchs
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_A(long groupId, java.lang.String articleId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_A(groupId, articleId);
	}

	/**
	* Returns the number of journal content searchs where groupId = &#63; and privateLayout = &#63; and layoutId = &#63;.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param layoutId the layout ID
	* @return the number of matching journal content searchs
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_P_L(long groupId, boolean privateLayout,
		long layoutId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_P_L(groupId, privateLayout, layoutId);
	}

	/**
	* Returns the number of journal content searchs where groupId = &#63; and privateLayout = &#63; and articleId = &#63;.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param articleId the article ID
	* @return the number of matching journal content searchs
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_P_A(long groupId, boolean privateLayout,
		java.lang.String articleId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_P_A(groupId, privateLayout, articleId);
	}

	/**
	* Returns the number of journal content searchs where groupId = &#63; and privateLayout = &#63; and layoutId = &#63; and portletId = &#63;.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param layoutId the layout ID
	* @param portletId the portlet ID
	* @return the number of matching journal content searchs
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_P_L_P(long groupId, boolean privateLayout,
		long layoutId, java.lang.String portletId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByG_P_L_P(groupId, privateLayout, layoutId, portletId);
	}

	/**
	* Returns the number of journal content searchs where groupId = &#63; and privateLayout = &#63; and layoutId = &#63; and portletId = &#63; and articleId = &#63;.
	*
	* @param groupId the group ID
	* @param privateLayout the private layout
	* @param layoutId the layout ID
	* @param portletId the portlet ID
	* @param articleId the article ID
	* @return the number of matching journal content searchs
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_P_L_P_A(long groupId, boolean privateLayout,
		long layoutId, java.lang.String portletId, java.lang.String articleId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByG_P_L_P_A(groupId, privateLayout, layoutId,
			portletId, articleId);
	}

	/**
	* Returns the number of journal content searchs.
	*
	* @return the number of journal content searchs
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static JournalContentSearchPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (JournalContentSearchPersistence)PortalBeanLocatorUtil.locate(JournalContentSearchPersistence.class.getName());

			ReferenceRegistry.registerReference(JournalContentSearchUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(JournalContentSearchPersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(JournalContentSearchUtil.class,
			"_persistence");
	}

	private static JournalContentSearchPersistence _persistence;
}