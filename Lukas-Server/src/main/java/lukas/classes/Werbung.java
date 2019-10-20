package lukas.classes;

public class Werbung {

  private long dauer;
  private String name,werbungID;

  public Werbung() {}

  public Werbung(String werbungID, long dauer, String name){
    this.werbungID = werbungID;
    this.dauer = dauer;
    this.name = name;
  }//K

  public String getWerbungID() {
    return werbungID;
  }

  public void setWerbungID(String werbungID) {
    this.werbungID = werbungID;
  }

  public long getDauer() {
    return dauer;
  }

  public void setDauer(long dauer) {
    this.dauer = dauer;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean equals (Werbung werbung){
    if (this.werbungID==werbung.getWerbungID())return true;
    else return false;
  }//equals

  public void set (String key, Object o){
    switch (key){
      case "name": this.name = (String) o;
      break;
      case "werbungID": this.werbungID = (String) o;
      break;
      case "dauer": this.dauer = Long.parseLong(o.toString());
      break;
      default: System.out.println("Attribut existiert nicht.");
    }//switch
  }//set

}//class