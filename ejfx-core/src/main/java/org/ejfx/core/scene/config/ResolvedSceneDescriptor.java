package org.ejfx.core.scene.config;

import org.ejfx.core.util.Arguments;
import org.ejfx.core.util.resources.ResourcesResolver;

import java.io.InputStream;
import java.util.Locale;
import java.util.ResourceBundle;

public class ResolvedSceneDescriptor extends ResolvedDescriptorBase implements AutoCloseable {

    private final ResolvedStageDescriptor stageDescriptor;

    private final InputStream fxmlAsStream;

    private final Class<?> controller;

    private ResolvedSceneDescriptor(final DefinedSceneDescriptor descriptor,
                                    final ResourcesResolver resolver,
                                    final Locale locale) {
        super(Arguments.requireNonNull(descriptor, "descriptor").getName(),
                loadResources(Arguments.requireNonNull(resolver, "resolver"),
                        descriptor.getResources(),
                        Arguments.requireNonNull(locale, "locale")));

        this.stageDescriptor = ResolvedStageDescriptor.of(descriptor.getStageDescriptor(), getResources());
        this.fxmlAsStream = loadFXML(resolver, descriptor.getFXML());
        this.controller = descriptor.getController();
    }

    public InputStream getFXMLAsStream() {
        return fxmlAsStream;
    }

    public Class<?> getController() {
        return controller;
    }

    @Override
    public void close() throws Exception {
        fxmlAsStream.close();
    }

    public ResolvedStageDescriptor getStageDescriptor() {
        return stageDescriptor;
    }

    private static InputStream loadFXML(final ResourcesResolver resolver, final String fxml) {
        return resolver.getFXMLAsStream(fxml);
    }

    private static ResourceBundle loadResources(final ResourcesResolver resolver,
                                                final String resources,
                                                final Locale locale) {
        return resolver.getResourceBundle(resources, locale);
    }

    public static ResolvedSceneDescriptor of(final DefinedSceneDescriptor descriptor,
                                             final ResourcesResolver resolver,
                                             final Locale locale) {
        return new ResolvedSceneDescriptor(descriptor, resolver, locale);
    }

}
