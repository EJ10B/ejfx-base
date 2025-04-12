package org.ejfx.core.annotation;

import org.ejfx.core.stage.FileDialogType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FxDefaultFileDialog {

    FileDialogType type() default FileDialogType.OPEN_FILE;

}
