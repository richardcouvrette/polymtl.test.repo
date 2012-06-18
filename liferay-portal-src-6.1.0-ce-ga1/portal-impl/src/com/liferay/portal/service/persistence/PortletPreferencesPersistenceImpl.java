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

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.NoSuchPortletPreferencesException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.model.PortletPreferences;
import com.liferay.portal.model.impl.PortletPreferencesImpl;
import com.liferay.portal.model.impl.PortletPreferencesModelImpl;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the portlet preferences service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PortletPreferencesPersistence
 * @see PortletPreferencesUtil
 * @generated
 */
public class PortletPreferencesPersistenceImpl extends BasePersistenceImpl<PortletPreferences>
	implements PortletPreferencesPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link PortletPreferencesUtil} to access the portlet preferences persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = PortletPreferencesImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PLID = new FinderPath(PortletPreferencesModelImpl.ENTITY_CACHE_ENABLED,
			PortletPreferencesModelImpl.FINDER_CACHE_ENABLED,
			PortletPreferencesImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPlid",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PLID = new FinderPath(PortletPreferencesModelImpl.ENTITY_CACHE_ENABLED,
			PortletPreferencesModelImpl.FINDER_CACHE_ENABLED,
			PortletPreferencesImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPlid",
			new String[] { Long.class.getName() },
			PortletPreferencesModelImpl.PLID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PLID = new FinderPath(PortletPreferencesModelImpl.ENTITY_CACHE_ENABLED,
			PortletPreferencesModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPlid",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_P_P = new FinderPath(PortletPreferencesModelImpl.ENTITY_CACHE_ENABLED,
			PortletPreferencesModelImpl.FINDER_CACHE_ENABLED,
			PortletPreferencesImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByP_P",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_P = new FinderPath(PortletPreferencesModelImpl.ENTITY_CACHE_ENABLED,
			PortletPreferencesModelImpl.FINDER_CACHE_ENABLED,
			PortletPreferencesImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByP_P",
			new String[] { Long.class.getName(), String.class.getName() },
			PortletPreferencesModelImpl.PLID_COLUMN_BITMASK |
			PortletPreferencesModelImpl.PORTLETID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_P_P = new FinderPath(PortletPreferencesModelImpl.ENTITY_CACHE_ENABLED,
			PortletPreferencesModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByP_P",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_O_O_P = new FinderPath(PortletPreferencesModelImpl.ENTITY_CACHE_ENABLED,
			PortletPreferencesModelImpl.FINDER_CACHE_ENABLED,
			PortletPreferencesImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByO_O_P",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_O_O_P = new FinderPath(PortletPreferencesModelImpl.ENTITY_CACHE_ENABLED,
			PortletPreferencesModelImpl.FINDER_CACHE_ENABLED,
			PortletPreferencesImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByO_O_P",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Long.class.getName()
			},
			PortletPreferencesModelImpl.OWNERID_COLUMN_BITMASK |
			PortletPreferencesModelImpl.OWNERTYPE_COLUMN_BITMASK |
			PortletPreferencesModelImpl.PLID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_O_O_P = new FinderPath(PortletPreferencesModelImpl.ENTITY_CACHE_ENABLED,
			PortletPreferencesModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByO_O_P",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Long.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_O_P_P = new FinderPath(PortletPreferencesModelImpl.ENTITY_CACHE_ENABLED,
			PortletPreferencesModelImpl.FINDER_CACHE_ENABLED,
			PortletPreferencesImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByO_P_P",
			new String[] {
				Integer.class.getName(), Long.class.getName(),
				String.class.getName(),

			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_O_P_P = new FinderPath(PortletPreferencesModelImpl.ENTITY_CACHE_ENABLED,
			PortletPreferencesModelImpl.FINDER_CACHE_ENABLED,
			PortletPreferencesImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByO_P_P",
			new String[] {
				Integer.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			PortletPreferencesModelImpl.OWNERTYPE_COLUMN_BITMASK |
			PortletPreferencesModelImpl.PLID_COLUMN_BITMASK |
			PortletPreferencesModelImpl.PORTLETID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_O_P_P = new FinderPath(PortletPreferencesModelImpl.ENTITY_CACHE_ENABLED,
			PortletPreferencesModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByO_P_P",
			new String[] {
				Integer.class.getName(), Long.class.getName(),
				String.class.getName()
			});
	public static final FinderPath FINDER_PATH_FETCH_BY_O_O_P_P = new FinderPath(PortletPreferencesModelImpl.ENTITY_CACHE_ENABLED,
			PortletPreferencesModelImpl.FINDER_CACHE_ENABLED,
			PortletPreferencesImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByO_O_P_P",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Long.class.getName(), String.class.getName()
			},
			PortletPreferencesModelImpl.OWNERID_COLUMN_BITMASK |
			PortletPreferencesModelImpl.OWNERTYPE_COLUMN_BITMASK |
			PortletPreferencesModelImpl.PLID_COLUMN_BITMASK |
			PortletPreferencesModelImpl.PORTLETID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_O_O_P_P = new FinderPath(PortletPreferencesModelImpl.ENTITY_CACHE_ENABLED,
			PortletPreferencesModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByO_O_P_P",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Long.class.getName(), String.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PortletPreferencesModelImpl.ENTITY_CACHE_ENABLED,
			PortletPreferencesModelImpl.FINDER_CACHE_ENABLED,
			PortletPreferencesImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PortletPreferencesModelImpl.ENTITY_CACHE_ENABLED,
			PortletPreferencesModelImpl.FINDER_CACHE_ENABLED,
			PortletPreferencesImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PortletPreferencesModelImpl.ENTITY_CACHE_ENABLED,
			PortletPreferencesModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the portlet preferences in the entity cache if it is enabled.
	 *
	 * @param portletPreferences the portlet preferences
	 */
	public void cacheResult(PortletPreferences portletPreferences) {
		EntityCacheUtil.putResult(PortletPreferencesModelImpl.ENTITY_CACHE_ENABLED,
			PortletPreferencesImpl.class, portletPreferences.getPrimaryKey(),
			portletPreferences);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_O_O_P_P,
			new Object[] {
				Long.valueOf(portletPreferences.getOwnerId()),
				Integer.valueOf(portletPreferences.getOwnerType()),
				Long.valueOf(portletPreferences.getPlid()),
				
			portletPreferences.getPortletId()
			}, portletPreferences);

		portletPreferences.resetOriginalValues();
	}

	/**
	 * Caches the portlet preferenceses in the entity cache if it is enabled.
	 *
	 * @param portletPreferenceses the portlet preferenceses
	 */
	public void cacheResult(List<PortletPreferences> portletPreferenceses) {
		for (PortletPreferences portletPreferences : portletPreferenceses) {
			if (EntityCacheUtil.getResult(
						PortletPreferencesModelImpl.ENTITY_CACHE_ENABLED,
						PortletPreferencesImpl.class,
						portletPreferences.getPrimaryKey()) == null) {
				cacheResult(portletPreferences);
			}
			else {
				portletPreferences.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all portlet preferenceses.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(PortletPreferencesImpl.class.getName());
		}

		EntityCacheUtil.clearCache(PortletPreferencesImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the portlet preferences.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PortletPreferences portletPreferences) {
		EntityCacheUtil.removeResult(PortletPreferencesModelImpl.ENTITY_CACHE_ENABLED,
			PortletPreferencesImpl.class, portletPreferences.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(portletPreferences);
	}

	@Override
	public void clearCache(List<PortletPreferences> portletPreferenceses) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (PortletPreferences portletPreferences : portletPreferenceses) {
			EntityCacheUtil.removeResult(PortletPreferencesModelImpl.ENTITY_CACHE_ENABLED,
				PortletPreferencesImpl.class, portletPreferences.getPrimaryKey());

			clearUniqueFindersCache(portletPreferences);
		}
	}

	protected void clearUniqueFindersCache(
		PortletPreferences portletPreferences) {
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_O_O_P_P,
			new Object[] {
				Long.valueOf(portletPreferences.getOwnerId()),
				Integer.valueOf(portletPreferences.getOwnerType()),
				Long.valueOf(portletPreferences.getPlid()),
				
			portletPreferences.getPortletId()
			});
	}

	/**
	 * Creates a new portlet preferences with the primary key. Does not add the portlet preferences to the database.
	 *
	 * @param portletPreferencesId the primary key for the new portlet preferences
	 * @return the new portlet preferences
	 */
	public PortletPreferences create(long portletPreferencesId) {
		PortletPreferences portletPreferences = new PortletPreferencesImpl();

		portletPreferences.setNew(true);
		portletPreferences.setPrimaryKey(portletPreferencesId);

		return portletPreferences;
	}

	/**
	 * Removes the portlet preferences with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param portletPreferencesId the primary key of the portlet preferences
	 * @return the portlet preferences that was removed
	 * @throws com.liferay.portal.NoSuchPortletPreferencesException if a portlet preferences with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PortletPreferences remove(long portletPreferencesId)
		throws NoSuchPortletPreferencesException, SystemException {
		return remove(Long.valueOf(portletPreferencesId));
	}

	/**
	 * Removes the portlet preferences with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the portlet preferences
	 * @return the portlet preferences that was removed
	 * @throws com.liferay.portal.NoSuchPortletPreferencesException if a portlet preferences with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PortletPreferences remove(Serializable primaryKey)
		throws NoSuchPortletPreferencesException, SystemException {
		Session session = null;

		try {
			session = openSession();

			PortletPreferences portletPreferences = (PortletPreferences)session.get(PortletPreferencesImpl.class,
					primaryKey);

			if (portletPreferences == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPortletPreferencesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(portletPreferences);
		}
		catch (NoSuchPortletPreferencesException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected PortletPreferences removeImpl(
		PortletPreferences portletPreferences) throws SystemException {
		portletPreferences = toUnwrappedModel(portletPreferences);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, portletPreferences);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(portletPreferences);

		return portletPreferences;
	}

	@Override
	public PortletPreferences updateImpl(
		com.liferay.portal.model.PortletPreferences portletPreferences,
		boolean merge) throws SystemException {
		portletPreferences = toUnwrappedModel(portletPreferences);

		boolean isNew = portletPreferences.isNew();

		PortletPreferencesModelImpl portletPreferencesModelImpl = (PortletPreferencesModelImpl)portletPreferences;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, portletPreferences, merge);

			portletPreferences.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !PortletPreferencesModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((portletPreferencesModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PLID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(portletPreferencesModelImpl.getOriginalPlid())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PLID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PLID,
					args);

				args = new Object[] {
						Long.valueOf(portletPreferencesModelImpl.getPlid())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PLID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PLID,
					args);
			}

			if ((portletPreferencesModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_P.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(portletPreferencesModelImpl.getOriginalPlid()),
						
						portletPreferencesModelImpl.getOriginalPortletId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_P_P, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_P,
					args);

				args = new Object[] {
						Long.valueOf(portletPreferencesModelImpl.getPlid()),
						
						portletPreferencesModelImpl.getPortletId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_P_P, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_P,
					args);
			}

			if ((portletPreferencesModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_O_O_P.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(portletPreferencesModelImpl.getOriginalOwnerId()),
						Integer.valueOf(portletPreferencesModelImpl.getOriginalOwnerType()),
						Long.valueOf(portletPreferencesModelImpl.getOriginalPlid())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_O_O_P, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_O_O_P,
					args);

				args = new Object[] {
						Long.valueOf(portletPreferencesModelImpl.getOwnerId()),
						Integer.valueOf(portletPreferencesModelImpl.getOwnerType()),
						Long.valueOf(portletPreferencesModelImpl.getPlid())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_O_O_P, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_O_O_P,
					args);
			}

			if ((portletPreferencesModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_O_P_P.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Integer.valueOf(portletPreferencesModelImpl.getOriginalOwnerType()),
						Long.valueOf(portletPreferencesModelImpl.getOriginalPlid()),

						portletPreferencesModelImpl.getOriginalPortletId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_O_P_P, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_O_P_P,
					args);

				args = new Object[] {
						Integer.valueOf(portletPreferencesModelImpl.getOwnerType()),
						Long.valueOf(portletPreferencesModelImpl.getPlid()),

						portletPreferencesModelImpl.getPortletId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_O_P_P, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_O_P_P,
					args);
			}
		}

		EntityCacheUtil.putResult(PortletPreferencesModelImpl.ENTITY_CACHE_ENABLED,
			PortletPreferencesImpl.class, portletPreferences.getPrimaryKey(),
			portletPreferences);

		if (isNew) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_O_O_P_P,
				new Object[] {
					Long.valueOf(portletPreferences.getOwnerId()),
					Integer.valueOf(portletPreferences.getOwnerType()),
					Long.valueOf(portletPreferences.getPlid()),
					
				portletPreferences.getPortletId()
				}, portletPreferences);
		}
		else {
			if ((portletPreferencesModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_O_O_P_P.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(portletPreferencesModelImpl.getOriginalOwnerId()),
						Integer.valueOf(portletPreferencesModelImpl.getOriginalOwnerType()),
						Long.valueOf(portletPreferencesModelImpl.getOriginalPlid()),
						
						portletPreferencesModelImpl.getOriginalPortletId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_O_O_P_P, args);
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_O_O_P_P, args);

				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_O_O_P_P,
					new Object[] {
						Long.valueOf(portletPreferences.getOwnerId()),
						Integer.valueOf(portletPreferences.getOwnerType()),
						Long.valueOf(portletPreferences.getPlid()),
						
					portletPreferences.getPortletId()
					}, portletPreferences);
			}
		}

		return portletPreferences;
	}

	protected PortletPreferences toUnwrappedModel(
		PortletPreferences portletPreferences) {
		if (portletPreferences instanceof PortletPreferencesImpl) {
			return portletPreferences;
		}

		PortletPreferencesImpl portletPreferencesImpl = new PortletPreferencesImpl();

		portletPreferencesImpl.setNew(portletPreferences.isNew());
		portletPreferencesImpl.setPrimaryKey(portletPreferences.getPrimaryKey());

		portletPreferencesImpl.setPortletPreferencesId(portletPreferences.getPortletPreferencesId());
		portletPreferencesImpl.setOwnerId(portletPreferences.getOwnerId());
		portletPreferencesImpl.setOwnerType(portletPreferences.getOwnerType());
		portletPreferencesImpl.setPlid(portletPreferences.getPlid());
		portletPreferencesImpl.setPortletId(portletPreferences.getPortletId());
		portletPreferencesImpl.setPreferences(portletPreferences.getPreferences());

		return portletPreferencesImpl;
	}

	/**
	 * Returns the portlet preferences with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the portlet preferences
	 * @return the portlet preferences
	 * @throws com.liferay.portal.NoSuchModelException if a portlet preferences with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PortletPreferences findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the portlet preferences with the primary key or throws a {@link com.liferay.portal.NoSuchPortletPreferencesException} if it could not be found.
	 *
	 * @param portletPreferencesId the primary key of the portlet preferences
	 * @return the portlet preferences
	 * @throws com.liferay.portal.NoSuchPortletPreferencesException if a portlet preferences with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PortletPreferences findByPrimaryKey(long portletPreferencesId)
		throws NoSuchPortletPreferencesException, SystemException {
		PortletPreferences portletPreferences = fetchByPrimaryKey(portletPreferencesId);

		if (portletPreferences == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					portletPreferencesId);
			}

			throw new NoSuchPortletPreferencesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				portletPreferencesId);
		}

		return portletPreferences;
	}

	/**
	 * Returns the portlet preferences with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the portlet preferences
	 * @return the portlet preferences, or <code>null</code> if a portlet preferences with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PortletPreferences fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the portlet preferences with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param portletPreferencesId the primary key of the portlet preferences
	 * @return the portlet preferences, or <code>null</code> if a portlet preferences with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PortletPreferences fetchByPrimaryKey(long portletPreferencesId)
		throws SystemException {
		PortletPreferences portletPreferences = (PortletPreferences)EntityCacheUtil.getResult(PortletPreferencesModelImpl.ENTITY_CACHE_ENABLED,
				PortletPreferencesImpl.class, portletPreferencesId);

		if (portletPreferences == _nullPortletPreferences) {
			return null;
		}

		if (portletPreferences == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				portletPreferences = (PortletPreferences)session.get(PortletPreferencesImpl.class,
						Long.valueOf(portletPreferencesId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (portletPreferences != null) {
					cacheResult(portletPreferences);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(PortletPreferencesModelImpl.ENTITY_CACHE_ENABLED,
						PortletPreferencesImpl.class, portletPreferencesId,
						_nullPortletPreferences);
				}

				closeSession(session);
			}
		}

		return portletPreferences;
	}

	/**
	 * Returns all the portlet preferenceses where plid = &#63;.
	 *
	 * @param plid the plid
	 * @return the matching portlet preferenceses
	 * @throws SystemException if a system exception occurred
	 */
	public List<PortletPreferences> findByPlid(long plid)
		throws SystemException {
		return findByPlid(plid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the portlet preferenceses where plid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param plid the plid
	 * @param start the lower bound of the range of portlet preferenceses
	 * @param end the upper bound of the range of portlet preferenceses (not inclusive)
	 * @return the range of matching portlet preferenceses
	 * @throws SystemException if a system exception occurred
	 */
	public List<PortletPreferences> findByPlid(long plid, int start, int end)
		throws SystemException {
		return findByPlid(plid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the portlet preferenceses where plid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param plid the plid
	 * @param start the lower bound of the range of portlet preferenceses
	 * @param end the upper bound of the range of portlet preferenceses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching portlet preferenceses
	 * @throws SystemException if a system exception occurred
	 */
	public List<PortletPreferences> findByPlid(long plid, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PLID;
			finderArgs = new Object[] { plid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PLID;
			finderArgs = new Object[] { plid, start, end, orderByComparator };
		}

		List<PortletPreferences> list = (List<PortletPreferences>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_PORTLETPREFERENCES_WHERE);

			query.append(_FINDER_COLUMN_PLID_PLID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(plid);

				list = (List<PortletPreferences>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first portlet preferences in the ordered set where plid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching portlet preferences
	 * @throws com.liferay.portal.NoSuchPortletPreferencesException if a matching portlet preferences could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PortletPreferences findByPlid_First(long plid,
		OrderByComparator orderByComparator)
		throws NoSuchPortletPreferencesException, SystemException {
		List<PortletPreferences> list = findByPlid(plid, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("plid=");
			msg.append(plid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchPortletPreferencesException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last portlet preferences in the ordered set where plid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching portlet preferences
	 * @throws com.liferay.portal.NoSuchPortletPreferencesException if a matching portlet preferences could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PortletPreferences findByPlid_Last(long plid,
		OrderByComparator orderByComparator)
		throws NoSuchPortletPreferencesException, SystemException {
		int count = countByPlid(plid);

		List<PortletPreferences> list = findByPlid(plid, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("plid=");
			msg.append(plid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchPortletPreferencesException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the portlet preferenceses before and after the current portlet preferences in the ordered set where plid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param portletPreferencesId the primary key of the current portlet preferences
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next portlet preferences
	 * @throws com.liferay.portal.NoSuchPortletPreferencesException if a portlet preferences with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PortletPreferences[] findByPlid_PrevAndNext(
		long portletPreferencesId, long plid,
		OrderByComparator orderByComparator)
		throws NoSuchPortletPreferencesException, SystemException {
		PortletPreferences portletPreferences = findByPrimaryKey(portletPreferencesId);

		Session session = null;

		try {
			session = openSession();

			PortletPreferences[] array = new PortletPreferencesImpl[3];

			array[0] = getByPlid_PrevAndNext(session, portletPreferences, plid,
					orderByComparator, true);

			array[1] = portletPreferences;

			array[2] = getByPlid_PrevAndNext(session, portletPreferences, plid,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PortletPreferences getByPlid_PrevAndNext(Session session,
		PortletPreferences portletPreferences, long plid,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PORTLETPREFERENCES_WHERE);

		query.append(_FINDER_COLUMN_PLID_PLID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(plid);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(portletPreferences);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PortletPreferences> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the portlet preferenceses where plid = &#63; and portletId = &#63;.
	 *
	 * @param plid the plid
	 * @param portletId the portlet ID
	 * @return the matching portlet preferenceses
	 * @throws SystemException if a system exception occurred
	 */
	public List<PortletPreferences> findByP_P(long plid, String portletId)
		throws SystemException {
		return findByP_P(plid, portletId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the portlet preferenceses where plid = &#63; and portletId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param plid the plid
	 * @param portletId the portlet ID
	 * @param start the lower bound of the range of portlet preferenceses
	 * @param end the upper bound of the range of portlet preferenceses (not inclusive)
	 * @return the range of matching portlet preferenceses
	 * @throws SystemException if a system exception occurred
	 */
	public List<PortletPreferences> findByP_P(long plid, String portletId,
		int start, int end) throws SystemException {
		return findByP_P(plid, portletId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the portlet preferenceses where plid = &#63; and portletId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param plid the plid
	 * @param portletId the portlet ID
	 * @param start the lower bound of the range of portlet preferenceses
	 * @param end the upper bound of the range of portlet preferenceses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching portlet preferenceses
	 * @throws SystemException if a system exception occurred
	 */
	public List<PortletPreferences> findByP_P(long plid, String portletId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_P;
			finderArgs = new Object[] { plid, portletId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_P_P;
			finderArgs = new Object[] {
					plid, portletId,
					
					start, end, orderByComparator
				};
		}

		List<PortletPreferences> list = (List<PortletPreferences>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_PORTLETPREFERENCES_WHERE);

			query.append(_FINDER_COLUMN_P_P_PLID_2);

			if (portletId == null) {
				query.append(_FINDER_COLUMN_P_P_PORTLETID_1);
			}
			else {
				if (portletId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_P_P_PORTLETID_3);
				}
				else {
					query.append(_FINDER_COLUMN_P_P_PORTLETID_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(plid);

				if (portletId != null) {
					qPos.add(portletId);
				}

				list = (List<PortletPreferences>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first portlet preferences in the ordered set where plid = &#63; and portletId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param plid the plid
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching portlet preferences
	 * @throws com.liferay.portal.NoSuchPortletPreferencesException if a matching portlet preferences could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PortletPreferences findByP_P_First(long plid, String portletId,
		OrderByComparator orderByComparator)
		throws NoSuchPortletPreferencesException, SystemException {
		List<PortletPreferences> list = findByP_P(plid, portletId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("plid=");
			msg.append(plid);

			msg.append(", portletId=");
			msg.append(portletId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchPortletPreferencesException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last portlet preferences in the ordered set where plid = &#63; and portletId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param plid the plid
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching portlet preferences
	 * @throws com.liferay.portal.NoSuchPortletPreferencesException if a matching portlet preferences could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PortletPreferences findByP_P_Last(long plid, String portletId,
		OrderByComparator orderByComparator)
		throws NoSuchPortletPreferencesException, SystemException {
		int count = countByP_P(plid, portletId);

		List<PortletPreferences> list = findByP_P(plid, portletId, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("plid=");
			msg.append(plid);

			msg.append(", portletId=");
			msg.append(portletId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchPortletPreferencesException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the portlet preferenceses before and after the current portlet preferences in the ordered set where plid = &#63; and portletId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param portletPreferencesId the primary key of the current portlet preferences
	 * @param plid the plid
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next portlet preferences
	 * @throws com.liferay.portal.NoSuchPortletPreferencesException if a portlet preferences with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PortletPreferences[] findByP_P_PrevAndNext(
		long portletPreferencesId, long plid, String portletId,
		OrderByComparator orderByComparator)
		throws NoSuchPortletPreferencesException, SystemException {
		PortletPreferences portletPreferences = findByPrimaryKey(portletPreferencesId);

		Session session = null;

		try {
			session = openSession();

			PortletPreferences[] array = new PortletPreferencesImpl[3];

			array[0] = getByP_P_PrevAndNext(session, portletPreferences, plid,
					portletId, orderByComparator, true);

			array[1] = portletPreferences;

			array[2] = getByP_P_PrevAndNext(session, portletPreferences, plid,
					portletId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PortletPreferences getByP_P_PrevAndNext(Session session,
		PortletPreferences portletPreferences, long plid, String portletId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PORTLETPREFERENCES_WHERE);

		query.append(_FINDER_COLUMN_P_P_PLID_2);

		if (portletId == null) {
			query.append(_FINDER_COLUMN_P_P_PORTLETID_1);
		}
		else {
			if (portletId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_P_P_PORTLETID_3);
			}
			else {
				query.append(_FINDER_COLUMN_P_P_PORTLETID_2);
			}
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(plid);

		if (portletId != null) {
			qPos.add(portletId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(portletPreferences);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PortletPreferences> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the portlet preferenceses where ownerId = &#63; and ownerType = &#63; and plid = &#63;.
	 *
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param plid the plid
	 * @return the matching portlet preferenceses
	 * @throws SystemException if a system exception occurred
	 */
	public List<PortletPreferences> findByO_O_P(long ownerId, int ownerType,
		long plid) throws SystemException {
		return findByO_O_P(ownerId, ownerType, plid, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the portlet preferenceses where ownerId = &#63; and ownerType = &#63; and plid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param plid the plid
	 * @param start the lower bound of the range of portlet preferenceses
	 * @param end the upper bound of the range of portlet preferenceses (not inclusive)
	 * @return the range of matching portlet preferenceses
	 * @throws SystemException if a system exception occurred
	 */
	public List<PortletPreferences> findByO_O_P(long ownerId, int ownerType,
		long plid, int start, int end) throws SystemException {
		return findByO_O_P(ownerId, ownerType, plid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the portlet preferenceses where ownerId = &#63; and ownerType = &#63; and plid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param plid the plid
	 * @param start the lower bound of the range of portlet preferenceses
	 * @param end the upper bound of the range of portlet preferenceses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching portlet preferenceses
	 * @throws SystemException if a system exception occurred
	 */
	public List<PortletPreferences> findByO_O_P(long ownerId, int ownerType,
		long plid, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_O_O_P;
			finderArgs = new Object[] { ownerId, ownerType, plid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_O_O_P;
			finderArgs = new Object[] {
					ownerId, ownerType, plid,
					
					start, end, orderByComparator
				};
		}

		List<PortletPreferences> list = (List<PortletPreferences>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_PORTLETPREFERENCES_WHERE);

			query.append(_FINDER_COLUMN_O_O_P_OWNERID_2);

			query.append(_FINDER_COLUMN_O_O_P_OWNERTYPE_2);

			query.append(_FINDER_COLUMN_O_O_P_PLID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ownerId);

				qPos.add(ownerType);

				qPos.add(plid);

				list = (List<PortletPreferences>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first portlet preferences in the ordered set where ownerId = &#63; and ownerType = &#63; and plid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching portlet preferences
	 * @throws com.liferay.portal.NoSuchPortletPreferencesException if a matching portlet preferences could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PortletPreferences findByO_O_P_First(long ownerId, int ownerType,
		long plid, OrderByComparator orderByComparator)
		throws NoSuchPortletPreferencesException, SystemException {
		List<PortletPreferences> list = findByO_O_P(ownerId, ownerType, plid,
				0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("ownerId=");
			msg.append(ownerId);

			msg.append(", ownerType=");
			msg.append(ownerType);

			msg.append(", plid=");
			msg.append(plid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchPortletPreferencesException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last portlet preferences in the ordered set where ownerId = &#63; and ownerType = &#63; and plid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching portlet preferences
	 * @throws com.liferay.portal.NoSuchPortletPreferencesException if a matching portlet preferences could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PortletPreferences findByO_O_P_Last(long ownerId, int ownerType,
		long plid, OrderByComparator orderByComparator)
		throws NoSuchPortletPreferencesException, SystemException {
		int count = countByO_O_P(ownerId, ownerType, plid);

		List<PortletPreferences> list = findByO_O_P(ownerId, ownerType, plid,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("ownerId=");
			msg.append(ownerId);

			msg.append(", ownerType=");
			msg.append(ownerType);

			msg.append(", plid=");
			msg.append(plid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchPortletPreferencesException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the portlet preferenceses before and after the current portlet preferences in the ordered set where ownerId = &#63; and ownerType = &#63; and plid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param portletPreferencesId the primary key of the current portlet preferences
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param plid the plid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next portlet preferences
	 * @throws com.liferay.portal.NoSuchPortletPreferencesException if a portlet preferences with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PortletPreferences[] findByO_O_P_PrevAndNext(
		long portletPreferencesId, long ownerId, int ownerType, long plid,
		OrderByComparator orderByComparator)
		throws NoSuchPortletPreferencesException, SystemException {
		PortletPreferences portletPreferences = findByPrimaryKey(portletPreferencesId);

		Session session = null;

		try {
			session = openSession();

			PortletPreferences[] array = new PortletPreferencesImpl[3];

			array[0] = getByO_O_P_PrevAndNext(session, portletPreferences,
					ownerId, ownerType, plid, orderByComparator, true);

			array[1] = portletPreferences;

			array[2] = getByO_O_P_PrevAndNext(session, portletPreferences,
					ownerId, ownerType, plid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PortletPreferences getByO_O_P_PrevAndNext(Session session,
		PortletPreferences portletPreferences, long ownerId, int ownerType,
		long plid, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PORTLETPREFERENCES_WHERE);

		query.append(_FINDER_COLUMN_O_O_P_OWNERID_2);

		query.append(_FINDER_COLUMN_O_O_P_OWNERTYPE_2);

		query.append(_FINDER_COLUMN_O_O_P_PLID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(ownerId);

		qPos.add(ownerType);

		qPos.add(plid);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(portletPreferences);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PortletPreferences> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the portlet preferenceses where ownerType = &#63; and plid = &#63; and portletId = &#63;.
	 *
	 * @param ownerType the owner type
	 * @param plid the plid
	 * @param portletId the portlet ID
	 * @return the matching portlet preferenceses
	 * @throws SystemException if a system exception occurred
	 */
	public List<PortletPreferences> findByO_P_P(int ownerType, long plid,
		String portletId) throws SystemException {
		return findByO_P_P(ownerType, plid, portletId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the portlet preferenceses where ownerType = &#63; and plid = &#63; and portletId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ownerType the owner type
	 * @param plid the plid
	 * @param portletId the portlet ID
	 * @param start the lower bound of the range of portlet preferenceses
	 * @param end the upper bound of the range of portlet preferenceses (not inclusive)
	 * @return the range of matching portlet preferenceses
	 * @throws SystemException if a system exception occurred
	 */
	public List<PortletPreferences> findByO_P_P(int ownerType, long plid,
		String portletId, int start, int end) throws SystemException {
		return findByO_P_P(ownerType, plid, portletId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the portlet preferenceses where ownerType = &#63; and plid = &#63; and portletId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ownerType the owner type
	 * @param plid the plid
	 * @param portletId the portlet ID
	 * @param start the lower bound of the range of portlet preferenceses
	 * @param end the upper bound of the range of portlet preferenceses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching portlet preferenceses
	 * @throws SystemException if a system exception occurred
	 */
	public List<PortletPreferences> findByO_P_P(int ownerType, long plid,
		String portletId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_O_P_P;
			finderArgs = new Object[] { ownerType, plid, portletId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_O_P_P;
			finderArgs = new Object[] {
					ownerType, plid, portletId,

					start, end, orderByComparator
				};
		}

		List<PortletPreferences> list = (List<PortletPreferences>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_PORTLETPREFERENCES_WHERE);

			query.append(_FINDER_COLUMN_O_P_P_OWNERTYPE_2);

			query.append(_FINDER_COLUMN_O_P_P_PLID_2);

			if (portletId == null) {
				query.append(_FINDER_COLUMN_O_P_P_PORTLETID_1);
			}
			else {
				if (portletId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_O_P_P_PORTLETID_3);
				}
				else {
					query.append(_FINDER_COLUMN_O_P_P_PORTLETID_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ownerType);

				qPos.add(plid);

				if (portletId != null) {
					qPos.add(portletId);
				}

				list = (List<PortletPreferences>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first portlet preferences in the ordered set where ownerType = &#63; and plid = &#63; and portletId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ownerType the owner type
	 * @param plid the plid
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching portlet preferences
	 * @throws com.liferay.portal.NoSuchPortletPreferencesException if a matching portlet preferences could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PortletPreferences findByO_P_P_First(int ownerType, long plid,
		String portletId, OrderByComparator orderByComparator)
		throws NoSuchPortletPreferencesException, SystemException {
		List<PortletPreferences> list = findByO_P_P(ownerType, plid, portletId,
				0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("ownerType=");
			msg.append(ownerType);

			msg.append(", plid=");
			msg.append(plid);

			msg.append(", portletId=");
			msg.append(portletId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchPortletPreferencesException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last portlet preferences in the ordered set where ownerType = &#63; and plid = &#63; and portletId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ownerType the owner type
	 * @param plid the plid
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching portlet preferences
	 * @throws com.liferay.portal.NoSuchPortletPreferencesException if a matching portlet preferences could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PortletPreferences findByO_P_P_Last(int ownerType, long plid,
		String portletId, OrderByComparator orderByComparator)
		throws NoSuchPortletPreferencesException, SystemException {
		int count = countByO_P_P(ownerType, plid, portletId);

		List<PortletPreferences> list = findByO_P_P(ownerType, plid, portletId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("ownerType=");
			msg.append(ownerType);

			msg.append(", plid=");
			msg.append(plid);

			msg.append(", portletId=");
			msg.append(portletId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchPortletPreferencesException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the portlet preferenceses before and after the current portlet preferences in the ordered set where ownerType = &#63; and plid = &#63; and portletId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param portletPreferencesId the primary key of the current portlet preferences
	 * @param ownerType the owner type
	 * @param plid the plid
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next portlet preferences
	 * @throws com.liferay.portal.NoSuchPortletPreferencesException if a portlet preferences with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PortletPreferences[] findByO_P_P_PrevAndNext(
		long portletPreferencesId, int ownerType, long plid, String portletId,
		OrderByComparator orderByComparator)
		throws NoSuchPortletPreferencesException, SystemException {
		PortletPreferences portletPreferences = findByPrimaryKey(portletPreferencesId);

		Session session = null;

		try {
			session = openSession();

			PortletPreferences[] array = new PortletPreferencesImpl[3];

			array[0] = getByO_P_P_PrevAndNext(session, portletPreferences,
					ownerType, plid, portletId, orderByComparator, true);

			array[1] = portletPreferences;

			array[2] = getByO_P_P_PrevAndNext(session, portletPreferences,
					ownerType, plid, portletId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PortletPreferences getByO_P_P_PrevAndNext(Session session,
		PortletPreferences portletPreferences, int ownerType, long plid,
		String portletId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PORTLETPREFERENCES_WHERE);

		query.append(_FINDER_COLUMN_O_P_P_OWNERTYPE_2);

		query.append(_FINDER_COLUMN_O_P_P_PLID_2);

		if (portletId == null) {
			query.append(_FINDER_COLUMN_O_P_P_PORTLETID_1);
		}
		else {
			if (portletId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_O_P_P_PORTLETID_3);
			}
			else {
				query.append(_FINDER_COLUMN_O_P_P_PORTLETID_2);
			}
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(ownerType);

		qPos.add(plid);

		if (portletId != null) {
			qPos.add(portletId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(portletPreferences);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PortletPreferences> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the portlet preferences where ownerId = &#63; and ownerType = &#63; and plid = &#63; and portletId = &#63; or throws a {@link com.liferay.portal.NoSuchPortletPreferencesException} if it could not be found.
	 *
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param plid the plid
	 * @param portletId the portlet ID
	 * @return the matching portlet preferences
	 * @throws com.liferay.portal.NoSuchPortletPreferencesException if a matching portlet preferences could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PortletPreferences findByO_O_P_P(long ownerId, int ownerType,
		long plid, String portletId)
		throws NoSuchPortletPreferencesException, SystemException {
		PortletPreferences portletPreferences = fetchByO_O_P_P(ownerId,
				ownerType, plid, portletId);

		if (portletPreferences == null) {
			StringBundler msg = new StringBundler(10);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("ownerId=");
			msg.append(ownerId);

			msg.append(", ownerType=");
			msg.append(ownerType);

			msg.append(", plid=");
			msg.append(plid);

			msg.append(", portletId=");
			msg.append(portletId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchPortletPreferencesException(msg.toString());
		}

		return portletPreferences;
	}

	/**
	 * Returns the portlet preferences where ownerId = &#63; and ownerType = &#63; and plid = &#63; and portletId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param plid the plid
	 * @param portletId the portlet ID
	 * @return the matching portlet preferences, or <code>null</code> if a matching portlet preferences could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PortletPreferences fetchByO_O_P_P(long ownerId, int ownerType,
		long plid, String portletId) throws SystemException {
		return fetchByO_O_P_P(ownerId, ownerType, plid, portletId, true);
	}

	/**
	 * Returns the portlet preferences where ownerId = &#63; and ownerType = &#63; and plid = &#63; and portletId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param plid the plid
	 * @param portletId the portlet ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching portlet preferences, or <code>null</code> if a matching portlet preferences could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PortletPreferences fetchByO_O_P_P(long ownerId, int ownerType,
		long plid, String portletId, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { ownerId, ownerType, plid, portletId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_O_O_P_P,
					finderArgs, this);
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_PORTLETPREFERENCES_WHERE);

			query.append(_FINDER_COLUMN_O_O_P_P_OWNERID_2);

			query.append(_FINDER_COLUMN_O_O_P_P_OWNERTYPE_2);

			query.append(_FINDER_COLUMN_O_O_P_P_PLID_2);

			if (portletId == null) {
				query.append(_FINDER_COLUMN_O_O_P_P_PORTLETID_1);
			}
			else {
				if (portletId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_O_O_P_P_PORTLETID_3);
				}
				else {
					query.append(_FINDER_COLUMN_O_O_P_P_PORTLETID_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ownerId);

				qPos.add(ownerType);

				qPos.add(plid);

				if (portletId != null) {
					qPos.add(portletId);
				}

				List<PortletPreferences> list = q.list();

				result = list;

				PortletPreferences portletPreferences = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_O_O_P_P,
						finderArgs, list);
				}
				else {
					portletPreferences = list.get(0);

					cacheResult(portletPreferences);

					if ((portletPreferences.getOwnerId() != ownerId) ||
							(portletPreferences.getOwnerType() != ownerType) ||
							(portletPreferences.getPlid() != plid) ||
							(portletPreferences.getPortletId() == null) ||
							!portletPreferences.getPortletId().equals(portletId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_O_O_P_P,
							finderArgs, portletPreferences);
					}
				}

				return portletPreferences;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_O_O_P_P,
						finderArgs);
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (PortletPreferences)result;
			}
		}
	}

	/**
	 * Returns all the portlet preferenceses.
	 *
	 * @return the portlet preferenceses
	 * @throws SystemException if a system exception occurred
	 */
	public List<PortletPreferences> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the portlet preferenceses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of portlet preferenceses
	 * @param end the upper bound of the range of portlet preferenceses (not inclusive)
	 * @return the range of portlet preferenceses
	 * @throws SystemException if a system exception occurred
	 */
	public List<PortletPreferences> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the portlet preferenceses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of portlet preferenceses
	 * @param end the upper bound of the range of portlet preferenceses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of portlet preferenceses
	 * @throws SystemException if a system exception occurred
	 */
	public List<PortletPreferences> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = new Object[] { start, end, orderByComparator };

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<PortletPreferences> list = (List<PortletPreferences>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_PORTLETPREFERENCES);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PORTLETPREFERENCES;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<PortletPreferences>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<PortletPreferences>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the portlet preferenceses where plid = &#63; from the database.
	 *
	 * @param plid the plid
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByPlid(long plid) throws SystemException {
		for (PortletPreferences portletPreferences : findByPlid(plid)) {
			remove(portletPreferences);
		}
	}

	/**
	 * Removes all the portlet preferenceses where plid = &#63; and portletId = &#63; from the database.
	 *
	 * @param plid the plid
	 * @param portletId the portlet ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByP_P(long plid, String portletId)
		throws SystemException {
		for (PortletPreferences portletPreferences : findByP_P(plid, portletId)) {
			remove(portletPreferences);
		}
	}

	/**
	 * Removes all the portlet preferenceses where ownerId = &#63; and ownerType = &#63; and plid = &#63; from the database.
	 *
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param plid the plid
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByO_O_P(long ownerId, int ownerType, long plid)
		throws SystemException {
		for (PortletPreferences portletPreferences : findByO_O_P(ownerId,
				ownerType, plid)) {
			remove(portletPreferences);
		}
	}

	/**
	 * Removes all the portlet preferenceses where ownerType = &#63; and plid = &#63; and portletId = &#63; from the database.
	 *
	 * @param ownerType the owner type
	 * @param plid the plid
	 * @param portletId the portlet ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByO_P_P(int ownerType, long plid, String portletId)
		throws SystemException {
		for (PortletPreferences portletPreferences : findByO_P_P(ownerType,
				plid, portletId)) {
			remove(portletPreferences);
		}
	}

	/**
	 * Removes the portlet preferences where ownerId = &#63; and ownerType = &#63; and plid = &#63; and portletId = &#63; from the database.
	 *
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param plid the plid
	 * @param portletId the portlet ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByO_O_P_P(long ownerId, int ownerType, long plid,
		String portletId)
		throws NoSuchPortletPreferencesException, SystemException {
		PortletPreferences portletPreferences = findByO_O_P_P(ownerId,
				ownerType, plid, portletId);

		remove(portletPreferences);
	}

	/**
	 * Removes all the portlet preferenceses from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (PortletPreferences portletPreferences : findAll()) {
			remove(portletPreferences);
		}
	}

	/**
	 * Returns the number of portlet preferenceses where plid = &#63;.
	 *
	 * @param plid the plid
	 * @return the number of matching portlet preferenceses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPlid(long plid) throws SystemException {
		Object[] finderArgs = new Object[] { plid };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PLID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PORTLETPREFERENCES_WHERE);

			query.append(_FINDER_COLUMN_PLID_PLID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(plid);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PLID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of portlet preferenceses where plid = &#63; and portletId = &#63;.
	 *
	 * @param plid the plid
	 * @param portletId the portlet ID
	 * @return the number of matching portlet preferenceses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByP_P(long plid, String portletId)
		throws SystemException {
		Object[] finderArgs = new Object[] { plid, portletId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_P_P,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PORTLETPREFERENCES_WHERE);

			query.append(_FINDER_COLUMN_P_P_PLID_2);

			if (portletId == null) {
				query.append(_FINDER_COLUMN_P_P_PORTLETID_1);
			}
			else {
				if (portletId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_P_P_PORTLETID_3);
				}
				else {
					query.append(_FINDER_COLUMN_P_P_PORTLETID_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(plid);

				if (portletId != null) {
					qPos.add(portletId);
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_P_P, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of portlet preferenceses where ownerId = &#63; and ownerType = &#63; and plid = &#63;.
	 *
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param plid the plid
	 * @return the number of matching portlet preferenceses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByO_O_P(long ownerId, int ownerType, long plid)
		throws SystemException {
		Object[] finderArgs = new Object[] { ownerId, ownerType, plid };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_O_O_P,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_PORTLETPREFERENCES_WHERE);

			query.append(_FINDER_COLUMN_O_O_P_OWNERID_2);

			query.append(_FINDER_COLUMN_O_O_P_OWNERTYPE_2);

			query.append(_FINDER_COLUMN_O_O_P_PLID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ownerId);

				qPos.add(ownerType);

				qPos.add(plid);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_O_O_P,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of portlet preferenceses where ownerType = &#63; and plid = &#63; and portletId = &#63;.
	 *
	 * @param ownerType the owner type
	 * @param plid the plid
	 * @param portletId the portlet ID
	 * @return the number of matching portlet preferenceses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByO_P_P(int ownerType, long plid, String portletId)
		throws SystemException {
		Object[] finderArgs = new Object[] { ownerType, plid, portletId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_O_P_P,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_PORTLETPREFERENCES_WHERE);

			query.append(_FINDER_COLUMN_O_P_P_OWNERTYPE_2);

			query.append(_FINDER_COLUMN_O_P_P_PLID_2);

			if (portletId == null) {
				query.append(_FINDER_COLUMN_O_P_P_PORTLETID_1);
			}
			else {
				if (portletId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_O_P_P_PORTLETID_3);
				}
				else {
					query.append(_FINDER_COLUMN_O_P_P_PORTLETID_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ownerType);

				qPos.add(plid);

				if (portletId != null) {
					qPos.add(portletId);
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_O_P_P,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of portlet preferenceses where ownerId = &#63; and ownerType = &#63; and plid = &#63; and portletId = &#63;.
	 *
	 * @param ownerId the owner ID
	 * @param ownerType the owner type
	 * @param plid the plid
	 * @param portletId the portlet ID
	 * @return the number of matching portlet preferenceses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByO_O_P_P(long ownerId, int ownerType, long plid,
		String portletId) throws SystemException {
		Object[] finderArgs = new Object[] { ownerId, ownerType, plid, portletId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_O_O_P_P,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_PORTLETPREFERENCES_WHERE);

			query.append(_FINDER_COLUMN_O_O_P_P_OWNERID_2);

			query.append(_FINDER_COLUMN_O_O_P_P_OWNERTYPE_2);

			query.append(_FINDER_COLUMN_O_O_P_P_PLID_2);

			if (portletId == null) {
				query.append(_FINDER_COLUMN_O_O_P_P_PORTLETID_1);
			}
			else {
				if (portletId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_O_O_P_P_PORTLETID_3);
				}
				else {
					query.append(_FINDER_COLUMN_O_O_P_P_PORTLETID_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ownerId);

				qPos.add(ownerType);

				qPos.add(plid);

				if (portletId != null) {
					qPos.add(portletId);
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_O_O_P_P,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of portlet preferenceses.
	 *
	 * @return the number of portlet preferenceses
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PORTLETPREFERENCES);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the portlet preferences persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.portal.util.PropsUtil.get(
						"value.object.listener.com.liferay.portal.model.PortletPreferences")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<PortletPreferences>> listenersList = new ArrayList<ModelListener<PortletPreferences>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<PortletPreferences>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(PortletPreferencesImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = AccountPersistence.class)
	protected AccountPersistence accountPersistence;
	@BeanReference(type = AddressPersistence.class)
	protected AddressPersistence addressPersistence;
	@BeanReference(type = BrowserTrackerPersistence.class)
	protected BrowserTrackerPersistence browserTrackerPersistence;
	@BeanReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@BeanReference(type = ClusterGroupPersistence.class)
	protected ClusterGroupPersistence clusterGroupPersistence;
	@BeanReference(type = CompanyPersistence.class)
	protected CompanyPersistence companyPersistence;
	@BeanReference(type = ContactPersistence.class)
	protected ContactPersistence contactPersistence;
	@BeanReference(type = CountryPersistence.class)
	protected CountryPersistence countryPersistence;
	@BeanReference(type = EmailAddressPersistence.class)
	protected EmailAddressPersistence emailAddressPersistence;
	@BeanReference(type = GroupPersistence.class)
	protected GroupPersistence groupPersistence;
	@BeanReference(type = ImagePersistence.class)
	protected ImagePersistence imagePersistence;
	@BeanReference(type = LayoutPersistence.class)
	protected LayoutPersistence layoutPersistence;
	@BeanReference(type = LayoutBranchPersistence.class)
	protected LayoutBranchPersistence layoutBranchPersistence;
	@BeanReference(type = LayoutPrototypePersistence.class)
	protected LayoutPrototypePersistence layoutPrototypePersistence;
	@BeanReference(type = LayoutRevisionPersistence.class)
	protected LayoutRevisionPersistence layoutRevisionPersistence;
	@BeanReference(type = LayoutSetPersistence.class)
	protected LayoutSetPersistence layoutSetPersistence;
	@BeanReference(type = LayoutSetBranchPersistence.class)
	protected LayoutSetBranchPersistence layoutSetBranchPersistence;
	@BeanReference(type = LayoutSetPrototypePersistence.class)
	protected LayoutSetPrototypePersistence layoutSetPrototypePersistence;
	@BeanReference(type = ListTypePersistence.class)
	protected ListTypePersistence listTypePersistence;
	@BeanReference(type = LockPersistence.class)
	protected LockPersistence lockPersistence;
	@BeanReference(type = MembershipRequestPersistence.class)
	protected MembershipRequestPersistence membershipRequestPersistence;
	@BeanReference(type = OrganizationPersistence.class)
	protected OrganizationPersistence organizationPersistence;
	@BeanReference(type = OrgGroupPermissionPersistence.class)
	protected OrgGroupPermissionPersistence orgGroupPermissionPersistence;
	@BeanReference(type = OrgGroupRolePersistence.class)
	protected OrgGroupRolePersistence orgGroupRolePersistence;
	@BeanReference(type = OrgLaborPersistence.class)
	protected OrgLaborPersistence orgLaborPersistence;
	@BeanReference(type = PasswordPolicyPersistence.class)
	protected PasswordPolicyPersistence passwordPolicyPersistence;
	@BeanReference(type = PasswordPolicyRelPersistence.class)
	protected PasswordPolicyRelPersistence passwordPolicyRelPersistence;
	@BeanReference(type = PasswordTrackerPersistence.class)
	protected PasswordTrackerPersistence passwordTrackerPersistence;
	@BeanReference(type = PermissionPersistence.class)
	protected PermissionPersistence permissionPersistence;
	@BeanReference(type = PhonePersistence.class)
	protected PhonePersistence phonePersistence;
	@BeanReference(type = PluginSettingPersistence.class)
	protected PluginSettingPersistence pluginSettingPersistence;
	@BeanReference(type = PortalPreferencesPersistence.class)
	protected PortalPreferencesPersistence portalPreferencesPersistence;
	@BeanReference(type = PortletPersistence.class)
	protected PortletPersistence portletPersistence;
	@BeanReference(type = PortletItemPersistence.class)
	protected PortletItemPersistence portletItemPersistence;
	@BeanReference(type = PortletPreferencesPersistence.class)
	protected PortletPreferencesPersistence portletPreferencesPersistence;
	@BeanReference(type = RegionPersistence.class)
	protected RegionPersistence regionPersistence;
	@BeanReference(type = ReleasePersistence.class)
	protected ReleasePersistence releasePersistence;
	@BeanReference(type = RepositoryPersistence.class)
	protected RepositoryPersistence repositoryPersistence;
	@BeanReference(type = RepositoryEntryPersistence.class)
	protected RepositoryEntryPersistence repositoryEntryPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = ResourceActionPersistence.class)
	protected ResourceActionPersistence resourceActionPersistence;
	@BeanReference(type = ResourceBlockPersistence.class)
	protected ResourceBlockPersistence resourceBlockPersistence;
	@BeanReference(type = ResourceBlockPermissionPersistence.class)
	protected ResourceBlockPermissionPersistence resourceBlockPermissionPersistence;
	@BeanReference(type = ResourceCodePersistence.class)
	protected ResourceCodePersistence resourceCodePersistence;
	@BeanReference(type = ResourcePermissionPersistence.class)
	protected ResourcePermissionPersistence resourcePermissionPersistence;
	@BeanReference(type = ResourceTypePermissionPersistence.class)
	protected ResourceTypePermissionPersistence resourceTypePermissionPersistence;
	@BeanReference(type = RolePersistence.class)
	protected RolePersistence rolePersistence;
	@BeanReference(type = ServiceComponentPersistence.class)
	protected ServiceComponentPersistence serviceComponentPersistence;
	@BeanReference(type = ShardPersistence.class)
	protected ShardPersistence shardPersistence;
	@BeanReference(type = SubscriptionPersistence.class)
	protected SubscriptionPersistence subscriptionPersistence;
	@BeanReference(type = TeamPersistence.class)
	protected TeamPersistence teamPersistence;
	@BeanReference(type = TicketPersistence.class)
	protected TicketPersistence ticketPersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@BeanReference(type = UserGroupPersistence.class)
	protected UserGroupPersistence userGroupPersistence;
	@BeanReference(type = UserGroupGroupRolePersistence.class)
	protected UserGroupGroupRolePersistence userGroupGroupRolePersistence;
	@BeanReference(type = UserGroupRolePersistence.class)
	protected UserGroupRolePersistence userGroupRolePersistence;
	@BeanReference(type = UserIdMapperPersistence.class)
	protected UserIdMapperPersistence userIdMapperPersistence;
	@BeanReference(type = UserNotificationEventPersistence.class)
	protected UserNotificationEventPersistence userNotificationEventPersistence;
	@BeanReference(type = UserTrackerPersistence.class)
	protected UserTrackerPersistence userTrackerPersistence;
	@BeanReference(type = UserTrackerPathPersistence.class)
	protected UserTrackerPathPersistence userTrackerPathPersistence;
	@BeanReference(type = VirtualHostPersistence.class)
	protected VirtualHostPersistence virtualHostPersistence;
	@BeanReference(type = WebDAVPropsPersistence.class)
	protected WebDAVPropsPersistence webDAVPropsPersistence;
	@BeanReference(type = WebsitePersistence.class)
	protected WebsitePersistence websitePersistence;
	@BeanReference(type = WorkflowDefinitionLinkPersistence.class)
	protected WorkflowDefinitionLinkPersistence workflowDefinitionLinkPersistence;
	@BeanReference(type = WorkflowInstanceLinkPersistence.class)
	protected WorkflowInstanceLinkPersistence workflowInstanceLinkPersistence;
	private static final String _SQL_SELECT_PORTLETPREFERENCES = "SELECT portletPreferences FROM PortletPreferences portletPreferences";
	private static final String _SQL_SELECT_PORTLETPREFERENCES_WHERE = "SELECT portletPreferences FROM PortletPreferences portletPreferences WHERE ";
	private static final String _SQL_COUNT_PORTLETPREFERENCES = "SELECT COUNT(portletPreferences) FROM PortletPreferences portletPreferences";
	private static final String _SQL_COUNT_PORTLETPREFERENCES_WHERE = "SELECT COUNT(portletPreferences) FROM PortletPreferences portletPreferences WHERE ";
	private static final String _FINDER_COLUMN_PLID_PLID_2 = "portletPreferences.plid = ?";
	private static final String _FINDER_COLUMN_P_P_PLID_2 = "portletPreferences.plid = ? AND ";
	private static final String _FINDER_COLUMN_P_P_PORTLETID_1 = "portletPreferences.portletId IS NULL";
	private static final String _FINDER_COLUMN_P_P_PORTLETID_2 = "portletPreferences.portletId = ?";
	private static final String _FINDER_COLUMN_P_P_PORTLETID_3 = "(portletPreferences.portletId IS NULL OR portletPreferences.portletId = ?)";
	private static final String _FINDER_COLUMN_O_O_P_OWNERID_2 = "portletPreferences.ownerId = ? AND ";
	private static final String _FINDER_COLUMN_O_O_P_OWNERTYPE_2 = "portletPreferences.ownerType = ? AND ";
	private static final String _FINDER_COLUMN_O_O_P_PLID_2 = "portletPreferences.plid = ?";
	private static final String _FINDER_COLUMN_O_P_P_OWNERTYPE_2 = "portletPreferences.ownerType = ? AND ";
	private static final String _FINDER_COLUMN_O_P_P_PLID_2 = "portletPreferences.plid = ? AND ";
	private static final String _FINDER_COLUMN_O_P_P_PORTLETID_1 = "portletPreferences.portletId IS NULL";
	private static final String _FINDER_COLUMN_O_P_P_PORTLETID_2 = "portletPreferences.portletId = ?";
	private static final String _FINDER_COLUMN_O_P_P_PORTLETID_3 = "(portletPreferences.portletId IS NULL OR portletPreferences.portletId = ?)";
	private static final String _FINDER_COLUMN_O_O_P_P_OWNERID_2 = "portletPreferences.ownerId = ? AND ";
	private static final String _FINDER_COLUMN_O_O_P_P_OWNERTYPE_2 = "portletPreferences.ownerType = ? AND ";
	private static final String _FINDER_COLUMN_O_O_P_P_PLID_2 = "portletPreferences.plid = ? AND ";
	private static final String _FINDER_COLUMN_O_O_P_P_PORTLETID_1 = "portletPreferences.portletId IS NULL";
	private static final String _FINDER_COLUMN_O_O_P_P_PORTLETID_2 = "portletPreferences.portletId = ?";
	private static final String _FINDER_COLUMN_O_O_P_P_PORTLETID_3 = "(portletPreferences.portletId IS NULL OR portletPreferences.portletId = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "portletPreferences.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PortletPreferences exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PortletPreferences exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = com.liferay.portal.util.PropsValues.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE;
	private static Log _log = LogFactoryUtil.getLog(PortletPreferencesPersistenceImpl.class);
	private static PortletPreferences _nullPortletPreferences = new PortletPreferencesImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<PortletPreferences> toCacheModel() {
				return _nullPortletPreferencesCacheModel;
			}
		};

	private static CacheModel<PortletPreferences> _nullPortletPreferencesCacheModel =
		new CacheModel<PortletPreferences>() {
			public PortletPreferences toEntityModel() {
				return _nullPortletPreferences;
			}
		};
}