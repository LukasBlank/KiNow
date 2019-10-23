package com.example.demo;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.gson.Gson;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import java.security.SignedObject;
import java.util.*;
import java.util.EventListener;
import java.util.concurrent.ExecutionException;

import lukas.classes.Kino;
import lukas.classes.Kinosaal;
import lukas.classes.Nutzer;
import lukas.classes.Sitz;
import org.apache.catalina.connector.Response;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    sc.test();
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

    @RequestMapping(value = "/buchen")
    public ResponseEntity<Object> buchen(@RequestHeader("sitze") String sitze){
      //String zuverlässig zu einer Map parsen
      String erg = "Success";
      try {
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




  }//Controller
}// class
