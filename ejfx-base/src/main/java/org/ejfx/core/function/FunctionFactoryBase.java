package org.ejfx.core.function;

public abstract class FunctionFactoryBase<S, F> implements FunctionFactory<S, F> {

    protected FunctionFactoryBase() {
        // do nothing
    }

    @Override
    public F create(final S source) {
        F result = null;

        if (source != null) {
            setAccessible(source);
            result = getComplexFunction(source);

            if (result == null) {
                result = getSimpleFunction(source);
            }
        }

        return result;
    }

    protected abstract void setAccessible(S source);

    protected abstract F getComplexFunction(S source);

    protected abstract F getSimpleFunction(S source);

}
