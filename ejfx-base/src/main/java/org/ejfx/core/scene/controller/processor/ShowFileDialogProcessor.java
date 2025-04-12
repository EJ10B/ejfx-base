package org.ejfx.core.scene.controller.processor;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.ejfx.core.controller.ControllerBase;
import org.ejfx.core.scene.SceneManagerBase;
import org.ejfx.core.scene.controller.config.ShowFileDialogDescriptor;
import org.ejfx.core.util.builder.BuilderEx;
import org.ejfx.core.util.builder.SingletonBuilderEx;

public final class ShowFileDialogProcessor extends SetOnActionProcessorBase<ControllerBase<?>, ShowFileDialogDescriptor<ControllerBase<?>>> {

    private static final BuilderEx<ShowFileDialogProcessor> BUILDER =
            SingletonBuilderEx.of(ShowFileDialogDescriptor.class, new ShowFileDialogProcessor());

    private ShowFileDialogProcessor() {
        super();
    }

    @Override
    protected EventHandler<ActionEvent> getOnAction(final ControllerBase<?> controller,
                                                    final ShowFileDialogDescriptor<ControllerBase<?>> descriptor) {
        return (event) -> doHandleAction(controller, descriptor);
    }

    private static void doHandleAction(final ControllerBase<?> controller,
                                       final ShowFileDialogDescriptor<ControllerBase<?>> descriptor) {
        final SceneManagerBase<?> manager = controller.getSceneManager();

        descriptor.handle(controller, manager.showFileDialog(descriptor.getName()));
    }

    public static BuilderEx<ShowFileDialogProcessor> getBuilder() {
        return BUILDER;
    }

}
