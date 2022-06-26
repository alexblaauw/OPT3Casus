package com.dl630.casus.scene.controller;

import com.dl630.casus.Main;
import com.dl630.casus.core.Session;
import com.dl630.casus.scene.ControllerLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ControllerSidebar extends ControllerLoader {
    @FXML
    Label usernameLabel;

    @FXML
    Button logoutButton;

    public void init() {
        loadSession();
        if (getSession().getLoggedInUser() != null) {
            usernameLabel.setText(getSession().getLoggedInUser().getGebruikersnaam());
        }

        logoutButton.setOnAction(e -> {
            getSession().setLoggedInUser(null);
            getSession().setScene("Login");
        });
    }
}
