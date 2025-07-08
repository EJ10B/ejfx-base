package org.ejfx.core.scene.config;

import javafx.stage.Modality;
import org.ejfx.core.stage.DialogType;
import org.ejfx.core.util.Arguments;

public final class DefinedDialogDescriptor extends DialogDescriptorBase<Modality> {

    private final DialogType type;

    private final boolean expanded;

    private DefinedDialogDescriptor(final String name,
                                    final DialogType type,
                                    final Modality modality,
                                    final String resources,
                                    final String title,
                                    final String header,
                                    final String content,
                                    final boolean expanded) {
        super(name, modality, resources, title, header, content);

        this.type = Arguments.requireNonNull(type, "type");
        this.expanded = Arguments.requireNonNull(expanded, "expanded");
    }

    public DialogType getType() {
        return type;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public static DefinedDialogDescriptor of(final String name,
                                             final DialogType type,
                                             final Modality modality,
                                             final String resources,
                                             final String title,
                                             final String header,
                                             final String content,
                                             final boolean expanded) {
        return new DefinedDialogDescriptor(name,
                type,
                modality,
                resources,
                title,
                header,
                content,
                expanded);
    }

}
