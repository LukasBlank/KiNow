package backend.classes;


public class Sitz {

  private String sitzID;
  private boolean loge,barrierefrei;

  public Sitz() {}

  public Sitz(String sitzID, boolean loge, boolean barrierefrei){
    this.sitzID = sitzID;
    this.loge = loge;
    this.barrierefrei =  barrierefrei;
  }//Sitz

  public String getSitzID() {
    return sitzID;
  }

  public void setSitzID(String sitzID) {
    this.sitzID = sitzID;
  }

  public boolean isLoge() {
    return loge;
  }

  public void setLoge(boolean loge) {
    this.loge = loge;
  }

  public boolean isBarrierefrei() {
    return barrierefrei;
  }

  public void setBarrierefrei(boolean barrierefrei) {
    this.barrierefrei = barrierefrei;
  }

  //anhand der SitzID die KinoID und die Saalnummer holen
  public String getKinoID (){
    if (sitzID.length()==0)return null;
    else if (sitzID.indexOf('_')==-1)return null;
    else {
      String erg = sitzID.substring(0,sitzID.indexOf('_'));
      return erg;
    }//else
  }//getKinoID

  public String getSaalnummer(){
    if (sitzID.length()==0)return null;
    else if (sitzID.indexOf('_')==-1)return null;
    else {
      String erg = sitzID.substring(0,sitzID.lastIndexOf('_'));
      return erg;
    }//else
  }//getSaalnummer

  public boolean equals (Sitz sitz){
    if (this.sitzID.equals(sitz.getSitzID()))return true;
    else return false;
  }//equals

  public void set (String key, Object o){
    switch (key){
      case "sitzID": this.sitzID = (String) o;
      break;
      case "loge": this.loge = Boolean.parseBoolean(o.toString());
      break;
      case "barrierefrei": this.barrierefrei = Boolean.parseBoolean(o.toString());
      break;
      default: System.out.println("Attribut existiert nicht.");
    }//switch
  }//set

}//class
