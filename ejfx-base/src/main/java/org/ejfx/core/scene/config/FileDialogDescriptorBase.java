package org.ejfx.core.scene.config;

import javafx.stage.FileChooser.ExtensionFilter;
import org.ejfx.core.util.Arguments;

import java.util.List;

public abstract class FileDialogDescriptorBase extends DescriptorBase {

    private final String title;

    private final List<ExtensionFilter> filters;

    protected FileDialogDescriptorBase(final String name,
                                       final String title,
                                       final List<ExtensionFilter> filters) {
        super(name);

        this.title = Arguments.requireNonEmptyEx(title, "title");
        this.filters = Arguments.copyOfNonNullEx(filters, "filters");
    }

    public final String getTitle() {
        return title;
    }

    public final List<ExtensionFilter> getFilters() {
        return filters;
    }

}
