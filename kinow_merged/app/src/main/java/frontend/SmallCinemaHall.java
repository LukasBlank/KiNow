package frontend;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class SmallCinemaHall extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.small_cinema_hall);

    }

    public void colorSeat(View v){

        View test = v.getRootView();
        ColorDrawable viewColor = (ColorDrawable) test.getBackground();
        int colorId = viewColor.getColor();
        v.setBackgroundColor(getResources().getColor(R.color.violet));
        if(colorId == R.color.violet){
            v.setBackgroundColor(getResources().getColor(R.color.transparent));
        }

    }


    public void bookSeats(View v2){

        Toast.makeText(SmallCinemaHall.this, "Thank you for booking this movie.", Toast.LENGTH_LONG).show();

        // Dennis Notiz: Mind. 1 Sitz muss ausgewählt sein
        // ++ Sitze onClick: Farbe ändern, wenn nochmals geklickt wird -> Farbe wieder entfernen
        // ++ Besetzte Sitze anzeigen

    }

}
