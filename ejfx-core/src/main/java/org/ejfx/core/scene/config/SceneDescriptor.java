package org.ejfx.core.scene.config;

public final class SceneDescriptor extends DescriptorBase {

    private final StageDescriptor stageDescriptor;

    private final String location;

    private final String resources;

    private final Class<?> controller;

    private SceneDescriptor(final String name,
                            final StageDescriptor stageDescriptor,
                            final String location,
                            final String resources,
                            final Class<?> controller) {
        super(name);

        this.stageDescriptor = stageDescriptor;
        this.location = location;
        this.resources = resources;
        this.controller = controller;
    }

    public StageDescriptor getStageDescriptor() {
        return stageDescriptor;
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

    public DefinedSceneDescriptor getDefined(final DefaultStageDescriptor descriptor) {
        return DefinedSceneDescriptor.of(getName(),
                (stageDescriptor != null) ? stageDescriptor.getDefined(descriptor) : null,
                location,
                resources,
                controller);
    }

    public static SceneDescriptor of(final String name,
                                     final StageDescriptor descriptor,
                                     final String location,
                                     final String resources,
                                     final Class<?> controller) {
        return new SceneDescriptor(name, descriptor, location, resources, controller);
    }

}
