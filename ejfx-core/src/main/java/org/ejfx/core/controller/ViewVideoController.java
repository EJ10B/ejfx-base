package org.ejfx.core.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import org.ejfx.core.annotation.FxCreator;
import org.ejfx.core.application.ApplicationBase;

import java.io.File;
import java.net.URI;
import java.net.URL;

public class ViewVideoController extends ControllerBaseEx<ApplicationBase<?>, URI> {

    @FXML
    private MediaView mediaView;

    protected ViewVideoController(final ApplicationBase<?> application, final URI uri) {
        super(application, uri);
    }

    @FXML
    public void handleCloseAction(final ActionEvent event) {
        closeScene();
    }

    @FXML
    protected void initialize() {
        final Media media = new Media(getValue().toString());
        final MediaPlayer player = new MediaPlayer(media);
        player.setAutoPlay(true);
        mediaView.setMediaPlayer(player);
    }

    @Override
    public void handleCloseScene() {
        final MediaPlayer player = mediaView.getMediaPlayer();
        if (player != null) {
            player.stop();
        }

        super.handleCloseScene();
    }

    @FxCreator
    public static ViewVideoController create(final Object... args) {
        final ViewVideoController result;
        final ApplicationBase<?> application = (ApplicationBase<?>) args[0];
        final Object src = args[1];

        if (src instanceof String) {
            result = new ViewVideoController(application, URI.create((String) src));
        } else if (src instanceof File) {
            result = new ViewVideoController(application, ((File) src).toURI());
        } else if (src instanceof URI) {
            result = new ViewVideoController(application, (URI) src);
        } else if (src instanceof URL) {
            try {
                result = new ViewVideoController(application, ((URL) src).toURI());
            } catch (final Exception e) {
                throw new IllegalStateException(String.format("Unable create controller - error in [%s] source.", src), e);
            }
        } else {
            throw new IllegalStateException(String.format("Unable create controller - unknown [%s] source type.", src));
        }

        return result;
    }

}

