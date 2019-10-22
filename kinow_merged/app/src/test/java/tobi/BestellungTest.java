package tobi;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import backend.classes.Bestellung;
import backend.classes.Buchung;
import backend.classes.Preisvariation;
import backend.classes.Sitz;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNull;

public class BestellungTest {
    ArrayList<Buchung> testbuchung1 = new ArrayList<Buchung>();
    Buchung buchung1 = new Buchung(1,2,"vorführungsID","buchungsID",new ArrayList<Sitz>(),new ArrayList<Preisvariation>());
    //testbuchung1.add(buchung1);

    Bestellung testBestellung = new Bestellung("bestellungsnummer", 1,testbuchung1);

    @Test
    public void getBestellungsnummer(){
        Assert.assertEquals("bestellungsnummer",testBestellung.getBesetellungsnummer());
    }

    @Test
    public void getGesamtpreis(){
        Assert.assertEquals(1,testBestellung.getGesamtpreis());

    }

    @Test
    public void getBuchungen(){
        Assert.assertEquals(testbuchung1,testBestellung.getBuchungen());
    }

    @Test
    public void setBestellungsnummer(){
        testBestellung.setBesetellungsnummer("bestellungsnummer1");
        assertTrue(testBestellung.getBesetellungsnummer().equals("bestellungsnummer1"));

    }

    @Test
    public void setBuchungen(){
        ArrayList<Buchung> testBuchung2 = new ArrayList<>();
        Buchung buchung2 =new Buchung (1,2,"vorführungsID2","buchungsID",null,null);
        testBestellung.setBuchungen(testBuchung2);
        Assert.assertEquals(testBuchung2, testBestellung.getBuchungen());
    }

    @Test
    public void setGesamtpreis(){
        //Buchung == null
        //Buchung != null
    }

    @Test
    public void addBuchung(){

    }

    @Test
    public void removeBuchung(){

    }

    @Test
    public void getNutzerID(){

    }

    @Test
    public void equals(){
        Assert.assertEquals(true,testBestellung.equals(testBestellung));
        Assert.assertEquals(false,testBestellung.equals(new Bestellung("bestellungsnummer2",2,null)));
    }
}
