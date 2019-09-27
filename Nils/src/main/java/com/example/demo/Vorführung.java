package com.example.demo;

import java.util.Date;

public class Vorführung {

    int vid,filmID,saalID,gesamtdauer,preis,freieSitze;
    Date zeitpunkt;
    boolean dreiD;



    public Vorführung(int vid, int filmID, int saalID, int preis, boolean dreiD, Date zeitpunkt) {
        this.vid = vid;
        this.filmID = filmID;
        this.saalID = saalID;
        this.preis = preis;
        this.zeitpunkt = zeitpunkt;
        this.dreiD = dreiD;
    }//Konstruktor

    public void setGesamtdauer(int gesamtdauer) {
        this.gesamtdauer = gesamtdauer;
    }

    public void setFreieSitze(int freieSitze) {
        this.freieSitze = freieSitze;
    }

    public int getVid() {
        return vid;
    }

    public int getFilmID() {
        return filmID;
    }

    public int getSaalID() {
        return saalID;
    }

    public int getGesamtdauer() {
        return gesamtdauer;
    }

    public int getPreis() {
        return preis;
    }

    public int getFreieSitze() {
        return freieSitze;
    }

    public Date getZeitpunkt() {
        return zeitpunkt;
    }

    public boolean isDreiD() {
        return dreiD;
    }
}//class
