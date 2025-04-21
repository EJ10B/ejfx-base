package org.ejfx.core.function.consumer;

import org.ejfx.core.util.Arguments;

import java.lang.reflect.Method;

public final class MethodConsumer<T, E> implements Consumer<T, E> {

    private final Method method;

    private MethodConsumer(final Method method) {
        this.method = Arguments.requireNonNull(method, "method");
    }

    @Override
    public void accept(final T bean, final E value) {
        try {
            method.invoke(bean, value);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T, E> MethodConsumer<T, E> of(final Method method) {
        return new MethodConsumer<>(method);
    }

}
