package com.dl630.casus.scene;

import com.dl630.casus.scene.controller.SimpleController;

public class SidebarLoader extends ControllerLoader {
    @Override
    public SimpleController getController() {
        this.scenePath = "scene/sidebar.fxml";
        return super.getController();
    }
}
