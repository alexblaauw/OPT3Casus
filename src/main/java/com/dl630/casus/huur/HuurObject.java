package com.dl630.casus.huur;

import com.dl630.casus.core.HuurProperty;
import com.dl630.casus.gebruiker.Gebruiker;
import com.dl630.casus.scene.controller.ControllerDetail;
import com.dl630.casus.scene.controller.ControllerToevoegen;
import com.dl630.casus.scene.controller.HuurController;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.HashMap;

public abstract class HuurObject {
    protected HuurProperty<String> title;

    protected HuurProperty<Double> weight;
    protected String typeName;
    protected Boolean available;

    protected String huurder;

    protected Gebruiker verhuurder;

    protected Boolean insured;

    private HashMap<String, HuurProperty> displayMap;

    public HuurObject(String title) {
        this(title, 0.0, new HashMap<>());
    }
    public HuurObject(String title, Double weight, HashMap<String, HuurProperty> displayMap) {
        this.displayMap = displayMap;
        this.available = true;
        this.insured = false;

        setTitle(title);
        setWeight(weight);
    }

    public abstract double getHuurPrijs();
    public abstract double getVerzekeringPrijs();

    public abstract HuurObject duplicate();

    public void setDetailPane(ControllerDetail controller) {
        addTitle(controller, title.get());
        addType(controller, typeName);
        addWeight(controller, weight.get());
        fillHuurBox(controller);
        addOther(controller, false);
    }

    public void setToevoegenPane(ControllerToevoegen controller) {
        addType(controller, typeName);
        addWeightField(controller);
        addOther(controller, true);
    }

    public void fillHuurBox(ControllerDetail controller) {
        addAvailability(controller, available);
        if (!available) {
            addHuurderDetails(controller, huurder, verhuurder);
        } else {
            addCostDetails(controller);
            addInsuranceButton(controller);
        }
    }

    public void addTitle(ControllerDetail controller, String title) {
        controller.titleLabel.setText(title);
    }

    public void addType(HuurController controller, String typeName) {
        controller.typeLabel.setText(typeName);
    }

    public void addAvailability(ControllerDetail controller, Boolean available) {
        controller.availabilityLabel.setText(available ? "Ja" : "Nee");
    }

    public void addHuurderDetails(ControllerDetail controller, String huurder, Gebruiker verhuurder) {
        controller.huurderLabel.setText(huurder);
        controller.verhuurderLabel.setText(verhuurder.getGebruikersnaam());
    }

    public void addCostDetails(ControllerDetail controller) {
        controller.huurBox.getChildren().remove(0, controller.huurBox.getChildren().size());
        controller.huurBox.getChildren().add(getBoldDetailLabel("Huurprijs per dag: "
                + (getInsured() ? getHuurPrijsTotaal().toString() : getHuurPrijs())
                + " euro"));
    }

    public void addInsuranceButton(ControllerDetail controller) {
        CheckBox checkBox = new CheckBox();
        if (insured) checkBox.setSelected(true);
        HBox hBox = getInsuranceHBox(checkBox);
        Label label = getBoldDetailLabel("Verzekeren?");
        checkBox.selectedProperty().addListener(change -> {
            controller.huurBox.getChildren().remove(0, controller.huurBox.getChildren().size());
            setInsured(checkBox.isSelected());
            fillHuurBox(controller);
        });
        hBox.getChildren().addAll(checkBox, label);
        controller.huurBox.getChildren().add(hBox);
    }

    public HBox getInsuranceHBox(CheckBox checkBox) {
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(0, 0, 0, 12));
        hBox.setSpacing(4);
        HBox.setMargin(checkBox, new Insets(0, 0, 6, 0));

        return hBox;
    }

    public void addWeight(ControllerDetail controller, Double weight) {
        controller.contentBox.getChildren().add(4, getBoldDetailLabel("Gewicht: "
                + weight.toString() +
                "kg"));
    }

    public void addWeightField(ControllerToevoegen controller) {
        TextField weightField = weight.getTextField();
        weightField.setPromptText("Gewicht");
        controller.contentBox.getChildren().add(2, weightField);
    }

    public void addOther(HuurController controller, boolean edit) {
        if (edit) {
            addOtherFields(controller);
        } else {
            addOtherLabels(controller);
        }
    }

    private void addOtherLabels(HuurController controller) {
        for (String title : displayMap.keySet()) {
            Label label = new Label(title + ": " + displayMap.get(title).get().toString());
            controller.detailBox.getChildren().add(label);
        }
    }

    private void addOtherFields(HuurController controller) {
        for (String title : displayMap.keySet()) {
            TextField textField = displayMap.get(title).getTextField();
            textField.setPromptText(title);
            controller.detailBox.getChildren().add(textField);
        }
    }

    public void readOtherFields() {
        for (String title : displayMap.keySet()) {
            displayMap.get(title).read();
        }
    }

    public void readWeight() {
        this.weight.read();
    }

    public void getImage() {}

    public String getTitle() {
        return title.get();
    }

    public String getTypeName() {
        return typeName;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setUnavailable(String huurder, Gebruiker verhuurder) {
        this.available = false;
        this.huurder = huurder;
        this.verhuurder = verhuurder;
    }

    public void setAvailable() {
        this.available = true;
        huurder = null;
        verhuurder = null;
    }

    public void verhuur(String huurder, Gebruiker verhuurder) {
        this.huurder = huurder;
        this.verhuurder = verhuurder;
        this.available = false;
    }

    public void maakBeschikbaar() {
        this.huurder = null;
        this.verhuurder = null;
        this.available = true;
    }

    public Label getBoldDetailLabel(String text) {
        Label label = new Label(text);
        label.setPadding(new Insets(0, 0, 4, 0));
        label.setFont(Font.font("System", FontWeight.BOLD, 12.0));
        return label;
    }

    public Double getHuurPrijsTotaal() {
        return getHuurPrijs() + getVerzekeringPrijs();
    }

    public HashMap<String, Object> getDisplayMap() {
        return new HashMap<>(displayMap);
    }

    public void addMiscProperty(String label, HuurProperty value) {
        displayMap.put(label, value);
    }

    public Boolean getInsured() {
        return insured;
    }

    public void setInsured(Boolean insured) {
        this.insured = insured;
    }

    public Double getWeight() {
        return weight.get();
    }

    public void setWeight(Double weight) {
        this.weight = new HuurProperty<>(weight, HuurProperty.getDoubleReader());
    }

    public void setTitle(String title) {
        this.title = new HuurProperty<>(title, HuurProperty.getStringReader());
    }
}
