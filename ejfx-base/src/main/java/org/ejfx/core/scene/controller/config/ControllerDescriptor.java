package org.ejfx.core.scene.controller.config;

import org.ejfx.core.function.creator.Creator;
import org.ejfx.core.util.Arguments;

import java.util.List;

public final class ControllerDescriptor<T> {

    private final Class<T> type;

    private final Creator<T> creator;

    private final List<DescriptorBase<T>> descriptors;

    private ControllerDescriptor(final Class<T> type, final Creator<T> creator, final List<DescriptorBase<T>> descriptors) {
        this.type = Arguments.requireNonNull(type, "type");
        this.creator = Arguments.requireNonNull(creator, "creator");
        this.descriptors = Arguments.copyOfNonNullEx(descriptors, "descriptors");
    }

    public Class<T> getType() {
        return type;
    }

    public T create(final Object... args) {
        return creator.create(args);
    }

    public List<DescriptorBase<T>> getDescriptors() {
        return descriptors;
    }

    public static <T> ControllerDescriptor<T> of(final Class<T> type,
                                                 final Creator<T> creator,
                                                 final List<DescriptorBase<T>> descriptors) {
        return new ControllerDescriptor<>(type, creator, descriptors);
    }

}
