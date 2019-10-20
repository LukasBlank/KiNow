package fm.feuermania.dennis.kinow_test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MovieDetailScreen extends AppCompatActivity {

    TextView timeOne;
    TextView timeTwo;
    TextView timeThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail_screen);

        timeOne = findViewById(R.id.time_one);
        timeTwo = findViewById(R.id.time_two);
        timeThree = findViewById(R.id.time_three);

        timeOne.setOnClickListener(onClickListener);
        timeTwo.setOnClickListener(onClickListener);
        timeThree.setOnClickListener(onClickListener);
    }

    // Dennis Notiz: Evtl. durch if Anweisung ersetzen, + Book Button sool nur gehen wenn eine der Zeiten ausgewählt ist
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()){

                case R.id.time_one:
                    timeOne.setBackgroundColor(getResources().getColor(R.color.red));
                    timeTwo.setBackgroundColor(getResources().getColor(R.color.transparent));
                    timeThree.setBackgroundColor(getResources().getColor(R.color.transparent));
                    break;

                case R.id.time_two:
                    timeOne.setBackgroundColor(getResources().getColor(R.color.transparent));
                    timeTwo.setBackgroundColor(getResources().getColor(R.color.red));
                    timeThree.setBackgroundColor(getResources().getColor(R.color.transparent));
                    break;

                case R.id.time_three:
                    timeOne.setBackgroundColor(getResources().getColor(R.color.transparent));
                    timeTwo.setBackgroundColor(getResources().getColor(R.color.transparent));
                    timeThree.setBackgroundColor(getResources().getColor(R.color.red));
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

}
