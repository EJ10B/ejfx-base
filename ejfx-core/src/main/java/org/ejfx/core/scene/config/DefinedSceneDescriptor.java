package org.ejfx.core.scene.config;

public final class DefinedSceneDescriptor extends DescriptorBase {

    private final DefinedStageDescriptor descriptor;

    private final String fxml;

    private final String resources;

    private final Class<?> controller;

    private DefinedSceneDescriptor(final String name,
                                   final DefinedStageDescriptor descriptor,
                                   final String fxml,
                                   final String resources,
                                   final Class<?> controller) {
        super(name);

        this.descriptor = descriptor;
        this.fxml = fxml;
        this.resources = resources;
        this.controller = controller;
    }

    public DefinedStageDescriptor getStageDescriptor() {
        return descriptor;
    }

    public String getFXML() {
        return fxml;
    }

    public String getResources() {
        return resources;
    }

    public Class<?> getController() {
        return controller;
    }

    public static DefinedSceneDescriptor of(final SceneDescriptor descriptor,
                                            final DefaultStageDescriptor defaultDescriptor) {
        return new DefinedSceneDescriptor(descriptor.getName(),
                DefinedStageDescriptor.of(descriptor.getStageDescriptor(), defaultDescriptor),
                descriptor.getFXML(),
                descriptor.getResources(),
                descriptor.getController());
    }

}
