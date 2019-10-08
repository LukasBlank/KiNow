package com.example.demo;

public class Sitz {

    int sitzID,saalID,reihe,sitzplatz;
    boolean loge;

    public Sitz(int sitzID, int saalID, int reihe, int sitzplatz, boolean loge) {
        this.sitzID = sitzID;
        this.saalID = saalID;
        this.reihe = reihe;
        this.sitzplatz = sitzplatz;
        this.loge = loge;
    }

    public int getSitzID() {
        return sitzID;
    }

    public int getSaalID() {
        return saalID;
    }

    public int getReihe() {
        return reihe;
    }

    public int getSitzplatz() {
        return sitzplatz;
    }

    public boolean isLoge() {
        return loge;
    }
}
