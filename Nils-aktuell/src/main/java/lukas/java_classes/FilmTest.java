package main.java.lukas.java_classes;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;


public class FilmTest {

  List<Film> TestFilme = new ArrayList<Film>();
  List<Darsteller> TestDarsteller = new ArrayList<Darsteller>();
  Darsteller Kyle = new Darsteller(1,"Kyle","Browslowski",TestFilme);
  Film TestFilm = new Film(1,"Die Antwoord",null,"Ein Film über Die Antwoord,",110,18,5,null,TestDarsteller);
  Film TestFilm2 = new Film(2,"Fast5",null,"Ein Film über Die Antwoord,",110,18,5,null,TestDarsteller);

  @Before
  public void main(){
    TestFilme.add(TestFilm);
    TestFilme.add(TestFilm2);
    TestDarsteller.add(Kyle);
  }


  @Test
  public void setFilmID() {
    Assert.assertEquals(1,TestFilm.getFilmID());
  }

  @Test
  public void setFsk() {
    TestFilm.setFsk(23);
    Assert.assertEquals(23,TestFilm.getFsk());
  }

  @Test
  public void setDauer() {
    TestFilm2.setDauer(18);
    Assert.assertEquals(18,TestFilm2.getDauer());
  }

  @Test
  public void setBewertung() {
    Assert.assertEquals(5,TestFilm2.getBewertung());
    TestFilm2.setBewertung(2);
    Assert.assertEquals(2,TestFilm2.getBewertung());
  }

  @Test
  public void setTitel() {
    TestFilm2.setTitel("Test1337");
    Assert.assertEquals("Test1337",TestFilm2.getTitel());
  }

  @Test
  public void setBeschreibung() {
    String besch = "Goiler Film";
    TestFilm2.setBeschreibung(besch);
    Assert.assertEquals(besch,TestFilm2.getBeschreibung());
  }

  @Test
  public void getFilmID() {
    Assert.assertEquals(1,TestFilm.getFilmID());
  }

  @Test
  public void getFsk() {
    Assert.assertEquals(18,TestFilm.getFsk());
  }

  @Test
  public void getDauer() {
    Assert.assertEquals(110,TestFilm.getDauer());
  }

  @Test
  public void getBewertung() {
    Assert.assertEquals(5,TestFilm2.getBewertung());
  }

  @Test
  public void getTitel() {
    Assert.assertEquals("Fast5",TestFilm2.getTitel());
  }

  @Test
  public void getBeschreibung() {
    Assert.assertEquals("Ein Film über Die Antwoord,",TestFilm2.getBeschreibung());
  }

  @Test
  public void getRegie() {
    Assert.assertEquals(null,TestFilm2.getRegie());
  }

  @Test
  public void getGenres() {
  }

  @Test
  public void getDarsteller() {
  }

  @Test
  public void equals() {
  }
}
