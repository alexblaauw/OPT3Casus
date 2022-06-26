package com.dl630.casus.scene;

import com.dl630.casus.Main;
import com.dl630.casus.scene.controller.SimpleController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.IOException;

public abstract class ControllerLoader extends SimpleController {
    protected String scenePath;

    public SimpleController getController() {
        // Create the FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        // Path to the FXML File
        String fxmlDocPath = Main.RESOURCE_ROOT_COMPLETE + scenePath;

        // Create the Pane and all Details
        Pane scenePane = null;
        SimpleController simpleController = null;
        try {
            FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);
            scenePane = (Pane) loader.load(fxmlStream);
            simpleController = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return simpleController;
    }
}
