package com.example.demo;

public class Regisseur {

  private int regisseurID;
  private String vorname,nachname;

  public Regisseur(int regisseurID, String vorname, String nachname){
    this.regisseurID = regisseurID;
    this.vorname = vorname;
    this.nachname = nachname;
  }//Konstruktor

  public Regisseur() {}

  public int getRegisseurID() {
    return regisseurID;
  }

  public String getVorname() {
    return vorname;
  }

  public String getNachname() {
    return nachname;
  }

  public boolean equals (Regisseur regisseur){
    if (this.regisseurID==regisseur.getRegisseurID())return true;
    else return false;
  }//equals

}///class
