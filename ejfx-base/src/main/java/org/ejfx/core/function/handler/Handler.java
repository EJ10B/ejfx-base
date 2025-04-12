package org.ejfx.core.function.handler;

@FunctionalInterface
public interface Handler<T, V> {

    void handle(T t, V v);

}
