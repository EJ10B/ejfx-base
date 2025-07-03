package org.ejfx.core.scene.config;

import javafx.scene.image.Image;
import javafx.stage.Modality;
import org.ejfx.core.util.Arguments;
import org.ejfx.core.util.resources.ResourcesResolver;

import java.util.List;
import java.util.ResourceBundle;

public final class ResolvedStageDescriptor extends ResolvedDescriptorBase {

    private final Modality modality;

    private final String title;

    private final List<Image> icons;

    private final boolean resizable;

    private final boolean maximized;

    private final boolean iconified;

    private ResolvedStageDescriptor(final DefinedStageDescriptor descriptor,
                                    final ResourceBundle resources,
                                    final ResourcesResolver resolver) {
        super(resources);

        this.modality = Arguments.requireNonNull(descriptor, "descriptor").getModality();
        this.title = getString(descriptor.getTitle());
        this.icons = doLoadIcons(Arguments.requireNonNull(resolver, "resolver"), descriptor.getIcon());
        this.resizable = descriptor.isResizable();
        this.maximized = descriptor.isMaximized();
        this.iconified = descriptor.isIconified();
    }

    public Modality getModality() {
        return modality;
    }

    public String getTitle() {
        return title;
    }

    public List<Image> getIcons() {
        return icons;
    }

    public boolean isResizable() {
        return resizable;
    }

    public boolean isMaximized() {
        return maximized;
    }

    public boolean isIconified() {
        return iconified;
    }

    private static List<Image> doLoadIcons(final ResourcesResolver resolver, final String icon) {
        return Arguments.copyOf(resolver.getIcon(icon));
    }

    public static ResolvedStageDescriptor of(final DefinedStageDescriptor descriptor,
                                             final ResourceBundle resources,
                                             final ResourcesResolver resolver) {
        return new ResolvedStageDescriptor(descriptor, resources, resolver);
    }

}
