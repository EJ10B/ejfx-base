package org.ejfx.core.function.getter;

import org.ejfx.core.util.Arguments;

import java.lang.reflect.Field;

public final class FieldGetter<T, E> implements Getter<T, E> {

    private final Field field;

    private FieldGetter(final Field field) {
        this.field = Arguments.requireNonNull(field, "field");
    }

    @SuppressWarnings("unchecked")
    @Override
    public E get(final T bean) {
        final E result;

        try {
            result = (E) field.get(bean);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public static <T, E> FieldGetter<T, E> of(final Field field) {
        return new FieldGetter<>(field);
    }

}
