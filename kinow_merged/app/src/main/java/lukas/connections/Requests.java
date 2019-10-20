package lukas.connections;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import lukas.classes.Film;
import lukas.classes.Kino;
import lukas.classes.Nutzer;
import okhttp3.Request;

public class Requests {

    private String ausgabe;
    Object o;

    public Requests (){
        o = null;
        ausgabe = "";
    }//K

    public ArrayList<Film> getFilme (long kinoID){
        ausgabe = "";
        ArrayList<lukas.classes.Film> filme = new ArrayList<Film>();
        ThreadRequest tr = new ThreadRequest();
        String url = "http://94.16.123.237:8080/getFilme";
        String SID = String.valueOf(kinoID);
        Request request = new Request.Builder()
                .addHeader("kinoID",SID)
                .url(url).build();
        tr.setRequest(request);
        tr.start();
        try {
            tr.join();
            long anfang = System.currentTimeMillis();
            long ende = anfang;
            //warten bis Thread fertig ist // höchstens 10 Sekunden //da Thread parallel arbeitet ist aktives Warten ok
            do {
                ende = System.currentTimeMillis();
            } while (!tr.isFertig() && ende-anfang<10000);
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

    public ArrayList<Kino> getKinos (){
        ausgabe = "";
        ArrayList<Kino> kinos = new ArrayList<Kino>();
        ThreadRequest tr = new ThreadRequest();
        String url = "http://94.16.123.237:8080/getKinos";
        Request request = new Request.Builder()
                .url(url).build();
        tr.setRequest(request);
        tr.start();
        try {
            tr.join();
            long anfang = System.currentTimeMillis();
            long ende = anfang;
            //warten bis Thread fertig ist // höchstens 10 Sekunden //da Thread parallel arbeitet ist aktives Warten ok
            do {
                ende = System.currentTimeMillis();
            } while (!tr.isFertig() && ende-anfang<10000);
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
                    Kino kino = new Kino();
                    //Diese "SubMaps" wieder durchiterieren und zu Filmen parsen
                    for (Map.Entry<String,Object> e : data.entrySet()){
                        kino.set(e.getKey(),e.getValue());
                    }//for
                    kinos.add(kino);
                }//for
                return kinos;
            }//else
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }//catch
    }//getKinos

    public Nutzer LogIn (String email, String pw){
        ausgabe = "";
        ThreadRequest tr = new ThreadRequest();
        String url = "http://94.16.123.237:8080/LogIn";
        Request request = new Request.Builder()
                .addHeader("email",email)
                .addHeader("passwort",pw)
                .url(url).build();
        tr.setRequest(request);
        tr.start();
        try {
            tr.join();
            long anfang = System.currentTimeMillis();
            long ende = anfang;
            //warten bis Thread fertig ist // höchstens 10 Sekunden //da Thread parallel arbeitet ist aktives Warten ok
            do {
                ende = System.currentTimeMillis();
            } while (!tr.isFertig() && ende-anfang<10000);
            if (!tr.isFertig()){
                System.out.println("Zeitlimit bei HttpRequest überschritten.");
                return null;
            }//then
            else {
                ausgabe = tr.getErg();
                if (ausgabe.length()==0)return null;
                else {
                    //Ergebnis zu einer Map parsen, diese zu Nutzer parsen
                    Map<String,Object> map = new ObjectMapper().readValue(ausgabe, Map.class);
                    Nutzer nutzer = new Nutzer();
                    for (Map.Entry<String,Object> entry : map.entrySet()){
                        nutzer.set(entry.getKey(),entry.getValue());
                    }//for
                    return nutzer;
                }//else
            }//else
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }//catch
    }//getNutzer


}//class