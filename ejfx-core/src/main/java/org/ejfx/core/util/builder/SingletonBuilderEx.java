package org.ejfx.core.util.builder;

import org.ejfx.core.util.Arguments;

import java.util.List;

public final class SingletonBuilderEx<T> implements BuilderEx<T> {

    private final List<Class<?>> types;

    private final T value;

    private SingletonBuilderEx(final List<Class<?>> types, final T value) {
        this.types = Arguments.copyOfNonEmptyEx(types, "types");
        this.value = value;
    }

    private SingletonBuilderEx(final Class<?> type, final T value) {
        this.types = Arguments.copyOfNonNullEx(type, "type");
        this.value = value;
    }

    @Override
    public List<Class<?>> getTypes() {
        return types;
    }

    @Override
    public T build() {
        return value;
    }

    public static <T> SingletonBuilderEx<T> of(final List<Class<?>> types, final T value) {
        return new SingletonBuilderEx<>(types, value);
    }

    public static <T> SingletonBuilderEx<T> of(final Class<?> type, final T value) {
        return new SingletonBuilderEx<>(type, value);
    }

}
