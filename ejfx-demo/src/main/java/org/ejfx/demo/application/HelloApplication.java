package org.ejfx.demo.application;

import javafx.stage.Stage;
import org.ejfx.core.annotation.*;
import org.ejfx.core.application.ApplicationBase;
import org.ejfx.core.scene.SceneManager;
import org.ejfx.core.scene.config.annotation.SceneManagerDescriptorLoader;
import org.ejfx.core.stage.FileDialogType;
import org.ejfx.core.stage.Modality;
import org.ejfx.core.util.lang.Boolean;

@FxDefaultDialog()
@FxDialog(name = "about", title = "About demo ...", header = "", content = "Demo software (ver. 0.1) ...", expanded = Boolean.TRUE)

@FxDefaultFileDialog(type = FileDialogType.OPEN_FILES)
@FxFileDialog(name = "openImage", title = "Open image", filters = @FxExtensionFilter(description = "Image (*.jpg)", extensions = "*.jpg"))

@FxDefaultStage(resizable = Boolean.TRUE)
@FxScene(name = "main", resource = "/hello-view.fxml", stage = @FxStage(modality = Modality.APPLICATION, title = "Hello"))
public class HelloApplication extends ApplicationBase<SceneManager<HelloApplication>> {

    @Override
    public void start(final Stage stage) throws Exception {
        setSceneManager(SceneManager.of(this, stage, SceneManagerDescriptorLoader.of(getClass()).load()));
        getSceneManager().showMainScene();
    }

    public static void main(final String[] args) {
        launch();
    }

}