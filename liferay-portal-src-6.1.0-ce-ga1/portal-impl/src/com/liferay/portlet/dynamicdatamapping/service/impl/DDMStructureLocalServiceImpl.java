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

package com.liferay.portlet.dynamicdatamapping.service.impl;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.kernel.xml.XPath;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.dynamicdatamapping.RequiredStructureException;
import com.liferay.portlet.dynamicdatamapping.StructureDuplicateElementException;
import com.liferay.portlet.dynamicdatamapping.StructureDuplicateStructureKeyException;
import com.liferay.portlet.dynamicdatamapping.StructureNameException;
import com.liferay.portlet.dynamicdatamapping.StructureXsdException;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructureConstants;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplateConstants;
import com.liferay.portlet.dynamicdatamapping.service.base.DDMStructureLocalServiceBaseImpl;
import com.liferay.portlet.dynamicdatamapping.util.DDMXMLUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 * @author Bruno Basto
 * @author Marcellus Tavares
 */
public class DDMStructureLocalServiceImpl
	extends DDMStructureLocalServiceBaseImpl {

	public DDMStructure addStructure(
			long userId, long groupId, long classNameId, String structureKey,
			Map<Locale, String> nameMap, Map<Locale, String> descriptionMap,
			String xsd, String storageType, int type,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Structure

		User user = userPersistence.findByPrimaryKey(userId);

		if (Validator.isNull(structureKey)) {
			structureKey = String.valueOf(counterLocalService.increment());
		}

		try {
			xsd = DDMXMLUtil.formatXML(xsd);
		}
		catch (Exception e) {
			throw new StructureXsdException();
		}

		Date now = new Date();

		validate(groupId, structureKey, nameMap, xsd);

		long structureId = counterLocalService.increment();

		DDMStructure structure = ddmStructurePersistence.create(structureId);

		structure.setUuid(serviceContext.getUuid());
		structure.setGroupId(serviceContext.getScopeGroupId());
		structure.setCompanyId(user.getCompanyId());
		structure.setUserId(user.getUserId());
		structure.setUserName(user.getFullName());
		structure.setCreateDate(serviceContext.getCreateDate(now));
		structure.setModifiedDate(serviceContext.getModifiedDate(now));
		structure.setClassNameId(classNameId);
		structure.setStructureKey(structureKey);
		structure.setNameMap(nameMap);
		structure.setDescriptionMap(descriptionMap);
		structure.setXsd(xsd);
		structure.setStorageType(storageType);
		structure.setType(type);

		ddmStructurePersistence.update(structure, false);

		// Resources

		if (serviceContext.isAddGroupPermissions() ||
			serviceContext.isAddGuestPermissions()) {

			addStructureResources(
				structure, serviceContext.isAddGroupPermissions(),
				serviceContext.isAddGuestPermissions());
		}
		else {
			addStructureResources(
				structure, serviceContext.getGroupPermissions(),
				serviceContext.getGuestPermissions());
		}

		return structure;
	}

	public void addStructureResources(
			DDMStructure structure, boolean addGroupPermissions,
			boolean addGuestPermissions)
		throws PortalException, SystemException {

		resourceLocalService.addResources(
			structure.getCompanyId(), structure.getGroupId(),
			structure.getUserId(), DDMStructure.class.getName(),
			structure.getStructureId(), false, addGroupPermissions,
			addGuestPermissions);
	}

	public void addStructureResources(
			DDMStructure structure, String[] groupPermissions,
			String[] guestPermissions)
		throws PortalException, SystemException {

		resourceLocalService.addModelResources(
			structure.getCompanyId(), structure.getGroupId(),
			structure.getUserId(), DDMStructure.class.getName(),
			structure.getStructureId(), groupPermissions, guestPermissions);
	}

	public DDMStructure copyStructure(
			long userId, long structureId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, ServiceContext serviceContext)
		throws PortalException, SystemException {

		DDMStructure structure = getStructure(structureId);

		return addStructure(
			userId, structure.getGroupId(), structure.getClassNameId(), null,
			nameMap, descriptionMap, structure.getXsd(),
			structure.getStorageType(), structure.getType(), serviceContext);
	}

	public void deleteStructure(DDMStructure structure)
		throws PortalException, SystemException {

		if (ddmStructureLinkPersistence.countByStructureId(
				structure.getStructureId()) > 0) {

			throw new RequiredStructureException();
		}

		// Structure

		ddmStructurePersistence.remove(structure);

		// Resources

		resourceLocalService.deleteResource(
			structure.getCompanyId(), DDMStructure.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL, structure.getStructureId());
	}

	public void deleteStructure(long structureId)
		throws PortalException, SystemException {

		DDMStructure structure = ddmStructurePersistence.findByPrimaryKey(
			structureId);

		deleteStructure(structure);
	}

	public void deleteStructure(long groupId, String structureKey)
		throws PortalException, SystemException {

		DDMStructure structure =
			ddmStructurePersistence.findByG_S(groupId, structureKey);

		deleteStructure(structure);
	}

	public void deleteStructures(long groupId)
		throws PortalException, SystemException {

		List<DDMStructure> structures = ddmStructurePersistence.findByGroupId(
			groupId);

		for (DDMStructure structure : structures) {
			deleteStructure(structure);
		}
	}

	public DDMStructure fetchStructure(long structureId)
		throws SystemException {

		return ddmStructurePersistence.fetchByPrimaryKey(structureId);
	}

	public DDMStructure fetchStructure(long groupId, String structureKey)
		throws SystemException {

		return ddmStructurePersistence.fetchByG_S(groupId, structureKey);
	}

	public List<DDMStructure> getClassStructures(long classNameId)
		throws SystemException {

		return ddmStructurePersistence.findByClassNameId(classNameId);
	}

	public List<DDMStructure> getClassStructures(
			long classNameId, int start, int end)
		throws SystemException {

		return ddmStructurePersistence.findByClassNameId(
			classNameId, start, end);
	}

	public List<DDMStructure> getClassStructures(
			long classNameId, OrderByComparator orderByComparator)
		throws SystemException {

		return ddmStructurePersistence.findByClassNameId(
			classNameId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			orderByComparator);
	}

	public List<DDMStructure> getDLFileEntryTypeStructures(
			long dlFileEntryTypeId)
		throws SystemException {

		return dlFileEntryTypePersistence.getDDMStructures(dlFileEntryTypeId);
	}

	public DDMStructure getStructure(long structureId)
		throws PortalException, SystemException {

		return ddmStructurePersistence.findByPrimaryKey(structureId);
	}

	public DDMStructure getStructure(long groupId, String structureKey)
		throws PortalException, SystemException {

		return ddmStructurePersistence.findByG_S(groupId, structureKey);
	}

	public List<DDMStructure> getStructure(
			long groupId, String name, String description)
		throws SystemException {

		return ddmStructurePersistence.findByG_N_D(groupId, name, description);
	}

	public List<DDMStructure> getStructureEntries() throws SystemException {
		return ddmStructurePersistence.findAll();
	}

	public List<DDMStructure> getStructureEntries(long groupId)
		throws SystemException {

		return ddmStructurePersistence.findByGroupId(groupId);
	}

	public List<DDMStructure> getStructureEntries(
			long groupId, int start, int end)
		throws SystemException {

		return ddmStructurePersistence.findByGroupId(groupId, start, end);
	}

	public int getStructureEntriesCount(long groupId) throws SystemException {
		return ddmStructurePersistence.countByGroupId(groupId);
	}

	public List<DDMStructure> search(
			long companyId, long[] groupIds, long[] classNameIds,
			String keywords, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return ddmStructureFinder.findByKeywords(
			companyId, groupIds, classNameIds, keywords, start, end,
			orderByComparator);
	}

	public List<DDMStructure> search(
			long companyId, long[] groupIds, long[] classNameIds, String name,
			String description, String storageType, int type,
			boolean andOperator, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return ddmStructureFinder.findByC_G_C_N_D_S_T(
			companyId, groupIds, classNameIds, name, description, storageType,
			type, andOperator, start, end, orderByComparator);
	}

	public int searchCount(
			long companyId, long[] groupIds, long[] classNameIds,
			String keywords)
		throws SystemException {

		return ddmStructureFinder.countByKeywords(
			companyId, groupIds, classNameIds, keywords);
	}

	public int searchCount(
			long companyId, long[] groupIds, long[] classNameIds, String name,
			String description, String storageType, int type,
			boolean andOperator)
		throws SystemException {

		return ddmStructureFinder.countByC_G_C_N_D_S_T(
			companyId, groupIds, classNameIds, name, description, storageType,
			type, andOperator);
	}

	public DDMStructure updateStructure(
			long structureId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String xsd,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		DDMStructure structure = ddmStructurePersistence.findByPrimaryKey(
			structureId);

		return doUpdateStructure(
			nameMap, descriptionMap, xsd, serviceContext, structure);
	}

	public DDMStructure updateStructure(
			long groupId, String structureKey, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String xsd,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		DDMStructure structure = ddmStructurePersistence.findByG_S(
			groupId, structureKey);

		return doUpdateStructure(
			nameMap, descriptionMap, xsd, serviceContext, structure);
	}

	protected void appendNewStructureRequiredFields(
		DDMStructure structure, Document templateDocument) {

		String xsd = structure.getXsd();

		Document structureDocument = null;

		try {
			structureDocument = SAXReaderUtil.read(xsd);
		}
		catch (DocumentException de) {
			if (_log.isWarnEnabled()) {
				_log.warn(de, de);
			}

			return;
		}

		Element templateElement = templateDocument.getRootElement();

		XPath structureXPath = SAXReaderUtil.createXPath(
			"//dynamic-element[.//meta-data/entry[@name=\"required\"]=" +
				"\"true\"]");

		List<Node> nodes = structureXPath.selectNodes(structureDocument);

		Iterator<Node> itr = nodes.iterator();

		while (itr.hasNext()) {
			Element element = (Element)itr.next();

			String name = element.attributeValue("name");

			XPath templateXPath = SAXReaderUtil.createXPath(
				"//dynamic-element[@name=\"" + name + "\"]");

			if (!templateXPath.booleanValueOf(templateDocument)) {
				templateElement.add(element.createCopy());
			}
		}
	}

	protected DDMStructure doUpdateStructure(
			Map<Locale, String> nameMap, Map<Locale, String> descriptionMap,
			String xsd, ServiceContext serviceContext, DDMStructure structure)
		throws PortalException, SystemException {

		try {
			xsd = DDMXMLUtil.formatXML(xsd);
		}
		catch (Exception e) {
			throw new StructureXsdException();
		}

		validate(nameMap, xsd);

		structure.setModifiedDate(serviceContext.getModifiedDate(null));
		structure.setNameMap(nameMap);
		structure.setDescriptionMap(descriptionMap);
		structure.setXsd(xsd);

		ddmStructurePersistence.update(structure, false);

		syncStructureTemplatesFields(structure);

		return structure;
	}

	protected void syncStructureTemplatesFields(DDMStructure structure)
		throws PortalException, SystemException {

		List<DDMTemplate> templates = ddmTemplateLocalService.getTemplates(
			structure.getStructureId(),
			DDMTemplateConstants.TEMPLATE_TYPE_DETAIL);

		for (DDMTemplate template : templates) {
			String script = template.getScript();

			Document templateDocument = null;

			try {
				templateDocument = SAXReaderUtil.read(script);
			}
			catch (DocumentException de) {
				if (_log.isWarnEnabled()) {
					_log.warn(de, de);
				}

				continue;
			}

			Element templateRootElement = templateDocument.getRootElement();

			syncStructureTemplatesFields(template, templateRootElement);

			appendNewStructureRequiredFields(structure, templateDocument);

			try {
				script = DDMXMLUtil.formatXML(templateDocument.asXML());
			}
			catch (Exception e) {
				throw new StructureXsdException();
			}

			template.setScript(script);

			ddmTemplatePersistence.update(template, false);
		}
	}

	protected void syncStructureTemplatesFields(
			DDMTemplate template, Element templateElement)
		throws PortalException, SystemException {

		DDMStructure structure = template.getStructure();

		List<Element> dynamicElementElements = templateElement.elements(
			"dynamic-element");

		for (Element dynamicElementElement : dynamicElementElements) {
			String dataType = dynamicElementElement.attributeValue("dataType");
			String fieldName = dynamicElementElement.attributeValue("name");

			if (Validator.isNull(dataType)) {
				continue;
			}

			if (!structure.hasField(fieldName)) {
				templateElement.remove(dynamicElementElement);

				continue;
			}

			String mode = template.getMode();

			if (mode.equals(DDMTemplateConstants.TEMPLATE_MODE_CREATE)) {
				boolean fieldRequired = structure.getFieldRequired(fieldName);

				List<Element> metadataElements = dynamicElementElement.elements(
					"meta-data");

				for (Element metadataElement : metadataElements) {
					for (Element metadataEntryElement :
							metadataElement.elements()) {

						String attributeName =
							metadataEntryElement.attributeValue("name");

						if (fieldRequired && attributeName.equals("required")) {
							metadataEntryElement.setText("true");
						}
					}
				}
			}

			syncStructureTemplatesFields(template, dynamicElementElement);
		}
	}

	protected void validate(List<Element> elements, Set<String> names)
		throws PortalException {

		for (Element element : elements) {
			String elementName = element.getName();

			if (elementName.equals("meta-data")) {
				continue;
			}

			String name = element.attributeValue("name", StringPool.BLANK);
			String type = element.attributeValue("type", StringPool.BLANK);

			if (Validator.isNull(name) ||
				name.startsWith(DDMStructureConstants.XSD_NAME_RESERVED)) {

				throw new StructureXsdException();
			}

			char[] charArray = name.toCharArray();

			for (int i = 0; i < charArray.length; i++) {
				if (!Character.isLetterOrDigit(charArray[i]) &&
					(charArray[i] != CharPool.DASH) &&
					(charArray[i] != CharPool.UNDERLINE)) {

					throw new StructureXsdException();
				}
			}

			String path = name;

			Element parentElement = element.getParent();

			while (!parentElement.isRootElement()) {
				path =
					parentElement.attributeValue("name", StringPool.BLANK) +
						StringPool.SLASH + path;

				parentElement = parentElement.getParent();
			}

			path = path.toLowerCase();

			if (names.contains(path)) {
				throw new StructureDuplicateElementException();
			}
			else {
				names.add(path);
			}

			if (Validator.isNull(type)) {
				throw new StructureXsdException();
			}

			validate(element.elements(), names);
		}
	}

	protected void validate(
			long groupId, String structureKey, Map<Locale, String> nameMap,
			String xsd)
		throws PortalException, SystemException {

		DDMStructure structure = ddmStructurePersistence.fetchByG_S(
			groupId, structureKey);

		if (structure != null) {
			throw new StructureDuplicateStructureKeyException();
		}

		validate(nameMap, xsd);
	}

	protected void validate(Map<Locale, String> nameMap, String xsd)
		throws PortalException {

		validateName(nameMap);

		if (Validator.isNull(xsd)) {
			throw new StructureXsdException();
		}
		else {
			try {
				List<Element> elements = new ArrayList<Element>();

				Document document = SAXReaderUtil.read(xsd);

				Element rootElement = document.getRootElement();

				if (rootElement.elements().isEmpty()) {
					throw new StructureXsdException();
				}

				elements.addAll(rootElement.elements());

				Set<String> elNames = new HashSet<String>();

				validate(elements, elNames);
			}
			catch (StructureDuplicateElementException fdsee) {
				throw fdsee;
			}
			catch (StructureXsdException sxe) {
				throw sxe;
			}
			catch (Exception e) {
				throw new StructureXsdException();
			}
		}
	}

	protected void validateName(Map<Locale, String> nameMap)
		throws PortalException {

		String name = nameMap.get(LocaleUtil.getDefault());

		if (Validator.isNull(name)) {
			throw new StructureNameException();
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		DDMStructureLocalServiceImpl.class);

}