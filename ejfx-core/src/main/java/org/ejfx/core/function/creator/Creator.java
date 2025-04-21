package org.ejfx.core.function.creator;

@FunctionalInterface
public interface Creator<T> {

    T create(Object... args);

}
