package fm.feuermania.dennis.kinow_test;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import com.google.gson.Gson;

import lukas.java_classes.Film;

public class Requests {

    public List<Film> getFilme (){
        try {
            List<Film> filme;

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
            }
            in.close();
            String json=content.toString();
            Gson gson = new Gson();
            gson.fromJson(json,Film.class);


            json=json.replace("[","");
            json=json.replace("]","");
            System.out.println(json);
        }catch(Exception e){}
        return null;
    }//getFilme
}
