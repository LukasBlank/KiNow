package tobi;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import backend.classes.Buchung;
import backend.classes.Preisvariation;
import backend.classes.Sitz;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNull;

public class BuchungTest {

      public ArrayList<Sitz> testSitz1 = new ArrayList();//keine Loge
          Sitz sitz1 = new Sitz("1",false,false);
          //testSitz1.add(sitz1);

          public ArrayList<Sitz> testSitz2 = new ArrayList();//mit Loge
          Sitz sitz2 = new Sitz("1",true,false);
          //testSitz2.add(sitz2);

          //preisvariation1
          ArrayList<Preisvariation> testPreisvariation1 = new ArrayList<Preisvariation>();
          Preisvariation preisvariation1 = new Preisvariation("Preisvar",1,1);
          //testPreisvariation1.add(preisvariation1);
          //preisvariation1

          ArrayList<Preisvariation> testPreisvariation2 = new ArrayList<Preisvariation>();
          Preisvariation preisvariation2 = new Preisvariation("Preisvar2",2,1);
          //testPreisvariation2.add(preisvariation2);


          Buchung testBuchung = new Buchung(1.0,"title",2,"vorführungsID","buchungID",testSitz1,testPreisvariation1);

          @Test
          public void getVorführungspreis(){
              Assert.assertEquals( 2.0,testBuchung.getVorführungspreis(),0 );

              //Assert.assertEquals(2,testBuchung.getVorführungspreis());
          }

          @Test
          public void setVorführungspreis(){
              testBuchung.setVorführungspreis(22d );
              assertTrue(testBuchung.getVorführungspreis() == 22d);
          }

          @Test
          public void getVorführungsID(){
              Assert.assertEquals("vorführungsID",testBuchung.getVorführungsID());

          }

          @Test
          public void setVorführungsID(){
              testBuchung.setVorführungsID("vorführungsID2");
              assertTrue(testBuchung.getVorführungsID().equals("vorführungsID2"));

          }

          @Test
          public void getBuchungsID(){
              Assert.assertEquals("buchungID",testBuchung.getBuchungID());

          }

          @Test
          public void setBuchungsID(){
              testBuchung.setBuchungID("buchungID2");
              assertTrue(testBuchung.getBuchungID().equals("buchungID2"));

          }

          @Test
          public void getSitze(){
              Assert.assertEquals(testSitz1,testBuchung.getSitze());
          }

          @Test
          public void setSitze(){
              testBuchung.setSitze(testSitz2);
              Assert.assertEquals(testSitz2,testBuchung.getSitze());
          }

          @Test
          public void getPreisvariation(){
              Assert.assertEquals(testPreisvariation1,testBuchung.getPreisvariationen());

          }

          @Test
          public void setPreisvariation(){
              testBuchung.setPreisvariationen(testPreisvariation2);
              Assert.assertEquals(testPreisvariation2,testBuchung.getPreisvariationen());

          }

          @Test
          public void getBuchungspreis(){
              //Nochmal testen
              Assert.assertEquals(0.0,testBuchung.getBuchungspreis(),0);
          }

          @Test
          public void setBuchungspreis(){
             /* //Buchung ohne Loge und ohne Preisvariation
              Buchung testBuchung1 = new Buchung(1.0,2,"ID","buchungID",testSitz1,null);
              testBuchung1.setBuchungspreis();
              //Buchung mit Loge und ohne Preisvariation
              Buchung testBuchung2 = new Buchung(1.0,2,"vorführungsID3","buchungID3",testSitz2,null);
              testBuchung2.setBuchungspreis();
              //Buchung mit Loge und mit Preisvariation
              Buchung testBuchung3 =  new Buchung(1.0,2,"vorführungsID","buchungID",testSitz2,testPreisvariation1);
              testBuchung3.setBuchungspreis();
              //Buchung wenn kein Sitz oder kein Vorführungspreis existiert

              //Test ohne Loge und ohne Preisvariation
              if(testBuchung1.getVorführungspreis()!=0 && testBuchung1.getSitze()!= null){
                  Assert.assertEquals(3,testBuchung1.getVorführungspreis(),0);
              }
              //Test mit Loge und ohne Preisvariation
              if(testBuchung2.getVorführungspreis()!=0 && testBuchung2.getSitze()!= null){
                  Assert.assertEquals(5,testBuchung2.getBuchungspreis(),0);
              }

              //Test mit Loge und mit Preisvariation
              if(testBuchung3.getVorführungspreis()!=0 && testBuchung3.getSitze() != null && testBuchung3.getPreisvariationen() != null){
                  Assert.assertEquals(6, testBuchung3.getBuchungspreis(),0);

              }

              //Test wenn kein Sitz oder kein Vorführungspreis existiert
              if(testBuchung1.getVorführungspreis() == 0 || testBuchung1.getSitze() == null){
                  assertNull(testBuchung1.getBuchungspreis());
              }
            */
          }

          @Test
          public void getBestellungsnummer(){
              /*//BuchungsID.length <=0
              Buchung testbuchung1 = new Buchung(1,1,"vorführungsID","",null,null);
              assertNull(testbuchung1.getBestellungsnummer());
              //Buchung mit falscher indizierung der BuchungsID
              Buchung testbuchung2 = new Buchung(1,1,"vorführungsID","notwendiges Zeichen taucht nicht auf",null,null);
              assertNull(testbuchung2.getBestellungsnummer());
              //die if.Bedingung wird erfüllt
              Buchung testbuchung3 = new Buchung(1,1,"vorführungsID","_BuchungID_",null,null);
              assertTrue(testbuchung3.getBestellungsnummer().equals(testbuchung3.getBestellungsnummer()));
            */
          }

          @Test
          public void getNutzerID(){
             /* //BuchungsID.length <=0
              Buchung testbuchung1 = new Buchung(1,"vorführungsID",1,null,null);
              assertNull(testbuchung1.getBestellungsnummer());
              //Buchung mit falscher indizierung der BuchungsID
              Buchung testbuchung2 = new Buchung(1,1,"vorführungsID","notwendiges Zeichen taucht nicht auf",null,null);
              assertNull(testbuchung2.getBestellungsnummer());
              //die if.Bedingung wird erfüllt
              Buchung testbuchung3 = new Buchung(1,1,"vorführungsID","_BuchungID_",null,null);
              assertTrue(testbuchung3.getNutzerID().equals(testbuchung3.getNutzerID()));

            */
          }

          @Test
          public void equals(){
            /*  Assert.assertEquals(true,testBuchung.equals(testBuchung));
              Assert.assertEquals(false,testBuchung.equals(new Buchung(1,2,"vorführungsID","buchungID2",testSitz1,testPreisvariation1)));
          */
          }




}
