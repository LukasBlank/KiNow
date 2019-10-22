package frontend;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

import backend.classes.Film;
import backend.classes.Kino;
import backend.classes.Nutzer;
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
    Nutzer nutzer;
    ArrayList<Vorführung> vorführungen;
    Vorführung vorführung;
    TextView movieRating;
    Button btnBuchen;


    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail_screen);

        film = (Film) getIntent().getSerializableExtra("filmSelect");
        kino = (Kino) getIntent().getSerializableExtra("kinoSelect");
        nutzer = (Nutzer) getIntent().getSerializableExtra("nutzer");

        Requests r = new Requests();
        vorführungen = r.getVor(kino.getKinoID(),film.getFilmID());


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
        movieRating.setText(film.getBewertung()+"/10 Sternen");

        movieFSK = findViewById(R.id.fsk);
        movieFSK.setText("FSK "+film.getFsk());

        movieLength = findViewById(R.id.duration);
        movieLength.setText(film.getDauer()+" Min");

        movieDescription = findViewById(R.id.movieDescDetail);
        movieDescription.setText(film.getBeschreibung());

        timeOne = findViewById(R.id.time_one);
        timeTwo = findViewById(R.id.time_two);
        timeThree = findViewById(R.id.time_three);
        btnBuchen = findViewById(R.id.button);

        if (vorführungen!=null){
            if(vorführungen.size()>2){
                timeOne.setText(vorführungen.get(0).getZeit());
                timeTwo.setText(vorführungen.get(1).getZeit());
                timeThree.setText(vorführungen.get(2).getZeit());
            }//then
        }//then

        if (kino.getKinoID()!=0&&vorführungen!=null){
            if (vorführungen.size()>2){
                timeOne.setText(vorführungen.get(0).getZeit());
                timeTwo.setText(vorführungen.get(1).getZeit());
                timeThree.setText(vorführungen.get(2).getZeit());

                timeOne.setVisibility(View.VISIBLE);
                timeTwo.setVisibility(View.VISIBLE);
                timeThree.setVisibility(View.VISIBLE);

                timeOne.setOnClickListener(onClickListener);
                timeTwo.setOnClickListener(onClickListener);
                timeThree.setOnClickListener(onClickListener);
                btnBuchen.setOnClickListener(onClickListener);
            }//then

        }//then


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
                    btnBuchen.setVisibility(View.VISIBLE);
                    vorführung = vorführungen.get(0);
                    break;

                case R.id.time_two:
                    timeOne.setBackground(getResources().getDrawable(R.drawable.button_border));
                    timeTwo.setBackground(getResources().getDrawable(R.drawable.button_filled));
                    timeThree.setBackground(getResources().getDrawable(R.drawable.button_border));
                    btnBuchen.setVisibility(View.VISIBLE);
                    vorführung = vorführungen.get(1);
                    break;

                case R.id.time_three:
                    timeOne.setBackground(getResources().getDrawable(R.drawable.button_border));
                    timeTwo.setBackground(getResources().getDrawable(R.drawable.button_border));
                    timeThree.setBackground(getResources().getDrawable(R.drawable.button_filled));
                    btnBuchen.setVisibility(View.VISIBLE);
                    vorführung = vorführungen.get(2);
                    break;

                case R.id.button:
                    if (nutzer.getNutzerID()==-1) {
                        Toast.makeText(getBaseContext(), "Bitte zuerst anmelden.", Toast.LENGTH_SHORT).show();
                    }//then
                    else if(vorführung==null){
                        Toast.makeText(getBaseContext(), "Bitte Vorführung auswählen.", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Intent intent = new Intent(MovieDetailScreen.this, SmallCinemaHall.class);
                        intent.putExtra("nutzer",nutzer);
                        intent.putExtra("vorführung",vorführung);
                        startActivity(intent);
                    }//else
                    break;
            }
        }
    };

    public void watchTrailer(View view) {
        Intent intent = new Intent(MovieDetailScreen.this, MovieTrailer.class);
        intent.putExtra("link",film.getLink());
        startActivity(intent);
    }//watchTrailer

}//class
