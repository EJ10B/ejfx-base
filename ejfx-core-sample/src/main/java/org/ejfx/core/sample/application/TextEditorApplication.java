package org.ejfx.core.sample.application;

import javafx.stage.Stage;
import org.ejfx.core.annotation.*;
import org.ejfx.core.application.ApplicationBase;
import org.ejfx.core.scene.SceneManager;
import org.ejfx.core.scene.config.annotation.SceneManagerDescriptorLoader;
import org.ejfx.core.stage.DialogType;
import org.ejfx.core.stage.FileDialogType;
import org.ejfx.core.stage.Modality;
import org.ejfx.core.util.lang.Boolean;

@FxDefaultDialog()
@FxDialog(name = "aboutApplication", title = "About ...", header = "About Text Editor", content = "This is a simple Text Editor realized with ejFX.", expanded = Boolean.TRUE)
@FxDialog(name = "exitApplicationConfirm", type = DialogType.CONFIRMATION, title = "Confirm ...", header = "Exit Text Editor", content = "Are you sure you want to exit the Text Editor?")
@FxDialog(name = "findText", type = DialogType.TEXT, title = "Find ...", header = "Search for text", content = "Please enter a text to search for ...")
@FxDialog(name = "textNotFound", title = "Text not found ...", header = "Text has not been found!")
@FxDialog(name = "saveFileSuccessful", title = "Save ...", header = "Save successful!")

@FxDefaultFileDialog(type = FileDialogType.OPEN_FILE)
@FxFileDialog(name = "openTextFile", title = "Open text file", filters = @FxExtensionFilter(description = "Text file (*.txt)", extensions = "*.txt"))
@FxFileDialog(name = "saveTextFile", type = FileDialogType.SAVE_FILE, title = "Save text file", filters = @FxExtensionFilter(description = "Text file (*.txt)", extensions = "*.txt"))

@FxDefaultStage(resizable = Boolean.TRUE)
@FxScene(name = "main", location = "/text-editor.fxml", stage = @FxStage(modality = Modality.APPLICATION, title = "Text editor"))
public class TextEditorApplication extends ApplicationBase<SceneManager<TextEditorApplication>> {

    @Override
    public void start(final Stage stage) throws Exception {
        setSceneManager(SceneManager.of(this, stage, SceneManagerDescriptorLoader.of(getClass()).load()));
        getSceneManager().showMainScene();
    }

    public static void main(final String[] args) {
        launch();
    }

}