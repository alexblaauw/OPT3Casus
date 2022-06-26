package com.dl630.casus.scene.controller;

import com.dl630.casus.core.HuurProperty;
import javafx.scene.control.TextField;

public interface ValueReader {
    void readValue(HuurProperty huurProperty, TextField textField);
}
