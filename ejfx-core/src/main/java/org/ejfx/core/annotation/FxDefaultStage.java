package org.ejfx.core.annotation;

import org.ejfx.core.stage.Modality;
import org.ejfx.core.util.lang.Boolean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FxDefaultStage {

    Modality modality() default Modality.WINDOW;

    Boolean resizable() default Boolean.FALSE;

    Boolean maximized() default Boolean.FALSE;

    Boolean iconified() default Boolean.FALSE;

}
