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

package com.liferay.portlet.announcements.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.AttachedModel;
import com.liferay.portal.model.AuditedModel;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the AnnouncementsEntry service. Represents a row in the &quot;AnnouncementsEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portlet.announcements.model.impl.AnnouncementsEntryModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portlet.announcements.model.impl.AnnouncementsEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AnnouncementsEntry
 * @see com.liferay.portlet.announcements.model.impl.AnnouncementsEntryImpl
 * @see com.liferay.portlet.announcements.model.impl.AnnouncementsEntryModelImpl
 * @generated
 */
public interface AnnouncementsEntryModel extends AttachedModel, AuditedModel,
	BaseModel<AnnouncementsEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a announcements entry model instance should use the {@link AnnouncementsEntry} interface instead.
	 */

	/**
	 * Returns the primary key of this announcements entry.
	 *
	 * @return the primary key of this announcements entry
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this announcements entry.
	 *
	 * @param primaryKey the primary key of this announcements entry
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this announcements entry.
	 *
	 * @return the uuid of this announcements entry
	 */
	@AutoEscape
	public String getUuid();

	/**
	 * Sets the uuid of this announcements entry.
	 *
	 * @param uuid the uuid of this announcements entry
	 */
	public void setUuid(String uuid);

	/**
	 * Returns the entry ID of this announcements entry.
	 *
	 * @return the entry ID of this announcements entry
	 */
	public long getEntryId();

	/**
	 * Sets the entry ID of this announcements entry.
	 *
	 * @param entryId the entry ID of this announcements entry
	 */
	public void setEntryId(long entryId);

	/**
	 * Returns the company ID of this announcements entry.
	 *
	 * @return the company ID of this announcements entry
	 */
	public long getCompanyId();

	/**
	 * Sets the company ID of this announcements entry.
	 *
	 * @param companyId the company ID of this announcements entry
	 */
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this announcements entry.
	 *
	 * @return the user ID of this announcements entry
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this announcements entry.
	 *
	 * @param userId the user ID of this announcements entry
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this announcements entry.
	 *
	 * @return the user uuid of this announcements entry
	 * @throws SystemException if a system exception occurred
	 */
	public String getUserUuid() throws SystemException;

	/**
	 * Sets the user uuid of this announcements entry.
	 *
	 * @param userUuid the user uuid of this announcements entry
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this announcements entry.
	 *
	 * @return the user name of this announcements entry
	 */
	@AutoEscape
	public String getUserName();

	/**
	 * Sets the user name of this announcements entry.
	 *
	 * @param userName the user name of this announcements entry
	 */
	public void setUserName(String userName);

	/**
	 * Returns the create date of this announcements entry.
	 *
	 * @return the create date of this announcements entry
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this announcements entry.
	 *
	 * @param createDate the create date of this announcements entry
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this announcements entry.
	 *
	 * @return the modified date of this announcements entry
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this announcements entry.
	 *
	 * @param modifiedDate the modified date of this announcements entry
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the fully qualified class name of this announcements entry.
	 *
	 * @return the fully qualified class name of this announcements entry
	 */
	public String getClassName();

	/**
	 * Returns the class name ID of this announcements entry.
	 *
	 * @return the class name ID of this announcements entry
	 */
	public long getClassNameId();

	/**
	 * Sets the class name ID of this announcements entry.
	 *
	 * @param classNameId the class name ID of this announcements entry
	 */
	public void setClassNameId(long classNameId);

	/**
	 * Returns the class p k of this announcements entry.
	 *
	 * @return the class p k of this announcements entry
	 */
	public long getClassPK();

	/**
	 * Sets the class p k of this announcements entry.
	 *
	 * @param classPK the class p k of this announcements entry
	 */
	public void setClassPK(long classPK);

	/**
	 * Returns the title of this announcements entry.
	 *
	 * @return the title of this announcements entry
	 */
	@AutoEscape
	public String getTitle();

	/**
	 * Sets the title of this announcements entry.
	 *
	 * @param title the title of this announcements entry
	 */
	public void setTitle(String title);

	/**
	 * Returns the content of this announcements entry.
	 *
	 * @return the content of this announcements entry
	 */
	@AutoEscape
	public String getContent();

	/**
	 * Sets the content of this announcements entry.
	 *
	 * @param content the content of this announcements entry
	 */
	public void setContent(String content);

	/**
	 * Returns the url of this announcements entry.
	 *
	 * @return the url of this announcements entry
	 */
	@AutoEscape
	public String getUrl();

	/**
	 * Sets the url of this announcements entry.
	 *
	 * @param url the url of this announcements entry
	 */
	public void setUrl(String url);

	/**
	 * Returns the type of this announcements entry.
	 *
	 * @return the type of this announcements entry
	 */
	@AutoEscape
	public String getType();

	/**
	 * Sets the type of this announcements entry.
	 *
	 * @param type the type of this announcements entry
	 */
	public void setType(String type);

	/**
	 * Returns the display date of this announcements entry.
	 *
	 * @return the display date of this announcements entry
	 */
	public Date getDisplayDate();

	/**
	 * Sets the display date of this announcements entry.
	 *
	 * @param displayDate the display date of this announcements entry
	 */
	public void setDisplayDate(Date displayDate);

	/**
	 * Returns the expiration date of this announcements entry.
	 *
	 * @return the expiration date of this announcements entry
	 */
	public Date getExpirationDate();

	/**
	 * Sets the expiration date of this announcements entry.
	 *
	 * @param expirationDate the expiration date of this announcements entry
	 */
	public void setExpirationDate(Date expirationDate);

	/**
	 * Returns the priority of this announcements entry.
	 *
	 * @return the priority of this announcements entry
	 */
	public int getPriority();

	/**
	 * Sets the priority of this announcements entry.
	 *
	 * @param priority the priority of this announcements entry
	 */
	public void setPriority(int priority);

	/**
	 * Returns the alert of this announcements entry.
	 *
	 * @return the alert of this announcements entry
	 */
	public boolean getAlert();

	/**
	 * Returns <code>true</code> if this announcements entry is alert.
	 *
	 * @return <code>true</code> if this announcements entry is alert; <code>false</code> otherwise
	 */
	public boolean isAlert();

	/**
	 * Sets whether this announcements entry is alert.
	 *
	 * @param alert the alert of this announcements entry
	 */
	public void setAlert(boolean alert);

	public boolean isNew();

	public void setNew(boolean n);

	public boolean isCachedModel();

	public void setCachedModel(boolean cachedModel);

	public boolean isEscapedModel();

	public Serializable getPrimaryKeyObj();

	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	public ExpandoBridge getExpandoBridge();

	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	public Object clone();

	public int compareTo(AnnouncementsEntry announcementsEntry);

	public int hashCode();

	public CacheModel<AnnouncementsEntry> toCacheModel();

	public AnnouncementsEntry toEscapedModel();

	public String toString();

	public String toXmlString();
}