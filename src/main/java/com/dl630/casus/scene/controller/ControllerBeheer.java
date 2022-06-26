package com.dl630.casus.scene.controller;

import com.dl630.casus.Main;
import com.dl630.casus.core.Utilities;
import com.dl630.casus.huur.HuurIndex;
import com.dl630.casus.huur.HuurObject;
import com.dl630.casus.scene.SceneToevoegen;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ControllerBeheer extends SimpleController {
    @FXML
    AnchorPane sidebarPane;

    @FXML
    VBox huurObjectListBox;

    @FXML
    ScrollPane huurScrollPane;

    @FXML
    TabPane tabPane;

    @FXML
    public void init() {
        loadSession();
        initTabPane(tabPane, 2, getSession());
        addSidebar(sidebarPane);
        fillList();

        huurObjectListBox.prefWidthProperty().bind(huurScrollPane.widthProperty());
        huurObjectListBox.setSpacing(12);
        huurObjectListBox.setPadding(new Insets(12.0, 0.0, 0.0, 12.0));

        huurScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }

    public void fillList() {
        for (HuurObject huurObject : HuurIndex.getHuurObjectTypes()) {
            AnchorPane itemPane = new AnchorPane();
            itemPane.prefWidthProperty().bind(huurObjectListBox.widthProperty().subtract(3.0));
            itemPane.setMinHeight(48);
            itemPane.setStyle("-fx-background-color: white; -fx-background-radius: 12");
            addItemLabel(itemPane, huurObject);
            addItemButton(itemPane, huurObject);

            huurObjectListBox.getChildren().add(itemPane);
        }
    }

    public void addItemLabel(AnchorPane itemPane, HuurObject huurObject) {
        Label label = new Label(huurObject.getTypeName());
        label.setFont(Font.font("System", FontWeight.BOLD, 12));
        AnchorPane.setTopAnchor(label, 14.0);
        AnchorPane.setLeftAnchor(label, 12.0);

        itemPane.getChildren().add(label);
    }

    public void addItemButton(AnchorPane itemPane, HuurObject huurObject) {
        Button button = new Button();
        button.setOpacity(0.0);
        Utilities.stretchFitToAnchor(button);
        button.setOnAction(e -> {
            SceneToevoegen.changeScene(huurObject.duplicate(), getSession());
        });

        itemPane.getChildren().add(button);
    }
}
