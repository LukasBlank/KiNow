package com.example.demo;

public class Zahlungsmethode{
    int methodeID;
    String methode;

    public Zahlungsmethode(int methodeID, String methode) {
        this.methodeID = methodeID;
        this.methode = methode;
    }

    public int getMethodeID() { return methodeID; }

    public String getMethode() {
        return methode;
    }

    //Ermöglicht die Bestimmung der Gleichheit von Zahlungsmethoden
    public boolean equals (Zahlungsmethode z){
      if (this.getMethode().equals(z.getMethode()))return true;
      else return false;
    }//equals
}
