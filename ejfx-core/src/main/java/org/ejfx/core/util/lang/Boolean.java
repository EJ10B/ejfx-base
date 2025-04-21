package org.ejfx.core.util.lang;

import org.ejfx.core.util.Arguments;

public enum Boolean implements Defined<java.lang.Boolean> {

    DEFAULT(null),
    FALSE(java.lang.Boolean.FALSE),
    TRUE(java.lang.Boolean.TRUE);

    private final java.lang.Boolean value;

    Boolean(final java.lang.Boolean value) {
        this.value = value;
    }

    @Override
    public boolean isDefined() {
        return value != null;
    }

    @Override
    public java.lang.Boolean getValue() {
        return Arguments.requireNonNull(value, "Unable get 'undefined' value.");
    }

}
