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

package com.liferay.portlet.dynamicdatamapping.action;

import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.struts.PortletAction;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.PortletURLImpl;
import com.liferay.portlet.dynamicdatamapping.NoSuchStructureException;
import com.liferay.portlet.dynamicdatamapping.StructureNameException;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplateConstants;
import com.liferay.portlet.dynamicdatamapping.service.DDMStructureServiceUtil;
import com.liferay.portlet.dynamicdatamapping.service.DDMTemplateServiceUtil;

import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Marcellus Tavares
 */
public class CopyStructureAction extends PortletAction {

	@Override
	public void processAction(
			ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			DDMStructure structure = copyStructure(actionRequest);

			String redirect = getSaveAndContinueRedirect(
				portletConfig, actionRequest, structure);

			String closeRedirect = ParamUtil.getString(
				actionRequest, "closeRedirect");

			if (Validator.isNotNull(closeRedirect)) {
				SessionMessages.add(
					actionRequest,
					portletConfig.getPortletName() +
						SessionMessages.KEY_SUFFIX_CLOSE_REDIRECT,
					closeRedirect);
			}

			sendRedirect(actionRequest, actionResponse, redirect);
		}
		catch (Exception e) {
			if (e instanceof NoSuchStructureException ||
				e instanceof PrincipalException) {

				SessionErrors.add(actionRequest, e.getClass().getName());

				setForward(actionRequest, "portlet.dynamic_data_mapping.error");
			}
			else if (e instanceof StructureNameException) {
				SessionErrors.add(actionRequest, e.getClass().getName(), e);
			}
			else {
				throw e;
			}
		}
	}

	@Override
	public ActionForward render(
			ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws Exception {

		try {
			ActionUtil.getStructure(renderRequest);
		}
		catch (NoSuchStructureException nsse) {

			// Let this slide because the user can manually input a structure
			// key for a new structure that does not yet exist

		}
		catch (Exception e) {
			if (e instanceof PrincipalException) {
				SessionErrors.add(renderRequest, e.getClass().getName());

				return mapping.findForward(
					"portlet.dynamic_data_mapping.error");
			}
			else {
				throw e;
			}
		}

		return mapping.findForward(
			getForward(
				renderRequest, "portlet.dynamic_data_mapping.copy_structure"));
	}

	protected DDMStructure copyStructure(ActionRequest actionRequest)
		throws Exception {

		long structureId = ParamUtil.getLong(actionRequest, "structureId");

		Map<Locale, String> nameMap = LocalizationUtil.getLocalizationMap(
			actionRequest, "name");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			DDMStructure.class.getName(), actionRequest);

		DDMStructure structure = DDMStructureServiceUtil.copyStructure(
			structureId, nameMap, null, serviceContext);

		copyTemplates(actionRequest, structureId, structure.getStructureId());

		return structure;
	}

	protected void copyTemplates(
			ActionRequest actionRequest, long structureId, long newStructureId)
		throws Exception {

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			DDMTemplate.class.getName(), actionRequest);

		boolean copyDetailTemplates = ParamUtil.getBoolean(
			actionRequest, "copyDetailTemplates");

		if (copyDetailTemplates) {
			DDMTemplateServiceUtil.copyTemplates(
				structureId, newStructureId,
				DDMTemplateConstants.TEMPLATE_TYPE_DETAIL, serviceContext);
		}

		boolean copyListTemplates = ParamUtil.getBoolean(
			actionRequest, "copyListTemplates");

		if (copyListTemplates) {
			DDMTemplateServiceUtil.copyTemplates(
				structureId, newStructureId,
				DDMTemplateConstants.TEMPLATE_TYPE_LIST, serviceContext);
		}
	}

	protected String getSaveAndContinueRedirect(
			PortletConfig portletConfig, ActionRequest actionRequest,
			DDMStructure structure)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PortletURLImpl portletURL = new PortletURLImpl(
			actionRequest, portletConfig.getPortletName(),
			themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);

		portletURL.setWindowState(actionRequest.getWindowState());

		portletURL.setParameter(
			"struts_action", "/dynamic_data_mapping/copy_structure");
		portletURL.setParameter(
			"structureId", String.valueOf(structure.getStructureId()),
			false);
		portletURL.setParameter(
			"copyDetailTemplates",
			ParamUtil.getString(actionRequest, "copyDetailTemplates"), false);
		portletURL.setParameter(
			"copyListTemplates",
			ParamUtil.getString(actionRequest, "copyListTemplates"), false);

		return portletURL.toString();
	}

}