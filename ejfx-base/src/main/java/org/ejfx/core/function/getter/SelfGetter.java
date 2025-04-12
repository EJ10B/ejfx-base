package org.ejfx.core.function.getter;

public final class SelfGetter<T> implements Getter<T, T> {

    private final static SelfGetter<?> INSTANCE = new SelfGetter<>();

    private SelfGetter() {
        // do nothing
    }

    @Override
    public T get(final T t) {
        return t;
    }

    @SuppressWarnings("unchecked")
    public static <T> SelfGetter<T> of() {
        return (SelfGetter<T>) INSTANCE;
    }

}
