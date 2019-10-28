package server;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;

import java.util.*;
import java.util.concurrent.ExecutionException;

import javax.print.Doc;
import lukas.classes.Bestellung;
import lukas.classes.Buchung;
import lukas.classes.Nutzer;
import lukas.classes.Sitz;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class DemoApplication {

  static Firestore db;

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
    try {
      //Pfad muss angepasst werden ggf. in Java einfügen

      String path = "serviceAccountKey.json";
      URL url = DemoApplication.class.getClassLoader().getResource(path);

      //Datenbankverbindung erstellen
      FileInputStream serviceAccount =
          new FileInputStream(url.getPath());//Wenn über Server: path // Wenn lokal : url.getPath() und oben einkommentieren

      FirebaseOptions options = new FirebaseOptions.Builder()
          .setCredentials(GoogleCredentials.fromStream(serviceAccount))
          .setDatabaseUrl("https://kinow-46514.firebaseio.com")
          .build();

      FirebaseApp.initializeApp(options);
    } catch (Exception e) {
      e.printStackTrace();
    }//catch
    db = FirestoreClient.getFirestore();
    SimpleController sc = new SimpleController();
    ResponseEntity<Object> re = sc.buchen("1");
  }//main

  @RestController
  public static class SimpleController {

    @RequestMapping (value = "/server")
    public String server (){
       return "Hallo.";
    }//lol

    @RequestMapping(value = "/getFilme")
    public ResponseEntity<Object> getFilme (@RequestHeader("kinoID") String kinoID){
      //alle filme für Kino mit bestimmter ID holen, ggf. alle Filme holen
      ApiFuture<QuerySnapshot> query;
      if (kinoID.equals("0")) query = db.collection("Filme").get();
      else {
        query = db.collection("Kino").document(kinoID).collection("spieltFilme").get();
      }//else
      //Filme holen
      Map<String,Map<String,Object>> map = getMapQuerySnapshot(query);
      return new ResponseEntity<>(map,HttpStatus.ACCEPTED);
    }//getFilme

    private Map<String,Map<String,Object>> getMapQuerySnapshot (ApiFuture<QuerySnapshot> query){
      Map<String,Map<String,Object>> map = new HashMap<>();
      try {
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (DocumentSnapshot documnt : documents){
          map.put(documnt.getId(),documnt.getData());
        }//for
      }//try
      catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }//catch
      return map;
    }//getMapQuerySnapshot

    @RequestMapping(value = "/getKinos")
    public ResponseEntity<Object> getKinos (){
      ApiFuture<QuerySnapshot> query = db.collection("Kino").get();
      Map<String,Map<String,Object>> map = getMapQuerySnapshot(query);
      return new ResponseEntity<>(map,HttpStatus.ACCEPTED);
    }//getKinos

    @RequestMapping(value = "/addNutzer")
    public ResponseEntity<Object> addNutzer(@RequestHeader("nutzer")String nutzer){
      //String zuverlässig zu einer Map parsen
      String erg = "Success";
      try {
        Map<String,Object> map = new ObjectMapper().readValue(nutzer,Map.class);
        //prüfen, ob Email bereits für einen Nutzer verwendet wurde
        String email = map.get("email").toString();
        ApiFuture<QuerySnapshot> query = db.collection("Nutzer").get();
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (DocumentSnapshot document : documents){
            if(!document.getId().equals("0")){
              String e = document.get("email").toString();
              if (e.equals(email))erg = "Error";
            }//then
        }//for
        if (erg.equals("Success")){
          long id = documents.size();
          map.put("nutzerID",id);
          db.collection("Nutzer").document("" + id).set(map);
        }//then
      } catch (IOException e) {
        e.printStackTrace();
        erg = "Error.";
      } catch (InterruptedException e) {
        e.printStackTrace();
        erg = "Error.";
      } catch (ExecutionException e) {
        e.printStackTrace();
        erg = "Error.";
      }//catch
      return new ResponseEntity<>(erg,HttpStatus.ACCEPTED);
    }//addNutzer

    @RequestMapping(value = "/LogIn")
    public ResponseEntity<Object> LogIn(@RequestHeader("email") String email, @RequestHeader("passwort") String passwort){
      Query query = db.collection("Nutzer").whereEqualTo("email",email);
      Map<String,Map<String,Object>> map = new HashMap<>();
      try {
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        QuerySnapshot q = querySnapshot.get();
        List<QueryDocumentSnapshot> documents = q.getDocuments();
        if (documents.size()==1){
          DocumentSnapshot document = documents.get(0);
          if (document.get("passwort").equals(passwort)){
            map.put(document.getId(),document.getData());
          }//then
          else map = new HashMap<>();
        }//then
        else map = new HashMap<>();
        }//try
        catch (InterruptedException e) {
          e.printStackTrace();
          map = new HashMap<>();
        } catch (ExecutionException e) {
          e.printStackTrace();
          map = new HashMap<>();
        }//catch
      return new ResponseEntity<>(map,HttpStatus.ACCEPTED);
    }//LogIn

    @RequestMapping(value = "/getVor")
    public ResponseEntity<Object> getVor(@RequestHeader("kinoID") String kinoID , @RequestHeader("filmID") String filmID){
      ApiFuture<QuerySnapshot> query = db.collection("Kino").document(kinoID)
          .collection("spieltFilme").document(filmID).collection("Vorstellungen").get();
      Map<String,Map<String,Object>> map = getMapQuerySnapshot(query);
      return new ResponseEntity<>(map,HttpStatus.ACCEPTED);
    }//getVor

    @RequestMapping(value = "/getFrei")
    public ResponseEntity<Object> getFrei(@RequestHeader("vorfuehrungsID") String vorführungsID){
      String kinoID = vorführungsID.substring(0,vorführungsID.indexOf('_'));
      String filmID = vorführungsID.substring(vorführungsID.indexOf('_')+1);
      filmID = filmID.substring(filmID.indexOf('_')+1);
      filmID = filmID.substring(0,filmID.indexOf('_'));
      //Zunächst die freien Sitze in die Map einfügen
      ApiFuture<QuerySnapshot> query = db.collection("Kino").document(kinoID)
          .collection("spieltFilme").document(filmID)
          .collection("Vorstellungen").document(vorführungsID)
          .collection("FreieSitze").get();
      Map<String,Map<String,Object>> map = getMapQuerySnapshot(query);
      return new ResponseEntity<>(map,HttpStatus.ACCEPTED);
    }//getFrei

    @RequestMapping(value = "/getBelegt")
    public ResponseEntity<Object> getSitze(@RequestHeader("vorfuehrungsID") String vorführungsID){
      String kinoID = vorführungsID.substring(0,vorführungsID.indexOf('_'));
      String filmID = vorführungsID.substring(vorführungsID.indexOf('_')+1);
      filmID = filmID.substring(filmID.indexOf('_')+1);
      filmID = filmID.substring(0,filmID.indexOf('_'));
      //Zunächst die freien Sitze in die Map einfügen
      ApiFuture<QuerySnapshot> query = db.collection("Kino").document(kinoID)
          .collection("spieltFilme").document(filmID)
          .collection("Vorstellungen").document(vorführungsID)
          .collection("BelegteSitze").get();
      Map<String,Map<String,Object>> map = getMapQuerySnapshot(query);
      return new ResponseEntity<>(map,HttpStatus.ACCEPTED);
    }//getBelegt

    @RequestMapping(value = "/getReservierte")
    public ResponseEntity<Object> getReservierte(@RequestHeader("vorfuehrungsID") String vorführungsID){
      String kinoID = vorführungsID.substring(0,vorführungsID.indexOf('_'));
      String filmID = vorführungsID.substring(vorführungsID.indexOf('_')+1);
      filmID = filmID.substring(filmID.indexOf('_')+1);
      filmID = filmID.substring(0,filmID.indexOf('_'));
      //Zunächst die freien Sitze in die Map einfügen
      ApiFuture<QuerySnapshot> query = db.collection("Kino").document(kinoID)
          .collection("spieltFilme").document(filmID)
          .collection("Vorstellungen").document(vorführungsID)
          .collection("ReservierteSitze").get();
      Map<String,Map<String,Object>> map = getMapQuerySnapshot(query);
      return new ResponseEntity<>(map,HttpStatus.ACCEPTED);
    }//getBelegt

    @RequestMapping(value = "/getBestellungen")
    public ResponseEntity<Object> getBestellungen(@RequestHeader("nutzerID") String nutzerID){
      Map<String,Map<String,Object>> map = new HashMap<>();
      if (Long.parseLong(nutzerID)>=1){
        ApiFuture<QuerySnapshot> query = db.collection("Nutzer").document(nutzerID)
            .collection("Bestellungen").get();
        map = getMapQuerySnapshot(query);
      }//then
      return new ResponseEntity<>(map,HttpStatus.ACCEPTED);
    }//getBestellungen

    @RequestMapping(value = "/getReservierungen")
    public ResponseEntity<Object> getReservierungen(@RequestHeader("nutzerID") String nutzerID){
      Map<String,Map<String,Object>> map = new HashMap<>();
      if (Long.parseLong(nutzerID)>=1){
        ApiFuture<QuerySnapshot> query = db.collection("Nutzer").document(nutzerID)
            .collection("Reservierungen").get();
        map = getMapQuerySnapshot(query);
      }//then
      return new ResponseEntity<>(map,HttpStatus.ACCEPTED);
    }//getBestellungen

    @RequestMapping(value = "/getResSitze")
    public ResponseEntity<Object> getResSitze(@RequestHeader("reservierungsID") String reservierungsID){
      Map<String,Map<String,Object>> map = new HashMap<>();
      if (reservierungsID.length()!=0){
        String nutzerID = reservierungsID.substring(0,reservierungsID.indexOf('_'));
        ApiFuture<QuerySnapshot> query = db.collection("Nutzer").document(nutzerID)
            .collection("Reservierungen").document(reservierungsID)
            .collection("Sitze").get();
        map = getMapQuerySnapshot(query);
      }//then
      return new ResponseEntity<>(map,HttpStatus.ACCEPTED);
    }//getResSitze

    @RequestMapping(value = "/reservieren")
    public ResponseEntity<Object> reservieren(@RequestHeader("sitze") String sitze, @RequestHeader("nutzer") String nutzerID){
      String erg = "Success";
      long id = Long.parseLong(nutzerID);
      if(id<1)erg = "Error.";
      else {
        ApiFuture<DocumentSnapshot> query = db.collection("Nutzer").document(nutzerID).get();
        DocumentSnapshot doc = null;
        try {
          doc = query.get();
          if (doc.exists()){
            //Header zu ArrayListe von Sitzen umwandeln
            Map<String,Map<String,Object>> map = new ObjectMapper().readValue(sitze,Map.class);
            ArrayList<Sitz> sitzListe = mapToSitze(map);
            //prüfen ob auch sitze mitgegeben wurden
            if (sitzListe.size()>0){
              Sitz tmp = sitzListe.get(0);
              DocumentReference vorführungRef = db.collection("Kino").document(tmp.getKinoID()).collection("spieltFilme")
                  .document(tmp.getFilmID()).collection("Vorstellungen").document(tmp.getVorID());
              //sitze auf freiheit überprüfen
              boolean frei = sitzeFrei(sitzListe);
              if (frei){
                //sitze aus der passendne vorführung entfernen und zu reservierten sitzen packen
                for (Map.Entry<String,Map<String,Object>> en : map.entrySet()){
                  vorführungRef.collection("FreieSitze").document(en.getKey()).delete();
                  vorführungRef.collection("ReservierteSitze").document(en.getKey()).set(en.getValue());
                }//for
                //neue Reservierung unter dem passenden Nutzer vornhemen
                erg = neueReservierung(nutzerID,map,sitzListe);
              }//then
              else erg = "Error.";
            }//then
            else erg = "Error.";
          }//then
          else erg = "Error.";
        } catch (InterruptedException e) {
          e.printStackTrace();
          erg = "Error.";
        } catch (ExecutionException e) {
          e.printStackTrace();
          erg = "Error.";
        } catch (JsonParseException e) {
          e.printStackTrace();
          erg = "Error.";
        } catch (JsonMappingException e) {
          e.printStackTrace();
          erg = "Error.";
        } catch (IOException e) {
          e.printStackTrace();
          erg = "Error.";
        }//catch
      }//else
      return new ResponseEntity<>(erg,HttpStatus.ACCEPTED);
    }//reservieren

    @RequestMapping(value = "/buchen")
    public ResponseEntity<Object> buchen(@RequestHeader("nutzerID") String nutzerID){
      long id = Long.parseLong(nutzerID);
      DocumentReference nutzer = db.collection("Nutzer").document(nutzerID);
      String erg = "Success";
      //Abbruch wenn Gast oder ID falsch
      if (id<1)erg = "Error.";
      else {
        ApiFuture<DocumentSnapshot> queryDoc = nutzer.get();
        try {
          //abbruch wenn es nutzer nicht gibt
          DocumentSnapshot doc = queryDoc.get();
          if (!doc.exists())erg = "Error.";
          else {
            //wenn nutzer existiert, dann
           ArrayList<Buchung> reservierungen = getRes(nutzerID);
           if (reservierungen.size()>0){
             if (!verbucheReservierungen(nutzer,reservierungen))erg = "Error.";
           }//then
          }//else
        } catch (InterruptedException e) {
          e.printStackTrace();
          erg = "Error.";
        } catch (ExecutionException e) {
          e.printStackTrace();
          erg = "Error.";
        }//catch
      }//else
      return new ResponseEntity<>(erg,HttpStatus.ACCEPTED);
    }//buchen

    private boolean sitzeFrei (ArrayList<Sitz> sitze){
      if (sitze!=null){
        ArrayList<Sitz> test = (ArrayList<Sitz>) sitze.clone();
        Sitz s = sitze.get(0);
        ApiFuture<QuerySnapshot> query = db.collection("Kino").document(s.getKinoID())
            .collection("spieltFilme").document(s.getFilmID())
            .collection("Vorstellungen").document(s.getVorID())
            .collection("FreieSitze").get();
        try {
          QuerySnapshot querySnapshot = query.get();
          List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
          for (DocumentSnapshot document : documents){
            for (Sitz sitz : sitze){
              if (document.getId().equals(sitz.getSitzID())){
                test.remove(sitz);
                if (test.size()==0)return true;
              }//then
            }//for
          }//for
          if (test.size()>0)return false;
          else return true;
        } catch (InterruptedException e) {
          e.printStackTrace();
          return false;
        } catch (ExecutionException e) {
          e.printStackTrace();
          return false;
        }//catch
      }//then
      else return false;
    }//sitzeFrei

    private ArrayList<Sitz> mapToSitze (Map<String,Map<String, Object>> map){
      ArrayList<Sitz> sitze =  new ArrayList<>();
      for (Map.Entry<String,Map<String,Object>> e : map.entrySet()){
        Map<String,Object> sitz = e.getValue();
        Sitz tmp = new Sitz();
        for (Map.Entry<String,Object> entry : sitz.entrySet()){
          tmp.set(entry.getKey(),entry.getValue());
        }//for
        sitze.add(tmp);
      }//for
      return sitze;
    }//mapToSitze

    private String neueReservierung (String nutzerID, Map<String,Map<String,Object>> map, ArrayList<Sitz> sitze){
      Buchung buchung = new Buchung();
      try {
        //aktuelle BuchungsID holen
        CollectionReference resRef = db.collection("Nutzer").document(nutzerID).collection("Reservierungen");
        String buchungsID = RandomStringUtils.random(20,true,true);
        ApiFuture<DocumentSnapshot> test = db.collection("Nutzer").document(nutzerID).collection("Reservierungen")
            .document(buchungsID).get();
        DocumentSnapshot testDoc = test.get();
        while (testDoc.exists()){
          buchungsID = RandomStringUtils.random(20,true,true);
          testDoc = test.get();
        }//while
        buchung.setBuchungID(buchungsID);

        //vorführungspreis und sitze der buchung zuweisen
        Sitz sitz = new Sitz();
        if (sitze.size()>0)sitz = sitze.get(0);
        ApiFuture<DocumentSnapshot>qD = db.collection("Kino").document(sitz.getKinoID()).collection("spieltFilme").document(sitz.getFilmID())
            .collection("Vorstellungen").document(sitz.getVorID()).get();
        DocumentSnapshot vor = qD.get();
        if (vor.exists()){
          Double vPreis = Double.parseDouble(vor.get("gesamtpreis").toString());
          buchung.setVorführungspreis(vPreis);
          buchung.setVorführungsID(vor.get("vorführungsID").toString());
          buchung.setFilmtitel(getFilmTitel(buchung.getVorführungsID()));
        }//then
        buchung.setSitze(sitze);

        //Erstelle eine Map in welche alle Informationen gepackt werden
        Map<String,Object> buchungsMap = buchungToMap(buchung);
        //gebe zeitpunkt der reservierung mit um diese wieder umzukehren
        buchungsMap.put("zeit",System.currentTimeMillis());
        //Dem Nutzer die Reservierungsbuchung hinzufügen
        resRef.document(buchungsID).set(buchungsMap);
        //Der Buchung die Sitze hinzufügen
        for (Map.Entry<String,Map<String,Object>> e : map.entrySet()){
          resRef.document(buchungsID).collection("Sitze")
              .document(e.getKey()).set(e.getValue());
        }//for
        return "Success";
      } catch (InterruptedException e) {
        e.printStackTrace();
        return "Error.";
      } catch (ExecutionException e) {
        e.printStackTrace();
        return "Error.";
      }//catch
    }//neueReservierung

    private String getBuchungsnummer (String nutzerID, CollectionReference collectionReference){
      ApiFuture<QuerySnapshot> query = collectionReference.get();
      try {
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        String buchungsID = nutzerID + "_0_" + (documents.size()+1);
        return buchungsID;
      } catch (InterruptedException e) {
        e.printStackTrace();
        return null;
      } catch (ExecutionException e) {
        e.printStackTrace();
        return null;
      }
    }//getBuchungsnummer

    private ArrayList<Buchung> getRes (String nutzerID){
      ArrayList<Buchung> buchungen = new ArrayList<>();
      DocumentReference nutzerRef = db.collection("Nutzer").document(nutzerID);
      CollectionReference resRef = nutzerRef.collection("Reservierungen");
      try {
        ApiFuture<QuerySnapshot> queryQ = resRef.get();
        QuerySnapshot q = queryQ.get();
        List<QueryDocumentSnapshot> documents = q.getDocuments();
        if (documents.size()==0)return buchungen;
        else {
          //buchungen aus reservierungen holen
          Map<String,Object> map = new HashMap<>();
          for (DocumentSnapshot document : documents){
            map = document.getData();
            Buchung b = new Buchung();
            for (Map.Entry<String,Object> e : map.entrySet()){
              b.set(e.getKey(),e.getValue());
            }//for
            buchungen.add(b);
            //sitze für buchungen holen
            for (Buchung buchung : buchungen){
              ArrayList<Sitz> sitze = new ArrayList<>();
              queryQ = resRef.document(buchung.getBuchungID()).collection("Sitze").get();
              q = queryQ.get();
              documents = q.getDocuments();
              if (documents.size()!=0){
                for (DocumentSnapshot d : documents){
                  map = d.getData();
                  Sitz s = new Sitz();
                  for (Map.Entry<String,Object> entry : map.entrySet()){
                    s.set(entry.getKey(),entry.getValue());
                  }//for
                  sitze.add(s);
                }//for
                b.setSitze(sitze);
              }//then
            }//for
          }//for
        }//else
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }//catch
      return buchungen;
    }//getRes

    private boolean verbucheReservierungen (DocumentReference nutzer, ArrayList<Buchung> reservierungen){
      //sitze in vorführung umbuchen
      for (Buchung buchung : reservierungen){
        ArrayList<Sitz> sitze = buchung.getSitze();
        DocumentReference vorRef;
        if (sitze.size()>0){
          Sitz s = sitze.get(0);
          vorRef = db.collection("Kino").document(s.getKinoID()).collection("spieltFilme").document(s.getFilmID())
              .collection("Vorstellungen").document(s.getVorID());
          for (Sitz sitz : sitze){
            Map<String,Object> sitzMap = sitzToMap(sitz);
            vorRef.collection("ReservierteSitze").document(sitz.getSitzID()).delete();
            vorRef.collection("BelegteSitze").document(sitz.getSitzID()).set(sitzMap);
          }///for
        }//then
        else return false;
      }//for

      //reservierungen löschen
      for (Buchung buch : reservierungen){
        for (Sitz sit : buch.getSitze()){
          nutzer.collection("Reservierungen").document(buch.getBuchungID()).collection("Sitze").document(sit.getSitzID()).delete();
        }//for
        nutzer.collection("Reservierungen").document(buch.getBuchungID()).delete();
      }//for
      //reservierungen zu bestellungen machen
      Bestellung bestellung = new Bestellung();
      bestellung.setBesetellungsnummer(getBestellungsnummer(nutzer));
      bestellung.setBuchungen(reservierungen);
      //bestellung hinzufügen
      Map<String,Object> bestellungMap = new HashMap<>(); bestellungMap.put("bestellungsnummer",bestellung.getBesetellungsnummer()); bestellungMap.put("gesamtpreis",bestellung.getGesamtpreis());
      nutzer.collection("Bestellungen").document(bestellung.getBesetellungsnummer()).set(bestellungMap);
      //buchungen hinzufügen
      int i = 1;
      for (Buchung b : bestellung.getBuchungen()){
        //buchungsID updaten und buchungen hinzufügen
        String buchungsID = bestellung.getBesetellungsnummer() + "_" + i;
        b.setBuchungID(buchungsID);
        Map<String,Object> buchungMap = buchungToMap(b);
        nutzer.collection("Bestellungen").document(b.getBestellungsnummer())
            .collection("Buchungen").document(b.getBuchungID()).set(buchungMap);
        i++;
      //sitze hinzufügen
        for (Sitz s : b.getSitze()){
          Map<String,Object> sitzeMap = sitzToMap(s);
          nutzer.collection("Bestellungen").document(b.getBestellungsnummer()).collection("Buchungen")
              .document(b.getBuchungID()).collection("Sitze").document(s.getSitzID()).set(sitzeMap);
        }//for
      }//for
      return true;
    }//verbucheReservierungen

    private Map<String,Object> sitzToMap (Sitz sitz){
      if (sitz!=null){
        Map<String,Object> map = new HashMap<>();
        map.put("sitzID",sitz.getSitzID());
        map.put("barrierefrei",sitz.isBarrierefrei());
        map.put("loge",sitz.isLoge());
        return map;
      }//then
      else return null;
    }//sitzToMap

    private Map<String,Object> buchungToMap (Buchung buchung){
      if (buchung!=null && buchung.getBuchungID()!=null){
        Map<String,Object> buchungMap = new HashMap<>();
        buchungMap.put("buchungsID",buchung.getBuchungID());
        buchungMap.put("buchungspreis",buchung.getBuchungspreis());
        buchungMap.put("vorführungsID",buchung.getVorführungsID());
        if (buchung.getFilmtitel()!=null)buchungMap.put("filmtitel",buchung.getFilmtitel());
        return buchungMap;
      }///then
      else return null;
    }//buchungToMap

    private String getBestellungsnummer (DocumentReference nutzer){
      String id = "";
      ApiFuture<QuerySnapshot> q = nutzer.collection("Bestellungen").get();
      try {
        QuerySnapshot querySnapshot = q.get();
        List<QueryDocumentSnapshot> documents =  querySnapshot.getDocuments();
        id = nutzer.getId() + "_" + (documents.size()+1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }
      return id;
    }//getBestellungsnummer

    private String getFilmTitel (String vorführungsID){
      String erg = null;
      String filmID = vorführungsID.substring(vorführungsID.indexOf('_')+1);
      filmID = filmID.substring(filmID.indexOf('_')+1); filmID = filmID.substring(0,filmID.indexOf('_'));
      ApiFuture<DocumentSnapshot> docQ = db.collection("Filme").document(filmID).get();
      try {
        DocumentSnapshot document = docQ.get();
        erg = document.getString("titel");
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }
      return erg;
    }//getFilmTitel

  }//Controller
}// class
