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
import com.liferay.portal.NoSuchWorkflowDefinitionLinkException;
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
import com.liferay.portal.model.WorkflowDefinitionLink;
import com.liferay.portal.model.impl.WorkflowDefinitionLinkImpl;
import com.liferay.portal.model.impl.WorkflowDefinitionLinkModelImpl;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the workflow definition link service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WorkflowDefinitionLinkPersistence
 * @see WorkflowDefinitionLinkUtil
 * @generated
 */
public class WorkflowDefinitionLinkPersistenceImpl extends BasePersistenceImpl<WorkflowDefinitionLink>
	implements WorkflowDefinitionLinkPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link WorkflowDefinitionLinkUtil} to access the workflow definition link persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = WorkflowDefinitionLinkImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(WorkflowDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowDefinitionLinkModelImpl.FINDER_CACHE_ENABLED,
			WorkflowDefinitionLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(WorkflowDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowDefinitionLinkModelImpl.FINDER_CACHE_ENABLED,
			WorkflowDefinitionLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] { Long.class.getName() },
			WorkflowDefinitionLinkModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(WorkflowDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowDefinitionLinkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_W_W = new FinderPath(WorkflowDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowDefinitionLinkModelImpl.FINDER_CACHE_ENABLED,
			WorkflowDefinitionLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_W_W",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_W_W = new FinderPath(WorkflowDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowDefinitionLinkModelImpl.FINDER_CACHE_ENABLED,
			WorkflowDefinitionLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_W_W",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName()
			},
			WorkflowDefinitionLinkModelImpl.COMPANYID_COLUMN_BITMASK |
			WorkflowDefinitionLinkModelImpl.WORKFLOWDEFINITIONNAME_COLUMN_BITMASK |
			WorkflowDefinitionLinkModelImpl.WORKFLOWDEFINITIONVERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_W_W = new FinderPath(WorkflowDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowDefinitionLinkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_W_W",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_FETCH_BY_G_C_C_C_T = new FinderPath(WorkflowDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowDefinitionLinkModelImpl.FINDER_CACHE_ENABLED,
			WorkflowDefinitionLinkImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByG_C_C_C_T",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Long.class.getName(), Long.class.getName()
			},
			WorkflowDefinitionLinkModelImpl.GROUPID_COLUMN_BITMASK |
			WorkflowDefinitionLinkModelImpl.COMPANYID_COLUMN_BITMASK |
			WorkflowDefinitionLinkModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			WorkflowDefinitionLinkModelImpl.CLASSPK_COLUMN_BITMASK |
			WorkflowDefinitionLinkModelImpl.TYPEPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_C_C_C_T = new FinderPath(WorkflowDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowDefinitionLinkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_C_C_C_T",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Long.class.getName(), Long.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(WorkflowDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowDefinitionLinkModelImpl.FINDER_CACHE_ENABLED,
			WorkflowDefinitionLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(WorkflowDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowDefinitionLinkModelImpl.FINDER_CACHE_ENABLED,
			WorkflowDefinitionLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WorkflowDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowDefinitionLinkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the workflow definition link in the entity cache if it is enabled.
	 *
	 * @param workflowDefinitionLink the workflow definition link
	 */
	public void cacheResult(WorkflowDefinitionLink workflowDefinitionLink) {
		EntityCacheUtil.putResult(WorkflowDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowDefinitionLinkImpl.class,
			workflowDefinitionLink.getPrimaryKey(), workflowDefinitionLink);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_C_C_C_T,
			new Object[] {
				Long.valueOf(workflowDefinitionLink.getGroupId()),
				Long.valueOf(workflowDefinitionLink.getCompanyId()),
				Long.valueOf(workflowDefinitionLink.getClassNameId()),
				Long.valueOf(workflowDefinitionLink.getClassPK()),
				Long.valueOf(workflowDefinitionLink.getTypePK())
			}, workflowDefinitionLink);

		workflowDefinitionLink.resetOriginalValues();
	}

	/**
	 * Caches the workflow definition links in the entity cache if it is enabled.
	 *
	 * @param workflowDefinitionLinks the workflow definition links
	 */
	public void cacheResult(
		List<WorkflowDefinitionLink> workflowDefinitionLinks) {
		for (WorkflowDefinitionLink workflowDefinitionLink : workflowDefinitionLinks) {
			if (EntityCacheUtil.getResult(
						WorkflowDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
						WorkflowDefinitionLinkImpl.class,
						workflowDefinitionLink.getPrimaryKey()) == null) {
				cacheResult(workflowDefinitionLink);
			}
			else {
				workflowDefinitionLink.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all workflow definition links.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(WorkflowDefinitionLinkImpl.class.getName());
		}

		EntityCacheUtil.clearCache(WorkflowDefinitionLinkImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the workflow definition link.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WorkflowDefinitionLink workflowDefinitionLink) {
		EntityCacheUtil.removeResult(WorkflowDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowDefinitionLinkImpl.class,
			workflowDefinitionLink.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(workflowDefinitionLink);
	}

	@Override
	public void clearCache(List<WorkflowDefinitionLink> workflowDefinitionLinks) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WorkflowDefinitionLink workflowDefinitionLink : workflowDefinitionLinks) {
			EntityCacheUtil.removeResult(WorkflowDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
				WorkflowDefinitionLinkImpl.class,
				workflowDefinitionLink.getPrimaryKey());

			clearUniqueFindersCache(workflowDefinitionLink);
		}
	}

	protected void clearUniqueFindersCache(
		WorkflowDefinitionLink workflowDefinitionLink) {
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_C_C_C_T,
			new Object[] {
				Long.valueOf(workflowDefinitionLink.getGroupId()),
				Long.valueOf(workflowDefinitionLink.getCompanyId()),
				Long.valueOf(workflowDefinitionLink.getClassNameId()),
				Long.valueOf(workflowDefinitionLink.getClassPK()),
				Long.valueOf(workflowDefinitionLink.getTypePK())
			});
	}

	/**
	 * Creates a new workflow definition link with the primary key. Does not add the workflow definition link to the database.
	 *
	 * @param workflowDefinitionLinkId the primary key for the new workflow definition link
	 * @return the new workflow definition link
	 */
	public WorkflowDefinitionLink create(long workflowDefinitionLinkId) {
		WorkflowDefinitionLink workflowDefinitionLink = new WorkflowDefinitionLinkImpl();

		workflowDefinitionLink.setNew(true);
		workflowDefinitionLink.setPrimaryKey(workflowDefinitionLinkId);

		return workflowDefinitionLink;
	}

	/**
	 * Removes the workflow definition link with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param workflowDefinitionLinkId the primary key of the workflow definition link
	 * @return the workflow definition link that was removed
	 * @throws com.liferay.portal.NoSuchWorkflowDefinitionLinkException if a workflow definition link with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WorkflowDefinitionLink remove(long workflowDefinitionLinkId)
		throws NoSuchWorkflowDefinitionLinkException, SystemException {
		return remove(Long.valueOf(workflowDefinitionLinkId));
	}

	/**
	 * Removes the workflow definition link with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the workflow definition link
	 * @return the workflow definition link that was removed
	 * @throws com.liferay.portal.NoSuchWorkflowDefinitionLinkException if a workflow definition link with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowDefinitionLink remove(Serializable primaryKey)
		throws NoSuchWorkflowDefinitionLinkException, SystemException {
		Session session = null;

		try {
			session = openSession();

			WorkflowDefinitionLink workflowDefinitionLink = (WorkflowDefinitionLink)session.get(WorkflowDefinitionLinkImpl.class,
					primaryKey);

			if (workflowDefinitionLink == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchWorkflowDefinitionLinkException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(workflowDefinitionLink);
		}
		catch (NoSuchWorkflowDefinitionLinkException nsee) {
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
	protected WorkflowDefinitionLink removeImpl(
		WorkflowDefinitionLink workflowDefinitionLink)
		throws SystemException {
		workflowDefinitionLink = toUnwrappedModel(workflowDefinitionLink);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, workflowDefinitionLink);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(workflowDefinitionLink);

		return workflowDefinitionLink;
	}

	@Override
	public WorkflowDefinitionLink updateImpl(
		com.liferay.portal.model.WorkflowDefinitionLink workflowDefinitionLink,
		boolean merge) throws SystemException {
		workflowDefinitionLink = toUnwrappedModel(workflowDefinitionLink);

		boolean isNew = workflowDefinitionLink.isNew();

		WorkflowDefinitionLinkModelImpl workflowDefinitionLinkModelImpl = (WorkflowDefinitionLinkModelImpl)workflowDefinitionLink;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, workflowDefinitionLink, merge);

			workflowDefinitionLink.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !WorkflowDefinitionLinkModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((workflowDefinitionLinkModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(workflowDefinitionLinkModelImpl.getOriginalCompanyId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] {
						Long.valueOf(workflowDefinitionLinkModelImpl.getCompanyId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}

			if ((workflowDefinitionLinkModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_W_W.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(workflowDefinitionLinkModelImpl.getOriginalCompanyId()),
						
						workflowDefinitionLinkModelImpl.getOriginalWorkflowDefinitionName(),
						Integer.valueOf(workflowDefinitionLinkModelImpl.getOriginalWorkflowDefinitionVersion())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_W_W, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_W_W,
					args);

				args = new Object[] {
						Long.valueOf(workflowDefinitionLinkModelImpl.getCompanyId()),
						
						workflowDefinitionLinkModelImpl.getWorkflowDefinitionName(),
						Integer.valueOf(workflowDefinitionLinkModelImpl.getWorkflowDefinitionVersion())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_W_W, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_W_W,
					args);
			}
		}

		EntityCacheUtil.putResult(WorkflowDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowDefinitionLinkImpl.class,
			workflowDefinitionLink.getPrimaryKey(), workflowDefinitionLink);

		if (isNew) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_C_C_C_T,
				new Object[] {
					Long.valueOf(workflowDefinitionLink.getGroupId()),
					Long.valueOf(workflowDefinitionLink.getCompanyId()),
					Long.valueOf(workflowDefinitionLink.getClassNameId()),
					Long.valueOf(workflowDefinitionLink.getClassPK()),
					Long.valueOf(workflowDefinitionLink.getTypePK())
				}, workflowDefinitionLink);
		}
		else {
			if ((workflowDefinitionLinkModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_G_C_C_C_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(workflowDefinitionLinkModelImpl.getOriginalGroupId()),
						Long.valueOf(workflowDefinitionLinkModelImpl.getOriginalCompanyId()),
						Long.valueOf(workflowDefinitionLinkModelImpl.getOriginalClassNameId()),
						Long.valueOf(workflowDefinitionLinkModelImpl.getOriginalClassPK()),
						Long.valueOf(workflowDefinitionLinkModelImpl.getOriginalTypePK())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_C_C_C_T,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_C_C_C_T,
					args);

				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_C_C_C_T,
					new Object[] {
						Long.valueOf(workflowDefinitionLink.getGroupId()),
						Long.valueOf(workflowDefinitionLink.getCompanyId()),
						Long.valueOf(workflowDefinitionLink.getClassNameId()),
						Long.valueOf(workflowDefinitionLink.getClassPK()),
						Long.valueOf(workflowDefinitionLink.getTypePK())
					}, workflowDefinitionLink);
			}
		}

		return workflowDefinitionLink;
	}

	protected WorkflowDefinitionLink toUnwrappedModel(
		WorkflowDefinitionLink workflowDefinitionLink) {
		if (workflowDefinitionLink instanceof WorkflowDefinitionLinkImpl) {
			return workflowDefinitionLink;
		}

		WorkflowDefinitionLinkImpl workflowDefinitionLinkImpl = new WorkflowDefinitionLinkImpl();

		workflowDefinitionLinkImpl.setNew(workflowDefinitionLink.isNew());
		workflowDefinitionLinkImpl.setPrimaryKey(workflowDefinitionLink.getPrimaryKey());

		workflowDefinitionLinkImpl.setWorkflowDefinitionLinkId(workflowDefinitionLink.getWorkflowDefinitionLinkId());
		workflowDefinitionLinkImpl.setGroupId(workflowDefinitionLink.getGroupId());
		workflowDefinitionLinkImpl.setCompanyId(workflowDefinitionLink.getCompanyId());
		workflowDefinitionLinkImpl.setUserId(workflowDefinitionLink.getUserId());
		workflowDefinitionLinkImpl.setUserName(workflowDefinitionLink.getUserName());
		workflowDefinitionLinkImpl.setCreateDate(workflowDefinitionLink.getCreateDate());
		workflowDefinitionLinkImpl.setModifiedDate(workflowDefinitionLink.getModifiedDate());
		workflowDefinitionLinkImpl.setClassNameId(workflowDefinitionLink.getClassNameId());
		workflowDefinitionLinkImpl.setClassPK(workflowDefinitionLink.getClassPK());
		workflowDefinitionLinkImpl.setTypePK(workflowDefinitionLink.getTypePK());
		workflowDefinitionLinkImpl.setWorkflowDefinitionName(workflowDefinitionLink.getWorkflowDefinitionName());
		workflowDefinitionLinkImpl.setWorkflowDefinitionVersion(workflowDefinitionLink.getWorkflowDefinitionVersion());

		return workflowDefinitionLinkImpl;
	}

	/**
	 * Returns the workflow definition link with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the workflow definition link
	 * @return the workflow definition link
	 * @throws com.liferay.portal.NoSuchModelException if a workflow definition link with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowDefinitionLink findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the workflow definition link with the primary key or throws a {@link com.liferay.portal.NoSuchWorkflowDefinitionLinkException} if it could not be found.
	 *
	 * @param workflowDefinitionLinkId the primary key of the workflow definition link
	 * @return the workflow definition link
	 * @throws com.liferay.portal.NoSuchWorkflowDefinitionLinkException if a workflow definition link with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WorkflowDefinitionLink findByPrimaryKey(
		long workflowDefinitionLinkId)
		throws NoSuchWorkflowDefinitionLinkException, SystemException {
		WorkflowDefinitionLink workflowDefinitionLink = fetchByPrimaryKey(workflowDefinitionLinkId);

		if (workflowDefinitionLink == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					workflowDefinitionLinkId);
			}

			throw new NoSuchWorkflowDefinitionLinkException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				workflowDefinitionLinkId);
		}

		return workflowDefinitionLink;
	}

	/**
	 * Returns the workflow definition link with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the workflow definition link
	 * @return the workflow definition link, or <code>null</code> if a workflow definition link with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorkflowDefinitionLink fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the workflow definition link with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param workflowDefinitionLinkId the primary key of the workflow definition link
	 * @return the workflow definition link, or <code>null</code> if a workflow definition link with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WorkflowDefinitionLink fetchByPrimaryKey(
		long workflowDefinitionLinkId) throws SystemException {
		WorkflowDefinitionLink workflowDefinitionLink = (WorkflowDefinitionLink)EntityCacheUtil.getResult(WorkflowDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
				WorkflowDefinitionLinkImpl.class, workflowDefinitionLinkId);

		if (workflowDefinitionLink == _nullWorkflowDefinitionLink) {
			return null;
		}

		if (workflowDefinitionLink == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				workflowDefinitionLink = (WorkflowDefinitionLink)session.get(WorkflowDefinitionLinkImpl.class,
						Long.valueOf(workflowDefinitionLinkId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (workflowDefinitionLink != null) {
					cacheResult(workflowDefinitionLink);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(WorkflowDefinitionLinkModelImpl.ENTITY_CACHE_ENABLED,
						WorkflowDefinitionLinkImpl.class,
						workflowDefinitionLinkId, _nullWorkflowDefinitionLink);
				}

				closeSession(session);
			}
		}

		return workflowDefinitionLink;
	}

	/**
	 * Returns all the workflow definition links where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching workflow definition links
	 * @throws SystemException if a system exception occurred
	 */
	public List<WorkflowDefinitionLink> findByCompanyId(long companyId)
		throws SystemException {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the workflow definition links where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of workflow definition links
	 * @param end the upper bound of the range of workflow definition links (not inclusive)
	 * @return the range of matching workflow definition links
	 * @throws SystemException if a system exception occurred
	 */
	public List<WorkflowDefinitionLink> findByCompanyId(long companyId,
		int start, int end) throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the workflow definition links where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of workflow definition links
	 * @param end the upper bound of the range of workflow definition links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching workflow definition links
	 * @throws SystemException if a system exception occurred
	 */
	public List<WorkflowDefinitionLink> findByCompanyId(long companyId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID;
			finderArgs = new Object[] { companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID;
			finderArgs = new Object[] { companyId, start, end, orderByComparator };
		}

		List<WorkflowDefinitionLink> list = (List<WorkflowDefinitionLink>)FinderCacheUtil.getResult(finderPath,
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

			query.append(_SQL_SELECT_WORKFLOWDEFINITIONLINK_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(WorkflowDefinitionLinkModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = (List<WorkflowDefinitionLink>)QueryUtil.list(q,
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
	 * Returns the first workflow definition link in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching workflow definition link
	 * @throws com.liferay.portal.NoSuchWorkflowDefinitionLinkException if a matching workflow definition link could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WorkflowDefinitionLink findByCompanyId_First(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchWorkflowDefinitionLinkException, SystemException {
		List<WorkflowDefinitionLink> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWorkflowDefinitionLinkException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last workflow definition link in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching workflow definition link
	 * @throws com.liferay.portal.NoSuchWorkflowDefinitionLinkException if a matching workflow definition link could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WorkflowDefinitionLink findByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchWorkflowDefinitionLinkException, SystemException {
		int count = countByCompanyId(companyId);

		List<WorkflowDefinitionLink> list = findByCompanyId(companyId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWorkflowDefinitionLinkException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the workflow definition links before and after the current workflow definition link in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param workflowDefinitionLinkId the primary key of the current workflow definition link
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next workflow definition link
	 * @throws com.liferay.portal.NoSuchWorkflowDefinitionLinkException if a workflow definition link with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WorkflowDefinitionLink[] findByCompanyId_PrevAndNext(
		long workflowDefinitionLinkId, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchWorkflowDefinitionLinkException, SystemException {
		WorkflowDefinitionLink workflowDefinitionLink = findByPrimaryKey(workflowDefinitionLinkId);

		Session session = null;

		try {
			session = openSession();

			WorkflowDefinitionLink[] array = new WorkflowDefinitionLinkImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session,
					workflowDefinitionLink, companyId, orderByComparator, true);

			array[1] = workflowDefinitionLink;

			array[2] = getByCompanyId_PrevAndNext(session,
					workflowDefinitionLink, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected WorkflowDefinitionLink getByCompanyId_PrevAndNext(
		Session session, WorkflowDefinitionLink workflowDefinitionLink,
		long companyId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_WORKFLOWDEFINITIONLINK_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

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
			query.append(WorkflowDefinitionLinkModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(workflowDefinitionLink);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WorkflowDefinitionLink> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the workflow definition links where companyId = &#63; and workflowDefinitionName = &#63; and workflowDefinitionVersion = &#63;.
	 *
	 * @param companyId the company ID
	 * @param workflowDefinitionName the workflow definition name
	 * @param workflowDefinitionVersion the workflow definition version
	 * @return the matching workflow definition links
	 * @throws SystemException if a system exception occurred
	 */
	public List<WorkflowDefinitionLink> findByC_W_W(long companyId,
		String workflowDefinitionName, int workflowDefinitionVersion)
		throws SystemException {
		return findByC_W_W(companyId, workflowDefinitionName,
			workflowDefinitionVersion, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the workflow definition links where companyId = &#63; and workflowDefinitionName = &#63; and workflowDefinitionVersion = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param workflowDefinitionName the workflow definition name
	 * @param workflowDefinitionVersion the workflow definition version
	 * @param start the lower bound of the range of workflow definition links
	 * @param end the upper bound of the range of workflow definition links (not inclusive)
	 * @return the range of matching workflow definition links
	 * @throws SystemException if a system exception occurred
	 */
	public List<WorkflowDefinitionLink> findByC_W_W(long companyId,
		String workflowDefinitionName, int workflowDefinitionVersion,
		int start, int end) throws SystemException {
		return findByC_W_W(companyId, workflowDefinitionName,
			workflowDefinitionVersion, start, end, null);
	}

	/**
	 * Returns an ordered range of all the workflow definition links where companyId = &#63; and workflowDefinitionName = &#63; and workflowDefinitionVersion = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param workflowDefinitionName the workflow definition name
	 * @param workflowDefinitionVersion the workflow definition version
	 * @param start the lower bound of the range of workflow definition links
	 * @param end the upper bound of the range of workflow definition links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching workflow definition links
	 * @throws SystemException if a system exception occurred
	 */
	public List<WorkflowDefinitionLink> findByC_W_W(long companyId,
		String workflowDefinitionName, int workflowDefinitionVersion,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_W_W;
			finderArgs = new Object[] {
					companyId, workflowDefinitionName, workflowDefinitionVersion
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_W_W;
			finderArgs = new Object[] {
					companyId, workflowDefinitionName, workflowDefinitionVersion,
					
					start, end, orderByComparator
				};
		}

		List<WorkflowDefinitionLink> list = (List<WorkflowDefinitionLink>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_WORKFLOWDEFINITIONLINK_WHERE);

			query.append(_FINDER_COLUMN_C_W_W_COMPANYID_2);

			if (workflowDefinitionName == null) {
				query.append(_FINDER_COLUMN_C_W_W_WORKFLOWDEFINITIONNAME_1);
			}
			else {
				if (workflowDefinitionName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_C_W_W_WORKFLOWDEFINITIONNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_C_W_W_WORKFLOWDEFINITIONNAME_2);
				}
			}

			query.append(_FINDER_COLUMN_C_W_W_WORKFLOWDEFINITIONVERSION_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(WorkflowDefinitionLinkModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (workflowDefinitionName != null) {
					qPos.add(workflowDefinitionName);
				}

				qPos.add(workflowDefinitionVersion);

				list = (List<WorkflowDefinitionLink>)QueryUtil.list(q,
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
	 * Returns the first workflow definition link in the ordered set where companyId = &#63; and workflowDefinitionName = &#63; and workflowDefinitionVersion = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param workflowDefinitionName the workflow definition name
	 * @param workflowDefinitionVersion the workflow definition version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching workflow definition link
	 * @throws com.liferay.portal.NoSuchWorkflowDefinitionLinkException if a matching workflow definition link could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WorkflowDefinitionLink findByC_W_W_First(long companyId,
		String workflowDefinitionName, int workflowDefinitionVersion,
		OrderByComparator orderByComparator)
		throws NoSuchWorkflowDefinitionLinkException, SystemException {
		List<WorkflowDefinitionLink> list = findByC_W_W(companyId,
				workflowDefinitionName, workflowDefinitionVersion, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", workflowDefinitionName=");
			msg.append(workflowDefinitionName);

			msg.append(", workflowDefinitionVersion=");
			msg.append(workflowDefinitionVersion);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWorkflowDefinitionLinkException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last workflow definition link in the ordered set where companyId = &#63; and workflowDefinitionName = &#63; and workflowDefinitionVersion = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param workflowDefinitionName the workflow definition name
	 * @param workflowDefinitionVersion the workflow definition version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching workflow definition link
	 * @throws com.liferay.portal.NoSuchWorkflowDefinitionLinkException if a matching workflow definition link could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WorkflowDefinitionLink findByC_W_W_Last(long companyId,
		String workflowDefinitionName, int workflowDefinitionVersion,
		OrderByComparator orderByComparator)
		throws NoSuchWorkflowDefinitionLinkException, SystemException {
		int count = countByC_W_W(companyId, workflowDefinitionName,
				workflowDefinitionVersion);

		List<WorkflowDefinitionLink> list = findByC_W_W(companyId,
				workflowDefinitionName, workflowDefinitionVersion, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", workflowDefinitionName=");
			msg.append(workflowDefinitionName);

			msg.append(", workflowDefinitionVersion=");
			msg.append(workflowDefinitionVersion);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWorkflowDefinitionLinkException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the workflow definition links before and after the current workflow definition link in the ordered set where companyId = &#63; and workflowDefinitionName = &#63; and workflowDefinitionVersion = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param workflowDefinitionLinkId the primary key of the current workflow definition link
	 * @param companyId the company ID
	 * @param workflowDefinitionName the workflow definition name
	 * @param workflowDefinitionVersion the workflow definition version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next workflow definition link
	 * @throws com.liferay.portal.NoSuchWorkflowDefinitionLinkException if a workflow definition link with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WorkflowDefinitionLink[] findByC_W_W_PrevAndNext(
		long workflowDefinitionLinkId, long companyId,
		String workflowDefinitionName, int workflowDefinitionVersion,
		OrderByComparator orderByComparator)
		throws NoSuchWorkflowDefinitionLinkException, SystemException {
		WorkflowDefinitionLink workflowDefinitionLink = findByPrimaryKey(workflowDefinitionLinkId);

		Session session = null;

		try {
			session = openSession();

			WorkflowDefinitionLink[] array = new WorkflowDefinitionLinkImpl[3];

			array[0] = getByC_W_W_PrevAndNext(session, workflowDefinitionLink,
					companyId, workflowDefinitionName,
					workflowDefinitionVersion, orderByComparator, true);

			array[1] = workflowDefinitionLink;

			array[2] = getByC_W_W_PrevAndNext(session, workflowDefinitionLink,
					companyId, workflowDefinitionName,
					workflowDefinitionVersion, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected WorkflowDefinitionLink getByC_W_W_PrevAndNext(Session session,
		WorkflowDefinitionLink workflowDefinitionLink, long companyId,
		String workflowDefinitionName, int workflowDefinitionVersion,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_WORKFLOWDEFINITIONLINK_WHERE);

		query.append(_FINDER_COLUMN_C_W_W_COMPANYID_2);

		if (workflowDefinitionName == null) {
			query.append(_FINDER_COLUMN_C_W_W_WORKFLOWDEFINITIONNAME_1);
		}
		else {
			if (workflowDefinitionName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_W_W_WORKFLOWDEFINITIONNAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_C_W_W_WORKFLOWDEFINITIONNAME_2);
			}
		}

		query.append(_FINDER_COLUMN_C_W_W_WORKFLOWDEFINITIONVERSION_2);

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
			query.append(WorkflowDefinitionLinkModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (workflowDefinitionName != null) {
			qPos.add(workflowDefinitionName);
		}

		qPos.add(workflowDefinitionVersion);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(workflowDefinitionLink);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WorkflowDefinitionLink> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the workflow definition link where groupId = &#63; and companyId = &#63; and classNameId = &#63; and classPK = &#63; and typePK = &#63; or throws a {@link com.liferay.portal.NoSuchWorkflowDefinitionLinkException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param typePK the type p k
	 * @return the matching workflow definition link
	 * @throws com.liferay.portal.NoSuchWorkflowDefinitionLinkException if a matching workflow definition link could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WorkflowDefinitionLink findByG_C_C_C_T(long groupId, long companyId,
		long classNameId, long classPK, long typePK)
		throws NoSuchWorkflowDefinitionLinkException, SystemException {
		WorkflowDefinitionLink workflowDefinitionLink = fetchByG_C_C_C_T(groupId,
				companyId, classNameId, classPK, typePK);

		if (workflowDefinitionLink == null) {
			StringBundler msg = new StringBundler(12);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", companyId=");
			msg.append(companyId);

			msg.append(", classNameId=");
			msg.append(classNameId);

			msg.append(", classPK=");
			msg.append(classPK);

			msg.append(", typePK=");
			msg.append(typePK);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchWorkflowDefinitionLinkException(msg.toString());
		}

		return workflowDefinitionLink;
	}

	/**
	 * Returns the workflow definition link where groupId = &#63; and companyId = &#63; and classNameId = &#63; and classPK = &#63; and typePK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param typePK the type p k
	 * @return the matching workflow definition link, or <code>null</code> if a matching workflow definition link could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WorkflowDefinitionLink fetchByG_C_C_C_T(long groupId,
		long companyId, long classNameId, long classPK, long typePK)
		throws SystemException {
		return fetchByG_C_C_C_T(groupId, companyId, classNameId, classPK,
			typePK, true);
	}

	/**
	 * Returns the workflow definition link where groupId = &#63; and companyId = &#63; and classNameId = &#63; and classPK = &#63; and typePK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param typePK the type p k
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching workflow definition link, or <code>null</code> if a matching workflow definition link could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WorkflowDefinitionLink fetchByG_C_C_C_T(long groupId,
		long companyId, long classNameId, long classPK, long typePK,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] {
				groupId, companyId, classNameId, classPK, typePK
			};

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_G_C_C_C_T,
					finderArgs, this);
		}

		if (result == null) {
			StringBundler query = new StringBundler(7);

			query.append(_SQL_SELECT_WORKFLOWDEFINITIONLINK_WHERE);

			query.append(_FINDER_COLUMN_G_C_C_C_T_GROUPID_2);

			query.append(_FINDER_COLUMN_G_C_C_C_T_COMPANYID_2);

			query.append(_FINDER_COLUMN_G_C_C_C_T_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_G_C_C_C_T_CLASSPK_2);

			query.append(_FINDER_COLUMN_G_C_C_C_T_TYPEPK_2);

			query.append(WorkflowDefinitionLinkModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(companyId);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(typePK);

				List<WorkflowDefinitionLink> list = q.list();

				result = list;

				WorkflowDefinitionLink workflowDefinitionLink = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_C_C_C_T,
						finderArgs, list);
				}
				else {
					workflowDefinitionLink = list.get(0);

					cacheResult(workflowDefinitionLink);

					if ((workflowDefinitionLink.getGroupId() != groupId) ||
							(workflowDefinitionLink.getCompanyId() != companyId) ||
							(workflowDefinitionLink.getClassNameId() != classNameId) ||
							(workflowDefinitionLink.getClassPK() != classPK) ||
							(workflowDefinitionLink.getTypePK() != typePK)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_C_C_C_T,
							finderArgs, workflowDefinitionLink);
					}
				}

				return workflowDefinitionLink;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_C_C_C_T,
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
				return (WorkflowDefinitionLink)result;
			}
		}
	}

	/**
	 * Returns all the workflow definition links.
	 *
	 * @return the workflow definition links
	 * @throws SystemException if a system exception occurred
	 */
	public List<WorkflowDefinitionLink> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the workflow definition links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of workflow definition links
	 * @param end the upper bound of the range of workflow definition links (not inclusive)
	 * @return the range of workflow definition links
	 * @throws SystemException if a system exception occurred
	 */
	public List<WorkflowDefinitionLink> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the workflow definition links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of workflow definition links
	 * @param end the upper bound of the range of workflow definition links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of workflow definition links
	 * @throws SystemException if a system exception occurred
	 */
	public List<WorkflowDefinitionLink> findAll(int start, int end,
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

		List<WorkflowDefinitionLink> list = (List<WorkflowDefinitionLink>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_WORKFLOWDEFINITIONLINK);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_WORKFLOWDEFINITIONLINK.concat(WorkflowDefinitionLinkModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<WorkflowDefinitionLink>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<WorkflowDefinitionLink>)QueryUtil.list(q,
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
	 * Removes all the workflow definition links where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByCompanyId(long companyId) throws SystemException {
		for (WorkflowDefinitionLink workflowDefinitionLink : findByCompanyId(
				companyId)) {
			remove(workflowDefinitionLink);
		}
	}

	/**
	 * Removes all the workflow definition links where companyId = &#63; and workflowDefinitionName = &#63; and workflowDefinitionVersion = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param workflowDefinitionName the workflow definition name
	 * @param workflowDefinitionVersion the workflow definition version
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByC_W_W(long companyId, String workflowDefinitionName,
		int workflowDefinitionVersion) throws SystemException {
		for (WorkflowDefinitionLink workflowDefinitionLink : findByC_W_W(
				companyId, workflowDefinitionName, workflowDefinitionVersion)) {
			remove(workflowDefinitionLink);
		}
	}

	/**
	 * Removes the workflow definition link where groupId = &#63; and companyId = &#63; and classNameId = &#63; and classPK = &#63; and typePK = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param typePK the type p k
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByG_C_C_C_T(long groupId, long companyId,
		long classNameId, long classPK, long typePK)
		throws NoSuchWorkflowDefinitionLinkException, SystemException {
		WorkflowDefinitionLink workflowDefinitionLink = findByG_C_C_C_T(groupId,
				companyId, classNameId, classPK, typePK);

		remove(workflowDefinitionLink);
	}

	/**
	 * Removes all the workflow definition links from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (WorkflowDefinitionLink workflowDefinitionLink : findAll()) {
			remove(workflowDefinitionLink);
		}
	}

	/**
	 * Returns the number of workflow definition links where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching workflow definition links
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCompanyId(long companyId) throws SystemException {
		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPANYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_WORKFLOWDEFINITIONLINK_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_COMPANYID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of workflow definition links where companyId = &#63; and workflowDefinitionName = &#63; and workflowDefinitionVersion = &#63;.
	 *
	 * @param companyId the company ID
	 * @param workflowDefinitionName the workflow definition name
	 * @param workflowDefinitionVersion the workflow definition version
	 * @return the number of matching workflow definition links
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_W_W(long companyId, String workflowDefinitionName,
		int workflowDefinitionVersion) throws SystemException {
		Object[] finderArgs = new Object[] {
				companyId, workflowDefinitionName, workflowDefinitionVersion
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_W_W,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_WORKFLOWDEFINITIONLINK_WHERE);

			query.append(_FINDER_COLUMN_C_W_W_COMPANYID_2);

			if (workflowDefinitionName == null) {
				query.append(_FINDER_COLUMN_C_W_W_WORKFLOWDEFINITIONNAME_1);
			}
			else {
				if (workflowDefinitionName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_C_W_W_WORKFLOWDEFINITIONNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_C_W_W_WORKFLOWDEFINITIONNAME_2);
				}
			}

			query.append(_FINDER_COLUMN_C_W_W_WORKFLOWDEFINITIONVERSION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (workflowDefinitionName != null) {
					qPos.add(workflowDefinitionName);
				}

				qPos.add(workflowDefinitionVersion);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_W_W,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of workflow definition links where groupId = &#63; and companyId = &#63; and classNameId = &#63; and classPK = &#63; and typePK = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param typePK the type p k
	 * @return the number of matching workflow definition links
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_C_C_C_T(long groupId, long companyId, long classNameId,
		long classPK, long typePK) throws SystemException {
		Object[] finderArgs = new Object[] {
				groupId, companyId, classNameId, classPK, typePK
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_C_C_C_T,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_COUNT_WORKFLOWDEFINITIONLINK_WHERE);

			query.append(_FINDER_COLUMN_G_C_C_C_T_GROUPID_2);

			query.append(_FINDER_COLUMN_G_C_C_C_T_COMPANYID_2);

			query.append(_FINDER_COLUMN_G_C_C_C_T_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_G_C_C_C_T_CLASSPK_2);

			query.append(_FINDER_COLUMN_G_C_C_C_T_TYPEPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(companyId);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(typePK);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_C_C_C_T,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of workflow definition links.
	 *
	 * @return the number of workflow definition links
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_WORKFLOWDEFINITIONLINK);

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
	 * Initializes the workflow definition link persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.portal.util.PropsUtil.get(
						"value.object.listener.com.liferay.portal.model.WorkflowDefinitionLink")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<WorkflowDefinitionLink>> listenersList = new ArrayList<ModelListener<WorkflowDefinitionLink>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<WorkflowDefinitionLink>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(WorkflowDefinitionLinkImpl.class.getName());
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
	private static final String _SQL_SELECT_WORKFLOWDEFINITIONLINK = "SELECT workflowDefinitionLink FROM WorkflowDefinitionLink workflowDefinitionLink";
	private static final String _SQL_SELECT_WORKFLOWDEFINITIONLINK_WHERE = "SELECT workflowDefinitionLink FROM WorkflowDefinitionLink workflowDefinitionLink WHERE ";
	private static final String _SQL_COUNT_WORKFLOWDEFINITIONLINK = "SELECT COUNT(workflowDefinitionLink) FROM WorkflowDefinitionLink workflowDefinitionLink";
	private static final String _SQL_COUNT_WORKFLOWDEFINITIONLINK_WHERE = "SELECT COUNT(workflowDefinitionLink) FROM WorkflowDefinitionLink workflowDefinitionLink WHERE ";
	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "workflowDefinitionLink.companyId = ?";
	private static final String _FINDER_COLUMN_C_W_W_COMPANYID_2 = "workflowDefinitionLink.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_W_W_WORKFLOWDEFINITIONNAME_1 = "workflowDefinitionLink.workflowDefinitionName IS NULL AND ";
	private static final String _FINDER_COLUMN_C_W_W_WORKFLOWDEFINITIONNAME_2 = "workflowDefinitionLink.workflowDefinitionName = ? AND ";
	private static final String _FINDER_COLUMN_C_W_W_WORKFLOWDEFINITIONNAME_3 = "(workflowDefinitionLink.workflowDefinitionName IS NULL OR workflowDefinitionLink.workflowDefinitionName = ?) AND ";
	private static final String _FINDER_COLUMN_C_W_W_WORKFLOWDEFINITIONVERSION_2 =
		"workflowDefinitionLink.workflowDefinitionVersion = ?";
	private static final String _FINDER_COLUMN_G_C_C_C_T_GROUPID_2 = "workflowDefinitionLink.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_C_C_C_T_COMPANYID_2 = "workflowDefinitionLink.companyId = ? AND ";
	private static final String _FINDER_COLUMN_G_C_C_C_T_CLASSNAMEID_2 = "workflowDefinitionLink.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_G_C_C_C_T_CLASSPK_2 = "workflowDefinitionLink.classPK = ? AND ";
	private static final String _FINDER_COLUMN_G_C_C_C_T_TYPEPK_2 = "workflowDefinitionLink.typePK = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "workflowDefinitionLink.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No WorkflowDefinitionLink exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No WorkflowDefinitionLink exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = com.liferay.portal.util.PropsValues.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE;
	private static Log _log = LogFactoryUtil.getLog(WorkflowDefinitionLinkPersistenceImpl.class);
	private static WorkflowDefinitionLink _nullWorkflowDefinitionLink = new WorkflowDefinitionLinkImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<WorkflowDefinitionLink> toCacheModel() {
				return _nullWorkflowDefinitionLinkCacheModel;
			}
		};

	private static CacheModel<WorkflowDefinitionLink> _nullWorkflowDefinitionLinkCacheModel =
		new CacheModel<WorkflowDefinitionLink>() {
			public WorkflowDefinitionLink toEntityModel() {
				return _nullWorkflowDefinitionLink;
			}
		};
}