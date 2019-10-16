package lukas.classes;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ThreadRequest extends Thread {

    private String erg;
    private String url;
    private boolean fertig;

    public void run (){
        fertig = false;
        OkHttpClient client = new OkHttpClient();
        if (url.length()==0)url = "http://94.16.123.237:8080/server";
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
                erg = e.getMessage();
                fertig = true;
            }//onFailure

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(response.isSuccessful()) {
                    erg = response.body().string();
                    fertig = true;
                    response.close();
                }//then
            }//onResponse;
        });
    }//run

    public void setUrl (String url){
        this.url = url;
    }//SetUrl

    public String getErg () {return erg;}

    public boolean isFertig () {return fertig;}

}
