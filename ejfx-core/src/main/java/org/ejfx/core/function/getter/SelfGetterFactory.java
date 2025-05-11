package org.ejfx.core.function.getter;

import org.ejfx.core.function.FunctionFactory;

@SuppressWarnings("unused")
public final class SelfGetterFactory<T> implements FunctionFactory<T, Getter<T, T>> {

    private SelfGetterFactory() {
        super();
    }

    @Override
    public Getter<T, T> create(final T source) {
        return SelfGetter.of();
    }

    public static <T> SelfGetterFactory<T> of() {
        return new SelfGetterFactory<>();
    }
}
