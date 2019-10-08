package com.example.demo;

public class Zahlungsmethode {
    int zId;
    String methode;

    public Zahlungsmethode(int zId, String methode) {
        this.zId = zId;
        this.methode = methode;
    }

    public int getzId() {
        return zId;
    }

    public void setzId(int zId) {
        this.zId = zId;
    }

    public String getMethode() {
        return methode;
    }

    public void setMethode(String methode) {
        this.methode = methode;
    }
}
