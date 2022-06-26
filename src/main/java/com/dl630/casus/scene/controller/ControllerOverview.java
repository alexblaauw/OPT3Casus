package com.dl630.casus.scene.controller;

import com.dl630.casus.Main;
import com.dl630.casus.core.EventSubscriber;
import com.dl630.casus.huur.HuurIndex;
import com.dl630.casus.huur.HuurObject;
import com.dl630.casus.scene.ControllerLoader;
import com.dl630.casus.scene.SceneDetail;
import com.dl630.casus.scene.SidebarLoader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class ControllerOverview extends ControllerLoader {
    @FXML
    public HBox gridParentBox;

    @FXML
    public TabPane tabPane;

    @FXML
    public AnchorPane sidebarPane;

    public void init() {
        loadSession();
        initTabPane(tabPane, 1, getSession());

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(12));
        gridPane.setHgap(12);
        gridPane.setVgap(12);
        gridParentBox.getChildren().add(gridPane);
        fillGridPane(gridPane);

        addSidebar(sidebarPane);
        EventSubscriber.subscribeEvent(() -> {
            fillGridPane(gridPane);
        }, "huur_index_update");
    }

    public void fillGridPane(GridPane gridPane) {
        scenePath = "scene/overview_item.fxml";
        gridPane.getChildren().remove(0, gridPane.getChildren().size());

        int counter = 0;
        for (HuurObject huurObject : HuurIndex.getHuurObjecten()) {
            ControllerOverviewItem controllerOverviewItem = (ControllerOverviewItem) getController();
            controllerOverviewItem.titleLabel.setText(huurObject.getTitle());
            controllerOverviewItem.availabilityLabel.setText(huurObject.getAvailable() ? "Ja" : "Nee");
            controllerOverviewItem.button.setOnAction(getButtonAction(huurObject));
            if (!huurObject.getAvailable()) {
                controllerOverviewItem.titleLabel.setOpacity(0.7);
                controllerOverviewItem.beschikbaar.setOpacity(0.7);
                controllerOverviewItem.availabilityLabel.setOpacity(0.7);
                controllerOverviewItem.imageView.setOpacity(0.7);
            }

            gridPane.add(controllerOverviewItem.scenePane, counter % 2, counter / 2);

            counter++;
        }
    }

    public EventHandler<ActionEvent> getButtonAction(HuurObject object) {
        return event -> {
            SceneDetail.changeScene(object, getSession());
        };
    }
}
