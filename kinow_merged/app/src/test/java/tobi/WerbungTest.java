package tobi;

import backend.classes.Werbung;
import org.junit.Assert;
import org.junit.Test;


import static org.junit.Assert.*;

public class WerbungTest {
    Werbung testWerbung = new Werbung("1",1,"inhalt");

    @Test
    public void getWerbungID() {
        Assert.assertEquals("1",testWerbung.getWerbungID());
    }

    @Test
    public void setWerbungID(){
        testWerbung.setWerbungID("testID");
        assertTrue(testWerbung.getWerbungID().equals("testID"));
    }
    @Test
    public void getDauer() {
        Assert.assertEquals(1,testWerbung.getDauer());
    }

    @Test
    public void setDauer(){
        testWerbung.setDauer(100);
        assertTrue(testWerbung.getDauer() == 100);
    }
    @Test
    public void getName() {
        Assert.assertEquals("inhalt",testWerbung.getName());
    }

    @Test
    public void setName() {
        testWerbung.setName("testName");
        assertTrue(testWerbung.getName().equals("testName"));
    }

    @Test
    public void equals() {
        Assert.assertEquals(true,testWerbung.equals(testWerbung));
        Assert.assertEquals(false,testWerbung.equals(new Werbung("2",2,"anderer Inhalt")));
    }
}

