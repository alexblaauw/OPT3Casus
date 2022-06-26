package com.dl630.casus.scene.controller;

import com.dl630.casus.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ControllerToevoegen extends HuurController {
    @FXML
    public TextField titleField;

    @FXML
    TabPane tabPane;

    @FXML
    Button terugButton;

    @FXML
    public Button addButton;

    @Override
    public void init() {
        loadSession();
        initTabPane(tabPane, 2, getSession());
        terugButton.setOnAction(event -> {
            System.out.println(getSession());
            getSession().setScene("Beheer");
        });
    }
}
