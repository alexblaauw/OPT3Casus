package com.dl630.casus.scene;

import com.dl630.casus.Main;
import com.dl630.casus.core.EventSubscriber;
import com.dl630.casus.core.Session;
import com.dl630.casus.huur.HuurObject;
import com.dl630.casus.scene.controller.ControllerDetail;
import com.dl630.casus.scene.controller.SimpleController;
import com.dl630.casus.scene.controller.SimpleScene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class SceneDetail extends SimpleScene {

    public HuurObject selectedObject = null;

    @Override
    public SimpleController getSceneController() {
        this.scenePath = "scene/detail.fxml";
        ControllerDetail controller = (ControllerDetail) getController();
        AnchorPane scenePane = controller.scenePane;
        if (selectedObject != null) {
            selectedObject.setDetailPane(controller);
            controller.rentButton.setOnAction(getRentAction(selectedObject, controller));
            if (!selectedObject.getAvailable()) controller.rentButton.setText("Retour");
        }
        return controller;
    }

    private EventHandler<ActionEvent> getRentAction(HuurObject object, ControllerDetail controller) {
        return object.getAvailable() ? e -> {
            String naam = controller.firstNameField.getText() + " " + controller.lastNameField.getText();
            selectedObject.setUnavailable(naam, controller.getSession().getLoggedInUser());
            EventSubscriber.broadcastEvent("object_verhuurd");
            changeScene(selectedObject, controller.getSession());
        } : e -> {
            selectedObject.setAvailable();
            EventSubscriber.broadcastEvent("object_retour");
            changeScene(selectedObject, controller.getSession());
        };
    }

    public static void changeScene(HuurObject object, Session session) {
        SceneDetail sceneDetail = new SceneDetail();
        sceneDetail.selectedObject = object;
        session.setSceneByController(sceneDetail.getSceneController());
    }
}
