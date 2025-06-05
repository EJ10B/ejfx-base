package org.ejfx.core.scene.config;

import org.ejfx.core.stage.Modality;
import org.ejfx.core.util.lang.Boolean;
import org.ejfx.core.util.lang.String;

public abstract class StageDescriptorBase extends DescriptorBase {

    private final Modality modality;

    private final String title;

    private final String icon;

    private final Boolean resizable;

    private final Boolean maximized;

    private final Boolean iconified;

    protected StageDescriptorBase(final Modality modality,
                                  final String title,
                                  final String icon,
                                  final Boolean resizable,
                                  final Boolean maximized,
                                  final Boolean iconified) {
        super();

        this.modality = modality;
        this.title = title;
        this.icon = icon;
        this.resizable = resizable;
        this.maximized = maximized;
        this.iconified = iconified;
    }

    public final Modality getModality() {
        return modality;
    }

    public final String getTitle() {
        return title;
    }

    public final String getIcon() {
        return icon;
    }

    public final Boolean isResizable() {
        return resizable;
    }

    public final Boolean isMaximized() {
        return maximized;
    }

    public final Boolean isIconified() {
        return iconified;
    }

}
