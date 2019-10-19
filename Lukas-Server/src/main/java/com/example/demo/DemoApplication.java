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

import java.util.*;
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

      //WENN: Nicht über Server laufend: die beiden unteren Zeilen einkommentieren
      // und in FileInputStream url.getPath() einfügen
      String path = "serviceAccountKey.json";
      URL url = DemoApplication.class.getClassLoader().getResource(path);

      //Datenbankverbindung erstellen
      FileInputStream serviceAccount =
          new FileInputStream(url.getPath());

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

    Map<String, Object> map = new HashMap<>();
    map.put("nutzerID","10");map.put("vorname","Lukas");map.put("nachname","Blank");
    String test = map.toString();

    boolean success = sc.addNutzer(test);
  }//main

  @RestController
  public static class SimpleController {

    @RequestMapping (value = "/server")
    public String server (){
       return "Hallo.";
    }//lol

    @RequestMapping(value = "/lukasTest")
    public void lukasTest (){
      Map<String, Object> data = new HashMap<>();
      data.put("filmID",7);
      data.put("titel","Ich war noch niemals in New York");
      data.put("beschreibung","Für Lisa Wartberg (Heike Makatsch), erfolgsverwöhnte Fernsehmoderatorin und Single, steht ihre Show an erster Stelle. Doch dann verliert ihre Mutter Maria (Katharina Thalbach) nach einem Unfall ihr Gedächtnis, kommt ins Krankenhaus und kann sich nur noch an eines erinnern: Sie war noch niemals in New York! Kurzentschlossen flieht Maria und schmuggelt sich als blinder Passagier an Bord eines luxuriösen Kreuzfahrtschiffes. Gemeinsam mit ihrem Maskenbildner Fred (Michael Ostrowski) macht sich Lisa auf die Suche nach ihrer Mutter und spürt sie tatsächlich auf der \"MS Maximiliane\" auf. Doch bevor die beiden Maria wieder von Bord bringen können, legt der Ozeandampfer auch schon ab und die drei finden sich auf einer unfreiwilligen Reise über den Atlantik wieder. Lisa lernt an Bord Axel Staudach (Moritz Bleibtreu) und dessen Sohn Florian (Marlon Schramm) kennen. Axel ist so gar nicht Lisas Typ, doch durch eine Reihe unglücklicher Missgeschicke kommen sich die beiden schließlich näher... Mutter Maria trifft auf Eintänzer Otto (Uwe Ochsenknecht), der behauptet, eine gemeinsame Vergangenheit mit ihr zu haben - was Maria mangels Gedächtnis natürlich nicht überprüfen kann. Und Fred verliebt sich Hals über Kopf in den griechischen Bordzauberer Costa (Pasquale Aleardi). So verläuft die turbulente Schiffsreise - mit mehrmaligem Finden und Verlieren der Liebe und jeder Menge Überraschungen - nach New York.");
      data.put("dauer",128);
      data.put("fsk",0);
      data.put("bewertung",5);
      ArrayList<String> genres = new ArrayList<>();
      genres.add("Komödie"); genres.add("Musical");
      data.put("genres",genres);
      ArrayList<String> regie = new ArrayList<>();
      regie.add("Philipp Stölzl");
      data.put("regie",regie);
      ArrayList<String> darsteller = new ArrayList<>();
      darsteller.add("Heike Makatsch"); darsteller.add("Moritz Bleibtreu"); darsteller.add("Katharina Thalbach");
      darsteller.add("Uwe Ochsenknecht"); darsteller.add( "Michael Ostrowski"); darsteller.add("Pasquale Aleardi");
      darsteller.add("Marlon Schramm"); darsteller.add("Mat Schuh");
      data.put("darsteller",darsteller);
      db.collection("Filme").document("7").set(data);
    }//lukasTest

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

    @RequestMapping(value = "/getNutzer")
    public ResponseEntity<Object> getNutzer (){
      ApiFuture<QuerySnapshot> query = db.collection("Nutzer").get();
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
    }//getNutzer

    @RequestMapping(value = "/addNutzer")
    public boolean addNutzer(@RequestHeader("nutzer")String nutzer){
      //String zuverlässig zu einer Map parsen
      return false;
    }//addNutzer


  }//Controller
}// class
