package org.ejfx.core.function.creator;

import org.ejfx.core.function.FunctionFactoryBase;

import java.lang.reflect.Method;

public final class MethodCreatorFactory<T> extends FunctionFactoryBase<Method, Creator<T>> {

    private MethodCreatorFactory() {
        super();
    }

    @Override
    protected void setAccessible(final Method method) {
        // do nothing
    }

    @Override
    protected Creator<T> getComplexFunction(final Method method) {
        return null;
    }

    @Override
    protected Creator<T> getSimpleFunction(final Method method) {
        return MethodCreator.of(method);
    }

    public static <T> MethodCreatorFactory<T> of() {
        return new MethodCreatorFactory<>();
    }

}
