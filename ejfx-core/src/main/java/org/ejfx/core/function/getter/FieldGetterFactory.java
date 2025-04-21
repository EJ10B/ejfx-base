package org.ejfx.core.function.getter;

import org.ejfx.core.function.FunctionFactoryBase;

import java.lang.reflect.Field;

public final class FieldGetterFactory<T> extends FunctionFactoryBase<Field, Getter<T, ?>> {

    private FieldGetterFactory() {
        super();
    }

    @Override
    protected void setAccessible(final Field field) {
        field.setAccessible(true);
    }

    @Override
    protected Getter<T, ?> getComplexFunction(final Field field) {
        return null;
    }

    @Override
    protected Getter<T, ?> getSimpleFunction(final Field field) {
        return FieldGetter.of(field);
    }

    public static <T> FieldGetterFactory<T> of() {
        return new FieldGetterFactory<>();
    }

}
