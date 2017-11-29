package com.awe.vaadindemo.portlet;

import com.vaadin.osgi.resources.OsgiVaadinTheme;
import com.vaadin.ui.themes.ValoTheme;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = OsgiVaadinTheme.class)
public class AppTheme extends ValoTheme implements OsgiVaadinTheme {
    @Override
    public String getName() {
        return "mytheme";
    }

}
