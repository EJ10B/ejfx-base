package org.ejfx.core.function;

public interface FunctionFactory<S, F> {

    F create(S source);

}
