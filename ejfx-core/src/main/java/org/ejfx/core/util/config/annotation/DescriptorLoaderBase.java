package org.ejfx.core.util.config.annotation;

import org.ejfx.core.util.Arguments;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public abstract class DescriptorLoaderBase<T, E, V> {

    private final Class<T> type;

    protected DescriptorLoaderBase(final Class<T> type) {
        this.type = Arguments.requireNonNull(type, "type");
    }

    public final Class<T> getType() {
        return type;
    }

    public final V load() {
        return convertDescriptors(doGetDescriptors(type));
    }

    protected abstract V convertDescriptors(List<E> descriptors);

    protected abstract List<Class<? extends Annotation>> getClassAnnotationTypes(Class<?> type);

    protected abstract List<Class<? extends Annotation>> getClassRepeatableAnnotationTypes(Class<?> type);

    protected abstract List<Class<? extends Annotation>> getFieldAnnotationTypes(Class<?> type);

    protected abstract List<Class<? extends Annotation>> getFieldRepeatableAnnotationTypes(Class<?> type);

    protected abstract List<Class<? extends Annotation>> getMethodAnnotationTypes(Class<?> type);

    protected abstract List<Class<? extends Annotation>> getMethodRepeatableAnnotationTypes(Class<?> type);

    protected E getDescriptor(final Class<?> type, final Annotation annotation) {
        throw new IllegalStateException(String.format("Unable get descriptor - unknown [%s] type annotation.", annotation));
    }

    protected List<E> getDescriptors(final Class<?> type, final Annotation annotation) {
        throw new IllegalStateException(String.format("Unable get descriptors - unknown [%s] type annotation.", annotation));
    }

    protected E getDescriptor(final Field field, final Annotation annotation) {
        throw new IllegalStateException(String.format("Unable get descriptor - unknown [%s] field annotation.", annotation));
    }

    @SuppressWarnings("unused")
    protected List<E> getDescriptors(final Field field, final Annotation annotation) {
        throw new IllegalStateException(String.format("Unable get descriptors - unknown [%s] field annotation.", annotation));
    }

    protected E getDescriptor(final Method method, final Annotation annotation) {
        throw new IllegalStateException(String.format("Unable get descriptor - unknown [%s] method annotation.", annotation));
    }

    @SuppressWarnings("unused")
    protected List<? extends E> getDescriptors(final Method method, final Annotation annotation) {
        throw new IllegalStateException(String.format("Unable get descriptors - unknown [%s] method annotation.", annotation));
    }

    private List<E> doGetDescriptors(final Class<?> type) {
        List<E> result = List.of();

        if (!Object.class.equals(type)) {
            final ArrayList<E> descriptors = new ArrayList<>(doGetDescriptors(type.getSuperclass()));

            descriptors.addAll(doGetClassAnnotationDescriptors(type));
            descriptors.addAll(doGetFieldAnnotationDescriptors(type));
            descriptors.addAll(doGetMethodAnnotationDescriptors(type));

            result = descriptors;
        }

        return result;
    }

    private List<E> doGetClassAnnotationDescriptors(final Class<?> type) {
        List<E> result = List.of();

        final List<Class<? extends Annotation>> annotationTypes = getClassAnnotationTypes(type);
        final List<Class<? extends Annotation>> repeatableAnnotationTypes = getClassRepeatableAnnotationTypes(type);

        if (!(annotationTypes.isEmpty() && repeatableAnnotationTypes.isEmpty())) {
            final Annotation[] annotations = type.getDeclaredAnnotations();
            final int length = annotations.length;

            if (length > 0) {
                final ArrayList<E> descriptors = new ArrayList<>(length);

                for (final Annotation annotation : annotations) {
                    final Class<? extends Annotation> annotationType = annotation.annotationType();

                    if (annotationTypes.contains(annotationType)) {
                        descriptors.add(getDescriptor(type, annotation));
                    } else if (repeatableAnnotationTypes.contains(annotationType)) {
                        descriptors.addAll(getDescriptors(type, annotation));
                    }
                }

                result = descriptors;
            }
        }

        return result;
    }

    private List<E> doGetFieldAnnotationDescriptors(final Class<?> type) {
        List<E> result = List.of();

        final List<Class<? extends Annotation>> annotationTypes = getFieldAnnotationTypes(type);
        final List<Class<? extends Annotation>> repeatableAnnotationTypes = getFieldRepeatableAnnotationTypes(type);

        if (!(annotationTypes.isEmpty() && repeatableAnnotationTypes.isEmpty())) {
            final Field[] fields = type.getDeclaredFields();
            final int length = fields.length;

            if (length > 0) {
                final ArrayList<E> descriptors = new ArrayList<>();

                for (final Field field : fields) {
                    final Annotation[] annotations = field.getDeclaredAnnotations();

                    for (final Annotation annotation : annotations) {
                        final Class<? extends Annotation> annotationType = annotation.annotationType();

                        if (annotationTypes.contains(annotationType)) {
                            descriptors.add(getDescriptor(field, annotation));
                        } else if (repeatableAnnotationTypes.contains(annotationType)) {
                            descriptors.addAll(getDescriptors(field, annotation));
                        }
                    }
                }

                result = descriptors;
            }
        }

        return result;
    }

    private List<E> doGetMethodAnnotationDescriptors(final Class<?> type) {
        List<E> result = List.of();

        final List<Class<? extends Annotation>> annotationTypes = getMethodAnnotationTypes(type);
        final List<Class<? extends Annotation>> repeatableAnnotationTypes = getMethodRepeatableAnnotationTypes(type);

        if (!(annotationTypes.isEmpty() && repeatableAnnotationTypes.isEmpty())) {
            final Method[] methods = type.getDeclaredMethods();
            final int length = methods.length;

            if (length > 0) {
                final ArrayList<E> descriptors = new ArrayList<>();

                for (final Method method : methods) {
                    final Annotation[] annotations = method.getDeclaredAnnotations();

                    for (final Annotation annotation : annotations) {
                        final Class<? extends Annotation> annotationType = annotation.annotationType();

                        if (annotationTypes.contains(annotationType)) {
                            descriptors.add(getDescriptor(method, annotation));
                        } else if (repeatableAnnotationTypes.contains(annotationType)) {
                            descriptors.addAll(getDescriptors(method, annotation));
                        }
                    }
                }

                result = descriptors;
            }
        }

        return result;
    }

}
