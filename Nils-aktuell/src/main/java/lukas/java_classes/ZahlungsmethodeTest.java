package main.java.lukas.java_classes;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ZahlungsmethodeTest {
    Zahlungsmethode testZahlungsMethode1 = new Zahlungsmethode(1,"Test_Methode");
    Zahlungsmethode testZahlungsMethode2 = new Zahlungsmethode(1,"Test_Methode");

    @Test
    public void getMethodeID() { Assert.assertEquals(1,testZahlungsMethode1.getMethodeID()); }

    @Test
    public void getMethode() { Assert.assertEquals("Test_Methode",testZahlungsMethode1.getMethode());
    }

    @Test
    public void equals() {
        Assert.assertEquals(testZahlungsMethode1,testZahlungsMethode2);
    }
}