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

package com.liferay.portlet.ratings.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link RatingsStats}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       RatingsStats
 * @generated
 */
public class RatingsStatsWrapper implements RatingsStats,
	ModelWrapper<RatingsStats> {
	public RatingsStatsWrapper(RatingsStats ratingsStats) {
		_ratingsStats = ratingsStats;
	}

	public Class<?> getModelClass() {
		return RatingsStats.class;
	}

	public String getModelClassName() {
		return RatingsStats.class.getName();
	}

	/**
	* Returns the primary key of this ratings stats.
	*
	* @return the primary key of this ratings stats
	*/
	public long getPrimaryKey() {
		return _ratingsStats.getPrimaryKey();
	}

	/**
	* Sets the primary key of this ratings stats.
	*
	* @param primaryKey the primary key of this ratings stats
	*/
	public void setPrimaryKey(long primaryKey) {
		_ratingsStats.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the stats ID of this ratings stats.
	*
	* @return the stats ID of this ratings stats
	*/
	public long getStatsId() {
		return _ratingsStats.getStatsId();
	}

	/**
	* Sets the stats ID of this ratings stats.
	*
	* @param statsId the stats ID of this ratings stats
	*/
	public void setStatsId(long statsId) {
		_ratingsStats.setStatsId(statsId);
	}

	/**
	* Returns the fully qualified class name of this ratings stats.
	*
	* @return the fully qualified class name of this ratings stats
	*/
	public java.lang.String getClassName() {
		return _ratingsStats.getClassName();
	}

	/**
	* Returns the class name ID of this ratings stats.
	*
	* @return the class name ID of this ratings stats
	*/
	public long getClassNameId() {
		return _ratingsStats.getClassNameId();
	}

	/**
	* Sets the class name ID of this ratings stats.
	*
	* @param classNameId the class name ID of this ratings stats
	*/
	public void setClassNameId(long classNameId) {
		_ratingsStats.setClassNameId(classNameId);
	}

	/**
	* Returns the class p k of this ratings stats.
	*
	* @return the class p k of this ratings stats
	*/
	public long getClassPK() {
		return _ratingsStats.getClassPK();
	}

	/**
	* Sets the class p k of this ratings stats.
	*
	* @param classPK the class p k of this ratings stats
	*/
	public void setClassPK(long classPK) {
		_ratingsStats.setClassPK(classPK);
	}

	/**
	* Returns the total entries of this ratings stats.
	*
	* @return the total entries of this ratings stats
	*/
	public int getTotalEntries() {
		return _ratingsStats.getTotalEntries();
	}

	/**
	* Sets the total entries of this ratings stats.
	*
	* @param totalEntries the total entries of this ratings stats
	*/
	public void setTotalEntries(int totalEntries) {
		_ratingsStats.setTotalEntries(totalEntries);
	}

	/**
	* Returns the total score of this ratings stats.
	*
	* @return the total score of this ratings stats
	*/
	public double getTotalScore() {
		return _ratingsStats.getTotalScore();
	}

	/**
	* Sets the total score of this ratings stats.
	*
	* @param totalScore the total score of this ratings stats
	*/
	public void setTotalScore(double totalScore) {
		_ratingsStats.setTotalScore(totalScore);
	}

	/**
	* Returns the average score of this ratings stats.
	*
	* @return the average score of this ratings stats
	*/
	public double getAverageScore() {
		return _ratingsStats.getAverageScore();
	}

	/**
	* Sets the average score of this ratings stats.
	*
	* @param averageScore the average score of this ratings stats
	*/
	public void setAverageScore(double averageScore) {
		_ratingsStats.setAverageScore(averageScore);
	}

	public boolean isNew() {
		return _ratingsStats.isNew();
	}

	public void setNew(boolean n) {
		_ratingsStats.setNew(n);
	}

	public boolean isCachedModel() {
		return _ratingsStats.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_ratingsStats.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _ratingsStats.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _ratingsStats.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_ratingsStats.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _ratingsStats.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_ratingsStats.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new RatingsStatsWrapper((RatingsStats)_ratingsStats.clone());
	}

	public int compareTo(
		com.liferay.portlet.ratings.model.RatingsStats ratingsStats) {
		return _ratingsStats.compareTo(ratingsStats);
	}

	@Override
	public int hashCode() {
		return _ratingsStats.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portlet.ratings.model.RatingsStats> toCacheModel() {
		return _ratingsStats.toCacheModel();
	}

	public com.liferay.portlet.ratings.model.RatingsStats toEscapedModel() {
		return new RatingsStatsWrapper(_ratingsStats.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _ratingsStats.toString();
	}

	public java.lang.String toXmlString() {
		return _ratingsStats.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_ratingsStats.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public RatingsStats getWrappedRatingsStats() {
		return _ratingsStats;
	}

	public RatingsStats getWrappedModel() {
		return _ratingsStats;
	}

	public void resetOriginalValues() {
		_ratingsStats.resetOriginalValues();
	}

	private RatingsStats _ratingsStats;
}