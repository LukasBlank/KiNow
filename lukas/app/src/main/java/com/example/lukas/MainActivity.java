package com.example.lukas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;
import java.util.function.ToLongBiFunction;

public class MainActivity extends AppCompatActivity {

    EditText ETvorname;
    EditText ETnachname;
    FirebaseFirestore db;
    TextView TVmeldung;
    DocumentReference lukasRef;
    CollectionReference nutzerRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ETvorname = findViewById(R.id.ETvorname);
        ETnachname = findViewById(R.id.ETnachname);
        db = FirebaseFirestore.getInstance();
        TVmeldung = findViewById(R.id.TVstatus);

    }


    public void createUser (View view){
        String vorname = ETvorname.getText().toString();
        String nachname = ETnachname.getText().toString();
        if (vorname.length()==0) ETvorname.setHint("Bitte ausfüllen.");
        if (nachname.length()==0) ETnachname.setHint("Bitte ausfüllen.");
        if (vorname.length()!=0 && nachname.length()!=0){
            Map<String, Object> doc = new HashMap<>();
            doc.put("Vorname",vorname);
            doc.put("Nachname",nachname);
            db.collection("Nutzer").document("Lukas").set(doc)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(MainActivity.this, "Complete.", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this, "Error.", Toast.LENGTH_SHORT).show();
                    Log.d("MainActivity",e.getMessage());
                }
            });
        }//else
    }//createUser

    public void getUser (View view){
        lukasRef = db.collection("Nutzer").document("Lukas");
        String vorname = ETvorname.getText().toString();
        String nachname = ETnachname.getText().toString();
            lukasRef.get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()){
                                TVmeldung.setText(documentSnapshot.getString("Vorname")+ "\n" + documentSnapshot.getString("Nachname"));
                            } else
                                Toast.makeText(MainActivity.this, "Document does not exist.", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this, "Error.", Toast.LENGTH_SHORT).show();
                    Log.d("MainActivity",e.getMessage());
                }
            });
    }//deleteUser

    public void getMovies (View view){
        nutzerRef = db.collection("Nutzer");
        nutzerRef.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                            ;
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                ;
            }
        });
    }//getFilme


}//class
