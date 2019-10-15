package lukas.java_classes;

import java.util.ArrayList;
import java.util.List;


public class FilmLukas {
  private long filmID,fsk,dauer,bewertung;
  private String titel,beschreibung;
  private ArrayList<String> genres;
  private ArrayList<String> darsteller;
  private ArrayList<String> regie;

  public FilmLukas () {}

  public FilmLukas (long filmID, String titel, String beschreibung, long dauer, long fsk, long bewertung, ArrayList<String> genres, ArrayList<String> darsteller, ArrayList<String> regie){
    this.filmID = filmID;
    this.titel = titel;
    this.beschreibung = beschreibung;
    this.dauer = dauer;
    this.fsk = fsk;
    this.bewertung = bewertung;
    this.genres = genres;
    this.darsteller = darsteller;
    this.regie = regie;
  }//K

  public long getFilmID() {
    return filmID;
  }

  public void setFilmID(long filmID) {
    this.filmID = filmID;
  }

  public long getFsk() {
    return fsk;
  }

  public void setFsk(long fsk) {
    this.fsk = fsk;
  }

  public long getDauer() {
    return dauer;
  }

  public void setDauer(long dauer) {
    this.dauer = dauer;
  }

  public long getBewertung() {
    return bewertung;
  }

  public void setBewertung(long bewertung) {
    this.bewertung = bewertung;
  }

  public String getTitel() {
    return titel;
  }

  public void setTitel(String titel) {
    this.titel = titel;
  }

  public String getBeschreibung() {
    return beschreibung;
  }

  public void setBeschreibung(String beschreibung) {
    this.beschreibung = beschreibung;
  }

  public ArrayList<String> getGenres() {
    return genres;
  }

  public void setGenres(ArrayList<String> genres) {
    this.genres = genres;
  }

  public ArrayList<String> getDarsteller() {
    return darsteller;
  }

  public void setDarsteller(ArrayList<String> darsteller) {
    this.darsteller = darsteller;
  }

  public ArrayList<String> getRegie() {
    return regie;
  }

  public void setRegie(ArrayList<String> regie) {
    this.regie = regie;
  }

  public boolean equals (FilmLukas film){
    if (this.filmID==film.getFilmID())return true;
    else return false;
  }//equals

  public void set (String key, Object o){
    switch (key){
      case "titel": this.titel = (String)o;
      break;
      case "beschreibung": this.beschreibung = (String)o;
      break;
      case "regie": this.regie = (ArrayList<String>) o;
      break;
      case "darsteller": this.darsteller = (ArrayList<String>) o;
      break;
      case "genres": this.genres = (ArrayList<String>) o;
      break;
      case "filmID": this.filmID = (long) o;
      break;
      case "dauer": this.dauer = (long) o;
      break;
      case "fsk": this.fsk = (long) o;
      break;
      case "bewertung": this.bewertung = (long) o;
      break;
      default: System.out.println("Attribut existiert nicht.");
    }//switch
  }//set


}//FilmLukas
