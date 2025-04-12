package org.ejfx.core.scene.controller.processor;

public interface Processor<T, D> {

    void process(T controller, D descriptor);

}
