package org.ejfx.core.util.builder;

import javafx.util.Builder;
import javafx.util.BuilderFactory;
import org.ejfx.core.util.Arguments;

public final class SingletonBuilderFactory implements BuilderFactory {

    private final Builder<?> builder;

    private SingletonBuilderFactory(final Builder<?> builder) {
        this.builder = Arguments.requireNonNull(builder, "builder");
    }

    @Override
    public Builder<?> getBuilder(final Class<?> type) {
        return builder;
    }

    public static SingletonBuilderFactory of(final Builder<?> builder) {
        return new SingletonBuilderFactory(builder);
    }

}
