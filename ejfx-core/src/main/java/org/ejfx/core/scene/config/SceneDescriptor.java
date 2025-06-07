package org.ejfx.core.scene.config;

public final class SceneDescriptor extends DescriptorBase {

    private final StageDescriptor stageDescriptor;

    private final String fxml;

    private final String resources;

    private final Class<?> controller;

    private SceneDescriptor(final String name,
                            final StageDescriptor stageDescriptor,
                            final String fxml,
                            final String resources,
                            final Class<?> controller) {
        super(name);

        this.stageDescriptor = stageDescriptor;
        this.fxml = fxml;
        this.resources = resources;
        this.controller = controller;
    }

    public StageDescriptor getStageDescriptor() {
        return stageDescriptor;
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

    public static SceneDescriptor of(final String name,
                                     final StageDescriptor descriptor,
                                     final String fxml,
                                     final String resources,
                                     final Class<?> controller) {
        return new SceneDescriptor(name,
                descriptor,
                fxml,
                resources,
                controller);
    }

}
