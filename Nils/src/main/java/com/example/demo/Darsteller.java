package com.example.demo;

import java.util.List;

public class Darsteller {

  private int darstellerID;
  private String vorname,nachname;
  private List<Film> filme;

  public Darsteller (int darstellerID, String vorname, String nachname, List<Film> filme){
    this.darstellerID = darstellerID;
    this.vorname = vorname;
    this.nachname = nachname;
    this.filme = filme;
  }//Konstruktor

  public Darsteller() {}

  public int getDarstellerID() {
    return darstellerID;
  }

  public String getVorname() {
    return vorname;
  }

  public String getNachname() {
    return nachname;
  }

  public List<Film> getFilme() {
    return filme;
  }

  public void addFilm (Film film){
    boolean neu = true;
    for (int i = 0;i<filme.size();i++){
      Film tmp = filme.get(i);
      if (tmp.equals(film))neu = false;
    }//for
    if (neu) filme.add(film);
  }//addFilm

}//class
