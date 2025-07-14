package org.ejfx.core.scene.config;

public final class SceneDescriptor extends ResourcesDescriptorBase {

    private final StageDescriptor stageDescriptor;

    private final String fxml;

    private final Class<?> controller;

    private SceneDescriptor(final String name,
                            final String resources,
                            final StageDescriptor stageDescriptor,
                            final String fxml,
                            final Class<?> controller) {
        super(name, resources);

        this.stageDescriptor = stageDescriptor;
        this.fxml = fxml;
        this.controller = controller;
    }

    public StageDescriptor getStageDescriptor() {
        return stageDescriptor;
    }

    public String getFXML() {
        return fxml;
    }

    public Class<?> getController() {
        return controller;
    }

    public static SceneDescriptor of(final String name,
                                     final String resources,
                                     final StageDescriptor descriptor,
                                     final String fxml,
                                     final Class<?> controller) {
        return new SceneDescriptor(name,
                resources,
                descriptor,
                fxml,
                controller);
    }

}
