package com.example.demo;

import java.util.List;
import javax.validation.constraints.Email;
import java.util.Date;
import sun.security.util.Password;

public class Nutzer {
    int nutzerID;
    String email,vorname,nachname,geschlecht;
    Date geburtstag;
    Password passwort;
    List<Zahlungsmethode> zahlungsmethoden;

    public Nutzer(int nutzerID, String email, String vorname, String nachname, String geschlecht, Date geburtstag, Password passwort) {
        this.nutzerID = nutzerID;
        this.email = email;
        this.vorname = vorname;
        this.nachname = nachname;
        this.geschlecht = geschlecht;
        this.geburtstag = geburtstag;
        this.passwort = passwort;
    }//Konstruktor

    public void setEmail(String email) {
        this.email = email;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public void setGeschlecht(String geschlecht) {
        this.geschlecht = geschlecht;
    }

    public void setGeburtstag(Date geburtstag) {
        this.geburtstag = geburtstag;
    }

    public void setPasswort(Password passwort) {
        this.passwort = passwort;
    }

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
      int i = zahlungsmethoden.size();
      //Prüfung auf Duplikate
      for (int n = 0;n<i;n++){
        Zahlungsmethode tmp = zahlungsmethoden.get(n);
        if (tmp.equals(z))neu = false;
      }//for
      if (neu)zahlungsmethoden.add(z);
    }//addPayment

}//class
