package org.ejfx.core.scene.config;

import org.ejfx.core.stage.Modality;
import org.ejfx.core.util.Arguments;
import org.ejfx.core.util.lang.Boolean;
import org.ejfx.core.util.lang.String;

public final class DefaultStageDescriptor extends StageDescriptorBase {

    private static final DefaultStageDescriptor DEFAULT = new DefaultStageDescriptor(Modality.WINDOW,
            String.EMPTY,
            String.EMPTY,
            Boolean.FALSE,
            Boolean.FALSE,
            Boolean.FALSE);

    private DefaultStageDescriptor(final Modality modality,
                                   final String title,
                                   final String icon,
                                   final Boolean resizable,
                                   final Boolean maximized,
                                   final Boolean iconified) {
        super(Arguments.requireDefined(modality, "modality"),
                Arguments.requireDefined(title, "title"),
                Arguments.requireDefined(icon, "icon"),
                Arguments.requireDefined(resizable, "resizable"),
                Arguments.requireDefined(maximized, "maximized"),
                Arguments.requireDefined(iconified, "iconified"));
    }

    public static DefaultStageDescriptor of(final Modality modality,
                                            final java.lang.String title,
                                            final java.lang.String icon,
                                            final Boolean resizable,
                                            final Boolean maximized,
                                            final Boolean iconified) {
        return new DefaultStageDescriptor(modality,
                String.of(title),
                String.of(icon),
                resizable,
                maximized,
                iconified);
    }

    public static DefaultStageDescriptor of() {
        return DEFAULT;
    }

}
