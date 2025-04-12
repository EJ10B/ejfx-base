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
        final DescriptorBase result;

        if (annotation instanceof FxScene) {
            result = doGetDescriptor(type, (FxScene) annotation);
        } else if (annotation instanceof FxDialog) {
            result = doGetDescriptor(type, (FxDialog) annotation);
        } else if (annotation instanceof FxFileDialog) {
            result = doGetDescriptor(type, (FxFileDialog) annotation);
        } else if (annotation instanceof FxDefaultDialog) {
            result = doGetDescriptor(type, (FxDefaultDialog) annotation);
        } else if (annotation instanceof FxDefaultFileDialog) {
            result = doGetDescriptor(type, (FxDefaultFileDialog) annotation);
        } else if (annotation instanceof FxDefaultStage) {
            result = doGetDescriptor(type, (FxDefaultStage) annotation);
        } else {
            result = super.getDescriptor(type, annotation);
        }

        return result;
    }

    @Override
    protected List<Class<? extends Annotation>> getClassRepeatableAnnotationTypes(final Class<?> type) {
        return List.of(FxScenes.class,
                FxDialogs.class,
                FxFileDialogs.class);
    }

    @Override
    protected List<DescriptorBase> getDescriptors(final Class<?> type, final Annotation annotation) {
        final List<DescriptorBase> result;

        if (annotation instanceof FxScenes) {
            result = doGetDescriptors(type, ((FxScenes) annotation).value());
        } else if (annotation instanceof FxDialogs) {
            result = doGetDescriptors(type, ((FxDialogs) annotation).value());
        } else if (annotation instanceof FxFileDialogs) {
            result = doGetDescriptors(type, ((FxFileDialogs) annotation).value());
        } else {
            result = super.getDescriptors(type, annotation);
        }

        return result;
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
                annotation.resource(),
                annotation.controller());
    }

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

    private StageDescriptor doGetStageDescriptor(final Class<?> type, final FxStage[] annotations) {
        StageDescriptor result = null;

        if (annotations != null && annotations.length > 0) {
            final FxStage annotation = annotations[0];
            result = getStageDescriptor(annotation.modality(), annotation.title(), annotation.resizable());
        }

        return result;
    }

    private DescriptorBase doGetDescriptor(final Class<?> type, final FxDefaultDialog annotation) {
        return getDefaultDialogDescriptor(annotation.type(), annotation.modality(), annotation.expanded());
    }

    private DescriptorBase doGetDescriptor(final Class<?> type, final FxDefaultFileDialog annotation) {
        return getDefaultFileDialogDescriptor(annotation.type());
    }

    private DescriptorBase doGetDescriptor(final Class<?> type, final FxDefaultStage annotation) {
        return getDefaultStageDescriptor(annotation.modality(), annotation.resizable());
    }

    public static <T> SceneManagerDescriptorLoader<T> of(final Class<T> type) {
        return new SceneManagerDescriptorLoader<>(type);
    }

}
