package frontend;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

import backend.classes.Film;

public class MovieDetailScreen extends AppCompatActivity implements Serializable{

    Context context;
    TextView timeOne;
    TextView timeTwo;
    TextView timeThree;
    TextView movieTitle;
    TextView movieGenre;
    TextView movieLength;
    TextView movieActors;
    TextView movieFSK;
    TextView movieDescription;
    TextView movieTrailer;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail_screen);


        Film filmSelect = (Film) getIntent().getSerializableExtra("filmSelect");

        movieTitle = findViewById(R.id.movieTitleDetail);
        movieTitle.setText(filmSelect.getTitel());

        movieGenre = findViewById(R.id.genre);
        String genres = "";
        for(int i=0;i<filmSelect.getGenres().size();i++){
            genres = genres+filmSelect.getGenres().get(i);
            if(i<filmSelect.getGenres().size()-1){
                genres = genres+", ";
            }
        }
        movieGenre.setText(genres);

        movieLength = findViewById(R.id.duration);
        movieLength.setText(""+filmSelect.getDauer());

        movieLength = findViewById(R.id.rating);
        movieLength.setText(filmSelect.getBewertung()+"/10 Sternen");

        movieFSK = findViewById(R.id.fsk);
        movieFSK.setText("Ab "+filmSelect.getFsk()+" Jahren");

        movieLength = findViewById(R.id.duration);
        movieLength.setText(filmSelect.getDauer()+" Minuten");

        movieDescription = findViewById(R.id.movieDescDetail);
        movieDescription.setText(filmSelect.getBeschreibung());

        timeOne = findViewById(R.id.time_one);
        timeTwo = findViewById(R.id.time_two);
        timeThree = findViewById(R.id.time_three);

        timeOne.setOnClickListener(onClickListener);
        timeTwo.setOnClickListener(onClickListener);
        timeThree.setOnClickListener(onClickListener);
    }//onCreate

    // Dennis Notiz: Evtl. durch if Anweisung ersetzen, + Book Button sool nur gehen wenn eine der Zeiten ausgewählt ist
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()){

                case R.id.time_one:
                    timeOne.setBackground(getResources().getDrawable(R.drawable.button_filled));
                    timeTwo.setBackground(getResources().getDrawable(R.drawable.button_border));
                    timeThree.setBackground(getResources().getDrawable(R.drawable.button_border));
                    break;

                case R.id.time_two:
                    timeOne.setBackground(getResources().getDrawable(R.drawable.button_border));
                    timeTwo.setBackground(getResources().getDrawable(R.drawable.button_filled));
                    timeThree.setBackground(getResources().getDrawable(R.drawable.button_border));
                    break;

                case R.id.time_three:
                    timeOne.setBackground(getResources().getDrawable(R.drawable.button_border));
                    timeTwo.setBackground(getResources().getDrawable(R.drawable.button_border));
                    timeThree.setBackground(getResources().getDrawable(R.drawable.button_filled));
                    break;
            }
        }
    };

    public void watchTrailer(View view) {
        Intent intent = new Intent(MovieDetailScreen.this, MovieTrailer.class);
        startActivity(intent);
    }

    public void bookMovie(View view) {
        Intent intent = new Intent(MovieDetailScreen.this, SmallCinemaHall.class);
        startActivity(intent);
    }

}//class
