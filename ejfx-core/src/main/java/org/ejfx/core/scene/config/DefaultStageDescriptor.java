package org.ejfx.core.scene.config;

import org.ejfx.core.stage.Modality;
import org.ejfx.core.util.Arguments;
import org.ejfx.core.util.lang.Boolean;

public final class DefaultStageDescriptor extends DescriptorBase {

    private static final DefaultStageDescriptor DEFAULT = new DefaultStageDescriptor(Modality.WINDOW, "", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE);

    private final Modality modality;

    private final String icon;

    private final Boolean resizable;

    private final Boolean maximized;

    private final Boolean iconified;

    private DefaultStageDescriptor(final Modality modality,
                                   final String icon,
                                   final Boolean resizable,
                                   final Boolean maximized,
                                   final Boolean iconified) {
        super();

        this.modality = Arguments.requireDefined(modality, "modality");
        this.icon = icon;
        this.resizable = Arguments.requireDefined(resizable, "resizable");
        this.maximized = Arguments.requireDefined(maximized, "maximized");
        this.iconified = Arguments.requireDefined(iconified, "iconified");
    }

    public Modality getModality() {
        return modality;
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

    public static DefaultStageDescriptor of(final Modality modality,
                                            final String icon,
                                            final Boolean resizable,
                                            final Boolean maximized,
                                            final Boolean iconified) {
        return new DefaultStageDescriptor(modality, icon, resizable, maximized, iconified);
    }

    public static DefaultStageDescriptor of() {
        return DEFAULT;
    }

}
