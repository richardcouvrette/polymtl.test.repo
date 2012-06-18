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

package com.liferay.portlet.messageboards.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.portlet.messageboards.model.MBThread;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing MBThread in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see MBThread
 * @generated
 */
public class MBThreadCacheModel implements CacheModel<MBThread>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(33);

		sb.append("{threadId=");
		sb.append(threadId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", categoryId=");
		sb.append(categoryId);
		sb.append(", rootMessageId=");
		sb.append(rootMessageId);
		sb.append(", rootMessageUserId=");
		sb.append(rootMessageUserId);
		sb.append(", messageCount=");
		sb.append(messageCount);
		sb.append(", viewCount=");
		sb.append(viewCount);
		sb.append(", lastPostByUserId=");
		sb.append(lastPostByUserId);
		sb.append(", lastPostDate=");
		sb.append(lastPostDate);
		sb.append(", priority=");
		sb.append(priority);
		sb.append(", question=");
		sb.append(question);
		sb.append(", status=");
		sb.append(status);
		sb.append(", statusByUserId=");
		sb.append(statusByUserId);
		sb.append(", statusByUserName=");
		sb.append(statusByUserName);
		sb.append(", statusDate=");
		sb.append(statusDate);
		sb.append("}");

		return sb.toString();
	}

	public MBThread toEntityModel() {
		MBThreadImpl mbThreadImpl = new MBThreadImpl();

		mbThreadImpl.setThreadId(threadId);
		mbThreadImpl.setGroupId(groupId);
		mbThreadImpl.setCompanyId(companyId);
		mbThreadImpl.setCategoryId(categoryId);
		mbThreadImpl.setRootMessageId(rootMessageId);
		mbThreadImpl.setRootMessageUserId(rootMessageUserId);
		mbThreadImpl.setMessageCount(messageCount);
		mbThreadImpl.setViewCount(viewCount);
		mbThreadImpl.setLastPostByUserId(lastPostByUserId);

		if (lastPostDate == Long.MIN_VALUE) {
			mbThreadImpl.setLastPostDate(null);
		}
		else {
			mbThreadImpl.setLastPostDate(new Date(lastPostDate));
		}

		mbThreadImpl.setPriority(priority);
		mbThreadImpl.setQuestion(question);
		mbThreadImpl.setStatus(status);
		mbThreadImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			mbThreadImpl.setStatusByUserName(StringPool.BLANK);
		}
		else {
			mbThreadImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			mbThreadImpl.setStatusDate(null);
		}
		else {
			mbThreadImpl.setStatusDate(new Date(statusDate));
		}

		mbThreadImpl.resetOriginalValues();

		return mbThreadImpl;
	}

	public long threadId;
	public long groupId;
	public long companyId;
	public long categoryId;
	public long rootMessageId;
	public long rootMessageUserId;
	public int messageCount;
	public int viewCount;
	public long lastPostByUserId;
	public long lastPostDate;
	public double priority;
	public boolean question;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;
}