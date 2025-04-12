package org.ejfx.core.scene.config;

import org.ejfx.core.stage.FileDialogType;
import org.ejfx.core.util.Arguments;

public final class DefaultFileDialogDescriptor extends DescriptorBase {

    private static final DefaultFileDialogDescriptor DEFAULT =
            new DefaultFileDialogDescriptor(FileDialogType.OPEN_FILE);

    private final FileDialogType type;

    private DefaultFileDialogDescriptor(final FileDialogType type) {
        super();

        this.type = Arguments.requireDefined(type, "type");
    }

    public FileDialogType getType() {
        return type;
    }

    public static DefaultFileDialogDescriptor of(final FileDialogType type) {
        return new DefaultFileDialogDescriptor(type);
    }

    public static DefaultFileDialogDescriptor of() {
        return DEFAULT;
    }

}
