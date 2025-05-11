package org.ejfx.core.scene.controller.processor;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import org.ejfx.core.scene.controller.config.FieldDescriptorBase;

public abstract class SetOnActionProcessorBase<T, D extends FieldDescriptorBase<T>> implements Processor<T, D> {

    @Override
    public void process(final T controller, final D descriptor) {
        doSetOnAction(descriptor.getField(controller), getOnAction(controller, descriptor));
    }

    protected abstract EventHandler<ActionEvent> getOnAction(T controller, D descriptor);

    private void doSetOnAction(final Object ctrl, final EventHandler<ActionEvent> handler) {
        switch (ctrl) {
            case final ButtonBase buttonBase -> buttonBase.setOnAction(handler);
            case final MenuItem menuItem -> menuItem.setOnAction(handler);
            case final ContextMenu contextMenu -> contextMenu.setOnAction(handler);
            case final TextField textField -> textField.setOnAction(handler);
            case null, default ->
                    throw new IllegalStateException(String.format("Unable set on action handler - unknown [%s] control.", ctrl));
        }
    }

}
