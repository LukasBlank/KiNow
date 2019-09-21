package com.example.kinow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = ""; //Variable die beim Wechseln der Ansicht übergeben wird

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/KiNow","postgres","postgres");
            EditText e = (EditText) findViewById(R.id.Textfeld);
            e.setText("EASY");
        } catch (Exception e){
            EditText ex = (EditText) findViewById(R.id.Textfeld);
            ex.setText(e.fillInStackTrace().toString());
        }


    }//onCreate

    public void sendMessage (View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText Textfeld = (EditText) findViewById(R.id.Textfeld);
        String message = Textfeld.getText().toString();
        intent.putExtra(EXTRA_MESSAGE , message);
        startActivity(intent);
    }//sendMessage

    private void connect (){
       //toDo
    }


}//class




