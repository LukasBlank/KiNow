package lukas.java_classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

public class BuchungTest {
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
  public void getBuchungID() {
    Assert.assertEquals(1,testBuchung.getBuchungID());
  }

  @Test
  public void getVorführung() {
    Assert.assertEquals(TestVorfuehrung,testBuchung.getVorführung());
  }

  @Test
  public void getBestellung() {
    Assert.assertEquals(testBestellung,testBuchung.getBestellung() );
  }

  @Test
  public void getSitze() {
    Assert.assertEquals(TestSitze,testBuchung.getSitze());
  }

  @Test
  public void getBuchungspreis() {
    Assert.assertEquals(7,testBuchung.getBuchungspreis());
  }

  @Test
  public void equals() {
    Assert.assertEquals(true,testBuchung.equals(testBuchung));
    Assert.assertEquals(false,testBuchung.equals(new Buchung(2,TestVorfuehrung,testBestellung,TestSitze,null)));
  }
}
