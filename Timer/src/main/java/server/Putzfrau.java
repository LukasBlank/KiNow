package server;


import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import java.io.FileInputStream;
import java.net.URL;

import java.util.List;
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
    int i = 1;
    while (true){
      try {
        Thread.sleep(2000);
        cleanup();
      } catch (InterruptedException e) {
        e.printStackTrace();
        System.out.println("Error");
      }
    }//true

  }//main

  private static void cleanup (){
    long grenze = System.currentTimeMillis();
    ApiFuture<QuerySnapshot> query = db.collection("Nutzer").get();
    try {
      QuerySnapshot querySnapshot = query.get();
      List<QueryDocumentSnapshot> nutzer = querySnapshot.getDocuments();
      for (DocumentSnapshot document : nutzer){
        if (!document.getId().equals("0")){
          query = db.collection("Nutzer").document(document.getId()).collection("Reservierungen").get();
          querySnapshot = query.get();
          List<QueryDocumentSnapshot> reservierungen = querySnapshot.getDocuments();
          if (reservierungen.size()>0){
            for (DocumentSnapshot reservierung : reservierungen){
              String 
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


}// class
