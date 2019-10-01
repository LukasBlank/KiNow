package com.example.demo;

public class Sitz {

    private Kinosaal saal;
    private String sitzID;
    private boolean loge,barrierefrei;

    public Sitz(String sitzID, Kinosaal saal, boolean loge, boolean barrierefrei) {
        this.sitzID = sitzID;
        this.saal = saal;
        this.loge = loge;
        this.barrierefrei = barrierefrei;
    }//Konstruktor

    public Sitz() {}

    public String getSitzID() {
        return sitzID;
    }

    public Kinosaal getSaal() {
        return saal;
    }

    public boolean getBarrierefrei() { return barrierefrei; }

    public boolean isLoge() { return loge; }

    public boolean equals (Sitz sitz){
        if (sitz.getSitzID().equals(sitzID) && saal.getSaalnummer()==sitz.getSaal().getSaalnummer() && saal.getKino().getKinoID()==sitz.getSaal().getKino().getKinoID())return true;
        else return false;
    }//equals
}//class
