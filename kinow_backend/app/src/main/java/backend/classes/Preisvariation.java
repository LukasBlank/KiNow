package backend.classes;

public class Preisvariation {

  private long variationID,wert;
  private String name;

  public Preisvariation () {}

  public Preisvariation(String name, long variationID, long wert){
    this.name = name;
    this.variationID = variationID;
    this.wert = wert;
  }//K

  public long getVariationID() {
    return variationID;
  }

  public void setVariationID(long variationID) {
    this.variationID = variationID;
  }

  public long getWert() {
    return wert;
  }

  public void setWert(long wert) {
    this.wert = wert;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean equals (Preisvariation p){
      return this.variationID == p.variationID;
  }//equals

  public void set (String key, Object o){
    switch (key){
      case "name": this.name = (String) o;
      break;
      case "variationID": this.variationID = Long.parseLong(o.toString());
      break;
      case "wert": this.wert = Long.parseLong(o.toString());
      break;
      default: System.out.println("Attribut existiert nicht.");
    }//switch
  }//set

}//class
