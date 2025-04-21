package org.ejfx.core.scene.controller;

import javafx.util.Builder;
import org.ejfx.core.scene.controller.config.annotation.ControllerDescriptorLoader;
import org.ejfx.core.util.builder.BuilderFactoryBase;
import org.ejfx.core.util.builder.SingletonBuilder;

public class ControllerManagerBuilderFactory extends BuilderFactoryBase {

    private ControllerManagerBuilderFactory() {
        super();
    }

    @Override
    protected Builder<?> createBuilder(final Class<?> type) {
        return SingletonBuilder.of(ControllerManagerImpl.of(ControllerDescriptorLoader.of(type).load()));
    }

    public static ControllerManagerBuilderFactory of() {
        return new ControllerManagerBuilderFactory();
    }

}
