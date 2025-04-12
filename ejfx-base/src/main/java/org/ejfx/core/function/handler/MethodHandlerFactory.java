package org.ejfx.core.function.handler;

import org.ejfx.core.function.FunctionFactoryBase;

import java.lang.invoke.*;
import java.lang.reflect.Method;

public final class MethodHandlerFactory<T> extends FunctionFactoryBase<Method, Handler<T, ?>> {

    private MethodHandlerFactory() {
        super();
    }

    @Override
    protected void setAccessible(final Method method) {
        method.setAccessible(true);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Handler<T, ?> getComplexFunction(final Method method) {
        Handler<T, ?> result = null;

        try {
            final MethodHandles.Lookup lookup = MethodHandles.lookup();
            final MethodHandle handle = lookup.unreflect(method);
            final CallSite callSite = LambdaMetafactory.metafactory(
                    lookup,
                    "handle",
                    MethodType.methodType(Handler.class),
                    MethodType.methodType(void.class, Object.class, Object.class),
                    handle,
                    handle.type());

            result = (Handler<T, ?>) callSite.getTarget().invokeExact();
        } catch (final Exception ignored) {
            // do nothing
        } catch (final Throwable t) {
            throw new RuntimeException(t);
        }

        return result;
    }

    @Override
    protected Handler<T, ?> getSimpleFunction(final Method method) {
        return MethodHandler.of(method);
    }

    public static <T> MethodHandlerFactory<T> of() {
        return new MethodHandlerFactory<>();
    }

}
