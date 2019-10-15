package fm.feuermania.dennis.kinow_test;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import lukas.classes.Film;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Requests {

    String ausgabe;
    ArrayList<lukas.classes.Film> filme;
    Object o;

    public Requests (){
        o = null;
        ausgabe = "";
        filme = new ArrayList<Film>();
    }//K

    public ArrayList<Film> getFilme (){
        ausgabe = "";

        //Connection zur Fachkonzeptschicht aufbauen
        OkHttpClient client = new OkHttpClient();
        String url = "http://94.16.123.237:8080/getFilme";
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
                ausgabe = e.getMessage();
            }//onFailure

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(response.isSuccessful()) {
                    //Wenn erfolgreich eine Rückgabe erfolgt ist, bekommenen String zurück zu Hashmaps parsen
                    ausgabe = response.body().string();
                    response.body().close();
                }//then
            }//onResponse;
        });


        //wait for the OkHttpRequest
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }//catch


        //variablen
            Map<String,Object> map = null;
            lukas.classes.Film film = new lukas.classes.Film();
            ArrayList<lukas.classes.Film> filme = new ArrayList<>();
            try {
                //map durchlaufen und einzelne Hashmaps holen
                map = new ObjectMapper().readValue(ausgabe, Map.class);
                for (Map.Entry<String,Object> entry : map.entrySet()){
                    Map<String,Object> data = (Map<String, Object>) entry.getValue();
                    //jede einzelne Hashmap dann zu einem Film parsen und zur Filmliste hinzufügen
                    film = new lukas.classes.Film();
                    for (Map.Entry<String,Object> e : data.entrySet()){
                        film.set(e.getKey(),e.getValue());
                    }//for
                    filme.add(film);
                }//for
            } catch (IOException e) {
                e.printStackTrace();
            }//catch
            return filme;
    }//getFilme


}
