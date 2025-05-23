package org.ejfx.core.util.resources;

import javafx.scene.image.Image;

import java.io.InputStream;
import java.util.Locale;
import java.util.ResourceBundle;

public interface ResourcesResolver {

    InputStream getFXMLAsStream(String name);

    Image getIcon(String name);

    ResourceBundle getResourceBundle(String name, Locale locale);

}
