package frontend;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MovieDetailScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail_screen);
    }

    public void watchTrailer(View view) {
        Intent intent = new Intent(MovieDetailScreen.this, MovieTrailer.class);
        startActivity(intent);
    }

}
