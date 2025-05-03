package org.ejfx.core.sample.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import org.ejfx.core.annotation.FxCreator;
import org.ejfx.core.controller.ControllerBase;
import org.ejfx.core.sample.application.TextEditorApplication;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class TextEditorController extends ControllerBase<TextEditorApplication> {

    @FXML
    private TextArea editorTextArea;

    protected TextEditorController(TextEditorApplication application) {
        super(application);
    }

    @FXML
    public void handleOpenFileMenuItem(ActionEvent actionEvent) {
        final List<File> files = getSceneManager().showFileDialog("openTextFile");

        if (!files.isEmpty()) {
            editorTextArea.clear();

            try (final Scanner scanner = new Scanner(files.getFirst())) {
                scanner.useDelimiter(System.lineSeparator());

                while (scanner.hasNext()) {
                    final String line = scanner.next();
                    editorTextArea.appendText(line + System.lineSeparator());
                }
            } catch (final Exception e) {
                getSceneManager().showDialog("openFileError", e);
            }
        }
    }

    @FXML
    public void handleSaveFileMenuItem(ActionEvent actionEvent) {
        final List<File> files = getSceneManager().showFileDialog("saveTextFile");

        if (!files.isEmpty()) {
            try {
                try (final BufferedWriter writer = new BufferedWriter(new FileWriter(files.getFirst(), StandardCharsets.UTF_8))) {
                    writer.write(editorTextArea.getText());
                }

                getSceneManager().showDialog("saveFileSuccessful");
            } catch (final Exception e) {
                getSceneManager().showDialog("saveFileError", e);
            }
        }
    }

    @FXML
    public void handleCloseMenuItem(ActionEvent actionEvent) {
        final Optional<ButtonType> button = getSceneManager().showDialog("exitApplicationConfirm");

        if (button.isPresent() && button.get() == ButtonType.OK) {
            Platform.exit();
        }
    }

    @FXML
    public void handleFindMenuItem(ActionEvent actionEvent) {
        final Optional<String> findText = getSceneManager().showDialog("findText");

        if (findText.isPresent()) {
            final String searchText = findText.get();
            final int beginIndex = editorTextArea.getText().indexOf(searchText);

            if (beginIndex > -1) {
                final int endIndex = beginIndex + searchText.length();
                editorTextArea.selectRange(beginIndex, endIndex);
            } else {
                getSceneManager().showDialog("textNotFound");
            }
        }
    }

    @FXML
    public void handleAboutMenuItem(ActionEvent actionEvent) {
        getSceneManager().showDialog("aboutApplication");
    }

    @FxCreator
    public static TextEditorController create(final Object... args) {
        return new TextEditorController((TextEditorApplication) args[0]);
    }

}