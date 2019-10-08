package com.example.demo;

public class Buchung {
    int NID;
    int BID;
    int VID;

    public Buchung(int NID, int BID, int VID) {
        this.NID = NID;
        this.BID = BID;
        this.VID = VID;
    }

    public int getNID() {
        return NID;
    }

    public int getBID() {
        return BID;
    }

    public int getVID() {
        return VID;
    }
}
