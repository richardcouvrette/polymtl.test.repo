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
 * This class is a wrapper for {@link Image}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Image
 * @generated
 */
public class ImageWrapper implements Image, ModelWrapper<Image> {
	public ImageWrapper(Image image) {
		_image = image;
	}

	public Class<?> getModelClass() {
		return Image.class;
	}

	public String getModelClassName() {
		return Image.class.getName();
	}

	/**
	* Returns the primary key of this image.
	*
	* @return the primary key of this image
	*/
	public long getPrimaryKey() {
		return _image.getPrimaryKey();
	}

	/**
	* Sets the primary key of this image.
	*
	* @param primaryKey the primary key of this image
	*/
	public void setPrimaryKey(long primaryKey) {
		_image.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the image ID of this image.
	*
	* @return the image ID of this image
	*/
	public long getImageId() {
		return _image.getImageId();
	}

	/**
	* Sets the image ID of this image.
	*
	* @param imageId the image ID of this image
	*/
	public void setImageId(long imageId) {
		_image.setImageId(imageId);
	}

	/**
	* Returns the modified date of this image.
	*
	* @return the modified date of this image
	*/
	public java.util.Date getModifiedDate() {
		return _image.getModifiedDate();
	}

	/**
	* Sets the modified date of this image.
	*
	* @param modifiedDate the modified date of this image
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_image.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the text of this image.
	*
	* @return the text of this image
	*/
	public java.lang.String getText() {
		return _image.getText();
	}

	/**
	* Sets the text of this image.
	*
	* @param text the text of this image
	*/
	public void setText(java.lang.String text) {
		_image.setText(text);
	}

	/**
	* Returns the type of this image.
	*
	* @return the type of this image
	*/
	public java.lang.String getType() {
		return _image.getType();
	}

	/**
	* Sets the type of this image.
	*
	* @param type the type of this image
	*/
	public void setType(java.lang.String type) {
		_image.setType(type);
	}

	/**
	* Returns the height of this image.
	*
	* @return the height of this image
	*/
	public int getHeight() {
		return _image.getHeight();
	}

	/**
	* Sets the height of this image.
	*
	* @param height the height of this image
	*/
	public void setHeight(int height) {
		_image.setHeight(height);
	}

	/**
	* Returns the width of this image.
	*
	* @return the width of this image
	*/
	public int getWidth() {
		return _image.getWidth();
	}

	/**
	* Sets the width of this image.
	*
	* @param width the width of this image
	*/
	public void setWidth(int width) {
		_image.setWidth(width);
	}

	/**
	* Returns the size of this image.
	*
	* @return the size of this image
	*/
	public int getSize() {
		return _image.getSize();
	}

	/**
	* Sets the size of this image.
	*
	* @param size the size of this image
	*/
	public void setSize(int size) {
		_image.setSize(size);
	}

	public boolean isNew() {
		return _image.isNew();
	}

	public void setNew(boolean n) {
		_image.setNew(n);
	}

	public boolean isCachedModel() {
		return _image.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_image.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _image.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _image.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_image.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _image.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_image.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new ImageWrapper((Image)_image.clone());
	}

	public int compareTo(com.liferay.portal.model.Image image) {
		return _image.compareTo(image);
	}

	@Override
	public int hashCode() {
		return _image.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portal.model.Image> toCacheModel() {
		return _image.toCacheModel();
	}

	public com.liferay.portal.model.Image toEscapedModel() {
		return new ImageWrapper(_image.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _image.toString();
	}

	public java.lang.String toXmlString() {
		return _image.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_image.persist();
	}

	public byte[] getTextObj() {
		return _image.getTextObj();
	}

	public void setTextObj(byte[] textObj) {
		_image.setTextObj(textObj);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public Image getWrappedImage() {
		return _image;
	}

	public Image getWrappedModel() {
		return _image;
	}

	public void resetOriginalValues() {
		_image.resetOriginalValues();
	}

	private Image _image;
}