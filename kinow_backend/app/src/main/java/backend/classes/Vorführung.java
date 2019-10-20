package backend.classes;

import java.util.ArrayList;

public class Vorführung {

  private long grunddauer,gesamtdauer,grundpreis,gesamtpreis;
  private String vorführungsID, zeitpunkt, saalnummer;
  private boolean dreiD;
  private ArrayList<Werbung> werbungen;
  private ArrayList<Sitz>freieSitze;
  private ArrayList<Sitz>belegteSitze;

  public Vorführung() {}

  public Vorführung(String vorführungsID,String saalnummer,long grunddauer, long grundpreis, long gesamtdauer, String zeitpunkt, boolean dreiD, ArrayList<Werbung> werbungen, ArrayList<Sitz> freieSitze){
    this.vorführungsID = vorführungsID;
    this.saalnummer = saalnummer;
    this.zeitpunkt = zeitpunkt;
    setGrundpreis(grundpreis);
    setDreiD(dreiD);
    setGrunddauer(grunddauer);
    setWerbungen(werbungen);
    this.freieSitze = freieSitze;
    this.belegteSitze = null;
  }//Vorführung

  public String getSaalnummer() {
    return saalnummer;
  }

  public void setSaalnummer(String saalnummer) {
    this.saalnummer = saalnummer;
  }

  public String getKinoID () {
    if (vorführungsID.length()==0 || vorführungsID.indexOf('_')==-1)return null;
    else return vorführungsID.substring(0,vorführungsID.indexOf('_'));
  }//getKinoID

  public long getGrunddauer() {
    return grunddauer;
  }

  public void setGrunddauer(long grunddauer) {
    this.grunddauer = grunddauer;
    if (werbungen!=null)setGesamtdauer();
  }//set

  private void setGesamtdauer(){
    if (werbungen != null && grunddauer != 0){
      gesamtdauer = grunddauer;
      for (Werbung akt : werbungen){
        gesamtdauer += akt.getDauer();
      }//for
    }//then
  }//setGesamtdauer

  private long getGesamtdauer() {
    if (grunddauer == 0 || werbungen == null) return -1;
    else return gesamtdauer;
  }//getGesamtdauer

  public void setGrundpreis(long grundpreis) {
    this.grundpreis = grundpreis;
    setGesamtpreis();
  }//setGrundpreis

  public void setGesamtpreis(){
    if (grundpreis != 0){
      if (dreiD)gesamtpreis = grundpreis + 4;
      else gesamtpreis = grundpreis;
    }//then
  }//setGesamtpreis

  public long getGesamtpreis () { return gesamtpreis; }

  public String getVorführungsID() {
    return vorführungsID;
  }

  public void setVorführungsID(String vorführungsID) {
    this.vorführungsID = vorführungsID;
  }

  public String getZeitpunkt() {
    return zeitpunkt;
  }

  public String getZeit (){
    if (this.zeitpunkt.indexOf('/')==-1)return null;
    else {
      return zeitpunkt.substring(zeitpunkt.indexOf('/')+1);
    }//else
  }//Zeit

  public String getDatum(){
    if (this.zeitpunkt.indexOf('/')==-1)return null;
    else return zeitpunkt.substring(0,zeitpunkt.indexOf('/'));
  }//getDatum

  public void setZeitpunkt(String zeitpunkt) {
    this.zeitpunkt = zeitpunkt;
  }

  public boolean isDreiD() {
    return dreiD;
  }

  public void setDreiD(boolean dreiD) {
    this.dreiD = dreiD;
    if(grundpreis!=0)setGesamtpreis();
  }//setDreiD

  public ArrayList<Werbung> getWerbungen() {
    return werbungen;
  }

  public void setWerbungen(ArrayList<Werbung> werbungen) {
    this.werbungen = werbungen;
    if (grunddauer!=0)setGesamtdauer();
  }//setWerbungen

  public void addWerbung(Werbung werbung){
    if (!werbungen.contains(werbung))werbungen.add(werbung);
  }//addWerbung

  public void removeWerbung(Werbung werbung){
    if(werbungen.contains(werbung))werbungen.remove(werbung);
  }//removeWerbung

  public ArrayList<Sitz> getFreieSitze() {
    return freieSitze;
  }//getFreieSitze

  public ArrayList<Sitz> getBelegteSitze() { return belegteSitze; }

  public void setFreieSitze(ArrayList<Sitz> freieSitze) {
    this.freieSitze = freieSitze;
  }

  public boolean sitzeFrei (ArrayList<Sitz> sitze){
    if (freieSitze.size() < sitze.size())return false;
    else {
      for (Sitz sitz : sitze){
        if ((!belegteSitze.contains(sitz)) && (freieSitze.contains(sitz)))return true;
      }//for
      return false;
    }//else
  }//sitzeFrei

  public void bucheSitze (ArrayList<Sitz> sitze) {
    if (sitzeFrei(sitze)){
      for (Sitz sitz : sitze){
        belegteSitze.add(sitz); freieSitze.remove(sitz);
      }//for
    }//then
  }//buchen

  public int getAnzahlFreieSitze (){
    return freieSitze.size();
  }//getAnzahlFreieSitze

  public boolean equals (Vorführung vor){
    if (vorführungsID.equals(vor.getVorführungsID()))return true;
    else return false;
  }//equals

  public void set (String key, Object o){
    switch (key){
      case "vorführungsID": this.vorführungsID = (String)o;
      break;
      case "zeitpunkt": this.zeitpunkt = (String) o;
      break;
      case "saalnummer": this.saalnummer = (String) saalnummer;
      break;
      case "grundpreis": this.grundpreis = Long.parseLong(o.toString());
      break;
      case "grunddauer": this.grunddauer = Long.parseLong(o.toString());
      break;
      case "dreiD": this.dreiD = Boolean.parseBoolean(o.toString());
      break;
      case "gesamtpreis": this.gesamtpreis = Long.parseLong(o.toString());
      break;
      case "gesamtdauer": this.gesamtdauer = Long.parseLong(o.toString());
      break;
      default: System.out.println("Attribut existiert nicht.");
    }//switch
  }//set

}//class
