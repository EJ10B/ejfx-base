package org.ejfx.core.scene.config;

public final class DefinedSceneDescriptor extends ResourcesDescriptorBase {

    private final DefinedStageDescriptor descriptor;

    private final String fxml;

    private final Class<?> controller;

    private DefinedSceneDescriptor(final String name,
                                   final String resources,
                                   final DefinedStageDescriptor descriptor,
                                   final String fxml,
                                   final Class<?> controller) {
        super(name, resources);

        this.descriptor = descriptor;
        this.fxml = fxml;
        this.controller = controller;
    }

    public DefinedStageDescriptor getStageDescriptor() {
        return descriptor;
    }

    public String getFXML() {
        return fxml;
    }

    public Class<?> getController() {
        return controller;
    }

    public static DefinedSceneDescriptor of(final SceneDescriptor descriptor,
                                            final DefaultStageDescriptor defaultDescriptor) {
        return new DefinedSceneDescriptor(descriptor.getName(),
                descriptor.getResources(),
                DefinedStageDescriptor.of(descriptor.getStageDescriptor(), defaultDescriptor),
                descriptor.getFXML(),
                descriptor.getController());
    }

}
