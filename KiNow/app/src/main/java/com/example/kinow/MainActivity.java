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

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = ""; //statische Varible, welche bei Seitenwechsel übergeben wird,

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




