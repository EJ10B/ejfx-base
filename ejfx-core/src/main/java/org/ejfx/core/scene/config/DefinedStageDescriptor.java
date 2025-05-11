package org.ejfx.core.scene.config;

import javafx.stage.Modality;
import org.ejfx.core.util.Arguments;

public final class DefinedStageDescriptor {

    private final Modality modality;

    private final String title;

    private final boolean resizable;

    private DefinedStageDescriptor(final Modality modality, final String title, final boolean resizable) {
        this.modality = Arguments.requireNonNull(modality, "modality");
        this.title = Arguments.requireNonNull(title, "title");
        this.resizable = resizable;
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

    public static DefinedStageDescriptor of(final Modality modality, final String title, final boolean resizable) {
        return new DefinedStageDescriptor(modality, title, resizable);
    }

}
