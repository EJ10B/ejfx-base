package org.ejfx.core.scene.controller.config;

import org.ejfx.core.function.creator.Creator;
import org.ejfx.core.util.Arguments;

public final class CreatorDescriptor<T> extends DescriptorBase<T> {

    private final Creator<T> creator;

    private CreatorDescriptor(final Creator<T> creator) {
        super();

        this.creator = Arguments.requireNonNull(creator, "creator");
    }

    public Creator<T> getCreator() {
        return creator;
    }

    public static <T> CreatorDescriptor<T> of(final Creator<T> creator) {
        return new CreatorDescriptor<>(creator);
    }

}
