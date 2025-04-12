package org.ejfx.core.scene.controller;

import org.ejfx.core.scene.controller.config.DescriptorBase;
import org.ejfx.core.scene.controller.processor.Processor;

import java.util.List;

public abstract class ControllerManagerBase<T> implements ControllerManager<T> {

    @Override
    public final void process(final T controller) {
        for (final DescriptorBase<T> descriptor : getDescriptors()) {
            getProcessor(descriptor.getClass()).process(controller, descriptor);
        }
    }

    protected abstract List<DescriptorBase<T>> getDescriptors();

    protected abstract <D> Processor<T, D> getProcessor(Class<?> type);

}
