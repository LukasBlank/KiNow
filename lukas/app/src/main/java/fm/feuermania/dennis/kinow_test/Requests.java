package fm.feuermania.dennis.kinow_test;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lukas.java_classes.Film;
import lukas.java_classes.Parser;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Requests {

    String ausgabe;
    Parser ps;
    ArrayList<Film> filme;
    Object o;

    public Requests (){
        o = null;
        ausgabe = "";
        ps = new Parser();
        filme = new ArrayList<Film>();
    }//K

    public ArrayList<Film> getFilme (){

        try {
            //Verbindung aufbauen
            URL url = new URL("http://94.16.123.237:8080/server");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            String response = con.getResponseMessage();
            String content = con.getContent().toString();
            return null;
            /**
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
             **/

        }catch(Exception e){
            String s = e.getMessage();
            return null;
        }//catch
    }//getFilme

    public String connect (){
        OkHttpClient client = new OkHttpClient();
        String url = "http://94.16.123.237:8080/getFilme";
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }//onFailure

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(response.isSuccessful()) {
                    o = response.body();
                    ausgabe = response.body().string();
                }//then
            }//onResponse;
        });
        return ausgabe;
    }//connect

}
