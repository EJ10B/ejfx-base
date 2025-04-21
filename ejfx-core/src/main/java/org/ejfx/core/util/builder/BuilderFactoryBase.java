package org.ejfx.core.util.builder;

import javafx.util.Builder;
import javafx.util.BuilderFactory;

import java.util.HashMap;

public abstract class BuilderFactoryBase implements BuilderFactory {

    private final HashMap<Class<?>, Builder<?>> builders = new HashMap<>();

    @Override
    public Builder<?> getBuilder(final Class<?> type) {
        if (!builders.containsKey(type)) {
            builders.put(type, createBuilder(type));
        }

        return builders.get(type);
    }

    protected abstract Builder<?> createBuilder(Class<?> type);

}
