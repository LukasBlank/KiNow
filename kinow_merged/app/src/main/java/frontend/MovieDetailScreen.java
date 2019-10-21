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
import backend.classes.Kino;
import backend.classes.Vorführung;
import backend.connections.Requests;

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
    Film film;
    Kino kino;
    TextView movieRating;


    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail_screen);

        film = (Film) getIntent().getSerializableExtra("filmSelect");
        kino = (Kino) getIntent().getSerializableExtra("kinoSelect");

        Requests r = new Requests();
        ArrayList<Vorführung> vorführungen = r.getVor(kino.getKinoID(),film.getFilmID());

        movieTitle = findViewById(R.id.movieTitleDetail);
        movieTitle.setText(film.getTitel());

        movieGenre = findViewById(R.id.genre);
        String genres = "";
        for(String g : film.getGenres()){
            genres += g + ", ";
        }//for
        if (genres.length()>0)genres = genres.substring(0,genres.lastIndexOf(','));
        movieGenre.setText(genres);

        movieRating = findViewById(R.id.rating);
        movieRating.setText(filmSelect.getBewertung()+"/10 Sternen");

        movieFSK = findViewById(R.id.fsk);
        movieFSK.setText("FSK "+film.getFsk());

        movieLength = findViewById(R.id.duration);
        movieLength.setText(film.getDauer()+" Min");

        movieDescription = findViewById(R.id.movieDescDetail);
        movieDescription.setText(film.getBeschreibung());

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
