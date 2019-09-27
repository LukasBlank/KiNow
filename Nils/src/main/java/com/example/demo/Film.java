package com.example.demo;

public class Film {
    int FID,FSK,dauer,bewertung;
    String titel,beschreibung;
    boolean dreiD;

    public Film(int FID, int FSK, int dauer, int bewertung, String titel, String beschreibung, boolean dreiD) {
        this.FID = FID;
        this.FSK = FSK;
        this.dauer = dauer;
        this.bewertung = bewertung;
        this.titel = titel;
        this.beschreibung = beschreibung;
        this.dreiD = dreiD;
    }

    public int getFSK() {
        return FSK;
    }

    public int getDauer() {
        return dauer;
    }

    public int getBewertung() {
        return bewertung;
    }

    public int getFID() {
        return FID;
    }

    public String getTitel() {
        return titel;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public boolean isDreiD() {
        return dreiD;
    }
}
