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

package com.liferay.portal.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.security.auth.HttpPrincipal;
import com.liferay.portal.service.OrganizationServiceUtil;

/**
 * <p>
 * This class provides a HTTP utility for the
 * {@link com.liferay.portal.service.OrganizationServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * {@link com.liferay.portal.security.auth.HttpPrincipal} parameter.
 * </p>
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       OrganizationServiceSoap
 * @see       com.liferay.portal.security.auth.HttpPrincipal
 * @see       com.liferay.portal.service.OrganizationServiceUtil
 * @generated
 */
public class OrganizationServiceHttp {
	public static void addGroupOrganizations(HttpPrincipal httpPrincipal,
		long groupId, long[] organizationIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		try {
			MethodKey methodKey = new MethodKey(OrganizationServiceUtil.class.getName(),
					"addGroupOrganizations",
					_addGroupOrganizationsParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					organizationIds);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				if (e instanceof com.liferay.portal.kernel.exception.SystemException) {
					throw (com.liferay.portal.kernel.exception.SystemException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.model.Organization addOrganization(
		HttpPrincipal httpPrincipal, long parentOrganizationId,
		java.lang.String name, java.lang.String type, boolean recursable,
		long regionId, long countryId, int statusId, java.lang.String comments,
		boolean site,
		java.util.List<com.liferay.portal.model.Address> addresses,
		java.util.List<com.liferay.portal.model.EmailAddress> emailAddresses,
		java.util.List<com.liferay.portal.model.OrgLabor> orgLabors,
		java.util.List<com.liferay.portal.model.Phone> phones,
		java.util.List<com.liferay.portal.model.Website> websites,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		try {
			MethodKey methodKey = new MethodKey(OrganizationServiceUtil.class.getName(),
					"addOrganization", _addOrganizationParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					parentOrganizationId, name, type, recursable, regionId,
					countryId, statusId, comments, site, addresses,
					emailAddresses, orgLabors, phones, websites, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				if (e instanceof com.liferay.portal.kernel.exception.SystemException) {
					throw (com.liferay.portal.kernel.exception.SystemException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.model.Organization)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.model.Organization addOrganization(
		HttpPrincipal httpPrincipal, long parentOrganizationId,
		java.lang.String name, java.lang.String type, boolean recursable,
		long regionId, long countryId, int statusId, java.lang.String comments,
		boolean site, com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		try {
			MethodKey methodKey = new MethodKey(OrganizationServiceUtil.class.getName(),
					"addOrganization", _addOrganizationParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					parentOrganizationId, name, type, recursable, regionId,
					countryId, statusId, comments, site, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				if (e instanceof com.liferay.portal.kernel.exception.SystemException) {
					throw (com.liferay.portal.kernel.exception.SystemException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.model.Organization)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void addPasswordPolicyOrganizations(
		HttpPrincipal httpPrincipal, long passwordPolicyId,
		long[] organizationIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		try {
			MethodKey methodKey = new MethodKey(OrganizationServiceUtil.class.getName(),
					"addPasswordPolicyOrganizations",
					_addPasswordPolicyOrganizationsParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					passwordPolicyId, organizationIds);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				if (e instanceof com.liferay.portal.kernel.exception.SystemException) {
					throw (com.liferay.portal.kernel.exception.SystemException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteLogo(HttpPrincipal httpPrincipal,
		long organizationId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		try {
			MethodKey methodKey = new MethodKey(OrganizationServiceUtil.class.getName(),
					"deleteLogo", _deleteLogoParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					organizationId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				if (e instanceof com.liferay.portal.kernel.exception.SystemException) {
					throw (com.liferay.portal.kernel.exception.SystemException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteOrganization(HttpPrincipal httpPrincipal,
		long organizationId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		try {
			MethodKey methodKey = new MethodKey(OrganizationServiceUtil.class.getName(),
					"deleteOrganization", _deleteOrganizationParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					organizationId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				if (e instanceof com.liferay.portal.kernel.exception.SystemException) {
					throw (com.liferay.portal.kernel.exception.SystemException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.portal.model.Organization> getManageableOrganizations(
		HttpPrincipal httpPrincipal, java.lang.String actionId, int max)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		try {
			MethodKey methodKey = new MethodKey(OrganizationServiceUtil.class.getName(),
					"getManageableOrganizations",
					_getManageableOrganizationsParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					actionId, max);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				if (e instanceof com.liferay.portal.kernel.exception.SystemException) {
					throw (com.liferay.portal.kernel.exception.SystemException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.portal.model.Organization>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.model.Organization getOrganization(
		HttpPrincipal httpPrincipal, long organizationId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		try {
			MethodKey methodKey = new MethodKey(OrganizationServiceUtil.class.getName(),
					"getOrganization", _getOrganizationParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					organizationId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				if (e instanceof com.liferay.portal.kernel.exception.SystemException) {
					throw (com.liferay.portal.kernel.exception.SystemException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.model.Organization)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static long getOrganizationId(HttpPrincipal httpPrincipal,
		long companyId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		try {
			MethodKey methodKey = new MethodKey(OrganizationServiceUtil.class.getName(),
					"getOrganizationId", _getOrganizationIdParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, name);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.SystemException) {
					throw (com.liferay.portal.kernel.exception.SystemException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Long)returnObj).longValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.portal.model.Organization> getOrganizations(
		HttpPrincipal httpPrincipal, long companyId, long parentOrganizationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		try {
			MethodKey methodKey = new MethodKey(OrganizationServiceUtil.class.getName(),
					"getOrganizations", _getOrganizationsParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, parentOrganizationId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.SystemException) {
					throw (com.liferay.portal.kernel.exception.SystemException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.portal.model.Organization>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.portal.model.Organization> getOrganizations(
		HttpPrincipal httpPrincipal, long companyId, long parentOrganizationId,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		try {
			MethodKey methodKey = new MethodKey(OrganizationServiceUtil.class.getName(),
					"getOrganizations", _getOrganizationsParameterTypes10);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, parentOrganizationId, start, end);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.SystemException) {
					throw (com.liferay.portal.kernel.exception.SystemException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.portal.model.Organization>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getOrganizationsCount(HttpPrincipal httpPrincipal,
		long companyId, long parentOrganizationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		try {
			MethodKey methodKey = new MethodKey(OrganizationServiceUtil.class.getName(),
					"getOrganizationsCount",
					_getOrganizationsCountParameterTypes11);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, parentOrganizationId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.SystemException) {
					throw (com.liferay.portal.kernel.exception.SystemException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.portal.model.Organization> getUserOrganizations(
		HttpPrincipal httpPrincipal, long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		try {
			MethodKey methodKey = new MethodKey(OrganizationServiceUtil.class.getName(),
					"getUserOrganizations",
					_getUserOrganizationsParameterTypes12);

			MethodHandler methodHandler = new MethodHandler(methodKey, userId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				if (e instanceof com.liferay.portal.kernel.exception.SystemException) {
					throw (com.liferay.portal.kernel.exception.SystemException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.portal.model.Organization>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void setGroupOrganizations(HttpPrincipal httpPrincipal,
		long groupId, long[] organizationIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		try {
			MethodKey methodKey = new MethodKey(OrganizationServiceUtil.class.getName(),
					"setGroupOrganizations",
					_setGroupOrganizationsParameterTypes13);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					organizationIds);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				if (e instanceof com.liferay.portal.kernel.exception.SystemException) {
					throw (com.liferay.portal.kernel.exception.SystemException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void unsetGroupOrganizations(HttpPrincipal httpPrincipal,
		long groupId, long[] organizationIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		try {
			MethodKey methodKey = new MethodKey(OrganizationServiceUtil.class.getName(),
					"unsetGroupOrganizations",
					_unsetGroupOrganizationsParameterTypes14);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					organizationIds);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				if (e instanceof com.liferay.portal.kernel.exception.SystemException) {
					throw (com.liferay.portal.kernel.exception.SystemException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void unsetPasswordPolicyOrganizations(
		HttpPrincipal httpPrincipal, long passwordPolicyId,
		long[] organizationIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		try {
			MethodKey methodKey = new MethodKey(OrganizationServiceUtil.class.getName(),
					"unsetPasswordPolicyOrganizations",
					_unsetPasswordPolicyOrganizationsParameterTypes15);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					passwordPolicyId, organizationIds);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				if (e instanceof com.liferay.portal.kernel.exception.SystemException) {
					throw (com.liferay.portal.kernel.exception.SystemException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.model.Organization updateOrganization(
		HttpPrincipal httpPrincipal, long organizationId,
		long parentOrganizationId, java.lang.String name,
		java.lang.String type, boolean recursable, long regionId,
		long countryId, int statusId, java.lang.String comments, boolean site,
		java.util.List<com.liferay.portal.model.Address> addresses,
		java.util.List<com.liferay.portal.model.EmailAddress> emailAddresses,
		java.util.List<com.liferay.portal.model.OrgLabor> orgLabors,
		java.util.List<com.liferay.portal.model.Phone> phones,
		java.util.List<com.liferay.portal.model.Website> websites,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		try {
			MethodKey methodKey = new MethodKey(OrganizationServiceUtil.class.getName(),
					"updateOrganization", _updateOrganizationParameterTypes16);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					organizationId, parentOrganizationId, name, type,
					recursable, regionId, countryId, statusId, comments, site,
					addresses, emailAddresses, orgLabors, phones, websites,
					serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				if (e instanceof com.liferay.portal.kernel.exception.SystemException) {
					throw (com.liferay.portal.kernel.exception.SystemException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.model.Organization)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.model.Organization updateOrganization(
		HttpPrincipal httpPrincipal, long organizationId,
		long parentOrganizationId, java.lang.String name,
		java.lang.String type, boolean recursable, long regionId,
		long countryId, int statusId, java.lang.String comments, boolean site,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		try {
			MethodKey methodKey = new MethodKey(OrganizationServiceUtil.class.getName(),
					"updateOrganization", _updateOrganizationParameterTypes17);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					organizationId, parentOrganizationId, name, type,
					recursable, regionId, countryId, statusId, comments, site,
					serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				if (e instanceof com.liferay.portal.kernel.exception.SystemException) {
					throw (com.liferay.portal.kernel.exception.SystemException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.model.Organization)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(OrganizationServiceHttp.class);
	private static final Class<?>[] _addGroupOrganizationsParameterTypes0 = new Class[] {
			long.class, long[].class
		};
	private static final Class<?>[] _addOrganizationParameterTypes1 = new Class[] {
			long.class, java.lang.String.class, java.lang.String.class,
			boolean.class, long.class, long.class, int.class,
			java.lang.String.class, boolean.class, java.util.List.class,
			java.util.List.class, java.util.List.class, java.util.List.class,
			java.util.List.class,
			com.liferay.portal.service.ServiceContext.class
		};
	private static final Class<?>[] _addOrganizationParameterTypes2 = new Class[] {
			long.class, java.lang.String.class, java.lang.String.class,
			boolean.class, long.class, long.class, int.class,
			java.lang.String.class, boolean.class,
			com.liferay.portal.service.ServiceContext.class
		};
	private static final Class<?>[] _addPasswordPolicyOrganizationsParameterTypes3 =
		new Class[] { long.class, long[].class };
	private static final Class<?>[] _deleteLogoParameterTypes4 = new Class[] {
			long.class
		};
	private static final Class<?>[] _deleteOrganizationParameterTypes5 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getManageableOrganizationsParameterTypes6 = new Class[] {
			java.lang.String.class, int.class
		};
	private static final Class<?>[] _getOrganizationParameterTypes7 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getOrganizationIdParameterTypes8 = new Class[] {
			long.class, java.lang.String.class
		};
	private static final Class<?>[] _getOrganizationsParameterTypes9 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _getOrganizationsParameterTypes10 = new Class[] {
			long.class, long.class, int.class, int.class
		};
	private static final Class<?>[] _getOrganizationsCountParameterTypes11 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _getUserOrganizationsParameterTypes12 = new Class[] {
			long.class
		};
	private static final Class<?>[] _setGroupOrganizationsParameterTypes13 = new Class[] {
			long.class, long[].class
		};
	private static final Class<?>[] _unsetGroupOrganizationsParameterTypes14 = new Class[] {
			long.class, long[].class
		};
	private static final Class<?>[] _unsetPasswordPolicyOrganizationsParameterTypes15 =
		new Class[] { long.class, long[].class };
	private static final Class<?>[] _updateOrganizationParameterTypes16 = new Class[] {
			long.class, long.class, java.lang.String.class,
			java.lang.String.class, boolean.class, long.class, long.class,
			int.class, java.lang.String.class, boolean.class,
			java.util.List.class, java.util.List.class, java.util.List.class,
			java.util.List.class, java.util.List.class,
			com.liferay.portal.service.ServiceContext.class
		};
	private static final Class<?>[] _updateOrganizationParameterTypes17 = new Class[] {
			long.class, long.class, java.lang.String.class,
			java.lang.String.class, boolean.class, long.class, long.class,
			int.class, java.lang.String.class, boolean.class,
			com.liferay.portal.service.ServiceContext.class
		};
}