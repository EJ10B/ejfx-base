package org.ejfx.core.function.getter;

import org.ejfx.core.util.Arguments;

import java.lang.reflect.Method;

public final class MethodGetter<T, E> implements Getter<T, E> {

    private final Method method;

    private MethodGetter(final Method method) {
        this.method = Arguments.requireNonNull(method, "method");
    }

    @SuppressWarnings("unchecked")
    @Override
    public E get(final T bean) {
        final E result;

        try {
            result = (E) method.invoke(bean);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public static <T, E> MethodGetter<T, E> of(final Method method) {
        return new MethodGetter<>(method);
    }

}
