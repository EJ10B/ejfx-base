package org.ejfx.core.scene.config;

import org.ejfx.core.stage.Modality;
import org.ejfx.core.util.Arguments;
import org.ejfx.core.util.lang.Boolean;
import org.ejfx.core.util.lang.String;

public final class StageDescriptor extends StageDescriptorBase {

    private StageDescriptor(final Modality modality,
                            final String title,
                            final String icon,
                            final Boolean resizable,
                            final Boolean maximized,
                            final Boolean iconified) {
        super(Arguments.requireNonNull(modality, "modality"),
                Arguments.requireNonNull(title, "title"),
                Arguments.requireNonNull(icon, "icon"),
                Arguments.requireNonNull(resizable, "resizable"),
                Arguments.requireNonNull(maximized, "maximized"),
                Arguments.requireNonNull(iconified, "iconified"));
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
