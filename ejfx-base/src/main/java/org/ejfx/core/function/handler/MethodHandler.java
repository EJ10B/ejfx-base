package org.ejfx.core.function.handler;

import org.ejfx.core.util.Arguments;

import java.lang.reflect.Method;

public final class MethodHandler<T, V> implements Handler<T, V> {

    private final Method method;

    private MethodHandler(final Method method) {
        this.method = Arguments.requireNonNull(method, "method");
    }

    @Override
    public void handle(final T bean, final V value) {
        try {
            method.invoke(bean, value);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T, V> MethodHandler<T, V> of(final Method method) {
        return new MethodHandler<>(method);
    }

}
