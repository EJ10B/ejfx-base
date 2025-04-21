module org.ejfx.core.sample {

    requires ejfx.core;
    requires javafx.fxml;
    requires javafx.controls;

    exports org.ejfx.core.sample.application;
    opens org.ejfx.core.sample.application to javafx.fxml;
    exports org.ejfx.core.sample.controller;
    opens org.ejfx.core.sample.controller to javafx.fxml;
}