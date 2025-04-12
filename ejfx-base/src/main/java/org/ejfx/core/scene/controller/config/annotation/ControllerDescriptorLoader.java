package org.ejfx.core.scene.controller.config.annotation;

import org.ejfx.core.function.creator.Creator;
import org.ejfx.core.scene.controller.config.ControllerDescriptor;
import org.ejfx.core.scene.controller.config.CreatorDescriptor;
import org.ejfx.core.scene.controller.config.DescriptorBase;
import org.ejfx.core.util.Arguments;

import java.util.ArrayList;
import java.util.List;

public final class ControllerDescriptorLoader<T> extends ControllerDescriptorLoaderBase<T> {

    private ControllerDescriptorLoader(final Class<T> type) {
        super(type);
    }

    @Override
    protected ControllerDescriptor<T> convertDescriptors(final List<DescriptorBase<T>> descriptors) {
        Arguments.requireNonEmptyEx(descriptors, "Unable convert empty descriptors.");

        Creator<T> creator = null;
        final ArrayList<DescriptorBase<T>> convertedDescriptors = new ArrayList<>(descriptors.size());

        for (final DescriptorBase<T> descriptor : descriptors) {
            if (descriptor instanceof CreatorDescriptor<T>) {
                creator = ((CreatorDescriptor<T>) descriptor).getCreator();
            } else {
                convertedDescriptors.add(descriptor);
            }
        }

        return ControllerDescriptor.of(getType(), creator, convertedDescriptors);
    }

    public static <T> ControllerDescriptorLoader<T> of(final Class<T> type) {
        return new ControllerDescriptorLoader<>(type);
    }

}
