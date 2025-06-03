package org.ejfx.core.scene.config;

import javafx.stage.Modality;
import org.ejfx.core.util.Arguments;

public final class DefinedStageDescriptor {

    private final Modality modality;

    private final String title;

    private final String icon;

    private final boolean resizable;

    private final boolean maximized;

    private final boolean iconified;

    public DefinedStageDescriptor(final StageDescriptor descriptor,
                                  final DefaultStageDescriptor defaultDescriptor) {
        this.modality = Arguments.getDefined(descriptor.getModality(), defaultDescriptor.getModality());
        this.title = Arguments.getDefined(descriptor.getTitle(), defaultDescriptor.getTitle());
        this.icon = Arguments.getDefined(descriptor.getIcon(), defaultDescriptor.getIcon());
        this.resizable = Arguments.getDefined(descriptor.isResizable(), defaultDescriptor.isResizable());
        this.maximized = Arguments.getDefined(descriptor.isMaximized(), defaultDescriptor.isMaximized());
        this.iconified = Arguments.getDefined(descriptor.isIconified(), defaultDescriptor.isIconified());
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

    public static DefinedStageDescriptor of(final StageDescriptor descriptor,
                                            final DefaultStageDescriptor defaultDescriptor) {
        return (descriptor != null) ? new DefinedStageDescriptor(descriptor, defaultDescriptor) : null;
    }

}
