package org.ejfx.core.scene.controller.processor;

import org.ejfx.core.util.builder.BuilderExFactoryBase;

import java.util.List;

public final class ProcessorBuilderFactory extends BuilderExFactoryBase {

    private ProcessorBuilderFactory() {
        super(List.of(ShowDialogProcessor.getBuilder(),
                ShowFileDialogProcessor.getBuilder(),
                ShowSceneProcessor.getBuilder()));
    }

    public static ProcessorBuilderFactory of() {
        return new ProcessorBuilderFactory();
    }

}
