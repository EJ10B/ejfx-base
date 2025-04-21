package org.ejfx.core.scene.config;

public final class SceneDescriptor extends DescriptorBase {

    private final StageDescriptor stageDescriptor;

    private final String resource;

    private final Class<?> controller;

    private SceneDescriptor(final String name,
                            final StageDescriptor stageDescriptor,
                            final String resource,
                            final Class<?> controller) {
        super(name);

        this.stageDescriptor = stageDescriptor;
        this.resource = resource;
        this.controller = controller;
    }

    public StageDescriptor getStageDescriptor() {
        return stageDescriptor;
    }

    public String getResource() {
        return resource;
    }

    public Class<?> getController() {
        return controller;
    }

    public DefinedSceneDescriptor getDefined(final DefaultStageDescriptor descriptor) {
        return DefinedSceneDescriptor.of(getName(),
                (stageDescriptor != null) ? stageDescriptor.getDefined(descriptor) : null,
                resource,
                controller);
    }

    public static SceneDescriptor of(final String name,
                                     final StageDescriptor descriptor,
                                     final String resource,
                                     final Class<?> controller) {
        return new SceneDescriptor(name, descriptor, resource, controller);
    }

}
