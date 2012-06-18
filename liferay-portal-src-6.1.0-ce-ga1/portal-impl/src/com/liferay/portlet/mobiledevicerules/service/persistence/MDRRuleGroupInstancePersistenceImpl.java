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

package com.liferay.portlet.mobiledevicerules.service.persistence;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.LayoutPersistence;
import com.liferay.portal.service.persistence.LayoutSetPersistence;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.portlet.mobiledevicerules.NoSuchRuleGroupInstanceException;
import com.liferay.portlet.mobiledevicerules.model.MDRRuleGroupInstance;
import com.liferay.portlet.mobiledevicerules.model.impl.MDRRuleGroupInstanceImpl;
import com.liferay.portlet.mobiledevicerules.model.impl.MDRRuleGroupInstanceModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the m d r rule group instance service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Edward C. Han
 * @see MDRRuleGroupInstancePersistence
 * @see MDRRuleGroupInstanceUtil
 * @generated
 */
public class MDRRuleGroupInstancePersistenceImpl extends BasePersistenceImpl<MDRRuleGroupInstance>
	implements MDRRuleGroupInstancePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link MDRRuleGroupInstanceUtil} to access the m d r rule group instance persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = MDRRuleGroupInstanceImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(MDRRuleGroupInstanceModelImpl.ENTITY_CACHE_ENABLED,
			MDRRuleGroupInstanceModelImpl.FINDER_CACHE_ENABLED,
			MDRRuleGroupInstanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(MDRRuleGroupInstanceModelImpl.ENTITY_CACHE_ENABLED,
			MDRRuleGroupInstanceModelImpl.FINDER_CACHE_ENABLED,
			MDRRuleGroupInstanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			MDRRuleGroupInstanceModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(MDRRuleGroupInstanceModelImpl.ENTITY_CACHE_ENABLED,
			MDRRuleGroupInstanceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(MDRRuleGroupInstanceModelImpl.ENTITY_CACHE_ENABLED,
			MDRRuleGroupInstanceModelImpl.FINDER_CACHE_ENABLED,
			MDRRuleGroupInstanceImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			MDRRuleGroupInstanceModelImpl.UUID_COLUMN_BITMASK |
			MDRRuleGroupInstanceModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(MDRRuleGroupInstanceModelImpl.ENTITY_CACHE_ENABLED,
			MDRRuleGroupInstanceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_RULEGROUPID =
		new FinderPath(MDRRuleGroupInstanceModelImpl.ENTITY_CACHE_ENABLED,
			MDRRuleGroupInstanceModelImpl.FINDER_CACHE_ENABLED,
			MDRRuleGroupInstanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByRuleGroupId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RULEGROUPID =
		new FinderPath(MDRRuleGroupInstanceModelImpl.ENTITY_CACHE_ENABLED,
			MDRRuleGroupInstanceModelImpl.FINDER_CACHE_ENABLED,
			MDRRuleGroupInstanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByRuleGroupId",
			new String[] { Long.class.getName() },
			MDRRuleGroupInstanceModelImpl.RULEGROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_RULEGROUPID = new FinderPath(MDRRuleGroupInstanceModelImpl.ENTITY_CACHE_ENABLED,
			MDRRuleGroupInstanceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByRuleGroupId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C = new FinderPath(MDRRuleGroupInstanceModelImpl.ENTITY_CACHE_ENABLED,
			MDRRuleGroupInstanceModelImpl.FINDER_CACHE_ENABLED,
			MDRRuleGroupInstanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_C",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C = new FinderPath(MDRRuleGroupInstanceModelImpl.ENTITY_CACHE_ENABLED,
			MDRRuleGroupInstanceModelImpl.FINDER_CACHE_ENABLED,
			MDRRuleGroupInstanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C",
			new String[] { Long.class.getName(), Long.class.getName() },
			MDRRuleGroupInstanceModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			MDRRuleGroupInstanceModelImpl.CLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C = new FinderPath(MDRRuleGroupInstanceModelImpl.ENTITY_CACHE_ENABLED,
			MDRRuleGroupInstanceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_C_C = new FinderPath(MDRRuleGroupInstanceModelImpl.ENTITY_CACHE_ENABLED,
			MDRRuleGroupInstanceModelImpl.FINDER_CACHE_ENABLED,
			MDRRuleGroupInstanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_C_C",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_C_C = new FinderPath(MDRRuleGroupInstanceModelImpl.ENTITY_CACHE_ENABLED,
			MDRRuleGroupInstanceModelImpl.FINDER_CACHE_ENABLED,
			MDRRuleGroupInstanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_C_C",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			MDRRuleGroupInstanceModelImpl.GROUPID_COLUMN_BITMASK |
			MDRRuleGroupInstanceModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			MDRRuleGroupInstanceModelImpl.CLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_C_C = new FinderPath(MDRRuleGroupInstanceModelImpl.ENTITY_CACHE_ENABLED,
			MDRRuleGroupInstanceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_C_C",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});
	public static final FinderPath FINDER_PATH_FETCH_BY_C_C_R = new FinderPath(MDRRuleGroupInstanceModelImpl.ENTITY_CACHE_ENABLED,
			MDRRuleGroupInstanceModelImpl.FINDER_CACHE_ENABLED,
			MDRRuleGroupInstanceImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByC_C_R",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			MDRRuleGroupInstanceModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			MDRRuleGroupInstanceModelImpl.CLASSPK_COLUMN_BITMASK |
			MDRRuleGroupInstanceModelImpl.RULEGROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C_R = new FinderPath(MDRRuleGroupInstanceModelImpl.ENTITY_CACHE_ENABLED,
			MDRRuleGroupInstanceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C_R",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MDRRuleGroupInstanceModelImpl.ENTITY_CACHE_ENABLED,
			MDRRuleGroupInstanceModelImpl.FINDER_CACHE_ENABLED,
			MDRRuleGroupInstanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MDRRuleGroupInstanceModelImpl.ENTITY_CACHE_ENABLED,
			MDRRuleGroupInstanceModelImpl.FINDER_CACHE_ENABLED,
			MDRRuleGroupInstanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MDRRuleGroupInstanceModelImpl.ENTITY_CACHE_ENABLED,
			MDRRuleGroupInstanceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the m d r rule group instance in the entity cache if it is enabled.
	 *
	 * @param mdrRuleGroupInstance the m d r rule group instance
	 */
	public void cacheResult(MDRRuleGroupInstance mdrRuleGroupInstance) {
		EntityCacheUtil.putResult(MDRRuleGroupInstanceModelImpl.ENTITY_CACHE_ENABLED,
			MDRRuleGroupInstanceImpl.class,
			mdrRuleGroupInstance.getPrimaryKey(), mdrRuleGroupInstance);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				mdrRuleGroupInstance.getUuid(),
				Long.valueOf(mdrRuleGroupInstance.getGroupId())
			}, mdrRuleGroupInstance);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C_R,
			new Object[] {
				Long.valueOf(mdrRuleGroupInstance.getClassNameId()),
				Long.valueOf(mdrRuleGroupInstance.getClassPK()),
				Long.valueOf(mdrRuleGroupInstance.getRuleGroupId())
			}, mdrRuleGroupInstance);

		mdrRuleGroupInstance.resetOriginalValues();
	}

	/**
	 * Caches the m d r rule group instances in the entity cache if it is enabled.
	 *
	 * @param mdrRuleGroupInstances the m d r rule group instances
	 */
	public void cacheResult(List<MDRRuleGroupInstance> mdrRuleGroupInstances) {
		for (MDRRuleGroupInstance mdrRuleGroupInstance : mdrRuleGroupInstances) {
			if (EntityCacheUtil.getResult(
						MDRRuleGroupInstanceModelImpl.ENTITY_CACHE_ENABLED,
						MDRRuleGroupInstanceImpl.class,
						mdrRuleGroupInstance.getPrimaryKey()) == null) {
				cacheResult(mdrRuleGroupInstance);
			}
			else {
				mdrRuleGroupInstance.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all m d r rule group instances.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(MDRRuleGroupInstanceImpl.class.getName());
		}

		EntityCacheUtil.clearCache(MDRRuleGroupInstanceImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the m d r rule group instance.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MDRRuleGroupInstance mdrRuleGroupInstance) {
		EntityCacheUtil.removeResult(MDRRuleGroupInstanceModelImpl.ENTITY_CACHE_ENABLED,
			MDRRuleGroupInstanceImpl.class, mdrRuleGroupInstance.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(mdrRuleGroupInstance);
	}

	@Override
	public void clearCache(List<MDRRuleGroupInstance> mdrRuleGroupInstances) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (MDRRuleGroupInstance mdrRuleGroupInstance : mdrRuleGroupInstances) {
			EntityCacheUtil.removeResult(MDRRuleGroupInstanceModelImpl.ENTITY_CACHE_ENABLED,
				MDRRuleGroupInstanceImpl.class,
				mdrRuleGroupInstance.getPrimaryKey());

			clearUniqueFindersCache(mdrRuleGroupInstance);
		}
	}

	protected void clearUniqueFindersCache(
		MDRRuleGroupInstance mdrRuleGroupInstance) {
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				mdrRuleGroupInstance.getUuid(),
				Long.valueOf(mdrRuleGroupInstance.getGroupId())
			});

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_C_R,
			new Object[] {
				Long.valueOf(mdrRuleGroupInstance.getClassNameId()),
				Long.valueOf(mdrRuleGroupInstance.getClassPK()),
				Long.valueOf(mdrRuleGroupInstance.getRuleGroupId())
			});
	}

	/**
	 * Creates a new m d r rule group instance with the primary key. Does not add the m d r rule group instance to the database.
	 *
	 * @param ruleGroupInstanceId the primary key for the new m d r rule group instance
	 * @return the new m d r rule group instance
	 */
	public MDRRuleGroupInstance create(long ruleGroupInstanceId) {
		MDRRuleGroupInstance mdrRuleGroupInstance = new MDRRuleGroupInstanceImpl();

		mdrRuleGroupInstance.setNew(true);
		mdrRuleGroupInstance.setPrimaryKey(ruleGroupInstanceId);

		String uuid = PortalUUIDUtil.generate();

		mdrRuleGroupInstance.setUuid(uuid);

		return mdrRuleGroupInstance;
	}

	/**
	 * Removes the m d r rule group instance with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ruleGroupInstanceId the primary key of the m d r rule group instance
	 * @return the m d r rule group instance that was removed
	 * @throws com.liferay.portlet.mobiledevicerules.NoSuchRuleGroupInstanceException if a m d r rule group instance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MDRRuleGroupInstance remove(long ruleGroupInstanceId)
		throws NoSuchRuleGroupInstanceException, SystemException {
		return remove(Long.valueOf(ruleGroupInstanceId));
	}

	/**
	 * Removes the m d r rule group instance with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the m d r rule group instance
	 * @return the m d r rule group instance that was removed
	 * @throws com.liferay.portlet.mobiledevicerules.NoSuchRuleGroupInstanceException if a m d r rule group instance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MDRRuleGroupInstance remove(Serializable primaryKey)
		throws NoSuchRuleGroupInstanceException, SystemException {
		Session session = null;

		try {
			session = openSession();

			MDRRuleGroupInstance mdrRuleGroupInstance = (MDRRuleGroupInstance)session.get(MDRRuleGroupInstanceImpl.class,
					primaryKey);

			if (mdrRuleGroupInstance == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchRuleGroupInstanceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(mdrRuleGroupInstance);
		}
		catch (NoSuchRuleGroupInstanceException nsee) {
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
	protected MDRRuleGroupInstance removeImpl(
		MDRRuleGroupInstance mdrRuleGroupInstance) throws SystemException {
		mdrRuleGroupInstance = toUnwrappedModel(mdrRuleGroupInstance);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, mdrRuleGroupInstance);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(mdrRuleGroupInstance);

		return mdrRuleGroupInstance;
	}

	@Override
	public MDRRuleGroupInstance updateImpl(
		com.liferay.portlet.mobiledevicerules.model.MDRRuleGroupInstance mdrRuleGroupInstance,
		boolean merge) throws SystemException {
		mdrRuleGroupInstance = toUnwrappedModel(mdrRuleGroupInstance);

		boolean isNew = mdrRuleGroupInstance.isNew();

		MDRRuleGroupInstanceModelImpl mdrRuleGroupInstanceModelImpl = (MDRRuleGroupInstanceModelImpl)mdrRuleGroupInstance;

		if (Validator.isNull(mdrRuleGroupInstance.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			mdrRuleGroupInstance.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, mdrRuleGroupInstance, merge);

			mdrRuleGroupInstance.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !MDRRuleGroupInstanceModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((mdrRuleGroupInstanceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						mdrRuleGroupInstanceModelImpl.getOriginalUuid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { mdrRuleGroupInstanceModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((mdrRuleGroupInstanceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RULEGROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(mdrRuleGroupInstanceModelImpl.getOriginalRuleGroupId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_RULEGROUPID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RULEGROUPID,
					args);

				args = new Object[] {
						Long.valueOf(mdrRuleGroupInstanceModelImpl.getRuleGroupId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_RULEGROUPID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RULEGROUPID,
					args);
			}

			if ((mdrRuleGroupInstanceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(mdrRuleGroupInstanceModelImpl.getOriginalClassNameId()),
						Long.valueOf(mdrRuleGroupInstanceModelImpl.getOriginalClassPK())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C,
					args);

				args = new Object[] {
						Long.valueOf(mdrRuleGroupInstanceModelImpl.getClassNameId()),
						Long.valueOf(mdrRuleGroupInstanceModelImpl.getClassPK())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C,
					args);
			}

			if ((mdrRuleGroupInstanceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_C_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(mdrRuleGroupInstanceModelImpl.getOriginalGroupId()),
						Long.valueOf(mdrRuleGroupInstanceModelImpl.getOriginalClassNameId()),
						Long.valueOf(mdrRuleGroupInstanceModelImpl.getOriginalClassPK())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_C_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_C_C,
					args);

				args = new Object[] {
						Long.valueOf(mdrRuleGroupInstanceModelImpl.getGroupId()),
						Long.valueOf(mdrRuleGroupInstanceModelImpl.getClassNameId()),
						Long.valueOf(mdrRuleGroupInstanceModelImpl.getClassPK())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_C_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_C_C,
					args);
			}
		}

		EntityCacheUtil.putResult(MDRRuleGroupInstanceModelImpl.ENTITY_CACHE_ENABLED,
			MDRRuleGroupInstanceImpl.class,
			mdrRuleGroupInstance.getPrimaryKey(), mdrRuleGroupInstance);

		if (isNew) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
				new Object[] {
					mdrRuleGroupInstance.getUuid(),
					Long.valueOf(mdrRuleGroupInstance.getGroupId())
				}, mdrRuleGroupInstance);

			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C_R,
				new Object[] {
					Long.valueOf(mdrRuleGroupInstance.getClassNameId()),
					Long.valueOf(mdrRuleGroupInstance.getClassPK()),
					Long.valueOf(mdrRuleGroupInstance.getRuleGroupId())
				}, mdrRuleGroupInstance);
		}
		else {
			if ((mdrRuleGroupInstanceModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						mdrRuleGroupInstanceModelImpl.getOriginalUuid(),
						Long.valueOf(mdrRuleGroupInstanceModelImpl.getOriginalGroupId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
					new Object[] {
						mdrRuleGroupInstance.getUuid(),
						Long.valueOf(mdrRuleGroupInstance.getGroupId())
					}, mdrRuleGroupInstance);
			}

			if ((mdrRuleGroupInstanceModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_C_C_R.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(mdrRuleGroupInstanceModelImpl.getOriginalClassNameId()),
						Long.valueOf(mdrRuleGroupInstanceModelImpl.getOriginalClassPK()),
						Long.valueOf(mdrRuleGroupInstanceModelImpl.getOriginalRuleGroupId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C_R, args);
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_C_R, args);

				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C_R,
					new Object[] {
						Long.valueOf(mdrRuleGroupInstance.getClassNameId()),
						Long.valueOf(mdrRuleGroupInstance.getClassPK()),
						Long.valueOf(mdrRuleGroupInstance.getRuleGroupId())
					}, mdrRuleGroupInstance);
			}
		}

		return mdrRuleGroupInstance;
	}

	protected MDRRuleGroupInstance toUnwrappedModel(
		MDRRuleGroupInstance mdrRuleGroupInstance) {
		if (mdrRuleGroupInstance instanceof MDRRuleGroupInstanceImpl) {
			return mdrRuleGroupInstance;
		}

		MDRRuleGroupInstanceImpl mdrRuleGroupInstanceImpl = new MDRRuleGroupInstanceImpl();

		mdrRuleGroupInstanceImpl.setNew(mdrRuleGroupInstance.isNew());
		mdrRuleGroupInstanceImpl.setPrimaryKey(mdrRuleGroupInstance.getPrimaryKey());

		mdrRuleGroupInstanceImpl.setUuid(mdrRuleGroupInstance.getUuid());
		mdrRuleGroupInstanceImpl.setRuleGroupInstanceId(mdrRuleGroupInstance.getRuleGroupInstanceId());
		mdrRuleGroupInstanceImpl.setGroupId(mdrRuleGroupInstance.getGroupId());
		mdrRuleGroupInstanceImpl.setCompanyId(mdrRuleGroupInstance.getCompanyId());
		mdrRuleGroupInstanceImpl.setUserId(mdrRuleGroupInstance.getUserId());
		mdrRuleGroupInstanceImpl.setUserName(mdrRuleGroupInstance.getUserName());
		mdrRuleGroupInstanceImpl.setCreateDate(mdrRuleGroupInstance.getCreateDate());
		mdrRuleGroupInstanceImpl.setModifiedDate(mdrRuleGroupInstance.getModifiedDate());
		mdrRuleGroupInstanceImpl.setClassNameId(mdrRuleGroupInstance.getClassNameId());
		mdrRuleGroupInstanceImpl.setClassPK(mdrRuleGroupInstance.getClassPK());
		mdrRuleGroupInstanceImpl.setRuleGroupId(mdrRuleGroupInstance.getRuleGroupId());
		mdrRuleGroupInstanceImpl.setPriority(mdrRuleGroupInstance.getPriority());

		return mdrRuleGroupInstanceImpl;
	}

	/**
	 * Returns the m d r rule group instance with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the m d r rule group instance
	 * @return the m d r rule group instance
	 * @throws com.liferay.portal.NoSuchModelException if a m d r rule group instance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MDRRuleGroupInstance findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the m d r rule group instance with the primary key or throws a {@link com.liferay.portlet.mobiledevicerules.NoSuchRuleGroupInstanceException} if it could not be found.
	 *
	 * @param ruleGroupInstanceId the primary key of the m d r rule group instance
	 * @return the m d r rule group instance
	 * @throws com.liferay.portlet.mobiledevicerules.NoSuchRuleGroupInstanceException if a m d r rule group instance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MDRRuleGroupInstance findByPrimaryKey(long ruleGroupInstanceId)
		throws NoSuchRuleGroupInstanceException, SystemException {
		MDRRuleGroupInstance mdrRuleGroupInstance = fetchByPrimaryKey(ruleGroupInstanceId);

		if (mdrRuleGroupInstance == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					ruleGroupInstanceId);
			}

			throw new NoSuchRuleGroupInstanceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				ruleGroupInstanceId);
		}

		return mdrRuleGroupInstance;
	}

	/**
	 * Returns the m d r rule group instance with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the m d r rule group instance
	 * @return the m d r rule group instance, or <code>null</code> if a m d r rule group instance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MDRRuleGroupInstance fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the m d r rule group instance with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ruleGroupInstanceId the primary key of the m d r rule group instance
	 * @return the m d r rule group instance, or <code>null</code> if a m d r rule group instance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MDRRuleGroupInstance fetchByPrimaryKey(long ruleGroupInstanceId)
		throws SystemException {
		MDRRuleGroupInstance mdrRuleGroupInstance = (MDRRuleGroupInstance)EntityCacheUtil.getResult(MDRRuleGroupInstanceModelImpl.ENTITY_CACHE_ENABLED,
				MDRRuleGroupInstanceImpl.class, ruleGroupInstanceId);

		if (mdrRuleGroupInstance == _nullMDRRuleGroupInstance) {
			return null;
		}

		if (mdrRuleGroupInstance == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				mdrRuleGroupInstance = (MDRRuleGroupInstance)session.get(MDRRuleGroupInstanceImpl.class,
						Long.valueOf(ruleGroupInstanceId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (mdrRuleGroupInstance != null) {
					cacheResult(mdrRuleGroupInstance);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(MDRRuleGroupInstanceModelImpl.ENTITY_CACHE_ENABLED,
						MDRRuleGroupInstanceImpl.class, ruleGroupInstanceId,
						_nullMDRRuleGroupInstance);
				}

				closeSession(session);
			}
		}

		return mdrRuleGroupInstance;
	}

	/**
	 * Returns all the m d r rule group instances where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching m d r rule group instances
	 * @throws SystemException if a system exception occurred
	 */
	public List<MDRRuleGroupInstance> findByUuid(String uuid)
		throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the m d r rule group instances where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of m d r rule group instances
	 * @param end the upper bound of the range of m d r rule group instances (not inclusive)
	 * @return the range of matching m d r rule group instances
	 * @throws SystemException if a system exception occurred
	 */
	public List<MDRRuleGroupInstance> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the m d r rule group instances where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of m d r rule group instances
	 * @param end the upper bound of the range of m d r rule group instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching m d r rule group instances
	 * @throws SystemException if a system exception occurred
	 */
	public List<MDRRuleGroupInstance> findByUuid(String uuid, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid, start, end, orderByComparator };
		}

		List<MDRRuleGroupInstance> list = (List<MDRRuleGroupInstance>)FinderCacheUtil.getResult(finderPath,
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

			query.append(_SQL_SELECT_MDRRULEGROUPINSTANCE_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_UUID_2);
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

				if (uuid != null) {
					qPos.add(uuid);
				}

				list = (List<MDRRuleGroupInstance>)QueryUtil.list(q,
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
	 * Returns the first m d r rule group instance in the ordered set where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching m d r rule group instance
	 * @throws com.liferay.portlet.mobiledevicerules.NoSuchRuleGroupInstanceException if a matching m d r rule group instance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MDRRuleGroupInstance findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchRuleGroupInstanceException, SystemException {
		List<MDRRuleGroupInstance> list = findByUuid(uuid, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchRuleGroupInstanceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last m d r rule group instance in the ordered set where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching m d r rule group instance
	 * @throws com.liferay.portlet.mobiledevicerules.NoSuchRuleGroupInstanceException if a matching m d r rule group instance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MDRRuleGroupInstance findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchRuleGroupInstanceException, SystemException {
		int count = countByUuid(uuid);

		List<MDRRuleGroupInstance> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchRuleGroupInstanceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the m d r rule group instances before and after the current m d r rule group instance in the ordered set where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ruleGroupInstanceId the primary key of the current m d r rule group instance
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next m d r rule group instance
	 * @throws com.liferay.portlet.mobiledevicerules.NoSuchRuleGroupInstanceException if a m d r rule group instance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MDRRuleGroupInstance[] findByUuid_PrevAndNext(
		long ruleGroupInstanceId, String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchRuleGroupInstanceException, SystemException {
		MDRRuleGroupInstance mdrRuleGroupInstance = findByPrimaryKey(ruleGroupInstanceId);

		Session session = null;

		try {
			session = openSession();

			MDRRuleGroupInstance[] array = new MDRRuleGroupInstanceImpl[3];

			array[0] = getByUuid_PrevAndNext(session, mdrRuleGroupInstance,
					uuid, orderByComparator, true);

			array[1] = mdrRuleGroupInstance;

			array[2] = getByUuid_PrevAndNext(session, mdrRuleGroupInstance,
					uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MDRRuleGroupInstance getByUuid_PrevAndNext(Session session,
		MDRRuleGroupInstance mdrRuleGroupInstance, String uuid,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MDRRULEGROUPINSTANCE_WHERE);

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else {
			if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				query.append(_FINDER_COLUMN_UUID_UUID_2);
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

		if (uuid != null) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(mdrRuleGroupInstance);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MDRRuleGroupInstance> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the m d r rule group instance where uuid = &#63; and groupId = &#63; or throws a {@link com.liferay.portlet.mobiledevicerules.NoSuchRuleGroupInstanceException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching m d r rule group instance
	 * @throws com.liferay.portlet.mobiledevicerules.NoSuchRuleGroupInstanceException if a matching m d r rule group instance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MDRRuleGroupInstance findByUUID_G(String uuid, long groupId)
		throws NoSuchRuleGroupInstanceException, SystemException {
		MDRRuleGroupInstance mdrRuleGroupInstance = fetchByUUID_G(uuid, groupId);

		if (mdrRuleGroupInstance == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchRuleGroupInstanceException(msg.toString());
		}

		return mdrRuleGroupInstance;
	}

	/**
	 * Returns the m d r rule group instance where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching m d r rule group instance, or <code>null</code> if a matching m d r rule group instance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MDRRuleGroupInstance fetchByUUID_G(String uuid, long groupId)
		throws SystemException {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the m d r rule group instance where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching m d r rule group instance, or <code>null</code> if a matching m d r rule group instance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MDRRuleGroupInstance fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_MDRRULEGROUPINSTANCE_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_G_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_G_UUID_2);
				}
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				List<MDRRuleGroupInstance> list = q.list();

				result = list;

				MDRRuleGroupInstance mdrRuleGroupInstance = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					mdrRuleGroupInstance = list.get(0);

					cacheResult(mdrRuleGroupInstance);

					if ((mdrRuleGroupInstance.getUuid() == null) ||
							!mdrRuleGroupInstance.getUuid().equals(uuid) ||
							(mdrRuleGroupInstance.getGroupId() != groupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, mdrRuleGroupInstance);
					}
				}

				return mdrRuleGroupInstance;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
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
				return (MDRRuleGroupInstance)result;
			}
		}
	}

	/**
	 * Returns all the m d r rule group instances where ruleGroupId = &#63;.
	 *
	 * @param ruleGroupId the rule group ID
	 * @return the matching m d r rule group instances
	 * @throws SystemException if a system exception occurred
	 */
	public List<MDRRuleGroupInstance> findByRuleGroupId(long ruleGroupId)
		throws SystemException {
		return findByRuleGroupId(ruleGroupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the m d r rule group instances where ruleGroupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ruleGroupId the rule group ID
	 * @param start the lower bound of the range of m d r rule group instances
	 * @param end the upper bound of the range of m d r rule group instances (not inclusive)
	 * @return the range of matching m d r rule group instances
	 * @throws SystemException if a system exception occurred
	 */
	public List<MDRRuleGroupInstance> findByRuleGroupId(long ruleGroupId,
		int start, int end) throws SystemException {
		return findByRuleGroupId(ruleGroupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the m d r rule group instances where ruleGroupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ruleGroupId the rule group ID
	 * @param start the lower bound of the range of m d r rule group instances
	 * @param end the upper bound of the range of m d r rule group instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching m d r rule group instances
	 * @throws SystemException if a system exception occurred
	 */
	public List<MDRRuleGroupInstance> findByRuleGroupId(long ruleGroupId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RULEGROUPID;
			finderArgs = new Object[] { ruleGroupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_RULEGROUPID;
			finderArgs = new Object[] { ruleGroupId, start, end, orderByComparator };
		}

		List<MDRRuleGroupInstance> list = (List<MDRRuleGroupInstance>)FinderCacheUtil.getResult(finderPath,
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

			query.append(_SQL_SELECT_MDRRULEGROUPINSTANCE_WHERE);

			query.append(_FINDER_COLUMN_RULEGROUPID_RULEGROUPID_2);

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

				qPos.add(ruleGroupId);

				list = (List<MDRRuleGroupInstance>)QueryUtil.list(q,
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
	 * Returns the first m d r rule group instance in the ordered set where ruleGroupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ruleGroupId the rule group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching m d r rule group instance
	 * @throws com.liferay.portlet.mobiledevicerules.NoSuchRuleGroupInstanceException if a matching m d r rule group instance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MDRRuleGroupInstance findByRuleGroupId_First(long ruleGroupId,
		OrderByComparator orderByComparator)
		throws NoSuchRuleGroupInstanceException, SystemException {
		List<MDRRuleGroupInstance> list = findByRuleGroupId(ruleGroupId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("ruleGroupId=");
			msg.append(ruleGroupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchRuleGroupInstanceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last m d r rule group instance in the ordered set where ruleGroupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ruleGroupId the rule group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching m d r rule group instance
	 * @throws com.liferay.portlet.mobiledevicerules.NoSuchRuleGroupInstanceException if a matching m d r rule group instance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MDRRuleGroupInstance findByRuleGroupId_Last(long ruleGroupId,
		OrderByComparator orderByComparator)
		throws NoSuchRuleGroupInstanceException, SystemException {
		int count = countByRuleGroupId(ruleGroupId);

		List<MDRRuleGroupInstance> list = findByRuleGroupId(ruleGroupId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("ruleGroupId=");
			msg.append(ruleGroupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchRuleGroupInstanceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the m d r rule group instances before and after the current m d r rule group instance in the ordered set where ruleGroupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ruleGroupInstanceId the primary key of the current m d r rule group instance
	 * @param ruleGroupId the rule group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next m d r rule group instance
	 * @throws com.liferay.portlet.mobiledevicerules.NoSuchRuleGroupInstanceException if a m d r rule group instance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MDRRuleGroupInstance[] findByRuleGroupId_PrevAndNext(
		long ruleGroupInstanceId, long ruleGroupId,
		OrderByComparator orderByComparator)
		throws NoSuchRuleGroupInstanceException, SystemException {
		MDRRuleGroupInstance mdrRuleGroupInstance = findByPrimaryKey(ruleGroupInstanceId);

		Session session = null;

		try {
			session = openSession();

			MDRRuleGroupInstance[] array = new MDRRuleGroupInstanceImpl[3];

			array[0] = getByRuleGroupId_PrevAndNext(session,
					mdrRuleGroupInstance, ruleGroupId, orderByComparator, true);

			array[1] = mdrRuleGroupInstance;

			array[2] = getByRuleGroupId_PrevAndNext(session,
					mdrRuleGroupInstance, ruleGroupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MDRRuleGroupInstance getByRuleGroupId_PrevAndNext(
		Session session, MDRRuleGroupInstance mdrRuleGroupInstance,
		long ruleGroupId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MDRRULEGROUPINSTANCE_WHERE);

		query.append(_FINDER_COLUMN_RULEGROUPID_RULEGROUPID_2);

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

		qPos.add(ruleGroupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(mdrRuleGroupInstance);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MDRRuleGroupInstance> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the m d r rule group instances where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the matching m d r rule group instances
	 * @throws SystemException if a system exception occurred
	 */
	public List<MDRRuleGroupInstance> findByC_C(long classNameId, long classPK)
		throws SystemException {
		return findByC_C(classNameId, classPK, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the m d r rule group instances where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param start the lower bound of the range of m d r rule group instances
	 * @param end the upper bound of the range of m d r rule group instances (not inclusive)
	 * @return the range of matching m d r rule group instances
	 * @throws SystemException if a system exception occurred
	 */
	public List<MDRRuleGroupInstance> findByC_C(long classNameId, long classPK,
		int start, int end) throws SystemException {
		return findByC_C(classNameId, classPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the m d r rule group instances where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param start the lower bound of the range of m d r rule group instances
	 * @param end the upper bound of the range of m d r rule group instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching m d r rule group instances
	 * @throws SystemException if a system exception occurred
	 */
	public List<MDRRuleGroupInstance> findByC_C(long classNameId, long classPK,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C;
			finderArgs = new Object[] { classNameId, classPK };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C;
			finderArgs = new Object[] {
					classNameId, classPK,
					
					start, end, orderByComparator
				};
		}

		List<MDRRuleGroupInstance> list = (List<MDRRuleGroupInstance>)FinderCacheUtil.getResult(finderPath,
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

			query.append(_SQL_SELECT_MDRRULEGROUPINSTANCE_WHERE);

			query.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_CLASSPK_2);

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

				qPos.add(classNameId);

				qPos.add(classPK);

				list = (List<MDRRuleGroupInstance>)QueryUtil.list(q,
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
	 * Returns the first m d r rule group instance in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching m d r rule group instance
	 * @throws com.liferay.portlet.mobiledevicerules.NoSuchRuleGroupInstanceException if a matching m d r rule group instance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MDRRuleGroupInstance findByC_C_First(long classNameId, long classPK,
		OrderByComparator orderByComparator)
		throws NoSuchRuleGroupInstanceException, SystemException {
		List<MDRRuleGroupInstance> list = findByC_C(classNameId, classPK, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("classNameId=");
			msg.append(classNameId);

			msg.append(", classPK=");
			msg.append(classPK);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchRuleGroupInstanceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last m d r rule group instance in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching m d r rule group instance
	 * @throws com.liferay.portlet.mobiledevicerules.NoSuchRuleGroupInstanceException if a matching m d r rule group instance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MDRRuleGroupInstance findByC_C_Last(long classNameId, long classPK,
		OrderByComparator orderByComparator)
		throws NoSuchRuleGroupInstanceException, SystemException {
		int count = countByC_C(classNameId, classPK);

		List<MDRRuleGroupInstance> list = findByC_C(classNameId, classPK,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("classNameId=");
			msg.append(classNameId);

			msg.append(", classPK=");
			msg.append(classPK);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchRuleGroupInstanceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the m d r rule group instances before and after the current m d r rule group instance in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ruleGroupInstanceId the primary key of the current m d r rule group instance
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next m d r rule group instance
	 * @throws com.liferay.portlet.mobiledevicerules.NoSuchRuleGroupInstanceException if a m d r rule group instance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MDRRuleGroupInstance[] findByC_C_PrevAndNext(
		long ruleGroupInstanceId, long classNameId, long classPK,
		OrderByComparator orderByComparator)
		throws NoSuchRuleGroupInstanceException, SystemException {
		MDRRuleGroupInstance mdrRuleGroupInstance = findByPrimaryKey(ruleGroupInstanceId);

		Session session = null;

		try {
			session = openSession();

			MDRRuleGroupInstance[] array = new MDRRuleGroupInstanceImpl[3];

			array[0] = getByC_C_PrevAndNext(session, mdrRuleGroupInstance,
					classNameId, classPK, orderByComparator, true);

			array[1] = mdrRuleGroupInstance;

			array[2] = getByC_C_PrevAndNext(session, mdrRuleGroupInstance,
					classNameId, classPK, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MDRRuleGroupInstance getByC_C_PrevAndNext(Session session,
		MDRRuleGroupInstance mdrRuleGroupInstance, long classNameId,
		long classPK, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MDRRULEGROUPINSTANCE_WHERE);

		query.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_C_C_CLASSPK_2);

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

		qPos.add(classNameId);

		qPos.add(classPK);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(mdrRuleGroupInstance);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MDRRuleGroupInstance> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the m d r rule group instances where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the matching m d r rule group instances
	 * @throws SystemException if a system exception occurred
	 */
	public List<MDRRuleGroupInstance> findByG_C_C(long groupId,
		long classNameId, long classPK) throws SystemException {
		return findByG_C_C(groupId, classNameId, classPK, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the m d r rule group instances where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param start the lower bound of the range of m d r rule group instances
	 * @param end the upper bound of the range of m d r rule group instances (not inclusive)
	 * @return the range of matching m d r rule group instances
	 * @throws SystemException if a system exception occurred
	 */
	public List<MDRRuleGroupInstance> findByG_C_C(long groupId,
		long classNameId, long classPK, int start, int end)
		throws SystemException {
		return findByG_C_C(groupId, classNameId, classPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the m d r rule group instances where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param start the lower bound of the range of m d r rule group instances
	 * @param end the upper bound of the range of m d r rule group instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching m d r rule group instances
	 * @throws SystemException if a system exception occurred
	 */
	public List<MDRRuleGroupInstance> findByG_C_C(long groupId,
		long classNameId, long classPK, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_C_C;
			finderArgs = new Object[] { groupId, classNameId, classPK };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_C_C;
			finderArgs = new Object[] {
					groupId, classNameId, classPK,
					
					start, end, orderByComparator
				};
		}

		List<MDRRuleGroupInstance> list = (List<MDRRuleGroupInstance>)FinderCacheUtil.getResult(finderPath,
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

			query.append(_SQL_SELECT_MDRRULEGROUPINSTANCE_WHERE);

			query.append(_FINDER_COLUMN_G_C_C_GROUPID_2);

			query.append(_FINDER_COLUMN_G_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_G_C_C_CLASSPK_2);

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

				qPos.add(groupId);

				qPos.add(classNameId);

				qPos.add(classPK);

				list = (List<MDRRuleGroupInstance>)QueryUtil.list(q,
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
	 * Returns the first m d r rule group instance in the ordered set where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching m d r rule group instance
	 * @throws com.liferay.portlet.mobiledevicerules.NoSuchRuleGroupInstanceException if a matching m d r rule group instance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MDRRuleGroupInstance findByG_C_C_First(long groupId,
		long classNameId, long classPK, OrderByComparator orderByComparator)
		throws NoSuchRuleGroupInstanceException, SystemException {
		List<MDRRuleGroupInstance> list = findByG_C_C(groupId, classNameId,
				classPK, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", classNameId=");
			msg.append(classNameId);

			msg.append(", classPK=");
			msg.append(classPK);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchRuleGroupInstanceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last m d r rule group instance in the ordered set where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching m d r rule group instance
	 * @throws com.liferay.portlet.mobiledevicerules.NoSuchRuleGroupInstanceException if a matching m d r rule group instance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MDRRuleGroupInstance findByG_C_C_Last(long groupId,
		long classNameId, long classPK, OrderByComparator orderByComparator)
		throws NoSuchRuleGroupInstanceException, SystemException {
		int count = countByG_C_C(groupId, classNameId, classPK);

		List<MDRRuleGroupInstance> list = findByG_C_C(groupId, classNameId,
				classPK, count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", classNameId=");
			msg.append(classNameId);

			msg.append(", classPK=");
			msg.append(classPK);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchRuleGroupInstanceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the m d r rule group instances before and after the current m d r rule group instance in the ordered set where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ruleGroupInstanceId the primary key of the current m d r rule group instance
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next m d r rule group instance
	 * @throws com.liferay.portlet.mobiledevicerules.NoSuchRuleGroupInstanceException if a m d r rule group instance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MDRRuleGroupInstance[] findByG_C_C_PrevAndNext(
		long ruleGroupInstanceId, long groupId, long classNameId, long classPK,
		OrderByComparator orderByComparator)
		throws NoSuchRuleGroupInstanceException, SystemException {
		MDRRuleGroupInstance mdrRuleGroupInstance = findByPrimaryKey(ruleGroupInstanceId);

		Session session = null;

		try {
			session = openSession();

			MDRRuleGroupInstance[] array = new MDRRuleGroupInstanceImpl[3];

			array[0] = getByG_C_C_PrevAndNext(session, mdrRuleGroupInstance,
					groupId, classNameId, classPK, orderByComparator, true);

			array[1] = mdrRuleGroupInstance;

			array[2] = getByG_C_C_PrevAndNext(session, mdrRuleGroupInstance,
					groupId, classNameId, classPK, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MDRRuleGroupInstance getByG_C_C_PrevAndNext(Session session,
		MDRRuleGroupInstance mdrRuleGroupInstance, long groupId,
		long classNameId, long classPK, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MDRRULEGROUPINSTANCE_WHERE);

		query.append(_FINDER_COLUMN_G_C_C_GROUPID_2);

		query.append(_FINDER_COLUMN_G_C_C_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_G_C_C_CLASSPK_2);

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

		qPos.add(groupId);

		qPos.add(classNameId);

		qPos.add(classPK);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(mdrRuleGroupInstance);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MDRRuleGroupInstance> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the m d r rule group instances that the user has permission to view where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the matching m d r rule group instances that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<MDRRuleGroupInstance> filterFindByG_C_C(long groupId,
		long classNameId, long classPK) throws SystemException {
		return filterFindByG_C_C(groupId, classNameId, classPK,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the m d r rule group instances that the user has permission to view where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param start the lower bound of the range of m d r rule group instances
	 * @param end the upper bound of the range of m d r rule group instances (not inclusive)
	 * @return the range of matching m d r rule group instances that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<MDRRuleGroupInstance> filterFindByG_C_C(long groupId,
		long classNameId, long classPK, int start, int end)
		throws SystemException {
		return filterFindByG_C_C(groupId, classNameId, classPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the m d r rule group instances that the user has permissions to view where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param start the lower bound of the range of m d r rule group instances
	 * @param end the upper bound of the range of m d r rule group instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching m d r rule group instances that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<MDRRuleGroupInstance> filterFindByG_C_C(long groupId,
		long classNameId, long classPK, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_C_C(groupId, classNameId, classPK, start, end,
				orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MDRRULEGROUPINSTANCE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_MDRRULEGROUPINSTANCE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_C_C_GROUPID_2);

		query.append(_FINDER_COLUMN_G_C_C_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_G_C_C_CLASSPK_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MDRRULEGROUPINSTANCE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				MDRRuleGroupInstance.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, MDRRuleGroupInstanceImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, MDRRuleGroupInstanceImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(classNameId);

			qPos.add(classPK);

			return (List<MDRRuleGroupInstance>)QueryUtil.list(q, getDialect(),
				start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the m d r rule group instances before and after the current m d r rule group instance in the ordered set of m d r rule group instances that the user has permission to view where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param ruleGroupInstanceId the primary key of the current m d r rule group instance
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next m d r rule group instance
	 * @throws com.liferay.portlet.mobiledevicerules.NoSuchRuleGroupInstanceException if a m d r rule group instance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MDRRuleGroupInstance[] filterFindByG_C_C_PrevAndNext(
		long ruleGroupInstanceId, long groupId, long classNameId, long classPK,
		OrderByComparator orderByComparator)
		throws NoSuchRuleGroupInstanceException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_C_C_PrevAndNext(ruleGroupInstanceId, groupId,
				classNameId, classPK, orderByComparator);
		}

		MDRRuleGroupInstance mdrRuleGroupInstance = findByPrimaryKey(ruleGroupInstanceId);

		Session session = null;

		try {
			session = openSession();

			MDRRuleGroupInstance[] array = new MDRRuleGroupInstanceImpl[3];

			array[0] = filterGetByG_C_C_PrevAndNext(session,
					mdrRuleGroupInstance, groupId, classNameId, classPK,
					orderByComparator, true);

			array[1] = mdrRuleGroupInstance;

			array[2] = filterGetByG_C_C_PrevAndNext(session,
					mdrRuleGroupInstance, groupId, classNameId, classPK,
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

	protected MDRRuleGroupInstance filterGetByG_C_C_PrevAndNext(
		Session session, MDRRuleGroupInstance mdrRuleGroupInstance,
		long groupId, long classNameId, long classPK,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MDRRULEGROUPINSTANCE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_MDRRULEGROUPINSTANCE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_C_C_GROUPID_2);

		query.append(_FINDER_COLUMN_G_C_C_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_G_C_C_CLASSPK_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MDRRULEGROUPINSTANCE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				MDRRuleGroupInstance.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, MDRRuleGroupInstanceImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, MDRRuleGroupInstanceImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(classNameId);

		qPos.add(classPK);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(mdrRuleGroupInstance);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MDRRuleGroupInstance> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the m d r rule group instance where classNameId = &#63; and classPK = &#63; and ruleGroupId = &#63; or throws a {@link com.liferay.portlet.mobiledevicerules.NoSuchRuleGroupInstanceException} if it could not be found.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param ruleGroupId the rule group ID
	 * @return the matching m d r rule group instance
	 * @throws com.liferay.portlet.mobiledevicerules.NoSuchRuleGroupInstanceException if a matching m d r rule group instance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MDRRuleGroupInstance findByC_C_R(long classNameId, long classPK,
		long ruleGroupId)
		throws NoSuchRuleGroupInstanceException, SystemException {
		MDRRuleGroupInstance mdrRuleGroupInstance = fetchByC_C_R(classNameId,
				classPK, ruleGroupId);

		if (mdrRuleGroupInstance == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("classNameId=");
			msg.append(classNameId);

			msg.append(", classPK=");
			msg.append(classPK);

			msg.append(", ruleGroupId=");
			msg.append(ruleGroupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchRuleGroupInstanceException(msg.toString());
		}

		return mdrRuleGroupInstance;
	}

	/**
	 * Returns the m d r rule group instance where classNameId = &#63; and classPK = &#63; and ruleGroupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param ruleGroupId the rule group ID
	 * @return the matching m d r rule group instance, or <code>null</code> if a matching m d r rule group instance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MDRRuleGroupInstance fetchByC_C_R(long classNameId, long classPK,
		long ruleGroupId) throws SystemException {
		return fetchByC_C_R(classNameId, classPK, ruleGroupId, true);
	}

	/**
	 * Returns the m d r rule group instance where classNameId = &#63; and classPK = &#63; and ruleGroupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param ruleGroupId the rule group ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching m d r rule group instance, or <code>null</code> if a matching m d r rule group instance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MDRRuleGroupInstance fetchByC_C_R(long classNameId, long classPK,
		long ruleGroupId, boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { classNameId, classPK, ruleGroupId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_C_C_R,
					finderArgs, this);
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_MDRRULEGROUPINSTANCE_WHERE);

			query.append(_FINDER_COLUMN_C_C_R_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_R_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_R_RULEGROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(ruleGroupId);

				List<MDRRuleGroupInstance> list = q.list();

				result = list;

				MDRRuleGroupInstance mdrRuleGroupInstance = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C_R,
						finderArgs, list);
				}
				else {
					mdrRuleGroupInstance = list.get(0);

					cacheResult(mdrRuleGroupInstance);

					if ((mdrRuleGroupInstance.getClassNameId() != classNameId) ||
							(mdrRuleGroupInstance.getClassPK() != classPK) ||
							(mdrRuleGroupInstance.getRuleGroupId() != ruleGroupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C_R,
							finderArgs, mdrRuleGroupInstance);
					}
				}

				return mdrRuleGroupInstance;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_C_R,
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
				return (MDRRuleGroupInstance)result;
			}
		}
	}

	/**
	 * Returns all the m d r rule group instances.
	 *
	 * @return the m d r rule group instances
	 * @throws SystemException if a system exception occurred
	 */
	public List<MDRRuleGroupInstance> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the m d r rule group instances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of m d r rule group instances
	 * @param end the upper bound of the range of m d r rule group instances (not inclusive)
	 * @return the range of m d r rule group instances
	 * @throws SystemException if a system exception occurred
	 */
	public List<MDRRuleGroupInstance> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the m d r rule group instances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of m d r rule group instances
	 * @param end the upper bound of the range of m d r rule group instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of m d r rule group instances
	 * @throws SystemException if a system exception occurred
	 */
	public List<MDRRuleGroupInstance> findAll(int start, int end,
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

		List<MDRRuleGroupInstance> list = (List<MDRRuleGroupInstance>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_MDRRULEGROUPINSTANCE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_MDRRULEGROUPINSTANCE;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<MDRRuleGroupInstance>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<MDRRuleGroupInstance>)QueryUtil.list(q,
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
	 * Removes all the m d r rule group instances where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUuid(String uuid) throws SystemException {
		for (MDRRuleGroupInstance mdrRuleGroupInstance : findByUuid(uuid)) {
			remove(mdrRuleGroupInstance);
		}
	}

	/**
	 * Removes the m d r rule group instance where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUUID_G(String uuid, long groupId)
		throws NoSuchRuleGroupInstanceException, SystemException {
		MDRRuleGroupInstance mdrRuleGroupInstance = findByUUID_G(uuid, groupId);

		remove(mdrRuleGroupInstance);
	}

	/**
	 * Removes all the m d r rule group instances where ruleGroupId = &#63; from the database.
	 *
	 * @param ruleGroupId the rule group ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByRuleGroupId(long ruleGroupId) throws SystemException {
		for (MDRRuleGroupInstance mdrRuleGroupInstance : findByRuleGroupId(
				ruleGroupId)) {
			remove(mdrRuleGroupInstance);
		}
	}

	/**
	 * Removes all the m d r rule group instances where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByC_C(long classNameId, long classPK)
		throws SystemException {
		for (MDRRuleGroupInstance mdrRuleGroupInstance : findByC_C(
				classNameId, classPK)) {
			remove(mdrRuleGroupInstance);
		}
	}

	/**
	 * Removes all the m d r rule group instances where groupId = &#63; and classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByG_C_C(long groupId, long classNameId, long classPK)
		throws SystemException {
		for (MDRRuleGroupInstance mdrRuleGroupInstance : findByG_C_C(groupId,
				classNameId, classPK)) {
			remove(mdrRuleGroupInstance);
		}
	}

	/**
	 * Removes the m d r rule group instance where classNameId = &#63; and classPK = &#63; and ruleGroupId = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param ruleGroupId the rule group ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByC_C_R(long classNameId, long classPK, long ruleGroupId)
		throws NoSuchRuleGroupInstanceException, SystemException {
		MDRRuleGroupInstance mdrRuleGroupInstance = findByC_C_R(classNameId,
				classPK, ruleGroupId);

		remove(mdrRuleGroupInstance);
	}

	/**
	 * Removes all the m d r rule group instances from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (MDRRuleGroupInstance mdrRuleGroupInstance : findAll()) {
			remove(mdrRuleGroupInstance);
		}
	}

	/**
	 * Returns the number of m d r rule group instances where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching m d r rule group instances
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUuid(String uuid) throws SystemException {
		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MDRRULEGROUPINSTANCE_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_UUID_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of m d r rule group instances where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching m d r rule group instances
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUUID_G(String uuid, long groupId)
		throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID_G,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MDRRULEGROUPINSTANCE_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_G_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_G_UUID_2);
				}
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of m d r rule group instances where ruleGroupId = &#63;.
	 *
	 * @param ruleGroupId the rule group ID
	 * @return the number of matching m d r rule group instances
	 * @throws SystemException if a system exception occurred
	 */
	public int countByRuleGroupId(long ruleGroupId) throws SystemException {
		Object[] finderArgs = new Object[] { ruleGroupId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_RULEGROUPID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MDRRULEGROUPINSTANCE_WHERE);

			query.append(_FINDER_COLUMN_RULEGROUPID_RULEGROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ruleGroupId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_RULEGROUPID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of m d r rule group instances where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the number of matching m d r rule group instances
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_C(long classNameId, long classPK)
		throws SystemException {
		Object[] finderArgs = new Object[] { classNameId, classPK };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_C,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MDRRULEGROUPINSTANCE_WHERE);

			query.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_CLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_C, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of m d r rule group instances where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the number of matching m d r rule group instances
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_C_C(long groupId, long classNameId, long classPK)
		throws SystemException {
		Object[] finderArgs = new Object[] { groupId, classNameId, classPK };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_C_C,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_MDRRULEGROUPINSTANCE_WHERE);

			query.append(_FINDER_COLUMN_G_C_C_GROUPID_2);

			query.append(_FINDER_COLUMN_G_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_G_C_C_CLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(classNameId);

				qPos.add(classPK);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_C_C,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of m d r rule group instances that the user has permission to view where groupId = &#63; and classNameId = &#63; and classPK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the number of matching m d r rule group instances that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByG_C_C(long groupId, long classNameId, long classPK)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_C_C(groupId, classNameId, classPK);
		}

		StringBundler query = new StringBundler(4);

		query.append(_FILTER_SQL_COUNT_MDRRULEGROUPINSTANCE_WHERE);

		query.append(_FINDER_COLUMN_G_C_C_GROUPID_2);

		query.append(_FINDER_COLUMN_G_C_C_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_G_C_C_CLASSPK_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				MDRRuleGroupInstance.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(classNameId);

			qPos.add(classPK);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the number of m d r rule group instances where classNameId = &#63; and classPK = &#63; and ruleGroupId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param ruleGroupId the rule group ID
	 * @return the number of matching m d r rule group instances
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_C_R(long classNameId, long classPK, long ruleGroupId)
		throws SystemException {
		Object[] finderArgs = new Object[] { classNameId, classPK, ruleGroupId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_C_R,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_MDRRULEGROUPINSTANCE_WHERE);

			query.append(_FINDER_COLUMN_C_C_R_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_R_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_R_RULEGROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(ruleGroupId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_C_R,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of m d r rule group instances.
	 *
	 * @return the number of m d r rule group instances
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MDRRULEGROUPINSTANCE);

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
	 * Initializes the m d r rule group instance persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.portal.util.PropsUtil.get(
						"value.object.listener.com.liferay.portlet.mobiledevicerules.model.MDRRuleGroupInstance")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<MDRRuleGroupInstance>> listenersList = new ArrayList<ModelListener<MDRRuleGroupInstance>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<MDRRuleGroupInstance>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(MDRRuleGroupInstanceImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = MDRActionPersistence.class)
	protected MDRActionPersistence mdrActionPersistence;
	@BeanReference(type = MDRRulePersistence.class)
	protected MDRRulePersistence mdrRulePersistence;
	@BeanReference(type = MDRRuleGroupPersistence.class)
	protected MDRRuleGroupPersistence mdrRuleGroupPersistence;
	@BeanReference(type = MDRRuleGroupInstancePersistence.class)
	protected MDRRuleGroupInstancePersistence mdrRuleGroupInstancePersistence;
	@BeanReference(type = LayoutPersistence.class)
	protected LayoutPersistence layoutPersistence;
	@BeanReference(type = LayoutSetPersistence.class)
	protected LayoutSetPersistence layoutSetPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_MDRRULEGROUPINSTANCE = "SELECT mdrRuleGroupInstance FROM MDRRuleGroupInstance mdrRuleGroupInstance";
	private static final String _SQL_SELECT_MDRRULEGROUPINSTANCE_WHERE = "SELECT mdrRuleGroupInstance FROM MDRRuleGroupInstance mdrRuleGroupInstance WHERE ";
	private static final String _SQL_COUNT_MDRRULEGROUPINSTANCE = "SELECT COUNT(mdrRuleGroupInstance) FROM MDRRuleGroupInstance mdrRuleGroupInstance";
	private static final String _SQL_COUNT_MDRRULEGROUPINSTANCE_WHERE = "SELECT COUNT(mdrRuleGroupInstance) FROM MDRRuleGroupInstance mdrRuleGroupInstance WHERE ";
	private static final String _FINDER_COLUMN_UUID_UUID_1 = "mdrRuleGroupInstance.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "mdrRuleGroupInstance.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(mdrRuleGroupInstance.uuid IS NULL OR mdrRuleGroupInstance.uuid = ?)";
	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "mdrRuleGroupInstance.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "mdrRuleGroupInstance.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(mdrRuleGroupInstance.uuid IS NULL OR mdrRuleGroupInstance.uuid = ?) AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "mdrRuleGroupInstance.groupId = ?";
	private static final String _FINDER_COLUMN_RULEGROUPID_RULEGROUPID_2 = "mdrRuleGroupInstance.ruleGroupId = ?";
	private static final String _FINDER_COLUMN_C_C_CLASSNAMEID_2 = "mdrRuleGroupInstance.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_CLASSPK_2 = "mdrRuleGroupInstance.classPK = ?";
	private static final String _FINDER_COLUMN_G_C_C_GROUPID_2 = "mdrRuleGroupInstance.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_C_C_CLASSNAMEID_2 = "mdrRuleGroupInstance.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_G_C_C_CLASSPK_2 = "mdrRuleGroupInstance.classPK = ?";
	private static final String _FINDER_COLUMN_C_C_R_CLASSNAMEID_2 = "mdrRuleGroupInstance.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_R_CLASSPK_2 = "mdrRuleGroupInstance.classPK = ? AND ";
	private static final String _FINDER_COLUMN_C_C_R_RULEGROUPID_2 = "mdrRuleGroupInstance.ruleGroupId = ?";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "mdrRuleGroupInstance.ruleGroupInstanceId";
	private static final String _FILTER_SQL_SELECT_MDRRULEGROUPINSTANCE_WHERE = "SELECT DISTINCT {mdrRuleGroupInstance.*} FROM MDRRuleGroupInstance mdrRuleGroupInstance WHERE ";
	private static final String _FILTER_SQL_SELECT_MDRRULEGROUPINSTANCE_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {MDRRuleGroupInstance.*} FROM (SELECT DISTINCT mdrRuleGroupInstance.ruleGroupInstanceId FROM MDRRuleGroupInstance mdrRuleGroupInstance WHERE ";
	private static final String _FILTER_SQL_SELECT_MDRRULEGROUPINSTANCE_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN MDRRuleGroupInstance ON TEMP_TABLE.ruleGroupInstanceId = MDRRuleGroupInstance.ruleGroupInstanceId";
	private static final String _FILTER_SQL_COUNT_MDRRULEGROUPINSTANCE_WHERE = "SELECT COUNT(DISTINCT mdrRuleGroupInstance.ruleGroupInstanceId) AS COUNT_VALUE FROM MDRRuleGroupInstance mdrRuleGroupInstance WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "mdrRuleGroupInstance";
	private static final String _FILTER_ENTITY_TABLE = "MDRRuleGroupInstance";
	private static final String _ORDER_BY_ENTITY_ALIAS = "mdrRuleGroupInstance.";
	private static final String _ORDER_BY_ENTITY_TABLE = "MDRRuleGroupInstance.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MDRRuleGroupInstance exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No MDRRuleGroupInstance exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = com.liferay.portal.util.PropsValues.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE;
	private static Log _log = LogFactoryUtil.getLog(MDRRuleGroupInstancePersistenceImpl.class);
	private static MDRRuleGroupInstance _nullMDRRuleGroupInstance = new MDRRuleGroupInstanceImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<MDRRuleGroupInstance> toCacheModel() {
				return _nullMDRRuleGroupInstanceCacheModel;
			}
		};

	private static CacheModel<MDRRuleGroupInstance> _nullMDRRuleGroupInstanceCacheModel =
		new CacheModel<MDRRuleGroupInstance>() {
			public MDRRuleGroupInstance toEntityModel() {
				return _nullMDRRuleGroupInstance;
			}
		};
}