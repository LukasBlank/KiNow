package com.example.demo;

import java.util.Date;

public class Vorführung {

    int VID,FID,SaalID;
    Date zeitpunk;


    public Vorführung(int VID, int FID, int saalID, Date zeitpunk) {
        this.VID = VID;
        this.FID = FID;
        SaalID = saalID;
        this.zeitpunk = zeitpunk;
    }

    public int getVID() {
        return VID;
    }

    public int getFID() {
        return FID;
    }

    public int getSaalID() {
        return SaalID;
    }

    public Date getZeitpunk() {
        return zeitpunk;
    }
}
