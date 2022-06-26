package com.dl630.casus.scene.controller;

import com.dl630.casus.Main;
import com.dl630.casus.scene.SceneFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ControllerDetail extends HuurController {
    @FXML
    public Button terugButton;
    @FXML
    public Label titleLabel;

    @FXML
    public Label availabilityLabel;

    @FXML
    public Label huurderLabel;

    @FXML
    public Label verhuurderLabel;

    @FXML
    public VBox contentBox;

    @FXML
    public HBox huurBox;

    @FXML
    TabPane tabPane;

    @FXML
    public Button rentButton;

    @FXML
    public TextField firstNameField;

    @FXML
    public TextField lastNameField;


    public void init() {
        loadSession();
        initTabPane(tabPane, 1, getSession());
        terugButton.setOnAction(event -> {
            getSession().setScene("Overview");
        });
    }

}
