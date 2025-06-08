package org.ejfx.core.scene;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.ejfx.core.controller.ControllerBase;
import org.ejfx.core.scene.config.*;
import org.ejfx.core.scene.controller.ControllerManager;
import org.ejfx.core.stage.DialogType;
import org.ejfx.core.stage.FileDialogType;
import org.ejfx.core.util.Arguments;
import org.ejfx.core.util.resources.ModuleResourcesResolver;
import org.ejfx.core.util.resources.ResourcesResolver;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public abstract class SceneManagerBase<A> {

    public static final String MAIN_SCENE = "main";

    // public static final String VIEW_IMAGE_SCENE = "viewImage";

    // public static final String VIEW_VIDEO_SCENE = "viewVideo";

    // public static final String VIEW_WEB_PAGE_SCENE = "viewWebPage";

    private final A application;

    private final Stage primaryStage;

    private final ResourcesResolver resolver;

    private Scene currentScene;

    protected SceneManagerBase(final A application, final Stage primaryStage) {
        this.application = Arguments.requireNonNull(application, "application");
        this.primaryStage = Arguments.requireNonNull(primaryStage, "primaryStage");
        this.resolver = ModuleResourcesResolver.of(application);
        this.currentScene = null;
    }

    public boolean canShowDialog(final String name) {
        return getDialogDescriptor(name) != null;
    }

    public void closeScene() {
        doCloseScene(true);
    }

    public <T> Optional<T> showDialog(final String name) {
        final Optional<T> result;
        final DefinedDialogDescriptor descriptor = doGetDialogDescriptor(name);

        try {
            result = doShowDialog(descriptor);
        } catch (final Exception e) {
            throw new IllegalStateException(String.format("Unable show dialog for [%s] name.", name), e);
        }

        return result;
    }

    public <T, E> Optional<T> showDialog(final String name, final E value) {
        final Optional<T> result;
        final DefinedDialogDescriptor descriptor = doGetDialogDescriptor(name);

        try {
            result = doShowDialog(descriptor, value);
        } catch (final Exception e) {
            throw new IllegalStateException(String.format("Unable show dialog for [%s] name with [%s] value.", name, value), e);
        }

        return result;
    }

    public <T> Optional<T> showDialog(final String name, final Collection<T> values) {
        final Optional<T> result;
        final DefinedDialogDescriptor descriptor = doGetDialogDescriptor(name);

        try {
            result = doShowDialog(descriptor, values);
        } catch (final Exception e) {
            throw new IllegalStateException(String.format("Unable show dialog for [%s] name with [%s] values.", name, values), e);
        }

        return result;
    }

    public <T> Optional<T> showDialog(final String name, final Collection<T> values, final T value) {
        final Optional<T> result;
        final DefinedDialogDescriptor descriptor = doGetDialogDescriptor(name);

        try {
            result = doShowDialog(descriptor, values, value);
        } catch (final Exception e) {
            throw new IllegalStateException(String.format("Unable show dialog for [%s] name with [%s] values and [%s] value.", name, values, value), e);
        }

        return result;
    }

    public List<File> showFileDialog(final String name) {
        final List<File> result;
        final DefinedFileDialogDescriptor descriptor = doGetFileDialogDescriptor(name);

        try {
            result = doShowFileDialog(descriptor);
        } catch (final Exception e) {
            throw new IllegalStateException(String.format("Unable show file dialog for [%s] name.", name), e);
        }

        return result;
    }

    public List<File> showFileDialog(final String name, final File directory) {
        final List<File> result;
        final DefinedFileDialogDescriptor descriptor = doGetFileDialogDescriptor(name);

        try {
            result = doShowFileDialog(descriptor, directory);
        } catch (final Exception e) {
            throw new IllegalStateException(String.format("Unable show file dialog for [%s] name with [%s] directory.", name, directory), e);
        }

        return result;
    }

    public List<File> showFileDialog(final String name, final File directory, final String fileName) {
        final List<File> result;
        final DefinedFileDialogDescriptor descriptor = doGetFileDialogDescriptor(name);

        try {
            result = doShowFileDialog(descriptor, directory, fileName);
        } catch (final Exception e) {
            throw new IllegalStateException(String.format("Unable show file dialog for [%s] name with [%s] directory and [%s] file name.", name, directory, fileName), e);
        }

        return result;
    }

    public List<File> showFileDialog(final String name, final String fileName) {
        final List<File> result;
        final DefinedFileDialogDescriptor descriptor = doGetFileDialogDescriptor(name);

        try {
            result = doShowFileDialog(descriptor, fileName);
        } catch (final Exception e) {
            throw new IllegalStateException(String.format("Unable show file dialog for [%s] name with [%s] file name.", name, fileName), e);
        }

        return result;
    }

    public void showMainScene() {
        showScene(MAIN_SCENE);
    }

    public void showScene(final String name) {
        try (final ResolvedSceneDescriptor descriptor = doGetSceneDescriptor(name)) {
            doShowScene(descriptor);
        } catch (final Exception e) {
            throw new IllegalStateException(String.format("Unable show scene for [%s] name.", name), e);
        }
    }

    public <E> void showScene(final String name, final E value) {
        try (final ResolvedSceneDescriptor descriptor = doGetSceneDescriptor(name)) {
            doShowScene(descriptor, value);
        } catch (final Exception e) {
            throw new IllegalStateException(String.format("Unable show scene for [%s] name with [%s] value.", name, value), e);
        }
    }

    protected abstract <T> ControllerManager<T> getControllerManager(Class<?> type);

    protected abstract DefinedDialogDescriptor getDialogDescriptor(String name);

    protected abstract DefinedFileDialogDescriptor getFileDialogDescriptor(String name);

    protected abstract DefinedSceneDescriptor getSceneDescriptor(String name);

    protected Node getDialogExpandableContent(final Object... values) {
        Node result = null;

        if (values != null && values.length > 0) {
            final Object value = values[0];

            if (value instanceof final Throwable throwable) {
                result = doGetDialogExpandableContent(throwable);
            } else {
                result = doGetDialogExpandableContent(value);
            }
        }

        return result;
    }

    private DefinedDialogDescriptor doGetDialogDescriptor(final String name) {
        final DefinedDialogDescriptor descriptor = getDialogDescriptor(name);

        return Arguments.requireNonNull(descriptor,
                String.format("Unable get dialog descriptor for [%s] name.", name));
    }

    private DefinedFileDialogDescriptor doGetFileDialogDescriptor(final String name) {
        final DefinedFileDialogDescriptor descriptor = getFileDialogDescriptor(name);

        return Arguments.requireNonNull(descriptor, String.format("Unable get file dialog descriptor for [%s] name.", name));
    }

    private ResolvedSceneDescriptor doGetSceneDescriptor(final String name) {
        final DefinedSceneDescriptor descriptor = Arguments.requireNonNull(getSceneDescriptor(name),
                String.format("Unable get scene descriptor for [%s] name.", name));

        return ResolvedSceneDescriptor.of(descriptor,
                resolver,
                Locale.ROOT);
    }

    private void doHandleOnCloseRequest(final WindowEvent event) {
        if (currentScene != null) {
            final Object controller = currentScene.getController();

            if (controller instanceof ControllerBase<?>) {
                ((ControllerBase<?>) controller).handleOnCloseRequest(event);
            }
        }

        if (!event.isConsumed()) {
            doCloseScene(false);
        }
    }

    private void doCloseScene(final boolean closeStage) {
        if (currentScene != null) {
            final Object controller = currentScene.getController();

            if (controller instanceof ControllerBase<?>) {
                ((ControllerBase<?>) controller).handleCloseScene();
            }

            final Scene parentScene = currentScene.getParent();

            if (parentScene != null) {
                if (parentScene.getWindow() != null && closeStage) {
                    doGetCurrentStage().close();
                }

                currentScene = parentScene;
            } else {
                throw new IllegalStateException("Unable close current scene -> parent scene is null.");
            }
        } else {
            throw new IllegalStateException("Unable close current scene -> current scene is null.");
        }
    }

    @SuppressWarnings("unchecked")
    private <T> Optional<T> doShowDialog(final DefinedDialogDescriptor descriptor, final Object... values) {
        final Optional<T> result;
        final DialogType type = descriptor.getType();

        if (type == DialogType.CHOICE) {
            T defaultChoice = null;
            Collection<T> choices = null;

            for (final Object value : values) {
                if (value instanceof Collection<?>) {
                    choices = (Collection<T>) value;
                } else {
                    defaultChoice = (T) value;
                }
            }

            final ChoiceDialog<T> dialog = new ChoiceDialog<>(defaultChoice, choices);
            dialog.initOwner(doGetCurrentStage());
            dialog.initModality(descriptor.getModality());
            dialog.setTitle(descriptor.getTitle());
            dialog.setHeaderText(descriptor.getHeader());
            dialog.setContentText(descriptor.getContent());

            result = dialog.showAndWait();
        } else if (type == DialogType.TEXT) {
            final TextInputDialog dialog = (values != null && values.length > 0)
                    ? new TextInputDialog((String) values[0]) : new TextInputDialog();
            dialog.initOwner(doGetCurrentStage());
            dialog.initModality(descriptor.getModality());
            dialog.setTitle(descriptor.getTitle());
            dialog.setHeaderText(descriptor.getHeader());
            dialog.setContentText(descriptor.getContent());

            result = (Optional<T>) dialog.showAndWait();
        } else {
            final Alert dialog = new Alert(type.getAlertType());
            dialog.initOwner(doGetCurrentStage());
            dialog.initModality(descriptor.getModality());
            dialog.setTitle(descriptor.getTitle());
            dialog.setHeaderText(descriptor.getHeader());
            dialog.setContentText(descriptor.getContent());
            dialog.getDialogPane().setExpanded(descriptor.isExpanded());
            dialog.getDialogPane().setExpandableContent(getDialogExpandableContent(values));

            result = (Optional<T>) dialog.showAndWait();
        }

        return result;
    }

    private List<File> doShowFileDialog(final DefinedFileDialogDescriptor descriptor, final Object... values) {
        final List<File> result;
        final FileDialogType type = descriptor.getType();

        if (type != FileDialogType.OPEN_DIRECTORY) {
            final FileChooser chooser = new FileChooser();
            chooser.setTitle(descriptor.getTitle());
            chooser.getExtensionFilters().addAll(descriptor.getFilters());

            for (final Object value : values) {
                if (value instanceof final File file) {
                    chooser.setInitialDirectory(file);
                } else if (value instanceof final String s) {
                    chooser.setInitialFileName(s);
                }
            }

            if (type == FileDialogType.OPEN_FILE) {
                result = Arguments.copyOf(chooser.showOpenDialog(doGetCurrentStage()));
            } else if (type == FileDialogType.OPEN_FILES) {
                result = Arguments.copyOf(chooser.showOpenMultipleDialog(doGetCurrentStage()));
            } else if (type == FileDialogType.SAVE_FILE) {
                result = Arguments.copyOf(chooser.showSaveDialog(doGetCurrentStage()));
            } else {
                throw new IllegalArgumentException(String.format("Unknown [%s] file dialog type.", type));
            }
        } else {
            final DirectoryChooser chooser = new DirectoryChooser();
            chooser.setTitle(descriptor.getTitle());

            for (final Object value : values) {
                if (value instanceof final File file) {
                    chooser.setInitialDirectory(file);
                }
            }

            result = Arguments.copyOf(chooser.showDialog(doGetCurrentStage()));
        }

        return result;
    }

    private void doShowScene(final ResolvedSceneDescriptor descriptor, final Object... values) throws Exception {
        final FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory((type) -> doCreateController(type, descriptor, values));
        loader.setResources(descriptor.getResources());
        final Parent parent = loader.load(descriptor.getFXMLAsStream());
        final Object controller = loader.getController();
        doProcessController(controller);

        final Scene scene = Scene.of(parent, currentScene, controller);
        final Stage stage = doGetStage(descriptor.getStageDescriptor());
        stage.setScene(scene);
        stage.show();

        currentScene = scene;
    }

    private Stage doGetStage(final ResolvedStageDescriptor descriptor) {
        final Stage result;
        final Stage currentStage = doGetCurrentStage();

        if (currentStage.getScene() != null) {
            if (descriptor != null) { // stage -> create new
                final Stage stage = new Stage();
                stage.setOnCloseRequest(this::doHandleOnCloseRequest);
                stage.initOwner(currentStage);
                stage.initModality(descriptor.getModality());
                stage.setTitle(descriptor.getTitle());
                stage.setResizable(descriptor.isResizable());
                currentStage.setMaximized(descriptor.isMaximized());
                currentStage.setIconified(descriptor.isIconified());

                result = stage;
            } else { // stage -> use current
                currentStage.setWidth(currentStage.getWidth());
                currentStage.setHeight(currentStage.getHeight());

                result = currentStage;
            }
        } else { // stage -> use current
            if (descriptor != null) { // stage -> init from stage descriptor
                currentStage.setTitle(descriptor.getTitle());
                currentStage.setResizable(descriptor.isResizable());
                currentStage.setMaximized(descriptor.isMaximized());
                currentStage.setIconified(descriptor.isIconified());
            }

            result = currentStage;
        }

        return result;
    }

    private <T> T doCreateController(final Class<?> type, final ResolvedSceneDescriptor descriptor, final Object... values) {
        final Class<?> controller = descriptor.getController();
        final ControllerManager<T> manager =
                (Object.class.equals(controller)) ? getControllerManager(type) : getControllerManager(controller);

        return values.length > 0 ? manager.create(application, values[0]) : manager.create(application);
    }

    private Stage doGetCurrentStage() {
        return (currentScene != null) ? (Stage) currentScene.getWindow() : primaryStage;
    }

    private <T> void doProcessController(final T controller) {
        final ControllerManager<T> manager = getControllerManager(controller.getClass());

        manager.process(controller);
    }

    private static Node doGetDialogExpandableContent(final Object value) {
        return doGetDialogExpandableContentAsTextArea(String.valueOf(value));
    }

    private static Node doGetDialogExpandableContent(final Throwable value) {
        final StringWriter stringWriter = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(stringWriter);
        value.printStackTrace(printWriter);

        return doGetDialogExpandableContentAsTextArea(stringWriter.toString());
    }

    private static Node doGetDialogExpandableContentAsTextArea(final String value) {
        final TextArea textArea = new TextArea(value);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        final GridPane gridPane = new GridPane();
        gridPane.setMaxWidth(Double.MAX_VALUE);
        gridPane.add(textArea, 0, 0);

        return gridPane;
    }

}
