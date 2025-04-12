package org.ejfx.core.scene.controller.config;

import org.ejfx.core.function.getter.Getter;
import org.ejfx.core.util.Arguments;

public final class ShowSceneDescriptor<T> extends FieldDescriptorBase<T> {

    private final String name;

    private ShowSceneDescriptor(final Getter<T, ?> getter, final String name) {
        super(getter);

        this.name = Arguments.requireNonEmptyEx(name, "name");
    }

    public String getName() {
        return name;
    }

    public static <T> ShowSceneDescriptor<T> of(final Getter<T, ?> getter, final String name) {
        return new ShowSceneDescriptor<>(getter, name);
    }

}
