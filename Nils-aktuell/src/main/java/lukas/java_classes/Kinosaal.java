package main.java.lukas.java_classes;

import java.util.List;

public class Kinosaal {

    private int saalnummer,platzzahl, kinoID;
    private Kino kino;
    private boolean barrierefrei;
    private  List<Sitz> sitze;

    public Kinosaal(Kino kino, int saalnummer, boolean barrierefrei,int platzzahl, List<Sitz>sitze) {
        this.kino = kino;
        this.saalnummer = saalnummer;
        this.barrierefrei = barrierefrei;
        this.platzzahl = platzzahl;
        this.sitze = sitze;
    }//Konstruktor

    public Kinosaal(int kinoID, int saalnummer, boolean barrierefrei,int platzzahl) {
        this.kinoID = kinoID;
        this.saalnummer = saalnummer;
        this.barrierefrei = barrierefrei;
        this.platzzahl = platzzahl;
        this.sitze = sitze;
    }//Konstruktor

    public Kinosaal (){}

    public Kino getKino() {
          return kino;
    }

    public int getSaalnummer() {
        return saalnummer;
    }

    public boolean istBarrierefrei() {
        return barrierefrei;
    }

    public int getPlatzzahl () { return platzzahl; }

    public List<Sitz> getSitze () { return sitze; }

    public boolean equels (Kinosaal saal){
      if (this.saalnummer == saal.saalnummer && this.kino.getKinoID()==saal.getKino().getKinoID())return true;
      else return false;
    }//equals

}//class
