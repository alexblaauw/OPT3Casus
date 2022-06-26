package com.dl630.casus.huur;

import com.dl630.casus.core.HuurProperty;

public class PersoonAutoObject extends HuurObject {
    private HuurProperty<String> brand;

    public PersoonAutoObject(String title, Double weight, String brand) {
        super(title);
        this.typeName = "Personenauto";
        this.brand = new HuurProperty<>(brand, HuurProperty.getStringReader());
        this.weight = new HuurProperty<>(weight, HuurProperty.getDoubleReader());

        addMiscProperty("Merk", this.brand);
    }

    @Override
    public HuurObject duplicate() {
        return new PersoonAutoObject(this.getTitle(), this.getWeight(), this.brand.get());
    }

    @Override
    public double getHuurPrijs() {
        return 50;
    }

    @Override
    public double getVerzekeringPrijs() {
        return weight.get() * 0.01;
    }
}
