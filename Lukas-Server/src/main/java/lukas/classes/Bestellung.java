package lukas.classes;

import java.util.ArrayList;
import java.util.List;

public class Bestellung {

  private double gesamtpreis;
  private String besetellungsnummer;
  private ArrayList<Buchung> buchungen;
  private long timestamp;

  public Bestellung (){}

  public Bestellung(String besetellungsnummer, double gesamtpreis, ArrayList<Buchung> buchungen){
    this.besetellungsnummer = besetellungsnummer;
    this.buchungen = buchungen;
    setGesamtpreis();
  }//K

  public void setTimestamp (long time) {timestamp = time;}

  public long getTimestamp () {return timestamp;}

  public String getBesetellungsnummer() {
    return besetellungsnummer;
  }

  public double getGesamtpreis() {
    return gesamtpreis;
  }

  public List<Buchung> getBuchungen() {
    return buchungen;
  }

  public void setBesetellungsnummer(String besetellungsnummer) {
    this.besetellungsnummer = besetellungsnummer;
  }

  public void setBuchungen(ArrayList<Buchung> buchungen) {
    this.buchungen = buchungen;
    setGesamtpreis();
  }

  public void setGesamtpreis (){
    if (buchungen!=null){
      gesamtpreis = 0;
      for (Buchung b : buchungen){
        gesamtpreis += b.getBuchungspreis();
      }//for
    }//then
  }//Gesamtpreis

  public void addBuchung (Buchung buchung){
    if (!buchungen.contains(buchung))buchungen.add(buchung);
    setGesamtpreis();
  }//addBuchung

  public void removeBuchung (Buchung buchung){
    if (buchungen.contains(buchung))buchungen.remove(buchung);
    setGesamtpreis();
  }//buchung

  public String getNutzerID (){
    if (this.besetellungsnummer.length()>0 && this.besetellungsnummer.indexOf('_')!=-1){
      return this.besetellungsnummer.substring(0,this.besetellungsnummer.indexOf('_'));
    }//then
    else return null;
  }//getNutzer

  public boolean equals (Bestellung bestellung){
    if (this.besetellungsnummer.equals(bestellung.getBesetellungsnummer()))return true;
    else return false;
  }//equals

  public void set (String key, Object o){
    switch (key){
      case "bestellungsnummer": this.besetellungsnummer = (String) o;
      break;
      case "gesamtpreis": this.gesamtpreis = Long.parseLong(o.toString());
      break;
      default: System.out.println("Attribut existiert nicht.");
    }//switch
  }//set
}//Bestellung
