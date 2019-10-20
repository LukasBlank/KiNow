package backend.classes;

import java.util.ArrayList;

public class Kinosaal {

  private long platzzahl;
  private String saalnummer;
  private boolean barrierefrei;
  private ArrayList<Sitz> sitze;

  public Kinosaal () {}

  public Kinosaal (String saalnummer, long platzzahl, boolean barrierefrei, ArrayList<Sitz> sitze){
    this.saalnummer = saalnummer;
    this.platzzahl = platzzahl;
    this.barrierefrei = barrierefrei;
    this.sitze = sitze;
  }//K

  public String getSaalnummer() {
    return saalnummer;
  }

  public void setSaalnummer(String saalnummer) {
    this.saalnummer = saalnummer;
  }

  public long getPlatzzahl() {
    return platzzahl;
  }

  public void setPlatzzahl(long platzzahl) {
    this.platzzahl = platzzahl;
  }

  public boolean isBarrierefrei() {
    return barrierefrei;
  }

  public void setBarrierefrei(boolean barrierefrei) {
    this.barrierefrei = barrierefrei;
  }

  public ArrayList<Sitz> getSitze() {
    return sitze;
  }

  public void setSitze(ArrayList<Sitz> sitze) {
    this.sitze = sitze;
  }

  //anhand der Saalnummer wird die KinoID herausgefunden
  public String getKinoID () {
    if (saalnummer.length()==0)return null;
    else if (saalnummer.indexOf('_')==-1)return null;
    else {
      String erg = saalnummer.substring(0,saalnummer.indexOf('_'));
      return erg;
    }//else
  }//getKinoID

  //pretty useless, cannot get the kino that way
  public boolean equals (Kinosaal saal){
    if (this.saalnummer.equals(saal.getSaalnummer()))return true;
    else return false;
  }//equals

  public Sitz getSitz (String sitzID){
    Sitz erg = null;
    for (Sitz akt : sitze){
      if (akt.getSitzID().equals(sitzID))erg = akt;
    }//for
    return erg;
  }//getSitz

  public void set (String key, Object o){
    switch (key){
      case "saalnummer": this.saalnummer = (String)o;
      break;
      case "platzzahl": this.platzzahl = Long.parseLong(o.toString());
      break;
      case "barrierefrei": this.barrierefrei = Boolean.parseBoolean(o.toString());
      break;
      default: System.out.println("Attribut existiert nicht.");
    }//switch
  }//set

}//class
