package main.java.lukas.java_classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class BestellungTest {
  Nutzer TestNutzer = new Nutzer(1,"mail","Max","Mustermann","diverse","BDay","3333");
  Zahlungsmethode PayPal = new Zahlungsmethode(1,"PayPal");
  List<Buchung> TestBuchungen = new ArrayList<Buchung>();
  List<Kinosaal> TestSaelle = new ArrayList<Kinosaal>();
  List<Film> TestFilme = new ArrayList<Film>();
  Film TestFilm = new Film(1,"Die Antwoord",null,"Ein Film über Die Antwoord,",110,18,5,null,null);
  List<Sitz> TestSitze = new ArrayList<Sitz>();
  Kinosaal TestSaal = new Kinosaal(new Kino(1,"kiNow","Mannheim",TestSaelle,TestFilme),1,true,69,TestSitze);

  Vorführung TestVorfuehrung = new Vorführung(1,TestSaal,7,TestFilm,new Date(2019,9,7),true,null);



  Bestellung testBestellung = new Bestellung(1,TestNutzer,PayPal,TestBuchungen);


  Buchung testBuchung = new Buchung(1,TestVorfuehrung,testBestellung,TestSitze,null);

  @Before
  public void main(){
    TestSaelle.add(TestSaal);
    TestFilme.add(TestFilm);
    TestBuchungen.add(testBuchung);
    TestSitze.add(new Sitz("1",TestSaal,true,true));

  }

  @Test
  public void getBesetellungsnummer() { Assert.assertEquals(1,testBestellung.getBesetellungsnummer()); }

  @Test
  public void getGesamtpreis() {
    Assert.assertEquals(7,testBestellung.getGesamtpreis());
  }

  @Test
  public void getNutzer() {
    Assert.assertEquals(TestNutzer,testBestellung.getNutzer());
  }

  @Test
  public void getMethode() {
    Assert.assertEquals(PayPal,testBestellung.getMethode());
  }

  @Test
  public void getBuchungen() {
    Assert.assertEquals(TestBuchungen,testBestellung.getBuchungen());
  }

  @Test
  public void equals (Bestellung bestellung){
    Assert.assertEquals(true,testBestellung.equals(testBestellung));
    Assert.assertEquals(false,testBestellung.equals(new Bestellung(2,TestNutzer,PayPal,TestBuchungen)));
  }
}
