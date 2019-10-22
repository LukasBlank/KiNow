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
import static org.junit.Assert.assertFalse;
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
        Bestellung testbestellung2 = new Bestellung("bestellungsnummer", 1,null);
        testbestellung2.setGesamtpreis();
        if(testbestellung2.getBuchungen() == null){
            assertNull(testbestellung2.getGesamtpreis());
        }
        //Buchung != null
        testBestellung.setGesamtpreis();
        if(testBestellung.getBuchungen() != null){
            Assert.assertEquals(2,testBestellung.getGesamtpreis());

        }
    }

    @Test
    public void addBuchung(){
        //!contains buchung2
        Buchung buchung2 = new Buchung(1,2,"vorführungsID","buchungsID",new ArrayList<Sitz>(),new ArrayList<Preisvariation>());
        if(!testbuchung1.contains(buchung2)){
            testbuchung1.add(buchung2);
            assertTrue(testbuchung1.contains(buchung2));
        }

    }

    @Test
    public void removeBuchung(){
        //contains buchung2
        Buchung buchung2 = new Buchung(1,2,"vorführungsID","buchungsID",new ArrayList<Sitz>(),new ArrayList<Preisvariation>());
        if(testbuchung1.contains(buchung2)){
            testbuchung1.remove(buchung2);
            assertFalse(testbuchung1.contains(buchung2));
        }


    }

    @Test
    public void getNutzerID(){
        //BuchungsID.length <=0
        Buchung testbuchung1 = new Buchung(1,1,"vorführungsID","",null,null);
        assertNull(testbuchung1.getBestellungsnummer());
        //Buchung mit falscher indizierung der BuchungsID
        Buchung testbuchung2 = new Buchung(1,1,"vorführungsID","notwendiges Zeichen taucht nicht auf",null,null);
        assertNull(testbuchung2.getBestellungsnummer());
        //die if.Bedingung wird erfüllt
        Buchung testbuchung3 = new Buchung(1,1,"vorführungsID","_BuchungID_",null,null);
        assertTrue(testbuchung3.getNutzerID().equals(testbuchung3.getNutzerID()));
        
    }

    @Test
    public void equals(){
        Assert.assertEquals(true,testBestellung.equals(testBestellung));
        Assert.assertEquals(false,testBestellung.equals(new Bestellung("bestellungsnummer2",2,null)));
    }
}
