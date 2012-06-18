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

package com.liferay.portlet.dynamicdatalists.model.impl;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;

import com.liferay.portlet.dynamicdatalists.model.DDLRecord;
import com.liferay.portlet.dynamicdatalists.model.DDLRecordModel;
import com.liferay.portlet.dynamicdatalists.model.DDLRecordSoap;
import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The base model implementation for the DDLRecord service. Represents a row in the &quot;DDLRecord&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.liferay.portlet.dynamicdatalists.model.DDLRecordModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DDLRecordImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DDLRecordImpl
 * @see com.liferay.portlet.dynamicdatalists.model.DDLRecord
 * @see com.liferay.portlet.dynamicdatalists.model.DDLRecordModel
 * @generated
 */
@JSON(strict = true)
public class DDLRecordModelImpl extends BaseModelImpl<DDLRecord>
	implements DDLRecordModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a d d l record model instance should use the {@link com.liferay.portlet.dynamicdatalists.model.DDLRecord} interface instead.
	 */
	public static final String TABLE_NAME = "DDLRecord";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "recordId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "versionUserId", Types.BIGINT },
			{ "versionUserName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "DDMStorageId", Types.BIGINT },
			{ "recordSetId", Types.BIGINT },
			{ "version", Types.VARCHAR },
			{ "displayIndex", Types.INTEGER }
		};
	public static final String TABLE_SQL_CREATE = "create table DDLRecord (uuid_ VARCHAR(75) null,recordId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,versionUserId LONG,versionUserName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,DDMStorageId LONG,recordSetId LONG,version VARCHAR(75) null,displayIndex INTEGER)";
	public static final String TABLE_SQL_DROP = "drop table DDLRecord";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.liferay.portlet.dynamicdatalists.model.DDLRecord"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.liferay.portlet.dynamicdatalists.model.DDLRecord"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.column.bitmask.enabled.com.liferay.portlet.dynamicdatalists.model.DDLRecord"),
			true);
	public static long GROUPID_COLUMN_BITMASK = 1L;
	public static long RECORDSETID_COLUMN_BITMASK = 2L;
	public static long USERID_COLUMN_BITMASK = 4L;
	public static long UUID_COLUMN_BITMASK = 8L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static DDLRecord toModel(DDLRecordSoap soapModel) {
		DDLRecord model = new DDLRecordImpl();

		model.setUuid(soapModel.getUuid());
		model.setRecordId(soapModel.getRecordId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setVersionUserId(soapModel.getVersionUserId());
		model.setVersionUserName(soapModel.getVersionUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setDDMStorageId(soapModel.getDDMStorageId());
		model.setRecordSetId(soapModel.getRecordSetId());
		model.setVersion(soapModel.getVersion());
		model.setDisplayIndex(soapModel.getDisplayIndex());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<DDLRecord> toModels(DDLRecordSoap[] soapModels) {
		List<DDLRecord> models = new ArrayList<DDLRecord>(soapModels.length);

		for (DDLRecordSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
				"lock.expiration.time.com.liferay.portlet.dynamicdatalists.model.DDLRecord"));

	public DDLRecordModelImpl() {
	}

	public long getPrimaryKey() {
		return _recordId;
	}

	public void setPrimaryKey(long primaryKey) {
		setRecordId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_recordId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public Class<?> getModelClass() {
		return DDLRecord.class;
	}

	public String getModelClassName() {
		return DDLRecord.class.getName();
	}

	@JSON
	public String getUuid() {
		if (_uuid == null) {
			return StringPool.BLANK;
		}
		else {
			return _uuid;
		}
	}

	public void setUuid(String uuid) {
		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@JSON
	public long getRecordId() {
		return _recordId;
	}

	public void setRecordId(long recordId) {
		_recordId = recordId;
	}

	@JSON
	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	@JSON
	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	@JSON
	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_columnBitmask |= USERID_COLUMN_BITMASK;

		if (!_setOriginalUserId) {
			_setOriginalUserId = true;

			_originalUserId = _userId;
		}

		_userId = userId;
	}

	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	public long getOriginalUserId() {
		return _originalUserId;
	}

	@JSON
	public String getUserName() {
		if (_userName == null) {
			return StringPool.BLANK;
		}
		else {
			return _userName;
		}
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	@JSON
	public long getVersionUserId() {
		return _versionUserId;
	}

	public void setVersionUserId(long versionUserId) {
		_versionUserId = versionUserId;
	}

	public String getVersionUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getVersionUserId(), "uuid",
			_versionUserUuid);
	}

	public void setVersionUserUuid(String versionUserUuid) {
		_versionUserUuid = versionUserUuid;
	}

	@JSON
	public String getVersionUserName() {
		if (_versionUserName == null) {
			return StringPool.BLANK;
		}
		else {
			return _versionUserName;
		}
	}

	public void setVersionUserName(String versionUserName) {
		_versionUserName = versionUserName;
	}

	@JSON
	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	@JSON
	public long getDDMStorageId() {
		return _DDMStorageId;
	}

	public void setDDMStorageId(long DDMStorageId) {
		_DDMStorageId = DDMStorageId;
	}

	@JSON
	public long getRecordSetId() {
		return _recordSetId;
	}

	public void setRecordSetId(long recordSetId) {
		_columnBitmask |= RECORDSETID_COLUMN_BITMASK;

		if (!_setOriginalRecordSetId) {
			_setOriginalRecordSetId = true;

			_originalRecordSetId = _recordSetId;
		}

		_recordSetId = recordSetId;
	}

	public long getOriginalRecordSetId() {
		return _originalRecordSetId;
	}

	@JSON
	public String getVersion() {
		if (_version == null) {
			return StringPool.BLANK;
		}
		else {
			return _version;
		}
	}

	public void setVersion(String version) {
		_version = version;
	}

	@JSON
	public int getDisplayIndex() {
		return _displayIndex;
	}

	public void setDisplayIndex(int displayIndex) {
		_displayIndex = displayIndex;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public DDLRecord toEscapedModel() {
		if (_escapedModelProxy == null) {
			_escapedModelProxy = (DDLRecord)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelProxyInterfaces,
					new AutoEscapeBeanHandler(this));
		}

		return _escapedModelProxy;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
					DDLRecord.class.getName(), getPrimaryKey());
		}

		return _expandoBridge;
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		getExpandoBridge().setAttributes(serviceContext);
	}

	@Override
	public Object clone() {
		DDLRecordImpl ddlRecordImpl = new DDLRecordImpl();

		ddlRecordImpl.setUuid(getUuid());
		ddlRecordImpl.setRecordId(getRecordId());
		ddlRecordImpl.setGroupId(getGroupId());
		ddlRecordImpl.setCompanyId(getCompanyId());
		ddlRecordImpl.setUserId(getUserId());
		ddlRecordImpl.setUserName(getUserName());
		ddlRecordImpl.setVersionUserId(getVersionUserId());
		ddlRecordImpl.setVersionUserName(getVersionUserName());
		ddlRecordImpl.setCreateDate(getCreateDate());
		ddlRecordImpl.setModifiedDate(getModifiedDate());
		ddlRecordImpl.setDDMStorageId(getDDMStorageId());
		ddlRecordImpl.setRecordSetId(getRecordSetId());
		ddlRecordImpl.setVersion(getVersion());
		ddlRecordImpl.setDisplayIndex(getDisplayIndex());

		ddlRecordImpl.resetOriginalValues();

		return ddlRecordImpl;
	}

	public int compareTo(DDLRecord ddlRecord) {
		long primaryKey = ddlRecord.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		DDLRecord ddlRecord = null;

		try {
			ddlRecord = (DDLRecord)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = ddlRecord.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public void resetOriginalValues() {
		DDLRecordModelImpl ddlRecordModelImpl = this;

		ddlRecordModelImpl._originalUuid = ddlRecordModelImpl._uuid;

		ddlRecordModelImpl._originalGroupId = ddlRecordModelImpl._groupId;

		ddlRecordModelImpl._setOriginalGroupId = false;

		ddlRecordModelImpl._originalUserId = ddlRecordModelImpl._userId;

		ddlRecordModelImpl._setOriginalUserId = false;

		ddlRecordModelImpl._originalRecordSetId = ddlRecordModelImpl._recordSetId;

		ddlRecordModelImpl._setOriginalRecordSetId = false;

		ddlRecordModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<DDLRecord> toCacheModel() {
		DDLRecordCacheModel ddlRecordCacheModel = new DDLRecordCacheModel();

		ddlRecordCacheModel.uuid = getUuid();

		String uuid = ddlRecordCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			ddlRecordCacheModel.uuid = null;
		}

		ddlRecordCacheModel.recordId = getRecordId();

		ddlRecordCacheModel.groupId = getGroupId();

		ddlRecordCacheModel.companyId = getCompanyId();

		ddlRecordCacheModel.userId = getUserId();

		ddlRecordCacheModel.userName = getUserName();

		String userName = ddlRecordCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			ddlRecordCacheModel.userName = null;
		}

		ddlRecordCacheModel.versionUserId = getVersionUserId();

		ddlRecordCacheModel.versionUserName = getVersionUserName();

		String versionUserName = ddlRecordCacheModel.versionUserName;

		if ((versionUserName != null) && (versionUserName.length() == 0)) {
			ddlRecordCacheModel.versionUserName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			ddlRecordCacheModel.createDate = createDate.getTime();
		}
		else {
			ddlRecordCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			ddlRecordCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			ddlRecordCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		ddlRecordCacheModel.DDMStorageId = getDDMStorageId();

		ddlRecordCacheModel.recordSetId = getRecordSetId();

		ddlRecordCacheModel.version = getVersion();

		String version = ddlRecordCacheModel.version;

		if ((version != null) && (version.length() == 0)) {
			ddlRecordCacheModel.version = null;
		}

		ddlRecordCacheModel.displayIndex = getDisplayIndex();

		return ddlRecordCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", recordId=");
		sb.append(getRecordId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", versionUserId=");
		sb.append(getVersionUserId());
		sb.append(", versionUserName=");
		sb.append(getVersionUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", DDMStorageId=");
		sb.append(getDDMStorageId());
		sb.append(", recordSetId=");
		sb.append(getRecordSetId());
		sb.append(", version=");
		sb.append(getVersion());
		sb.append(", displayIndex=");
		sb.append(getDisplayIndex());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(46);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portlet.dynamicdatalists.model.DDLRecord");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>recordId</column-name><column-value><![CDATA[");
		sb.append(getRecordId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>versionUserId</column-name><column-value><![CDATA[");
		sb.append(getVersionUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>versionUserName</column-name><column-value><![CDATA[");
		sb.append(getVersionUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>DDMStorageId</column-name><column-value><![CDATA[");
		sb.append(getDDMStorageId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>recordSetId</column-name><column-value><![CDATA[");
		sb.append(getRecordSetId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>version</column-name><column-value><![CDATA[");
		sb.append(getVersion());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>displayIndex</column-name><column-value><![CDATA[");
		sb.append(getDisplayIndex());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = DDLRecord.class.getClassLoader();
	private static Class<?>[] _escapedModelProxyInterfaces = new Class[] {
			DDLRecord.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _recordId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private long _originalUserId;
	private boolean _setOriginalUserId;
	private String _userName;
	private long _versionUserId;
	private String _versionUserUuid;
	private String _versionUserName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _DDMStorageId;
	private long _recordSetId;
	private long _originalRecordSetId;
	private boolean _setOriginalRecordSetId;
	private String _version;
	private int _displayIndex;
	private transient ExpandoBridge _expandoBridge;
	private long _columnBitmask;
	private DDLRecord _escapedModelProxy;
}