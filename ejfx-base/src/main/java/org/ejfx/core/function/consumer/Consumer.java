package org.ejfx.core.function.consumer;

@FunctionalInterface
public interface Consumer<T, E> {

    void accept(T t, E e);

}
