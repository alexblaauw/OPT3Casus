package com.dl630.casus.scene.controller;

import com.dl630.casus.Main;
import com.dl630.casus.core.EventListener;
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
        getSession().getEventSubscriber().subscribeEvent(() -> {
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
            controllerOverviewItem.button.setOnAction(getButtonAction(huurObject));
            setItemAvailability(controllerOverviewItem, huurObject.getAvailable());
            addAvailabilityListeners(controllerOverviewItem, huurObject);

            gridPane.add(controllerOverviewItem.scenePane, counter % 2, counter / 2);
            counter++;
        }

        getSession().getEventSubscriber().subscribeEvent(() -> {
            getSession().setScene("Overview");
        }, "huur_index_update");
    }

    private void addAvailabilityListeners(ControllerOverviewItem controllerOverviewItem, HuurObject huurObject) {
        getSession().getEventSubscriber().subscribeEvent(getAvailabilityEvent(controllerOverviewItem, huurObject),
                "object_verhuurd");
        getSession().getEventSubscriber().subscribeEvent(getAvailabilityEvent(controllerOverviewItem, huurObject),
                "object_retour");
    }

    private EventListener getAvailabilityEvent(ControllerOverviewItem controllerOverviewItem, HuurObject huurObject) {
        return () -> {
            setItemAvailability(controllerOverviewItem, huurObject.getAvailable());
        };
    }

    public void setItemAvailability(ControllerOverviewItem controllerOverviewItem, boolean available) {
        Float opacity = available ? 1.0F : 0.7F;
        controllerOverviewItem.titleLabel.setOpacity(opacity);
        controllerOverviewItem.beschikbaar.setOpacity(opacity);
        controllerOverviewItem.availabilityLabel.setOpacity(opacity);
        controllerOverviewItem.imageView.setOpacity(opacity);
        controllerOverviewItem.availabilityLabel.setText(available ? "Ja" : "Nee");
    }

    public EventHandler<ActionEvent> getButtonAction(HuurObject object) {
        return event -> {
            SceneDetail.changeScene(object, getSession());
        };
    }
}
