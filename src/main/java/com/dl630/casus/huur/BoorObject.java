package com.dl630.casus.huur;

import com.dl630.casus.core.HuurProperty;
import com.dl630.casus.scene.controller.ControllerDetail;

public class BoorObject extends HuurObject {
    HuurProperty<String> subTypeName;
    HuurProperty<String> brand;

    public BoorObject(String title, String subTypeName, String brand) {
        super(title);
        this.typeName = "Boormachine";
        this.subTypeName = new HuurProperty<>(subTypeName, HuurProperty.getStringReader());
        this.brand = new HuurProperty<>(brand, HuurProperty.getStringReader());

        addMiscProperty("Model", this.subTypeName);
        addMiscProperty("Merk", this.brand);
    }

    @Override
    public HuurObject duplicate() {
        return new BoorObject(this.title.get(), this.subTypeName.get(), this.brand.get());
    }

    @Override
    public void setDetailPane(ControllerDetail controller) {
        addTitle(controller, title.get());
        addType(controller, typeName);
        fillHuurBox(controller);

        addOther(controller, false);
    }

    @Override
    public double getHuurPrijs() {
        return 5;
    }

    @Override
    public double getVerzekeringPrijs() {
        return 1;
    }
}
