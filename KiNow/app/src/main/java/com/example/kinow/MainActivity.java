package com.example.kinow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = ""; //Variable die beim Wechseln der Ansicht übergeben wird

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }//onCreate

    public void sendMessage (View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText Textfeld = (EditText) findViewById(R.id.Textfeld);
        String message = Textfeld.getText().toString();
        intent.putExtra(EXTRA_MESSAGE , message);
        startActivity(intent);
    }//sendMessage


}//class




