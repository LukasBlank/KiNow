package lukas.junit_tests;

import lukas.java_classes.Werbung;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class WerbungTest {
    Werbung testWerbung = new Werbung(1,1,"inhalt");

    @Test
    public void getWerbungID() {
        Assert.assertEquals(1,testWerbung.getWerbungID());
    }

    @Test
    public void getDauer() {
        Assert.assertEquals(1,testWerbung.getDauer());
    }

    @Test
    public void getInhalt() {
        Assert.assertEquals("inhalt",testWerbung.getInhalt());
    }

    @Test
    public void equals() {
        Assert.assertEquals(true,testWerbung.equals(testWerbung));
        Assert.assertEquals(false,testWerbung.equals(new Werbung(2,2,"anderer Inhalt")));
    }
}