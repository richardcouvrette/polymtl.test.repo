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

package com.liferay.portlet.documentlibrary.service.persistence;

import com.liferay.portal.NoSuchModelException;
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
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.LayoutPersistence;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.portlet.documentlibrary.NoSuchFileRankException;
import com.liferay.portlet.documentlibrary.model.DLFileRank;
import com.liferay.portlet.documentlibrary.model.impl.DLFileRankImpl;
import com.liferay.portlet.documentlibrary.model.impl.DLFileRankModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the document library file rank service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DLFileRankPersistence
 * @see DLFileRankUtil
 * @generated
 */
public class DLFileRankPersistenceImpl extends BasePersistenceImpl<DLFileRank>
	implements DLFileRankPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DLFileRankUtil} to access the document library file rank persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DLFileRankImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(DLFileRankModelImpl.ENTITY_CACHE_ENABLED,
			DLFileRankModelImpl.FINDER_CACHE_ENABLED, DLFileRankImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(DLFileRankModelImpl.ENTITY_CACHE_ENABLED,
			DLFileRankModelImpl.FINDER_CACHE_ENABLED, DLFileRankImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] { Long.class.getName() },
			DLFileRankModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(DLFileRankModelImpl.ENTITY_CACHE_ENABLED,
			DLFileRankModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_FILEENTRYID =
		new FinderPath(DLFileRankModelImpl.ENTITY_CACHE_ENABLED,
			DLFileRankModelImpl.FINDER_CACHE_ENABLED, DLFileRankImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByFileEntryId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FILEENTRYID =
		new FinderPath(DLFileRankModelImpl.ENTITY_CACHE_ENABLED,
			DLFileRankModelImpl.FINDER_CACHE_ENABLED, DLFileRankImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByFileEntryId",
			new String[] { Long.class.getName() },
			DLFileRankModelImpl.FILEENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_FILEENTRYID = new FinderPath(DLFileRankModelImpl.ENTITY_CACHE_ENABLED,
			DLFileRankModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByFileEntryId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_U = new FinderPath(DLFileRankModelImpl.ENTITY_CACHE_ENABLED,
			DLFileRankModelImpl.FINDER_CACHE_ENABLED, DLFileRankImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_U",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U = new FinderPath(DLFileRankModelImpl.ENTITY_CACHE_ENABLED,
			DLFileRankModelImpl.FINDER_CACHE_ENABLED, DLFileRankImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_U",
			new String[] { Long.class.getName(), Long.class.getName() },
			DLFileRankModelImpl.GROUPID_COLUMN_BITMASK |
			DLFileRankModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_U = new FinderPath(DLFileRankModelImpl.ENTITY_CACHE_ENABLED,
			DLFileRankModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_U",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_C_U_F = new FinderPath(DLFileRankModelImpl.ENTITY_CACHE_ENABLED,
			DLFileRankModelImpl.FINDER_CACHE_ENABLED, DLFileRankImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByC_U_F",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			DLFileRankModelImpl.COMPANYID_COLUMN_BITMASK |
			DLFileRankModelImpl.USERID_COLUMN_BITMASK |
			DLFileRankModelImpl.FILEENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_U_F = new FinderPath(DLFileRankModelImpl.ENTITY_CACHE_ENABLED,
			DLFileRankModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_U_F",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DLFileRankModelImpl.ENTITY_CACHE_ENABLED,
			DLFileRankModelImpl.FINDER_CACHE_ENABLED, DLFileRankImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DLFileRankModelImpl.ENTITY_CACHE_ENABLED,
			DLFileRankModelImpl.FINDER_CACHE_ENABLED, DLFileRankImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DLFileRankModelImpl.ENTITY_CACHE_ENABLED,
			DLFileRankModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the document library file rank in the entity cache if it is enabled.
	 *
	 * @param dlFileRank the document library file rank
	 */
	public void cacheResult(DLFileRank dlFileRank) {
		EntityCacheUtil.putResult(DLFileRankModelImpl.ENTITY_CACHE_ENABLED,
			DLFileRankImpl.class, dlFileRank.getPrimaryKey(), dlFileRank);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_U_F,
			new Object[] {
				Long.valueOf(dlFileRank.getCompanyId()),
				Long.valueOf(dlFileRank.getUserId()),
				Long.valueOf(dlFileRank.getFileEntryId())
			}, dlFileRank);

		dlFileRank.resetOriginalValues();
	}

	/**
	 * Caches the document library file ranks in the entity cache if it is enabled.
	 *
	 * @param dlFileRanks the document library file ranks
	 */
	public void cacheResult(List<DLFileRank> dlFileRanks) {
		for (DLFileRank dlFileRank : dlFileRanks) {
			if (EntityCacheUtil.getResult(
						DLFileRankModelImpl.ENTITY_CACHE_ENABLED,
						DLFileRankImpl.class, dlFileRank.getPrimaryKey()) == null) {
				cacheResult(dlFileRank);
			}
			else {
				dlFileRank.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all document library file ranks.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(DLFileRankImpl.class.getName());
		}

		EntityCacheUtil.clearCache(DLFileRankImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the document library file rank.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DLFileRank dlFileRank) {
		EntityCacheUtil.removeResult(DLFileRankModelImpl.ENTITY_CACHE_ENABLED,
			DLFileRankImpl.class, dlFileRank.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(dlFileRank);
	}

	@Override
	public void clearCache(List<DLFileRank> dlFileRanks) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DLFileRank dlFileRank : dlFileRanks) {
			EntityCacheUtil.removeResult(DLFileRankModelImpl.ENTITY_CACHE_ENABLED,
				DLFileRankImpl.class, dlFileRank.getPrimaryKey());

			clearUniqueFindersCache(dlFileRank);
		}
	}

	protected void clearUniqueFindersCache(DLFileRank dlFileRank) {
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_U_F,
			new Object[] {
				Long.valueOf(dlFileRank.getCompanyId()),
				Long.valueOf(dlFileRank.getUserId()),
				Long.valueOf(dlFileRank.getFileEntryId())
			});
	}

	/**
	 * Creates a new document library file rank with the primary key. Does not add the document library file rank to the database.
	 *
	 * @param fileRankId the primary key for the new document library file rank
	 * @return the new document library file rank
	 */
	public DLFileRank create(long fileRankId) {
		DLFileRank dlFileRank = new DLFileRankImpl();

		dlFileRank.setNew(true);
		dlFileRank.setPrimaryKey(fileRankId);

		return dlFileRank;
	}

	/**
	 * Removes the document library file rank with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param fileRankId the primary key of the document library file rank
	 * @return the document library file rank that was removed
	 * @throws com.liferay.portlet.documentlibrary.NoSuchFileRankException if a document library file rank with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DLFileRank remove(long fileRankId)
		throws NoSuchFileRankException, SystemException {
		return remove(Long.valueOf(fileRankId));
	}

	/**
	 * Removes the document library file rank with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the document library file rank
	 * @return the document library file rank that was removed
	 * @throws com.liferay.portlet.documentlibrary.NoSuchFileRankException if a document library file rank with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DLFileRank remove(Serializable primaryKey)
		throws NoSuchFileRankException, SystemException {
		Session session = null;

		try {
			session = openSession();

			DLFileRank dlFileRank = (DLFileRank)session.get(DLFileRankImpl.class,
					primaryKey);

			if (dlFileRank == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchFileRankException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(dlFileRank);
		}
		catch (NoSuchFileRankException nsee) {
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
	protected DLFileRank removeImpl(DLFileRank dlFileRank)
		throws SystemException {
		dlFileRank = toUnwrappedModel(dlFileRank);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, dlFileRank);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(dlFileRank);

		return dlFileRank;
	}

	@Override
	public DLFileRank updateImpl(
		com.liferay.portlet.documentlibrary.model.DLFileRank dlFileRank,
		boolean merge) throws SystemException {
		dlFileRank = toUnwrappedModel(dlFileRank);

		boolean isNew = dlFileRank.isNew();

		DLFileRankModelImpl dlFileRankModelImpl = (DLFileRankModelImpl)dlFileRank;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, dlFileRank, merge);

			dlFileRank.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !DLFileRankModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((dlFileRankModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(dlFileRankModelImpl.getOriginalUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] {
						Long.valueOf(dlFileRankModelImpl.getUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}

			if ((dlFileRankModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FILEENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(dlFileRankModelImpl.getOriginalFileEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FILEENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FILEENTRYID,
					args);

				args = new Object[] {
						Long.valueOf(dlFileRankModelImpl.getFileEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FILEENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FILEENTRYID,
					args);
			}

			if ((dlFileRankModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(dlFileRankModelImpl.getOriginalGroupId()),
						Long.valueOf(dlFileRankModelImpl.getOriginalUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_U, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U,
					args);

				args = new Object[] {
						Long.valueOf(dlFileRankModelImpl.getGroupId()),
						Long.valueOf(dlFileRankModelImpl.getUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_U, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U,
					args);
			}
		}

		EntityCacheUtil.putResult(DLFileRankModelImpl.ENTITY_CACHE_ENABLED,
			DLFileRankImpl.class, dlFileRank.getPrimaryKey(), dlFileRank);

		if (isNew) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_U_F,
				new Object[] {
					Long.valueOf(dlFileRank.getCompanyId()),
					Long.valueOf(dlFileRank.getUserId()),
					Long.valueOf(dlFileRank.getFileEntryId())
				}, dlFileRank);
		}
		else {
			if ((dlFileRankModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_C_U_F.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(dlFileRankModelImpl.getOriginalCompanyId()),
						Long.valueOf(dlFileRankModelImpl.getOriginalUserId()),
						Long.valueOf(dlFileRankModelImpl.getOriginalFileEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_U_F, args);
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_U_F, args);

				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_U_F,
					new Object[] {
						Long.valueOf(dlFileRank.getCompanyId()),
						Long.valueOf(dlFileRank.getUserId()),
						Long.valueOf(dlFileRank.getFileEntryId())
					}, dlFileRank);
			}
		}

		return dlFileRank;
	}

	protected DLFileRank toUnwrappedModel(DLFileRank dlFileRank) {
		if (dlFileRank instanceof DLFileRankImpl) {
			return dlFileRank;
		}

		DLFileRankImpl dlFileRankImpl = new DLFileRankImpl();

		dlFileRankImpl.setNew(dlFileRank.isNew());
		dlFileRankImpl.setPrimaryKey(dlFileRank.getPrimaryKey());

		dlFileRankImpl.setFileRankId(dlFileRank.getFileRankId());
		dlFileRankImpl.setGroupId(dlFileRank.getGroupId());
		dlFileRankImpl.setCompanyId(dlFileRank.getCompanyId());
		dlFileRankImpl.setUserId(dlFileRank.getUserId());
		dlFileRankImpl.setCreateDate(dlFileRank.getCreateDate());
		dlFileRankImpl.setFileEntryId(dlFileRank.getFileEntryId());

		return dlFileRankImpl;
	}

	/**
	 * Returns the document library file rank with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the document library file rank
	 * @return the document library file rank
	 * @throws com.liferay.portal.NoSuchModelException if a document library file rank with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DLFileRank findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the document library file rank with the primary key or throws a {@link com.liferay.portlet.documentlibrary.NoSuchFileRankException} if it could not be found.
	 *
	 * @param fileRankId the primary key of the document library file rank
	 * @return the document library file rank
	 * @throws com.liferay.portlet.documentlibrary.NoSuchFileRankException if a document library file rank with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DLFileRank findByPrimaryKey(long fileRankId)
		throws NoSuchFileRankException, SystemException {
		DLFileRank dlFileRank = fetchByPrimaryKey(fileRankId);

		if (dlFileRank == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + fileRankId);
			}

			throw new NoSuchFileRankException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				fileRankId);
		}

		return dlFileRank;
	}

	/**
	 * Returns the document library file rank with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the document library file rank
	 * @return the document library file rank, or <code>null</code> if a document library file rank with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DLFileRank fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the document library file rank with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param fileRankId the primary key of the document library file rank
	 * @return the document library file rank, or <code>null</code> if a document library file rank with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DLFileRank fetchByPrimaryKey(long fileRankId)
		throws SystemException {
		DLFileRank dlFileRank = (DLFileRank)EntityCacheUtil.getResult(DLFileRankModelImpl.ENTITY_CACHE_ENABLED,
				DLFileRankImpl.class, fileRankId);

		if (dlFileRank == _nullDLFileRank) {
			return null;
		}

		if (dlFileRank == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				dlFileRank = (DLFileRank)session.get(DLFileRankImpl.class,
						Long.valueOf(fileRankId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (dlFileRank != null) {
					cacheResult(dlFileRank);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(DLFileRankModelImpl.ENTITY_CACHE_ENABLED,
						DLFileRankImpl.class, fileRankId, _nullDLFileRank);
				}

				closeSession(session);
			}
		}

		return dlFileRank;
	}

	/**
	 * Returns all the document library file ranks where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching document library file ranks
	 * @throws SystemException if a system exception occurred
	 */
	public List<DLFileRank> findByUserId(long userId) throws SystemException {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the document library file ranks where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of document library file ranks
	 * @param end the upper bound of the range of document library file ranks (not inclusive)
	 * @return the range of matching document library file ranks
	 * @throws SystemException if a system exception occurred
	 */
	public List<DLFileRank> findByUserId(long userId, int start, int end)
		throws SystemException {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the document library file ranks where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of document library file ranks
	 * @param end the upper bound of the range of document library file ranks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching document library file ranks
	 * @throws SystemException if a system exception occurred
	 */
	public List<DLFileRank> findByUserId(long userId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId, start, end, orderByComparator };
		}

		List<DLFileRank> list = (List<DLFileRank>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DLFILERANK_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DLFileRankModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				list = (List<DLFileRank>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first document library file rank in the ordered set where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching document library file rank
	 * @throws com.liferay.portlet.documentlibrary.NoSuchFileRankException if a matching document library file rank could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DLFileRank findByUserId_First(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchFileRankException, SystemException {
		List<DLFileRank> list = findByUserId(userId, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchFileRankException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last document library file rank in the ordered set where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching document library file rank
	 * @throws com.liferay.portlet.documentlibrary.NoSuchFileRankException if a matching document library file rank could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DLFileRank findByUserId_Last(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchFileRankException, SystemException {
		int count = countByUserId(userId);

		List<DLFileRank> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchFileRankException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the document library file ranks before and after the current document library file rank in the ordered set where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param fileRankId the primary key of the current document library file rank
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next document library file rank
	 * @throws com.liferay.portlet.documentlibrary.NoSuchFileRankException if a document library file rank with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DLFileRank[] findByUserId_PrevAndNext(long fileRankId, long userId,
		OrderByComparator orderByComparator)
		throws NoSuchFileRankException, SystemException {
		DLFileRank dlFileRank = findByPrimaryKey(fileRankId);

		Session session = null;

		try {
			session = openSession();

			DLFileRank[] array = new DLFileRankImpl[3];

			array[0] = getByUserId_PrevAndNext(session, dlFileRank, userId,
					orderByComparator, true);

			array[1] = dlFileRank;

			array[2] = getByUserId_PrevAndNext(session, dlFileRank, userId,
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

	protected DLFileRank getByUserId_PrevAndNext(Session session,
		DLFileRank dlFileRank, long userId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DLFILERANK_WHERE);

		query.append(_FINDER_COLUMN_USERID_USERID_2);

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

		else {
			query.append(DLFileRankModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dlFileRank);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DLFileRank> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the document library file ranks where fileEntryId = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @return the matching document library file ranks
	 * @throws SystemException if a system exception occurred
	 */
	public List<DLFileRank> findByFileEntryId(long fileEntryId)
		throws SystemException {
		return findByFileEntryId(fileEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the document library file ranks where fileEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param fileEntryId the file entry ID
	 * @param start the lower bound of the range of document library file ranks
	 * @param end the upper bound of the range of document library file ranks (not inclusive)
	 * @return the range of matching document library file ranks
	 * @throws SystemException if a system exception occurred
	 */
	public List<DLFileRank> findByFileEntryId(long fileEntryId, int start,
		int end) throws SystemException {
		return findByFileEntryId(fileEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the document library file ranks where fileEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param fileEntryId the file entry ID
	 * @param start the lower bound of the range of document library file ranks
	 * @param end the upper bound of the range of document library file ranks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching document library file ranks
	 * @throws SystemException if a system exception occurred
	 */
	public List<DLFileRank> findByFileEntryId(long fileEntryId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FILEENTRYID;
			finderArgs = new Object[] { fileEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_FILEENTRYID;
			finderArgs = new Object[] { fileEntryId, start, end, orderByComparator };
		}

		List<DLFileRank> list = (List<DLFileRank>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DLFILERANK_WHERE);

			query.append(_FINDER_COLUMN_FILEENTRYID_FILEENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DLFileRankModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(fileEntryId);

				list = (List<DLFileRank>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first document library file rank in the ordered set where fileEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param fileEntryId the file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching document library file rank
	 * @throws com.liferay.portlet.documentlibrary.NoSuchFileRankException if a matching document library file rank could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DLFileRank findByFileEntryId_First(long fileEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchFileRankException, SystemException {
		List<DLFileRank> list = findByFileEntryId(fileEntryId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("fileEntryId=");
			msg.append(fileEntryId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchFileRankException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last document library file rank in the ordered set where fileEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param fileEntryId the file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching document library file rank
	 * @throws com.liferay.portlet.documentlibrary.NoSuchFileRankException if a matching document library file rank could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DLFileRank findByFileEntryId_Last(long fileEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchFileRankException, SystemException {
		int count = countByFileEntryId(fileEntryId);

		List<DLFileRank> list = findByFileEntryId(fileEntryId, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("fileEntryId=");
			msg.append(fileEntryId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchFileRankException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the document library file ranks before and after the current document library file rank in the ordered set where fileEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param fileRankId the primary key of the current document library file rank
	 * @param fileEntryId the file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next document library file rank
	 * @throws com.liferay.portlet.documentlibrary.NoSuchFileRankException if a document library file rank with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DLFileRank[] findByFileEntryId_PrevAndNext(long fileRankId,
		long fileEntryId, OrderByComparator orderByComparator)
		throws NoSuchFileRankException, SystemException {
		DLFileRank dlFileRank = findByPrimaryKey(fileRankId);

		Session session = null;

		try {
			session = openSession();

			DLFileRank[] array = new DLFileRankImpl[3];

			array[0] = getByFileEntryId_PrevAndNext(session, dlFileRank,
					fileEntryId, orderByComparator, true);

			array[1] = dlFileRank;

			array[2] = getByFileEntryId_PrevAndNext(session, dlFileRank,
					fileEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DLFileRank getByFileEntryId_PrevAndNext(Session session,
		DLFileRank dlFileRank, long fileEntryId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DLFILERANK_WHERE);

		query.append(_FINDER_COLUMN_FILEENTRYID_FILEENTRYID_2);

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

		else {
			query.append(DLFileRankModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(fileEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dlFileRank);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DLFileRank> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the document library file ranks where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the matching document library file ranks
	 * @throws SystemException if a system exception occurred
	 */
	public List<DLFileRank> findByG_U(long groupId, long userId)
		throws SystemException {
		return findByG_U(groupId, userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the document library file ranks where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of document library file ranks
	 * @param end the upper bound of the range of document library file ranks (not inclusive)
	 * @return the range of matching document library file ranks
	 * @throws SystemException if a system exception occurred
	 */
	public List<DLFileRank> findByG_U(long groupId, long userId, int start,
		int end) throws SystemException {
		return findByG_U(groupId, userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the document library file ranks where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of document library file ranks
	 * @param end the upper bound of the range of document library file ranks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching document library file ranks
	 * @throws SystemException if a system exception occurred
	 */
	public List<DLFileRank> findByG_U(long groupId, long userId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U;
			finderArgs = new Object[] { groupId, userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_U;
			finderArgs = new Object[] {
					groupId, userId,
					
					start, end, orderByComparator
				};
		}

		List<DLFileRank> list = (List<DLFileRank>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_DLFILERANK_WHERE);

			query.append(_FINDER_COLUMN_G_U_GROUPID_2);

			query.append(_FINDER_COLUMN_G_U_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DLFileRankModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				list = (List<DLFileRank>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first document library file rank in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching document library file rank
	 * @throws com.liferay.portlet.documentlibrary.NoSuchFileRankException if a matching document library file rank could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DLFileRank findByG_U_First(long groupId, long userId,
		OrderByComparator orderByComparator)
		throws NoSuchFileRankException, SystemException {
		List<DLFileRank> list = findByG_U(groupId, userId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", userId=");
			msg.append(userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchFileRankException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last document library file rank in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching document library file rank
	 * @throws com.liferay.portlet.documentlibrary.NoSuchFileRankException if a matching document library file rank could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DLFileRank findByG_U_Last(long groupId, long userId,
		OrderByComparator orderByComparator)
		throws NoSuchFileRankException, SystemException {
		int count = countByG_U(groupId, userId);

		List<DLFileRank> list = findByG_U(groupId, userId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", userId=");
			msg.append(userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchFileRankException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the document library file ranks before and after the current document library file rank in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param fileRankId the primary key of the current document library file rank
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next document library file rank
	 * @throws com.liferay.portlet.documentlibrary.NoSuchFileRankException if a document library file rank with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DLFileRank[] findByG_U_PrevAndNext(long fileRankId, long groupId,
		long userId, OrderByComparator orderByComparator)
		throws NoSuchFileRankException, SystemException {
		DLFileRank dlFileRank = findByPrimaryKey(fileRankId);

		Session session = null;

		try {
			session = openSession();

			DLFileRank[] array = new DLFileRankImpl[3];

			array[0] = getByG_U_PrevAndNext(session, dlFileRank, groupId,
					userId, orderByComparator, true);

			array[1] = dlFileRank;

			array[2] = getByG_U_PrevAndNext(session, dlFileRank, groupId,
					userId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DLFileRank getByG_U_PrevAndNext(Session session,
		DLFileRank dlFileRank, long groupId, long userId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DLFILERANK_WHERE);

		query.append(_FINDER_COLUMN_G_U_GROUPID_2);

		query.append(_FINDER_COLUMN_G_U_USERID_2);

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

		else {
			query.append(DLFileRankModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dlFileRank);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DLFileRank> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the document library file rank where companyId = &#63; and userId = &#63; and fileEntryId = &#63; or throws a {@link com.liferay.portlet.documentlibrary.NoSuchFileRankException} if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param fileEntryId the file entry ID
	 * @return the matching document library file rank
	 * @throws com.liferay.portlet.documentlibrary.NoSuchFileRankException if a matching document library file rank could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DLFileRank findByC_U_F(long companyId, long userId, long fileEntryId)
		throws NoSuchFileRankException, SystemException {
		DLFileRank dlFileRank = fetchByC_U_F(companyId, userId, fileEntryId);

		if (dlFileRank == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", userId=");
			msg.append(userId);

			msg.append(", fileEntryId=");
			msg.append(fileEntryId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchFileRankException(msg.toString());
		}

		return dlFileRank;
	}

	/**
	 * Returns the document library file rank where companyId = &#63; and userId = &#63; and fileEntryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param fileEntryId the file entry ID
	 * @return the matching document library file rank, or <code>null</code> if a matching document library file rank could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DLFileRank fetchByC_U_F(long companyId, long userId, long fileEntryId)
		throws SystemException {
		return fetchByC_U_F(companyId, userId, fileEntryId, true);
	}

	/**
	 * Returns the document library file rank where companyId = &#63; and userId = &#63; and fileEntryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param fileEntryId the file entry ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching document library file rank, or <code>null</code> if a matching document library file rank could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DLFileRank fetchByC_U_F(long companyId, long userId,
		long fileEntryId, boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { companyId, userId, fileEntryId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_C_U_F,
					finderArgs, this);
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_DLFILERANK_WHERE);

			query.append(_FINDER_COLUMN_C_U_F_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_U_F_USERID_2);

			query.append(_FINDER_COLUMN_C_U_F_FILEENTRYID_2);

			query.append(DLFileRankModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(userId);

				qPos.add(fileEntryId);

				List<DLFileRank> list = q.list();

				result = list;

				DLFileRank dlFileRank = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_U_F,
						finderArgs, list);
				}
				else {
					dlFileRank = list.get(0);

					cacheResult(dlFileRank);

					if ((dlFileRank.getCompanyId() != companyId) ||
							(dlFileRank.getUserId() != userId) ||
							(dlFileRank.getFileEntryId() != fileEntryId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_U_F,
							finderArgs, dlFileRank);
					}
				}

				return dlFileRank;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_U_F,
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
				return (DLFileRank)result;
			}
		}
	}

	/**
	 * Returns all the document library file ranks.
	 *
	 * @return the document library file ranks
	 * @throws SystemException if a system exception occurred
	 */
	public List<DLFileRank> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the document library file ranks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of document library file ranks
	 * @param end the upper bound of the range of document library file ranks (not inclusive)
	 * @return the range of document library file ranks
	 * @throws SystemException if a system exception occurred
	 */
	public List<DLFileRank> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the document library file ranks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of document library file ranks
	 * @param end the upper bound of the range of document library file ranks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of document library file ranks
	 * @throws SystemException if a system exception occurred
	 */
	public List<DLFileRank> findAll(int start, int end,
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

		List<DLFileRank> list = (List<DLFileRank>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DLFILERANK);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DLFILERANK.concat(DLFileRankModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<DLFileRank>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<DLFileRank>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes all the document library file ranks where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUserId(long userId) throws SystemException {
		for (DLFileRank dlFileRank : findByUserId(userId)) {
			remove(dlFileRank);
		}
	}

	/**
	 * Removes all the document library file ranks where fileEntryId = &#63; from the database.
	 *
	 * @param fileEntryId the file entry ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByFileEntryId(long fileEntryId) throws SystemException {
		for (DLFileRank dlFileRank : findByFileEntryId(fileEntryId)) {
			remove(dlFileRank);
		}
	}

	/**
	 * Removes all the document library file ranks where groupId = &#63; and userId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByG_U(long groupId, long userId)
		throws SystemException {
		for (DLFileRank dlFileRank : findByG_U(groupId, userId)) {
			remove(dlFileRank);
		}
	}

	/**
	 * Removes the document library file rank where companyId = &#63; and userId = &#63; and fileEntryId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param fileEntryId the file entry ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByC_U_F(long companyId, long userId, long fileEntryId)
		throws NoSuchFileRankException, SystemException {
		DLFileRank dlFileRank = findByC_U_F(companyId, userId, fileEntryId);

		remove(dlFileRank);
	}

	/**
	 * Removes all the document library file ranks from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DLFileRank dlFileRank : findAll()) {
			remove(dlFileRank);
		}
	}

	/**
	 * Returns the number of document library file ranks where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching document library file ranks
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUserId(long userId) throws SystemException {
		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DLFILERANK_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of document library file ranks where fileEntryId = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @return the number of matching document library file ranks
	 * @throws SystemException if a system exception occurred
	 */
	public int countByFileEntryId(long fileEntryId) throws SystemException {
		Object[] finderArgs = new Object[] { fileEntryId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_FILEENTRYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DLFILERANK_WHERE);

			query.append(_FINDER_COLUMN_FILEENTRYID_FILEENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(fileEntryId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_FILEENTRYID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of document library file ranks where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the number of matching document library file ranks
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_U(long groupId, long userId) throws SystemException {
		Object[] finderArgs = new Object[] { groupId, userId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_U,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DLFILERANK_WHERE);

			query.append(_FINDER_COLUMN_G_U_GROUPID_2);

			query.append(_FINDER_COLUMN_G_U_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_U, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of document library file ranks where companyId = &#63; and userId = &#63; and fileEntryId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param fileEntryId the file entry ID
	 * @return the number of matching document library file ranks
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_U_F(long companyId, long userId, long fileEntryId)
		throws SystemException {
		Object[] finderArgs = new Object[] { companyId, userId, fileEntryId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_U_F,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_DLFILERANK_WHERE);

			query.append(_FINDER_COLUMN_C_U_F_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_U_F_USERID_2);

			query.append(_FINDER_COLUMN_C_U_F_FILEENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(userId);

				qPos.add(fileEntryId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_U_F,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of document library file ranks.
	 *
	 * @return the number of document library file ranks
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_DLFILERANK);

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
	 * Initializes the document library file rank persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.portal.util.PropsUtil.get(
						"value.object.listener.com.liferay.portlet.documentlibrary.model.DLFileRank")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<DLFileRank>> listenersList = new ArrayList<ModelListener<DLFileRank>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<DLFileRank>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(DLFileRankImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = DLContentPersistence.class)
	protected DLContentPersistence dlContentPersistence;
	@BeanReference(type = DLFileEntryPersistence.class)
	protected DLFileEntryPersistence dlFileEntryPersistence;
	@BeanReference(type = DLFileEntryMetadataPersistence.class)
	protected DLFileEntryMetadataPersistence dlFileEntryMetadataPersistence;
	@BeanReference(type = DLFileEntryTypePersistence.class)
	protected DLFileEntryTypePersistence dlFileEntryTypePersistence;
	@BeanReference(type = DLFileRankPersistence.class)
	protected DLFileRankPersistence dlFileRankPersistence;
	@BeanReference(type = DLFileShortcutPersistence.class)
	protected DLFileShortcutPersistence dlFileShortcutPersistence;
	@BeanReference(type = DLFileVersionPersistence.class)
	protected DLFileVersionPersistence dlFileVersionPersistence;
	@BeanReference(type = DLFolderPersistence.class)
	protected DLFolderPersistence dlFolderPersistence;
	@BeanReference(type = DLSyncPersistence.class)
	protected DLSyncPersistence dlSyncPersistence;
	@BeanReference(type = LayoutPersistence.class)
	protected LayoutPersistence layoutPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_DLFILERANK = "SELECT dlFileRank FROM DLFileRank dlFileRank";
	private static final String _SQL_SELECT_DLFILERANK_WHERE = "SELECT dlFileRank FROM DLFileRank dlFileRank WHERE ";
	private static final String _SQL_COUNT_DLFILERANK = "SELECT COUNT(dlFileRank) FROM DLFileRank dlFileRank";
	private static final String _SQL_COUNT_DLFILERANK_WHERE = "SELECT COUNT(dlFileRank) FROM DLFileRank dlFileRank WHERE ";
	private static final String _FINDER_COLUMN_USERID_USERID_2 = "dlFileRank.userId = ?";
	private static final String _FINDER_COLUMN_FILEENTRYID_FILEENTRYID_2 = "dlFileRank.fileEntryId = ?";
	private static final String _FINDER_COLUMN_G_U_GROUPID_2 = "dlFileRank.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_U_USERID_2 = "dlFileRank.userId = ?";
	private static final String _FINDER_COLUMN_C_U_F_COMPANYID_2 = "dlFileRank.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_U_F_USERID_2 = "dlFileRank.userId = ? AND ";
	private static final String _FINDER_COLUMN_C_U_F_FILEENTRYID_2 = "dlFileRank.fileEntryId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dlFileRank.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DLFileRank exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DLFileRank exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = com.liferay.portal.util.PropsValues.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE;
	private static Log _log = LogFactoryUtil.getLog(DLFileRankPersistenceImpl.class);
	private static DLFileRank _nullDLFileRank = new DLFileRankImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<DLFileRank> toCacheModel() {
				return _nullDLFileRankCacheModel;
			}
		};

	private static CacheModel<DLFileRank> _nullDLFileRankCacheModel = new CacheModel<DLFileRank>() {
			public DLFileRank toEntityModel() {
				return _nullDLFileRank;
			}
		};
}