package lukas.classes;

import java.util.ArrayList;

public class Kino {

  private long kinoID;
  private String name,ort;
  private ArrayList<Film> filme;
  private ArrayList<Kinosaal>saele;

  public Kino () {}

  public Kino (long kinoID, String name, String ort, ArrayList<Film> filme, ArrayList<Kinosaal> saele){
    this.kinoID = kinoID;
    this.name = name;
    this.ort = ort;
    this.filme = filme;
    this.saele = saele;
  }//Kino

  public long getKinoID() {
    return kinoID;
  }

  public void setKinoID(long kinoID) {
    this.kinoID = kinoID;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getOrt() {
    return ort;
  }

  public void setOrt(String ort) {
    this.ort = ort;
  }

  public ArrayList<Film> getFilme() {
    return filme;
  }

  public void setFilme(ArrayList<Film> filme) {
    this.filme = filme;
  }

  public ArrayList<Kinosaal> getSaele() {
    return saele;
  }

  public void setSaele(ArrayList<Kinosaal> saele) {
    this.saele = saele;
  }

  public boolean equals (Kino kino){
    if (this.kinoID == kino.getKinoID()) return true;
    else return false;
  }//equals

  public void addFilm (Film film) {
    if (!filme.contains(film))filme.add(film);
    else System.out.println("Film wird hier bereits gespielt.");
  }//add

  public void removeFilm (Film film) {
    if (!filme.contains(film))System.out.println("Dieser Film wird hier nicht gespielt.");
    else filme.remove(film);
  }//remove

  public Film getFilm (long ID){
    Film erg = null;
    for (Film akt : filme){
      if (akt.getFilmID()==ID)erg = akt;
    }//for
    return erg;
  }//getFilm

  public Kinosaal getSaal (String ID){
    Kinosaal erg = null;
    for (Kinosaal saal : saele){
      if (saal.getSaalnummer().equals(ID))erg = saal;
    }//for
    return erg;
  }//getSaal

  public void set (String key, Object o){
    switch (key){
      case "kinoID": this.kinoID = Long.parseLong(o.toString());
        break;
      case "name": this.name =  (String) o;
        break;
      case "ort": this.ort = (String) o;
        break;
      default: System.out.println("Attribut existiert nicht.");
    }//switch
  }//set

}//class

