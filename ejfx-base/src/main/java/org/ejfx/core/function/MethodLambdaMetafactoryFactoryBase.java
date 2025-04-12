package org.ejfx.core.function;

import java.lang.invoke.*;
import java.lang.reflect.Method;

public abstract class MethodLambdaMetafactoryFactoryBase<F> extends FunctionFactoryBase<Method, F> {

    protected MethodLambdaMetafactoryFactoryBase() {
        super();
    }

    protected abstract boolean setAccessible();

    protected abstract String getInterfaceMethodName();

    protected abstract MethodType getFactoryType();

    protected abstract MethodType getInterfaceMethodType();

    @Override
    protected final void setAccessible(final Method method) {
        if (setAccessible()) {
            method.setAccessible(true);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    protected final F getComplexFunction(final Method method) {
        F result = null;

        try {
            final MethodHandles.Lookup lookup = MethodHandles.lookup();
            final MethodHandle handle = lookup.unreflect(method);
            final CallSite callSite = LambdaMetafactory.metafactory(
                    lookup,
                    getInterfaceMethodName(),
                    getFactoryType(),
                    getInterfaceMethodType(),
                    handle,
                    handle.type());

            result = (F) callSite.getTarget().invokeExact();
        } catch (final Exception ignored) {
            ignored.printStackTrace();
            // do nothing
        } catch (final Throwable t) {
            throw new RuntimeException(t);
        }

        return result;
    }

}
