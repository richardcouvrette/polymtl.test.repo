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

package com.liferay.portal.model;

/**
 * <p>
 * This class is a wrapper for {@link Ticket}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Ticket
 * @generated
 */
public class TicketWrapper implements Ticket, ModelWrapper<Ticket> {
	public TicketWrapper(Ticket ticket) {
		_ticket = ticket;
	}

	public Class<?> getModelClass() {
		return Ticket.class;
	}

	public String getModelClassName() {
		return Ticket.class.getName();
	}

	/**
	* Returns the primary key of this ticket.
	*
	* @return the primary key of this ticket
	*/
	public long getPrimaryKey() {
		return _ticket.getPrimaryKey();
	}

	/**
	* Sets the primary key of this ticket.
	*
	* @param primaryKey the primary key of this ticket
	*/
	public void setPrimaryKey(long primaryKey) {
		_ticket.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the ticket ID of this ticket.
	*
	* @return the ticket ID of this ticket
	*/
	public long getTicketId() {
		return _ticket.getTicketId();
	}

	/**
	* Sets the ticket ID of this ticket.
	*
	* @param ticketId the ticket ID of this ticket
	*/
	public void setTicketId(long ticketId) {
		_ticket.setTicketId(ticketId);
	}

	/**
	* Returns the company ID of this ticket.
	*
	* @return the company ID of this ticket
	*/
	public long getCompanyId() {
		return _ticket.getCompanyId();
	}

	/**
	* Sets the company ID of this ticket.
	*
	* @param companyId the company ID of this ticket
	*/
	public void setCompanyId(long companyId) {
		_ticket.setCompanyId(companyId);
	}

	/**
	* Returns the create date of this ticket.
	*
	* @return the create date of this ticket
	*/
	public java.util.Date getCreateDate() {
		return _ticket.getCreateDate();
	}

	/**
	* Sets the create date of this ticket.
	*
	* @param createDate the create date of this ticket
	*/
	public void setCreateDate(java.util.Date createDate) {
		_ticket.setCreateDate(createDate);
	}

	/**
	* Returns the fully qualified class name of this ticket.
	*
	* @return the fully qualified class name of this ticket
	*/
	public java.lang.String getClassName() {
		return _ticket.getClassName();
	}

	/**
	* Returns the class name ID of this ticket.
	*
	* @return the class name ID of this ticket
	*/
	public long getClassNameId() {
		return _ticket.getClassNameId();
	}

	/**
	* Sets the class name ID of this ticket.
	*
	* @param classNameId the class name ID of this ticket
	*/
	public void setClassNameId(long classNameId) {
		_ticket.setClassNameId(classNameId);
	}

	/**
	* Returns the class p k of this ticket.
	*
	* @return the class p k of this ticket
	*/
	public long getClassPK() {
		return _ticket.getClassPK();
	}

	/**
	* Sets the class p k of this ticket.
	*
	* @param classPK the class p k of this ticket
	*/
	public void setClassPK(long classPK) {
		_ticket.setClassPK(classPK);
	}

	/**
	* Returns the key of this ticket.
	*
	* @return the key of this ticket
	*/
	public java.lang.String getKey() {
		return _ticket.getKey();
	}

	/**
	* Sets the key of this ticket.
	*
	* @param key the key of this ticket
	*/
	public void setKey(java.lang.String key) {
		_ticket.setKey(key);
	}

	/**
	* Returns the type of this ticket.
	*
	* @return the type of this ticket
	*/
	public int getType() {
		return _ticket.getType();
	}

	/**
	* Sets the type of this ticket.
	*
	* @param type the type of this ticket
	*/
	public void setType(int type) {
		_ticket.setType(type);
	}

	/**
	* Returns the extra info of this ticket.
	*
	* @return the extra info of this ticket
	*/
	public java.lang.String getExtraInfo() {
		return _ticket.getExtraInfo();
	}

	/**
	* Sets the extra info of this ticket.
	*
	* @param extraInfo the extra info of this ticket
	*/
	public void setExtraInfo(java.lang.String extraInfo) {
		_ticket.setExtraInfo(extraInfo);
	}

	/**
	* Returns the expiration date of this ticket.
	*
	* @return the expiration date of this ticket
	*/
	public java.util.Date getExpirationDate() {
		return _ticket.getExpirationDate();
	}

	/**
	* Sets the expiration date of this ticket.
	*
	* @param expirationDate the expiration date of this ticket
	*/
	public void setExpirationDate(java.util.Date expirationDate) {
		_ticket.setExpirationDate(expirationDate);
	}

	public boolean isNew() {
		return _ticket.isNew();
	}

	public void setNew(boolean n) {
		_ticket.setNew(n);
	}

	public boolean isCachedModel() {
		return _ticket.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_ticket.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _ticket.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _ticket.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_ticket.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _ticket.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_ticket.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new TicketWrapper((Ticket)_ticket.clone());
	}

	public int compareTo(com.liferay.portal.model.Ticket ticket) {
		return _ticket.compareTo(ticket);
	}

	@Override
	public int hashCode() {
		return _ticket.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portal.model.Ticket> toCacheModel() {
		return _ticket.toCacheModel();
	}

	public com.liferay.portal.model.Ticket toEscapedModel() {
		return new TicketWrapper(_ticket.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _ticket.toString();
	}

	public java.lang.String toXmlString() {
		return _ticket.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_ticket.persist();
	}

	public boolean isExpired() {
		return _ticket.isExpired();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public Ticket getWrappedTicket() {
		return _ticket;
	}

	public Ticket getWrappedModel() {
		return _ticket;
	}

	public void resetOriginalValues() {
		_ticket.resetOriginalValues();
	}

	private Ticket _ticket;
}