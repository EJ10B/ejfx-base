package org.ejfx.core.scene.config;

import org.ejfx.core.util.Arguments;

public abstract class DialogDescriptorBase<T> extends DescriptorBase {

    private final T modality;

    private final String title;

    private final String header;

    private final String content;

    protected DialogDescriptorBase(final String name,
                                   final T modality,
                                   final String title,
                                   final String header,
                                   final String content) {
        super(name);

        this.modality = Arguments.requireNonNull(modality, "modality");
        this.title = Arguments.requireNonNull(title, "title");
        this.header = Arguments.requireNonNull(header, "header");
        this.content = Arguments.requireNonNull(content, "content");
    }

    public final T getModality() {
        return modality;
    }

    public final String getTitle() {
        return title;
    }

    public final String getHeader() {
        return header;
    }

    public final String getContent() {
        return content;
    }

}
