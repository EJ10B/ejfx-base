package org.ejfx.core.scene.config.annotation;

import javafx.stage.FileChooser;
import org.ejfx.core.annotation.*;
import org.ejfx.core.scene.config.DescriptorBase;
import org.ejfx.core.scene.config.StageDescriptor;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public class SceneManagerDescriptorLoader<T> extends SceneManagerDescriptorLoaderBase<T> {

    private SceneManagerDescriptorLoader(final Class<T> type) {
        super(type);
    }

    @Override
    protected List<Class<? extends Annotation>> getClassAnnotationTypes(final Class<?> type) {
        return List.of(FxScene.class,
                FxDialog.class,
                FxFileDialog.class,
                FxDefaultDialog.class,
                FxDefaultFileDialog.class,
                FxDefaultStage.class);
    }

    @Override
    protected DescriptorBase getDescriptor(final Class<?> type, final Annotation annotation) {
        return switch (annotation) {
            case final FxScene fxScene -> doGetDescriptor(type, fxScene);
            case final FxDialog fxDialog -> doGetDescriptor(type, fxDialog);
            case final FxFileDialog fxFileDialog -> doGetDescriptor(type, fxFileDialog);
            case final FxDefaultDialog fxDefaultDialog -> doGetDescriptor(type, fxDefaultDialog);
            case final FxDefaultFileDialog fxDefaultFileDialog -> doGetDescriptor(type, fxDefaultFileDialog);
            case final FxDefaultStage fxDefaultStage -> doGetDescriptor(type, fxDefaultStage);
            case null, default -> super.getDescriptor(type, annotation);
        };
    }

    @Override
    protected List<Class<? extends Annotation>> getClassRepeatableAnnotationTypes(final Class<?> type) {
        return List.of(FxScenes.class,
                FxDialogs.class,
                FxFileDialogs.class);
    }

    @Override
    protected List<DescriptorBase> getDescriptors(final Class<?> type, final Annotation annotation) {
        return switch (annotation) {
            case final FxScenes fxScenes -> doGetDescriptors(type, fxScenes.value());
            case final FxDialogs fxDialogs -> doGetDescriptors(type, fxDialogs.value());
            case final FxFileDialogs fxFileDialogs -> doGetDescriptors(type, fxFileDialogs.value());
            case null, default -> super.getDescriptors(type, annotation);
        };
    }

    @Override
    protected List<Class<? extends Annotation>> getFieldAnnotationTypes(final Class<?> type) {
        return List.of();
    }

    @Override
    protected List<Class<? extends Annotation>> getFieldRepeatableAnnotationTypes(final Class<?> type) {
        return List.of();
    }

    @Override
    protected List<Class<? extends Annotation>> getMethodAnnotationTypes(final Class<?> type) {
        return List.of();
    }

    @Override
    protected List<Class<? extends Annotation>> getMethodRepeatableAnnotationTypes(final Class<?> type) {
        return List.of();
    }

    private List<DescriptorBase> doGetDescriptors(final Class<?> type, final FxDialog[] annotations) {
        List<DescriptorBase> result = List.of();

        if (annotations != null && annotations.length > 0) {
            final ArrayList<DescriptorBase> descriptors = new ArrayList<>(annotations.length);

            for (final FxDialog annotation : annotations) {
                descriptors.add(doGetDescriptor(type, annotation));
            }

            result = descriptors;
        }

        return result;
    }

    @SuppressWarnings("unused")
    private DescriptorBase doGetDescriptor(final Class<?> type, final FxDialog annotation) {
        return getDialogDescriptor(annotation.name(),
                annotation.type(),
                annotation.modality(),
                annotation.title(),
                annotation.header(),
                annotation.content(),
                annotation.expanded()
        );
    }

    private List<DescriptorBase> doGetDescriptors(final Class<?> type, final FxFileDialog[] annotations) {
        List<DescriptorBase> result = List.of();

        if (annotations != null && annotations.length > 0) {
            final ArrayList<DescriptorBase> descriptors = new ArrayList<>(annotations.length);

            for (final FxFileDialog annotation : annotations) {
                descriptors.add(doGetDescriptor(type, annotation));
            }

            result = descriptors;
        }

        return result;
    }

    private DescriptorBase doGetDescriptor(final Class<?> type, final FxFileDialog annotation) {
        return getFileDialogDescriptor(annotation.name(),
                annotation.type(),
                annotation.title(),
                doGetExtensionFilters(type, annotation.filters()));
    }

    private List<DescriptorBase> doGetDescriptors(final Class<?> type, final FxScene[] annotations) {
        List<DescriptorBase> result = List.of();

        if (annotations != null && annotations.length > 0) {
            final ArrayList<DescriptorBase> descriptors = new ArrayList<>(annotations.length);

            for (final FxScene annotation : annotations) {
                descriptors.add(doGetDescriptor(type, annotation));
            }

            result = descriptors;
        }

        return result;
    }

    private DescriptorBase doGetDescriptor(final Class<?> type, final FxScene annotation) {
        return getSceneDescriptor(annotation.name(),
                doGetStageDescriptor(type, annotation.stage()),
                annotation.location(),
                annotation.resources(),
                annotation.controller());
    }

    @SuppressWarnings("unused")
    private List<FileChooser.ExtensionFilter> doGetExtensionFilters(final Class<?> type, final FxExtensionFilter[] annotations) {
        List<FileChooser.ExtensionFilter> result = List.of();

        if (annotations != null && annotations.length > 0) {
            final ArrayList<FileChooser.ExtensionFilter> filters = new ArrayList<>(annotations.length);

            for (final FxExtensionFilter annotation : annotations) {
                filters.add(getExtensionFilter(annotation.description(), annotation.extensions()));
            }

            result = filters;
        }

        return result;
    }

    @SuppressWarnings("unused")
    private StageDescriptor doGetStageDescriptor(final Class<?> type, final FxStage[] annotations) {
        StageDescriptor result = null;

        if (annotations != null && annotations.length > 0) {
            final FxStage annotation = annotations[0];

            result = getStageDescriptor(annotation.modality(),
                    annotation.title(),
                    annotation.resizable(),
                    annotation.maximized(),
                    annotation.iconified());
        }

        return result;
    }

    @SuppressWarnings("unused")
    private DescriptorBase doGetDescriptor(final Class<?> type, final FxDefaultDialog annotation) {
        return getDefaultDialogDescriptor(annotation.type(), annotation.modality(), annotation.expanded());
    }

    @SuppressWarnings("unused")
    private DescriptorBase doGetDescriptor(final Class<?> type, final FxDefaultFileDialog annotation) {
        return getDefaultFileDialogDescriptor(annotation.type());
    }

    @SuppressWarnings("unused")
    private DescriptorBase doGetDescriptor(final Class<?> type, final FxDefaultStage annotation) {
        return getDefaultStageDescriptor(annotation.modality(),
                annotation.resizable(),
                annotation.maximized(),
                annotation.iconified());
    }

    public static <T> SceneManagerDescriptorLoader<T> of(final Class<T> type) {
        return new SceneManagerDescriptorLoader<>(type);
    }

}
