package org.ejfx.core.scene.config;

import javafx.stage.FileChooser.ExtensionFilter;
import org.ejfx.core.stage.FileDialogType;
import org.ejfx.core.util.Arguments;

import java.util.List;

public final class FileDialogDescriptor extends FileDialogDescriptorBase {

    private final FileDialogType type;

    private FileDialogDescriptor(final String name,
                                 final FileDialogType type,
                                 final String title,
                                 final List<ExtensionFilter> filters) {
        super(name, title, filters);

        this.type = Arguments.requireNonNull(type, "type");
    }

    public FileDialogType getType() {
        return type;
    }

    public DefinedFileDialogDescriptor getDefined(final DefaultFileDialogDescriptor descriptor) {
        return DefinedFileDialogDescriptor.of(getName(),
                Arguments.getDefined(type, descriptor.getType()),
                getTitle(),
                getFilters());
    }

    public static FileDialogDescriptor of(final String name,
                                          final FileDialogType type,
                                          final String title,
                                          final List<ExtensionFilter> filters) {
        return new FileDialogDescriptor(name,
                type,
                title,
                filters);
    }

}
