package org.ejfx.core.scene.controller.config;

import org.ejfx.core.function.getter.Getter;
import org.ejfx.core.function.handler.Handler;
import org.ejfx.core.util.Arguments;

public final class ShowDialogDescriptor<T> extends FieldDescriptorBase<T> {

    private final String name;

    private final Handler<T, ?> handler;

    private ShowDialogDescriptor(final Getter<T, ?> getter, final String name, final Handler<T, ?> handler) {
        super(getter);

        this.name = Arguments.requireNonEmptyEx(name, "name");
        this.handler = handler;
    }

    public String getName() {
        return name;
    }

    @SuppressWarnings("unchecked")
    public <V> void handle(final T controller, final V value) {
        if (handler != null) {
            ((Handler<T, V>) handler).handle(controller, value);
        }
    }

    public static <T> ShowDialogDescriptor<T> of(final Getter<T, ?> getter,
                                                 final String name,
                                                 final Handler<T, ?> handler) {
        return new ShowDialogDescriptor<>(getter, name, handler);
    }

}
