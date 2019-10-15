package lukas.java_classes;

import java.util.List;

public class Bestellung {

  private int besetellungsnummer,gesamtpreis;
  private Nutzer nutzer;
  private Zahlungsmethode methode;
  private List<Buchung> buchungen;

  public Bestellung (int besetellungsnummer, Nutzer nutzer, Zahlungsmethode methode, List<Buchung> buchungen){
    this.besetellungsnummer = besetellungsnummer;
    this.nutzer = nutzer;
    this.methode = methode;
    this.buchungen = buchungen;
  }//Bestellung

  public Bestellung () {}

  public int getBesetellungsnummer() {
    return besetellungsnummer;
  }

  public int getGesamtpreis() {
    for (int i = 0;i<buchungen.size();i++){
      gesamtpreis += buchungen.get(i).getBuchungspreis();
    }//for
    return gesamtpreis;
  }//getGesamtpreis

  public Nutzer getNutzer() {
    return nutzer;
  }

  public Zahlungsmethode getMethode() {
    return methode;
  }

  public List<Buchung> getBuchungen() {
    return buchungen;
  }

  public boolean equals (Bestellung bestellung){
    if (this.besetellungsnummer == bestellung.getBesetellungsnummer())return true;
    else return false;
  } //equals

}///class
