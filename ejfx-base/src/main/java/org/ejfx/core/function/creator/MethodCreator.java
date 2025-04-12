package org.ejfx.core.function.creator;

import org.ejfx.core.util.Arguments;

import java.lang.reflect.Method;

public final class MethodCreator<T> implements Creator<T> {

    private final Method method;

    private MethodCreator(final Method method) {
        this.method = Arguments.requireNonNull(method, "method");
    }

    @SuppressWarnings("unchecked")
    @Override
    public T create(final Object... args) {
        final T result;

        try {
            result = (T) method.invoke(null, new Object[]{args});
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public static <T> MethodCreator<T> of(final Method method) {
        return new MethodCreator<>(method);
    }

}
