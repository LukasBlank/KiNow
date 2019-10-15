package com.example.demo;


import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.gson.Gson;
import java.io.FileInputStream;
import java.net.URL;

import java.util.*;
import java.util.concurrent.ExecutionException;

import lukas.classes.Film;
import lukas.java_classes.Nutzer;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
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

      //WENN: Nicht über Server laufend: die beiden unteren Zeilen einkommentieren
      // und in FileInputStream url.getPath() einfügen
      //String path = "serviceAccountKey.json";
      //URL url = DemoApplication.class.getClassLoader().getResource(path);

      //Datenbankverbindung erstellen
      FileInputStream serviceAccount =
          new FileInputStream( "serviceAccountKey.json");

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
    sc.getFilme();

  }//main

  @RestController
  public static class SimpleController {

    /**
    @RequestMapping(value = "/Person")
    public ResponseEntity<Nutzer> getPerson() {
      return new ResponseEntity<Nutzer>(
          new Nutzer(1, "Hans", "peter", new Date(), "email@email.com", "1234"), HttpStatus.OK);
    }

    @RequestMapping(value = "/Kino")
    public ResponseEntity<Object> getKino(@RequestHeader("kinoID") int kino) {
      Kino k = new Kino(kino, "HAns", "Pad");
      Map<Integer, Kino> KinoRepo = new HashMap<>();
      KinoRepo.put(k.getKinoID(), k);
      return new ResponseEntity<>(KinoRepo.values(), HttpStatus.OK);
    }

    @RequestMapping(value = "/KinoBody")
    public ResponseEntity<Object> getKino(@RequestBody() String kino) {
      Gson gson = new Gson();
      Map<Integer, Kino> KinoRepo = new HashMap<>();
      try {
        Kino kino1 = gson.fromJson(kino, Kino.class);
        KinoRepo.put(kino1.getKinoID(), kino1);
        return new ResponseEntity<>(KinoRepo.values(), HttpStatus.OK);
      } catch (Exception e) {
      }
      return new ResponseEntity<>(KinoRepo.values(), HttpStatus.BAD_REQUEST);
    }

     **/

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

    @RequestMapping(value = "/test2")
    public void test2 (){
      ApiFuture<DocumentSnapshot> query = db.collection("Filme").document("7").get();
      Film film = new Film();
      try {
        DocumentSnapshot documentSnapshot = query.get();
        Map <String,Object> data = new HashMap<>();
        data = documentSnapshot.getData();
        String erg = data.toString();
        for (Map.Entry<String, Object> entry : data.entrySet()){
          film.set(entry.getKey(),entry.getValue());
        }//for
        int i = 23 * 45;
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }
    }//test2

    @RequestMapping(value = "/getFilme")
    public ResponseEntity<Object> getFilme (){
      ApiFuture<QuerySnapshot> query = db.collection("Filme").get();
      QuerySnapshot querySnapshot = null;
      List<QueryDocumentSnapshot> documents = null;
      Map<String,Map<String,Object>> map = new HashMap<>();

      try {
        querySnapshot = query.get();
        documents = querySnapshot.getDocuments();

        for ( DocumentSnapshot document : documents){
          map.put(document.getId(),document.getData());
        }//for

      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }//catch

      return new ResponseEntity<>(map,HttpStatus.ACCEPTED);
    }//getFilme

    @RequestMapping(value = "/setNutzer")
    public void setData(@RequestBody String body) {
      Gson gson= new Gson();
      Nutzer nutzer = gson.fromJson(body,Nutzer.class);
      ApiFuture<QuerySnapshot> query = db.collection("Nutzer").get();

      QuerySnapshot querySnapshot = null;
      try {
        int nutzerID;
        querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        nutzerID = documents.size();
        DocumentReference docRef = db.collection("Nutzer").document(Integer.toString(nutzerID));
        nutzer.setNutzerID(nutzerID);
        System.out.println(body);
        //Nutzer nutzer=gson.fromJson(body,Nutzer.class);
        ApiFuture<WriteResult> result = docRef.set(nutzer);
        System.out.println(result.toString());
        JSONObject jsonObject=new JSONObject(body);
        System.out.println(jsonObject.toString(5));
      } catch (InterruptedException e) {
        System.out.println("InterruptException");
        e.printStackTrace();
      } catch (ExecutionException e) {
        System.out.println("Exception");
        e.printStackTrace();
      }  catch (Exception e) {
      System.out.println("Exception");
      e.printStackTrace();
    }
    }//setNutzer

    // Fügt Säle zu dem Dokument Kino hinzu. Jedes Kino hat seine eigenen Säle
    @RequestMapping(value = "/addSaele")
    public void addSaele () {
      for (int i = 5; i <= 10; i++) {

        Map<String, Object> docData = new HashMap<>();
        docData.put("barrierefrei", false);
        docData.put("platzzahl", 100);
        docData.put("saalnummer", i);

        String name = ""+i;

        db.collection("Kino").document("1").collection("HatSaele").document(name).set(docData);

        db.collection("Saele").document("2").set(docData);
      }

    }

    @RequestMapping (value = "/addTestdata")
    public void addSitzeToSaele (){

      String sitzReihe = "";
      String sitzID = "";

      for (int i = 5; i<=10; i++) {
        Map<String, Object> docData = new HashMap<>();

       // docData.put("sitzID", "A1");
        //docData.put("loge", true);
        //docData.put("barriereFrei", false);

        //add Sitz ID
        for (int j = 1; j <= 10; j++) {

          if (j == 1) {
            sitzReihe = "A";
          } else if (j == 2) {
            sitzReihe = "B";
          } else if (j == 3) {
            sitzReihe = "C";
          } else if (j == 4) {
            sitzReihe = "D";
          } else if (j == 5) {
            sitzReihe = "E";
          } else if (j == 6) {
            sitzReihe = "F";
          } else if (j == 7) {
            sitzReihe = "G";
          } else if (j == 8) {
            sitzReihe = "G";
          } else if (j == 9) {
            sitzReihe = "G";
          } else if (j == 10) {
            sitzReihe = "G";
          }


          System.out.printf("CHECKPOINT 1");

          for (int k = 1; k <= 10; k++) {
            sitzID = sitzReihe + k;
            docData.put("sitzID", sitzID);

            if (i <= 2) {
              docData.put("loge", true);
              System.out.println("CHECKPOINT 2a");
            } else {
              docData.put("loge", false);
              System.out.println("CHECKPOINT 2b");
            }

              docData.put("barriereFrei", false);
              System.out.println("CHECKPOINT 3");


            System.out.println("CHECKPOINT 5");
            System.out.println(sitzID);
            db.collection("Kino").document("1").collection("HatSaele").document("" + i).collection("HatSitze").document("" + sitzID).set(docData);

          }
        }
      }

    }

    @RequestMapping(value = "/getTestArray")
    public void getTestArray() {
      DocumentReference documentReference = db.collection("test").document("ArrayOrList");

      ApiFuture<DocumentSnapshot> doc = documentReference.get();

      try{
        DocumentSnapshot document = doc.get();
        Map<String, Object> list = document.getData();

        if(document.exists()){
          System.out.println("Document Data" +document.getData());
        }else{
          System.out.println("No such Document!");
        }

      }catch (InterruptedException e){
        e.printStackTrace();
      }catch (java.util.concurrent.ExecutionException e){
        e.printStackTrace();
      }

    }


    @RequestMapping(value = "/testArray")
    public void testSäle(){

      Map<String, Object> docData = new HashMap<>();

      docData.put("numbers", Arrays.asList(1,2,3,4,5,6));

      db.collection("Test").document("ArrayOrList").set(docData);

    }

    @RequestMapping(value = "/getAllData")
    public ResponseEntity<Object> getAllData(@RequestHeader("head") String head) {
      // asynchronously retrieve all documents of an collection
      ApiFuture<QuerySnapshot> query = db.collection(head).get();
      Map<String, Object> data = new HashMap<>();
      Map<String, String> JsonString = new HashMap<>();
// ...
// query.get() blocks on response
      try {
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        int länge=documents.size();

        for (QueryDocumentSnapshot document : documents) {
          data.put(document.getId(), document.getData());
          JsonString.put(document.getId(), document.getData().toString());
        }//for

          JSONObject jsonObject=new JSONObject(JsonString);
          System.out.println(jsonObject.toString(5));

      } catch (Exception e) {
      }//catch
      return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
    }//getAllData

    /**
    @RequestMapping(value = "/getNutzerbyName")
    public ResponseEntity<ArrayList> getNutzer(@RequestHeader("name") String name) {
      // asynchronously retrieve all users
      Query query = db.collection("Nutzer").whereEqualTo("nachname", name);
      ApiFuture<QuerySnapshot> querySnapshot = query.get();
      ArrayList<Nutzer> list = new ArrayList<>();
      try {
        Nutzer nutzer = new Nutzer();
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
          nutzer = document.toObject(Nutzer.class);
          list.add(nutzer);
        }
// ...
// query.get() blocks on response
        return new ResponseEntity<ArrayList>(list, HttpStatus.ACCEPTED);
      } catch (Exception e) {
      }
      return new ResponseEntity<ArrayList>(list, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/getNutzerbyID")
    public ResponseEntity<ArrayList> getNutzerbyID(@RequestHeader("id") int id) {
      // asynchronously retrieve all users
      Query query = db.collection("Nutzer").whereEqualTo("nid", id);
      ApiFuture<QuerySnapshot> querySnapshot = query.get();
      ArrayList<Nutzer> list = new ArrayList<>();
      try {
        Nutzer nutzer = new Nutzer();
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
          nutzer = document.toObject(Nutzer.class);
          list.add(nutzer);
        }
// ...
// query.get() blocks on response
        System.out.println("Anfrage von id:" + id);
        return new ResponseEntity<ArrayList>(list, HttpStatus.ACCEPTED);
      } catch (Exception e) {
      }
      return new ResponseEntity<ArrayList>(list, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/getFilmInKino")
    public ResponseEntity<ArrayList> getFilmInKino(@RequestHeader("kino") String kino) {
      // asynchronously retrieve all users
      Query query = db.collection("Filme").whereEqualTo("nachname", kino);
      ApiFuture<QuerySnapshot> querySnapshot = query.get();
      ArrayList<Nutzer> list = new ArrayList<>();
      try {
        Nutzer nutzer = new Nutzer();
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
          nutzer = document.toObject(Nutzer.class);
          list.add(nutzer);
        }
// ...
// query.get() blocks on response
        return new ResponseEntity<ArrayList>(list, HttpStatus.ACCEPTED);
      } catch (Exception e) {
      }
      return new ResponseEntity<ArrayList>(list, HttpStatus.BAD_REQUEST);

    }//getFilmKino
    **/

  }//Controller
}// class
