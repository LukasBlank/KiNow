package lukas.classes;

import java.util.ArrayList;

public class Buchung {

  private long buchungspreis,vorführungspreis;
  private String vorführungsID,buchungsID;
  private ArrayList<Sitz> sitze;
  private ArrayList<Preisvariation> preisvariationen;

  public Buchung() {}

  public Buchung(long buchungspreis,long vorführungspreis,String vorführungsID,String buchungID,ArrayList<Sitz>sitze,ArrayList<Preisvariation>preisvariationen){
    this.buchungsID = buchungID;
    this.vorführungsID =vorführungsID;
    this.vorführungspreis = vorführungspreis;
    this.sitze = sitze;
    this.preisvariationen = preisvariationen;
    setBuchungspreis();
    //setBuchungspreis
  }//K

  public long getVorführungspreis() {
    return vorführungspreis;
  }

  public void setVorführungspreis(long vorführungspreis) {
    this.vorführungspreis = vorführungspreis;
  }

  public String getVorführungsID() {
    return vorführungsID;
  }

  public void setVorführungsID(String vorführungsID) {
    this.vorführungsID = vorführungsID;
  }

  public String getBuchungID() {
    return buchungsID;
  }

  public void setBuchungID(String buchungID) {
    this.buchungsID = buchungID;
  }

  public ArrayList<Sitz> getSitze() {
    return sitze;
  }

  public void setSitze(ArrayList<Sitz> sitze) {
    this.sitze = sitze;
  }

  public ArrayList<Preisvariation> getPreisvariationen() {
    return preisvariationen;
  }

  public void setPreisvariationen(ArrayList<Preisvariation> preisvariationen) {
    this.preisvariationen = preisvariationen;
  }

  public long getBuchungspreis () { return this.buchungspreis; }

  public void setBuchungspreis (){
    if (vorführungspreis!=0 && sitze!=null){
      for (Sitz sitz : sitze){
        if (sitz.isLoge())this.buchungspreis += vorführungspreis + 2;
        else this.buchungspreis += vorführungspreis;
      }//for
      if (preisvariationen != null){
        for (Preisvariation p : preisvariationen){
          this.buchungspreis += p.getWert();
        }//for
      }//then
    }//then
  }//setBuchungspreis

  public String getBestellungsnummer (){
    if (this.buchungsID.length()>0 && this.buchungsID.indexOf('_')!=-1){
      return this.buchungsID.substring(this.buchungsID.indexOf('_')+1,this.buchungsID.lastIndexOf('_'));
    }//then
    else return null;
  }//getBestellugsnummer

  public String getNutzerID () {
    if (this.buchungsID.length()>0 && this.buchungsID.indexOf('_')!=-1){
      return this.buchungsID.substring(0,this.buchungsID.indexOf('_'));
    }//then
    else return null;
  }

  public boolean equals (Buchung buchung){
    if (this.buchungsID.equals(buchung.getBuchungID()))return true;
    else return false;
  }//equals

  public void set (String key, Object o){
    switch (key){
      case "buchungsID": this.buchungsID = (String) o;
      break;
      case "vorführungspreis": this.vorführungspreis = Long.parseLong(o.toString());
      break;
      case "vorführungsID": this.vorführungsID = (String) o;
      break;
      default: System.out.println("Attribut existiert nicht.");
    }//switch
  }//set

}//class
