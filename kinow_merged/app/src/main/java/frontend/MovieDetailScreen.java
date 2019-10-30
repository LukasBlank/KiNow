package frontend;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

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
    ImageView movieImage;
    Film film;
    Kino kino;
    Nutzer nutzer;
    TextView movieHall;TextView movieDate;
    TextView movieRegie;
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

        movieActors = findViewById(R.id.actor);
        String actors = "";
        for(String a : film.getDarsteller()){
            actors += a + ", ";
        }//for
        if (actors.length()>0)actors = actors.substring(0,actors.lastIndexOf(','));
        movieActors.setText(actors);

        movieRegie = findViewById(R.id.regisseur);
        String regie = "";
        for(String reg : film.getRegie()){
            regie += reg + ", ";
        }//for
        if (regie.length()>0)regie = regie.substring(0,regie.lastIndexOf(','));
        movieRegie.setText(regie);

        movieRating = findViewById(R.id.rating);
        movieRating.setText(film.getBewertung()+"/10★");

        movieFSK = findViewById(R.id.fsk);
        movieFSK.setText("FSK "+film.getFsk());

        movieLength = findViewById(R.id.duration);
        movieLength.setText(film.getDauer()+" Minuten");

        movieDescription = findViewById(R.id.movieDescDetail);
        movieDescription.setText(film.getBeschreibung());

        movieImage = findViewById(R.id.movieImageDetail);
        String imgURL = film.getBildLink();
        Picasso.get().load(imgURL).into(movieImage);

        //movieImage.setImageBitmap(bitmap);

        movieHall = findViewById(R.id.hall);
        movieDate = findViewById(R.id.date);

        timeOne = findViewById(R.id.time_one);
        timeTwo = findViewById(R.id.time_two);
        timeThree = findViewById(R.id.time_three);
        btnBuchen = findViewById(R.id.button);

        if (vorführungen!=null){
            if (vorführungen.size()>2){
                timeOne.setText(vorführungen.get(0).getZeit());
                timeTwo.setText(vorführungen.get(1).getZeit());
                timeThree.setText(vorführungen.get(2).getZeit());
            }//then
        }//then

        //wenn ein Kino gewählt wurde und es passende vorführungen gibt, dass zeige die zeiten dazu an
        if (kino.getKinoID()!=0 && vorführungen!=null){
            timeOne.setVisibility(View.VISIBLE);
            timeTwo.setVisibility(View.VISIBLE);
            timeThree.setVisibility(View.VISIBLE);
            //weise den angezeigten componenten und dem buchen button den onClickListener zu
            timeOne.setOnClickListener(onClickListener);
            timeTwo.setOnClickListener(onClickListener);
            timeThree.setOnClickListener(onClickListener);
            btnBuchen.setOnClickListener(onClickListener);
        }//then

    }//onCreate

    public Bitmap getBitMapFromURL(String src){
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Bitmap getBitMapFromURLHttps(String src){
        try {
            URL url = new URL(src);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

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
                    movieDate.setText(vorführung.getDatum());
                    movieHall.setText("Hall " + vorführung.getSaalnummer());
                    break;

                case R.id.time_two:
                    timeOne.setBackground(getResources().getDrawable(R.drawable.button_border));
                    timeTwo.setBackground(getResources().getDrawable(R.drawable.button_filled));
                    timeThree.setBackground(getResources().getDrawable(R.drawable.button_border));
                    btnBuchen.setVisibility(View.VISIBLE);
                    vorführung = vorführungen.get(1);
                    movieDate.setText(vorführung.getDatum());
                    movieHall.setText("Hall " + vorführung.getSaalnummer());
                    break;

                case R.id.time_three:
                    timeOne.setBackground(getResources().getDrawable(R.drawable.button_border));
                    timeTwo.setBackground(getResources().getDrawable(R.drawable.button_border));
                    timeThree.setBackground(getResources().getDrawable(R.drawable.button_filled));
                    btnBuchen.setVisibility(View.VISIBLE);
                    vorführung = vorführungen.get(2);
                    movieDate.setText(vorführung.getDatum());
                    movieHall.setText("Hall " +  vorführung.getSaalnummer());
                    break;

                case R.id.button:
                    if(vorführung==null){
                        Toast.makeText(getBaseContext(), "Bitte Vorführung auswählen.", Toast.LENGTH_SHORT).show();
                    }//then
                    else {
                        Intent intent = new Intent(MovieDetailScreen.this, SmallCinemaHall.class);
                        intent.putExtra("nutzer",nutzer);
                        intent.putExtra("vorführung",vorführung);
                        startActivity(intent);
                    }//else
                    break;
            }//switch
        }
    };

    public void watchTrailer(View view) {
        Intent intent = new Intent(this, MovieTrailer.class);
        intent.putExtra("link",film.getLink());
        startActivity(intent);
    }//watchTrailer

    private class DownLoadImageTask extends AsyncTask<String,Void, Bitmap> {
        ImageView imageView;

        public DownLoadImageTask(ImageView imageView){
            this.imageView = imageView;
        }

        /*
            doInBackground(Params... params)
                Override this method to perform a computation on a background thread.
         */
        protected Bitmap doInBackground(String...urls){
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try{
                InputStream is = new URL(urlOfImage).openStream();
                /*
                    decodeStream(InputStream is)
                        Decode an input stream into a bitmap.
                 */
                logo = BitmapFactory.decodeStream(is);
            }catch(Exception e){ // Catch the download exception
                e.printStackTrace();
            }
            return logo;
        }

        /*
            onPostExecute(Result result)
                Runs on the UI thread after doInBackground(Params...).
         */
        protected void onPostExecute(Bitmap result){
            imageView.setImageBitmap(result);
        }
    }
}//class
