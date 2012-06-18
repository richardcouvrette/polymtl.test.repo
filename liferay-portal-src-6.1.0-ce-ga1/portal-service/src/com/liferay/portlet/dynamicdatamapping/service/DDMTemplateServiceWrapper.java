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

package com.liferay.portlet.dynamicdatamapping.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link DDMTemplateService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       DDMTemplateService
 * @generated
 */
public class DDMTemplateServiceWrapper implements DDMTemplateService,
	ServiceWrapper<DDMTemplateService> {
	public DDMTemplateServiceWrapper(DDMTemplateService ddmTemplateService) {
		_ddmTemplateService = ddmTemplateService;
	}

	public com.liferay.portlet.dynamicdatamapping.model.DDMTemplate addTemplate(
		long groupId, long structureId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String type, java.lang.String mode,
		java.lang.String language, java.lang.String script,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ddmTemplateService.addTemplate(groupId, structureId, nameMap,
			descriptionMap, type, mode, language, script, serviceContext);
	}

	public java.util.List<com.liferay.portlet.dynamicdatamapping.model.DDMTemplate> copyTemplates(
		long structureId, long newStructureId, java.lang.String type,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ddmTemplateService.copyTemplates(structureId, newStructureId,
			type, serviceContext);
	}

	public void deleteTemplate(long templateId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_ddmTemplateService.deleteTemplate(templateId);
	}

	public com.liferay.portlet.dynamicdatamapping.model.DDMTemplate getTemplate(
		long templateId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ddmTemplateService.getTemplate(templateId);
	}

	public java.util.List<com.liferay.portlet.dynamicdatamapping.model.DDMTemplate> getTemplates(
		long structureId, java.lang.String type, java.lang.String mode)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ddmTemplateService.getTemplates(structureId, type, mode);
	}

	public com.liferay.portlet.dynamicdatamapping.model.DDMTemplate updateTemplate(
		long templateId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String type, java.lang.String mode,
		java.lang.String language, java.lang.String script,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ddmTemplateService.updateTemplate(templateId, nameMap,
			descriptionMap, type, mode, language, script, serviceContext);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public DDMTemplateService getWrappedDDMTemplateService() {
		return _ddmTemplateService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedDDMTemplateService(
		DDMTemplateService ddmTemplateService) {
		_ddmTemplateService = ddmTemplateService;
	}

	public DDMTemplateService getWrappedService() {
		return _ddmTemplateService;
	}

	public void setWrappedService(DDMTemplateService ddmTemplateService) {
		_ddmTemplateService = ddmTemplateService;
	}

	private DDMTemplateService _ddmTemplateService;
}