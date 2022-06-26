package com.dl630.casus.core;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class Utilities {
    public static void stretchFitToAnchor(Node node) {
        AnchorPane.setTopAnchor(node, 0.0);
        AnchorPane.setBottomAnchor(node, 0.0);
        AnchorPane.setLeftAnchor(node, 0.0);
        AnchorPane.setRightAnchor(node, 0.0);
    }
}
