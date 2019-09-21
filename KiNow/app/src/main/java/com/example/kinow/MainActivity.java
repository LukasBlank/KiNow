package com.example.kinow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;

import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = ""; //Variable die beim Wechseln der Ansicht übergeben wird

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connect();
    }//onCreate

    public void sendMessage (View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText Textfeld = (EditText) findViewById(R.id.Textfeld);
        String message = Textfeld.getText().toString();
        intent.putExtra(EXTRA_MESSAGE , message);
        startActivity(intent);
    }//sendMessage

    private void connect (){
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("org.postgresql.Driver");
            Connection c = null;
            c = DriverManager.getConnection("jdbc:postgresql://10.0.2.2:5432/KiNow","postgres","postgres");
            //^^ Versuch mit Datenbank zu verbinden
            EditText e = (EditText) findViewById(R.id.Textfeld);
            e.setText("EASY");
        } catch (Exception e){
            EditText ex = (EditText) findViewById(R.id.Textfeld);
            ex.setText(e.fillInStackTrace().toString());
        }
    }//connect


}//class




