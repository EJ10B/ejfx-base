package org.ejfx.core.controller;

import javafx.fxml.FXML;
import javafx.stage.WindowEvent;
import org.ejfx.core.application.ApplicationBase;
import org.ejfx.core.scene.SceneManagerBase;
import org.ejfx.core.util.Arguments;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class ControllerBase<A extends ApplicationBase<?>> {

    private final A application;

    @FXML
    private URL location;

    @FXML
    private ResourceBundle resources;

    protected ControllerBase(final A application) {
        this.application = Arguments.requireNonNull(application, "application");
    }

    public final A getApplication() {
        return application;
    }

    @SuppressWarnings("unchecked")
    public final SceneManagerBase<A> getSceneManager() {
        return (SceneManagerBase<A>) application.getSceneManager();
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
