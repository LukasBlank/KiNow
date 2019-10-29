package frontend;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
    ArrayList<Sitz> freieSitze,belegteSitze,selected,reserviert;
    Requests r;


    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.small_cinema_hall);
        r = new Requests();
        nutzer = (Nutzer) getIntent().getSerializableExtra("nutzer");
        vorführung = (Vorführung) getIntent().getSerializableExtra("vorführung");
        freieSitze = r.getFreieSitze(vorführung.getVorführungsID());
        belegteSitze = r.getBelegteSitze(vorführung.getVorführungsID());
        reserviert = r.getReservierte(vorführung.getVorführungsID());
        selected = new ArrayList<>();
        setup();
    }//onCreate

    private boolean sitzfrei (String id){
        if (id.length()==0 || id.indexOf('_')==-1)return false;
        else {
            for (Sitz tmp : freieSitze){
                if (tmp.getSitzID().equals(id))return true;
            }//for
            for (Sitz tmp : selected){
                if (tmp.getSitzID().equals(id))return true;
            }//for
            return false;
        }//else
    }//frei

    private Sitz getSitzFrei (String id){
        if (id.length()==0 || id.indexOf('_')==-1)return null;
        else {
            for (Sitz tmp : freieSitze){
                if (tmp.getSitzID().equals(id))return tmp;
            }//for
            return null;
        }//else
    }//getSitz

    private Sitz getSitzSelected (String id){
        if (id.length()==0 || id.indexOf('_')==-1)return null;
        else {
            for (Sitz tmp : selected){
                if (tmp.getSitzID().equals(id))return tmp;
            }//for
            return null;
        }//else
    }//getSitzSelected

    public void bookSeats(View v2){
        if (selected.size()==0) Toast.makeText(this, "No seats selected.", Toast.LENGTH_SHORT).show();
        else {
            boolean möglich = r.reservieren(selected,String.valueOf(nutzer.getNutzerID()));
            if (möglich){
                Toast.makeText(this, "Reservierung zum Warenkorb hinzugefügt.", Toast.LENGTH_SHORT).show();
                finish();
            }//then
            else {
                Toast.makeText(this, "Reservierung leider nicht möglich. Versuchen Sie es erneut.", Toast.LENGTH_SHORT).show();
                finish();
            }//else
        }//else
    }//bookSeats

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



        boolean frei;
        String id;
        switch (view.getId()) {
            case R.id.a1:
                id = vorführung.getVorführungsID() + "_A1";
                frei = sitzfrei(id);
                if (frei) {
                    Sitz sitz;
                    if (!clicked_a1) {
                        sitz = getSitzFrei(id);
                        selected.add(sitz);
                        freieSitze.remove(sitz);
                        btn_a1.setBackgroundResource(R.drawable.seat_violet);
                        clicked_a1 = true;
                    } else {
                        sitz = getSitzSelected(id);
                        selected.remove(sitz);
                        freieSitze.add(sitz);
                        btn_a1.setBackgroundResource(R.drawable.seat_grey);
                        clicked_a1 = false;
                    }//else
                }//then
                break;

            case R.id.a2:
                id = vorführung.getVorführungsID() + "_A2";
                frei = sitzfrei(id);
                if (frei) {
                    Sitz sitz;
                    if (!clicked_a2) {
                        sitz = getSitzFrei(id);
                        selected.add(sitz);
                        freieSitze.remove(sitz);
                        btn_a2.setBackgroundResource(R.drawable.seat_violet);
                        clicked_a2 = true;
                    } else {
                        sitz = getSitzSelected(id);
                        selected.remove(sitz);
                        freieSitze.add(sitz);
                        btn_a2.setBackgroundResource(R.drawable.seat_grey);
                        clicked_a2 = false;
                    }//else
                }//then
                break;

            case R.id.a3:
                id = vorführung.getVorführungsID() + "_A3";
                frei = sitzfrei(id);
                if (frei) {
                    Sitz sitz;
                    if (!clicked_a3) {
                        sitz = getSitzFrei(id);
                        selected.add(sitz);
                        freieSitze.remove(sitz);
                        btn_a3.setBackgroundResource(R.drawable.seat_violet);
                        clicked_a3 = true;
                    } else {
                        sitz = getSitzSelected(id);
                        selected.remove(sitz);
                        freieSitze.add(sitz);
                        btn_a3.setBackgroundResource(R.drawable.seat_grey);
                        clicked_a3 = false;
                    }//else
                }//then
                break;

            case R.id.a4:
                id = vorführung.getVorführungsID() + "_A4";
                frei = sitzfrei(id);
                if (frei) {
                    Sitz sitz;
                    if (!clicked_a4) {
                        sitz = getSitzFrei(id);
                        selected.add(sitz);
                        freieSitze.remove(sitz);
                        btn_a4.setBackgroundResource(R.drawable.seat_violet);
                        clicked_a4 = true;
                    } else {
                        sitz = getSitzSelected(id);
                        selected.remove(sitz);
                        freieSitze.add(sitz);
                        btn_a4.setBackgroundResource(R.drawable.seat_grey);
                        clicked_a4 = false;
                    }//else
                }//then
                break;

            case R.id.a5:
                id = vorführung.getVorführungsID() + "_A5";
                frei = sitzfrei(id);
                if (frei) {
                    Sitz sitz;
                    if (!clicked_a5) {
                        sitz = getSitzFrei(id);
                        selected.add(sitz);
                        freieSitze.remove(sitz);
                        btn_a5.setBackgroundResource(R.drawable.seat_violet);
                        clicked_a5 = true;
                    } else {
                        sitz = getSitzSelected(id);
                        selected.remove(sitz);
                        freieSitze.add(sitz);
                        btn_a5.setBackgroundResource(R.drawable.seat_grey);
                        clicked_a5 = false;
                    }//else
                }//then
                break;

            case R.id.a6:
                id = vorführung.getVorführungsID() + "_A6";
                frei = sitzfrei(id);
                if (frei) {
                    Sitz sitz;
                    if (!clicked_a6) {
                        sitz = getSitzFrei(id);
                        selected.add(sitz);
                        freieSitze.remove(sitz);
                        btn_a6.setBackgroundResource(R.drawable.seat_violet);
                        clicked_a6 = true;
                    } else {
                        sitz = getSitzSelected(id);
                        selected.remove(sitz);
                        freieSitze.add(sitz);
                        btn_a6.setBackgroundResource(R.drawable.seat_grey);
                        clicked_a6 = false;
                    }//else
                }//then
                break;

            case R.id.a7:
                id = vorführung.getVorführungsID() + "_A7";
                frei = sitzfrei(id);
                if (frei) {
                    Sitz sitz;
                    if (!clicked_a7) {
                        sitz = getSitzFrei(id);
                        selected.add(sitz);
                        freieSitze.remove(sitz);
                        btn_a7.setBackgroundResource(R.drawable.seat_violet);
                        clicked_a7 = true;
                    } else {
                        sitz = getSitzSelected(id);
                        selected.remove(sitz);
                        freieSitze.add(sitz);
                        btn_a7.setBackgroundResource(R.drawable.seat_grey);
                        clicked_a7 = false;
                    }//else
                }//then
                break;

            case R.id.b1:
                id = vorführung.getVorführungsID() + "_B1";
                frei = sitzfrei(id);
                if (frei) {
                    Sitz sitz;
                    if (!clicked_b1) {
                        sitz = getSitzFrei(id);
                        selected.add(sitz);
                        freieSitze.remove(sitz);
                        btn_b1.setBackgroundResource(R.drawable.seat_violet);
                        clicked_b1 = true;
                    } else {
                        sitz = getSitzSelected(id);
                        selected.remove(sitz);
                        freieSitze.add(sitz);
                        btn_b1.setBackgroundResource(R.drawable.seat_grey);
                        clicked_b1 = false;
                    }//else
                }//then
                break;

            case R.id.b2:
                id = vorführung.getVorführungsID() + "_B2";
                frei = sitzfrei(id);
                if (frei) {
                    Sitz sitz;
                    if (!clicked_b2) {
                        sitz = getSitzFrei(id);
                        selected.add(sitz);
                        freieSitze.remove(sitz);
                        btn_b2.setBackgroundResource(R.drawable.seat_violet);
                        clicked_b2 = true;
                    } else {
                        sitz = getSitzSelected(id);
                        selected.remove(sitz);
                        freieSitze.add(sitz);
                        btn_b2.setBackgroundResource(R.drawable.seat_grey);
                        clicked_b2 = false;
                    }//else
                }//then
                break;

            case R.id.b3:
                id = vorführung.getVorführungsID() + "_B3";
                frei = sitzfrei(id);
                if (frei) {
                    Sitz sitz;
                    if (!clicked_b3) {
                        sitz = getSitzFrei(id);
                        selected.add(sitz);
                        freieSitze.remove(sitz);
                        btn_b3.setBackgroundResource(R.drawable.seat_violet);
                        clicked_b3 = true;
                    } else {
                        sitz = getSitzSelected(id);
                        selected.remove(sitz);
                        freieSitze.add(sitz);
                        btn_b3.setBackgroundResource(R.drawable.seat_grey);
                        clicked_b3 = false;
                    }//else
                }//then
                break;

            case R.id.b4:
                id = vorführung.getVorführungsID() + "_B4";
                frei = sitzfrei(id);
                if (frei) {
                    Sitz sitz;
                    if (!clicked_b4) {
                        sitz = getSitzFrei(id);
                        selected.add(sitz);
                        freieSitze.remove(sitz);
                        btn_b4.setBackgroundResource(R.drawable.seat_violet);
                        clicked_b4 = true;
                    } else {
                        sitz = getSitzSelected(id);
                        selected.remove(sitz);
                        freieSitze.add(sitz);
                        btn_b4.setBackgroundResource(R.drawable.seat_grey);
                        clicked_b4 = false;
                    }//else
                }//then
                break;

            case R.id.b5:
                id = vorführung.getVorführungsID() + "_B5";
                frei = sitzfrei(id);
                if (frei) {
                    Sitz sitz;
                    if (!clicked_b5) {
                        sitz = getSitzFrei(id);
                        selected.add(sitz);
                        freieSitze.remove(sitz);
                        btn_b5.setBackgroundResource(R.drawable.seat_violet);
                        clicked_b5 = true;
                    } else {
                        sitz = getSitzSelected(id);
                        selected.remove(sitz);
                        freieSitze.add(sitz);
                        btn_b5.setBackgroundResource(R.drawable.seat_grey);
                        clicked_b5 = false;
                    }//else
                }//then
                break;

            case R.id.b6:
                id = vorführung.getVorführungsID() + "_B6";
                frei = sitzfrei(id);
                if (frei) {
                    Sitz sitz;
                    if (!clicked_b6) {
                        sitz = getSitzFrei(id);
                        selected.add(sitz);
                        freieSitze.remove(sitz);
                        btn_b6.setBackgroundResource(R.drawable.seat_violet);
                        clicked_b6 = true;
                    } else {
                        sitz = getSitzSelected(id);
                        selected.remove(sitz);
                        freieSitze.add(sitz);
                        btn_b6.setBackgroundResource(R.drawable.seat_grey);
                        clicked_b6 = false;
                    }//else
                }//then
                break;

            case R.id.b7:
                id = vorführung.getVorführungsID() + "_B7";
                frei = sitzfrei(id);
                if (frei) {
                    Sitz sitz;
                    if (!clicked_b7) {
                        sitz = getSitzFrei(id);
                        selected.add(sitz);
                        freieSitze.remove(sitz);
                        btn_b7.setBackgroundResource(R.drawable.seat_violet);
                        clicked_b7 = true;
                    } else {
                        sitz = getSitzSelected(id);
                        selected.remove(sitz);
                        freieSitze.add(sitz);
                        btn_b7.setBackgroundResource(R.drawable.seat_grey);
                        clicked_b7 = false;
                    }//else
                }//then
                break;

            case R.id.c1:
                id = vorführung.getVorführungsID() + "_C1";
                frei = sitzfrei(id);
                if (frei) {
                    Sitz sitz;
                    if (!clicked_c1) {
                        sitz = getSitzFrei(id);
                        selected.add(sitz);
                        freieSitze.remove(sitz);
                        btn_c1.setBackgroundResource(R.drawable.seat_violet);
                        clicked_c1 = true;
                    } else {
                        sitz = getSitzSelected(id);
                        selected.remove(sitz);
                        freieSitze.add(sitz);
                        btn_c1.setBackgroundResource(R.drawable.seat_grey);
                        clicked_c1 = false;
                    }//else
                }//then
                break;

            case R.id.c2:
                id = vorführung.getVorführungsID() + "_C2";
                frei = sitzfrei(id);
                if (frei) {
                    Sitz sitz;
                    if (!clicked_c2) {
                        sitz = getSitzFrei(id);
                        selected.add(sitz);
                        freieSitze.remove(sitz);
                        btn_c2.setBackgroundResource(R.drawable.seat_violet);
                        clicked_c2 = true;
                    } else {
                        sitz = getSitzSelected(id);
                        selected.remove(sitz);
                        freieSitze.add(sitz);
                        btn_c2.setBackgroundResource(R.drawable.seat_grey);
                        clicked_c2 = false;
                    }//else
                }//then
                break;

            case R.id.c3:
                id = vorführung.getVorführungsID() + "_C3";
                frei = sitzfrei(id);
                if (frei) {
                    Sitz sitz;
                    if (!clicked_c3) {
                        sitz = getSitzFrei(id);
                        selected.add(sitz);
                        freieSitze.remove(sitz);
                        btn_c3.setBackgroundResource(R.drawable.seat_violet);
                        clicked_c3 = true;
                    } else {
                        sitz = getSitzSelected(id);
                        selected.remove(sitz);
                        freieSitze.add(sitz);
                        btn_c3.setBackgroundResource(R.drawable.seat_grey);
                        clicked_c3 = false;
                    }//else
                }//then
                break;

            case R.id.c4:
                id = vorführung.getVorführungsID() + "_C4";
                frei = sitzfrei(id);
                if (frei) {
                    Sitz sitz;
                    if (!clicked_c4) {
                        sitz = getSitzFrei(id);
                        selected.add(sitz);
                        freieSitze.remove(sitz);
                        btn_c4.setBackgroundResource(R.drawable.seat_violet);
                        clicked_c4 = true;
                    } else {
                        sitz = getSitzSelected(id);
                        selected.remove(sitz);
                        freieSitze.add(sitz);
                        btn_c4.setBackgroundResource(R.drawable.seat_grey);
                        clicked_c4 = false;
                    }//else
                }//then
                break;

            case R.id.c5:
                id = vorführung.getVorführungsID() + "_C5";
                frei = sitzfrei(id);
                if (frei) {
                    Sitz sitz;
                    if (!clicked_c5) {
                        sitz = getSitzFrei(id);
                        selected.add(sitz);
                        freieSitze.remove(sitz);
                        btn_c5.setBackgroundResource(R.drawable.seat_violet);
                        clicked_c5 = true;
                    } else {
                        sitz = getSitzSelected(id);
                        selected.remove(sitz);
                        freieSitze.add(sitz);
                        btn_c5.setBackgroundResource(R.drawable.seat_grey);
                        clicked_c5 = false;
                    }//else
                }//then
                break;

            case R.id.c6:
                id = vorführung.getVorführungsID() + "_C6";
                frei = sitzfrei(id);
                if (frei) {
                    Sitz sitz;
                    if (!clicked_c6) {
                        sitz = getSitzFrei(id);
                        selected.add(sitz);
                        freieSitze.remove(sitz);
                        btn_c6.setBackgroundResource(R.drawable.seat_violet);
                        clicked_c6 = true;
                    } else {
                        sitz = getSitzSelected(id);
                        selected.remove(sitz);
                        freieSitze.add(sitz);
                        btn_c6.setBackgroundResource(R.drawable.seat_grey);
                        clicked_c6 = false;
                    }//else
                }//then
                break;

            case R.id.c7:
                id = vorführung.getVorführungsID() + "_C7";
                frei = sitzfrei(id);
                if (frei) {
                    Sitz sitz;
                    if (!clicked_c7) {
                        sitz = getSitzFrei(id);
                        selected.add(sitz);
                        freieSitze.remove(sitz);
                        btn_c7.setBackgroundResource(R.drawable.seat_violet);
                        clicked_c7 = true;
                    } else {
                        sitz = getSitzSelected(id);
                        selected.remove(sitz);
                        freieSitze.add(sitz);
                        btn_c7.setBackgroundResource(R.drawable.seat_grey);
                        clicked_c7 = false;
                    }//else
                }//then
                break;

            case R.id.d1:
                id = vorführung.getVorführungsID() + "_D1";
                frei = sitzfrei(id);
                if (frei) {
                    Sitz sitz;
                    if (!clicked_d1) {
                        sitz = getSitzFrei(id);
                        selected.add(sitz);
                        freieSitze.remove(sitz);
                        btn_d1.setBackgroundResource(R.drawable.seat_violet);
                        clicked_d1 = true;
                    } else {
                        sitz = getSitzSelected(id);
                        selected.remove(sitz);
                        freieSitze.add(sitz);
                        btn_d1.setBackgroundResource(R.drawable.seat_grey);
                        clicked_d1 = false;
                    }//else
                }//then
                break;

            case R.id.d2:
                id = vorführung.getVorführungsID() + "_D2";
                frei = sitzfrei(id);
                if (frei) {
                    Sitz sitz;
                    if (!clicked_d2) {
                        sitz = getSitzFrei(id);
                        selected.add(sitz);
                        freieSitze.remove(sitz);
                        btn_d2.setBackgroundResource(R.drawable.seat_violet);
                        clicked_d2 = true;
                    } else {
                        sitz = getSitzSelected(id);
                        selected.remove(sitz);
                        freieSitze.add(sitz);
                        btn_d2.setBackgroundResource(R.drawable.seat_grey);
                        clicked_d2 = false;
                    }//else
                }//then
                break;

            case R.id.d3:
                id = vorführung.getVorführungsID() + "_D3";
                frei = sitzfrei(id);
                if (frei) {
                    Sitz sitz;
                    if (!clicked_d3) {
                        sitz = getSitzFrei(id);
                        selected.add(sitz);
                        freieSitze.remove(sitz);
                        btn_d3.setBackgroundResource(R.drawable.seat_violet);
                        clicked_d3 = true;
                    } else {
                        sitz = getSitzSelected(id);
                        selected.remove(sitz);
                        freieSitze.add(sitz);
                        btn_d3.setBackgroundResource(R.drawable.seat_grey);
                        clicked_d3 = false;
                    }//else
                }//then
                break;

            case R.id.d4:
                id = vorführung.getVorführungsID() + "_D4";
                frei = sitzfrei(id);
                if (frei) {
                    Sitz sitz;
                    if (!clicked_d4) {
                        sitz = getSitzFrei(id);
                        selected.add(sitz);
                        freieSitze.remove(sitz);
                        btn_d4.setBackgroundResource(R.drawable.seat_violet);
                        clicked_d4 = true;
                    } else {
                        sitz = getSitzSelected(id);
                        selected.remove(sitz);
                        freieSitze.add(sitz);
                        btn_d4.setBackgroundResource(R.drawable.seat_grey);
                        clicked_d4 = false;
                    }//else
                }//then
                break;

            case R.id.d5:
                id = vorführung.getVorführungsID() + "_D5";
                frei = sitzfrei(id);
                if (frei) {
                    Sitz sitz;
                    if (!clicked_d5) {
                        sitz = getSitzFrei(id);
                        selected.add(sitz);
                        freieSitze.remove(sitz);
                        btn_d5.setBackgroundResource(R.drawable.seat_violet);
                        clicked_d5 = true;
                    } else {
                        sitz = getSitzSelected(id);
                        selected.remove(sitz);
                        freieSitze.add(sitz);
                        btn_d5.setBackgroundResource(R.drawable.seat_grey);
                        clicked_d5 = false;
                    }//else
                }//then
                break;

            case R.id.d6:
                id = vorführung.getVorführungsID() + "_D6";
                frei = sitzfrei(id);
                if (frei) {
                    Sitz sitz;
                    if (!clicked_d6) {
                        sitz = getSitzFrei(id);
                        selected.add(sitz);
                        freieSitze.remove(sitz);
                        btn_d6.setBackgroundResource(R.drawable.seat_violet);
                        clicked_d6 = true;
                    } else {
                        sitz = getSitzSelected(id);
                        selected.remove(sitz);
                        freieSitze.add(sitz);
                        btn_d6.setBackgroundResource(R.drawable.seat_grey);
                        clicked_d6 = false;
                    }//else
                }//then
                break;

            case R.id.d7:
                id = vorführung.getVorführungsID() + "_D7";
                frei = sitzfrei(id);
                if (frei) {
                    Sitz sitz;
                    if (!clicked_d7) {
                        sitz = getSitzFrei(id);
                        selected.add(sitz);
                        freieSitze.remove(sitz);
                        btn_d7.setBackgroundResource(R.drawable.seat_violet);
                        clicked_d7 = true;
                    } else {
                        sitz = getSitzSelected(id);
                        selected.remove(sitz);
                        freieSitze.add(sitz);
                        btn_d7.setBackgroundResource(R.drawable.seat_grey);
                        clicked_d7 = false;
                    }//else
                }//then
                break;

            case R.id.e1:
                id = vorführung.getVorführungsID() + "_E1";
                frei = sitzfrei(id);
                if (frei) {
                    Sitz sitz;
                    if (!clicked_e1) {
                        sitz = getSitzFrei(id);
                        selected.add(sitz);
                        freieSitze.remove(sitz);
                        btn_e1.setBackgroundResource(R.drawable.seat_violet);
                        clicked_e1 = true;
                    } else {
                        sitz = getSitzSelected(id);
                        selected.remove(sitz);
                        freieSitze.add(sitz);
                        btn_e1.setBackgroundResource(R.drawable.seat_grey);
                        clicked_e1 = false;
                    }//else
                }//then
                break;

            case R.id.e2:
                id = vorführung.getVorführungsID() + "_E2";
                frei = sitzfrei(id);
                if (frei) {
                    Sitz sitz;
                    if (!clicked_e2) {
                        sitz = getSitzFrei(id);
                        selected.add(sitz);
                        freieSitze.remove(sitz);
                        btn_e2.setBackgroundResource(R.drawable.seat_violet);
                        clicked_e2 = true;
                    } else {
                        sitz = getSitzSelected(id);
                        selected.remove(sitz);
                        freieSitze.add(sitz);
                        btn_e2.setBackgroundResource(R.drawable.seat_grey);
                        clicked_e2 = false;
                    }//else
                }//then
                break;

            case R.id.e3:
                id = vorführung.getVorführungsID() + "_E3";
                frei = sitzfrei(id);
                if (frei) {
                    Sitz sitz;
                    if (!clicked_e3) {
                        sitz = getSitzFrei(id);
                        selected.add(sitz);
                        freieSitze.remove(sitz);
                        btn_e3.setBackgroundResource(R.drawable.seat_violet);
                        clicked_e3 = true;
                    } else {
                        sitz = getSitzSelected(id);
                        selected.remove(sitz);
                        freieSitze.add(sitz);
                        btn_e3.setBackgroundResource(R.drawable.seat_grey);
                        clicked_e3 = false;
                    }//else
                }//then
                break;

            case R.id.e4:
                id = vorführung.getVorführungsID() + "_E4";
                frei = sitzfrei(id);
                if (frei) {
                    Sitz sitz;
                    if (!clicked_e4) {
                        sitz = getSitzFrei(id);
                        selected.add(sitz);
                        freieSitze.remove(sitz);
                        btn_e4.setBackgroundResource(R.drawable.seat_violet);
                        clicked_e4 = true;
                    } else {
                        sitz = getSitzSelected(id);
                        selected.remove(sitz);
                        freieSitze.add(sitz);
                        btn_e4.setBackgroundResource(R.drawable.seat_grey);
                        clicked_e4 = false;
                    }//else
                }//then
                break;

            case R.id.e5:
                id = vorführung.getVorführungsID() + "_E5";
                frei = sitzfrei(id);
                if (frei) {
                    Sitz sitz;
                    if (!clicked_e5) {
                        sitz = getSitzFrei(id);
                        selected.add(sitz);
                        freieSitze.remove(sitz);
                        btn_e5.setBackgroundResource(R.drawable.seat_violet);
                        clicked_e5 = true;
                    } else {
                        sitz = getSitzSelected(id);
                        selected.remove(sitz);
                        freieSitze.add(sitz);
                        btn_e5.setBackgroundResource(R.drawable.seat_grey);
                        clicked_e5 = false;
                    }//else
                }//then
                break;

            case R.id.e6:
                id = vorführung.getVorführungsID() + "_E6";
                frei = sitzfrei(id);
                if (frei) {
                    Sitz sitz;
                    if (!clicked_e6) {
                        sitz = getSitzFrei(id);
                        selected.add(sitz);
                        freieSitze.remove(sitz);
                        btn_e6.setBackgroundResource(R.drawable.seat_violet);
                        clicked_e6 = true;
                    } else {
                        sitz = getSitzSelected(id);
                        selected.remove(sitz);
                        freieSitze.add(sitz);
                        btn_e6.setBackgroundResource(R.drawable.seat_grey);
                        clicked_e6 = false;
                    }//else
                }//then
                break;

            case R.id.e7:
                id = vorführung.getVorführungsID() + "_E7";
                frei = sitzfrei(id);
                if (frei) {
                    Sitz sitz;
                    if (!clicked_e7) {
                        sitz = getSitzFrei(id);
                        selected.add(sitz);
                        freieSitze.remove(sitz);
                        btn_e7.setBackgroundResource(R.drawable.seat_violet);
                        clicked_e7 = true;
                    } else {
                        sitz = getSitzSelected(id);
                        selected.remove(sitz);
                        freieSitze.add(sitz);
                        btn_e7.setBackgroundResource(R.drawable.seat_grey);
                        clicked_e7 = false;
                    }//else
                }//then
                break;

            case R.id.f1:
                id = vorführung.getVorführungsID() + "_F1";
                frei = sitzfrei(id);
                if (frei) {
                    Sitz sitz;
                    if (!clicked_f1) {
                        sitz = getSitzFrei(id);
                        selected.add(sitz);
                        freieSitze.remove(sitz);
                        btn_f1.setBackgroundResource(R.drawable.seat_violet);
                        clicked_f1 = true;
                    } else {
                        sitz = getSitzSelected(id);
                        selected.remove(sitz);
                        freieSitze.add(sitz);
                        btn_f1.setBackgroundResource(R.drawable.seat_grey);
                        clicked_f1 = false;
                    }//else
                }//then
                break;

            case R.id.f2:
                id = vorführung.getVorführungsID() + "_F2";
                frei = sitzfrei(id);
                if (frei) {
                    Sitz sitz;
                    if (!clicked_f2) {
                        sitz = getSitzFrei(id);
                        selected.add(sitz);
                        freieSitze.remove(sitz);
                        btn_f2.setBackgroundResource(R.drawable.seat_violet);
                        clicked_f2 = true;
                    } else {
                        sitz = getSitzSelected(id);
                        selected.remove(sitz);
                        freieSitze.add(sitz);
                        btn_f2.setBackgroundResource(R.drawable.seat_grey);
                        clicked_f2 = false;
                    }//else
                }//then
                break;

            case R.id.f3:
                id = vorführung.getVorführungsID() + "_F3";
                frei = sitzfrei(id);
                if (frei) {
                    Sitz sitz;
                    if (!clicked_f3) {
                        sitz = getSitzFrei(id);
                        selected.add(sitz);
                        freieSitze.remove(sitz);
                        btn_f3.setBackgroundResource(R.drawable.seat_violet);
                        clicked_f3 = true;
                    } else {
                        sitz = getSitzSelected(id);
                        selected.remove(sitz);
                        freieSitze.add(sitz);
                        btn_f3.setBackgroundResource(R.drawable.seat_grey);
                        clicked_f3 = false;
                    }//else
                }//then
                break;

            case R.id.f4:
                id = vorführung.getVorführungsID() + "_F4";
                frei = sitzfrei(id);
                if (frei) {
                    Sitz sitz;
                    if (!clicked_f4) {
                        sitz = getSitzFrei(id);
                        selected.add(sitz);
                        freieSitze.remove(sitz);
                        btn_f4.setBackgroundResource(R.drawable.seat_violet);
                        clicked_f4 = true;
                    } else {
                        sitz = getSitzSelected(id);
                        selected.remove(sitz);
                        freieSitze.add(sitz);
                        btn_f4.setBackgroundResource(R.drawable.seat_grey);
                        clicked_f4 = false;
                    }//else
                }//then
                break;

            case R.id.f5:
                id = vorführung.getVorführungsID() + "_F5";
                frei = sitzfrei(id);
                if (frei) {
                    Sitz sitz;
                    if (!clicked_f5) {
                        sitz = getSitzFrei(id);
                        selected.add(sitz);
                        freieSitze.remove(sitz);
                        btn_f5.setBackgroundResource(R.drawable.seat_violet);
                        clicked_f5 = true;
                    } else {
                        sitz = getSitzSelected(id);
                        selected.remove(sitz);
                        freieSitze.add(sitz);
                        btn_f5.setBackgroundResource(R.drawable.seat_grey);
                        clicked_f5 = false;
                    }//else
                }//then
                break;

            case R.id.f6:
                id = vorführung.getVorführungsID() + "_F6";
                frei = sitzfrei(id);
                if (frei) {
                    Sitz sitz;
                    if (!clicked_f6) {
                        sitz = getSitzFrei(id);
                        selected.add(sitz);
                        freieSitze.remove(sitz);
                        btn_f6.setBackgroundResource(R.drawable.seat_violet);
                        clicked_f6 = true;
                    } else {
                        sitz = getSitzSelected(id);
                        selected.remove(sitz);
                        freieSitze.add(sitz);
                        btn_f6.setBackgroundResource(R.drawable.seat_grey);
                        clicked_f6 = false;
                    }//else
                }//then
                break;

            case R.id.f7:
                id = vorführung.getVorführungsID() + "_F7";
                frei = sitzfrei(id);
                if (frei) {
                    Sitz sitz;
                    if (!clicked_f7) {
                        sitz = getSitzFrei(id);
                        selected.add(sitz);
                        freieSitze.remove(sitz);
                        btn_f7.setBackgroundResource(R.drawable.seat_violet);
                        clicked_f7 = true;
                    } else {
                        sitz = getSitzSelected(id);
                        selected.remove(sitz);
                        freieSitze.add(sitz);
                        btn_f7.setBackgroundResource(R.drawable.seat_grey);
                        clicked_f7 = false;
                    }//else
                }//then
                break;

            case R.id.g1:
                id = vorführung.getVorführungsID() + "_G1";
                frei = sitzfrei(id);
                if (frei) {
                    Sitz sitz;
                    if (!clicked_g1) {
                        sitz = getSitzFrei(id);
                        selected.add(sitz);
                        freieSitze.remove(sitz);
                        btn_g1.setBackgroundResource(R.drawable.seat_violet);
                        clicked_g1 = true;
                    } else {
                        sitz = getSitzSelected(id);
                        selected.remove(sitz);
                        freieSitze.add(sitz);
                        btn_g1.setBackgroundResource(R.drawable.seat_grey);
                        clicked_g1 = false;
                    }//else
                }//then
                break;

            case R.id.g2:
                id = vorführung.getVorführungsID() + "_G2";
                frei = sitzfrei(id);
                if (frei) {
                    Sitz sitz;
                    if (!clicked_g2) {
                        sitz = getSitzFrei(id);
                        selected.add(sitz);
                        freieSitze.remove(sitz);
                        btn_g2.setBackgroundResource(R.drawable.seat_violet);
                        clicked_g2 = true;
                    } else {
                        sitz = getSitzSelected(id);
                        selected.remove(sitz);
                        freieSitze.add(sitz);
                        btn_g2.setBackgroundResource(R.drawable.seat_grey);
                        clicked_g2 = false;
                    }//else
                }//then
                break;

            case R.id.g3:
                id = vorführung.getVorführungsID() + "_G3";
                frei = sitzfrei(id);
                if (frei) {
                    Sitz sitz;
                    if (!clicked_g3) {
                        sitz = getSitzFrei(id);
                        selected.add(sitz);
                        freieSitze.remove(sitz);
                        btn_g3.setBackgroundResource(R.drawable.seat_violet);
                        clicked_g3 = true;
                    } else {
                        sitz = getSitzSelected(id);
                        selected.remove(sitz);
                        freieSitze.add(sitz);
                        btn_g3.setBackgroundResource(R.drawable.seat_grey);
                        clicked_g3 = false;
                    }//else
                }//then
                break;

            case R.id.g4:
                id = vorführung.getVorführungsID() + "_G4";
                frei = sitzfrei(id);
                if (frei) {
                    Sitz sitz;
                    if (!clicked_g4) {
                        sitz = getSitzFrei(id);
                        selected.add(sitz);
                        freieSitze.remove(sitz);
                        btn_g4.setBackgroundResource(R.drawable.seat_violet);
                        clicked_g4 = true;
                    } else {
                        sitz = getSitzSelected(id);
                        selected.remove(sitz);
                        freieSitze.add(sitz);
                        btn_g4.setBackgroundResource(R.drawable.seat_grey);
                        clicked_g4 = false;
                    }//else
                }//then
                break;

            case R.id.g5:
                id = vorführung.getVorführungsID() + "_G5";
                frei = sitzfrei(id);
                if (frei) {
                    Sitz sitz;
                    if (!clicked_g5) {
                        sitz = getSitzFrei(id);
                        selected.add(sitz);
                        freieSitze.remove(sitz);
                        btn_g5.setBackgroundResource(R.drawable.seat_violet);
                        clicked_g5 = true;
                    } else {
                        sitz = getSitzSelected(id);
                        selected.remove(sitz);
                        freieSitze.add(sitz);
                        btn_g5.setBackgroundResource(R.drawable.seat_grey);
                        clicked_g5 = false;
                    }//else
                }//then
                break;

            case R.id.g6:
                id = vorführung.getVorführungsID() + "_G6";
                frei = sitzfrei(id);
                if (frei) {
                    Sitz sitz;
                    if (!clicked_g6) {
                        sitz = getSitzFrei(id);
                        selected.add(sitz);
                        freieSitze.remove(sitz);
                        btn_g6.setBackgroundResource(R.drawable.seat_violet);
                        clicked_g6 = true;
                    } else {
                        sitz = getSitzSelected(id);
                        selected.remove(sitz);
                        freieSitze.add(sitz);
                        btn_g6.setBackgroundResource(R.drawable.seat_grey);
                        clicked_g6 = false;
                    }//else
                }//then
                break;

            case R.id.g7:
                id = vorführung.getVorführungsID() + "_G7";
                frei = sitzfrei(id);
                if (frei) {
                    Sitz sitz;
                    if (!clicked_g7) {
                        sitz = getSitzFrei(id);
                        selected.add(sitz);
                        freieSitze.remove(sitz);
                        btn_g7.setBackgroundResource(R.drawable.seat_violet);
                        clicked_g7 = true;
                    } else {
                        sitz = getSitzSelected(id);
                        selected.remove(sitz);
                        freieSitze.add(sitz);
                        btn_g7.setBackgroundResource(R.drawable.seat_grey);
                        clicked_g7 = false;
                    }//else
                }//then
                break;
        }//switch
    }//btnClick

    public void setup (){
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


        if (belegteSitze.size()!=0 || reserviert.size()!=0){
            for (Sitz s : belegteSitze){
                String sitzID = s.getSitzID();
                sitzID = sitzID.substring(sitzID.lastIndexOf('_')+1);
                switch (sitzID){
                    case "A1": btn_a1.setBackgroundResource(R.drawable.seat_black);break;case "A2": btn_a2.setBackgroundResource(R.drawable.seat_black);break;case "A3": btn_a3.setBackgroundResource(R.drawable.seat_black);break;case "A4": btn_a4.setBackgroundResource(R.drawable.seat_black);break;case "A5": btn_a5.setBackgroundResource(R.drawable.seat_black);break;case "A6": btn_a6.setBackgroundResource(R.drawable.seat_black);break;case "A7": btn_a7.setBackgroundResource(R.drawable.seat_black);break;
                    case "B1": btn_b1.setBackgroundResource(R.drawable.seat_black);break;case "B2": btn_b2.setBackgroundResource(R.drawable.seat_black);break;case "B3": btn_b3.setBackgroundResource(R.drawable.seat_black);break;case "B4": btn_b4.setBackgroundResource(R.drawable.seat_black);break;case "B5": btn_b5.setBackgroundResource(R.drawable.seat_black);break;case "B6": btn_b6.setBackgroundResource(R.drawable.seat_black);break;case "B7": btn_b7.setBackgroundResource(R.drawable.seat_black);break;
                    case "C1": btn_c1.setBackgroundResource(R.drawable.seat_black);break;case "C2": btn_c2.setBackgroundResource(R.drawable.seat_black);break;case "C3": btn_c3.setBackgroundResource(R.drawable.seat_black);break;case "C4": btn_c4.setBackgroundResource(R.drawable.seat_black);break;case "C5": btn_c5.setBackgroundResource(R.drawable.seat_black);break;case "C6": btn_c6.setBackgroundResource(R.drawable.seat_black);break;case "C7": btn_c7.setBackgroundResource(R.drawable.seat_black);break;
                    case "D1": btn_d1.setBackgroundResource(R.drawable.seat_black);break;case "D2": btn_d2.setBackgroundResource(R.drawable.seat_black);break;case "D3": btn_d3.setBackgroundResource(R.drawable.seat_black);break;case "D4": btn_d4.setBackgroundResource(R.drawable.seat_black);break;case "D5": btn_d5.setBackgroundResource(R.drawable.seat_black);break;case "D6": btn_d6.setBackgroundResource(R.drawable.seat_black);break;case "D7": btn_d7.setBackgroundResource(R.drawable.seat_black);break;
                    case "E1": btn_e1.setBackgroundResource(R.drawable.seat_black);break;case "E2": btn_e2.setBackgroundResource(R.drawable.seat_black);break;case "E3": btn_e3.setBackgroundResource(R.drawable.seat_black);break;case "E4": btn_e4.setBackgroundResource(R.drawable.seat_black);break;case "E5": btn_e5.setBackgroundResource(R.drawable.seat_black);break;case "E6": btn_e6.setBackgroundResource(R.drawable.seat_black);break;case "E7": btn_e7.setBackgroundResource(R.drawable.seat_black);break;
                    case "F1": btn_f1.setBackgroundResource(R.drawable.seat_black);break;case "F2": btn_f2.setBackgroundResource(R.drawable.seat_black);break;case "F3": btn_f3.setBackgroundResource(R.drawable.seat_black);break;case "F4": btn_f4.setBackgroundResource(R.drawable.seat_black);break;case "F5": btn_f5.setBackgroundResource(R.drawable.seat_black);break;case "F6": btn_f6.setBackgroundResource(R.drawable.seat_black);break;case "F7": btn_f7.setBackgroundResource(R.drawable.seat_black);break;
                    case "G1": btn_g1.setBackgroundResource(R.drawable.seat_black);break;case "G2": btn_g2.setBackgroundResource(R.drawable.seat_black);break;case "G3": btn_g3.setBackgroundResource(R.drawable.seat_black);break;case "G4": btn_g4.setBackgroundResource(R.drawable.seat_black);break;case "G5": btn_g5.setBackgroundResource(R.drawable.seat_black);break;case "G6": btn_g6.setBackgroundResource(R.drawable.seat_black);break;case "G7": btn_g7.setBackgroundResource(R.drawable.seat_black);break;
                    default:
                }//switch
            }//for
            for (Sitz s : reserviert){
                String sitzID = s.getSitzID();
                sitzID = sitzID.substring(sitzID.lastIndexOf('_')+1);
                switch (sitzID){
                    case "A1": btn_a1.setBackgroundResource(R.drawable.seat_black);break;case "A2": btn_a2.setBackgroundResource(R.drawable.seat_black);break;case "A3": btn_a3.setBackgroundResource(R.drawable.seat_black);break;case "A4": btn_a4.setBackgroundResource(R.drawable.seat_black);break;case "A5": btn_a5.setBackgroundResource(R.drawable.seat_black);break;case "A6": btn_a6.setBackgroundResource(R.drawable.seat_black);break;case "A7": btn_a7.setBackgroundResource(R.drawable.seat_black);break;
                    case "B1": btn_b1.setBackgroundResource(R.drawable.seat_black);break;case "B2": btn_b2.setBackgroundResource(R.drawable.seat_black);break;case "B3": btn_b3.setBackgroundResource(R.drawable.seat_black);break;case "B4": btn_b4.setBackgroundResource(R.drawable.seat_black);break;case "B5": btn_b5.setBackgroundResource(R.drawable.seat_black);break;case "B6": btn_b6.setBackgroundResource(R.drawable.seat_black);break;case "B7": btn_b7.setBackgroundResource(R.drawable.seat_black);break;
                    case "C1": btn_c1.setBackgroundResource(R.drawable.seat_black);break;case "C2": btn_c2.setBackgroundResource(R.drawable.seat_black);break;case "C3": btn_c3.setBackgroundResource(R.drawable.seat_black);break;case "C4": btn_c4.setBackgroundResource(R.drawable.seat_black);break;case "C5": btn_c5.setBackgroundResource(R.drawable.seat_black);break;case "C6": btn_c6.setBackgroundResource(R.drawable.seat_black);break;case "C7": btn_c7.setBackgroundResource(R.drawable.seat_black);break;
                    case "D1": btn_d1.setBackgroundResource(R.drawable.seat_black);break;case "D2": btn_d2.setBackgroundResource(R.drawable.seat_black);break;case "D3": btn_d3.setBackgroundResource(R.drawable.seat_black);break;case "D4": btn_d4.setBackgroundResource(R.drawable.seat_black);break;case "D5": btn_d5.setBackgroundResource(R.drawable.seat_black);break;case "D6": btn_d6.setBackgroundResource(R.drawable.seat_black);break;case "D7": btn_d7.setBackgroundResource(R.drawable.seat_black);break;
                    case "E1": btn_e1.setBackgroundResource(R.drawable.seat_black);break;case "E2": btn_e2.setBackgroundResource(R.drawable.seat_black);break;case "E3": btn_e3.setBackgroundResource(R.drawable.seat_black);break;case "E4": btn_e4.setBackgroundResource(R.drawable.seat_black);break;case "E5": btn_e5.setBackgroundResource(R.drawable.seat_black);break;case "E6": btn_e6.setBackgroundResource(R.drawable.seat_black);break;case "E7": btn_e7.setBackgroundResource(R.drawable.seat_black);break;
                    case "F1": btn_f1.setBackgroundResource(R.drawable.seat_black);break;case "F2": btn_f2.setBackgroundResource(R.drawable.seat_black);break;case "F3": btn_f3.setBackgroundResource(R.drawable.seat_black);break;case "F4": btn_f4.setBackgroundResource(R.drawable.seat_black);break;case "F5": btn_f5.setBackgroundResource(R.drawable.seat_black);break;case "F6": btn_f6.setBackgroundResource(R.drawable.seat_black);break;case "F7": btn_f7.setBackgroundResource(R.drawable.seat_black);break;
                    case "G1": btn_g1.setBackgroundResource(R.drawable.seat_black);break;case "G2": btn_g2.setBackgroundResource(R.drawable.seat_black);break;case "G3": btn_g3.setBackgroundResource(R.drawable.seat_black);break;case "G4": btn_g4.setBackgroundResource(R.drawable.seat_black);break;case "G5": btn_g5.setBackgroundResource(R.drawable.seat_black);break;case "G6": btn_g6.setBackgroundResource(R.drawable.seat_black);break;case "G7": btn_g7.setBackgroundResource(R.drawable.seat_black);break;
                    default:
                }//switch
            }//for
        }//then
    }//setup

}//class
