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
      //URL url = DemoApplication.class.getClassLoader().getResource(path);

      //Datenbankverbindung erstellen
      FileInputStream serviceAccount =
          new FileInputStream(path);//Wenn über Server: path // Wenn lokal : url.getPath() und oben einkommentieren

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
  }//main

  @RestController
  public static class SimpleController {

    @RequestMapping (value = "/server")
    public String server (){
       return "Hallo.";
    }//lol

    @RequestMapping(value = "/getFilme")
    public ResponseEntity<Object> getFilme (@RequestHeader("kinoID") String SkinoID){
      //alle filme für Kino mit bestimmter ID holen, ggf. alle Filme holen
      ApiFuture<QuerySnapshot> query;
      long kinoID = Long.parseLong(SkinoID);
      if (kinoID==0) query = db.collection("Filme").get();
      else {
        query = db.collection("Kino").document((String.valueOf(kinoID))).collection("spieltFilme").get();
      }//else

      QuerySnapshot querySnapshot = null;
      List<QueryDocumentSnapshot> documents = null;
      Map<String,Map<String,Object>> map = new HashMap<>();
      try {
        querySnapshot = query.get();
        documents = querySnapshot.getDocuments();
        for ( DocumentSnapshot document : documents){
          map.put(document.getId(),document.getData());
          //maps für subcollections erstellen
        }//for
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }//catch

      return new ResponseEntity<>(map,HttpStatus.ACCEPTED);
    }//getFilme

    @RequestMapping(value = "/getKinos")
    public ResponseEntity<Object> getKinos (){
      ApiFuture<QuerySnapshot> query = db.collection("Kino").get();
      Map<String,Map<String,Object>> map = new HashMap<>();
      try {
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (DocumentSnapshot document : documents){
          map.put(document.getId(),document.getData());
        }//for
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }//catch
      return new ResponseEntity<>(map,HttpStatus.ACCEPTED);
    }//getKinos

    @RequestMapping(value = "/addNutzer")
    public ResponseEntity<Object> addNutzer(@RequestHeader("nutzer")String nutzer){
      //String zuverlässig zu einer Map parsen
      String erg = "Success";
      try {
        Map<String,Object> map = new ObjectMapper().readValue(nutzer,Map.class);
        Nutzer n = new Nutzer();
        for (Map.Entry<String,Object> e : map.entrySet()){
          n.set(e.getKey(),e.getValue());
        }//for
        String email = n.getEmail();
        ApiFuture<QuerySnapshot> query = db.collection("Nutzer").get();
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (DocumentSnapshot document : documents){
            if(!document.getId().equals("0")){
              String e = document.get("email").toString();
              if (e.equals(n.getEmail()))erg = "Error";
            }//then
        }//for
        if (erg.equals("Success")){
          long id = documents.size();
          map.put("nutzerID",id);
          db.collection("Nutzer").document("" + id).set(map);
        }//then
      } catch (IOException e) {
        e.printStackTrace();
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
      ApiFuture<QuerySnapshot> query = db.collection("Nutzer").get();
      Map<String,Map<String,Object>> map = new HashMap<>();
      try {
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (DocumentSnapshot document : documents){
          if (!document.getId().equals("0")){
            String e = document.get("email").toString();
            String p = document.get("passwort").toString();
            if (e.equals(email) && p.equals(passwort))map.put(document.getId(),document.getData());
          }//then
        }//for
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }//catch
      return new ResponseEntity<>(map,HttpStatus.ACCEPTED);
    }//LogIn

    @RequestMapping(value = "/getVor")
    public ResponseEntity<Object> getVor(@RequestHeader("kinoID") String kinoID , @RequestHeader("filmID") String filmID){
      ApiFuture<QuerySnapshot> query = db.collection("Kino").document(kinoID).collection("spieltFilme").document(filmID).collection("Vorstellungen").get();
      Map<String,Map<String,Object>> map = new HashMap<>();
      try {
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (DocumentSnapshot document : documents){
          map.put(document.getId(),document.getData());
        }//for
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }//catch
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
      Map<String,Map<String,Object>> map = new HashMap<>();
      try {
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (DocumentSnapshot document : documents){
          map.put(document.getId(),document.getData());
        }//for
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }//catch
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
      Map<String,Map<String,Object>> map = new HashMap<>();
      try {
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (DocumentSnapshot document : documents){
          map.put(document.getId(),document.getData());
        }//for
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }//catch
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
      Map<String,Map<String,Object>> map = new HashMap<>();
      try {
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (DocumentSnapshot document : documents){
          map.put(document.getId(),document.getData());
        }//for
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }//catch
      return new ResponseEntity<>(map,HttpStatus.ACCEPTED);
    }//getBelegt

    @RequestMapping(value = "/getBestellungen")
    public ResponseEntity<Object> getBestellungen(@RequestHeader("nutzerID") String nutzerID){
      Map<String,Map<String,Object>> map = new HashMap<>();
      if (Long.parseLong(nutzerID)>=1){
        ApiFuture<QuerySnapshot> query = db.collection("Nutzer").document(nutzerID)
            .collection("Bestellungen").get();
        try {
          QuerySnapshot querySnapshot = query.get();
          List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
          for (DocumentSnapshot document : documents){
            map.put(document.getId(),document.getData());
          }//for
        } catch (InterruptedException e) {
          e.printStackTrace();
        } catch (ExecutionException e) {
          e.printStackTrace();
        }//catch
      }//then
      return new ResponseEntity<>(map,HttpStatus.ACCEPTED);
    }//getBestellungen

    @RequestMapping(value = "/getReservierungen")
    public ResponseEntity<Object> getReservierungen(@RequestHeader("nutzerID") String nutzerID){
      Map<String,Map<String,Object>> map = new HashMap<>();
      if (Long.parseLong(nutzerID)>=1){
        ApiFuture<QuerySnapshot> query = db.collection("Nutzer").document(nutzerID)
            .collection("Reservierungen").get();
        try {
          QuerySnapshot querySnapshot = query.get();
          List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
          for (DocumentSnapshot document : documents){
            map.put(document.getId(),document.getData());
          }//for
        } catch (InterruptedException e) {
          e.printStackTrace();
        } catch (ExecutionException e) {
          e.printStackTrace();
        }//catch
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
        try {
          QuerySnapshot querySnapshot = query.get();
          List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
          for (DocumentSnapshot document : documents){
            map.put(document.getId(),document.getData());
          }//for
        } catch (InterruptedException e) {
          e.printStackTrace();
        } catch (ExecutionException e) {
          e.printStackTrace();
        }//catch
      }//then
      return new ResponseEntity<>(map,HttpStatus.ACCEPTED);
    }//getResSitze

    /**
    @RequestMapping(value = "/buchen")
    public ResponseEntity<Object> buchen(@RequestHeader("sitze") String sitze, @RequestHeader("nutzer") String nutzerID){
      String erg = "Success";
      try {
        //Überprüfen, ob Nutzer existiert
        ApiFuture<DocumentSnapshot> query = db.collection("Nutzer").document(nutzerID).get();
        DocumentSnapshot doc = query.get();
        if (doc.exists()){
          //Header zu ArrayListe von Sitzen umwandeln
          ArrayList<Sitz> sitzListe = new ArrayList<>();
          Map<String,Map<String,Object>> map = new ObjectMapper().readValue(sitze,Map.class);
          for (Map.Entry<String,Map<String,Object>> e : map.entrySet()){
            Map<String,Object> sitz = e.getValue();
            Sitz tmp = new Sitz();
            for (Map.Entry<String,Object> entry : sitz.entrySet()){
              tmp.set(entry.getKey(),entry.getValue());
            }//for
            sitzListe.add(tmp);
          }//for

          //Prüfen, ob Sitze immernoch frei
          if (sitzListe.size()>0){
            boolean frei = sitzeFrei(sitzListe);
            //wenn ja, dann aus freien Sitzen entfernen und zu belegten hinzufügen
            if (frei){
              String kinoID = sitzListe.get(0).getKinoID();
              String filmID = sitzListe.get(0).getFilmID();
              String vorID = sitzListe.get(0).getVorID();

              for (Map.Entry<String,Map<String,Object>> en : map.entrySet()){
                db.collection("Kino").document(kinoID).collection("spieltFilme")
                    .document(filmID).collection("Vorstellungen").document(vorID)
                    .collection("FreieSitze").document(en.getKey()).delete();
                db.collection("Kino").document(kinoID).collection("spieltFilme")
                    .document(filmID).collection("Vorstellungen").document(vorID)
                    .collection("BelegteSitze").document(en.getKey()).set(en.getValue());
              }//for
              //die verbuchten plätze zum passenden nutzer hinzufügen
              if (!nutzerID.equals("0")){
                //aktuelle Bestellungsnummer holen
                ApiFuture<QuerySnapshot> q = db.collection("Nutzer").document(nutzerID).collection("Bestellungen").get();
                QuerySnapshot qSnapshot =  q.get();
                List<QueryDocumentSnapshot> documents = qSnapshot.getDocuments();
                String bestellungsnummer = nutzerID + "_" + (documents.size()+1);
              }//then

            }//then
        }//then
        else erg = "Error.";
        }//then
        else {
          erg = "Error.";
        }//else
      } catch (IOException e) {
        e.printStackTrace();
        erg = "Error.";
      } //catch
      catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }

      return new ResponseEntity<>(erg,HttpStatus.ACCEPTED);
    }//buchen
    **/
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
            if (sitzListe.size()>0){
              //prüfen ob sitze frei sind
              boolean frei = sitzeFrei(sitzListe);
              if (frei){
                String kinoID = sitzListe.get(0).getKinoID();
                String filmID = sitzListe.get(0).getFilmID();
                String vorID = sitzListe.get(0).getVorID();
                //wenn die sitze frei sind unter reserviert speichern und unter Nutzer Reservierung hinzufügen
                //TODO: Timer, welcher nach 30 Minuten Reservierungen löscht
                for (Map.Entry<String,Map<String,Object>> en : map.entrySet()){
                  db.collection("Kino").document(kinoID).collection("spieltFilme")
                      .document(filmID).collection("Vorstellungen").document(vorID)
                      .collection("FreieSitze").document(en.getKey()).delete();
                  db.collection("Kino").document(kinoID).collection("spieltFilme")
                      .document(filmID).collection("Vorstellungen").document(vorID)
                      .collection("ReservierteSitze").document(en.getKey()).set(en.getValue());
                  //Unter dem passenden Nutzer eine Reservierung hinzufügen
                }//for
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
      String erg = "Success";
      //Abbruch wenn falsche id
      if (id<1)erg = "Error.";
      else {
        ApiFuture<DocumentSnapshot> queryDoc = db.collection("Nutzer").document(nutzerID).get();
        DocumentSnapshot doc = null;
        try {
          //abbruch wenn es nutzer nicht gibt
          doc = queryDoc.get();
          if (!doc.exists())erg = "Error.";
          else {
            DocumentReference nutzer = db.collection("Nutzer").document(nutzerID);
           ArrayList<Buchung> reservierungen = getRes(nutzerID);
           if (reservierungen.size()>0){
             if (!verbucheReservierungen(nutzer,reservierungen))erg = "Error.";
           }//then
          }//else
        } catch (InterruptedException e) {
          e.printStackTrace();
        } catch (ExecutionException e) {
          e.printStackTrace();
        }
      }//else
      return new ResponseEntity<>(erg,HttpStatus.ACCEPTED);
    }//buchen

    private boolean sitzeFrei (ArrayList<Sitz> sitze){
      if (sitze!=null){
        ArrayList<Sitz> test = (ArrayList<Sitz>) sitze.clone();
        Sitz s = sitze.get(0);
        String kinoID = s.getKinoID();
        String filmID = s.getFilmID();
        String vorID = s.getVorID();
        ApiFuture<QuerySnapshot> query = db.collection("Kino").document(kinoID)
            .collection("spieltFilme").document(filmID)
            .collection("Vorstellungen").document(vorID)
            .collection("FreieSitze").get();
        try {
          QuerySnapshot querySnapshot = query.get();
          List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
          for (DocumentSnapshot document : documents){
            for (Sitz iteraor : test){
              if (document.getId().equals(iteraor.getSitzID())){
                test.remove(iteraor);
                if (test.size()==0)return true;
              }
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
        ApiFuture<QuerySnapshot> query = db.collection("Nutzer").document(nutzerID).collection("Reservierungen").get();
        QuerySnapshot qSnapshot =  query.get();
        List<QueryDocumentSnapshot> documents = qSnapshot.getDocuments();
        String buchungsID = nutzerID + "_0_" + (documents.size()+1);
        buchung.setBuchungID(buchungsID);

        //vorführungspreis holen
        Sitz sitz = new Sitz();
        if (sitze.size()>0)sitz = sitze.get(0);
        ApiFuture<DocumentSnapshot>qD = db.collection("Kino").document(sitz.getKinoID()).collection("spieltFilme").document(sitz.getFilmID())
            .collection("Vorstellungen").document(sitz.getVorID()).get();
        DocumentSnapshot vor = qD.get();
        Double vPreis = 0.0;
        if (vor.exists()){
          vPreis = Double.parseDouble(vor.get("gesamtpreis").toString());
          buchung.setVorführungspreis(vPreis);
          buchung.setVorführungsID(vor.get("vorführungsID").toString());
        }//then

        //der Buchung die sitze zuweisen
        buchung.setSitze(sitze);
        //hole den automatisch berechneten Buchungspreis
        Double preis = buchung.getBuchungspreis();
        //Erstelle eine Map in welche alle Informationen gepackt werden
        Map<String,Object> buchungsMap = new HashMap<>();
        buchungsMap.put("buchungsID",buchungsID);
        buchungsMap.put("vorführungsID",sitz.getVorID());
        buchungsMap.put("buchungspreis",preis);
        //Dem Nutzer die Reservierungsbuchung hinzufügen
        db.collection("Nutzer").document(nutzerID).collection("Reservierungen").document(buchungsID).set(buchungsMap);
        //Der Buchung die Sitze hinzufügen
        for (Map.Entry<String,Map<String,Object>> e : map.entrySet()){
          db.collection("Nutzer").document(nutzerID).collection("Reservierungen").document(buchungsID).collection("Sitze")
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
      for (Buchung b : bestellung.getBuchungen()){
        //buchungsID updaten und buchungen hinzufügen
        String buchungsID = bestellung.getBesetellungsnummer() + "_" + b.getBuchungID().substring(b.getBuchungID().lastIndexOf('_')+1);
        b.setBuchungID(buchungsID);
        Map<String,Object> buchungMap = buchungToMap(b);
        nutzer.collection("Bestellungen").document(b.getBestellungsnummer()).collection("Buchungen").document(b.getBuchungID()).set(buchungMap);
      //sitze der buchungen namen ändern und hinzufügen
        for (Sitz s : b.getSitze()){
          String sitzID = buchungsID + "_" + s.getSitzID().substring(s.getSitzID().lastIndexOf('_')+1);
          s.setSitzID(sitzID);
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
      if (buchung!=null){
        Map<String,Object> buchungMap = new HashMap<>();
        buchungMap.put("buchungsID",buchung.getBuchungID());
        buchungMap.put("buchungspreis",buchung.getBuchungspreis());
        buchungMap.put("vorführungsID",buchung.getVorführungsID());
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

  }//Controller
}// class
