package org.ejfx.core.scene.config;

import org.ejfx.core.stage.Modality;
import org.ejfx.core.util.Arguments;
import org.ejfx.core.util.lang.Boolean;

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

    public Boolean getMaximized() {
        return maximized;
    }

    public Boolean getIconified() {
        return iconified;
    }

    public DefinedStageDescriptor getDefined(final DefaultStageDescriptor descriptor) {
        return DefinedStageDescriptor.of(Arguments.getDefined(modality, descriptor.getModality()),
                title,
                (icon != null) ? icon : descriptor.getIcon(),
                Arguments.getDefined(resizable, descriptor.isResizable()),
                Arguments.getDefined(maximized, descriptor.isMaximized()),
                Arguments.getDefined(iconified, descriptor.isIconified()));
    }

    public static StageDescriptor of(final Modality modality,
                                     final String title,
                                     final String icon,
                                     final Boolean resizable,
                                     final Boolean maximized,
                                     final Boolean iconified) {
        return new StageDescriptor(modality,
                title,
                icon,
                resizable,
                maximized,
                iconified);
    }

}
