package org.ejfx.core.scene.config;

import org.ejfx.core.util.Arguments;

import java.util.HashMap;
import java.util.Map;

public final class SceneManagerDescriptor {

    private final Map<String, SceneDescriptor> sceneDescriptors;

    private final DefaultStageDescriptor defaultStageDescriptor;

    private final Map<String, DialogDescriptor> dialogDescriptors;

    private final DefaultDialogDescriptor defaultDialogDescriptor;

    private final Map<String, FileDialogDescriptor> fileDialogDescriptors;

    private final DefaultFileDialogDescriptor defaultFileDialogDescriptor;

    private SceneManagerDescriptor(final Map<String, SceneDescriptor> sceneDescriptors,
                                   final DefaultStageDescriptor defaultStageDescriptor,
                                   final Map<String, DialogDescriptor> dialogDescriptors,
                                   final DefaultDialogDescriptor defaultDialogDescriptor,
                                   final Map<String, FileDialogDescriptor> fileDialogDescriptors,
                                   final DefaultFileDialogDescriptor defaultFileDialogDescriptor) {
        this.dialogDescriptors = Arguments.copyOfNonNullEx(dialogDescriptors, "dialogDescriptors");
        this.defaultDialogDescriptor = Arguments.requireNonNull(defaultDialogDescriptor, "defaultDialogDescriptors");
        this.fileDialogDescriptors = Arguments.copyOfNonNullEx(fileDialogDescriptors, "fileDialogDescriptors");
        this.defaultFileDialogDescriptor = Arguments.requireNonNull(defaultFileDialogDescriptor, "defaultFileDialogDescriptor");
        this.sceneDescriptors = Arguments.copyOfNonNullEx(sceneDescriptors, "sceneDescriptors");
        this.defaultStageDescriptor = Arguments.requireNonNull(defaultStageDescriptor, "defaultStageDescriptor");
    }

    public SceneDescriptor getSceneDescriptor(final String name) {
        return sceneDescriptors.get(name);
    }

    public DefaultStageDescriptor getDefaultStageDescriptor() {
        return defaultStageDescriptor;
    }

    public DialogDescriptor getDialogDescriptor(final String name) {
        return dialogDescriptors.get(name);
    }

    public DefaultDialogDescriptor getDefaultDialogDescriptor() {
        return defaultDialogDescriptor;
    }

    public FileDialogDescriptor getFileDialogDescriptor(final String name) {
        return fileDialogDescriptors.get(name);
    }

    public DefaultFileDialogDescriptor getDefaultFileDialogDescriptor() {
        return defaultFileDialogDescriptor;
    }

    public DefinedSceneManagerDescriptor getDefined() {
        return DefinedSceneManagerDescriptor.of(doGetDefinedSceneDescriptors(),
                doGetDefinedDialogDescriptors(),
                doGetDefinedFileDialogDescriptors());
    }

    private Map<String, DefinedSceneDescriptor> doGetDefinedSceneDescriptors() {
        final Map<String, DefinedSceneDescriptor> result;

        if (!sceneDescriptors.isEmpty()) {
            final HashMap<String, DefinedSceneDescriptor> descriptors = new HashMap<>(sceneDescriptors.size());

            for (final String name : sceneDescriptors.keySet()) {
                descriptors.put(name, sceneDescriptors.get(name).getDefined(defaultStageDescriptor));
            }

            result = descriptors;
        } else {
            result = Map.of();
        }

        return result;
    }

    private Map<String, DefinedDialogDescriptor> doGetDefinedDialogDescriptors() {
        final Map<String, DefinedDialogDescriptor> result;

        if (!dialogDescriptors.isEmpty()) {
            final HashMap<String, DefinedDialogDescriptor> descriptors = new HashMap<>(dialogDescriptors.size());

            for (final String name : dialogDescriptors.keySet()) {
                descriptors.put(name, dialogDescriptors.get(name).getDefined(defaultDialogDescriptor));
            }

            result = descriptors;
        } else {
            result = Map.of();
        }

        return result;
    }

    private Map<String, DefinedFileDialogDescriptor> doGetDefinedFileDialogDescriptors() {
        final Map<String, DefinedFileDialogDescriptor> result;

        if (!fileDialogDescriptors.isEmpty()) {
            final HashMap<String, DefinedFileDialogDescriptor> descriptors = new HashMap<>(fileDialogDescriptors.size());

            for (final String name : fileDialogDescriptors.keySet()) {
                descriptors.put(name, fileDialogDescriptors.get(name).getDefined(defaultFileDialogDescriptor));
            }

            result = descriptors;
        } else {
            result = Map.of();
        }

        return result;
    }

    public static SceneManagerDescriptor of(final Map<String, SceneDescriptor> sceneDescriptors,
                                            final DefaultStageDescriptor defaultStageDescriptor,
                                            final Map<String, DialogDescriptor> dialogDescriptors,
                                            final DefaultDialogDescriptor defaultDialogDescriptor,
                                            final Map<String, FileDialogDescriptor> fileDialogDescriptors,
                                            final DefaultFileDialogDescriptor defaultFileDialogDescriptor) {
        return new SceneManagerDescriptor(sceneDescriptors,
                defaultStageDescriptor,
                dialogDescriptors,
                defaultDialogDescriptor,
                fileDialogDescriptors,
                defaultFileDialogDescriptor);
    }

}
