package arthur;

import java.util.ArrayList;
import java.util.List;
import backend.classes.Film;
import backend.classes.Kino;
import backend.classes.Kinosaal;
import backend.classes.Sitz;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class KinosaalTest {

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
  Kinosaal TestSaal1 = new Kinosaal("1_", 50, true, TestSitze);
  ArrayList<Kinosaal> TestSaelle = new ArrayList<Kinosaal>();
  Kino TestKino = new Kino(1, "KiNOW", "Paderborn", TestFilme, TestSaelle);


  @Before
  public void main() {
    TestKino.setSaele(TestSaelle);
    TestSaelle.add(TestSaal1);
    for (int i = 0; i < 49; i++) {
      TestSitze.add(new Sitz("" + (i + 1), false, false));
    }
  }

  @Test
  public void getSaalnummer() {
    Assert.assertEquals("1_",TestSaal1.getSaalnummer());
  }

  @Test
  public void setSaalnummer() {
    TestSaal1.setSaalnummer("23");
    Assert.assertEquals("23",TestSaal1.getSaalnummer());
  }

  @Test
  public void getPlatzzahl() {
    Assert.assertEquals(50,TestSaal1.getPlatzzahl());
  }

  @Test
  public void setPlatzzahl() {
    TestSaal1.setPlatzzahl(67);
    Assert.assertEquals(67,TestSaal1.getPlatzzahl());
  }

  @Test
  public void isBarrierefrei() {
    Assert.assertEquals(true,TestSaal1.isBarrierefrei());
  }

  @Test
  public void setBarrierefrei() {
    TestSaal1.setBarrierefrei(true);
    Assert.assertEquals(true,TestSaal1.isBarrierefrei());
  }

  @Test
  public void getSitze() {
    Assert.assertEquals(TestSitze,TestSaal1.getSitze());
  }

  @Test
  public void setSitze() {
    ArrayList<Sitz> keineSitze = new ArrayList<Sitz>();
    TestSaal1.setSitze(keineSitze);
    Assert.assertEquals(keineSitze,TestSaal1.getSitze());
  }

  @Test
  public void getKinoID() {
    main();
    Assert.assertEquals("1",TestSaal1.getKinoID());
  }

  @Test
  public void equals() {
    Assert.assertEquals(true,TestSaal1.equals(TestSaal1));
    Assert.assertEquals(false,TestSaal1.equals(new Kinosaal("1", 50, true, TestSitze)));
  }

  @Test
  public void getSitz() {
    Assert.assertEquals(TestSitze,TestSaal1.getSitze());
  }

  @Test
  public void set() {
    TestSaal1.set("saalnummer","55");
    TestSaal1.set("platzzahl",5);
    TestSaal1.set("barrierefrei",true);
    Assert.assertEquals("55",TestSaal1.getSaalnummer());
    Assert.assertEquals(5,TestSaal1.getPlatzzahl());
    Assert.assertEquals(true,TestSaal1.isBarrierefrei());
  }
}