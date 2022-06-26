package com.dl630.casus.scene;

import com.dl630.casus.AbstractFactory;
import com.dl630.casus.scene.controller.SimpleController;
import javafx.scene.Scene;

public class SceneFactory implements AbstractFactory<SimpleController> {
    @Override
    public SimpleController create(String type) {
        switch(type) {
            case "Login" -> {
                return new SceneLogin().getSceneController();
            }
            case "Overview" -> {
                return new SceneOverview().getSceneController();
            }
            case "Beheer" -> {
                return new SceneBeheer().getSceneController();
            }
            default -> throw new IllegalStateException("Unexpected value: " + type);
        }
    }
}
