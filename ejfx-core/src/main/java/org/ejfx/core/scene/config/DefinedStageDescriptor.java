package org.ejfx.core.scene.config;

import javafx.stage.Modality;
import org.ejfx.core.util.Arguments;

public final class DefinedStageDescriptor {

    private final Modality modality;

    private final String title;

    private final boolean resizable;

    private final boolean maximized;

    private final boolean iconified;

    private DefinedStageDescriptor(final Modality modality,
                                   final String title,
                                   final boolean resizable,
                                   final boolean maximized,
                                   final boolean iconified) {
        this.modality = Arguments.requireNonNull(modality, "modality");
        this.title = Arguments.requireNonNull(title, "title");
        this.resizable = resizable;
        this.maximized = maximized;
        this.iconified = iconified;
    }

    public Modality getModality() {
        return modality;
    }

    public String getTitle() {
        return title;
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

    public static DefinedStageDescriptor of(final Modality modality,
                                            final String title,
                                            final boolean resizable,
                                            final boolean maximized,
                                            final boolean iconified) {
        return new DefinedStageDescriptor(modality, title, resizable, maximized, iconified);
    }

}
