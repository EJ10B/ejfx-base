package org.ejfx.core.scene.config;

import org.ejfx.core.util.Arguments;

public abstract class DescriptorBase {

    private final String name;

    protected DescriptorBase() {
        this.name = null;
    }

    protected DescriptorBase(final String name) {
        this.name = Arguments.requireNonEmptyEx(name, "name");
    }

    public final String getName() {
        return Arguments.requireNonNull(name, "Unable get 'unnamed' name.");
    }

}
