package org.ejfx.core.function.setter;

import org.ejfx.core.function.FunctionFactoryBase;

import java.lang.invoke.*;
import java.lang.reflect.Method;

@SuppressWarnings("unused")
public final class SetterFactory<T> extends FunctionFactoryBase<Method, Setter<T, ?>> {

    @Override
    protected void setAccessible(final Method method) {
        // no-op
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Setter<T, ?> getComplexFunction(final Method method) {
        Setter<T, ?> result = null;

        try {
            final MethodHandles.Lookup lookup = MethodHandles.lookup();
            final MethodHandle handle = lookup.unreflect(method);
            final CallSite callSite = LambdaMetafactory.metafactory(
                    lookup,
                    "set",
                    MethodType.methodType(Setter.class),
                    MethodType.methodType(void.class, Object.class, Object.class),
                    handle,
                    handle.type());

            result = (Setter<T, ?>) callSite.getTarget().invokeExact();
        } catch (final Exception _) {
            // no-op
        } catch (final Throwable t) {
            throw new RuntimeException(t);
        }

        return result;
    }

    @Override
    protected Setter<T, ?> getSimpleFunction(final Method method) {
        return MethodSetter.of(method);
    }

}
