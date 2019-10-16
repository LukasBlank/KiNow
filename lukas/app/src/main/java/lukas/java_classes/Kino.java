package lukas.java_classes;

import java.util.List;

import lukas.classes.Film;

public class Kino {
    private int kinoID;
    private String name,ort;
    private List<Film> filme;
    private List<Kinosaal>säle;

    public Kino(int kinoID, String name, String ort, List<Kinosaal>säle, List<Film> filme) {
        this.kinoID = kinoID;
        this.name = name;
        this.ort = ort;
        this.säle = säle;
        this.filme = filme;
    }//Konstruktor

    public Kino () {}

    public int getKinoID() {
        return kinoID;
    }

    public String getName() {
        return name;
    }

    public String getOrt() {
        return ort;
    }

    public List<Film> getFilme (){ return filme; }

    public void addFilm (Film film){
        boolean neu = true;
        for (int i = 0;i<filme.size();i++){
          Film tmp = filme.get(i);
          if (tmp.equals(film))neu = false;
        }//for
        if (neu) filme.add(film);
    }//addFilm

  public void removeFilm (Film film){
      int pos = -1;
      for (int i = 0; i<filme.size();i++){
        Film tmp = filme.get(i);
        if (tmp.equals(film))pos = i;
      }//for
    if (pos!=-1)filme.remove(pos);
  }//removeFilm

    public List<Kinosaal> getSäle (){ return säle; }

    public boolean equals (Kino kino){
      if (this.kinoID==kino.getKinoID())return true;
      else return false;
    }//equals

}//class