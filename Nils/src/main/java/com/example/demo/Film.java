package com.example.demo;

public class Film {
    int filmID,fsk,dauer,bewertung;
    String titel,beschreibung,genre,regie,darsteller;

    public Film(int filmID, String titel, String genre, String regie, String darsteller, String beschreibung, int dauer, int fsk, int bewertung) {
        this.filmID = filmID;
        this.titel = titel;
        this.genre = titel;
        this.regie = regie;
        this.darsteller = darsteller;
        this.beschreibung = beschreibung;
        this.fsk = fsk;
        this.dauer = dauer;
        this.bewertung = bewertung;
    }//Konstruktor

  public int getFilmID() {
    return filmID;
  }

  public int getFsk() {
    return fsk;
  }

  public int getDauer() {
    return dauer;
  }

  public int getBewertung() {
    return bewertung;
  }

  public String getTitel() {
    return titel;
  }

  public String getBeschreibung() {
    return beschreibung;
  }

  public String getGenre() {
    return genre;
  }

  public String getRegie() {
    return regie;
  }

  public String getDarsteller() {
    return darsteller;
  }

}//class
