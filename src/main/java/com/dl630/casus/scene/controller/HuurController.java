package com.dl630.casus.scene.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public abstract class HuurController extends SimpleController {
    @FXML
    public Label typeLabel;

    @FXML
    public VBox contentBox;

    @FXML
    public VBox detailBox;

    public void init() {
        super.init();
    }
}
