package org.ejfx.core.scene.controller.config.annotation;

import org.ejfx.core.annotation.FxCreator;
import org.ejfx.core.annotation.FxShowDialog;
import org.ejfx.core.annotation.FxShowFileDialog;
import org.ejfx.core.annotation.FxShowScene;
import org.ejfx.core.function.FunctionFactory;
import org.ejfx.core.function.creator.Creator;
import org.ejfx.core.function.creator.MethodCreatorFactory;
import org.ejfx.core.function.getter.FieldGetterFactory;
import org.ejfx.core.function.getter.Getter;
import org.ejfx.core.function.handler.Handler;
import org.ejfx.core.function.handler.MethodHandlerFactory;
import org.ejfx.core.scene.controller.config.*;
import org.ejfx.core.util.config.annotation.DescriptorLoaderBase;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

public abstract class ControllerDescriptorLoaderBase<T> extends DescriptorLoaderBase<T, DescriptorBase<T>, ControllerDescriptor<T>> {

    private static final List<Class<? extends Annotation>> FIELD_ANNOTATION_TYPES =
            List.of(FxShowDialog.class, FxShowFileDialog.class, FxShowScene.class);

    private final FunctionFactory<Method, Creator<T>> creatorFactory;
    private final FunctionFactory<Field, Getter<T, ?>> getterFactory;
    private final FunctionFactory<Method, Handler<T, ?>> handlerFactory;

    protected ControllerDescriptorLoaderBase(final Class<T> type) {
        super(type);

        creatorFactory = MethodCreatorFactory.of();
        getterFactory = FieldGetterFactory.of();
        handlerFactory = MethodHandlerFactory.of();
    }

    @Override
    protected List<Class<? extends Annotation>> getClassAnnotationTypes(final Class<?> type) {
        return List.of();
    }

    @Override
    protected List<Class<? extends Annotation>> getClassRepeatableAnnotationTypes(final Class<?> type) {
        return List.of();
    }

    @Override
    protected List<Class<? extends Annotation>> getFieldAnnotationTypes(final Class<?> type) {
        return FIELD_ANNOTATION_TYPES;
    }

    @Override
    protected DescriptorBase<T> getDescriptor(final Field field, final Annotation annotation) {
        final DescriptorBase<T> result;

        if (annotation instanceof FxShowDialog) {
            result = doGetShowDialogDescriptor(field, (FxShowDialog) annotation);
        } else if (annotation instanceof FxShowFileDialog) {
            result = doGetShowFileDialogDescriptor(field, (FxShowFileDialog) annotation);
        } else if (annotation instanceof FxShowScene) {
            result = doGetShowSceneDescriptor(field, (FxShowScene) annotation);
        } else {
            result = super.getDescriptor(field, annotation);
        }

        return result;
    }

    @Override
    protected List<Class<? extends Annotation>> getFieldRepeatableAnnotationTypes(final Class<?> type) {
        return List.of();
    }

    @Override
    protected List<Class<? extends Annotation>> getMethodAnnotationTypes(final Class<?> type) {
        return (getType().equals(type)) ? List.of(FxCreator.class) : List.of();
    }

    @Override
    protected DescriptorBase<T> getDescriptor(final Method method, final Annotation annotation) {
        final DescriptorBase<T> result;

        if (annotation instanceof FxCreator) {
            result = doGetCreatorDescriptor(method, (FxCreator) annotation);
        } else {
            result = super.getDescriptor(method, annotation);
        }

        return result;
    }

    @Override
    protected List<Class<? extends Annotation>> getMethodRepeatableAnnotationTypes(final Class<?> type) {
        return List.of();
    }

    private DescriptorBase<T> doGetCreatorDescriptor(final Method method, final FxCreator annotation) {
        return CreatorDescriptor.of(doGetCreator(method));
    }

    private DescriptorBase<T> doGetShowDialogDescriptor(final Field field, final FxShowDialog annotation) {
        return ShowDialogDescriptor.of(getGetter(field),
                annotation.name(),
                getHandler(doGetMethod(annotation.handler(), Optional.class)));
    }

    private DescriptorBase<T> doGetShowFileDialogDescriptor(final Field field, final FxShowFileDialog annotation) {
        return ShowFileDialogDescriptor.of(getGetter(field),
                annotation.name(),
                getHandler(doGetMethod(annotation.handler(), List.class)));
    }

    private DescriptorBase<T> doGetShowSceneDescriptor(final Field field, final FxShowScene annotation) {
        return ShowSceneDescriptor.of(getGetter(field), annotation.name());
    }

    private Creator<T> doGetCreator(final Method method) {
        return creatorFactory.create(method);
    }

    protected Getter<T, ?> getGetter(final Field field) {
        return getterFactory.create(field);
    }

    protected Handler<T, ?> getHandler(final Method method) {
        return handlerFactory.create(method);
    }

    private Method doGetMethod(final String name, final Class<?>... parameterTypes) {
        Method result = null;

        if (name != null && !name.isEmpty()) {
            for (Class<?> type = getType(); !Object.class.equals(type); type = type.getSuperclass()) {
                try {
                    result = type.getDeclaredMethod(name, parameterTypes);
                    break;
                } catch (final NoSuchMethodException e) {
                    // do nothing
                }
            }
        }

        return result;
    }

}
