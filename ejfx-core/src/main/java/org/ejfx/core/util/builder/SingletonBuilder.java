package org.ejfx.core.util.builder;

import javafx.util.Builder;

public final class SingletonBuilder<T> implements Builder<T> {

    private final T value;

    private SingletonBuilder(final T value) {
        this.value = value;
    }

    @Override
    public T build() {
        return value;
    }

    public static <T> SingletonBuilder<T> of(final T value) {
        return new SingletonBuilder<>(value);
    }

}
