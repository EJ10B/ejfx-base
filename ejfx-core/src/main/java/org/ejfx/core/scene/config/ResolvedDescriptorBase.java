package org.ejfx.core.scene.config;

import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.RESOURCE_KEY_PREFIX;

public abstract class ResolvedDescriptorBase extends DescriptorBase {

    private final ResourceBundle resources;

    protected ResolvedDescriptorBase(final ResourceBundle resources) {
        super();

        this.resources = resources;
    }

    protected ResolvedDescriptorBase(final String name,
                                     final ResourceBundle resources) {
        super(name);

        this.resources = resources;
    }

    public final ResourceBundle getResources() {
        return resources;
    }

    public final String getString(final String value) {
        String result;

        if (value != null && value.startsWith(RESOURCE_KEY_PREFIX)) {
            if (resources != null) {
                final String key = value.substring(RESOURCE_KEY_PREFIX.length());

                if (resources.containsKey(key)) {
                    result = resources.getString(key);
                } else {
                    throw new IllegalStateException(String.format("Resource with key [%s] not found.", key));
                }
            } else {
                throw new IllegalStateException("No resources specified.");
            }
        } else {
            result = value;
        }

        return result;
    }

}
