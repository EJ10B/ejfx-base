package org.ejfx.core.scene.config;

import javafx.stage.FileChooser.ExtensionFilter;
import org.ejfx.core.stage.FileDialogType;
import org.ejfx.core.util.Arguments;

import java.util.List;

public class DefinedFileDialogDescriptor extends FileDialogDescriptorBase {

    private final FileDialogType type;

    private DefinedFileDialogDescriptor(final String name,
                                        final FileDialogType type,
                                        final String title,
                                        final List<ExtensionFilter> filters) {
        super(name, title, filters);

        this.type = Arguments.requireDefined(type, "type");
    }

    public FileDialogType getType() {
        return type;
    }

    public static DefinedFileDialogDescriptor of(final String name,
                                                 final FileDialogType type,
                                                 final String title,
                                                 final List<ExtensionFilter> filters) {
        return new DefinedFileDialogDescriptor(name,
                type,
                title,
                filters);
    }

}
