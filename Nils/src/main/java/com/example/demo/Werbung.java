package com.example.demo;

public class Werbung {

  int werbungID,dauer;
  String inhalt;

  public Werbung(int werbungID, int dauer, String inhalt){
    this.werbungID = werbungID;
    this.dauer = dauer;
    this.inhalt = inhalt;
  }//Konstruktor

  public int getWerbungID() {
    return werbungID;
  }

  public int getDauer() {
    return dauer;
  }

  public String getInhalt() {
    return inhalt;
  }
}//class
