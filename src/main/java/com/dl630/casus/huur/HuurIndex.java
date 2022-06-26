package com.dl630.casus.huur;

import com.dl630.casus.core.EventSubscriber;

import java.util.ArrayList;

public class HuurIndex {
    public static ArrayList<HuurObject> huurObjecten = new ArrayList<>();

    public static void initIndexes() {
        huurObjecten.add(new BoorObject("Hendrik's boor", "MLX300", "Yamaha"));
        huurObjecten.add(new BoorObject("Gave Boormachine", "DCD777S2T", "DeWalt"));
        huurObjecten.add(new BoorObject("Einhell TE-CD", "TE-CD", "Einhell"));
        huurObjecten.add(new VrachtAutoObject("Coole Vracht", 800.0, 150.0));
        huurObjecten.add(new VrachtAutoObject("Vrachtmachien Vrachtmachien", 200.0, 5.0));
        huurObjecten.add(new VrachtAutoObject("DAF CF", 600.0, 100.0));
        huurObjecten.add(new PersoonAutoObject("Autotje", 1200.0, "Prius"));
        huurObjecten.add(new PersoonAutoObject("Schroot", 800.0, "Lexus"));
        huurObjecten.add(new PersoonAutoObject("Raceauto", 1000.0, "Jaguar"));
    }

    public static ArrayList<HuurObject> getHuurObjecten() {
        if (huurObjecten.size() < 1) initIndexes();
        return new ArrayList<>(huurObjecten);
    }

    public static void addHuurObject(HuurObject huurObject) {
        huurObjecten.add(huurObject);
        EventSubscriber.broadcastEvent("huur_index_update");
    }

    public static HuurObject getObjectWithTitle(String title) {
        for (HuurObject huurObject : huurObjecten) {
            if (huurObject.getTitle().equals(title)) {
                return huurObject;
            }
        }

        return null;
    }

    public static ArrayList<HuurObject> getHuurObjectTypes() {
        if (huurObjecten.size() < 1) initIndexes();
        ArrayList<HuurObject> objects = new ArrayList<>();
        String type = new String();
        for (HuurObject huurObject : huurObjecten) {
            if (!huurObject.getTypeName().equals(type)) {
                objects.add(huurObject);
                type = huurObject.getTypeName();
            }
        }

        return objects;
    }
}
