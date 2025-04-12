package org.ejfx.demo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.ejfx.core.annotation.FxCreator;
import org.ejfx.core.controller.ControllerBase;
import org.ejfx.demo.application.HelloApplication;

import java.io.File;
import java.util.List;

public class HelloController extends ControllerBase<HelloApplication> {

    @FXML
    public Label imageText;

    @FXML
    private Label welcomeText;

    protected HelloController(HelloApplication application) {
        super(application);
    }

    @FXML
    protected void handleHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void handleOpenImageButtonClick() {
        final List<File> files = getSceneManager().showFileDialog("openImage");
        imageText.setText(String.format("Selected %s image(s)", files.size()));
    }

    @FxCreator
    public static HelloController create(final Object... args) {
        return new HelloController((HelloApplication) args[0]);
    }

}