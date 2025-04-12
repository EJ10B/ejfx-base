package org.ejfx.core.function.setter;

import org.ejfx.core.util.Arguments;

import java.lang.reflect.Method;

public final class MethodSetter<T, E> implements Setter<T, E> {

    private final Method method;

    private MethodSetter(final Method method) {
        this.method = Arguments.requireNonNull(method, "method");
    }

    @Override
    public void set(final T bean, final E value) {
        try {
            method.invoke(bean, value);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T, E> MethodSetter<T, E> of(final Method method) {
        return new MethodSetter<>(method);
    }

}

