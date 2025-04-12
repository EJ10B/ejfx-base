package org.ejfx.core.annotation;

import org.ejfx.core.stage.FileDialogType;

import java.lang.annotation.*;

@Repeatable(FxFileDialogs.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FxFileDialog {

    String name();

    FileDialogType type() default FileDialogType.DEFAULT;

    String title() default "";

    FxExtensionFilter[] filters() default {};

}
