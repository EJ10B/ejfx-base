package org.ejfx.core.stage;

import javafx.scene.control.Alert.AlertType;
import org.ejfx.core.util.lang.Defined;

public enum DialogType implements Defined<DialogType> {

    CONFIRMATION(AlertType.CONFIRMATION),
    DEFAULT(null),
    ERROR(AlertType.ERROR),
    INFORMATION(AlertType.INFORMATION),
    NONE(AlertType.NONE),
    WARNING(AlertType.WARNING),
    TEXT(null),
    CHOICE(null);

    private final AlertType value;

    DialogType(final AlertType value) {
        this.value = value;
    }

    @Override
    public final boolean isDefined() {
        return this != DEFAULT;
    }

    @Override
    public DialogType getValue() {
        return this;
    }

    public AlertType getAlertType() {
        return value;
    }

}
