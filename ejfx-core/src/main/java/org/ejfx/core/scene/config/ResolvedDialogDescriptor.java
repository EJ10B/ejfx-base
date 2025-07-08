package org.ejfx.core.scene.config;

import javafx.stage.Modality;
import org.ejfx.core.stage.DialogType;
import org.ejfx.core.util.Arguments;
import org.ejfx.core.util.resources.ResourcesResolver;

import java.util.Locale;
import java.util.ResourceBundle;

public final class ResolvedDialogDescriptor extends ResolvedDescriptorBase {

    private final Modality modality;

    private final String title;

    private final String header;

    private final String content;

    private final DialogType type;

    private final boolean expanded;

    private ResolvedDialogDescriptor(final DefinedDialogDescriptor descriptor,
                                     final ResourcesResolver resolver,
                                     final Locale locale) {

        super(Arguments.requireNonNull(descriptor, "descriptor").getName(),
                doGetResources(descriptor, Arguments.requireNonNull(resolver, "resolver"), locale));

        this.modality = descriptor.getModality();
        this.title = getString(descriptor.getTitle());
        this.header = getString(descriptor.getHeader());
        this.content = getString(descriptor.getContent());
        this.type = descriptor.getType();
        this.expanded = descriptor.isExpanded();
    }

    public Modality getModality() {
        return modality;
    }

    public String getTitle() {
        return title;
    }

    public String getHeader() {
        return header;
    }

    public String getContent() {
        return content;
    }

    public DialogType getType() {
        return type;
    }

    public boolean isExpanded() {
        return expanded;
    }

    private static ResourceBundle doGetResources(final DefinedDialogDescriptor descriptor,
                                                 final ResourcesResolver resolver,
                                                 final Locale locale) {
        return resolver.getResourceBundle(descriptor.getResources(), locale);
    }

    public static ResolvedDialogDescriptor of(final DefinedDialogDescriptor descriptor,
                                              final ResourcesResolver resolver,
                                              final Locale locale) {
        return new ResolvedDialogDescriptor(descriptor, resolver, locale);
    }

}
