package com.example.demo;

import java.util.List;
import javax.validation.constraints.Email;
import java.util.Date;
import sun.security.util.Password;

public class Nutzer {
    private int nutzerID;
    private String email,vorname,nachname,geschlecht;
    private Date geburtstag;
    private Password passwort;
    private List<Zahlungsmethode> zahlungsmethoden;
    private List<Bestellung> bestellungen;

    public Nutzer(int nutzerID, String email, String vorname, String nachname, String geschlecht, Date geburtstag, Password passwort) {
        this.nutzerID = nutzerID;
        this.email = email;
        this.vorname = vorname;
        this.nachname = nachname;
        this.geschlecht = geschlecht;
        this.geburtstag = geburtstag;
        this.passwort = passwort;
    }//Konstruktor

   public Nutzer (){}

    public void setPasswort(Password passwort) {
        this.passwort = passwort;
    }

    public void setEmail(String email) { this.email = email; }

    public int getNutzerID() {
        return nutzerID;
    }

    public String getEmail() {
        return email;
    }

    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public String getGeschlecht() {
        return geschlecht;
    }

    public Date getGeburtstag() {
        return geburtstag;
    }

    public Password getPasswort() {
        return passwort;
    }

    public void addPayment (Zahlungsmethode z){
      boolean neu = true;
      //Prüfung auf Duplikate
      for (int i = 0;i<zahlungsmethoden.size();i++){
        Zahlungsmethode tmp = zahlungsmethoden.get(i);
        if (tmp.equals(z))neu = false;
      }//for
      if (neu)zahlungsmethoden.add(z);
    }//addPayment

  public List<Zahlungsmethode> getZahlungsmethoden () { return zahlungsmethoden; }

  public void addBestellung (Bestellung bestellung){ if(bestellung.getNutzer().getNutzerID()==this.nutzerID)bestellungen.add(bestellung); }

  public List<Bestellung> getBestellungen () { return bestellungen; }

  public boolean equals (Nutzer nutzer){
      if (this.nutzerID == nutzer.getNutzerID())return true;
      else return false;
  }//equals

}//class
