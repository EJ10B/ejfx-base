package org.ejfx.core.scene.config;

import org.ejfx.core.stage.DialogType;
import org.ejfx.core.stage.Modality;
import org.ejfx.core.util.Arguments;
import org.ejfx.core.util.lang.Boolean;

public final class DefaultDialogDescriptor extends DescriptorBase {

    private static final DefaultDialogDescriptor DEFAULT =
            new DefaultDialogDescriptor(DialogType.INFORMATION, Modality.WINDOW, Boolean.FALSE);

    private final DialogType type;

    private final Modality modality;

    private final Boolean expanded;

    private DefaultDialogDescriptor(final DialogType type, final Modality modality, final Boolean expanded) {
        super();

        this.type = Arguments.requireDefined(type, "type");
        this.modality = Arguments.requireDefined(modality, "modality");
        this.expanded = Arguments.requireDefined(expanded, "expanded");
    }

    public DialogType getType() {
        return type;
    }

    public Modality getModality() {
        return modality;
    }

    public Boolean isExpanded() {
        return expanded;
    }

    public static DefaultDialogDescriptor of(final DialogType type, final Modality modality, final Boolean expanded) {
        return new DefaultDialogDescriptor(type, modality, expanded);
    }

    public static DefaultDialogDescriptor of() {
        return DEFAULT;
    }

}
