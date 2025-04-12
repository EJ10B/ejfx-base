package org.ejfx.core.scene;

import javafx.scene.Parent;

public final class Scene extends javafx.scene.Scene {

    private final Scene parent;

    private final Object controller;

    private Scene(final Parent root, final Scene parent, final Object controller) {
        super(root);

        this.parent = parent;
        this.controller = controller;
    }

    public Scene getParent() {
        return parent;
    }

    public Object getController() {
        return controller;
    }

    public static Scene of(final Parent root, final Scene parent, final Object controller) {
        return new Scene(root, parent, controller);
    }

}
