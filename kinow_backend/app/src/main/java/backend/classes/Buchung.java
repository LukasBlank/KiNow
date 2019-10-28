package backend.classes;

import java.util.ArrayList;

public class Buchung {

  private double buchungspreis,vorführungspreis;
  private String vorführungsID,buchungsID,filmtitel;
  private ArrayList<Sitz> sitze;
  private ArrayList<Preisvariation> preisvariationen;

  public Buchung() {}

  public Buchung(double buchungspreis,String filmtitel,double vorführungspreis,String vorführungsID,String buchungID,ArrayList<Sitz>sitze,ArrayList<Preisvariation>preisvariationen){
    this.buchungsID = buchungID;
    this.vorführungsID =vorführungsID;
    this.vorführungspreis = vorführungspreis;
    this.sitze = sitze;
    this.preisvariationen = preisvariationen;
    this.filmtitel = filmtitel;
    setBuchungspreis();
    //setBuchungspreis
  }//K

  public double getVorführungspreis() {
    return vorführungspreis;
  }

  public void setVorführungspreis(Double vorführungspreis) {
    this.vorführungspreis = vorführungspreis;
    if (sitze!=null)setBuchungspreis();
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

  public String getFilmtitel() {
    return filmtitel;
  }

  public void setFilmtitel(String filmtitel) {
    this.filmtitel = filmtitel;
  }

  public ArrayList<Sitz> getSitze() {
    return sitze;
  }

  public void setSitze(ArrayList<Sitz> sitze) {
    this.sitze = sitze;
    if (this.vorführungspreis>0)setBuchungspreis();
  }

  public ArrayList<Preisvariation> getPreisvariationen() {
    return preisvariationen;
  }

  public void setPreisvariationen(ArrayList<Preisvariation> preisvariationen) {
    this.preisvariationen = preisvariationen;
  }

  public double getBuchungspreis () { return this.buchungspreis; }

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
      return this.buchungsID.substring(0,this.buchungsID.lastIndexOf('_'));
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
      case "vorführungspreis": this.vorführungspreis = Double.parseDouble(o.toString());
        break;
      case "vorführungsID": this.vorführungsID = (String) o;
        break;
      case "buchungspreis": this.buchungspreis = Double.parseDouble(o.toString());
        break;
      case "filmtitel": this.filmtitel = (String) o;
        break;
      default: System.out.println("Attribut existiert nicht.");
    }//switch
  }//set

}//class
