package org.ejfx.core.scene.controller.processor;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.ejfx.core.controller.ControllerBase;
import org.ejfx.core.scene.SceneManagerBase;
import org.ejfx.core.scene.controller.config.ShowSceneDescriptor;
import org.ejfx.core.util.builder.BuilderEx;
import org.ejfx.core.util.builder.SingletonBuilderEx;

public final class ShowSceneProcessor extends SetOnActionProcessorBase<ControllerBase<?>, ShowSceneDescriptor<ControllerBase<?>>> {

    private static final BuilderEx<ShowSceneProcessor> BUILDER =
            SingletonBuilderEx.of(ShowSceneDescriptor.class, new ShowSceneProcessor());

    private ShowSceneProcessor() {
        super();
    }

    @Override
    protected EventHandler<ActionEvent> getOnAction(final ControllerBase<?> controller,
                                                    final ShowSceneDescriptor<ControllerBase<?>> descriptor) {
        return (event) -> doHandleAction(controller, descriptor);
    }

    private static void doHandleAction(final ControllerBase<?> controller,
                                       final ShowSceneDescriptor<ControllerBase<?>> descriptor) {
        final SceneManagerBase<?> manager = controller.getApplication().getSceneManager();

        manager.showScene(descriptor.getName());
    }

    public static BuilderEx<ShowSceneProcessor> getBuilder() {
        return BUILDER;
    }

}
