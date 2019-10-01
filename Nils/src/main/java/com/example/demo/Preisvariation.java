package com.example.demo;


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
}//class
