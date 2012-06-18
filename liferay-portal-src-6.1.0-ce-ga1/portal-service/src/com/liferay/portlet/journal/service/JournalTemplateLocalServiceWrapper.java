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

package com.liferay.portlet.journal.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link JournalTemplateLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       JournalTemplateLocalService
 * @generated
 */
public class JournalTemplateLocalServiceWrapper
	implements JournalTemplateLocalService,
		ServiceWrapper<JournalTemplateLocalService> {
	public JournalTemplateLocalServiceWrapper(
		JournalTemplateLocalService journalTemplateLocalService) {
		_journalTemplateLocalService = journalTemplateLocalService;
	}

	/**
	* Adds the journal template to the database. Also notifies the appropriate model listeners.
	*
	* @param journalTemplate the journal template
	* @return the journal template that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.journal.model.JournalTemplate addJournalTemplate(
		com.liferay.portlet.journal.model.JournalTemplate journalTemplate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _journalTemplateLocalService.addJournalTemplate(journalTemplate);
	}

	/**
	* Creates a new journal template with the primary key. Does not add the journal template to the database.
	*
	* @param id the primary key for the new journal template
	* @return the new journal template
	*/
	public com.liferay.portlet.journal.model.JournalTemplate createJournalTemplate(
		long id) {
		return _journalTemplateLocalService.createJournalTemplate(id);
	}

	/**
	* Deletes the journal template with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the journal template
	* @throws PortalException if a journal template with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteJournalTemplate(long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_journalTemplateLocalService.deleteJournalTemplate(id);
	}

	/**
	* Deletes the journal template from the database. Also notifies the appropriate model listeners.
	*
	* @param journalTemplate the journal template
	* @throws SystemException if a system exception occurred
	*/
	public void deleteJournalTemplate(
		com.liferay.portlet.journal.model.JournalTemplate journalTemplate)
		throws com.liferay.portal.kernel.exception.SystemException {
		_journalTemplateLocalService.deleteJournalTemplate(journalTemplate);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _journalTemplateLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _journalTemplateLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _journalTemplateLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _journalTemplateLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.portlet.journal.model.JournalTemplate fetchJournalTemplate(
		long id) throws com.liferay.portal.kernel.exception.SystemException {
		return _journalTemplateLocalService.fetchJournalTemplate(id);
	}

	/**
	* Returns the journal template with the primary key.
	*
	* @param id the primary key of the journal template
	* @return the journal template
	* @throws PortalException if a journal template with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.journal.model.JournalTemplate getJournalTemplate(
		long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _journalTemplateLocalService.getJournalTemplate(id);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _journalTemplateLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the journal template with the UUID in the group.
	*
	* @param uuid the UUID of journal template
	* @param groupId the group id of the journal template
	* @return the journal template
	* @throws PortalException if a journal template with the UUID in the group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.journal.model.JournalTemplate getJournalTemplateByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _journalTemplateLocalService.getJournalTemplateByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the journal templates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of journal templates
	* @param end the upper bound of the range of journal templates (not inclusive)
	* @return the range of journal templates
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portlet.journal.model.JournalTemplate> getJournalTemplates(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _journalTemplateLocalService.getJournalTemplates(start, end);
	}

	/**
	* Returns the number of journal templates.
	*
	* @return the number of journal templates
	* @throws SystemException if a system exception occurred
	*/
	public int getJournalTemplatesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _journalTemplateLocalService.getJournalTemplatesCount();
	}

	/**
	* Updates the journal template in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param journalTemplate the journal template
	* @return the journal template that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.journal.model.JournalTemplate updateJournalTemplate(
		com.liferay.portlet.journal.model.JournalTemplate journalTemplate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _journalTemplateLocalService.updateJournalTemplate(journalTemplate);
	}

	/**
	* Updates the journal template in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param journalTemplate the journal template
	* @param merge whether to merge the journal template with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the journal template that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.journal.model.JournalTemplate updateJournalTemplate(
		com.liferay.portlet.journal.model.JournalTemplate journalTemplate,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _journalTemplateLocalService.updateJournalTemplate(journalTemplate,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _journalTemplateLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_journalTemplateLocalService.setBeanIdentifier(beanIdentifier);
	}

	public com.liferay.portlet.journal.model.JournalTemplate addTemplate(
		long userId, long groupId, java.lang.String templateId,
		boolean autoTemplateId, java.lang.String structureId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String xsl, boolean formatXsl, java.lang.String langType,
		boolean cacheable, boolean smallImage, java.lang.String smallImageURL,
		java.io.File smallImageFile,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _journalTemplateLocalService.addTemplate(userId, groupId,
			templateId, autoTemplateId, structureId, nameMap, descriptionMap,
			xsl, formatXsl, langType, cacheable, smallImage, smallImageURL,
			smallImageFile, serviceContext);
	}

	public void addTemplateResources(long groupId, java.lang.String templateId,
		boolean addGroupPermissions, boolean addGuestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_journalTemplateLocalService.addTemplateResources(groupId, templateId,
			addGroupPermissions, addGuestPermissions);
	}

	public void addTemplateResources(
		com.liferay.portlet.journal.model.JournalTemplate template,
		boolean addGroupPermissions, boolean addGuestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_journalTemplateLocalService.addTemplateResources(template,
			addGroupPermissions, addGuestPermissions);
	}

	public void addTemplateResources(long groupId, java.lang.String templateId,
		java.lang.String[] groupPermissions, java.lang.String[] guestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_journalTemplateLocalService.addTemplateResources(groupId, templateId,
			groupPermissions, guestPermissions);
	}

	public void addTemplateResources(
		com.liferay.portlet.journal.model.JournalTemplate template,
		java.lang.String[] groupPermissions, java.lang.String[] guestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_journalTemplateLocalService.addTemplateResources(template,
			groupPermissions, guestPermissions);
	}

	public void checkNewLine(long groupId, java.lang.String templateId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_journalTemplateLocalService.checkNewLine(groupId, templateId);
	}

	public com.liferay.portlet.journal.model.JournalTemplate copyTemplate(
		long userId, long groupId, java.lang.String oldTemplateId,
		java.lang.String newTemplateId, boolean autoTemplateId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _journalTemplateLocalService.copyTemplate(userId, groupId,
			oldTemplateId, newTemplateId, autoTemplateId);
	}

	public void deleteTemplate(long groupId, java.lang.String templateId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_journalTemplateLocalService.deleteTemplate(groupId, templateId);
	}

	public void deleteTemplate(
		com.liferay.portlet.journal.model.JournalTemplate template)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_journalTemplateLocalService.deleteTemplate(template);
	}

	public void deleteTemplates(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_journalTemplateLocalService.deleteTemplates(groupId);
	}

	public java.util.List<com.liferay.portlet.journal.model.JournalTemplate> getStructureTemplates(
		long groupId, java.lang.String structureId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _journalTemplateLocalService.getStructureTemplates(groupId,
			structureId);
	}

	public java.util.List<com.liferay.portlet.journal.model.JournalTemplate> getStructureTemplates(
		long groupId, java.lang.String structureId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _journalTemplateLocalService.getStructureTemplates(groupId,
			structureId, start, end);
	}

	public int getStructureTemplatesCount(long groupId,
		java.lang.String structureId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _journalTemplateLocalService.getStructureTemplatesCount(groupId,
			structureId);
	}

	public com.liferay.portlet.journal.model.JournalTemplate getTemplate(
		long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _journalTemplateLocalService.getTemplate(id);
	}

	public com.liferay.portlet.journal.model.JournalTemplate getTemplate(
		long groupId, java.lang.String templateId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _journalTemplateLocalService.getTemplate(groupId, templateId);
	}

	public com.liferay.portlet.journal.model.JournalTemplate getTemplateBySmallImageId(
		long smallImageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _journalTemplateLocalService.getTemplateBySmallImageId(smallImageId);
	}

	public java.util.List<com.liferay.portlet.journal.model.JournalTemplate> getTemplates()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _journalTemplateLocalService.getTemplates();
	}

	public java.util.List<com.liferay.portlet.journal.model.JournalTemplate> getTemplates(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _journalTemplateLocalService.getTemplates(groupId);
	}

	public java.util.List<com.liferay.portlet.journal.model.JournalTemplate> getTemplates(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _journalTemplateLocalService.getTemplates(groupId, start, end);
	}

	public int getTemplatesCount(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _journalTemplateLocalService.getTemplatesCount(groupId);
	}

	public boolean hasTemplate(long groupId, java.lang.String templateId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _journalTemplateLocalService.hasTemplate(groupId, templateId);
	}

	public java.util.List<com.liferay.portlet.journal.model.JournalTemplate> search(
		long companyId, long[] groupIds, java.lang.String keywords,
		java.lang.String structureId, java.lang.String structureIdComparator,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _journalTemplateLocalService.search(companyId, groupIds,
			keywords, structureId, structureIdComparator, start, end, obc);
	}

	public java.util.List<com.liferay.portlet.journal.model.JournalTemplate> search(
		long companyId, long[] groupIds, java.lang.String templateId,
		java.lang.String structureId, java.lang.String structureIdComparator,
		java.lang.String name, java.lang.String description,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _journalTemplateLocalService.search(companyId, groupIds,
			templateId, structureId, structureIdComparator, name, description,
			andOperator, start, end, obc);
	}

	public int searchCount(long companyId, long[] groupIds,
		java.lang.String keywords, java.lang.String structureId,
		java.lang.String structureIdComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _journalTemplateLocalService.searchCount(companyId, groupIds,
			keywords, structureId, structureIdComparator);
	}

	public int searchCount(long companyId, long[] groupIds,
		java.lang.String templateId, java.lang.String structureId,
		java.lang.String structureIdComparator, java.lang.String name,
		java.lang.String description, boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _journalTemplateLocalService.searchCount(companyId, groupIds,
			templateId, structureId, structureIdComparator, name, description,
			andOperator);
	}

	public com.liferay.portlet.journal.model.JournalTemplate updateTemplate(
		long groupId, java.lang.String templateId,
		java.lang.String structureId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String xsl, boolean formatXsl, java.lang.String langType,
		boolean cacheable, boolean smallImage, java.lang.String smallImageURL,
		java.io.File smallImageFile,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _journalTemplateLocalService.updateTemplate(groupId, templateId,
			structureId, nameMap, descriptionMap, xsl, formatXsl, langType,
			cacheable, smallImage, smallImageURL, smallImageFile, serviceContext);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public JournalTemplateLocalService getWrappedJournalTemplateLocalService() {
		return _journalTemplateLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedJournalTemplateLocalService(
		JournalTemplateLocalService journalTemplateLocalService) {
		_journalTemplateLocalService = journalTemplateLocalService;
	}

	public JournalTemplateLocalService getWrappedService() {
		return _journalTemplateLocalService;
	}

	public void setWrappedService(
		JournalTemplateLocalService journalTemplateLocalService) {
		_journalTemplateLocalService = journalTemplateLocalService;
	}

	private JournalTemplateLocalService _journalTemplateLocalService;
}