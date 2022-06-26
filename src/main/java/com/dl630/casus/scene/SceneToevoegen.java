package com.dl630.casus.scene;

import com.dl630.casus.core.Session;
import com.dl630.casus.huur.HuurIndex;
import com.dl630.casus.huur.HuurObject;
import com.dl630.casus.scene.controller.ControllerToevoegen;
import com.dl630.casus.scene.controller.SimpleController;
import com.dl630.casus.scene.controller.SimpleScene;

public class SceneToevoegen extends SimpleScene {

    public HuurObject selectedObject = null;

    @Override
    public SimpleController getSceneController() {
        this.scenePath = "scene/toevoegen.fxml";
        ControllerToevoegen controller = (ControllerToevoegen) getController();
        if (selectedObject != null) {
            selectedObject.setToevoegenPane(controller);
            controller.addButton.setOnAction(event -> {
                selectedObject.setTitle(controller.titleField.getText());
                selectedObject.readWeight();
                selectedObject.readOtherFields();
                HuurIndex.addHuurObject(selectedObject);
                controller.getSession().setScene("Overview");
            });
        }

        return controller;
    }

    public static void changeScene(HuurObject object, Session session) {
        SceneToevoegen sceneToevoegen = new SceneToevoegen();
        sceneToevoegen.selectedObject = object;
        session.setSceneByController(sceneToevoegen.getSceneController());
    }
}
