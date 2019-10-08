package com.example.demo;

public class Kino {
    int kinoID;
    String name,ort;

    public Kino(int kinoID, String name, String ort) {
        this.kinoID = kinoID;
        this.name = name;
        this.ort = ort;
    }

    public int getKinoID() {
        return kinoID;
    }

    public String getName() {
        return name;
    }

    public String getOrt() {
        return ort;
    }
}
