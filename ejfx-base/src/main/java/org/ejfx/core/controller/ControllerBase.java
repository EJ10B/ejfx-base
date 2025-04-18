package org.ejfx.core.controller;

import javafx.stage.WindowEvent;
import org.ejfx.core.application.ApplicationBase;
import org.ejfx.core.scene.SceneManagerBase;
import org.ejfx.core.util.Arguments;

public abstract class ControllerBase<A extends ApplicationBase<?>> {

    private final A application;

    protected ControllerBase(final A application) {
        this.application = Arguments.requireNonNull(application, "application");
    }

    public final A getApplication() {
        return application;
    }

    public final SceneManagerBase<?> getSceneManager() {
        return application.getSceneManager();
    }

    public final void closeScene() {
        getSceneManager().closeScene();
    }

    public void handleOnCloseRequest(final WindowEvent event) {
        // do nothing
    }

    public void handleCloseScene() {
        // do nothing
    }

}
