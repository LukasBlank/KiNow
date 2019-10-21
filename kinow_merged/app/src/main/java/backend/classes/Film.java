package backend.classes;

import java.io.Serializable;
import java.util.ArrayList;



public class Film implements Serializable {
  private long filmID,fsk,dauer,bewertung;
  private String titel,beschreibung;
  private ArrayList<String> genres;
  private ArrayList<String> darsteller;
  private ArrayList<String> regie;
  private ArrayList<Vorführung> vorführungen;

  public Film() {}

  public Film(long filmID, String titel, String beschreibung, long dauer, long fsk, long bewertung, ArrayList<String> genres, ArrayList<String> darsteller, ArrayList<String> regie, ArrayList<Vorführung> vorführungen){
    this.filmID = filmID;
    this.titel = titel;
    this.beschreibung = beschreibung;
    this.dauer = dauer;
    this.fsk = fsk;
    this.bewertung = bewertung;
    this.genres = genres;
    this.darsteller = darsteller;
    this.regie = regie;
    this.vorführungen = vorführungen;
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

  public ArrayList<Vorführung> getVorführungen() {
    return vorführungen;
  }

  public void setVorführungen(ArrayList<Vorführung> vorführungen) {
    this.vorführungen = vorführungen;
  }

  public Vorführung getVorführung (String vorführungsID){
    if (vorführungen!= null){
      for (Vorführung v : vorführungen){
        if (v.getVorführungsID().equals(vorführungsID))return v;
      }//for
      return null;
    }//then
    else return null;
  }//Vorführung

  public void addVorführung (Vorführung v){
    if (!vorführungen.contains(v))vorführungen.add(v);
  }//addVorführung

  public void removeVorführung (Vorführung v){
    if (vorführungen.contains(v))vorführungen.remove(v);
  }//removeVorführung

  public boolean equals (Film film){
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
      case "filmID": this.filmID = Long.parseLong(o.toString());
        break;
      case "dauer": this.dauer = Long.parseLong(o.toString());
        break;
      case "fsk": this.fsk = Long.parseLong(o.toString());
        break;
      case "bewertung": this.bewertung = Long.parseLong(o.toString());
        break;
      default: System.out.println("Attribut existiert nicht.");
    }//switch
  }//set

}//Film
