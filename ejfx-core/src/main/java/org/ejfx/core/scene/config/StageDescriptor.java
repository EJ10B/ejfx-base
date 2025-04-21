package org.ejfx.core.scene.config;

import org.ejfx.core.stage.Modality;
import org.ejfx.core.util.Arguments;
import org.ejfx.core.util.lang.Boolean;

public final class StageDescriptor {

    private final Modality modality;

    private final String title;

    private final Boolean resizable;

    private StageDescriptor(final Modality modality, final String title, final Boolean resizable) {
        this.modality = Arguments.requireNonNull(modality, "modality");
        this.title = Arguments.requireNonNull(title, "title");
        this.resizable = Arguments.requireNonNull(resizable, "resizable");
    }

    public Modality getModality() {
        return modality;
    }

    public String getTitle() {
        return title;
    }

    public Boolean isResizable() {
        return resizable;
    }

    public DefinedStageDescriptor getDefined(final DefaultStageDescriptor descriptor) {
        return DefinedStageDescriptor.of(Arguments.getDefined(modality, descriptor.getModality()),
                title,
                Arguments.getDefined(resizable, descriptor.isResizable()));
    }

    public static StageDescriptor of(final Modality modality, final String title, final Boolean resizable) {
        return new StageDescriptor(modality, title, resizable);
    }

}
