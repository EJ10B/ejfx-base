package org.ejfx.core.function.getter;

import org.ejfx.core.function.FunctionFactoryBase;

import java.lang.invoke.*;
import java.lang.reflect.Method;

@SuppressWarnings("unused")
public final class MethodGetterFactory<T> extends FunctionFactoryBase<Method, Getter<T, ?>> {

    private MethodGetterFactory() {
        super();
    }

    @Override
    protected void setAccessible(final Method method) {
        // do nothing
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Getter<T, ?> getComplexFunction(final Method method) {
        Getter<T, ?> result = null;

        try {
            final MethodHandles.Lookup lookup = MethodHandles.lookup();
            final MethodHandle handle = lookup.unreflect(method);
            final CallSite callSite = LambdaMetafactory.metafactory(
                    lookup,
                    "get",
                    MethodType.methodType(Getter.class),
                    MethodType.methodType(Object.class, Object.class),
                    handle,
                    handle.type());

            result = (Getter<T, ?>) callSite.getTarget().invokeExact();
        } catch (final Exception _) {
            // no-op
        } catch (final Throwable t) {
            throw new RuntimeException(t);
        }

        return result;
    }

    @Override
    protected Getter<T, ?> getSimpleFunction(final Method method) {
        return MethodGetter.of(method);
    }

    public static <T> MethodGetterFactory<T> of() {
        return new MethodGetterFactory<>();
    }

}
