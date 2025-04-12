package org.ejfx.core.scene.controller;

public interface ControllerManager<T> {

    T create(Object... args);

    void process(T controller);

}
