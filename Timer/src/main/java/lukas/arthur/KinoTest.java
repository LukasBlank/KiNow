package lukas.arthur;

import java.util.ArrayList;
import java.util.List;
import lukas.classes.Film;
import lukas.classes.Kino;
import lukas.classes.Kinosaal;
import lukas.classes.Sitz;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

public class KinoTest {
  ArrayList<Film> TestFilme = new ArrayList<Film>();
  List<String> darsteller1 = new ArrayList<String>();
  ArrayList<String> genres1 = new ArrayList<String>();
  ArrayList<String> regie1 = new ArrayList<String>();
  List<String> darsteller2 = new ArrayList<String>();
  ArrayList<String> genres2 = new ArrayList<String>();
  ArrayList<String> regie2 = new ArrayList<String>();
  /**
  Film TestFilm = new Film(1, "Das Erwachen der Macht", "Die Macht", 110, 16, 3, genres1,
      (ArrayList<String>) darsteller1, regie1);
  Film TestFilm2 = new Film(2, "Zombieland", "TestB", 150, 18, 5, genres2,
      (ArrayList<String>) darsteller2, regie2);
   **/
  ArrayList<Sitz> TestSitze = new ArrayList<Sitz>();
  Kinosaal TestSaal1 = new Kinosaal("1", 50, true, TestSitze);
  Kinosaal TestSaal2 = new Kinosaal("2", 50, true, TestSitze);
  ArrayList<Kinosaal> TestSaelle = new ArrayList<Kinosaal>();
  Kino TestKino = new Kino(1, "KiNOW", "Paderborn", TestFilme, TestSaelle);


  @Before
  public void main() {
    for (int i = 0; i < 49; i++) {
      TestSitze.add(new Sitz("" + (i + 1), false, false));
    }
  }

  @Test
  public void getKinoID() {
    Assert.assertEquals(1, TestKino.getKinoID());
  }

  @Test
  public void setKinoID() {
    TestKino.setKinoID(77);
    Assert.assertEquals(77, TestKino.getKinoID());
  }

  @Test
  public void getName() {
    Assert.assertEquals("KiNOW", TestKino.getName());
  }

  @Test
  public void setName() {
    TestKino.setName("Kino 3");
    Assert.assertEquals("Kino 3", TestKino.getName());
  }

  @Test
  public void getOrt() {
    Assert.assertEquals("Paderborn", TestKino.getOrt());
  }

  @Test
  public void setOrt() {
    TestKino.setOrt("FFM");
    Assert.assertEquals("FFM", TestKino.getOrt());
  }

  @Test
  public void getFilme() {
    Assert.assertEquals(TestFilme, TestKino.getFilme());
  }

  @Test
  public void setFilme() {
    ArrayList<Film> keineFilme = new ArrayList<Film>();
    TestKino.setFilme(keineFilme);
    Assert.assertEquals(keineFilme, TestKino.getFilme());
  }

  @Test
  public void getSaele() {
    Assert.assertEquals(TestSaelle, TestKino.getSaele());
  }

  @Test
  public void setSaele() {
    ArrayList<Kinosaal> keineSaele = new ArrayList<Kinosaal>();
    TestKino.setSaele(keineSaele);
    Assert.assertEquals(keineSaele, TestKino.getSaele());
  }

  @Test
  public void equals() {
    Assert.assertEquals(true, TestKino.equals(TestKino));
    Assert.assertEquals(true, new Kino(1, "KiNOW", "Paderborn", TestFilme, TestSaelle).equals(TestKino));
  }

  /**
  @Test
  public void addFilm() {
    TestKino.addFilm(new Film(5, "Das Erwachen der Macht", "Die Macht", 110, 16, 3, genres1,
        (ArrayList<String>) darsteller1, regie1));
    Assert.assertEquals(TestKino.getFilm(5),
        new Film(5, "Das Erwachen der Macht", "Die Macht", 110, 16, 3, genres1,
            (ArrayList<String>) darsteller1, regie1));
  }

  @Test
  public void removeFilm() {
    TestKino.removeFilm(TestFilm2);
    Assert.assertEquals(null, TestKino.getFilm(2));
  }


  @Test
  public void getFilm() {
    Assert.assertEquals(TestFilm, TestKino.getFilm(1));
  }

  @Test
  public void getSaal() {
    Assert.assertEquals(TestSaal1, TestKino.getSaal("1"));
  }

  @Test
  public void set() {
    TestKino.set("KinoID", 3);
    TestKino.set("name", "Hella");
    TestKino.set("ort", "Mannheim");
    Assert.assertEquals(3, TestKino.getKinoID());
    Assert.assertEquals("Hella", TestKino.getName());
    Assert.assertEquals("Mannheim", TestKino.getOrt());
  }
**/
}