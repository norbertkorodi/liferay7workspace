package com.awe.vaadindemo.portlet;

import com.awe.awesome.service.model.Foo;
import com.awe.awesome.service.service.FooLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.*;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.osgi.service.component.annotations.Component;

import javax.portlet.*;
import java.time.Instant;
import java.util.Date;

@Widgetset(AppWidgetSet.NAME)
//@Theme("mytheme")
@SuppressWarnings("serial")
@Component(service = UI.class, property = {
  "com.liferay.portlet.display-category=category.Awesome",
  "javax.portlet.name=My_Vaadin_8.1_Portlet",
  "javax.portlet.display-name=Awesome Liferay 7 Vaadin portlet",
  "javax.portlet.security-role-ref=power-user,user",
  "com.vaadin.osgi.liferay.portlet-ui=true"})
public class MyPortletUI extends UI implements VaadinPortletSession.PortletListener {

  private static Log LOG = LogFactoryUtil.getLog(MyPortletUI.class);

  TextField tf = new TextField("TF");

  VerticalLayout viewContent = new VerticalLayout();
  VerticalLayout editContent = new VerticalLayout();
  VerticalLayout helpContent = new VerticalLayout();
  //  PortletMode customMode;
  private Label viewText;
  private final HorizontalLayout horizontalLayout = new HorizontalLayout();

  @Override
  protected void init(VaadinRequest request) {

    final ColorPicker colorPicker = new ColorPicker();

    FormLayout formlayout = new FormLayout();
    formlayout.setMargin(true);
    formlayout.addStyleName("outlined");
    formlayout.setSizeFull();

    final DateField child1 = new DateField("Child 1");
    child1.setWidth(100.0f, Unit.PERCENTAGE);
    formlayout.addComponent(child1);

    final TextField child2 = new TextField("Child 2", "");
    child2.setWidth(100.0f, Unit.PERCENTAGE);
    formlayout.addComponent(child2);

    formlayout.addComponent(new CheckBox("Child 3"));
    formlayout.addComponent(new Button("Child 4"));

    horizontalLayout.addComponent(colorPicker);
    BrowserFrame browserFrame = new BrowserFrame("vaadin.com", new ExternalResource("https://vaadin.com/home"));
    browserFrame.setSizeFull();
    horizontalLayout.addComponent(browserFrame);

    final VerticalLayout verticalLayout = new VerticalLayout();
    final VerticalLayout verticalLayout2 = new VerticalLayout();
    horizontalLayout.addComponent(verticalLayout);
    verticalLayout.setSpacing(false);

    final RichTextArea richtext = new RichTextArea();
    verticalLayout.addComponent(richtext);
    verticalLayout.addComponent(formlayout);

    // Data model
    String data = "<h1>Heading</h1> <p>Some example content</p>";

    // View mode content
    viewText = new Label("Simple Label", ContentMode.HTML);
    viewContent.addComponent(viewText);
    viewContent.addComponent(tf);

    // Edit mode content
    RichTextArea editText = new RichTextArea();
    editText.setCaption("Edit the value:");
    editText.setData(data);
    editContent.addComponent(editText);

    // Help mode content
    Label helpText = new Label("<h1>Help</h1>" +
      "<p>This helps you.</p>",
      ContentMode.HTML);
    helpContent.addComponent(helpText);

    // Custom mode content
    Label customText = new Label("");

    // Start in the view mode
//    setContent(viewContent);
    WrappedSession wrappedSession = request.getWrappedSession();
    horizontalLayout.addComponent(viewContent);

    verticalLayout.addComponent(customText);
    setContent(horizontalLayout);

    MyPortletUI thisComponent = this;

    // Define the custom mode and a button to switch to it
    viewContent.addComponent(new Button("Awesome mode",
      new Button.ClickListener() {
        private static final long serialVersionUID = 7260317716523284153L;

        public void buttonClick(Button.ClickEvent event) {
          String tfValue = "tf.getvalue: " + tf.getValue();
//          LOG.info(tfValue);
          viewText.setValue(tfValue);

          String awesomeParam = "awesomeParam";
          wrappedSession.setAttribute(awesomeParam, tfValue);
          customText.setValue(String.valueOf(wrappedSession.getAttribute(awesomeParam)));

          Foo foo = FooLocalServiceUtil.createFoo(FooLocalServiceUtil.getFoosCount() + 1);
          foo.setField1("field1");
          foo.setField2(true);
          foo.setField3(4);
          foo.setField4(Date.from(Instant.now()));
          FooLocalServiceUtil.addFoo(foo);
//          LOG.info(foo);
        }
      }));

//    // Check that we are running as a portlet.
    if (VaadinSession.getCurrent() instanceof VaadinPortletSession) {
      VaadinPortletSession portletsession =
        (VaadinPortletSession) VaadinSession.getCurrent();

      // Add a custom listener to handle action and
      // render requests.
      portletsession.addPortletListener(thisComponent);
      LOG.info(this.getClass() + " is running as portlet.");
    } else {
    }
  }

  @Override
  public void handleRenderRequest(RenderRequest request,
                                  RenderResponse response, UI root) {
    if (viewText != null) {
      viewText.setValue("RenderRequest. Portlet mode: " + request.getPortletMode());
    }
    LOG.error("RenderRequest");
    LOG.error("Portlet mode: " + request.getPortletMode());
  }

  @Override
  public void handleActionRequest(ActionRequest request,
                                  ActionResponse response, UI root) {
    if (viewText != null) {
      viewText.setValue("ActionRequest. Portlet mode: " + request.getPortletMode());
    }
    LOG.error("ActionRequest");
    LOG.error("Portlet mode: " + request.getPortletMode());
  }

  @Override
  public void handleEventRequest(EventRequest request,
                                 EventResponse response, UI root) {
    if (viewText != null) {
      viewText.setValue("EventRequest. Portlet mode: " + request.getPortletMode());
    }
    LOG.error("EventRequest");
    LOG.error("Portlet mode: " + request.getPortletMode());
  }

  @Override
  public void handleResourceRequest(ResourceRequest request,
                                    ResourceResponse response, UI root) {
    if (request.getPortletMode() == PortletMode.EDIT)
      setContent(editContent);
    else if (request.getPortletMode() == PortletMode.VIEW)
      setContent(horizontalLayout);
    else if (request.getPortletMode() == PortletMode.HELP)
      setContent(helpContent);
  }

}

