package org.ejfx.core.function.validator;

import org.ejfx.core.util.Arguments;

import java.lang.reflect.Method;

public final class MethodValidator<T> implements Validator<T> {

    private final Method method;

    private MethodValidator(final Method method) {
        this.method = Arguments.requireNonNull(method, "method");
    }

    @Override
    public boolean validate(final T t) {
        final boolean result;

        try {
            result = (boolean) method.invoke(null, t);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public static <T> MethodValidator<T> of(final Method method) {
        return new MethodValidator<>(method);
    }

}
