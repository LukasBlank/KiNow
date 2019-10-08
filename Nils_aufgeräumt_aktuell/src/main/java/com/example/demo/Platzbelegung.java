package com.example.demo;

public class Platzbelegung {

    int VID,reihe,sitzplatz;
    boolean frei;

    public Platzbelegung(int VID, int reihe, int sitzplatz) {
        frei=false;
        this.VID = VID;
        this.reihe = reihe;
        this.sitzplatz = sitzplatz;
    }

    public void setFrei(boolean frei) {
        this.frei = frei;
    }

    public int getVID() {
        return VID;
    }

    public int getReihe() {
        return reihe;
    }

    public int getSitzplatz() {
        return sitzplatz;
    }

    public boolean isFrei() {
        return frei;
    }
}
