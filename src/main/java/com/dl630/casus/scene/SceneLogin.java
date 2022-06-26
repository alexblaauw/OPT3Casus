package com.dl630.casus.scene;

import com.dl630.casus.scene.controller.SimpleController;
import com.dl630.casus.scene.controller.SimpleScene;
import javafx.scene.layout.Pane;

public class SceneLogin extends SimpleScene {
    @Override
    public SimpleController getSceneController() {
        this.scenePath = "scene/login_scene.fxml";
        return getController();
    }
}
