package org.ejfx.core.scene.config;

import org.ejfx.core.stage.Modality;
import org.ejfx.core.util.Arguments;
import org.ejfx.core.util.lang.Boolean;

public final class DefaultStageDescriptor extends DescriptorBase {

    private static final DefaultStageDescriptor DEFAULT =
            new DefaultStageDescriptor(Modality.WINDOW, Boolean.FALSE);

    private final Modality modality;

    private final Boolean resizable;

    private DefaultStageDescriptor(final Modality modality, final Boolean resizable) {
        super();

        this.modality = Arguments.requireDefined(modality, "modality");
        this.resizable = Arguments.requireDefined(resizable, "resizable");
    }

    public Modality getModality() {
        return modality;
    }

    public Boolean isResizable() {
        return resizable;
    }

    public static DefaultStageDescriptor of(final Modality modality, final Boolean resizable) {
        return new DefaultStageDescriptor(modality, resizable);
    }

    public static DefaultStageDescriptor of() {
        return DEFAULT;
    }

}
