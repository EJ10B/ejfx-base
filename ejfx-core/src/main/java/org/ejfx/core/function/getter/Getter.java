package org.ejfx.core.function.getter;

@FunctionalInterface
public interface Getter<T, E> {

    E get(T t);

}
