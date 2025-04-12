package org.ejfx.core.function.creator;

import java.lang.reflect.Method;

public interface CreatorFactory {

    <T> Creator<T> get(Method method);

}
