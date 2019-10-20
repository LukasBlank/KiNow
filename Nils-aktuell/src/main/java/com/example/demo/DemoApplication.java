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

import java.rmi.MarshalledObject;
import java.sql.Timestamp;
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

import javax.print.Doc;

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
    sc.updateFilm(7,3);
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

    public void updateFilm(int film, int kino){
        String link;
        Map<String, Object> docData = new HashMap<>();

        switch (film){
            case 1:
                // ES
                link = "OYsSefoUdaw";
                docData.put("link", link);
                db.collection("Kino").document(kino+"").collection("spieltFilme").document(film+"").update(docData);
                break;
            case 2:
                //Joker
                link = "vVGJUj6Hh8s";
                docData.put("link", link);
                db.collection("Kino").document(kino+"").collection("spieltFilme").document(film+"").update(docData);
                break;
            case 3:
                //downtown abbey
                link = "sSDOSaipC9Q";
                docData.put("link", link);
                db.collection("Kino").document(kino+"").collection("spieltFilme").document(film+"").update(docData);
                break;
            case 4:
                //gemini man
                link = "geIcrgq55uA";
                docData.put("link", link);
                db.collection("Kino").document(kino+"").collection("spieltFilme").document(film+"").update(docData);
                break;
            case 5:
                // eine ganz heiße nummer 2.0
                link = "tg7xjbTpiCc";
                docData.put("link", link);
                db.collection("Kino").document(kino+"").collection("spieltFilme").document(film+"").update(docData);
                break;
            case 6:
                //everest - ein yeti will hoch hinaus
                link = "8Q7OY-NcTZM";
                docData.put("link", link);
                db.collection("Kino").document(kino+"").collection("spieltFilme").document(film+"").update(docData);
                break;
            case 7:
                //ich war noch niemals in NY
                link = "oPkzUhsRrGc";
                docData.put("link", link);
                db.collection("Kino").document(kino+"").collection("spieltFilme").document(film+"").update(docData);
                break;
            case 8:
                //dem horizont so nah
                link = "Xkxh6mrA5kI";
                docData.put("link", link);
                db.collection("Kino").document(kino+"").collection("spieltFilme").document(film+"").update(docData);
                break;
            case 9:
                //after teh wedding
                link = "M9I52xL3DPQ";
                docData.put("link", link);
                db.collection("Kino").document(kino+"").collection("spieltFilme").document(film+"").update(docData);
                break;
            case 10:
                //maleficint
                link = "xlB_ZwOF3Gk";
                docData.put("link", link);
                db.collection("Kino").document(kino+"").collection("spieltFilme").document(film+"").update(docData);
                break;
            case 11:
                //parasite
                link = "9qvaE99iMR0";
                docData.put("link", link);
                db.collection("Kino").document(kino+"").collection("spieltFilme").document(film+"").update(docData);
                break;
            case 12:
                //das kapitel im 21. jahrhundert
                link = "zd7whXnRLbI";
                docData.put("link", link);
                db.collection("Kino").document(kino+"").collection("spieltFilme").document(film+"").update(docData);
                break;
        }
        System.out.println("END");
    }

    public void addWerbung(int kino, int film, int saal, int vorstellung){
        Map <String, Object> docData = new HashMap<>();

        long werbungID, dauer;
        String name;

        werbungID = 0;

        for (int i = 0; i <= 20; i++) {
            docData.put("werbungID", werbungID+1);
            db.collection("Kino").document(kino+"")
                    .collection("spieltFilme").document(film+"")
                    .collection("Vorstellungen").document(kino + "_" + saal + "_" + film + "_" + vorstellung)
                    .collection("Werbung").document(kino + "_" + saal + "_" + film + "_" + vorstellung + "_" + werbungID).set(docData);
        }
    }

    @RequestMapping (value = "/juliesPlayground")
    public void juliesPlayground(int kino, int saal, int film, int anzVorstellungen){

        Map<String, Object> docData = new HashMap<>();
        int cache = 1;
        String [] date = {
          "2019-10-21", "2019-10-22", "2019-10-23", "2019-10-24", "2019-10-25", "2019-10-26", "2019-10-27"
        };

        int local = 0;

        for (int i = 1; i <= anzVorstellungen; i++) {

            //je nach film ist die dauer anders -- setzen grunddauer und gesamtdauer
            switch (film){
                case 1:
                    docData.put("gesamtdauer", 200);
                    docData.put("grunddauer", 170);
                    break;
                case 2:
                case 3:
                    docData.put("gesamtdauer", 152);
                    docData.put("grunddauer", 122);
                    break;
                case 4:
                    docData.put("gesamtdauer", 149);
                    docData.put("grunddauer", 119);
                    break;
                case 5:
                    docData.put("gesamtdauer", 121);
                    docData.put("grunddauer", 91);
                    break;
                case 6:
                    docData.put("gesamtdauer", 127);
                    docData.put("grunddauer", 97);
                    break;
                case 7:
                    docData.put("gesamtdauer", 158);
                    docData.put("grunddauer", 128);
                    break;
                case 8:
                    docData.put("gesamtdauer", 147);
                    docData.put("grunddauer", 117);
                    break;
                case 9:
                    docData.put("gesamtdauer", 143);
                    docData.put("grunddauer", 113);
                    break;
                case 10:
                    docData.put("gesamtdauer", 148);
                    docData.put("grunddauer", 118);
                    break;
                case 11:
                    docData.put("gesamtdauer", 162);
                    docData.put("grunddauer", 132);
                    break;
                case 12:
                    docData.put("gesamtdauer", 132);
                    docData.put("grunddauer", 102);
                    break;


            }

            //VorführungsID
            docData.put("vorführugnsID", kino + "_" + saal + "_" + film + "_" + i);

            //saalnummer
            docData.put("saalnummer", kino + "_" + saal);

            docData.put("grundpreis", 7.00);

            // dreiD oder nicht
            switch(film){
                case 1: case 2: case 3: case 4: case 5: case 7: case 8: case 9: case 11: case 12:
                    docData.put("gesamtpreis", 7.00);
                    docData.put("3D", false);
                    break;
                case 10: case 6:
                    if (anzVorstellungen == 3 || anzVorstellungen == 6 || anzVorstellungen == 9) {
                        docData.put("gesamtpreis", 11.00);
                        docData.put("dreiD", true);
                    }
                    else {
                        docData.put("gesamtpreis", 7.00);
                        docData.put("dreiD", false);
                    }
                    break;
            }

            //Vorstellungen

            //Option 1
            /*switch (anzVorstellungen){
                case 7:
                    //eine Vorstellung am Tag
                        docData.put("zeitpunkt",  date [i-1] + "/" + "13:00");
                        db.collection("Kino").document(kino + "").collection("spieltFilme").document(film +  "").collection("Vorstellungen").document(kino + "_" + saal + "_"+ film + "_" + i).set(docData);
                    break;

                case 14:
                    //zwei vorstellungen am Tag
                    if(i <= 7) {
                        docData.put("zeitpunkt", date[i-1] + "/" + "12:00");
                        db.collection("Kino").document(kino + "").collection("spieltFilme").document(film + "").collection("Vorstellungen").document(kino + "_" + saal + "_" + film + "_" + i).set(docData);
                    }else{
                        docData.put("zeitpunkt", date[local] + "/" + "18:00");
                        System.out.println(cache);
                        local ++;
                        db.collection("Kino").document(kino + "").collection("spieltFilme").document(film +  "").collection("Vorstellungen").document(kino + "_" + saal + "_"+ film + "_" + i).set(docData);
                    }
                    break;

                case 21:
                    //drei Vorste   llungen am Tag
                  if (i <= 7){
                        docData.put("zeitpunkt", date[i-1]+ "/" + "11:00");
                        db.collection("Kino").document(kino + "").collection("spieltFilme").document(film +  "").collection("Vorstellungen").document(kino + "_" + saal + "_"+ film + "_" + i).set(docData);
                    }else if( i > 7 && i <= 14){
                        docData.put("zeitpunkt", date[local] + "/" + "15:00");
                        local ++;
                        db.collection("Kino").document(kino + "").collection("spieltFilme").document(film +  "").collection("Vorstellungen").document(kino + "_" + saal + "_"+ film + "_" + i).set(docData);
                    } else {
                      if (local >= 7){
                          local = 0;
                      }
                        docData.put("zeitpunkt", date[local] + "/" + "19:00");
                        local ++;
                        db.collection("Kino").document(kino + "").collection("spieltFilme").document(film +  "").collection("Vorstellungen").document(kino + "_" + saal + "_"+ film + "_" + i).set(docData);
                    }
                    break;

                case 28:
                    //vier vorstellugen am Tag
                    if (i <= 7){
                        docData.put("zeitpunkt", date[i-1] + "/" + "09:00");
                        db.collection("Kino").document(kino + "").collection("spieltFilme").document(film +  "").collection("Vorstellungen").document(kino + "_" + saal + "_"+ film + "_" + i).set(docData);
                    }else if ( i > 7 && i <= 14){
                        docData.put("zeitpunkt", date[local] + "/" + "13:00");
                        local ++;
                        db.collection("Kino").document(kino + "").collection("spieltFilme").document(film +  "").collection("Vorstellungen").document(kino + "_" + saal + "_"+ film + "_" + i).set(docData);
                    }else if (i > 14&& i <= 21){
                        if (local >= 7){
                            local = 0;
                        }
                        docData.put("zeitpunkt", date[local] + "/" + "17:00");
                        local ++;
                        db.collection("Kino").document(kino + "").collection("spieltFilme").document(film +  "").collection("Vorstellungen").document(kino + "_" + saal + "_"+ film + "_" + i).set(docData);
                    } else {
                        if(local >= 7){
                            local = 0;
                        }
                        docData.put("zeitpunkt", date[local] + "/" + "21:00");
                        local ++;
                        db.collection("Kino").document(kino + "").collection("spieltFilme").document(film +  "").collection("Vorstellungen").document(kino + "_" + saal + "_"+ film + "_" + i).set(docData);
                    }
                    break;
            }*/

            // Option 2
            switch (anzVorstellungen){
                case 7:
                    //eine Vorstellung am Tag
                    docData.put("zeitpunkt",  date [i-1] + "/" + "15:30");
                    db.collection("Kino").document(kino + "").collection("spieltFilme").document(film +  "").collection("Vorstellungen").document(kino + "_" + saal + "_"+ film + "_" + i).set(docData);
                    break;

                case 14:
                    //zwei vorstellungen am Tag
                    if(i <= 7) {
                        docData.put("zeitpunkt", date[i-1] + "/" + "17:30");
                        db.collection("Kino").document(kino + "").collection("spieltFilme").document(film + "").collection("Vorstellungen").document(kino + "_" + saal + "_" + film + "_" + i).set(docData);
                    }else{
                        docData.put("zeitpunkt", date[local] + "/" + "20:30");
                        System.out.println(cache);
                        local ++;
                        db.collection("Kino").document(kino + "").collection("spieltFilme").document(film +  "").collection("Vorstellungen").document(kino + "_" + saal + "_"+ film + "_" + i).set(docData);
                    }
                    break;

                case 21:
                    //drei Vorste   llungen am Tag
                    if (i <= 7){
                        docData.put("zeitpunkt", date[i-1] + "/" + "10:30");
                        db.collection("Kino").document(kino + "").collection("spieltFilme").document(film +  "").collection("Vorstellungen").document(kino + "_" + saal + "_"+ film + "_" + i).set(docData);
                    }else if( i > 7 && i <= 14){
                        docData.put("zeitpunkt", date[local] + "/" + "16:30");
                        local ++;
                        db.collection("Kino").document(kino + "").collection("spieltFilme").document(film +  "").collection("Vorstellungen").document(kino + "_" + saal + "_"+ film + "_" + i).set(docData);
                    } else {
                        if (local >= 7){
                            local = 0;
                        }
                        docData.put("zeitpunkt", date[local] + "/" + "19:30");
                        local ++;
                        db.collection("Kino").document(kino + "").collection("spieltFilme").document(film +  "").collection("Vorstellungen").document(kino + "_" + saal + "_"+ film + "_" + i).set(docData);
                    }
                    break;

                case 28:
                    //vier vorstellugen am Tag
                    if (i <= 7){
                        docData.put("zeitpunkt", date[i-1] + "/" + "11:30");
                        db.collection("Kino").document(kino + "").collection("spieltFilme").document(film +  "").collection("Vorstellungen").document(kino + "_" + saal + "_"+ film + "_" + i).set(docData);
                    }else if ( i > 7 && i <= 14){
                        docData.put("zeitpunkt", date[local] + "/" + "14:30");
                        local ++;
                        db.collection("Kino").document(kino + "").collection("spieltFilme").document(film +  "").collection("Vorstellungen").document(kino + "_" + saal + "_"+ film + "_" + i).set(docData);
                    }else if (i > 14&& i <= 21){
                        if (local >= 7){
                            local = 0;
                        }
                        docData.put("zeitpunkt", date[local] + "/" + "17:30");
                        local ++;
                        db.collection("Kino").document(kino + "").collection("spieltFilme").document(film +  "").collection("Vorstellungen").document(kino + "_" + saal + "_"+ film + "_" + i).set(docData);
                    } else {
                        if(local >= 7){
                            local = 0;
                        }
                        docData.put("zeitpunkt", date[local] + "/" + "22:30");
                        local ++;
                        db.collection("Kino").document(kino + "").collection("spieltFilme").document(film +  "").collection("Vorstellungen").document(kino + "_" + saal + "_"+ film + "_" + i).set(docData);
                    }
                    break;
            }
        }
        System.out.println("END");
    }

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
    public String getFilme (){

      ApiFuture<QuerySnapshot> query = db.collection("Filme").get();
      QuerySnapshot querySnapshot;
      List<QueryDocumentSnapshot> documents;
      ArrayList<Map<String,Object>> data = new ArrayList<>();
      String erg = "";
      try {
        querySnapshot = query.get();
        documents = querySnapshot.getDocuments();
        for ( DocumentSnapshot documentSnapshot : documents){
          data.add(documentSnapshot.getData());
        }//for
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }//catch
      return erg;
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
        Map<String, Object> docData = new HashMap<>();

        for (int l = 1; l <= 3; l++) {

            for (int i = 1; i <= 4; i++) {

                String name = l + "_" + i;

                docData.put("barrierefrei", true);
                docData.put("platzzahl", 49);
                docData.put("saalnummer", name);

                db.collection("Kino").document(""+l).collection("HatSaele").document(name).set(docData);
            }

            for (int i = 5; i <= 10; i++) {

                String name = l + "_" + i;

                docData.put("barrierefrei", false);
                docData.put("platzzahl", 100);
                docData.put("saalnummer", name);

                db.collection("Kino").document(""+l).collection("HatSaele").document(name).set(docData);
            }
        }

    }

    //Fügt zu jedem Film Vorstellungen hinzu -> Muss zwar immer individuell angepasst werden
    @RequestMapping (value = "/addVorstellungen")
    public void addVorstellungen() {

        int film = 9;

        for (int i = 1; i <= 3; i++) {
            Map<String, Object> docData = new HashMap<>();

            docData.put("vorführungsID", "2_" + film + "_" + i); //Kino 1, Film 2, 1 Vorführung
            docData.put("filmID", film);
            docData.put("saalnummer", 6);

            if(i == 1){
                docData.put("zeitpunkt", "12:00");
            } else if (i == 2) {
                docData.put("zeitpunkt", "15:00");
            } else if (i == 3) {
                docData.put("zeitpunkt", "18:00");
            } else if (i == 4) {
                docData.put("zeitpunkt", "20:30");
            } else if (i == 5){
                docData.put("zeitpunkt", "8:00");
            }

            docData.put("gesamtdauer", 137);
            docData.put("grundpreis", 7.00);

            //if(i == 2) {
            //  docData.put("gesamtpreis", 11.00);
            //  docData.put("3D", true);
            //} else {
                docData.put("gesamtpreis", 7.00);
                docData.put("3D", false);
            //}

            db.collection("Kino").document("2").collection("spieltFilme").document(film +  "").collection("Vorstellungen").document("2_" + film + "_" + i).set(docData);
        }
    }

    @RequestMapping (value = "/addTestdata")
    public void addSitzeToSaele (){

      String sitzReihe = "";
      String sitzID = "";
      Map<String, Object> docData = new HashMap<>();

      for (int l = 1; l <= 3; l++) {
          for (int i = 1; i <= 4; i++) {

              for (int j = 1; j <= 7; j++) {

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
                      sitzReihe = "H";
                  } else if (j == 9) {
                      sitzReihe = "I";
                  } else if (j == 10) {
                      sitzReihe = "J";
                  }

                  for (int k = 1; k <= 7; k++) {
                      sitzID = l + "_" + i + "_" + sitzReihe + k;
                      docData.put("sitzID", sitzID);

                      if (i <= 2) {
                          docData.put("loge", true);
                      } else {
                          docData.put("loge", false);
                      }

                      docData.put("barrierefrei", true);
                      db.collection("Kino").document(l + "").collection("HatSaele").document(l + "_" + i).collection("HatSitze").document("" + sitzID).set(docData);

                  }
              }
          }


          for (int i = 5; i <= 10; i++) {

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
                      sitzReihe = "H";
                  } else if (j == 9) {
                      sitzReihe = "I";
                  } else if (j == 10) {
                      sitzReihe = "J";
                  }
                  System.out.printf("CHECKPOINT 1");

                  for (int k = 1; k <= 10; k++) {
                      sitzID = l + "_" + i + "_" + sitzReihe + k;
                      docData.put("sitzID", sitzID);

                      if (i <= 2) {
                          docData.put("loge", true);
                      } else {
                          docData.put("loge", false);
                      }

                      docData.put("barrierefrei", false);

                      db.collection("Kino").document(l+"").collection("HatSaele").document(l + "_" + i).collection("HatSitze").document("" + sitzID).set(docData);

                  }
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
