package org.ejfx.core.scene.config;

public final class DefinedSceneDescriptor extends DescriptorBase {

    private final DefinedStageDescriptor descriptor;

    private final String resource;

    private final Class<?> controller;

    private DefinedSceneDescriptor(final String name,
                                   final DefinedStageDescriptor descriptor,
                                   final String resource,
                                   final Class<?> controller) {
        super(name);

        this.descriptor = descriptor;
        this.resource = resource;
        this.controller = controller;
    }

    public DefinedStageDescriptor getStageDescriptor() {
        return descriptor;
    }

    public String getResource() {
        return resource;
    }

    public Class<?> getController() {
        return controller;
    }

    public static DefinedSceneDescriptor of(final String name,
                                            final DefinedStageDescriptor descriptor,
                                            final String resource,
                                            final Class<?> controller) {
        return new DefinedSceneDescriptor(name, descriptor, resource, controller);
    }

}
