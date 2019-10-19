package arthur;

import java.util.ArrayList;
import java.util.List;
import lukas.classes.Film;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FilmTest {


  List<Film> TestFilme = new ArrayList<Film>();
  List<String> darsteller1 = new ArrayList<String>();
  ArrayList<String> genres1 = new ArrayList<String>();
  ArrayList<String> regie1 = new ArrayList<String>();
  List<String> darsteller2 = new ArrayList<String>();
  ArrayList<String> genres2 = new ArrayList<String>();
  ArrayList<String> regie2 = new ArrayList<String>();
  Film TestFilm = new Film(1,"Das Erwachen der Macht", "Die Macht", 110, 16, 3, genres1,
      (ArrayList<String>) darsteller1, regie1);
  Film TestFilm2 = new Film(2,"Zombieland","TestB",150,18,5,genres2,
      (ArrayList<String>) darsteller2,regie2);

  @Before
  public void main(){
    darsteller1.add("Andi");
    darsteller1.add("Leo");
    genres1.add("Action");
    genres1.add("Comedy");
    regie1.add("Chris");
    darsteller2.add("Heinrich");
    darsteller2.add("Angi");
    genres2.add("Blue");
    genres2.add("Green");
    regie2.add("Micha");
    TestFilme.add(TestFilm);
    TestFilme.add(TestFilm2);

  }


  @Test
  public void setFilmID() {
    TestFilm.setFilmID(66);
    Assert.assertEquals(66,TestFilm.getFilmID());
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
    Assert.assertEquals(16,TestFilm.getFsk());
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
    Assert.assertEquals("Zombieland",TestFilm2.getTitel());
  }

  @Test
  public void getBeschreibung() {
    Assert.assertEquals("TestB",TestFilm2.getBeschreibung());
  }

  @Test
  public void getRegie() {
    Assert.assertEquals(regie2,TestFilm2.getRegie());
  }

  @Test
  public void getGenres() {
    Assert.assertEquals(genres1,TestFilm.getGenres());
  }

  @Test
  public void getDarsteller() {
    Assert.assertEquals(darsteller1,TestFilm.getDarsteller());
  }

  @Test
  public void equals() {
   Film TestFilm3 = new Film(1,"Das Erwachen der Macht", "Die Macht", 110, 16, 3, genres1,
        (ArrayList<String>) darsteller1, regie1);
    Assert.assertEquals(true,TestFilm.equals(TestFilm));
    Assert.assertEquals(true,TestFilm3.equals(TestFilm));
  }

  @Test
  public void set() {
    TestFilm.set("titel","Fast5");
    TestFilm.set("beschreibung","Ein guter Film sieht anders aus");
    TestFilm.set("regie",regie2);
    TestFilm.set("darsteller",darsteller2);
    TestFilm.set("genres",genres2);
    TestFilm.set("filmID",7);
    TestFilm.set("dauer",333);
    TestFilm.set("fsk",90);
    TestFilm.set("bewertung",10);
    Assert.assertEquals("Fast5",TestFilm.getTitel());
    Assert.assertEquals("Ein guter Film sieht anders aus",TestFilm.getBeschreibung());
    Assert.assertEquals(regie2,TestFilm.getRegie());
    Assert.assertEquals(darsteller2,TestFilm.getDarsteller());
    Assert.assertEquals(genres2,TestFilm.getGenres());
    Assert.assertEquals(7,TestFilm.getFilmID());
    Assert.assertEquals(333,TestFilm.getDauer());
    Assert.assertEquals(90,TestFilm.getFsk());
    Assert.assertEquals(10,TestFilm.getBewertung());
  }
}
