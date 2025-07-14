package org.ejfx.core.scene.config;

import org.ejfx.core.util.Arguments;

public abstract class ResourcesDescriptorBase extends DescriptorBase {

    private final String resources;

    protected ResourcesDescriptorBase(final String resources) {
        super();

        this.resources = Arguments.requireNonNull(resources, "resources");
    }

    protected ResourcesDescriptorBase(final String name, final String resources) {
        super(name);

        this.resources = Arguments.requireNonNull(resources, "resources");
    }

    public final String getResources() {
        return resources;
    }

}
