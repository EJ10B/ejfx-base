package org.ejfx.core.util.builder;

import javafx.util.Builder;
import javafx.util.BuilderFactory;
import org.ejfx.core.util.Arguments;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BuilderExFactoryBase implements BuilderFactory {

    private final Map<Class<?>, Builder<?>> builders;

    private final BuilderFactory factory;

    protected BuilderExFactoryBase(final Builder<?> builder, final List<BuilderEx<?>> builders) {
        this.factory = SingletonBuilderFactory.of(builder);
        this.builders = doCorrectedBuilders(Arguments.requireNonNull(builders, "builders"));
    }

    protected BuilderExFactoryBase(final BuilderFactory factory, final List<BuilderEx<?>> builders) {
        this.factory = Arguments.requireNonNull(factory, "factory");
        this.builders = doCorrectedBuilders(Arguments.requireNonNull(builders, "builders"));
    }

    protected BuilderExFactoryBase(final List<BuilderEx<?>> builders) {
        this.factory = null;
        this.builders = doCorrectedBuilders(Arguments.requireNonNull(builders, "builders"));
    }

    @Override
    public Builder<?> getBuilder(final Class<?> type) {
        Builder<?> result = null;
        final Builder<?> builder = builders.get(type);

        if (builder != null) {
            result = builder;
        } else {
            if (factory != null) {
                result = factory.getBuilder(type);
            }
        }

        return Arguments.requireNonNull(result, String.format("Unable get builder for [%s] type.", type));
    }

    private Map<Class<?>, Builder<?>> doCorrectedBuilders(final List<BuilderEx<?>> builders) {
        final Map<Class<?>, Builder<?>> result;

        if (!builders.isEmpty()) {
            final HashMap<Class<?>, Builder<?>> correctedBuilders = new HashMap<>(builders.size());

            for (final BuilderEx<?> builder : builders) {
                for (final Class<?> type : builder.getTypes()) {
                    correctedBuilders.put(type, builder);
                }
            }

            result = Map.copyOf(correctedBuilders);
        } else {
            result = Map.of();
        }

        return result;
    }

}
