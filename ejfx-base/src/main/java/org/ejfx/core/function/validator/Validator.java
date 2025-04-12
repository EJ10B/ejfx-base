package org.ejfx.core.function.validator;

@FunctionalInterface
public interface Validator<T> {

    boolean validate(T t);

}
