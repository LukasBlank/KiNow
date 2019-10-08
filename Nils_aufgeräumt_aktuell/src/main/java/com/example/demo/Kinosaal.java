package com.example.demo;

public class Kinosaal {

    int kinoID,saalID;
    boolean barrierefrei;

    public Kinosaal(int kinoID, int saalID, boolean barrierefrei) {
        this.kinoID = kinoID;
        this.saalID = saalID;
        this.barrierefrei = barrierefrei;
    }

    public int getKinoID() {
        return kinoID;
    }

    public int getSaalID() {
        return saalID;
    }

    public boolean isBarrierefrei() {
        return barrierefrei;
    }
}
