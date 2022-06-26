package com.dl630.casus.scene;

import com.dl630.casus.scene.controller.ControllerOverview;
import com.dl630.casus.scene.controller.SimpleController;
import com.dl630.casus.scene.controller.SimpleScene;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class SceneOverview extends SimpleScene {
    @Override
    public SimpleController getSceneController() {
        this.scenePath = "scene/overview.fxml";
        return getController();
    }
}
