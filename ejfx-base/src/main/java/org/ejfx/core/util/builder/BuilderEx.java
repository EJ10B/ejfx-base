package org.ejfx.core.util.builder;

import javafx.util.Builder;

import java.util.List;

public interface BuilderEx<T> extends Builder<T> {

    List<Class<?>> getTypes();

}
