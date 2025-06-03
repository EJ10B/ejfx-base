package org.ejfx.core.scene.config;

import org.ejfx.core.stage.Modality;
import org.ejfx.core.util.Arguments;
import org.ejfx.core.util.lang.Boolean;
import org.ejfx.core.util.lang.String;

public final class StageDescriptor {

    private final Modality modality;

    private final String title;

    private final String icon;

    private final Boolean resizable;

    private final Boolean maximized;

    private final Boolean iconified;

    private StageDescriptor(final Modality modality,
                            final String title,
                            final String icon,
                            final Boolean resizable,
                            final Boolean maximized,
                            final Boolean iconified) {
        this.modality = Arguments.requireNonNull(modality, "modality");
        this.title = Arguments.requireNonNull(title, "title");
        this.icon = Arguments.requireNonNull(icon, "icon");
        this.resizable = Arguments.requireNonNull(resizable, "resizable");
        this.maximized = Arguments.requireNonNull(maximized, "maximized");
        this.iconified = Arguments.requireNonNull(iconified, "iconified");
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

    public Boolean isResizable() {
        return resizable;
    }

    public Boolean isMaximized() {
        return maximized;
    }

    public Boolean isIconified() {
        return iconified;
    }

    public static StageDescriptor of(final Modality modality,
                                     final java.lang.String title,
                                     final java.lang.String icon,
                                     final Boolean resizable,
                                     final Boolean maximized,
                                     final Boolean iconified) {
        return new StageDescriptor(modality,
                String.of(title),
                String.of(icon),
                resizable,
                maximized,
                iconified);
    }

}
