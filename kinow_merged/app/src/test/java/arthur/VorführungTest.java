package arthur;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import backend.classes.Kino;
import backend.classes.Kinosaal;
import backend.classes.Sitz;
import backend.classes.Vorführung;
import backend.classes.Werbung;

public class VorführungTest {
  ArrayList<Sitz> TestSitze = new ArrayList<Sitz>();
  ArrayList<Werbung> TestWerbung = new ArrayList<Werbung>();
  Vorführung TestVorfuehrung = new Vorführung("1_7_5_1","1_7",90, 5, 110, "2019-11-11/15:00", true, TestWerbung, TestSitze);
  Kinosaal TestSaal1 = new Kinosaal("1_7", 50, true, TestSitze);
  Kinosaal TestSaal2 = new Kinosaal("1_2", 50, true, TestSitze);
  ArrayList<Kinosaal> TestSaelle = new ArrayList<Kinosaal>();
  Kino TestKino = new Kino(1, "KiNOW", "Paderborn", null, TestSaelle);
  @Before
  public void main() {
    for (int i = 0; i < 49; i++) {
      TestSitze.add(new Sitz("1_7_5_1_" + (i + 1), false, false));
    }
  }

  @Test
  public void getSaalnummer() {
    Assert.assertEquals("7",TestVorfuehrung.getSaalnummer());
  }

  @Test
  public void setSaalnummer() {
    TestVorfuehrung.setSaalnummer("1_2");
    Assert.assertEquals("2",TestVorfuehrung.getSaalnummer());
  }

  @Test
  public void getKinoID() {
    Assert.assertEquals("1",TestVorfuehrung.getKinoID());

  }

  @Test
  public void getGrunddauer() {
    Assert.assertEquals(90,TestVorfuehrung.getGrunddauer());
  }

  @Test
  public void setGrunddauer() {
    TestVorfuehrung.setGrunddauer(50);
    Assert.assertEquals(50,TestVorfuehrung.getGrunddauer());
  }

  @Test
  public void setGrundpreis() {
    TestVorfuehrung.setGrundpreis(7.0);
    Assert.assertEquals(7.0,TestVorfuehrung.getGrundpreis(),1.0);
  }

  @Test
  public void setGesamtpreis() {
    TestVorfuehrung.setGrundpreis(7.0);
    Assert.assertEquals(11.0,TestVorfuehrung.getGesamtpreis(),1.0);
  }

  @Test
  public void getGesamtpreis() {

  }

  @Test
  public void getVorführungsID() {
  }

  @Test
  public void setVorführungsID() {
  }

  @Test
  public void getZeitpunkt() {
  }

  @Test
  public void setZeitpunkt() {
  }

  @Test
  public void isDreiD() {
  }

  @Test
  public void setDreiD() {
  }

  @Test
  public void getWerbungen() {
  }

  @Test
  public void setWerbungen() {
  }

  @Test
  public void addWerbung() {
  }

  @Test
  public void removeWerbung() {
  }

  @Test
  public void getFreieSitze() {
      Assert.assertEquals( TestSitze,TestVorfuehrung.getFreieSitze() );
  }

  @Test
  public void getBelegteSitze() {
      Assert.assertEquals( null,TestVorfuehrung.getBelegteSitze() );
  }

  @Test
  public void setFreieSitze() {
  }

  @Test
  public void sitzeFrei() {
  }

  @Test
  public void bucheSitze() {
  }

  @Test
  public void getAnzahlFreieSitze() {
  }

  @Test
  public void equals() {
  }

  @Test
  public void set() {
  }
}