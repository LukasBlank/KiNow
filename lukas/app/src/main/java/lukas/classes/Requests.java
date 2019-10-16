package lukas.classes;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.Request;

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
        String url = "http://94.16.123.237:8080/getFilme";
        Request request = new Request.Builder()
                .addHeader("kinoID","0")
                .url(url).build();
        tr.setRequest(request);
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
