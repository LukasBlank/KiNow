package com.example.demo;

public class Kino {
    int kinoID;
    String name,adresse;

    public Kino(int kinoID, String name, String adresse) {
        this.kinoID = kinoID;
        this.name = name;
        this.adresse = adresse;
    }//Konstruktor

    public int getKinoID() {
        return kinoID;
    }

    public String getName() {
        return name;
    }

    public String getOrt() {
        return adresse;
    }

}//class
