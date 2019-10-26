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
import lukas.java_classes.Sitz;
import org.apache.commons.lang3.concurrent.ConcurrentException;
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
    sc.addWerbung(3, 1, 2, 4, 7);

  }//main

  @RestController
  public static class SimpleController {
    /**
    @RequestMapping(value = "/Person")
    public ResponseEntity<Nutzer> getPerson() {
      return new ResponseEntity<Nutzer>(
          new Nutzer(1, "Hans", "peter", new Date(), "email@email.com", "1234"), HttpStatus.OK);
    }

    @RequestMapping(value = "/kino")
    public ResponseEntity<Object> getKino(@RequestHeader("kinoID") int kino) {
      kino k = new kino(kino, "HAns", "Pad");
      Map<Integer, kino> KinoRepo = new HashMap<>();
      KinoRepo.put(k.getKinoID(), k);
      return new ResponseEntity<>(KinoRepo.values(), HttpStatus.OK);
    }

    @RequestMapping(value = "/KinoBody")
    public ResponseEntity<Object> getKino(@RequestBody() String kino) {
      Gson gson = new Gson();
      Map<Integer, kino> KinoRepo = new HashMap<>();
      try {
        kino kino1 = gson.fromJson(kino, kino.class);
        KinoRepo.put(kino1.getKinoID(), kino1);
        return new ResponseEntity<>(KinoRepo.values(), HttpStatus.OK);
      } ca-tch (Exception e) {
      }
      return new ResponseEntity<>(KinoRepo.values(), HttpStatus.BAD_REQUEST);
    }

     **/

    //Not working properly
    public void freieSitze(int kino, int film,  String vorstellung1, String vorstellung2, String vorstellung3){
        Map <String, Object> docData = new HashMap<>();

        ApiFuture <QuerySnapshot> docRef = db.collection("Kino").document(""+kino)
                .collection("HatSaele").document(kino + "_1")
                .collection("HatSitze").get();
        System.out.println(docRef);

        String [] vorstellungen = {
                vorstellung1, vorstellung2, vorstellung3
        };
        System.out.println(vorstellungen[0] + " ---- " + vorstellungen [1] + " --- " + vorstellungen [2]);

        try {

            List<QueryDocumentSnapshot> documents = docRef.get().getDocuments();

            for (int k = 0; k <= 2; k++) {
                System.out.println("CHECKPOINT 2");
                for (QueryDocumentSnapshot document : documents) {
                   // System.out.println(document.getId() + " => " + document.getData());

                    String [] sitzname = document.getId().split("_");

                    System.out.println("__________________________________");
                    System.out.println("SitzID: " + vorstellungen[k] + "_" + sitzname[2]);

                    db.collection("Kino").document("" + kino)
                            .collection("spieltFilme").document("" + film)
                            .collection("Vorstellungen").document("" + vorstellungen[k])
                            .collection("FreieSitze").document(vorstellungen[k] + "_" + sitzname[2]).set(document.getData());

                }
            }

            for (int k = 0; k <= 2; k++) {
                System.out.println("CHECKPOINT 2");
                for (QueryDocumentSnapshot document : documents) {
                    // System.out.println(document.getId() + " => " + document.getData());

                    String [] sitzname = document.getId().split("_");

                    docData.put("sitzID", vorstellungen[k] + "_" + sitzname[2]);

                    System.out.println("__________________________________");
                    System.out.println("SitzID: " + vorstellungen[k] + "_" + sitzname[2]);

                    db.collection("Kino").document(""+kino)
                            .collection("spieltFilme").document(""+film)
                            .collection("Vorstellungen").document(""+vorstellungen[k])
                            .collection("FreieSitze").document(vorstellungen[k] + "_" + sitzname[2]).update(docData);
                }
            }

        } catch (InterruptedException e){
            e.printStackTrace();
        } catch (ExecutionException e){
            e.printStackTrace();
        }
        System.out.println("--END--");
    }

    public void updateFilmAcht(){
        DocumentReference docRef = db.collection("Filme").document("8");

        ApiFuture<DocumentSnapshot> future = docRef.get();

        try {
            DocumentSnapshot document = future.get();
            if (document.exists()) {
                System.out.println("Document data: " + document.getData());

                db.collection("Kino").document("2").collection("spieltFilme").document("8").update(document.getData());

            } else {
                System.out.println("No such Document");
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        } catch (ExecutionException e){
            e.printStackTrace();
        }
    }

    public void updateFilm(int film, int kino){
        String link;
        Map<String, Object> docData = new HashMap<>();

        switch (film){
            case 1:
                // ES
                link = "https://m.media-amazon.com/images/M/MV5BYWRiODMyYTgtMjBlZi00NGVmLThmZGYtNjkwOTcyNTEzM2IyXkEyXkFqcGdeQXVyODIyOTEyMzY@._V1_SY1000_CR0,0,706,1000_AL_.jpg";
                docData.put("bildLink", link);
                db.collection("Filme").document(""+film).update(docData);
                db.collection("Kino").document(kino+"").collection("spieltFilme").document(film+"").update(docData);
                break;
            case 2:
                //Joker
                link = "https://m.media-amazon.com/images/M/MV5BZjc4MTE3OTktZjBiOC00ZGQ1LTkzZjctMjdhYTUxYjE0ZWFhXkEyXkFqcGdeQXVyODc0OTEyNDU@._V1_.jpg";
                docData.put("bildLink", link);
                db.collection("Filme").document(""+film).update(docData);
                db.collection("Kino").document(kino+"").collection("spieltFilme").document(film+"").update(docData);
                break;
            case 3:
                //downtown abbey
                link = "MV5BY2U1NmIwYzgtNjFkOS00YWUxLTg0YTMtZmE5NTA3YjRmY2NlXkEyXkFqcGdeQXVyNTA4NzY1MzY";
                docData.put("bildLink", link);
                db.collection("Filme").document(""+film).update(docData);
                db.collection("Kino").document(kino+"").collection("spieltFilme").document(film+"").update(docData);
                break;
            case 4:
                //gemini man
                link = "https://m.media-amazon.com/images/M/MV5BZjM4NzQ3YzEtMzlkNS00NDg2LTkwOGUtNGM5ZWEyYjUyNGU4XkEyXkFqcGdeQXVyODIyOTEyMzY@._V1_SY1000_CR0,0,674,1000_AL_.jpg";
                docData.put("bildLink", link);
                db.collection("Filme").document(""+film).update(docData);
                db.collection("Kino").document(kino+"").collection("spieltFilme").document(film+"").update(docData);
                break;
            case 5:
                // eine ganz heiße nummer 2.0
                link = "https://m.media-amazon.com/images/M/MV5BNzgyN2FlZTQtODJlNC00M2Q2LWFmM2ItNjVjYjkwYjJhYmVlXkEyXkFqcGdeQXVyMDU5MDEyMA@@._V1_.jpg";
                docData.put("bildLink", link);
                db.collection("Filme").document(""+film).update(docData);
                db.collection("Kino").document(kino+"").collection("spieltFilme").document(film+"").update(docData);
                break;
            case 6:
                //everest - ein yeti will hoch hinaus
                link = "8Q7OY-NcTZM";
                docData.put("bildLink", link);
                db.collection("Filme").document(""+film).update(docData);
                db.collection("Kino").document(kino+"").collection("spieltFilme").document(film+"").update(docData);
                break;
            case 7:
                //ich war noch niemals in NY
                link = "oPkzUhsRrGc";
                docData.put("bildLink", link);
                db.collection("Filme").document(""+film).update(docData);
                db.collection("Kino").document(kino+"").collection("spieltFilme").document(film+"").update(docData);
                break;
            case 8:
                //dem horizont so nah
                link = "Xkxh6mrA5kI";
                docData.put("bildLink", link);
                db.collection("Filme").document(""+film).update(docData);
                db.collection("Kino").document(kino+"").collection("spieltFilme").document(film+"").update(docData);
                break;
            case 9:
                //after teh wedding
                link = "M9I52xL3DPQ";
                docData.put("bildLink", link);
                db.collection("Filme").document(""+film).update(docData);
                db.collection("Kino").document(kino+"").collection("spieltFilme").document(film+"").update(docData);
                break;
            case 10:
                //maleficint
                link = "xlB_ZwOF3Gk";
                docData.put("bildLink", link);
                db.collection("Filme").document(""+film).update(docData);
                db.collection("Kino").document(kino+"").collection("spieltFilme").document(film+"").update(docData);
                break;
            case 11:
                //parasite
                link = "9qvaE99iMR0";
                docData.put("bildLink", link);
                db.collection("Filme").document(""+film).update(docData);
                db.collection("Kino").document(kino+"").collection("spieltFilme").document(film+"").update(docData);
                break;
            case 12:
                //das kapitel im 21. jahrhundert
                link = "zd7whXnRLbI";
                docData.put("bildLink", link);
                db.collection("Filme").document(""+film).update(docData);
                db.collection("Kino").document(kino+"").collection("spieltFilme").document(film+"").update(docData);
                break;
        }
        System.out.println("END");
    }

    public void addWerbung(int kino, int saal1, int saal2, int saal3, int film){

        Map <String, Object> docData = new HashMap<>();

        int vorstellung = 1;
        long werbungID = 1;

        int [] saal = {saal1, saal2, saal3};
        long [] dauer = {3, 4, 4, 3, 4, 3, 3, 3, 3};
        String [] name = {"Süddeutsche Zeitung", "M&M's", "TK MAX", "Magnum", "Die Addams Family", "Das perfekte Geheimnis", "Star Wars 9: der Aufstieg der Skywalkers", "Die fantastische Reise des Dr. Dolittle", "Zombieland 2: Doppelt hält besser"};

        for (int k = 0; k < saal.length; k++) {
            werbungID = 1;
            for (int i = 0; i < name.length; i++) {
                docData.put("werbungID", kino + "_" + saal[k]+"_" + film + "_" + vorstellung + "_" + werbungID);
                docData.put("name", name[i]);
                docData.put("dauer", dauer[i]);
                db.collection("Kino").document(kino + "")
                        .collection("spieltFilme").document(film + "")
                        .collection("Vorstellungen").document(kino + "_" + saal[k] + "_" + film + "_" + vorstellung)
                        .collection("Werbung").document(kino + "_" + saal[k] + "_" + film + "_" + vorstellung + "_" + (werbungID)).set(docData);
                werbungID++;
            }
            vorstellung++;
        }

        System.out.println("END");
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
    public void addVorstellungen(int kino, int film) {
        //Vorstellungen Kino 1
        Map <String, Object> vorstellung1 = new HashMap<>();
        Map <String, Object> vorstellung2 = new HashMap<>();
        Map <String, Object> vorstellung3 = new HashMap<>();
        String saal1, saal2, saal3, vorführung1, vorführung2, vorführung3, zeit1, zeit2, zeit3;
        int dauer;

        dauer = 1;
        saal1 = ""; saal2 = ""; saal3 = "";
        vorführung3 = ""; vorführung2 = "";  vorführung1 = "";
        zeit1 = ""; zeit2 = ""; zeit3 = "";

        if(film == 1){

            dauer = 170;

            if (kino == 3){
                saal1 = "3_1"; vorführung1 = "3_1_1_1"; zeit1 = "22:00";
                saal2 = "3_3"; vorführung2 = "3_3_1_2"; zeit2 = "21:30";
                saal3 = "3_4"; vorführung3 = "3_4_1_3"; zeit3 = "20:30";
            }

        } else if (film == 2){

            dauer = 122;

             if (kino == 3){
                saal1 = "3_1"; vorführung1 = "3_1_2_1"; zeit1 = "10:30";
                saal2 = "3_2"; vorführung2 = "3_2_2_2"; zeit2 = "09:30";
                saal3 = "3_4"; vorführung3 = "3_4_2_3"; zeit3 = "14:30";
            }

        } else if (film == 3){

            dauer = 122;

            if (kino == 2){
                saal1 = "2_1"; vorführung1 = "2_1_3_1"; zeit1 = "09:00";
                saal2 = "2_2"; vorführung2 = "2_2_3_2"; zeit2 = "12:00";
                saal3 = "2_3"; vorführung3 = "2_3_3_3"; zeit3 = "09:30";
            } else if (kino == 3){
                saal1 = "3_2"; vorführung1 = "3_2_3_1"; zeit1 = "12:30";
                saal2 = "3_3"; vorführung2 = "3_3_3_2"; zeit2 = "09:00";
                saal3 = "3_4"; vorführung3 = "3_4_3_3"; zeit3 = "08:30";
            }

        } else if (film == 4){

            dauer = 119;

            if(kino == 1){
                saal1 = "1_1"; vorführung1 = "1_1_4_1"; zeit1 = "13:30";
                saal2 = "1_2"; vorführung2 = "1_2_4_2"; zeit2 = "16:00";
                saal3 = "1_4"; vorführung3 = "1_4_4_3"; zeit3 = "18:00";
            }

        } else if (film == 5){

            dauer = 91;

            if (kino == 1){
                saal1 = "1_1"; vorführung1 = "1_1_5_1"; zeit1 = "10:00";
                saal2 = "1_4"; vorführung2 = "1_4_5_2"; zeit2 = "11:00";
                saal3 = "1_3"; vorführung3 = "1_3_5_3"; zeit3 = "18:30";
            } else if (kino == 2){
                saal1 = "2_1"; vorführung1 = "2_1_5_1"; zeit1 = "12:00";
                saal2 = "2_3"; vorführung2 = "2_3_5_2"; zeit2 = "12:30";
                saal3 = "2_4"; vorführung3 = "2_4_5_3"; zeit3 = "10:00";
            }

        } else if (film == 6){

            dauer = 97;

            if (kino == 2){
                saal1 = "2_1"; vorführung1 = "2_1_6_1"; zeit1 = "15:00";
                saal2 = "2_2"; vorführung2 = "2_2_6_2"; zeit2 = "09:30";
                saal3 = "2_4"; vorführung3 = "2_4_6_3"; zeit3 = "13:00";
            } else if (kino == 3){
                saal1 = "3_1"; vorführung1 = "3_1_6_1"; zeit1 = "17:00";
                saal2 = "3_3"; vorführung2 = "3_3_6_2"; zeit2 = "15:00";
                saal3 = "3_4"; vorführung3 = "3_4_6_3"; zeit3 = "12:00";
            }

        } else if (film == 7){

            dauer = 128;

            if (kino == 1){
                saal1 = "1_3"; vorführung1 = "1_3_7_1"; zeit1 = "08:30";
                saal2 = "1_2"; vorführung2 = "1_2_7_2"; zeit2 = "13:30";
                saal3 = "1_1"; vorführung3 = "1_1_7_3"; zeit3 = "20:00";
            } else if (kino == 3){
                saal1 = "3_1"; vorführung1 = "3_1_7_1"; zeit1 = "14:00";
                saal2 = "3_2"; vorführung2 = "3_2_7_2"; zeit2 = "19:00";
                saal3 = "3_4"; vorführung3 = "3_4_7_3"; zeit3 = "17:30";
            }

        } else if (film == 8){

            dauer = 117;

            if (kino == 1){
                saal1 = "1_2"; vorführung1 = "1_2_8_1"; zeit1 = "9:30";
                saal2 = "1_3"; vorführung2 = "1_3_8_2"; zeit2 = "12:00";
                saal3 = "1_4"; vorführung3 = "1_4_8_3"; zeit3 = "08:00";
            } else if (kino == 2){
                saal1 = "2_1"; vorführung1 = "2_1_8_1"; zeit1 = "17:30";
                saal2 = "2_3"; vorführung2 = "2_3_8_2"; zeit2 = "15:00";
                saal3 = "2_4"; vorführung3 = "2_4_8_3"; zeit3 = "20:30";
            }

        } else if (film == 9){

            dauer = 113;

            if (kino == 1){
                saal1 = "1_1"; vorführung1 = "1_1_9_1"; zeit1 = "16:30";
                saal2 = "1_3"; vorführung2 = "1_3_9_2"; zeit2 = "15:30";
                saal3 = "1_4"; vorführung3 = "1_4_9_3"; zeit3 = "19:00";
            } else if (kino == 2){
                saal1 = "2_1"; vorführung1 = "2_1_9_1"; zeit1 = "21:00";
                saal2 = "2_2"; vorführung2 = "2_2_9_2"; zeit2 = "15:00";
                saal3 = "2_3"; vorführung3 = "2_3_9_3"; zeit3 = "18:30";
            }

        } else if (film == 10){

            dauer = 118;

            if (kino == 2){
                saal1 = "2_2"; vorführung1 = "2_2_10_1"; zeit1 = "17:30";
                saal2 = "2_3"; vorführung2 = "2_3_10_2"; zeit2 = "21:30";
                saal3 = "2_4"; vorführung3 = "2_4_10_3"; zeit3 = "16:00";
            } else if (kino == 3){
                saal1 = "3_1"; vorführung1 = "3_1_10_1"; zeit1 = "19:00";
                saal2 = "3_2"; vorführung2 = "3_2_10_2"; zeit2 = "15:30";
                saal3 = "3_3"; vorführung3 = "3_3_10_3"; zeit3 = "12:00";
            }

        } else if (film == 11){

            dauer = 132;

            if (kino == 1){
                saal1 = "1_2"; vorführung1 = "1_2_11_1"; zeit1 = "09:30";
                saal2 = "1_3"; vorführung2 = "1_3_11_2"; zeit2 = "08:30";
                saal3 = "1_4"; vorführung3 = "1_4_11_3"; zeit3 = "10:00";
            }

        } else if (film == 12){

            dauer = 102;

            if (kino == 3){
                saal1 = "3_1"; vorführung1 = "3_1_12_1"; zeit1 = "08:00";
                saal2 = "3_2"; vorführung2 = "3_2_12_2"; zeit2 = "22:30";
                saal3 = "3_3"; vorführung3 = "3_3_12_3"; zeit3 = "18:00";
            }
        }

        vorstellung1.put("dreiD", false);
        vorstellung1.put("gesamtdauer", dauer+30);
        vorstellung1.put("gesamtpreis", 7.0 );
        vorstellung1.put("grunddauer", dauer);
        vorstellung1.put("grundpreis", 7.0);
        vorstellung1.put("saalnummer", saal1);
        vorstellung1.put("vorführungsID", vorführung1);
        vorstellung1.put("zeitpunkt", "2019-11-04/"+zeit1);

        vorstellung2.put("dreiD", false);
        vorstellung2.put("gesamtdauer", dauer+30);
        vorstellung2.put("gesamtpreis", 7.0 );
        vorstellung2.put("grunddauer", dauer);
        vorstellung2.put("grundpreis", 7.0);
        vorstellung2.put("saalnummer", saal2);
        vorstellung2.put("vorführungsID", vorführung2);
        vorstellung2.put("zeitpunkt", "2019-11-04/" + zeit2);

        vorstellung3.put("dreiD", false);
        vorstellung3.put("gesamtdauer", dauer+30);
        vorstellung3.put("gesamtpreis", 7.0 );
        vorstellung3.put("grunddauer", dauer);
        vorstellung3.put("grundpreis", 7.0);
        vorstellung3.put("saalnummer", saal3);
        vorstellung3.put("vorführungsID", vorführung3);
        vorstellung3.put("zeitpunkt", "2019-11-04/" + zeit3);

        db.collection("Kino").document(kino + "").collection("spieltFilme").document(film+"").collection("Vorstellungen").document(vorführung1).set(vorstellung1);
        db.collection("Kino").document(kino + "").collection("spieltFilme").document(film+"").collection("Vorstellungen").document(vorführung2).set(vorstellung2);
        db.collection("Kino").document(kino + "").collection("spieltFilme").document(film+"").collection("Vorstellungen").document(vorführung3).set(vorstellung3);


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
