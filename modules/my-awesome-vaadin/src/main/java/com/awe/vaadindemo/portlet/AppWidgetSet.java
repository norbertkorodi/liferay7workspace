package com.awe.vaadindemo.portlet;

import com.vaadin.osgi.resources.OsgiVaadinWidgetset;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = OsgiVaadinWidgetset.class)
public class AppWidgetSet implements OsgiVaadinWidgetset {

//    public static final String NAME = "org.vaadin.liferay.example.AppWidgetSet";
    public static final String NAME = "com.awe.vaadindemo.portlet.AppWidgetSet";

    @Override
    public String getName() {
        return NAME;
    }

}
