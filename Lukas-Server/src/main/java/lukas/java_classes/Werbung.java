package lukas.java_classes;

public class Werbung {

  private int werbungID,dauer;
  private String inhalt;

  public Werbung(int werbungID, int dauer, String inhalt){
    this.werbungID = werbungID;
    this.dauer = dauer;
    this.inhalt = inhalt;
  }//Konstruktor

  public Werbung() {}

  public int getWerbungID() {
    return werbungID;
  }

  public int getDauer() {
    return dauer;
  }

  public String getInhalt() {
    return inhalt;
  }

  public boolean equals (Werbung werbung){
    if (this.werbungID==werbung.getWerbungID())return true;
    else return false;
  }//equals

}//class
