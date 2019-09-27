package com.example.demo;

public class Sitz {

    int sitzID,saalID;
    boolean loge,barrierefrei;

    public Sitz(int sitzID, int saalID, int reihe, int sitzplatz, boolean loge, boolean barrierefrei) {
        this.sitzID = sitzID;
        this.saalID = saalID;
        this.loge = loge;
        this.barrierefrei = barrierefrei;
    }//Konstruktor

    public int getSitzID() {
        return sitzID;
    }

    public int getSaalID() {
        return saalID;
    }

    public boolean getBarrierefrei() { return barrierefrei; }

    public boolean isLoge() { return loge; }
}//class
