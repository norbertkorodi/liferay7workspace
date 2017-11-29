package com.nor.awesome.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import com.nor.awesome.constants.AwesomePortletKeys;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author norbert.korodi
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.Awesome",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=my-awesome-project Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + AwesomePortletKeys.Awesome,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class AwesomePortlet extends MVCPortlet {
}