package org.ejfx.core.scene.controller.processor;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.ejfx.core.controller.ControllerBase;
import org.ejfx.core.scene.SceneManagerBase;
import org.ejfx.core.scene.controller.config.ShowDialogDescriptor;
import org.ejfx.core.util.builder.BuilderEx;
import org.ejfx.core.util.builder.SingletonBuilderEx;

public final class ShowDialogProcessor extends SetOnActionProcessorBase<ControllerBase<?>, ShowDialogDescriptor<ControllerBase<?>>> {

    private static final BuilderEx<ShowDialogProcessor> BUILDER =
            SingletonBuilderEx.of(ShowDialogDescriptor.class, new ShowDialogProcessor());

    private ShowDialogProcessor() {
        super();
    }

    @Override
    protected EventHandler<ActionEvent> getOnAction(final ControllerBase<?> controller,
                                                    final ShowDialogDescriptor<ControllerBase<?>> descriptor) {
        return event -> doHandleAction(controller, descriptor);
    }

    private void doHandleAction(final ControllerBase<?> controller,
                                final ShowDialogDescriptor<ControllerBase<?>> descriptor) {
        final SceneManagerBase<?> manager = controller.getSceneManager();

        descriptor.handle(controller, manager.showDialog(descriptor.getName()));
    }

    public static BuilderEx<ShowDialogProcessor> getBuilder() {
        return BUILDER;
    }

}
