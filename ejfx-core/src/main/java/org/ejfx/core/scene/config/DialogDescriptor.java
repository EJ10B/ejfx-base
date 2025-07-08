package org.ejfx.core.scene.config;

import org.ejfx.core.stage.DialogType;
import org.ejfx.core.stage.Modality;
import org.ejfx.core.util.Arguments;
import org.ejfx.core.util.lang.Boolean;

public final class DialogDescriptor extends DialogDescriptorBase<Modality> {

    private final DialogType type;

    private final Boolean expanded;

    private DialogDescriptor(final String name,
                             final DialogType type,
                             final Modality modality,
                             final String resources,
                             final String title,
                             final String header,
                             final String content,
                             final Boolean expanded) {
        super(name, modality, resources, title, header, content);

        this.type = Arguments.requireNonNull(type, "type");
        this.expanded = Arguments.requireNonNull(expanded, "expanded");
    }

    public DialogType getType() {
        return type;
    }

    public Boolean isExpanded() {
        return expanded;
    }

    public DefinedDialogDescriptor getDefined(final DefaultDialogDescriptor descriptor) {
        return DefinedDialogDescriptor.of(getName(),
                Arguments.getDefined(type, descriptor.getType()),
                Arguments.getDefined(getModality(), descriptor.getModality()),
                getResources(),
                getTitle(),
                getHeader(),
                getContent(),
                Arguments.getDefined(expanded, descriptor.isExpanded()));
    }

    public static DialogDescriptor of(final String name,
                                      final DialogType type,
                                      final Modality modality,
                                      final String resources,
                                      final String title,
                                      final String header,
                                      final String content,
                                      final Boolean expanded) {
        return new DialogDescriptor(name,
                type,
                modality,
                resources,
                title,
                header,
                content,
                expanded);
    }

}
