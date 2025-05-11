package org.ejfx.core.scene.config;

import org.ejfx.core.stage.Modality;

public final class TextInputDialogDescriptor extends DialogDescriptorBase<Modality> {

    private TextInputDialogDescriptor(final String name,
                                      final Modality modality,
                                      final String title,
                                      final String header,
                                      final String content) {
        super(name, modality, title, header, content);
    }

    public static TextInputDialogDescriptor of(final String name,
                                               final Modality modality,
                                               final String title,
                                               final String header,
                                               final String content) {
        return new TextInputDialogDescriptor(name, modality, title, header, content);
    }

}
