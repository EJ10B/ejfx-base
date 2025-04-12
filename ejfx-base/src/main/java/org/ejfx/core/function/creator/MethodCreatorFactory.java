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

    @SuppressWarnings("unchecked")
    @Override
    protected Creator<T> getComplexFunction(final Method method) {
//        Creator<T> result = null;
//
//        try {
//            final MethodHandles.Lookup lookup = MethodHandles.lookup();
//            final MethodHandle handle = lookup.unreflect(method);
//            final CallSite callSite = LambdaMetafactory.metafactory(
//                    lookup,
//                    "create",
//                    MethodType.methodType(Creator.class),
//                    MethodType.methodType(Object.class, Object[].class),
//                    handle,
//                    handle.type());
//
//            result = (Creator<T>) callSite.getTarget().invokeExact();
//        } catch (final Exception ignored) {
//            // do nothing
//        } catch (final Throwable t) {
//            throw new RuntimeException(t);
//        }
//
//        return result;\
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
