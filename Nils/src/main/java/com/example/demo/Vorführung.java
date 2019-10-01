package com.example.demo;

import java.util.Date;
import java.util.List;

public class Vorführung {

    private int vid,gesamtdauer,grundpreis,gesamtpreis;
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
        this.grundpreis = this.grundpreis;
        this.film = film;
        this.zeitpunkt = zeitpunkt;
        this.dreiD = dreiD;
        this.freieSitze = freieSitze;
        this.werbungen = werbungen;
        this.preisvariationen = preisvariationen;
        this.freieSitze = saal.getSitze();
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

    public void buchePlätze (List<Sitz> sitze){
      for (int i = 0;i<sitze.size();i++){
        Sitz tmp = sitze.get(i);
        if (freieSitze.contains(sitze));
      }//for
    }//setFreieSitze
}//class
