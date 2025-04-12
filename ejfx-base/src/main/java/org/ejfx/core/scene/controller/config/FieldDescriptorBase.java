package org.ejfx.core.scene.controller.config;

import org.ejfx.core.function.getter.Getter;
import org.ejfx.core.util.Arguments;

public abstract class FieldDescriptorBase<T> extends DescriptorBase<T> {

    private final Getter<T, ?> getter;

    protected FieldDescriptorBase(final Getter<T, ?> getter) {
        super();

        this.getter = Arguments.requireNonNull(getter, "getter");
    }

    @SuppressWarnings("unchecked")
    public final <E> E getField(final T controller) {
        return (E) getter.get(controller);
    }

}
