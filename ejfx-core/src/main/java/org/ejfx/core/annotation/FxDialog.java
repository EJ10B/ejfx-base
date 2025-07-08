package org.ejfx.core.annotation;

import org.ejfx.core.stage.DialogType;
import org.ejfx.core.stage.Modality;
import org.ejfx.core.util.lang.Boolean;

import java.lang.annotation.*;

@Repeatable(FxDialogs.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FxDialog {

    String name();

    DialogType type() default DialogType.DEFAULT;

    Modality modality() default Modality.DEFAULT;

    String resources() default "";

    String title() default "";

    String header() default "";

    String content() default "";

    Boolean expanded() default Boolean.DEFAULT;

}
