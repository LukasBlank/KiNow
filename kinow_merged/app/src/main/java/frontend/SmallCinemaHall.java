package frontend;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import backend.classes.Nutzer;
import backend.classes.Sitz;
import backend.classes.Vorführung;
import backend.connections.Requests;

public class SmallCinemaHall extends AppCompatActivity {

    Boolean clicked_a1 = false, clicked_a2 = false, clicked_a3 = false, clicked_a4 = false, clicked_a5 = false, clicked_a6 = false, clicked_a7 = false, clicked_b1 = false,
            clicked_b2 = false, clicked_b3 = false, clicked_b4 = false, clicked_b5 = false, clicked_b6 = false, clicked_b7 = false, clicked_c1 = false, clicked_c2 = false,
            clicked_c3 = false, clicked_c4 = false, clicked_c5 = false, clicked_c6 = false, clicked_c7 = false, clicked_d1 = false, clicked_d2 = false, clicked_d3 = false,
            clicked_d4 = false, clicked_d5 = false, clicked_d6 = false, clicked_d7 = false, clicked_e1 = false, clicked_e2 = false, clicked_e3 = false, clicked_e4 = false,
            clicked_e5 = false, clicked_e6 = false, clicked_e7 = false, clicked_f1 = false, clicked_f2 = false, clicked_f3 = false, clicked_f4 = false, clicked_f5 = false,
            clicked_f6 = false, clicked_f7 = false, clicked_g1 = false, clicked_g2 = false, clicked_g3 = false, clicked_g4 = false, clicked_g5 = false, clicked_g6 = false,
            clicked_g7 = false;

