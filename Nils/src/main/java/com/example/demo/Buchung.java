package com.example.demo;

import java.util.List;

public class Buchung {

    private int buchungID,buchungspreis;
    private Vorführung vorführung;
    private Bestellung bestellung;
    private List<Sitz> sitze;

    public Buchung(int buchungID, Vorführung vorführung, Bestellung bestellung, List<Sitz> sitze) {
        this.buchungID = buchungID;
        this.bestellung = bestellung;
        this.vorführung = vorführung;
        this.sitze = sitze;
    }//Konstruktor

    public Buchung () {}

  public int getBuchungID() {
    return buchungID;
  }

  public Vorführung getVorführung() {
    return vorführung;
  }

  public Bestellung getBestellung() {
    return bestellung;
  }

  public List<Sitz> getSitze() {
    return sitze;
  }

  public int getBuchungspreis(){
      //TODO
      int zusatzLoge = 2;
      int preis = vorführung.getGesamtpreis();
      int gesamt = sitze.size()*preis;
      for (int i = 0;i<sitze.size();i++){
        if(sitze.get(i).isLoge())gesamt += zusatzLoge;
      }//for
    return gesamt;
  }//getBuchungspreis

  public boolean equals (Buchung buchung){
      if (this.buchungID==buchung.getBuchungID())return true;
      else return false;
  }//equals



}//class
