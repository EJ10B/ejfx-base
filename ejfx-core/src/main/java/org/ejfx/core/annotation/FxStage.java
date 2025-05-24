package org.ejfx.core.annotation;

import org.ejfx.core.stage.Modality;
import org.ejfx.core.util.lang.Boolean;

import static org.ejfx.core.stage.Modality.DEFAULT;

public @interface FxStage {

    Modality modality() default DEFAULT;

    String title() default "";

    Boolean resizable() default Boolean.DEFAULT;

    Boolean maximized() default Boolean.DEFAULT;

    Boolean iconified() default Boolean.DEFAULT;

}
