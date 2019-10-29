package arthur;


import java.util.ArrayList;
import backend.classes.Sitz;
import backend.classes.Werbung;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import backend.classes.*;

public class VorführungTest {
  ArrayList<Sitz> TestSitze = new ArrayList<Sitz>();
  ArrayList<Werbung> TestWerbung = new ArrayList<Werbung>();
  Vorführung TestVorfuehrung = new Vorführung("1","7",90, 5L, 110L, "15:00", true, TestWerbung, TestSitze);
  Kinosaal TestSaal1 = new Kinosaal("1", 50, true, TestSitze);
  Kinosaal TestSaal2 = new Kinosaal("2", 50, true, TestSitze);
  ArrayList<Kinosaal> TestSaelle = new ArrayList<Kinosaal>();
  Kino TestKino = new Kino(1, "KiNOW", "Paderborn", null, TestSaelle);
  @Before
  public void main() {
    for (int i = 0; i < 49; i++) {
      TestSitze.add(new Sitz("" + (i + 1), false, false));
    }
  }

  @Test
  public void getSaalnummer() {
    Assert.assertEquals("1",TestVorfuehrung.getSaalnummer());
  }

  @Test
  public void setSaalnummer() {
    TestVorfuehrung.setSaalnummer("2");
    Assert.assertNotEquals("2",TestVorfuehrung.getSaalnummer());
  }

  @Test
  public void getKinoID() {
    Assert.assertNotEquals("1",TestVorfuehrung.getKinoID());

  }

  @Test
  public void getGrunddauer() {
    Assert.assertEquals(90L,TestVorfuehrung.getGrunddauer());
  }

  @Test
  public void setGrunddauer() {
    TestVorfuehrung.setGrunddauer(50L);
    Assert.assertEquals(50L,TestVorfuehrung.getGrunddauer());
  }

  @Test
  public void setGrundpreis() {
    TestVorfuehrung.setGrundpreis(7L);
    Assert.assertEquals(7L,TestVorfuehrung.getGesamtpreis());
  }

  @Test
  public void setGesamtpreis() {
    TestVorfuehrung.setGesamtpreis();
    Assert.assertEquals(100L,TestVorfuehrung.getGesamtpreis());
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