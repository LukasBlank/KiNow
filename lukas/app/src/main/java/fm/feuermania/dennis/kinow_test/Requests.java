package fm.feuermania.dennis.kinow_test;

import android.preference.PreferenceActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

import lukas.java_classes.Film;
import lukas.java_classes.Parser;

public class Requests {

    Parser ps;
    ArrayList<Film> filme;

    public Requests (){
        ps = new Parser();
        filme = new ArrayList<Film>();
    }//K

    public ArrayList<Film> getFilme (){

        try {
            //Verbindung aufbauen
            URL url = new URL("http://v220191010515498885.nicesrv.de:8080/getAllData");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("head","Filme");
            con.setRequestMethod("GET");
            con.connect();

            //JSON Datei holen
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }//while
            in.close();
            String json=content.toString();
            ps = new Parser();
            return ps.toFilmList(json);

        }catch(Exception e){
            String s = e.getMessage();
            return null;
        }//catch
    }//getFilme
}
