package main.java.lukas.java_classes;


public class Preisvariation {

  private int variationID,wert;

  public Preisvariation (int variationID, int wert){
    this.variationID = variationID;
    this.wert = wert;
  }//Konstruktor

  public Preisvariation () {}

  public int getVariationID() {
    return variationID;
  }

  public int getWert() {
    return wert;
  }

  public boolean equals (Preisvariation preisvariation){
    if (this.variationID==preisvariation.getVariationID())return true;
    else return false;
  }//equals

}//class
