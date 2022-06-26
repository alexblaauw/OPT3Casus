package com.dl630.casus.scene.controller;

import com.dl630.casus.Main;
import com.dl630.casus.core.Session;
import com.dl630.casus.gebruiker.Gebruiker;
import com.dl630.casus.gebruiker.GebruikerIndex;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ControllerLogin extends SimpleController {
    @FXML
    TextField usernameField;

    @FXML
    PasswordField passwordField;

    @FXML
    Button loginButton;

    @FXML
    Button windowButton;

    public void login() {
        loadSession();
        String username = usernameField.getText();
        String password = passwordField.getText();
        Gebruiker gebruiker = GebruikerIndex.getGebruiker(username);
        for (Session session : Main.getSessions()) {
            if (session.getLoggedInUser() != null) {
                if (session.getLoggedInUser().equals(gebruiker)) return;
            }
        }

        if (gebruiker != null) {
            if (gebruiker.getWachtwoord().equals(password)) {
                getSession().setLoggedInUser(gebruiker);
                getSession().setScene("Overview");
            } else {
                updateLabels();
            }
        } else {
            updateLabels();
        }
    }

    public void updateLabels() {

    }

    public void openWindow() {
        Session session = Main.startSession();
        session.setScene("Login");
        session.getStage().show();
    }
}
