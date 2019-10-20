package frontend;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import backend.classes.Film;

public class MovieDetailScreen extends AppCompatActivity{

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail_screen);
    }//onCreate

    public void watchTrailer(View view) {
        Intent intent = new Intent(MovieDetailScreen.this, MovieTrailer.class);
        startActivity(intent);
    }

}//class
