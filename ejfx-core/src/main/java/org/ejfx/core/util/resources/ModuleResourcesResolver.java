package org.ejfx.core.util.resources;

import org.ejfx.core.util.Arguments;

import java.io.InputStream;
import java.util.Locale;
import java.util.ResourceBundle;

@SuppressWarnings("unused")
public final class ModuleResourcesResolver extends ResourcesResolverBase {

    private final Module module;

    private <T> ModuleResourcesResolver(final T value) {
        this.module = Arguments.requireNonNull(value, "value").getClass().getModule();
    }

    @Override
    protected InputStream getNamedFXMLAsStream(final String name) throws Exception {
        return module.getResourceAsStream(name);
    }

    @Override
    protected InputStream getNamedIconAsStream(final String name) throws Exception {
        return module.getResourceAsStream(name);
    }

    @Override
    protected ResourceBundle getNamedResourceBundle(final String name, final Locale locale) throws Exception {
        return ResourceBundle.getBundle(name, locale, module);
    }

    public static <T> ModuleResourcesResolver of(final T value) {
        return new ModuleResourcesResolver(value);
    }

}
