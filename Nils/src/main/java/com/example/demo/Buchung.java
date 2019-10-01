package com.example.demo;

import java.util.List;

public class Buchung {

    private int buchungID, bestellungsnummer, vid;
    private List<Sitz> sitze;

    public Buchung(int buchungID, int bestellungsnummer, int vid) {
        this.buchungID = buchungID;
        this.bestellungsnummer = bestellungsnummer;
        this.vid = vid;
        this.sitze = sitze;
    }//Konstruktor

    public void bucheSitze (List<Sitz> sitze){
        if (this.sitze.size()==0)this.sitze = sitze;
        else {
            for (int i = 0;i<sitze.size();i++){
                Sitz tmp = sitze.get(i);
                this.sitze .add(tmp);
            }///for
        }//else

    }//bucheSitze



}//class
