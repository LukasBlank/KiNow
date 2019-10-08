package com.example.demo;

public class Kunden_Zahlungsmethode {
    int Nid;
    int Zid;


    public Kunden_Zahlungsmethode(int nid, int zid) {
        Nid = nid;
        Zid = zid;
    }

    public int getNid() {
        return Nid;
    }

    public void setNid(int nid) {
        Nid = nid;
    }

    public int getZid() {
        return Zid;
    }

    public void setZid(int zid) {
        Zid = zid;
    }
}
