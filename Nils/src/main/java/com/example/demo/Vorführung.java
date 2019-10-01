package com.example.demo;

import java.util.Date;
import java.util.List;

public class Vorführung {

    private int vid,gesamtdauer,grundpreis,gesamtpreis,frei;
    private Kinosaal saal;
    private Film film;
    private Date zeitpunkt;
    private boolean dreiD;
    private List<Werbung> werbungen;
    private List<Preisvariation>preisvariationen;
    private List<Sitz>freieSitze;
    private List<Sitz>belegteSitze;


    public Vorführung(int vid, Kinosaal saal, int grundpreis, Film film, Date zeitpunkt, boolean dreiD, List<Werbung>werbungen, List<Preisvariation>preisvariationen) {
        this.vid = vid;
        this.saal = saal;
        this.grundpreis = grundpreis;
        this.film = film;
        this.zeitpunkt = zeitpunkt;
        this.dreiD = dreiD;
        this.werbungen = werbungen;
        this.preisvariationen = preisvariationen;
        this.freieSitze = saal.getSitze();
        this.frei = saal.getPlatzzahl();
    }//Konstruktor

   public Vorführung (){}

    public int getVid() {
        return vid;
    }

    public Kinosaal getSaal() {
        return saal;
    }

    public int getGesamtdauer() {
        int tmp = film.getDauer();
        for (int i = 0;i<werbungen.size();i++){
            tmp += werbungen.get(i).getDauer();
        }//for
      return tmp;
    }//getGesamtdauer

    public int getGesamtpreis() {
        int tmp = grundpreis;
        for (int i = 0; i<preisvariationen.size();i++){
          tmp += preisvariationen.get(i).getWert();
        }//for
      return tmp;
    }//getGesamtpreis

    public List<Sitz> getFreieSitze() {
        return freieSitze;
    }

    public Film getFilm() {
        return film;
    }

    public Date getZeitpunkt() {
        return zeitpunkt;
    }

    public boolean isDreiD() {
        return dreiD;
    }

    public List<Werbung> getWerbungen() {
        return werbungen;
    }

    public List<Preisvariation> getPreisvariationen() {
        return preisvariationen;
    }

    public boolean buchePlätze (List<Sitz> sitze){
      boolean möglich = true;
      if (frei==0 || sitze.size()>frei)möglich=  false;
      else {
        for (int i = 0;i<sitze.size();i++){
          Sitz tmp = sitze.get(i);
          if (!freieSitze.contains(tmp))möglich = false;
        }//for
        if (möglich){
          for (int i = 0;i<sitze.size();i++){
            Sitz tmp = sitze.get(i);
            belegteSitze.add(tmp);
            freieSitze.remove(tmp);
            frei--;
          }//for
        }//then
      }//else
      return möglich;
    }//buchen

  public boolean buchungMöglich (List<Sitz> sitze){
    boolean möglich = true;
    if (frei==0 || sitze.size()>frei)möglich = false;
    else {
      for (int i = 0; i < sitze.size(); i++) {
        Sitz tmp = sitze.get(i);
        if (!freieSitze.contains(tmp))
          möglich = false;
      }//for
    }//else
    return möglich;
  }//buchungMöglich

  public boolean equals (Vorführung vorführung){
      if (this.vid == vorführung.getVid())return true;
      else return false;
  }//equals

}//class
