package com.example.demo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.text.SimpleDateFormat;
import javax.validation.constraints.Email;
import java.util.Date;

public class Nutzer {
    int Nid;
    String Vorname,Nachname;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    Date Geburtstag;
    String Email;
    String Passwort;

    public Nutzer() {
    }



    public Nutzer(int Nid, String Vorname, String Nachname, Date Geburtstag, String Email, String Passwort) {
        this.Nid = Nid;
        this.Vorname = Vorname;
        this.Nachname = Nachname;
        this.Geburtstag = Geburtstag;
        this.Email = Email;
        this.Passwort = Passwort;
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
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getPasswort() {
        return Passwort;
    }

    public void setPasswort(String passwort) {
        this.Passwort = passwort;
    }
}
