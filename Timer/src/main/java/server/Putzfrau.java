package server;


import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.protobuf.Api;
import java.io.FileInputStream;
import java.net.URL;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Putzfrau {

  static Firestore db;

  public static void main(String[] args) {
    SpringApplication.run(Putzfrau.class, args);
    try {
      //Pfad muss angepasst werden ggf. in Java einfügen

      String path = "serviceAccountKey.json";
      URL url = Putzfrau.class.getClassLoader().getResource(path);

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
    cleanup();
  }//main

  private static void cleanup (){
    long grenze = System.currentTimeMillis();
    ApiFuture<QuerySnapshot> query = db.collection("Nutzer").get();
    try {
      QuerySnapshot querySnapshot = query.get();
      //alle Nutzer
      List<QueryDocumentSnapshot> nutzer = querySnapshot.getDocuments();
      for (DocumentSnapshot document : nutzer){
        if (!document.getId().equals("0")){
          query = db.collection("Nutzer").document(document.getId()).collection("Reservierungen").get();
          querySnapshot = query.get();
          List<QueryDocumentSnapshot> reservierungen = querySnapshot.getDocuments();
          if (reservierungen.size()>0){
            //alle reservierungen des nutzers
            for (DocumentSnapshot reservierung : reservierungen){
              long resZeit = reservierung.getLong("zeit");
              if (grenze-resZeit>60000){
                DocumentReference documentReference = db.collection("Nutzer").document(document.getId()).collection("Reservierungen").document(reservierung.getId());
                stonieren(documentReference);
                //documentReference.delete();
              }//then
            }//for
          }//then
        }//then
      }//for
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }//cleanup

  private static void stonieren (DocumentReference resRef){
    ApiFuture<DocumentSnapshot> res = resRef.get();
    ApiFuture<QuerySnapshot> query = resRef.collection("Sitze").get();
    try {
      DocumentSnapshot doc = res.get();
      String vorID = doc.getString("vorführungsID");
      QuerySnapshot querySnapshot = query.get();
      List<QueryDocumentSnapshot> sitze = querySnapshot.getDocuments();
      for (DocumentSnapshot sitz : sitze){
        //sitz to map und sitzID aktualisieren
        Map<String,Object> sitzMap = new HashMap<>();
        sitzMap.put("barrierefrei",sitz.getBoolean("barrierefrei"));
        sitzMap.put("loge",sitz.getBoolean("loge"));
        String ID = sitz.getString("sitzID"); ID = ID.substring(ID.lastIndexOf('_')+1);
        String sitzID = vorID + ID;
        sitzMap.put("sitzID",sitzID);
        //map in der vorführung von reserviert zu frei
        String kinoID = sitzID.substring(0,sitzID.indexOf('_'));
        String filmID = sitzID.substring(0,sitzID.indexOf('_')); filmID = filmID.substring(0,filmID.indexOf('_'));
        DocumentReference vorRef = db.collection("Kino").document(kinoID).collection("spieltFilme").document(filmID)
            .collection("Vorstellungen").document(vorID);
        //vorRef.collection("ReservierteSitze").document(sitzID).delete();
        //vorRef.collection("FreieSitze").document(sitzID).set(sitzMap);
      }//for
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }//catch
  }//stonieren


}// class
