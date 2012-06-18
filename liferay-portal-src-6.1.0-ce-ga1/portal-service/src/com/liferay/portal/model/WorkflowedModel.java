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

import java.util.Date;

/**
 * @author Brian Wing Shun Chan
 */
public interface WorkflowedModel {

	public int getStatus();

	public long getStatusByUserId();

	public String getStatusByUserName();

	public Date getStatusDate();

	public boolean isApproved();

	public boolean isDraft();

	public boolean isExpired();

	public boolean isPending();

	public void setStatus(int status);

	public void setStatusByUserId(long statusByUserId);

	public void setStatusByUserName(String statusByUserName);

	public void setStatusDate(Date statusDate);

}