    Vorführung vorführung;
    Nutzer nutzer;
    ArrayList<Sitz> freieSitze,belegteSitze;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.small_cinema_hall);
        nutzer = (Nutzer) getIntent().getSerializableExtra("nutzer");
        vorführung = (Vorführung) getIntent().getSerializableExtra("vorführung");

        Requests request = new Requests();
        freieSitze = request.getFreieSitze(vorführung.getVorführungsID());
        belegteSitze = request.getBelegteSitze(vorführung.getVorführungsID());
    }//onCreate


    public void bookSeats(View v2){

        Toast.makeText(SmallCinemaHall.this, "Thank you for booking this movie.", Toast.LENGTH_LONG).show();

        // Dennis Notiz: Mind. 1 Sitz muss ausgewählt sein
        // ++ Sitze onClick: Farbe ändern, wenn nochmals geklickt wird -> Farbe wieder entfernen
        // ++ Besetzte Sitze anzeigen

    }

    public void onBtnClick(View view) {

        Button btn_a1 = findViewById(R.id.a1);Button btn_a2 = findViewById(R.id.a2);Button btn_a3 = findViewById(R.id.a3);
        Button btn_a4 = findViewById(R.id.a4);Button btn_a5 = findViewById(R.id.a5);Button btn_a6 = findViewById(R.id.a6);Button btn_a7 = findViewById(R.id.a7);
        Button btn_b1 = findViewById(R.id.b1);Button btn_b2 = findViewById(R.id.b2);Button btn_b3 = findViewById(R.id.b3);
        Button btn_b4 = findViewById(R.id.b4);Button btn_b5 = findViewById(R.id.b5);Button btn_b6 = findViewById(R.id.b6);
        Button btn_b7 = findViewById(R.id.b7);Button btn_c1 = findViewById(R.id.c1);Button btn_c2 = findViewById(R.id.c2);
        Button btn_c3 = findViewById(R.id.c3);Button btn_c4 = findViewById(R.id.c4);Button btn_c5 = findViewById(R.id.c5);
        Button btn_c6 = findViewById(R.id.c6);Button btn_c7 = findViewById(R.id.c7);Button btn_d1 = findViewById(R.id.d1);
        Button btn_d2 = findViewById(R.id.d2);Button btn_d3 = findViewById(R.id.d3);Button btn_d4 = findViewById(R.id.d4);
        Button btn_d5 = findViewById(R.id.d5);Button btn_d6 = findViewById(R.id.d6);Button btn_d7 = findViewById(R.id.d7);
        Button btn_e1 = findViewById(R.id.e1);Button btn_e2 = findViewById(R.id.e2);Button btn_e3 = findViewById(R.id.e3);
        Button btn_e4 = findViewById(R.id.e4);Button btn_e5 = findViewById(R.id.e5);Button btn_e6 = findViewById(R.id.e6);
        Button btn_e7 = findViewById(R.id.e7);Button btn_f1 = findViewById(R.id.f1);Button btn_f2 = findViewById(R.id.f2);
        Button btn_f3 = findViewById(R.id.f3);Button btn_f4 = findViewById(R.id.f4);Button btn_f5 = findViewById(R.id.f5);
        Button btn_f6 = findViewById(R.id.f6);Button btn_f7 = findViewById(R.id.f7);Button btn_g1 = findViewById(R.id.g1);
        Button btn_g2 = findViewById(R.id.g2);Button btn_g3 = findViewById(R.id.g3);Button btn_g4 = findViewById(R.id.g4);
        Button btn_g5 = findViewById(R.id.g5);Button btn_g6 = findViewById(R.id.g6);Button btn_g7 = findViewById(R.id.g7);

        switch (view.getId()) {

            case R.id.a1:
                if (!clicked_a1) {
                    btn_a1.setBackgroundResource(R.drawable.seat_violet);
                    clicked_a1 = true;
                } else {
                    btn_a1.setBackgroundResource(R.drawable.seat_grey);
                    clicked_a1 = false;
                }
                break;

            case R.id.a2:
                if (!clicked_a2) {
                    btn_a2.setBackgroundResource(R.drawable.seat_violet);
                    clicked_a2 = true;
                } else {
                    btn_a2.setBackgroundResource(R.drawable.seat_grey);
                    clicked_a2 = false;
                }
                break;

            case R.id.a3:
                if (!clicked_a3) {
                    btn_a3.setBackgroundResource(R.drawable.seat_violet);
                    clicked_a3 = true;
                } else {
                    btn_a3.setBackgroundResource(R.drawable.seat_grey);
                    clicked_a3 = false;
                }
                break;

            case R.id.a4:
                if (!clicked_a4) {
                    btn_a4.setBackgroundResource(R.drawable.seat_violet);
                    clicked_a4 = true;
                } else {
                    btn_a4.setBackgroundResource(R.drawable.seat_grey);
                    clicked_a4 = false;
                }
                break;

            case R.id.a5:
                if (!clicked_a5) {
                    btn_a5.setBackgroundResource(R.drawable.seat_violet);
                    clicked_a5 = true;
                } else {
                    btn_a5.setBackgroundResource(R.drawable.seat_grey);
                    clicked_a5 = false;
                }
                break;

            case R.id.a6:
                if (!clicked_a6) {
                    btn_a6.setBackgroundResource(R.drawable.seat_violet);
                    clicked_a6 = true;
                } else {
                    btn_a6.setBackgroundResource(R.drawable.seat_grey);
                    clicked_a6 = false;
                }
                break;

            case R.id.a7:
                if (!clicked_a7) {
                    btn_a7.setBackgroundResource(R.drawable.seat_violet);
                    clicked_a7 = true;
                } else {
                    btn_a7.setBackgroundResource(R.drawable.seat_grey);
                    clicked_a7 = false;
                }
                break;

            case R.id.b1:
                if (!clicked_b1) {
                    btn_b1.setBackgroundResource(R.drawable.seat_violet);
                    clicked_b1 = true;
                } else {
                    btn_b1.setBackgroundResource(R.drawable.seat_grey);
                    clicked_b1 = false;
                }
                break;

            case R.id.b2:
                if (!clicked_b2) {
                    btn_b2.setBackgroundResource(R.drawable.seat_violet);
                    clicked_b2 = true;
                } else {
                    btn_b2.setBackgroundResource(R.drawable.seat_grey);
                    clicked_b2 = false;
                }
                break;

            case R.id.b3:
                if (!clicked_b3) {
                    btn_b3.setBackgroundResource(R.drawable.seat_violet);
                    clicked_b3 = true;
                } else {
                    btn_b3.setBackgroundResource(R.drawable.seat_grey);
                    clicked_b3 = false;
                }
                break;

            case R.id.b4:
                if (!clicked_b4) {
                    btn_b4.setBackgroundResource(R.drawable.seat_violet);
                    clicked_b4 = true;
                } else {
                    btn_b4.setBackgroundResource(R.drawable.seat_grey);
                    clicked_b4 = false;
                }
                break;

            case R.id.b5:
                if (!clicked_b5) {
                    btn_b5.setBackgroundResource(R.drawable.seat_violet);
                    clicked_b5 = true;
                } else {
                    btn_b5.setBackgroundResource(R.drawable.seat_grey);
                    clicked_b5 = false;
                }
                break;

            case R.id.b6:
                if (!clicked_b6) {
                    btn_b6.setBackgroundResource(R.drawable.seat_violet);
                    clicked_b6 = true;
                } else {
                    btn_b6.setBackgroundResource(R.drawable.seat_grey);
                    clicked_b6 = false;
                }
                break;

            case R.id.b7:
                if (!clicked_b7) {
                    btn_b7.setBackgroundResource(R.drawable.seat_violet);
                    clicked_b7 = true;
                } else {
                    btn_b7.setBackgroundResource(R.drawable.seat_grey);
                    clicked_b7 = false;
                }
                break;

            case R.id.c1:
                if (!clicked_c1) {
                    btn_c1.setBackgroundResource(R.drawable.seat_violet);
                    clicked_c1 = true;
                } else {
                    btn_c1.setBackgroundResource(R.drawable.seat_grey);
                    clicked_c1 = false;
                }
                break;

            case R.id.c2:
                if (!clicked_c2) {
                    btn_c2.setBackgroundResource(R.drawable.seat_violet);
                    clicked_c2 = true;
                } else {
                    btn_c2.setBackgroundResource(R.drawable.seat_grey);
                    clicked_c2 = false;
                }
                break;

            case R.id.c3:
                if (!clicked_c3) {
                    btn_c3.setBackgroundResource(R.drawable.seat_violet);
                    clicked_c3 = true;
                } else {
                    btn_c3.setBackgroundResource(R.drawable.seat_grey);
                    clicked_c3 = false;
                }
                break;

            case R.id.c4:
                if (!clicked_c4) {
                    btn_c4.setBackgroundResource(R.drawable.seat_violet);
                    clicked_c4 = true;
                } else {
                    btn_c4.setBackgroundResource(R.drawable.seat_grey);
                    clicked_c4 = false;
                }
                break;

            case R.id.c5:
                if (!clicked_c5) {
                    btn_c5.setBackgroundResource(R.drawable.seat_violet);
                    clicked_c5 = true;
                } else {
                    btn_c5.setBackgroundResource(R.drawable.seat_grey);
                    clicked_c5 = false;
                }
                break;

            case R.id.c6:
                if (!clicked_c6) {
                    btn_c6.setBackgroundResource(R.drawable.seat_violet);
                    clicked_c6 = true;
                } else {
                    btn_c6.setBackgroundResource(R.drawable.seat_grey);
                    clicked_c6 = false;
                }
                break;

            case R.id.c7:
                if (!clicked_c7) {
                    btn_c7.setBackgroundResource(R.drawable.seat_violet);
                    clicked_c7 = true;
                } else {
                    btn_c7.setBackgroundResource(R.drawable.seat_grey);
                    clicked_c7 = false;
                }
                break;

            case R.id.d1:
                if (!clicked_d1) {
                    btn_d1.setBackgroundResource(R.drawable.seat_violet);
                    clicked_d1 = true;
                } else {
                    btn_d1.setBackgroundResource(R.drawable.seat_grey);
                    clicked_d1 = false;
                }
                break;

            case R.id.d2:
                if (!clicked_d2) {
                    btn_d2.setBackgroundResource(R.drawable.seat_violet);
                    clicked_d2 = true;
                } else {
                    btn_d2.setBackgroundResource(R.drawable.seat_grey);
                    clicked_d2 = false;
                }
                break;

            case R.id.d3:
                if (!clicked_d3) {
                    btn_d3.setBackgroundResource(R.drawable.seat_violet);
                    clicked_d3 = true;
                } else {
                    btn_d3.setBackgroundResource(R.drawable.seat_grey);
                    clicked_d3 = false;
                }
                break;

            case R.id.d4:
                if (!clicked_d4) {
                    btn_d4.setBackgroundResource(R.drawable.seat_violet);
                    clicked_d4 = true;
                } else {
                    btn_d4.setBackgroundResource(R.drawable.seat_grey);
                    clicked_d4 = false;
                }
                break;

            case R.id.d5:
                if (!clicked_d5) {
                    btn_d5.setBackgroundResource(R.drawable.seat_violet);
                    clicked_d5 = true;
                } else {
                    btn_d5.setBackgroundResource(R.drawable.seat_grey);
                    clicked_d5 = false;
                }
                break;

            case R.id.d6:
                if (!clicked_d6) {
                    btn_d6.setBackgroundResource(R.drawable.seat_violet);
                    clicked_d6 = true;
                } else {
                    btn_d6.setBackgroundResource(R.drawable.seat_grey);
                    clicked_d6 = false;
                }
                break;

            case R.id.d7:
                if (!clicked_d7) {
                    btn_d7.setBackgroundResource(R.drawable.seat_violet);
                    clicked_d7 = true;
                } else {
                    btn_d7.setBackgroundResource(R.drawable.seat_grey);
                    clicked_d7 = false;
                }
                break;

            case R.id.e1:
                if (!clicked_e1) {
                    btn_e1.setBackgroundResource(R.drawable.seat_violet);
                    clicked_e1 = true;
                } else {
                    btn_e1.setBackgroundResource(R.drawable.seat_grey);
                    clicked_e1 = false;
                }
                break;

            case R.id.e2:
                if (!clicked_e2) {
                    btn_e2.setBackgroundResource(R.drawable.seat_violet);
                    clicked_e2 = true;
                } else {
                    btn_e2.setBackgroundResource(R.drawable.seat_grey);
                    clicked_e2 = false;
                }
                break;

            case R.id.e3:
                if (!clicked_e3) {
                    btn_e3.setBackgroundResource(R.drawable.seat_violet);
                    clicked_e3 = true;
                } else {
                    btn_e3.setBackgroundResource(R.drawable.seat_grey);
                    clicked_e3 = false;
                }
                break;

            case R.id.e4:
                if (!clicked_e4) {
                    btn_e4.setBackgroundResource(R.drawable.seat_violet);
                    clicked_e4 = true;
                } else {
                    btn_e4.setBackgroundResource(R.drawable.seat_grey);
                    clicked_e4 = false;
                }
                break;

            case R.id.e5:
                if (!clicked_e5) {
                    btn_e5.setBackgroundResource(R.drawable.seat_violet);
                    clicked_e5 = true;
                } else {
                    btn_e5.setBackgroundResource(R.drawable.seat_grey);
                    clicked_e5 = false;
                }
                break;

            case R.id.e6:
                if (!clicked_e6) {
                    btn_e6.setBackgroundResource(R.drawable.seat_violet);
                    clicked_e6 = true;
                } else {
                    btn_e6.setBackgroundResource(R.drawable.seat_grey);
                    clicked_e6 = false;
                }
                break;

            case R.id.e7:
                if (!clicked_e7) {
                    btn_e7.setBackgroundResource(R.drawable.seat_violet);
                    clicked_e7 = true;
                } else {
                    btn_e7.setBackgroundResource(R.drawable.seat_grey);
                    clicked_e7 = false;
                }
                break;

            case R.id.f1:
                if (!clicked_f1) {
                    btn_f1.setBackgroundResource(R.drawable.seat_violet);
                    clicked_f1 = true;
                } else {
                    btn_f1.setBackgroundResource(R.drawable.seat_grey);
                    clicked_f1 = false;
                }
                break;

            case R.id.f2:
                if (!clicked_f2) {
                    btn_f2.setBackgroundResource(R.drawable.seat_violet);
                    clicked_f2 = true;
                } else {
                    btn_f2.setBackgroundResource(R.drawable.seat_grey);
                    clicked_f2 = false;
                }
                break;

            case R.id.f3:
                if (!clicked_f3) {
                    btn_f3.setBackgroundResource(R.drawable.seat_violet);
                    clicked_f3 = true;
                } else {
                    btn_f3.setBackgroundResource(R.drawable.seat_grey);
                    clicked_f3 = false;
                }
                break;

            case R.id.f4:
                if (!clicked_f4) {
                    btn_f4.setBackgroundResource(R.drawable.seat_violet);
                    clicked_f4 = true;
                } else {
                    btn_f4.setBackgroundResource(R.drawable.seat_grey);
                    clicked_f4 = false;
                }
                break;

            case R.id.f5:
                if (!clicked_f5) {
                    btn_f5.setBackgroundResource(R.drawable.seat_violet);
                    clicked_f5 = true;
                } else {
                    btn_f5.setBackgroundResource(R.drawable.seat_grey);
                    clicked_f5 = false;
                }
                break;

            case R.id.f6:
                if (!clicked_f6) {
                    btn_f6.setBackgroundResource(R.drawable.seat_violet);
                    clicked_f6 = true;
                } else {
                    btn_f6.setBackgroundResource(R.drawable.seat_grey);
                    clicked_f6 = false;
                }
                break;

            case R.id.f7:
                if (!clicked_f7) {
                    btn_f7.setBackgroundResource(R.drawable.seat_violet);
                    clicked_f7 = true;
                } else {
                    btn_f7.setBackgroundResource(R.drawable.seat_grey);
                    clicked_f7 = false;
                }
                break;

            case R.id.g1:
                if (!clicked_g1) {
                    btn_g1.setBackgroundResource(R.drawable.seat_violet);
                    clicked_g1 = true;
                } else {
                    btn_g1.setBackgroundResource(R.drawable.seat_grey);
                    clicked_g1 = false;
                }
                break;

            case R.id.g2:
                if (!clicked_g2) {
                    btn_g2.setBackgroundResource(R.drawable.seat_violet);
                    clicked_g2 = true;
                } else {
                    btn_g2.setBackgroundResource(R.drawable.seat_grey);
                    clicked_g2 = false;
                }
                break;

            case R.id.g3:
                if (!clicked_g3) {
                    btn_g3.setBackgroundResource(R.drawable.seat_violet);
                    clicked_g3 = true;
                } else {
                    btn_g3.setBackgroundResource(R.drawable.seat_grey);
                    clicked_g3 = false;
                }
                break;

            case R.id.g4:
                if (!clicked_g4) {
                    btn_g4.setBackgroundResource(R.drawable.seat_violet);
                    clicked_g4 = true;
                } else {
                    btn_g4.setBackgroundResource(R.drawable.seat_grey);
                    clicked_g4 = false;
                }
                break;

            case R.id.g5:
                if (!clicked_g5) {
                    btn_g5.setBackgroundResource(R.drawable.seat_violet);
                    clicked_g5 = true;
                } else {
                    btn_g5.setBackgroundResource(R.drawable.seat_grey);
                    clicked_g5 = false;
                }
                break;

            case R.id.g6:
                if (!clicked_g6) {
                    btn_g6.setBackgroundResource(R.drawable.seat_violet);
                    clicked_g6 = true;
                } else {
                    btn_g6.setBackgroundResource(R.drawable.seat_grey);
                    clicked_g6 = false;
                }
                break;

            case R.id.g7:
                if (!clicked_g7) {
                    btn_g7.setBackgroundResource(R.drawable.seat_violet);
                    clicked_g7 = true;
                } else {
                    btn_g7.setBackgroundResource(R.drawable.seat_grey);
                    clicked_g7 = false;
                }
                break;

        }
    }//btnClick
}//class
