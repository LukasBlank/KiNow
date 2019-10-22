package backend.connections;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import backend.classes.Film;
import backend.classes.Kino;
import backend.classes.Nutzer;
import backend.classes.Sitz;
import backend.classes.Vorführung;
import okhttp3.Request;

public class Requests {

    private String ausgabe;

    public Requests (){
        ausgabe = "";
    }//K

    public ArrayList<Film> getFilme (long kinoID){
        ausgabe = "";
        ArrayList<backend.classes.Film> filme = new ArrayList<Film>();
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
                if (ausgabe.indexOf(':')==-1)return null;
                else {
                    //Ergebnis zu einer Map parsen, diese zu Nutzer parsen
                    Map<String,Object> gesamt = new ObjectMapper().readValue(ausgabe, Map.class);
                    Map<String,Object> map = new HashMap<>();
                    for (Map.Entry<String,Object> e : gesamt.entrySet()){
                        map = (Map<String, Object>) e.getValue();
                    }//for
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

    public boolean registerUser (Nutzer n){
        ausgabe = "";
        String nutzer = n.toMapString();
        ThreadRequest tr = new ThreadRequest();
        String url = "http://94.16.123.237:8080/addNutzer";
        Request request = new Request.Builder()
                .addHeader("nutzer",nutzer)
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
                return false;
            }//then
            else {
                ausgabe = tr.getErg();
                if (ausgabe.equals("Success"))return true;
                else return false;
            }//else
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }//catch
    }//register

    public ArrayList<Vorführung> getVor (long kinoID, long filmID){
        ausgabe = "";
        if (kinoID==0 || filmID==0)return null;
        else {
            ArrayList<backend.classes.Vorführung> vorführungen  = new ArrayList<Vorführung>();
            ThreadRequest tr = new ThreadRequest();
            String url = "http://94.16.123.237:8080/getVor";
            String kID = String.valueOf(kinoID);
            String fID = String.valueOf(filmID);
            Request request = new Request.Builder()
                    .addHeader("kinoID",kID)
                    .addHeader("filmID",fID)
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
                        Vorführung v = new Vorführung();
                        for (Map.Entry<String,Object> e : data.entrySet()){
                            v.set(e.getKey(),e.getValue());
                        }//for
                        vorführungen.add(v);
                    }//for
                    return vorführungen;
                }//else
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }//catch
        }//else
    }//getVor

    public ArrayList<Sitz> getFreieSitze (String vorführungsID){
        ausgabe = "";
        if (vorführungsID.length()==0)return null;
        else {
            ArrayList<backend.classes.Sitz> sitze  = new ArrayList<Sitz>();
            ThreadRequest tr = new ThreadRequest();
            String url = "http://94.16.123.237:8080/getFrei";
            Request request = new Request.Builder()
                    .addHeader("vorführungsID",vorführungsID)
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
                        Sitz s = new Sitz();
                        for (Map.Entry<String,Object> e : data.entrySet()){
                            s.set(e.getKey(),e.getValue());
                        }//for
                        sitze.add(s);
                    }//for
                    return sitze;
                }//else
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }//catch
        }//else
    }//getFreieSitze

    public ArrayList<Sitz> getBelegteSitze (String vorführungsID){
        ausgabe = "";
        if (vorführungsID.length()==0)return null;
        else {
            ArrayList<backend.classes.Sitz> sitze  = new ArrayList<Sitz>();
            ThreadRequest tr = new ThreadRequest();
            String url = "http://94.16.123.237:8080/getBelegt";
            Request request = new Request.Builder()
                    .addHeader("vorführungsID",vorführungsID)
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
                        Sitz s = new Sitz();
                        for (Map.Entry<String,Object> e : data.entrySet()){
                            s.set(e.getKey(),e.getValue());
                        }//for
                        sitze.add(s);
                    }//for
                    return sitze;
                }//else
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }//catch
        }//else
    }//getBelegteSitze




}//class