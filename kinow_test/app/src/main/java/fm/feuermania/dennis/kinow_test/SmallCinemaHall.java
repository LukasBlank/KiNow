package fm.feuermania.dennis.kinow_test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SmallCinemaHall extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.small_cinema_hall);
    }

    public void bookSeats(){

        // Dennis Notiz: Mind. 1 Sitz muss ausgewählt sein
        // ++ Sitze onClick: Farbe ändern, wenn nochmals geklickt wird -> Farbe wieder entfernen
        // ++ Besetzte Sitze anzeigen

    }

}
