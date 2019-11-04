package arthur;

import backend.classes.Sitz;
import org.junit.Assert;
import org.junit.Test;

public class SitzTest {
  Sitz sitzTest = new Sitz("1",true,true);

  @Test
  public void getSitzID() {
    Assert.assertEquals("1",sitzTest.getSitzID());
  }

  @Test
  public void setSitzID() {
    sitzTest.setSitzID( "A" );
    Assert.assertEquals( "A",sitzTest.getSitzID() );
  }

  @Test
  public void isLoge() {
    Assert.assertEquals( true,sitzTest.isLoge() );
  }

  @Test
  public void setLoge() {
    sitzTest.setLoge( false );
    Assert.assertEquals( false,sitzTest.isLoge() );
  }

  @Test
  public void isBarrierefrei() {
    Assert.assertEquals( true,sitzTest.isBarrierefrei() );
  }

  @Test
  public void setBarrierefrei() {
    sitzTest.setBarrierefrei( false );
    Assert.assertEquals( false,sitzTest.isBarrierefrei() );
  }

  @Test
  public void getKinoID() {

  }

  @Test
  public void getSaalnummer() {
  }

  @Test
  public void equals() {
  }

  @Test
  public void set() {
  }
}