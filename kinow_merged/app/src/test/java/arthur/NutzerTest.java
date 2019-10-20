package arthur;

import java.util.ArrayList;

import backend.classes.Bestellung;
import backend.classes.Nutzer;
import org.junit.Assert;
import org.junit.Test;

public class NutzerTest {
  ArrayList<String> payments = new ArrayList<String>();
  ArrayList<Bestellung> bestellungen = new ArrayList<Bestellung>();
  Nutzer Ben = new Nutzer("1", "Mail", "Ben", "Benutzer","Bday", "Diverse","123456", payments, bestellungen);

  @Test
  public void getNutzerID() {
    Assert.assertEquals("1",Ben.getNutzerID());
  }

  @Test
  public void setNutzerID() {
    Ben.setNutzerID("3");
    Assert.assertEquals("3",Ben.getNutzerID());
  }

  @Test
  public void getEmail() {
    Assert.assertEquals("Mail",Ben.getEmail());
  }

  @Test
  public void setEmail() {
    Ben.setEmail("EMail");
    Assert.assertEquals("EMail",Ben.getEmail());
  }

  @Test
  public void getVorname() {
    Assert.assertEquals("Ben",Ben.getVorname());
  }

  @Test
  public void setVorname() {
    Ben.setVorname("Henri");
    Assert.assertEquals("Henri",Ben.getVorname());
  }

  @Test
  public void getNachname() {
    Assert.assertEquals("Benutzer",Ben.getNachname());
  }

  @Test
  public void setNachname() {
    Ben.setNachname("Neubert");
    Assert.assertEquals("Neubert",Ben.getNachname());
  }

  @Test
  public void getGeschlecht() {
    Assert.assertEquals("Diverse",Ben.getGeschlecht());
  }

  @Test
  public void setGeschlecht() {
    Ben.setGeschlecht("Xenomorph");
    Assert.assertEquals("Xenomorph",Ben.getGeschlecht());
  }

  @Test
  public void getGeburtstag() {
    Assert.assertEquals("Bday",Ben.getGeburtstag());
  }

  @Test
  public void setGeburtstag() {
    Ben.setGeburtstag("Test");
    Assert.assertEquals("Test",Ben.getGeburtstag());
  }

  @Test
  public void getPasswort() {
    Assert.assertEquals("123456",Ben.getPasswort());
  }

  @Test
  public void setPasswort() {
    Ben.setPasswort("3");
    Assert.assertEquals("3",Ben.getPasswort());
  }

  @Test
  public void getZahlungsmethoden() {
    Assert.assertEquals(payments,Ben.getZahlungsmethoden());
  }

  @Test
  public void setZahlungsmethoden() {
    ArrayList<String> payments2 = new ArrayList<String>();
    Ben.setZahlungsmethoden(payments2);
    Assert.assertEquals(payments2,Ben.getZahlungsmethoden());
  }

  @Test
  public void getBestellungen() {
    Assert.assertEquals(bestellungen,Ben.getBestellungen());
  }

  @Test
  public void setBestellungen() {
    ArrayList<Bestellung> bestellungs = new ArrayList<Bestellung>();
    Ben.setBestellungen(bestellungs);
    Assert.assertEquals(bestellungs,Ben.getBestellungen());
  }

  /**
  @Test
  public void getBestellung() {
    ArrayList<Buchung> buchungen = new ArrayList<Buchung>();
    Bestellung bestellungTest = new Bestellung(1, Ben, buchungen);
    Ben.addBestellung(bestellungTest);
    Assert.assertEquals(bestellungTest,Ben.getBestellung(1));
  }

  @Test
  public void addBestellung() {
    ArrayList<Buchung> buchungen = new ArrayList<Buchung>();
    Bestellung bestellungTest = new Bestellung(1, Ben, buchungen);
    Ben.addBestellung(bestellungTest);
    Assert.assertEquals(bestellungTest,Ben.getBestellung(1));
  }
   **/

  @Test
  public void equals() {
    Assert.assertEquals(true,Ben.equals(Ben));
    Assert.assertEquals(false,Ben.equals(new Nutzer("3", "Mail", "Ben", "Benutzer","Bday", "Diverse","123456", payments, bestellungen)));
  }

  @Test
  public void set() {
    Ben.set("nutzerID",3);
    Ben.set("vorname", "Henri");
    Ben.set("nachname","Neubert");
    Ben.set("geschlecht","Xenomorph");
    Ben.set("geburtstag","Test");
    Ben.set("passwort","3");
    Ben.set("email","EMail");
    ArrayList<String> methoden = new ArrayList<String>();
    Ben.set("zahlungsmethoden",methoden);
    Assert.assertEquals(3,Ben.getNutzerID());
    Assert.assertEquals("Henri",Ben.getVorname());
    Assert.assertEquals("Neubert",Ben.getNachname());
    Assert.assertEquals("Xenomorph",Ben.getGeschlecht());
    Assert.assertEquals("Test",Ben.getGeburtstag());
    Assert.assertEquals("3",Ben.getPasswort());
    Assert.assertEquals("EMail",Ben.getEmail());
    Assert.assertEquals(methoden,Ben.getZahlungsmethoden());

  }

  @Test
  public void hashPasswordWithRSA() {
    Assert.assertEquals(Ben.hashPasswordWithRSA(Ben.getPasswort()),Ben.hashPasswordWithRSA(Ben.getPasswort()));
    Assert.assertNotEquals(Ben.getPasswort(),Ben.hashPasswordWithRSA(Ben.getPasswort()));
  }
}