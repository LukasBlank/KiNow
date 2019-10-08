package fm.feuermania.dennis.kinow_test;

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

    public ArrayList<Film> getFilme (){
        try {

            //JSON-Datei mit allen Filmen holen und zu Filmen Parsen
            URL url = new URL("10.0.2.2:8080/getAllData");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
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

        }catch(Exception e){}
        return null;
    }//getFilme
}
