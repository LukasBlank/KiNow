package lukas.java_classes;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;


public class DarstellerTest {

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
  public void getDarstellerID() {
    Assert.assertEquals(1,Kyle.getDarstellerID());
  }

  @Test
  public void getVorname() {
    Assert.assertEquals("Kyle",Kyle.getVorname());
  }

  @Test
  public void getNachname() {
    Assert.assertEquals("Browslowski",Kyle.getNachname());
  }

  @Test
  public void getFilme() {
    Assert.assertEquals(TestFilme,Kyle.getFilme());
  }

  @Test
  public void addFilm() {
    Film TestFilm3 = new Film(3,"Fast5",null,"Ein Film über Die Antwoord,",110,18,5,null,TestDarsteller);
    Assert.assertEquals(TestFilme,Kyle.getFilme());
    Kyle.addFilm(TestFilm3);
    Assert.assertEquals(TestFilme,Kyle.getFilme());
    Assert.assertEquals(TestFilme.get(2),TestFilm3);

  }

  @Test
  public void equals() {
    Assert.assertEquals(true,Kyle.equals(Kyle));
    Assert.assertEquals(false,Kyle.equals(new Darsteller(2,null,null,null)));
  }
}
