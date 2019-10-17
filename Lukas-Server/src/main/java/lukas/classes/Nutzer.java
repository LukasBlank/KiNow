package lukas.classes;

import java.util.ArrayList;
import java.util.List;
import lukas.java_classes.Bestellung;

public class Nutzer {

  private String nutzerID;
  private String email,vorname,nachname,geschlecht,geburtstag;
  private String passwort;
  private ArrayList<String> zahlungsmethoden;
  private ArrayList<Bestellung> bestellungen;

  public Nutzer (){}

  public Nutzer (String nutzerID, String email, String vorname, String nachname,String geburtstag, String geschlecht,String passwort, ArrayList<String> zahlungsmethoden, ArrayList<Bestellung> bestellungen){
    this.nutzerID = nutzerID;
    this.vorname = vorname;
    this.nachname = nachname;
    this.email = email;
    this.geburtstag = geburtstag;
    this.geschlecht = geschlecht;
    this.passwort = passwort;
    this.zahlungsmethoden = zahlungsmethoden;
    this.bestellungen = bestellungen;
  }//K

  public String getNutzerID() {
    return nutzerID;
  }

  public void setNutzerID(String nutzerID) {
    this.nutzerID = nutzerID;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getVorname() {
    return vorname;
  }

  public void setVorname(String vorname) {
    this.vorname = vorname;
  }

  public String getNachname() {
    return nachname;
  }

  public void setNachname(String nachname) {
    this.nachname = nachname;
  }

  public String getGeschlecht() {
    return geschlecht;
  }

  public void setGeschlecht(String geschlecht) {
    this.geschlecht = geschlecht;
  }

  public String getGeburtstag() {
    return geburtstag;
  }

  public void setGeburtstag(String geburtstag) {
    this.geburtstag = geburtstag;
  }

  public String getPasswort() {
    return passwort;
  }

  public void setPasswort(String passwort) {
    this.passwort = passwort;
  }

  public ArrayList<String> getZahlungsmethoden() {
    return zahlungsmethoden;
  }

  public void setZahlungsmethoden(ArrayList<String> zahlungsmethoden) {
    this.zahlungsmethoden = zahlungsmethoden;
  }

  public ArrayList<Bestellung> getBestellungen() {
    return bestellungen;
  }

  public void setBestellungen(ArrayList<Bestellung> bestellungen) {
    this.bestellungen = bestellungen;
  }

  public Bestellung getBestellung (int bestellungsnummer){
    Bestellung erg = null;
    for (Bestellung bestellung : bestellungen){
      if (bestellung.getBesetellungsnummer()==bestellungsnummer)erg = bestellung;
    }//for
    return erg;
  }//getBestellungen

  public void addBestellung (Bestellung bestellung){
    if (!this.bestellungen.contains(bestellung))bestellungen.add(bestellung);
  }//addBestellung

  public boolean equals (Nutzer nutzer){
    if (this.nutzerID.equals(nutzer.getNutzerID()))return true;
    else return false;
  }//nutzer

  public void set (String key, Object o){
    switch (key){
      case "nutzerID": this.nutzerID = (String) o;
        break;
      case "vorname": this.vorname = (String) o;
        break;
      case "nachname": this.nachname = (String) o;
        break;
      case "geschlecht": this.geschlecht = (String) o;
        break;
      case "geburtstag": this.geburtstag = (String) o;
        break;
      case "passwort": this.passwort = (String) o;
        break;
      case "email": this.email = (String) o;
        break;
      case "zahlungsmethoden": this.zahlungsmethoden = (ArrayList<String>) o;
        break;
      default: System.out.println("Attribut existiert nicht.");
    }//switch
  }//set

}//class
