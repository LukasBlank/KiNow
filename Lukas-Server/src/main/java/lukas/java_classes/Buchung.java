package lukas.java_classes;

import java.util.List;
import lukas.classes.Sitz;

public class Buchung {

    private int buchungID,buchungspreis,logezusatz;
    private Vorführung vorführung;
    private Bestellung bestellung;
    private List<Sitz> sitze;
    private List<Preisvariation> preisvariationen;

    public Buchung(int buchungID, Vorführung vorführung, Bestellung bestellung, List<Sitz> sitze, List<Preisvariation> preisvariationen) {
        this.buchungID = buchungID;
        this.bestellung = bestellung;
        this.vorführung = vorführung;
        this.sitze = sitze;
        this.preisvariationen = preisvariationen;
        logezusatz = 2;
    }//Konstruktor

    public Buchung () {}

  public int getBuchungID() {
    return buchungID;
  }

  public Vorführung getVorführung() {
    return vorführung;
  }

  public Bestellung getBestellung() {
    return bestellung;
  }

  public List<Sitz> getSitze() {
    return sitze;
  }

  public long getBuchungspreis(){
      long preis = vorführung.getGrundpreis();
      long gesamt = sitze.size()*preis;
      for (int i = 0;i<sitze.size();i++){
        if(sitze.get(i).isLoge())gesamt += logezusatz;
      }//for
    for (int i = 0;i<preisvariationen.size();i++){
       gesamt += preisvariationen.get(i).getWert();
    }//for
    return gesamt;
  }//getBuchungspreis

  public boolean equals (Buchung buchung){
      if (this.buchungID==buchung.getBuchungID())return true;
      else return false;
  }//equals



}//class
