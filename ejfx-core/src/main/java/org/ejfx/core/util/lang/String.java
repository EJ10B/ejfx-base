package org.ejfx.core.util.lang;

public final class String implements Defined<java.lang.String> {

    public static final String DEFAULT = new String(null);

    public static final String EMPTY = new String("");

    private final java.lang.String value;

    private String(java.lang.String value) {
        this.value = value;
    }

    @Override
    public boolean isDefined() {
        return this != DEFAULT;
    }

    @Override
    public java.lang.String getValue() {
        return value;
    }

    public static String of(final java.lang.String value) {
        final String result;

        if (value == null) {
            result = DEFAULT;
        } else if (value.isEmpty()) {
            result = EMPTY;
        } else {
            result = new String(value);
        }

        return result;
    }

}
