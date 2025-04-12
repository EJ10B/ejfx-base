module org.ejfx.demo {
    requires javafx.base;
    requires javafx.fxml;
    requires javafx.controls;
    requires ejfx.base;

    exports org.ejfx.demo.application;
    opens org.ejfx.demo.application to javafx.fxml;

    exports org.ejfx.demo.controller;
    opens org.ejfx.demo.controller to javafx.fxml;

}