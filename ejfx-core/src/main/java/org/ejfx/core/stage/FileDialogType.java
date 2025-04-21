package org.ejfx.core.stage;

import org.ejfx.core.util.lang.Defined;

public enum FileDialogType implements Defined<FileDialogType> {

    DEFAULT,
    OPEN_DIRECTORY,
    OPEN_FILE,
    OPEN_FILES,
    SAVE_FILE;

    @Override
    public boolean isDefined() {
        return (this != DEFAULT);
    }

    @Override
    public FileDialogType getValue() {
        return this;
    }

}
