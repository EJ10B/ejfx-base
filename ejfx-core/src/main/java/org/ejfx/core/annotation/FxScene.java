package org.ejfx.core.annotation;

import java.lang.annotation.*;

@Repeatable(FxScenes.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FxScene {

    String name();

    String location();

    String resources() default "";

    FxStage[] stage() default {};

    Class<?> controller() default Object.class;

}
