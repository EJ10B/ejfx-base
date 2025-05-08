package org.ejfx.core.scene.config.annotation;

import javafx.stage.FileChooser;
import org.ejfx.core.scene.config.*;
import org.ejfx.core.stage.DialogType;
import org.ejfx.core.stage.FileDialogType;
import org.ejfx.core.stage.Modality;
import org.ejfx.core.util.config.annotation.DescriptorLoaderBase;
import org.ejfx.core.util.lang.Boolean;

import java.util.HashMap;
import java.util.List;

public abstract class SceneManagerDescriptorLoaderBase<T> extends DescriptorLoaderBase<T, DescriptorBase, DefinedSceneManagerDescriptor> {

    protected SceneManagerDescriptorLoaderBase(final Class<T> type) {
        super(type);
    }

    @Override
    protected DefinedSceneManagerDescriptor convertDescriptors(final List<DescriptorBase> descriptors) {
        final DefinedSceneManagerDescriptor result;

        if (!descriptors.isEmpty()) {
            final HashMap<String, SceneDescriptor> sceneDescriptors = HashMap.newHashMap(descriptors.size());
            DefaultStageDescriptor defaultStageDescriptor = DefaultStageDescriptor.of();
            final HashMap<String, DialogDescriptor> dialogDescriptors = HashMap.newHashMap(descriptors.size());
            DefaultDialogDescriptor defaultDialogDescriptor = DefaultDialogDescriptor.of();
            final HashMap<String, FileDialogDescriptor> fileDialogDescriptors = HashMap.newHashMap(descriptors.size());
            DefaultFileDialogDescriptor defaultFileDialogDescriptor = DefaultFileDialogDescriptor.of();

            for (final DescriptorBase descriptor : descriptors) {
                switch (descriptor) {
                    case final SceneDescriptor sceneDescriptor ->
                            sceneDescriptors.put(sceneDescriptor.getName(), sceneDescriptor);
                    case final DialogDescriptor dialogDescriptor ->
                            dialogDescriptors.put(dialogDescriptor.getName(), dialogDescriptor);
                    case final FileDialogDescriptor fileDialogDescriptor ->
                            fileDialogDescriptors.put(fileDialogDescriptor.getName(), fileDialogDescriptor);
                    case final DefaultDialogDescriptor dialogDescriptor -> defaultDialogDescriptor = dialogDescriptor;
                    case final DefaultFileDialogDescriptor fileDialogDescriptor ->
                            defaultFileDialogDescriptor = fileDialogDescriptor;
                    case final DefaultStageDescriptor stageDescriptor -> defaultStageDescriptor = stageDescriptor;
                    case null, default ->
                            throw new IllegalStateException(String.format("Unable convert descriptor - unknown [%s] descriptor.", descriptor));
                }
            }

            result = SceneManagerDescriptor.of(sceneDescriptors,
                    defaultStageDescriptor,
                    dialogDescriptors,
                    defaultDialogDescriptor,
                    fileDialogDescriptors,
                    defaultFileDialogDescriptor).getDefined();
        } else {
            result = SceneManagerDescriptor.of(DefaultStageDescriptor.of(),
                    DefaultDialogDescriptor.of(),
                    DefaultFileDialogDescriptor.of()).getDefined();
        }

        return result;
    }

    protected DescriptorBase getSceneDescriptor(final String name,
                                                final StageDescriptor descriptor,
                                                final String location,
                                                final String resources,
                                                final Class<?> controller) {
        return SceneDescriptor.of(name, descriptor, location, resources, controller);
    }

    protected DescriptorBase getDialogDescriptor(final String name,
                                                 final DialogType type,
                                                 final Modality modality,
                                                 final String title,
                                                 final String header,
                                                 final String content,
                                                 final Boolean expanded) {
        return DialogDescriptor.of(name, type, modality, title, header, content, expanded);
    }

    protected DescriptorBase getFileDialogDescriptor(final String name,
                                                     final FileDialogType type,
                                                     final String title,
                                                     final List<FileChooser.ExtensionFilter> filters) {
        return FileDialogDescriptor.of(name, type, title, filters);
    }

    protected FileChooser.ExtensionFilter getExtensionFilter(final String description,
                                                             final String[] extensions) {
        return new FileChooser.ExtensionFilter(description, extensions);
    }

    protected StageDescriptor getStageDescriptor(final Modality modality,
                                                 final String title,
                                                 final Boolean resizable) {
        return StageDescriptor.of(modality, title, resizable);
    }

    protected DescriptorBase getDefaultDialogDescriptor(final DialogType type,
                                                        final Modality modality,
                                                        final Boolean expanded) {
        return DefaultDialogDescriptor.of(type, modality, expanded);
    }

    protected DescriptorBase getDefaultFileDialogDescriptor(final FileDialogType type) {
        return DefaultFileDialogDescriptor.of(type);
    }

    protected DescriptorBase getDefaultStageDescriptor(final Modality modality,
                                                       final Boolean resizable) {
        return DefaultStageDescriptor.of(modality, resizable);
    }

}
