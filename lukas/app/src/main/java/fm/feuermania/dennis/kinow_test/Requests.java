package fm.feuermania.dennis.kinow_test;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;

import lukas.classes.Film;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Requests {

    private String ausgabe;
    ArrayList<lukas.classes.Film> filme;
    Object o;

    public Requests (){
        o = null;
        ausgabe = "";
        filme = new ArrayList<Film>();
    }//K

    public ArrayList<Film> getFilme (){
        ausgabe = "";
        ThreadRequest tr = new ThreadRequest();
        tr.setUrl("http://94.16.123.237:8080/getFilme");
        tr.start();
        try {
            tr.join();
            long anfang = System.currentTimeMillis();
            long ende = anfang;
            //warten bis Thread fertig ist // höchstens 5 Sekunden
            do {
                ende = System.currentTimeMillis();
            } while (!tr.isFertig() && ende-anfang<5000);
            if (!tr.isFertig()){
                System.out.println("Zeitlimit bei HttpRequest überschritten.");
                return null;
            }//then
            else {
                ausgabe = tr.getErg();
                //gesamte Ausgabe zu einer Map parsen
                Map<String,Object> map = new ObjectMapper().readValue(ausgabe, Map.class);
                //durch diese Map iterieren und jeden Value-Eintrag zu einer Map machen
                for (Map.Entry<String,Object> entry : map.entrySet()){
                    Map<String,Object> data = (Map<String, Object>) entry.getValue();
                    Film film = new Film();
                    //Diese "SubMaps" wieder durchiterieren und zu Filmen parsen
                    for (Map.Entry<String,Object> e : data.entrySet()){
                        film.set(e.getKey(),e.getValue());
                    }//for
                    filme.add(film);
                }//for
                return filme;
            }//else
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }//catch
    }//getFilme


}
