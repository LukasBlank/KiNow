package lukas.java_classes;

import org.junit.Assert;
import org.junit.Test;

public class ZahlungsmethodeTest {
    Zahlungsmethode testZahlungsMethode = new Zahlungsmethode(1,"Test_Methode");

    @Test
    public void getMethodeID() { Assert.assertEquals(1, testZahlungsMethode.getMethodeID()); }

    @Test
    public void getMethode() { Assert.assertEquals("Test_Methode", testZahlungsMethode.getMethode());
    }

    @Test
    public void equals(Zahlungsmethode zahlungsmethode) {
        Assert.assertEquals(true,testZahlungsMethode.equals(testZahlungsMethode));
        Assert.assertEquals(false,testZahlungsMethode.equals(new Zahlungsmethode(2,"andere Methode")));
    }
}