package com.awe.foouserportlet.portlet;

import com.awe.awesome.service.service.FooLocalServiceUtil;
import com.awe.foouserportlet.constants.FooUserPortletKeys;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.io.IOException;

/**
 * @author norbert.korodi
 */
@Component(
  immediate = true,
  property = {
    "com.liferay.portlet.display-category=category.Awesome",
    "com.liferay.portlet.instanceable=true",
    "javax.portlet.display-name=my-awesome-foouser-portlet Portlet",
    "javax.portlet.init-param.template-path=/",
    "javax.portlet.init-param.view-template=/view.jsp",
    "javax.portlet.name=" + FooUserPortletKeys.FooUser,
    "javax.portlet.resource-bundle=content.Language",
    "javax.portlet.security-role-ref=power-user,user"
  },
  service = Portlet.class
)
public class FooUserPortlet extends MVCPortlet {
  private static Log LOG = LogFactoryUtil.getLog("com.awe.foouserportlet.portlet.FooUserPortlet");

  @Override
  public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
    LOG.info("-----NORBI-----");
    LOG.info("Number of Foos: " + FooLocalServiceUtil.getFoosCount());
    super.doView(renderRequest, renderResponse);
  }
}
