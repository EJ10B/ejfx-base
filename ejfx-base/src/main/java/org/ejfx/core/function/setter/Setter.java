package org.ejfx.core.function.setter;

@FunctionalInterface
public interface Setter<T, E> {

    void set(T t, E e);

}
