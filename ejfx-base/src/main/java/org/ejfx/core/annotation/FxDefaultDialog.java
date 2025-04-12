package org.ejfx.core.annotation;

import org.ejfx.core.stage.DialogType;
import org.ejfx.core.stage.Modality;
import org.ejfx.core.util.lang.Boolean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FxDefaultDialog {

    DialogType type() default DialogType.INFORMATION;

    Modality modality() default Modality.WINDOW;

    Boolean expanded() default Boolean.FALSE;

}
