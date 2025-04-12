package org.ejfx.core.scene.controller;

import javafx.util.BuilderFactory;
import org.ejfx.core.scene.controller.config.ControllerDescriptor;
import org.ejfx.core.scene.controller.config.DescriptorBase;
import org.ejfx.core.scene.controller.processor.Processor;
import org.ejfx.core.scene.controller.processor.ProcessorBuilderFactory;
import org.ejfx.core.util.Arguments;

import java.util.List;

public class ControllerManagerImpl<T> extends ControllerManagerBase<T> {

    private static final BuilderFactory factory = ProcessorBuilderFactory.of();

    private final ControllerDescriptor<T> descriptor;

    private ControllerManagerImpl(final ControllerDescriptor<T> descriptor) {
        this.descriptor = Arguments.requireNonNull(descriptor, "descriptor");
    }

    @Override
    public final T create(final Object... args) {
        return descriptor.create(args);
    }

    @Override
    protected List<DescriptorBase<T>> getDescriptors() {
        return descriptor.getDescriptors();
    }


    @SuppressWarnings("unchecked")
    @Override
    protected <D> Processor<T, D> getProcessor(final Class<?> type) {
        return (Processor<T, D>) factory.getBuilder(type).build();
    }

    public static <T> ControllerManagerImpl<T> of(final ControllerDescriptor<T> descriptor) {
        return new ControllerManagerImpl<>(descriptor);
    }

}
