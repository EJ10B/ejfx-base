package org.ejfx.core.util.resources;

import javafx.scene.image.Image;

import java.io.InputStream;
import java.util.Locale;
import java.util.ResourceBundle;

public abstract class ResourcesResolverBase implements ResourcesResolver {

    private static final String UNABLE_GET_FXML_AS_STREAM_FOR_NAME = "Unable get fxml as stream for [%s] name.";

    private static final String UNABLE_GET_ICON_FOR_NAME = "Unable get icon for [%s] name.";

    private static final String UNABLE_GET_RESOURCE_BUNDLE_FOR_NAME = "Unable get resource bundle for [%s] name.";

    @Override
    public final InputStream getFXMLAsStream(final String name) {
        final InputStream result;

        if (isNamed(name)) {
            final InputStream stream;

            try {
                stream = getNamedFXMLAsStream(name);
            } catch (final Exception e) {
                throw constructException(UNABLE_GET_FXML_AS_STREAM_FOR_NAME, name, e);
            }

            if (stream != null) {
                result = stream;
            } else {
                throw constructException(UNABLE_GET_FXML_AS_STREAM_FOR_NAME, name, null);
            }
        } else {
            result = null;
        }

        return result;
    }

    @Override
    public final Image getIcon(final String name) {
        final Image result;

        if (isNamed(name)) {
            final Image image;

            try (final InputStream stream = getNamedIconAsStream(name)) {
                image = (stream != null) ? new Image(stream) : null;
            } catch (final Exception e) {
                throw constructException(UNABLE_GET_ICON_FOR_NAME, name, e);
            }

            if (image != null) {
                result = image;
            } else {
                throw constructException(UNABLE_GET_ICON_FOR_NAME, name, null);
            }
        } else {
            result = null;
        }

        return result;
    }

    @Override
    public ResourceBundle getResourceBundle(final String name, final Locale locale) {
        final ResourceBundle result;

        if (isNamed(name)) {
            final ResourceBundle bundle;

            try {
                bundle = getNamedResourceBundle(name, locale);
            } catch (final Exception e) {
                throw constructException(UNABLE_GET_RESOURCE_BUNDLE_FOR_NAME, name, e);
            }

            if (bundle != null) {
                result = bundle;
            } else {
                throw constructException(UNABLE_GET_RESOURCE_BUNDLE_FOR_NAME, name, null);
            }
        } else {
            result = null;
        }

        return result;
    }

    protected abstract InputStream getNamedFXMLAsStream(final String name) throws Exception;

    protected abstract InputStream getNamedIconAsStream(final String name) throws Exception;

    protected abstract ResourceBundle getNamedResourceBundle(final String name, final Locale locale) throws Exception;

    private static boolean isNamed(final String name) {
        return (name != null && !name.isEmpty());
    }

    private static IllegalStateException constructException(final String message, final String name, final Throwable cause) {
        return (cause != null) ? new IllegalStateException(String.format(message, name), cause) :
                new IllegalStateException(String.format(message, name));
    }

}
