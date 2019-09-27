package com.example.demo;

public class Zahlungsmethode{
    int methodeID;
    String methode;

    public Zahlungsmethode(int methodeID, String methode) {
        this.methodeID = methodeID;
        this.methode = methode;
    }

    public int getMethodeID() { return methodeID; }

    public void setMethodeID(int methodeID) { this.methodeID = methodeID; }

    public String getMethode() {
        return methode;
    }

    public void setMethode(String methode) {
        this.methode = methode;
    }

    //Ermöglicht die Bestimmung der Gleichheit von Zahlungsmethoden
    public boolean equals (Zahlungsmethode z){
      if (this.getMethode().equals(z.getMethode()))return true;
      else return false;
    }//equals
}
