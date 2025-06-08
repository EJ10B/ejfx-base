package org.ejfx.core.scene.config;

import javafx.stage.Modality;

import java.util.ResourceBundle;

public class ResolvedStageDescriptor extends ResolvedDescriptorBase {

    private final Modality modality;

    private final String title;

    private final String icon;

    private final boolean resizable;

    private final boolean maximized;

    private final boolean iconified;

    private ResolvedStageDescriptor(final DefinedStageDescriptor descriptor,
                                    final ResourceBundle resources) {
        super(resources);

        this.modality = descriptor.getModality();
        this.title = getString(descriptor.getTitle());
        this.icon = descriptor.getIcon();
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

    public String getIcon() {
        return icon;
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

    public static ResolvedStageDescriptor of(final DefinedStageDescriptor descriptor,
                                             final ResourceBundle resources) {
        return new ResolvedStageDescriptor(descriptor, resources);
    }

}
