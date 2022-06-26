package com.dl630.casus.scene.controller;

import com.dl630.casus.core.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import java.util.NoSuchElementException;

public abstract class SimpleController {
    @FXML
    public AnchorPane scenePane;

    private Session session;

    public static void addTabListener(TabPane tabPane, Session session) {
        if (tabPane == null) {
            throw new NullPointerException("Failed to add listener, tabPane is null.");
        }
        if (tabPane.getTabs().size() < 2) {
            throw new NoSuchElementException("Failed to add listener, tabPane was missing tabs.");
        }
        tabPane.getSelectionModel().selectedItemProperty().addListener(change -> {
            switch(tabPane.getSelectionModel().getSelectedIndex()) {
                case 1 -> session.setScene("Overview");
                case 2 -> session.setScene("Beheer");
            }
        });
    }

    public static void initTabPane(TabPane tabPane, Integer currentTab, Session session) {
        tabPane.getTabs().remove(0, tabPane.getTabs().size());
        tabPane.getTabs().add(0, new Tab("Menu"));
        tabPane.getTabs().add(1, new Tab("Overzicht"));
        tabPane.getTabs().add(2, new Tab("Beheer"));

        tabPane.getSelectionModel().select(currentTab);
        addTabListener(tabPane, session);
    }

    public static void addSidebar(AnchorPane sidebarParent) {
//        ControllerSidebar controller = new ControllerSidebar();
//        controller.getController();
//        AnchorPane sidebar = controller.scenePane;
//        sidebarParent.getChildren().add(sidebar);
//        controller.init();
//
//        AnchorPane.setTopAnchor(sidebar, 0.0);
//        AnchorPane.setBottomAnchor(sidebar, 0.0);
//        AnchorPane.setLeftAnchor(sidebar, 0.0);
//        AnchorPane.setRightAnchor(sidebar, 0.0);
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public void loadSession() {
        setSession(Session.getSessionFromScene(scenePane.getScene()));
    }

    public void init() {}
}
