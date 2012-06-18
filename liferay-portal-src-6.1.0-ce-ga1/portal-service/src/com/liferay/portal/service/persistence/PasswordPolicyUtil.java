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

package com.liferay.portal.service.persistence;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.model.PasswordPolicy;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the password policy service. This utility wraps {@link PasswordPolicyPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PasswordPolicyPersistence
 * @see PasswordPolicyPersistenceImpl
 * @generated
 */
public class PasswordPolicyUtil {
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
	public static void clearCache(PasswordPolicy passwordPolicy) {
		getPersistence().clearCache(passwordPolicy);
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
	public static List<PasswordPolicy> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<PasswordPolicy> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<PasswordPolicy> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static PasswordPolicy update(PasswordPolicy passwordPolicy,
		boolean merge) throws SystemException {
		return getPersistence().update(passwordPolicy, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static PasswordPolicy update(PasswordPolicy passwordPolicy,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(passwordPolicy, merge, serviceContext);
	}

	/**
	* Caches the password policy in the entity cache if it is enabled.
	*
	* @param passwordPolicy the password policy
	*/
	public static void cacheResult(
		com.liferay.portal.model.PasswordPolicy passwordPolicy) {
		getPersistence().cacheResult(passwordPolicy);
	}

	/**
	* Caches the password policies in the entity cache if it is enabled.
	*
	* @param passwordPolicies the password policies
	*/
	public static void cacheResult(
		java.util.List<com.liferay.portal.model.PasswordPolicy> passwordPolicies) {
		getPersistence().cacheResult(passwordPolicies);
	}

	/**
	* Creates a new password policy with the primary key. Does not add the password policy to the database.
	*
	* @param passwordPolicyId the primary key for the new password policy
	* @return the new password policy
	*/
	public static com.liferay.portal.model.PasswordPolicy create(
		long passwordPolicyId) {
		return getPersistence().create(passwordPolicyId);
	}

	/**
	* Removes the password policy with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param passwordPolicyId the primary key of the password policy
	* @return the password policy that was removed
	* @throws com.liferay.portal.NoSuchPasswordPolicyException if a password policy with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.PasswordPolicy remove(
		long passwordPolicyId)
		throws com.liferay.portal.NoSuchPasswordPolicyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(passwordPolicyId);
	}

	public static com.liferay.portal.model.PasswordPolicy updateImpl(
		com.liferay.portal.model.PasswordPolicy passwordPolicy, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(passwordPolicy, merge);
	}

	/**
	* Returns the password policy with the primary key or throws a {@link com.liferay.portal.NoSuchPasswordPolicyException} if it could not be found.
	*
	* @param passwordPolicyId the primary key of the password policy
	* @return the password policy
	* @throws com.liferay.portal.NoSuchPasswordPolicyException if a password policy with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.PasswordPolicy findByPrimaryKey(
		long passwordPolicyId)
		throws com.liferay.portal.NoSuchPasswordPolicyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(passwordPolicyId);
	}

	/**
	* Returns the password policy with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param passwordPolicyId the primary key of the password policy
	* @return the password policy, or <code>null</code> if a password policy with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.PasswordPolicy fetchByPrimaryKey(
		long passwordPolicyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(passwordPolicyId);
	}

	/**
	* Returns the password policy where companyId = &#63; and defaultPolicy = &#63; or throws a {@link com.liferay.portal.NoSuchPasswordPolicyException} if it could not be found.
	*
	* @param companyId the company ID
	* @param defaultPolicy the default policy
	* @return the matching password policy
	* @throws com.liferay.portal.NoSuchPasswordPolicyException if a matching password policy could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.PasswordPolicy findByC_DP(
		long companyId, boolean defaultPolicy)
		throws com.liferay.portal.NoSuchPasswordPolicyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_DP(companyId, defaultPolicy);
	}

	/**
	* Returns the password policy where companyId = &#63; and defaultPolicy = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID
	* @param defaultPolicy the default policy
	* @return the matching password policy, or <code>null</code> if a matching password policy could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.PasswordPolicy fetchByC_DP(
		long companyId, boolean defaultPolicy)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByC_DP(companyId, defaultPolicy);
	}

	/**
	* Returns the password policy where companyId = &#63; and defaultPolicy = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company ID
	* @param defaultPolicy the default policy
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching password policy, or <code>null</code> if a matching password policy could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.PasswordPolicy fetchByC_DP(
		long companyId, boolean defaultPolicy, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_DP(companyId, defaultPolicy, retrieveFromCache);
	}

	/**
	* Returns the password policy where companyId = &#63; and name = &#63; or throws a {@link com.liferay.portal.NoSuchPasswordPolicyException} if it could not be found.
	*
	* @param companyId the company ID
	* @param name the name
	* @return the matching password policy
	* @throws com.liferay.portal.NoSuchPasswordPolicyException if a matching password policy could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.PasswordPolicy findByC_N(
		long companyId, java.lang.String name)
		throws com.liferay.portal.NoSuchPasswordPolicyException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_N(companyId, name);
	}

	/**
	* Returns the password policy where companyId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID
	* @param name the name
	* @return the matching password policy, or <code>null</code> if a matching password policy could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.PasswordPolicy fetchByC_N(
		long companyId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByC_N(companyId, name);
	}

	/**
	* Returns the password policy where companyId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company ID
	* @param name the name
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching password policy, or <code>null</code> if a matching password policy could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.PasswordPolicy fetchByC_N(
		long companyId, java.lang.String name, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByC_N(companyId, name, retrieveFromCache);
	}

	/**
	* Returns all the password policies.
	*
	* @return the password policies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.PasswordPolicy> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the password policies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of password policies
	* @param end the upper bound of the range of password policies (not inclusive)
	* @return the range of password policies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.PasswordPolicy> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the password policies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of password policies
	* @param end the upper bound of the range of password policies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of password policies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.PasswordPolicy> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes the password policy where companyId = &#63; and defaultPolicy = &#63; from the database.
	*
	* @param companyId the company ID
	* @param defaultPolicy the default policy
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_DP(long companyId, boolean defaultPolicy)
		throws com.liferay.portal.NoSuchPasswordPolicyException,
			com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByC_DP(companyId, defaultPolicy);
	}

	/**
	* Removes the password policy where companyId = &#63; and name = &#63; from the database.
	*
	* @param companyId the company ID
	* @param name the name
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_N(long companyId, java.lang.String name)
		throws com.liferay.portal.NoSuchPasswordPolicyException,
			com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByC_N(companyId, name);
	}

	/**
	* Removes all the password policies from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of password policies where companyId = &#63; and defaultPolicy = &#63;.
	*
	* @param companyId the company ID
	* @param defaultPolicy the default policy
	* @return the number of matching password policies
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_DP(long companyId, boolean defaultPolicy)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_DP(companyId, defaultPolicy);
	}

	/**
	* Returns the number of password policies where companyId = &#63; and name = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @return the number of matching password policies
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_N(long companyId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_N(companyId, name);
	}

	/**
	* Returns the number of password policies.
	*
	* @return the number of password policies
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static PasswordPolicyPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (PasswordPolicyPersistence)PortalBeanLocatorUtil.locate(PasswordPolicyPersistence.class.getName());

			ReferenceRegistry.registerReference(PasswordPolicyUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(PasswordPolicyPersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(PasswordPolicyUtil.class,
			"_persistence");
	}

	private static PasswordPolicyPersistence _persistence;
}