package org.ejfx.core.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.ejfx.core.annotation.FxCreator;
import org.ejfx.core.application.ApplicationBase;

import java.io.File;
import java.net.URI;
import java.net.URL;

public class ViewImageController extends ControllerBaseEx<ApplicationBase<?>, URL> {

    @FXML
    private ImageView imageView;

    protected ViewImageController(final ApplicationBase<?> application, final URL url) {
        super(application, url);
    }

    @FXML
    public void handleCloseAction(final ActionEvent event) {
        closeScene();
    }

    @FXML
    protected void initialize() {
        imageView.setImage(new Image(getValue().toString()));
    }

    @FxCreator
    public static ViewImageController create(final Object... args) {
        final ViewImageController result;
        final ApplicationBase<?> application = (ApplicationBase<?>) args[0];
        final Object src = args[1];

        if (src instanceof String) {
            try {
                result = new ViewImageController(application, new URI((String) src).toURL());
            } catch (final Exception e) {
                throw new IllegalStateException(String.format("Unable create controller - error in [%s] source.", src), e);
            }
        } else if (src instanceof File) {
            try {
                result = new ViewImageController(application, ((File) src).toURI().toURL());
            } catch (final Exception e) {
                throw new IllegalStateException(String.format("Unable create controller - error in [%s] source.", src), e);
            }
        } else if (src instanceof URI) {
            try {
                result = new ViewImageController(application, ((URI) src).toURL());
            } catch (final Exception e) {
                throw new IllegalStateException(String.format("Unable create controller - error in [%s] source.", src), e);
            }
        } else if (src instanceof URL) {
            result = new ViewImageController(application, (URL) src);
        } else {
            throw new IllegalStateException(String.format("Unable create controller - unknown [%s] source type.", src));
        }

        return result;
    }

}
