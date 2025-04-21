package org.ejfx.core.function.consumer;

import org.ejfx.core.function.FunctionFactoryBase;

import java.lang.invoke.*;
import java.lang.reflect.Method;

public final class MethodConsumerFactory<T> extends FunctionFactoryBase<Method, Consumer<T, ?>> {

    private MethodConsumerFactory() {
    }

    @Override
    protected void setAccessible(final Method method) {
        method.setAccessible(true);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Consumer<T, ?> getComplexFunction(final Method method) {
        Consumer<T, ?> result = null;

        try {
            final MethodHandles.Lookup lookup = MethodHandles.lookup();
            final MethodHandle handle = lookup.unreflect(method);
            final CallSite callSite = LambdaMetafactory.metafactory(
                    lookup,
                    "accept",
                    MethodType.methodType(Consumer.class),
                    MethodType.methodType(void.class, Object.class, Object.class),
                    handle,
                    handle.type());

            result = (Consumer<T, ?>) callSite.getTarget().invokeExact();
        } catch (final Exception ignored) {
            // do nothing
        } catch (final Throwable t) {
            throw new RuntimeException(t);
        }

        return result;
    }

    @Override
    protected Consumer<T, ?> getSimpleFunction(final Method method) {
        return MethodConsumer.of(method);
    }

    public static <T> MethodConsumerFactory<T> of() {
        return new MethodConsumerFactory<T>();
    }

}
