package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.WriteResult;
import com.google.cloud.firestore.v1.FirestoreAdminClient;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.internal.NonNull;
import com.google.gson.Gson;
import java.io.FileInputStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class DemoApplication {


  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @RestController
  public static class SimpleController {

    //   @Value("Beste App")
    String appName = "Best App";
    String ZweiterName = "Moiga";

    @GetMapping("/")
    public String homePage(Model model) {
      model.addAttribute("appName", appName);
      return "home";
    }

    @GetMapping("/Geilo")
    public String SecondPage(Model model) {
      model.addAttribute("bestName", ZweiterName);
      return "second";
    }

    @GetMapping("/Drei")
    public String ThirdPage(Model model) {
      model.addAttribute("beitra", "Das ist einfach");
      return "third";
    }

    private static Map<Integer, Nutzer> productRepo = new HashMap<>();

    static {
      Nutzer honey = new Nutzer(1, "nils", "falk", new Date(1999), "nilsfalkneu@gmail.com",
          "Hallo");
      productRepo.put(honey.getNid(), honey);

      Nutzer almond = new Nutzer(2, "Hans", "Peter", new Date(100000), "lel@atos.com", "NEu");
      productRepo.put(almond.getNid(), almond);
    }

    @RequestMapping(value = "/Person")
    public ResponseEntity<Object> getPerson() {
      return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
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

    @RequestMapping(value = "/Data")
    public void getData() {
      try
      {
        FileInputStream serviceAccount =
            new FileInputStream("C:/Users/A704600/Documents/serviceAccountKey.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .setDatabaseUrl("https://kinow-46514.firebaseio.com")
            .build();

         FirebaseApp.initializeApp(options);
      }catch(Exception e){}

      Gson gson = new Gson();
      Firestore db = FirestoreClient.getFirestore();
      DocumentReference docRef = db.collection("Nutzer").document("4");
// Add document data with an additional field ("middle")
      Map<String, Object> data = new HashMap<>();
      data.put("E-Mail", "Alan@de");
      data.put("Passwort", "passwort");
      data.put("Vorname", "Turing");

      ApiFuture<WriteResult> result = docRef.set(data);
      try {
        System.out.println("Update time : " + result.get().getUpdateTime());
      } catch (Exception e) {}
    }
  }
}
