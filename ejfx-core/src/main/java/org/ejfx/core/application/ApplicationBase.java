package org.ejfx.core.application;

import javafx.application.Application;
import org.ejfx.core.scene.SceneManagerBase;
import org.ejfx.core.util.Arguments;

public abstract class ApplicationBase<S extends SceneManagerBase<?>> extends Application {

    private S manager;

    public final S getSceneManager() {
        return manager;
    }

    public final void setSceneManager(final S manager) {
        this.manager = Arguments.requireNonNull(manager, "manager");
    }

}
