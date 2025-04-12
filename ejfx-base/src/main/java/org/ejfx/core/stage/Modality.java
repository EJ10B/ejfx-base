package org.ejfx.core.stage;

import org.ejfx.core.util.Arguments;
import org.ejfx.core.util.lang.Defined;

public enum Modality implements Defined<javafx.stage.Modality> {

    DEFAULT(null),

    NONE(javafx.stage.Modality.NONE),

    WINDOW(javafx.stage.Modality.WINDOW_MODAL),

    APPLICATION(javafx.stage.Modality.APPLICATION_MODAL);

    private final javafx.stage.Modality value;

    Modality(final javafx.stage.Modality value) {
        this.value = value;
    }

    @Override
    public boolean isDefined() {
        return value != null;
    }

    @Override
    public javafx.stage.Modality getValue() {
        return Arguments.requireNonNull(value, "Unable get 'undefined' value.");
    }

}
