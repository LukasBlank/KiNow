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

    public String getNutzer() {
        try {
            URL url = new URL("10.0.2.2:8080/getNutzerbyName");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("name","falko");
            con.connect();
            int status = con.getResponseCode();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            String json=content.toString();
            json=json.replace("[","");
            json=json.replace("]","");
            JSONObject root = new JSONObject(json);
            String vorname= root.getString("vorname");
            // System.out.println(json.toString());
            System.out.println(vorname);
            return vorname;
        }catch(Exception e){}
        return "";
    }

    public String getNutzerbyID(String id) {
        try {
            URL url = new URL("10.0.2.2:8080/getNutzerbyID");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("id",id);
            con.connect();
            int status = con.getResponseCode();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            String json=content.toString();
            json=json.replace("[","");
            json=json.replace("]","");
            JSONObject root = new JSONObject(json);
            String vorname= root.getString("vorname");
            // System.out.println(json.toString());
            System.out.println(vorname);
            return vorname;
        }catch(Exception e){}
        return "";
    }

    public String getPerson() {
        try {
            URL url = new URL("10.0.2.2:8080/Person");
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
            json=json.replace("[","");
            json=json.replace("]","");
            System.out.println(json);
            return json;
        }catch(Exception e){}

        return "";
    }

    public List<Film> getFilme (){
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
            }
            in.close();
            String json=content.toString();
            Gson gson = new Gson();
            gson.fromJson(json,Film.class);


            json=json.replace("[","");
            json=json.replace("]","");
            System.out.println(json);
            return json;
        }catch(Exception e){}
    }//getFilme
}
