package org.ejfx.core.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import org.ejfx.core.annotation.FxCreator;
import org.ejfx.core.application.ApplicationBase;

import java.net.URI;
import java.net.URL;

public class ViewWebPageController extends ControllerBaseEx<ApplicationBase<?>, URL> {

    @FXML
    private WebView webView;

    protected ViewWebPageController(final ApplicationBase<?> application, final URL url) {
        super(application, url);
    }

    @FXML
    public void handleBackAction(final ActionEvent event) {
        final WebHistory history = webView.getEngine().getHistory();

        if (history.getCurrentIndex() > 0) {
            history.go(-1);
        }
    }

    @FXML
    public void handleRefreshAction(final ActionEvent event) {
        webView.getEngine().reload();
    }

    @FXML
    public void handleCloseAction(final ActionEvent event) {
        closeScene();
    }

    @FXML
    protected void initialize() {
        webView.setContextMenuEnabled(false);
        webView.getEngine().load(getValue().toString());
    }

    @FxCreator
    public static ViewWebPageController create(final Object... args) {
        final ViewWebPageController result;
        final ApplicationBase<?> application = (ApplicationBase<?>) args[0];
        final Object src = args[1];

        if (src instanceof String) {
            try {
                result = new ViewWebPageController(application, new URL((String) src));
            } catch (final Exception e) {
                throw new IllegalStateException(String.format("Unable create controller - error in [%s] source.", src), e);
            }
        } else if (src instanceof URI) {
            try {
                result = new ViewWebPageController(application, ((URI) src).toURL());
            } catch (final Exception e) {
                throw new IllegalStateException(String.format("Unable create controller - error in [%s] source.", src), e);
            }
        } else if (src instanceof URL) {
            result = new ViewWebPageController(application, (URL) src);
        } else {
            throw new IllegalStateException(String.format("Unable create controller - unknown [%s] source type.", src));
        }

        return result;
    }

}
