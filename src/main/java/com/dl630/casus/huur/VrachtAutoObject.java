package com.dl630.casus.huur;

import com.dl630.casus.core.HuurProperty;

public class VrachtAutoObject extends HuurObject {
    private final HuurProperty<Double> loadWeight;

    public VrachtAutoObject(String title, Double weight, Double loadWeight) {
        super(title);
        this.weight = new HuurProperty<>(weight, HuurProperty.getDoubleReader());
        this.loadWeight = new HuurProperty<>(loadWeight, HuurProperty.getDoubleReader());
        typeName = "Vrachtauto";

        addMiscProperty("Laadvermogen", this.loadWeight);
    }

    @Override
    public HuurObject duplicate() {
        return new VrachtAutoObject(this.title.get(), this.weight.get(), this.loadWeight.get());
    }

    @Override
    public double getHuurPrijs() {
        return loadWeight.get() * 0.10;
    }

    @Override
    public double getVerzekeringPrijs() {
        return weight.get() * 0.01;
    }
}
