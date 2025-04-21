package org.ejfx.core.scene.config;

import org.ejfx.core.util.Arguments;

import java.util.Map;

public class DefinedSceneManagerDescriptor {

    private final Map<String, DefinedSceneDescriptor> sceneDescriptors;

    private final Map<String, DefinedDialogDescriptor> dialogDescriptors;

    private final Map<String, DefinedFileDialogDescriptor> fileDialogDescriptors;

    private DefinedSceneManagerDescriptor(final Map<String, DefinedSceneDescriptor> sceneDescriptors,
                                          final Map<String, DefinedDialogDescriptor> dialogDescriptors,
                                          final Map<String, DefinedFileDialogDescriptor> fileDialogDescriptors) {
        this.sceneDescriptors = Arguments.copyOfNonNullEx(sceneDescriptors, "sceneDescriptors");
        this.dialogDescriptors = Arguments.copyOfNonNullEx(dialogDescriptors, "dialogDescriptors");
        this.fileDialogDescriptors = Arguments.copyOfNonNullEx(fileDialogDescriptors, "fileDialogDescriptors");
    }

    public DefinedSceneDescriptor getSceneDescriptor(final String name) {
        return sceneDescriptors.get(name);
    }

    public DefinedDialogDescriptor getDialogDescriptor(final String name) {
        return dialogDescriptors.get(name);
    }

    public DefinedFileDialogDescriptor getFileDialogDescriptor(final String name) {
        return fileDialogDescriptors.get(name);
    }

    public static DefinedSceneManagerDescriptor of(final Map<String, DefinedSceneDescriptor> sceneDescriptors,
                                                   final Map<String, DefinedDialogDescriptor> dialogDescriptors,
                                                   final Map<String, DefinedFileDialogDescriptor> fileDialogDescriptors) {
        return new DefinedSceneManagerDescriptor(sceneDescriptors,
                dialogDescriptors,
                fileDialogDescriptors);
    }

}

