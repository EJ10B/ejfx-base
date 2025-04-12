module ejfx.base {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.web;

    exports org.ejfx.core.scene;
    exports org.ejfx.core.annotation;
    exports org.ejfx.core.application;
    exports org.ejfx.core.util.lang;
    exports org.ejfx.core.scene.config;
    exports org.ejfx.core.scene.config.annotation;
    exports org.ejfx.core.controller;
    exports org.ejfx.core.stage;
}