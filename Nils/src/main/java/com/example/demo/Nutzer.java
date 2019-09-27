package com.example.demo;

import javax.validation.constraints.Email;
import java.util.Date;

public class Nutzer {
    int Nid;
    String Vorname,Nachname;
    Date Geburtstag;
    String email;
    String passwort;

    public Nutzer(int nid, String vorname, String nachname, Date geburtstag, String email, String passwort) {
        Nid = nid;
        Vorname = vorname;
        Nachname = nachname;
        Geburtstag = geburtstag;
        this.email = email;
        this.passwort = passwort;
    }

    public int getNid() {
        return Nid;
    }

    public void setNid(int nid) {
        Nid = nid;
    }

    public String getVorname() {
        return Vorname;
    }

    public void setVorname(String vorname) {
        Vorname = vorname;
    }

    public String getNachname() {
        return Nachname;
    }

    public void setNachname(String nachname) {
        Nachname = nachname;
    }

    public Date getGeburtstag() {
        return Geburtstag;
    }

    public void setGeburtstag(Date geburtstag) {
        Geburtstag = geburtstag;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }
}
