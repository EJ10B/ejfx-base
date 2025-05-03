package org.ejfx.core.scene.config;

public final class DefinedSceneDescriptor extends DescriptorBase {

    private final DefinedStageDescriptor descriptor;

    private final String location;

    private final String resources;

    private final Class<?> controller;

    private DefinedSceneDescriptor(final String name,
                                   final DefinedStageDescriptor descriptor,
                                   final String location,
                                   final String resources,
                                   final Class<?> controller) {
        super(name);

        this.descriptor = descriptor;
        this.location = location;
        this.resources = resources;
        this.controller = controller;
    }

    public DefinedStageDescriptor getStageDescriptor() {
        return descriptor;
    }

    public String getLocation() {
        return location;
    }

    public String getResources() {
        return resources;
    }

    public Class<?> getController() {
        return controller;
    }

    public static DefinedSceneDescriptor of(final String name,
                                            final DefinedStageDescriptor descriptor,
                                            final String location,
                                            final String resources,
                                            final Class<?> controller) {
        return new DefinedSceneDescriptor(name, descriptor, location, resources, controller);
    }

}
