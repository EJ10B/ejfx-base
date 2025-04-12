package org.ejfx.core.scene;

import javafx.stage.Stage;
import javafx.util.BuilderFactory;
import org.ejfx.core.scene.config.DefinedDialogDescriptor;
import org.ejfx.core.scene.config.DefinedFileDialogDescriptor;
import org.ejfx.core.scene.config.DefinedSceneDescriptor;
import org.ejfx.core.scene.config.DefinedSceneManagerDescriptor;
import org.ejfx.core.scene.controller.ControllerManager;
import org.ejfx.core.scene.controller.ControllerManagerBuilderFactory;
import org.ejfx.core.util.Arguments;

public class SceneManager<A> extends SceneManagerBase<A> {

    private final DefinedSceneManagerDescriptor descriptor;

    private final BuilderFactory factory;

    private SceneManager(final A application,
                         final Stage primaryStage,
                         final DefinedSceneManagerDescriptor descriptor) {
        super(application, primaryStage);

        this.descriptor = Arguments.requireNonNull(descriptor, "descriptor");
        this.factory = ControllerManagerBuilderFactory.of();
    }

    @SuppressWarnings("unchecked")
    @Override
    protected <T> ControllerManager<T> getControllerManager(final Class<?> type) {
        return (ControllerManager<T>) factory.getBuilder(type).build();
    }

    @Override
    protected DefinedDialogDescriptor getDialogDescriptor(final String name) {
        return descriptor.getDialogDescriptor(name);
    }

    @Override
    protected DefinedFileDialogDescriptor getFileDialogDescriptor(final String name) {
        return descriptor.getFileDialogDescriptor(name);
    }

    @Override
    protected DefinedSceneDescriptor getSceneDescriptor(final String name) {
        return descriptor.getSceneDescriptor(name);
    }

    public static <A> SceneManager<A> of(final A application,
                                         final Stage primaryStage,
                                         final DefinedSceneManagerDescriptor descriptor) {
        return new SceneManager<>(application, primaryStage, descriptor);
    }

}
