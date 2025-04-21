package org.ejfx.core.controller;

import org.ejfx.core.application.ApplicationBase;

public abstract class ControllerBaseEx<A extends ApplicationBase<?>, E> extends ControllerBase<A> {

    private final E value;

    protected ControllerBaseEx(final A application, final E value) {
        super(application);

        this.value = value;
    }

    protected final E getValue() {
        return value;
    }

}
