package org.ejfx.core.util;

import org.ejfx.core.util.lang.Defined;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public final class Arguments {

    private static final String REQUIRE_NON_NULL = "The argument '%s' can not be null.";

    private static final String REQUIRE_NON_EMPTY = "The argument '%s' can not be null or empty.";

    // private static final String REQUIRE_INSTANCE_OF = "The argument '%s' can not be null or not instance of '%s'.";

    private static final String REQUIRE_DEFINED = "The argument '%s' can not be null or not defined.";

    private Arguments() {
        // no-op
    }

    public static <T> T requireNonNull(final T obj, final String argName) {
        if (obj == null) {
            throw new IllegalArgumentException(String.format(REQUIRE_NON_NULL, argName));
        }

        return obj;
    }

    public static <T> T requireNonNullEx(final T obj, final String message) {
        if (obj == null) {
            throw new IllegalArgumentException(message);
        }

        return obj;
    }

    public static String requireNonEmpty(final String str, final String argName) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException(String.format(REQUIRE_NON_EMPTY, argName));
        }

        return str;
    }

    public static String requireNonEmptyEx(final String str, final String message) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException(message);
        }

        return str;
    }

    public static <E> Collection<E> requireNonEmpty(final Collection<E> collection, final String argName) {
        if (collection == null || collection.isEmpty()) {
            throw new IllegalArgumentException(String.format(REQUIRE_NON_EMPTY, argName));
        }

        return collection;
    }

    public static <E> Collection<E> requireNonEmptyEx(final Collection<E> collection, final String message) {
        if (collection == null || collection.isEmpty()) {
            throw new IllegalArgumentException(message);
        }

        return collection;
    }

    public static <E, T extends Defined<E>> T requireDefined(final T obj, final String argName) {
        if (obj == null || !obj.isDefined()) {
            throw new IllegalArgumentException(String.format(REQUIRE_DEFINED, argName));
        }

        return obj;
    }

    public static <E, T extends Defined<E>> T requireDefinedEx(final T obj, final String message) {
        if (obj == null || !obj.isDefined()) {
            throw new IllegalArgumentException(message);
        }

        return obj;
    }

    public static <T> T getDefined(final Defined<T> value, final Defined<T> defaultValue) {
        final T result;

        if (value != null && value.isDefined()) {
            result = value.getValue();
        } else if (defaultValue != null && defaultValue.isDefined()) {
            result = defaultValue.getValue();
        } else {
            throw new IllegalArgumentException(String.format(REQUIRE_DEFINED, "defaultValue"));
        }

        return result;
    }

    public static <E> List<E> copyOf(E obj) {
        return (obj != null) ? List.of(obj) : List.of();
    }

    public static <E> List<E> copyOfNonNull(final E obj, final String argName) {
        if (obj == null) {
            throw new IllegalArgumentException(String.format(REQUIRE_NON_NULL, argName));
        }

        return List.of(obj);
    }

    public static <E> List<E> copyOfNonNullEx(final E obj, final String message) {
        if (obj == null) {
            throw new IllegalArgumentException(message);
        }

        return List.of(obj);
    }

    public static <E> List<E> copyOf(final Collection<? extends E> collection) {
        return (collection != null) ? List.copyOf(collection) : List.of();
    }

    public static <E> List<E> copyOfNonNull(final Collection<? extends E> collection, final String argName) {
        if (collection == null) {
            throw new IllegalArgumentException(String.format(REQUIRE_NON_NULL, argName));
        }

        return List.copyOf(collection);
    }

    public static <E> List<E> copyOfNonNullEx(final Collection<? extends E> collection, final String message) {
        if (collection == null) {
            throw new IllegalArgumentException(message);
        }

        return List.copyOf(collection);
    }

    public static <E> List<E> copyOfNonEmpty(final Collection<? extends E> collection, final String argName) {
        if (collection == null || collection.isEmpty()) {
            throw new IllegalArgumentException(String.format(REQUIRE_NON_EMPTY, argName));
        }

        return List.copyOf(collection);
    }

    public static <E> List<E> copyOfNonEmptyEx(final Collection<? extends E> collection, final String message) {
        if (collection == null || collection.isEmpty()) {
            throw new IllegalArgumentException(message);
        }

        return List.copyOf(collection);
    }

    public static <K, V> Map<K, V> copyOfNonNull(final Map<? extends K, ? extends V> collection, final String argName) {
        if (collection == null) {
            throw new IllegalArgumentException(String.format(REQUIRE_NON_NULL, argName));
        }

        return Map.copyOf(collection);
    }

    public static <K, V> Map<K, V> copyOfNonNullEx(final Map<? extends K, ? extends V> collection, final String message) {
        if (collection == null) {
            throw new IllegalArgumentException(message);
        }

        return Map.copyOf(collection);
    }

    public static <K, V> Map<K, V> copyOfNonEmpty(final Map<? extends K, ? extends V> collection, final String argName) {
        if (collection == null || collection.isEmpty()) {
            throw new IllegalArgumentException(String.format(REQUIRE_NON_EMPTY, argName));
        }

        return Map.copyOf(collection);
    }

    public static <K, V> Map<K, V> copyOfNonEmptyEx(final Map<? extends K, ? extends V> collection, final String message) {
        if (collection == null || collection.isEmpty()) {
            throw new IllegalArgumentException(message);
        }

        return Map.copyOf(collection);
    }

    public static <E> List<E> concat(final Collection<? extends E> collectionA, final Collection<? extends E> collectionB) {
        final List<E> result;
        final ArrayList<E> temp = (collectionA != null && !collectionA.isEmpty()) ? new ArrayList<>(collectionA) : null;

        if (collectionB != null && !collectionB.isEmpty()) {
            if (temp != null) {
                temp.addAll(collectionB);
                result = List.copyOf(temp);
            } else {
                result = List.copyOf(collectionB);
            }
        } else {
            if (temp != null) {
                result = List.copyOf(temp);
            } else {
                result = List.of();
            }
        }

        return result;
    }

}
