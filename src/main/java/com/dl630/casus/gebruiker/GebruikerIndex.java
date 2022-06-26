package com.dl630.casus.gebruiker;

import java.util.ArrayList;

public class GebruikerIndex {
    private static ArrayList<Gebruiker> gebruikers = new ArrayList<>();

    public static void initIndexes() {
        gebruikers.add(new Gebruiker("maximus22", "geheim"));
    }

    public ArrayList<Gebruiker> getGebruikers() {
        if (gebruikers.size() < 1) initIndexes();
        return new ArrayList<>(gebruikers);
    }

    public static Gebruiker getGebruiker(String gebruikersnaam) {
        if (gebruikers.size() < 1) initIndexes();
        for (Gebruiker gebruiker : gebruikers) {
            if (gebruiker.getGebruikersnaam().equals(gebruikersnaam)) return gebruiker;
        }

        return null;
    }

    public static void addGebruiker(Gebruiker gebruiker) {
        if (gebruikers.size() < 1) initIndexes();
        gebruikers.add(gebruiker);
    }

    public static Boolean removeGebruiker(String gebruikersnaam) {
        if (gebruikers.size() < 1) initIndexes();
        for (Gebruiker gebruiker : gebruikers) {
            if (gebruiker.getGebruikersnaam().equals(gebruikersnaam)) {
                gebruikers.remove(gebruiker);
                return true;
            }
        }

        return false;
    }
}
