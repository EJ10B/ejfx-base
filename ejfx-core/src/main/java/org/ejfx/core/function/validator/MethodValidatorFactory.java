package org.ejfx.core.function.validator;

import org.ejfx.core.function.FunctionFactoryBase;

import java.lang.invoke.*;
import java.lang.reflect.Method;

@SuppressWarnings("unused")
public final class MethodValidatorFactory<T> extends FunctionFactoryBase<Method, Validator<T>> {

    @Override
    protected void setAccessible(final Method method) {
        // no-op
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Validator<T> getComplexFunction(final Method method) {
        Validator<T> result = null;

        try {
            final MethodHandles.Lookup lookup = MethodHandles.lookup();
            final MethodHandle handle = lookup.unreflect(method);
            final CallSite callSite = LambdaMetafactory.metafactory(
                    lookup,
                    "validate",
                    MethodType.methodType(Validator.class),
                    MethodType.methodType(boolean.class, Object.class),
                    handle,
                    handle.type());

            result = (Validator<T>) callSite.getTarget().invokeExact();
        } catch (final Exception _) {
            // no-op
        } catch (final Throwable t) {
            throw new RuntimeException(t);
        }

        return result;
    }

    @Override
    protected Validator<T> getSimpleFunction(final Method method) {
        return MethodValidator.of(method);
    }

}
